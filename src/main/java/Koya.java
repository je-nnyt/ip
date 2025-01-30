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

    public void markAsDone() {
        this.isDone = true;
    }
}

public class Koya {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Koya");

        String input;
        Scanner in = new Scanner(System.in);
        int countTasks = 0;
        Task[] list = new Task[100];
        System.out.println("What can I do for you?");

        do {
            input = in.nextLine();

            //exit
            if (input.equals("bye")) {
                break;
            }

            //display list
            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < countTasks; i++) {
                    System.out.println((i + 1) + ". " + list[i].getStatusIcon() + " " + list[i].description);
                }
                continue;
            }

            //mark as done
            if (input.startsWith("mark")) {
                int spaceIndex = input.indexOf(" ");
                int taskIndex = Integer.parseInt(input.substring(spaceIndex + 1)) - 1;
                list[taskIndex].markAsDone();
                System.out.println("Nice! I've marked this as done:");
                System.out.println(list[taskIndex].getStatusIcon() + " " + list[taskIndex].description);
                continue;
            }

            //store task into list
            else {
                list[countTasks] = new Task(input);
                System.out.println("added: " + list[countTasks].description);
                countTasks++;
            }

        } while (true);

        System.out.println("Bye Bye! See you soon!");
    }
}

