package data;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author PHAT
 * @param <T>
 */
public interface IFileManager {
    List<String> readDataFromFile() throws IOException;  
}
