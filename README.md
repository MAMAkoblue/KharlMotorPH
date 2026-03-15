MotorPH Payroll System

Course Information
Course Code: MO-IT110L
Course Title: Object-oriented Programming
Section: H2101

Group Members

- Jhon Kharl Victoriano
- Aubrey De Fuzman
- Ivone Christel Trinidad
- Liam Cody Sumadsad



Project Overview

The MotorPH Payroll System is an object-oriented payroll management application developed to automate the payroll processing of employees in MotorPH. The system manages employee records, attendance information, and payroll computations to improve the efficiency and accuracy of payroll operations.

The application is designed using object-oriented programming (OOP) principles, where core business entities such as employees are represented as classes and system functionalities are organized into modular components. The system reads employee data from external sources such as CSV files, processes payroll-related information, and presents the results through user interface pages.

The system architecture separates responsibilities across different components. Core business objects handle payroll data and computations, helper classes manage tasks such as file reading and data transfer, and user interface pages allow users to interact with the system for operations such as employee management, attendance viewing, and payroll generation.

Key capabilities of the MotorPH Payroll System include:
- Managing and storing employee information
- Reading employee records from CSV files
- Viewing and tracking employee attendance
- Generating payroll runs for employees
- Supporting user login and registration
- Displaying payroll and attendance data through user interface pages

The project demonstrates the practical implementation of OOP concepts such as encapsulation, abstraction, inheritance, and modular system design. It was developed as part of the MO-IT110 Object-Oriented Programming course in the Bachelor of Science in Information Technology (BSIT) program.

Overall, the MotorPH Payroll System provides a structured and automated approach to payroll processing while showcasing fundamental software engineering and object-oriented programming practices.



Technologies Used

The MotorPH Payroll System was developed using the following technologies:

- Apache Netbeans IDE
- GitHub
- Bcrypt
- SQLite
  
These technologies support backend development, database management, authentication mechanisms, and collaborative version control.


Project Structure

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
│   ├── Permission.java
│   ├── Role.java
│   ├── RolePermission.java
│   ├── TotalPay.java
│   └── UserAccount.java
│
├── Services
│   ├── EmployeeService.Java
│   ├── PayrollService.java
│   ├── RBACService.java
│   ├── SessionManager.java
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
│   │   ├──RBACUIHelper.java
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
```

AI Tool Disclosure

AI-assisted tools such as ChatGPT (OpenAI) and Claude AI were occasionally used to support learning activities. These tools were used for clarification of programming concepts, debugging assistance, and minor documentation refinement.

All implementation decisions, code integration, and final project outputs were reviewed and validated by the project members.
