package application.ui;

import application.utilities.DataInput;
import bussiness.entity.Product;
import bussiness.entity.Receipt;
import bussiness.services.productservice.IProductService;
import bussiness.services.receiptservice.IReceiptService;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author PHAT
 */
public class ReportMenu {

    IProductService productService;
    IReceiptService receiptService;

    public ReportMenu(IProductService productService, IReceiptService receiptService) {
        this.productService = productService;
        this.receiptService = receiptService;
    }

    public void processMenuForReport() {
        boolean quit = false;

        do {
            try {
                Menu.print("******Report Management******|1.Products that have expired.|2.The products that the store is selling.|3.Products that are running out of stock"
                        + "|4.Import/export receipt of a product.|5.Store data to files.|6.Back to main menu|Select: ");

                int choice = Menu.getUserChoice();

                switch (choice) {
                    case 1 -> {
                        productsThatHaveExpired();
                    }

                    case 2 -> {
                        productThatTheStoreIsSelling();
                    }

                    case 3 -> {
                        productsThatAreRunningOutOfStock();
                    }

                    case 4 -> {
                        importExportReceiptOfAProduct();
                    }

                    case 5 -> {
                        saveDataToFile();
                    }

                    case 6 -> {
                        quit = true;
                    }

                    default ->
                        System.out.println("Choice invalid");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (quit == false);
    }

    public void productsThatHaveExpired() {
        List<Product> listProductHaveExpired = productService.productHaveExpried();

        if (listProductHaveExpired.isEmpty() == true) {
            System.out.println("No product that have expried");
        }

        for (Product product : listProductHaveExpired) {
            System.out.println(product);
        }
    }

    public void productThatTheStoreIsSelling() {
        List<Product> listProductIsSelling = productService.productIsSelling();

        if (listProductIsSelling.isEmpty() == true) {
            System.out.println("No product that the store is selling");
        }

        for (Product product : listProductIsSelling) {
            System.out.println(product);
        }
    }

    public void productsThatAreRunningOutOfStock() {
        List<Product> listProduct = productService.productAreRunningOutOfStock();

        for (Product product : listProduct) {
            System.out.println(product);
        }
    }

    public void saveDataToFile() throws Exception {
        receiptService.saveFile();
        productService.saveFile();
        System.out.println("Save successful");
    }

    public void importExportReceiptOfAProduct() {
        String productCode = DataInput.getString("Enter product code: ");
        List<Receipt> listReceiptResult = receiptService.importExportReceiptOfAProduct(productCode);

        if (listReceiptResult.isEmpty() == true) {
            System.out.println("No receipt contain that product");
        } else {
            for (Receipt receipt : listReceiptResult) {
                System.out.println("Receipt code = " + receipt.getCode() + ", type = " + receipt.getType() + ", time = " + formatDateToString(receipt.getReceiptTime()));
            }
        }
    }

    private String formatDateToString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        return dateFormat.format(date);
    }

}
