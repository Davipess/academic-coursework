public class WordsInBook {

    private String word;
    private int occurrences;

    public WordsInBook(String word) {
        this.word = word;
        this.occurrences = 1;
    }

    public String getWord() {
        return word;
    }

    public int getOccurrences() {
        return occurrences;
    }

    public void increment() {
        occurrences++;
    }
}