package Collections;

import Podcast.Podcast;
import Author.*;
import Show.*;
import Videos.Video;
import dataStructures.Iterator;

/**
 * This interface represents a collection of authors
 */
public interface AuthorCollection {

    /**
     * Gets an author with the same name as the one given
     * @param name the name of the author
     * @return the author with the same name as the one given
     */
    Author getAuthor(String name);

    /**
     * Adds an author into an array of authors
     * @param name the name of the author to be added
     */
    void addAuthor(String name);

    /**
     * Checks if there is an author with the same name as the one given
     * @param author the name to be checked
     * @return true if there is an author with the given name and false otherwise
     */
    boolean isAuthorExistant(String author);

    /**
     * Creates a new show and stores it into the array of shows in an author.
     * @param author the name of the author of the show
     * @param transmissionDate the transmission date of the show
     * @param video the video to be presented in the show
     */
    void addShowToAuthor(String author, String transmissionDate, Video video);

    /**
     * Removes a show given its title
     * @param Show the title of yhe show to be removed
     */
    void removeShowFromAuthor(Show Show);

    /**
     * Checks if a certain author has any podcasts
     * @param authorName the name of the author
     * @return true if the author has at least one podcast and false otherwise
     */
    boolean authorHasPodcasts(String authorName);

    /**
     * Gets all podcasts from a certain author
     * @param author the author
     * @return an Iterator with an array of all the podcasts from an author
     */
    Iterator<Podcast> podcastIterator(Author author);
}
