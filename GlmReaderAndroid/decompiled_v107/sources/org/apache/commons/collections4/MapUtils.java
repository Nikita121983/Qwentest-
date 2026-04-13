package org.apache.commons.collections4;

import java.io.PrintStream;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.BiFunction;
import java.util.function.Function;
import org.apache.commons.collections4.map.AbstractMapDecorator;
import org.apache.commons.collections4.map.AbstractSortedMapDecorator;
import org.apache.commons.collections4.map.FixedSizeMap;
import org.apache.commons.collections4.map.FixedSizeSortedMap;
import org.apache.commons.collections4.map.LazyMap;
import org.apache.commons.collections4.map.LazySortedMap;
import org.apache.commons.collections4.map.ListOrderedMap;
import org.apache.commons.collections4.map.MultiValueMap;
import org.apache.commons.collections4.map.PredicatedMap;
import org.apache.commons.collections4.map.PredicatedSortedMap;
import org.apache.commons.collections4.map.TransformedMap;
import org.apache.commons.collections4.map.TransformedSortedMap;
import org.apache.commons.collections4.map.UnmodifiableMap;
import org.apache.commons.collections4.map.UnmodifiableSortedMap;
import org.apache.commons.math3.geometry.VectorFormat;
import org.apache.logging.log4j.util.Chars;

/* loaded from: classes9.dex */
public class MapUtils {
    public static final SortedMap EMPTY_SORTED_MAP = UnmodifiableSortedMap.unmodifiableSortedMap(new TreeMap());
    private static final String INDENT_STRING = "    ";

    private static <K, R> R applyDefaultFunction(Map<? super K, ?> map, K k, BiFunction<Map<? super K, ?>, K, R> biFunction, Function<K, R> function) {
        return (R) applyDefaultFunction(map, k, biFunction, function, null);
    }

    private static <K, R> R applyDefaultFunction(Map<? super K, ?> map, K key, BiFunction<Map<? super K, ?>, K, R> getFunction, Function<K, R> defaultFunction, R defaultValue) {
        R value = (map == null || getFunction == null) ? null : getFunction.apply(map, key);
        if (value == null) {
            value = defaultFunction != null ? defaultFunction.apply(key) : null;
        }
        return value != null ? value : defaultValue;
    }

    private static <K, R> R applyDefaultValue(Map<? super K, ?> map, K key, BiFunction<Map<? super K, ?>, K, R> getFunction, R defaultValue) {
        R value = (map == null || getFunction == null) ? null : getFunction.apply(map, key);
        return value == null ? defaultValue : value;
    }

    public static void debugPrint(PrintStream out, Object label, Map<?, ?> map) {
        verbosePrintInternal(out, label, map, new ArrayDeque(), true);
    }

    public static <K, V> Map<K, V> emptyIfNull(Map<K, V> map) {
        return map == null ? Collections.emptyMap() : map;
    }

    public static <K, V> IterableMap<K, V> fixedSizeMap(Map<K, V> map) {
        return FixedSizeMap.fixedSizeMap(map);
    }

    public static <K, V> SortedMap<K, V> fixedSizeSortedMap(SortedMap<K, V> map) {
        return FixedSizeSortedMap.fixedSizeSortedMap(map);
    }

    public static <K> Boolean getBoolean(Map<? super K, ?> map, K key) {
        Object answer;
        if (map != null && (answer = map.get(key)) != null) {
            if (answer instanceof Boolean) {
                return (Boolean) answer;
            }
            if (answer instanceof String) {
                return Boolean.valueOf((String) answer);
            }
            if (answer instanceof Number) {
                Number n = (Number) answer;
                return n.intValue() != 0 ? Boolean.TRUE : Boolean.FALSE;
            }
            return null;
        }
        return null;
    }

    public static <K> Boolean getBoolean(Map<? super K, ?> map, K key, Boolean defaultValue) {
        return (Boolean) applyDefaultValue(map, key, new MapUtils$$ExternalSyntheticLambda0(), defaultValue);
    }

    public static <K> Boolean getBoolean(Map<? super K, ?> map, K key, Function<K, Boolean> defaultFunction) {
        return (Boolean) applyDefaultFunction(map, key, new MapUtils$$ExternalSyntheticLambda0(), defaultFunction);
    }

    public static <K> boolean getBooleanValue(Map<? super K, ?> map, K key) {
        return Boolean.TRUE.equals(getBoolean(map, key));
    }

    public static <K> boolean getBooleanValue(Map<? super K, ?> map, K key, boolean defaultValue) {
        return ((Boolean) applyDefaultValue(map, key, new MapUtils$$ExternalSyntheticLambda0(), Boolean.valueOf(defaultValue))).booleanValue();
    }

    public static <K> boolean getBooleanValue(Map<? super K, ?> map, K key, Function<K, Boolean> defaultFunction) {
        return ((Boolean) applyDefaultFunction(map, key, new MapUtils$$ExternalSyntheticLambda0(), defaultFunction, false)).booleanValue();
    }

    public static <K> Byte getByte(Map<? super K, ?> map, K key) {
        Number answer = getNumber(map, key);
        if (answer == null) {
            return null;
        }
        if (answer instanceof Byte) {
            return (Byte) answer;
        }
        return Byte.valueOf(answer.byteValue());
    }

    public static <K> Byte getByte(Map<? super K, ?> map, K key, Byte defaultValue) {
        return (Byte) applyDefaultValue(map, key, new MapUtils$$ExternalSyntheticLambda10(), defaultValue);
    }

    public static <K> Byte getByte(Map<? super K, ?> map, K key, Function<K, Byte> defaultFunction) {
        return (Byte) applyDefaultFunction(map, key, new MapUtils$$ExternalSyntheticLambda10(), defaultFunction);
    }

    public static <K> byte getByteValue(Map<? super K, ?> map, K key) {
        return ((Number) applyDefaultValue(map, key, new BiFunction() { // from class: org.apache.commons.collections4.MapUtils$$ExternalSyntheticLambda2
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return MapUtils.getByte((Map) obj, obj2);
            }
        }, 0)).byteValue();
    }

    public static <K> byte getByteValue(Map<? super K, ?> map, K key, byte defaultValue) {
        return ((Byte) applyDefaultValue(map, key, new MapUtils$$ExternalSyntheticLambda10(), Byte.valueOf(defaultValue))).byteValue();
    }

    public static <K> byte getByteValue(Map<? super K, ?> map, K key, Function<K, Byte> defaultFunction) {
        return ((Byte) applyDefaultFunction(map, key, new MapUtils$$ExternalSyntheticLambda10(), defaultFunction, (byte) 0)).byteValue();
    }

    public static <K> Double getDouble(Map<? super K, ?> map, K key) {
        Number answer = getNumber(map, key);
        if (answer == null) {
            return null;
        }
        if (answer instanceof Double) {
            return (Double) answer;
        }
        return Double.valueOf(answer.doubleValue());
    }

    public static <K> Double getDouble(Map<? super K, ?> map, K key, Double defaultValue) {
        return (Double) applyDefaultValue(map, key, new MapUtils$$ExternalSyntheticLambda8(), defaultValue);
    }

    public static <K> Double getDouble(Map<? super K, ?> map, K key, Function<K, Double> defaultFunction) {
        return (Double) applyDefaultFunction(map, key, new MapUtils$$ExternalSyntheticLambda8(), defaultFunction);
    }

    public static <K> double getDoubleValue(Map<? super K, ?> map, K key) {
        return ((Double) applyDefaultValue(map, key, new MapUtils$$ExternalSyntheticLambda8(), Double.valueOf(0.0d))).doubleValue();
    }

    public static <K> double getDoubleValue(Map<? super K, ?> map, K key, double defaultValue) {
        return ((Double) applyDefaultValue(map, key, new MapUtils$$ExternalSyntheticLambda8(), Double.valueOf(defaultValue))).doubleValue();
    }

    public static <K> double getDoubleValue(Map<? super K, ?> map, K key, Function<K, Double> defaultFunction) {
        return ((Double) applyDefaultFunction(map, key, new MapUtils$$ExternalSyntheticLambda8(), defaultFunction, Double.valueOf(0.0d))).doubleValue();
    }

    public static <K> Float getFloat(Map<? super K, ?> map, K key) {
        Number answer = getNumber(map, key);
        if (answer == null) {
            return null;
        }
        if (answer instanceof Float) {
            return (Float) answer;
        }
        return Float.valueOf(answer.floatValue());
    }

    public static <K> Float getFloat(Map<? super K, ?> map, K key, Float defaultValue) {
        return (Float) applyDefaultValue(map, key, new MapUtils$$ExternalSyntheticLambda4(), defaultValue);
    }

    public static <K> Float getFloat(Map<? super K, ?> map, K key, Function<K, Float> defaultFunction) {
        return (Float) applyDefaultFunction(map, key, new MapUtils$$ExternalSyntheticLambda4(), defaultFunction);
    }

    public static <K> float getFloatValue(Map<? super K, ?> map, K key) {
        return ((Float) applyDefaultValue(map, key, new MapUtils$$ExternalSyntheticLambda4(), Float.valueOf(0.0f))).floatValue();
    }

    public static <K> float getFloatValue(Map<? super K, ?> map, K key, float defaultValue) {
        return ((Float) applyDefaultValue(map, key, new MapUtils$$ExternalSyntheticLambda4(), Float.valueOf(defaultValue))).floatValue();
    }

    public static <K> float getFloatValue(Map<? super K, ?> map, K key, Function<K, Float> defaultFunction) {
        return ((Float) applyDefaultFunction(map, key, new MapUtils$$ExternalSyntheticLambda4(), defaultFunction, Float.valueOf(0.0f))).floatValue();
    }

    public static <K> Integer getInteger(Map<? super K, ?> map, K key) {
        Number answer = getNumber(map, key);
        if (answer == null) {
            return null;
        }
        if (answer instanceof Integer) {
            return (Integer) answer;
        }
        return Integer.valueOf(answer.intValue());
    }

    public static <K> Integer getInteger(Map<? super K, ?> map, K key, Function<K, Integer> defaultFunction) {
        return (Integer) applyDefaultFunction(map, key, new MapUtils$$ExternalSyntheticLambda9(), defaultFunction);
    }

    public static <K> Integer getInteger(Map<? super K, ?> map, K key, Integer defaultValue) {
        return (Integer) applyDefaultValue(map, key, new MapUtils$$ExternalSyntheticLambda9(), defaultValue);
    }

    public static <K> int getIntValue(Map<? super K, ?> map, K key) {
        return ((Integer) applyDefaultValue(map, key, new MapUtils$$ExternalSyntheticLambda9(), 0)).intValue();
    }

    public static <K> int getIntValue(Map<? super K, ?> map, K key, Function<K, Integer> defaultFunction) {
        return ((Integer) applyDefaultFunction(map, key, new MapUtils$$ExternalSyntheticLambda9(), defaultFunction, 0)).byteValue();
    }

    public static <K> int getIntValue(Map<? super K, ?> map, K key, int defaultValue) {
        return ((Integer) applyDefaultValue(map, key, new MapUtils$$ExternalSyntheticLambda9(), Integer.valueOf(defaultValue))).intValue();
    }

    public static <K> Long getLong(Map<? super K, ?> map, K key) {
        Number answer = getNumber(map, key);
        if (answer == null) {
            return null;
        }
        if (answer instanceof Long) {
            return (Long) answer;
        }
        return Long.valueOf(answer.longValue());
    }

    public static <K> Long getLong(Map<? super K, ?> map, K key, Function<K, Long> defaultFunction) {
        return (Long) applyDefaultFunction(map, key, new MapUtils$$ExternalSyntheticLambda5(), defaultFunction);
    }

    public static <K> Long getLong(Map<? super K, ?> map, K key, Long defaultValue) {
        return (Long) applyDefaultValue(map, key, new MapUtils$$ExternalSyntheticLambda5(), defaultValue);
    }

    public static <K> long getLongValue(Map<? super K, ?> map, K key) {
        return ((Long) applyDefaultValue(map, key, new MapUtils$$ExternalSyntheticLambda5(), 0L)).longValue();
    }

    public static <K> long getLongValue(Map<? super K, ?> map, K key, Function<K, Long> defaultFunction) {
        return ((Long) applyDefaultFunction(map, key, new MapUtils$$ExternalSyntheticLambda5(), defaultFunction, 0L)).byteValue();
    }

    public static <K> long getLongValue(Map<? super K, ?> map, K key, long defaultValue) {
        return ((Long) applyDefaultValue(map, key, new MapUtils$$ExternalSyntheticLambda5(), Long.valueOf(defaultValue))).longValue();
    }

    public static <K> Map<?, ?> getMap(Map<? super K, ?> map, K key) {
        if (map != null) {
            Object answer = map.get(key);
            if (answer instanceof Map) {
                return (Map) answer;
            }
            return null;
        }
        return null;
    }

    public static <K> Map<?, ?> getMap(Map<? super K, ?> map, K key, Function<K, Map<?, ?>> defaultFunction) {
        return (Map) applyDefaultFunction(map, key, new MapUtils$$ExternalSyntheticLambda6(), defaultFunction);
    }

    public static <K> Map<?, ?> getMap(Map<? super K, ?> map, K key, Map<?, ?> defaultValue) {
        return (Map) applyDefaultValue(map, key, new MapUtils$$ExternalSyntheticLambda6(), defaultValue);
    }

    public static <K> Number getNumber(Map<? super K, ?> map, K key) {
        Object answer;
        if (map != null && (answer = map.get(key)) != null) {
            if (answer instanceof Number) {
                return (Number) answer;
            }
            if (answer instanceof String) {
                try {
                    String text = (String) answer;
                    return NumberFormat.getInstance().parse(text);
                } catch (ParseException e) {
                    return null;
                }
            }
            return null;
        }
        return null;
    }

    public static <K> Number getNumber(Map<? super K, ?> map, K key, Function<K, Number> defaultFunction) {
        return (Number) applyDefaultFunction(map, key, new MapUtils$$ExternalSyntheticLambda3(), defaultFunction);
    }

    public static <K> Number getNumber(Map<? super K, ?> map, K key, Number defaultValue) {
        return (Number) applyDefaultValue(map, key, new MapUtils$$ExternalSyntheticLambda3(), defaultValue);
    }

    public static <K, V> V getObject(Map<? super K, V> map, K key) {
        if (map != null) {
            return map.get(key);
        }
        return null;
    }

    public static <K, V> V getObject(Map<K, V> map, K key, V defaultValue) {
        V answer;
        if (map != null && (answer = map.get(key)) != null) {
            return answer;
        }
        return defaultValue;
    }

    public static <K> Short getShort(Map<? super K, ?> map, K key) {
        Number answer = getNumber(map, key);
        if (answer == null) {
            return null;
        }
        if (answer instanceof Short) {
            return (Short) answer;
        }
        return Short.valueOf(answer.shortValue());
    }

    public static <K> Short getShort(Map<? super K, ?> map, K key, Function<K, Short> defaultFunction) {
        return (Short) applyDefaultFunction(map, key, new MapUtils$$ExternalSyntheticLambda1(), defaultFunction);
    }

    public static <K> Short getShort(Map<? super K, ?> map, K key, Short defaultValue) {
        return (Short) applyDefaultValue(map, key, new MapUtils$$ExternalSyntheticLambda1(), defaultValue);
    }

    public static <K> short getShortValue(Map<? super K, ?> map, K key) {
        return ((Number) applyDefaultValue(map, key, new BiFunction() { // from class: org.apache.commons.collections4.MapUtils$$ExternalSyntheticLambda11
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return MapUtils.getShort((Map) obj, obj2);
            }
        }, 0)).shortValue();
    }

    public static <K> short getShortValue(Map<? super K, ?> map, K key, Function<K, Short> defaultFunction) {
        return ((Short) applyDefaultFunction(map, key, new MapUtils$$ExternalSyntheticLambda1(), defaultFunction, (short) 0)).shortValue();
    }

    public static <K> short getShortValue(Map<? super K, ?> map, K key, short defaultValue) {
        return ((Short) applyDefaultValue(map, key, new MapUtils$$ExternalSyntheticLambda1(), Short.valueOf(defaultValue))).shortValue();
    }

    public static <K> String getString(Map<? super K, ?> map, K key) {
        Object answer;
        if (map != null && (answer = map.get(key)) != null) {
            return answer.toString();
        }
        return null;
    }

    public static <K> String getString(Map<? super K, ?> map, K key, Function<K, String> defaultFunction) {
        return (String) applyDefaultFunction(map, key, new MapUtils$$ExternalSyntheticLambda7(), defaultFunction);
    }

    public static <K> String getString(Map<? super K, ?> map, K key, String defaultValue) {
        return (String) applyDefaultValue(map, key, new MapUtils$$ExternalSyntheticLambda7(), defaultValue);
    }

    public static <K, V> Map<V, K> invertMap(Map<K, V> map) {
        Objects.requireNonNull(map, "map");
        Map<V, K> out = new HashMap<>(map.size());
        for (Map.Entry<K, V> entry : map.entrySet()) {
            out.put(entry.getValue(), entry.getKey());
        }
        return out;
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    public static <K, V> IterableMap<K, V> iterableMap(Map<K, V> map) {
        Objects.requireNonNull(map, "map");
        return map instanceof IterableMap ? (IterableMap) map : new AbstractMapDecorator<K, V>(map) { // from class: org.apache.commons.collections4.MapUtils.1
        };
    }

    public static <K, V> IterableSortedMap<K, V> iterableSortedMap(SortedMap<K, V> sortedMap) {
        Objects.requireNonNull(sortedMap, "sortedMap");
        return sortedMap instanceof IterableSortedMap ? (IterableSortedMap) sortedMap : new AbstractSortedMapDecorator<K, V>(sortedMap) { // from class: org.apache.commons.collections4.MapUtils.2
        };
    }

    public static <K, V> IterableMap<K, V> lazyMap(Map<K, V> map, Factory<? extends V> factory) {
        return LazyMap.lazyMap(map, factory);
    }

    public static <K, V> IterableMap<K, V> lazyMap(Map<K, V> map, Transformer<? super K, ? extends V> transformerFactory) {
        return LazyMap.lazyMap(map, transformerFactory);
    }

    public static <K, V> SortedMap<K, V> lazySortedMap(SortedMap<K, V> map, Factory<? extends V> factory) {
        return LazySortedMap.lazySortedMap(map, factory);
    }

    public static <K, V> SortedMap<K, V> lazySortedMap(SortedMap<K, V> map, Transformer<? super K, ? extends V> transformerFactory) {
        return LazySortedMap.lazySortedMap(map, transformerFactory);
    }

    @Deprecated
    public static <K, V> MultiValueMap<K, V> multiValueMap(Map<K, ? super Collection<V>> map) {
        return MultiValueMap.multiValueMap(map);
    }

    @Deprecated
    public static <K, V, C extends Collection<V>> MultiValueMap<K, V> multiValueMap(Map<K, C> map, Class<C> collectionClass) {
        return MultiValueMap.multiValueMap(map, collectionClass);
    }

    @Deprecated
    public static <K, V, C extends Collection<V>> MultiValueMap<K, V> multiValueMap(Map<K, C> map, Factory<C> collectionFactory) {
        return MultiValueMap.multiValueMap(map, collectionFactory);
    }

    public static <K, V> OrderedMap<K, V> orderedMap(Map<K, V> map) {
        return ListOrderedMap.listOrderedMap(map);
    }

    public static <K, V, E> void populateMap(Map<K, V> map, Iterable<? extends E> elements, Transformer<E, K> keyTransformer, Transformer<E, V> valueTransformer) {
        for (E temp : elements) {
            map.put(keyTransformer.apply(temp), valueTransformer.apply(temp));
        }
    }

    public static <K, V> void populateMap(Map<K, V> map, Iterable<? extends V> elements, Transformer<V, K> keyTransformer) {
        populateMap(map, elements, keyTransformer, TransformerUtils.nopTransformer());
    }

    public static <K, V, E> void populateMap(MultiMap<K, V> map, Iterable<? extends E> elements, Transformer<E, K> keyTransformer, Transformer<E, V> valueTransformer) {
        for (E temp : elements) {
            map.put(keyTransformer.apply(temp), valueTransformer.apply(temp));
        }
    }

    public static <K, V> void populateMap(MultiMap<K, V> map, Iterable<? extends V> elements, Transformer<V, K> keyTransformer) {
        populateMap((MultiMap) map, (Iterable) elements, (Transformer) keyTransformer, TransformerUtils.nopTransformer());
    }

    public static <K, V> IterableMap<K, V> predicatedMap(Map<K, V> map, Predicate<? super K> keyPred, Predicate<? super V> valuePred) {
        return PredicatedMap.predicatedMap(map, keyPred, valuePred);
    }

    public static <K, V> SortedMap<K, V> predicatedSortedMap(SortedMap<K, V> map, Predicate<? super K> keyPred, Predicate<? super V> valuePred) {
        return PredicatedSortedMap.predicatedSortedMap(map, keyPred, valuePred);
    }

    private static void printIndent(PrintStream out, int indent) {
        for (int i = 0; i < indent; i++) {
            out.print(INDENT_STRING);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <K, V> Map<K, V> putAll(Map<K, V> map, Object[] array) {
        Objects.requireNonNull(map, "map");
        if (array == null || array.length == 0) {
            return map;
        }
        int i = 0;
        Object obj = array[0];
        if (obj instanceof Map.Entry) {
            int length = array.length;
            while (i < length) {
                Object element = array[i];
                Map.Entry<K, V> entry = (Map.Entry) element;
                map.put(entry.getKey(), entry.getValue());
                i++;
            }
        } else if (obj instanceof KeyValue) {
            int length2 = array.length;
            while (i < length2) {
                Object element2 = array[i];
                KeyValue<K, V> keyval = (KeyValue) element2;
                map.put(keyval.getKey(), keyval.getValue());
                i++;
            }
        } else if (obj instanceof Object[]) {
            for (int i2 = 0; i2 < array.length; i2++) {
                Object[] sub = (Object[]) array[i2];
                if (sub == null || sub.length < 2) {
                    throw new IllegalArgumentException("Invalid array element: " + i2);
                }
                map.put(sub[0], sub[1]);
            }
        } else {
            int i3 = 0;
            while (i3 < array.length - 1) {
                int i4 = i3 + 1;
                map.put(array[i3], array[i4]);
                i3 = i4 + 1;
            }
        }
        return map;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <K> void safeAddToMap(Map<? super K, Object> map, K k, Object value) throws NullPointerException {
        Objects.requireNonNull(map, "map");
        map.put(k, value == null ? "" : value);
    }

    public static int size(Map<?, ?> map) {
        if (map == null) {
            return 0;
        }
        return map.size();
    }

    public static <K, V> Map<K, V> synchronizedMap(Map<K, V> map) {
        return Collections.synchronizedMap(map);
    }

    public static <K, V> SortedMap<K, V> synchronizedSortedMap(SortedMap<K, V> map) {
        return Collections.synchronizedSortedMap(map);
    }

    public static Map<String, Object> toMap(ResourceBundle resourceBundle) {
        Objects.requireNonNull(resourceBundle, "resourceBundle");
        Enumeration<String> enumeration = resourceBundle.getKeys();
        Map<String, Object> map = new HashMap<>();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement();
            Object value = resourceBundle.getObject(key);
            map.put(key, value);
        }
        return map;
    }

    public static <K, V> Properties toProperties(Map<K, V> map) {
        Properties answer = new Properties();
        if (map != null) {
            for (Map.Entry<K, V> entry2 : map.entrySet()) {
                answer.put(entry2.getKey(), entry2.getValue());
            }
        }
        return answer;
    }

    public static <K, V> IterableMap<K, V> transformedMap(Map<K, V> map, Transformer<? super K, ? extends K> keyTransformer, Transformer<? super V, ? extends V> valueTransformer) {
        return TransformedMap.transformingMap(map, keyTransformer, valueTransformer);
    }

    public static <K, V> SortedMap<K, V> transformedSortedMap(SortedMap<K, V> map, Transformer<? super K, ? extends K> keyTransformer, Transformer<? super V, ? extends V> valueTransformer) {
        return TransformedSortedMap.transformingSortedMap(map, keyTransformer, valueTransformer);
    }

    public static <K, V> Map<K, V> unmodifiableMap(Map<? extends K, ? extends V> map) {
        return UnmodifiableMap.unmodifiableMap(map);
    }

    public static <K, V> SortedMap<K, V> unmodifiableSortedMap(SortedMap<K, ? extends V> map) {
        return UnmodifiableSortedMap.unmodifiableSortedMap(map);
    }

    public static void verbosePrint(PrintStream out, Object label, Map<?, ?> map) {
        verbosePrintInternal(out, label, map, new ArrayDeque(), false);
    }

    private static void verbosePrintInternal(PrintStream out, Object label, Map<?, ?> map, Deque<Map<?, ?>> lineage, boolean debug) {
        printIndent(out, lineage.size());
        if (map == null) {
            if (label != null) {
                out.print(label);
                out.print(" = ");
            }
            out.println("null");
            return;
        }
        if (label != null) {
            out.print(label);
            out.println(" = ");
        }
        printIndent(out, lineage.size());
        out.println(VectorFormat.DEFAULT_PREFIX);
        lineage.addLast(map);
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            Object childKey = entry.getKey();
            Object childValue = entry.getValue();
            if ((childValue instanceof Map) && !lineage.contains(childValue)) {
                verbosePrintInternal(out, childKey == null ? "null" : childKey, (Map) childValue, lineage, debug);
            } else {
                printIndent(out, lineage.size());
                out.print(childKey);
                out.print(" = ");
                int lineageIndex = IterableUtils.indexOf(lineage, PredicateUtils.equalPredicate(childValue));
                if (lineageIndex == -1) {
                    out.print(childValue);
                } else if (lineage.size() - 1 == lineageIndex) {
                    out.print("(this Map)");
                } else {
                    out.print("(ancestor[" + (((lineage.size() - 1) - lineageIndex) - 1) + "] Map)");
                }
                if (debug && childValue != null) {
                    out.print(Chars.SPACE);
                    out.println(childValue.getClass().getName());
                } else {
                    out.println();
                }
            }
        }
        lineage.removeLast();
        printIndent(out, lineage.size());
        out.println(debug ? "} " + map.getClass().getName() : VectorFormat.DEFAULT_SUFFIX);
    }

    private MapUtils() {
    }
}
