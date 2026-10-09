import java.util.Scanner;

public class Main {

    private static final String CMD_REGISTERED = "REGISTERED";
    private static final String CMD_REGISTER = "REGISTER";
    private static final String CMD_CHECKFRIENDSHIP = "CHECKFRIENDSHIP";
    private static final String CMD_ADDFRIEND = "ADDFRIEND";
    private static final String CMD_FRIENDS = "FRIENDS";
    private static final String CMD_NEWSTATUS = "NEWSTATUS";
    private static final String CMD_CHECKSTATUS = "CHECKSTATUS";
    private static final String CMD_USERS = "USERS";
    private static final String CMD_QUIT = "QUIT";

    public static final String ALREADY_REGISTERED = "Already registered.";
    public static final String NOT_REGISTERED = "Not registered.";
    public static final String SUCCESSFULLY_REGISTERED = "Successfully registered.";
    public static final String FRIENDSHIP_EXISTS = "Friendship already exists.";
    public static final String FRIENDSHIP_NOT_EXISTS = "Non-existent friendship.";
    public static final String CREATE_FRIENDSHIP = "Friendship created.";
    public static final String ONLY_PERSON_FRIENDSHIP = "Invalid friendship.";
    public static final String NO_REGISTERED_FRIENDS = "No registered friends.";
    public static final String FRIENDS_LIST = "Friends list:";
    public static final String UPDATED_STATUS = "Status updated.";
    public static final String EMPTY_SOCIAL = "Empty social network.";
    public static final String REGISTERED_USERS_LIST = "List of registered users:";
    public static final String BYE = "Bye.";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        RedeSocial rede = new RedeSocialClass();
        processCommands(in, rede);
    }

    public static void processCommands(Scanner in, RedeSocial rede) {
        String command = "";
        boolean running = true;

        while (running) {
            if (in.hasNextLine()) {
                command = in.nextLine().trim().toUpperCase();
            }

            if (!command.isEmpty()) {
                switch (command) {
                    case CMD_REGISTERED -> processRegistered(in, rede);
                    case CMD_REGISTER -> processRegister(in, rede);
                    case CMD_CHECKFRIENDSHIP -> processCheckFriendship(in, rede);
                    case CMD_ADDFRIEND -> processAddFriend(in, rede);
                    case CMD_FRIENDS -> processFriends(in, rede);
                    case CMD_NEWSTATUS -> processNewStatus(in, rede);
                    case CMD_CHECKSTATUS -> processCheckStatus(in, rede);
                    case CMD_USERS -> processUsers(rede);
                    case CMD_QUIT -> {
                        System.out.println(BYE);
                        running = false;
                    }
                    default -> {
                    }
                }
            } else {
                running = false;
            }
        }
    }

    private static void processRegistered(Scanner in, RedeSocial rede) {
        String name = in.nextLine().trim();

        if (rede.hasUser(name)) {
            System.out.println(ALREADY_REGISTERED);
        } else {
            System.out.println(NOT_REGISTERED);
        }
    }

    private static void processRegister(Scanner in, RedeSocial rede) {
        String name = in.nextLine().trim();
        String email = in.nextLine().trim();
        String status = in.nextLine().trim();

        if (rede.hasUser(name)) {
            System.out.println(ALREADY_REGISTERED);
        } else {
            rede.registerUser(name, email, status);
            System.out.println(SUCCESSFULLY_REGISTERED);
        }
    }

    private static void processCheckFriendship(Scanner in, RedeSocial rede) {
        String name1 = in.nextLine().trim();
        String name2 = in.nextLine().trim();

        if (rede.hasUser(name1) && rede.hasUser(name2) && rede.checkFriendship(name1, name2)) {
            System.out.println(FRIENDSHIP_EXISTS);
        } else {
            System.out.println(FRIENDSHIP_NOT_EXISTS);
        }
    }

    private static void processAddFriend(Scanner in, RedeSocial rede) {
        String name1 = in.nextLine().trim();
        String name2 = in.nextLine().trim();

        if (name1.equals(name2)) {
            System.out.println(ONLY_PERSON_FRIENDSHIP);
        } else if (!rede.hasUser(name1) || !rede.hasUser(name2)) {
            System.out.println(NOT_REGISTERED);
        } else if (rede.checkFriendship(name1, name2)) {
            System.out.println(FRIENDSHIP_EXISTS);
        } else {
            rede.addFriendship(name1, name2);
            System.out.println(CREATE_FRIENDSHIP);
        }
    }

    private static void processFriends(Scanner in, RedeSocial rede) {
        String name = in.nextLine().trim();

        if (!rede.hasUser(name)) {
            System.out.println(NOT_REGISTERED);
        } else {
            Utilizador user = rede.getUser(name);

            if (user.getNumberOfFriends() == 0) {
                System.out.println(NO_REGISTERED_FRIENDS);
            } else {
                System.out.println(FRIENDS_LIST);
                UtilizadorIterator it = user.getFriendsIterator();
                it.init();
                while (it.hasNext()) {
                    Utilizador friend = it.next();
                    System.out.println(friend.getName() + "; " + friend.getEmail());
                }
            }
        }
    }

    private static void processNewStatus(Scanner in, RedeSocial rede) {
        String name = in.nextLine().trim();
        String newStatus = in.nextLine().trim();

        if (!rede.hasUser(name)) {
            System.out.println(NOT_REGISTERED);
        } else {
            rede.getUser(name).setStatus(newStatus);
            System.out.println(UPDATED_STATUS);
        }
    }

    private static void processCheckStatus(Scanner in, RedeSocial rede) {
        String name = in.nextLine().trim();

        if (!rede.hasUser(name)) {
            System.out.println(NOT_REGISTERED);
        } else {
            System.out.println(rede.getUser(name).getStatus());
        }
    }

    private static void processUsers(RedeSocial rede) {
        if (rede.getNumberOfUsers() == 0) {
            System.out.println(EMPTY_SOCIAL);
        } else {
            System.out.println(REGISTERED_USERS_LIST);
            UtilizadorIterator it = rede.getAllUsersIterator();
            it.init();
            while (it.hasNext()) {
                Utilizador user = it.next();
                System.out.println(user.getName() + "; " + user.getEmail());
            }
        }
    }
}