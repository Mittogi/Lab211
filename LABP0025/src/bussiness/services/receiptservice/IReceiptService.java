package bussiness.services.receiptservice;

import bussiness.entity.Product;
import bussiness.entity.Receipt;
import bussiness.services.IService;
import java.util.List;

/**
 *
 * @author PHAT
 */
public interface IReceiptService<Receipt> extends IService<Receipt>{
    Receipt findReceipt(String code) throws Exception;
    
    Product findProductOfReceipt(String code, List<Product> productList);
    
    void saveFile() throws Exception; 
    
    List<Receipt> importExportReceiptOfAProduct(String productCode);
}
