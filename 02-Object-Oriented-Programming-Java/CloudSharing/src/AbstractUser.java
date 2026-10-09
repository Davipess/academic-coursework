abstract class AbstractUser implements User {

    private static final int MAX_FILES = 100;
    private String email;
    private File[] files;
    private int fileCount;

    protected AbstractUser(String email) {
        this.email = email;
        this.files = new FileClass[MAX_FILES];
        this.fileCount = 0;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public boolean hasFile(String filename) {
        for (int i = 0; i < fileCount; i++) {
            if (files[i].getName().equals(filename)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void addFile(String filename, int size) {
        if (fileCount < MAX_FILES) {
            files[fileCount++] = new FileClass(filename, size);
        }
    }

    @Override
    public Iterator getFilesIterator() {
        return new FileIteratorClass(files, fileCount);
    }

    @Override
    public abstract boolean isTooLarge(int size);

    @Override
    public abstract boolean isPremium();
}