package koya.task;

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
