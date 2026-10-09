package Podcast;

import dataStructures.Array;
import dataStructures.Iterator;

class ReverseIteratorClass<E> implements Iterator<E> {
    private final Array<E> array;
    private int current;

    public ReverseIteratorClass(Array<E> array) {
        this.array = array;
        this.current = array.getSize() - 1;
    }

    @Override
    public boolean hasNext() {
        return current >= 0;
    }

    @Override
    public E next() {
        return array.get(current--);
    }
}
