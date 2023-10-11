package bussiness.service;

import bussiness.entity.Vehicle;
import java.util.List;

public interface IVehicleService {
    void addVehicle(Vehicle vehicle) throws Exception;

    Vehicle checkExitsVehicle(String id);

    List<Vehicle> getListVehicle();

    void updateVehicle(List<String> listNewInforVehicle, Vehicle vehicle);

    void deleteVehicle(Vehicle vehicle);

    Vehicle searchById(String id);

    List<Vehicle> searchByName(String name);

    List<Vehicle> searchByColor(String color);

    List<Vehicle> searchByPrice(double price);

    List<Vehicle> searchByLessThanPrice(double price);

    List<Vehicle> searchByBrand(String brand);

    List<Vehicle> searchByYear(int year);

    List<Vehicle> searchByGreaterEqualThanYear(int year);

    List<Vehicle> getListSortByYear(int year);

    boolean saveToFile() throws Exception;
}
