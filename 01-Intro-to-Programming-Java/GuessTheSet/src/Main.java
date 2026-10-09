import java.util.Scanner;

public class Main {
    final static String GUESS = "guess";
    final static String QUIT = "quit";
    final static String VICTORY_MESSAGE = "Congratulations!\nFinal score %d points.\n";
    final static String SCORE_MESSAGE = "Score for this guess: %d\n";
    final static String BYE_MESSAGE = "Goodbye.\nPlease try again.";

    private static void quitCommand() {
        System.out.println(BYE_MESSAGE);
    }

    private static boolean guessCommand(Scanner in, GuessTheSet game) {
        boolean shouldContinue = true;
        int guess = in.nextInt();
        int score = game.guess(guess);

        System.out.printf(SCORE_MESSAGE, score);

        if (game.allFound()) {
            System.out.printf(VICTORY_MESSAGE, game.getFinalScore());
            shouldContinue = false;
        }

        return shouldContinue;
    }

    private static void executeCommands(Scanner in, GuessTheSet game) {
        boolean gameIsRunning = true;

        do {
            String command = in.next();
            switch (command) {
                case GUESS -> {
                    gameIsRunning = guessCommand(in, game);
                }
                case QUIT -> {
                    quitCommand();
                    gameIsRunning = false;
                }
                default -> System.out.println("Unknown command.");
            }
        } while (gameIsRunning);
    }

    private static GuessTheSet createGame(Scanner in) {
        int sizeOfSet = in.nextInt();
        GuessTheSet game = new GuessTheSet(sizeOfSet);

        for (int i = 0; i < sizeOfSet; i++) {
            int number = in.nextInt();
            game.addToSet(number);
        }

        return game;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        GuessTheSet game = createGame(in);
        executeCommands(in, game);

        in.close();
    }
}