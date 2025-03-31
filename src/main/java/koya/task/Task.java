package koya.task;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor initializes the description and set the isDone as false
     *
     * @param description Description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * This method returns the task status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * This method returns the task type icon
     */
    public String getTaskTypeIcon() {
        return " ";//leave empty, overridden by subclass
    }

    /**
     * This method sets the isDone as true to mark a task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    public String getDescription() {
        return description;
    }

    /**
     * This method returns the string representation of the task type, status and description.
     */
    public String toString() {
        return "[" + getTaskTypeIcon() + "]" + getStatusIcon() + " " + description;
    }

    /**
     * Prints the information of a given task onto the text file.
     */
    public String toTextFile() {
        return getTaskTypeIcon() + " | " + (this.isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Loads the task from the text file into the program
     *
     * @param task Task to load from the file to the program
     */
    public static Task loadTaskToList(String task) {
        String[] parts = task.split("\\|");

        String taskType = parts[0].trim();
        boolean isTaskDone = parts[1].trim().equals("1");
        String taskDescription = parts[2].trim();
        String taskByOrFrom = parts.length > 3 ? parts[3].trim() : " ";
        // if array length >3, then a Deadline or Event must have been parsed
        String taskTo = parts.length > 4 ? parts[4].trim() : " ";
        // if array length >4, then an Event must have been parsed

        switch (taskType) {
        case "T":
            return new ToDo(taskDescription, isTaskDone);
        case "D":
            return new Deadline(taskDescription, taskByOrFrom, isTaskDone);
        case "E":
            return new Event(taskDescription, taskByOrFrom, taskTo, isTaskDone);
        default:
            throw new IllegalArgumentException("Unknown task type: " + taskType);
        }
    }
}

