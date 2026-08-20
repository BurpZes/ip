public class Task {
    private String name;
    private boolean status;

    public Task(String name) {
        this.name = name;
        this.status = false;
    }

    public void set_status(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "[" + (this.status ? "X" : " ") + "] " + this.name;
    }
}
