package Collections;
import Content.Content;
import Content.ContentGetters;
import Exceptions.InvalidParametersException;
import Exceptions.TagDoesNotExistException;
import Exceptions.TitleIsNotTaggedException;

import java.util.Iterator;

/**
 * This interface represents a collection of content that is tagged (either shows or podcasts)
 */
public interface TagCollection {

    /**
     * Adds a tag to a certain content type (also creates one if the tag doesn't exist)
     * @param tagName the name of the tag
     * @param content the content which will be added the tag
     */
    void addTag(String tagName, Content content);

    /**
     * Removes a tag from a certain content type
     * @param tagName the name of the tag
     * @param content teh content whose tag will be removed
     * @throws TitleIsNotTaggedException if the content is not tagged with the given tag
     */
    void removeTag(String tagName, Content content) throws TitleIsNotTaggedException;

    /**
     * Gets all the content (Shows, Podcasts or both) tagged with a certain tag
     * @param tagName the tag name
     * @param contentType the type of content (show, podcast or both)
     * @param order the order of the objects to be shown, ascending or descending
     * @return an iterator that iterates through a set of Contents
     * @throws TagDoesNotExistException if there is no tag with such name
     * @throws InvalidParametersException if either the content type or the order are invalid
     */
    Iterator<ContentGetters> contentIterator(String tagName, String contentType, String order)
            throws TagDoesNotExistException, InvalidParametersException;
}
