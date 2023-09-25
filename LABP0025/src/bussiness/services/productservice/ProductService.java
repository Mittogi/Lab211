package bussiness.services.productservice;

import bussiness.entity.Product;
import data.FileManager;
import data.factory.DaoFactory;
import data.factory.IDaoFactory;
import data.productdao.IProductDao;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    public Product findProduct(String code) {
        Product product = (Product) productAction.findProduct(code);
        return product;
    }

    @Override
    public void updateProductInformation(List<String> listInformation, Product productUpdated) throws Exception {
        productAction.updateProductInformation(listInformation, productUpdated);
    }

    @Override
    public void deleteProduct(Product productDeleted) throws Exception {
        productAction.deleteProduct(productDeleted);
    }

    @Override
    public List<Product> productHaveExpried() {
        List<Product> listProductHaveExpired = new ArrayList<>();
        List<Product> listProduct = getList();

        for (Product product : listProduct) {
            int comparisonResult = product.getManufacturingDate().compareTo(product.getExpirationDate());

            if (comparisonResult > 0) {
                listProductHaveExpired.add(product);
            }
        }

        return listProductHaveExpired;
    }

    @Override
    public List<Product> productIsSelling() {
        List<Product> listProductIsSelling = new ArrayList<>();
        List<Product> listProduct = getList();

        for (Product product : listProduct) {
            int comparisonResult = product.getManufacturingDate().compareTo(product.getExpirationDate());

            if (comparisonResult <= 0 && product.getQuantity() > 0) {
                listProductIsSelling.add(product);
            }
        }

        return listProductIsSelling;
    }

    @Override
    public List<Product> productAreRunningOutOfStock() {
        List<Product> listProductOutOfStock = new ArrayList<>();
        List<Product> listProduct = getList();

        for (Product product : listProduct) {
            if (product.getQuantity() <= 3) {
                listProductOutOfStock.add(product);
            }
        }

        listProductOutOfStock.sort(
            new Comparator<Product>() {
                @Override
                public int compare(Product o1, Product o2) {
                    return o1.getQuantity() - o2.getQuantity();
                }

            }
        );

        return listProductOutOfStock;
    }

    @Override
    public void saveFile() throws Exception {
        productAction.saveFile();
    }

}
