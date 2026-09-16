package wally;

import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Runs a chatbot that stores and displays tasks.
 */
public class Wally {
    private final Tasklist tasks = new Tasklist();
    private final Save save;

    /** Creates a chatbot using the default save file. */
    public Wally() {
        save = new Save(tasks);
    }

    /** Creates a chatbot using an isolated save file. */
    Wally(Path saveFile) {
        save = new Save(tasks, saveFile);
    }

    /**
     * Returns a greeting with today's local date followed by the saved task schedule.
     *
     * @return Startup message to display when the GUI opens.
     */
    public String getStartupMessage() {
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ENGLISH));
        return "Welcome back, today is " + date + "\n\n" + Parser.processCommand("schedule", tasks);
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String userInput) {
        String output = Parser.processCommand(userInput, tasks);
        save.writeToSave(tasks);
        return output;
    }
}
