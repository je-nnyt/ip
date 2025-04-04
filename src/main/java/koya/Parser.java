package koya;

/**
 * Contains the parsing methods of Event and Deadline task.
 */
public class Parser {

    public static final int DEADLINE_CHAR_COUNT = 9; // "deadline " has 6 char including spaces and '/'
    public static final int EVENT_CHAR_COUNT = 6; // "event " has 6 char including spaces and '/'
    public static final int FROM_CHAR_COUNT = 7; // " /from " has 7 char including spaces and '/'
    public static final int TO_CHAR_COUNT = 5;
    public static final int BY_CHAR_COUNT = 5;


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

    public Parser(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * This method returns a Parser object containing the description, "by" deadline of
     * a Deadline task
     *
     * @param input String input by the user
     * @return Parser
     */
    public Parser parseDeadline(String input) throws KoyaException {
        try {
            int dividerPosition = input.indexOf(" /by ");

            String description = input.substring(DEADLINE_CHAR_COUNT, dividerPosition).trim();
            String by = input.substring(dividerPosition + BY_CHAR_COUNT).trim();

            return new Parser(description, by);
        } catch (Exception e) {
            throw new KoyaException("OOH! Invalid format");
        }
    }

    /**
     * This method returns a Parser object containing the description, "from" and "to" date of
     * an Event input by the user.
     *
     * @param input String input by the user
     * @return Parser
     */
    public Parser parseEvent(String input) throws KoyaException {

        try {
            //index to obtain description, from and to
            int fromDividerPosition = input.indexOf(" /from ");
            int toDividerPosition = input.indexOf(" /to ");

            String description = input.substring(EVENT_CHAR_COUNT, fromDividerPosition).trim();
            String from = input.substring(fromDividerPosition + FROM_CHAR_COUNT, toDividerPosition).trim();
            String to = input.substring(toDividerPosition + TO_CHAR_COUNT).trim();

            return new Parser(description, from, to);
        } catch (Exception e) {
            throw new KoyaException("OHH! Invalid format");
        }
    }

}