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
    private final Path saveFile;

    /**
     * Loads the task list stored in Saves/save.txt.
     * If the file does not exist, creates one.
     *
     * @param tasklist Task list to load into.
     */
    public Save(Tasklist tasklist) {
        this(tasklist, Path.of(SAVE_FILE_PATH));
    }

    /** Loads tasks from a specified file, allowing isolated storage in tests. */
    Save(Tasklist tasklist, Path saveFile) {
        this.saveFile = saveFile;
        if (Files.isRegularFile(saveFile)) {
            try (Stream<String> lines = Files.lines(saveFile)) {
                lines.forEach(command -> Parser.processCommand(command, tasklist));
            } catch (IOException e) {
                System.out.println("Exception caught: " + e);
            }
        } else {
            try {
                Files.createDirectories(saveFile.toAbsolutePath().getParent());
                Files.createFile(saveFile);
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
            Files.writeString(saveFile, contents);
        } catch (IOException e) {
            System.out.println("Exception caught: " + e);
        }
    }
}
