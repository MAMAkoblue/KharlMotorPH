package ui.SystemPages;

import DAO.AttendanceDAO;
import DAO.AttendanceDAO.Attendance;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;

public class AttendancePage extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private JButton btnRefresh, btnAdd, btnEdit, btnDelete, btnBack;

    public AttendancePage() {
        setTitle("Attendance");
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setSize(900, 500);
        setLocationRelativeTo(null);
        initUI();
        loadData();
    }

    private void initUI() {
        String[] cols = {
                "ID", "Employee ID", "Date", "Time In", "Time Out",
                "Hours Worked", "OT Hours", "Notes"
        };
        model = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane scroll = new JScrollPane(table);

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnRefresh = new JButton("Refresh");
        btnAdd = new JButton("Add");
        btnEdit = new JButton("Edit");
        btnDelete = new JButton("Delete");
        btnBack = new JButton("Back");

        btnRefresh.addActionListener(this::onRefresh);
        btnAdd.addActionListener(this::onAdd);
        btnEdit.addActionListener(this::onEdit);
        btnDelete.addActionListener(this::onDelete);

        actions.add(btnRefresh);
        actions.add(btnAdd);
        actions.add(btnEdit);
        actions.add(btnDelete);
        actions.add(Box.createHorizontalStrut(20));
        actions.add(btnBack);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(actions, BorderLayout.NORTH);
        getContentPane().add(scroll, BorderLayout.CENTER);
    }

    private void loadData() {
        model.setRowCount(0);
        try {
            AttendanceDAO dao = new AttendanceDAO();
            List<Attendance> list = dao.findAll();
            for (Attendance a : list) {
                model.addRow(new Object[]{
                        a.id,
                        a.employeeId,
                        a.date,
                        a.timeIn,
                        a.timeOut,
                        a.hoursWorked,
                        a.overtimeHours,
                        a.notes
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "DB error: " + ex.getMessage());
        }
    }

    private void onRefresh(ActionEvent e) { loadData(); }

    private void onAdd(ActionEvent e) {
        Attendance a = promptAttendance(null);
        if (a == null) return;
        try {
            AttendanceDAO dao = new AttendanceDAO();
            if (dao.create(a)) {
                loadData();
            } else {
                JOptionPane.showMessageDialog(this, "Create failed.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "DB error: " + ex.getMessage());
        }
    }

    private void onEdit(ActionEvent e) {
        int row = table.getSelectedRow();
        if (row < 0) { JOptionPane.showMessageDialog(this, "Select a row."); return; }
        Attendance current = rowToAttendance(row);
        Attendance updated = promptAttendance(current);
        if (updated == null) return;
        updated.id = current.id;
        try {
            AttendanceDAO dao = new AttendanceDAO();
            if (dao.update(updated)) {
                loadData();
            } else {
                JOptionPane.showMessageDialog(this, "No changes saved.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "DB error: " + ex.getMessage());
        }
    }

    private void onDelete(ActionEvent e) {
        int row = table.getSelectedRow();
        if (row < 0) { JOptionPane.showMessageDialog(this, "Select a row."); return; }
        int id = Integer.parseInt(String.valueOf(model.getValueAt(row, 0)));
        int confirm = JOptionPane.showConfirmDialog(this, "Delete record #" + id + "?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;
        try {
            AttendanceDAO dao = new AttendanceDAO();
            if (dao.delete(id)) {
                loadData();
            } else {
                JOptionPane.showMessageDialog(this, "Delete failed.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "DB error: " + ex.getMessage());
        }
    }

    private Attendance rowToAttendance(int row) {
        Attendance a = new Attendance();
        a.id = Integer.parseInt(String.valueOf(model.getValueAt(row, 0)));
        a.employeeId = String.valueOf(model.getValueAt(row, 1));
        a.date = String.valueOf(model.getValueAt(row, 2));
        a.timeIn = String.valueOf(model.getValueAt(row, 3));
        a.timeOut = String.valueOf(model.getValueAt(row, 4));
        Object hw = model.getValueAt(row, 5);
        a.hoursWorked = hw == null || String.valueOf(hw).isEmpty() ? null : Double.valueOf(String.valueOf(hw));
        Object ot = model.getValueAt(row, 6);
        a.overtimeHours = ot == null || String.valueOf(ot).isEmpty() ? null : Double.valueOf(String.valueOf(ot));
        a.notes = String.valueOf(model.getValueAt(row, 7));
        return a;
    }

    private Attendance promptAttendance(Attendance base) {
        JTextField tfEmp = new JTextField(base == null ? "" : base.employeeId);
        JTextField tfDate = new JTextField(base == null ? "" : base.date);
        JTextField tfIn = new JTextField(base == null ? "" : base.timeIn);
        JTextField tfOut = new JTextField(base == null ? "" : base.timeOut);
        JTextField tfHW = new JTextField(base == null || base.hoursWorked == null ? "" : String.valueOf(base.hoursWorked));
        JTextField tfOT = new JTextField(base == null || base.overtimeHours == null ? "" : String.valueOf(base.overtimeHours));
        JTextField tfNotes = new JTextField(base == null ? "" : base.notes);

        JPanel panel = new JPanel(new GridLayout(0, 2, 6, 6));
        panel.add(new JLabel("Employee ID")); panel.add(tfEmp);
        panel.add(new JLabel("Date (YYYY-MM-DD)")); panel.add(tfDate);
        panel.add(new JLabel("Time In (HH:mm)")); panel.add(tfIn);
        panel.add(new JLabel("Time Out (HH:mm)")); panel.add(tfOut);
        panel.add(new JLabel("Hours Worked")); panel.add(tfHW);
        panel.add(new JLabel("OT Hours")); panel.add(tfOT);
        panel.add(new JLabel("Notes")); panel.add(tfNotes);

        int res = JOptionPane.showConfirmDialog(this, panel, (base == null ? "Add Attendance" : "Edit Attendance"), JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (res != JOptionPane.OK_OPTION) return null;
        Attendance a = new Attendance();
        a.employeeId = tfEmp.getText().trim();
        a.date = tfDate.getText().trim();
        a.timeIn = tfIn.getText().trim();
        a.timeOut = tfOut.getText().trim();
        try {
            a.hoursWorked = tfHW.getText().trim().isEmpty() ? null : Double.parseDouble(tfHW.getText().trim());
            a.overtimeHours = tfOT.getText().trim().isEmpty() ? null : Double.parseDouble(tfOT.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid numeric value: " + ex.getMessage());
            return null;
        }
        a.notes = tfNotes.getText().trim();
        return a;
    }
}
