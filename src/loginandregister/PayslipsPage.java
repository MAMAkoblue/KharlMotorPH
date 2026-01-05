package loginandregister;

import data.PayslipDAO;
import data.PayslipDAO.Payslip;
import data.EmployeeDAO;
import data.Employee;
import service.PayrollService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;

public class PayslipsPage extends JFrame {
    private final int payrollRunId;
    private JTable table;
    private DefaultTableModel model;
    private JButton btnRefresh, btnGenerate, btnDeleteAll, btnBack;

    public PayslipsPage(int payrollRunId) {
        this.payrollRunId = payrollRunId;
        setTitle("Payslips - Run #" + payrollRunId);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setSize(900, 500);
        setLocationRelativeTo(null);
        initUI();
        loadData();
    }

    private void initUI() {
        String[] cols = {"ID", "Employee ID", "Gross", "Deductions", "Net", "Details"};
        model = new DefaultTableModel(cols, 0) { @Override public boolean isCellEditable(int r, int c){return false;} };
        table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane scroll = new JScrollPane(table);

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnRefresh = new JButton("Refresh");
        btnGenerate = new JButton("Generate Payslips");
        btnDeleteAll = new JButton("Delete All");
        btnBack = new JButton("Back");

        btnRefresh.addActionListener(this::onRefresh);
        btnGenerate.addActionListener(this::onGenerate);
        btnDeleteAll.addActionListener(this::onDeleteAll);
        btnBack.addActionListener(e -> { this.setVisible(false); new PayrollRunsPage().setVisible(true); });

        actions.add(btnRefresh);
        actions.add(btnGenerate);
        actions.add(btnDeleteAll);
        actions.add(Box.createHorizontalStrut(20));
        actions.add(btnBack);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(actions, BorderLayout.NORTH);
        getContentPane().add(scroll, BorderLayout.CENTER);
    }

    private void loadData() {
        model.setRowCount(0);
        try {
            PayslipDAO dao = new PayslipDAO();
            List<Payslip> list = dao.findByRun(payrollRunId);
            for (Payslip p : list) {
                model.addRow(new Object[]{ p.id, p.employeeId, p.grossPay, p.deductions, p.netPay, p.details });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "DB error: " + ex.getMessage());
        }
    }

    private void onRefresh(ActionEvent e) { loadData(); }

    private void onGenerate(ActionEvent e) {
        int confirm = JOptionPane.showConfirmDialog(this, "Generate payslips for run #" + payrollRunId + "?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;
        try {
            EmployeeDAO employeeDAO = new EmployeeDAO();
            List<Employee> employees = employeeDAO.findAll();
            PayslipDAO payslipDAO = new PayslipDAO();
            PayrollService payroll = new PayrollService();
            for (Employee emp : employees) {
                PayrollService.Result r = payroll.computeForEmployee(emp);
                Payslip p = new Payslip();
                p.payrollRunId = payrollRunId;
                p.employeeId = emp.getEmployeeId();
                p.grossPay = r.gross;
                p.deductions = r.deductions;
                p.netPay = r.net;
                p.details = r.details;
                payslipDAO.create(p);
            }
            JOptionPane.showMessageDialog(this, "Payslips generated.");
            loadData();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void onDeleteAll(ActionEvent e) {
        int confirm = JOptionPane.showConfirmDialog(this, "Delete all payslips for run #" + payrollRunId + "?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;
        try {
            PayslipDAO dao = new PayslipDAO();
            if (dao.deleteByRun(payrollRunId)) {
                loadData();
            } else {
                loadData();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "DB error: " + ex.getMessage());
        }
    }

}
