package service;

import java.math.BigDecimal;
import data.Employee;
import data.WithholdingTax;
import data.TotalPay;

/**
 * Service class for handling payroll calculations.
 */
public class PayrollService {
    
    /**
     * Represents the result of a payroll computation.
     */
    public static class PayrollResult {
        public double gross;
        public double deductions;
        public double net;
        public String details;
        
        @Override
        public String toString() {
            return String.format("Gross: %.2f, Deductions: %.2f, Net: %.2f - %s", 
                gross, deductions, net, details);
        }
    }

    /**
     * Computes payroll for a given employee.
     * 
     * @param employee The employee to compute payroll for
     * @return PayrollResult containing gross pay, deductions, net pay, and details
     */
    public PayrollResult computeForEmployee(Employee employee) {
        PayrollResult result = new PayrollResult();
        
        if (!isValidEmployee(employee)) {
            result.details = "Invalid employee data: Missing basic salary";
            return result;
        }
        
        try {
            WithholdingTax withholdingTax = calculateWithholdingTax(employee);
            TotalPay totalPay = calculateTotalPay(employee, withholdingTax);
            
            result.gross = safe(totalPay.getGross());
            result.net = safe(totalPay.getNetPay());
            result.deductions = result.gross - result.net;
            result.details = formatDetails(withholdingTax);
            
        } catch (Exception ex) {
            result.details = "Error processing payroll: " + ex.getMessage();
        }
        
        return result;
    }
    
    private boolean isValidEmployee(Employee employee) {
        return employee != null 
            && employee.getBasicSalary() != null 
            && employee.getBasicSalary().compareTo(BigDecimal.ZERO) >= 0;
    }
    
    private WithholdingTax calculateWithholdingTax(Employee employee) {
        WithholdingTax withholdingTax = new WithholdingTax();
        withholdingTax.calculatePayroll(employee);
        return withholdingTax;
    }
    
    private TotalPay calculateTotalPay(Employee employee, WithholdingTax withholdingTax) {
        TotalPay totalPay = new TotalPay();
        totalPay.calculatePayroll(employee, withholdingTax);
        return totalPay;
    }
    
    private String formatDetails(WithholdingTax withholdingTax) {
        return String.format("Withholding Tax: %.2f, Statutory Deductions: %.2f",
            (double) WithholdingTax.getWithHoldingTax(),
            withholdingTax.getTotalDeduction());
    }
    
    /**
     * Safely handles NaN values by converting them to 0.
     * 
     * @param value The value to check
     * @return The original value or 0 if NaN
     */
    private static double safe(double value) {
        return Double.isNaN(value) ? 0.0 : value;
    }
}
