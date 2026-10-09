public class Game {

    private Jar[] jars;
    private int nextJarPos;

    public Game(int size) {
        this.jars = new Jar[size];
        this.nextJarPos = 0;
    }

    public void createJar(int capacity) {
        if (nextJarPos < jars.length) {
            jars[nextJarPos++] = new Jar(capacity);
        }
    }

    public JarIterator getIterator() {
        return new JarIterator(jars, nextJarPos);
    }
}