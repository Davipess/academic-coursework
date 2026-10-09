package Videos;

import Subtitles.*;

import java.util.Iterator;
import java.util.LinkedList;


/**
 * This class implements the PremiumVideo interface and all the methods in it
 */
public class PremiumVideoClass extends AbstractPublishable implements PremiumVideo{
    private final LinkedList<Subtitle> subtitles;

    public PremiumVideoClass
            (String id, int duration, String url, String publisher, String title, String code, String subUrl,
             String subtitle) {
        super(id, duration, url, publisher, title, code);
        this.subtitles = new LinkedList<>();
        addSubtitle(subUrl, subtitle);
    }

    @Override
    public void addSubtitle(String url, String code){
        subtitles.add(new SubtitleClass(url, code));
    }

    @Override
    public Iterator<Subtitle> getSubtitles() {
        return subtitles.iterator();
    }
}
