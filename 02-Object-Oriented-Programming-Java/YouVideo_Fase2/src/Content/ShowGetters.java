package Content;

import Videos.Video;

import java.time.LocalDate;

/**
 * This interface is a read-only interface for the show object.
 */
public interface ShowGetters extends ContentGetters {

    /**
     * Gets the transmission date of the show
     * @return the transmission date
     */
    LocalDate getTransmissionDate();

    /**
     * Gets the video of the show
     * @return the show
     */
    Video getVideo();

    /**
     * Gets the title of the video in the show
     * @return the title
     */
    String getTitle();
    /**
     * Gets the language code of the video in the show
     * @return the language code
     */
    String getLanguageCode();
}
