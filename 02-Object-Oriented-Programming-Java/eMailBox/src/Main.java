import Exceptions.*;
import Enum.*;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.Scanner;

/**
 * The aim of this exercise is to develop an application to manage email exchanges. The system
 * should be able to manage a set of incoming and outgoing messages, with the number of people
 * with whom messages are exchanged expected not to exceed 200. Each message is associated
 * with the email address of the sender/recipient of the message, the date of receipt/sending,
 * the subject of the message and the text of the message.
 */

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EmailBox eb = new EmailBoxClass();
        executeCommand(sc, eb);
        sc.close();
    }

    private static void executeCommand(Scanner sc, EmailBox eb) {
        Commands cmd;
        do {
            cmd = Commands.valueOf(sc.nextLine().toUpperCase());
            switch (cmd) {
                case SEND -> setSend(sc, eb);
                case RECEIVE -> setReceive(sc, eb);
                case SENT -> setSent(eb);
                case RECEIVED -> setReceived(eb);
                case SUBJECT -> setSubject(sc, eb);
                case EMAIL -> setEmail(sc, eb);
                case SUBJECTS -> setSubjects(eb);
                case EXIT -> setExit();
            }
        } while (!cmd.equals(Commands.EXIT));
    }

    private static void setSend(Scanner sc, EmailBox eb) {
        try {
            String subject = sc.nextLine();
            String email = sc.nextLine();
            String text = sc.nextLine();
            LocalDate date = LocalDate.parse(sc.nextLine());

            eb.sendEmail(subject, email, text, date);
            System.out.println(Messages.MESSAGE_REGISTERED.getText());
        } catch (DuplicatedMessageException e) {
            System.out.println(Messages.DUPLICATED_MESSAGE.getText());
        }
    }

    private static void setReceive(Scanner sc, EmailBox eb) {
        try {
            String subject = sc.nextLine();
            String email = sc.nextLine();
            String text = sc.nextLine();
            LocalDate date = LocalDate.parse(sc.nextLine());

            eb.receiveEmail(subject, email, text, date);
            System.out.println(Messages.MESSAGE_REGISTERED.getText());
        } catch (DuplicatedMessageException e) {
            System.out.println(Messages.DUPLICATED_MESSAGE.getText());
        }
    }

    private static void setSent(EmailBox eb) {
        System.out.println(Messages.MESSAGE_HEADLINE1.getText());
        Iterator<Message> it = eb.getSentMessages();
        while (it.hasNext()) {
            Message m = it.next();
            System.out.printf(Messages.MESSAGE_LAYOUT1.getText(), m.date(), m.subject(), m.email());
        }
    }

    private static void setReceived(EmailBox eb) {
        System.out.println(Messages.MESSAGE_HEADLINE1.getText());
        Iterator<Message> it = eb.getReceivedMessages();
        while (it.hasNext()) {
            Message m = it.next();
            System.out.printf(Messages.MESSAGE_LAYOUT1.getText(), m.date(), m.subject(), m.email());
        }
    }

    private static void setSubject(Scanner sc, EmailBox eb) {
        try {
            String subject = sc.nextLine();

            Iterator<Message> it = eb.getMessagesBySubject(subject);
            System.out.println(Messages.MESSAGE_HEADLINE2.getText());
            while (it.hasNext()) {
                Message m = it.next();
                System.out.printf(Messages.MESSAGE_LAYOUT2.getText(), m.date(), m.subject(), m.email(), m.text());
            }
        } catch (NoMessagesWithSubjectException e) {
            System.out.println(Messages.NO_SUBJECT_MESSAGES.getText());
        }
    }

    private static void setEmail(Scanner sc, EmailBox eb) {
        try {
            String email = sc.nextLine();

            Iterator<Message> it = eb.getMessagesByEmail(email);
            System.out.println(Messages.MESSAGE_HEADLINE2.getText());
            while (it.hasNext()) {
                Message m = it.next();
                System.out.printf(Messages.MESSAGE_LAYOUT2.getText(), m.date(), m.subject(), m.email(), m.text());
            }
        } catch (NoMessagesWithEmailException e) {
            System.out.println(Messages.NO_EMAIL_MESSAGES.getText());
        }
    }

    private static void setSubjects(EmailBox eb) {
        Iterator<String> it = eb.getSubjects();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    private static void setExit() {
        System.out.println(Messages.EXIT.getText());
    }
}