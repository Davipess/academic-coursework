package Collections;


import Content.*;
import Exceptions.InvalidParametersException;
import Exceptions.TagDoesNotExistException;
import Exceptions.TitleIsNotTaggedException;

import java.util.*;

public class TagCollectionClass implements TagCollection {
    private final Map<Tag, SortedSet<ContentGetters>> taggedContent;
    private final Map<Tag, SortedSet<ContentGetters>> taggedPodcasts;
    private final Map<Tag, SortedSet<ContentGetters>> taggedShows;

    private static final Comparator<ContentGetters> CONTENT_ORDER_ASC =
            Comparator.comparing(ContentGetters::getTitle, String.CASE_INSENSITIVE_ORDER)
                    .thenComparing(content -> content instanceof PodcastGetters ? 1 : 0);

    private static final Comparator<ContentGetters> CONTENT_ORDER_DES =
            Comparator.comparing(ContentGetters::getTitle, String.CASE_INSENSITIVE_ORDER).reversed()
                    .thenComparing(content -> content instanceof PodcastGetters ? 1 : 0);


    private enum types {
        ALL(), PODCAST(), SHOW();

        types() {
        }
    }

    private enum orders {
        DES(), ASC();

        orders() {
        }
    }

    public TagCollectionClass() {
        taggedContent = new HashMap<>(1000, .75f);
        taggedPodcasts = new HashMap<>(500, .75f);
        taggedShows = new HashMap<>(500, .75f);
    }

    @Override
    public void addTag(String tagName, Content content) {
        Tag tag = getTag(tagName);
        if (tag == null) {
            tag = new TagClass(tagName);
            taggedContent.put(tag, new TreeSet<>(CONTENT_ORDER_ASC));
        }
        content.addTag(tag);
        taggedContent.get(tag).add(content);

        if (content instanceof Podcast) {
            if (!taggedPodcasts.containsKey(tag))
                taggedPodcasts.put(tag, new TreeSet<>(CONTENT_ORDER_ASC));
            taggedPodcasts.get(tag).add(content);
        } else {
            if (!taggedShows.containsKey(tag))
                taggedShows.put(tag, new TreeSet<>(CONTENT_ORDER_ASC));
            taggedShows.get(tag).add(content);
        }
    }

    @Override
    public void removeTag(String tagName, Content content) throws TitleIsNotTaggedException {
        Tag tag = getTag(tagName);
        if (tag == null)
            throw new TitleIsNotTaggedException();

        content.removeTag(tag);
        taggedContent.get(tag).remove(content);
        if (taggedContent.get(tag).isEmpty()) {
            taggedContent.remove(tag);
        }

        if (content instanceof Show) {
            if (taggedShows.containsKey(tag)) {
                taggedShows.get(tag).remove(content);
                if (taggedShows.get(tag).isEmpty())
                    taggedShows.remove(tag);
            }
        } else {
            if (taggedPodcasts.containsKey(tag)) {
                taggedPodcasts.get(tag).remove(content);
                if (taggedPodcasts.get(tag).isEmpty())
                    taggedPodcasts.remove(tag);
            }
        }
    }

    /**
     * Gets a tag given its name
     * @param tagName teh name of the tag
     * @return a tag with the name given if exists or null
     */
    private Tag getTag(String tagName) {
        for (Tag tag : taggedContent.keySet()) {
            if (tag.name().equalsIgnoreCase(tagName))
                return tag;
        }
        return null;
    }

    @Override
    public Iterator<ContentGetters> contentIterator(String tagName, String contentType, String order)
            throws TagDoesNotExistException, InvalidParametersException {
        types t;
        orders o;
        try {
            t = types.valueOf(contentType.toUpperCase());
            o = orders.valueOf(order.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidParametersException();
        }
        Tag tag = getTag(tagName);

        SortedSet<ContentGetters> set = null;
        switch (t) {
            case ALL -> set = taggedContent.get(tag);
            case PODCAST -> set = taggedPodcasts.get(tag);
            case SHOW -> set = taggedShows.get(tag);
        }
        if (set == null)
            throw new TagDoesNotExistException();
        return getOrderedIterator(set, o);
    }

    /**
     * Gets the correct ordered iterator from the set based on the order given
     *
     * @param set   the sorted set that contains the iterator
     * @param order an enumerator that tells the order of the elements of the iterator
     * @return an ordered ContentGetters iterator
     */
    private Iterator<ContentGetters> getOrderedIterator(SortedSet<ContentGetters> set, orders order) {
        return switch (order) {
            case ASC -> set.iterator();
            case DES -> {
                SortedSet<ContentGetters> descending = new TreeSet<>(CONTENT_ORDER_DES);
                descending.addAll(set);
                yield descending.iterator();
            }
        };
    }
}
