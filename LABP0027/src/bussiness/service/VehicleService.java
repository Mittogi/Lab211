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
    public void checkExitsVehicle(String ID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Vehicle> getListVehicle() {
        return vehicleAction.getList();
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

}
