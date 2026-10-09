package Subtitles;

/**
 * This interface represents a Subtitle from a premium video
 */
public interface Subtitle {
    /**
     * Gets the url of a subtitle.
     * @return the url
     */
    String url();

    /**
     * Gets the code of a subtitle.
     * @return the code
     */
    String code();
}
