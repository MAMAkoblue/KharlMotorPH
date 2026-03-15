/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.EnumSet;
import java.util.Set;

/**
 * Defines the permissions assigned to each role
 * Follows the Model layer of the system architecture
 */
public class RolePermission {
    
    /**
     * Get all permissions for a specific role
     * @param role the role to get permissions for
     * @return set of permissions for the role
     */
    public static Set<Permission> getPermissions(Role role) {
        switch (role) {
            case ADMIN:
                return getAdminPermissions();
            case HR:
                return getHRPermissions();
            case FINANCE:
                return getFinancePermissions();
            case IT:
                return getITPermissions();
            case EMPLOYEE:
                return getEmployeePermissions();
            default:
                return EnumSet.noneOf(Permission.class);
        }
    }
    
    /**
     * Check if a role has a specific permission
     * @param role the role to check
     * @param permission the permission to check for
     * @return true if the role has the permission
     */
    public static boolean hasPermission(Role role, Permission permission) {
        return getPermissions(role).contains(permission);
    }
    
    private static Set<Permission> getAdminPermissions() {
        return EnumSet.allOf(Permission.class);
    }
    
    private static Set<Permission> getHRPermissions() {
        return EnumSet.of(
            // Employee Management
            Permission.CREATE_EMPLOYEE,Permission.EDIT_EMPLOYEE, Permission.VIEW_ALL_EMPLOYEES,
            // Leave Management
            Permission.APPROVE_LEAVE, Permission.VIEW_ALL_LEAVE_REQUESTS,
            // Reports
            Permission.GENERATE_REPORTS, Permission.VIEW_ATTENDANCE_REPORTS,
            // Basic Access
            Permission.LOGIN, Permission.VIEW_DASHBOARD, Permission.CREATE_USER
        );
    }
    
    private static Set<Permission> getFinancePermissions() {
        return EnumSet.of(
            // Payroll Management
            Permission.APPROVE_PAYROLL, Permission.VIEW_ALL_PAYSLIPS, Permission.EDIT_SALARY_DEDUCTION,
            // Reports
            Permission.GENERATE_REPORTS, Permission.VIEW_FINANCIAL_REPORTS,
            // Employee Management (read-only)
            Permission.VIEW_ALL_EMPLOYEES,
            // Basic Access
            Permission.LOGIN, Permission.VIEW_DASHBOARD
        );
    }
    
    private static Set<Permission> getITPermissions() {
        return EnumSet.of(
            // User Management
            Permission.CREATE_USER, Permission.EDIT_USER, Permission.VIEW_ALL_USERS,
            // Employee Management (read-only)
            Permission.VIEW_ALL_EMPLOYEES,
            // System Administration
            Permission.SYSTEM_MAINTENANCE, Permission.VIEW_SYSTEM_LOGS, Permission.MANAGE_PERMISSIONS,
            // Basic Access
            Permission.LOGIN, Permission.VIEW_DASHBOARD
        );
    }
    
    private static Set<Permission> getEmployeePermissions() {
        return EnumSet.of(
            // Employee Management (self only)
            Permission.VIEW_OWN_EMPLOYEE_INFO,
            // Payroll Management (self only)
            Permission.VIEW_OWN_PAYSLIP,
            // Leave Management (self only)
            Permission.CREATE_LEAVE_REQUEST, Permission.VIEW_OWN_LEAVE,
            // Basic Access
            Permission.LOGIN, Permission.VIEW_DASHBOARD
        );
    }
}