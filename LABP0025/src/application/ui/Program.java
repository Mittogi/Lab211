package application.ui;

import application.utilities.DataInput;
import bussiness.entity.Product;
import bussiness.entity.Receipt;
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

        System.out.println(String.join("", Collections.nCopies(10, "**********")));
    
        try {
            IProductService<Product> productService = new ProductService();
            IReceiptService<Receipt> receiptService = new ReceiptService();

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
            } while (!quit);
        } catch (Exception e) {
                System.out.println(e.getMessage());
        }
    }
        
}


