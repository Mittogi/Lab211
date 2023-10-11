package application.ui;

import application.utilities.DataInput;
import application.utilities.DataValidation;
import bussiness.entity.Vehicle;
import bussiness.service.IVehicleService;
import bussiness.service.VehicleService;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author PHAT
 */
public class MenuVehicle {

    IVehicleService service = new VehicleService();

    public MenuVehicle(IVehicleService service) {
        this.service = service;
    }

    public void processMenuForVehicle() {
        boolean quit = false;

        try {
            do {
                Menu.print(
                        "******Product Management******|1.Add new vehicle.|2.Check exits vehicle.|3.Update vehicle.|4.Delete vehicle.|5.Search vehicle.|6.Display vehicle list|7.Print vehicle list.|Other.Back to main menu|Select: ");

                int choice = Menu.getUserChoice();

                switch (choice) {
                    case 1 -> {
                        addVehicle();
                    }

                    case 2 -> {
                        checkToExistVehicle();
                    }

                    case 3 -> {
                        updateVehicle();
                    }

                    case 4 -> {
                        deleteVehicle();
                    }

                    case 5 -> {
                        searchVehicle();
                    }

                    case 6 -> {
                        displayVehicleList();
                    }
                    
                    case 7 -> {
                        printVehicleList();
                    }

                    case 8 -> {
                        updatePriceByBrand();
                    }

                    default -> {
                        quit = true;
                    }
                }

                if (quit) {
                    break;
                }
            } while (true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addVehicle() {
        String id, name, color, brand, type;
        int year;
        double price;

        do {
            id = DataInput.getString("Enter id: ");

            if (!DataValidation.checkStringWithFormat(id, "\\d{5}")) {
                System.out.println("Id invalid. The correct id is xxxxx with x is digit");
            }

            if (DataValidation.checkStringIsDulicated(service.getListVehicle(), id)) {
                System.out.println("Id is dulicated");
            }
        } while (!DataValidation.checkStringWithFormat(id, "\\d{5}")
                || DataValidation.checkStringIsDulicated(service.getListVehicle(), id));

        do {
            name = DataInput.getString("Enter name: ").toUpperCase();

            if (DataValidation.checkValueIsEmpty(name)) {
                System.out.println("Name is empty");
            }
        } while (DataValidation.checkValueIsEmpty(name));

        do {
            color = DataInput.getString("Enter color: ").toUpperCase();

            if (DataValidation.checkValueIsEmpty(color)) {
                System.out.println("Color is empty");
            }
        } while (DataValidation.checkValueIsEmpty(color));

        do {
            price = DataInput.getDoubleNumber("Enter price: ");

            if (price < 0) {
                System.out.println("Price is not smaller than 0");
            }
        } while (price < 0.0);

        do {
            brand = DataInput.getString("Enter brand: ").toUpperCase();

            if (DataValidation.checkValueIsEmpty(brand)) {
                System.out.println("Brand is empty");
            }
        } while (DataValidation.checkValueIsEmpty(brand));

        do {
            type = DataInput.getString("Enter type: ").toUpperCase();

            if (DataValidation.checkValueIsEmpty(type)) {
                System.out.println("Type is empty");
            }
        } while (DataValidation.checkValueIsEmpty(type));

        do {
            year = DataInput.getIntegerNumber("Enter year: ");

            if (year < 1950 || year > 2023) {
                System.out.println("Year invalid, min year is 1950 and max year is 2023");
            }
        } while (year < 1950 || year > 2023);
        Vehicle vehicle = new Vehicle(id, name, color, price, brand, type, year);

        try {
            service.addVehicle(vehicle);
            System.out.println("Vehicle added");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void checkToExistVehicle() {
        String id = DataInput.getString("Enter id:");

        Vehicle vehicle = service.checkExitsVehicle(id);

        if (vehicle == null) {
            System.out.println("No Vehicle Found In File!");
        } else {
            System.out.println(vehicle);
        }
    }

    public void searchVehicle() {
        Menu.print("******Search Vehicle******|1.Search by name.|2.Search by id|Select: ");

        int choice = Menu.getUserChoice();

        switch (choice) {
            case 1 -> {
                searchVehicleByName();
            }

            case 2 -> {
                searchVehicleById();
            }
        }

    }

    public void updateVehicle() {
        String id, name, color, price, brand, type, year;
        List<String> listNewInfoVehicle = new ArrayList<>();

        id = DataInput.getString("Enter id: ");
        Vehicle vehicle = service.searchById(id);

        if (vehicle == null) {
            System.out.println("Vehicle does not exist");
        } else {
            name = DataInput.getString("Enter new name: ").toUpperCase();

            color = DataInput.getString("Enter new color: ").toUpperCase();

            do {
                price = DataInput.getString("Enter new price: ");

                if (!DataValidation.checkDoubleForUpdate(price)) {
                    System.out.println("Price invalid");
                } else {
                    break;
                }
            } while (true);

            brand = DataInput.getString("Enter new brand: ").toUpperCase();

            type = DataInput.getString("Enter new type: ").toUpperCase();

            do {
                year = DataInput.getString("Enter new year: ");

                if (!DataValidation.checkIntegerForUpdate(year, 1950, 2023)) {
                    System.out.println("Year invalid. Year is interger number, year >= 1950 and <=2023");
                } else {
                    break;
                }
            } while (true);

            listNewInfoVehicle.add(name);
            listNewInfoVehicle.add(color);
            listNewInfoVehicle.add(price);
            listNewInfoVehicle.add(brand);
            listNewInfoVehicle.add(type);
            listNewInfoVehicle.add(year);

            service.updateVehicle(listNewInfoVehicle, vehicle);

            System.out.println("Vehicle updated");
        }

    }

    public void deleteVehicle() {
        String id = DataInput.getString("Enter id: ");

        Vehicle vehicle = service.searchById(id);

        if (vehicle == null) {
            System.out.println("Vehicle not found");
        } else {
            String comfirm = DataInput.getString("Comfirm delete?(Y/N): ");

            if (DataValidation.checkStringWithFormat(comfirm, "^[yY]$")) {
                service.deleteVehicle(vehicle);
                System.out.println("Vehicle deleted");
            }
        }
    }

    public void searchVehicleById() {
        String id = DataInput.getString("Enter id: ");

        Vehicle vehicleResult = service.searchById(id);

        if (vehicleResult == null) {
            System.out.println("Vehicle not found");
        } else {
            System.out.println(vehicleResult);
        }
    }

    public void searchVehicleByName() {
        String name = DataInput.getString("Enter name: ").toUpperCase();

        List<Vehicle> listVehicleReslut = service.searchByName(name);

        listVehicleReslut.sort(
                new Comparator<Vehicle>() {

                    @Override
                    public int compare(Vehicle o1, Vehicle o2) {
                        return o2.getName().compareTo(o1.getName());
                    }

                });

        if (listVehicleReslut.isEmpty())

        {
            System.out.println("Vehicle not found");
        } else {
            for (Vehicle vehicle : listVehicleReslut) {
                System.out.println(vehicle);
            }
        }
    }

    public void displayVehicleList() {
        Menu.print("******Display Vehicle List******|1.Show all vehicle.|2.Show vehicle by price|Select: ");

        int choice = Menu.getUserChoice();

        switch (choice) {
            case 1 -> {
                displayAllVehicle();
            }

            case 2 -> {
                displayVehicleByPrice();
            }
        }
    }

    public void displayAllVehicle() {
        List<Vehicle> listVehicle = service.getListVehicle();
        System.out.println("Vehicle list:");

        if (listVehicle.isEmpty()) {
            System.out.println("List is empty");
        }

        for (Vehicle vehicle : listVehicle) {
            System.out.println(vehicle);
        }
    }

    public void displayVehicleByPrice() {
        Double price = DataInput.getDoubleNumber("Enter price: ");
        List<Vehicle> listVehicle = service.searchByLessThanPrice(price);

        listVehicle.sort(
                new Comparator<Vehicle>() {

                    @Override
                    public int compare(Vehicle o1, Vehicle o2) {
                        if (o2.getPrice() > o1.getPrice()) {
                            return 1;
                        }

                        return -1;
                    }
                }

        );

        if (listVehicle.isEmpty()) {
            System.out.println("List is empty");
        }

        for (Vehicle vehicle : listVehicle) {
            System.out.println(vehicle);
        }
    }

    public void printVehicleList() {
        Menu.print("******Print Vehicle List******|1.Print all vehicle.|2.Print vehicle by year|Select: ");

        int choice = Menu.getUserChoice();

        switch (choice) {
            case 1 -> {
                displayAllVehicle();
            }

            case 2 -> {
                printAllSortByYear();
            }
        }
    }

    public void printAllSortByYear() {
        int year, lenght;
        year = DataInput.getIntegerNumber("Enter year:");
        List<Vehicle> listCheck = service.getListSortByYear(year);
        lenght = listCheck.size();

        if (lenght != 0) {
            for (Vehicle vehicle : listCheck) {
                System.out.println(vehicle);
            }
        } else
            System.out.println("List empty");

    }

//11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111
    public void updatePriceByBrand() {
        String brand = DataInput.getString("Enter brand: ");
        List<Vehicle> listVehicleUpdate = service.searchByBrand(brand);

        for (Vehicle vehicle : listVehicleUpdate) {
            System.out.println(vehicle);
        }
    }
}

