package Collections;

import Podcast.Podcast;
import Videos.Episode;
import dataStructures.Iterator;

/**
 * This interface represents a collection of podcasts, each one with their own episodes
 */
public interface PodcastCollection {
    /**
     * Creates a podcast and stores it in the podcasts array
     * @param title the podcast title
     * @param author the podcast author
     * @param code the podcast code
     */
    void createPodcast(String title, String author, String code);

    /**
     * Checks if a certain podcast exists
     * @param title the title of the podcast to be checked
     * @return true if exists and false if it doesn't
     */
    boolean isExistingPodcast(String title);

    /**
     * Gets a podcasts with the same title as the one given
     * @param title the title of the podcast to be searched
     * @return the podcast with the same title as the one given
     */
    Podcast getPodcast(String title);

    /**
     * Checks if a certain video is addressed as an episode of a podcast
     * @param id the id of the video to be checked
     * @return true if the video is an episode and false otherwise
     */
    boolean isExistingEpisode(String id);

    /**
     * Checks if a certain date of an episode from a certain podcast is sooner than
     * the last episode of the podcast
     * @param podcast the podcast
     * @param date the date to be checked
     * @return true if the date of the episode is earlier than the last episode of the podcats
     */
    boolean isDateEarlier(String podcast, String date);

    /**
     * Checks if a podcast with a certain title has any episodes
     * @param title the title of the podcast
     * @return true if the podcast has episodes and false otherwise
     */
    boolean hasEpisodes(String title);

    /**
     * Gets all the episodes from a podcast given its title
     * @param title the title of the podcast
     * @return an iterator with all the episodes from a certain podcast
     */
    Iterator<Episode> getPodcastEpisodes(String title);

    /**
     * Removes a certain podcast from the collection
     * @param podcast the podcast to be removed
     */
    void removePodcast(Podcast podcast);
}
