package data.receiptdao;

import bussiness.entity.Product;
import data.IUserDao;
import java.util.List;

/**
 *
 * @author PHAT
 * @param <Receipt>
 */
public interface IReceiptDao<Receipt> extends IUserDao<Receipt>{
    void loadDataFromFile() throws Exception;
    
    Receipt findReceipt(String code) throws Exception;
    
    Product findProductOfReceipt(String code, List<Product> productList);
    
    void saveFile() throws Exception;
}