package Collections;

import Show.*;
import Videos.Publishable;
import Videos.Video;
import dataStructures.Array;
import dataStructures.ArrayClass;

public class ShowCollectionClass implements ShowCollection {

    private final Array<Show> shows;

    public ShowCollectionClass() {
        shows = new ArrayClass<>();
    }

    @Override
    public void createShow(String authorName, String transmissionDate, Video video) {
        shows.insertLast(new ShowClass(authorName, transmissionDate, video));
    }

    @Override
    public void removeShow(String showTitle) {
        Show show = getShow(showTitle);
        int idx = shows.searchIndexOf(show);
        shows.removeAt(idx);
    }

    @Override
    public Show getShow(String showTitle) {
        int i = 0;
        Show show = null;
        while (i < shows.getSize() && show == null) {
            if (shows.get(i).getShowTitle().equalsIgnoreCase(showTitle)){
                show = shows.get(i);
            }
            i++;
        }
        return show;
    }

    @Override
    public boolean isShow(Video video) {
        int i = 0;
        boolean found = false;
        while (i < shows.getSize() && !found) {
            if (video instanceof Publishable) {
                String title = ((Publishable) video).getTitle();
                if (shows.get(i).getShowTitle().equalsIgnoreCase(title)){
                    found = true;
                }
            }
            i++;
        }
        return found;
    }

    @Override
    public boolean isExistingShow(String showTitle) {
        int i = 0;
        boolean found = false;
        while (i < shows.getSize() && !found) {
            if (shows.get(i).getShowTitle().equalsIgnoreCase(showTitle)){
                found = true;
            }
            i++;
        }
        return found;
    }
}
