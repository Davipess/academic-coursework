public class NeighborIterator {

    private int[][] neighbors;
    private int size;
    private int nextIndex;

    public NeighborIterator(int[][] neighbors, int size) {
        this.neighbors = neighbors;
        this.size = size;
        this.nextIndex = 0;
    }

    public boolean hasNext() {
        boolean hasMore = false;

        if (nextIndex < size) {
            hasMore = true;
        }

        return hasMore;
    }

    public int[] next() {
        int[] current = null;

        if (hasNext()) {
            current = neighbors[nextIndex++];
        }

        return current;
    }
}