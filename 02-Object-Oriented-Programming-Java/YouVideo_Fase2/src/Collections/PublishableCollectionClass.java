package Collections;

import java.util.*;

import Exceptions.*;
import Videos.*;

public class PublishableCollectionClass implements PublishableCollection {
    private final Map<String, Video> publishables;

    public PublishableCollectionClass() {
        publishables = new HashMap<>(5000, .75f);
    }

    @Override
    public boolean isValidCode(String code) {
        for (String isoLanguage : Locale.getISOLanguages()) {
            if (code.equalsIgnoreCase(isoLanguage)) {
                return true;
            }
        }
        return false;
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
        Iterator<String> it = publishables.keySet().iterator();
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
    public boolean isExistingVideo(String videoId) {
        return publishables.containsKey(getOriginalKey(videoId));
    }

    @Override
    public Video getVideo(String id) throws VideoDoesNotExistException {
        if (isExistingVideo(id)) {
            return publishables.get(getOriginalKey(id));
        }
        throw new VideoDoesNotExistException();
    }

    @Override
    public void createPublishable(String id, int duration, String url, String publisherName,
                                  String title, String code) throws InvalidCodeException,
            InvalidDurationException, VideoAlreadyExistsException {
        if (!isValidCode(code)) throw new InvalidCodeException();
        if (duration <= 0) throw new InvalidDurationException();
        if (isExistingVideo(id)) throw new VideoAlreadyExistsException();

        publishables.put(id, new BasicVideoClass(id, duration, url, publisherName, title, code));
    }

    @Override
    public void createPremium(String id, int duration, String url, String publisherName,
                              String title, String code, String subtitleUrl, String subtitleCode)
            throws InvalidCodeException, InvalidDurationException, VideoAlreadyExistsException,
            InvalidSubtitleLanguageException {
        if (!isValidCode(code)) throw new InvalidCodeException();
        if (!isValidCode(subtitleCode)) throw new InvalidSubtitleLanguageException();
        if (duration <= 0) throw new InvalidDurationException();
        if (isExistingVideo(id)) throw new VideoAlreadyExistsException();

        publishables.put(id, new PremiumVideoClass(id, duration, url, publisherName, title,
                code, subtitleUrl, subtitleCode));
    }

    @Override
    public void addSubtitle(String videoId, String url, String code) throws InvalidCodeException,
            VideoIsNotPremiumException, VideoDoesNotExistException {
        if (!isValidCode(code)) throw new InvalidCodeException();
        Video video = getVideo(videoId);
        if (!(video instanceof PremiumVideo)) {
            throw new VideoIsNotPremiumException();
        }
        ((PremiumVideo) video).addSubtitle(url, code);
    }

    @Override
    public void removeVideo(Video video) {
        publishables.remove(video.getId(), video);
    }
}