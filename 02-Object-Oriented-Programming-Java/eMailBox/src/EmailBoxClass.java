import Exceptions.*;

import java.time.LocalDate;
import java.util.*;

public class EmailBoxClass implements EmailBox {
    private final SortedSet<Message> msgSent;
    private final SortedSet<Message> msgReceived;
    private final SortedMap<String, List<Message>> smsPerSubject;
    private final Map<String, List<Message>> smsPerEmail;

    public EmailBoxClass() {
        msgSent = new TreeSet<>();
        msgReceived = new TreeSet<>();
        smsPerSubject = new TreeMap<>();
        smsPerEmail = new HashMap<>();
    }

    @Override
    public void sendEmail(String subject, String email, String text, LocalDate date)
            throws DuplicatedMessageException {
        addMessage(subject, email, text, date, msgSent);
    }

    @Override
    public void receiveEmail(String subject, String email, String text, LocalDate date)
            throws DuplicatedMessageException {
        addMessage(subject, email, text, date, msgReceived);
    }

    private void addMessage
            (String subject, String email, String text, LocalDate date, SortedSet<Message> set) {
        Message message = new Message(subject, email, text, date);
        if (!set.add(message)) {
            throw new DuplicatedMessageException();
        }
        if (!smsPerSubject.containsKey(subject))
            smsPerSubject.put(subject, new ArrayList<>());
        smsPerSubject.get(subject).add(message);
        if (!smsPerEmail.containsKey(email))
            smsPerEmail.put(email, new ArrayList<>());
        smsPerEmail.get(email).add(message);
    }

    @Override
    public Iterator<Message> getSentMessages() {
        return getSetMessages(msgSent);
    }

    @Override
    public Iterator<Message> getReceivedMessages() {
        return getSetMessages(msgReceived);
    }

    private Iterator<Message> getSetMessages(SortedSet<Message> set) {
        return set.iterator();
    }

    @Override
    public Iterator<Message> getMessagesBySubject(String subject)
            throws NoMessagesWithSubjectException {
        if (!smsPerSubject.containsKey(subject))
            throw new NoMessagesWithSubjectException();
        return getListMessages(smsPerSubject.get(subject));
    }

    @Override
    public Iterator<Message> getMessagesByEmail(String email)
            throws NoMessagesWithEmailException {
        if (!smsPerEmail.containsKey(email))
            throw new NoMessagesWithEmailException();
        return getListMessages(smsPerEmail.get(email));
    }

    private Iterator<Message> getListMessages(List<Message> list) {
        return list.iterator();
    }

    @Override
    public Iterator<String> getSubjects() {
        return smsPerSubject.keySet().iterator();
    }
}
