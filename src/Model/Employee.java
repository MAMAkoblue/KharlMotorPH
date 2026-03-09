package Model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 * Represents an employee in the system with all related information.
 * This class enforces encapsulation by making fields private and providing
 * controlled access through methods.
 */
public class Employee {
    private final String employeeId;
    private String lastName;
    private String firstName;
    private LocalDate birthday;
    private String address;
    private String phoneNumber;
    private String sssNumber;
    private String philhealthNumber;
    private String tinNumber;
    private String pagIbigNumber;
    private EmploymentStatus status;
    private String position;
    private String immediateSupervisor;
    private BigDecimal basicSalary;
    private BigDecimal riceSubsidy;
    private BigDecimal phoneAllowance;
    private BigDecimal clothingAllowance;
    private BigDecimal grossSemiMonthlyRate;
    private BigDecimal hourlyRate;

    // Private constructor to force use of builder
    private Employee(Builder builder) {
        this.employeeId = builder.employeeId;
        this.lastName = builder.lastName;
        this.firstName = builder.firstName;
        this.birthday = builder.birthday;
        this.address = builder.address;
        this.phoneNumber = builder.phoneNumber;
        this.sssNumber = builder.sssNumber;
        this.philhealthNumber = builder.philhealthNumber;
        this.tinNumber = builder.tinNumber;
        this.pagIbigNumber = builder.pagIbigNumber;
        this.status = builder.status;
        this.position = builder.position;
        this.immediateSupervisor = builder.immediateSupervisor;
        this.basicSalary = builder.basicSalary;
        this.riceSubsidy = builder.riceSubsidy;
        this.phoneAllowance = builder.phoneAllowance;
        this.clothingAllowance = builder.clothingAllowance;
        this.grossSemiMonthlyRate = builder.grossSemiMonthlyRate;
        this.hourlyRate = builder.hourlyRate;
    }

    // Getters with proper return types
    public String getEmployeeId() {
        return employeeId;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getFormattedBirthday() {
        return birthday != null ? birthday.format(DateTimeFormatter.ISO_LOCAL_DATE) : "";
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getSssNumber() {
        return sssNumber;
    }

    public String getPhilhealthNumber() {
        return philhealthNumber;
    }

    public String getTinNumber() {
        return tinNumber;
    }

    public String getPagIbigNumber() {
        return pagIbigNumber;
    }

    public EmploymentStatus getStatus() {
        return status;
    }

    public String getPosition() {
        return position;
    }

    public String getImmediateSupervisor() {
        return immediateSupervisor;
    }

    public BigDecimal getBasicSalary() {
        return basicSalary;
    }

    public BigDecimal getRiceSubsidy() {
        return riceSubsidy;
    }

    public BigDecimal getPhoneAllowance() {
        return phoneAllowance;
    }

    public BigDecimal getClothingAllowance() {
        return clothingAllowance;
    }

    public BigDecimal getGrossSemiMonthlyRate() {
        return grossSemiMonthlyRate;
    }

    public BigDecimal getHourlyRate() {
        return hourlyRate;
    }

    // Setters with validation
    public void setLastName(String lastName) {
        this.lastName = validateName(lastName, "Last name");
    }

    public void setFirstName(String firstName) {
        this.firstName = validateName(firstName, "First name");
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = Objects.requireNonNull(birthday, "Birthday cannot be null");
    }

    public void setBirthday(String date) {
        try {
            this.birthday = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Expected yyyy-MM-dd", e);
        }
    }

    public void setAddress(String address) {
        this.address = validateString(address, "Address");
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber != null && !phoneNumber.matches("\\+?[0-9\\s-]+")) {
            throw new IllegalArgumentException("Invalid phone number format");
        }
        this.phoneNumber = phoneNumber;
    }

    // Other setters with similar validation...

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(employeeId, employee.employeeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId='" + employeeId + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", position='" + position + '\'' +
                '}';
    }

    // Validation helper methods
    private String validateName(String name, String fieldName) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty");
        }
        return name.trim();
    }

    private String validateString(String value, String fieldName) {
        return value != null ? value.trim() : null;
    }

    /**
     * Builder pattern for Employee class
     */
    public static class Builder {
        // Required parameters
        private final String employeeId;
        private final String lastName;
        private final String firstName;
        private final LocalDate birthday;

        // Optional parameters - initialized to default values
        private String address = "";
        private String phoneNumber = "";
        private String sssNumber = "";
        private String philhealthNumber = "";
        private String tinNumber = "";
        private String pagIbigNumber = "";
        private EmploymentStatus status = EmploymentStatus.PROBATIONARY;
        private String position = "";
        private String immediateSupervisor = "";
        private BigDecimal basicSalary = BigDecimal.ZERO;
        private BigDecimal riceSubsidy = BigDecimal.ZERO;
        private BigDecimal phoneAllowance = BigDecimal.ZERO;
        private BigDecimal clothingAllowance = BigDecimal.ZERO;
        private BigDecimal grossSemiMonthlyRate = BigDecimal.ZERO;
        private BigDecimal hourlyRate = BigDecimal.ZERO;

        public Builder(String employeeId, String lastName, String firstName, LocalDate birthday) {
            this.employeeId = employeeId;
            this.lastName = lastName;
            this.firstName = firstName;
            this.birthday = birthday;
        }

        // Builder methods for optional parameters
        public Builder withAddress(String address) {
            this.address = address != null ? address : "";
            return this;
        }

        public Builder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber != null ? phoneNumber : "";
            return this;
        }

        public Builder withSssNumber(String sssNumber) {
            this.sssNumber = sssNumber != null ? sssNumber : "";
            return this;
        }

        public Builder withPhilhealthNumber(String philhealthNumber) {
            this.philhealthNumber = philhealthNumber != null ? philhealthNumber : "";
            return this;
        }

        public Builder withTinNumber(String tinNumber) {
            this.tinNumber = tinNumber != null ? tinNumber : "";
            return this;
        }

        public Builder withPagIbigNumber(String pagIbigNumber) {
            this.pagIbigNumber = pagIbigNumber != null ? pagIbigNumber : "";
            return this;
        }

        public Builder withStatus(EmploymentStatus status) {
            this.status = status != null ? status : EmploymentStatus.PROBATIONARY;
            return this;
        }

        public Builder withPosition(String position) {
            this.position = position != null ? position : "";
            return this;
        }

        public Builder withImmediateSupervisor(String immediateSupervisor) {
            this.immediateSupervisor = immediateSupervisor != null ? immediateSupervisor : "";
            return this;
        }

        public Builder withBasicSalary(BigDecimal basicSalary) {
            this.basicSalary = basicSalary != null ? basicSalary : BigDecimal.ZERO;
            return this;
        }

        public Builder withRiceSubsidy(BigDecimal riceSubsidy) {
            this.riceSubsidy = riceSubsidy != null ? riceSubsidy : BigDecimal.ZERO;
            return this;
        }

        public Builder withPhoneAllowance(BigDecimal phoneAllowance) {
            this.phoneAllowance = phoneAllowance != null ? phoneAllowance : BigDecimal.ZERO;
            return this;
        }

        public Builder withClothingAllowance(BigDecimal clothingAllowance) {
            this.clothingAllowance = clothingAllowance != null ? clothingAllowance : BigDecimal.ZERO;
            return this;
        }

        public Builder withGrossSemiMonthlyRate(BigDecimal grossSemiMonthlyRate) {
            this.grossSemiMonthlyRate = grossSemiMonthlyRate != null ? grossSemiMonthlyRate : BigDecimal.ZERO;
            return this;
        }

        public Builder withHourlyRate(BigDecimal hourlyRate) {
            this.hourlyRate = hourlyRate != null ? hourlyRate : BigDecimal.ZERO;
            return this;
        }

        public Employee build() {
            return new Employee(this);
        }
    }

    /**
     * Enum for employment status
     */
    public enum EmploymentStatus {
        PROBATIONARY, REGULAR, RESIGNED, TERMINATED, RETIRED
    }
}