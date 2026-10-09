package Videos;

/**
 * This class implements the BasicVideo interface and all its methods
 */
public class BasicVideoClass extends AbstractPublishable implements BasicVideo {
    public BasicVideoClass
            (String id, int duration, String url, String publisher, String title, String code) {
        super(id, duration, url, publisher, title, code);
    }
}
