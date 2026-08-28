import java.util.ArrayList;

/**
 * Stores tasks
 */
public class Tasklist {
    private ArrayList<Task> taskings;

    /**
     * Initialises the ArrayList that stores Tasks.
     */
    public Tasklist() {
        this.taskings = new ArrayList<>();
    }

    /**
     * Returns the task in index pos - 1 of taskings.
     * @param pos index + 1 to retrieve
     * @return the Task object in pos - 1 index
     */
    public Task getTask(int pos) {
        return taskings.get(pos - 1);
    }

    /**
     * Returns the number of Tasks in taskings.
     * @return Integer representing the number of Tasks in taskings.
     */
    public int getSize() {
        return taskings.size();
    }

    /**
     * Adds task to taskings
     * @param task a ToDo, Deadline or Event object
     */
    public void addTask(Task task) {
        taskings.add(task);
    }

    /**
     * Removes the Task in pos index from taskings
     * @param pos The index of the Task to be removed.
     */
    public void removeTask(int pos) {
        taskings.remove(pos - 1);
    }
}
