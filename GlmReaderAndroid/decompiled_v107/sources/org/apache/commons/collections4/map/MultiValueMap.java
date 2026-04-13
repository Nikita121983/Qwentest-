package org.apache.commons.collections4.map;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Factory;
import org.apache.commons.collections4.FunctorException;
import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.iterators.EmptyIterator;
import org.apache.commons.collections4.iterators.IteratorChain;
import org.apache.commons.collections4.iterators.LazyIteratorChain;
import org.apache.commons.collections4.iterators.TransformIterator;
import org.apache.commons.collections4.map.MultiValueMap;

@Deprecated
/* loaded from: classes9.dex */
public class MultiValueMap<K, V> extends AbstractMapDecorator<K, Object> implements MultiMap<K, V>, Serializable {
    private static final long serialVersionUID = -2214159910087182007L;
    private final Factory<? extends Collection<V>> collectionFactory;
    private transient Collection<V> valuesView;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class ReflectionFactory<T extends Collection<?>> implements Factory<T>, Serializable {
        private static final long serialVersionUID = 2986114157496788874L;
        private final Class<T> clazz;

        ReflectionFactory(Class<T> clazz) {
            this.clazz = clazz;
        }

        @Override // org.apache.commons.collections4.Factory
        public T create() {
            try {
                return this.clazz.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            } catch (Exception ex) {
                throw new FunctorException("Cannot instantiate class: " + this.clazz, ex);
            }
        }

        private void readObject(ObjectInputStream is) throws IOException, ClassNotFoundException {
            is.defaultReadObject();
            if (this.clazz != null && !Collection.class.isAssignableFrom(this.clazz)) {
                throw new UnsupportedOperationException();
            }
        }
    }

    /* loaded from: classes9.dex */
    private final class Values extends AbstractCollection<V> {
        private Values() {
        }

        /* synthetic */ Values(MultiValueMap x0, AnonymousClass1 x1) {
            this();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            MultiValueMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            IteratorChain<V> chain = new IteratorChain<>();
            for (K k : MultiValueMap.this.keySet()) {
                chain.addIterator(new ValuesIterator(k));
            }
            return chain;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return MultiValueMap.this.totalSize();
        }
    }

    /* loaded from: classes9.dex */
    private final class ValuesIterator implements Iterator<V> {
        private final Iterator<V> iterator;
        private final Object key;
        private final Collection<V> values;

        ValuesIterator(Object key) {
            this.key = key;
            this.values = MultiValueMap.this.getCollection(key);
            this.iterator = this.values.iterator();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        @Override // java.util.Iterator
        public V next() {
            return this.iterator.next();
        }

        @Override // java.util.Iterator
        public void remove() {
            this.iterator.remove();
            if (this.values.isEmpty()) {
                MultiValueMap.this.remove(this.key);
            }
        }
    }

    public static <K, V, C extends Collection<V>> MultiValueMap<K, V> multiValueMap(Map<K, ? super C> map, Class<C> collectionClass) {
        return new MultiValueMap<>(map, new ReflectionFactory(collectionClass));
    }

    public static <K, V, C extends Collection<V>> MultiValueMap<K, V> multiValueMap(Map<K, ? super C> map, Factory<C> collectionFactory) {
        return new MultiValueMap<>(map, collectionFactory);
    }

    public static <K, V> MultiValueMap<K, V> multiValueMap(Map<K, ? super Collection<V>> map) {
        return multiValueMap(map, ArrayList.class);
    }

    public MultiValueMap() {
        this(new HashMap(), new ReflectionFactory(ArrayList.class));
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected <C extends Collection<V>> MultiValueMap(Map<K, ? super C> map, Factory<C> factory) {
        super(map);
        if (factory == 0) {
            throw new IllegalArgumentException("The factory must not be null");
        }
        this.collectionFactory = factory;
    }

    @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Put
    public void clear() {
        decorated().clear();
    }

    @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Get
    public boolean containsValue(Object value) {
        Set<Map.Entry<K, Object>> pairs = decorated().entrySet();
        if (pairs != null) {
            for (Map.Entry<K, Object> entry : pairs) {
                if (((Collection) entry.getValue()).contains(value)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public boolean containsValue(Object key, Object value) {
        Collection<V> coll = getCollection(key);
        if (coll == null) {
            return false;
        }
        return coll.contains(value);
    }

    protected Collection<V> createCollection(int size) {
        return this.collectionFactory.get();
    }

    @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Get
    public Set<Map.Entry<K, Object>> entrySet() {
        return super.entrySet();
    }

    public Collection<V> getCollection(Object key) {
        return (Collection) decorated().get(key);
    }

    public Iterator<Map.Entry<K, V>> iterator() {
        Collection<K> allKeys = new ArrayList<>(keySet());
        Iterator<K> keyIterator = allKeys.iterator();
        return new AnonymousClass1(this, keyIterator);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.apache.commons.collections4.map.MultiValueMap$1, reason: invalid class name */
    /* loaded from: classes9.dex */
    public class AnonymousClass1 extends LazyIteratorChain<Map.Entry<K, V>> {
        final /* synthetic */ MultiValueMap this$0;
        final /* synthetic */ Iterator val$keyIterator;

        AnonymousClass1(MultiValueMap this$0, Iterator it) {
            this.val$keyIterator = it;
            this.this$0 = this$0;
        }

        @Override // org.apache.commons.collections4.iterators.LazyIteratorChain
        protected Iterator<? extends Map.Entry<K, V>> nextIterator(int count) {
            if (!this.val$keyIterator.hasNext()) {
                return null;
            }
            final Object next = this.val$keyIterator.next();
            Transformer<V, Map.Entry<K, V>> transformer = new Transformer() { // from class: org.apache.commons.collections4.map.MultiValueMap$1$$ExternalSyntheticLambda0
                @Override // org.apache.commons.collections4.Transformer
                public final Object transform(Object obj) {
                    return MultiValueMap.AnonymousClass1.this.m2046xb3e34b93(next, obj);
                }
            };
            return new TransformIterator(new ValuesIterator(next), transformer);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$nextIterator$0$org-apache-commons-collections4-map-MultiValueMap$1, reason: not valid java name */
        public /* synthetic */ Map.Entry m2046xb3e34b93(final Object key, final Object input) {
            return new Map.Entry<K, V>(this) { // from class: org.apache.commons.collections4.map.MultiValueMap.1.1
                final /* synthetic */ AnonymousClass1 this$1;

                {
                    this.this$1 = this;
                }

                @Override // java.util.Map.Entry
                public K getKey() {
                    return (K) key;
                }

                @Override // java.util.Map.Entry
                public V getValue() {
                    return (V) input;
                }

                @Override // java.util.Map.Entry
                public V setValue(V value) {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }

    public Iterator<V> iterator(Object key) {
        if (!containsKey(key)) {
            return EmptyIterator.emptyIterator();
        }
        return new ValuesIterator(key);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Put
    public Object put(K key, Object obj) {
        boolean result = false;
        Collection<V> coll = getCollection(key);
        if (coll == null) {
            Collection<V> coll2 = createCollection(1);
            coll2.add(obj);
            if (!coll2.isEmpty()) {
                decorated().put(key, coll2);
                result = true;
            }
        } else {
            result = coll.add(obj);
        }
        if (result) {
            return obj;
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean putAll(K key, Collection<V> collection) {
        if (collection == 0 || collection.isEmpty()) {
            return false;
        }
        Collection<V> coll = getCollection(key);
        if (coll == null) {
            Collection<V> coll2 = createCollection(collection.size());
            coll2.addAll(collection);
            if (coll2.isEmpty()) {
                return false;
            }
            decorated().put(key, coll2);
            return true;
        }
        boolean result = coll.addAll(collection);
        return result;
    }

    @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Put
    public void putAll(Map<? extends K, ?> map) {
        if (map instanceof MultiMap) {
            for (Map.Entry<K, Object> entry : ((MultiMap) map).entrySet()) {
                putAll(entry.getKey(), (Collection) entry.getValue());
            }
            return;
        }
        for (Map.Entry<? extends K, ?> entry2 : map.entrySet()) {
            put(entry2.getKey(), entry2.getValue());
        }
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.map = (Map) in.readObject();
    }

    @Override // org.apache.commons.collections4.MultiMap
    public boolean removeMapping(Object key, Object value) {
        Collection<V> valuesForKey = getCollection(key);
        if (valuesForKey == null) {
            return false;
        }
        boolean removed = valuesForKey.remove(value);
        if (!removed) {
            return false;
        }
        if (valuesForKey.isEmpty()) {
            remove(key);
            return true;
        }
        return true;
    }

    public int size(Object key) {
        Collection<V> coll = getCollection(key);
        if (coll == null) {
            return 0;
        }
        return coll.size();
    }

    public int totalSize() {
        int total = 0;
        for (Object v : decorated().values()) {
            total += CollectionUtils.size(v);
        }
        return total;
    }

    @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Get
    public Collection<Object> values() {
        Collection<V> vs = this.valuesView;
        if (vs != null) {
            return vs;
        }
        Values values = new Values(this, null);
        this.valuesView = values;
        return values;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(this.map);
    }
}
