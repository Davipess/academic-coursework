public class UtilizadorClass implements Utilizador {

    private String name;
    private String email;
    private String status;
    private UtilizadorColecao friends;
    private static final int MAX_FRIENDS = 50;

    public UtilizadorClass(String name, String email, String status) {
        this.name = name;
        this.email = email;
        this.status = status;
        this.friends = new UtilizadorColecaoClass(MAX_FRIENDS);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public void addFriend(Utilizador friend) {
        friends.addUser(friend);
    }

    @Override
    public boolean hasFriend(String friendName) {
        return friends.hasUser(friendName);
    }

    @Override
    public int getNumberOfFriends() {
        return friends.getSize();
    }

    @Override
    public UtilizadorIterator getFriendsIterator() {
        return friends.getIterator();
    }
}