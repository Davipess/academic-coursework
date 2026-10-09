package Show;

import Videos.Publishable;
import Videos.Video;

/**
 * This record class implements the Show interface and all its parameters
 * @param authorName the name of the author who created the show
 * @param transmissionDate the transmission date of the show
 * @param video the video addressed to the show
 */
public record ShowClass(String authorName, String transmissionDate, Video video) implements Show {

    @Override
    public String getShowTitle() {
        if (video instanceof Publishable) {
            return ((Publishable) video).getTitle();
        }
        return null;
    }
}
