package Videos;

import Subtitles.Subtitle;
import dataStructures.Iterator;

/**
 * This interface represents a premium video that can additionally store and array of codes of
 * subtitles
 */
public interface PremiumVideo extends Publishable{

    /**
     * Adds a subtitle to a premium video
     * @param url the subtitle url
     * @param code the subtitle code
     */
    void addSubtitle(String url, String code);

    /**
     * Iterates all the subtitle files from a premium video
     * @return all the subtitles from a premium video
     */
    Iterator<Subtitle> getSubtitles();
}
