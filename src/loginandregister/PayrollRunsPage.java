package loginandregister;

import data.PayrollRunDAO;
import data.PayrollRunDAO.PayrollRun;
import data.PayslipDAO;
import data.PayslipDAO.Payslip;
import data.EmployeeDAO;
import data.EmployeeDAO; // using data.Employee model
import data.EmployeeDAO; // for compile safety if optimizer removes

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PayrollRunsPage extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private JButton btnRefresh, btnCreate, btnCloseRun, btnViewPayslips, btnBack;

    public PayrollRunsPage() {
        setTitle("Payroll Runs");
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setSize(900, 500);
        setLocationRelativeTo(null);
        initUI();
        loadData();
    }

    private void initUI() {
        String[] cols = {"ID", "Period Start", "Period End", "Run Date", "Status"};
        model = new DefaultTableModel(cols, 0) { @Override public boolean isCellEditable(int r, int c){return false;} };
        table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane scroll = new JScrollPane(table);

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnRefresh = new JButton("Refresh");
        btnCreate = new JButton("Create Run");
        btnCloseRun = new JButton("Close Run");
        btnViewPayslips = new JButton("View Payslips");
        btnBack = new JButton("Back");

        btnRefresh.addActionListener(this::onRefresh);
        btnCreate.addActionListener(this::onCreate);
        btnCloseRun.addActionListener(this::onClose);
        btnViewPayslips.addActionListener(this::onViewPayslips);
        btnBack.addActionListener(e -> { this.setVisible(false); new HomePage().setVisible(true); });

        actions.add(btnRefresh);
        actions.add(btnCreate);
        actions.add(btnCloseRun);
        actions.add(btnViewPayslips);
        actions.add(Box.createHorizontalStrut(20));
        actions.add(btnBack);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(actions, BorderLayout.NORTH);
        getContentPane().add(scroll, BorderLayout.CENTER);
    }

    private void loadData() {
        model.setRowCount(0);
        try {
            PayrollRunDAO dao = new PayrollRunDAO();
            List<PayrollRun> list = dao.findAll();
            for (PayrollRun pr : list) {
                model.addRow(new Object[]{ pr.id, pr.periodStart, pr.periodEnd, pr.runDate, pr.status });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "DB error: " + ex.getMessage());
        }
    }

    private void onRefresh(ActionEvent e) { loadData(); }

    private void onCreate(ActionEvent e) {
        JTextField tfStart = new JTextField();
        JTextField tfEnd = new JTextField();
        JPanel panel = new JPanel(new GridLayout(0,2,6,6));
        panel.add(new JLabel("Period Start (YYYY-MM-DD)")); panel.add(tfStart);
        panel.add(new JLabel("Period End (YYYY-MM-DD)")); panel.add(tfEnd);
        int res = JOptionPane.showConfirmDialog(this, panel, "Create Payroll Run", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (res != JOptionPane.OK_OPTION) return;
        try {
            PayrollRun pr = new PayrollRun();
            pr.periodStart = tfStart.getText().trim();
            pr.periodEnd = tfEnd.getText().trim();
            pr.runDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            pr.status = "OPEN";
            PayrollRunDAO dao = new PayrollRunDAO();
            if (dao.create(pr)) loadData(); else JOptionPane.showMessageDialog(this, "Create failed");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "DB error: " + ex.getMessage());
        }
    }

    private void onClose(ActionEvent e) {
        int row = table.getSelectedRow();
        if (row < 0) { JOptionPane.showMessageDialog(this, "Select a run."); return; }
        int id = Integer.parseInt(String.valueOf(model.getValueAt(row, 0)));
        try {
            PayrollRunDAO dao = new PayrollRunDAO();
            if (dao.closeRun(id)) {
                JOptionPane.showMessageDialog(this, "Run closed.");
                loadData();
            } else {
                JOptionPane.showMessageDialog(this, "Close failed.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "DB error: " + ex.getMessage());
        }
    }

    private void onViewPayslips(ActionEvent e) {
        int row = table.getSelectedRow();
        if (row < 0) { JOptionPane.showMessageDialog(this, "Select a run."); return; }
        int id = Integer.parseInt(String.valueOf(model.getValueAt(row, 0)));
        this.setVisible(false);
        new PayslipsPage(id).setVisible(true);
    }
}
