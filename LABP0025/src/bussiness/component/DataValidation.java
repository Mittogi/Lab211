package bussiness.component;

import application.utilities.DataInput;
import bussiness.entity.Product;
import bussiness.services.productservice.ProductService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * @author PHAT
 */
public final class DataValidation {
//------------------------------------------------------------------------------

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

//case 1 product manager--------------------------------------------------------
    public static boolean checkNewProductIsValid(Product newProduct, List<Product> listProduct) throws Exception {
        boolean result = true;
        
        newProduct.setCode(newProduct.getCode().toUpperCase());
        newProduct.setName(newProduct.getName().toUpperCase());
        
        String newProductCode = newProduct.getCode();
        String newProductName = newProduct.getName();
        int newProductQuantity = newProduct.getQuantity();
        Date newProductManufacturingDate = newProduct.getManufacturingDate();
        Date newProductExpirationDate = newProduct.getExpirationDate();
        
        if (!DataValidation.checkCodeIsValid(newProductCode, "P")) {
            result = false;
        }
        
        if (DataValidation.checkCodeIsDulicated(newProductCode, listProduct)) {
            result = false;
        }
        
        if (newProductQuantity < 0) {
            result = false;
        }
        
        return result;
    }
    
    public static boolean checkCodeIsValid(String code, String firstCharacter) throws Exception{
        boolean result = true;
        
//        if (checkValueIsEmpty(code) == true) {
//            result = false;
//        }
        
        if (checkValueLengthInRange(code, 8, 8) == false) {
            result = false;
        }

        ArrayList<String> splitedString = new ArrayList<>();
        splitedString.add(code.substring(0, 1));
        splitedString.add(code.substring(1));

        if (splitedString.get(0).matches(firstCharacter) == false) {
            result = false;
        }

        try {
            int number = Integer.parseInt(splitedString.get(1));
        } catch (Exception e) {
            result = false;
        }
        
        if (result == false) {
            throw new Exception("Code is invalid. The correct code is Pxxxxxxx with x is digit");
        }

        return result;
    }

    public static boolean checkCodeIsDulicated(String code, List<Product> productList) throws Exception {
        boolean result = false;

        for (Product oldProduct : productList) {
            String oldProductCode = oldProduct.getCode();

            if (code.equalsIgnoreCase(oldProductCode)) {
                result = true;
                break;
            }
        }
        
        if (result == true) {
            throw new Exception("Code is dulicated");
        }

        return result;
    }
    
//case 2------------------------------------------------------------------------    
    public static boolean checkNewQuatiy(String value) {
        boolean result = true;
        
        if (checkValueIsEmpty(value) == true) {
            return true;
        }
        
        try {
            int number = DataInput.convertStringToInt(value);
            
            if (number < 0) {
                result = false;
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("Data invalid");
            result = false;
        }
        
        return result;
    }
    
    public static boolean checkNewDate(String value) {
        boolean result = true;
        
        if (checkValueIsEmpty(value) == true) {
            return true;
        }
        
        try {
            Date date = DataInput.convertStringToDate(value);
        } catch (Exception e) {
            System.out.println("Date is invalid. The correct format is yyyy-MM-dd");
            result = false;
        }
        
        return result;
    }
    
//case 3------------------------------------------------------------------------
}
