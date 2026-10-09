package Exceptions;

public class NonExistingCartException extends RuntimeException {
    public NonExistingCartException(String message) {
        super(message);
    }

    public NonExistingCartException() {
        super();
    }
}
