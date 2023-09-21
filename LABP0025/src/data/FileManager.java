package data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 * @author PHAT
 * @param <T>
 */
public class FileManager<T> implements IFileManager<T>{
//fileds---------------------------------------------------------------
    public String fileName;
    public static final String productFileName = "D:\\study\\Semester_3\\LAB211\\code\\LABP0025\\src\\product.txt";
    public static final String receiptFileName = "D:\\study\\Semester_3\\LAB211\\code\\LABP0025\\src\\receipt.txt";
//contructor-----------------------------------------------------------

    public FileManager() {
    }

    public FileManager(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<String> readDataFromFile() throws IOException{
        List<String> result;
        result = Files.readAllLines(Paths.get(fileName), Charset.forName("utf-8"));
        return result;
    }

}
