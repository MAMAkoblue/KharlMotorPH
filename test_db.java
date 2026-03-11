import java.sql.*;
import data.Database;

public class test_db {
    public static void main(String[] args) {
        try {
            Database db = Database.getInstance();
            Connection conn = db.getConnection();
            
            // Check users table
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT username, password, role FROM users LIMIT 5");
            
            System.out.println("Users in database:");
            while (rs.next()) {
                System.out.println("Username: " + rs.getString("username") + 
                                 ", Role: " + rs.getString("role") + 
                                 ", Password hash: " + rs.getString("password").substring(0, 20) + "...");
            }
            
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
