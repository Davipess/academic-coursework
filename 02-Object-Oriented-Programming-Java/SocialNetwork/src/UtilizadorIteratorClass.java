public class UtilizadorIteratorClass implements UtilizadorIterator {

    private Utilizador[] users;
    private int counter;
    private int current;

    public UtilizadorIteratorClass(Utilizador[] users, int counter) {
        this.users = users;
        this.counter = counter;
        this.current = 0;
    }

    @Override
    public void init() {
        this.current = 0;
    }

    @Override
    public boolean hasNext() {
        return current < counter;
    }

    @Override
    public Utilizador next() {
        Utilizador result = null;

        if (current < counter) {
            result = users[current];
            current++;
        }

        return result;
    }
}