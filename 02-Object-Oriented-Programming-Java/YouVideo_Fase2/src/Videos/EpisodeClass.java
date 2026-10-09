package Videos;

import java.time.LocalDate;

/**
 * This class implements the Episode interface and all it's methods
 */
public class EpisodeClass extends AbstractVideo implements Episode{

    private final LocalDate date;

    public EpisodeClass(String id, int duration, String url, LocalDate date){
        super(id, duration, url);
        this.date = date;
    }

    @Override
    public LocalDate getDate(){
        return date;
    }
}
