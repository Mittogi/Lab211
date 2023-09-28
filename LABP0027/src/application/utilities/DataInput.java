package application.utilities;

import java.util.Scanner;

/**
 *
 * @author PHAT
 */
public class DataInput {

    public static int getIntegerNumber(String displayMessage) {
        int number;
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
        int number;
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
    
    public static double getDoubleNumber(String displayMessage) {
        double number;
        Scanner sc = new Scanner(System.in);

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
}
