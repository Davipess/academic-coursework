public class Matrix {

    private int[][] matrix;
    private int numLines;
    private int numColumns;
    private String path;
    private int sum;
    private int targetNumber;

    public Matrix(int numLines, int numColumns) {
        this.numLines = numLines;
        this.numColumns = numColumns;
        this.matrix = new int[numLines][numColumns];
        this.path = "";
        this.sum = 0;
    }

    public void fillPos(int line, int column, int value) {
        matrix[line][column] = value;
    }

    public int getLines() {
        return numLines;
    }

    public int getCols() {
        return numColumns;
    }

    public String getPath() {
        return path;
    }

    public int getSum() {
        return sum;
    }

    // Checks if a position is valid for the next move
    private boolean isValidMove(int line, int column) {
        boolean isValid = true;

        if (line < 0 || line >= numLines || column < 0 || column >= numColumns) {
            isValid = false;
        } else if (matrix[line][column] != targetNumber) {
            isValid = false;
        }

        return isValid;
    }

    // Prepares and starts the pathfinding
    public void findPathFrom(int line, int column) {
        targetNumber = matrix[line][column];
        sum = targetNumber;
        matrix[line][column] = 0; // Marks starting position as visited
        this.path = findPath(line, column);
    }

    // Helper method to find the valid neighbor
    private NeighborIterator getValidNeighbor(int line, int column) {
        int[][] neighbors = new int[1][2];
        int size = 0;

        // Tries UP (C)
        if (isValidMove(line - 1, column)) {
            neighbors[0] = new int[]{line - 1, column};
            size = 1;
        }
        // Tries RIGHT (D)
        else if (isValidMove(line, column + 1)) {
            neighbors[0] = new int[]{line, column + 1};
            size = 1;
        }
        // Tries DOWN (B)
        else if (isValidMove(line + 1, column)) {
            neighbors[0] = new int[]{line + 1, column};
            size = 1;
        }
        // Tries LEFT (E)
        else if (isValidMove(line, column - 1)) {
            neighbors[0] = new int[]{line, column - 1};
            size = 1;
        }

        return new NeighborIterator(neighbors, size);
    }

    private String findPath(int line, int column) {
        String currentPath = "";
        NeighborIterator it = getValidNeighbor(line, column);

        if (it.hasNext()) {
            int[] nextPos = it.next();
            int nextLine = nextPos[0];
            int nextCol = nextPos[1];

            // Updates state
            sum += targetNumber;
            matrix[nextLine][nextCol] = 0;

            // 1=Up, 2=Right, 3=Down, 4=Left
            int moveCode = 0;
            if (nextLine < line) {
                moveCode = 1;
            } else if (nextCol > column) {
                moveCode = 2;
            } else if (nextLine > line) {
                moveCode = 3;
            } else {
                moveCode = 4;
            }

            char move = switch (moveCode) {
                case 1 -> 'C';
                case 2 -> 'D';
                case 3 -> 'B';
                case 4 -> 'E';
                default -> ' ';
            };

            currentPath = move + findPath(nextLine, nextCol);
        }

        return currentPath;
    }
}