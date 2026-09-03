package wally;

/**
 * Runs a chatbot that stores and displays tasks.
 */
public class Wally {
    private final Tasklist tasks = new Tasklist();
    private final Save save = new Save(tasks);
    private String output;

    /**
     * Starts the command-line version of Wally.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        // Banner
        String banner = "__        ___    _     _  __   __\n"
                + "\\ \\      / / \\  | |   | | \\ \\ / /\n"
                + " \\ \\ /\\ / / _ \\ | |   | |  \\ V / \n"
                + "  \\ V  V / ___ \\| |___| |___| |  \n"
                + "   \\_/\\_/_/   \\_\\_____|_____|_|  \n";

        // On start
        System.out.println("Hello! I'm Wally.\nWhat can I do for you?");
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String userInput) {
        output = Parser.processCommand(userInput, tasks);
        save.writeToSave(tasks);
        return output;
    }
}
