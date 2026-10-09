public class BookWordIterator {
    private WordsInBook[] words;
    private int size;
    private int sup;
    private int inf;
    private int nextIndex;

    public BookWordIterator(WordsInBook[] words, int size, int inf, int sup) {
        this.words = words;
        this.size = size;
        this.inf = inf;
        this.sup = sup;
        this.nextIndex = 0;

        // Fast-forward to the first valid word
        advanceToNextValid();
    }

    private void advanceToNextValid() {
        boolean foundValid = false;

        while (nextIndex < size && !foundValid) {
            int count = words[nextIndex].getOccurrences();

            if (count >= inf && count <= sup) {
                foundValid = true;
            } else {
                nextIndex++;
            }
        }
    }

    public boolean hasNext() {
        boolean hasMore = false;

        if (nextIndex < size) {
            hasMore = true;
        }

        return hasMore;
    }

    public WordsInBook next() {
        WordsInBook current = null;

        if (hasNext()) {
            current = words[nextIndex++];
            // Find the next valid word for the upcoming iteration
            advanceToNextValid();
        }

        return current;
    }
}