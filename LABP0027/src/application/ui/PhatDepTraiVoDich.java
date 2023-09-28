package application.ui;

import application.utilities.DataInput;
import bussiness.entity.Vehicle;
import bussiness.service.IVehicleService;
import bussiness.service.VehicleService;
import java.util.Collections;

/**
 *
 * @author PHAT
 */
public class PhatDepTraiVoDich {

    public static void main(String[] args) throws Exception {
        boolean quit = false;
        int choice;

        System.out.println(String.join("", Collections.nCopies(10, "**********")));

        try {
            IVehicleService service = new VehicleService();
            
            do {
                Menu.print("1.Vehicle manager.|2.Save|Other.Quit|Select: ");

                choice = DataInput.getIntegerNumber();

                switch (choice) {
                    case 1 -> {
                        Menu.manageVehicle(service);
                    }

                    case 2 -> {
                    }
                    
                    default -> {
                        quit = true;
                    }
                }

                if (quit) {
                    System.out.println("good bye");
                    break;
                }
            } while (!quit);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
