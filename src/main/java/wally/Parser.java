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
            // Close chatbot
            if (command.equals("bye")) {
                output = "TERMINATE_PROGRAM";
            }

            // list tasks
            else if (command.equals("list")) {
                output = "Here are the tasks in your list:";
                for (int i = 0; i < tasklist.getSize(); i++) {
                    output += ("\n" + String.valueOf(i + 1) + ". " + tasklist.getTask(i + 1));
                }
            }

            // mark command
            else if (command.matches("mark \\d+")) {
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
            }

            // unmark command
            else if (command.matches("unmark \\d+")) {
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
            }

            // todo command
            else if (command.matches("todo .*")) {
                String taskDescription = command.split("todo ")[1];
                tasklist.addTask(new ToDo(taskDescription));
                output = "The following task has been added:\n";
                output += tasklist.getTask(tasklist.getSize()).toString();
                output += ("\nNow you have " + tasklist.getSize() + " tasks in the list.");
            }

            // deadline command
            else if (command.matches("deadline .*")) {
                if (command.matches("deadline .* /by \\d{4}-\\d\\d-\\d\\d \\d\\d:\\d\\d")) {
                    String[] commandParts = command.split("deadline ")[1].split(" /by ");
                    tasklist.addTask(new Deadline(commandParts[0], commandParts[1]));
                    output = "The following task has been added:\n";
                    output += tasklist.getTask(tasklist.getSize()).toString();
                    output += ("\nNow you have " + tasklist.getSize() + " tasks in the list.");
                } else {
                    throw (new InvalidDeadlineException());
                }
            }

            // event command
            else if (command.matches("event .*")) {
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
            }

            // delete command
            else if (command.matches("delete \\d+")) {
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
            }

            // list command
            else if (command.matches("find .*")) {
                String searchTerm = command.split("find ")[1];
                output = "Here are the matching tasks in your list:";
                for (int i = 1; i <= tasklist.getSize(); i++) {
                    if (tasklist.getTask(i).toString().toLowerCase().contains(searchTerm.toLowerCase())) {
                        output += ("\n" + String.valueOf(i) + "." + tasklist.getTask(i));
                    }
                }
            }

            // everything else
            else {
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
