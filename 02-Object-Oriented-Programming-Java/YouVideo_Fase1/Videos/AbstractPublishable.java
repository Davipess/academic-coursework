package Videos;

/**
 * This abstract class implements the Publishable interface and all its methods
 */
abstract class AbstractPublishable extends AbstractVideo implements Publishable{

    private final String publisher, title, code;

    public AbstractPublishable
            (String id, int duration, String url, String publisher, String title, String code) {
        super(id, duration, url);
        this.publisher = publisher;
        this.title = title;
        this.code = code;
    }

    @Override
    public String getPublisher() {
        return publisher;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getCode() {
        return code;
    }
}
