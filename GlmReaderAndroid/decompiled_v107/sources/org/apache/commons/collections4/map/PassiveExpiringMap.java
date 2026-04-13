package org.apache.commons.collections4.map;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/* loaded from: classes9.dex */
public class PassiveExpiringMap<K, V> extends AbstractMapDecorator<K, V> implements Serializable {
    private static final long serialVersionUID = 1;
    private final Map<Object, Long> expirationMap;
    private final ExpirationPolicy<K, V> expiringPolicy;

    @FunctionalInterface
    /* loaded from: classes9.dex */
    public interface ExpirationPolicy<K, V> extends Serializable {
        long expirationTime(K k, V v);
    }

    /* loaded from: classes9.dex */
    public static class ConstantTimeToLiveExpirationPolicy<K, V> implements ExpirationPolicy<K, V> {
        private static final long serialVersionUID = 1;
        private final long timeToLiveMillis;

        public ConstantTimeToLiveExpirationPolicy() {
            this(-1L);
        }

        public ConstantTimeToLiveExpirationPolicy(long timeToLiveMillis) {
            this.timeToLiveMillis = timeToLiveMillis;
        }

        public ConstantTimeToLiveExpirationPolicy(long timeToLive, TimeUnit timeUnit) {
            this(PassiveExpiringMap.validateAndConvertToMillis(timeToLive, timeUnit));
        }

        @Override // org.apache.commons.collections4.map.PassiveExpiringMap.ExpirationPolicy
        public long expirationTime(K key, V value) {
            if (this.timeToLiveMillis < 0) {
                return -1L;
            }
            long nowMillis = System.currentTimeMillis();
            if (nowMillis > Long.MAX_VALUE - this.timeToLiveMillis) {
                return -1L;
            }
            return this.timeToLiveMillis + nowMillis;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static long validateAndConvertToMillis(long timeToLive, TimeUnit timeUnit) {
        Objects.requireNonNull(timeUnit, "timeUnit");
        return TimeUnit.MILLISECONDS.convert(timeToLive, timeUnit);
    }

    public PassiveExpiringMap() {
        this(-1L);
    }

    public PassiveExpiringMap(ExpirationPolicy<K, V> expiringPolicy) {
        this(expiringPolicy, new HashMap());
    }

    public PassiveExpiringMap(ExpirationPolicy<K, V> expiringPolicy, Map<K, V> map) {
        super(map);
        this.expirationMap = new HashMap();
        this.expiringPolicy = (ExpirationPolicy) Objects.requireNonNull(expiringPolicy, "expiringPolicy");
    }

    public PassiveExpiringMap(long timeToLiveMillis) {
        this(new ConstantTimeToLiveExpirationPolicy(timeToLiveMillis), new HashMap());
    }

    public PassiveExpiringMap(long timeToLiveMillis, Map<K, V> map) {
        this(new ConstantTimeToLiveExpirationPolicy(timeToLiveMillis), map);
    }

    public PassiveExpiringMap(long timeToLive, TimeUnit timeUnit) {
        this(validateAndConvertToMillis(timeToLive, timeUnit));
    }

    public PassiveExpiringMap(long timeToLive, TimeUnit timeUnit, Map<K, V> map) {
        this(validateAndConvertToMillis(timeToLive, timeUnit), map);
    }

    public PassiveExpiringMap(Map<K, V> map) {
        this(-1L, map);
    }

    @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Put
    public void clear() {
        super.clear();
        this.expirationMap.clear();
    }

    @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Get
    public boolean containsKey(Object key) {
        removeIfExpired(key, now());
        return super.containsKey(key);
    }

    @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Get
    public boolean containsValue(Object value) {
        removeAllExpired(now());
        return super.containsValue(value);
    }

    @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Get
    public Set<Map.Entry<K, V>> entrySet() {
        removeAllExpired(now());
        return super.entrySet();
    }

    @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Get
    public V get(Object obj) {
        removeIfExpired(obj, now());
        return (V) super.get(obj);
    }

    @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Get
    public boolean isEmpty() {
        removeAllExpired(now());
        return super.isEmpty();
    }

    private boolean isExpired(long now, Long expirationTimeObject) {
        if (expirationTimeObject == null) {
            return false;
        }
        long expirationTime = expirationTimeObject.longValue();
        return expirationTime >= 0 && now >= expirationTime;
    }

    @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Get
    public Set<K> keySet() {
        removeAllExpired(now());
        return super.keySet();
    }

    private long now() {
        return System.currentTimeMillis();
    }

    @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Put
    public V put(K k, V v) {
        removeIfExpired(k, now());
        this.expirationMap.put(k, Long.valueOf(this.expiringPolicy.expirationTime(k, v)));
        return (V) super.put(k, v);
    }

    @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Put
    public void putAll(Map<? extends K, ? extends V> mapToCopy) {
        for (Map.Entry<? extends K, ? extends V> entry : mapToCopy.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.map = (Map) in.readObject();
    }

    @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Get
    public V remove(Object obj) {
        this.expirationMap.remove(obj);
        return (V) super.remove(obj);
    }

    private void removeAllExpired(long nowMillis) {
        Iterator<Map.Entry<Object, Long>> iter = this.expirationMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<Object, Long> expirationEntry = iter.next();
            if (isExpired(nowMillis, expirationEntry.getValue())) {
                super.remove(expirationEntry.getKey());
                iter.remove();
            }
        }
    }

    private void removeIfExpired(Object key, long nowMillis) {
        Long expirationTimeObject = this.expirationMap.get(key);
        if (isExpired(nowMillis, expirationTimeObject)) {
            remove(key);
        }
    }

    @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Get
    public int size() {
        removeAllExpired(now());
        return super.size();
    }

    @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Get
    public Collection<V> values() {
        removeAllExpired(now());
        return super.values();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(this.map);
    }
}
