package bussiness.services.receiptservice;

import bussiness.entity.Product;
import bussiness.entity.Receipt;
import data.factory.DaoFactory;
import data.factory.IDaoFactory;
import data.receiptdao.IReceiptDao;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PHAT
 */
public class ReceiptService implements IReceiptService<Receipt>{
    IReceiptDao<Receipt> receiptAction;
    IDaoFactory receiptDaoFactory;
    
    public ReceiptService() throws Exception {
        receiptDaoFactory = new DaoFactory();
        this.receiptAction = receiptDaoFactory.getReceiptDao();
    }
    
    @Override
    public void printList() throws Exception {
        if (receiptAction.getList().isEmpty() == true) {
            System.out.println("Receipt list is empty");
        }
        receiptAction.getList().forEach(obj -> System.out.println(obj));
    }

    @Override
    public List<Receipt> getList(){
        return receiptAction.getList();
    }

    @Override
    public void add(Receipt obj) throws Exception {
        receiptAction.addNew(obj);
    }

    @Override
    public Receipt findReceipt(String code) throws Exception {
        Receipt receipt = (Receipt) receiptAction.findReceipt(code);
        return receipt;
    }

    @Override
    public Product findProductOfReceipt(String code, List<Product> productList) {
        return receiptAction.findProductOfReceipt(code, productList);
    }

    public List<Receipt> importExportReceiptOfAProduct(String productCode) {
        List<Receipt> listReceipt = getList();
        List<Receipt> listReceiptResult = new ArrayList<>();
        
        for (Receipt receipt : listReceipt) {
            for (Product item : receipt.getItems()) {
                if (productCode.equalsIgnoreCase(item.getCode()) == true) {
                    listReceiptResult.add(receipt);
                    break;
                }
            }
        }
        
        return listReceiptResult;
    }
    
    @Override
    public void saveFile() throws Exception {
        receiptAction.saveFile();
    }
    
    



}
