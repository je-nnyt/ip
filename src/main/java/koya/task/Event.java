package koya.task;

/**
 * Represents an Event task. An <code>Event</code> object
 * contains a description and the duration from its starting to its ending time
 * e.g., <code>event from Monday 3PM to 5pm
 */
public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, String from, String to, Boolean isEventDone) {
        super(description);
        this.from = from;
        this.to = to;
        this.isDone = isEventDone;
    }

    @Override
    public String getTaskTypeIcon() {
        return "E";
    }

    @Override
    public String toString() {
        return super.toString() +
                " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toTextFile() {
        return super.toTextFile() + " | " + from + " | " + to;
    }
}