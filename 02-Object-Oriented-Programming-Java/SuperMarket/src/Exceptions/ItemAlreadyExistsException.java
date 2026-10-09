package Exceptions;

public class ItemAlreadyExistsException extends RuntimeException {
    public ItemAlreadyExistsException(String message) {
        super(message);
    }

    public ItemAlreadyExistsException() {
        super();
    }
}
