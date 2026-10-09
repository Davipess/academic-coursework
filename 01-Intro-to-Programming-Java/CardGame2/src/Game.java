import java.util.Scanner;

public class Game {
    private static final int DECK_SIZE = 5;
    private Scanner in;

    public Game() {
        this.in = new Scanner(System.in);
    }

    public void startGame() {
        if (in.hasNextInt()) {
            int numPlayers = in.nextInt();

            int numWinners = 0;
            int maxPoints = -1;

            for (int i = 0; i < numPlayers; i++) {
                int[] cards = new int[DECK_SIZE];

                for (int k = 0; k < DECK_SIZE; k++) {
                    cards[k] = in.nextInt();
                }

                Hand playerHand = new Hand(cards);
                int points = playerHand.calculateScore();

                // Determines the mountain king
                if (points > maxPoints) {
                    maxPoints = points;
                    numWinners = 1;
                } else if (points == maxPoints) {
                    numWinners++;
                }
            }

            System.out.println(numWinners + " " + maxPoints);
        }

        in.close();
    }
}