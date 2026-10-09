public class ContactDoesNotExistException extends RuntimeException {
    public ContactDoesNotExistException(String message) {
        super(message);
    }

    public ContactDoesNotExistException() {
        super();
    }

}


