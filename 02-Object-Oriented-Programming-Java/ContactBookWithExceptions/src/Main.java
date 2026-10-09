import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
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

    private static final String CONTACT_EXISTS = "Contact already exists.";
    private static final String NAME_NOT_EXIST = "Contact does not exist.";
    private static final String CANNOT_REMOVE = "Cannot remove contact.";
    private static final String INVALID_PHONE = "Not a valid phone number.";
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
                case CHECK_CONTACTS -> checkContacs(cBook);
                case LIST_CONTACTS -> listAllContacts(cBook);
                case QUIT -> System.out.println(QUIT_MSG);
                default -> System.out.println(COMMAND_ERROR);
            }
        } while (!comm.equals(QUIT));
        in.close();
    }

    private static String getCommand(Scanner in) {
        return in.nextLine().toUpperCase();
    }

    private static void addContact(Scanner in, ContactBook cBook) {
        String name = in.nextLine();
        int phone = 0;
        boolean phoneValid = true;

        try {
            phone = in.nextInt();
        } catch (InputMismatchException e) {
            phoneValid = false;
        }
        in.nextLine();

        String email = in.nextLine();

        if (!phoneValid) {
            System.out.println(INVALID_PHONE);
        } else {
            try {
                cBook.addContact(name, phone, email);
                System.out.println(CONTACT_ADDED);
            } catch (ContactAlreadyExistsException e) {
                System.out.println(CONTACT_EXISTS);
            }
        }
    }

    private static void deleteContact(Scanner in, ContactBook cBook) {
        String name = in.nextLine();
        try {
            cBook.deleteContact(name);
            System.out.println(CONTACT_REMOVED);
        } catch (ContactDoesNotExistException e) {
            System.out.println(NAME_NOT_EXIST);
        }
    }

    private static void getPhone(Scanner in, ContactBook cBook) {
        String name = in.nextLine();
        try {
            System.out.println(cBook.getPhone(name));
        } catch (ContactDoesNotExistException e) {
            System.out.println(NAME_NOT_EXIST);
        }
    }

    private static void getEmail(Scanner in, ContactBook cBook) {
        String name = in.nextLine();
        try {
            System.out.println(cBook.getEmail(name));
        } catch (ContactDoesNotExistException e) {
            System.out.println(NAME_NOT_EXIST);
        }
    }

    private static void setPhone(Scanner in, ContactBook cBook) {
        String name = in.nextLine();
        int phone = 0;
        boolean phoneValid = true;

        try {
            phone = in.nextInt();
        } catch (InputMismatchException e) {
            phoneValid = false;
        }
        in.nextLine();

        if (!phoneValid) {
            System.out.println(INVALID_PHONE);
        } else {
            try {
                cBook.setPhone(name, phone);
                System.out.println(CONTACT_UPDATED);
            } catch (ContactDoesNotExistException e) {
                System.out.println(NAME_NOT_EXIST);
            }
        }
    }

    private static void setEmail(Scanner in, ContactBook cBook) {
        String name = in.nextLine();
        String email = in.nextLine();
        try {
            cBook.setEmail(name, email);
            System.out.println(CONTACT_UPDATED);
        } catch (ContactDoesNotExistException e) {
            System.out.println(NAME_NOT_EXIST);
        }
    }

    private static void getNameWithNumber(Scanner in, ContactBook cBook) {
        int phone = 0;
        boolean phoneValid = true;

        try {
            phone = in.nextInt();
        } catch (InputMismatchException e) {
            phoneValid = false;
        }
        in.nextLine();

        if (!phoneValid) {
            System.out.println(INVALID_PHONE);
        } else {
            if (!cBook.hasPhone(phone)) {
                System.out.println(PHONE_NOT_EXIST);
            } else {
                String name = cBook.getNameWithNumber(phone);
                System.out.println(name);
            }
        }
    }

    private static void checkContacs(ContactBook cBook) {
        boolean comp = cBook.contactsComparer();
        if (comp) {
            System.out.println(SHARE_NAME);
        } else {
            System.out.println(DIFF_NAME);
        }
    }

    private static void listAllContacts(ContactBook cBook) {
        if (cBook.getNumberOfContacts() != 0) {
            ContactIterator it = cBook.iterator();
            while (it.hasNext()) {
                Contact c = it.next();
                System.out.println(c.getName() + "; " + c.getEmail() + "; " + c.getPhone());
            }
        } else {
            System.out.println(BOOK_EMPTY);
        }
    }
}