package Collections;

import Videos.Video;

/**
 * This interface represents a collection of publishable videos (basic and premium)
 */
public interface PublishableCollection {

    /**
     * Checks if the language code of a video given is valid
     * @param code the code to be validated
     * @return true if it's valid
     */
    boolean isValidCode(String code);

    /**
     * Checks if the duration of a video is valid
     * @param duration the duration of the video
     * @return true if it's valid (greater than 0)
     */
    boolean isValidDuration(int duration);

    /**
     * Checks if a video already exists given is identifier
     * @param videoId the identifier
     * @return true if it already exists
     */
    boolean isExistingVideo(String videoId);

    /**
     * Creates a basic publishable video
     * @param id the video identifier
     * @param duration the video duration
     * @param url the video url link
     * @param publisherName the video's publisher name
     * @param title the video title
     * @param code the primary language code
     */
    void createPublishable
            (String id, int duration, String url, String publisherName, String title, String code);

    /**
     * Creates a premium publishable video
     * @param id the video identifier
     * @param duration the video duration
     * @param url the video url link
     * @param publisherName the video's publisher name
     * @param title the video title
     * @param code the primary language code
     * @param subtitleUrl where the initial subtitle file is stored
     * @param subtitleCode  code for a specific subtitle
     */
    void createPremium
            (String id, int duration, String url, String publisherName, String title, String code, String subtitleUrl,
             String subtitleCode);

    /**
     * Checks if a certain video is a premium video
     * @param videoId the id of the video to be checked
     * @return true of the given video is premium and false if it's not.
     */
    boolean isPremiumVideo(String videoId);

    /**
     * Adds a subtitle file into a premium video
     * @param videoId the id of the premium video
     * @param url the url of subtitle file
     * @param code the code of the subtitle language
     */
    void addSubtitle(String videoId, String url, String code);

    /**
     * Gets a video based on it's id
     * @param videoId the video id to be returned
     * @return the video with the id given
     */
    Video getVideo(String videoId);

    /**
     * Removes a video given its id
     * @param id the id of the video to be removed
     */
    void removeVideo(String id);
}
