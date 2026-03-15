# RBAC Access Control and Permissions

## Overview

The KharlMotorPH Payroll System implements Role-Based Access Control (RBAC) to ensure secure and appropriate access to system features based on user roles. Each role has specific permissions that define what actions they can perform within the system.

## User Roles and Their Permissions

### 1. Administrator (ADMIN)
**Full system access and user management**

#### User Management
- ✅ **CREATE_USER** - Create new user accounts
- ✅ **EDIT_USER** - Edit existing user accounts  
- ✅ **DELETE_USER** - Delete user accounts
- ✅ **VIEW_ALL_USERS** - View all user accounts

#### Employee Management
- ✅ **CREATE_EMPLOYEE** - Create new employee records
- ✅ **EDIT_EMPLOYEE** - Edit employee records
- ✅ **DELETE_EMPLOYEE** - Delete employee records
- ✅ **VIEW_ALL_EMPLOYEES** - View all employee records
- ✅ **VIEW_OWN_EMPLOYEE_INFO** - View own employee information

#### Payroll Management
- ✅ **PROCESS_PAYROLL** - Process payroll runs
- ✅ **APPROVE_PAYROLL** - Approve payroll
- ✅ **VIEW_ALL_PAYSLIPS** - View all employee payslips
- ✅ **VIEW_OWN_PAYSLIP** - View own payslip
- ✅ **EDIT_SALARY_DEDUCTION** - Edit salary deductions

#### Leave Management
- ✅ **APPROVE_LEAVE** - Approve leave requests
- ✅ **VIEW_ALL_LEAVE_REQUESTS** - View all leave requests
- ✅ **CREATE_LEAVE_REQUEST** - Create leave requests
- ✅ **VIEW_OWN_LEAVE** - View own leave history

#### Reports
- ✅ **GENERATE_REPORTS** - Generate system reports
- ✅ **VIEW_FINANCIAL_REPORTS** - View financial reports
- ✅ **VIEW_ATTENDANCE_REPORTS** - View attendance reports

#### System Administration
- ✅ **SYSTEM_MAINTENANCE** - Perform system maintenance
- ✅ **VIEW_SYSTEM_LOGS** - View system logs
- ✅ **MANAGE_PERMISSIONS** - Manage user permissions

#### Basic Access
- ✅ **LOGIN** - Basic login access
- ✅ **VIEW_DASHBOARD** - View dashboard

---

### 2. Human Resources (HR)
**Employee management, leave requests, payroll processing**

#### Employee Management
- ✅ **CREATE_EMPLOYEE** - Create new employee records
- ✅ **EDIT_EMPLOYEE** - Edit employee records
- ✅ **VIEW_ALL_EMPLOYEES** - View all employee records
- ❌ **DELETE_EMPLOYEE** - Cannot delete employee records

#### Payroll Management
- ✅ **PROCESS_PAYROLL** - Process payroll runs
- ✅ **VIEW_ALL_PAYSLIPS** - View all employee payslips
- ❌ **APPROVE_PAYROLL** - Cannot approve payroll
- ❌ **EDIT_SALARY_DEDUCTION** - Cannot edit salary deductions

#### Leave Management
- ✅ **APPROVE_LEAVE** - Approve leave requests
- ✅ **VIEW_ALL_LEAVE_REQUESTS** - View all leave requests
- ✅ **CREATE_LEAVE_REQUEST** - Create leave requests
- ✅ **VIEW_OWN_LEAVE** - View own leave history

#### User Management
- ✅ **CREATE_USER** - Create new user accounts
- ❌ **EDIT_USER** - Cannot edit existing user accounts
- ❌ **DELETE_USER** - Cannot delete user accounts
- ❌ **VIEW_ALL_USERS** - Cannot view all user accounts

#### Reports
- ✅ **GENERATE_REPORTS** - Generate system reports
- ✅ **VIEW_ATTENDANCE_REPORTS** - View attendance reports
- ❌ **VIEW_FINANCIAL_REPORTS** - Cannot view financial reports

#### System Administration
- ❌ **SYSTEM_MAINTENANCE** - Cannot perform system maintenance
- ❌ **VIEW_SYSTEM_LOGS** - Cannot view system logs
- ❌ **MANAGE_PERMISSIONS** - Cannot manage user permissions

#### Basic Access
- ✅ **LOGIN** - Basic login access
- ✅ **VIEW_DASHBOARD** - View dashboard

---

### 3. Finance
**Financial reports, salary deductions, payroll approval**

#### Payroll Management
- ✅ **APPROVE_PAYROLL** - Approve payroll
- ✅ **VIEW_ALL_PAYSLIPS** - View all employee payslips
- ✅ **EDIT_SALARY_DEDUCTION** - Edit salary deductions
- ❌ **PROCESS_PAYROLL** - Cannot process payroll runs

#### Employee Management (Read-Only)
- ✅ **VIEW_ALL_EMPLOYEES** - View all employee records
- ❌ **CREATE_EMPLOYEE** - Cannot create employee records
- ❌ **EDIT_EMPLOYEE** - Cannot edit employee records
- ❌ **DELETE_EMPLOYEE** - Cannot delete employee records

#### Reports
- ✅ **GENERATE_REPORTS** - Generate system reports
- ✅ **VIEW_FINANCIAL_REPORTS** - View financial reports
- ❌ **VIEW_ATTENDANCE_REPORTS** - Cannot view attendance reports

#### Leave Management
- ❌ **APPROVE_LEAVE** - Cannot approve leave requests
- ❌ **VIEW_ALL_LEAVE_REQUESTS** - Cannot view all leave requests
- ❌ **CREATE_LEAVE_REQUEST** - Cannot create leave requests

#### User Management
- ❌ **CREATE_USER** - Cannot create user accounts
- ❌ **EDIT_USER** - Cannot edit existing user accounts
- ❌ **DELETE_USER** - Cannot delete user accounts
- ❌ **VIEW_ALL_USERS** - Cannot view all user accounts

#### System Administration
- ❌ **SYSTEM_MAINTENANCE** - Cannot perform system maintenance
- ❌ **VIEW_SYSTEM_LOGS** - Cannot view system logs
- ❌ **MANAGE_PERMISSIONS** - Cannot manage user permissions

#### Basic Access
- ✅ **LOGIN** - Basic login access
- ✅ **VIEW_DASHBOARD** - View dashboard

---

### 4. IT Support
**System maintenance, user account management, technical support**

#### User Management
- ✅ **CREATE_USER** - Create new user accounts
- ✅ **EDIT_USER** - Edit existing user accounts
- ✅ **VIEW_ALL_USERS** - View all user accounts
- ❌ **DELETE_USER** - Cannot delete user accounts

#### Employee Management (Read-Only)
- ✅ **VIEW_ALL_EMPLOYEES** - View all employee records
- ❌ **CREATE_EMPLOYEE** - Cannot create employee records
- ❌ **EDIT_EMPLOYEE** - Cannot edit employee records
- ❌ **DELETE_EMPLOYEE** - Cannot delete employee records

#### System Administration
- ✅ **SYSTEM_MAINTENANCE** - Perform system maintenance
- ✅ **VIEW_SYSTEM_LOGS** - View system logs
- ✅ **MANAGE_PERMISSIONS** - Manage user permissions

#### Payroll Management
- ❌ **PROCESS_PAYROLL** - Cannot process payroll runs
- ❌ **APPROVE_PAYROLL** - Cannot approve payroll
- ❌ **VIEW_ALL_PAYSLIPS** - Cannot view all employee payslips
- ❌ **EDIT_SALARY_DEDUCTION** - Cannot edit salary deductions

#### Leave Management
- ❌ **APPROVE_LEAVE** - Cannot approve leave requests
- ❌ **VIEW_ALL_LEAVE_REQUESTS** - Cannot view all leave requests
- ❌ **CREATE_LEAVE_REQUEST** - Cannot create leave requests

#### Reports
- ❌ **GENERATE_REPORTS** - Cannot generate system reports
- ❌ **VIEW_FINANCIAL_REPORTS** - Cannot view financial reports
- ❌ **VIEW_ATTENDANCE_REPORTS** - Cannot view attendance reports

#### Basic Access
- ✅ **LOGIN** - Basic login access
- ✅ **VIEW_DASHBOARD** - View dashboard

---

### 5. Employee
**Personal information, payslips, leave requests**

#### Employee Management (Self Only)
- ✅ **VIEW_OWN_EMPLOYEE_INFO** - View own employee information
- ❌ **CREATE_EMPLOYEE** - Cannot create employee records
- ❌ **EDIT_EMPLOYEE** - Cannot edit employee records
- ❌ **DELETE_EMPLOYEE** - Cannot delete employee records
- ❌ **VIEW_ALL_EMPLOYEES** - Cannot view all employee records

#### Payroll Management (Self Only)
- ✅ **VIEW_OWN_PAYSLIP** - View own payslip
- ❌ **PROCESS_PAYROLL** - Cannot process payroll runs
- ❌ **APPROVE_PAYROLL** - Cannot approve payroll
- ❌ **VIEW_ALL_PAYSLIPS** - Cannot view all employee payslips
- ❌ **EDIT_SALARY_DEDUCTION** - Cannot edit salary deductions

#### Leave Management (Self Only)
- ✅ **CREATE_LEAVE_REQUEST** - Create leave requests
- ✅ **VIEW_OWN_LEAVE** - View own leave history
- ❌ **APPROVE_LEAVE** - Cannot approve leave requests
- ❌ **VIEW_ALL_LEAVE_REQUESTS** - Cannot view all leave requests

#### User Management
- ❌ **CREATE_USER** - Cannot create user accounts
- ❌ **EDIT_USER** - Cannot edit existing user accounts
- ❌ **DELETE_USER** - Cannot delete user accounts
- ❌ **VIEW_ALL_USERS** - Cannot view all user accounts

#### Reports
- ❌ **GENERATE_REPORTS** - Cannot generate system reports
- ❌ **VIEW_FINANCIAL_REPORTS** - Cannot view financial reports
- ❌ **VIEW_ATTENDANCE_REPORTS** - Cannot view attendance reports

#### System Administration
- ❌ **SYSTEM_MAINTENANCE** - Cannot perform system maintenance
- ❌ **VIEW_SYSTEM_LOGS** - Cannot view system logs
- ❌ **MANAGE_PERMISSIONS** - Cannot manage user permissions

#### Basic Access
- ✅ **LOGIN** - Basic login access
- ✅ **VIEW_DASHBOARD** - View dashboard

---

## Access Control Rules

### Self-Access Rules
- Users can **always** view and edit their own employee information if they have `VIEW_OWN_EMPLOYEE_INFO` permission
- Users can **always** view their own payslips if they have `VIEW_OWN_PAYSLIP` permission
- Users can **always** create and view their own leave requests if they have `CREATE_LEAVE_REQUEST` and `VIEW_OWN_LEAVE` permissions

### Cross-Department Access
- **HR** can manage all employee information but cannot access financial reports
- **Finance** can approve payroll and view financial data but cannot manage employees
- **IT** can manage user accounts and system maintenance but cannot access payroll or employee data
- **Admin** has full access to all system features

### Permission Inheritance
- Higher-level permissions automatically include lower-level access
- For example, `VIEW_ALL_EMPLOYEES` includes the ability to view individual employee records
- `EDIT_EMPLOYEE` implies `VIEW_ALL_EMPLOYEES` access

## Default Test Accounts

| Username | Password | Role | Description |
|----------|----------|------|-------------|
| admin1 | adminpw1 | Admin | Full system access |
| hr1 | hrpw1 | HR | Employee and leave management |
| finance1 | financepw1 | Finance | Payroll approval and financial reports |
| it1 | itpw1 | IT | System maintenance and user management |
| username1 | pw1 | Employee | Basic employee access |

## Security Features

### Password Security
- All passwords are hashed using BCrypt with salt
- Custom BCrypt implementation with `$stub$` prefix for compatibility
- Passwords are never stored in plain text

### Session Management
- User sessions are managed through `SessionManager`
- Current user context is maintained throughout the session
- Automatic logout capability

### Permission Checking
- All feature access is validated through `RBACService`
- Permissions are checked before allowing any action
- Role-based navigation directs users to appropriate interfaces

## Implementation Details

### Core Classes
- `UserAccount` - User entity with role and permission methods
- `RBACService` - Service layer for permission validation
- `RolePermission` - Permission definitions per role
- `SessionManager` - Session and user context management
- `Role` enum - Role definitions (ADMIN, HR, FINANCE, IT, EMPLOYEE)
- `Permission` enum - All system permissions

### Database Schema
```sql
CREATE TABLE users (
    employee_id TEXT PRIMARY KEY,
    username TEXT UNIQUE NOT NULL,
    password TEXT NOT NULL,
    role TEXT NOT NULL DEFAULT 'Employee'
);
```

## Usage Examples

### Checking User Permissions
```java
UserAccount user = SessionManager.getInstance().getCurrentUser();
if (user.hasPermission(Permission.CREATE_EMPLOYEE)) {
    // Allow employee creation
}
```

### Role-Based Navigation
```java
switch (user.getRoleEnum()) {
    case ADMIN:
        // Show admin interface
        break;
    case HR:
        // Show HR interface
        break;
    case EMPLOYEE:
        // Show employee interface
        break;
}
```

### Feature Access Control
```java
RBACService rbac = RBACService.getInstance();
if (rbac.canAccessFeature(user, "process_payroll")) {
    // Allow payroll processing
}
```

---

**Last Updated:** March 14, 2026  
**Version:** 1.0  
**System:** KharlMotorPH Payroll System
