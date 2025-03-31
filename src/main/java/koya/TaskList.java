package koya;

import java.io.IOException;
import java.util.ArrayList;

import koya.task.Deadline;
import koya.task.Event;
import koya.task.Task;
import koya.task.ToDo;
import koya.Ui;

/**
 * Contains all methods relevant to handling the tasks in a list, such as adding a Deadline, ToDo and Event
 * to the list. It also allows to delete a task from the list, to list all the tasks
 * and to retrieve the associated task's index.
 */
public class TaskList {
    /**
     * This method adds a Deadline to the list of task found in the text file
     *
     * @param description Description of the Deadline
     * @param by          By duration of the Deadline
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
     *
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
     * This method prints all the tasks from the list of task
     *
     * @param list List of tasks
     */
    protected static void listTasks(ArrayList<Task> list) {
        Ui.listMessage();
        int taskIndex = 0;
        for (Task task : list) {
            System.out.println((taskIndex + 1) + ". " + " " + list.get(taskIndex));
            taskIndex++;
        }
        Ui.dashedLine();
    }

    /**
     * This method adds a ToDo to the list of task from the text file
     *
     * @param input Input String describing the ToDo task
     */
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

    /**
     * This method parses the input and returns the task index
     *
     * @param input Input String containing the task index
     * @return Index of the task to be deleted
     */
    protected static int getTaskIndex(String input) throws KoyaException {
        try {
            //indexes to obtain number next to delete
            int spaceIndex = input.indexOf(" ");
            int taskIndex = Integer.parseInt(input.substring(spaceIndex).trim()) - 1;
            //-1 to adjust for zero-based index array
            // (e.g. mark 2 should be at index 1 in the array)
            return taskIndex;
        } catch (Exception e) {
            throw new KoyaException("OOH! Invalid task index");
        }
    }

    /**
     * This method adds an Event to the list of task from the text file
     *
     * @param description Description of the event
     * @param from        Starting time of the event
     * @param to          Ending time of the event
     */
    protected static void addToListEvent(String description, String from, String to) {
        Koya.list.add(new Event(description, from, to));
        try {
            Storage.appendToFile(Storage.FILE_PATH, Koya.list.get(Koya.taskCount));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method finds all tasks with matching keyword and prints them
     *
     * @param input Input desired keyword
     * @throws KoyaException if no keyword is found in the input string
     */
    protected static void findMatchingTask(String input) throws KoyaException {

        ArrayList<Task> matchingTaskList = null;

        try {
            int findDividerPosition = 5; //find takes 5 char
            matchingTaskList = new ArrayList<>();

            //find matching tasks in the list
            for (Task task : Koya.list) {
                //split the description and store each words in the array
                String[] taskDescriptionSplit = task.getDescription().split(" ");

                findMatchingKeyword(input, task, taskDescriptionSplit,
                        findDividerPosition, matchingTaskList);
            }
        } catch (Exception e) {
            throw new KoyaException("OOH! Invalid format");
        }

        //print matching tasks
        if (matchingTaskList.size() > 0) {
            Ui.printMatchingTaskList(matchingTaskList);
        } else {
            Ui.printNoMatchingTask();
        }

    }

    private static void findMatchingKeyword(String input, Task task, String[] taskDescriptionSplit,
                                            int findDividerPosition, ArrayList<Task> matchingTaskList) {
        for (String words : taskDescriptionSplit) {
            if (words.equals(input.substring(findDividerPosition))) {
                matchingTaskList.add(task);
            }
        }
    }

    /**
     * This method returns the task at a given index.
     *
     * @param taskIndex Index of the associated task to retrieve from the list
     * @return Task
     */
    protected static Task getTaskFromList(int taskIndex) throws KoyaException {
        try {
            return Koya.list.get(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new KoyaException("oops! Invalid index!");
        }
    }
}
