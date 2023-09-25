package data.receiptdao;

import bussiness.entity.Product;
import bussiness.entity.Receipt;
import data.FileManager;
import data.IFileManager;
import data.productdao.IProductDao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * @author PHAT
 */
public class ReceiptDao implements IReceiptDao<Receipt> {

    IProductDao<Product> productAction;
    IFileManager fileManager;
    List<Receipt> receiptList = new ArrayList<>();

    public ReceiptDao(IProductDao<Product> productDao) throws Exception {
        this.productAction = productDao;
        this.fileManager = new FileManager<Receipt>(FileManager.receiptFileName);
        loadDataFromFile();
    }

    @Override
    public void loadDataFromFile() throws Exception {
        String code, type;
        Date time;
        List<String> receiptData = fileManager.readDataFromFile();

        for (String s : receiptData) {
            int i = 3;

            List<String> emp = Arrays.asList(s.split(","));
            ArrayList<Product> listProduct = new ArrayList<>();

            code = emp.get(0).trim().toUpperCase();
            type = emp.get(1).trim().toUpperCase();
            time = convertStringToDate(emp.get(2).trim());
            while (i < emp.size()) {
                String productCode = emp.get(i).trim();

                Product product = productAction.findProduct(productCode);

                listProduct.add(product);
                
                i++;
            }

            Receipt newReceipt = new Receipt(code, type,  time, listProduct);

            addNew(newReceipt);
        }
    }

    @Override
    public void addNew(Receipt obj) {
        receiptList.add(obj);
    }

    @Override
    public List<Receipt> getList() {
//        if (receiptList.isEmpty()) {
//            System.out.println("Receipt list is empty");
//        }

        return receiptList;
    }

    @Override
    public Receipt findReceipt(String code) throws Exception {
        Receipt receipt = null;
        List<Receipt> listReceipt = getList();

        for (Receipt empReceipt : listReceipt) {
            if (empReceipt.getCode().equalsIgnoreCase(code)) {
                receipt = empReceipt;
                break;
            }
        }

        return receipt;
    }

    @Override
    public Product findProductOfReceipt(String code, List<Product> productList) {
        Product product = null;

        for (Product oldProduct : productList) {
            if (oldProduct.getCode().equalsIgnoreCase(code)) {
                product = oldProduct;
                break;
            }
        }

        return product;
    }

//------------------------------------------------------------------------------
    public Date convertStringToDate(String string) throws Exception {
        Date date;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        date = dateFormat.parse(string);

        return date;
    }

    @Override
    public void saveFile() throws Exception {
        fileManager.writeReceiptToFile(getList());
    }
}
