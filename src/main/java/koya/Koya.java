package koya;

import java.util.Scanner;
import java.util.ArrayList;

import koya.task.Task;

/**
 * This is the main class for the Koya personal chatbot program.
 * This program manages a list of Tasks (Todo, Event, Deadline) and features functionalities
 * such as adding, deleting and marking a task.
 */
public class Koya {

    public static int taskCount = 0;
    protected static ArrayList<Task> list = new ArrayList<>();
    public static Parser parser = new Parser();

    /**
     * This is the main method where the Koya chatbot program is run.
     */
    public static void main(String[] args) {

        String input;
        Scanner in = new Scanner(System.in);

        Ui.printIntroMessage();
        //create or load task
        Storage.createLoadFile();

        while (true) {
            try {
                input = in.nextLine().trim();

                if (input.equals("bye")) {
                    break;

                } else if (input.equals("list")) {
                    TaskList.listTasks(list);

                } else if (input.startsWith("mark")) {
                    int taskIndex = TaskList.getTaskIndex(input);

                    TaskList.getTaskFromList(taskIndex).markAsDone();
                    Ui.confirmMarkDone(TaskList.getTaskFromList(taskIndex));
                    Storage.updateFile();

                } else if (input.startsWith("todo")) {
                    TaskList.addToListToDo(input);
                    Ui.confirmAddTask(list);

                } else if (input.startsWith("deadline")) {
                    parser = parser.parseDeadline(input);

                    TaskList.addToListDeadline(parser.description, parser.by);
                    Ui.confirmAddTask(list);

                } else if (input.startsWith("event")) {
                    parser = parser.parseEvent(input);

                    TaskList.addToListEvent(parser.description, parser.from, parser.to);
                    Ui.confirmAddTask(list);

                } else if (input.startsWith("delete")) {
                    int taskIndex = TaskList.getTaskIndex(input);
                    TaskList.deleteTask(taskIndex);

                } else if (input.startsWith("find")) {
                    TaskList.findMatchingTask(input);
                } else {
                    throw new KoyaException("OOH OH! I don't know what that means :0 ");
                }
            } catch (KoyaException e) {
                System.out.println(e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
        Ui.goodbyeMessage();
    }

}
