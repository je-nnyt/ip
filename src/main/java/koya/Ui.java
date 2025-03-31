package koya;

import java.util.ArrayList;

import koya.task.Task;

/**
 * <code>Ui</code> contains all methods that interacts with the user such as
 * printing the intro and the outro message, confirms that a task has been added, removed,
 * and marked as done.
 */
public class Ui {

    /**
     * This method prints a dashed line for clarity purposes.
     */
    public static void dashedLine() {
        System.out.println("------------------------------------------");
    }

    /**
     * This method prints a message once a task has been removed.
     */
    public static void confirmRemoveTask(Task removedTask) {
        dashedLine();
        System.out.println("Noted! I've removed this task:");
        System.out.println(" " + removedTask.toString());
        System.out.println("You only have " + Koya.taskCount + " tasks in your list");
        dashedLine();
    }

    /**
     * This method prints the introduction message to greet and prompt the user to enter a command.
     */
    public static void printIntroMessage() {
        dashedLine();
        System.out.println("Hello! I'm Koya");
        System.out.println("What can I do for you?");
        dashedLine();
    }

    /**
     * This method prints a message once a task has been mark as done.
     */
    public static void confirmMarkDone(Task task) {
        dashedLine();
        System.out.println("Nice! I've marked this as done:");
        System.out.println(task.toString());
        dashedLine();
    }

    /**
     * This method prints a message once a task has been added.
     */
    public static void confirmAddTask(ArrayList<Task> list) {
        dashedLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + list.get(Koya.taskCount).toString());
        Koya.taskCount++;
        System.out.println("Now you have " + Koya.taskCount + " tasks in the list.");
        dashedLine();
    }

    /**
     * This method prints the goodbye message once the user decides to exit the program.
     */
    static void goodbyeMessage() {
        dashedLine();
        System.out.println("Bye Bye! See you soon!");
        dashedLine();
    }

    /**
     * This method prints a message that no matching tasks from the given keyword have been found.
     */
    static void printNoMatchingTask() {
        dashedLine();
        System.out.println("No matching tasks in your list.");
        dashedLine();
    }

    /**
     * This method prints all tasks that matches the given keyword.
     */
    static void printMatchingTaskList(ArrayList<Task> matchingTaskList) {
        dashedLine();
        System.out.println("Here are the matching tasks in your list:");
        TaskList.listTasks(matchingTaskList);
        dashedLine();
    }


    public static void listMessage() {
        dashedLine();
        System.out.println("Here are the tasks in your list:");
    }
}
