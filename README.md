# KharlMotorPH Payroll System

## ⚠️ IMPORTANT: NetBeans IDE Required

This project **requires NetBeans IDE** to run because it uses NetBeans-specific GUI components and form-generated code.

## How to Run

### Option 1: NetBeans IDE (REQUIRED)
1. Install NetBeans IDE (with Java SE support)
2. File → Open Project → Select this folder
3. Right-click project → "Run Project" (F6)

### Option 2: For Collaborators Without NetBeans
❌ **Cannot run without NetBeans** - Project uses:
- NetBeans AbsoluteLayout manager
- NetBeans GUI form components
- NetBeans-generated code

## Required Libraries (Included)
- sqlite-jdbc.jar
- jcalendar-1.4.jar
- AbsoluteLayout.jar (NetBeans specific)

## Database
- SQLite database: `KharlMotorPH.db` (auto-created on first run)
- CSV files will be migrated to database automatically

## Login Credentials
- **Admin:** admin1/adminpw1
- **Employee:** username1/pw1

## For Distribution
If you need to distribute this project to users without NetBeans:
1. Export as executable JAR from NetBeans
2. Or refactor to remove NetBeans dependencies
