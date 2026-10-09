package Content;

/**
 * This interface represents a generic pice of content, it can either be a show or podcast that contains a list of tags
 */
public interface Content extends ContentGetters {

    /**
     * Adds a Tag object to a Content Object.
     *
     * @param tag the tag to be added
     */
    void addTag(Tag tag);

    /**
     * Removes a Tag object from a Content Object.
     *
     * @param tag teh tag to be removed
     */
    void removeTag(Tag tag);

}
