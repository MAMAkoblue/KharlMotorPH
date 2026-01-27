package data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PayslipDAO {
    public static class Payslip {
        public Integer id; // auto
        public Integer payrollRunId;
        public String employeeId;
        public Double grossPay;
        public Double deductions;
        public Double netPay;
        public String details; // JSON/text
    }

    public List<Payslip> findByRun(int runId) throws SQLException {
        String sql = "SELECT id, payroll_run_id, employee_id, gross_pay, deductions, net_pay, details FROM payslips WHERE payroll_run_id = ? ORDER BY id DESC";
        try (PreparedStatement ps = Database.getInstance().getConnection().prepareStatement(sql)) {
            ps.setInt(1, runId);
            try (ResultSet rs = ps.executeQuery()) {
                List<Payslip> list = new ArrayList<>();
                while (rs.next()) list.add(map(rs));
                return list;
            }
        }
    }

    public boolean create(Payslip p) throws SQLException {
        String sql = "INSERT INTO payslips(payroll_run_id, employee_id, gross_pay, deductions, net_pay, details) VALUES(?,?,?,?,?,?)";
        try (PreparedStatement ps = Database.getInstance().getConnection().prepareStatement(sql)) {
            ps.setInt(1, p.payrollRunId);
            ps.setString(2, p.employeeId);
            if (p.grossPay == null) ps.setNull(3, Types.REAL); else ps.setDouble(3, p.grossPay);
            if (p.deductions == null) ps.setNull(4, Types.REAL); else ps.setDouble(4, p.deductions);
            if (p.netPay == null) ps.setNull(5, Types.REAL); else ps.setDouble(5, p.netPay);
            ps.setString(6, p.details);
            return ps.executeUpdate() == 1;
        }
    }

    public boolean deleteByRun(int runId) throws SQLException {
        String sql = "DELETE FROM payslips WHERE payroll_run_id = ?";
        try (PreparedStatement ps = Database.getInstance().getConnection().prepareStatement(sql)) {
            ps.setInt(1, runId);
            return ps.executeUpdate() > 0;
        }
    }

    private static Payslip map(ResultSet rs) throws SQLException {
        Payslip p = new Payslip();
        p.id = rs.getInt("id");
        p.payrollRunId = rs.getInt("payroll_run_id");
        p.employeeId = rs.getString("employee_id");
        double gp = rs.getDouble("gross_pay");
        p.grossPay = rs.wasNull() ? null : gp;
        double dd = rs.getDouble("deductions");
        p.deductions = rs.wasNull() ? null : dd;
        double np = rs.getDouble("net_pay");
        p.netPay = rs.wasNull() ? null : np;
        p.details = rs.getString("details");
        return p;
    }
}
