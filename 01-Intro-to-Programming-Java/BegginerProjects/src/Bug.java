import java.util.Scanner;

public class Bug {
    private static int[] bugPositions;

    private static void handleJumps(Scanner in, int jumps) {
        for (int i = 0; i < jumps; i++) {
            int position = in.nextInt();
            int size = in.nextInt();
            jump(position, size);
        }
    }

    private static void jump(int position, int size) {
        if (position >= 0 && position < bugPositions.length) {
            bugPositions[position] += size;
        }
    }

    private static void printPositions() {
        for(int j = 0; j < bugPositions.length; j++) {
            System.out.println(bugPositions[j]);
        }
    }

    private static void readInitialPositions(Scanner in) {
        for (int k = 0; k < bugPositions.length; k++) {
            bugPositions[k] = in.nextInt();
        }
    }

    private static int initState(Scanner in) {
        int lines = in.nextInt();
        int bugs = in.nextInt();
        int jumps = in.nextInt();

        bugPositions = new int[bugs];
        return jumps;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        if (in.hasNextInt()) {
            int jumps = initState(in);
            readInitialPositions(in);
            handleJumps(in, jumps);
            printPositions();
        }

        in.close();
    }
}