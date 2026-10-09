public interface User {
    String getEmail();

    boolean hasFile(String filename);

    void addFile(String filename, int size);

    boolean isTooLarge(int size);

    boolean isPremium();

    Iterator getFilesIterator();
}