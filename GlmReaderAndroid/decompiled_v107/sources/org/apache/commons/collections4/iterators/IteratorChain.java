package org.apache.commons.collections4.iterators;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/* loaded from: classes9.dex */
public class IteratorChain<E> implements Iterator<E> {
    private Iterator<? extends E> currentIterator;
    private boolean isLocked;
    private final Queue<Iterator<? extends E>> iteratorQueue = new LinkedList();
    private Iterator<? extends E> lastUsedIterator;

    public IteratorChain() {
    }

    public IteratorChain(Collection<? extends Iterator<? extends E>> iteratorQueue) {
        for (Iterator<? extends E> iterator : iteratorQueue) {
            addIterator(iterator);
        }
    }

    public IteratorChain(Iterator<? extends E> iterator) {
        addIterator(iterator);
    }

    public IteratorChain(Iterator<? extends E>... iteratorQueue) {
        for (Iterator<? extends E> element : iteratorQueue) {
            addIterator(element);
        }
    }

    public IteratorChain(Iterator<? extends E> first, Iterator<? extends E> second) {
        addIterator(first);
        addIterator(second);
    }

    public void addIterator(Iterator<? extends E> iterator) {
        checkLocked();
        this.iteratorQueue.add((Iterator) Objects.requireNonNull(iterator, "iterator"));
    }

    private void checkLocked() {
        if (this.isLocked) {
            throw new UnsupportedOperationException("IteratorChain cannot be changed after the first use of a method from the Iterator interface");
        }
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        lockChain();
        updateCurrentIterator();
        this.lastUsedIterator = this.currentIterator;
        return this.currentIterator.hasNext();
    }

    public boolean isLocked() {
        return this.isLocked;
    }

    private void lockChain() {
        if (!this.isLocked) {
            this.isLocked = true;
        }
    }

    @Override // java.util.Iterator
    public E next() {
        lockChain();
        updateCurrentIterator();
        this.lastUsedIterator = this.currentIterator;
        return this.currentIterator.next();
    }

    @Override // java.util.Iterator
    public void remove() {
        lockChain();
        if (this.currentIterator == null) {
            updateCurrentIterator();
        }
        this.lastUsedIterator.remove();
    }

    public int size() {
        return this.iteratorQueue.size();
    }

    protected void updateCurrentIterator() {
        if (this.currentIterator == null) {
            if (this.iteratorQueue.isEmpty()) {
                this.currentIterator = EmptyIterator.emptyIterator();
            } else {
                this.currentIterator = this.iteratorQueue.remove();
            }
            this.lastUsedIterator = this.currentIterator;
        }
        while (!this.currentIterator.hasNext() && !this.iteratorQueue.isEmpty()) {
            this.currentIterator = this.iteratorQueue.remove();
        }
    }
}
