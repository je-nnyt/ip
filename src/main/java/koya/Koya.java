package koya;

import static koya.task.Task.loadTaskToList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import koya.task.Deadline;
import koya.task.Event;
import koya.task.Task;
import koya.task.ToDo;

public class Koya {

    private static int taskCount = 0;
    private static ArrayList<Task> list = new ArrayList<>();
    private static final int TODO_CHAR_COUNT = 5;
    private static final String FOLDER_PATH = "data";
    private static final String FILE_PATH = "./data/koya.txt";

    public static void main(String[] args) {

        String input;
        Scanner in = new Scanner(System.in);
        printIntroMessage();

        //check if folder exists
        File folder = new File(FOLDER_PATH);
        try {
            if (!folder.exists()) {
                folder.mkdirs();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //create or load task
        File file = new File(FILE_PATH);
        try {
            if (!file.exists()) {
                file.createNewFile();
            } else {
                Scanner s = new Scanner(file);
                while (s.hasNext()) {
                    taskCount++;
                    String line = s.nextLine().trim();
                    list.add(loadTaskToList(line));

                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        while (true) {
            try {
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

                    list.get(taskIndex).markAsDone();
                    confirmMarkDone(list.get(taskIndex));

                } else if (input.startsWith("todo")) {

                    if (input.length() <= TODO_CHAR_COUNT) {
                        throw new KoyaException("OOH OH! The description of a todo cannot be left empty...");
                    }

                    String description = input.substring(5);
                    list.add(new ToDo(description));
                    try {
                        appendToFile(FILE_PATH, list.get(taskCount));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    confirmAddTask(list);

                } else if (input.startsWith("deadline")) {

                    //Parsing index to obtain description and by
                    int dividerPosition = input.indexOf(" /by ");

                    String description = input.substring(9, dividerPosition); // 9: 8 for deadline + 1 for the space
                    String by = input.substring(dividerPosition + 5); // 5 for the number of characters in " /by "

                    list.add(new Deadline(description, by));
                    try {
                        appendToFile(FILE_PATH, list.get(taskCount));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    confirmAddTask(list);


                } else if (input.startsWith("event")) {
                    //index to obtain description, from and to
                    int fromDividerPosition = input.indexOf(" /from ");
                    int toDividerPosition = input.indexOf(" /to ");

                    String description = input.substring(6, fromDividerPosition);
                    String from = input.substring(fromDividerPosition + 7, toDividerPosition);
                    String to = input.substring(toDividerPosition + 5);

                    list.add(new Event(description, from, to));
                    try {
                        appendToFile(FILE_PATH, list.get(taskCount));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    confirmAddTask(list);

                } else if (input.startsWith("delete")) {
                    //indexes to obtain number next to delete
                    int spaceIndex = input.indexOf(" ");
                    int taskIndex = Integer.parseInt(input.substring(spaceIndex + 1)) - 1;
                    //-1 to adjust for zero-based index array (e.g. mark 2 should be at index 1 in the array)

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

    private static void removeTask(int taskIndex) {
        Task removedTask = list.remove(taskIndex);
        taskCount--;
        confirmRemoveTask(removedTask);
    }

    private static void confirmRemoveTask(Task removedTask) {
        System.out.println("Noted! I've removed this task:");
        System.out.println(" " + removedTask.toString());
        System.out.println("You only have " + taskCount + " tasks in your list");
    }

    private static void printIntroMessage() {
        System.out.println("Hello! I'm Koya");
        System.out.println("What can I do for you?");
    }

    private static void confirmMarkDone(Task task) {
        System.out.println("Nice! I've marked this as done:");
        System.out.println(task.toString());
    }

    private static void listTasks(ArrayList<Task> list) {
        int taskIndex = 0;
        for (Task task : list) {
            System.out.println((taskIndex + 1) + ". " + " " + list.get(taskIndex));
            taskIndex++;
        }
    }

    private static void confirmAddTask(ArrayList<Task> list) {
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + list.get(taskCount).toString());
        taskCount++;
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    private static void appendToFile(String filePath, Task taskToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(taskToAdd.toTextFile() + "\n");
        fw.close();
    }
}

