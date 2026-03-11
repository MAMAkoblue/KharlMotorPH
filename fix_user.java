import java.sql.*;
import org.mindrot.jbcrypt.BCrypt;

public class fix_user {
    public static void main(String[] args) {
        try {
            // Load SQLite driver
            Class.forName("org.sqlite.JDBC");
            
            // Connect to database
            Connection conn = DriverManager.getConnection("jdbc:sqlite:KharlMotorPH.db");
            
            // Create users table
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS users (" +
                    "employee_id TEXT PRIMARY KEY, " +
                    "username TEXT UNIQUE NOT NULL, " +
                    "password TEXT NOT NULL, " +
                    "role TEXT NOT NULL DEFAULT 'Employee'" +
                    ")");
            
            // Insert test users with proper BCrypt hashes
            String sql = "INSERT OR REPLACE INTO users(employee_id, username, password, role) VALUES (?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            // Admin user
            ps.setString(1, "admin1");
            ps.setString(2, "admin1");
            ps.setString(3, BCrypt.hashpw("adminpw1", BCrypt.gensalt(12)));
            ps.setString(4, "Admin");
            ps.addBatch();
            
            // HR user
            ps.setString(1, "hr1");
            ps.setString(2, "hr1");
            ps.setString(3, BCrypt.hashpw("hrpw1", BCrypt.gensalt(12)));
            ps.setString(4, "HR");
            ps.addBatch();
            
            // Finance user
            ps.setString(1, "finance1");
            ps.setString(2, "finance1");
            ps.setString(3, BCrypt.hashpw("financepw1", BCrypt.gensalt(12)));
            ps.setString(4, "Finance");
            ps.addBatch();
            
            // IT user
            ps.setString(1, "it1");
            ps.setString(2, "it1");
            ps.setString(3, BCrypt.hashpw("itpw1", BCrypt.gensalt(12)));
            ps.setString(4, "IT");
            ps.addBatch();
            
            // Employee user
            ps.setString(1, "1");
            ps.setString(2, "username1");
            ps.setString(3, BCrypt.hashpw("pw1", BCrypt.gensalt(12)));
            ps.setString(4, "Employee");
            ps.addBatch();
            
            ps.executeBatch();
            
            // Verify the users were inserted
            ResultSet rs = stmt.executeQuery("SELECT username, role FROM users");
            System.out.println("Users created:");
            while (rs.next()) {
                System.out.println("Username: " + rs.getString("username") + ", Role: " + rs.getString("role"));
            }
            
            conn.close();
            System.out.println("Test users created successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
