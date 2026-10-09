public class BookDigest {

    private static final int MAX_WORDS = 10000;
    private WordsInBook[] words;
    private int counter;

    public BookDigest() {
        this.words = new WordsInBook[MAX_WORDS];
        this.counter = 0;
    }

    public void addOrInc(String word) {
        int index = findWord(word);

        if (index != -1) {
            words[index].increment();
        } else if (counter < words.length) {
            words[counter++] = new WordsInBook(word);
        }
    }

    private int findWord(String word) {
        int foundIndex = -1;
        boolean isFound = false;

        for (int i = 0; i < counter && !isFound; i++) {
            if (words[i].getWord().equals(word)) {
                foundIndex = i;
                isFound = true;
            }
        }

        return foundIndex;
    }

    public int getNmbWords() {
        return counter;
    }

    public BookWordIterator createIter(int inf, int sup) {
        return new BookWordIterator(words, counter, inf, sup);
    }
}