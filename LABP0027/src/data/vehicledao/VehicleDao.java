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
    public Vehicle checkToexistVehicle(String id) {
        Vehicle vehicle = null; 
        
        for (Vehicle vehicleEmp : listVehicle) {
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
        
        if (!name.equals("")) {
            vehicleUpdated.setName(name);
        }
        
        if (!color.equals("")) {
            vehicleUpdated.setColor(color);
        }
        
        if (!price.equals("")) {
            vehicleUpdated.setPrice(Integer.parseInt(price));
        }
        
        if (!brand.equals("")) {
            vehicleUpdated.setBrand(brand);
        }
        
        if (!type.equals("")) {
            vehicleUpdated.setType(type);
        }
        
        if (!year.equals("")) {
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
            if(vehicle.getName().equalsIgnoreCase(name)) {
                listVehicleResult.add(vehicle);
            }
        }
        
        return listVehicleResult;
    }
}
