package data;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author PHAT
 * @param <T>
 */
public interface IFileManager<T> {
    List<String> readDataFromFile() throws IOException;  
}
