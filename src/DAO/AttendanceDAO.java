package DAO;

import DAO.Database;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDAO {
    public static class Attendance {
        public Integer id; // nullable for new rows
        public String employeeId;
        public String date; // YYYY-MM-DD or similar
        public String timeIn;
        public String timeOut;
        public Double hoursWorked;
        public Double overtimeHours;
        public String notes;
    }

    public List<Attendance> findAll() throws SQLException {
        String sql = "SELECT id, employee_id, date, time_in, time_out, hours_worked, overtime_hours, notes FROM attendance ORDER BY date DESC, id DESC";
        try (PreparedStatement ps = Database.getInstance().getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            List<Attendance> list = new ArrayList<>();
            while (rs.next()) {
                list.add(map(rs));
            }
            return list;
        }
    }

    public List<Attendance> findByEmployee(String employeeId) throws SQLException {
        String sql = "SELECT id, employee_id, date, time_in, time_out, hours_worked, overtime_hours, notes FROM attendance WHERE employee_id = ? ORDER BY date DESC, id DESC";
        try (PreparedStatement ps = Database.getInstance().getConnection().prepareStatement(sql)) {
            ps.setString(1, employeeId);
            try (ResultSet rs = ps.executeQuery()) {
                List<Attendance> list = new ArrayList<>();
                while (rs.next()) list.add(map(rs));
                return list;
            }
        }
    }

    public boolean create(Attendance a) throws SQLException {
        String sql = "INSERT INTO attendance(employee_id, date, time_in, time_out, hours_worked, overtime_hours, notes) VALUES(?,?,?,?,?,?,?)";
        try (PreparedStatement ps = Database.getInstance().getConnection().prepareStatement(sql)) {
            bind(ps, a);
            return ps.executeUpdate() == 1;
        }
    }

    public boolean update(Attendance a) throws SQLException {
        if (a.id == null) throw new IllegalArgumentException("Attendance.id required for update");
        String sql = "UPDATE attendance SET employee_id=?, date=?, time_in=?, time_out=?, hours_worked=?, overtime_hours=?, notes=? WHERE id=?";
        try (PreparedStatement ps = Database.getInstance().getConnection().prepareStatement(sql)) {
            bind(ps, a);
            ps.setInt(8, a.id);
            return ps.executeUpdate() == 1;
        }
    }

    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM attendance WHERE id=?";
        try (PreparedStatement ps = Database.getInstance().getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() == 1;
        }
    }

    private static void bind(PreparedStatement ps, Attendance a) throws SQLException {
        ps.setString(1, a.employeeId);
        ps.setString(2, a.date);
        ps.setString(3, a.timeIn);
        ps.setString(4, a.timeOut);
        if (a.hoursWorked == null) ps.setNull(5, Types.REAL); else ps.setDouble(5, a.hoursWorked);
        if (a.overtimeHours == null) ps.setNull(6, Types.REAL); else ps.setDouble(6, a.overtimeHours);
        ps.setString(7, a.notes);
    }

    private static Attendance map(ResultSet rs) throws SQLException {
        Attendance a = new Attendance();
        a.id = rs.getInt("id");
        a.employeeId = rs.getString("employee_id");
        a.date = rs.getString("date");
        a.timeIn = rs.getString("time_in");
        a.timeOut = rs.getString("time_out");
        double hw = rs.getDouble("hours_worked");
        a.hoursWorked = rs.wasNull() ? null : hw;
        double ot = rs.getDouble("overtime_hours");
        a.overtimeHours = rs.wasNull() ? null : ot;
        a.notes = rs.getString("notes");
        return a;
    }
}
