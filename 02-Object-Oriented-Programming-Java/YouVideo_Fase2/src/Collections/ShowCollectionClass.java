package Collections;

import Content.Show;
import Content.ShowClass;
import Exceptions.ShowDoesNotExistException;
import Videos.Publishable;
import Videos.Video;

import java.time.LocalDate;
import java.util.*;

public class ShowCollectionClass implements ShowCollection {

    private final SortedMap<String, Show> shows;

    public ShowCollectionClass() {
        shows = new TreeMap<>();
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
        Iterator<String> it = shows.keySet().iterator();
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
    public Show createShow(String authorName, LocalDate transmissionDate, Video video) {
        if (video instanceof Publishable) {
            Show show = new ShowClass(authorName, transmissionDate, video);
            shows.put(((Publishable) video).getTitle(),
                    show);
            return show;
        }
        return null;
    }

    @Override
    public void removeShow(Show show) {
        shows.remove(show.getTitle(), show);
    }

    @Override
    public Show getShow(String showTitle) throws ShowDoesNotExistException {
        if (!isExistingShow(showTitle))
            throw new ShowDoesNotExistException();
        return shows.get(getOriginalKey(showTitle));
    }

    @Override
    public boolean isExistingShow(String showTitle) {
        return shows.containsKey(getOriginalKey(showTitle));
    }
}
