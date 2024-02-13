package app.exceptions;

public class MissedParametersException extends Exception {
    public MissedParametersException() {
        super();
    }

    public MissedParametersException(String message) {
        super(message);
    }
}
