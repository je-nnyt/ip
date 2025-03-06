package koya;

import java.util.Scanner;
import java.util.ArrayList;

import koya.task.Task;

public class Koya {

    public static int taskCount = 0;
    public static ArrayList<Task> list = new ArrayList<>();
    private static final int TODO_CHAR_COUNT = 5;
    public static Parser parser = new Parser();

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
                    parser = parser.parseDeadline(input);

                    TaskList.addToListDeadline(parser.description, parser.by);
                    Ui.confirmAddTask(list);

                } else if (input.startsWith("event")) {
                    parser.parseEvent(input);

                    TaskList.addToListEvent(parser.description, parser.from, parser.to);
                    Ui.confirmAddTask(list);

                } else if (input.startsWith("delete")) {
                    int taskIndex = TaskList.getTaskIndex(input);
                    TaskList.removeTask(taskIndex);

                } else if (input.startsWith("find")) {
                    TaskList.findMatchingTask(input);
                } else {
                    throw new KoyaException("OOH OH! I don't know what that means :/ ");
                }
            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            } catch (KoyaException e) {
                System.out.println(e);
            }

        }
        Ui.goodbyeMessage();
    }

}
