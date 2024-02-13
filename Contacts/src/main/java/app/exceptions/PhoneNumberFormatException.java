package app.exceptions;

public class PhoneNumberFormatException extends Exception {
    public PhoneNumberFormatException() {
        super();
    }

    public PhoneNumberFormatException(String message) {
        super(message);
    }
}
