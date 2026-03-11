MotorPH Payroll System

Course Information
Course Code: MO-IT110L
Course Title: Object-oriented Programming
Section: H2101

Group Members
Jhon Kharl Victoriano
Aubrey De Fuzman
Ivone Christel Trinidad
Liam Cody Sumadsad

Project Overview



Technologies Used
The MotorPH Payroll System was developed using the following technologies:

Apache Netbeans IDE
GitHub
Bcrypt
SQLite
These technologies support backend development, database management, authentication mechanisms, and collaborative version control.

Project Structure
The Connectly API follows Django’s recommended modular architecture to maintain clear separation between system components.

```text
motorPh payroll
│
├──src
├── DAO
│   ├── AttendanceDAO.java
│   ├── Database.java
│   ├── EmployeeCSVReader.Java
│   ├── EmployeeDAO.java
│   ├── PayrollRunDAO.java
│   ├── PayslipDAO.java
│   └── UserAccountDAO.Java
│
├── Model
│   ├── Employee.java
│   ├── TotalPay.java
│   └── UserAccount.java
│
├── Services
│   ├── EmployeeService.Java
│   ├── PayrollService.java
│   ├── SalaryDeduction.java
│   ├── Validator.java
│   └── WithholdingTax.java
│
├── Utility
│   ├── Bcrypt.java
│   ├── StringsUtils.java
│   └── build.xml
│
├── ui
│   ├──Authentication
│   │   ├──Login.form
│   │   └──LoginAndRegister.java
│   ├──EmployeeManagement
│   │   ├──CreateEmployee.form
│   │   ├──EditEmployee.form
│   │   ├──EmployeeDTO.java
│   │   └──ViewEmployee.java
│   ├──LeaveManagement
│   │   ├──LeaveRequestFOrm.form
│   │   └──ViewLeaveManagement.java
│   ├──SupportingUI
│   │   ├──NeonBorderPanel.java
│   │   └──jTextFieldEmpNum.java
│   └──SystemPages
│   │   ├──AdminPage.form
│   │   ├──AttendancePage.java
│   │   ├──EmployeePage.form
│   │   ├──PayrollRunsPage.java
│   │   ├──PayslipPage.java
│   │   └──ReportsPage.java
│
├── .gitattributes
├── .gitignore
├── KharlMotorPH.db
├── LeaveManagement.csv
├── LoginCredentials.csv
├── MotorPH.csv
├── build.xml
└── manifest.mf

Login Credentials
Employee Credentials

AI Tool Disclosure
AI-assisted tools such as ChatGPT (OpenAI) were occasionally used to support learning activities. These tools were used for clarification of programming concepts, debugging assistance, and minor documentation refinement.

All implementation decisions, code integration, and final project outputs were reviewed and validated by the project members.
