package koya.task;

/**
 * Represents a deadline task. A <code>Deadline</code> object
 * contains a description and the date and/or time by which it should be completed
 * e.g., <code>deadline by Monday 3PM</code>
 */
public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, String byDeadline, Boolean isTaskDone) {
        super(description);
        this.by = byDeadline;
        this.isDone = isTaskDone;
    }

    @Override
    public String getTaskTypeIcon() {
        return "D";
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toTextFile() {
        return super.toTextFile() + " | " + by;
    }
}