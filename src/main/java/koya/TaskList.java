package koya;

import java.util.ArrayList;

import koya.task.Deadline;
import koya.task.Event;
import koya.task.Task;
import koya.task.ToDo;

public class TaskList {
    public static void addToListDeadline(String description, String by) {
        Koya.list.add(new Deadline(description, by));
        try {
            Storage.appendToFile(Storage.FILE_PATH, Koya.list.get(Koya.taskCount));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void removeTask(int taskIndex) {
        Task removedTask = Koya.list.remove(taskIndex);
        Koya.taskCount--;
        Ui.confirmRemoveTask(removedTask);
        Storage.updateFile();
    }

    public static void listTasks(ArrayList<Task> list) {
        int taskIndex = 0;
        for (Task task : list) {
            System.out.println((taskIndex + 1) + ". " + " " + list.get(taskIndex));
            taskIndex++;
        }
    }

    public static void addToListToDo(String input) {
        String description = input.substring(5);
        Koya.list.add(new ToDo(description));
        try {
            Storage.appendToFile(Storage.FILE_PATH, Koya.list.get(Koya.taskCount));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static int getTaskIndex(String input) {
        //indexes to obtain number next to delete
        int spaceIndex = input.indexOf(" ");
        int taskIndex = Integer.parseInt(input.substring(spaceIndex + 1)) - 1;
        //-1 to adjust for zero-based index array (e.g. mark 2 should be at index 1 in the array)
        return taskIndex;
    }

    public static void addToListEvent(String description, String from, String to) {
        Koya.list.add(new Event(description, from, to));
        try {
            Storage.appendToFile(Storage.FILE_PATH, Koya.list.get(Koya.taskCount));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
