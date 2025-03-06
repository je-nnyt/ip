package koya;

import java.util.Scanner;
import java.util.ArrayList;

import koya.task.Deadline;
import koya.task.Event;
import koya.task.Task;
import koya.task.ToDo;

public class Koya {

    public static int taskCount = 0;
    public static ArrayList<Task> list = new ArrayList<>();
    private static final int TODO_CHAR_COUNT = 5;

    public static void main(String[] args) {

        String input;
        Scanner in = new Scanner(System.in);

        UI.printIntroMessage();
        //check if folder exists
        Storage.checkFolderExists();
        //create or load task
        Storage.createLoadFile();

        while (true) {
            try {
                input = in.nextLine();

                if (input.equals("bye")) {
                    break;
                } else if (input.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    listTasks(list);

                } else if (input.startsWith("mark")) {
                    //obtain number next to mark
                    int taskIndex = getTaskIndex(input);

                    list.get(taskIndex).markAsDone();
                    UI.confirmMarkDone(list.get(taskIndex));
                    Storage.updateFile();

                } else if (input.startsWith("todo")) {

                    if (input.length() <= TODO_CHAR_COUNT) {
                        throw new KoyaException("OOH OH! The description of a todo cannot be left empty...");
                    }
                    addToListToDo(input);
                    UI.confirmAddTask(list);

                } else if (input.startsWith("deadline")) {

                    //Parsing index to obtain description and by
                    int dividerPosition = input.indexOf(" /by ");

                    String description = input.substring(9, dividerPosition); // 9: 8 for deadline + 1 for the space
                    String by = input.substring(dividerPosition + 5); // 5 for the number of characters in " /by "

                    addToListDeadline(description, by);
                    UI.confirmAddTask(list);


                } else if (input.startsWith("event")) {
                    //index to obtain description, from and to
                    int fromDividerPosition = input.indexOf(" /from ");
                    int toDividerPosition = input.indexOf(" /to ");

                    String description = input.substring(6, fromDividerPosition);
                    String from = input.substring(fromDividerPosition + 7, toDividerPosition);
                    String to = input.substring(toDividerPosition + 5);

                    addToListEvent(description, from, to);
                    UI.confirmAddTask(list);

                } else if (input.startsWith("delete")) {
                    int taskIndex = getTaskIndex(input);
                    removeTask(taskIndex);

                } else {
                    throw new KoyaException("OOH OH! I don't know what that means :/ ");
                }
            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            } catch (KoyaException e) {
                System.out.println(e);
            }

        }

        System.out.println("Bye Bye! See you soon!");
    }

    private static int getTaskIndex(String input) {
        //indexes to obtain number next to delete
        int spaceIndex = input.indexOf(" ");
        int taskIndex = Integer.parseInt(input.substring(spaceIndex + 1)) - 1;
        //-1 to adjust for zero-based index array (e.g. mark 2 should be at index 1 in the array)
        return taskIndex;
    }

    private static void addToListEvent(String description, String from, String to) {
        list.add(new Event(description, from, to));
        try {
            Storage.appendToFile(Storage.FILE_PATH, list.get(taskCount));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void addToListDeadline(String description, String by) {
        list.add(new Deadline(description, by));
        try {
            Storage.appendToFile(Storage.FILE_PATH, list.get(taskCount));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void addToListToDo(String input) {
        String description = input.substring(5);
        list.add(new ToDo(description));
        try {
            Storage.appendToFile(Storage.FILE_PATH, list.get(taskCount));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void removeTask(int taskIndex) {
        Task removedTask = list.remove(taskIndex);
        taskCount--;
        UI.confirmRemoveTask(removedTask);
        Storage.updateFile();
    }

    private static void listTasks(ArrayList<Task> list) {
        int taskIndex = 0;
        for (Task task : list) {
            System.out.println((taskIndex + 1) + ". " + " " + list.get(taskIndex));
            taskIndex++;
        }
    }
}
