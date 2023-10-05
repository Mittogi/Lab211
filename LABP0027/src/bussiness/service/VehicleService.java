package bussiness.service;

import bussiness.entity.Vehicle;
import data.vehicledao.IVehicleDao;
import data.vehicledao.VehicleDao;
import java.util.List;

/**
 *
 * @author PHAT
 */
public class VehicleService implements IVehicleService {

    IVehicleDao vehicleAction = new VehicleDao();


    @Override
    public void addVehicle(Vehicle vehicle) throws Exception{
        if (vehicleAction.addList(vehicle) == false) {
            throw new Exception("Add error");
        }
    }

    @Override
    public Vehicle checkExitsVehicle(String id) {
        return vehicleAction.checkToexistVehicle(id);
    }

    @Override
    public void updateVehicle(List<String> listNewInforVehicle, Vehicle vehicle) {
        vehicleAction.updateVehicle(listNewInforVehicle, vehicle);
    }
    
    @Override
    public void deleteVehicle(Vehicle vehicle) {
        vehicleAction.deleteVehicle(vehicle);
    }

    @Override
    public Vehicle searchById(String id) {
         return vehicleAction.searchById(id);
    }

    @Override
    public List<Vehicle> searchByName(String name) {
        return vehicleAction.searchByName(name);
    }
       
    @Override
    public List<Vehicle> getListVehicle() {
        return vehicleAction.getList();
    }

    @Override
    public List<Vehicle> getListSortByYear(int year) {
        return vehicleAction.listSortWithYear(year);
    }

    @Override
    public boolean saveToFile() throws Exception {
        return vehicleAction.saveToFile();
    }
    
}
