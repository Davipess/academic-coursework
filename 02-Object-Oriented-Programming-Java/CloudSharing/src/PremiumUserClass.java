public class PremiumUserClass extends AbstractUser implements PremiumUser {

    private static final int MAX_FILE_SIZE = 250;

    public PremiumUserClass(String email) {
        super(email);
    }

    @Override
    public boolean isTooLarge(int size) {
        return size > MAX_FILE_SIZE;
    }

    @Override
    public boolean isPremium() {
        return true;
    }
}