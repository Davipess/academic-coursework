package Exceptions;

public class ItemNotInCartException extends RuntimeException {
    public ItemNotInCartException(String message) {
        super(message);
    }

    public ItemNotInCartException() {
        super();
    }
}
