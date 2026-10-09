import java.util.Scanner;

public class Main {
    private static final int FIRST_LINE = 0;
    private static final int FIRST_COL = 0;

    // Fills the matrix with input values
    private static void fillMaze(Matrix maze, Scanner in) {
        int lines = maze.getLines();
        int cols = maze.getCols();

        for(int i = FIRST_LINE; i < lines; i++) {
            for(int j = FIRST_COL; j < cols; j++) {
                maze.fillPos(i, j, in.nextInt());
            }
        }
    }

    public static int readIntLn(Scanner in) {
        int val = in.nextInt();
        in.nextLine();
        return val;
    }

    // Creates the matrix with input dimensions
    public static Matrix createMatrix(Scanner in) {
        int lines = in.nextInt();
        int columns = readIntLn(in);
        return new Matrix(lines, columns);
    }

    // Prints the path and total points
    private static void printPathAndPoints(Matrix maze) {
        System.out.println(maze.getPath());
        System.out.println(maze.getSum());
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        Matrix maze = createMatrix(in);
        fillMaze(maze, in);

        maze.findPathFrom(FIRST_LINE, FIRST_COL);
        printPathAndPoints(maze);

        in.close();
    }
}