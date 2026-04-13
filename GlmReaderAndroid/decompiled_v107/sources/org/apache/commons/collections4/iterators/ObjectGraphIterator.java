package org.apache.commons.collections4.iterators;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.apache.commons.collections4.Transformer;

/* loaded from: classes9.dex */
public class ObjectGraphIterator<E> implements Iterator<E> {
    private Iterator<? extends E> currentIterator;
    private E currentValue;
    private boolean hasNext;
    private Iterator<? extends E> lastUsedIterator;
    private E root;
    private final Deque<Iterator<? extends E>> stack;
    private final Transformer<? super E, ? extends E> transformer;

    public ObjectGraphIterator(E root, Transformer<? super E, ? extends E> transformer) {
        this.stack = new ArrayDeque(8);
        if (root instanceof Iterator) {
            this.currentIterator = (Iterator) root;
        } else {
            this.root = root;
        }
        this.transformer = transformer;
    }

    public ObjectGraphIterator(Iterator<? extends E> rootIterator) {
        this.stack = new ArrayDeque(8);
        this.currentIterator = rootIterator;
        this.transformer = null;
    }

    protected void findNext(E value) {
        if (value instanceof Iterator) {
            findNextByIterator((Iterator) value);
        } else {
            this.currentValue = value;
            this.hasNext = true;
        }
    }

    protected void findNextByIterator(Iterator<? extends E> it) {
        if (it != this.currentIterator) {
            if (this.currentIterator != null) {
                this.stack.push(this.currentIterator);
            }
            this.currentIterator = it;
        }
        while (this.currentIterator.hasNext() && !this.hasNext) {
            E next = this.currentIterator.next();
            if (this.transformer != null) {
                next = this.transformer.apply(next);
            }
            findNext(next);
        }
        if (!this.hasNext && !this.stack.isEmpty()) {
            this.currentIterator = this.stack.pop();
            findNextByIterator(this.currentIterator);
        }
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        updateCurrentIterator();
        return this.hasNext;
    }

    @Override // java.util.Iterator
    public E next() {
        updateCurrentIterator();
        if (!this.hasNext) {
            throw new NoSuchElementException("No more elements in the iteration");
        }
        this.lastUsedIterator = this.currentIterator;
        E result = this.currentValue;
        this.currentValue = null;
        this.hasNext = false;
        return result;
    }

    @Override // java.util.Iterator
    public void remove() {
        if (this.lastUsedIterator == null) {
            throw new IllegalStateException("Iterator remove() cannot be called at this time");
        }
        this.lastUsedIterator.remove();
        this.lastUsedIterator = null;
    }

    protected void updateCurrentIterator() {
        if (this.hasNext) {
            return;
        }
        if (this.currentIterator == null) {
            if (this.root != null) {
                if (this.transformer == null) {
                    findNext(this.root);
                } else {
                    findNext(this.transformer.apply(this.root));
                }
                this.root = null;
                return;
            }
            return;
        }
        findNextByIterator(this.currentIterator);
    }
}
