package wally;

import java.util.Scanner;

public class Wally {
    public static void main(String[] args) {
        Tasklist taskings = new Tasklist();
        Scanner myScanner = new Scanner(System.in);
        String userInput = "";
        boolean shouldTerminate = false;
        Save save = new Save(taskings);

        // Banner
        String banner = "__        ___    _     _  __   __\n"
                + "\\ \\      / / \\  | |   | | \\ \\ / /\n"
                + " \\ \\ /\\ / / _ \\ | |   | |  \\ V / \n"
                + "  \\ V  V / ___ \\| |___| |___| |  \n"
                + "   \\_/\\_/_/   \\_\\_____|_____|_|  \n";

        // On start
        System.out.println("-".repeat(50));
        System.out.println(banner);
        System.out.println("-".repeat(50));
        System.out.println("Hello! I'm Wally.\nWhat can I do for you?");
        System.out.println("-".repeat(50));

        // Looping for user inputs
        while (!shouldTerminate) {
            userInput = myScanner.nextLine().strip();
            System.out.println();
            shouldTerminate = Parser.processCommand(userInput, taskings);
            save.writeToSave(taskings);
        }

        // Cleanup
        myScanner.close();
    }
}
