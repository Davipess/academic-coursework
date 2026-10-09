package Collections;

import Show.Show;
import Videos.Video;

public interface ShowCollection {

    /**
     * Creates a new show and stores it into the array of shows of the collection
     * @param authorName the name if the author of the show
     * @param transmissionDate the transmission date of the show
     * @param video the video to be presented in the show
     */
    void createShow(String authorName, String transmissionDate, Video video);

    /**
     * Removes a show given its title
     * @param showTitle the title of yhe show to be removed
     */
    void removeShow(String showTitle);

    /**
     * Gets a show with same title as the one given
     * @param showTitle the show title
     * @return the Show with the same title as the one given
     */
    Show getShow(String showTitle);

    /**
     * Checks if a given video is addressed to a show
     * @param video the video to be checked
     * @return true if the video is addressed to a show
     */
    boolean isShow(Video video);

    /**
     * Checks if there is a show with the same title as the one given
     * @param showTitle the title to be checked
     * @return true if there is a show with the same title as the one given and false otherwise
     */
    boolean isExistingShow(String showTitle);
}
