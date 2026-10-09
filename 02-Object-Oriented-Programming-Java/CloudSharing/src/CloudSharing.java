public interface CloudSharing {
    boolean hasUser(String email);

    void addBasicUser(String email);

    void addPremiumUser(String email);

    boolean hasFile(String email, String fileName);

    boolean isTooLarge(String email, int fileSize);

    void addFile(String email, String filename, int fileSize);

    boolean isPremium(String email);

    Iterator getFiles(String email);
}