package koya;

public class Parser {
    String description;
    String by;
    String from;
    String to;

    public Parser() {

    }

    public Parser(String description, String by) {
        this.description = description;
        this.by = by;
    }

    public Parser(String description, String by, String from, String to) {
        this.description = description;
        this.by = by;
        this.from = from;
        this.to = to;
    }

    public Parser parseDeadline(String input) {
        int dividerPosition = input.indexOf(" /by ");

        String description = input.substring(9, dividerPosition); // 9: 8 for deadline + 1 for the space
        String by = input.substring(dividerPosition + 5); // 5 for the number of characters in " /by "}

        return new Parser(description, by);
    }

    public Parser parseEvent(String input) {

        //index to obtain description, from and to
        int fromDividerPosition = input.indexOf(" /from ");
        int toDividerPosition = input.indexOf(" /to ");

        String description = input.substring(6, fromDividerPosition);
        String from = input.substring(fromDividerPosition + 7, toDividerPosition);
        String to = input.substring(toDividerPosition + 5);

        return new Parser(description, by, from, to);
    }


}