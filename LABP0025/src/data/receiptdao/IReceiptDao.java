package data.receiptdao;

import data.IUserDao;

/**
 *
 * @author PHAT
 * @param <Receipt>
 */
public interface IReceiptDao<Receipt> extends IUserDao<Receipt>{
    void loadDataFromFile() throws Exception;
    
    Receipt findReceipt(String code) throws Exception;
}
