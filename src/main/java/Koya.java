import java.util.Scanner;

class Task {
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
        return getTaskTypeIcon() + getStatusIcon() + " " + description;
    }
}

class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String getTaskTypeIcon() {
        return "[T]";
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getTaskTypeIcon() {
        return "[D]";
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }

}


class Event extends Task {

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


public class Koya {

    private static int taskCount = 0;
    private static final int MAX_TASKS = 100;
    private static Task[] list = new Task[MAX_TASKS];

    public static void main(String[] args) {

        System.out.println("Hello! I'm Koya");
        String input;

        Scanner in = new Scanner(System.in);
        System.out.println("What can I do for you?");

        while (true) {
            input = in.nextLine();

            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                listTasks(list);

            } else if (input.startsWith("mark")) {
                //indexes to obtain number next to mark
                int spaceIndex = input.indexOf(" ");
                int taskIndex = Integer.parseInt(input.substring(spaceIndex + 1)) - 1;
                //-1 to adjust for zero-based index array (e.g. mark 2 should be at index 1 in the array)

                list[taskIndex].markAsDone();
                confirmMarkDone(list[taskIndex]);

            } else if (input.startsWith("todo")) {
                String description = input.substring(5);

                list[taskCount] = new Todo(description);
                confirmAddTask(list);

            } else if (input.startsWith("deadline")) {
                //Parsing index to obtain description and by
                int dividerPosition = input.indexOf(" /by ");

                String description = input.substring(9, dividerPosition);
                String by = input.substring(dividerPosition + 5);

                list[taskCount] = new Deadline(description, by);
                confirmAddTask(list);

            } else if (input.startsWith("event")) {
                //index to obtain description, from and to
                int fromDividerPosition = input.indexOf(" /from ");
                int toDividerPosition = input.indexOf(" /to ");

                String description = input.substring(6, fromDividerPosition);
                String from = input.substring(fromDividerPosition + 7, toDividerPosition);
                String to = input.substring(toDividerPosition + 5);

                list[taskCount] = new Event(description, from, to);
                confirmAddTask(list);
            } else {
                System.out.println("Action unsupported. Try again.");
            }

        }

        System.out.println("Bye Bye! See you soon!");
    }

    private static void confirmMarkDone(Task list) {
        System.out.println("Nice! I've marked this as done:");
        System.out.println(list.toString());
    }

    private static void listTasks(Task[] list) {
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + " " + list[i].toString());
        }
    }

    private static void confirmAddTask(Task[] list) {
        System.out.println("Got it. I've added this task:");
        System.out.println(list[taskCount].toString());
        taskCount++;
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }
}

