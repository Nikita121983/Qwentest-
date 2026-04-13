package org.apache.commons.collections4.queue;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Queue;
import org.apache.commons.collections4.BoundedCollection;

/* loaded from: classes9.dex */
public class CircularFifoQueue<E> extends AbstractCollection<E> implements Queue<E>, BoundedCollection<E>, Serializable {
    private static final long serialVersionUID = -8423413834657610406L;
    private transient E[] elements;
    private transient int end;
    private transient boolean full;
    private final int maxElements;
    private transient int start;

    public CircularFifoQueue() {
        this(32);
    }

    public CircularFifoQueue(Collection<? extends E> coll) {
        this(coll.size());
        addAll(coll);
    }

    public CircularFifoQueue(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("The size must be greater than 0");
        }
        this.elements = (E[]) new Object[i];
        this.maxElements = this.elements.length;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Queue
    public boolean add(E element) {
        Objects.requireNonNull(element, "element");
        if (isAtFullCapacity()) {
            remove();
        }
        E[] eArr = this.elements;
        int i = this.end;
        this.end = i + 1;
        eArr[i] = element;
        if (this.end >= this.maxElements) {
            this.end = 0;
        }
        if (this.end == this.start) {
            this.full = true;
        }
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public void clear() {
        this.full = false;
        this.start = 0;
        this.end = 0;
        Arrays.fill(this.elements, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int decrement(int index) {
        int index2 = index - 1;
        if (index2 < 0) {
            int index3 = this.maxElements - 1;
            return index3;
        }
        return index2;
    }

    @Override // java.util.Queue
    public E element() {
        if (isEmpty()) {
            throw new NoSuchElementException("queue is empty");
        }
        return peek();
    }

    public E get(int index) {
        int sz = size();
        if (index < 0 || index >= sz) {
            throw new NoSuchElementException(String.format("The specified index %1$d is outside the available range [0, %2$d)", Integer.valueOf(index), Integer.valueOf(sz)));
        }
        int idx = (this.start + index) % this.maxElements;
        return this.elements[idx];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int increment(int index) {
        int index2 = index + 1;
        if (index2 >= this.maxElements) {
            return 0;
        }
        return index2;
    }

    public boolean isAtFullCapacity() {
        return size() == this.maxElements;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override // org.apache.commons.collections4.BoundedCollection
    public boolean isFull() {
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        return new Iterator<E>() { // from class: org.apache.commons.collections4.queue.CircularFifoQueue.1
            private int index;
            private boolean isFirst;
            private int lastReturnedIndex = -1;

            {
                this.index = CircularFifoQueue.this.start;
                this.isFirst = CircularFifoQueue.this.full;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.isFirst || this.index != CircularFifoQueue.this.end;
            }

            @Override // java.util.Iterator
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                this.isFirst = false;
                this.lastReturnedIndex = this.index;
                this.index = CircularFifoQueue.this.increment(this.index);
                return (E) CircularFifoQueue.this.elements[this.lastReturnedIndex];
            }

            @Override // java.util.Iterator
            public void remove() {
                if (this.lastReturnedIndex != -1) {
                    if (this.lastReturnedIndex == CircularFifoQueue.this.start) {
                        CircularFifoQueue.this.remove();
                        this.lastReturnedIndex = -1;
                        return;
                    }
                    int pos = this.lastReturnedIndex + 1;
                    if (CircularFifoQueue.this.start >= this.lastReturnedIndex || pos >= CircularFifoQueue.this.end) {
                        while (pos != CircularFifoQueue.this.end) {
                            if (pos >= CircularFifoQueue.this.maxElements) {
                                CircularFifoQueue.this.elements[pos - 1] = CircularFifoQueue.this.elements[0];
                                pos = 0;
                            } else {
                                CircularFifoQueue.this.elements[CircularFifoQueue.this.decrement(pos)] = CircularFifoQueue.this.elements[pos];
                                pos = CircularFifoQueue.this.increment(pos);
                            }
                        }
                    } else {
                        System.arraycopy(CircularFifoQueue.this.elements, pos, CircularFifoQueue.this.elements, this.lastReturnedIndex, CircularFifoQueue.this.end - pos);
                    }
                    this.lastReturnedIndex = -1;
                    CircularFifoQueue.this.end = CircularFifoQueue.this.decrement(CircularFifoQueue.this.end);
                    CircularFifoQueue.this.elements[CircularFifoQueue.this.end] = null;
                    CircularFifoQueue.this.full = false;
                    this.index = CircularFifoQueue.this.decrement(this.index);
                    return;
                }
                throw new IllegalStateException();
            }
        };
    }

    @Override // org.apache.commons.collections4.BoundedCollection
    public int maxSize() {
        return this.maxElements;
    }

    @Override // java.util.Queue
    public boolean offer(E element) {
        return add(element);
    }

    @Override // java.util.Queue
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return this.elements[this.start];
    }

    @Override // java.util.Queue
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        return remove();
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.elements = (E[]) new Object[this.maxElements];
        int readInt = objectInputStream.readInt();
        for (int i = 0; i < readInt; i++) {
            ((E[]) this.elements)[i] = objectInputStream.readObject();
        }
        this.start = 0;
        this.full = readInt == this.maxElements;
        if (this.full) {
            this.end = 0;
        } else {
            this.end = readInt;
        }
    }

    @Override // java.util.Queue
    public E remove() {
        if (isEmpty()) {
            throw new NoSuchElementException("queue is empty");
        }
        E element = this.elements[this.start];
        if (element != null) {
            E[] eArr = this.elements;
            int i = this.start;
            this.start = i + 1;
            eArr[i] = null;
            if (this.start >= this.maxElements) {
                this.start = 0;
            }
            this.full = false;
        }
        return element;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public int size() {
        if (this.end < this.start) {
            int size = (this.maxElements - this.start) + this.end;
            return size;
        }
        int size2 = this.end;
        if (size2 == this.start) {
            if (!this.full) {
                return 0;
            }
            int size3 = this.maxElements;
            return size3;
        }
        int size4 = this.end;
        return size4 - this.start;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeInt(size());
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            E e = it.next();
            out.writeObject(e);
        }
    }
}
