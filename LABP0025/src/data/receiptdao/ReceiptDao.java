package data.receiptdao;

import bussiness.entity.Product;
import bussiness.entity.Receipt;
import data.IFileManager;
import data.productdao.IProductDao;
import data.productdao.ProductDao;
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
//Declare an arraylist to store Receipt data------------------------------------
    IProductDao<Product> productAction;
    IFileManager<Receipt> fileManager;
    List<Receipt> receiptList = new ArrayList<>();

//Contructor--------------------------------------------------------------------
    public ReceiptDao() {
    }

    public ReceiptDao(IFileManager fileManager, IProductDao producDao) throws Exception{
        this.productAction = producDao;
        this.fileManager = fileManager;
        loadDataFromFile();
    }

//method implement--------------------------------------------------------------
    @Override
    public void loadDataFromFile() throws Exception {
        String code;
        Date time;
        List<String> receiptData = fileManager.readDataFromFile();

        for (String s : receiptData) {
            int i = 2;
            
            List<String> emp = Arrays.asList(s.split(","));
            ArrayList<Product> listProduct = new ArrayList<>();

            code = emp.get(0).trim();
            time = convertStringToDate(emp.get(1).trim());
            while (i < emp.size()) {
                String productCode = emp.get(i).trim();
         
                Product product = productAction.findProduct(productCode);
                
                listProduct.add(product);
                
            }
            
            Receipt newReceipt = new Receipt(code, time, listProduct);
            
            addNew(newReceipt);
        }
    }

    @Override
    public void addNew(Receipt obj) throws Exception {
        receiptList.add(obj);
    }

    @Override
    public List<Receipt> getList() throws Exception {
        if (receiptList.isEmpty()) {
            throw new Exception("Product is empty");
        }
        
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

//------------------------------------------------------------------------------
    public Date convertStringToDate(String string) throws Exception {
        Date date;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        date = dateFormat.parse(string);

        return date;
    }
}
