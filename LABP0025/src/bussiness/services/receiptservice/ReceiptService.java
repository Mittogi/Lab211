package bussiness.services.receiptservice;

import bussiness.entity.Receipt;
import data.factory.DaoFactory;
import data.factory.IDaoFactory;
import data.receiptdao.IReceiptDao;
import java.util.List;

/**
 *
 * @author PHAT
 */
public class ReceiptService implements IReceiptService<Receipt>{
    IReceiptDao<Receipt> receiptAction;
    IDaoFactory receiptDaoFactory;
    
//contructor--------------------------------------------------------------------
    public ReceiptService() throws Exception {
        receiptDaoFactory = new DaoFactory();
        this.receiptAction = receiptDaoFactory.getReceiptDao();
    }

//------------------------------------------------------------------------------    

    @Override
    public void printList() throws Exception {
        receiptAction.getList().forEach(obj -> System.out.println(obj));
    }

    @Override
    public List<Receipt> getList() throws Exception {
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

}
