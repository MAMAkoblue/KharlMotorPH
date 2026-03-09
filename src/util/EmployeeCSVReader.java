// copied from class na sinend ni zach
package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeCSVReader {
private List<String[]> employeeData; // Store data from CSV

    //Constructor to load employee data but when you create the class object, pass on the csv file
    public EmployeeCSVReader(String csvFile) {
        employeeData = new ArrayList<>();
        loadDataFromCSV(csvFile);
    }

        private void loadDataFromCSV(String csvFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            
            String line;
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");
                employeeData.add(columns);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Pass the employee ID being searched and return the array employee. 
    public String[] searchEmployee(String empID) {
        for (String[] employee : employeeData) {
            if (employee[0].equals(empID)) {
                return employee;
            }
        }
        return null; // Returns null if the employee doesn't exist
    }

      public List<String[]> getEmployeeData() {
        return employeeData;
    }  
      
      
}
