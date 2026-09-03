package wally;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;

import java.util.stream.Stream;

/**
 * Saves changes to the task list and loads it when the chatbot starts.
 */
public class Save {
    private static final String SAVE_FILE_PATH = "/wally/Saves/save.txt";
    private static final Path SAVE_FILE = Path.of(SAVE_FILE_PATH);

    /**
     * Loads the task list stored in Saves/save.txt.
     * If the file does not exist, creates one.
     *
     * @param tasklist Task list to load into.
     */
    public Save(Tasklist tasklist) {
        if (Files.isRegularFile(SAVE_FILE)) {
            try (Stream<String> lines = Files.lines(SAVE_FILE)) {
                lines.reduce("", (x, y) -> Parser.processCommand(y, tasklist));
            } catch (IOException e) {
                System.out.println("Exception caught: " + e);
            }
        } else {
            try {
                Files.createDirectories(SAVE_FILE.getParent());
                Files.createFile(SAVE_FILE);
            } catch (IOException e) {
                System.out.println("Exception caught: " + e);
            }
        }
    }

    /**
     * Overwrites the save file with the contents of the task list.
     *
     * @param tasklist Task list to save.
     */
    public void writeToSave(Tasklist tasklist) {
        String contents = "";
        for (int i = 0; i < tasklist.getSize(); i++) {
            contents += tasklist.getTask(i + 1).getCommand();
            contents += "\n";
        }
        try {
            Files.writeString(SAVE_FILE, contents);
        } catch (IOException e) {
            System.out.println("Exception caught: " + e);
        }
    }
}
