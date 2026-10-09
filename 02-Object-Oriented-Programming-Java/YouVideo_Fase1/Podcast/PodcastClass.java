package Podcast;

import Videos.Episode;
import Videos.EpisodeClass;
import dataStructures.*;

/**
 * This class implements the Podcast interface and all its methods
 */
public class PodcastClass implements Podcast{
    private final String title, author, code;

    private final Array<Episode> episodes;

    public PodcastClass(String title, String author, String code){
        this.title = title;
        this.author = author;
        this.code = code;
        episodes = new ArrayClass<>();
    }

    @Override
    public String getTitle(){
        return title;
    }

    @Override
    public String getAuthor(){
        return author;
    }

    @Override
    public String getCode(){
        return code;
    }

    @Override
    public void addEpisode(String id, int duration, String url, String date){
        episodes.insertLast(new EpisodeClass(id, duration, url, date));
    }

    @Override
    public boolean hasEpisodes() {
        return episodes.getSize() > 0;
    }

    @Override
    public Episode getLastEpisode(){
        int lastIdx = episodes.getSize() - 1;
        return episodes.get(lastIdx);
    }

    @Override
    public boolean isDateEarlier(String date){
        if (episodes.getSize() == 0)
            return false;
        return date.compareTo(getLastEpisode().getDate()) < 0;
    }

    @Override
    public Iterator<Episode> getEpisodes(){
        return new ReverseIteratorClass<>(episodes);
    }
}
