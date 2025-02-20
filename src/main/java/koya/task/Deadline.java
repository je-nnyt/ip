package koya.task;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, String byDeadline, Boolean isTaskDone){
        super(description);
        this.by = byDeadline;
        this.isDone=isTaskDone;
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
    public String toTextFile(){
        return super.toTextFile() + " | " + by;
    }
}