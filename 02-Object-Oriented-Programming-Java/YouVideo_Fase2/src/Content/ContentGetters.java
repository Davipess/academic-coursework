package Content;

import java.util.Iterator;

/**
 * A Read-Only interface for Content objects.
 * This interface exposes methods to retrieve information and inspect the state of the content
 * without allowing any modifications to its internal data, ensuring proper encapsulation.
 */
public interface ContentGetters extends Comparable<Content> {

    /**
     * Gets the name of the author of the content.
     *
     * @return the name of the author.
     */
    String getAuthor();

    /**
     * Returns a read-only iterator over the tags associated with the content.
     *
     * @return an Iterator of Tag objects.
     */
    Iterator<Tag> getTags();

    /**
     * Gets the title of the content.
     *
     * @return the title of the content.
     */
    String getTitle();

    /**
     * Checks if the content has any categorization tags associated with it.
     *
     * @return true if the content has at least one tag, false otherwise.
     */
    boolean isTagged();

    /**
     * Checks if the content is associated with a specific tag.
     *
     * @param tagName the name of the tag to look for.
     * @return true if the content has the specified tag, false otherwise.
     */
    boolean hasTag(String tagName);

}