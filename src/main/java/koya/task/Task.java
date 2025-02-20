package koya.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public String getTaskTypeIcon() {
        return " ";//leave empty, overridden by subclass
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String toString() {
        return "[" + getTaskTypeIcon() + "]" + getStatusIcon() + " " + description;
    }

    public String toTextFile() {
        if (this.isDone) {
            return getTaskTypeIcon() + " | " + "1" + description;
        } else {
            return getTaskTypeIcon() + " | " + "0" + " | " + description;
        }

    }

    public static Task loadTaskToList(String line) {
        String[] parts = line.split("\\|");
        String taskType = parts[0].trim();
        String taskDescription = parts[2].trim();
        boolean isTaskDone = parts[1].equals("1");

        switch (taskType) {
        case "T":
            return new ToDo(taskDescription, isTaskDone);
//        case "D":
//            return new Deadline(taskDescription, parts[2].trim(), isTaskDone);
//        case "E":
//            return new Event(taskDescription, parts[2].trim(), isTaskDone);
        default:
            throw new IllegalArgumentException("Invalid Task");
        }
    }
}

// "T | 1 | description"
// par