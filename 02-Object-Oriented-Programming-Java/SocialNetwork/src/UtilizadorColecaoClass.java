public class UtilizadorColecaoClass implements UtilizadorColecao {

    private Utilizador[] users;
    private int size;
    private int limit;

    public UtilizadorColecaoClass(int limit) {
        this.limit = limit;
        this.users = new Utilizador[limit];
        this.size = 0;
    }

    @Override
    public boolean hasUser(String name) {
        boolean found = false;
        for (int i = 0; i < size && !found; i++) {
            if (users[i].getName().equals(name)) {
                found = true;
            }
        }
        return found;
    }

    @Override
    public void addUser(Utilizador user) {
        if (size < limit) {
            users[size++] = user;
        }
    }

    @Override
    public Utilizador getUser(String name) {
        Utilizador result = null;
        boolean found = false;
        for (int i = 0; i < size && !found; i++) {
            if (users[i].getName().equals(name)) {
                result = users[i];
                found = true;
            }
        }
        return result;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public UtilizadorIterator getIterator() {
        return new UtilizadorIteratorClass(users, size);
    }
}