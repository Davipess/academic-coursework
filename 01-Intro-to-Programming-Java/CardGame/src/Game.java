public class Game {
    private static final int SINGLE_MULTIPLIER = 1;
    private static final int PAIR_MULTIPLIER = 3;
    private static final int TRIPLE_MULTIPLIER = 5;
    private static final int POKER_MULTIPLIER = 10;

    private int bestScore = -1;
    private int winnerCount = 0;

    // Evaluates a single hand, updates the best score and winner count
    public void processHand(int[] hand) {
        int score = calculateScore(hand);

        if (score > bestScore) {
            bestScore = score;
            winnerCount = 1;
        } else if (score == bestScore) {
            winnerCount++;
        }
    }

    // Calculates the score for a 5-card array
    private int calculateScore(int[] hand) {
        int score = 0;
        boolean[] counted = new boolean[hand.length]; // Tracks cards we've already scored

        for (int i = 0; i < hand.length; i++) {
            if (counted[i]) continue; // Skip if already part of a counted pair/triple/poker

            int count = 1;
            for (int j = i + 1; j < hand.length; j++) {
                if (hand[i] == hand[j]) {
                    count++;
                    counted[j] = true;
                }
            }

            switch (count) {
                case 1 -> score += hand[i] * SINGLE_MULTIPLIER;
                case 2 -> score += hand[i] * PAIR_MULTIPLIER;
                case 3 -> score += hand[i] * TRIPLE_MULTIPLIER;
                case 4 -> score += hand[i] * POKER_MULTIPLIER;
            }
        }
        return score;
    }

    public void printResults() {
        System.out.println(winnerCount + " " + bestScore);
    }
}