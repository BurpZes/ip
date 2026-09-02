package wally;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;

import java.util.stream.Stream;

/**
 * Saves changes to task list and loads task list when chatbot starts
 */
public class Save {
    public static String SAVE_FILE_PATH = "/wally/Saves/save.txt";
    private static Path PATH = Path.of(SAVE_FILE_PATH);

    /**
     * Loads the task list stored in Saves/save.txt.
     * If the file does not exist, create one.
     * 
     * @param tasklist Tasklist object representing the list of Tasks
     */
    public Save(Tasklist tasklist) {
        if (Files.isRegularFile(PATH)) {
            try (Stream<String> lines = Files.lines(PATH)) {
                lines.reduce("", (x, y) -> (Parser.processCommand(y, tasklist) ? x : x));
            } catch (IOException e) {
                System.out.println("Exception caught: " + e);
            }
        } else {
            try {
                Files.createDirectories(PATH.getParent());
                Files.createFile(PATH);
            } catch (IOException e) {
                System.out.println("Exception caught: " + e);
            }
        }
    }

    /**
     * Overwrites the savefile with the contents of tasklist
     * 
     * @param tasklist Tasklist object representing the collection of taskings
     */
    public void writeToSave(Tasklist tasklist) {
        String contents = "";
        for (int i = 0; i < tasklist.getSize(); i++) {
            contents += tasklist.getTask(i + 1).getCommand();
            contents += "\n";
        }
        try {
            Files.writeString(PATH, contents);
        } catch (IOException e) {
            System.out.println("Exception caught: " + e);
        }
    }
}
