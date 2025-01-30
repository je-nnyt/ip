import java.util.Scanner;

public class Koya {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Koya");
        String line;
        Scanner in = new Scanner(System.in);

        System.out.println("What can I do for you?");
        do {
            line = in.nextLine();
            System.out.println(line);
        } while (!line.equals("bye"));

        System.out.println("Bye! See you soon!");

    }
}
