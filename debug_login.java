import java.sql.*;
import org.mindrot.jbcrypt.BCrypt;

public class debug_login {
    public static void main(String[] args) {
        try {
            // Load SQLite driver
            Class.forName("org.sqlite.JDBC");
            
            // Connect to database
            Connection conn = DriverManager.getConnection("jdbc:sqlite:KharlMotorPH.db");
            
            // Test the exact login process
            String username = "hr1";
            String password = "hrpw1";
            
            System.out.println("Testing login for: " + username);
            
            // Find user by username (like UserAccountDAO does)
            PreparedStatement ps = conn.prepareStatement("SELECT employee_id, username, password, role FROM users WHERE username = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                String storedHash = rs.getString("password");
                System.out.println("Found user: " + rs.getString("username"));
                System.out.println("Role: " + rs.getString("role"));
                System.out.println("Stored hash: " + storedHash);
                System.out.println("Testing password: " + password);
                
                // Test BCrypt verification
                try {
                    boolean isValid = BCrypt.checkpw(password, storedHash);
                    System.out.println("Password valid: " + isValid);
                    
                    if (!isValid) {
                        // Test with a fresh hash
                        String freshHash = BCrypt.hashpw(password, BCrypt.gensalt(12));
                        System.out.println("Fresh hash for same password: " + freshHash);
                        boolean freshValid = BCrypt.checkpw(password, freshHash);
                        System.out.println("Fresh hash valid: " + freshValid);
                    }
                } catch (Exception e) {
                    System.out.println("BCrypt error: " + e.getMessage());
                    e.printStackTrace();
                }
            } else {
                System.out.println("User not found!");
            }
            
            // Show all users for debugging
            System.out.println("\nAll users in database:");
            Statement stmt = conn.createStatement();
            ResultSet allUsers = stmt.executeQuery("SELECT username, role, password FROM users");
            while (allUsers.next()) {
                System.out.println("Username: " + allUsers.getString("username") + 
                                 ", Role: " + allUsers.getString("role") + 
                                 ", Password: " + allUsers.getString("password"));
            }
            
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
