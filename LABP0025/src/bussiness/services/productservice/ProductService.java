package bussiness.services.productservice;

import bussiness.entity.Product;
import data.factory.DaoFactory;
import data.factory.IDaoFactory;
import data.productdao.IProductDao;
import java.util.List;

/**
 *
 * @author PHAT
 */
public class ProductService implements IProductService<Product> {

    IProductDao productAction;
    IDaoFactory productDaoFactory;

//contructor--------------------------------------------------------------------    
    public ProductService(String inputDataFile) throws Exception {
        productDaoFactory = new DaoFactory(inputDataFile);
        this.productAction = productDaoFactory.productDao();
    }

//------------------------------------------------------------------------------
    @Override
    public void printList() throws Exception {
        productAction.getList().forEach(obj -> System.out.println(obj));
    }

    @Override
    public List<Product> getList() throws Exception {
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
