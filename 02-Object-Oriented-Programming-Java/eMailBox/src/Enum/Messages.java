package Enum;

public enum Messages {
    MESSAGE_REGISTERED("Message registered."),
    DUPLICATED_MESSAGE("Duplicated message."),
    MESSAGE_HEADLINE1("date | subject | email"),
    MESSAGE_LAYOUT1("%s | %s | %s\n"),
    MESSAGE_HEADLINE2("date | subject | email | text"),
    MESSAGE_LAYOUT2("%s | %s | %s | %s\n"),
    NO_SUBJECT_MESSAGES("No messages exchanged on this subject."),
    NO_EMAIL_MESSAGES("No messages exchanged with this email."),
    EXIT("Bye!");

    private final String text;

    Messages(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}