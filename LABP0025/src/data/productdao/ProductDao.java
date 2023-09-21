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
//Declare an arraylist to store Product data------------------------------------

    IFileManager<Product> fileManager;
    List<Product> productList = new ArrayList<>();

//contructor--------------------------------------------------------------------
    public ProductDao() {
    }

    public ProductDao(IFileManager fileManager) throws Exception {
        this.fileManager = fileManager;
        loadDataFromFile();
    }

//singleton design pattern------------------------------------------------------
    private static IProductDao INSTANCE = null;

    public static IProductDao getInstance() throws Exception {
        if (INSTANCE == null) {
            synchronized (ProductDao.class) {
                IFileManager fileManager = new FileManager(FileManager.productFileName);
            
                INSTANCE = new ProductDao(fileManager);
            }
        }
        return INSTANCE;
    }    
    
//method implemented------------------------------------------------------------
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
    public void addNew(Product obj) throws Exception {
        productList.add(obj);
    }

    @Override
    public List<Product> getList() throws Exception {
        if (productList.isEmpty()) {
            throw new Exception("Product list is empty");
        }

        return productList;
    }

    @Override
    public Product findProduct(String code) throws Exception {
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

//------------------------------------------------------------------------------
    public Date convertStringToDate(String string) throws Exception {
        Date date;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        date = dateFormat.parse(string);

        return date;
    }

    public int convertStringToInteger(String string) throws Exception {
        return Integer.parseInt(string);
    }

//Update product----------------------------------------------------------------
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

//delete product----------------------------------------------------------------
    @Override
    public void deleteProduct(Product productDeleted) throws Exception {
        try {      
            List<Product> listProduct = getList();
            listProduct.remove(productDeleted);
        } catch (Exception e) {
            System.out.println("Delete fail");
        }
    }
}
