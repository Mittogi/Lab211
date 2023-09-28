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
                        + "|4.Print Product List|5.Remove product by quantity|6.Back to main menu|Select :");

                int choice = Menu.getUserChoice();

                switch (choice) {
                    case 1 ->
                        addNewProduct();

                    case 2 ->
                        updateProductInformation();

                    case 3 ->
                        deleteProduct();

                    case 4 -> {
                        System.out.println("Product list:");
                        printAllProduct();
                    }
                    
                    case 5 -> {
                        deleteProductByQuantity();
                    }

                    case 6 ->
                        quit = true;

                    default ->
                        System.out.println("Choice invalid");

                }
            } while (!quit);
        } catch (Exception ignored) {
        }
    }

    public Product getProductFromUser() throws Exception {
        String code;
        String name;
        int quantity;
        Date manufacturingDate;
        Date expirationDate;

        do {
            boolean valid = true;

            code = DataInput.getString("Enter product's code: ").toUpperCase();

            if (DataValidation.checkStringWithFormat(code, "^P\\d{7}") == false) {
                System.out.println("Code is invalid. The correct code is Pxxxxxxx with x is digit");
                valid = false;
            }

            if (DataValidation.checkProductCodeIsDulicated(code, service.getList()) == true) {
                System.out.println("Code id duplicated.");
                valid = false;
            }

            if (valid == true) {
                break;
            }
        } while (true);

        do {
            boolean valid = true;

            name = DataInput.getString("Enter product's name: ").toUpperCase().trim();

            if (DataValidation.checkValueIsEmpty(name) == true) {
                System.out.println("Name is empty");
                valid = false;
            }

            if (valid == true) {
                break;
            }
        } while (true);

        do {
            boolean result = true;

            quantity = DataInput.getIntegerNumber("Enter product's quantity: ");

            if (quantity < 0) {
                System.out.println("Data invalid");
                result = false;
            }

            if (result == true) {
                break;
            }
        } while (true);

        manufacturingDate = DataInput.getDate("Enter product's manufacturing date(dd-MM-yyyy): ");

        expirationDate = DataInput.getDate("Enter product's expiration datedd-MM-yyyy): ");

        return new Product(code, name, quantity, manufacturingDate, expirationDate);
    }

    public void addNewProduct() {
        do {
            try {
                Product newProduct = getProductFromUser();

                service.add(newProduct);

                System.out.println("Product added successfully.");
                Menu.print("Do you want to continues(Y/N)");

                boolean isContinue = DataInput.getString().matches("[y,Y]");

                if (!isContinue) {
                    break;
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    public List<String> getNewProductInformation() throws Exception {
        List<String> listNewInformation = new ArrayList<>();
        String newName, newQuantity, newManufacturingDate, newExpirationDate;

        newName = DataInput.getString("Enter new name: ").toUpperCase().trim();

        do {
            newQuantity = DataInput.getString("Enter new quantity: ");
        } while (!DataValidation.checkNewQuatiy(newQuantity));

        do {
            newManufacturingDate = DataInput.getString("Enter new manufacturing date (dd-MM-yyyy): ");
            
            if (!DataValidation.checkNewDateForUpdate(newManufacturingDate)) {
                System.out.println("Date is invalid. The correct format is dd-MM-yyyy:");
            }
        } while (!DataValidation.checkNewDateForUpdate(newManufacturingDate));

        do {
            newExpirationDate = DataInput.getString("Enter new expiration date (dd-MM-yyyy): ");
            
            if (!DataValidation.checkNewDateForUpdate(newExpirationDate)) {
                System.out.println("Date is invalid. The correct format is dd-MM-yyyy:");
            }
        } while (!DataValidation.checkNewDateForUpdate(newExpirationDate));

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
    
    public void deleteProductByQuantity() {
        int value = DataInput.getIntegerNumber("Enter x: ");
        
        service.deleteProductByQuantity(value);
        System.out.println("Products deleted successfully.");
    }

    public void printAllProduct() throws Exception {

        if (service.getList().isEmpty() == true) {
            System.out.println("Product list is empty");
        } else {
            try {
                service.printList();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
