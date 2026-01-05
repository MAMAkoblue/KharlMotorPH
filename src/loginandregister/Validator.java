/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginandregister;

/**
 *
 * @author zach malonjao
 */
public class Validator {
 
    public boolean isValidString(String value) {
        String pattern = "^[a-zA-Z\\s]+$";
        return value.matches(pattern);
    }
    
    //denies input if it has commas so csv file won't error
    public boolean isValidAddress(String input) {
    return !input.contains(",");
}
    
    public boolean isValidNumber(String value) {
            //ATTENTION DJANH FROM THE FUTURE
                //use this for all salary info (yung anim bale)
                //also patangal nalang ng comments once nakita mo to
                //nakakahiya kay sir AHHAHAH
            try {
                Double.parseDouble(value);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    
     public boolean isValidPhilhealthNumber(String value) {
        String pattern = "^\\d{12}$";
        return value.matches(pattern);
} 
     public boolean isValidPagibigNumber(String value) {
        String pattern = "^\\d{12}$";
        return value.matches(pattern);
} 

     public boolean isValidPhoneNumber(String phoneNumber) {
        String pattern =  "^\\s*\\d{3}-\\d{3}-\\d{3}\\s*$";
        return phoneNumber.matches(pattern);
}

     public boolean isValidSssNumber(String value){
        String pattern = "^\\d{2}-\\d{7}-\\d{1}$";  
        return value.matches(pattern);
     }

     public boolean isValidTin(String value){
         String pattern = "^\\d{3}-\\d{3}-\\d{3}-\\d{3}$";
         return value.matches(pattern);
     }
    
}


