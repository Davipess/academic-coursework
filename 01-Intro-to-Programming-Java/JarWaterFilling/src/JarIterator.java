public class JarIterator {
    private Jar[] jars;
    private int size;
    private int next;

    public JarIterator(Jar[] jars, int size) {
        this.jars = jars;
        this.size = size;
        this.next = 0;
    }

    public boolean hasNext() {
        boolean hasMore = false;

        if (next < size) {
            hasMore = true;
        }

        return hasMore;
    }

    public Jar next() {
        Jar current = null;

        if (hasNext()) {
            current = jars[next++];
        }

        return current;
    }
}