public class Hand {

    private static final int PAIR_VALUE = 3;
    private static final int TRIO_VALUE = 5;
    private static final int FOUR_OF_A_KIND_VALUE = 10;
    private static final int MAX_CARD_VALUE = 10;

    private int[] cards;

    public Hand(int[] receivedCards) {
        this.cards = receivedCards;
    }

    public int calculateScore() {
        int[] counts = new int[MAX_CARD_VALUE + 1];

        for (int i = 0; i < cards.length; i++) {
            counts[cards[i]]++;
        }

        int totalPoints = 0;

        for (int cardNumber = 1; cardNumber <= MAX_CARD_VALUE; cardNumber++) {
            int qty = counts[cardNumber];

            switch (qty) {
                case 4 -> totalPoints += FOUR_OF_A_KIND_VALUE * cardNumber;
                case 3 -> totalPoints += TRIO_VALUE * cardNumber;
                case 2 -> totalPoints += PAIR_VALUE * cardNumber;
                case 1 -> totalPoints += cardNumber;
            }
        }

        return totalPoints;
    }
}