/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui.SystemPages;

/**
 *
 * @author Claire
 */

import Services.SalaryDeduction;
import Model.UserAccount;
import Model.Employee;
import Model.TotalPay;
import Services.WithholdingTax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import DAO.AttendanceDAO;
import java.time.*;
import java.util.List;
import DAO.EmployeeCSVReader;
import ui.LeaveManagement.LeaveRequestForm;
import ui.Authentication.Login;



public class EmployeePage extends javax.swing.JFrame {
    private EmployeeCSVReader reader;
    private UserAccount userAccount;
    private javax.swing.JButton jButtonTimeIn;
    private javax.swing.JButton jButtonTimeOut;
    private javax.swing.JLabel jLabelLastTimeIn;
    /**
     * Creates new form EmployeePage
     */
    public EmployeePage(UserAccount userAcc) {
        initComponents();
        
        
        this.reader = new EmployeeCSVReader("MotorPH.csv");
        this.userAccount = userAcc;
        String employeeID = userAcc.getEmployeeID();
        String[] employeeData = reader.searchEmployee(employeeID);
        
        jTextFieldEmpNum.setText(employeeData[0]);
        jTextFieldFirstName.setText(employeeData[2]);
        jTextFieldLastName.setText(employeeData[1]);
        jTextFieldBirthDay.setText(employeeData[3]);
        jTextAreaAddress.setText(employeeData[4]); //
        jTextFieldPhoneNum.setText(employeeData[5]);
        jTextFieldSSSNum.setText(employeeData[6]);
        jTextFieldPhilHealthNum.setText(employeeData[7]);
        jTextFieldTINNum.setText(employeeData[8]);
        jTextFieldPagIBIGNum.setText(employeeData[9]);
        jTextFieldStatus.setText(employeeData[10]);
        jTextFieldPosition.setText(employeeData[11]);
        jTextFieldSupervisor.setText(employeeData[12]);
        jTextFieldBasicSalary.setText(employeeData[13]);
        jTextFieldRiceSubsidy.setText(employeeData[14]);
        jTextFieldPhoneAllowance.setText(employeeData[15]);
        jTextFieldClothingAllowance.setText(employeeData[16]);
        jTextFieldSemiMonthlyRate.setText(employeeData[17]);
        jTextFieldHourlyRate.setText(employeeData[18]);
        
    // Time In button
    jButtonTimeIn = new javax.swing.JButton("Time In");
    jButtonTimeIn.addActionListener(evt -> handleTimeIn(evt));
    jPanel1.add(jButtonTimeIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 620, 100, 30));

    // Time Out button
    jButtonTimeOut = new javax.swing.JButton("Time Out");
    jButtonTimeOut.addActionListener(evt -> handleTimeOut(evt));
    jPanel1.add(jButtonTimeOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 620, 100, 30));
    
    jLabelLastTimeIn = new javax.swing.JLabel("Last Time In: --:--");
    jLabelLastTimeIn.setFont(new java.awt.Font("Segoe UI", 1, 12)); 
    jPanel1.add(jLabelLastTimeIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 580, 200, 30));
        
    // Refresh the panel
    jPanel1.revalidate();
    jPanel1.repaint();
    }
    
    public void calculatePayroll(Employee employee) {
    SalaryDeduction salaryDeduction = new SalaryDeduction();
     salaryDeduction.calculatePayroll(employee);
    }
    
    private void handleTimeIn(java.awt.event.ActionEvent evt) {
    try {
        AttendanceDAO dao = new AttendanceDAO();
        AttendanceDAO.Attendance a = new AttendanceDAO.Attendance();
        a.employeeId = jTextFieldEmpNum.getText();
        a.date = LocalDate.now().toString();
        a.timeIn = LocalTime.now().toString();
        dao.create(a);
        
        List<AttendanceDAO.Attendance> records = dao.findByEmployee(a.employeeId);
        AttendanceDAO.Attendance latest = records.get(records.size() - 1);
        
        JOptionPane.showMessageDialog(this, "Time In recorded!");
        jLabelLastTimeIn.setText("Last Time In: " + latest.timeIn);
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    private void handleTimeOut(java.awt.event.ActionEvent evt) {
    try {
        AttendanceDAO dao = new AttendanceDAO();
        List<AttendanceDAO.Attendance> records = dao.findByEmployee(jTextFieldEmpNum.getText());

        for (AttendanceDAO.Attendance a : records) {
            if (a.date.equals(LocalDate.now().toString()) && a.timeOut == null) {
                a.timeOut = LocalTime.now().toString();
                LocalTime in = LocalTime.parse(a.timeIn);
                LocalTime out = LocalTime.parse(a.timeOut);
                a.hoursWorked = Duration.between(in, out).toMinutes() / 60.0;
                dao.update(a);
                JOptionPane.showMessageDialog(this, "Time Out recorded!");
                break;
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldEmpNum = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldLastName = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldFirstName = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jTextFieldStatus = new javax.swing.JTextField();
        jTextFieldPosition = new javax.swing.JTextField();
        jTextFieldSupervisor = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldSSSNum = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTextFieldPagIBIGNum = new javax.swing.JTextField();
        jTextFieldTINNum = new javax.swing.JTextField();
        jTextFieldPhilHealthNum = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jTextFieldBirthDay = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldPhoneNum = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaAddress = new javax.swing.JTextArea();
        jComboBoxMonth = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jTextFieldBasicSalary = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jTextFieldRiceSubsidy = new javax.swing.JTextField();
        jTextFieldSemiMonthlyRate = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jTextFieldHourlyRate = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jTextFieldPhoneAllowance = new javax.swing.JTextField();
        jTextFieldClothingAllowance = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jTextFieldSSSContribution = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jTextFieldPhilHealthContribution = new javax.swing.JTextField();
        jTextFieldPagIBIGContribution = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jTextFieldGrossPay = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jTextFieldNetPay = new javax.swing.JTextField();
        jButtonCompute = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        jButtonBack = new javax.swing.JButton();
        jButtonFileLeave = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 255, 204));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel2.setText("MotorPH");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 10, 170, 30));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Payroll System");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 40, -1, 20));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1310, 70));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Employee No. : ");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 90, 20));

        jTextFieldEmpNum.setEditable(false);
        jTextFieldEmpNum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldEmpNumActionPerformed(evt);
            }
        });
        jPanel1.add(jTextFieldEmpNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 110, 50, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Last Name : ");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, -1, 20));

        jTextFieldLastName.setEditable(false);
        jPanel1.add(jTextFieldLastName, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 140, 140, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("First Name :");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, -1, 20));

        jTextFieldFirstName.setEditable(false);
        jPanel1.add(jTextFieldFirstName, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 170, 140, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Address :");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 200, 60, 20));

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel19.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("Status :");
        jPanel19.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 90, 60, 20));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setText("Position :");
        jPanel19.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 120, 90, 20));

        jTextFieldStatus.setEditable(false);
        jPanel19.add(jTextFieldStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 90, 140, -1));

        jTextFieldPosition.setEditable(false);
        jPanel19.add(jTextFieldPosition, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 120, 140, -1));

        jTextFieldSupervisor.setEditable(false);
        jPanel19.add(jTextFieldSupervisor, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 20, 140, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("SSS No. :");
        jPanel19.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 50, 60, 20));

        jTextFieldSSSNum.setEditable(false);
        jPanel19.add(jTextFieldSSSNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 50, 140, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("PhilHealth No. :");
        jPanel19.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 80, 90, 20));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("TIN :");
        jPanel19.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 110, 60, 20));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setText("Pag-IBIG No. :");
        jPanel19.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 140, 90, 20));

        jTextFieldPagIBIGNum.setEditable(false);
        jPanel19.add(jTextFieldPagIBIGNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 140, 140, -1));

        jTextFieldTINNum.setEditable(false);
        jPanel19.add(jTextFieldTINNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 110, 140, -1));

        jTextFieldPhilHealthNum.setEditable(false);
        jPanel19.add(jTextFieldPhilHealthNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 80, 140, -1));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel18.setText("Immediate Supervisor :");
        jPanel19.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 20, 130, 20));

        jTextFieldBirthDay.setEditable(false);
        jPanel19.add(jTextFieldBirthDay, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 60, 140, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Birthday:");
        jPanel19.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 60, -1, -1));

        jTextFieldPhoneNum.setEditable(false);
        jPanel19.add(jTextFieldPhoneNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 30, 140, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Phone Number :");
        jPanel19.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 30, 100, 20));

        jTextAreaAddress.setEditable(false);
        jTextAreaAddress.setColumns(20);
        jTextAreaAddress.setRows(5);
        jScrollPane1.setViewportView(jTextAreaAddress);

        jPanel19.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, 240, 40));

        jPanel1.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 1260, 180));

        jComboBoxMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "November", "December" }));
        jComboBoxMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxMonthActionPerformed(evt);
            }
        });
        jPanel1.add(jComboBoxMonth, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 280, 100, 40));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setText("Select Month");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 100, 20));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel19.setText("Hourly Rate :");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 410, -1, 20));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel20.setText("Gross Semi-Monthly Rate :");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 380, 160, 20));

        jTextFieldBasicSalary.setEditable(false);
        jPanel1.add(jTextFieldBasicSalary, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 350, 270, -1));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel21.setText("Basic Salary :");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 350, 100, 20));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel22.setText("Rice Subsidy :");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 480, 90, 20));

        jTextFieldRiceSubsidy.setEditable(false);
        jPanel1.add(jTextFieldRiceSubsidy, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 480, 270, -1));

        jTextFieldSemiMonthlyRate.setEditable(false);
        jPanel1.add(jTextFieldSemiMonthlyRate, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 380, 270, -1));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel23.setText("Total");
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 460, 100, 20));

        jTextFieldHourlyRate.setEditable(false);
        jTextFieldHourlyRate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldHourlyRateActionPerformed(evt);
            }
        });
        jPanel1.add(jTextFieldHourlyRate, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 410, 270, -1));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel24.setText("Earnings");
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 320, 70, 20));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel25.setText("Phone Allowance :");
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 510, 110, 20));

        jTextFieldPhoneAllowance.setEditable(false);
        jPanel1.add(jTextFieldPhoneAllowance, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 510, 270, -1));

        jTextFieldClothingAllowance.setEditable(false);
        jPanel1.add(jTextFieldClothingAllowance, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 540, 270, -1));

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel26.setText("Clothing Allowance :");
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 540, 140, 20));

        jTextFieldSSSContribution.setEditable(false);
        jTextFieldSSSContribution.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSSSContributionActionPerformed(evt);
            }
        });
        jPanel1.add(jTextFieldSSSContribution, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 350, 270, -1));

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel27.setText("SSS Contribution :");
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 350, 110, 20));

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel28.setText("PhilHealth Contribution :");
        jPanel1.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 380, 140, 20));

        jTextFieldPhilHealthContribution.setEditable(false);
        jTextFieldPhilHealthContribution.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPhilHealthContributionActionPerformed(evt);
            }
        });
        jPanel1.add(jTextFieldPhilHealthContribution, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 380, 270, -1));

        jTextFieldPagIBIGContribution.setEditable(false);
        jTextFieldPagIBIGContribution.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPagIBIGContributionActionPerformed(evt);
            }
        });
        jPanel1.add(jTextFieldPagIBIGContribution, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 410, 270, -1));

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel29.setText("Pag-IBIG Contribution :");
        jPanel1.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 410, 150, 20));

        jLabel30.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel30.setText("Allowances");
        jPanel1.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 450, 100, 20));

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel31.setText("Gross Pay :");
        jPanel1.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 490, 100, 20));

        jTextFieldGrossPay.setEditable(false);
        jPanel1.add(jTextFieldGrossPay, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 490, 270, -1));

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel32.setText("Net Pay :");
        jPanel1.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 520, 100, 20));

        jTextFieldNetPay.setEditable(false);
        jPanel1.add(jTextFieldNetPay, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 520, 270, -1));

        jButtonCompute.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonCompute.setText("Compute");
        jButtonCompute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonComputeActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonCompute, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 560, 170, 50));

        jLabel33.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel33.setText("Deductions");
        jPanel1.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 320, 90, 20));

        jButtonBack.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonBack.setText("Log Out");
        jButtonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBackActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 620, 100, 30));

        jButtonFileLeave.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonFileLeave.setText("File Leave");
        jButtonFileLeave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFileLeaveActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonFileLeave, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 620, -1, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 664, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBackActionPerformed
        // + go back to login page
        this.setVisible(false);
        new Login().setVisible(true);
    }//GEN-LAST:event_jButtonBackActionPerformed

    private void jTextFieldEmpNumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldEmpNumActionPerformed
        
    }//GEN-LAST:event_jTextFieldEmpNumActionPerformed

    private void jComboBoxMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxMonthActionPerformed
        
    }//GEN-LAST:event_jComboBoxMonthActionPerformed

    private void jTextFieldHourlyRateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldHourlyRateActionPerformed
        
    }//GEN-LAST:event_jTextFieldHourlyRateActionPerformed

    private void jTextFieldSSSContributionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSSSContributionActionPerformed
        
    }//GEN-LAST:event_jTextFieldSSSContributionActionPerformed

    private void jTextFieldPhilHealthContributionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPhilHealthContributionActionPerformed
        
    }//GEN-LAST:event_jTextFieldPhilHealthContributionActionPerformed

    private void jTextFieldPagIBIGContributionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPagIBIGContributionActionPerformed
        
    }//GEN-LAST:event_jTextFieldPagIBIGContributionActionPerformed

    private void jButtonComputeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonComputeActionPerformed
        //         double sssContribution = SalaryDeduction.getSSS(); // assuming getSSS() returns a numeric value

        //    jTextFieldSSSContribution.setText(String.valueOf(SalaryDeduction.getSSS()));
        //    jTextFieldPhilHealthContribution.setText(String.valueOf(SalaryDeduction.getPhilDeduct()));
        //    jTextFieldPagIBIGContribution.setText(String.valueOf(SalaryDeduction.getPagibigDeduct()));

        double hourlyRate=Double.parseDouble(jTextFieldHourlyRate.getText());
        double monthlyValue;
        String month = (String) jComboBoxMonth.getSelectedItem();

        switch(month){
            case "January":
            monthlyValue = 21;
            break;
            case "February":
            monthlyValue = 20;
            break;
            case "March":
            monthlyValue = 23;
            break;
            case "April":
            monthlyValue = 22;
            break;
            case "May":
            monthlyValue = 22;
            break;
            case "June":
            monthlyValue = 21;
            break;
            case "July":
            monthlyValue = 22;
            break;
            case "August":
            monthlyValue = 23;
            break;
            case "September":
            monthlyValue = 22;
            break;
            case "October":
            monthlyValue = 23;
            break;
            case "November":
            monthlyValue = 22;
            break;
            case "December":
            monthlyValue = 21;
            break;
            default:
            monthlyValue = 0; // set a default value if no month is selected
            break;

        }

        double dailyRate=hourlyRate*8;
        double basicSalary = dailyRate*monthlyValue;

        LocalDate birthdayDate;
        try {
            birthdayDate = LocalDate.parse(jTextFieldBirthDay.getText().trim(), DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (Exception ex) {
            try {
                birthdayDate = LocalDate.parse(jTextFieldBirthDay.getText().trim(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            } catch (Exception ex2) {
                birthdayDate = LocalDate.now();
            }
        }

        BigDecimal riceSubsidy;
        try { riceSubsidy = new BigDecimal(jTextFieldRiceSubsidy.getText().trim()); } catch (Exception ex) { riceSubsidy = BigDecimal.ZERO; }
        BigDecimal phoneAllowance;
        try { phoneAllowance = new BigDecimal(jTextFieldPhoneAllowance.getText().trim()); } catch (Exception ex) { phoneAllowance = BigDecimal.ZERO; }
        BigDecimal clothingAllowance;
        try { clothingAllowance = new BigDecimal(jTextFieldClothingAllowance.getText().trim()); } catch (Exception ex) { clothingAllowance = BigDecimal.ZERO; }

        Employee employee = new Employee.Builder(
                jTextFieldEmpNum.getText(),
                jTextFieldLastName.getText(),
                jTextFieldFirstName.getText(),
                birthdayDate
        )
                .withBasicSalary(BigDecimal.valueOf(basicSalary))
                .withRiceSubsidy(riceSubsidy)
                .withPhoneAllowance(phoneAllowance)
                .withClothingAllowance(clothingAllowance)
                .withHourlyRate(BigDecimal.valueOf(hourlyRate))
                .build();

        SalaryDeduction salaryDeduction = new SalaryDeduction();
        salaryDeduction.calculatePayroll(employee);

        jTextFieldSSSContribution.setText(String.valueOf(salaryDeduction.getSSS()));
        jTextFieldPhilHealthContribution.setText(String.valueOf(salaryDeduction.getPhilDeduct()));
        jTextFieldPagIBIGContribution.setText(String.valueOf(salaryDeduction.getPagibigDeduct()));

        WithholdingTax withholdingtax = new WithholdingTax();
        withholdingtax.calculatePayroll(employee);

        TotalPay totalpay = new TotalPay();
        totalpay.calculatePayroll(employee, withholdingtax);
        jTextFieldNetPay.setText(String.valueOf(totalpay.getNetPay()));
        jTextFieldGrossPay.setText(String.valueOf(totalpay.getGross()));

        // JOptionPane.showMessageDialog();
    }//GEN-LAST:event_jButtonComputeActionPerformed

    private void jButtonFileLeaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFileLeaveActionPerformed
        
        UserAccount userAcc = new UserAccount();
        userAcc.setEmployeeID(jTextFieldEmpNum.getText());
        new LeaveRequestForm(userAcc).setVisible(true);
        
        
    }//GEN-LAST:event_jButtonFileLeaveActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(EmployeePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(EmployeePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(EmployeePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(EmployeePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new EmployeePage().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton jButtonBack;
    private javax.swing.JButton jButtonCompute;
    private javax.swing.JButton jButtonFileLeave;
    private javax.swing.JComboBox<String> jComboBoxMonth;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaAddress;
    private javax.swing.JTextField jTextFieldBasicSalary;
    private javax.swing.JTextField jTextFieldBirthDay;
    private javax.swing.JTextField jTextFieldClothingAllowance;
    private javax.swing.JTextField jTextFieldEmpNum;
    private javax.swing.JTextField jTextFieldFirstName;
    private javax.swing.JTextField jTextFieldGrossPay;
    private javax.swing.JTextField jTextFieldHourlyRate;
    private javax.swing.JTextField jTextFieldLastName;
    private javax.swing.JTextField jTextFieldNetPay;
    private javax.swing.JTextField jTextFieldPagIBIGContribution;
    private javax.swing.JTextField jTextFieldPagIBIGNum;
    private javax.swing.JTextField jTextFieldPhilHealthContribution;
    private javax.swing.JTextField jTextFieldPhilHealthNum;
    private javax.swing.JTextField jTextFieldPhoneAllowance;
    private javax.swing.JTextField jTextFieldPhoneNum;
    private javax.swing.JTextField jTextFieldPosition;
    private javax.swing.JTextField jTextFieldRiceSubsidy;
    private javax.swing.JTextField jTextFieldSSSContribution;
    private javax.swing.JTextField jTextFieldSSSNum;
    private javax.swing.JTextField jTextFieldSemiMonthlyRate;
    private javax.swing.JTextField jTextFieldStatus;
    private javax.swing.JTextField jTextFieldSupervisor;
    private javax.swing.JTextField jTextFieldTINNum;

}
