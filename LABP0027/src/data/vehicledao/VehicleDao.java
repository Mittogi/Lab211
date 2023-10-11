package data.vehicledao;

import bussiness.entity.Vehicle;
import data.filemanager.FileManager;
import data.filemanager.IFileManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author PHAT
 */
public class VehicleDao implements IVehicleDao {
    IFileManager vFileManager;
    List<Vehicle> listVehicle = new ArrayList<>();

    public VehicleDao() {
        try {
            vFileManager = new FileManager("vehicle.txt");
            loadDataFromFile();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    // Read data
    public void loadDataFromFile() throws Exception {
        for (String p : vFileManager.readDataFromFile()) {
            Vehicle vehicle = convertStringToVehicle(p);
            listVehicle.add(vehicle);
        }
    }

    public Vehicle convertStringToVehicle(String rawString) {
        String id, name, color, brand, type;
        double price;
        int year;
        List<String> raw = Arrays.asList(rawString.split(","));

        id = raw.get(0).trim();
        name = raw.get(1).trim();
        color = raw.get(2).trim();
        price = Double.parseDouble(raw.get(3).trim());
        brand = raw.get(4).trim();
        type = raw.get(5).trim();
        year = Integer.parseInt(raw.get(6).trim());

        return new Vehicle(id, name, color, price, brand, type, year);
    }

    public boolean saveToFile() {
        try {
            List<String> rawList = new ArrayList<>();

            for (Vehicle vehicle : getList()) {
                String line = vehicle.toString() + "\n";
                rawList.add(line);
            }

            vFileManager.commitFile(rawList);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public List<Vehicle> getList() {
        return listVehicle;
    }

    @Override
    public boolean addList(Vehicle vehicle) {
        return listVehicle.add(vehicle);
    }

    @Override
    public Vehicle checkToexistVehicle(String id) {
        Vehicle vehicle = null;
        List<Vehicle> listFromFile = new ArrayList<>();

        try {
            for (String p : vFileManager.readDataFromFile()) {
                listFromFile.add(convertStringToVehicle(p));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        for (Vehicle vehicleEmp : listFromFile) {
            if (vehicleEmp.getId().equalsIgnoreCase(id)) {
                vehicle = vehicleEmp;
                break;
            }
        }

        return vehicle;
    }

    @Override
    public void updateVehicle(List<String> listNewInforVehicle, Vehicle vehicleUpdated) {
        String name = listNewInforVehicle.get(0);
        String color = listNewInforVehicle.get(1);
        String price = listNewInforVehicle.get(2);
        String brand = listNewInforVehicle.get(3);
        String type = listNewInforVehicle.get(4);
        String year = listNewInforVehicle.get(5);

        if (!name.isBlank()) {
            vehicleUpdated.setName(name);
        }

        if (!color.isBlank()) {
            vehicleUpdated.setColor(color);
        }

        if (!price.isBlank()) {
            vehicleUpdated.setPrice(Double.parseDouble(price));
        }

        if (!brand.isBlank()) {
            vehicleUpdated.setBrand(brand);
        }

        if (!type.isBlank()) {
            vehicleUpdated.setType(type);
        }

        if (!year.isBlank()) {
            vehicleUpdated.setYear(Integer.parseInt(year));
        }
    }

    @Override
    public void deleteVehicle(Vehicle vehicle) {
        getList().remove(vehicle);
    }

    @Override
    public Vehicle searchById(String id) {
        Vehicle vehicleResult = null;

        for (Vehicle vehicle : getList()) {
            if (vehicle.getId().equalsIgnoreCase(id)) {
                vehicleResult = vehicle;
                break;
            }
        }

        return vehicleResult;
    }

    @Override
    public List<Vehicle> searchByName(String name) {
        List<Vehicle> listVehicleResult = new ArrayList<>();
        List<Vehicle> listVehicle = getList();

        for (Vehicle vehicle : listVehicle) {
            if (vehicle.getName().contains(name)) {
                listVehicleResult.add(vehicle);
            }
        }

        return listVehicleResult;
    }

    @Override
    public List<Vehicle> searchByColor(String color) {
        List<Vehicle> listVehicleResult = new ArrayList<>();
        List<Vehicle> listVehicle = getList();

        for (Vehicle vehicle : listVehicle) {
            if (vehicle.getColor().equalsIgnoreCase(color)) {
                listVehicleResult.add(vehicle);
            }
        }

        return listVehicleResult;
    }

    @Override
    public List<Vehicle> searchByPrice(double price) {
        List<Vehicle> listVehicleResult = new ArrayList<>();
        List<Vehicle> listVehicle = getList();

        for (Vehicle vehicle : listVehicle) {
            if (vehicle.getPrice() == price) {
                listVehicleResult.add(vehicle);
            }
        }

        return listVehicleResult;
    }

    @Override
    public List<Vehicle> searchByLessThanPrice(double price) {
        List<Vehicle> listVehicleResult = new ArrayList<>();
        List<Vehicle> listVehicle = getList();

        for (Vehicle vehicle : listVehicle) {
            if (vehicle.getPrice() < price) {
                listVehicleResult.add(vehicle);
            }
        }

        return listVehicleResult;
    }

    @Override
    public List<Vehicle> searchByBrand(String brand) {
        List<Vehicle> listVehicleResult = new ArrayList<>();
        List<Vehicle> listVehicle = getList();

        for (Vehicle vehicle : listVehicle) {
            if (vehicle.getBrand().equalsIgnoreCase(brand)) {
                listVehicleResult.add(vehicle);
            }
        }

        return listVehicleResult;
    }

    @Override
    public List<Vehicle> searchByYear(int year) {
        List<Vehicle> listVehicleResult = new ArrayList<>();
        List<Vehicle> listVehicle = getList();

        for (Vehicle vehicle : listVehicle) {
            if (vehicle.getYear() == year) {
                listVehicleResult.add(vehicle);
            }
        }

        return listVehicleResult;
    }

    @Override
    public List<Vehicle> searchByGreaterEqualThanYear(int year) {
        List<Vehicle> listVehicleResult = new ArrayList<>();
        List<Vehicle> listVehicle = getList();

        for (Vehicle vehicle : listVehicle) {
            if (vehicle.getYear() >= year) {
                listVehicleResult.add(vehicle);
            }
        }

        return listVehicleResult;
    }

    // ----------------------------------------------------------------------------------------------------------------------------------

    // Funtion 8:
    @Override
    public List<Vehicle> listSortWithYear(int year) {
        List<Vehicle> listCheck = searchByGreaterEqualThanYear(year);

        listCheck.sort(new Comparator<Vehicle>() {
            @Override
            public int compare(Vehicle o1, Vehicle o2) {
                return o2.getYear() - o1.getYear();
            }

        });
        return listCheck;
    }
}
