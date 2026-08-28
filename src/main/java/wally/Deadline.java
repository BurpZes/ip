package wally;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Stores a task with description and deadline / due date
 */
public class Deadline extends Task {
    private LocalDateTime endDate;

    /**
     * Creates a Deadline object
     * @param name String representing the task
     * @param endDate String representing the due date or deadline
     */
    public Deadline(String name, String endDate) {
        super(name);
        this.endDate = LocalDateTime.parse(endDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " 
                + this.endDate.format(DateTimeFormatter.ofPattern("d MMM yyyy, HH:mm")) + ")";
    }

    @Override
    public String getCommand() {
        return "deadline " + super.getCommand() + " /by " 
                + this.endDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
