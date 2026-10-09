
import shape.Iterator;
import shape.Shapes;
import shape.ShapesApp;
import shape.ShapesAppClass;

import java.util.Scanner;

/**
 *
 * David Figueiredo
 * 74167
 * Main class that handles the user interface, reads console input,
 * and executes operations on the geometric shapes collection.
 */
public class Main {

    public static final String ADD = "ADD";
    public static final String LIST = "LIST";
    public static final String MOVE = "MOVE";
    public static final String MINAREA = "MINAREA";
    public static final String EXIT = "EXIT";

    public static final String TYPE_NOT_EXISTS = "Type does not exist.";
    public static final String ID_EXISTS = "Identifier already exists.";
    public static final String ID_NOT_EXISTS = "Identifier does not exist.";
    public static final String NEW_TYPE = "A new %s was added.";
    public static final String WITHOUT_SHAPES = "Without geometric shapes.";
    public static final String ALL_SHAPES = "All shapes:";
    public static final String SHAPE_MOVED = "Shape was moved.";
    public static final String EXITING = "Exiting...";

    public static void executeCommands(Scanner in, ShapesAppClass app) {
        String command = "";

        while (!command.equals(EXIT) && in.hasNext()) {
            command = in.next().trim().toUpperCase();

            switch (command) {
                case ADD -> addCMD(in, app);
                case LIST -> listCMD(in, app);
                case MOVE -> moveCMD(in, app);
                case MINAREA -> minAreaCMD(app);
                case EXIT -> System.out.println(EXITING);
            }
        }
    }

    private static void addCMD(Scanner in, ShapesAppClass app) {
        String type = in.next().toUpperCase();

        if (!ShapesApp.isValidType(type)) {
            in.nextLine();
            if (in.hasNextLine()) {
                in.nextLine();
            }
            System.out.println(TYPE_NOT_EXISTS);
        } else {
            String id = in.next();

            if (app.hasShape(id)) {
                in.nextLine();
                System.out.println(ID_EXISTS);
            } else {
                int x = in.nextInt();
                int y = in.nextInt();

                if (type.equals(ShapesApp.CIRCLE)) {
                    int radius = in.nextInt();
                    app.addCircle(id, type, x, y, radius);
                } else {
                    int height = in.nextInt();
                    int width = in.nextInt();
                    app.addRectangle(id, type, x, y, height, width);
                }

                System.out.printf(NEW_TYPE + "%n", type);
            }
        }
    }

    private static void listCMD(Scanner in, ShapesAppClass app) {
        String restOfLine = in.nextLine().trim().toUpperCase();

        if (!restOfLine.isEmpty() && !ShapesApp.isValidType(restOfLine)) {
            System.out.println(TYPE_NOT_EXISTS);
        } else {
            if (app.isEmpty()) {
                System.out.println(WITHOUT_SHAPES);
            } else {
                Iterator it = app.allShapesIterator();
                boolean foundAny = false;

                while (it.hasNext()) {
                    Shapes s = it.next();

                    if (restOfLine.isEmpty() || s.getType().equals(restOfLine)) {
                        if (!foundAny) {
                            System.out.println(ALL_SHAPES);
                            foundAny = true;
                        }
                        System.out.printf("%s (%d, %d) %s%n", s.getId(), s.getXCenter(), s.getYCenter(), s.getType());
                    }
                }

                if (!foundAny) {
                    System.out.println(WITHOUT_SHAPES);
                }
            }
        }
    }

    private static void moveCMD(Scanner in, ShapesAppClass app) {
        String id = in.next();
        int x = in.nextInt();
        int y = in.nextInt();

        if (!app.hasShape(id)) {
            System.out.println(ID_NOT_EXISTS);
        } else {
            app.move(id, x, y);
            System.out.println(SHAPE_MOVED);
        }
    }

    private static void minAreaCMD(ShapesAppClass app) {
        if (app.isEmpty()) {
            System.out.println(WITHOUT_SHAPES);
        } else {
            Shapes min = app.smallestArea();
            System.out.printf("%s (%d, %d) %s%n", min.getId(), min.getXCenter(), min.getYCenter(), min.getType());
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ShapesAppClass app = new ShapesAppClass();
        executeCommands(in, app);
        in.close();
    }
}