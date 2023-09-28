package data.productdao;

import bussiness.entity.Product;
import data.IUserDao;
import java.util.List;

/**
 *
 * @author PHAT
 * @param <Product>
 */
public interface IProductDao<Product> extends IUserDao<Product>{
    void loadDataFromFile() throws Exception; 
    
    Product findProduct(String code);
    
    void updateProductInformation(List<String> listInformation, Product productUpdated) throws Exception;

    void deleteProduct(Product productDeleted) throws Exception;
    
    void saveFile() throws Exception;
    
    List<Product> findProductByName(String name);
    
    void deleteProductByQuantity(int value);
}
