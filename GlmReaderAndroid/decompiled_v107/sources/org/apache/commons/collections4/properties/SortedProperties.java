package org.apache.commons.collections4.properties;

import java.util.AbstractMap;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.collections4.iterators.IteratorEnumeration;

/* loaded from: classes9.dex */
public class SortedProperties extends Properties {
    private static final long serialVersionUID = 1;

    public static /* synthetic */ LinkedHashSet $r8$lambda$8t7les3oNGAWx5tdDX7k2wROC5E() {
        return new LinkedHashSet();
    }

    @Override // java.util.Hashtable, java.util.Map
    public Set<Map.Entry<Object, Object>> entrySet() {
        return (Set) sortedKeys().map(new Function() { // from class: org.apache.commons.collections4.properties.SortedProperties$$ExternalSyntheticLambda1
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return SortedProperties.this.m2052x9fec02ac((String) obj);
            }
        }).collect(Collectors.toCollection(new Supplier() { // from class: org.apache.commons.collections4.properties.SortedProperties$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return SortedProperties.$r8$lambda$8t7les3oNGAWx5tdDX7k2wROC5E();
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$entrySet$0$org-apache-commons-collections4-properties-SortedProperties, reason: not valid java name */
    public /* synthetic */ AbstractMap.SimpleEntry m2052x9fec02ac(String k) {
        return new AbstractMap.SimpleEntry(k, getProperty(k));
    }

    @Override // java.util.Hashtable, java.util.Dictionary
    public synchronized Enumeration<Object> keys() {
        return new IteratorEnumeration(((List) sortedKeys().collect(Collectors.toList())).iterator());
    }

    private Stream<String> sortedKeys() {
        return keySet().stream().map(new Function() { // from class: org.apache.commons.collections4.properties.SortedProperties$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                String obj2;
                obj2 = obj.toString();
                return obj2;
            }
        }).sorted();
    }
}
