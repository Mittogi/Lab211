package application.ui;

import application.utilities.DataInput;
import bussiness.component.DataValidation;
import bussiness.entity.Product;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import bussiness.services.productservice.IProductService;

/**
 *
 * @author PHAT
 */
public class ProductMenu {

    IProductService<Product> service;

    public ProductMenu(IProductService<Product> service) {
        this.service = service;
    }

    public void processMenuForProduct() {

        boolean quit = false;

        try {
            do {
                Menu.print("******Product Management******|1.Add Product|2.Update Product|3.Remove Product"
                        + "|4.Print Product List|5.Back to main menu|Select :");

                int choice = Menu.getUserChoice();

                switch (choice) {
                    case 1:
                        addNewProduct();
                        break;

                    case 2:
                        updateProductInformation();
                        break;

                    case 3:
                        deleteProduct();
                        break;

                    case 4: //Show all product
                        System.out.println("Product list:");
                        printAllProduct();
                        break;

                    case 5:
                        quit = true;
                        break;

                    default:
                        System.out.println("Choice invalid");
                        break;

                }
            } while (!quit);
        } catch (Exception ignored) {}
    }

    public Product getProduct() {
        String code;
        String name;
        int quantity;
        Date manufacturingDate;
        Date expirationDate;

        while (true) {
            try {
                code = DataInput.getString("Enter product's code : ").toUpperCase();
                if (DataValidation.checkValueIsEmpty(code)) {
                    throw new Exception("Invalid due to empty");
                }

                name = DataInput.getString("Enter product's name: ").toUpperCase();
                if (DataValidation.checkValueIsEmpty(name)) {
                    throw new Exception("Invalid due to empty");
                }

                quantity = DataInput.getIntegerNumber("Enter product's quantity: ");
                manufacturingDate = DataInput.getDate("Enter product's manufacturing date (Format: yyyy-mm-dd): ");
                expirationDate = DataInput.getDate("Enter product's expiration date (Format: yyyy-mm-dd): ");

                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        return new Product(code, name, quantity, manufacturingDate, expirationDate);
    }

    public void addNewProduct() {
        do {
            try {
                Product newProduct = getProduct();
                List<Product> listProduct = service.getList();

                if (DataValidation.checkNewProductIsValid(newProduct, listProduct)) {
                    service.add(newProduct);

                    System.out.println("Product added successfully.");
                    Menu.print("Do you want to continues(Y/N)");

                    boolean isContinue = DataInput.getString().matches("[y,Y]");

                    if (!isContinue) {
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    public List<String> getNewProductInformation() throws Exception {
        List<String> listNewInformation = new ArrayList<>();
        String newName, newQuantity, newManufacturingDate, newExpirationDate;

        newName = DataInput.getString("Enter new name: ").toUpperCase();

        do {
            newQuantity = DataInput.getString("Enter new quantity: ");

        } while (!DataValidation.checkNewQuatiy(newQuantity));

        do {
            newManufacturingDate = DataInput.getString("Enter new manufacturing date (yyyy-MM-dd): ");

        } while (!DataValidation.checkNewDate(newManufacturingDate));

        do {
            newExpirationDate = DataInput.getString("Enter new expiration date (yyyy-MM-dd): ");
        } while (!DataValidation.checkNewDate(newExpirationDate));

        listNewInformation.add(newName);
        listNewInformation.add(newQuantity);
        listNewInformation.add(newManufacturingDate);
        listNewInformation.add(newExpirationDate);

        return listNewInformation;
    }

    public void updateProductInformation() {
        try {
            String productCodeUpdated = DataInput.getString("Enter product's code need updated: ");
            Product productUpdated = service.findProduct(productCodeUpdated);

            if (productUpdated == null) {
                throw new Exception("Product does not exist");
            }

            List<String> listNewInformation = getNewProductInformation();

            service.updateProductInformation(listNewInformation, productUpdated);

            System.out.println("Procduct updated successfully.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteProduct() throws Exception {
        try {
            String productCodeDeleted = DataInput.getString("Enter product's code need deleted: ");
            Product productDeleted = service.findProduct(productCodeDeleted);
            int choice;

            if (productDeleted == null) {
                throw new Exception("Product does not exits");
            }
            
            Menu.print("Are you sure to delete product?|1.Yes|2.No|Select: ");
            choice = Menu.getUserChoice();
            
            switch (choice) {
                case 1:
                    service.deleteProduct(productDeleted);
                    System.out.println("Product deleted successfully.");
                    break;
                    
                case 2:
                    break;
                    
                default:
                    throw new Exception("Choice is invalid");
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void printAllProduct() {
        try {
            service.printList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
