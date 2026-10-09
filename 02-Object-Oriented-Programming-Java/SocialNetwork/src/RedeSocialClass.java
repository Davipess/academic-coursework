public class RedeSocialClass implements RedeSocial {

    private UtilizadorColecao users;
    private final int MAX_USERS = 500;

    public RedeSocialClass() {
        this.users = new UtilizadorColecaoClass(MAX_USERS);
    }

    @Override
    public boolean hasUser(String name) {
        return this.users.hasUser(name);
    }

    @Override
    public void registerUser(String name, String email, String status) {
        Utilizador newUser = new UtilizadorClass(name, email, status);
        this.users.addUser(newUser);
    }

    @Override
    public Utilizador getUser(String name) {
        return this.users.getUser(name);
    }

    @Override
    public void addFriendship(String name1, String name2) {
        Utilizador user1 = this.users.getUser(name1);
        Utilizador user2 = this.users.getUser(name2);

        user1.addFriend(user2);
        user2.addFriend(user1);
    }

    @Override
    public boolean checkFriendship(String name1, String name2) {
        return this.users.getUser(name1).hasFriend(name2);
    }

    @Override
    public UtilizadorIterator getAllUsersIterator() {
        return this.users.getIterator();
    }

    @Override
    public int getNumberOfUsers() {
        return this.users.getSize();
    }
}