package org.apache.commons.collections4.bidimap;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.Comparable;
import java.util.AbstractSet;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import org.apache.commons.collections4.KeyValue;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.OrderedBidiMap;
import org.apache.commons.collections4.OrderedIterator;
import org.apache.commons.collections4.OrderedMapIterator;
import org.apache.commons.collections4.iterators.EmptyOrderedMapIterator;
import org.apache.commons.collections4.keyvalue.UnmodifiableMapEntry;
import org.apache.logging.log4j.util.Chars;

/* loaded from: classes9.dex */
public class TreeBidiMap<K extends Comparable<K>, V extends Comparable<V>> implements OrderedBidiMap<K, V>, Serializable {
    private static final long serialVersionUID = 721969328361807L;
    private transient Set<Map.Entry<K, V>> entrySet;
    private transient TreeBidiMap<K, V>.Inverse inverse;
    private transient Set<K> keySet;
    private transient int modifications;
    private transient int nodeCount;
    private transient Node<K, V>[] rootNode;
    private transient Set<V> valuesSet;

    /* loaded from: classes9.dex */
    abstract class AbstractView<E> extends AbstractSet<E> {
        final DataElement orderType;

        AbstractView(DataElement orderType) {
            this.orderType = orderType;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            TreeBidiMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return TreeBidiMap.this.size();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public abstract class AbstractViewIterator {
        private int expectedModifications;
        private Node<K, V> nextNode;
        private final DataElement orderType;
        Node<K, V> lastReturnedNode = null;
        private Node<K, V> previousNode = null;

        AbstractViewIterator(DataElement orderType) {
            this.orderType = orderType;
            this.expectedModifications = TreeBidiMap.this.modifications;
            this.nextNode = TreeBidiMap.this.leastNode(TreeBidiMap.this.rootNode[orderType.ordinal()], orderType);
        }

        public final boolean hasNext() {
            return this.nextNode != null;
        }

        public boolean hasPrevious() {
            return this.previousNode != null;
        }

        protected Node<K, V> navigateNext() {
            if (this.nextNode != null) {
                if (TreeBidiMap.this.modifications != this.expectedModifications) {
                    throw new ConcurrentModificationException();
                }
                this.lastReturnedNode = this.nextNode;
                this.previousNode = this.nextNode;
                this.nextNode = TreeBidiMap.this.nextGreater(this.nextNode, this.orderType);
                return this.lastReturnedNode;
            }
            throw new NoSuchElementException();
        }

        protected Node<K, V> navigatePrevious() {
            if (this.previousNode != null) {
                if (TreeBidiMap.this.modifications != this.expectedModifications) {
                    throw new ConcurrentModificationException();
                }
                this.nextNode = this.lastReturnedNode;
                if (this.nextNode == null) {
                    this.nextNode = TreeBidiMap.this.nextGreater(this.previousNode, this.orderType);
                }
                this.lastReturnedNode = this.previousNode;
                this.previousNode = TreeBidiMap.this.nextSmaller(this.previousNode, this.orderType);
                return this.lastReturnedNode;
            }
            throw new NoSuchElementException();
        }

        public final void remove() {
            if (this.lastReturnedNode != null) {
                if (TreeBidiMap.this.modifications == this.expectedModifications) {
                    TreeBidiMap.this.doRedBlackDelete(this.lastReturnedNode);
                    this.expectedModifications++;
                    this.lastReturnedNode = null;
                    if (this.nextNode == null) {
                        this.previousNode = TreeBidiMap.this.greatestNode(TreeBidiMap.this.rootNode[this.orderType.ordinal()], this.orderType);
                        return;
                    } else {
                        this.previousNode = TreeBidiMap.this.nextSmaller(this.nextNode, this.orderType);
                        return;
                    }
                }
                throw new ConcurrentModificationException();
            }
            throw new IllegalStateException();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public enum DataElement {
        KEY("key"),
        VALUE("value");

        private final String description;

        DataElement(String description) {
            this.description = description;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.description;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public final class EntryView extends TreeBidiMap<K, V>.AbstractView<Map.Entry<K, V>> {
        EntryView() {
            super(DataElement.KEY);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry<?, ?> entry = (Map.Entry) obj;
            Object value = entry.getValue();
            Node<K, V> node = TreeBidiMap.this.lookupKey(entry.getKey());
            return node != null && Objects.equals(node.getValue(), value);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return new ViewMapEntryIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry<?, ?> entry = (Map.Entry) obj;
            Object value = entry.getValue();
            Node<K, V> node = TreeBidiMap.this.lookupKey(entry.getKey());
            if (node == null || !Objects.equals(node.getValue(), value)) {
                return false;
            }
            TreeBidiMap.this.doRedBlackDelete(node);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public final class Inverse implements OrderedBidiMap<V, K> {
        private Set<Map.Entry<V, K>> inverseEntrySet;
        private Set<V> inverseKeySet;
        private Set<K> inverseValuesSet;

        Inverse() {
        }

        @Override // java.util.Map, org.apache.commons.collections4.Put
        public void clear() {
            TreeBidiMap.this.clear();
        }

        @Override // java.util.Map, org.apache.commons.collections4.Get
        public boolean containsKey(Object key) {
            return TreeBidiMap.this.containsValue(key);
        }

        @Override // java.util.Map, org.apache.commons.collections4.Get
        public boolean containsValue(Object value) {
            return TreeBidiMap.this.containsKey(value);
        }

        @Override // java.util.Map, org.apache.commons.collections4.Get
        public Set<Map.Entry<V, K>> entrySet() {
            if (this.inverseEntrySet == null) {
                this.inverseEntrySet = new InverseEntryView();
            }
            return this.inverseEntrySet;
        }

        @Override // java.util.Map
        public boolean equals(Object obj) {
            return TreeBidiMap.this.doEquals(obj, DataElement.VALUE);
        }

        @Override // org.apache.commons.collections4.OrderedMap
        public V firstKey() {
            if (TreeBidiMap.this.nodeCount != 0) {
                return (V) TreeBidiMap.this.leastNode(TreeBidiMap.this.rootNode[DataElement.VALUE.ordinal()], DataElement.VALUE).getValue();
            }
            throw new NoSuchElementException("Map is empty");
        }

        @Override // java.util.Map, org.apache.commons.collections4.Get
        public K get(Object obj) {
            return (K) TreeBidiMap.this.getKey(obj);
        }

        @Override // org.apache.commons.collections4.BidiMap
        public V getKey(Object obj) {
            return (V) TreeBidiMap.this.get(obj);
        }

        @Override // java.util.Map
        public int hashCode() {
            return TreeBidiMap.this.doHashCode(DataElement.VALUE);
        }

        @Override // org.apache.commons.collections4.OrderedBidiMap, org.apache.commons.collections4.BidiMap
        public OrderedBidiMap<K, V> inverseBidiMap() {
            return TreeBidiMap.this;
        }

        @Override // java.util.Map, org.apache.commons.collections4.Get
        public boolean isEmpty() {
            return TreeBidiMap.this.isEmpty();
        }

        @Override // java.util.Map, org.apache.commons.collections4.Get
        public Set<V> keySet() {
            if (this.inverseKeySet == null) {
                this.inverseKeySet = new ValueView(DataElement.VALUE);
            }
            return this.inverseKeySet;
        }

        @Override // org.apache.commons.collections4.OrderedMap
        public V lastKey() {
            if (TreeBidiMap.this.nodeCount != 0) {
                return (V) TreeBidiMap.this.greatestNode(TreeBidiMap.this.rootNode[DataElement.VALUE.ordinal()], DataElement.VALUE).getValue();
            }
            throw new NoSuchElementException("Map is empty");
        }

        @Override // org.apache.commons.collections4.IterableGet
        public OrderedMapIterator<V, K> mapIterator() {
            if (isEmpty()) {
                return EmptyOrderedMapIterator.emptyOrderedMapIterator();
            }
            return new InverseViewMapIterator(DataElement.VALUE);
        }

        @Override // org.apache.commons.collections4.OrderedMap
        public V nextKey(V key) {
            TreeBidiMap.checkKey(key);
            Node<K, V> node = TreeBidiMap.this.nextGreater(TreeBidiMap.this.lookup(key, DataElement.VALUE), DataElement.VALUE);
            if (node == null) {
                return null;
            }
            return node.getValue();
        }

        @Override // org.apache.commons.collections4.OrderedMap
        public V previousKey(V key) {
            TreeBidiMap.checkKey(key);
            Node<K, V> node = TreeBidiMap.this.nextSmaller(TreeBidiMap.this.lookup(key, DataElement.VALUE), DataElement.VALUE);
            if (node == null) {
                return null;
            }
            return node.getValue();
        }

        @Override // org.apache.commons.collections4.BidiMap, java.util.Map, org.apache.commons.collections4.Put
        public K put(V v, K k) {
            K k2 = (K) get((Object) v);
            TreeBidiMap.this.doPut(k, v);
            return k2;
        }

        @Override // java.util.Map, org.apache.commons.collections4.Put
        public void putAll(Map<? extends V, ? extends K> map) {
            for (Map.Entry<? extends V, ? extends K> e : map.entrySet()) {
                put((Inverse) e.getKey(), (V) e.getValue());
            }
        }

        @Override // java.util.Map, org.apache.commons.collections4.Get
        public K remove(Object obj) {
            return (K) TreeBidiMap.this.removeValue(obj);
        }

        @Override // org.apache.commons.collections4.BidiMap
        public V removeValue(Object obj) {
            return (V) TreeBidiMap.this.remove(obj);
        }

        @Override // java.util.Map, org.apache.commons.collections4.Get
        public int size() {
            return TreeBidiMap.this.size();
        }

        public String toString() {
            return TreeBidiMap.this.doToString(DataElement.VALUE);
        }

        @Override // org.apache.commons.collections4.BidiMap, java.util.Map, org.apache.commons.collections4.Get
        public Set<K> values() {
            if (this.inverseValuesSet == null) {
                this.inverseValuesSet = new KeyView(DataElement.VALUE);
            }
            return this.inverseValuesSet;
        }
    }

    /* loaded from: classes9.dex */
    final class InverseEntryView extends TreeBidiMap<K, V>.AbstractView<Map.Entry<V, K>> {
        InverseEntryView() {
            super(DataElement.VALUE);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry<?, ?> entry = (Map.Entry) obj;
            Object value = entry.getValue();
            Node<K, V> node = TreeBidiMap.this.lookupValue(entry.getKey());
            return node != null && Objects.equals(node.getKey(), value);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<V, K>> iterator() {
            return new InverseViewMapEntryIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry<?, ?> entry = (Map.Entry) obj;
            Object value = entry.getValue();
            Node<K, V> node = TreeBidiMap.this.lookupValue(entry.getKey());
            if (node == null || !Objects.equals(node.getKey(), value)) {
                return false;
            }
            TreeBidiMap.this.doRedBlackDelete(node);
            return true;
        }
    }

    /* loaded from: classes9.dex */
    final class InverseViewMapEntryIterator extends TreeBidiMap<K, V>.AbstractViewIterator implements OrderedIterator<Map.Entry<V, K>> {
        InverseViewMapEntryIterator() {
            super(DataElement.VALUE);
        }

        private Map.Entry<V, K> createEntry(Node<K, V> node) {
            return new UnmodifiableMapEntry(node.getValue(), node.getKey());
        }

        @Override // java.util.Iterator
        public Map.Entry<V, K> next() {
            return createEntry(navigateNext());
        }

        @Override // org.apache.commons.collections4.OrderedIterator
        public Map.Entry<V, K> previous() {
            return createEntry(navigatePrevious());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public final class InverseViewMapIterator extends TreeBidiMap<K, V>.AbstractViewIterator implements OrderedMapIterator<V, K> {
        InverseViewMapIterator(DataElement orderType) {
            super(orderType);
        }

        @Override // org.apache.commons.collections4.MapIterator
        public V getKey() {
            if (this.lastReturnedNode == null) {
                throw new IllegalStateException("Iterator getKey() can only be called after next() and before remove()");
            }
            return this.lastReturnedNode.getValue();
        }

        @Override // org.apache.commons.collections4.MapIterator
        public K getValue() {
            if (this.lastReturnedNode == null) {
                throw new IllegalStateException("Iterator getValue() can only be called after next() and before remove()");
            }
            return this.lastReturnedNode.getKey();
        }

        @Override // org.apache.commons.collections4.MapIterator, java.util.Iterator
        public V next() {
            return navigateNext().getValue();
        }

        @Override // org.apache.commons.collections4.OrderedMapIterator, org.apache.commons.collections4.OrderedIterator
        public V previous() {
            return navigatePrevious().getValue();
        }

        @Override // org.apache.commons.collections4.MapIterator
        public K setValue(K value) {
            throw new UnsupportedOperationException();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public final class KeyView extends TreeBidiMap<K, V>.AbstractView<K> {
        KeyView(DataElement orderType) {
            super(orderType);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            TreeBidiMap.checkNonNullComparable(obj, DataElement.KEY);
            return TreeBidiMap.this.lookupKey(obj) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<K> iterator() {
            return new ViewMapIterator(this.orderType);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object o) {
            return TreeBidiMap.this.doRemoveKey(o) != null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public static class Node<K extends Comparable<K>, V extends Comparable<V>> implements Map.Entry<K, V>, KeyValue<K, V> {
        private int hashCodeValue;
        private final K key;
        private final V value;
        private final Node<K, V>[] leftNode = new Node[2];
        private final Node<K, V>[] rightNode = new Node[2];
        private final Node<K, V>[] parentNode = new Node[2];
        private final boolean[] blackColor = {true, true};
        private boolean calculatedHashCode = false;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void copyColor(Node<K, V> node, DataElement dataElement) {
            this.blackColor[dataElement.ordinal()] = node.blackColor[dataElement.ordinal()];
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry<?, ?> e = (Map.Entry) obj;
            return Objects.equals(getKey(), e.getKey()) && Objects.equals(getValue(), e.getValue());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Object getData(DataElement dataElement) {
            switch (dataElement) {
                case KEY:
                    return getKey();
                case VALUE:
                    return getValue();
                default:
                    throw new IllegalArgumentException();
            }
        }

        @Override // java.util.Map.Entry, org.apache.commons.collections4.KeyValue
        public K getKey() {
            return this.key;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Node<K, V> getLeft(DataElement dataElement) {
            return this.leftNode[dataElement.ordinal()];
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Node<K, V> getParent(DataElement dataElement) {
            return this.parentNode[dataElement.ordinal()];
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Node<K, V> getRight(DataElement dataElement) {
            return this.rightNode[dataElement.ordinal()];
        }

        @Override // java.util.Map.Entry, org.apache.commons.collections4.KeyValue
        public V getValue() {
            return this.value;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            if (!this.calculatedHashCode) {
                this.hashCodeValue = getKey().hashCode() ^ getValue().hashCode();
                this.calculatedHashCode = true;
            }
            return this.hashCodeValue;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isBlack(DataElement dataElement) {
            return this.blackColor[dataElement.ordinal()];
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isLeftChild(DataElement dataElement) {
            return this.parentNode[dataElement.ordinal()] != null && this.parentNode[dataElement.ordinal()].leftNode[dataElement.ordinal()] == this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isRed(DataElement dataElement) {
            return !this.blackColor[dataElement.ordinal()];
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isRightChild(DataElement dataElement) {
            return this.parentNode[dataElement.ordinal()] != null && this.parentNode[dataElement.ordinal()].rightNode[dataElement.ordinal()] == this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBlack(DataElement dataElement) {
            this.blackColor[dataElement.ordinal()] = true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLeft(Node<K, V> node, DataElement dataElement) {
            this.leftNode[dataElement.ordinal()] = node;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setParent(Node<K, V> node, DataElement dataElement) {
            this.parentNode[dataElement.ordinal()] = node;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRed(DataElement dataElement) {
            this.blackColor[dataElement.ordinal()] = false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRight(Node<K, V> node, DataElement dataElement) {
            this.rightNode[dataElement.ordinal()] = node;
        }

        @Override // java.util.Map.Entry
        public V setValue(V ignored) throws UnsupportedOperationException {
            throw new UnsupportedOperationException("Map.Entry.setValue is not supported");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void swapColors(Node<K, V> node, DataElement dataElement) {
            boolean[] zArr = this.blackColor;
            int ordinal = dataElement.ordinal();
            zArr[ordinal] = zArr[ordinal] ^ node.blackColor[dataElement.ordinal()];
            boolean[] zArr2 = node.blackColor;
            int ordinal2 = dataElement.ordinal();
            zArr2[ordinal2] = zArr2[ordinal2] ^ this.blackColor[dataElement.ordinal()];
            boolean[] zArr3 = this.blackColor;
            int ordinal3 = dataElement.ordinal();
            zArr3[ordinal3] = zArr3[ordinal3] ^ node.blackColor[dataElement.ordinal()];
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public final class ValueView extends TreeBidiMap<K, V>.AbstractView<V> {
        ValueView(DataElement orderType) {
            super(orderType);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            TreeBidiMap.checkNonNullComparable(obj, DataElement.VALUE);
            return TreeBidiMap.this.lookupValue(obj) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<V> iterator() {
            return new InverseViewMapIterator(this.orderType);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object o) {
            return TreeBidiMap.this.doRemoveValue(o) != null;
        }
    }

    /* loaded from: classes9.dex */
    final class ViewMapEntryIterator extends TreeBidiMap<K, V>.AbstractViewIterator implements OrderedIterator<Map.Entry<K, V>> {
        ViewMapEntryIterator() {
            super(DataElement.KEY);
        }

        @Override // java.util.Iterator
        public Map.Entry<K, V> next() {
            return navigateNext();
        }

        @Override // org.apache.commons.collections4.OrderedIterator
        public Map.Entry<K, V> previous() {
            return navigatePrevious();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public final class ViewMapIterator extends TreeBidiMap<K, V>.AbstractViewIterator implements OrderedMapIterator<K, V> {
        ViewMapIterator(DataElement orderType) {
            super(orderType);
        }

        @Override // org.apache.commons.collections4.MapIterator
        public K getKey() {
            if (this.lastReturnedNode == null) {
                throw new IllegalStateException("Iterator getKey() can only be called after next() and before remove()");
            }
            return this.lastReturnedNode.getKey();
        }

        @Override // org.apache.commons.collections4.MapIterator
        public V getValue() {
            if (this.lastReturnedNode == null) {
                throw new IllegalStateException("Iterator getValue() can only be called after next() and before remove()");
            }
            return this.lastReturnedNode.getValue();
        }

        @Override // org.apache.commons.collections4.MapIterator, java.util.Iterator
        public K next() {
            return navigateNext().getKey();
        }

        @Override // org.apache.commons.collections4.OrderedMapIterator, org.apache.commons.collections4.OrderedIterator
        public K previous() {
            return navigatePrevious().getKey();
        }

        @Override // org.apache.commons.collections4.MapIterator
        public V setValue(V value) {
            throw new UnsupportedOperationException();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void checkKey(Object key) {
        checkNonNullComparable(key, DataElement.KEY);
    }

    private static void checkKeyAndValue(Object key, Object value) {
        checkKey(key);
        checkValue(value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void checkNonNullComparable(Object obj, DataElement dataElement) {
        Objects.requireNonNull(obj, Objects.toString(dataElement));
        if (!(obj instanceof Comparable)) {
            throw new ClassCastException(dataElement + " must be Comparable");
        }
    }

    private static void checkValue(Object value) {
        checkNonNullComparable(value, DataElement.VALUE);
    }

    private static <T extends Comparable<T>> int compare(T o1, T o2) {
        return o1.compareTo(o2);
    }

    private static boolean isBlack(Node<?, ?> node, DataElement dataElement) {
        return node == null || node.isBlack(dataElement);
    }

    private static boolean isRed(Node<?, ?> node, DataElement dataElement) {
        return node != null && node.isRed(dataElement);
    }

    private static void makeBlack(Node<?, ?> node, DataElement dataElement) {
        if (node != null) {
            node.setBlack(dataElement);
        }
    }

    private static void makeRed(Node<?, ?> node, DataElement dataElement) {
        if (node != null) {
            node.setRed(dataElement);
        }
    }

    public TreeBidiMap() {
        this.rootNode = new Node[2];
    }

    public TreeBidiMap(Map<? extends K, ? extends V> map) {
        this();
        putAll(map);
    }

    @Override // java.util.Map, org.apache.commons.collections4.Put
    public void clear() {
        modify();
        this.nodeCount = 0;
        this.rootNode[DataElement.KEY.ordinal()] = null;
        this.rootNode[DataElement.VALUE.ordinal()] = null;
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    public boolean containsKey(Object key) {
        checkKey(key);
        return lookupKey(key) != null;
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    public boolean containsValue(Object value) {
        checkValue(value);
        return lookupValue(value) != null;
    }

    private void copyColor(Node<K, V> from, Node<K, V> to, DataElement dataElement) {
        if (to != null) {
            if (from == null) {
                to.setBlack(dataElement);
            } else {
                to.copyColor(from, dataElement);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean doEquals(Object obj, DataElement dataElement) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Map)) {
            return false;
        }
        Map<?, ?> other = (Map) obj;
        if (other.size() != size()) {
            return false;
        }
        if (this.nodeCount > 0) {
            try {
                MapIterator<?, ?> it = getMapIterator(dataElement);
                while (it.hasNext()) {
                    Object key = it.next();
                    Object value = it.getValue();
                    if (!value.equals(other.get(key))) {
                        return false;
                    }
                }
            } catch (ClassCastException | NullPointerException e) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int doHashCode(DataElement dataElement) {
        int total = 0;
        if (this.nodeCount > 0) {
            MapIterator<?, ?> it = getMapIterator(dataElement);
            while (it.hasNext()) {
                Object key = it.next();
                Object value = it.getValue();
                total += key.hashCode() ^ value.hashCode();
            }
        }
        return total;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doPut(K key, V value) {
        checkKeyAndValue(key, value);
        doRemoveKey(key);
        doRemoveValue(value);
        Node<K, V> node = this.rootNode[DataElement.KEY.ordinal()];
        if (node == null) {
            Node<K, V> root = new Node<>(key, value);
            this.rootNode[DataElement.KEY.ordinal()] = root;
            this.rootNode[DataElement.VALUE.ordinal()] = root;
            grow();
            return;
        }
        while (true) {
            int cmp = compare(key, node.getKey());
            if (cmp == 0) {
                throw new IllegalArgumentException("Cannot store a duplicate key (\"" + key + "\") in this Map");
            }
            if (cmp < 0) {
                if (node.getLeft(DataElement.KEY) != null) {
                    node = node.getLeft(DataElement.KEY);
                } else {
                    Node<K, V> newNode = new Node<>(key, value);
                    insertValue(newNode);
                    node.setLeft(newNode, DataElement.KEY);
                    newNode.setParent(node, DataElement.KEY);
                    doRedBlackInsert(newNode, DataElement.KEY);
                    grow();
                    return;
                }
            } else if (node.getRight(DataElement.KEY) != null) {
                node = node.getRight(DataElement.KEY);
            } else {
                Node<K, V> newNode2 = new Node<>(key, value);
                insertValue(newNode2);
                node.setRight(newNode2, DataElement.KEY);
                newNode2.setParent(node, DataElement.KEY);
                doRedBlackInsert(newNode2, DataElement.KEY);
                grow();
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doRedBlackDelete(Node<K, V> deletedNode) {
        for (DataElement dataElement : DataElement.values()) {
            if (deletedNode.getLeft(dataElement) != null && deletedNode.getRight(dataElement) != null) {
                swapPosition(nextGreater(deletedNode, dataElement), deletedNode, dataElement);
            }
            Node<K, V> replacement = deletedNode.getLeft(dataElement) != null ? deletedNode.getLeft(dataElement) : deletedNode.getRight(dataElement);
            if (replacement != null) {
                replacement.setParent(deletedNode.getParent(dataElement), dataElement);
                if (deletedNode.getParent(dataElement) != null) {
                    if (deletedNode == deletedNode.getParent(dataElement).getLeft(dataElement)) {
                        deletedNode.getParent(dataElement).setLeft(replacement, dataElement);
                    } else {
                        deletedNode.getParent(dataElement).setRight(replacement, dataElement);
                    }
                } else {
                    this.rootNode[dataElement.ordinal()] = replacement;
                }
                deletedNode.setLeft(null, dataElement);
                deletedNode.setRight(null, dataElement);
                deletedNode.setParent(null, dataElement);
                if (isBlack(deletedNode, dataElement)) {
                    doRedBlackDeleteFixup(replacement, dataElement);
                }
            } else if (deletedNode.getParent(dataElement) == null) {
                this.rootNode[dataElement.ordinal()] = null;
            } else {
                if (isBlack(deletedNode, dataElement)) {
                    doRedBlackDeleteFixup(deletedNode, dataElement);
                }
                if (deletedNode.getParent(dataElement) != null) {
                    if (deletedNode == deletedNode.getParent(dataElement).getLeft(dataElement)) {
                        deletedNode.getParent(dataElement).setLeft(null, dataElement);
                    } else {
                        deletedNode.getParent(dataElement).setRight(null, dataElement);
                    }
                    deletedNode.setParent(null, dataElement);
                }
            }
        }
        shrink();
    }

    private void doRedBlackDeleteFixup(Node<K, V> replacementNode, DataElement dataElement) {
        Node<K, V> currentNode = replacementNode;
        while (currentNode != this.rootNode[dataElement.ordinal()] && isBlack(currentNode, dataElement)) {
            if (currentNode.isLeftChild(dataElement)) {
                Node<K, V> siblingNode = getRightChild(getParent(currentNode, dataElement), dataElement);
                if (isRed(siblingNode, dataElement)) {
                    makeBlack(siblingNode, dataElement);
                    makeRed(getParent(currentNode, dataElement), dataElement);
                    rotateLeft(getParent(currentNode, dataElement), dataElement);
                    siblingNode = getRightChild(getParent(currentNode, dataElement), dataElement);
                }
                if (isBlack(getLeftChild(siblingNode, dataElement), dataElement) && isBlack(getRightChild(siblingNode, dataElement), dataElement)) {
                    makeRed(siblingNode, dataElement);
                    currentNode = getParent(currentNode, dataElement);
                } else {
                    if (isBlack(getRightChild(siblingNode, dataElement), dataElement)) {
                        makeBlack(getLeftChild(siblingNode, dataElement), dataElement);
                        makeRed(siblingNode, dataElement);
                        rotateRight(siblingNode, dataElement);
                        siblingNode = getRightChild(getParent(currentNode, dataElement), dataElement);
                    }
                    copyColor(getParent(currentNode, dataElement), siblingNode, dataElement);
                    makeBlack(getParent(currentNode, dataElement), dataElement);
                    makeBlack(getRightChild(siblingNode, dataElement), dataElement);
                    rotateLeft(getParent(currentNode, dataElement), dataElement);
                    currentNode = this.rootNode[dataElement.ordinal()];
                }
            } else {
                Node<K, V> siblingNode2 = getLeftChild(getParent(currentNode, dataElement), dataElement);
                if (isRed(siblingNode2, dataElement)) {
                    makeBlack(siblingNode2, dataElement);
                    makeRed(getParent(currentNode, dataElement), dataElement);
                    rotateRight(getParent(currentNode, dataElement), dataElement);
                    siblingNode2 = getLeftChild(getParent(currentNode, dataElement), dataElement);
                }
                if (isBlack(getRightChild(siblingNode2, dataElement), dataElement) && isBlack(getLeftChild(siblingNode2, dataElement), dataElement)) {
                    makeRed(siblingNode2, dataElement);
                    currentNode = getParent(currentNode, dataElement);
                } else {
                    if (isBlack(getLeftChild(siblingNode2, dataElement), dataElement)) {
                        makeBlack(getRightChild(siblingNode2, dataElement), dataElement);
                        makeRed(siblingNode2, dataElement);
                        rotateLeft(siblingNode2, dataElement);
                        siblingNode2 = getLeftChild(getParent(currentNode, dataElement), dataElement);
                    }
                    copyColor(getParent(currentNode, dataElement), siblingNode2, dataElement);
                    makeBlack(getParent(currentNode, dataElement), dataElement);
                    makeBlack(getLeftChild(siblingNode2, dataElement), dataElement);
                    rotateRight(getParent(currentNode, dataElement), dataElement);
                    currentNode = this.rootNode[dataElement.ordinal()];
                }
            }
        }
        makeBlack(currentNode, dataElement);
    }

    private void doRedBlackInsert(Node<K, V> insertedNode, DataElement dataElement) {
        Node<K, V> currentNode = insertedNode;
        makeRed(currentNode, dataElement);
        while (currentNode != null && currentNode != this.rootNode[dataElement.ordinal()] && isRed(currentNode.getParent(dataElement), dataElement)) {
            if (currentNode.isLeftChild(dataElement)) {
                Node<K, V> y = getRightChild(getGrandParent(currentNode, dataElement), dataElement);
                if (!isRed(y, dataElement)) {
                    if (currentNode.isRightChild(dataElement)) {
                        currentNode = getParent(currentNode, dataElement);
                        rotateLeft(currentNode, dataElement);
                    }
                    makeBlack(getParent(currentNode, dataElement), dataElement);
                    makeRed(getGrandParent(currentNode, dataElement), dataElement);
                    if (getGrandParent(currentNode, dataElement) != null) {
                        rotateRight(getGrandParent(currentNode, dataElement), dataElement);
                    }
                } else {
                    makeBlack(getParent(currentNode, dataElement), dataElement);
                    makeBlack(y, dataElement);
                    makeRed(getGrandParent(currentNode, dataElement), dataElement);
                    currentNode = getGrandParent(currentNode, dataElement);
                }
            } else {
                Node<K, V> y2 = getLeftChild(getGrandParent(currentNode, dataElement), dataElement);
                if (!isRed(y2, dataElement)) {
                    if (currentNode.isLeftChild(dataElement)) {
                        currentNode = getParent(currentNode, dataElement);
                        rotateRight(currentNode, dataElement);
                    }
                    makeBlack(getParent(currentNode, dataElement), dataElement);
                    makeRed(getGrandParent(currentNode, dataElement), dataElement);
                    if (getGrandParent(currentNode, dataElement) != null) {
                        rotateLeft(getGrandParent(currentNode, dataElement), dataElement);
                    }
                } else {
                    makeBlack(getParent(currentNode, dataElement), dataElement);
                    makeBlack(y2, dataElement);
                    makeRed(getGrandParent(currentNode, dataElement), dataElement);
                    currentNode = getGrandParent(currentNode, dataElement);
                }
            }
        }
        makeBlack(this.rootNode[dataElement.ordinal()], dataElement);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public V doRemoveKey(Object key) {
        Node<K, V> node = lookupKey(key);
        if (node == null) {
            return null;
        }
        doRedBlackDelete(node);
        return node.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public K doRemoveValue(Object value) {
        Node<K, V> node = lookupValue(value);
        if (node == null) {
            return null;
        }
        doRedBlackDelete(node);
        return node.getKey();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String doToString(DataElement dataElement) {
        if (this.nodeCount == 0) {
            return "{}";
        }
        StringBuilder buf = new StringBuilder(this.nodeCount * 32);
        buf.append('{');
        MapIterator<?, ?> it = getMapIterator(dataElement);
        boolean hasNext = it.hasNext();
        while (hasNext) {
            Object key = it.next();
            Object value = it.getValue();
            buf.append(key == this ? "(this Map)" : key).append(Chars.EQ).append(value != this ? value : "(this Map)");
            hasNext = it.hasNext();
            if (hasNext) {
                buf.append(", ");
            }
        }
        buf.append('}');
        return buf.toString();
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    public Set<Map.Entry<K, V>> entrySet() {
        if (this.entrySet == null) {
            this.entrySet = new EntryView();
        }
        return this.entrySet;
    }

    @Override // java.util.Map
    public boolean equals(Object obj) {
        return doEquals(obj, DataElement.KEY);
    }

    @Override // org.apache.commons.collections4.OrderedMap
    public K firstKey() {
        if (this.nodeCount == 0) {
            throw new NoSuchElementException("Map is empty");
        }
        return leastNode(this.rootNode[DataElement.KEY.ordinal()], DataElement.KEY).getKey();
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    public V get(Object key) {
        checkKey(key);
        Node<K, V> node = lookupKey(key);
        if (node == null) {
            return null;
        }
        return node.getValue();
    }

    private Node<K, V> getGrandParent(Node<K, V> node, DataElement dataElement) {
        return getParent(getParent(node, dataElement), dataElement);
    }

    @Override // org.apache.commons.collections4.BidiMap
    public K getKey(Object value) {
        checkValue(value);
        Node<K, V> node = lookupValue(value);
        if (node == null) {
            return null;
        }
        return node.getKey();
    }

    private Node<K, V> getLeftChild(Node<K, V> node, DataElement dataElement) {
        if (node == null) {
            return null;
        }
        return node.getLeft(dataElement);
    }

    private MapIterator<?, ?> getMapIterator(DataElement dataElement) {
        switch (dataElement) {
            case KEY:
                return new ViewMapIterator(DataElement.KEY);
            case VALUE:
                return new InverseViewMapIterator(DataElement.VALUE);
            default:
                throw new IllegalArgumentException();
        }
    }

    private Node<K, V> getParent(Node<K, V> node, DataElement dataElement) {
        if (node == null) {
            return null;
        }
        return node.getParent(dataElement);
    }

    private Node<K, V> getRightChild(Node<K, V> node, DataElement dataElement) {
        if (node == null) {
            return null;
        }
        return node.getRight(dataElement);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Node<K, V> greatestNode(Node<K, V> node, DataElement dataElement) {
        Node<K, V> rval = node;
        if (rval != null) {
            while (rval.getRight(dataElement) != null) {
                rval = rval.getRight(dataElement);
            }
        }
        return rval;
    }

    private void grow() {
        modify();
        this.nodeCount++;
    }

    @Override // java.util.Map
    public int hashCode() {
        return doHashCode(DataElement.KEY);
    }

    private void insertValue(Node<K, V> newNode) throws IllegalArgumentException {
        Node<K, V> node = this.rootNode[DataElement.VALUE.ordinal()];
        while (true) {
            int cmp = compare(newNode.getValue(), node.getValue());
            if (cmp == 0) {
                throw new IllegalArgumentException("Cannot store a duplicate value (\"" + newNode.getData(DataElement.VALUE) + "\") in this Map");
            }
            if (cmp < 0) {
                if (node.getLeft(DataElement.VALUE) == null) {
                    node.setLeft(newNode, DataElement.VALUE);
                    newNode.setParent(node, DataElement.VALUE);
                    doRedBlackInsert(newNode, DataElement.VALUE);
                    return;
                }
                node = node.getLeft(DataElement.VALUE);
            } else {
                if (node.getRight(DataElement.VALUE) == null) {
                    node.setRight(newNode, DataElement.VALUE);
                    newNode.setParent(node, DataElement.VALUE);
                    doRedBlackInsert(newNode, DataElement.VALUE);
                    return;
                }
                node = node.getRight(DataElement.VALUE);
            }
        }
    }

    @Override // org.apache.commons.collections4.OrderedBidiMap, org.apache.commons.collections4.BidiMap
    public OrderedBidiMap<V, K> inverseBidiMap() {
        if (this.inverse == null) {
            this.inverse = new Inverse();
        }
        return this.inverse;
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    public boolean isEmpty() {
        return this.nodeCount == 0;
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    public Set<K> keySet() {
        if (this.keySet == null) {
            this.keySet = new KeyView(DataElement.KEY);
        }
        return this.keySet;
    }

    @Override // org.apache.commons.collections4.OrderedMap
    public K lastKey() {
        if (this.nodeCount == 0) {
            throw new NoSuchElementException("Map is empty");
        }
        return greatestNode(this.rootNode[DataElement.KEY.ordinal()], DataElement.KEY).getKey();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Node<K, V> leastNode(Node<K, V> node, DataElement dataElement) {
        Node<K, V> rval = node;
        if (rval != null) {
            while (rval.getLeft(dataElement) != null) {
                rval = rval.getLeft(dataElement);
            }
        }
        return rval;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T extends Comparable<T>> Node<K, V> lookup(Object data, DataElement dataElement) {
        Node<K, V> node = this.rootNode[dataElement.ordinal()];
        while (node != null) {
            int cmp = compare((Comparable) data, (Comparable) node.getData(dataElement));
            if (cmp == 0) {
                Node<K, V> rval = node;
                return rval;
            }
            node = cmp < 0 ? node.getLeft(dataElement) : node.getRight(dataElement);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Node<K, V> lookupKey(Object key) {
        return lookup(key, DataElement.KEY);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Node<K, V> lookupValue(Object value) {
        return lookup(value, DataElement.VALUE);
    }

    @Override // org.apache.commons.collections4.IterableGet
    public OrderedMapIterator<K, V> mapIterator() {
        if (isEmpty()) {
            return EmptyOrderedMapIterator.emptyOrderedMapIterator();
        }
        return new ViewMapIterator(DataElement.KEY);
    }

    private void modify() {
        this.modifications++;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Node<K, V> nextGreater(Node<K, V> node, DataElement dataElement) {
        if (node != null) {
            Node<K, V> rval = node.getRight(dataElement);
            if (rval != null) {
                Node<K, V> rval2 = leastNode(node.getRight(dataElement), dataElement);
                return rval2;
            }
            Node<K, V> parent = node.getParent(dataElement);
            Node<K, V> child = node;
            while (parent != null && child == parent.getRight(dataElement)) {
                child = parent;
                parent = parent.getParent(dataElement);
            }
            return parent;
        }
        return null;
    }

    @Override // org.apache.commons.collections4.OrderedMap
    public K nextKey(K key) {
        checkKey(key);
        Node<K, V> node = nextGreater(lookupKey(key), DataElement.KEY);
        if (node == null) {
            return null;
        }
        return node.getKey();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Node<K, V> nextSmaller(Node<K, V> node, DataElement dataElement) {
        if (node != null) {
            Node<K, V> rval = node.getLeft(dataElement);
            if (rval != null) {
                Node<K, V> rval2 = greatestNode(node.getLeft(dataElement), dataElement);
                return rval2;
            }
            Node<K, V> parent = node.getParent(dataElement);
            Node<K, V> child = node;
            while (parent != null && child == parent.getLeft(dataElement)) {
                child = parent;
                parent = parent.getParent(dataElement);
            }
            return parent;
        }
        return null;
    }

    @Override // org.apache.commons.collections4.OrderedMap
    public K previousKey(K key) {
        checkKey(key);
        Node<K, V> node = nextSmaller(lookupKey(key), DataElement.KEY);
        if (node == null) {
            return null;
        }
        return node.getKey();
    }

    @Override // org.apache.commons.collections4.BidiMap, java.util.Map, org.apache.commons.collections4.Put
    public V put(K key, V value) {
        V result = get((Object) key);
        doPut(key, value);
        return result;
    }

    @Override // java.util.Map, org.apache.commons.collections4.Put
    public void putAll(Map<? extends K, ? extends V> map) {
        for (Map.Entry<? extends K, ? extends V> e : map.entrySet()) {
            put((TreeBidiMap<K, V>) e.getKey(), (K) e.getValue());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.rootNode = new Node[2];
        int size = stream.readInt();
        for (int i = 0; i < size; i++) {
            put((TreeBidiMap<K, V>) stream.readObject(), (Comparable) stream.readObject());
        }
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    public V remove(Object key) {
        return doRemoveKey(key);
    }

    @Override // org.apache.commons.collections4.BidiMap
    public K removeValue(Object value) {
        return doRemoveValue(value);
    }

    private void rotateLeft(Node<K, V> node, DataElement dataElement) {
        Node<K, V> rightChild = node.getRight(dataElement);
        node.setRight(rightChild.getLeft(dataElement), dataElement);
        if (rightChild.getLeft(dataElement) != null) {
            rightChild.getLeft(dataElement).setParent(node, dataElement);
        }
        rightChild.setParent(node.getParent(dataElement), dataElement);
        if (node.getParent(dataElement) != null) {
            if (node.getParent(dataElement).getLeft(dataElement) == node) {
                node.getParent(dataElement).setLeft(rightChild, dataElement);
            } else {
                node.getParent(dataElement).setRight(rightChild, dataElement);
            }
        } else {
            this.rootNode[dataElement.ordinal()] = rightChild;
        }
        rightChild.setLeft(node, dataElement);
        node.setParent(rightChild, dataElement);
    }

    private void rotateRight(Node<K, V> node, DataElement dataElement) {
        Node<K, V> leftChild = node.getLeft(dataElement);
        node.setLeft(leftChild.getRight(dataElement), dataElement);
        if (leftChild.getRight(dataElement) != null) {
            leftChild.getRight(dataElement).setParent(node, dataElement);
        }
        leftChild.setParent(node.getParent(dataElement), dataElement);
        if (node.getParent(dataElement) != null) {
            if (node.getParent(dataElement).getRight(dataElement) == node) {
                node.getParent(dataElement).setRight(leftChild, dataElement);
            } else {
                node.getParent(dataElement).setLeft(leftChild, dataElement);
            }
        } else {
            this.rootNode[dataElement.ordinal()] = leftChild;
        }
        leftChild.setRight(node, dataElement);
        node.setParent(leftChild, dataElement);
    }

    private void shrink() {
        modify();
        this.nodeCount--;
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    public int size() {
        return this.nodeCount;
    }

    private void swapPosition(Node<K, V> x, Node<K, V> y, DataElement dataElement) {
        Node<K, V> xFormerParent = x.getParent(dataElement);
        Node<K, V> xFormerLeftChild = x.getLeft(dataElement);
        Node<K, V> xFormerRightChild = x.getRight(dataElement);
        Node<K, V> yFormerParent = y.getParent(dataElement);
        Node<K, V> yFormerLeftChild = y.getLeft(dataElement);
        Node<K, V> yFormerRightChild = y.getRight(dataElement);
        boolean xWasLeftChild = x.getParent(dataElement) != null && x == x.getParent(dataElement).getLeft(dataElement);
        boolean yWasLeftChild = y.getParent(dataElement) != null && y == y.getParent(dataElement).getLeft(dataElement);
        if (x == yFormerParent) {
            x.setParent(y, dataElement);
            if (yWasLeftChild) {
                y.setLeft(x, dataElement);
                y.setRight(xFormerRightChild, dataElement);
            } else {
                y.setRight(x, dataElement);
                y.setLeft(xFormerLeftChild, dataElement);
            }
        } else {
            x.setParent(yFormerParent, dataElement);
            if (yFormerParent != null) {
                if (yWasLeftChild) {
                    yFormerParent.setLeft(x, dataElement);
                } else {
                    yFormerParent.setRight(x, dataElement);
                }
            }
            y.setLeft(xFormerLeftChild, dataElement);
            y.setRight(xFormerRightChild, dataElement);
        }
        if (y == xFormerParent) {
            y.setParent(x, dataElement);
            if (xWasLeftChild) {
                x.setLeft(y, dataElement);
                x.setRight(yFormerRightChild, dataElement);
            } else {
                x.setRight(y, dataElement);
                x.setLeft(yFormerLeftChild, dataElement);
            }
        } else {
            y.setParent(xFormerParent, dataElement);
            if (xFormerParent != null) {
                if (xWasLeftChild) {
                    xFormerParent.setLeft(y, dataElement);
                } else {
                    xFormerParent.setRight(y, dataElement);
                }
            }
            x.setLeft(yFormerLeftChild, dataElement);
            x.setRight(yFormerRightChild, dataElement);
        }
        if (x.getLeft(dataElement) != null) {
            x.getLeft(dataElement).setParent(x, dataElement);
        }
        if (x.getRight(dataElement) != null) {
            x.getRight(dataElement).setParent(x, dataElement);
        }
        if (y.getLeft(dataElement) != null) {
            y.getLeft(dataElement).setParent(y, dataElement);
        }
        if (y.getRight(dataElement) != null) {
            y.getRight(dataElement).setParent(y, dataElement);
        }
        x.swapColors(y, dataElement);
        if (this.rootNode[dataElement.ordinal()] == x) {
            this.rootNode[dataElement.ordinal()] = y;
        } else if (this.rootNode[dataElement.ordinal()] == y) {
            this.rootNode[dataElement.ordinal()] = x;
        }
    }

    public String toString() {
        return doToString(DataElement.KEY);
    }

    @Override // org.apache.commons.collections4.BidiMap, java.util.Map, org.apache.commons.collections4.Get
    public Set<V> values() {
        if (this.valuesSet == null) {
            this.valuesSet = new ValueView(DataElement.KEY);
        }
        return this.valuesSet;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeInt(size());
        for (Map.Entry<K, V> entry : entrySet()) {
            out.writeObject(entry.getKey());
            out.writeObject(entry.getValue());
        }
    }
}
