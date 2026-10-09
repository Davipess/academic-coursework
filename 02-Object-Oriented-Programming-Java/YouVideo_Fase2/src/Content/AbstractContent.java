package Content;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * This abstract class implements all the methods in Content and ContentGetters interfaces, it is never initialized
 */
abstract class AbstractContent implements Content {
    private final SortedSet<Tag> tags;

    public AbstractContent() {
        tags = new TreeSet<>();
    }

    @Override
    public boolean isTagged() {
        return !tags.isEmpty();
    }

    @Override
    public void addTag(Tag tag) {
        tags.add(tag);
    }

    @Override
    public boolean hasTag(String tagName) {
        boolean found = false;
        for (Tag tag : tags) {
            if (tag.name().equalsIgnoreCase(tagName))
                found = true;
        }
        return found;
    }

    @Override
    public void removeTag(Tag tag) {
        tags.remove(tag);
    }

    @Override
    public Iterator<Tag> getTags() {
        return tags.iterator();
    }
}
