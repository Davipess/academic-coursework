package Collections;

import java.util.Locale;
import Videos.*;
import dataStructures.*;

/**
 * This class provides the implementation of the PublishableCollection interface
 */
public class PublishableCollectionClass implements PublishableCollection {
    private final Array<Video> videos;

    public PublishableCollectionClass(){
        videos = new ArrayClass<>();
    }

    @Override
    public Video getVideo(String id){
        int i = 0;
        Video video = null;
        while(i < videos.getSize() && video == null){
            if(videos.get(i).getId().equalsIgnoreCase(id)) {
                video = videos.get(i);
            }
            i++;
        }
        return video;
    }

    @Override
    public boolean isValidCode(String code) {
        int i = 0;
        boolean isValid = false;
        while (i < Locale.getISOLanguages().length && !isValid) {
            if (code.equalsIgnoreCase(Locale.getISOLanguages()[i]))
                isValid = true;
            i++;
        }
        return isValid;
    }

    @Override
    public boolean isValidDuration(int duration){
        return duration > 0;
    }

    @Override
    public boolean isExistingVideo(String videoId){
        return getVideo(videoId) != null;
    }

    @Override
    public void createPublishable
            (String id, int duration, String url, String publisherName, String title, String code) {
        videos.insertLast(new BasicVideoClass(id, duration, url, publisherName,title, code));
    }

    @Override
    public void createPremium
            (String id, int duration, String url, String publisherName, String title, String code, String subtitleUrl,
             String subtitleCode) {
        videos.insertLast(new PremiumVideoClass(id, duration, url, publisherName,title, code, subtitleUrl, subtitleCode));
    }

    @Override
    public boolean isPremiumVideo(String videoId){
        return getVideo(videoId) instanceof PremiumVideo;
    }

    @Override
    public void addSubtitle(String videoId, String url, String code) {
        Video v = getVideo(videoId);
        if (v instanceof PremiumVideo) {
            ((PremiumVideo) v).addSubtitle(url, code);
        }
    }
    
    @Override
    public void removeVideo(String id){
        int index = videos.searchIndexOf(getVideo(id));
        videos.removeAt(index);
    }

}
