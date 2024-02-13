package app.exceptions;

public class FullNameFormatException extends Exception {
    public FullNameFormatException() {
        super();
    }

    public FullNameFormatException(String message) {
        super(message);
    }
}
