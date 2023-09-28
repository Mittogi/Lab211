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

    public static int getIntegerNumber(String displayMessage) {
        int number = 0;
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                System.out.print(displayMessage);
                number = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Data invalid");
            }
        }
        return number;
    }

    public static int getIntegerNumber() {
        int number = 0;
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                number = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Data invalid");
            }
        }

        return number;
    }

    public static String getString(String displayMessage) {
        String s;
        Scanner sc = new Scanner(System.in);

        System.out.print(displayMessage);

        s = sc.nextLine();

        return s;
    }
    

    public static String getString() {
        String s;
        Scanner sc = new Scanner(System.in);

        s = sc.nextLine();

        return s;
    }

    public static Date getDate(String displayMessage) {
        Date date;
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                System.out.print(displayMessage);
                date = convertStringToDate(sc.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Date is invalid. The correct format is dd-MM-yyyy:");
            }
        }

        return date;
    }

    public static Date getDate() {
        Date date;
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                date = convertStringToDate(sc.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Date invalid:");
            }
        }

        return date;
    }

    public static Date getCurrentDate() {
        Date date = new Date();
        return date;
    }

    public static Date convertStringToDate(String string) throws Exception {
        Date date;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        if (DataValidation.checkNewDate(string) == false) {
            throw new Exception();
        }

        date = dateFormat.parse(string);

        return date;
    }

    public static int convertStringToInt(String string) throws Exception {
        return Integer.parseInt(string);
    }
}
