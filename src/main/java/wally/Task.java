package wally;

/**
 * Represents a Task with descriptions
 */
public class Task {
    private String name;
    private boolean status;

    /**
     * Creates a Task object with name as "name" and status as false.
     * @param name String representing the Task
     */
    public Task(String name) {
        this.name = name;
        this.status = false;
    }

    public void set_status(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "[" + (this.status ? "X" : " ") + "] " + this.name;
    }

    /**
     * Returns the command used to create this task as a string
     * @return A string representing the command
     */
    public String getCommand() {
        return this.name;
    }
}
