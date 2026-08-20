import java.util.Scanner;

public class Wally {
    public static void main(String[] args) {
        String taskings[] = new String[100];
        int tasking_size = 0;
        String banner = "__        ___    _     _  __   __\n"
            + "\\ \\      / / \\  | |   | | \\ \\ / /\n"
            + " \\ \\ /\\ / / _ \\ | |   | |  \\ V / \n"
            + "  \\ V  V / ___ \\| |___| |___| |  \n"
            + "   \\_/\\_/_/   \\_\\_____|_____|_|  \n";
        System.out.println("-".repeat(50));
        System.out.println(banner);
        System.out.println("-".repeat(50));
        System.out.println("Hello! I'm Wally.\nWhat can I do for you?");
        System.out.println("-".repeat(50));
        Scanner myScanner = new Scanner(System.in);
        String userInput = "";
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
                taskings[tasking_size] = userInput;
                tasking_size += 1;
                System.out.println("added: " + userInput);
                System.out.println("-".repeat(50));
            }
        }
        myScanner.close();
    }
}
