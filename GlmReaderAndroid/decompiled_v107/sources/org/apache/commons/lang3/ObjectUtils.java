package org.apache.commons.lang3;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.exception.CloneFailedException;
import org.apache.commons.lang3.function.FailableBiConsumer;
import org.apache.commons.lang3.function.Suppliers;
import org.apache.commons.lang3.mutable.MutableInt;
import org.apache.commons.lang3.text.StrBuilder;
import org.apache.commons.lang3.time.DurationUtils;

/* loaded from: classes9.dex */
public class ObjectUtils {
    private static final char AT_SIGN = '@';
    public static final Null NULL = new Null();

    /* loaded from: classes9.dex */
    public static class Null implements Serializable {
        private static final long serialVersionUID = 7092611880189329093L;

        Null() {
        }

        private Object readResolve() {
            return ObjectUtils.NULL;
        }
    }

    public static boolean allNotNull(Object... values) {
        return values != null && Stream.of(values).noneMatch(new Predicate() { // from class: org.apache.commons.lang3.ObjectUtils$$ExternalSyntheticLambda2
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean isNull;
                isNull = Objects.isNull(obj);
                return isNull;
            }
        });
    }

    public static boolean allNull(Object... values) {
        return !anyNotNull(values);
    }

    public static boolean anyNotNull(Object... values) {
        return firstNonNull(values) != null;
    }

    public static boolean anyNull(Object... values) {
        return !allNotNull(values);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> T clone(T t) {
        if (t instanceof Cloneable) {
            Class<?> cls = t.getClass();
            if (isArray(t)) {
                Class<?> componentType = cls.getComponentType();
                if (componentType.isPrimitive()) {
                    int length = Array.getLength(t);
                    T t2 = (T) Array.newInstance(componentType, length);
                    while (true) {
                        int i = length - 1;
                        if (length > 0) {
                            Array.set(t2, i, Array.get(t, i));
                            length = i;
                        } else {
                            return t2;
                        }
                    }
                } else {
                    return (T) ((Object[]) t).clone();
                }
            } else {
                try {
                    return (T) cls.getMethod("clone", new Class[0]).invoke(t, new Object[0]);
                } catch (ReflectiveOperationException e) {
                    throw new CloneFailedException("Exception cloning Cloneable type " + cls.getName(), e);
                }
            }
        } else {
            return null;
        }
    }

    public static <T> T cloneIfPossible(T t) {
        T t2 = (T) clone(t);
        return t2 == null ? t : t2;
    }

    public static <T extends Comparable<? super T>> int compare(T c1, T c2) {
        return compare(c1, c2, false);
    }

    public static <T extends Comparable<? super T>> int compare(T c1, T c2, boolean nullGreater) {
        if (c1 == c2) {
            return 0;
        }
        if (c1 == null) {
            return nullGreater ? 1 : -1;
        }
        if (c2 == null) {
            return nullGreater ? -1 : 1;
        }
        return c1.compareTo(c2);
    }

    public static boolean CONST(boolean v) {
        return v;
    }

    public static byte CONST(byte v) {
        return v;
    }

    public static char CONST(char v) {
        return v;
    }

    public static double CONST(double v) {
        return v;
    }

    public static float CONST(float v) {
        return v;
    }

    public static int CONST(int v) {
        return v;
    }

    public static long CONST(long v) {
        return v;
    }

    public static short CONST(short v) {
        return v;
    }

    public static <T> T CONST(T v) {
        return v;
    }

    public static byte CONST_BYTE(int v) {
        if (v < -128 || v > 127) {
            throw new IllegalArgumentException("Supplied value must be a valid byte literal between -128 and 127: [" + v + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
        }
        return (byte) v;
    }

    public static short CONST_SHORT(int v) {
        if (v < -32768 || v > 32767) {
            throw new IllegalArgumentException("Supplied value must be a valid byte literal between -32768 and 32767: [" + v + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
        }
        return (short) v;
    }

    @Deprecated
    public static <T> T defaultIfNull(T t, T t2) {
        return (T) getIfNull(t, t2);
    }

    @Deprecated
    public static boolean equals(Object object1, Object object2) {
        return Objects.equals(object1, object2);
    }

    @SafeVarargs
    public static <T> T firstNonNull(T... values) {
        return org.apache.commons.lang3.stream.Streams.of(values).filter(new ObjectUtils$$ExternalSyntheticLambda0()).findFirst().orElse(null);
    }

    public static <T> Class<T> getClass(T t) {
        if (t == null) {
            return null;
        }
        return (Class<T>) t.getClass();
    }

    @SafeVarargs
    public static <T> T getFirstNonNull(Supplier<T>... suppliers) {
        return org.apache.commons.lang3.stream.Streams.of(suppliers).filter(new Predicate() { // from class: org.apache.commons.lang3.ObjectUtils$$ExternalSyntheticLambda3
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean nonNull;
                nonNull = Objects.nonNull((Supplier) obj);
                return nonNull;
            }
        }).map(new Function() { // from class: org.apache.commons.lang3.ObjectUtils$$ExternalSyntheticLambda4
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((Supplier) obj).get();
            }
        }).filter(new ObjectUtils$$ExternalSyntheticLambda0()).findFirst().orElse(null);
    }

    public static <T> T getIfNull(T t, Supplier<T> supplier) {
        return t != null ? t : (T) Suppliers.get(supplier);
    }

    public static <T> T getIfNull(T object, T defaultValue) {
        return object != null ? object : defaultValue;
    }

    @Deprecated
    public static int hashCode(Object obj) {
        return Objects.hashCode(obj);
    }

    public static String hashCodeHex(Object object) {
        return Integer.toHexString(Objects.hashCode(object));
    }

    @Deprecated
    public static int hashCodeMulti(Object... objects) {
        int hash = 1;
        if (objects != null) {
            for (Object object : objects) {
                int tmpHash = Objects.hashCode(object);
                hash = (hash * 31) + tmpHash;
            }
        }
        return hash;
    }

    public static String identityHashCodeHex(Object object) {
        return Integer.toHexString(System.identityHashCode(object));
    }

    public static void identityToString(Appendable appendable, Object object) throws IOException {
        Objects.requireNonNull(object, "object");
        appendable.append(object.getClass().getName()).append(AT_SIGN).append(identityHashCodeHex(object));
    }

    public static String identityToString(Object object) {
        if (object == null) {
            return null;
        }
        String name = object.getClass().getName();
        String hexString = identityHashCodeHex(object);
        StringBuilder builder = new StringBuilder(name.length() + 1 + hexString.length());
        builder.append(name).append(AT_SIGN).append(hexString);
        return builder.toString();
    }

    @Deprecated
    public static void identityToString(StrBuilder builder, Object object) {
        Objects.requireNonNull(object, "object");
        String name = object.getClass().getName();
        String hexString = identityHashCodeHex(object);
        builder.ensureCapacity(builder.length() + name.length() + 1 + hexString.length());
        builder.append(name).append(AT_SIGN).append(hexString);
    }

    public static void identityToString(StringBuffer buffer, Object object) {
        Objects.requireNonNull(object, "object");
        String name = object.getClass().getName();
        String hexString = identityHashCodeHex(object);
        buffer.ensureCapacity(buffer.length() + name.length() + 1 + hexString.length());
        buffer.append(name).append(AT_SIGN).append(hexString);
    }

    public static void identityToString(StringBuilder builder, Object object) {
        Objects.requireNonNull(object, "object");
        String name = object.getClass().getName();
        String hexString = identityHashCodeHex(object);
        builder.ensureCapacity(builder.length() + name.length() + 1 + hexString.length());
        builder.append(name).append(AT_SIGN).append(hexString);
    }

    public static boolean isArray(Object object) {
        return object != null && object.getClass().isArray();
    }

    public static boolean isEmpty(Object object) {
        if (object == null) {
            return true;
        }
        if (object instanceof CharSequence) {
            return ((CharSequence) object).length() == 0;
        }
        if (isArray(object)) {
            return Array.getLength(object) == 0;
        }
        if (object instanceof Collection) {
            return ((Collection) object).isEmpty();
        }
        if (object instanceof Map) {
            return ((Map) object).isEmpty();
        }
        if (object instanceof Optional) {
            return true ^ ((Optional) object).isPresent();
        }
        return false;
    }

    public static boolean isNotEmpty(Object object) {
        return !isEmpty(object);
    }

    @SafeVarargs
    public static <T extends Comparable<? super T>> T max(T... values) {
        T result = null;
        if (values != null) {
            for (T value : values) {
                if (compare(value, result, false) > 0) {
                    result = value;
                }
            }
        }
        return result;
    }

    @SafeVarargs
    public static <T> T median(Comparator<T> comparator, T... tArr) {
        Validate.notEmpty(tArr, "null/empty items", new Object[0]);
        Validate.noNullElements(tArr);
        Objects.requireNonNull(comparator, "comparator");
        TreeSet treeSet = new TreeSet(comparator);
        Collections.addAll(treeSet, tArr);
        return (T) treeSet.toArray()[(treeSet.size() - 1) / 2];
    }

    @SafeVarargs
    public static <T extends Comparable<? super T>> T median(T... items) {
        Validate.notEmpty(items);
        Validate.noNullElements(items);
        TreeSet<T> sort = new TreeSet<>();
        Collections.addAll(sort, items);
        return (T) sort.toArray()[(sort.size() - 1) / 2];
    }

    @SafeVarargs
    public static <T extends Comparable<? super T>> T min(T... values) {
        T result = null;
        if (values != null) {
            for (T value : values) {
                if (compare(value, result, true) < 0) {
                    result = value;
                }
            }
        }
        return result;
    }

    @SafeVarargs
    public static <T> T mode(T... items) {
        if (ArrayUtils.isNotEmpty(items)) {
            HashMap<T, MutableInt> occurrences = new HashMap<>(items.length);
            for (T t : items) {
                ArrayUtils.increment(occurrences, t);
            }
            T result = null;
            int max = 0;
            for (Map.Entry<T, MutableInt> e : occurrences.entrySet()) {
                int cmp = e.getValue().intValue();
                if (cmp == max) {
                    result = null;
                } else if (cmp > max) {
                    max = cmp;
                    result = e.getKey();
                }
            }
            return result;
        }
        return null;
    }

    public static boolean notEqual(Object object1, Object object2) {
        return !Objects.equals(object1, object2);
    }

    public static <T> T requireNonEmpty(T t) {
        return (T) requireNonEmpty(t, "object");
    }

    public static <T> T requireNonEmpty(T obj, String message) {
        Objects.requireNonNull(obj, message);
        if (isEmpty(obj)) {
            throw new IllegalArgumentException(message);
        }
        return obj;
    }

    public static String toString(Object obj) {
        return Objects.toString(obj, "");
    }

    @Deprecated
    public static String toString(Object obj, String nullStr) {
        return Objects.toString(obj, nullStr);
    }

    public static String toString(Supplier<Object> obj, Supplier<String> supplier) {
        return obj == null ? (String) Suppliers.get(supplier) : toString(obj.get(), supplier);
    }

    public static <T> String toString(T obj, Supplier<String> supplier) {
        return obj == null ? (String) Suppliers.get(supplier) : obj.toString();
    }

    public static void wait(final Object obj, Duration duration) throws InterruptedException {
        Objects.requireNonNull(obj);
        DurationUtils.accept(new FailableBiConsumer() { // from class: org.apache.commons.lang3.ObjectUtils$$ExternalSyntheticLambda1
            @Override // org.apache.commons.lang3.function.FailableBiConsumer
            public final void accept(Object obj2, Object obj3) {
                obj.wait(((Long) obj2).longValue(), ((Integer) obj3).intValue());
            }
        }, DurationUtils.zeroIfNull(duration));
    }

    @Deprecated
    public ObjectUtils() {
    }
}
