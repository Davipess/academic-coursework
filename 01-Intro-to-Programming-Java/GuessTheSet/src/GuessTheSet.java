public class GuessTheSet {

    private int sizeOfSet;
    private int[] numbers;
    private boolean[] found;
    private int counter;
    private int guesses;
    private int correctGuesses;

    public GuessTheSet(int sizeOfSet) {
        this.sizeOfSet = sizeOfSet;
        this.numbers = new int[sizeOfSet];
        this.found = new boolean[sizeOfSet];
        this.counter = 0;
        this.guesses = 0;
        this.correctGuesses = 0;
    }

    public void addToSet(int number) {
        if (counter < sizeOfSet) {
            numbers[counter++] = number;
        }
    }

    public int guess(int num) {
        guesses++;
        int index = getIndexOf(num);
        int score = -2;

        if (index != -1 && !found[index]) {
            found[index] = true;
            correctGuesses++;
            score = 10;
        }

        return score;
    }

    public boolean allFound() {
        return correctGuesses == sizeOfSet;
    }

    public int getFinalScore() {
        return (sizeOfSet * 10) - ((guesses - sizeOfSet) * 2);
    }

    private int getIndexOf(int number) {
        int foundIndex = -1;
        boolean isFound = false;

        for (int i = 0; i < sizeOfSet && !isFound; i++) {
            if (numbers[i] == number) {
                foundIndex = i;
                isFound = true;
            }
        }

        return foundIndex;
    }
}