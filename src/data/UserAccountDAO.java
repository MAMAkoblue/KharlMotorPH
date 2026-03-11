package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import loginandregister.UserAccount;

public class UserAccountDAO {

    public UserAccount findByUsernameAndPassword(String username, String password) throws SQLException {
        // First find user by username (password validation is done in validateLogin method)
        return findByUsername(username);
    }

    public UserAccount findByUsername(String username) throws SQLException {
        String sql = "SELECT employee_id, username, password, role FROM users WHERE username = ?";
        Connection conn = Database.getInstance().getConnection();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    UserAccount ua = new UserAccount();
                    ua.setEmployeeID(rs.getString("employee_id"));
                    ua.setUsername(rs.getString("username"));
                    ua.setPassword(rs.getString("password"));
                    ua.setRole(rs.getString("role"));
                    return ua;
                }
            }
        }
        return null;
    }

    public boolean createUser(UserAccount ua) throws SQLException {
        String sql = "INSERT INTO users(employee_id, username, password, role) VALUES(?,?,?,?)";
        Connection conn = Database.getInstance().getConnection();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ua.getEmployeeID());
            ps.setString(2, ua.getUsername());
            ps.setString(3, ua.getPassword());
            ps.setString(4, ua.getRole() == null ? "Employee" : ua.getRole());
            return ps.executeUpdate() == 1;
        }
    }

    public boolean updatePasswordByUsername(String username, String newHashedPassword) throws SQLException {
        String sql = "UPDATE users SET password = ? WHERE username = ?";
        Connection conn = Database.getInstance().getConnection();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newHashedPassword);
            ps.setString(2, username);
            return ps.executeUpdate() == 1;
        }
    }
}
