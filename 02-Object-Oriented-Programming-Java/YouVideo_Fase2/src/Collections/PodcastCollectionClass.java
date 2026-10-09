package Collections;

import Content.Podcast;
import Content.PodcastClass;
import Videos.Episode;
import Exceptions.*;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;

public class PodcastCollectionClass implements PodcastCollection {
    private final SortedMap<String, Podcast> podcasts;

    public PodcastCollectionClass() {
        podcasts = new TreeMap<>();
    }

    @Override
    public Podcast createPodcast(String title, String author, String code) throws PodcastAlreadyExistsException {
        if (isExistingPodcast(title)) {
            throw new PodcastAlreadyExistsException();
        }

        Podcast pod = new PodcastClass(title, author, code);
        podcasts.put(title, pod);
        return  pod;
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
        Iterator<String> it = podcasts.keySet().iterator();
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
    public boolean isExistingPodcast(String title) {
        return podcasts.containsKey(getOriginalKey(title));
    }

    @Override
    public Podcast getPodcast(String title) throws PodcastDoesNotExistException {
        if (!isExistingPodcast(title)) {
            throw new PodcastDoesNotExistException();
        }
        return podcasts.get(getOriginalKey(title));
    }

    @Override
    public void addEpisode(String podTitle, String id, int duration, String url, LocalDate date)
            throws InvalidDurationException, EpisodeAlreadyExistsException,
            DateIsEarlierException, PodcastDoesNotExistException {
        if (duration <= 0) {
            throw new InvalidDurationException();
        }
        Podcast pod = getPodcast(podTitle);
        if (isDateEarlier(pod, date)) {
            throw new DateIsEarlierException();
        }
        pod.addEpisode(id, duration, url, date);
    }

    @Override
    public boolean isExistingEpisode(String id) {
        for (Podcast pod : podcasts.values()) {
            Iterator<Episode> it = pod.getEpisodes();
            while (it.hasNext()) {
                Episode ep = it.next();
                if (ep.getId().equalsIgnoreCase(id))
                    return true;
            }
        }
        return false;
    }

    @Override
    public boolean isDateEarlier(Podcast pod, LocalDate date) {
        return pod.isDateEarlier(date);
    }

    @Override
    public Iterator<Episode> getPodcastEpisodes(String title) throws PodcastDoesNotExistException, NoEpisodesException{
        if (!podcasts.containsKey(getOriginalKey(title))) {
            throw new PodcastDoesNotExistException();
        }
        Podcast p = getPodcast(title);

        if (!p.hasEpisodes()) {
            throw new NoEpisodesException();
        }

        return p.getEpisodes();
    }

    @Override
    public void removePodcast(Podcast podcast) throws PodcastDoesNotExistException {
        if (podcast == null || !podcasts.containsKey(podcast.getTitle())) {
            throw new PodcastDoesNotExistException();
        }
        podcasts.remove(podcast.getTitle(), podcast);
    }
}