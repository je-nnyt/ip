package koya;

import java.util.Scanner;
import java.util.ArrayList;

import koya.task.Task;

public class Koya {

    public static int taskCount = 0;
    public static ArrayList<Task> list = new ArrayList<>();
    private static final int TODO_CHAR_COUNT = 5;

    public static void main(String[] args) {

        String input;
        Scanner in = new Scanner(System.in);

        Ui.printIntroMessage();
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
                    TaskList.listTasks(list);

                } else if (input.startsWith("mark")) {
                    //obtain number next to mark
                    int taskIndex = TaskList.getTaskIndex(input);

                    list.get(taskIndex).markAsDone();
                    Ui.confirmMarkDone(list.get(taskIndex));
                    Storage.updateFile();

                } else if (input.startsWith("todo")) {

                    if (input.length() <= TODO_CHAR_COUNT) {
                        throw new KoyaException("OOH OH! The description of a todo cannot be left empty...");
                    }
                    TaskList.addToListToDo(input);
                    Ui.confirmAddTask(list);

                } else if (input.startsWith("deadline")) {

                    //Parsing index to obtain description and by
                    int dividerPosition = input.indexOf(" /by ");

                    String description = input.substring(9, dividerPosition); // 9: 8 for deadline + 1 for the space
                    String by = input.substring(dividerPosition + 5); // 5 for the number of characters in " /by "

                    TaskList.addToListDeadline(description, by);
                    Ui.confirmAddTask(list);


                } else if (input.startsWith("event")) {
                    //index to obtain description, from and to
                    int fromDividerPosition = input.indexOf(" /from ");
                    int toDividerPosition = input.indexOf(" /to ");

                    String description = input.substring(6, fromDividerPosition);
                    String from = input.substring(fromDividerPosition + 7, toDividerPosition);
                    String to = input.substring(toDividerPosition + 5);

                    TaskList.addToListEvent(description, from, to);
                    Ui.confirmAddTask(list);

                } else if (input.startsWith("delete")) {
                    int taskIndex = TaskList.getTaskIndex(input);
                    TaskList.removeTask(taskIndex);

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

}
