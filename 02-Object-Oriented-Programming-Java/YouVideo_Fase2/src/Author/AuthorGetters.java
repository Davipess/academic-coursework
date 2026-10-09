package Author;

import Content.PodcastGetters;
import Content.ShowGetters;

import java.util.Iterator;

/**
 * This interface is a read-only object of the Author interface
 */
public interface AuthorGetters extends Comparable<Author>{

    /**
     * Gets the name of the author
     * @return the name of the author
     */
    String getName();

    /**
     * Gets an iterator that iterates all the podcasts from the author
     * @return the iterator with all the podcasts
     */
    Iterator<PodcastGetters> podcastIterator();

    /**
     * Checks if the author has any shows
     * @return true if it has any shows, and false otherwise
     */
    boolean hasShows();

    /**
     * Gets all the shows from a given author
     * @return an iterator that iterates through a set of shows
     */
    Iterator<ShowGetters> showsIterator();

    /**
     * Gets the productivity of an author
     * @return the productivity of an author (number of shows and podcasts)
     */
    int getProductivity();
}
