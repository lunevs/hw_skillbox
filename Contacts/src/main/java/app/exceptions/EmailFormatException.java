package app.exceptions;

public class EmailFormatException extends Exception {

    public EmailFormatException() {
        super();
    }

    public EmailFormatException(String message) {
        super(message);
    }
}
