package wally;

/**
 * Represents a task with a description and completion status.
 */
public class Task {
    private String name;
    private boolean isCompleted;

    /**
     * Creates a task with the specified name and an incomplete status.
     *
     * @param name Name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isCompleted = false;
    }

    /**
     * Updates this task's completion status.
     *
     * @param isCompleted Whether this task is completed.
     */
    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    @Override
    public String toString() {
        return "[" + (isCompleted ? "X" : " ") + "] " + name;
    }

    /**
     * Returns the command used to create this task.
     *
     * @return Command that creates this task.
     */
    public String getCommand() {
        return name;
    }
}
