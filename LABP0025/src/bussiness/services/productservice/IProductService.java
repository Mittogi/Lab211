package bussiness.services.productservice;

import bussiness.entity.Product;
import bussiness.services.IService;
import java.util.List;

/**
 *
 * @author PHAT
 * @param <Product>
 */
public interface IProductService<Product> extends IService<Product>{
    Product findProduct(String code) throws Exception;
    
    void updateProductInformation(List<String> listInformation, Product productUpdated) throws Exception;
    
    void deleteProduct(Product productDeleted) throws Exception;
}