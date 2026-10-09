package Show;

import Videos.Video;

public interface Show {

    /**
     *
     * @return returns the Author name
     */
    String authorName();

    /**
     * Gets the transmission date of the show
     * @return the transmission date
     */
    String transmissionDate();

    /**
     * Gets the video of the show
     * @return the show
     */
    Video video();

    /**
     * Gets the title of the video in the show
     * @return the title
     */
    String getShowTitle();
}
