package koya;

import java.util.ArrayList;

import koya.task.Task;

public class UI {
    public static void confirmRemoveTask(Task removedTask) {
        System.out.println("Noted! I've removed this task:");
        System.out.println(" " + removedTask.toString());
        System.out.println("You only have " + Koya.taskCount + " tasks in your list");
    }

    public static void printIntroMessage() {
        System.out.println("Hello! I'm Koya");
        System.out.println("What can I do for you?");
    }

    public static void confirmMarkDone(Task task) {
        System.out.println("Nice! I've marked this as done:");
        System.out.println(task.toString());
    }

    public static void confirmAddTask(ArrayList<Task> list) {
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + list.get(Koya.taskCount).toString());
        Koya.taskCount++;
        System.out.println("Now you have " + Koya.taskCount + " tasks in the list.");
    }
}
