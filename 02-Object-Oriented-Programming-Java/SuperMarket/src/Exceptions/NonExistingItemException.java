package Exceptions;

public class NonExistingItemException extends RuntimeException {
    public NonExistingItemException(String message) {
        super(message);
    }

    public NonExistingItemException() {
        super();
    }
}
