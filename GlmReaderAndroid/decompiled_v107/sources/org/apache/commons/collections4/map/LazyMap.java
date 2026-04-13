package org.apache.commons.collections4.map;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import org.apache.commons.collections4.Factory;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.functors.FactoryTransformer;

/* loaded from: classes9.dex */
public class LazyMap<K, V> extends AbstractMapDecorator<K, V> implements Serializable {
    private static final long serialVersionUID = 7990956402564206740L;
    protected final Transformer<? super K, ? extends V> factory;

    public static <K, V> LazyMap<K, V> lazyMap(Map<K, V> map, Factory<? extends V> factory) {
        return new LazyMap<>(map, factory);
    }

    public static <V, K> LazyMap<K, V> lazyMap(Map<K, V> map, Transformer<? super K, ? extends V> factory) {
        return new LazyMap<>(map, factory);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public LazyMap(Map<K, V> map, Factory<? extends V> factory) {
        super(map);
        this.factory = FactoryTransformer.factoryTransformer((Factory) Objects.requireNonNull(factory, "factory"));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public LazyMap(Map<K, V> map, Transformer<? super K, ? extends V> factory) {
        super(map);
        this.factory = (Transformer) Objects.requireNonNull(factory, "factory");
    }

    @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Get
    public V get(Object key) {
        if (!this.map.containsKey(key)) {
            V value = this.factory.apply(key);
            this.map.put(key, value);
            return value;
        }
        return this.map.get(key);
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
