import java.sql.*;

public class simple_db_test {
    public static void main(String[] args) {
        try {
            // Load SQLite driver
            Class.forName("org.sqlite.JDBC");
            
            // Connect to database
            Connection conn = DriverManager.getConnection("jdbc:sqlite:KharlMotorPH.db");
            
            // Check users table
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT username, password, role FROM users LIMIT 5");
            
            System.out.println("Users in database:");
            while (rs.next()) {
                String username = rs.getString("username");
                String role = rs.getString("role");
                String password = rs.getString("password");
                System.out.println("Username: " + username + ", Role: " + role + ", Password: " + password);
            }
            
            // Test BCrypt on a simple password
            System.out.println("\nTesting BCrypt:");
            String plainPassword = "adminpw1";
            String hashedPassword = "$2a$12$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy"; // Example hash
            System.out.println("Plain password: " + plainPassword);
            System.out.println("Testing against hash: " + hashedPassword);
            
            try {
                boolean matches = org.mindrot.jbcrypt.BCrypt.checkpw(plainPassword, hashedPassword);
                System.out.println("Password matches: " + matches);
            } catch (Exception e) {
                System.out.println("BCrypt error: " + e.getMessage());
            }
            
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
