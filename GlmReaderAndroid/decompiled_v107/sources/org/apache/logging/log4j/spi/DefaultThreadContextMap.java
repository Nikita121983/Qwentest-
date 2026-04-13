package org.apache.logging.log4j.spi;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.apache.logging.log4j.internal.map.UnmodifiableArrayBackedMap;
import org.apache.logging.log4j.util.BiConsumer;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.apache.logging.log4j.util.ReadOnlyStringMap;
import org.apache.logging.log4j.util.TriConsumer;

/* loaded from: classes10.dex */
public class DefaultThreadContextMap implements ThreadContextMap, ReadOnlyStringMap {
    public static final String INHERITABLE_MAP = "isThreadContextMapInheritable";
    private static final long serialVersionUID = -2635197170958057849L;
    private ThreadLocal<Object[]> localState;

    public DefaultThreadContextMap() {
        this(PropertiesUtil.getProperties());
    }

    @Deprecated
    public DefaultThreadContextMap(final boolean ignored) {
        this(PropertiesUtil.getProperties());
    }

    DefaultThreadContextMap(final PropertiesUtil properties) {
        ThreadLocal<Object[]> threadLocal;
        if (properties.getBooleanProperty(INHERITABLE_MAP)) {
            threadLocal = new InheritableThreadLocal<Object[]>() { // from class: org.apache.logging.log4j.spi.DefaultThreadContextMap.1
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // java.lang.InheritableThreadLocal
                public Object[] childValue(final Object[] parentValue) {
                    return parentValue;
                }
            };
        } else {
            threadLocal = new ThreadLocal<>();
        }
        this.localState = threadLocal;
    }

    @Override // org.apache.logging.log4j.spi.ThreadContextMap
    public void put(final String key, final String value) {
        Object[] state = this.localState.get();
        this.localState.set(UnmodifiableArrayBackedMap.getMap(state).copyAndPut(key, value).getBackingArray());
    }

    public void putAll(final Map<String, String> m) {
        Object[] state = this.localState.get();
        this.localState.set(UnmodifiableArrayBackedMap.getMap(state).copyAndPutAll(m).getBackingArray());
    }

    @Override // org.apache.logging.log4j.spi.ThreadContextMap
    public String get(final String key) {
        Object[] state = this.localState.get();
        if (state == null) {
            return null;
        }
        return UnmodifiableArrayBackedMap.getMap(state).get((Object) key);
    }

    @Override // org.apache.logging.log4j.spi.ThreadContextMap
    public void remove(final String key) {
        Object[] state = this.localState.get();
        if (state != null) {
            this.localState.set(UnmodifiableArrayBackedMap.getMap(state).copyAndRemove(key).getBackingArray());
        }
    }

    public void removeAll(final Iterable<String> keys) {
        Object[] state = this.localState.get();
        if (state != null) {
            this.localState.set(UnmodifiableArrayBackedMap.getMap(state).copyAndRemoveAll(keys).getBackingArray());
        }
    }

    @Override // org.apache.logging.log4j.spi.ThreadContextMap
    public void clear() {
        this.localState.remove();
    }

    @Override // org.apache.logging.log4j.util.ReadOnlyStringMap
    public Map<String, String> toMap() {
        return getCopy();
    }

    @Override // org.apache.logging.log4j.spi.ThreadContextMap, org.apache.logging.log4j.util.ReadOnlyStringMap
    public boolean containsKey(final String key) {
        Object[] state = this.localState.get();
        return state != null && UnmodifiableArrayBackedMap.getMap(state).containsKey(key);
    }

    @Override // org.apache.logging.log4j.util.ReadOnlyStringMap
    public <V> void forEach(final BiConsumer<String, ? super V> action) {
        Object[] state = this.localState.get();
        if (state == null) {
            return;
        }
        UnmodifiableArrayBackedMap.getMap(state).forEach((BiConsumer) action);
    }

    @Override // org.apache.logging.log4j.util.ReadOnlyStringMap
    public <V, S> void forEach(final TriConsumer<String, ? super V, S> action, final S state) {
        Object[] localState = this.localState.get();
        if (localState == null) {
            return;
        }
        UnmodifiableArrayBackedMap.getMap(localState).forEach(action, state);
    }

    @Override // org.apache.logging.log4j.util.ReadOnlyStringMap
    public <V> V getValue(String str) {
        return (V) get(str);
    }

    @Override // org.apache.logging.log4j.spi.ThreadContextMap
    public Map<String, String> getCopy() {
        Object[] state = this.localState.get();
        if (state == null) {
            return new HashMap(0);
        }
        return new HashMap(UnmodifiableArrayBackedMap.getMap(state));
    }

    @Override // org.apache.logging.log4j.spi.ThreadContextMap
    public Map<String, String> getImmutableMapOrNull() {
        Object[] state = this.localState.get();
        if (state == null) {
            return null;
        }
        return UnmodifiableArrayBackedMap.getMap(state);
    }

    @Override // org.apache.logging.log4j.spi.ThreadContextMap, org.apache.logging.log4j.util.ReadOnlyStringMap
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override // org.apache.logging.log4j.util.ReadOnlyStringMap, java.util.Map
    public int size() {
        Object[] state = this.localState.get();
        return UnmodifiableArrayBackedMap.getMap(state).size();
    }

    public String toString() {
        Object[] state = this.localState.get();
        return state == null ? "{}" : UnmodifiableArrayBackedMap.getMap(state).toString();
    }

    public int hashCode() {
        Object[] state = this.localState.get();
        int result = (1 * 31) + (state == null ? 0 : UnmodifiableArrayBackedMap.getMap(state).hashCode());
        return result;
    }

    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ThreadContextMap)) {
            return false;
        }
        ThreadContextMap other = (ThreadContextMap) obj;
        Map<String, String> map = UnmodifiableArrayBackedMap.getMap(this.localState.get());
        Map<String, String> otherMap = other.getImmutableMapOrNull();
        return Objects.equals(map, otherMap);
    }
}
