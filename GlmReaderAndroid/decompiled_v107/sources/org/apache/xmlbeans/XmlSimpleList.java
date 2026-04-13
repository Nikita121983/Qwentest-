package org.apache.xmlbeans;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import org.apache.logging.log4j.util.Chars;

/* loaded from: classes.dex */
public class XmlSimpleList<T> implements List<T>, Serializable {
    private static final long serialVersionUID = 1;
    private final List<T> underlying;

    public XmlSimpleList(List<T> list) {
        this.underlying = list;
    }

    @Override // java.util.List, java.util.Collection
    public int size() {
        return this.underlying.size();
    }

    @Override // java.util.List, java.util.Collection
    public boolean isEmpty() {
        return this.underlying.isEmpty();
    }

    @Override // java.util.List, java.util.Collection
    public boolean contains(Object o) {
        return this.underlying.contains(o);
    }

    @Override // java.util.List, java.util.Collection
    public boolean containsAll(Collection coll) {
        return this.underlying.containsAll(coll);
    }

    @Override // java.util.List, java.util.Collection
    public Object[] toArray() {
        return this.underlying.toArray(new Object[0]);
    }

    @Override // java.util.List, java.util.Collection
    public <X> X[] toArray(X[] xArr) {
        return (X[]) this.underlying.toArray(xArr);
    }

    @Override // java.util.List, java.util.Collection
    public boolean add(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, java.util.Collection
    public boolean addAll(Collection coll) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, java.util.Collection
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, java.util.Collection
    public boolean removeAll(Collection coll) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, java.util.Collection
    public boolean retainAll(Collection coll) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, java.util.Collection
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public T get(int index) {
        return this.underlying.get(index);
    }

    @Override // java.util.List
    public T set(int index, T element) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public void add(int index, Object element) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public T remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public int indexOf(Object o) {
        return this.underlying.indexOf(o);
    }

    @Override // java.util.List
    public int lastIndexOf(Object o) {
        return this.underlying.lastIndexOf(o);
    }

    @Override // java.util.List
    public boolean addAll(int index, Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public List<T> subList(int from, int to) {
        return new XmlSimpleList(this.underlying.subList(from, to));
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public Iterator<T> iterator() {
        return new Iterator<T>() { // from class: org.apache.xmlbeans.XmlSimpleList.1
            final Iterator<T> i;

            {
                this.i = XmlSimpleList.this.underlying.iterator();
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.i.hasNext();
            }

            @Override // java.util.Iterator
            public T next() {
                return this.i.next();
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override // java.util.List
    public ListIterator<T> listIterator() {
        return listIterator(0);
    }

    @Override // java.util.List
    public ListIterator<T> listIterator(final int index) {
        return new ListIterator<T>() { // from class: org.apache.xmlbeans.XmlSimpleList.2
            final ListIterator<T> i;

            {
                this.i = XmlSimpleList.this.underlying.listIterator(index);
            }

            @Override // java.util.ListIterator, java.util.Iterator
            public boolean hasNext() {
                return this.i.hasNext();
            }

            @Override // java.util.ListIterator, java.util.Iterator
            public T next() {
                return this.i.next();
            }

            @Override // java.util.ListIterator
            public boolean hasPrevious() {
                return this.i.hasPrevious();
            }

            @Override // java.util.ListIterator
            public T previous() {
                return this.i.previous();
            }

            @Override // java.util.ListIterator
            public int nextIndex() {
                return this.i.nextIndex();
            }

            @Override // java.util.ListIterator
            public int previousIndex() {
                return this.i.previousIndex();
            }

            @Override // java.util.ListIterator, java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }

            @Override // java.util.ListIterator
            public void set(Object o) {
                throw new UnsupportedOperationException();
            }

            @Override // java.util.ListIterator
            public void add(Object o) {
                throw new UnsupportedOperationException();
            }
        };
    }

    private String stringValue(Object o) {
        if (o instanceof SimpleValue) {
            return ((SimpleValue) o).getStringValue();
        }
        return o.toString();
    }

    public String toString() {
        int size = this.underlying.size();
        if (size == 0) {
            return "";
        }
        String first = stringValue(this.underlying.get(0));
        if (size == 1) {
            return first;
        }
        StringBuilder result = new StringBuilder(first);
        for (int i = 1; i < size; i++) {
            result.append(Chars.SPACE);
            result.append(stringValue(this.underlying.get(i)));
        }
        return result.toString();
    }

    @Override // java.util.List, java.util.Collection
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof XmlSimpleList)) {
            return false;
        }
        XmlSimpleList<?> xmlSimpleList = (XmlSimpleList) o;
        List<T> list = xmlSimpleList.underlying;
        int size = this.underlying.size();
        if (size != list.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!Objects.equals(this.underlying.get(i), list.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.List, java.util.Collection
    public int hashCode() {
        int hash = 0;
        for (Object item : this.underlying) {
            hash = (hash * 19) + item.hashCode();
        }
        return hash;
    }
}
