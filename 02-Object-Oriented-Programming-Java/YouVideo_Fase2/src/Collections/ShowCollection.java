package Collections;

import Content.Show;
import Exceptions.ShowDoesNotExistException;
import Videos.Video;

import java.time.LocalDate;

/**
 * This interface represents a collection of all the shows in the system
 */
public interface ShowCollection {

    /**
     * Creates a new show to a video
     * @param authorName the name of the author of the video
     * @param transmissionDate the transmission date of the show
     * @param video the video that will be shown
     * @return the show that was just created because it also needs to be added to the author
     */
    Show createShow(String authorName, LocalDate transmissionDate, Video video);

    /**
     * Removes a show given its title
     * @param show the show to be removed
     */
    void removeShow(Show show);

    /**
     * Gets a show with same title as the one given
     * @param showTitle the show title
     * @return the Show with the same title as the one given
     */
    Show getShow(String showTitle) throws ShowDoesNotExistException;

    /**
     * Checks if there is a show with the same title as the one given
     * @param showTitle the title to be checked
     * @return true if there is a show with the same title as the one given and false otherwise
     */
    boolean isExistingShow(String showTitle);
}
