public class InvalidPhoneNumberException extends RuntimeException {
    public InvalidPhoneNumberException(String message) {
        super(message);
    }

    public InvalidPhoneNumberException() {
        super();
    }
}
