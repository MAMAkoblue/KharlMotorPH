package Model;

import Model.Employee;
import Services.WithholdingTax;

public class TotalPay {
    
    private double gross;
    private  double netPay;
    
    public void calculatePayroll(Employee employee, WithholdingTax withholdingTax) {
        double basicSalary = employee.getBasicSalary() != null ? employee.getBasicSalary().doubleValue() : 0.0;
        double riceSubsidy = employee.getRiceSubsidy() != null ? employee.getRiceSubsidy().doubleValue() : 0.0;
        double phoneAllowance = employee.getPhoneAllowance() != null ? employee.getPhoneAllowance().doubleValue() : 0.0;
        double clothingAllowance = employee.getClothingAllowance() != null ? employee.getClothingAllowance().doubleValue() : 0.0;
        
        //Basic = hourlyrate x Monthvalue
        gross = riceSubsidy + phoneAllowance + clothingAllowance + basicSalary; 
        netPay = gross - withholdingTax.getTotalDeduction() - withholdingTax.getWithHoldingTax();
    }
    
    // getters and setters
    public  double getGross() {
        return gross;
    }

    public void setGross(double gross) {
        this.gross = gross;
    }
    
    public double getNetPay() {
        return netPay;
    }

    public void setNetPay(double netPay) {
        this.netPay = netPay;
    }
}