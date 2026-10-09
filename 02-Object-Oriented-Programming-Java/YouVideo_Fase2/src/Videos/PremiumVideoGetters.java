package Videos;

import Subtitles.Subtitle;

import java.util.Iterator;

/**
 * A read-only interface for PremiumVideo objects.
 */
public interface PremiumVideoGetters extends Publishable {

    /**
     * Iterates all the subtitle files from a premium video
     * @return all the subtitles from a premium video
     */
    Iterator<Subtitle> getSubtitles();
}
