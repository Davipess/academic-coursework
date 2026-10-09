import java.util.Scanner;

/**
 * Please identify yourself here:
 * Name: David Figueiredo
 * Student number: 74167
 * <p>
 * Object-Oriented Programming Assignment 1: Contact Book Application
 * This is the starter code for the contact book application.
 * You should complete it by implementing the additional functionality,
 * as described in the assignment instructions available in Moodle.
 * Contact book application that allows users to manage their
 * contacts through a command-line interface.
 * The application supports adding, removing, retrieving, and
 * updating contacts, as well as listing all contacts and quitting
 * the application.
 * The main class contains constants for commands and messages, and
 * methods for handling each command.
 * The application uses a ContactBook to store contacts and a
 * ContactIterator to iterate through them.
 */
public class Main {
    // Constants defining the commands
    private static final String ADD_CONTACT = "AC";
    private static final String REMOVE_CONTACT = "RC";
    private static final String GET_PHONE = "GP";
    private static final String GET_EMAIL = "GE";
    private static final String SET_PHONE = "SP";
    private static final String SET_EMAIL = "SE";
    private static final String GET_NAME_WITH_NUMBER = "GN";
    private static final String CHECK_CONTACTS = "EP";
    private static final String LIST_CONTACTS = "LC";
    private static final String QUIT = "Q";

    // Constants defining messages for the user
    private static final String CONTACT_EXISTS = "Contact already exists.";
    private static final String NAME_NOT_EXIST = "Contact does not exist.";
    private static final String PHONE_NOT_EXIST = "Phone number does not exist.";
    private static final String SHARE_NAME = "There are contacts that share phone numbers.";
    private static final String DIFF_NAME = "All contacts have different phone numbers.";
    private static final String CONTACT_ADDED = "Contact added.";
    private static final String CONTACT_REMOVED = "Contact removed.";
    private static final String CONTACT_UPDATED = "Contact updated.";
    private static final String BOOK_EMPTY = "Contact book empty.";
    private static final String QUIT_MSG = "Goodbye!";
    private static final String COMMAND_ERROR = "Unknown command.";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ContactBook cBook = new ContactBook();
        String comm;

        do {
            comm = getCommand(in);
            switch (comm) {
                case ADD_CONTACT -> addContact(in, cBook);
                case REMOVE_CONTACT -> deleteContact(in, cBook);
                case GET_PHONE -> getPhone(in, cBook);
                case GET_EMAIL -> getEmail(in, cBook);
                case SET_PHONE -> setPhone(in, cBook);
                case SET_EMAIL -> setEmail(in, cBook);
                case GET_NAME_WITH_NUMBER -> getNameWithNumber(in, cBook);
                case CHECK_CONTACTS -> checkContacs(in, cBook);
                case LIST_CONTACTS -> listAllContacts(cBook);
                case QUIT -> System.out.println(QUIT_MSG);
                default -> System.out.println(COMMAND_ERROR);
            }
        } while (!comm.equals(QUIT));
        in.close();
    }

    /**
     * Reads a command from the user.
     *
     * @param in Scanner for reading user input
     * @return the command entered by the user
     */
    private static String getCommand(Scanner in) {
        String input;

        input = in.nextLine().toUpperCase();
        return input;
    }

    /**
     * Adds a new contact to the contact book based on user input.
     *
     * @param in    Scanner for reading user input
     * @param cBook the ContactBook to add the contact to
     */
    private static void addContact(Scanner in, ContactBook cBook) {
        String name, email;
        int phone;

        name = in.nextLine();
        phone = in.nextInt();
        in.nextLine();
        email = in.nextLine();
        if (!cBook.hasContact(name)) {
            cBook.addContact(name, phone, email);
            System.out.println(CONTACT_ADDED);
        } else System.out.println(CONTACT_EXISTS);
    }

    /**
     * Deletes a contact from the contact book based on user input.
     *
     * @param in    Scanner for reading user input
     * @param cBook the ContactBook to remove the contact from
     */
    private static void deleteContact(Scanner in, ContactBook cBook) {
        String name;
        name = in.nextLine();
        if (cBook.hasContact(name)) {
            cBook.deleteContact(name);
            System.out.println(CONTACT_REMOVED);
        } else System.out.println(NAME_NOT_EXIST);
    }

    /**
     * Gets the phone number of a contact from the contact book
     * based on user input.
     *
     * @param in    Scanner for reading user input
     * @param cBook the ContactBook to consult
     */
    private static void getPhone(Scanner in, ContactBook cBook) {
        String name;
        name = in.nextLine();
        if (cBook.hasContact(name)) {
            System.out.println(cBook.getPhone(name));
        } else System.out.println(NAME_NOT_EXIST);
    }

    /**
     * Gets the email of a contact from the contact book based on user input.
     *
     * @param in    Scanner for reading user input
     * @param cBook the ContactBook to consult
     */
    private static void getEmail(Scanner in, ContactBook cBook) {
        String name;
        name = in.nextLine();
        if (cBook.hasContact(name)) {
            System.out.println(cBook.getEmail(name));
        } else System.out.println(NAME_NOT_EXIST);
    }

    /**
     * Changes the phone of a contact from the contact book based on user input.
     *
     * @param in    Scanner for reading user input
     * @param cBook the ContactBook to update
     */
    private static void setPhone(Scanner in, ContactBook cBook) {
        String name;
        int phone;
        name = in.nextLine();
        phone = in.nextInt();
        in.nextLine();
        if (cBook.hasContact(name)) {
            cBook.setPhone(name, phone);
            System.out.println(CONTACT_UPDATED);
        } else System.out.println(NAME_NOT_EXIST);
    }

    /**
     * Changes the email from the contact book based on user input.
     *
     * @param in    Scanner for reading user input
     * @param cBook the ContactBook to update
     */
    private static void setEmail(Scanner in, ContactBook cBook) {
        String name;
        String email;
        name = in.nextLine();
        email = in.nextLine();
        if (cBook.hasContact(name)) {
            cBook.setEmail(name, email);
            System.out.println(CONTACT_UPDATED);
        } else System.out.println(NAME_NOT_EXIST);
    }

    /**
     * Gets the name of a contact based on the phone number.
     *
     * @param in    Scanner for reading user input
     * @param cBook the ContactBook to update
     */
    private static void getNameWithNumber(Scanner in, ContactBook cBook) {
        int phone;
        phone = in.nextInt();
        in.nextLine();

        if (!cBook.hasPhone(phone)) {
            System.out.println(PHONE_NOT_EXIST);
        } else {
            String name = cBook.getNameWithNumber(phone);
            System.out.println(name);
        }
    }

    /**
     * Checks if there are contacts with the same phone number.
     *
     * @param in    Scanner for reading user input
     * @param cBook the ContactBook to update
     */
    private static void checkContacs(Scanner in, ContactBook cBook) {
        boolean comp = cBook.contactsComparer();
        if (comp) {
            System.out.println(SHARE_NAME);
        } else {
            System.out.println(DIFF_NAME);
        }
    }


    /**
     * Lists all contacts from the contact book.
     *
     * @param cBook the ContactBook to list
     */
    private static void listAllContacts(ContactBook cBook) {
        if (cBook.getNumberOfContacts() != 0) {
            ContactIterator it = cBook.iterator();
            while (it.hasNext()) {
                Contact c = it.next();
                System.out.println(c.getName() + "; " + c.getEmail() + "; " + c.getPhone());
            }
        } else System.out.println(BOOK_EMPTY);
    }
}
