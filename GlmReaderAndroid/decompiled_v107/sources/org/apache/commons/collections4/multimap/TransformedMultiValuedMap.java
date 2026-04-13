package org.apache.commons.collections4.multimap;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.FluentIterable;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.Transformer;

/* loaded from: classes9.dex */
public class TransformedMultiValuedMap<K, V> extends AbstractMultiValuedMapDecorator<K, V> {
    private static final long serialVersionUID = 20150612;
    private final Transformer<? super K, ? extends K> keyTransformer;
    private final Transformer<? super V, ? extends V> valueTransformer;

    public static <K, V> TransformedMultiValuedMap<K, V> transformedMap(MultiValuedMap<K, V> map, Transformer<? super K, ? extends K> keyTransformer, Transformer<? super V, ? extends V> valueTransformer) {
        TransformedMultiValuedMap<K, V> decorated = new TransformedMultiValuedMap<>(map, keyTransformer, valueTransformer);
        if (!map.isEmpty()) {
            ArrayListValuedHashMap arrayListValuedHashMap = new ArrayListValuedHashMap(map);
            decorated.clear();
            decorated.putAll(arrayListValuedHashMap);
        }
        return decorated;
    }

    public static <K, V> TransformedMultiValuedMap<K, V> transformingMap(MultiValuedMap<K, V> map, Transformer<? super K, ? extends K> keyTransformer, Transformer<? super V, ? extends V> valueTransformer) {
        return new TransformedMultiValuedMap<>(map, keyTransformer, valueTransformer);
    }

    protected TransformedMultiValuedMap(MultiValuedMap<K, V> map, Transformer<? super K, ? extends K> keyTransformer, Transformer<? super V, ? extends V> valueTransformer) {
        super(map);
        this.keyTransformer = keyTransformer;
        this.valueTransformer = valueTransformer;
    }

    @Override // org.apache.commons.collections4.multimap.AbstractMultiValuedMapDecorator, org.apache.commons.collections4.MultiValuedMap
    public boolean put(K key, V value) {
        return decorated().put(transformKey(key), transformValue(value));
    }

    @Override // org.apache.commons.collections4.multimap.AbstractMultiValuedMapDecorator, org.apache.commons.collections4.MultiValuedMap
    public boolean putAll(K key, Iterable<? extends V> values) {
        Objects.requireNonNull(values, "values");
        Iterable<V> transformedValues = FluentIterable.of((Iterable) values).transform(this.valueTransformer);
        Iterator<V> it = transformedValues.iterator();
        return it.hasNext() && CollectionUtils.addAll(decorated().get(transformKey(key)), it);
    }

    @Override // org.apache.commons.collections4.multimap.AbstractMultiValuedMapDecorator, org.apache.commons.collections4.MultiValuedMap
    public boolean putAll(Map<? extends K, ? extends V> map) {
        Objects.requireNonNull(map, "map");
        boolean changed = false;
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            changed |= put(entry.getKey(), entry.getValue());
        }
        return changed;
    }

    @Override // org.apache.commons.collections4.multimap.AbstractMultiValuedMapDecorator, org.apache.commons.collections4.MultiValuedMap
    public boolean putAll(MultiValuedMap<? extends K, ? extends V> map) {
        Objects.requireNonNull(map, "map");
        boolean changed = false;
        for (Map.Entry<? extends K, ? extends V> entry : map.entries()) {
            changed |= put(entry.getKey(), entry.getValue());
        }
        return changed;
    }

    protected K transformKey(K object) {
        if (this.keyTransformer == null) {
            return object;
        }
        return this.keyTransformer.apply(object);
    }

    protected V transformValue(V object) {
        if (this.valueTransformer == null) {
            return object;
        }
        return this.valueTransformer.apply(object);
    }
}
