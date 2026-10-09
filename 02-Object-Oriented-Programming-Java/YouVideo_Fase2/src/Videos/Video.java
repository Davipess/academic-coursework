package Videos;

/**
 * This interface represents a publishable video
 * (Basic or Premium Video or an Episode of a Podcast) with its own information
 */
public interface Video {

    /**
     * Gets the ID of the video
     *
     * @return the ID
     */
    String getId();

    /**
     * Gets the duration of the video
     *
     * @return the duration
     */
    int getDuration();

    /**
     * Gets the url of the video
     *
     * @return the url
     */
    String getUrl();
}
