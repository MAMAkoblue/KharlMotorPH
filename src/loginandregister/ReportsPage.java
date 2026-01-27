package loginandregister;

import data.EmployeeDAO;
import data.Employee;
import data.PayslipDAO;
import data.PayslipDAO.Payslip;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ReportsPage extends JFrame {
    private JButton btnExportEmployees, btnExportPayslips, btnBack;

    public ReportsPage() {
        setTitle("Reports");
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setSize(600, 200);
        setLocationRelativeTo(null);
        initUI();
    }

    private void initUI() {
        btnExportEmployees = new JButton("Export Employees CSV");
        btnExportPayslips = new JButton("Export Payslips CSV (by Run ID)");
        btnBack = new JButton("Back");

        btnExportEmployees.addActionListener(this::onExportEmployees);
        btnExportPayslips.addActionListener(this::onExportPayslips);
        btnBack.addActionListener(e -> { this.setVisible(false); new HomePage().setVisible(true); });

        JPanel p = new JPanel(new GridLayout(0,1,10,10));
        p.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        p.add(btnExportEmployees);
        p.add(btnExportPayslips);
        p.add(btnBack);

        getContentPane().add(p);
    }

    private void onExportEmployees(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Save Employees CSV");
        if (chooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) return;
        try (FileWriter out = new FileWriter(chooser.getSelectedFile())) {
            EmployeeDAO dao = new EmployeeDAO();
            List<Employee> list = dao.findAll();
            out.write("id,last_name,first_name,birthday,address,phone_number,sss,philhealth,tin,pagibig,status,position,supervisor,basic_salary,rice_subsidy,phone_allowance,clothing_allowance,gross_semi_monthly,hourly_rate\n");
            for (Employee e1 : list) {
                out.write(String.join(",",
                        safe(e1.getEmployeeId()), safe(e1.getLastName()), safe(e1.getFirstName()), safe(e1.getFormattedBirthday()),
                        safe(e1.getAddress()), safe(e1.getPhoneNumber()), safe(e1.getSssNumber()), safe(e1.getPhilhealthNumber()),
                        safe(e1.getTinNumber()), safe(e1.getPagIbigNumber()), safe(e1.getStatus() != null ? e1.getStatus().name() : ""), safe(e1.getPosition()),
                        safe(e1.getImmediateSupervisor()), safe(e1.getBasicSalary() != null ? e1.getBasicSalary().toPlainString() : ""), safe(e1.getRiceSubsidy() != null ? e1.getRiceSubsidy().toPlainString() : ""),
                        safe(e1.getPhoneAllowance() != null ? e1.getPhoneAllowance().toPlainString() : ""), safe(e1.getClothingAllowance() != null ? e1.getClothingAllowance().toPlainString() : ""), safe(e1.getGrossSemiMonthlyRate() != null ? e1.getGrossSemiMonthlyRate().toPlainString() : ""),
                        safe(e1.getHourlyRate() != null ? e1.getHourlyRate().toPlainString() : "")));
                out.write("\n");
            }
            JOptionPane.showMessageDialog(this, "Employees exported.");
        } catch (IOException | SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void onExportPayslips(ActionEvent e) {
        String runIdStr = JOptionPane.showInputDialog(this, "Enter Payroll Run ID:");
        if (runIdStr == null || runIdStr.trim().isEmpty()) return;
        int runId;
        try { runId = Integer.parseInt(runIdStr.trim()); } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid Run ID");
            return;
        }
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Save Payslips CSV");
        if (chooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) return;
        try (FileWriter out = new FileWriter(chooser.getSelectedFile())) {
            PayslipDAO dao = new PayslipDAO();
            List<Payslip> list = dao.findByRun(runId);
            out.write("id,payroll_run_id,employee_id,gross,deductions,net,details\n");
            for (Payslip p : list) {
                out.write(String.join(",",
                        String.valueOf(p.id), String.valueOf(p.payrollRunId), safe(p.employeeId),
                        String.valueOf(nz(p.grossPay)), String.valueOf(nz(p.deductions)), String.valueOf(nz(p.netPay)),
                        quoteCsv(safe(p.details))));
                out.write("\n");
            }
            JOptionPane.showMessageDialog(this, "Payslips exported.");
        } catch (IOException | SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private static String safe(Object s) { return s == null ? "" : String.valueOf(s).replaceAll(",", " "); }
    private static String quoteCsv(String s) { return '"' + s.replace("\"","'") + '"'; }
    private static double nz(Double d) { return d == null ? 0.0 : d; }
}
