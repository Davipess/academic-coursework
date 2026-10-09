import java.time.LocalDate;

public record Message(String subject, String email, String text, LocalDate date)
        implements Comparable<Message> {

    @Override
    public int compareTo(Message other) {
        if (!this.date.isEqual(other.date))
            return this.date.compareTo(other.date);
        else if (!this.subject.equalsIgnoreCase(other.subject))
            return this.subject.compareTo(other.subject);
        else {
            return this.email.compareTo(other.email);
        }
    }
}
