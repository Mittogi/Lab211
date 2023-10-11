package application.utilities;

import java.util.Scanner;

/**
 *
 * @author PHAT
 */
public class DataInput {
    static Scanner sc = new Scanner(System.in);

    public static int getIntegerNumber(String displayMessage) {
        int number;

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
        int number;

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
    
    public static double getDoubleNumber(String displayMessage) {
        double number;

        while (true) {
            try {
                System.out.print(displayMessage);
                number = Double.parseDouble(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Data invalid");
            }
        }
        return number;
    }


    public static String getString(String displayMessage) {
        String s;

        System.out.print(displayMessage);

        s = sc.nextLine();

        return s;
    }

    public static String getString() {
        String s;

        s = sc.nextLine();

        return s;
    }
}
