package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    public List<Employee> findAll() throws SQLException {
        String sql = "SELECT id,last_name,first_name,birthday,address,phone_number,sss_number,philhealth_number,tin_number,pagibig_number,status,position,immediate_supervisor,basic_salary,rice_subsidy,phone_allowance,clothing_allowance,gross_semi_monthly_rate,hourly_rate FROM employees ORDER BY id";
        Connection conn = Database.getInstance().getConnection();
        List<Employee> list = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(map(rs));
            }
        }
        return list;
    }

    public Employee findById(String id) throws SQLException {
        String sql = "SELECT id,last_name,first_name,birthday,address,phone_number,sss_number,philhealth_number,tin_number,pagibig_number,status,position,immediate_supervisor,basic_salary,rice_subsidy,phone_allowance,clothing_allowance,gross_semi_monthly_rate,hourly_rate FROM employees WHERE id=?";
        Connection conn = Database.getInstance().getConnection();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        }
        return null;
    }

    public boolean create(Employee e) throws SQLException {
        String sql = "INSERT INTO employees(id,last_name,first_name,birthday,address,phone_number,sss_number,philhealth_number,tin_number,pagibig_number,status,position,immediate_supervisor,basic_salary,rice_subsidy,phone_allowance,clothing_allowance,gross_semi_monthly_rate,hourly_rate) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Connection conn = Database.getInstance().getConnection();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            bind(ps, e);
            return ps.executeUpdate() == 1;
        }
    }

    public boolean update(Employee e) throws SQLException {
        String sql = "UPDATE employees SET last_name=?,first_name=?,birthday=?,address=?,phone_number=?,sss_number=?,philhealth_number=?,tin_number=?,pagibig_number=?,status=?,position=?,immediate_supervisor=?,basic_salary=?,rice_subsidy=?,phone_allowance=?,clothing_allowance=?,gross_semi_monthly_rate=?,hourly_rate=? WHERE id=?";
        Connection conn = Database.getInstance().getConnection();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, e.getLastName());
            ps.setString(2, e.getFirstName());
            ps.setString(3, e.getBirthday());
            ps.setString(4, e.getAddress());
            ps.setString(5, e.getPhoneNumber());
            ps.setString(6, e.getSssNumber());
            ps.setString(7, e.getPhilhealthNumber());
            ps.setString(8, e.getTinNumber());
            ps.setString(9, e.getPagIbigNumber());
            ps.setString(10, e.getStatus());
            ps.setString(11, e.getPosition());
            ps.setString(12, e.getImmediateSupervisor());
            ps.setString(13, e.getBasicSalary());
            ps.setString(14, e.getRiceSubsidy());
            ps.setString(15, e.getPhoneAllowance());
            ps.setString(16, e.getClothingAllowance());
            ps.setString(17, e.getGrossSemiMonthlyRate());
            ps.setString(18, e.getHourlyRate());
            ps.setString(19, e.getEmployeeId());
            return ps.executeUpdate() == 1;
        }
    }

    public boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM employees WHERE id=?";
        Connection conn = Database.getInstance().getConnection();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            return ps.executeUpdate() == 1;
        }
    }

    private static void bind(PreparedStatement ps, Employee e) throws SQLException {
        ps.setString(1, e.getEmployeeId());
        ps.setString(2, e.getLastName());
        ps.setString(3, e.getFirstName());
        ps.setString(4, e.getBirthday());
        ps.setString(5, e.getAddress());
        ps.setString(6, e.getPhoneNumber());
        ps.setString(7, e.getSssNumber());
        ps.setString(8, e.getPhilhealthNumber());
        ps.setString(9, e.getTinNumber());
        ps.setString(10, e.getPagIbigNumber());
        ps.setString(11, e.getStatus());
        ps.setString(12, e.getPosition());
        ps.setString(13, e.getImmediateSupervisor());
        ps.setString(14, e.getBasicSalary());
        ps.setString(15, e.getRiceSubsidy());
        ps.setString(16, e.getPhoneAllowance());
        ps.setString(17, e.getClothingAllowance());
        ps.setString(18, e.getGrossSemiMonthlyRate());
        ps.setString(19, e.getHourlyRate());
    }

    private static Employee map(ResultSet rs) throws SQLException {
        Employee e = new Employee();
        e.setEmployeeId(rs.getString("id"));
        e.setLastName(rs.getString("last_name"));
        e.setFirstName(rs.getString("first_name"));
        e.setBirthday(rs.getString("birthday"));
        e.setAddress(rs.getString("address"));
        e.setPhoneNumber(rs.getString("phone_number"));
        e.setSssNumber(rs.getString("sss_number"));
        e.setPhilhealthNumber(rs.getString("philhealth_number"));
        e.setTinNumber(rs.getString("tin_number"));
        e.setPagIbigNumber(rs.getString("pagibig_number"));
        e.setStatus(rs.getString("status"));
        e.setPosition(rs.getString("position"));
        e.setImmediateSupervisor(rs.getString("immediate_supervisor"));
        e.setBasicSalary(rs.getString("basic_salary"));
        e.setRiceSubsidy(rs.getString("rice_subsidy"));
        e.setPhoneAllowance(rs.getString("phone_allowance"));
        e.setClothingAllowance(rs.getString("clothing_allowance"));
        e.setGrossSemiMonthlyRate(rs.getString("gross_semi_monthly_rate"));
        e.setHourlyRate(rs.getString("hourly_rate"));
        return e;
    }
}
