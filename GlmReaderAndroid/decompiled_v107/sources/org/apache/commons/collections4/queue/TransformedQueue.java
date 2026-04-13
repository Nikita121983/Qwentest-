package org.apache.commons.collections4.queue;

import java.util.Queue;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.collection.TransformedCollection;

/* loaded from: classes9.dex */
public class TransformedQueue<E> extends TransformedCollection<E> implements Queue<E> {
    private static final long serialVersionUID = -7901091318986132033L;

    public static <E> TransformedQueue<E> transformedQueue(Queue<E> queue, Transformer<? super E, ? extends E> transformer) {
        TransformedQueue<E> decorated = new TransformedQueue<>(queue, transformer);
        if (!queue.isEmpty()) {
            Object[] array = queue.toArray();
            queue.clear();
            for (Object obj : array) {
                decorated.decorated().add(transformer.apply(obj));
            }
        }
        return decorated;
    }

    public static <E> TransformedQueue<E> transformingQueue(Queue<E> queue, Transformer<? super E, ? extends E> transformer) {
        return new TransformedQueue<>(queue, transformer);
    }

    protected TransformedQueue(Queue<E> queue, Transformer<? super E, ? extends E> transformer) {
        super(queue, transformer);
    }

    @Override // java.util.Queue
    public E element() {
        return getQueue().element();
    }

    protected Queue<E> getQueue() {
        return (Queue) decorated();
    }

    @Override // java.util.Queue
    public boolean offer(E obj) {
        return getQueue().offer(transform((TransformedQueue<E>) obj));
    }

    @Override // java.util.Queue
    public E peek() {
        return getQueue().peek();
    }

    @Override // java.util.Queue
    public E poll() {
        return getQueue().poll();
    }

    @Override // java.util.Queue
    public E remove() {
        return getQueue().remove();
    }
}
