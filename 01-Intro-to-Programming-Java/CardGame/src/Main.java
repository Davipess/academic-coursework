import java.util.Scanner;

public class Main {
    private static final int NUM_CARDS = 5;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the number of players
        if (!scanner.hasNextInt()) {
            System.out.println("No input provided.");
            scanner.close();
            return;
        }
        int numPlayers = scanner.nextInt();

        Game game = new Game();

        // Read each player's hand and pass it to the Game instance
        for (int i = 0; i < numPlayers; i++) {
            int[] hand = new int[NUM_CARDS];
            for (int j = 0; j < NUM_CARDS; j++) {
                hand[j] = scanner.nextInt();
            }
            game.processHand(hand);
        }

        game.printResults();
        scanner.close();
    }
}