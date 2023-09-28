package data.productdao;

import bussiness.entity.Product;
import data.FileManager;
import data.IFileManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * @author PHAT
 */
public class ProductDao implements IProductDao<Product> {

    IFileManager fileManager;
    List<Product> productList = new ArrayList<>();

    public ProductDao() throws Exception {
        fileManager = new FileManager<Product>(
                FileManager.productFileName
        );
        loadDataFromFile();
    }

    private static IProductDao<Product> INSTANCE = null;

    public static IProductDao<Product> getInstance() throws Exception {
        if (INSTANCE == null) {
            synchronized (ProductDao.class) {
                INSTANCE = new ProductDao();
            }
        }
        return INSTANCE;
    }

    @Override
    public void loadDataFromFile() throws Exception {
        String code, name;
        int quantity;
        Date manufacturingDate, expirationDate;
        List<String> productData = fileManager.readDataFromFile();

        for (String s : productData) {
            List<String> emp = Arrays.asList(s.split(","));

            code = emp.get(0).trim().toUpperCase();
            name = emp.get(1).trim().toUpperCase();
            quantity = Integer.parseInt(emp.get(2).trim());
            manufacturingDate = convertStringToDate(emp.get(3).trim());
            expirationDate = convertStringToDate(emp.get(4).trim());
            Product newProduct = new Product(code, name, quantity, manufacturingDate, expirationDate);

            addNew(newProduct);
        }
    }

    @Override
    public void addNew(Product obj) {
        productList.add(obj);
    }

    @Override
    public List<Product> getList() {
//        if (productList.isEmpty()) {
//            System.out.println("Product list is empty");
//        }

        return productList;
    }

    @Override
    public Product findProduct(String code) {
        Product product = null;
        List<Product> listProduct = getList();

        for (Product empProduct : listProduct) {
            if (empProduct.getCode().equalsIgnoreCase(code)) {
                product = empProduct;
                break;
            }
        }

        return product;
    }

    @Override
    public List<Product> findProductByName(String name) {
        List<Product> listProductResult = new ArrayList<>();
        List<Product> listProduct = getList();

        for (Product product : listProduct) {
            if (product.getName().equalsIgnoreCase(name) == true) {
                listProductResult.add(product);
            }
        }

        return listProductResult;
    }

    @Override
    public void deleteProductByQuantity(int value) {
        List<Product> listProduct = getList();
        
        for (Product product : listProduct) {
            if (product.getQuantity() == value) {
                listProduct.remove(product);
            }
        }
    }

//------------------------------------------------------------------------------
    public Date convertStringToDate(String string) throws Exception {
        Date date;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        date = dateFormat.parse(string);

        return date;
    }

    public int convertStringToInteger(String string) throws Exception {
        return Integer.parseInt(string);
    }

    @Override
    public void updateProductInformation(List<String> listInformation, Product productUpdated) throws Exception {
        if (listInformation.get(0).equalsIgnoreCase("") == false) {
            productUpdated.setName(listInformation.get(0));
        }

        if (listInformation.get(1).equalsIgnoreCase("") == false) {
            int number = convertStringToInteger(listInformation.get(1));

            productUpdated.setQuantity(number);
        }

        if (listInformation.get(2).equalsIgnoreCase("") == false) {
            Date date = convertStringToDate(listInformation.get(2));

            productUpdated.setManufacturingDate(date);
        }

        if (listInformation.get(3).equalsIgnoreCase("") == false) {
            Date date = convertStringToDate(listInformation.get(3));

            productUpdated.setExpirationDate(date);
        }
    }

    @Override
    public void deleteProduct(Product productDeleted) throws Exception {
        try {
            List<Product> listProduct = getList();
            listProduct.remove(productDeleted);
        } catch (Exception e) {
            System.out.println("Delete fail");
        }
    }

    @Override
    public void saveFile() throws Exception {
        fileManager.writeProductToFile(getList());
    }

}
