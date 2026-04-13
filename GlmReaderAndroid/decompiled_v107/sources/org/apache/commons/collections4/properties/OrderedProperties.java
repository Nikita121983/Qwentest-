package org.apache.commons.collections4.properties;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import org.apache.logging.log4j.util.Chars;

/* loaded from: classes9.dex */
public class OrderedProperties extends Properties {
    private static final long serialVersionUID = 1;
    private final LinkedHashSet<Object> orderedKeys = new LinkedHashSet<>();

    public static /* synthetic */ LinkedHashSet $r8$lambda$8t7les3oNGAWx5tdDX7k2wROC5E() {
        return new LinkedHashSet();
    }

    @Override // java.util.Hashtable, java.util.Map
    public synchronized void clear() {
        this.orderedKeys.clear();
        super.clear();
    }

    @Override // java.util.Hashtable, java.util.Map
    public synchronized Object compute(Object key, BiFunction<? super Object, ? super Object, ? extends Object> remappingFunction) {
        Object compute;
        compute = super.compute(key, remappingFunction);
        if (compute != null) {
            this.orderedKeys.add(key);
        }
        return compute;
    }

    @Override // java.util.Hashtable, java.util.Map
    public synchronized Object computeIfAbsent(Object key, Function<? super Object, ? extends Object> mappingFunction) {
        Object computeIfAbsent;
        computeIfAbsent = super.computeIfAbsent(key, mappingFunction);
        if (computeIfAbsent != null) {
            this.orderedKeys.add(key);
        }
        return computeIfAbsent;
    }

    @Override // java.util.Hashtable, java.util.Map
    public Set<Map.Entry<Object, Object>> entrySet() {
        return (Set) this.orderedKeys.stream().map(new Function() { // from class: org.apache.commons.collections4.properties.OrderedProperties$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return OrderedProperties.this.m2049x72a44a24(obj);
            }
        }).collect(Collectors.toCollection(new Supplier() { // from class: org.apache.commons.collections4.properties.OrderedProperties$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return OrderedProperties.$r8$lambda$8t7les3oNGAWx5tdDX7k2wROC5E();
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$entrySet$0$org-apache-commons-collections4-properties-OrderedProperties, reason: not valid java name */
    public /* synthetic */ AbstractMap.SimpleEntry m2049x72a44a24(Object k) {
        return new AbstractMap.SimpleEntry(k, get(k));
    }

    @Override // java.util.Hashtable, java.util.Map
    public synchronized void forEach(final BiConsumer<? super Object, ? super Object> action) {
        Objects.requireNonNull(action);
        this.orderedKeys.forEach(new Consumer() { // from class: org.apache.commons.collections4.properties.OrderedProperties$$ExternalSyntheticLambda2
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                OrderedProperties.this.m2050x46362603(action, obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$forEach$1$org-apache-commons-collections4-properties-OrderedProperties, reason: not valid java name */
    public /* synthetic */ void m2050x46362603(BiConsumer action, Object k) {
        action.accept(k, get(k));
    }

    @Override // java.util.Hashtable, java.util.Dictionary
    public synchronized Enumeration<Object> keys() {
        return Collections.enumeration(this.orderedKeys);
    }

    @Override // java.util.Hashtable, java.util.Map
    public Set<Object> keySet() {
        return this.orderedKeys;
    }

    @Override // java.util.Hashtable, java.util.Map
    public synchronized Object merge(Object key, Object value, BiFunction<? super Object, ? super Object, ? extends Object> remappingFunction) {
        this.orderedKeys.add(key);
        return super.merge(key, value, remappingFunction);
    }

    @Override // java.util.Properties
    public Enumeration<?> propertyNames() {
        return Collections.enumeration(this.orderedKeys);
    }

    @Override // java.util.Hashtable, java.util.Dictionary, java.util.Map
    public synchronized Object put(Object key, Object value) {
        Object put;
        put = super.put(key, value);
        if (put == null) {
            this.orderedKeys.add(key);
        }
        return put;
    }

    @Override // java.util.Hashtable, java.util.Map
    public synchronized void putAll(Map<? extends Object, ? extends Object> t) {
        this.orderedKeys.addAll(t.keySet());
        super.putAll(t);
    }

    @Override // java.util.Hashtable, java.util.Map
    public synchronized Object putIfAbsent(Object key, Object value) {
        Object putIfAbsent;
        putIfAbsent = super.putIfAbsent(key, value);
        if (putIfAbsent == null) {
            this.orderedKeys.add(key);
        }
        return putIfAbsent;
    }

    @Override // java.util.Hashtable, java.util.Dictionary, java.util.Map
    public synchronized Object remove(Object key) {
        Object remove;
        remove = super.remove(key);
        if (remove != null) {
            this.orderedKeys.remove(key);
        }
        return remove;
    }

    @Override // java.util.Hashtable, java.util.Map
    public synchronized boolean remove(Object key, Object value) {
        boolean remove;
        remove = super.remove(key, value);
        if (remove) {
            this.orderedKeys.remove(key);
        }
        return remove;
    }

    @Override // java.util.Hashtable
    public synchronized String toString() {
        int max = size() - 1;
        if (max == -1) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder();
        Iterator<Map.Entry<Object, Object>> it = entrySet().iterator();
        sb.append('{');
        int i = 0;
        while (true) {
            Map.Entry<Object, Object> e = it.next();
            Object key = e.getKey();
            Object value = e.getValue();
            sb.append(key == this ? "(this Map)" : key.toString());
            sb.append(Chars.EQ);
            sb.append(value == this ? "(this Map)" : value.toString());
            if (i == max) {
                return sb.append('}').toString();
            }
            sb.append(", ");
            i++;
        }
    }
}
