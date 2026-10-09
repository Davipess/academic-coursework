package Author;

import Podcast.Podcast;
import Show.Show;
import dataStructures.Array;
import dataStructures.ArrayClass;
import dataStructures.Iterator;

/**
 * This class implements the Author interface and all its methods
 */
public class AuthorClass implements Author {

    private final String name;
    private final Array<Podcast> pods;
    private final Array<Show> shows;

    public AuthorClass(String name) {
        this.name = name;
        this.pods = new ArrayClass<>();
        this.shows = new ArrayClass<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean hasPodcasts() {
        return pods.getSize() > 0;
    }

    @Override
    public void addPodcast(Podcast pod) {
        pods.insertLast(pod);
    }

    @Override
    public void removePodcast(Podcast pod) {
        int idx = pods.searchIndexOf(pod);
        pods.removeAt(idx);
    }

    @Override
    public void addShow(Show show) {
        shows.insertLast(show);
    }

    @Override
    public void removeShow(Show show) {
        int index = shows.searchIndexOf(show);
        shows.removeAt(index);
    }

    @Override
    public Iterator<Podcast> podcastIterator() {
        return pods.iterator();
    }

}
