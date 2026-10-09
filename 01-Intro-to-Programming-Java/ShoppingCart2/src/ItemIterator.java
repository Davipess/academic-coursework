public class ItemIterator {

    private Item[] items;
    private int size;
    private int nextIndex;

    public ItemIterator(Item[] items, int size) {
        this.items = items;
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

    public Item next() {
        Item current = null;

        if (hasNext()) {
            current = items[nextIndex++];
        }

        return current;
    }
}