package Collections;

import Content.Podcast;
import Exceptions.*;
import Videos.Episode;

import java.time.LocalDate;
import java.util.Iterator;

/**
 * This interface represents a collection of podcasts, each one with their own episodes
 */
public interface PodcastCollection {
    /**
     * Creates a podcast and stores it in the podcasts array
     * @param title the podcast title
     * @param author the podcast author
     * @param code the podcast code
     * @throws PodcastAlreadyExistsException if the podcast already exists before creation.
     */
    Podcast createPodcast(String title, String author, String code) throws PodcastAlreadyExistsException;

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
     * @throws PodcastDoesNotExistException if the podcast does not exist
     */
    Podcast getPodcast(String title) throws PodcastDoesNotExistException;

    /**
     * Creates and adds an episode to a certain podcast
     * @param podTitle teh title of a podcast
     * @param id the id of the episode
     * @param duration the duration of the episode
     * @param url the url of the episode
     * @param date the release date of the episode
     * @throws InvalidDurationException if the duration is 0 or below
     * @throws EpisodeAlreadyExistsException if an episode with the same id already exists
     * @throws DateIsEarlierException if the date given is earlier than the latest episode of the podcats
     * @throws PodcastDoesNotExistException if the podcast does not exist
     */
    void addEpisode(String podTitle, String id, int duration, String url, LocalDate date)
            throws InvalidDurationException, EpisodeAlreadyExistsException,
            DateIsEarlierException, PodcastDoesNotExistException;

    /**
     * Checks if a certain video is addressed as an episode of a podcast
     * @param id the id of the video to be checked
     * @return true if the video is an episode and false otherwise
     */
    boolean isExistingEpisode(String id);

    /**
     * Checks if a certain date of an episode from a certain podcast is sooner than
     * the last episode of the podcast
     * @param pod the podcast
     * @param date the date to be checked
     * @return true if the date of the episode is earlier than the last episode of the podcats
     */
    boolean isDateEarlier(Podcast pod, LocalDate date);

    /**
     * Gets all the episodes from a podcast given its title
     * @param title the title of the podcast
     * @return an iterator with all the episodes from a certain podcast
     * @throws PodcastDoesNotExistException if the podcast does not exist
     * @throws NoEpisodesException if the podcast has no episodes
     */
    Iterator<Episode> getPodcastEpisodes(String title) throws  PodcastDoesNotExistException, NoEpisodesException;

    /**
     * Removes a certain podcast from the collection
     * @param podcast the podcast to be removed
     * @throws PodcastDoesNotExistException if the podcast does not exist
     */
    void removePodcast(Podcast podcast) throws PodcastDoesNotExistException;
}
