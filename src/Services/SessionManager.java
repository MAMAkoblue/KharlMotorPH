/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Model.UserAccount;
import Model.Permission;
import Model.Role;
import java.util.Set;

/**
 * Session manager to track the currently logged-in user and their permissions
 * Follows the system architecture as a utility class
 */
public class SessionManager {
    
    private static SessionManager instance;
    private UserAccount currentUser;
    
    private SessionManager() {}
    
    public static synchronized SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }
    
    /**
     * Set the current logged-in user
     * @param user the user to set as current
     */
    public void setCurrentUser(UserAccount user) {
        this.currentUser = user;
    }
    
    /**
     * Get the current logged-in user
     * @return the current user, or null if no user is logged in
     */
    public UserAccount getCurrentUser() {
        return currentUser;
    }
    
    /**
     * Check if any user is currently logged in
     * @return true if a user is logged in
     */
    public boolean isLoggedIn() {
        return currentUser != null;
    }
    
    /**
     * Logout the current user
     */
    public void logout() {
        currentUser = null;
    }
    
    /**
     * Get the current user's role
     * @return the current user's role, or null if no user is logged in
     */
    public Role getCurrentUserRole() {
        return currentUser != null ? currentUser.getRoleEnum() : null;
    }
    
    /**
     * Get the current user's employee ID
     * @return the current user's employee ID, or null if no user is logged in
     */
    public String getCurrentUserEmployeeId() {
        return currentUser != null ? currentUser.getEmployeeID() : null;
    }
    
    /**
     * Check if the current user has a specific permission
     * @param permission the permission to check
     * @return true if the current user has the permission
     */
    public boolean hasPermission(Permission permission) {
        return currentUser != null && currentUser.hasPermission(permission);
    }
    
    /**
     * Check if the current user can access a specific feature
     * @param featureName the feature name
     * @return true if the current user can access the feature
     */
    public boolean canAccessFeature(String featureName) {
        return currentUser != null && currentUser.canAccessFeature(featureName);
    }
    
    /**
     * Get all permissions for the current user
     * @return set of permissions, or empty set if no user is logged in
     */
    public Set<Permission> getCurrentUserPermissions() {
        return currentUser != null ? currentUser.getPermissions() : Set.of();
    }
    
    /**
     * Check if the current user can edit specific employee information
     * @param targetEmployeeId the employee ID being edited
     * @return true if the current user can edit the employee information
     */
    public boolean canEditEmployee(String targetEmployeeId) {
        return currentUser != null && currentUser.canEditEmployee(targetEmployeeId);
    }
    
    /**
     * Check if the current user can view specific employee information
     * @param targetEmployeeId the employee ID being viewed
     * @return true if the current user can view the employee information
     */
    public boolean canViewEmployee(String targetEmployeeId) {
        return currentUser != null && currentUser.canViewEmployee(targetEmployeeId);
    }
    
    /**
     * Check if the current user can view specific payslip
     * @param targetEmployeeId the employee ID of the payslip
     * @return true if the current user can view the payslip
     */
    public boolean canViewPayslip(String targetEmployeeId) {
        return currentUser != null && currentUser.canViewPayslip(targetEmployeeId);
    }
    
    /**
     * Check if the current user is an admin
     * @return true if the current user is an admin
     */
    public boolean isAdmin() {
        return currentUser != null && currentUser.isAdmin();
    }
    
    /**
     * Check if the current user is HR
     * @return true if the current user is HR
     */
    public boolean isHR() {
        return currentUser != null && currentUser.isHR();
    }
    
    /**
     * Check if the current user is Finance
     * @return true if the current user is Finance
     */
    public boolean isFinance() {
        return currentUser != null && currentUser.isFinance();
    }
    
    /**
     * Check if the current user is IT
     * @return true if the current user is IT
     */
    public boolean isIT() {
        return currentUser != null && currentUser.isIT();
    }
    
    /**
     * Check if the current user is a regular employee
     * @return true if the current user is an employee
     */
    public boolean isEmployee() {
        return currentUser != null && currentUser.isEmployee();
    }
    
    /**
     * Get display name for the current user
     * @return the display name, or "Guest" if no user is logged in
     */
    public String getCurrentUserDisplayName() {
        if (currentUser == null) {
            return "Guest";
        }
        Role role = currentUser.getRoleEnum();
        return currentUser.getUsername() + " (" + role.getDisplayName() + ")";
    }
}