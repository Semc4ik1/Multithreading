import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileWriter {

    public static void writeFile(String filePath, String content) throws IOException {
        Files.writeString(Paths.get(filePath), content);
    }
}

