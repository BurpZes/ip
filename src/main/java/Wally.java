import java.util.Scanner;
import java.util.ArrayList;

public class Wally {
    public static void main(String[] args) {
        ArrayList<Task> taskings = new ArrayList<>();
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

            try {
                // Close chatbot
                if (userInput.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println("-".repeat(50));
                    break;
                } 

                // list tasks
                else if (userInput.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < taskings.size(); i++) {
                        System.out.println(String.valueOf(i + 1) + ". " + taskings.get(i));
                    }
                } 
                
                // mark command
                else if (userInput.matches("mark \\d")) {
                    String temp[] = userInput.split(" ");
                    if (taskings.size() == 0) {
                        throw(new EmptyTaskingsException());
                    } else if (Integer.parseInt(temp[1]) > taskings.size() || Integer.parseInt(temp[1]) < 1) {
                        throw(new IndexOutOfBoundsException());
                    } else {
                        taskings.get(Integer.parseInt(temp[1]) - 1).set_status(true);
                        System.out.println("The following task has been marked as done:");
                        System.out.println(taskings.get(Integer.parseInt(temp[1]) - 1));
                    }
                } 
                
                // unmark command
                else if (userInput.matches("unmark \\d")) {
                    String temp[] = userInput.split(" ");
                    if (taskings.size() == 0) {
                        throw(new EmptyTaskingsException());
                    } else if (Integer.parseInt(temp[1]) > taskings.size() || Integer.parseInt(temp[1]) < 1) {
                        throw(new IndexOutOfBoundsException());
                    } else {
                        taskings.get(Integer.parseInt(temp[1]) - 1).set_status(false);
                        System.out.println("The following task has been marked as not done yet:");
                        System.out.println(taskings.get(Integer.parseInt(temp[1]) - 1));
                    }
                } 
                
                // todo command
                else if (userInput.matches("todo .*")) {
                    String temp = userInput.split("todo ")[1];
                    taskings.add(new ToDo(temp));
                    System.out.println("The following task has been added:");
                    System.out.println(taskings.get(taskings.size() - 1));
                    System.out.println("Now you have " + taskings.size() + " tasks in the list.");
                } 
                
                // deadline command
                else if (userInput.matches("deadline .*")) {
                    if (userInput.matches("deadline .* /by .*")) {
                        String temp[] = userInput.split("deadline ")[1].split(" /by ");
                        taskings.add(new Deadline(temp[0], temp[1]));
                        System.out.println("The following task has been added:");
                        System.out.println(taskings.get(taskings.size() - 1));
                        System.out.println("Now you have " + taskings.size() + " tasks in the list.");
                    } else {
                        throw(new InvalidDeadlineException());
                    }
                } 
                
                // event command
                else if (userInput.matches("event .*")) {
                    if (userInput.matches("event .* /from .* /to .*")) {
                        String temp[] = userInput.split("event ")[1].split(" /from | /to ");
                        taskings.add(new Event(temp[0], temp[1], temp[2]));
                        System.out.println("The following task has been added:");
                        System.out.println(taskings.get(taskings.size() - 1));
                        System.out.println("Now you have " + taskings.size() + " tasks in the list.");
                    } else {
                        throw(new InvalidEventException());
                    }
                } 
                
                // everything else
                else {
                    throw(new InvalidCommandException());
                }
            } catch (InvalidCommandException e) {
                System.out.println("Invalid Command Entered!");
            } catch (InvalidDeadlineException e) {
                System.out.println("Invalid format for deadline tasks!");
                System.out.println("Format: deadline <name> /by <date>");
            } catch (InvalidEventException e) {
                System.out.println("Invalid format for event tasks!");
                System.out.println("Format: event <name> /from <date> /to <date>");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Enter an index between 1 and " + taskings.size());  
            } catch (EmptyTaskingsException e) {
                System.out.println("You have no taskings yet!");  
            } finally {
                System.out.println("-".repeat(50));
            }
        }

        // Cleanup
        myScanner.close();
    }
}
