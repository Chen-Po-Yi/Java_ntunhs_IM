import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public class hw4 {
    public static void main(String[] args) {
        try {
            Path filePath = Path.of("C://Users/User/desktop/demo.txt");
            String content = Files.readString(filePath);
            System.out.println(content); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}