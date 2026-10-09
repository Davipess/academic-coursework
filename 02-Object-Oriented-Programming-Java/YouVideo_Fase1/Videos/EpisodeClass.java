package Videos;

/**
 * This class implements the Episode interface and all it's methods
 */
public class EpisodeClass extends AbstractVideo implements Episode{

    private final String date;

    public EpisodeClass(String id, int duration, String url, String date){
        super(id, duration, url);
        this.date = date;
    }

    @Override
    public String getDate(){
        return date;
    }
}
