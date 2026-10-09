package Videos;

/**
 * This class contains all the information that any video, basic, premium or and episode,
 * should contain
 */
abstract class AbstractVideo implements Video {
    private final String id, url;
    private final int duration;

    public AbstractVideo(String id, int duration, String url) {
        this.id = id;
        this.duration = duration;
        this.url = url;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public String getUrl(){
        return url;
    }
}