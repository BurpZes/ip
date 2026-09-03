package wally;

/**
 * Processes commands and returns the corresponding responses.
 */
public class Parser {
    /**
     * Processes user inputs and performs the corresponding actions.
     * Handles errors caused by invalid commands.
     *
     * @param command User input.
     * @param tasklist List of tasks to update.
     * @return Wally's response.
     */
    public static String processCommand(String command, Tasklist tasklist) {
        String output = "";
        try {
            if (command.equals("bye")) {
                // Closes chatbot.
                output = "TERMINATE_PROGRAM";
            } else if (command.equals("list")) {
                // Lists tasks.
                output = "Here are the tasks in your list:";
                for (int i = 0; i < tasklist.getSize(); i++) {
                    output += ("\n" + String.valueOf(i + 1) + ". " + tasklist.getTask(i + 1));
                }
            } else if (command.matches("mark \\d+")) {
                // Marks a task as done.
                String[] commandParts = command.split(" ");
                if (tasklist.getSize() == 0) {
                    throw (new EmptyTaskingsException());
                } else if (Integer.parseInt(commandParts[1]) > tasklist.getSize()
                        || Integer.parseInt(commandParts[1]) < 1) {
                    throw (new IndexOutOfBoundsException());
                } else {
                    tasklist.getTask(Integer.parseInt(commandParts[1])).setCompleted(true);
                    output = "The following task has been marked as done:\n";
                    output += tasklist.getTask(Integer.parseInt(commandParts[1])).toString();
                }
            } else if (command.matches("unmark \\d+")) {
                // Marks a task as not done.
                String[] commandParts = command.split(" ");
                if (tasklist.getSize() == 0) {
                    throw (new EmptyTaskingsException());
                } else if (Integer.parseInt(commandParts[1]) > tasklist.getSize()
                        || Integer.parseInt(commandParts[1]) < 1) {
                    throw (new IndexOutOfBoundsException());
                } else {
                    tasklist.getTask(Integer.parseInt(commandParts[1])).setCompleted(false);
                    output = "The following task has been marked as not done yet:\n";
                    output += tasklist.getTask(Integer.parseInt(commandParts[1])).toString();
                }
            } else if (command.matches("todo .*")) {
                // Adds a to-do task.
                String taskDescription = command.split("todo ")[1];
                tasklist.addTask(new ToDo(taskDescription));
                output = "The following task has been added:\n";
                output += tasklist.getTask(tasklist.getSize()).toString();
                output += ("\nNow you have " + tasklist.getSize() + " tasks in the list.");
            } else if (command.matches("deadline .*")) {
                // Adds a deadline task.
                if (command.matches("deadline .* /by \\d{4}-\\d\\d-\\d\\d \\d\\d:\\d\\d")) {
                    String[] commandParts = command.split("deadline ")[1].split(" /by ");
                    tasklist.addTask(new Deadline(commandParts[0], commandParts[1]));
                    output = "The following task has been added:\n";
                    output += tasklist.getTask(tasklist.getSize()).toString();
                    output += ("\nNow you have " + tasklist.getSize() + " tasks in the list.");
                } else {
                    throw (new InvalidDeadlineException());
                }
            } else if (command.matches("event .*")) {
                // Adds an event task.
                if (command.matches(
                        "event .* /from \\d{4}-\\d\\d-\\d\\d \\d\\d:\\d\\d /to \\d{4}-\\d\\d-\\d\\d \\d\\d:\\d\\d")) {
                    String[] commandParts = command.split("event ")[1].split(" /from | /to ");
                    tasklist.addTask(new Event(commandParts[0], commandParts[1], commandParts[2]));
                    output = "The following task has been added:\n";
                    output += tasklist.getTask(tasklist.getSize()).toString();
                    output += ("\nNow you have " + tasklist.getSize() + " tasks in the list.");
                } else {
                    throw (new InvalidEventException());
                }
            } else if (command.matches("delete \\d+")) {
                // Deletes a task.
                String[] commandParts = command.split(" ");
                if (tasklist.getSize() == 0) {
                    throw (new EmptyTaskingsException());
                } else if (Integer.parseInt(commandParts[1]) > tasklist.getSize()
                        || Integer.parseInt(commandParts[1]) < 1) {
                    throw (new IndexOutOfBoundsException());
                } else {
                    Task currentTask = tasklist.getTask(Integer.parseInt(commandParts[1]));
                    tasklist.removeTask(Integer.parseInt(commandParts[1]));
                    output = "The following task has been removed:\n";
                    output += currentTask.toString();
                    output += ("\nNow you have " + tasklist.getSize() + " tasks in the list.");
                }
            } else if (command.matches("find .*")) {
                // Finds matching tasks.
                String searchTerm = command.split("find ")[1];
                output = "Here are the matching tasks in your list:";
                for (int i = 1; i <= tasklist.getSize(); i++) {
                    if (tasklist.getTask(i).toString().toLowerCase().contains(searchTerm.toLowerCase())) {
                        output += ("\n" + String.valueOf(i) + "." + tasklist.getTask(i));
                    }
                }
            } else {
                // Rejects unsupported commands.
                throw (new InvalidCommandException());
            }

        } catch (InvalidCommandException e) {
            output = "Invalid Command Entered!";
        } catch (InvalidDeadlineException e) {
            output = "Invalid format for deadline tasks!";
            output = "Format: deadline <name> /by <date: yyyy-MM-dd> <time: HH:mm>";
        } catch (InvalidEventException e) {
            output = "Invalid format for event tasks!";
            output = "Format: event <name> /from <date: yyyy-MM-dd> <time: HH:mm>"
                    + " /to <date: yyyy-MM-dd> <time: HH:mm>";
        } catch (IndexOutOfBoundsException e) {
            output = "Enter an index between 1 and " + tasklist.getSize();
        } catch (EmptyTaskingsException e) {
            output = "You have no tasklist yet!";
        }
        return output;
    }
}
