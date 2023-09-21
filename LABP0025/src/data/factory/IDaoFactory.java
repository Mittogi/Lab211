package data.factory;

import bussiness.entity.Product;
import bussiness.entity.Receipt;
import data.productdao.IProductDao;
import data.receiptdao.IReceiptDao;

/**
 *
 * @author PHAT
 */
public interface IDaoFactory {
    IProductDao<Product> getProductDao() throws Exception;
    
    IReceiptDao<Receipt> getReceiptDao() throws Exception;
}
