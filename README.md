# KharlMotorPH Payroll System

## How to Run

### Option 1: NetBeans IDE (Recommended)
1. Open NetBeans IDE
2. File → Open Project → Select this folder
3. Right-click project → "Run Project" (F6)

### Option 2: Command Line
```bash
# Compile with NetBeans libraries
javac -cp "lib/*:lib/absolutelayout/*" src/loginandregister/LoginAndRegister.java

# Run
java -cp "lib/*:lib/absolutelayout/*:." loginandregister.LoginAndRegister
```

## Required Libraries
- sqlite-jdbc.jar (included)
- jcalendar-1.4.jar (included) 
- AbsoluteLayout.jar (NetBeans specific)
- NetBeans GUI components

## Database
- SQLite database: `KharlMotorPH.db` (auto-created on first run)
- CSV files will be migrated to database automatically

## Login Credentials
- Admin: admin1/adminpw1
- Employee: username1/pw1
