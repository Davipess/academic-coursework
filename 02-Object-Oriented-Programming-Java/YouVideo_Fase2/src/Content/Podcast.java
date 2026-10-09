package Content;

import Exceptions.EpisodeAlreadyExistsException;

import java.time.LocalDate;

/**
 * This interface represents a Podcast with a collection of episodes sorted by release date
 */
public interface Podcast extends PodcastGetters{

    /**
     * Adds a new episode to the podcast
     * @param id the id of the episode
     * @param duration the duration in minutes of the episode
     * @param url the url link of the episode
     * @param date the release date of the episode
     * @throws EpisodeAlreadyExistsException if the episode already exists in the system
     */
    void addEpisode(String id, int duration, String url, LocalDate date) throws EpisodeAlreadyExistsException;

    /**
     * Checks if the release date of an episode to be added is sooner than the latest episode
     * of the podcast
     * @param date the date to be checked
     * @return true if it's earlier and false otherwise
     */
    boolean isDateEarlier(LocalDate date);

    /**
     * Compares a type of Content, in this case, a Podcast, by their title.
     * @param other another Content object for the comparison
     * @return 1 if the title of this Content is greater than the other Content title,
     * -1 if this title is less than the other title, 0 if they're equal.
     */
    int compareTo(Content other);
}
