package koya;

import java.io.IOException;
import java.util.ArrayList;

import koya.task.Deadline;
import koya.task.Event;
import koya.task.Task;
import koya.task.ToDo;

/**
 * Contains all methods relevant to handling the tasks in a list, such as adding a Deadline, ToDo and Event
 * to the list. It also allows to delete a task from the list, to list all the tasks
 * and to retrieve the associated task's index.
 */
public class TaskList {
    /**
     * This method adds a Deadline to the list of task found in the text file
     * @param description Description of the Deadline
     * @param by By duration of the Deadline
     */
    public static void addToListDeadline(String description, String by) {
        Koya.list.add(new Deadline(description, by));
        try {
            Storage.appendToFile(Storage.FILE_PATH, Koya.list.get(Koya.taskCount));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method deletes the task associated to the index
     * from the list of task found in the text file
     * @param taskIndex TaskIndex of the associated task
     * @throws KoyaException If the index are out of bounds
     */
    protected static void deleteTask(int taskIndex) throws KoyaException {
        try {
            Task removedTask = Koya.list.remove(taskIndex);
            Koya.taskCount--;
            Ui.confirmRemoveTask(removedTask);
            Storage.updateFile();
        } catch (IndexOutOfBoundsException e) {
            throw new KoyaException("oops! Invalid Index...");
        }
    }

    /**
     * This method lists all the tasks from the list of task
     * @param list List of tasks
     */
    protected static void listTasks(ArrayList<Task> list) {
        int taskIndex = 0;
        for (Task task : list) {
            System.out.println((taskIndex + 1) + ". " + " " + list.get(taskIndex));
            taskIndex++;
        }
    }

    protected static void addToListToDo(String input) throws KoyaException {
        try {
            String description = input.substring(5);
            Koya.list.add(new ToDo(description));

            Storage.appendToFile(Storage.FILE_PATH, Koya.list.get(Koya.taskCount));

        } catch (IndexOutOfBoundsException e) {
            throw new KoyaException("OOH OH! The description of a todo cannot be left empty...");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected static int getTaskIndex(String input) {
        //indexes to obtain number next to delete
        int spaceIndex = input.indexOf(" ");
        int taskIndex = Integer.parseInt(input.substring(spaceIndex).trim()) - 1;
        //-1 to adjust for zero-based index array
        // (e.g. mark 2 should be at index 1 in the array)
        return taskIndex;
    }

    protected static void addToListEvent(String description, String from, String to) {
        Koya.list.add(new Event(description, from, to));
        try {
            Storage.appendToFile(Storage.FILE_PATH, Koya.list.get(Koya.taskCount));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected static void findMatchingTask(String input) {
        int findDividerPosition = 5; //find takes 5 char
        ArrayList<Task> matchingTaskList = new ArrayList<>();

        //find matching tasks in the list
        for (Task task : Koya.list) {
            String[] taskDescriptionSplit = task.getDescription().split(" ");

            for (String words : taskDescriptionSplit) {
                if (words.equals(input.substring(findDividerPosition))) {
                    matchingTaskList.add(task);
                }
            }
        }
        //print matching tasks
        if (matchingTaskList.size() > 0) {
            System.out.println("Here are the matching tasks in your list:");
            listTasks(matchingTaskList);
        } else {
            System.out.println("No matching tasks in your list.");
        }

    }

    protected static Task getTaskFromList(int taskIndex) throws KoyaException {
        try {
            return Koya.list.get(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new KoyaException("oops! Invalid index!");
        }
    }
}
