package Exceptions;

public class CartAlreadyExistsException extends RuntimeException {
    public CartAlreadyExistsException(String message) {
        super(message);
    }

    public CartAlreadyExistsException() {
        super();
    }
}
