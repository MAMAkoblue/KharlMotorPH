package model;

/**
 * Enum representing all possible roles in the KharlMotorPH Payroll System
 * Follows the Model layer of the system architecture
 */
public enum Role {
    ADMIN("Administrator", "Full system access and user management"),
    HR("Human Resources", "Employee management, leave requests, payroll processing"),
    FINANCE("Finance", "Financial reports, salary deductions, payroll approval"),
    IT("IT Support", "System maintenance, user account management, technical support"),
    EMPLOYEE("Employee", "Personal information, payslips, leave requests");
    
    private final String displayName;
    private final String description;
    
    Role(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public String getDescription() {
        return description;
    }
    
    /**
     * Convert string to Role enum
     * @param roleString the string representation
     * @return corresponding Role or EMPLOYEE as default
     */
    public static Role fromString(String roleString) {
        if (roleString == null) return EMPLOYEE;
        
        try {
            return Role.valueOf(roleString.toUpperCase());
        } catch (IllegalArgumentException e) {
            return EMPLOYEE;
        }
    }
}
