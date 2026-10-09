public class FileIteratorClass implements Iterator {

    private File[] files;
    private int size;
    private int current;

    public FileIteratorClass(File[] files, int size) {
        this.files = files;
        this.size = size;
        this.current = 0;
    }

    @Override
    public boolean hasNext() {
        return current < size;
    }

    @Override
    public File next() {
        return files[current++];
    }
}