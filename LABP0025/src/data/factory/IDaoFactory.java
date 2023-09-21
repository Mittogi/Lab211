package data.factory;

import data.productdao.IProductDao;
import data.receiptdao.IReceiptDao;

/**
 *
 * @author PHAT
 */
public interface IDaoFactory {
    IProductDao productDao() throws Exception;
    
    IReceiptDao receiptDao() throws Exception;
}
