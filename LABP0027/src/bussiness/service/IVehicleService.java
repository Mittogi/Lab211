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
}
