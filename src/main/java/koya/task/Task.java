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
        String taskByOrFrom = parts.length > 3 ?  parts[3].trim() : " ";
        String taskTo = parts.length > 4 ? parts[4].trim() : " ";


        switch (taskType) {
        case "T":
            return new ToDo(taskDescription, isTaskDone);
        case "D":
            return new Deadline(taskDescription, taskByOrFrom, isTaskDone);
        case "E":
            return new Event(taskDescription, taskByOrFrom,taskTo, isTaskDone);
        default:
            throw new IllegalArgumentException("Invalid Task");
        }
    }
}

// "T | 1 | description"
// D | 0 | return book | June 6th
// E | 0 | project meeting | Aug 6th 2-4pm