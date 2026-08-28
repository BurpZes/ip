package wally;

/**
 * Processes commands and returns the corresponding products
 */
public class Parser {
    /**
     * Processes user inputs and performs the corresponding actions.
     * Throws exceptions when occurred.
     * @param command String representing the user input
     * @param tasklist Tasklist representing the list of Tasks
     * @return Boolean where true is a signal to close the chatbot and vice versa
     */
    public static boolean processCommand(String command, Tasklist tasklist) {
        try {
            // Close chatbot
            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("-".repeat(50));
                return true;
            }

            // list tasks
            else if (command.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasklist.getSize(); i++) {
                    System.out.println(String.valueOf(i + 1) + ". " + tasklist.getTask(i + 1));
                }
            }

            // mark command
            else if (command.matches("mark \\d+")) {
                String temp[] = command.split(" ");
                if (tasklist.getSize() == 0) {
                    throw (new EmptyTaskingsException());
                } else if (Integer.parseInt(temp[1]) > tasklist.getSize() || Integer.parseInt(temp[1]) < 1) {
                    throw (new IndexOutOfBoundsException());
                } else {
                    tasklist.getTask(Integer.parseInt(temp[1])).set_status(true);
                    System.out.println("The following task has been marked as done:");
                    System.out.println(tasklist.getTask(Integer.parseInt(temp[1])));
                }
            }

            // unmark command
            else if (command.matches("unmark \\d+")) {
                String temp[] = command.split(" ");
                if (tasklist.getSize() == 0) {
                    throw (new EmptyTaskingsException());
                } else if (Integer.parseInt(temp[1]) > tasklist.getSize() || Integer.parseInt(temp[1]) < 1) {
                    throw (new IndexOutOfBoundsException());
                } else {
                    tasklist.getTask(Integer.parseInt(temp[1])).set_status(false);
                    System.out.println("The following task has been marked as not done yet:");
                    System.out.println(tasklist.getTask(Integer.parseInt(temp[1])));
                }
            }

            // todo command
            else if (command.matches("todo .*")) {
                String temp = command.split("todo ")[1];
                tasklist.addTask(new ToDo(temp));
                System.out.println("The following task has been added:");
                System.out.println(tasklist.getTask(tasklist.getSize()));
                System.out.println("Now you have " + tasklist.getSize() + " tasks in the list.");
            }

            // deadline command
            else if (command.matches("deadline .*")) {
                if (command.matches("deadline .* /by \\d{4}-\\d\\d-\\d\\d \\d\\d:\\d\\d")) {
                    String temp[] = command.split("deadline ")[1].split(" /by ");
                    tasklist.addTask(new Deadline(temp[0], temp[1]));
                    System.out.println("The following task has been added:");
                    System.out.println(tasklist.getTask(tasklist.getSize()));
                    System.out.println("Now you have " + tasklist.getSize() + " tasks in the list.");
                } else {
                    throw (new InvalidDeadlineException());
                }
            }

            // event command
            else if (command.matches("event .*")) {
                if (command.matches(
                        "event .* /from \\d{4}-\\d\\d-\\d\\d \\d\\d:\\d\\d /to \\d{4}-\\d\\d-\\d\\d \\d\\d:\\d\\d")) {
                    String temp[] = command.split("event ")[1].split(" /from | /to ");
                    tasklist.addTask(new Event(temp[0], temp[1], temp[2]));
                    System.out.println("The following task has been added:");
                    System.out.println(tasklist.getTask(tasklist.getSize()));
                    System.out.println("Now you have " + tasklist.getSize() + " tasks in the list.");
                } else {
                    throw (new InvalidEventException());
                }
            }

            // delete command
            else if (command.matches("delete \\d+")) {
                String temp[] = command.split(" ");
                if (tasklist.getSize() == 0) {
                    throw (new EmptyTaskingsException());
                } else if (Integer.parseInt(temp[1]) > tasklist.getSize() || Integer.parseInt(temp[1]) < 1) {
                    throw (new IndexOutOfBoundsException());
                } else {
                    Task currentTask = tasklist.getTask(Integer.parseInt(temp[1]));
                    tasklist.removeTask(Integer.parseInt(temp[1]));
                    System.out.println("The following task has been removed:");
                    System.out.println(currentTask);
                    System.out.println("Now you have " + tasklist.getSize() + " tasks in the list.");
                }
            }

            // everything else
            else {
                throw (new InvalidCommandException());
            }
        } catch (InvalidCommandException e) {
            System.out.println("Invalid Command Entered!");
        } catch (InvalidDeadlineException e) {
            System.out.println("Invalid format for deadline tasks!");
            System.out.println("Format: deadline <name> /by <date: yyyy-MM-dd> <time: HH:mm>");
        } catch (InvalidEventException e) {
            System.out.println("Invalid format for event tasks!");
            System.out.println(
                    "Format: event <name> /from <date: yyyy-MM-dd> <time: HH:mm> /to <date: yyyy-MM-dd> <time: HH:mm>");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Enter an index between 1 and " + tasklist.getSize());
        } catch (EmptyTaskingsException e) {
            System.out.println("You have no tasklist yet!");
        } finally {
            System.out.println("-".repeat(50));
        }
        return false;
    }
}
