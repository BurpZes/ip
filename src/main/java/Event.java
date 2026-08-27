public class Event extends Task {
    private String starting;
    private String ending;

    /**
     * Creates an Event object
     * @param name String representing the task
     * @param starting String representing the start of the event
     * @param ending String representing the end of the event
     */
    public Event(String name, String starting, String ending) {
        super(name);
        this.starting = starting;
        this.ending = ending;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.starting + " to: " + this.ending + ")";
    }

    @Override
    public String getCommand() {
        return "event " + super.toString() + " /from " + this.starting + " /to " + this.ending;
    }
}
