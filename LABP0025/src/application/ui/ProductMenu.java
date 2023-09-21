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
//------------------------------------------------------------------------------

    IProductService<Product> service;

//contructor--------------------------------------------------------------------
    public ProductMenu(IProductService<Product> service) {
        this.service = service;
    }

//product menu------------------------------------------------------------------
    public void processMenuForProduct() {

        boolean quit = false;

        try {
            do {
                Menu.print("******Product Management******|1.Add Product|2.Update Product|3.Remove Product"
                        + "|4.Print Product List|5.Back to main menu|Select :");

                int choice = Menu.getUserChoice();

                switch (choice) {
                    case 1: //add new product
                        addNewProduct();
                        break;

                    case 2: //update product information
                        updateProductInformation();
                        break;

                    case 3: //delete product
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
            } while (quit == false);
        } catch (Exception e) {
        }
    }

//user's choice-----------------------------------------------------------------
    public Product getProduct() throws Exception {
        String code = "";
        String name = "";
        int quantity = 0;
        Date manufacturingDate = null;
        Date expirationDate = null;

        while (true) {
            try {
                code = DataInput.getString("Enter product's code : ").toUpperCase();
                if (DataValidation.checkValueIsEmpty(code) == true) {
                    throw new Exception("Invalid due to empty");
                }

                name = DataInput.getString("Enter product's name: ").toUpperCase();
                if (DataValidation.checkValueIsEmpty(name) == true) {
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

//case 1------------------------------------------------------------------------    
    public void addNewProduct() {
        boolean continuous = true;

        do {
            try {
                Product newProduct = getProduct();
                List<Product> listProduct = service.getList();

                if (DataValidation.checkNewProductIsValid(newProduct, listProduct) == true) {
                    service.add(newProduct);

                    System.out.println("Product added successfully.");
                    Menu.print("Do you want to continuous add product or go back to main menu|1.Continuous|2.Back to main menu|Select: ");

                    int choice = Menu.getUserChoice();

                    switch (choice) {
                        case 1: //continuous add product
                            break;

                        case 2: //Go back to main menu
                            continuous = false;
                            break;

                        default:
                            System.out.println("Choice invalid");
                            break;
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (continuous == true);
    }

//case 2------------------------------------------------------------------------
    public List<String> getNewProductInformation() throws Exception {
        List<String> listNewInformation = new ArrayList<>();
        String newName = "", newQuantity = "", newManufacturingDate = "", newExpirationDate = "";

        newName = DataInput.getString("Enter new name: ").toUpperCase();

        while (true) {
            newQuantity = DataInput.getString("Enter new quantity: ");

            if (DataValidation.checkNewQuatiy(newQuantity) == true) {
                break;
            }
        }

        while (true) {
            newManufacturingDate = DataInput.getString("Enter new manufacturing date (yyyy-MM-dd): ");

            if (DataValidation.checkNewDate(newManufacturingDate) == true) {
                break;
            }
        }

        while (true) {
            newExpirationDate = DataInput.getString("Enter new expiration date (yyyy-MM-dd): ");

            if (DataValidation.checkNewDate(newExpirationDate) == true) {
                break;
            }
        }

        listNewInformation.add(newName);
        listNewInformation.add(newQuantity);
        listNewInformation.add(newManufacturingDate);
        listNewInformation.add(newExpirationDate);

        return listNewInformation;
    }

    public void updateProductInformation() throws Exception {
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

//case 3------------------------------------------------------------------------
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

//case 4------------------------------------------------------------------------
    public void printAllProduct() {
        try {
            service.printList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
