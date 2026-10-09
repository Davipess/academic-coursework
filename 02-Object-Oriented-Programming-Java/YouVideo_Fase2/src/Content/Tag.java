package Content;

/**
 * This interface symbolizes a Tag object that can be added to a show or a video.
 */
public interface Tag extends Comparable<Tag>{

    /**
     * @return tags name
     */
    String name();
}
