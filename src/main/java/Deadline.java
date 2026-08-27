public class Deadline extends Task {
    private String endDate;

    /**
     * Creates a Deadline object
     * @param name String representing the task
     * @param endDate String representing the due date or deadline
     */
    public Deadline(String name, String endDate) {
        super(name);
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.endDate + ")";
    }

    @Override
    public String getCommand() {
        return "deadline " + super.getCommand() + " /by " + this.endDate;
    }
}
