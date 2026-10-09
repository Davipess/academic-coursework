public class CloudSharingClass implements CloudSharing {

    private static final int MAX_USERS = 500;
    private User[] users;
    private int userCount;

    public CloudSharingClass() {
        this.users = new User[MAX_USERS];
        this.userCount = 0;
    }

    private User getUser(String email) {
        for (int i = 0; i < userCount; i++) {
            if (users[i].getEmail().equals(email)) {
                return users[i];
            }
        }
        return null;
    }

    @Override
    public boolean hasUser(String email) {
        return getUser(email) != null;
    }

    @Override
    public void addBasicUser(String email) {
        if (userCount < MAX_USERS) {
            users[userCount++] = new BasicUserClass(email);
        }
    }

    @Override
    public void addPremiumUser(String email) {
        if (userCount < MAX_USERS) {
            users[userCount++] = new PremiumUserClass(email);
        }
    }

    @Override
    public boolean hasFile(String email, String fileName) {
        User u = getUser(email);
        return u != null && u.hasFile(fileName);
    }

    @Override
    public boolean isTooLarge(String email, int fileSize) {
        User u = getUser(email);
        return u != null && u.isTooLarge(fileSize);
    }

    @Override
    public void addFile(String email, String filename, int fileSize) {
        User u = getUser(email);
        if (u != null) {
            u.addFile(filename, fileSize);
        }
    }

    @Override
    public boolean isPremium(String email) {
        User u = getUser(email);
        return u != null && u.isPremium();
    }

    @Override
    public Iterator getFiles(String email) {
        User u = getUser(email);
        return (u != null) ? u.getFilesIterator() : null;
    }
}