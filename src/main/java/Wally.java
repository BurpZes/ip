import java.util.Scanner;

public class Wally {
    public static void main(String[] args) {
        Task taskings[] = new Task[100];
        int tasking_size = 0;
        Scanner myScanner = new Scanner(System.in);
        String userInput = "";

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
        while (true) {
            userInput = myScanner.nextLine().strip();
            System.out.println();
            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("-".repeat(50));
                break;
            } else if (userInput.equals("list")) {
                for (int i = 0; i < tasking_size; i++) {
                    System.out.println(String.valueOf(i + 1) + ". " + taskings[i]);
                }
                System.out.println("-".repeat(50));
            } else {
                taskings[tasking_size] = new Task(userInput);
                tasking_size += 1;
                System.out.println("added: " + userInput);
                System.out.println("-".repeat(50));
            }
        }

        // Cleanup
        myScanner.close();
    }
}
