package Collections;

import Exceptions.*;
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
            (String id, int duration, String url, String publisherName, String title, String code)
            throws InvalidDurationException, InvalidCodeException, VideoAlreadyExistsException;

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
     * @throws InvalidDurationException if the duration of the video is invalid
     * @throws InvalidCodeException if the language code is invalid
     * @throws VideoAlreadyExistsException if the video already exists
     * @throws InvalidSubtitleLanguageException if the subtitle language code is invalid
     */
    void createPremium
            (String id, int duration, String url, String publisherName, String title, String code, String subtitleUrl,
             String subtitleCode) throws InvalidDurationException, InvalidCodeException,
            VideoAlreadyExistsException, InvalidSubtitleLanguageException;

    /**
     * Adds a subtitle file into a premium video
     * @param videoId the id of the premium video
     * @param url the url of subtitle file
     * @param code the code of the subtitle language
     */
    void addSubtitle(String videoId, String url, String code) throws InvalidCodeException, VideoIsNotPremiumException,
            VideoDoesNotExistException;

    /**
     * Gets a video based on it's id
     * @param videoId the video id to be returned
     * @return the video with the id given
     * @throws  VideoDoesNotExistException if the video does not exist
     */
    Video getVideo(String videoId) throws VideoDoesNotExistException;

    /**
     * Removes a video given its id
     * @param video the video to be removed
     */
    void removeVideo(Video video);
}
