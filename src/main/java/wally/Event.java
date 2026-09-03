package wally;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Stores a task with a description, start date-time, and end date-time.
 */
public class Event extends Task {
    private LocalDateTime starting;
    private LocalDateTime ending;

    /**
     * Creates an event task with the specified name, start, and end times.
     *
     * @param name Name of the task.
     * @param starting Start time in the expected date-time format.
     * @param ending End time in the expected date-time format.
     */
    public Event(String name, String starting, String ending) {
        super(name);
        this.starting = LocalDateTime.parse(starting, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.ending = LocalDateTime.parse(ending, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + this.starting.format(DateTimeFormatter.ofPattern("d MMM yyyy, HH:mm"))
                + " to: " + this.ending.format(DateTimeFormatter.ofPattern("d MMM yyyy, HH:mm")) + ")";
    }

    @Override
    public String getCommand() {
        return "event " + super.getCommand() + " /from "
                + this.starting.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + " /to "
                + this.ending.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
