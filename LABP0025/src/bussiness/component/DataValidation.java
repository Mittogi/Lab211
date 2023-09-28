package bussiness.component;

import application.utilities.DataInput;
import bussiness.entity.Product;
import bussiness.entity.Receipt;
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

//product manager--------------------------------------------------------
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

    public static boolean checkProductCodeIsDulicated(String code, List<Product> list) {
        boolean result = false;

        for (Product oldItem : list) {
            String oldItemCode = oldItem.getCode();

            if (code.equalsIgnoreCase(oldItemCode)) {
                result = true;
                break;
            }
        }

        return result;
    }

    public static boolean checkNewDate(String date) {
        boolean result = true;

        if (checkValueIsEmpty(date) == true) {
            return false;
        }

        if (date.length() != 10) {
            result = false;
        }
        String[] dateParts = date.split("-");
        if (dateParts.length != 3) {
            result = false;
        }
        int day = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int year = Integer.parseInt(dateParts[2]);

        if (month < 1 || month > 12) {
            result = false;
        }
        if (year < 1) {
           result = false;
        }
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            if (day > 30 || day < 1) {
                result = false;
            }
        } else if (month != 2) {
            if (day > 31 || day < 1) {
                result = false;
            }
        }
        if ((year % 400 == 0) || (year % 4 == 0 && year % 100 != 0)) {
            if (month == 2 && (day > 29 || day < 1)) {
                result = false;
            }
        } else {
            if (month == 2 && (day > 28 || day < 1)) {
                result = false;
            }
        }

        return result;
    }
    
    public static boolean checkNewDateForUpdate(String date) {
        boolean result = true;

        if (checkValueIsEmpty(date) == true) {
            return true;
        }

        if (date.length() != 10) {
            result = false;
        }
        String[] dateParts = date.split("-");
        if (dateParts.length != 3) {
            result = false;
        }
        int day = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int year = Integer.parseInt(dateParts[2]);

        if (month < 1 || month > 12) {
            result = false;
        }
        if (year < 1) {
           result = false;
        }
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            if (day > 30 || day < 1) {
                result = false;
            }
        } else if (month != 2) {
            if (day > 31 || day < 1) {
                result = false;
            }
        }
        if ((year % 400 == 0) || (year % 4 == 0 && year % 100 != 0)) {
            if (month == 2 && (day > 29 || day < 1)) {
                result = false;
            }
        } else {
            if (month == 2 && (day > 28 || day < 1)) {
                result = false;
            }
        }

        return result;
    }

//receipt manager---------------------------------------------------------------
    public static boolean checkRecieptCodeIsDulicated(String code, List<Receipt> list) {
        boolean result = false;

        for (Receipt oldItem : list) {
            String oldItemCode = oldItem.getCode();

            if (code.equalsIgnoreCase(oldItemCode)) {
                result = true;
                break;
            }
        }

        return result;
    }

    public static boolean checkProductOfReceiptIsDulicated(String productCode, List<Product> productListOfReceipt) {
        boolean result = false;

        for (Product product : productListOfReceipt) {
            if (product.getCode().equalsIgnoreCase(productCode) == true) {
                result = true;
                break;
            }
        }

        return result;
    }

    public static boolean checkProductIsExist(String code, List<Product> productList) {
        boolean result = false;

        for (Product product : productList) {
            if (product.getCode().equalsIgnoreCase(code) == true) {
                result = true;
                break;
            }
        }

        return result;
    }
}
