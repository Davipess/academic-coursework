package Author;

import Content.Podcast;
import Content.Show;

/**
 * This interface represents an author with a collection of podcasts and shows
 */
public interface Author extends AuthorGetters{

    /**
     * Checks if the author has podcasts
     * @return true if the author has podcasts and false otherwise
     */
    boolean hasPodcasts();

    /**
     * Adds a podcast into the array of podcasts of the author
     * @param pod the podcast to
     */
    void addPodcast(Podcast pod);

    /**
     * Removes a podcast from the collection of podcasts of the author
     * @param pod the podcast to be removed
     */
    void removePodcast(Podcast pod);


    /**
     * Adds a show into the array of shows of the author
     * @param show the show to be added
     */
    void addShow(Show show);

    /**
     * Removes a show from the collection of shows of the author
     * @param show the show to be removed
     */
    void removeShow(Show show);

}
