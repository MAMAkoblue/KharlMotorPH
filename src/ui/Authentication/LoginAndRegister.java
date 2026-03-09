package ui.Authentication;

import ui.Authentication.Login;

/**
 *
 * @author Claire
 */
public class LoginAndRegister {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Initialize SQLite database and migrate CSVs if tables are empty
        try {
            DAO.Database db = DAO.Database.getInstance();
            db.init();
            db.migrateFromCsvIfEmpty();
        } catch (Exception e) {
            System.err.println("Database init error: " + e.getMessage());
        }
        // Start UI
        Login login = new Login();
        login.setVisible(true);
    }
    
}
