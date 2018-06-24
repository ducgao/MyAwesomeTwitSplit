package zalora.assignment.duckie.twitsplit.core.twitsplit;

public class TwitSplitException extends Exception {
    private static String COMMON_ERROR_MESSAGE = "The input string is empty or contain non-spacing character with length greater than 45";

    private int error;

    public TwitSplitException() {
        super(COMMON_ERROR_MESSAGE);
    }

    public TwitSplitException(int error) {
        super(COMMON_ERROR_MESSAGE);
        this.error = error;
    }

    public int getMessageID() {
        return error;
    }
}