package data.filemanager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

public class FileManager implements IFileManager {

    private File inputFile;

    public FileManager(String fileName) throws IOException {
        inputFile = new File(fileName);

        if (!inputFile.exists()) {
            if (!inputFile.createNewFile()) {
                throw new RuntimeException("Can not create " + fileName + " file");
            }
        }
    }

    @Override
    public List<String> readDataFromFile() throws Exception {
        return Files.readAllLines(inputFile.toPath(), StandardCharsets.UTF_8);
    }

    @Override
    public void commitFile(List<String> raws) {
        try (FileOutputStream fos = new FileOutputStream(inputFile)) {
            for (String raw : raws) {
                byte b[] = raw.getBytes();
                fos.write(b);
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }

    }
}
