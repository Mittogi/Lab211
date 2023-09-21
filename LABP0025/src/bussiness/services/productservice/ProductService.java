package bussiness.services.productservice;

import bussiness.entity.Product;
import data.FileManager;
import data.factory.DaoFactory;
import data.factory.IDaoFactory;
import data.productdao.IProductDao;
import java.util.List;

/**
 *
 * @author PHAT
 */
public class ProductService implements IProductService<Product> {

    IProductDao<Product> productAction;
    IDaoFactory productDaoFactory;

    public ProductService() throws Exception {
        productDaoFactory = new DaoFactory();
        this.productAction = productDaoFactory.getProductDao();
    }

    @Override
    public void printList() {
        productAction.getList().forEach(obj -> System.out.println(obj));
    }

    @Override
    public List<Product> getList() {
        return productAction.getList();
    }

    @Override
    public void add(Product obj) throws Exception {
        productAction.addNew(obj);
    }

    @Override
    public Product findProduct(String code) throws Exception {
        Product product = (Product) productAction.findProduct(code);
        return product;
    }

//case 2------------------------------------------------------------------------
    @Override
    public void updateProductInformation(List<String> listInformation, Product productUpdated) throws Exception {
        productAction.updateProductInformation(listInformation, productUpdated);
    }

//case 3------------------------------------------------------------------------
    @Override
    public void deleteProduct(Product productDeleted) throws Exception {
          productAction.deleteProduct(productDeleted);
    }
    


}
