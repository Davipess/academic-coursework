import java.util.Scanner;

public class Main {
    final static int MAX_JARS = 9;
    final static int MIN_JARS = 2;

    private static int readIntLn(Scanner in) {
        int value = in.nextInt();
        in.nextLine();
        return value;
    }

    private static void createJars(Scanner sc, Game jc) {
        int capacity = 1;
        while (capacity != 0) {
            capacity = readIntLn(sc);
            if (capacity > 0) {
                jc.createJar(capacity);
            }
        }
    }

    private static void runPuzzle() {
        Scanner sc = new Scanner(System.in);
        Game jc = new Game(MAX_JARS);
        createJars(sc, jc);

        sc.close();
    }

    public static void main(String[] args) {
        runPuzzle();
    }
}