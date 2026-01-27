package loginandregister;

import data.Employee;

public class SalaryDeduction {

    
    private  double SSS;
    private  double PagibigDeduct;
    private  double PhilDeduct;
    
    public void calculatePayroll(Employee employee) {
    double basicsalary = employee != null && employee.getBasicSalary() != null ? employee.getBasicSalary().doubleValue() : 0.0;
    
       SSS = basicsalary;
        
      
      double Pagibig, Philhealth; 

// Pagibig Deduction
      Pagibig = 100;
         PagibigDeduct = Pagibig;
// Philhealth Deduction
      Philhealth = 0.03;
       PhilDeduct =  basicsalary * Philhealth;
      
// SSS Deduction
      if (basicsalary < 3250) {
          SSS = 135.00;
      } else if (basicsalary >= 3250 && basicsalary <= 3750) {
          SSS = 157.00;
      } else if (basicsalary >= 3750 && basicsalary <= 4250) {
          SSS = 180.00;
      } else if (basicsalary >= 4250 && basicsalary <= 4750) {
          SSS = 202.50;
      } else if (basicsalary >= 4750 && basicsalary <= 5250) {
          SSS = 225.00;
      } else if (basicsalary >= 5250 && basicsalary <= 5750) {
          SSS = 247.50;
      } else if (basicsalary >= 5750 && basicsalary <= 6250) {
          SSS = 270.00;
      } else if (basicsalary >= 6250 && basicsalary <= 6750) {
          SSS = 292.50;
      } else if (basicsalary >= 6750 && basicsalary <= 7250) {
          SSS = 315.00;
      } else if (basicsalary >= 7250 && basicsalary <= 7750) {
          SSS = 337.50;
      } else if (basicsalary >= 7750 && basicsalary <= 8250) {
          SSS = 360.00;
      } else if (basicsalary >= 8250 && basicsalary <= 8750) {
          SSS = 382.50;
      } else if (basicsalary >= 8750 && basicsalary <= 9250) {
          SSS = 405.00;
      } else if (basicsalary >= 9250 && basicsalary <= 9750) {
          SSS = 427.50;
      } else if (basicsalary >= 9750 && basicsalary <= 10250) {
          SSS = 450.00;
      } else if (basicsalary >= 10250 && basicsalary <= 10750) {
          SSS = 472.50;
      } else if (basicsalary >= 10750 && basicsalary <= 11250) {
          SSS = 495.00;
      } else if (basicsalary >= 11250 && basicsalary <= 11750) {
          SSS = 517.50;
      } else if (basicsalary >= 11750 && basicsalary <= 12250) {
          SSS = 540.00;
      } else if (basicsalary >= 12250 && basicsalary <= 12750) {
          SSS = 562.50;
      } else if (basicsalary >= 12750 && basicsalary <= 13250) {
          SSS = 585.00;
      } else if (basicsalary >= 13250 && basicsalary <= 13750) {
          SSS = 607.50;
      } else if (basicsalary >= 13750 && basicsalary <= 14250) {
          SSS = 630.00;
      } else if (basicsalary >= 14250 && basicsalary <= 14750) {
          SSS = 652.50;
      } else if (basicsalary >= 14750 && basicsalary <= 15250) {
          SSS = 675.00;
      } else if (basicsalary >= 15250 && basicsalary <= 15750) {
          SSS = 697.50;
      } else if (basicsalary >= 15750 && basicsalary <= 16250) {
          SSS = 720.00;
      } else if (basicsalary >= 16250 && basicsalary <= 16750) {
          SSS = 742.50;
      } else if (basicsalary >= 16750 && basicsalary <= 17250) {
          SSS = 765.00;
      } else if (basicsalary >= 17250 && basicsalary <= 17750) {
          SSS = 787.50;
      } else if (basicsalary >= 17750 && basicsalary <= 18250) {
          SSS = 810.00;
      } else if (basicsalary >= 18250 && basicsalary <= 18750) {
          SSS = 832.50;
      } else if (basicsalary >= 18750 && basicsalary <= 19250) {
          SSS = 855.00;
      } else if (basicsalary >= 19250 && basicsalary <= 19750) {
          SSS = 877.50;
      } else if (basicsalary >= 19750 && basicsalary <= 20250) {
          SSS = 900.00;
      } else if (basicsalary >= 20250 && basicsalary <= 20750) {
          SSS = 922.50;
      } else if (basicsalary >= 20750 && basicsalary <= 21750) {
          SSS = 967.50;
      } else if (basicsalary >= 21750 && basicsalary <= 22250) {
          SSS = 990.00;
      } else if (basicsalary >= 22250 && basicsalary <= 22750) {
          SSS = 1012.50;
      } else if (basicsalary >= 22750 && basicsalary <= 23250) {
          SSS = 1035.00;
      } else if (basicsalary >= 23250 && basicsalary <= 23750) {
          SSS = 1057.50;
      } else if (basicsalary >= 23750 && basicsalary <= 24250) {
          SSS = 1080.00;
      } else if (basicsalary >= 24250 && basicsalary <= 24750) {
          SSS = 1102.50;
      } else if (basicsalary > 24750) {
          SSS = 1125.50;
      } else {
          System.out.println("Error");
      }
        
      
    }
    // getters and setters

    
    
    public double getSSS() {
        return SSS;
    }



    public double getPagibigDeduct() {
        return PagibigDeduct;
    }

 

    public double getPhilDeduct() {
        return PhilDeduct;
    }

   
    
  
}
