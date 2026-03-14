/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Model.UserAccount;
import Model.Permission;
import Model.Role;
import Model.RolePermission;

/**
 * Service layer for Role-Based Access Control (RBAC)
 * Follows the Service layer of the system architecture
 * Provides business rules and validation for permissions
 */
public class RBACService {
    
    private static RBACService instance;
    
    private RBACService() {}
    
    public static synchronized RBACService getInstance() {
        if (instance == null) {
            instance = new RBACService();
        }
        return instance;
    }
    
    /**
     * Check if a user has a specific permission
     * @param user the user to check
     * @param permission the permission to check for
     * @return true if the user has the permission
     */
    public boolean hasPermission(UserAccount user, Permission permission) {
        if (user == null || permission == null) {
            return false;
        }
        
        Role userRole = Role.fromString(user.getRole());
        return RolePermission.hasPermission(userRole, permission);
    }
    
    /**
     * Check if a user can access a specific feature
     * @param user the user to check
     * @param featureName the name of the feature
     * @return true if the user can access the feature
     */
    public boolean canAccessFeature(UserAccount user, String featureName) {
        if (user == null || featureName == null) {
            return false;
        }
        
        // Map feature names to permissions
        Permission requiredPermission = mapFeatureToPermission(featureName);
        if (requiredPermission == null) {
            return false; // Unknown feature
        }
        
        return hasPermission(user, requiredPermission);
    }
    
    /**
     * Get all permissions for a user
     * @param user the user
     * @return set of permissions
     */
    public java.util.Set<Permission> getUserPermissions(UserAccount user) {
        if (user == null) {
            return java.util.Collections.emptySet();
        }
        
        Role userRole = Role.fromString(user.getRole());
        return RolePermission.getPermissions(userRole);
    }
    
    /**
     * Check if a user can edit their own employee information
     * @param user the user making the request
     * @param targetEmployeeId the employee ID being edited
     * @return true if the user can edit the employee information
     */
    public boolean canEditEmployee(UserAccount user, String targetEmployeeId) {
        if (user == null || targetEmployeeId == null) {
            return false;
        }
        
        // Users can edit their own information if they have the basic permission
        if (targetEmployeeId.equals(user.getEmployeeID()) && 
            hasPermission(user, Permission.VIEW_OWN_EMPLOYEE_INFO)) {
            return true;
        }
        
        // HR and Admin can edit any employee
        return hasPermission(user, Permission.EDIT_EMPLOYEE);
    }
    
    /**
     * Check if a user can view specific employee information
     * @param user the user making the request
     * @param targetEmployeeId the employee ID being viewed
     * @return true if the user can view the employee information
     */
    public boolean canViewEmployee(UserAccount user, String targetEmployeeId) {
        if (user == null || targetEmployeeId == null) {
            return false;
        }
        
        // Users can view their own information
        if (targetEmployeeId.equals(user.getEmployeeID()) && 
            hasPermission(user, Permission.VIEW_OWN_EMPLOYEE_INFO)) {
            return true;
        }
        
        // HR, Admin, Finance, IT can view all employees
        return hasPermission(user, Permission.VIEW_ALL_EMPLOYEES);
    }
    
    /**
     * Check if a user can view specific payslip
     * @param user the user making the request
     * @param targetEmployeeId the employee ID of the payslip
     * @return true if the user can view the payslip
     */
    public boolean canViewPayslip(UserAccount user, String targetEmployeeId) {
        if (user == null || targetEmployeeId == null) {
            return false;
        }
        
        // Users can view their own payslip
        if (targetEmployeeId.equals(user.getEmployeeID()) && 
            hasPermission(user, Permission.VIEW_OWN_PAYSLIP)) {
            return true;
        }
        
        // HR, Admin, Finance can view all payslips
        return hasPermission(user, Permission.VIEW_ALL_PAYSLIPS);
    }
    
    /**
     * Map feature names to permissions
     * @param featureName the feature name
     * @return the required permission
     */
    private Permission mapFeatureToPermission(String featureName) {
        switch (featureName.toLowerCase()) {
            case "create_employee":
                return Permission.CREATE_EMPLOYEE;
            case "edit_employee":
                return Permission.EDIT_EMPLOYEE;
            case "delete_employee":
                return Permission.DELETE_EMPLOYEE;
            case "view_all_employees":
                return Permission.VIEW_ALL_EMPLOYEES;
            case "process_payroll":
                return Permission.PROCESS_PAYROLL;
            case "approve_payroll":
                return Permission.APPROVE_PAYROLL;
            case "view_all_payslips":
                return Permission.VIEW_ALL_PAYSLIPS;
            case "edit_salary_deduction":
                return Permission.EDIT_SALARY_DEDUCTION;
            case "approve_leave":
                return Permission.APPROVE_LEAVE;
            case "view_all_leave_requests":
                return Permission.VIEW_ALL_LEAVE_REQUESTS;
            case "create_leave_request":
                return Permission.CREATE_LEAVE_REQUEST;
            case "generate_reports":
                return Permission.GENERATE_REPORTS;
            case "view_financial_reports":
                return Permission.VIEW_FINANCIAL_REPORTS;
            case "system_maintenance":
                return Permission.SYSTEM_MAINTENANCE;
            case "create_user":
                return Permission.CREATE_USER;
            case "edit_user":
                return Permission.EDIT_USER;
            case "delete_user":
                return Permission.DELETE_USER;
            default:
                return null;
        }
    }
}