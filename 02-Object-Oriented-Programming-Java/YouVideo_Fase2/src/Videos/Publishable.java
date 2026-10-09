package Videos;

/**
 * This interface represents a Publishable video (Basic or Premium)
 * that can be converted into a show as well
 */
public interface Publishable {

    /**
     * Gets the publisher of the video
     * @return the name of the publisher
     */
    String getPublisher();

    /**
     * Gets the title of the video
     * @return the title of the video
     */
    String getTitle();

    /**
     * Gets the main language code of the video
     * @return the code of the video
     */
    String getCode();
}
