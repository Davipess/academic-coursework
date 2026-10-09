public class ContactAlreadyExistsException extends RuntimeException {
    public ContactAlreadyExistsException(String message) {
        super(message);
    }

    public ContactAlreadyExistsException() {
        super();
    }

}
