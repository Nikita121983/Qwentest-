package org.apache.commons.collections4.map;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.apache.commons.collections4.Factory;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.functors.ConstantTransformer;
import org.apache.commons.collections4.functors.FactoryTransformer;

/* loaded from: classes9.dex */
public class DefaultedMap<K, V> extends AbstractMapDecorator<K, V> implements Serializable {
    private static final long serialVersionUID = 19698628745827L;
    private final Transformer<? super K, ? extends V> value;

    public static <K, V> DefaultedMap<K, V> defaultedMap(Map<K, V> map, Factory<? extends V> factory) {
        return new DefaultedMap<>(map, FactoryTransformer.factoryTransformer((Factory) Objects.requireNonNull(factory, "Factory must not be null")));
    }

    public static <K, V> Map<K, V> defaultedMap(Map<K, V> map, Transformer<? super K, ? extends V> transformer) {
        return new DefaultedMap(map, (Transformer) Objects.requireNonNull(transformer, "Transformer must not be null"));
    }

    public static <K, V> DefaultedMap<K, V> defaultedMap(Map<K, V> map, V defaultValue) {
        return new DefaultedMap<>(map, ConstantTransformer.constantTransformer(defaultValue));
    }

    protected DefaultedMap(Map<K, V> map, Transformer<? super K, ? extends V> defaultValueTransformer) {
        super(map);
        this.value = (Transformer) Objects.requireNonNull(defaultValueTransformer, "defaultValueTransformer");
    }

    public DefaultedMap(Transformer<? super K, ? extends V> defaultValueTransformer) {
        this(new HashMap(), defaultValueTransformer);
    }

    public DefaultedMap(V defaultValue) {
        this(ConstantTransformer.constantTransformer(defaultValue));
    }

    @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Get
    public V get(Object key) {
        V v = this.map.get(key);
        if (v != null || this.map.containsKey(key)) {
            return v;
        }
        return this.value.apply(key);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.map = (Map) in.readObject();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(this.map);
    }
}
