package org.apache.commons.collections4.multimap;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import org.apache.commons.collections4.MultiValuedMap;

/* loaded from: classes9.dex */
public class HashSetValuedHashMap<K, V> extends AbstractSetValuedMap<K, V> implements Serializable {
    private static final int DEFAULT_INITIAL_MAP_CAPACITY = 16;
    private static final int DEFAULT_INITIAL_SET_CAPACITY = 3;
    private static final long serialVersionUID = 20151118;
    private final int initialSetCapacity;

    public HashSetValuedHashMap() {
        this(16, 3);
    }

    public HashSetValuedHashMap(int initialSetCapacity) {
        this(16, initialSetCapacity);
    }

    public HashSetValuedHashMap(int initialMapCapacity, int initialSetCapacity) {
        super(new HashMap(initialMapCapacity));
        this.initialSetCapacity = initialSetCapacity;
    }

    public HashSetValuedHashMap(Map<? extends K, ? extends V> map) {
        this(map.size(), 3);
        super.putAll(map);
    }

    public HashSetValuedHashMap(MultiValuedMap<? extends K, ? extends V> map) {
        this(map.size(), 3);
        super.putAll(map);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.collections4.multimap.AbstractSetValuedMap, org.apache.commons.collections4.multimap.AbstractMultiValuedMap
    public HashSet<V> createCollection() {
        return new HashSet<>(this.initialSetCapacity);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        setMap(new HashMap());
        doReadObject(in);
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        doWriteObject(out);
    }
}
