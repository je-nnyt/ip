import java.util.Scanner;

public class Koya {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Koya");
        String input;
        Scanner in = new Scanner(System.in);

        int countTasks = 0;
        String[] list = new String[100];
        System.out.println("What can I do for you?");
        do {
            input = in.nextLine();

            //exit
            if (input.equals("bye")) {
                break;
            }

            //display list
            if (input.equals("list")) {
                for (int i = 0; i < countTasks; i++) {
                    System.out.println(i + 1 + ". " + list[i]);
                }
            }

            //store task into list
            else {
                list[countTasks] = input;
                System.out.println("added: " + list[countTasks]);
                countTasks++;
            }

        } while (true);

        System.out.println("Bye Bye! See you soon!");

    }
}
