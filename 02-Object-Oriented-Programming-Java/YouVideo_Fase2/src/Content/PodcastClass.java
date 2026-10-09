package Content;

import Exceptions.EpisodeAlreadyExistsException;
import Exceptions.PodcastAlreadyExistsException;
import Videos.Episode;
import Videos.EpisodeClass;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * This class implements the Podcast interface and all its methods
 */

public class PodcastClass extends AbstractContent implements Podcast {
    private final String title, author, code;

    private final List<Episode> episodes;

    public PodcastClass(String title, String author, String code) {
        this.title = title;
        this.author = author;
        this.code = code;
        episodes = new LinkedList<>();
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void addEpisode(String id, int duration, String url, LocalDate date) throws EpisodeAlreadyExistsException {
        Episode episode = new EpisodeClass(id, duration, url, date);
        if (episodes.contains(episode))
            throw new EpisodeAlreadyExistsException();
        episodes.addLast(episode);
    }

    @Override
    public boolean hasEpisodes() {
        return !episodes.isEmpty();
    }

    @Override
    public Episode getLastEpisode() {
        return episodes.getLast();
    }

    @Override
    public boolean isDateEarlier(LocalDate date) {
        if (episodes.isEmpty())
            return false;
        return date.isBefore(getLastEpisode().getDate());
    }

    @Override
    public Iterator<Episode> getEpisodes() {
        return episodes.reversed().iterator();
    }

    @Override
    public int compareTo(Content other) {
        if (other instanceof PodcastGetters)
            return getTitle().compareToIgnoreCase(other.getTitle());
        else return 1;
    }
}
