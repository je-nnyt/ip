package koya.task;

public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getTaskTypeIcon() {
        return "[E]";
    }

    @Override
    public String toString() {
        return super.toString() +
                " (from: " + from + " to: " + to + ")";
    }
}