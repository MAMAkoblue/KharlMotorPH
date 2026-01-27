package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

/**
 * SQLite database helper (single-file DB: KharlMotorPH.db)
 * - Creates tables if they don't exist
 * - Optional one-time CSV migration for users and employees
 */
public class Database {
    private static final String DB_URL = "jdbc:sqlite:KharlMotorPH.db";
    private static Database INSTANCE;
    private Connection connection;

    private Database() {}

    public static synchronized Database getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Database();
        }
        return INSTANCE;
    }

    public synchronized Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            // Driver class is auto-registered by modern sqlite-jdbc, but safe to attempt load
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException ignored) {}
            connection = DriverManager.getConnection(DB_URL);
        }
        return connection;
    }

    public void init() throws SQLException {
        try (Statement st = getConnection().createStatement()) {
            st.executeUpdate("CREATE TABLE IF NOT EXISTS users (" +
                    "employee_id TEXT PRIMARY KEY, " +
                    "username TEXT UNIQUE NOT NULL, " +
                    "password TEXT NOT NULL, " +
                    "role TEXT NOT NULL DEFAULT 'Employee'" +
                    ")");

            st.executeUpdate("CREATE TABLE IF NOT EXISTS employees (" +
                    "id TEXT PRIMARY KEY, " +
                    "last_name TEXT, first_name TEXT, birthday TEXT, address TEXT, phone_number TEXT, " +
                    "sss_number TEXT, philhealth_number TEXT, tin_number TEXT, pagibig_number TEXT, status TEXT, " +
                    "position TEXT, immediate_supervisor TEXT, basic_salary TEXT, rice_subsidy TEXT, phone_allowance TEXT, " +
                    "clothing_allowance TEXT, gross_semi_monthly_rate TEXT, hourly_rate TEXT" +
                    ")");

            // Attendance table for daily time records
            st.executeUpdate("CREATE TABLE IF NOT EXISTS attendance (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "employee_id TEXT NOT NULL, " +
                    "date TEXT NOT NULL, " +
                    "time_in TEXT, " +
                    "time_out TEXT, " +
                    "hours_worked REAL, " +
                    "overtime_hours REAL, " +
                    "notes TEXT" +
                    ")");

            // Payroll run headers
            st.executeUpdate("CREATE TABLE IF NOT EXISTS payroll_runs (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "period_start TEXT NOT NULL, " +
                    "period_end TEXT NOT NULL, " +
                    "run_date TEXT NOT NULL, " +
                    "status TEXT NOT NULL DEFAULT 'OPEN'" +
                    ")");

            // Payslips (results per employee per run)
            st.executeUpdate("CREATE TABLE IF NOT EXISTS payslips (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "payroll_run_id INTEGER NOT NULL, " +
                    "employee_id TEXT NOT NULL, " +
                    "gross_pay REAL, " +
                    "deductions REAL, " +
                    "net_pay REAL, " +
                    "details TEXT" +
                    ")");
        }
    }

    /**
     * One-time migration from CSV files if tables are empty.
     */
    public void migrateFromCsvIfEmpty() {
        try {
            if (isTableEmpty("users")) {
                migrateUsersCsv("LoginCredentials.csv");
            }
            if (isTableEmpty("employees")) {
                migrateEmployeesCsv("MotorPH.csv");
            }
        } catch (Exception e) {
            // Log to console; avoid crashing the app on migration failure
            System.err.println("CSV migration error: " + e.getMessage());
        }
    }

    private boolean isTableEmpty(String table) throws SQLException {
        String sql = "SELECT COUNT(1) FROM " + table;
        try (Statement st = getConnection().createStatement(); ResultSet rs = st.executeQuery(sql)) {
            return rs.next() && rs.getInt(1) == 0;
        }
    }

    private void migrateUsersCsv(String path) throws IOException, SQLException {
        File f = new File(path);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line; String delim = ",";
            String sql = "INSERT OR IGNORE INTO users(employee_id, username, password, role) VALUES (?,?,?,?)";
            try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
                while ((line = br.readLine()) != null) {
                    String[] u = line.split(delim);
                    if (u.length < 3) continue;
                    String role = (u.length >= 4 && u[3] != null && !u[3].isEmpty()) ? u[3] : "Employee";
                    String hashedPassword = BCrypt.hashpw(u[2], BCrypt.gensalt(12));
                    ps.setString(1, u[0]);
                    ps.setString(2, u[1]);
                    ps.setString(3, hashedPassword);
                    ps.setString(4, role);
                    ps.addBatch();
                }
                ps.executeBatch();
            }
        }
    }

    private void migrateEmployeesCsv(String path) throws IOException, SQLException {
        File f = new File(path);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line; String delim = ",";
            String sql = "INSERT OR IGNORE INTO employees(" +
                    "id,last_name,first_name,birthday,address,phone_number,sss_number,philhealth_number,tin_number,pagibig_number," +
                    "status,position,immediate_supervisor,basic_salary,rice_subsidy,phone_allowance,clothing_allowance,gross_semi_monthly_rate,hourly_rate" +
                    ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
                while ((line = br.readLine()) != null) {
                    String[] r = splitKeepEmpty(line, delim, 19);
                    if (r.length < 19) continue;
                    for (int i = 0; i < 19; i++) {
                        ps.setString(i + 1, r[i]);
                    }
                    ps.addBatch();
                }
                ps.executeBatch();
            }
        }
    }

    private static String[] splitKeepEmpty(String line, String delim, int expected) {
        String[] raw = line.split(delim, -1);
        if (raw.length >= expected) return raw;
        List<String> out = new ArrayList<>();
        for (String s : raw) out.add(s);
        while (out.size() < expected) out.add("");
        return out.toArray(new String[0]);
    }
}
