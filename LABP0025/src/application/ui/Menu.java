package application.ui;

import application.utilities.DataInput;
import bussiness.entity.Product;
import java.util.Arrays;
import bussiness.services.IService;
import bussiness.services.productservice.IProductService;
import bussiness.services.receiptservice.IReceiptService;

/**
 *
 * @author PHAT
 */
public class Menu {
    public static void print(String str) {
        var menuList = Arrays.asList(str.split("\\|"));
        menuList.forEach(menuItem -> {
            if (menuItem.equalsIgnoreCase("Select")) {
                System.out.print(menuItem);
            } else {
                System.out.println(menuItem);
            }

        });
    }
    
    public static int getUserChoice() {
        int number = DataInput.getIntegerNumber();
        
        return number;
    }
    
    public static void manageProduct(IProductService service) {
        ProductMenu empMenu = new ProductMenu(service);
        empMenu.processMenuForProduct();
    }
    
    public static void manageReceipt(IReceiptService service, IProductService<Product> productService) {
        ReceiptMenu empMenu = new ReceiptMenu(service, productService);
        empMenu.processMenuForReceipt();
    }

    public static void manageReport(IReceiptService receiptService, IProductService<Product> productService) {
        ReportMenu empMenu = new ReportMenu(productService, receiptService);
        empMenu.processMenuForReport();
    }
    
    public static void saveDataToFile(IReceiptService receiptService, IProductService<Product> productService) {
        try {
            receiptService.saveFile();
            productService.saveFile();
            System.out.println("Save successful");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
