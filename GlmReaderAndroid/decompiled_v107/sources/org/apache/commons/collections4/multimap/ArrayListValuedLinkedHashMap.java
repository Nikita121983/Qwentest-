package org.apache.commons.collections4.multimap;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.collections4.MultiValuedMap;

/* loaded from: classes9.dex */
public class ArrayListValuedLinkedHashMap<K, V> extends AbstractListValuedMap<K, V> implements Serializable {
    private static final int DEFAULT_INITIAL_LIST_CAPACITY = 3;
    private static final int DEFAULT_INITIAL_MAP_CAPACITY = 16;
    private static final long serialVersionUID = 20241014;
    private final int initialListCapacity;

    public ArrayListValuedLinkedHashMap() {
        this(16, 3);
    }

    public ArrayListValuedLinkedHashMap(int initialListCapacity) {
        this(16, initialListCapacity);
    }

    public ArrayListValuedLinkedHashMap(int initialMapCapacity, int initialListCapacity) {
        super(new LinkedHashMap(initialMapCapacity));
        this.initialListCapacity = initialListCapacity;
    }

    public ArrayListValuedLinkedHashMap(Map<? extends K, ? extends V> map) {
        this(map.size(), 3);
        super.putAll(map);
    }

    public ArrayListValuedLinkedHashMap(MultiValuedMap<? extends K, ? extends V> map) {
        this(map.size(), 3);
        super.putAll(map);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.collections4.multimap.AbstractListValuedMap, org.apache.commons.collections4.multimap.AbstractMultiValuedMap
    public ArrayList<V> createCollection() {
        return new ArrayList<>(this.initialListCapacity);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        setMap(new LinkedHashMap());
        doReadObject(in);
    }

    public void trimToSize() {
        for (Collection<V> coll : getMap().values()) {
            ArrayList<V> list = (ArrayList) coll;
            list.trimToSize();
        }
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        doWriteObject(out);
    }
}
