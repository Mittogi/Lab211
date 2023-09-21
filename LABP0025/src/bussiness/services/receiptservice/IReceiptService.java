package bussiness.services.receiptservice;

import bussiness.entity.Receipt;
import bussiness.services.IService;

/**
 *
 * @author PHAT
 */
public interface IReceiptService<Receipt> extends IService<Receipt>{
    Receipt findReceipt(String code) throws Exception;
}
