package Videos;

import Subtitles.*;
import dataStructures.*;


/**
 * This class implements the PremiumVideo interface and all the methods in it
 */
public class PremiumVideoClass extends AbstractPublishable implements PremiumVideo{
    private final Array<Subtitle> subtitles;

    public PremiumVideoClass
            (String id, int duration, String url, String publisher, String title, String code, String subUrl,
             String subtitle) {
        super(id, duration, url, publisher, title, code);
        this.subtitles = new ArrayClass<>();
        addSubtitle(subUrl, subtitle);
    }

    @Override
    public void addSubtitle(String url, String code){
        subtitles.insertLast(new SubtitleClass(url, code));
    }

    @Override
    public Iterator<Subtitle> getSubtitles() {
        return subtitles.iterator();
    }



}
