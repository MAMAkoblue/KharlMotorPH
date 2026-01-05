package data;

public class TotalPay {
    
    private double gross;
    private  double netPay;
    
    public void calculatePayroll(Employee employee, WithholdingTax withholdingTax) {
        double basicSalary = Double.parseDouble(employee.getBasicSalary());
        double riceSubsidy = Double.parseDouble(employee.getRiceSubsidy());
        double phoneAllowance = Double.parseDouble(employee.getPhoneAllowance());
        double clothingAllowance = Double.parseDouble(employee.getClothingAllowance());
        
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