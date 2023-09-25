package data;

import bussiness.entity.Product;
import bussiness.entity.Receipt;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 * @author PHAT
 * @param <T>
 */
public class FileManager<T> implements IFileManager {

    public String fileName;
    public static final String productFileName = "product.txt";
    public static final String receiptFileName = "warehouse.txt";

    public FileManager(String fileName) {
        this.fileName = fileName;

        File file = new File(fileName);
        if (!file.exists()) {
            try {
                if (!file.createNewFile()) {
                    throw  new RuntimeException("Can not create origin file.");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<String> readDataFromFile() throws IOException{
        List<String> result;
        result = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        return result;
    }

    @Override
    public void writeProductToFile(List<Product> listProduct) throws Exception {
        PrintWriter printWriter = new PrintWriter(productFileName);
        
        for (Product product : listProduct) {
            printWriter.println(product.toRawData());
        }
        
        printWriter.close();
    }

    @Override
    public void writeReceiptToFile(List<Receipt> listReceipt) throws Exception {
        PrintWriter printWriter = new PrintWriter(receiptFileName);
        
        for (Receipt receipt : listReceipt) {
            printWriter.println(receipt.toRawData());
        }
        
        printWriter.close();
    }

}
