/**
 * Interface that defines the core functionalities of the Social Network.
 */
public interface RedeSocial {

    /**
     * Checks if a user is registered in the social network.
     * @param name The name of the user to check.
     * @return true if registered, false otherwise.
     * @pre name != null
     */
    boolean hasUser(String name);

    /**
     * Registers a new user in the network.
     * @param name The name of the user.
     * @param email The email of the user.
     * @param status The initial status of the user.
     * @pre name != null && email != null && status != null
     * @pre !hasUser(name)
     * @pre getNumberOfUsers() < 500
     */
    void registerUser(String name, String email, String status);

    /**
     * Retrieves a user by their name.
     * @param name The name of the user.
     * @return The Utilizador object, or null if not found.
     * @pre hasUser(name) == true
     */
    Utilizador getUser(String name);

    /**
     * Creates a symmetric friendship between two users.
     * @param name1 The name of the first user.
     * @param name2 The name of the second user.
     * @pre hasUser(name1) && hasUser(name2)
     * @pre !name1.equals(name2)
     * @pre !checkFriendship(name1, name2)
     */
    void addFriendship(String name1, String name2);

    /**
     * Checks if a friendship exists between two users.
     * @param name1 The name of the first user.
     * @param name2 The name of the second user.
     * @return true if they are friends, false otherwise.
     * @pre hasUser(name1) && hasUser(name2)
     */
    boolean checkFriendship(String name1, String name2);

    /**
     * Returns an iterator for all registered users in the network.
     * @return A UtilizadorIterator object.
     */
    UtilizadorIterator getAllUsersIterator();

    /**
     * Retrieves the total number of registered users.
     * @return The number of users.
     */
    int getNumberOfUsers();
}