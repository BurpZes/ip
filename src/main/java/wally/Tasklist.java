package wally;

import java.util.ArrayList;

/**
 * Stores tasks.
 */
public class Tasklist {
    private final ArrayList<Task> tasks;

    /**
     * Initialises the ArrayList that stores Tasks.
     */
    public Tasklist() {
        tasks = new ArrayList<>();
    }

    /**
     * Returns the task at the specified one-based position.
     *
     * @param pos One-based task position.
     * @return Task at the specified position.
     */
    public Task getTask(int pos) {
        return tasks.get(pos - 1);
    }

    /**
     * Returns the number of tasks in this list.
     *
     * @return Number of tasks.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Adds a task to this list.
     *
     * @param task Task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes the task at the specified one-based position.
     *
     * @param pos One-based task position.
     */
    public void removeTask(int pos) {
        tasks.remove(pos - 1);
    }
}
