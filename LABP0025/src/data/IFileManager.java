package data;

import bussiness.entity.Product;
import bussiness.entity.Receipt;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author PHAT
 * @param <T>
 */
public interface IFileManager {
    List<String> readDataFromFile() throws IOException;  

    void writeProductToFile(List<Product> listProduct) throws Exception;
    void writeReceiptToFile(List<Receipt> listReceipt) throws Exception;
}
