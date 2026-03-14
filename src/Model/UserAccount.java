/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Services.RBACService;
import java.util.Set;

/**
 *
 * @author Claire
 */
public class UserAccount {
    private String username;
    private String password;
    private String employeeID;
    private String role;
    
    public UserAccount() {}
    
    // getters
    public String getUsername() {
        return this.username;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public String getEmployeeID() {
        return this.employeeID;
    }
    
    // +
    public String getRole() {
        return this.role;
    }
    
    // setters
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setPassword(String pw) {
        this.password = pw;
    }
    
    public void setEmployeeID(String id) {
        this.employeeID = id;
    }
    
    // +
    public void setRole(String role) {
        this.role = role;
    }
    
    /**
     * Get the user's role as a Role enum
     * @return the user's role
     */
    public Role getRoleEnum() {
        return Role.fromString(this.role);
    }
    
    /**
     * Check if the user has a specific permission
     * @param permission the permission to check
     * @return true if the user has the permission
     */
    public boolean hasPermission(Permission permission) {
        return RBACService.getInstance().hasPermission(this, permission);
    }
    
    /**
     * Check if the user can access a specific feature
     * @param featureName the feature name
     * @return true if the user can access the feature
     */
    public boolean canAccessFeature(String featureName) {
        return RBACService.getInstance().canAccessFeature(this, featureName);
    }
    
    /**
     * Get all permissions for this user
     * @return set of permissions
     */
    public Set<Permission> getPermissions() {
        return RBACService.getInstance().getUserPermissions(this);
    }
    
    /**
     * Check if the user can edit specific employee information
     * @param targetEmployeeId the employee ID being edited
     * @return true if the user can edit the employee information
     */
    public boolean canEditEmployee(String targetEmployeeId) {
        return RBACService.getInstance().canEditEmployee(this, targetEmployeeId);
    }
    
    /**
     * Check if the user can view specific employee information
     * @param targetEmployeeId the employee ID being viewed
     * @return true if the user can view the employee information
     */
    public boolean canViewEmployee(String targetEmployeeId) {
        return RBACService.getInstance().canViewEmployee(this, targetEmployeeId);
    }
    
    /**
     * Check if the user can view specific payslip
     * @param targetEmployeeId the employee ID of the payslip
     * @return true if the user can view the payslip
     */
    public boolean canViewPayslip(String targetEmployeeId) {
        return RBACService.getInstance().canViewPayslip(this, targetEmployeeId);
    }
    
    /**
     * Check if the user is an admin
     * @return true if the user is an admin
     */
    public boolean isAdmin() {
        return Role.ADMIN.equals(getRoleEnum());
    }
    
    /**
     * Check if the user is HR
     * @return true if the user is HR
     */
    public boolean isHR() {
        return Role.HR.equals(getRoleEnum());
    }
    
    /**
     * Check if the user is Finance
     * @return true if the user is Finance
     */
    public boolean isFinance() {
        return Role.FINANCE.equals(getRoleEnum());
    }
    
    /**
     * Check if the user is IT
     * @return true if the user is IT
     */
    public boolean isIT() {
        return Role.IT.equals(getRoleEnum());
    }
    
    /**
     * Check if the user is a regular employee
     * @return true if the user is an employee
     */
    public boolean isEmployee() {
        return Role.EMPLOYEE.equals(getRoleEnum());
    }
}
