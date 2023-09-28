package data.filemanager;

import java.util.List;

public interface IFileManager {

    List<String> readDataFromFile() throws Exception;

    void commitFile(List<String> raws);
}
