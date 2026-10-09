package Collections;

import Videos.Episode;
import Podcast.*;
import dataStructures.*;

/**
 * This class implements the PodcastCollection and all the methods in it
 */
public class PodcastCollectionClass implements PodcastCollection {
    private final Array<Podcast> podcasts;

    public PodcastCollectionClass() {
        podcasts = new ArrayClass<>();
    }

    @Override
    public void createPodcast(String title, String author, String code) {
        podcasts.insertLast(new PodcastClass(title, author, code));
    }

    @Override
    public boolean isExistingPodcast(String title) {
        int i = 0;
        boolean found = false;
        while (i < podcasts.getSize() && !found) {
            if (podcasts.get(i).getTitle().equalsIgnoreCase(title))
                found = true;
            i++;
        }
        return found;
    }

    @Override
    public Podcast getPodcast(String title) {
        int i = 0;
        Podcast podcast = null;
        while (i < podcasts.getSize() && podcast == null) {
            if (podcasts.get(i).getTitle().equalsIgnoreCase(title))
                podcast = podcasts.get(i);
            i++;
        }
        return podcast;
    }

    @Override
    public boolean isExistingEpisode(String id) {
        int i = 0;
        boolean found = false;
        while (i < podcasts.getSize() && !found) {
            Iterator<Episode> it = podcasts.get(i).getEpisodes();
            while (it.hasNext() && !found) {
                Episode ep = it.next();
                if (ep.getId().equalsIgnoreCase(id))
                    found = true;
            }
            i++;
        }
        return found;
    }

    @Override
    public boolean isDateEarlier(String podcast, String date) {
        return getPodcast(podcast).isDateEarlier(date);
    }

    @Override
    public boolean hasEpisodes(String title) {
        Podcast p = getPodcast(title);
        return p != null && p.hasEpisodes();
    }

    @Override
    public Iterator<Episode> getPodcastEpisodes(String title) {
        Podcast p = getPodcast(title);
        return p.getEpisodes();
    }

    public void removePodcast(Podcast podcast) {
        int idx = podcasts.searchIndexOf(podcast);
        podcasts.removeAt(idx);
    }


}
