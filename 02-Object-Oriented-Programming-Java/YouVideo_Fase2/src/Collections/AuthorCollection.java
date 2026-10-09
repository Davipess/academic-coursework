package Collections;

import Author.*;
import Content.PodcastGetters;
import Content.Show;
import Exceptions.AuthorHasNoPodcastsException;
import Exceptions.AuthorNotExistsException;

import java.util.Iterator;

/**
 * This interface represents and manages a collection of authors in the system.
 * It provides methods to retrieve, add, and query authors, as well as manage
 * their respective contents (Shows and Podcasts).
 */
public interface AuthorCollection {

    /**
     * Retrieves an author by their name.
     * @param name the name of the author to be retrieved.
     * @return the Author object matching the given name.
     * @throws AuthorNotExistsException if no author with the specified name is found in the collection.
     */
    Author getAuthor(String name) throws AuthorNotExistsException;

    /**
     * Adds a new author to the collection.
     * @param name the name of the author to be registered.
     */
    void addAuthor(String name);

    /**
     * Checks if an author with the specified name already exists in the collection.
     * @param author the name of the author to check.
     * @return true if the author exists in the system, false otherwise.
     */
    boolean isAuthorExistant(String author);

    /**
     * Associates a newly created show with its respective author.
     * @param show the Show object to be added to the author's record.
     */
    void addShowToAuthor(Show show) throws  AuthorNotExistsException;

    /**
     * Removes a show from its author's record.
     * @param show the Show object to be removed.
     */
    void removeShowFromAuthor(Show show) throws  AuthorNotExistsException;

    /**
     * Checks if a specific author has published any podcasts.
     * @param author the Author object to inspect.
     * @return true if the author has at least one podcast, false otherwise.
     */
    boolean authorHasPodcasts(Author author);

    /**
     * Retrieves an iterator over all podcasts created by a specific author.
     * @param author the Author object whose podcasts are to be retrieved.
     * @return a read-only Iterator of PodcastGetters representing the author's podcasts.
     * @throws AuthorHasNoPodcastsException if given author has no podcasts.
     */
    Iterator<PodcastGetters> podcastIterator(Author author) throws AuthorHasNoPodcastsException;

    /**
     * Checks if there are any productive authors (authors with at least one content) within the system.
     * @return true if there is at least one productive author, false otherwise.
     */
    boolean hasProductiveAuthors();

    /**
     * Retrieves all productive authors in the system, sorted descendingly by their amount of productivity
     * (total number of shows and podcasts).
     * @return a read-only Iterator of AuthorGetters.
     */
    Iterator<AuthorGetters> getAuthorsByProductivity();
}