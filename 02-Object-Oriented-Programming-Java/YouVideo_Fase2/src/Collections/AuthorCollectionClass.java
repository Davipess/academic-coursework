package Collections;

import Author.*;
import Content.PodcastGetters;
import Content.Show;
import Exceptions.AuthorHasNoPodcastsException;
import Exceptions.AuthorNotExistsException;

import java.util.*;

/**
 * This class implements the AuthorCollection interface and all its methods
 */
public class AuthorCollectionClass implements AuthorCollection {

    private final Map<String, Author> authors;

    public AuthorCollectionClass() {
        this.authors = new HashMap<>(500, .75f);
    }

    /**
     * Gets the key from the first time an object was inserted into the publishables Map
     *
     * @param str a string that corresponds to an object in the keyMap Map that will be used
     *            to get the key from the publishables Map
     * @return the initial string from the publishables Map
     */
    private String getOriginalKey(String str) {
        boolean found = false;
        Iterator<String> it = authors.keySet().iterator();
        while (it.hasNext() && !found) {
            String original = it.next();
            if (str.equalsIgnoreCase(original)) {
                found = true;
                str = original;
            }
        }
        return str;
    }

    @Override
    public Author getAuthor(String name) throws AuthorNotExistsException {
        if (!isAuthorExistant(name))
            throw new AuthorNotExistsException();
        return authors.get(getOriginalKey(name));
    }

    @Override
    public void addAuthor(String name) {
        Author author = new AuthorClass(name);
        authors.put(name, author);
    }

    @Override
    public boolean isAuthorExistant(String author) {
        return authors.containsKey(getOriginalKey(author));
    }

    @Override
    public void addShowToAuthor(Show show) throws AuthorNotExistsException {
        Author author = getAuthor(show.getAuthor());
        authors.put(show.getAuthor(), author);
        author.addShow(show);
    }

    @Override
    public void removeShowFromAuthor(Show show) throws AuthorNotExistsException {
        Author author = getAuthor(show.getAuthor());
        author.removeShow(show);
    }

    @Override
    public boolean authorHasPodcasts(Author author) {
        return author.hasPodcasts();
    }

    @Override
    public Iterator<PodcastGetters> podcastIterator(Author author) throws AuthorHasNoPodcastsException {
        if (!authorHasPodcasts(author))
            throw new AuthorHasNoPodcastsException();
        return author.podcastIterator();
    }

    @Override
    public boolean hasProductiveAuthors() {
        boolean hasProductiveAuthor = false;
        Iterator<Author> it = authors.values().iterator();
        while (it.hasNext() && !hasProductiveAuthor) {
            Author author = it.next();
            if (author.getProductivity() > 0)
                hasProductiveAuthor = true;
        }
        return hasProductiveAuthor;
    }

    @Override
    public Iterator<AuthorGetters> getAuthorsByProductivity() {
        SortedSet<AuthorGetters> authorsByProductivity = new TreeSet<>(authors.values());
        return authorsByProductivity.iterator();
    }
}
