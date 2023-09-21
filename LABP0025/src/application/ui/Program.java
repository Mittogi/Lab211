package application.ui;

import application.utilities.DataInput;
import bussiness.services.productservice.ProductService;
import java.util.Collections;
import bussiness.services.productservice.IProductService;
import bussiness.services.receiptservice.IReceiptService;
import bussiness.services.receiptservice.ReceiptService;

/**
 *
 * @author PHAT
 */
public class Program {
    

    public static void main(String[] args) {
        int choice;
        boolean quit = false;
        String productInputFile = "D:\\study\\Semester_3\\LAB211\\code\\LABP0025\\src\\product.txt";
        String receiptInputFile = "D:\\study\\Semester_3\\LAB211\\code\\LABP0025\\src\\warehouse.txt";
        
        System.out.println(String.join("", Collections.nCopies(10, "**********")));
    
        try {
            IProductService productService = new ProductService(productInputFile);
            IReceiptService receiptService = new ReceiptService(receiptInputFile);
            do {
                Menu.print("1.Producct Management|2.Warehouse Management|3.Exit|Select:");
                choice = DataInput.getIntegerNumber();
                switch (choice) {
                    case 1: {
                        Menu.manageProduct(productService);
                        break;
                    }    
                    case 2: {
                        Menu.manageReceipt(receiptService);
                        break;
                    }
                    default: {
                        System.out.println("Good bye");
                        quit = true;
                        break;
                    }
                }
            } while (quit == false);
        } catch (Exception e) {
                System.out.println(e.getMessage());
        }
    }
        
}


