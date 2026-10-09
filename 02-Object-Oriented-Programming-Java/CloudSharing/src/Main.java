import java.util.Scanner;

public class Main {

    private static final String CMD_ADD = "ADD";
    private static final String CMD_UPLOAD = "UPLOAD";
    private static final String CMD_LIST = "LIST";
    private static final String CMD_EXIT = "EXIT";

    private static final String USER_EXISTS = "User already exists.";
    private static final String USER_NOT_FOUND = "User does not exist.";
    private static final String USER_ADDED = "User added successfully.";
    private static final String FILE_EXISTS = "File already exists.";
    private static final String FILE_TOO_LARGE = "File exceeds the allowed size limit.";
    private static final String FILE_UPLOADED = "File uploaded successfully.";
    private static final String EXITING = "Exiting...";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        CloudSharing app = new CloudSharingClass();
        executeCommands(in, app);
        in.close();
    }

    private static void executeCommands(Scanner in, CloudSharing app) {
        String command = "";

        while (in.hasNext() && !command.equals(CMD_EXIT)) {
            command = in.next().trim().toUpperCase();

            switch (command) {
                case CMD_ADD -> addCMD(in, app);
                case CMD_UPLOAD -> uploadCMD(in, app);
                case CMD_LIST -> listCMD(in, app);
                case CMD_EXIT -> System.out.println(EXITING);
            }
        }
    }

    private static void addCMD(Scanner in, CloudSharing app) {
        String type = in.next().toUpperCase();
        String email = in.next();

        if (app.hasUser(email)) {
            System.out.println(USER_EXISTS);
        } else {
            if (type.equals("BASIC")) {
                app.addBasicUser(email);
            } else if (type.equals("PREMIUM")) {
                app.addPremiumUser(email);
            }
            System.out.println(USER_ADDED);
        }
    }

    private static void uploadCMD(Scanner in, CloudSharing app) {
        String email = in.next();
        String filename = in.next();
        int size = in.nextInt();

        if (!app.hasUser(email)) {
            System.out.println(USER_NOT_FOUND);
        } else if (app.hasFile(email, filename)) {
            System.out.println(FILE_EXISTS);
        } else if (app.isTooLarge(email, size)) {
            System.out.println(FILE_TOO_LARGE);
        } else {
            app.addFile(email, filename, size);
            System.out.println(FILE_UPLOADED);
        }
    }

    private static void listCMD(Scanner in, CloudSharing app) {
        String email = in.next();

        if (!app.hasUser(email)) {
            System.out.println(USER_NOT_FOUND);
        } else {
            Iterator it = app.getFiles(email);
            System.out.println("Files for " + email + ":");
            boolean hasFiles = false;

            while (it.hasNext()) {
                File f = it.next();
                System.out.println("- " + f.getName() + " (" + f.getSize() + " MB)");
                hasFiles = true;
            }

            if (!hasFiles) {
                System.out.println("No files uploaded.");
            }
        }
    }
}