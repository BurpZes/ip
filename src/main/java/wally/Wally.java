package wally;

/**
 * Runs a chatbot that stores and displays tasks.
 */
public class Wally {
    private final Tasklist tasks = new Tasklist();
    private final Save save = new Save(tasks);
    private String output;

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String userInput) {
        output = Parser.processCommand(userInput, tasks);
        save.writeToSave(tasks);
        return output;
    }
}
