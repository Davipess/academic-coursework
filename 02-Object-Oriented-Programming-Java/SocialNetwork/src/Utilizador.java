/**
 * Interface that defines the behavior of a User in the Social Network.
 */
public interface Utilizador {

    /**
     * Retrieves the user's name.
     * @return The name of the user.
     */
    String getName();

    /**
     * Retrieves the user's email.
     * @return The email of the user.
     */
    String getEmail();

    /**
     * Retrieves the user's current status.
     * @return The status of the user.
     */
    String getStatus();

    /**
     * Updates the user's status.
     * @param status The new status to be set.
     * @pre status != null
     */
    void setStatus(String status);

    /**
     * Adds a new friend to the user's friend list.
     * @param friend The user to be added as a friend.
     * @pre friend != null
     * @pre !hasFriend(friend.getName())
     * @pre getNumberOfFriends() < 50
     */
    void addFriend(Utilizador friend);

    /**
     * Checks if the user has a specific friend.
     * @param friendName The name of the friend to check.
     * @return true if the friend exists in the list, false otherwise.
     * @pre friendName != null
     */
    boolean hasFriend(String friendName);

    /**
     * Retrieves the current number of friends the user has.
     * @return The total number of friends.
     */
    int getNumberOfFriends();

    /**
     * Returns an iterator to traverse the user's friends list.
     * @return A UtilizadorIterator object.
     */
    UtilizadorIterator getFriendsIterator();
}