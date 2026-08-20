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
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasking_size; i++) {
                    System.out.println(String.valueOf(i + 1) + ". " + taskings[i]);
                }
            } else if (userInput.matches("mark \\d")) {
                String temp[] = userInput.split(" ");
                if (Integer.parseInt(temp[1]) > tasking_size || Integer.parseInt(temp[1]) < 1) {
                    System.out.println("Invalid tasking number.");
                } else {
                    taskings[Integer.parseInt(temp[1]) - 1].set_status(true);
                    System.out.println("The following task has been marked as done:");
                    System.out.println(taskings[Integer.parseInt(temp[1]) - 1]);
                }
            } else if (userInput.matches("unmark \\d")) {
                String temp[] = userInput.split(" ");
                if (Integer.parseInt(temp[1]) > tasking_size || Integer.parseInt(temp[1]) < 1) {
                    System.out.println("Invalid tasking number.");
                } else {
                    taskings[Integer.parseInt(temp[1]) - 1].set_status(false);
                    System.out.println("The following task has been marked as not done yet:");
                    System.out.println(taskings[Integer.parseInt(temp[1]) - 1]);
                }
            } else if (userInput.matches("todo .*")) {
                String temp = userInput.split("todo ")[1];
                taskings[tasking_size] = new ToDo(temp);
                System.out.println("The following task has been added:");
                System.out.println(taskings[tasking_size]);
                tasking_size += 1;
                System.out.println("Now you have " + tasking_size + " tasks in the list.");
            } else if (userInput.matches("deadline .* /by .*")) {
                String temp[] = userInput.split("deadline ")[1].split(" /by ");
                taskings[tasking_size] = new Deadline(temp[0], temp[1]);
                System.out.println("The following task has been added:");
                System.out.println(taskings[tasking_size]);
                tasking_size += 1;
                System.out.println("Now you have " + tasking_size + " tasks in the list.");
            } else if (userInput.matches("event .* /from .* /to .*")) {
                String temp[] = userInput.split("event ")[1].split(" /from | /to ");
                taskings[tasking_size] = new Event(temp[0], temp[1], temp[2]);
                System.out.println("The following task has been added:");
                System.out.println(taskings[tasking_size]);
                tasking_size += 1;
                System.out.println("Now you have " + tasking_size + " tasks in the list.");
            } else {
                taskings[tasking_size] = new Task(userInput);
                tasking_size += 1;
                System.out.println("added: " + userInput);
            }
            System.out.println("-".repeat(50));
        }

        // Cleanup
        myScanner.close();
    }
}
