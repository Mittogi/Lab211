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
    
    Vehicle checkToexistVehicle(String id);
    
    void updateVehicle(List<String> listNewInforVehicle, Vehicle vehicle);
    
    void deleteVehicle(Vehicle vehicle);
    
    Vehicle searchById(String id);
    
    List<Vehicle> searchByName(String name);

    List<Vehicle> listSortWithYear(int year);

    boolean saveToFile();
}
