package org.apache.commons.collections4.list;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Predicate;
import org.apache.commons.collections4.list.AbstractLinkedList;

@Deprecated
/* loaded from: classes9.dex */
public class CursorableLinkedList<E> extends AbstractLinkedList<E> implements Serializable {
    private static final long serialVersionUID = 8836393098519411393L;
    private transient List<WeakReference<Cursor<E>>> cursors;

    /* loaded from: classes9.dex */
    public static class Cursor<E> extends AbstractLinkedList.LinkedListIterator<E> {
        boolean currentRemovedByAnother;
        boolean nextIndexValid;
        boolean valid;

        @Override // org.apache.commons.collections4.list.AbstractLinkedList.LinkedListIterator, java.util.ListIterator, java.util.Iterator
        public /* bridge */ /* synthetic */ boolean hasNext() {
            return super.hasNext();
        }

        @Override // org.apache.commons.collections4.list.AbstractLinkedList.LinkedListIterator, java.util.ListIterator, org.apache.commons.collections4.OrderedIterator
        public /* bridge */ /* synthetic */ boolean hasPrevious() {
            return super.hasPrevious();
        }

        @Override // org.apache.commons.collections4.list.AbstractLinkedList.LinkedListIterator, java.util.ListIterator, java.util.Iterator
        public /* bridge */ /* synthetic */ Object next() {
            return super.next();
        }

        @Override // org.apache.commons.collections4.list.AbstractLinkedList.LinkedListIterator, java.util.ListIterator, org.apache.commons.collections4.OrderedIterator
        public /* bridge */ /* synthetic */ Object previous() {
            return super.previous();
        }

        @Override // org.apache.commons.collections4.list.AbstractLinkedList.LinkedListIterator, java.util.ListIterator
        public /* bridge */ /* synthetic */ int previousIndex() {
            return super.previousIndex();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // org.apache.commons.collections4.list.AbstractLinkedList.LinkedListIterator, java.util.ListIterator
        public /* bridge */ /* synthetic */ void set(Object obj) {
            super.set(obj);
        }

        protected Cursor(CursorableLinkedList<E> parent, int index) {
            super(parent, index);
            this.valid = true;
            this.nextIndexValid = true;
            this.valid = true;
        }

        @Override // org.apache.commons.collections4.list.AbstractLinkedList.LinkedListIterator, java.util.ListIterator
        public void add(E obj) {
            super.add(obj);
            this.next = this.next.next;
        }

        @Override // org.apache.commons.collections4.list.AbstractLinkedList.LinkedListIterator
        protected void checkModCount() {
            if (!this.valid) {
                throw new ConcurrentModificationException("Cursor closed");
            }
        }

        public void close() {
            if (this.valid) {
                ((CursorableLinkedList) this.parent).unregisterCursor(this);
                this.valid = false;
            }
        }

        @Override // org.apache.commons.collections4.list.AbstractLinkedList.LinkedListIterator, java.util.ListIterator
        public int nextIndex() {
            if (!this.nextIndexValid) {
                if (this.next == this.parent.header) {
                    this.nextIndex = this.parent.size();
                } else {
                    int pos = 0;
                    for (AbstractLinkedList.Node<E> temp = this.parent.header.next; temp != this.next; temp = temp.next) {
                        pos++;
                    }
                    this.nextIndex = pos;
                }
                this.nextIndexValid = true;
            }
            return this.nextIndex;
        }

        protected void nodeChanged(AbstractLinkedList.Node<E> node) {
        }

        protected void nodeInserted(AbstractLinkedList.Node<E> node) {
            if (node.previous == this.current || this.next.previous == node) {
                this.next = node;
            } else {
                this.nextIndexValid = false;
            }
        }

        protected void nodeRemoved(AbstractLinkedList.Node<E> node) {
            if (node == this.next && node == this.current) {
                this.next = node.next;
                this.current = null;
                this.currentRemovedByAnother = true;
            } else if (node == this.next) {
                this.next = node.next;
                this.currentRemovedByAnother = false;
            } else if (node == this.current) {
                this.current = null;
                this.currentRemovedByAnother = true;
                this.nextIndex--;
            } else {
                this.nextIndexValid = false;
                this.currentRemovedByAnother = false;
            }
        }

        @Override // org.apache.commons.collections4.list.AbstractLinkedList.LinkedListIterator, java.util.ListIterator, java.util.Iterator
        public void remove() {
            if (this.current != null || !this.currentRemovedByAnother) {
                checkModCount();
                this.parent.removeNode(getLastNodeReturned());
            }
            this.currentRemovedByAnother = false;
        }
    }

    /* loaded from: classes9.dex */
    protected static class SubCursor<E> extends Cursor<E> {
        protected final AbstractLinkedList.LinkedSubList<E> sub;

        protected SubCursor(AbstractLinkedList.LinkedSubList<E> sub, int index) {
            super((CursorableLinkedList) sub.parent, sub.offset + index);
            this.sub = sub;
        }

        @Override // org.apache.commons.collections4.list.CursorableLinkedList.Cursor, org.apache.commons.collections4.list.AbstractLinkedList.LinkedListIterator, java.util.ListIterator
        public void add(E obj) {
            super.add(obj);
            this.sub.expectedModCount = this.parent.modCount;
            this.sub.size++;
        }

        @Override // org.apache.commons.collections4.list.CursorableLinkedList.Cursor, org.apache.commons.collections4.list.AbstractLinkedList.LinkedListIterator, java.util.ListIterator, java.util.Iterator
        public boolean hasNext() {
            return nextIndex() < this.sub.size;
        }

        @Override // org.apache.commons.collections4.list.CursorableLinkedList.Cursor, org.apache.commons.collections4.list.AbstractLinkedList.LinkedListIterator, java.util.ListIterator, org.apache.commons.collections4.OrderedIterator
        public boolean hasPrevious() {
            return previousIndex() >= 0;
        }

        @Override // org.apache.commons.collections4.list.CursorableLinkedList.Cursor, org.apache.commons.collections4.list.AbstractLinkedList.LinkedListIterator, java.util.ListIterator
        public int nextIndex() {
            return super.nextIndex() - this.sub.offset;
        }

        @Override // org.apache.commons.collections4.list.CursorableLinkedList.Cursor, org.apache.commons.collections4.list.AbstractLinkedList.LinkedListIterator, java.util.ListIterator, java.util.Iterator
        public void remove() {
            super.remove();
            this.sub.expectedModCount = this.parent.modCount;
            AbstractLinkedList.LinkedSubList<E> linkedSubList = this.sub;
            linkedSubList.size--;
        }
    }

    public CursorableLinkedList() {
        init();
    }

    public CursorableLinkedList(Collection<? extends E> coll) {
        super(coll);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.collections4.list.AbstractLinkedList
    public void addNode(AbstractLinkedList.Node<E> nodeToInsert, AbstractLinkedList.Node<E> insertBeforeNode) {
        super.addNode(nodeToInsert, insertBeforeNode);
        broadcastNodeInserted(nodeToInsert);
    }

    protected void broadcastNodeChanged(AbstractLinkedList.Node<E> node) {
        Iterator<WeakReference<Cursor<E>>> it = this.cursors.iterator();
        while (it.hasNext()) {
            WeakReference<Cursor<E>> ref = it.next();
            Cursor<E> cursor = ref.get();
            if (cursor == null) {
                it.remove();
            } else {
                cursor.nodeChanged(node);
            }
        }
    }

    protected void broadcastNodeInserted(AbstractLinkedList.Node<E> node) {
        Iterator<WeakReference<Cursor<E>>> it = this.cursors.iterator();
        while (it.hasNext()) {
            WeakReference<Cursor<E>> ref = it.next();
            Cursor<E> cursor = ref.get();
            if (cursor == null) {
                it.remove();
            } else {
                cursor.nodeInserted(node);
            }
        }
    }

    protected void broadcastNodeRemoved(AbstractLinkedList.Node<E> node) {
        Iterator<WeakReference<Cursor<E>>> it = this.cursors.iterator();
        while (it.hasNext()) {
            WeakReference<Cursor<E>> ref = it.next();
            Cursor<E> cursor = ref.get();
            if (cursor == null) {
                it.remove();
            } else {
                cursor.nodeRemoved(node);
            }
        }
    }

    @Override // org.apache.commons.collections4.list.AbstractLinkedList
    protected ListIterator<E> createSubListListIterator(AbstractLinkedList.LinkedSubList<E> subList, int fromIndex) {
        SubCursor<E> cursor = new SubCursor<>(subList, fromIndex);
        registerCursor(cursor);
        return cursor;
    }

    public Cursor<E> cursor() {
        return cursor(0);
    }

    public Cursor<E> cursor(int fromIndex) {
        Cursor<E> cursor = new Cursor<>(this, fromIndex);
        registerCursor(cursor);
        return cursor;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.collections4.list.AbstractLinkedList
    public void init() {
        super.init();
        this.cursors = new ArrayList();
    }

    @Override // org.apache.commons.collections4.list.AbstractLinkedList, java.util.List, java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        return super.listIterator(0);
    }

    @Override // org.apache.commons.collections4.list.AbstractLinkedList, java.util.List
    public ListIterator<E> listIterator() {
        return cursor(0);
    }

    @Override // org.apache.commons.collections4.list.AbstractLinkedList, java.util.List
    public ListIterator<E> listIterator(int fromIndex) {
        return cursor(fromIndex);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        doReadObject(in);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$registerCursor$0(WeakReference ref) {
        return ref.get() == null;
    }

    protected void registerCursor(Cursor<E> cursor) {
        this.cursors.removeIf(new Predicate() { // from class: org.apache.commons.collections4.list.CursorableLinkedList$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return CursorableLinkedList.lambda$registerCursor$0((WeakReference) obj);
            }
        });
        this.cursors.add(new WeakReference<>(cursor));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.collections4.list.AbstractLinkedList
    public void removeAllNodes() {
        if (!isEmpty()) {
            Iterator<E> it = iterator();
            while (it.hasNext()) {
                it.next();
                it.remove();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.collections4.list.AbstractLinkedList
    public void removeNode(AbstractLinkedList.Node<E> node) {
        super.removeNode(node);
        broadcastNodeRemoved(node);
    }

    protected void unregisterCursor(Cursor<E> cursor) {
        Iterator<WeakReference<Cursor<E>>> it = this.cursors.iterator();
        while (it.hasNext()) {
            WeakReference<Cursor<E>> ref = it.next();
            Cursor<E> cur = ref.get();
            if (cur == null) {
                it.remove();
            } else if (cur == cursor) {
                ref.clear();
                it.remove();
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.collections4.list.AbstractLinkedList
    public void updateNode(AbstractLinkedList.Node<E> node, E value) {
        super.updateNode(node, value);
        broadcastNodeChanged(node);
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        doWriteObject(out);
    }
}
