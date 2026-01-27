package service;

import data.Employee;
import data.EmployeeDAO;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import util.StringUtils;

public class EmployeeService {
    private final EmployeeDAO employeeDAO;
    
    public EmployeeService() {
        this.employeeDAO = new EmployeeDAO();
    }
    
    public void loadEmployeesToTable(JTable table) {
        String[] columnNames = {
            "ID", "Last Name", "First Name", "Birthday", "Address", 
            "Phone Number", "SSS #", "Philhealth #", "TIN #", "Pag-ibig #", 
            "Status", "Position", "Immediate Supervisor", "Basic Salary", 
            "Rice Subsidy", "Phone Allowance", "Clothing Allowance", 
            "Gross Semi-monthly Rate", "Hourly Rate"
        };

        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        try {
            List<Employee> employees = employeeDAO.findAll();
            
            if (employees == null || employees.isEmpty()) {
                JOptionPane.showMessageDialog(null, 
                    "No employee records found.", 
                    "Information", 
                    JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            for (Employee e : employees) {
                if (e != null) {
                    model.addRow(createEmployeeRow(e));
                }
            }
            
            table.setModel(model);
            
        } catch (SQLException ex) {
            showError("Database Error", "Database error: " + ex.getMessage());
            ex.printStackTrace();
        } catch (Exception ex) {
            showError("Error", "An unexpected error occurred: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    private Object[] createEmployeeRow(Employee e) {
        return new Object[]{
            StringUtils.nullToEmpty(e.getEmployeeId()),
            StringUtils.nullToEmpty(e.getLastName()),
            StringUtils.nullToEmpty(e.getFirstName()),
            StringUtils.nullToEmpty(e.getFormattedBirthday()),
            StringUtils.nullToEmpty(e.getAddress()),
            StringUtils.nullToEmpty(e.getPhoneNumber()),
            StringUtils.nullToEmpty(e.getSssNumber()),
            StringUtils.nullToEmpty(e.getPhilhealthNumber()),
            StringUtils.nullToEmpty(e.getTinNumber()),
            StringUtils.nullToEmpty(e.getPagIbigNumber()),
            StringUtils.nullToEmpty(e.getStatus() != null ? e.getStatus().name() : ""),
            StringUtils.nullToEmpty(e.getPosition()),
            StringUtils.nullToEmpty(e.getImmediateSupervisor()),
            StringUtils.nullToEmpty(e.getBasicSalary() != null ? e.getBasicSalary().toPlainString() : ""),
            StringUtils.nullToEmpty(e.getRiceSubsidy() != null ? e.getRiceSubsidy().toPlainString() : ""),
            StringUtils.nullToEmpty(e.getPhoneAllowance() != null ? e.getPhoneAllowance().toPlainString() : ""),
            StringUtils.nullToEmpty(e.getClothingAllowance() != null ? e.getClothingAllowance().toPlainString() : ""),
            StringUtils.nullToEmpty(e.getGrossSemiMonthlyRate() != null ? e.getGrossSemiMonthlyRate().toPlainString() : ""),
            StringUtils.nullToEmpty(e.getHourlyRate() != null ? e.getHourlyRate().toPlainString() : "")
        };
    }
    
    private void showError(String title, String message) {
        JOptionPane.showMessageDialog(
            null, 
            message, 
            title, 
            JOptionPane.ERROR_MESSAGE
        );
    }
}
