package koya;

import static koya.task.Task.loadTaskToList;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;

import koya.task.Deadline;
import koya.task.Event;
import koya.task.Task;
import koya.task.ToDo;

public class Koya {

    private static int taskCount = 0;
    private static final int MAX_TASKS = 100;
    private static Task[] list = new Task[MAX_TASKS];
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
            if (!folder.exists()){
                folder.mkdirs();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //create or load task
        File file = new File (FILE_PATH);
        try {
            if (!file.exists() ) {
                file.createNewFile();
            } else {
                Scanner s = new Scanner(file);
                while (s.hasNext()){
                    String line = s.nextLine().trim();
                    list[taskCount]=loadTaskToList(line);
                    taskCount++;
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

                    list[taskIndex].markAsDone();
                    confirmMarkDone(list[taskIndex]);

                } else if (input.startsWith("todo")) {

                    if (input.length() <= TODO_CHAR_COUNT) {
                        throw new KoyaException("OOH OH! The description of a todo cannot be left empty...");
                    }

                    String description = input.substring(5);
                    list[taskCount] = new ToDo(description);
                    try {
                        appendToFile(FILE_PATH,list[taskCount]);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    confirmAddTask(list);

                } else if (input.startsWith("deadline")) {

                    //Parsing index to obtain description and by
                    int dividerPosition = input.indexOf(" /by ");

                    String description = input.substring(9, dividerPosition); // 9: 8 for deadline + 1 for the space
                    String by = input.substring(dividerPosition + 5); // 5 for the number of characters in " /by "

                    list[taskCount] = new Deadline(description, by);
                    try {
                        appendToFile(FILE_PATH,list[taskCount]);
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

                    list[taskCount] = new Event(description, from, to);
                    try {
                        appendToFile(FILE_PATH,list[taskCount]);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    confirmAddTask(list);
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

    private static void printIntroMessage() {
        System.out.println("Hello! I'm Koya");
        System.out.println("What can I do for you?");
    }

    private static void confirmMarkDone(Task task) {
        System.out.println("Nice! I've marked this as done:");
        System.out.println(task.toString());
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

    private static void appendToFile (String filePath, Task taskToAdd) throws IOException{
        FileWriter fw = new FileWriter(filePath,true);
        fw.write(taskToAdd.toTextFile() + "\n");
        fw.close();

    }
}

