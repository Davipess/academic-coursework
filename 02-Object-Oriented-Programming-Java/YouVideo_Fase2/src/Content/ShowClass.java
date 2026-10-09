package Content;

import Videos.Publishable;
import Videos.Video;

import java.time.LocalDate;

/**
 * This record class implements the Show interface and all its parameters
 */
public class ShowClass extends AbstractContent implements Show {
    private final String name;
    private final LocalDate date;
    private final Video video;

    public ShowClass(String name, LocalDate date, Video video){
        this.name = name;
        this.date = date;
        this.video = video;
    }

    @Override
    public String getAuthor(){
        return name;
    }

    @Override
    public LocalDate getTransmissionDate(){
        return date;
    }

    @Override
    public Video getVideo(){
        return video;
    }

    @Override
    public String getTitle() {
        if (video instanceof Publishable) {
            return ((Publishable) video).getTitle();
        }
        return null; // This will never happen.
    }

    @Override
    public String getLanguageCode(){
        if (video instanceof Publishable)
            return ((Publishable) video).getCode();
        return null; // This will never happen.
    }

    @Override
    public int compareTo(Content other){
        if (other instanceof ShowGetters){
            if (!this.date.isEqual(((ShowGetters) other).getTransmissionDate()))
                return this.date.compareTo(((ShowGetters) other).getTransmissionDate());
            else
                return this.getTitle().compareToIgnoreCase(other.getTitle());
        } return -1;
    }
}
