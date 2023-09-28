package application.ui;

import application.utilities.DataInput;
import bussiness.service.IVehicleService;
import java.util.Arrays;

/**
 *
 * @author PHAT
 */
public class Menu {

    public static void print(String str) {
        var menuList = Arrays.asList(str.split("\\|"));
        menuList.forEach(menuItem -> {
            if (menuItem.equalsIgnoreCase("Select")) {
                System.out.print(menuItem);
            } else {
                System.out.println(menuItem);
            }

        });
    }

    public static int getUserChoice() {
        int number = 0;
        try {
            number = DataInput.getIntegerNumber();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return number;
    }

    public static void manageVehicle(IVehicleService service) {
        MenuVehicle empMenu = new MenuVehicle(service);
        empMenu.processMenuForVehicle();
    }
}
