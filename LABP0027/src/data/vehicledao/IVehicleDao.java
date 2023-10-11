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

    List<Vehicle> searchByColor(String color);

    List<Vehicle> searchByPrice(double price);

    List<Vehicle> searchByLessThanPrice(double price);

    List<Vehicle> searchByBrand(String brand);

    List<Vehicle> searchByYear(int year);

    List<Vehicle> searchByGreaterEqualThanYear(int year);

    List<Vehicle> listSortWithYear(int year);

    boolean saveToFile();
}
