/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.SupportingUI;

import javax.swing.*;
import Services.SessionManager;
import Model.Permission;

/**
 * Helper class for UI components to easily implement RBAC
 * Follows the UI layer of the system architecture
 */
public class RBACUIHelper {
    
    /**
     * Enable or disable a component based on user permission
     * @param component the component to control
     * @param permission the required permission
     */
    public static void setComponentEnabled(JComponent component, Permission permission) {
        boolean hasPermission = SessionManager.getInstance().hasPermission(permission);
        component.setEnabled(hasPermission);
    }
    
    /**
     * Show or hide a component based on user permission
     * @param component the component to control
     * @param permission the required permission
     */
    public static void setComponentVisible(JComponent component, Permission permission) {
        boolean hasPermission = SessionManager.getInstance().hasPermission(permission);
        component.setVisible(hasPermission);
    }
    
    /**
     * Enable or disable a menu item based on user permission
     * @param menuItem the menu item to control
     * @param permission the required permission
     */
    public static void setMenuItemEnabled(JMenuItem menuItem, Permission permission) {
        boolean hasPermission = SessionManager.getInstance().hasPermission(permission);
        menuItem.setEnabled(hasPermission);
    }
    
    /**
     * Enable or disable a button based on feature access
     * @param button the button to control
     * @param featureName the feature name
     */
    public static void setButtonEnabled(JButton button, String featureName) {
        boolean canAccess = SessionManager.getInstance().canAccessFeature(featureName);
        button.setEnabled(canAccess);
    }
    
    /**
     * Show or hide a button based on feature access
     * @param button the button to control
     * @param featureName the feature name
     */
    public static void setButtonVisible(JButton button, String featureName) {
        boolean canAccess = SessionManager.getInstance().canAccessFeature(featureName);
        button.setVisible(canAccess);
    }
    
    /**
     * Check if current user can edit specific employee information
     * @param targetEmployeeId the employee ID being edited
     * @return true if the current user can edit the employee information
     */
    public static boolean canEditEmployee(String targetEmployeeId) {
        return SessionManager.getInstance().canEditEmployee(targetEmployeeId);
    }
    
    /**
     * Check if current user can view specific employee information
     * @param targetEmployeeId the employee ID being viewed
     * @return true if the current user can view the employee information
     */
    public static boolean canViewEmployee(String targetEmployeeId) {
        return SessionManager.getInstance().canViewEmployee(targetEmployeeId);
    }
    
    /**
     * Check if current user can view specific payslip
     * @param targetEmployeeId the employee ID of the payslip
     * @return true if the current user can view the payslip
     */
    public static boolean canViewPayslip(String targetEmployeeId) {
        return SessionManager.getInstance().canViewPayslip(targetEmployeeId);
    }
    
    /**
     * Show an access denied message
     * @param parent the parent component
     */
    public static void showAccessDeniedMessage(JComponent parent) {
        JOptionPane.showMessageDialog(parent, 
            "Access Denied: You don't have permission to perform this action.", 
            "Access Denied", 
            JOptionPane.WARNING_MESSAGE);
    }
    
    /**
     * Configure a button for employee editing with RBAC
     * @param button the button to configure
     * @param targetEmployeeId the employee ID being edited
     */
    public static void configureEditEmployeeButton(JButton button, String targetEmployeeId) {
        boolean canEdit = canEditEmployee(targetEmployeeId);
        button.setEnabled(canEdit);
        
        if (!canEdit) {
            button.setToolTipText("You don't have permission to edit this employee");
        }
    }
    
    /**
     * Configure a button for employee viewing with RBAC
     * @param button the button to configure
     * @param targetEmployeeId the employee ID being viewed
     */
    public static void configureViewEmployeeButton(JButton button, String targetEmployeeId) {
        boolean canView = canViewEmployee(targetEmployeeId);
        button.setEnabled(canView);
        
        if (!canView) {
            button.setToolTipText("You don't have permission to view this employee");
        }
    }
    
    /**
     * Get the current user display name for UI
     * @return the display name
     */
    public static String getCurrentUserDisplayName() {
        return SessionManager.getInstance().getCurrentUserDisplayName();
    }
    
    /**
     * Add logout functionality to a button
     * @param button the logout button
     * @param parentFrame the parent frame to close
     */
    public static void configureLogoutButton(JButton button, JFrame parentFrame) {
        button.addActionListener(e -> {
            SessionManager.getInstance().logout();
            parentFrame.dispose();
            // Show login screen again
            new ui.Authentication.Login().setVisible(true);
        });
    }
}
