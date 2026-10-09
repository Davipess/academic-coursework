package Podcast;

import Videos.Episode;
import dataStructures.Iterator;

/**
 * This interface represents a Podcast with a collection of episodes sorted by release date
 */
public interface Podcast {
    /**
     * Gets the title of the podcast
     * @return the title
     */
    String getTitle();

    /**
     * Gets the name of the author of the podcast
     * @return the name of the author
     */
    String getAuthor();

    /**
     * Gets the language code of the podcast
     * @return the language code
     */
    String getCode();

    /**
     * Adds a new episode to the podcast
     * @param title the title of the episode
     * @param duration the duration in minutes of the episode
     * @param url the url link of the episode
     * @param date the release date of the episode
     */
    void addEpisode(String title, int duration, String url, String date);

    /**
     * Gets the last added episode of the podcast
     * @return the last episode
     */
    Episode getLastEpisode();

    /**
     * Checks if the release date of an episode to be added is sooner than the latest episode
     * of the podcast
     * @param date the date to be checked
     * @return true if it's earlier and false otherwise
     */
    boolean isDateEarlier(String date);

    /**
     * Gets an iterator with all the episodes of the podcast
     * @return the iterator with all the episodes of the podcast
     */
    Iterator<Episode> getEpisodes();

    /**
     * Checks if the podcast has episodes
     * @return true if it has any episodes and false otherwise
     */
    boolean hasEpisodes();
}
