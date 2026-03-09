package DAO;

import DAO.Database;
import Model.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;

public class EmployeeDAO {
    private static final DateTimeFormatter DATE_FORMATTER =
        DateTimeFormatter.ofPattern("MM/dd/yyyy");

    public List<Employee> findAll() throws SQLException {
        String sql = "SELECT * FROM employees ORDER BY id";
        List<Employee> employees = new ArrayList<>();
        
        try (Connection conn = Database.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                employees.add(map(rs));
            }
        }
        return employees;
    }

    public Employee findById(String id) throws SQLException {
        String sql = "SELECT * FROM employees WHERE id = ?";
        
        try (Connection conn = Database.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, id);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return map(rs);
                }
            }
        }
        return null;
    }

    public boolean create(Employee e) throws SQLException {
        String sql = "INSERT INTO employees(" +
                    "id, last_name, first_name, birthday, address, phone_number, " +
                    "sss_number, philhealth_number, tin_number, pagibig_number, status, " +
                    "position, immediate_supervisor, basic_salary, rice_subsidy, " +
                    "phone_allowance, clothing_allowance, gross_semi_monthly_rate, hourly_rate" +
                    ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = Database.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            bind(ps, e);
            return ps.executeUpdate() == 1;
        }
    }

    public boolean update(Employee e) throws SQLException {
        String sql = "UPDATE employees SET " +
                    "last_name = ?, first_name = ?, birthday = ?, address = ?, " +
                    "phone_number = ?, sss_number = ?, philhealth_number = ?, " +
                    "tin_number = ?, pagibig_number = ?, status = ?, position = ?, " +
                    "immediate_supervisor = ?, basic_salary = ?, rice_subsidy = ?, " +
                    "phone_allowance = ?, clothing_allowance = ?, " +
                    "gross_semi_monthly_rate = ?, hourly_rate = ? " +
                    "WHERE id = ?";
        
        try (Connection conn = Database.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            // Set parameters for the update
            int paramIndex = 1;
            ps.setString(paramIndex++, e.getLastName());
            ps.setString(paramIndex++, e.getFirstName());
            ps.setString(paramIndex++, e.getBirthday().format(DATE_FORMATTER));
            ps.setString(paramIndex++, e.getAddress());
            ps.setString(paramIndex++, e.getPhoneNumber());
            ps.setString(paramIndex++, e.getSssNumber());
            ps.setString(paramIndex++, e.getPhilhealthNumber());
            ps.setString(paramIndex++, e.getTinNumber());
            ps.setString(paramIndex++, e.getPagIbigNumber());
            ps.setString(paramIndex++, e.getStatus().name());
            ps.setString(paramIndex++, e.getPosition());
            ps.setString(paramIndex++, e.getImmediateSupervisor());
            ps.setBigDecimal(paramIndex++, e.getBasicSalary());
            ps.setBigDecimal(paramIndex++, e.getRiceSubsidy());
            ps.setBigDecimal(paramIndex++, e.getPhoneAllowance());
            ps.setBigDecimal(paramIndex++, e.getClothingAllowance());
            ps.setBigDecimal(paramIndex++, e.getGrossSemiMonthlyRate());
            ps.setBigDecimal(paramIndex++, e.getHourlyRate());
            
            // WHERE clause parameter
            ps.setString(paramIndex, e.getEmployeeId());
            
            return ps.executeUpdate() == 1;
        }
    }

    public boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM employees WHERE id = ?";
        
        try (Connection conn = Database.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, id);
            return ps.executeUpdate() == 1;
        }
    }

    private void bind(PreparedStatement ps, Employee e) throws SQLException {
        int paramIndex = 1;
        ps.setString(paramIndex++, e.getEmployeeId());
        ps.setString(paramIndex++, e.getLastName());
        ps.setString(paramIndex++, e.getFirstName());
        ps.setString(paramIndex++, e.getBirthday().format(DATE_FORMATTER));
        ps.setString(paramIndex++, e.getAddress());
        ps.setString(paramIndex++, e.getPhoneNumber());
        ps.setString(paramIndex++, e.getSssNumber());
        ps.setString(paramIndex++, e.getPhilhealthNumber());
        ps.setString(paramIndex++, e.getTinNumber());
        ps.setString(paramIndex++, e.getPagIbigNumber());
        ps.setString(paramIndex++, e.getStatus().name());
        ps.setString(paramIndex++, e.getPosition());
        ps.setString(paramIndex++, e.getImmediateSupervisor());
        ps.setBigDecimal(paramIndex++, e.getBasicSalary());
        ps.setBigDecimal(paramIndex++, e.getRiceSubsidy());
        ps.setBigDecimal(paramIndex++, e.getPhoneAllowance());
        ps.setBigDecimal(paramIndex++, e.getClothingAllowance());
        ps.setBigDecimal(paramIndex, e.getHourlyRate());
    }

    private Employee map(ResultSet rs) throws SQLException {
        String statusStr = rs.getString("status");
        Employee.EmploymentStatus status;
        
        try {
            status = Employee.EmploymentStatus.valueOf(statusStr.toUpperCase());
        } catch (Exception ex) {
            status = Employee.EmploymentStatus.REGULAR; // fallback
        }
        
        return new Employee.Builder(
            rs.getString("id"),
            rs.getString("last_name"),
            rs.getString("first_name"),
            LocalDate.parse(rs.getString("birthday"), DATE_FORMATTER)
        )
            .withAddress(rs.getString("address"))
            .withPhoneNumber(rs.getString("phone_number"))
            .withSssNumber(rs.getString("sss_number"))
            .withPhilhealthNumber(rs.getString("philhealth_number"))
            .withTinNumber(rs.getString("tin_number"))
            .withPagIbigNumber(rs.getString("pagibig_number"))
            .withStatus(status)
            .withPosition(rs.getString("position"))
            .withImmediateSupervisor(rs.getString("immediate_supervisor"))
            .withBasicSalary(rs.getBigDecimal("basic_salary") != null ? rs.getBigDecimal("basic_salary") : BigDecimal.ZERO)
            .withRiceSubsidy(rs.getBigDecimal("rice_subsidy") != null ? rs.getBigDecimal("rice_subsidy") : BigDecimal.ZERO)
            .withPhoneAllowance(rs.getBigDecimal("phone_allowance") != null ? rs.getBigDecimal("phone_allowance") : BigDecimal.ZERO)
            .withClothingAllowance(rs.getBigDecimal("clothing_allowance") != null ? rs.getBigDecimal("clothing_allowance") : BigDecimal.ZERO)
            .build();
    }
}