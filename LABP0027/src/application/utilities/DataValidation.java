package application.utilities;

import bussiness.entity.Vehicle;
import java.util.List;

/**
 *
 * @author PHAT
 */
public class DataValidation {

    public static boolean checkNumberInRange(int value, int min, int max) {
        return !(value < min || value > max); // true if value in range
    }

    public static boolean checkValueIsEmpty(String value) {
        return (value.isEmpty()); // true if String is empty
    }

    public static boolean checkValueLengthInRange(String value, int min, int max) {
        if (checkValueIsEmpty(value) == true) {
            return false;
        }

        return !(value.length() < min || value.length() > max); // true if value in range
    }

    public static boolean checkStringWithFormat(String value, String pattern) {
        return value.matches(pattern); //true if String matches pattern
    }

    public static boolean checkStringIsDulicated(List<Vehicle> listVehicle, String value) {
        boolean result = false;
        
        for (Vehicle vehicle : listVehicle) {
            if(vehicle.getId().equalsIgnoreCase(value)) {
                result = true;
            }
        }
        
        return result;
    }
    
    public static boolean checkIntegerForUpdate(String value) {
        boolean result = true;
        
        if (value.isEmpty()) {
            return true;
        }
        
        try {
            int number = Integer.parseInt(value);
            
            if (number < 0) {
                result = false;
            }
        } catch (NumberFormatException e) {
            result = false;
        }
        return result;
    }
//------------------------------------------------------------------------------
}
