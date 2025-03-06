package koya.task;

/**
 * Represents a ToDo task. A <code>ToDo</code> object
 * contains the description of the task that must be completed
 * e.g., <code> todo level-9
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, Boolean isTaskDone) {
        super(description);
        this.isDone = isTaskDone;
    }

    @Override
    public String getTaskTypeIcon() {
        return "T";
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
