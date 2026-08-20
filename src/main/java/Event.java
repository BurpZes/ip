public class Event extends Task {
    private String starting;
    private String ending;

    public Event(String name, String starting, String ending) {
        super(name);
        this.starting = starting;
        this.ending = ending;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.starting + " to: " + this.ending + ")";
    }
}
