package Collections;

import Author.*;
import Podcast.Podcast;
import Show.*;
import Videos.Publishable;
import Videos.Video;
import dataStructures.*;

/**
 * This class implements the AuthorCollection interface and all its methods
 */
public class AuthorCollectionClass implements AuthorCollection {

    private final Array<Author> authors;
    private final Array<Show> shows;

    public AuthorCollectionClass() {
        this.authors = new ArrayClass<>();
        this.shows = new ArrayClass<>();
    }

    @Override
    public Author getAuthor(String name) {
        int i = 0;
        Author author = null;
        while (i < authors.getSize() && author == null) {
            if (authors.get(i).getName().equalsIgnoreCase(name)) {
                author = authors.get(i);
            }
            i++;
        }
        return author;
    }

    @Override
    public void addAuthor(String name) {
        Author author = new AuthorClass(name);
        authors.insertLast(author);
    }

    @Override
    public boolean isAuthorExistant(String author) {
        boolean found = false;
        for (int i = 0; i < authors.getSize(); i++) {
            if (authors.get(i).getName().equalsIgnoreCase(author)) {
                found = true;
            }
        }
        return found;
    }

    @Override
    public void addShowToAuthor(String authorName, String transmissionDate, Video video) {
        Author author = getAuthor(authorName);
        Show show = new ShowClass(author.getName(),transmissionDate, video);
        if (!isAuthorExistant(authorName)){
            authors.insertLast(author);
        }
        author.addShow(show);
    }

    @Override
    public void removeShowFromAuthor(Show show) {
        if (show != null){
            if (show.video() instanceof Publishable) {
                String authorName = ((Publishable) show.video()).getPublisher();
                Author author = getAuthor(authorName);
                if (author != null)
                    author.removeShow(show);
            }
        }
    }

    @Override
    public boolean authorHasPodcasts(String authorName){
        if (!isAuthorExistant(authorName))
            return false;
        Author author = getAuthor(authorName);
        return author.hasPodcasts();
    }

    @Override
    public Iterator<Podcast> podcastIterator(Author author){
        return author.podcastIterator();
    }
}
