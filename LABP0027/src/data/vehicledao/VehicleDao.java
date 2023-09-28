package data.vehicledao;

import bussiness.entity.Vehicle;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PHAT
 */
public class VehicleDao implements IVehicleDao{
    List<Vehicle> listVehicle = new ArrayList<>();

    public VehicleDao() {
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
            if(vehicle.getName().equalsIgnoreCase(name)) {
                listVehicleResult.add(vehicle);
            }
        }
        
        return listVehicleResult;
    }
}
