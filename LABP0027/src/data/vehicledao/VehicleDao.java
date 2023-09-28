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
}
