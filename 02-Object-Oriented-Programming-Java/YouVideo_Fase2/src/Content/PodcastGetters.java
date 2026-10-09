package Content;

import Videos.Episode;

import java.util.Iterator;

/**
 * This interface is a read-only interface for the Podcast object.
 */
public interface PodcastGetters extends ContentGetters {

    /**
     * Gets the language code of the podcast
     * @return the language code
     */
    String getCode();

    /**
     * Checks if the podcast has episodes
     * @return true if it has any episodes and false otherwise
     */
    boolean hasEpisodes();

    /**
     * Gets the last added episode of the podcast
     * @return the last episode
     */
    Episode getLastEpisode();

    /**
     * Gets an iterator with all the episodes of the podcast
     * @return the iterator with all the episodes of the podcast
     */
    Iterator<Episode> getEpisodes();
}
