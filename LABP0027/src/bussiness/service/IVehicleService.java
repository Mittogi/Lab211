package bussiness.service;

import bussiness.entity.Vehicle;
import java.util.List;

public interface IVehicleService {
    void addVehicle(Vehicle vehicle) throws Exception;
    
    void checkExitsVehicle(String ID);
    
    List<Vehicle> getListVehicle();
    
    void deleteVehicle(Vehicle vehicle);
    
    Vehicle searchById(String id);
    
    List<Vehicle> searchByName(String name);
}
