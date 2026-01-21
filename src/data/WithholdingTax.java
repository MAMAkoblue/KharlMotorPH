package data;

import loginandregister.SalaryDeduction;

public class WithholdingTax {

    private static String info;
    private static long withHoldingTax;
    private static double TotalDeduction;

    public void calculatePayroll(Employee employee) {
        SalaryDeduction salaryDeduction = new SalaryDeduction();
        salaryDeduction.calculatePayroll(employee);
        double pagibigDeduct = salaryDeduction.getPagibigDeduct();
        double philDeduct = salaryDeduction.getPhilDeduct();
        double sss = salaryDeduction.getSSS();
        double basicSalary = employee != null && employee.getBasicSalary() != null ? employee.getBasicSalary().doubleValue() : 0.0;

        TotalDeduction = pagibigDeduct + philDeduct + sss;
        double taxableIncome = basicSalary - TotalDeduction;

        if (taxableIncome < 20832) {
            info = "No withholding tax";
            withHoldingTax = 0;
        } else if (taxableIncome >= 20833 && taxableIncome <= 33333) {
            withHoldingTax = (long) (taxableIncome * 0.2);
            info = String.format("20%% in excess of 20,833: %.2f", taxableIncome - 20833);
        } else if (taxableIncome >= 33333 && taxableIncome <= 66667) {
            withHoldingTax = (long) (taxableIncome * 0.25 - 2500);
            info = String.format("2,500 plus 25%% in excess of 33,333: %.2f", taxableIncome - 33333);
        } else if (taxableIncome >= 66667 && taxableIncome <= 166667) {
            withHoldingTax = (long) (taxableIncome * 0.3 - 10833);
            info = String.format("10,833 plus 30%% in excess of 66,667: %.2f", taxableIncome - 66667);
        } else if (taxableIncome >= 166667 && taxableIncome <= 666667) {
            withHoldingTax = (long) (taxableIncome * 0.32 - 40833.33);
            info = String.format("40,833.33 plus 32%% in excess over 166,667: %.2f", taxableIncome - 166667);
        } else if (taxableIncome > 666667) {
            withHoldingTax = (long) (taxableIncome * 0.35 - 200833.33);
            info = String.format("200,833.33 plus 35%% in excess of 666,667: %.2f", taxableIncome - 666667);
        } else {
            System.out.println("Error");
            info = "";
        }
    }

    // getters and setters
    public static String getInfo() {
        return info;
    }

    public static void setInfo(String info) {
        WithholdingTax.info = info;
    }

    public static long getWithHoldingTax() {
        return withHoldingTax;
    }

    public static void setWithholdingTax(long withHoldingTax) {
        WithholdingTax.withHoldingTax = withHoldingTax;
    }

    public double getTotalDeduction() {
        return TotalDeduction;
    }

    public static void setTotalDeduction(double totalDeduction) {
        WithholdingTax.TotalDeduction = totalDeduction;
    }
}