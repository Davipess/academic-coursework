package Author;

import Content.Podcast;
import Content.PodcastGetters;
import Content.Show;
import Content.ShowGetters;

import java.util.*;

/**
 * This class implements the Author interface and all its methods
 */
public class AuthorClass implements Author {

    private final String name;
    private final List<PodcastGetters> pods;
    private final SortedSet<ShowGetters> shows;

    public AuthorClass(String name) {
        this.name = name;
        this.pods = new LinkedList<>();
        this.shows = new TreeSet<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean hasPodcasts() {
        return !pods.isEmpty();
    }

    @Override
    public void addPodcast(Podcast pod) {
        pods.addLast(pod);
    }

    @Override
    public void removePodcast(Podcast pod) {
        pods.remove(pod);
    }

    @Override
    public boolean hasShows() {
        return !shows.isEmpty();
    }

    @Override
    public void addShow(Show show) {
        shows.add(show);
    }

    @Override
    public void removeShow(Show show) {
        shows.remove(show);
    }

    @Override
    public Iterator<PodcastGetters> podcastIterator() {
        return pods.iterator();
    }

    @Override
    public Iterator<ShowGetters> showsIterator() {
        return shows.iterator();
    }

    @Override
    public int getProductivity() {
        return pods.size() + shows.size();
    }

    @Override
    public int compareTo(Author other) {
        int productivityThis = this.getProductivity();
        int productivityOther = other.getProductivity();

        if (productivityThis != productivityOther) {
            return productivityOther - productivityThis;
        }
        return name.compareTo(other.getName());
    }
}
