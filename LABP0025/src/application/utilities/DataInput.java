package application.utilities;

import bussiness.component.DataValidation;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author PHAT
 */
public class DataInput {
//get number with message-------------------------------------------------------
    public static int getIntegerNumber (String displayMessage) {
        int number = 0;
        Scanner sc = new Scanner(System.in);
    
    //get number method
        while (true) {
            try {
                System.out.print(displayMessage);
                number = Integer.parseInt(sc.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Data invalid");
            }
        }
    //------------------
        return number;
        }
 
//get number without message----------------------------------------------------
    public static int getIntegerNumber () {
        int number = 0;
        Scanner sc = new Scanner(System.in);
    
    //get number method
        while (true) {
            try {
                number = Integer.parseInt(sc.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Data invalid");
            }
        }
    //------------------
        return number;
        }
    
//get string with message-------------------------------------------------------
    public static String getString (String displayMessage) throws Exception {
        String s;
        Scanner sc = new Scanner(System.in);
                
        System.out.print(displayMessage);
        
        s = sc.nextLine();
        
        return s;
    }
    
//get string without message----------------------------------------------------
    public static String getString () throws Exception {
        String s;
        Scanner sc = new Scanner(System.in);
                
        s = sc.nextLine();
        
        return s;
    }
    
//get Date with message---------------------------------------------------------
    public static Date getDate(String displayMessage) {
        Date date;
        Scanner sc = new Scanner(System.in);
        
        while (true){
        try {
            System.out.print(displayMessage);
            date = convertStringToDate(sc.nextLine());
            break;
        } catch (Exception e) {
            System.out.println("Date is invalid:");
        }
        }
        
        return date;
    }
    
//get Date without message------------------------------------------------------
    public static Date getDate() {
        Date date;
        Scanner sc = new Scanner(System.in);
        
        while (true){
        try {
            date = convertStringToDate(sc.nextLine());
            break;
        } catch (Exception e) {
            System.out.println("Date invalid:");
        }
        }
        
        return date;
    }
    
//convert String----------------------------------------------------------------
    public static Date convertStringToDate(String string) throws Exception{
        Date date;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        date = dateFormat.parse(string);
       
        return date;
    }
    
    public static int convertStringToInt(String string) throws Exception {
        return Integer.parseInt(string);
    }
}
