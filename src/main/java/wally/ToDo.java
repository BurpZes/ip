package wally;

public class ToDo extends Task {
    /**
     * Creates a ToDo object
     * 
     * @param name String representing the task
     */
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getCommand() {
        return "ToDo " + super.getCommand();
    }
}
