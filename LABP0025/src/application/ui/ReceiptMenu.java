package application.ui;

import application.utilities.DataInput;
import bussiness.component.DataValidation;
import bussiness.entity.Product;
import bussiness.entity.Receipt;
import bussiness.services.productservice.IProductService;
import bussiness.services.receiptservice.IReceiptService;
import data.factory.DaoFactory;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 *
 * @author PHAT
 */
public class ReceiptMenu {

    IReceiptService<Receipt> service;
    IProductService<Product> productService;

    public ReceiptMenu(IReceiptService<Receipt> service, IProductService<Product> productService) {
        this.service = service;
        this.productService = productService;
    }

    public void processMenuForReceipt() {
        boolean quit = false;

        try {
            do {
                Menu.print("******Product Management******|1.Create an import receipt.|2.Create an export receipt|3.Print all receipt|4. Back to main menu|Select: ");

                int choice = Menu.getUserChoice();

                switch (choice) {
                    case 1 -> {
                        createAnImportReceipt();
                    }

                    case 2 -> {
                        createAnExportReceipt();
                    }

                    case 3 -> {
                        System.out.println("Receipt list:");
                        printAllReceipt();
                    }

                    case 4 ->
                        quit = true;

                    default ->
                        System.out.println("Choice invalid");
                }

            } while (quit == false);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void createAnImportReceipt() throws Exception {
        do {
            try {
                Receipt newReceipt = getReceiptFromUser("IMPORT");

                service.add(newReceipt);

                System.out.println("Receipt added successfully.");
                Menu.print("Do you want to continues(Y/N)");

                boolean isContinue = DataInput.getString().matches("[y,Y]");

                if (!isContinue) {
                    break;
                }
            } catch (Exception e) {
            }
        } while (true);
    }

    public void createAnExportReceipt() throws Exception {
        do {
            try {
                Receipt newReceipt = getReceiptFromUser("EXPORT");

                service.add(newReceipt);

                System.out.println("Receipt added successfully.");
                Menu.print("Do you want to continues(Y/N)");

                boolean isContinue = DataInput.getString().matches("[y,Y]");

                if (!isContinue) {
                    break;
                }
            } catch (Exception e) {
            }
        } while (true);
    }

    public Receipt getReceiptFromUser(String typeReceipt) {
        int numberOfReceipt = service.getList().size() + 1;
        String receiptCode = "";
        Date time;
        List<Product> listProduct = new ArrayList<>();

        while((receiptCode + numberOfReceipt).length() < 7) {
            receiptCode = receiptCode + "0";
        }
        
        receiptCode = receiptCode + numberOfReceipt;

        time = DataInput.getCurrentDate();

        do {
            String productCode;
            Product product;

            do {
                boolean valid = true;

                productCode = DataInput.getString("Enter product's code: ");

                if (DataValidation.checkProductOfReceiptIsDulicated(productCode, listProduct) == true) {
                    System.out.println("Product is dulicated in receipt");
                    valid = false;
                }

                if (DataValidation.checkProductIsExist(productCode, productService.getList()) == false) {
                    System.out.println("Product is not exist in product's data");
                    valid = false;
                }

                if (valid == true) {
                    break;
                }
            } while (true);

            product = productService.findProduct(productCode);
            listProduct.add(product);

            Menu.print("Do you want to continues enter product?(Y/N)");

            boolean isContinue = DataInput.getString().matches("[y,Y]");

            if (isContinue == false) {
                break;
            }
        } while (true);

        return new Receipt(receiptCode, typeReceipt, time, (ArrayList<Product>) listProduct);
    }

    public void printAllReceipt() {
        try {
            List<Receipt> listReceipt = service.getList();

            listReceipt.sort(
                    new Comparator<Receipt>() {
                @Override
                public int compare(Receipt o1, Receipt o2) {
                    return o1.getCode().compareTo(o2.getCode());
                }
            }
            );

            for (Receipt receipt : listReceipt) {
                System.out.println("Receipt code = " + receipt.getCode() + ", type = " + receipt.getType() + ", time = " + formatDateToString(receipt.getReceiptTime()));

                for (Product item : receipt.getItems()) {
                    System.out.println(item);
                }

                System.out.println("");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private String formatDateToString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        return dateFormat.format(date);
    }
}
