public class BasicUserClass extends AbstractUser implements BasicUser {

    private static final int MAX_FILE_SIZE = 25; // Limite de 25 MB

    public BasicUserClass(String email) {
        super(email);
    }

    @Override
    public boolean isTooLarge(int size) {
        return size > MAX_FILE_SIZE;
    }

    @Override
    public boolean isPremium() {
        return false;
    }
}