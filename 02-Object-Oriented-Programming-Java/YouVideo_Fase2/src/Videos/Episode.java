package Videos;

import java.time.LocalDate;

/**
 * This interface represents an Episode contained in a certain podcast
 */
public interface Episode extends Video {

    /**
     * Gets the release date of the episode
     * @return the date of the episode
     */
    LocalDate getDate();
}
