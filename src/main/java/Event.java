import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Stores a task with description, starting datetime and ending datetime
 */
public class Event extends Task {
    private LocalDateTime starting;
    private LocalDateTime ending;

    /**
     * Creates an Event object
     * @param name String representing the task
     * @param starting String representing the start of the event
     * @param ending String representing the end of the event
     */
    public Event(String name, String starting, String ending) {
        super(name);
        this.starting = LocalDateTime.parse(starting);
        this.ending = LocalDateTime.parse(ending);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " 
                + this.starting.format(DateTimeFormatter.ofPattern("d MMM yyyy, HH:mm")) 
                + " to: " + this.ending.format(DateTimeFormatter.ofPattern("d MMM yyyy, HH:mm")) + ")";
    }

    @Override
    public String getCommand() {
        return "event " + super.toString() + " /from " + this.starting + " /to " + this.ending;
    }
}
