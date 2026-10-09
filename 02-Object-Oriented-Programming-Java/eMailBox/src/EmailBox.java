import Exceptions.DuplicatedMessageException;
import Exceptions.NoMessagesWithEmailException;
import Exceptions.NoMessagesWithSubjectException;

import java.time.LocalDate;
import java.util.Iterator;

public interface EmailBox {

    void sendEmail(String subject, String email, String text, LocalDate date)
            throws DuplicatedMessageException;

    void receiveEmail(String subject, String email, String text, LocalDate date)
            throws DuplicatedMessageException;

    Iterator<Message> getSentMessages();

    Iterator<Message> getReceivedMessages();

    Iterator<Message> getMessagesBySubject(String subject)
            throws NoMessagesWithSubjectException;

    Iterator<Message> getMessagesByEmail(String email)
            throws NoMessagesWithEmailException;

    Iterator<String> getSubjects();
}
