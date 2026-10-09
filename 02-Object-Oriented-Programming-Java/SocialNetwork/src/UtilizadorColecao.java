/**
 * Interface that represents a collection of users in the Social Network.
 * Responsible for managing the array of users across classes.
 */
public interface UtilizadorColecao {

    /**
     * Checks if a user with the given name exists in the collection.
     * @param name The name of the user to search for.
     * @return true if the user exists, false otherwise.
     * @pre name != null
     */
    boolean hasUser(String name);

    /**
     * Adds a new user to the collection.
     * @param user The Utilizador object to be added.
     * @pre user != null
     * @pre !hasUser(user.getName())
     * @pre getSize() < 500
     */
    void addUser(Utilizador user);

    /**
     * Retrieves a user from the collection by their name.
     * @param name The name of the user to retrieve.
     * @return The Utilizador object, or null if not found.
     * @pre hasUser(name) == true
     */
    Utilizador getUser(String name);

    /**
     * Retrieves the current number of users in the collection.
     * @return The number of stored users.
     */
    int getSize();

    /**
     * Returns an iterator to traverse the collection of users.
     * @return A UtilizadorIterator object.
     */
    UtilizadorIterator getIterator();
}