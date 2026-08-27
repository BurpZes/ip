import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Saves changes to task list and loads task list when chatbot starts
 */
public class Save {
    public static String SAVE_FILE_PATH = "./Saves/save.txt";

    /**
     * Loads the task list stored in Saves/save.txt.
     * If the file does not exist, create one.
     */
    public Save(Tasklist tasklist) {
        Path path = Paths.get(SAVE_FILE_PATH);

        if (Files.isRegularFile(path)) {
            try (Stream<String> lines = Files.lines(path)) {
                
            } catch (IOException e) {
                System.out.println("Exception caught: " + e);
            }
        } else {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                System.out.println("Exception caught: " + e);
            }
        }
    }
}
