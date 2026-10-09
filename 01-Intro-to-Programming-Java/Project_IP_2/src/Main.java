import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Classe principal da aplicação Calendário Partilhado.
 * Gere a interação com o utilizador e o carregamento de ficheiros.
 *
 * @author David Figueiredo 74167
 * @author Diana Barra 74154
 */
public class Main {

    //Opções
    private final static String EXIT = "exit";
    private final static String CREATE = "create";
    private final static String SCHEDULE = "schedule";
    private final static String CANCEL = "cancel";
    private final static String SHOW = "show";
    private final static String TOP = "top";

    //String para default
    private final static String INVALID_COMMAND = "Invalid command.";

    //String para exit
    private final static String EXIT_ANSWER = "Application exited.";

    //Strings para create
    private final static String USER_REGISTERED = "User already registered.";
    private final static String USER_CREATED = "User successfully created.";

    //Strings para schedule
    private final static String USER_NOT_REGISTERED = "Some user not registered.";
    private final static String EVENT_EXISTS = "Event already exists.";
    private final static String PROPOSER_NOT_AVAILABLE = "Proposer not available.";
    private final static String USER_NOT_AVAILABLE = "Some user not available.";
    private final static String EVENT_CREATED = "Event successfully created.";

    //Strings para cancel
    private final static String USER_NOT_FOUND = "User not registered.";
    private final static String EVENT_NOT_IN_CALENDAR = "Event not found in calendar of %s.";
    private final static String NOT_PROPOSER = "User %s did not create event %s.";
    private final static String EVENT_CANCELLED = "Event successfully canceled.";

    //Strings para show
    private final static String NO_EVENTS = "User %s has no events.";

    //String para top
    private final static String NO_EVENTS_REGISTERED = "No events registered.";

    //String para show e top
    private final static String EVENT_FORMAT = "%s, day %d, %d-%d, %d participants.%n";


    /**
     * Carrega o calendário a partir de um ficheiro.
     * Mantém a lógica original: recebe o scanner e chama as leituras parciais.
     *
     * @param fileScanner Scanner do ficheiro.
     * @param cal         O calendário a preencher.
     */
    private static void loadCalendarFromFile(Scanner fileScanner, Calendar cal) {
        //Se o ficheiro ainda tiver informção, este lê os seus users
        if (fileScanner.hasNext()) {
            readUsersFromFile(fileScanner, cal);
        }
        //Se o ficheiro ainda tiver informção, este lê os seus eventos
        if (fileScanner.hasNext()) {
            readEventsFromFile(fileScanner, cal);
        }
    }

    /**
     * Lê os users já criados dos ficheiros que estamos a ler
     * @param fileScanner Scanner do ficheiro
     * @param cal O calendário genérico
     */
    private static void readUsersFromFile(Scanner fileScanner, Calendar cal) {
        //Lê o número dos users
        int numUsers = fileScanner.nextInt();
        //Enquanto ainda existem users, continua a ler os nomes e adiciona um user
        for (int i = 0; i < numUsers; i++) {
            if (fileScanner.hasNext()) {
                String name = fileScanner.next();
                cal.addUser(name);
            }
        }
    }

    /**
     * Lê os eventos já existentes do ficheiro que estamos a ler
     * @param fileScanner Scanner do ficheiro
     * @param cal O calendário genérico
     */
    private static void readEventsFromFile(Scanner fileScanner, Calendar cal) {
        if (!fileScanner.hasNextInt())
            return;

        //Guarda as informações do evento que o programa está a ler
        int numEvents = fileScanner.nextInt();
        for (int i = 0; i < numEvents; i++) {
            String eventName = fileScanner.next();
            int day = fileScanner.nextInt();
            int start = fileScanner.nextInt();
            int end = fileScanner.nextInt();
            int numPart = fileScanner.nextInt();

            //Cria um array com os participantes involvidos no evento
            String[] participants = new String[numPart];
            for (int j = 0; j < numPart; j++) {
                participants[j] = fileScanner.next();
            }
            //Cria o evento
            cal.addEvent(eventName, day, start, end, participants);
        }
    }

    /**
     * O comando exit
     */
    private static void exitCommand() {
        System.out.println(EXIT_ANSWER);
    }

    /**
     * O comando create
     *
     * @param in  O Scanner principal
     * @param cal O calendário genérico
     */
    private static void createCommand(Scanner in, Calendar cal) {
        //Avalia se o user já existe e se não cria-o
        String name = in.next();
        if (cal.hasUsers(name)) {
            System.out.println(USER_REGISTERED);
        } else {
            cal.addUser(name);
            System.out.println(USER_CREATED);
        }
        in.nextLine();
    }

    /**
     * O comando schedule
     * @param in O Scanner principal
     * @param cal O calendário genérico
     */
    private static void scheduleCommand(Scanner in, Calendar cal) {
        String eventName = in.next();
        int day = in.nextInt();
        int start = in.nextInt();
        int end = in.nextInt();
        int numUsers = in.nextInt();
        String[] participants = new String[numUsers];
        for (int i = 0; i < numUsers; i++) {
            participants[i] = in.next();
        }
        in.nextLine();
        //Avalia se existem: todos os users, evento igual, proposer está livre e os part
        String proposerName = participants[0];
        if (!cal.allUsersExist(participants)) {
            System.out.println(USER_NOT_REGISTERED);
        } else if (cal.isEqualEvent(eventName)) {
            System.out.println(EVENT_EXISTS);
        } else if (!cal.isProposerAvailable(proposerName, day, start, end)) {
            System.out.println(PROPOSER_NOT_AVAILABLE);
        } else if (!cal.areAllGuestsAvailable(participants, day, start, end)) {
            System.out.println(USER_NOT_AVAILABLE);
        } else {
            cal.addEvent(eventName, day, start, end, participants);
            System.out.println(EVENT_CREATED);
        }
    }

    /**
     * O comando cancel
     * @param in O Scanner principal
     * @param cal O calendário genérico
     */
    private static void cancelCommand(Scanner in, Calendar cal) {
        String eventName = in.next();
        String userName = in.next();
        in.nextLine();

        //Verifica se existe user, se o user está no evento ou se este é o proposer; cancela se for
        if (!cal.hasUsers(userName)) {
            System.out.println(USER_NOT_FOUND);
        } else if (!cal.isUserOnEvent(userName, eventName)) {
            System.out.printf(EVENT_NOT_IN_CALENDAR + "%n", userName);
        } else if (!cal.isProposer(userName, eventName)) {
            System.out.printf(NOT_PROPOSER + "%n", userName, eventName);
        } else {
            cal.removeEvent(eventName);
            System.out.println(EVENT_CANCELLED);
        }
    }

    /**
     * O comando show
     * @param in O Scanner principal
     * @param cal O calendário genérico
     */
    private static void showCommand(Scanner in, Calendar cal) {
        String userName = in.next();
        in.nextLine();
        //Verifica se o user existe, se o user tem eventos e mostra os eventos do user escolhido
        if (!cal.hasUsers(userName)) {
            System.out.println(USER_NOT_FOUND);
        } else if (!cal.isUserOccupied(userName)) {
            System.out.printf(NO_EVENTS + "%n", userName);
        } else {
            EventIterator it = cal.getUserEventsIterator(userName);
            while (it.hasNext()) {
                printEvent(it.next());
            }
        }
    }

    /**
     * O comando top
     * @param cal O calendário genérico
     */
    private static void topCommand(Calendar cal) {
        //Verifica se existem eventos em geral e se sim, mostra todos por ordem crescente de part
        if (!cal.AreThereEvents()) {
            System.out.println(NO_EVENTS_REGISTERED);
        } else {
            EventIterator it = cal.getTopEventsIterator();
            while (it.hasNext()) {
                printEvent(it.next());
            }
        }
    }

    /**
     * Método que põe todos os eventos por ordem crescente
     * @param e O nome do evento
     */
    private static void printEvent(Event e) {
        System.out.printf(EVENT_FORMAT,
                e.getName(), e.getDay(), e.getStart(), e.getEnd(), e.getNumParticipants());
    }

    /**
     * O comando dos comandos
     *
     * @param in  O Scanner principal
     * @param cal O calendário genérico
     */
    private static void executeCommands(Scanner in, Calendar cal) {
        String command;
        do {
            command = in.next();
            switch (command) {
                case EXIT -> exitCommand();
                case CREATE -> createCommand(in, cal);
                case SCHEDULE -> scheduleCommand(in, cal);
                case CANCEL -> cancelCommand(in, cal);
                case SHOW -> showCommand(in, cal);
                case TOP -> topCommand(cal);
                default -> {
                    System.out.println(INVALID_COMMAND);
                    in.nextLine();
                }
            }
        } while (!command.equals(EXIT));
    }

    /**
     * Método principal que inicia a aplicação.
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String fileName = in.nextLine();
        //Cria um novo calendário
        Calendar cal = new Calendar();
        // Abre o ficheiro aqui, passa o scanner para o load, e fecha
        try {
            FileReader fileReader = new FileReader(fileName);
            Scanner fileScanner = new Scanner(fileReader);
            loadCalendarFromFile(fileScanner, cal);
            fileScanner.close();
        } catch (FileNotFoundException exception) {
            // Ignora o erro
        }
        executeCommands(in, cal);
        in.close();
    }
}