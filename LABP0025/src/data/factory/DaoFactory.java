package data.factory;

import bussiness.entity.Product;
import bussiness.entity.Receipt;
import data.FileManager;
import data.IFileManager;
import data.factory.IDaoFactory;
import data.productdao.IProductDao;
import data.receiptdao.IReceiptDao;
import data.productdao.ProductDao;
import data.receiptdao.ReceiptDao;

/**
 *
 * @author PHAT
 */
public class DaoFactory implements IDaoFactory{
    public DaoFactory() {
    }


    @Override
    public IProductDao<Product> getProductDao() throws Exception {
        return ProductDao.getInstance();
    }

    @Override
    public IReceiptDao<Receipt> getReceiptDao() throws Exception {
        return new ReceiptDao(getProductDao());
    }
    
    
}
