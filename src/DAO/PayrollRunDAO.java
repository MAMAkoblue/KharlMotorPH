package DAO;

import DAO.Database;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PayrollRunDAO {
    public static class PayrollRun {
        public Integer id; // auto
        public String periodStart; // YYYY-MM-DD
        public String periodEnd;   // YYYY-MM-DD
        public String runDate;     // YYYY-MM-DD
        public String status;      // OPEN/CLOSED
    }

    public List<PayrollRun> findAll() throws SQLException {
        String sql = "SELECT id, period_start, period_end, run_date, status FROM payroll_runs ORDER BY id DESC";
        try (PreparedStatement ps = Database.getInstance().getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            List<PayrollRun> list = new ArrayList<>();
            while (rs.next()) list.add(map(rs));
            return list;
        }
    }

    public boolean create(PayrollRun pr) throws SQLException {
        String sql = "INSERT INTO payroll_runs(period_start, period_end, run_date, status) VALUES(?,?,?,?)";
        try (PreparedStatement ps = Database.getInstance().getConnection().prepareStatement(sql)) {
            ps.setString(1, pr.periodStart);
            ps.setString(2, pr.periodEnd);
            ps.setString(3, pr.runDate);
            ps.setString(4, pr.status == null ? "OPEN" : pr.status);
            return ps.executeUpdate() == 1;
        }
    }

    public boolean closeRun(int id) throws SQLException {
        String sql = "UPDATE payroll_runs SET status='CLOSED' WHERE id=?";
        try (PreparedStatement ps = Database.getInstance().getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() == 1;
        }
    }

    private static PayrollRun map(ResultSet rs) throws SQLException {
        PayrollRun pr = new PayrollRun();
        pr.id = rs.getInt("id");
        pr.periodStart = rs.getString("period_start");
        pr.periodEnd = rs.getString("period_end");
        pr.runDate = rs.getString("run_date");
        pr.status = rs.getString("status");
        return pr;
    }
}
