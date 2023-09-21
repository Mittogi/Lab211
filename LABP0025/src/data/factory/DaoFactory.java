package data.factory;

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
    IFileManager fileManager;
    
//contructor-------------------------------------------------
    public DaoFactory() {
    }

    public DaoFactory(String inputDataFile) {
        this.fileManager = new FileManager(inputDataFile);
    }
    
//-----------------------------------------------------------

    @Override
    public IProductDao productDao() throws Exception {
        IProductDao productDao = new ProductDao(fileManager);
        return productDao;
    }

    @Override
    public IReceiptDao receiptDao() throws Exception {
        IProductDao productDaoEmp = ProductDao.getInstance();
        return new ReceiptDao(fileManager, productDaoEmp);
    }
    
    
}
