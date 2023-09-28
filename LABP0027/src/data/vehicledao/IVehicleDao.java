package data.vehicledao;

import bussiness.entity.Vehicle;
import java.util.List;

/**
 *
 * @author PHAT
 */
public interface IVehicleDao {

    List<Vehicle> getList();
    
    boolean addList(Vehicle vehicle);
}
