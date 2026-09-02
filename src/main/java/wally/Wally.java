package wally;

/**
 * Runs a chatbot that stores and displays taskings
 */
public class Wally {
    private Tasklist taskings = new Tasklist();
    private Save save = new Save(taskings);
    private String output;

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
        output = Parser.processCommand(userInput, taskings);
        save.writeToSave(taskings);
        return output;
    }
}
