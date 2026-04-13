package org.apache.commons.lang3;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.function.FailableFunction;
import org.apache.commons.lang3.function.FailableIntFunction;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.mutable.MutableInt;
import org.apache.commons.lang3.stream.IntStreams;

/* loaded from: classes9.dex */
public class ArrayUtils {
    public static final int INDEX_NOT_FOUND = -1;
    public static final boolean[] EMPTY_BOOLEAN_ARRAY = new boolean[0];
    public static final Boolean[] EMPTY_BOOLEAN_OBJECT_ARRAY = new Boolean[0];
    public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    public static final Byte[] EMPTY_BYTE_OBJECT_ARRAY = new Byte[0];
    public static final char[] EMPTY_CHAR_ARRAY = new char[0];
    public static final Character[] EMPTY_CHARACTER_OBJECT_ARRAY = new Character[0];
    public static final Class<?>[] EMPTY_CLASS_ARRAY = new Class[0];
    public static final double[] EMPTY_DOUBLE_ARRAY = new double[0];
    public static final Double[] EMPTY_DOUBLE_OBJECT_ARRAY = new Double[0];
    public static final Field[] EMPTY_FIELD_ARRAY = new Field[0];
    public static final float[] EMPTY_FLOAT_ARRAY = new float[0];
    public static final Float[] EMPTY_FLOAT_OBJECT_ARRAY = new Float[0];
    public static final int[] EMPTY_INT_ARRAY = new int[0];
    public static final Integer[] EMPTY_INTEGER_OBJECT_ARRAY = new Integer[0];
    public static final long[] EMPTY_LONG_ARRAY = new long[0];
    public static final Long[] EMPTY_LONG_OBJECT_ARRAY = new Long[0];
    public static final Method[] EMPTY_METHOD_ARRAY = new Method[0];
    public static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];
    public static final short[] EMPTY_SHORT_ARRAY = new short[0];
    public static final Short[] EMPTY_SHORT_OBJECT_ARRAY = new Short[0];
    public static final String[] EMPTY_STRING_ARRAY = new String[0];
    public static final Throwable[] EMPTY_THROWABLE_ARRAY = new Throwable[0];
    public static final Type[] EMPTY_TYPE_ARRAY = new Type[0];

    public static boolean[] add(boolean[] array, boolean element) {
        boolean[] newArray = (boolean[]) copyArrayGrow1(array, Boolean.TYPE);
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    @Deprecated
    public static boolean[] add(boolean[] array, int index, boolean element) {
        return (boolean[]) add(array, index, Boolean.valueOf(element), Boolean.TYPE);
    }

    public static byte[] add(byte[] array, byte element) {
        byte[] newArray = (byte[]) copyArrayGrow1(array, Byte.TYPE);
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    @Deprecated
    public static byte[] add(byte[] array, int index, byte element) {
        return (byte[]) add(array, index, Byte.valueOf(element), Byte.TYPE);
    }

    public static char[] add(char[] array, char element) {
        char[] newArray = (char[]) copyArrayGrow1(array, Character.TYPE);
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    @Deprecated
    public static char[] add(char[] array, int index, char element) {
        return (char[]) add(array, index, Character.valueOf(element), Character.TYPE);
    }

    public static double[] add(double[] array, double element) {
        double[] newArray = (double[]) copyArrayGrow1(array, Double.TYPE);
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    @Deprecated
    public static double[] add(double[] array, int index, double element) {
        return (double[]) add(array, index, Double.valueOf(element), Double.TYPE);
    }

    public static float[] add(float[] array, float element) {
        float[] newArray = (float[]) copyArrayGrow1(array, Float.TYPE);
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    @Deprecated
    public static float[] add(float[] array, int index, float element) {
        return (float[]) add(array, index, Float.valueOf(element), Float.TYPE);
    }

    public static int[] add(int[] array, int element) {
        int[] newArray = (int[]) copyArrayGrow1(array, Integer.TYPE);
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    @Deprecated
    public static int[] add(int[] array, int index, int element) {
        return (int[]) add(array, index, Integer.valueOf(element), Integer.TYPE);
    }

    @Deprecated
    public static long[] add(long[] array, int index, long element) {
        return (long[]) add(array, index, Long.valueOf(element), Long.TYPE);
    }

    public static long[] add(long[] array, long element) {
        long[] newArray = (long[]) copyArrayGrow1(array, Long.TYPE);
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    private static Object add(Object array, int index, Object element, final Class<?> clazz) {
        if (array == null) {
            if (index != 0) {
                throw new IndexOutOfBoundsException("Index: " + index + ", Length: 0");
            }
            Object joinedArray = Array.newInstance(clazz, 1);
            Array.set(joinedArray, 0, element);
            return joinedArray;
        }
        final int length = Array.getLength(array);
        if (index > length || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + length);
        }
        Object result = arraycopy(array, 0, 0, index, (Supplier<Object>) new Supplier() { // from class: org.apache.commons.lang3.ArrayUtils$$ExternalSyntheticLambda18
            @Override // java.util.function.Supplier
            public final Object get() {
                Object newInstance;
                newInstance = Array.newInstance((Class<?>) clazz, length + 1);
                return newInstance;
            }
        });
        Array.set(result, index, element);
        if (index < length) {
            System.arraycopy(array, index, result, index + 1, length - index);
        }
        return result;
    }

    @Deprecated
    public static short[] add(short[] array, int index, short element) {
        return (short[]) add(array, index, Short.valueOf(element), Short.TYPE);
    }

    public static short[] add(short[] array, short element) {
        short[] newArray = (short[]) copyArrayGrow1(array, Short.TYPE);
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    @Deprecated
    public static <T> T[] add(T[] tArr, int i, T t) {
        Class cls;
        if (tArr != null) {
            cls = getComponentType(tArr);
        } else if (t != null) {
            cls = ObjectUtils.getClass(t);
        } else {
            throw new IllegalArgumentException("Array and element cannot both be null");
        }
        return (T[]) ((Object[]) add(tArr, i, t, cls));
    }

    public static <T> T[] add(T[] tArr, T t) {
        Class<?> cls;
        if (tArr != null) {
            cls = tArr.getClass().getComponentType();
        } else if (t != null) {
            cls = t.getClass();
        } else {
            throw new IllegalArgumentException("Arguments cannot both be null");
        }
        T[] tArr2 = (T[]) ((Object[]) copyArrayGrow1(tArr, cls));
        tArr2[tArr2.length - 1] = t;
        return tArr2;
    }

    public static boolean[] addAll(boolean[] array1, boolean... array2) {
        if (array1 == null) {
            return clone(array2);
        }
        if (array2 == null) {
            return clone(array1);
        }
        boolean[] joinedArray = new boolean[array1.length + array2.length];
        System.arraycopy(array1, 0, joinedArray, 0, array1.length);
        System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
        return joinedArray;
    }

    public static byte[] addAll(byte[] array1, byte... array2) {
        if (array1 == null) {
            return clone(array2);
        }
        if (array2 == null) {
            return clone(array1);
        }
        byte[] joinedArray = new byte[array1.length + array2.length];
        System.arraycopy(array1, 0, joinedArray, 0, array1.length);
        System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
        return joinedArray;
    }

    public static char[] addAll(char[] array1, char... array2) {
        if (array1 == null) {
            return clone(array2);
        }
        if (array2 == null) {
            return clone(array1);
        }
        char[] joinedArray = new char[array1.length + array2.length];
        System.arraycopy(array1, 0, joinedArray, 0, array1.length);
        System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
        return joinedArray;
    }

    public static double[] addAll(double[] array1, double... array2) {
        if (array1 == null) {
            return clone(array2);
        }
        if (array2 == null) {
            return clone(array1);
        }
        double[] joinedArray = new double[array1.length + array2.length];
        System.arraycopy(array1, 0, joinedArray, 0, array1.length);
        System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
        return joinedArray;
    }

    public static float[] addAll(float[] array1, float... array2) {
        if (array1 == null) {
            return clone(array2);
        }
        if (array2 == null) {
            return clone(array1);
        }
        float[] joinedArray = new float[array1.length + array2.length];
        System.arraycopy(array1, 0, joinedArray, 0, array1.length);
        System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
        return joinedArray;
    }

    public static int[] addAll(int[] array1, int... array2) {
        if (array1 == null) {
            return clone(array2);
        }
        if (array2 == null) {
            return clone(array1);
        }
        int[] joinedArray = new int[array1.length + array2.length];
        System.arraycopy(array1, 0, joinedArray, 0, array1.length);
        System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
        return joinedArray;
    }

    public static long[] addAll(long[] array1, long... array2) {
        if (array1 == null) {
            return clone(array2);
        }
        if (array2 == null) {
            return clone(array1);
        }
        long[] joinedArray = new long[array1.length + array2.length];
        System.arraycopy(array1, 0, joinedArray, 0, array1.length);
        System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
        return joinedArray;
    }

    public static short[] addAll(short[] array1, short... array2) {
        if (array1 == null) {
            return clone(array2);
        }
        if (array2 == null) {
            return clone(array1);
        }
        short[] joinedArray = new short[array1.length + array2.length];
        System.arraycopy(array1, 0, joinedArray, 0, array1.length);
        System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
        return joinedArray;
    }

    public static <T> T[] addAll(final T[] tArr, final T... tArr2) {
        if (tArr == null) {
            return (T[]) clone(tArr2);
        }
        if (tArr2 == null) {
            return (T[]) clone(tArr);
        }
        final Class componentType = getComponentType(tArr);
        T[] tArr3 = (T[]) ((Object[]) arraycopy(tArr, 0, 0, tArr.length, (Supplier<T[]>) new Supplier() { // from class: org.apache.commons.lang3.ArrayUtils$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                Object[] newInstance;
                newInstance = ArrayUtils.newInstance(componentType, tArr.length + tArr2.length);
                return newInstance;
            }
        }));
        try {
            System.arraycopy(tArr2, 0, tArr3, tArr.length, tArr2.length);
            return tArr3;
        } catch (ArrayStoreException e) {
            Class<?> componentType2 = tArr2.getClass().getComponentType();
            if (!componentType.isAssignableFrom(componentType2)) {
                throw new IllegalArgumentException("Cannot store " + componentType2.getName() + " in an array of " + componentType.getName(), e);
            }
            throw e;
        }
    }

    public static boolean[] addFirst(boolean[] array, boolean element) {
        return array == null ? add(array, element) : insert(0, array, element);
    }

    public static byte[] addFirst(byte[] array, byte element) {
        return array == null ? add(array, element) : insert(0, array, element);
    }

    public static char[] addFirst(char[] array, char element) {
        return array == null ? add(array, element) : insert(0, array, element);
    }

    public static double[] addFirst(double[] array, double element) {
        return array == null ? add(array, element) : insert(0, array, element);
    }

    public static float[] addFirst(float[] array, float element) {
        return array == null ? add(array, element) : insert(0, array, element);
    }

    public static int[] addFirst(int[] array, int element) {
        return array == null ? add(array, element) : insert(0, array, element);
    }

    public static long[] addFirst(long[] array, long element) {
        return array == null ? add(array, element) : insert(0, array, element);
    }

    public static short[] addFirst(short[] array, short element) {
        return array == null ? add(array, element) : insert(0, array, element);
    }

    public static <T> T[] addFirst(T[] tArr, T t) {
        return tArr == null ? (T[]) add(tArr, t) : (T[]) insert(0, tArr, t);
    }

    public static <T> T arraycopy(T t, int i, int i2, int i3, Function<Integer, T> function) {
        return (T) arraycopy(t, i, function.apply(Integer.valueOf(i3)), i2, i3);
    }

    public static <T> T arraycopy(T t, int i, int i2, int i3, Supplier<T> supplier) {
        return (T) arraycopy(t, i, supplier.get(), i2, i3);
    }

    public static <T> T arraycopy(T source, int sourcePos, T dest, int destPos, int length) {
        System.arraycopy(source, sourcePos, dest, destPos, length);
        return dest;
    }

    public static boolean[] clone(boolean[] array) {
        if (array != null) {
            return (boolean[]) array.clone();
        }
        return null;
    }

    public static byte[] clone(byte[] array) {
        if (array != null) {
            return (byte[]) array.clone();
        }
        return null;
    }

    public static char[] clone(char[] array) {
        if (array != null) {
            return (char[]) array.clone();
        }
        return null;
    }

    public static double[] clone(double[] array) {
        if (array != null) {
            return (double[]) array.clone();
        }
        return null;
    }

    public static float[] clone(float[] array) {
        if (array != null) {
            return (float[]) array.clone();
        }
        return null;
    }

    public static int[] clone(int[] array) {
        if (array != null) {
            return (int[]) array.clone();
        }
        return null;
    }

    public static long[] clone(long[] array) {
        if (array != null) {
            return (long[]) array.clone();
        }
        return null;
    }

    public static short[] clone(short[] array) {
        if (array != null) {
            return (short[]) array.clone();
        }
        return null;
    }

    public static <T> T[] clone(T[] tArr) {
        if (tArr != null) {
            return (T[]) ((Object[]) tArr.clone());
        }
        return null;
    }

    public static boolean contains(boolean[] array, boolean valueToFind) {
        return indexOf(array, valueToFind) != -1;
    }

    public static boolean contains(byte[] array, byte valueToFind) {
        return indexOf(array, valueToFind) != -1;
    }

    public static boolean contains(char[] array, char valueToFind) {
        return indexOf(array, valueToFind) != -1;
    }

    public static boolean contains(double[] array, double valueToFind) {
        return indexOf(array, valueToFind) != -1;
    }

    public static boolean contains(double[] array, double valueToFind, double tolerance) {
        return indexOf(array, valueToFind, 0, tolerance) != -1;
    }

    public static boolean contains(float[] array, float valueToFind) {
        return indexOf(array, valueToFind) != -1;
    }

    public static boolean contains(int[] array, int valueToFind) {
        return indexOf(array, valueToFind) != -1;
    }

    public static boolean contains(long[] array, long valueToFind) {
        return indexOf(array, valueToFind) != -1;
    }

    public static boolean contains(Object[] array, Object objectToFind) {
        return indexOf(array, objectToFind) != -1;
    }

    public static boolean contains(short[] array, short valueToFind) {
        return indexOf(array, valueToFind) != -1;
    }

    public static boolean containsAny(final int[] array, int... objectsToFind) {
        return IntStreams.of(objectsToFind).anyMatch(new IntPredicate() { // from class: org.apache.commons.lang3.ArrayUtils$$ExternalSyntheticLambda1
            @Override // java.util.function.IntPredicate
            public final boolean test(int i) {
                boolean contains;
                contains = ArrayUtils.contains(array, i);
                return contains;
            }
        });
    }

    public static boolean containsAny(final Object[] array, Object... objectsToFind) {
        return org.apache.commons.lang3.stream.Streams.of(objectsToFind).anyMatch(new Predicate() { // from class: org.apache.commons.lang3.ArrayUtils$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean contains;
                contains = ArrayUtils.contains(array, obj);
                return contains;
            }
        });
    }

    private static Object copyArrayGrow1(Object array, Class<?> newArrayComponentType) {
        if (array != null) {
            int arrayLength = Array.getLength(array);
            Object newArray = Array.newInstance(array.getClass().getComponentType(), arrayLength + 1);
            System.arraycopy(array, 0, newArray, 0, arrayLength);
            return newArray;
        }
        return Array.newInstance(newArrayComponentType, 1);
    }

    public static <T> T get(T[] tArr, int i) {
        return (T) get(tArr, i, null);
    }

    public static <T> T get(T[] array, int index, T defaultValue) {
        return isArrayIndexValid(array, index) ? array[index] : defaultValue;
    }

    public static <T> Class<T> getComponentType(T[] array) {
        return ClassUtils.getComponentType(ObjectUtils.getClass(array));
    }

    public static int getLength(Object array) {
        if (array != null) {
            return Array.getLength(array);
        }
        return 0;
    }

    public static int hashCode(Object array) {
        return new HashCodeBuilder().append(array).toHashCode();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <K> void increment(Map<K, MutableInt> occurrences, K boxed) {
        occurrences.computeIfAbsent(boxed, new Function() { // from class: org.apache.commons.lang3.ArrayUtils$$ExternalSyntheticLambda8
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ArrayUtils.lambda$increment$4(obj);
            }
        }).increment();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ MutableInt lambda$increment$4(Object k) {
        return new MutableInt();
    }

    public static BitSet indexesOf(boolean[] array, boolean valueToFind) {
        return indexesOf(array, valueToFind, 0);
    }

    public static BitSet indexesOf(boolean[] array, boolean valueToFind, int startIndex) {
        int startIndex2;
        BitSet bitSet = new BitSet();
        if (array == null) {
            return bitSet;
        }
        while (startIndex < array.length && (startIndex2 = indexOf(array, valueToFind, startIndex)) != -1) {
            bitSet.set(startIndex2);
            startIndex = startIndex2 + 1;
        }
        return bitSet;
    }

    public static BitSet indexesOf(byte[] array, byte valueToFind) {
        return indexesOf(array, valueToFind, 0);
    }

    public static BitSet indexesOf(byte[] array, byte valueToFind, int startIndex) {
        int startIndex2;
        BitSet bitSet = new BitSet();
        if (array == null) {
            return bitSet;
        }
        while (startIndex < array.length && (startIndex2 = indexOf(array, valueToFind, startIndex)) != -1) {
            bitSet.set(startIndex2);
            startIndex = startIndex2 + 1;
        }
        return bitSet;
    }

    public static BitSet indexesOf(char[] array, char valueToFind) {
        return indexesOf(array, valueToFind, 0);
    }

    public static BitSet indexesOf(char[] array, char valueToFind, int startIndex) {
        int startIndex2;
        BitSet bitSet = new BitSet();
        if (array == null) {
            return bitSet;
        }
        while (startIndex < array.length && (startIndex2 = indexOf(array, valueToFind, startIndex)) != -1) {
            bitSet.set(startIndex2);
            startIndex = startIndex2 + 1;
        }
        return bitSet;
    }

    public static BitSet indexesOf(double[] array, double valueToFind) {
        return indexesOf(array, valueToFind, 0);
    }

    public static BitSet indexesOf(double[] array, double valueToFind, double tolerance) {
        return indexesOf(array, valueToFind, 0, tolerance);
    }

    public static BitSet indexesOf(double[] array, double valueToFind, int startIndex) {
        int startIndex2;
        BitSet bitSet = new BitSet();
        if (array == null) {
            return bitSet;
        }
        while (startIndex < array.length && (startIndex2 = indexOf(array, valueToFind, startIndex)) != -1) {
            bitSet.set(startIndex2);
            startIndex = startIndex2 + 1;
        }
        return bitSet;
    }

    public static BitSet indexesOf(double[] array, double valueToFind, int startIndex, double tolerance) {
        int startIndex2;
        BitSet bitSet = new BitSet();
        if (array == null) {
            return bitSet;
        }
        while (startIndex < array.length && (startIndex2 = indexOf(array, valueToFind, startIndex, tolerance)) != -1) {
            bitSet.set(startIndex2);
            startIndex = startIndex2 + 1;
        }
        return bitSet;
    }

    public static BitSet indexesOf(float[] array, float valueToFind) {
        return indexesOf(array, valueToFind, 0);
    }

    public static BitSet indexesOf(float[] array, float valueToFind, int startIndex) {
        int startIndex2;
        BitSet bitSet = new BitSet();
        if (array == null) {
            return bitSet;
        }
        while (startIndex < array.length && (startIndex2 = indexOf(array, valueToFind, startIndex)) != -1) {
            bitSet.set(startIndex2);
            startIndex = startIndex2 + 1;
        }
        return bitSet;
    }

    public static BitSet indexesOf(int[] array, int valueToFind) {
        return indexesOf(array, valueToFind, 0);
    }

    public static BitSet indexesOf(int[] array, int valueToFind, int startIndex) {
        int startIndex2;
        BitSet bitSet = new BitSet();
        if (array == null) {
            return bitSet;
        }
        while (startIndex < array.length && (startIndex2 = indexOf(array, valueToFind, startIndex)) != -1) {
            bitSet.set(startIndex2);
            startIndex = startIndex2 + 1;
        }
        return bitSet;
    }

    public static BitSet indexesOf(long[] array, long valueToFind) {
        return indexesOf(array, valueToFind, 0);
    }

    public static BitSet indexesOf(long[] array, long valueToFind, int startIndex) {
        int startIndex2;
        BitSet bitSet = new BitSet();
        if (array == null) {
            return bitSet;
        }
        while (startIndex < array.length && (startIndex2 = indexOf(array, valueToFind, startIndex)) != -1) {
            bitSet.set(startIndex2);
            startIndex = startIndex2 + 1;
        }
        return bitSet;
    }

    public static BitSet indexesOf(Object[] array, Object objectToFind) {
        return indexesOf(array, objectToFind, 0);
    }

    public static BitSet indexesOf(Object[] array, Object objectToFind, int startIndex) {
        int startIndex2;
        BitSet bitSet = new BitSet();
        if (array == null) {
            return bitSet;
        }
        while (startIndex < array.length && (startIndex2 = indexOf(array, objectToFind, startIndex)) != -1) {
            bitSet.set(startIndex2);
            startIndex = startIndex2 + 1;
        }
        return bitSet;
    }

    public static BitSet indexesOf(short[] array, short valueToFind) {
        return indexesOf(array, valueToFind, 0);
    }

    public static BitSet indexesOf(short[] array, short valueToFind, int startIndex) {
        int startIndex2;
        BitSet bitSet = new BitSet();
        if (array == null) {
            return bitSet;
        }
        while (startIndex < array.length && (startIndex2 = indexOf(array, valueToFind, startIndex)) != -1) {
            bitSet.set(startIndex2);
            startIndex = startIndex2 + 1;
        }
        return bitSet;
    }

    public static int indexOf(boolean[] array, boolean valueToFind) {
        return indexOf(array, valueToFind, 0);
    }

    public static int indexOf(boolean[] array, boolean valueToFind, int startIndex) {
        if (isEmpty(array)) {
            return -1;
        }
        for (int i = max0(startIndex); i < array.length; i++) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return -1;
    }

    public static int indexOf(byte[] array, byte valueToFind) {
        return indexOf(array, valueToFind, 0);
    }

    public static int indexOf(byte[] array, byte valueToFind, int startIndex) {
        if (array == null) {
            return -1;
        }
        for (int i = max0(startIndex); i < array.length; i++) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return -1;
    }

    public static int indexOf(char[] array, char valueToFind) {
        return indexOf(array, valueToFind, 0);
    }

    public static int indexOf(char[] array, char valueToFind, int startIndex) {
        if (array == null) {
            return -1;
        }
        for (int i = max0(startIndex); i < array.length; i++) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return -1;
    }

    public static int indexOf(double[] array, double valueToFind) {
        return indexOf(array, valueToFind, 0);
    }

    public static int indexOf(double[] array, double valueToFind, double tolerance) {
        return indexOf(array, valueToFind, 0, tolerance);
    }

    public static int indexOf(double[] array, double valueToFind, int startIndex) {
        if (isEmpty(array)) {
            return -1;
        }
        boolean searchNaN = Double.isNaN(valueToFind);
        for (int i = max0(startIndex); i < array.length; i++) {
            double element = array[i];
            if (valueToFind == element || (searchNaN && Double.isNaN(element))) {
                return i;
            }
        }
        return -1;
    }

    public static int indexOf(double[] array, double valueToFind, int startIndex, double tolerance) {
        if (isEmpty(array)) {
            return -1;
        }
        double min = valueToFind - tolerance;
        double max = valueToFind + tolerance;
        for (int i = max0(startIndex); i < array.length; i++) {
            if (array[i] >= min && array[i] <= max) {
                return i;
            }
        }
        return -1;
    }

    public static int indexOf(float[] array, float valueToFind) {
        return indexOf(array, valueToFind, 0);
    }

    public static int indexOf(float[] array, float valueToFind, int startIndex) {
        if (isEmpty(array)) {
            return -1;
        }
        boolean searchNaN = Float.isNaN(valueToFind);
        for (int i = max0(startIndex); i < array.length; i++) {
            float element = array[i];
            if (valueToFind == element || (searchNaN && Float.isNaN(element))) {
                return i;
            }
        }
        return -1;
    }

    public static int indexOf(int[] array, int valueToFind) {
        return indexOf(array, valueToFind, 0);
    }

    public static int indexOf(int[] array, int valueToFind, int startIndex) {
        if (array == null) {
            return -1;
        }
        for (int i = max0(startIndex); i < array.length; i++) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return -1;
    }

    public static int indexOf(long[] array, long valueToFind) {
        return indexOf(array, valueToFind, 0);
    }

    public static int indexOf(long[] array, long valueToFind, int startIndex) {
        if (array == null) {
            return -1;
        }
        for (int i = max0(startIndex); i < array.length; i++) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return -1;
    }

    public static int indexOf(Object[] array, Object objectToFind) {
        return indexOf(array, objectToFind, 0);
    }

    public static int indexOf(Object[] array, Object objectToFind, int startIndex) {
        if (array == null) {
            return -1;
        }
        int startIndex2 = max0(startIndex);
        if (objectToFind == null) {
            for (int i = startIndex2; i < array.length; i++) {
                if (array[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i2 = startIndex2; i2 < array.length; i2++) {
                if (objectToFind.equals(array[i2])) {
                    return i2;
                }
            }
        }
        return -1;
    }

    public static int indexOf(short[] array, short valueToFind) {
        return indexOf(array, valueToFind, 0);
    }

    public static int indexOf(short[] array, short valueToFind, int startIndex) {
        if (array == null) {
            return -1;
        }
        for (int i = max0(startIndex); i < array.length; i++) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return -1;
    }

    public static boolean[] insert(int index, boolean[] array, boolean... values) {
        if (array == null) {
            return null;
        }
        if (isEmpty(values)) {
            return clone(array);
        }
        if (index < 0 || index > array.length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + array.length);
        }
        boolean[] result = new boolean[array.length + values.length];
        System.arraycopy(values, 0, result, index, values.length);
        if (index > 0) {
            System.arraycopy(array, 0, result, 0, index);
        }
        if (index < array.length) {
            System.arraycopy(array, index, result, values.length + index, array.length - index);
        }
        return result;
    }

    public static byte[] insert(int index, byte[] array, byte... values) {
        if (array == null) {
            return null;
        }
        if (isEmpty(values)) {
            return clone(array);
        }
        if (index < 0 || index > array.length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + array.length);
        }
        byte[] result = new byte[array.length + values.length];
        System.arraycopy(values, 0, result, index, values.length);
        if (index > 0) {
            System.arraycopy(array, 0, result, 0, index);
        }
        if (index < array.length) {
            System.arraycopy(array, index, result, values.length + index, array.length - index);
        }
        return result;
    }

    public static char[] insert(int index, char[] array, char... values) {
        if (array == null) {
            return null;
        }
        if (isEmpty(values)) {
            return clone(array);
        }
        if (index < 0 || index > array.length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + array.length);
        }
        char[] result = new char[array.length + values.length];
        System.arraycopy(values, 0, result, index, values.length);
        if (index > 0) {
            System.arraycopy(array, 0, result, 0, index);
        }
        if (index < array.length) {
            System.arraycopy(array, index, result, values.length + index, array.length - index);
        }
        return result;
    }

    public static double[] insert(int index, double[] array, double... values) {
        if (array == null) {
            return null;
        }
        if (isEmpty(values)) {
            return clone(array);
        }
        if (index < 0 || index > array.length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + array.length);
        }
        double[] result = new double[array.length + values.length];
        System.arraycopy(values, 0, result, index, values.length);
        if (index > 0) {
            System.arraycopy(array, 0, result, 0, index);
        }
        if (index < array.length) {
            System.arraycopy(array, index, result, values.length + index, array.length - index);
        }
        return result;
    }

    public static float[] insert(int index, float[] array, float... values) {
        if (array == null) {
            return null;
        }
        if (isEmpty(values)) {
            return clone(array);
        }
        if (index < 0 || index > array.length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + array.length);
        }
        float[] result = new float[array.length + values.length];
        System.arraycopy(values, 0, result, index, values.length);
        if (index > 0) {
            System.arraycopy(array, 0, result, 0, index);
        }
        if (index < array.length) {
            System.arraycopy(array, index, result, values.length + index, array.length - index);
        }
        return result;
    }

    public static int[] insert(int index, int[] array, int... values) {
        if (array == null) {
            return null;
        }
        if (isEmpty(values)) {
            return clone(array);
        }
        if (index < 0 || index > array.length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + array.length);
        }
        int[] result = new int[array.length + values.length];
        System.arraycopy(values, 0, result, index, values.length);
        if (index > 0) {
            System.arraycopy(array, 0, result, 0, index);
        }
        if (index < array.length) {
            System.arraycopy(array, index, result, values.length + index, array.length - index);
        }
        return result;
    }

    public static long[] insert(int index, long[] array, long... values) {
        if (array == null) {
            return null;
        }
        if (isEmpty(values)) {
            return clone(array);
        }
        if (index < 0 || index > array.length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + array.length);
        }
        long[] result = new long[array.length + values.length];
        System.arraycopy(values, 0, result, index, values.length);
        if (index > 0) {
            System.arraycopy(array, 0, result, 0, index);
        }
        if (index < array.length) {
            System.arraycopy(array, index, result, values.length + index, array.length - index);
        }
        return result;
    }

    public static short[] insert(int index, short[] array, short... values) {
        if (array == null) {
            return null;
        }
        if (isEmpty(values)) {
            return clone(array);
        }
        if (index < 0 || index > array.length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + array.length);
        }
        short[] result = new short[array.length + values.length];
        System.arraycopy(values, 0, result, index, values.length);
        if (index > 0) {
            System.arraycopy(array, 0, result, 0, index);
        }
        if (index < array.length) {
            System.arraycopy(array, index, result, values.length + index, array.length - index);
        }
        return result;
    }

    @SafeVarargs
    public static <T> T[] insert(int i, T[] tArr, T... tArr2) {
        if (tArr == null) {
            return null;
        }
        if (isEmpty(tArr2)) {
            return (T[]) clone(tArr);
        }
        if (i < 0 || i > tArr.length) {
            throw new IndexOutOfBoundsException("Index: " + i + ", Length: " + tArr.length);
        }
        T[] tArr3 = (T[]) newInstance(getComponentType(tArr), tArr.length + tArr2.length);
        System.arraycopy(tArr2, 0, tArr3, i, tArr2.length);
        if (i > 0) {
            System.arraycopy(tArr, 0, tArr3, 0, i);
        }
        if (i < tArr.length) {
            System.arraycopy(tArr, i, tArr3, tArr2.length + i, tArr.length - i);
        }
        return tArr3;
    }

    private static boolean isArrayEmpty(Object array) {
        return getLength(array) == 0;
    }

    public static <T> boolean isArrayIndexValid(T[] array, int index) {
        return index >= 0 && getLength(array) > index;
    }

    public static boolean isEmpty(boolean[] array) {
        return isArrayEmpty(array);
    }

    public static boolean isEmpty(byte[] array) {
        return isArrayEmpty(array);
    }

    public static boolean isEmpty(char[] array) {
        return isArrayEmpty(array);
    }

    public static boolean isEmpty(double[] array) {
        return isArrayEmpty(array);
    }

    public static boolean isEmpty(float[] array) {
        return isArrayEmpty(array);
    }

    public static boolean isEmpty(int[] array) {
        return isArrayEmpty(array);
    }

    public static boolean isEmpty(long[] array) {
        return isArrayEmpty(array);
    }

    public static boolean isEmpty(Object[] array) {
        return isArrayEmpty(array);
    }

    public static boolean isEmpty(short[] array) {
        return isArrayEmpty(array);
    }

    @Deprecated
    public static boolean isEquals(Object array1, Object array2) {
        return new EqualsBuilder().append(array1, array2).isEquals();
    }

    public static boolean isNotEmpty(boolean[] array) {
        return !isEmpty(array);
    }

    public static boolean isNotEmpty(byte[] array) {
        return !isEmpty(array);
    }

    public static boolean isNotEmpty(char[] array) {
        return !isEmpty(array);
    }

    public static boolean isNotEmpty(double[] array) {
        return !isEmpty(array);
    }

    public static boolean isNotEmpty(float[] array) {
        return !isEmpty(array);
    }

    public static boolean isNotEmpty(int[] array) {
        return !isEmpty(array);
    }

    public static boolean isNotEmpty(long[] array) {
        return !isEmpty(array);
    }

    public static boolean isNotEmpty(short[] array) {
        return !isEmpty(array);
    }

    public static <T> boolean isNotEmpty(T[] array) {
        return !isEmpty(array);
    }

    public static boolean isSameLength(boolean[] array1, boolean[] array2) {
        return getLength(array1) == getLength(array2);
    }

    public static boolean isSameLength(byte[] array1, byte[] array2) {
        return getLength(array1) == getLength(array2);
    }

    public static boolean isSameLength(char[] array1, char[] array2) {
        return getLength(array1) == getLength(array2);
    }

    public static boolean isSameLength(double[] array1, double[] array2) {
        return getLength(array1) == getLength(array2);
    }

    public static boolean isSameLength(float[] array1, float[] array2) {
        return getLength(array1) == getLength(array2);
    }

    public static boolean isSameLength(int[] array1, int[] array2) {
        return getLength(array1) == getLength(array2);
    }

    public static boolean isSameLength(long[] array1, long[] array2) {
        return getLength(array1) == getLength(array2);
    }

    public static boolean isSameLength(Object array1, Object array2) {
        return getLength(array1) == getLength(array2);
    }

    public static boolean isSameLength(Object[] array1, Object[] array2) {
        return getLength(array1) == getLength(array2);
    }

    public static boolean isSameLength(short[] array1, short[] array2) {
        return getLength(array1) == getLength(array2);
    }

    public static boolean isSameType(Object array1, Object array2) {
        if (array1 == null || array2 == null) {
            throw new IllegalArgumentException("The Array must not be null");
        }
        return array1.getClass().getName().equals(array2.getClass().getName());
    }

    public static boolean isSorted(boolean[] array) {
        if (getLength(array) < 2) {
            return true;
        }
        boolean previous = array[0];
        int n = array.length;
        for (int i = 1; i < n; i++) {
            boolean current = array[i];
            if (BooleanUtils.compare(previous, current) > 0) {
                return false;
            }
            previous = current;
        }
        return true;
    }

    public static boolean isSorted(byte[] array) {
        if (getLength(array) < 2) {
            return true;
        }
        byte previous = array[0];
        int n = array.length;
        for (int i = 1; i < n; i++) {
            byte current = array[i];
            if (NumberUtils.compare(previous, current) > 0) {
                return false;
            }
            previous = current;
        }
        return true;
    }

    public static boolean isSorted(char[] array) {
        if (getLength(array) < 2) {
            return true;
        }
        char previous = array[0];
        int n = array.length;
        for (int i = 1; i < n; i++) {
            char current = array[i];
            if (CharUtils.compare(previous, current) > 0) {
                return false;
            }
            previous = current;
        }
        return true;
    }

    public static boolean isSorted(double[] array) {
        if (getLength(array) < 2) {
            return true;
        }
        double previous = array[0];
        int n = array.length;
        for (int i = 1; i < n; i++) {
            double current = array[i];
            if (Double.compare(previous, current) > 0) {
                return false;
            }
            previous = current;
        }
        return true;
    }

    public static boolean isSorted(float[] array) {
        if (getLength(array) < 2) {
            return true;
        }
        float previous = array[0];
        int n = array.length;
        for (int i = 1; i < n; i++) {
            float current = array[i];
            if (Float.compare(previous, current) > 0) {
                return false;
            }
            previous = current;
        }
        return true;
    }

    public static boolean isSorted(int[] array) {
        if (getLength(array) < 2) {
            return true;
        }
        int previous = array[0];
        int n = array.length;
        for (int i = 1; i < n; i++) {
            int current = array[i];
            if (NumberUtils.compare(previous, current) > 0) {
                return false;
            }
            previous = current;
        }
        return true;
    }

    public static boolean isSorted(long[] array) {
        if (getLength(array) < 2) {
            return true;
        }
        long previous = array[0];
        int n = array.length;
        for (int i = 1; i < n; i++) {
            long current = array[i];
            if (NumberUtils.compare(previous, current) > 0) {
                return false;
            }
            previous = current;
        }
        return true;
    }

    public static boolean isSorted(short[] array) {
        if (getLength(array) < 2) {
            return true;
        }
        short previous = array[0];
        int n = array.length;
        for (int i = 1; i < n; i++) {
            short current = array[i];
            if (NumberUtils.compare(previous, current) > 0) {
                return false;
            }
            previous = current;
        }
        return true;
    }

    public static <T extends Comparable<? super T>> boolean isSorted(T[] array) {
        return isSorted(array, new Comparator() { // from class: org.apache.commons.lang3.ArrayUtils$$ExternalSyntheticLambda15
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return ((Comparable) obj).compareTo((Comparable) obj2);
            }
        });
    }

    public static <T> boolean isSorted(T[] array, Comparator<T> comparator) {
        Objects.requireNonNull(comparator, "comparator");
        if (getLength(array) < 2) {
            return true;
        }
        T previous = array[0];
        int n = array.length;
        for (int i = 1; i < n; i++) {
            T current = array[i];
            if (comparator.compare(previous, current) > 0) {
                return false;
            }
            previous = current;
        }
        return true;
    }

    public static int lastIndexOf(boolean[] array, boolean valueToFind) {
        return lastIndexOf(array, valueToFind, Integer.MAX_VALUE);
    }

    public static int lastIndexOf(boolean[] array, boolean valueToFind, int startIndex) {
        if (isEmpty(array) || startIndex < 0) {
            return -1;
        }
        if (startIndex >= array.length) {
            startIndex = array.length - 1;
        }
        for (int i = startIndex; i >= 0; i--) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return -1;
    }

    public static int lastIndexOf(byte[] array, byte valueToFind) {
        return lastIndexOf(array, valueToFind, Integer.MAX_VALUE);
    }

    public static int lastIndexOf(byte[] array, byte valueToFind, int startIndex) {
        if (array == null || startIndex < 0) {
            return -1;
        }
        if (startIndex >= array.length) {
            startIndex = array.length - 1;
        }
        for (int i = startIndex; i >= 0; i--) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return -1;
    }

    public static int lastIndexOf(char[] array, char valueToFind) {
        return lastIndexOf(array, valueToFind, Integer.MAX_VALUE);
    }

    public static int lastIndexOf(char[] array, char valueToFind, int startIndex) {
        if (array == null || startIndex < 0) {
            return -1;
        }
        if (startIndex >= array.length) {
            startIndex = array.length - 1;
        }
        for (int i = startIndex; i >= 0; i--) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return -1;
    }

    public static int lastIndexOf(double[] array, double valueToFind) {
        return lastIndexOf(array, valueToFind, Integer.MAX_VALUE);
    }

    public static int lastIndexOf(double[] array, double valueToFind, double tolerance) {
        return lastIndexOf(array, valueToFind, Integer.MAX_VALUE, tolerance);
    }

    public static int lastIndexOf(double[] array, double valueToFind, int startIndex) {
        if (isEmpty(array) || startIndex < 0) {
            return -1;
        }
        if (startIndex >= array.length) {
            startIndex = array.length - 1;
        }
        for (int i = startIndex; i >= 0; i--) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return -1;
    }

    public static int lastIndexOf(double[] array, double valueToFind, int startIndex, double tolerance) {
        if (isEmpty(array) || startIndex < 0) {
            return -1;
        }
        if (startIndex >= array.length) {
            startIndex = array.length - 1;
        }
        double min = valueToFind - tolerance;
        double max = valueToFind + tolerance;
        for (int i = startIndex; i >= 0; i--) {
            if (array[i] >= min && array[i] <= max) {
                return i;
            }
        }
        return -1;
    }

    public static int lastIndexOf(float[] array, float valueToFind) {
        return lastIndexOf(array, valueToFind, Integer.MAX_VALUE);
    }

    public static int lastIndexOf(float[] array, float valueToFind, int startIndex) {
        if (isEmpty(array) || startIndex < 0) {
            return -1;
        }
        if (startIndex >= array.length) {
            startIndex = array.length - 1;
        }
        for (int i = startIndex; i >= 0; i--) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return -1;
    }

    public static int lastIndexOf(int[] array, int valueToFind) {
        return lastIndexOf(array, valueToFind, Integer.MAX_VALUE);
    }

    public static int lastIndexOf(int[] array, int valueToFind, int startIndex) {
        if (array == null || startIndex < 0) {
            return -1;
        }
        if (startIndex >= array.length) {
            startIndex = array.length - 1;
        }
        for (int i = startIndex; i >= 0; i--) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return -1;
    }

    public static int lastIndexOf(long[] array, long valueToFind) {
        return lastIndexOf(array, valueToFind, Integer.MAX_VALUE);
    }

    public static int lastIndexOf(long[] array, long valueToFind, int startIndex) {
        if (array == null || startIndex < 0) {
            return -1;
        }
        if (startIndex >= array.length) {
            startIndex = array.length - 1;
        }
        for (int i = startIndex; i >= 0; i--) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return -1;
    }

    public static int lastIndexOf(Object[] array, Object objectToFind) {
        return lastIndexOf(array, objectToFind, Integer.MAX_VALUE);
    }

    public static int lastIndexOf(Object[] array, Object objectToFind, int startIndex) {
        if (array == null || startIndex < 0) {
            return -1;
        }
        if (startIndex >= array.length) {
            startIndex = array.length - 1;
        }
        if (objectToFind == null) {
            for (int i = startIndex; i >= 0; i--) {
                if (array[i] == null) {
                    return i;
                }
            }
        } else if (array.getClass().getComponentType().isInstance(objectToFind)) {
            for (int i2 = startIndex; i2 >= 0; i2--) {
                if (objectToFind.equals(array[i2])) {
                    return i2;
                }
            }
        }
        return -1;
    }

    public static int lastIndexOf(short[] array, short valueToFind) {
        return lastIndexOf(array, valueToFind, Integer.MAX_VALUE);
    }

    public static int lastIndexOf(short[] array, short valueToFind, int startIndex) {
        if (array == null || startIndex < 0) {
            return -1;
        }
        if (startIndex >= array.length) {
            startIndex = array.length - 1;
        }
        for (int i = startIndex; i >= 0; i--) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return -1;
    }

    private static <T, R, E extends Throwable> R[] map(final T[] tArr, Class<R> cls, final FailableFunction<? super T, ? extends R, E> failableFunction) throws Throwable {
        return (R[]) ArrayFill.fill(newInstance(cls, tArr.length), new FailableIntFunction() { // from class: org.apache.commons.lang3.ArrayUtils$$ExternalSyntheticLambda19
            @Override // org.apache.commons.lang3.function.FailableIntFunction
            public final Object apply(int i) {
                Object apply;
                apply = FailableFunction.this.apply(tArr[i]);
                return apply;
            }
        });
    }

    private static int max0(int other) {
        return Math.max(0, other);
    }

    public static <T> T[] newInstance(Class<T> cls, int i) {
        return (T[]) ((Object[]) Array.newInstance((Class<?>) cls, i));
    }

    public static <T> T[] nullTo(T[] array, T[] defaultArray) {
        return isEmpty(array) ? defaultArray : array;
    }

    public static boolean[] nullToEmpty(boolean[] array) {
        return isEmpty(array) ? EMPTY_BOOLEAN_ARRAY : array;
    }

    public static Boolean[] nullToEmpty(Boolean[] array) {
        return (Boolean[]) nullTo(array, EMPTY_BOOLEAN_OBJECT_ARRAY);
    }

    public static byte[] nullToEmpty(byte[] array) {
        return isEmpty(array) ? EMPTY_BYTE_ARRAY : array;
    }

    public static Byte[] nullToEmpty(Byte[] array) {
        return (Byte[]) nullTo(array, EMPTY_BYTE_OBJECT_ARRAY);
    }

    public static char[] nullToEmpty(char[] array) {
        return isEmpty(array) ? EMPTY_CHAR_ARRAY : array;
    }

    public static Character[] nullToEmpty(Character[] array) {
        return (Character[]) nullTo(array, EMPTY_CHARACTER_OBJECT_ARRAY);
    }

    public static Class<?>[] nullToEmpty(Class<?>[] array) {
        return (Class[]) nullTo(array, EMPTY_CLASS_ARRAY);
    }

    public static double[] nullToEmpty(double[] array) {
        return isEmpty(array) ? EMPTY_DOUBLE_ARRAY : array;
    }

    public static Double[] nullToEmpty(Double[] array) {
        return (Double[]) nullTo(array, EMPTY_DOUBLE_OBJECT_ARRAY);
    }

    public static float[] nullToEmpty(float[] array) {
        return isEmpty(array) ? EMPTY_FLOAT_ARRAY : array;
    }

    public static Float[] nullToEmpty(Float[] array) {
        return (Float[]) nullTo(array, EMPTY_FLOAT_OBJECT_ARRAY);
    }

    public static int[] nullToEmpty(int[] array) {
        return isEmpty(array) ? EMPTY_INT_ARRAY : array;
    }

    public static Integer[] nullToEmpty(Integer[] array) {
        return (Integer[]) nullTo(array, EMPTY_INTEGER_OBJECT_ARRAY);
    }

    public static long[] nullToEmpty(long[] array) {
        return isEmpty(array) ? EMPTY_LONG_ARRAY : array;
    }

    public static Long[] nullToEmpty(Long[] array) {
        return (Long[]) nullTo(array, EMPTY_LONG_OBJECT_ARRAY);
    }

    public static Object[] nullToEmpty(Object[] array) {
        return nullTo(array, EMPTY_OBJECT_ARRAY);
    }

    public static short[] nullToEmpty(short[] array) {
        return isEmpty(array) ? EMPTY_SHORT_ARRAY : array;
    }

    public static Short[] nullToEmpty(Short[] array) {
        return (Short[]) nullTo(array, EMPTY_SHORT_OBJECT_ARRAY);
    }

    public static String[] nullToEmpty(String[] array) {
        return (String[]) nullTo(array, EMPTY_STRING_ARRAY);
    }

    public static <T> T[] nullToEmpty(T[] array, Class<T[]> type) {
        if (type == null) {
            throw new IllegalArgumentException("The type must not be null");
        }
        if (array == null) {
            return type.cast(Array.newInstance(type.getComponentType(), 0));
        }
        return array;
    }

    private static ThreadLocalRandom random() {
        return ThreadLocalRandom.current();
    }

    public static boolean[] remove(boolean[] array, int index) {
        return (boolean[]) remove((Object) array, index);
    }

    public static byte[] remove(byte[] array, int index) {
        return (byte[]) remove((Object) array, index);
    }

    public static char[] remove(char[] array, int index) {
        return (char[]) remove((Object) array, index);
    }

    public static double[] remove(double[] array, int index) {
        return (double[]) remove((Object) array, index);
    }

    public static float[] remove(float[] array, int index) {
        return (float[]) remove((Object) array, index);
    }

    public static int[] remove(int[] array, int index) {
        return (int[]) remove((Object) array, index);
    }

    public static long[] remove(long[] array, int index) {
        return (long[]) remove((Object) array, index);
    }

    private static Object remove(Object array, int index) {
        int length = getLength(array);
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + length);
        }
        Object result = Array.newInstance(array.getClass().getComponentType(), length - 1);
        System.arraycopy(array, 0, result, 0, index);
        if (index < length - 1) {
            System.arraycopy(array, index + 1, result, index, (length - index) - 1);
        }
        return result;
    }

    public static short[] remove(short[] array, int index) {
        return (short[]) remove((Object) array, index);
    }

    public static <T> T[] remove(T[] tArr, int i) {
        return (T[]) ((Object[]) remove((Object) tArr, i));
    }

    public static boolean[] removeAll(boolean[] array, int... indices) {
        return (boolean[]) removeAll((Object) array, indices);
    }

    public static byte[] removeAll(byte[] array, int... indices) {
        return (byte[]) removeAll((Object) array, indices);
    }

    public static char[] removeAll(char[] array, int... indices) {
        return (char[]) removeAll((Object) array, indices);
    }

    public static double[] removeAll(double[] array, int... indices) {
        return (double[]) removeAll((Object) array, indices);
    }

    public static float[] removeAll(float[] array, int... indices) {
        return (float[]) removeAll((Object) array, indices);
    }

    public static int[] removeAll(int[] array, int... indices) {
        return (int[]) removeAll((Object) array, indices);
    }

    public static long[] removeAll(long[] array, int... indices) {
        return (long[]) removeAll((Object) array, indices);
    }

    static Object removeAll(Object array, int... indices) {
        int index;
        if (array == null) {
            return null;
        }
        int length = getLength(array);
        int diff = 0;
        int[] clonedIndices = ArraySorter.sort(clone(indices));
        if (isNotEmpty(clonedIndices)) {
            int i = clonedIndices.length;
            int prevIndex = length;
            while (true) {
                i--;
                if (i < 0) {
                    break;
                }
                index = clonedIndices[i];
                if (index < 0 || index >= length) {
                    break;
                }
                if (index < prevIndex) {
                    diff++;
                    prevIndex = index;
                }
            }
            throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + length);
        }
        Object result = Array.newInstance(array.getClass().getComponentType(), length - diff);
        if (diff < length && clonedIndices != null) {
            int end = length;
            int dest = length - diff;
            for (int i2 = clonedIndices.length - 1; i2 >= 0; i2--) {
                int index2 = clonedIndices[i2];
                if (end - index2 > 1) {
                    int cp = (end - index2) - 1;
                    dest -= cp;
                    System.arraycopy(array, index2 + 1, result, dest, cp);
                }
                end = index2;
            }
            if (end > 0) {
                System.arraycopy(array, 0, result, 0, end);
            }
        }
        return result;
    }

    public static short[] removeAll(short[] array, int... indices) {
        return (short[]) removeAll((Object) array, indices);
    }

    public static <T> T[] removeAll(T[] tArr, int... iArr) {
        return (T[]) ((Object[]) removeAll((Object) tArr, iArr));
    }

    @Deprecated
    public static boolean[] removeAllOccurences(boolean[] array, boolean element) {
        return (boolean[]) removeAt(array, indexesOf(array, element));
    }

    @Deprecated
    public static byte[] removeAllOccurences(byte[] array, byte element) {
        return (byte[]) removeAt(array, indexesOf(array, element));
    }

    @Deprecated
    public static char[] removeAllOccurences(char[] array, char element) {
        return (char[]) removeAt(array, indexesOf(array, element));
    }

    @Deprecated
    public static double[] removeAllOccurences(double[] array, double element) {
        return (double[]) removeAt(array, indexesOf(array, element));
    }

    @Deprecated
    public static float[] removeAllOccurences(float[] array, float element) {
        return (float[]) removeAt(array, indexesOf(array, element));
    }

    @Deprecated
    public static int[] removeAllOccurences(int[] array, int element) {
        return (int[]) removeAt(array, indexesOf(array, element));
    }

    @Deprecated
    public static long[] removeAllOccurences(long[] array, long element) {
        return (long[]) removeAt(array, indexesOf(array, element));
    }

    @Deprecated
    public static short[] removeAllOccurences(short[] array, short element) {
        return (short[]) removeAt(array, indexesOf(array, element));
    }

    @Deprecated
    public static <T> T[] removeAllOccurences(T[] tArr, T t) {
        return (T[]) ((Object[]) removeAt(tArr, indexesOf(tArr, t)));
    }

    public static boolean[] removeAllOccurrences(boolean[] array, boolean element) {
        return (boolean[]) removeAt(array, indexesOf(array, element));
    }

    public static byte[] removeAllOccurrences(byte[] array, byte element) {
        return (byte[]) removeAt(array, indexesOf(array, element));
    }

    public static char[] removeAllOccurrences(char[] array, char element) {
        return (char[]) removeAt(array, indexesOf(array, element));
    }

    public static double[] removeAllOccurrences(double[] array, double element) {
        return (double[]) removeAt(array, indexesOf(array, element));
    }

    public static float[] removeAllOccurrences(float[] array, float element) {
        return (float[]) removeAt(array, indexesOf(array, element));
    }

    public static int[] removeAllOccurrences(int[] array, int element) {
        return (int[]) removeAt(array, indexesOf(array, element));
    }

    public static long[] removeAllOccurrences(long[] array, long element) {
        return (long[]) removeAt(array, indexesOf(array, element));
    }

    public static short[] removeAllOccurrences(short[] array, short element) {
        return (short[]) removeAt(array, indexesOf(array, element));
    }

    public static <T> T[] removeAllOccurrences(T[] tArr, T t) {
        return (T[]) ((Object[]) removeAt(tArr, indexesOf(tArr, t)));
    }

    static Object removeAt(Object array, BitSet indices) {
        if (array == null) {
            return null;
        }
        int srcLength = getLength(array);
        int removals = indices.cardinality();
        Object result = Array.newInstance(array.getClass().getComponentType(), srcLength - removals);
        int srcIndex = 0;
        int destIndex = 0;
        while (true) {
            int set = indices.nextSetBit(srcIndex);
            if (set == -1) {
                break;
            }
            int count = set - srcIndex;
            if (count > 0) {
                System.arraycopy(array, srcIndex, result, destIndex, count);
                destIndex += count;
            }
            srcIndex = indices.nextClearBit(set);
        }
        int count2 = srcLength - srcIndex;
        if (count2 > 0) {
            System.arraycopy(array, srcIndex, result, destIndex, count2);
        }
        return result;
    }

    public static boolean[] removeElement(boolean[] array, boolean element) {
        int index = indexOf(array, element);
        return index == -1 ? clone(array) : remove(array, index);
    }

    public static byte[] removeElement(byte[] array, byte element) {
        int index = indexOf(array, element);
        return index == -1 ? clone(array) : remove(array, index);
    }

    public static char[] removeElement(char[] array, char element) {
        int index = indexOf(array, element);
        return index == -1 ? clone(array) : remove(array, index);
    }

    public static double[] removeElement(double[] array, double element) {
        int index = indexOf(array, element);
        return index == -1 ? clone(array) : remove(array, index);
    }

    public static float[] removeElement(float[] array, float element) {
        int index = indexOf(array, element);
        return index == -1 ? clone(array) : remove(array, index);
    }

    public static int[] removeElement(int[] array, int element) {
        int index = indexOf(array, element);
        return index == -1 ? clone(array) : remove(array, index);
    }

    public static long[] removeElement(long[] array, long element) {
        int index = indexOf(array, element);
        return index == -1 ? clone(array) : remove(array, index);
    }

    public static short[] removeElement(short[] array, short element) {
        int index = indexOf(array, element);
        return index == -1 ? clone(array) : remove(array, index);
    }

    public static <T> T[] removeElement(T[] tArr, Object obj) {
        int indexOf = indexOf(tArr, obj);
        return indexOf == -1 ? (T[]) clone(tArr) : (T[]) remove((Object[]) tArr, indexOf);
    }

    public static boolean[] removeElements(boolean[] array, boolean... values) {
        if (isEmpty(array) || isEmpty(values)) {
            return clone(array);
        }
        HashMap<Boolean, MutableInt> occurrences = new HashMap<>(2);
        for (boolean v : values) {
            increment(occurrences, Boolean.valueOf(v));
        }
        BitSet toRemove = new BitSet();
        for (int i = 0; i < array.length; i++) {
            boolean key = array[i];
            MutableInt count = occurrences.get(Boolean.valueOf(key));
            if (count != null) {
                if (count.decrementAndGet() == 0) {
                    occurrences.remove(Boolean.valueOf(key));
                }
                toRemove.set(i);
            }
        }
        return (boolean[]) removeAt(array, toRemove);
    }

    public static byte[] removeElements(byte[] array, byte... values) {
        if (isEmpty(array) || isEmpty(values)) {
            return clone(array);
        }
        HashMap<Byte, MutableInt> occurrences = new HashMap<>(values.length);
        for (byte v : values) {
            increment(occurrences, Byte.valueOf(v));
        }
        BitSet toRemove = new BitSet();
        for (int i = 0; i < array.length; i++) {
            byte key = array[i];
            MutableInt count = occurrences.get(Byte.valueOf(key));
            if (count != null) {
                if (count.decrementAndGet() == 0) {
                    occurrences.remove(Byte.valueOf(key));
                }
                toRemove.set(i);
            }
        }
        return (byte[]) removeAt(array, toRemove);
    }

    public static char[] removeElements(char[] array, char... values) {
        if (isEmpty(array) || isEmpty(values)) {
            return clone(array);
        }
        HashMap<Character, MutableInt> occurrences = new HashMap<>(values.length);
        for (char v : values) {
            increment(occurrences, Character.valueOf(v));
        }
        BitSet toRemove = new BitSet();
        for (int i = 0; i < array.length; i++) {
            char key = array[i];
            MutableInt count = occurrences.get(Character.valueOf(key));
            if (count != null) {
                if (count.decrementAndGet() == 0) {
                    occurrences.remove(Character.valueOf(key));
                }
                toRemove.set(i);
            }
        }
        return (char[]) removeAt(array, toRemove);
    }

    public static double[] removeElements(double[] array, double... values) {
        if (isEmpty(array) || isEmpty(values)) {
            return clone(array);
        }
        HashMap<Double, MutableInt> occurrences = new HashMap<>(values.length);
        for (double v : values) {
            increment(occurrences, Double.valueOf(v));
        }
        BitSet toRemove = new BitSet();
        for (int i = 0; i < array.length; i++) {
            double key = array[i];
            MutableInt count = occurrences.get(Double.valueOf(key));
            if (count != null) {
                if (count.decrementAndGet() == 0) {
                    occurrences.remove(Double.valueOf(key));
                }
                toRemove.set(i);
            }
        }
        return (double[]) removeAt(array, toRemove);
    }

    public static float[] removeElements(float[] array, float... values) {
        if (isEmpty(array) || isEmpty(values)) {
            return clone(array);
        }
        HashMap<Float, MutableInt> occurrences = new HashMap<>(values.length);
        for (float v : values) {
            increment(occurrences, Float.valueOf(v));
        }
        BitSet toRemove = new BitSet();
        for (int i = 0; i < array.length; i++) {
            float key = array[i];
            MutableInt count = occurrences.get(Float.valueOf(key));
            if (count != null) {
                if (count.decrementAndGet() == 0) {
                    occurrences.remove(Float.valueOf(key));
                }
                toRemove.set(i);
            }
        }
        return (float[]) removeAt(array, toRemove);
    }

    public static int[] removeElements(int[] array, int... values) {
        if (isEmpty(array) || isEmpty(values)) {
            return clone(array);
        }
        HashMap<Integer, MutableInt> occurrences = new HashMap<>(values.length);
        for (int v : values) {
            increment(occurrences, Integer.valueOf(v));
        }
        BitSet toRemove = new BitSet();
        for (int i = 0; i < array.length; i++) {
            int key = array[i];
            MutableInt count = occurrences.get(Integer.valueOf(key));
            if (count != null) {
                if (count.decrementAndGet() == 0) {
                    occurrences.remove(Integer.valueOf(key));
                }
                toRemove.set(i);
            }
        }
        return (int[]) removeAt(array, toRemove);
    }

    public static long[] removeElements(long[] array, long... values) {
        if (isEmpty(array) || isEmpty(values)) {
            return clone(array);
        }
        HashMap<Long, MutableInt> occurrences = new HashMap<>(values.length);
        for (long v : values) {
            increment(occurrences, Long.valueOf(v));
        }
        BitSet toRemove = new BitSet();
        for (int i = 0; i < array.length; i++) {
            long key = array[i];
            MutableInt count = occurrences.get(Long.valueOf(key));
            if (count != null) {
                if (count.decrementAndGet() == 0) {
                    occurrences.remove(Long.valueOf(key));
                }
                toRemove.set(i);
            }
        }
        return (long[]) removeAt(array, toRemove);
    }

    public static short[] removeElements(short[] array, short... values) {
        if (isEmpty(array) || isEmpty(values)) {
            return clone(array);
        }
        HashMap<Short, MutableInt> occurrences = new HashMap<>(values.length);
        for (short v : values) {
            increment(occurrences, Short.valueOf(v));
        }
        BitSet toRemove = new BitSet();
        for (int i = 0; i < array.length; i++) {
            short key = array[i];
            MutableInt count = occurrences.get(Short.valueOf(key));
            if (count != null) {
                if (count.decrementAndGet() == 0) {
                    occurrences.remove(Short.valueOf(key));
                }
                toRemove.set(i);
            }
        }
        return (short[]) removeAt(array, toRemove);
    }

    @SafeVarargs
    public static <T> T[] removeElements(T[] tArr, T... tArr2) {
        if (isEmpty(tArr) || isEmpty(tArr2)) {
            return (T[]) clone(tArr);
        }
        HashMap hashMap = new HashMap(tArr2.length);
        for (T t : tArr2) {
            increment(hashMap, t);
        }
        BitSet bitSet = new BitSet();
        for (int i = 0; i < tArr.length; i++) {
            T t2 = tArr[i];
            MutableInt mutableInt = (MutableInt) hashMap.get(t2);
            if (mutableInt != null) {
                if (mutableInt.decrementAndGet() == 0) {
                    hashMap.remove(t2);
                }
                bitSet.set(i);
            }
        }
        return (T[]) ((Object[]) removeAt(tArr, bitSet));
    }

    public static void reverse(boolean[] array) {
        if (array == null) {
            return;
        }
        reverse(array, 0, array.length);
    }

    public static void reverse(boolean[] array, int startIndexInclusive, int endIndexExclusive) {
        if (array == null) {
            return;
        }
        int j = Math.min(array.length, endIndexExclusive) - 1;
        for (int i = Math.max(startIndexInclusive, 0); j > i; i++) {
            boolean tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
        }
    }

    public static void reverse(byte[] array) {
        if (array != null) {
            reverse(array, 0, array.length);
        }
    }

    public static void reverse(byte[] array, int startIndexInclusive, int endIndexExclusive) {
        if (array == null) {
            return;
        }
        int j = Math.min(array.length, endIndexExclusive) - 1;
        for (int i = Math.max(startIndexInclusive, 0); j > i; i++) {
            byte tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
        }
    }

    public static void reverse(char[] array) {
        if (array != null) {
            reverse(array, 0, array.length);
        }
    }

    public static void reverse(char[] array, int startIndexInclusive, int endIndexExclusive) {
        if (array == null) {
            return;
        }
        int j = Math.min(array.length, endIndexExclusive) - 1;
        for (int i = Math.max(startIndexInclusive, 0); j > i; i++) {
            char tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
        }
    }

    public static void reverse(double[] array) {
        if (array != null) {
            reverse(array, 0, array.length);
        }
    }

    public static void reverse(double[] array, int startIndexInclusive, int endIndexExclusive) {
        if (array == null) {
            return;
        }
        int j = Math.min(array.length, endIndexExclusive) - 1;
        for (int i = Math.max(startIndexInclusive, 0); j > i; i++) {
            double tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
        }
    }

    public static void reverse(float[] array) {
        if (array != null) {
            reverse(array, 0, array.length);
        }
    }

    public static void reverse(float[] array, int startIndexInclusive, int endIndexExclusive) {
        if (array == null) {
            return;
        }
        int j = Math.min(array.length, endIndexExclusive) - 1;
        for (int i = Math.max(startIndexInclusive, 0); j > i; i++) {
            float tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
        }
    }

    public static void reverse(int[] array) {
        if (array != null) {
            reverse(array, 0, array.length);
        }
    }

    public static void reverse(int[] array, int startIndexInclusive, int endIndexExclusive) {
        if (array == null) {
            return;
        }
        int j = Math.min(array.length, endIndexExclusive) - 1;
        for (int i = Math.max(startIndexInclusive, 0); j > i; i++) {
            int tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
        }
    }

    public static void reverse(long[] array) {
        if (array != null) {
            reverse(array, 0, array.length);
        }
    }

    public static void reverse(long[] array, int startIndexInclusive, int endIndexExclusive) {
        if (array == null) {
            return;
        }
        int j = Math.min(array.length, endIndexExclusive) - 1;
        for (int i = Math.max(startIndexInclusive, 0); j > i; i++) {
            long tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
        }
    }

    public static void reverse(Object[] array) {
        if (array != null) {
            reverse(array, 0, array.length);
        }
    }

    public static void reverse(Object[] array, int startIndexInclusive, int endIndexExclusive) {
        if (array == null) {
            return;
        }
        int j = Math.min(array.length, endIndexExclusive) - 1;
        for (int i = Math.max(startIndexInclusive, 0); j > i; i++) {
            Object tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
        }
    }

    public static void reverse(short[] array) {
        if (array != null) {
            reverse(array, 0, array.length);
        }
    }

    public static void reverse(short[] array, int startIndexInclusive, int endIndexExclusive) {
        if (array == null) {
            return;
        }
        int j = Math.min(array.length, endIndexExclusive) - 1;
        for (int i = Math.max(startIndexInclusive, 0); j > i; i++) {
            short tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
        }
    }

    public static <T> T[] setAll(T[] array, IntFunction<? extends T> generator) {
        if (array != null && generator != null) {
            Arrays.setAll(array, generator);
        }
        return array;
    }

    public static <T> T[] setAll(T[] array, Supplier<? extends T> generator) {
        if (array != null && generator != null) {
            for (int i = 0; i < array.length; i++) {
                array[i] = generator.get();
            }
        }
        return array;
    }

    public static void shift(boolean[] array, int offset) {
        if (array != null) {
            shift(array, 0, array.length, offset);
        }
    }

    public static void shift(boolean[] array, int startIndexInclusive, int endIndexExclusive, int offset) {
        int startIndexInclusive2;
        int n;
        if (array == null || startIndexInclusive >= array.length - 1 || endIndexExclusive <= 0 || (n = Math.min(endIndexExclusive, array.length) - (startIndexInclusive2 = max0(startIndexInclusive))) <= 1) {
            return;
        }
        int offset2 = offset % n;
        if (offset2 < 0) {
            offset2 += n;
        }
        while (n > 1 && offset2 > 0) {
            int nOffset = n - offset2;
            if (offset2 > nOffset) {
                swap(array, startIndexInclusive2, (startIndexInclusive2 + n) - nOffset, nOffset);
                n = offset2;
                offset2 -= nOffset;
            } else if (offset2 < nOffset) {
                swap(array, startIndexInclusive2, startIndexInclusive2 + nOffset, offset2);
                startIndexInclusive2 += offset2;
                n = nOffset;
            } else {
                swap(array, startIndexInclusive2, startIndexInclusive2 + nOffset, offset2);
                return;
            }
        }
    }

    public static void shift(byte[] array, int offset) {
        if (array != null) {
            shift(array, 0, array.length, offset);
        }
    }

    public static void shift(byte[] array, int startIndexInclusive, int endIndexExclusive, int offset) {
        int startIndexInclusive2;
        int n;
        if (array == null || startIndexInclusive >= array.length - 1 || endIndexExclusive <= 0 || (n = Math.min(endIndexExclusive, array.length) - (startIndexInclusive2 = max0(startIndexInclusive))) <= 1) {
            return;
        }
        int offset2 = offset % n;
        if (offset2 < 0) {
            offset2 += n;
        }
        while (n > 1 && offset2 > 0) {
            int nOffset = n - offset2;
            if (offset2 > nOffset) {
                swap(array, startIndexInclusive2, (startIndexInclusive2 + n) - nOffset, nOffset);
                n = offset2;
                offset2 -= nOffset;
            } else if (offset2 < nOffset) {
                swap(array, startIndexInclusive2, startIndexInclusive2 + nOffset, offset2);
                startIndexInclusive2 += offset2;
                n = nOffset;
            } else {
                swap(array, startIndexInclusive2, startIndexInclusive2 + nOffset, offset2);
                return;
            }
        }
    }

    public static void shift(char[] array, int offset) {
        if (array != null) {
            shift(array, 0, array.length, offset);
        }
    }

    public static void shift(char[] array, int startIndexInclusive, int endIndexExclusive, int offset) {
        int startIndexInclusive2;
        int n;
        if (array == null || startIndexInclusive >= array.length - 1 || endIndexExclusive <= 0 || (n = Math.min(endIndexExclusive, array.length) - (startIndexInclusive2 = max0(startIndexInclusive))) <= 1) {
            return;
        }
        int offset2 = offset % n;
        if (offset2 < 0) {
            offset2 += n;
        }
        while (n > 1 && offset2 > 0) {
            int nOffset = n - offset2;
            if (offset2 > nOffset) {
                swap(array, startIndexInclusive2, (startIndexInclusive2 + n) - nOffset, nOffset);
                n = offset2;
                offset2 -= nOffset;
            } else if (offset2 < nOffset) {
                swap(array, startIndexInclusive2, startIndexInclusive2 + nOffset, offset2);
                startIndexInclusive2 += offset2;
                n = nOffset;
            } else {
                swap(array, startIndexInclusive2, startIndexInclusive2 + nOffset, offset2);
                return;
            }
        }
    }

    public static void shift(double[] array, int offset) {
        if (array != null) {
            shift(array, 0, array.length, offset);
        }
    }

    public static void shift(double[] array, int startIndexInclusive, int endIndexExclusive, int offset) {
        int startIndexInclusive2;
        int n;
        if (array == null || startIndexInclusive >= array.length - 1 || endIndexExclusive <= 0 || (n = Math.min(endIndexExclusive, array.length) - (startIndexInclusive2 = max0(startIndexInclusive))) <= 1) {
            return;
        }
        int offset2 = offset % n;
        if (offset2 < 0) {
            offset2 += n;
        }
        while (n > 1 && offset2 > 0) {
            int nOffset = n - offset2;
            if (offset2 > nOffset) {
                swap(array, startIndexInclusive2, (startIndexInclusive2 + n) - nOffset, nOffset);
                n = offset2;
                offset2 -= nOffset;
            } else if (offset2 < nOffset) {
                swap(array, startIndexInclusive2, startIndexInclusive2 + nOffset, offset2);
                startIndexInclusive2 += offset2;
                n = nOffset;
            } else {
                swap(array, startIndexInclusive2, startIndexInclusive2 + nOffset, offset2);
                return;
            }
        }
    }

    public static void shift(float[] array, int offset) {
        if (array != null) {
            shift(array, 0, array.length, offset);
        }
    }

    public static void shift(float[] array, int startIndexInclusive, int endIndexExclusive, int offset) {
        int startIndexInclusive2;
        int n;
        if (array == null || startIndexInclusive >= array.length - 1 || endIndexExclusive <= 0 || (n = Math.min(endIndexExclusive, array.length) - (startIndexInclusive2 = max0(startIndexInclusive))) <= 1) {
            return;
        }
        int offset2 = offset % n;
        if (offset2 < 0) {
            offset2 += n;
        }
        while (n > 1 && offset2 > 0) {
            int nOffset = n - offset2;
            if (offset2 > nOffset) {
                swap(array, startIndexInclusive2, (startIndexInclusive2 + n) - nOffset, nOffset);
                n = offset2;
                offset2 -= nOffset;
            } else if (offset2 < nOffset) {
                swap(array, startIndexInclusive2, startIndexInclusive2 + nOffset, offset2);
                startIndexInclusive2 += offset2;
                n = nOffset;
            } else {
                swap(array, startIndexInclusive2, startIndexInclusive2 + nOffset, offset2);
                return;
            }
        }
    }

    public static void shift(int[] array, int offset) {
        if (array != null) {
            shift(array, 0, array.length, offset);
        }
    }

    public static void shift(int[] array, int startIndexInclusive, int endIndexExclusive, int offset) {
        int startIndexInclusive2;
        int n;
        if (array == null || startIndexInclusive >= array.length - 1 || endIndexExclusive <= 0 || (n = Math.min(endIndexExclusive, array.length) - (startIndexInclusive2 = max0(startIndexInclusive))) <= 1) {
            return;
        }
        int offset2 = offset % n;
        if (offset2 < 0) {
            offset2 += n;
        }
        while (n > 1 && offset2 > 0) {
            int nOffset = n - offset2;
            if (offset2 > nOffset) {
                swap(array, startIndexInclusive2, (startIndexInclusive2 + n) - nOffset, nOffset);
                n = offset2;
                offset2 -= nOffset;
            } else if (offset2 < nOffset) {
                swap(array, startIndexInclusive2, startIndexInclusive2 + nOffset, offset2);
                startIndexInclusive2 += offset2;
                n = nOffset;
            } else {
                swap(array, startIndexInclusive2, startIndexInclusive2 + nOffset, offset2);
                return;
            }
        }
    }

    public static void shift(long[] array, int offset) {
        if (array != null) {
            shift(array, 0, array.length, offset);
        }
    }

    public static void shift(long[] array, int startIndexInclusive, int endIndexExclusive, int offset) {
        int startIndexInclusive2;
        int n;
        if (array == null || startIndexInclusive >= array.length - 1 || endIndexExclusive <= 0 || (n = Math.min(endIndexExclusive, array.length) - (startIndexInclusive2 = max0(startIndexInclusive))) <= 1) {
            return;
        }
        int offset2 = offset % n;
        if (offset2 < 0) {
            offset2 += n;
        }
        while (n > 1 && offset2 > 0) {
            int nOffset = n - offset2;
            if (offset2 > nOffset) {
                swap(array, startIndexInclusive2, (startIndexInclusive2 + n) - nOffset, nOffset);
                n = offset2;
                offset2 -= nOffset;
            } else if (offset2 < nOffset) {
                swap(array, startIndexInclusive2, startIndexInclusive2 + nOffset, offset2);
                startIndexInclusive2 += offset2;
                n = nOffset;
            } else {
                swap(array, startIndexInclusive2, startIndexInclusive2 + nOffset, offset2);
                return;
            }
        }
    }

    public static void shift(Object[] array, int offset) {
        if (array != null) {
            shift(array, 0, array.length, offset);
        }
    }

    public static void shift(Object[] array, int startIndexInclusive, int endIndexExclusive, int offset) {
        int startIndexInclusive2;
        int n;
        if (array == null || startIndexInclusive >= array.length - 1 || endIndexExclusive <= 0 || (n = Math.min(endIndexExclusive, array.length) - (startIndexInclusive2 = max0(startIndexInclusive))) <= 1) {
            return;
        }
        int offset2 = offset % n;
        if (offset2 < 0) {
            offset2 += n;
        }
        while (n > 1 && offset2 > 0) {
            int nOffset = n - offset2;
            if (offset2 > nOffset) {
                swap(array, startIndexInclusive2, (startIndexInclusive2 + n) - nOffset, nOffset);
                n = offset2;
                offset2 -= nOffset;
            } else if (offset2 < nOffset) {
                swap(array, startIndexInclusive2, startIndexInclusive2 + nOffset, offset2);
                startIndexInclusive2 += offset2;
                n = nOffset;
            } else {
                swap(array, startIndexInclusive2, startIndexInclusive2 + nOffset, offset2);
                return;
            }
        }
    }

    public static void shift(short[] array, int offset) {
        if (array != null) {
            shift(array, 0, array.length, offset);
        }
    }

    public static void shift(short[] array, int startIndexInclusive, int endIndexExclusive, int offset) {
        int startIndexInclusive2;
        int n;
        if (array == null || startIndexInclusive >= array.length - 1 || endIndexExclusive <= 0 || (n = Math.min(endIndexExclusive, array.length) - (startIndexInclusive2 = max0(startIndexInclusive))) <= 1) {
            return;
        }
        int offset2 = offset % n;
        if (offset2 < 0) {
            offset2 += n;
        }
        while (n > 1 && offset2 > 0) {
            int nOffset = n - offset2;
            if (offset2 > nOffset) {
                swap(array, startIndexInclusive2, (startIndexInclusive2 + n) - nOffset, nOffset);
                n = offset2;
                offset2 -= nOffset;
            } else if (offset2 < nOffset) {
                swap(array, startIndexInclusive2, startIndexInclusive2 + nOffset, offset2);
                startIndexInclusive2 += offset2;
                n = nOffset;
            } else {
                swap(array, startIndexInclusive2, startIndexInclusive2 + nOffset, offset2);
                return;
            }
        }
    }

    public static void shuffle(boolean[] array) {
        shuffle(array, random());
    }

    public static void shuffle(boolean[] array, Random random) {
        for (int i = array.length; i > 1; i--) {
            swap(array, i - 1, random.nextInt(i), 1);
        }
    }

    public static void shuffle(byte[] array) {
        shuffle(array, (Random) random());
    }

    public static void shuffle(byte[] array, Random random) {
        for (int i = array.length; i > 1; i--) {
            swap(array, i - 1, random.nextInt(i), 1);
        }
    }

    public static void shuffle(char[] array) {
        shuffle(array, (Random) random());
    }

    public static void shuffle(char[] array, Random random) {
        for (int i = array.length; i > 1; i--) {
            swap(array, i - 1, random.nextInt(i), 1);
        }
    }

    public static void shuffle(double[] array) {
        shuffle(array, random());
    }

    public static void shuffle(double[] array, Random random) {
        for (int i = array.length; i > 1; i--) {
            swap(array, i - 1, random.nextInt(i), 1);
        }
    }

    public static void shuffle(float[] array) {
        shuffle(array, (Random) random());
    }

    public static void shuffle(float[] array, Random random) {
        for (int i = array.length; i > 1; i--) {
            swap(array, i - 1, random.nextInt(i), 1);
        }
    }

    public static void shuffle(int[] array) {
        shuffle(array, (Random) random());
    }

    public static void shuffle(int[] array, Random random) {
        for (int i = array.length; i > 1; i--) {
            swap(array, i - 1, random.nextInt(i), 1);
        }
    }

    public static void shuffle(long[] array) {
        shuffle(array, (Random) random());
    }

    public static void shuffle(long[] array, Random random) {
        for (int i = array.length; i > 1; i--) {
            swap(array, i - 1, random.nextInt(i), 1);
        }
    }

    public static void shuffle(Object[] array) {
        shuffle(array, random());
    }

    public static void shuffle(Object[] array, Random random) {
        for (int i = array.length; i > 1; i--) {
            swap(array, i - 1, random.nextInt(i), 1);
        }
    }

    public static void shuffle(short[] array) {
        shuffle(array, (Random) random());
    }

    public static void shuffle(short[] array, Random random) {
        for (int i = array.length; i > 1; i--) {
            swap(array, i - 1, random.nextInt(i), 1);
        }
    }

    public static boolean startsWith(byte[] data, byte[] expected) {
        int dataLen;
        if (data == expected) {
            return true;
        }
        if (data == null || expected == null || expected.length > (dataLen = data.length)) {
            return false;
        }
        if (expected.length == dataLen) {
            return Arrays.equals(data, expected);
        }
        for (int i = 0; i < expected.length; i++) {
            if (data[i] != expected[i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean[] subarray(boolean[] array, int startIndexInclusive, int endIndexExclusive) {
        if (array == null) {
            return null;
        }
        int startIndexInclusive2 = max0(startIndexInclusive);
        int newSize = Math.min(endIndexExclusive, array.length) - startIndexInclusive2;
        if (newSize <= 0) {
            return EMPTY_BOOLEAN_ARRAY;
        }
        return (boolean[]) arraycopy(array, startIndexInclusive2, 0, newSize, (Function<Integer, boolean[]>) new Function() { // from class: org.apache.commons.lang3.ArrayUtils$$ExternalSyntheticLambda22
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ArrayUtils.lambda$subarray$6(((Integer) obj).intValue());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean[] lambda$subarray$6(int x$0) {
        return new boolean[x$0];
    }

    public static byte[] subarray(byte[] array, int startIndexInclusive, int endIndexExclusive) {
        if (array == null) {
            return null;
        }
        int startIndexInclusive2 = max0(startIndexInclusive);
        int newSize = Math.min(endIndexExclusive, array.length) - startIndexInclusive2;
        if (newSize <= 0) {
            return EMPTY_BYTE_ARRAY;
        }
        return (byte[]) arraycopy(array, startIndexInclusive2, 0, newSize, (Function<Integer, byte[]>) new Function() { // from class: org.apache.commons.lang3.ArrayUtils$$ExternalSyntheticLambda16
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ArrayUtils.lambda$subarray$7(((Integer) obj).intValue());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ byte[] lambda$subarray$7(int x$0) {
        return new byte[x$0];
    }

    public static char[] subarray(char[] array, int startIndexInclusive, int endIndexExclusive) {
        if (array == null) {
            return null;
        }
        int startIndexInclusive2 = max0(startIndexInclusive);
        int newSize = Math.min(endIndexExclusive, array.length) - startIndexInclusive2;
        if (newSize <= 0) {
            return EMPTY_CHAR_ARRAY;
        }
        return (char[]) arraycopy(array, startIndexInclusive2, 0, newSize, (Function<Integer, char[]>) new Function() { // from class: org.apache.commons.lang3.ArrayUtils$$ExternalSyntheticLambda24
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ArrayUtils.lambda$subarray$8(((Integer) obj).intValue());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ char[] lambda$subarray$8(int x$0) {
        return new char[x$0];
    }

    public static double[] subarray(double[] array, int startIndexInclusive, int endIndexExclusive) {
        if (array == null) {
            return null;
        }
        int startIndexInclusive2 = max0(startIndexInclusive);
        int newSize = Math.min(endIndexExclusive, array.length) - startIndexInclusive2;
        if (newSize <= 0) {
            return EMPTY_DOUBLE_ARRAY;
        }
        return (double[]) arraycopy(array, startIndexInclusive2, 0, newSize, (Function<Integer, double[]>) new Function() { // from class: org.apache.commons.lang3.ArrayUtils$$ExternalSyntheticLambda11
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ArrayUtils.lambda$subarray$9(((Integer) obj).intValue());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ double[] lambda$subarray$9(int x$0) {
        return new double[x$0];
    }

    public static float[] subarray(float[] array, int startIndexInclusive, int endIndexExclusive) {
        if (array == null) {
            return null;
        }
        int startIndexInclusive2 = max0(startIndexInclusive);
        int newSize = Math.min(endIndexExclusive, array.length) - startIndexInclusive2;
        if (newSize <= 0) {
            return EMPTY_FLOAT_ARRAY;
        }
        return (float[]) arraycopy(array, startIndexInclusive2, 0, newSize, (Function<Integer, float[]>) new Function() { // from class: org.apache.commons.lang3.ArrayUtils$$ExternalSyntheticLambda7
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ArrayUtils.lambda$subarray$10(((Integer) obj).intValue());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ float[] lambda$subarray$10(int x$0) {
        return new float[x$0];
    }

    public static int[] subarray(int[] array, int startIndexInclusive, int endIndexExclusive) {
        if (array == null) {
            return null;
        }
        int startIndexInclusive2 = max0(startIndexInclusive);
        int newSize = Math.min(endIndexExclusive, array.length) - startIndexInclusive2;
        if (newSize <= 0) {
            return EMPTY_INT_ARRAY;
        }
        return (int[]) arraycopy(array, startIndexInclusive2, 0, newSize, (Function<Integer, int[]>) new Function() { // from class: org.apache.commons.lang3.ArrayUtils$$ExternalSyntheticLambda9
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ArrayUtils.lambda$subarray$11(((Integer) obj).intValue());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int[] lambda$subarray$11(int x$0) {
        return new int[x$0];
    }

    public static long[] subarray(long[] array, int startIndexInclusive, int endIndexExclusive) {
        if (array == null) {
            return null;
        }
        int startIndexInclusive2 = max0(startIndexInclusive);
        int newSize = Math.min(endIndexExclusive, array.length) - startIndexInclusive2;
        if (newSize <= 0) {
            return EMPTY_LONG_ARRAY;
        }
        return (long[]) arraycopy(array, startIndexInclusive2, 0, newSize, (Function<Integer, long[]>) new Function() { // from class: org.apache.commons.lang3.ArrayUtils$$ExternalSyntheticLambda4
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ArrayUtils.lambda$subarray$12(((Integer) obj).intValue());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ long[] lambda$subarray$12(int x$0) {
        return new long[x$0];
    }

    public static short[] subarray(short[] array, int startIndexInclusive, int endIndexExclusive) {
        if (array == null) {
            return null;
        }
        int startIndexInclusive2 = max0(startIndexInclusive);
        int newSize = Math.min(endIndexExclusive, array.length) - startIndexInclusive2;
        if (newSize <= 0) {
            return EMPTY_SHORT_ARRAY;
        }
        return (short[]) arraycopy(array, startIndexInclusive2, 0, newSize, (Function<Integer, short[]>) new Function() { // from class: org.apache.commons.lang3.ArrayUtils$$ExternalSyntheticLambda20
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ArrayUtils.lambda$subarray$13(((Integer) obj).intValue());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ short[] lambda$subarray$13(int x$0) {
        return new short[x$0];
    }

    public static <T> T[] subarray(T[] tArr, int i, int i2) {
        if (tArr == null) {
            return null;
        }
        int max0 = max0(i);
        final int min = Math.min(i2, tArr.length) - max0;
        final Class componentType = getComponentType(tArr);
        if (min > 0) {
            return (T[]) ((Object[]) arraycopy(tArr, max0, 0, min, (Supplier<T[]>) new Supplier() { // from class: org.apache.commons.lang3.ArrayUtils$$ExternalSyntheticLambda2
                @Override // java.util.function.Supplier
                public final Object get() {
                    Object[] newInstance;
                    newInstance = ArrayUtils.newInstance(componentType, min);
                    return newInstance;
                }
            }));
        }
        return (T[]) newInstance(componentType, 0);
    }

    public static void swap(boolean[] array, int offset1, int offset2) {
        swap(array, offset1, offset2, 1);
    }

    public static void swap(boolean[] array, int offset1, int offset2, int len) {
        if (isEmpty(array) || offset1 >= array.length || offset2 >= array.length) {
            return;
        }
        int offset12 = max0(offset1);
        int offset22 = max0(offset2);
        int len2 = Math.min(Math.min(len, array.length - offset12), array.length - offset22);
        int i = 0;
        while (i < len2) {
            boolean aux = array[offset12];
            array[offset12] = array[offset22];
            array[offset22] = aux;
            i++;
            offset12++;
            offset22++;
        }
    }

    public static void swap(byte[] array, int offset1, int offset2) {
        swap(array, offset1, offset2, 1);
    }

    public static void swap(byte[] array, int offset1, int offset2, int len) {
        if (isEmpty(array) || offset1 >= array.length || offset2 >= array.length) {
            return;
        }
        int offset12 = max0(offset1);
        int offset22 = max0(offset2);
        int len2 = Math.min(Math.min(len, array.length - offset12), array.length - offset22);
        int i = 0;
        while (i < len2) {
            byte aux = array[offset12];
            array[offset12] = array[offset22];
            array[offset22] = aux;
            i++;
            offset12++;
            offset22++;
        }
    }

    public static void swap(char[] array, int offset1, int offset2) {
        swap(array, offset1, offset2, 1);
    }

    public static void swap(char[] array, int offset1, int offset2, int len) {
        if (isEmpty(array) || offset1 >= array.length || offset2 >= array.length) {
            return;
        }
        int offset12 = max0(offset1);
        int offset22 = max0(offset2);
        int len2 = Math.min(Math.min(len, array.length - offset12), array.length - offset22);
        int i = 0;
        while (i < len2) {
            char aux = array[offset12];
            array[offset12] = array[offset22];
            array[offset22] = aux;
            i++;
            offset12++;
            offset22++;
        }
    }

    public static void swap(double[] array, int offset1, int offset2) {
        swap(array, offset1, offset2, 1);
    }

    public static void swap(double[] array, int offset1, int offset2, int len) {
        if (isEmpty(array) || offset1 >= array.length || offset2 >= array.length) {
            return;
        }
        int offset12 = max0(offset1);
        int offset22 = max0(offset2);
        int len2 = Math.min(Math.min(len, array.length - offset12), array.length - offset22);
        int i = 0;
        while (i < len2) {
            double aux = array[offset12];
            array[offset12] = array[offset22];
            array[offset22] = aux;
            i++;
            offset12++;
            offset22++;
        }
    }

    public static void swap(float[] array, int offset1, int offset2) {
        swap(array, offset1, offset2, 1);
    }

    public static void swap(float[] array, int offset1, int offset2, int len) {
        if (isEmpty(array) || offset1 >= array.length || offset2 >= array.length) {
            return;
        }
        int offset12 = max0(offset1);
        int offset22 = max0(offset2);
        int len2 = Math.min(Math.min(len, array.length - offset12), array.length - offset22);
        int i = 0;
        while (i < len2) {
            float aux = array[offset12];
            array[offset12] = array[offset22];
            array[offset22] = aux;
            i++;
            offset12++;
            offset22++;
        }
    }

    public static void swap(int[] array, int offset1, int offset2) {
        swap(array, offset1, offset2, 1);
    }

    public static void swap(int[] array, int offset1, int offset2, int len) {
        if (isEmpty(array) || offset1 >= array.length || offset2 >= array.length) {
            return;
        }
        int offset12 = max0(offset1);
        int offset22 = max0(offset2);
        int len2 = Math.min(Math.min(len, array.length - offset12), array.length - offset22);
        int i = 0;
        while (i < len2) {
            int aux = array[offset12];
            array[offset12] = array[offset22];
            array[offset22] = aux;
            i++;
            offset12++;
            offset22++;
        }
    }

    public static void swap(long[] array, int offset1, int offset2) {
        swap(array, offset1, offset2, 1);
    }

    public static void swap(long[] array, int offset1, int offset2, int len) {
        if (isEmpty(array) || offset1 >= array.length || offset2 >= array.length) {
            return;
        }
        int offset12 = max0(offset1);
        int offset22 = max0(offset2);
        int len2 = Math.min(Math.min(len, array.length - offset12), array.length - offset22);
        int i = 0;
        while (i < len2) {
            long aux = array[offset12];
            array[offset12] = array[offset22];
            array[offset22] = aux;
            i++;
            offset12++;
            offset22++;
        }
    }

    public static void swap(Object[] array, int offset1, int offset2) {
        swap(array, offset1, offset2, 1);
    }

    public static void swap(Object[] array, int offset1, int offset2, int len) {
        if (isEmpty(array) || offset1 >= array.length || offset2 >= array.length) {
            return;
        }
        int offset12 = max0(offset1);
        int offset22 = max0(offset2);
        int len2 = Math.min(Math.min(len, array.length - offset12), array.length - offset22);
        int i = 0;
        while (i < len2) {
            Object aux = array[offset12];
            array[offset12] = array[offset22];
            array[offset22] = aux;
            i++;
            offset12++;
            offset22++;
        }
    }

    public static void swap(short[] array, int offset1, int offset2) {
        swap(array, offset1, offset2, 1);
    }

    public static void swap(short[] array, int offset1, int offset2, int len) {
        int offset12;
        int offset22;
        if (isEmpty(array) || offset1 >= array.length || offset2 >= array.length || (offset12 = max0(offset1)) == (offset22 = max0(offset2))) {
            return;
        }
        int len2 = Math.min(Math.min(len, array.length - offset12), array.length - offset22);
        int i = 0;
        while (i < len2) {
            short aux = array[offset12];
            array[offset12] = array[offset22];
            array[offset22] = aux;
            i++;
            offset12++;
            offset22++;
        }
    }

    public static <T> T[] toArray(T... items) {
        return items;
    }

    public static Map<Object, Object> toMap(Object[] array) {
        if (array == null) {
            return null;
        }
        Map<Object, Object> map = new HashMap<>((int) (array.length * 1.5d));
        for (int i = 0; i < array.length; i++) {
            Object object = array[i];
            if (object instanceof Map.Entry) {
                Map.Entry<?, ?> entry = (Map.Entry) object;
                map.put(entry.getKey(), entry.getValue());
            } else if (object instanceof Object[]) {
                Object[] entry2 = (Object[]) object;
                if (entry2.length < 2) {
                    throw new IllegalArgumentException("Array element " + i + ", '" + object + "', has a length less than 2");
                }
                map.put(entry2[0], entry2[1]);
            } else {
                throw new IllegalArgumentException("Array element " + i + ", '" + object + "', is neither of type Map.Entry nor an Array");
            }
        }
        return map;
    }

    public static Boolean[] toObject(final boolean[] array) {
        if (array == null) {
            return null;
        }
        if (array.length == 0) {
            return EMPTY_BOOLEAN_OBJECT_ARRAY;
        }
        return (Boolean[]) setAll(new Boolean[array.length], new IntFunction() { // from class: org.apache.commons.lang3.ArrayUtils$$ExternalSyntheticLambda13
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return ArrayUtils.lambda$toObject$15(array, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Boolean lambda$toObject$15(boolean[] array, int i) {
        return array[i] ? Boolean.TRUE : Boolean.FALSE;
    }

    public static Byte[] toObject(final byte[] array) {
        if (array == null) {
            return null;
        }
        if (array.length == 0) {
            return EMPTY_BYTE_OBJECT_ARRAY;
        }
        return (Byte[]) setAll(new Byte[array.length], new IntFunction() { // from class: org.apache.commons.lang3.ArrayUtils$$ExternalSyntheticLambda23
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                Byte valueOf;
                valueOf = Byte.valueOf(array[i]);
                return valueOf;
            }
        });
    }

    public static Character[] toObject(final char[] array) {
        if (array == null) {
            return null;
        }
        if (array.length == 0) {
            return EMPTY_CHARACTER_OBJECT_ARRAY;
        }
        return (Character[]) setAll(new Character[array.length], new IntFunction() { // from class: org.apache.commons.lang3.ArrayUtils$$ExternalSyntheticLambda6
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                Character valueOf;
                valueOf = Character.valueOf(array[i]);
                return valueOf;
            }
        });
    }

    public static Double[] toObject(final double[] array) {
        if (array == null) {
            return null;
        }
        if (array.length == 0) {
            return EMPTY_DOUBLE_OBJECT_ARRAY;
        }
        return (Double[]) setAll(new Double[array.length], new IntFunction() { // from class: org.apache.commons.lang3.ArrayUtils$$ExternalSyntheticLambda14
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                Double valueOf;
                valueOf = Double.valueOf(array[i]);
                return valueOf;
            }
        });
    }

    public static Float[] toObject(final float[] array) {
        if (array == null) {
            return null;
        }
        if (array.length == 0) {
            return EMPTY_FLOAT_OBJECT_ARRAY;
        }
        return (Float[]) setAll(new Float[array.length], new IntFunction() { // from class: org.apache.commons.lang3.ArrayUtils$$ExternalSyntheticLambda5
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                Float valueOf;
                valueOf = Float.valueOf(array[i]);
                return valueOf;
            }
        });
    }

    public static Integer[] toObject(final int[] array) {
        if (array == null) {
            return null;
        }
        if (array.length == 0) {
            return EMPTY_INTEGER_OBJECT_ARRAY;
        }
        return (Integer[]) setAll(new Integer[array.length], new IntFunction() { // from class: org.apache.commons.lang3.ArrayUtils$$ExternalSyntheticLambda12
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                Integer valueOf;
                valueOf = Integer.valueOf(array[i]);
                return valueOf;
            }
        });
    }

    public static Long[] toObject(final long[] array) {
        if (array == null) {
            return null;
        }
        if (array.length == 0) {
            return EMPTY_LONG_OBJECT_ARRAY;
        }
        return (Long[]) setAll(new Long[array.length], new IntFunction() { // from class: org.apache.commons.lang3.ArrayUtils$$ExternalSyntheticLambda17
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                Long valueOf;
                valueOf = Long.valueOf(array[i]);
                return valueOf;
            }
        });
    }

    public static Short[] toObject(final short[] array) {
        if (array == null) {
            return null;
        }
        if (array.length == 0) {
            return EMPTY_SHORT_OBJECT_ARRAY;
        }
        return (Short[]) setAll(new Short[array.length], new IntFunction() { // from class: org.apache.commons.lang3.ArrayUtils$$ExternalSyntheticLambda21
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                Short valueOf;
                valueOf = Short.valueOf(array[i]);
                return valueOf;
            }
        });
    }

    public static boolean[] toPrimitive(Boolean[] array) {
        return toPrimitive(array, false);
    }

    public static boolean[] toPrimitive(Boolean[] array, boolean valueForNull) {
        if (array == null) {
            return null;
        }
        if (array.length == 0) {
            return EMPTY_BOOLEAN_ARRAY;
        }
        boolean[] result = new boolean[array.length];
        for (int i = 0; i < array.length; i++) {
            Boolean b = array[i];
            result[i] = b == null ? valueForNull : b.booleanValue();
        }
        return result;
    }

    public static byte[] toPrimitive(Byte[] array) {
        if (array == null) {
            return null;
        }
        if (array.length == 0) {
            return EMPTY_BYTE_ARRAY;
        }
        byte[] result = new byte[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i].byteValue();
        }
        return result;
    }

    public static byte[] toPrimitive(Byte[] array, byte valueForNull) {
        if (array == null) {
            return null;
        }
        if (array.length == 0) {
            return EMPTY_BYTE_ARRAY;
        }
        byte[] result = new byte[array.length];
        for (int i = 0; i < array.length; i++) {
            Byte b = array[i];
            result[i] = b == null ? valueForNull : b.byteValue();
        }
        return result;
    }

    public static char[] toPrimitive(Character[] array) {
        if (array == null) {
            return null;
        }
        if (array.length == 0) {
            return EMPTY_CHAR_ARRAY;
        }
        char[] result = new char[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i].charValue();
        }
        return result;
    }

    public static char[] toPrimitive(Character[] array, char valueForNull) {
        if (array == null) {
            return null;
        }
        if (array.length == 0) {
            return EMPTY_CHAR_ARRAY;
        }
        char[] result = new char[array.length];
        for (int i = 0; i < array.length; i++) {
            Character b = array[i];
            result[i] = b == null ? valueForNull : b.charValue();
        }
        return result;
    }

    public static double[] toPrimitive(Double[] array) {
        if (array == null) {
            return null;
        }
        if (array.length == 0) {
            return EMPTY_DOUBLE_ARRAY;
        }
        double[] result = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i].doubleValue();
        }
        return result;
    }

    public static double[] toPrimitive(Double[] array, double valueForNull) {
        if (array == null) {
            return null;
        }
        if (array.length == 0) {
            return EMPTY_DOUBLE_ARRAY;
        }
        double[] result = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            Double b = array[i];
            result[i] = b == null ? valueForNull : b.doubleValue();
        }
        return result;
    }

    public static float[] toPrimitive(Float[] array) {
        if (array == null) {
            return null;
        }
        if (array.length == 0) {
            return EMPTY_FLOAT_ARRAY;
        }
        float[] result = new float[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i].floatValue();
        }
        return result;
    }

    public static float[] toPrimitive(Float[] array, float valueForNull) {
        if (array == null) {
            return null;
        }
        if (array.length == 0) {
            return EMPTY_FLOAT_ARRAY;
        }
        float[] result = new float[array.length];
        for (int i = 0; i < array.length; i++) {
            Float b = array[i];
            result[i] = b == null ? valueForNull : b.floatValue();
        }
        return result;
    }

    public static int[] toPrimitive(Integer[] array) {
        if (array == null) {
            return null;
        }
        if (array.length == 0) {
            return EMPTY_INT_ARRAY;
        }
        int[] result = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i].intValue();
        }
        return result;
    }

    public static int[] toPrimitive(Integer[] array, int valueForNull) {
        if (array == null) {
            return null;
        }
        if (array.length == 0) {
            return EMPTY_INT_ARRAY;
        }
        int[] result = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            Integer b = array[i];
            result[i] = b == null ? valueForNull : b.intValue();
        }
        return result;
    }

    public static long[] toPrimitive(Long[] array) {
        if (array == null) {
            return null;
        }
        if (array.length == 0) {
            return EMPTY_LONG_ARRAY;
        }
        long[] result = new long[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i].longValue();
        }
        return result;
    }

    public static long[] toPrimitive(Long[] array, long valueForNull) {
        if (array == null) {
            return null;
        }
        if (array.length == 0) {
            return EMPTY_LONG_ARRAY;
        }
        long[] result = new long[array.length];
        for (int i = 0; i < array.length; i++) {
            Long b = array[i];
            result[i] = b == null ? valueForNull : b.longValue();
        }
        return result;
    }

    public static Object toPrimitive(Object array) {
        if (array == null) {
            return null;
        }
        Class<?> ct = array.getClass().getComponentType();
        Class<?> pt = ClassUtils.wrapperToPrimitive(ct);
        if (Boolean.TYPE.equals(pt)) {
            return toPrimitive((Boolean[]) array);
        }
        if (Character.TYPE.equals(pt)) {
            return toPrimitive((Character[]) array);
        }
        if (Byte.TYPE.equals(pt)) {
            return toPrimitive((Byte[]) array);
        }
        if (Integer.TYPE.equals(pt)) {
            return toPrimitive((Integer[]) array);
        }
        if (Long.TYPE.equals(pt)) {
            return toPrimitive((Long[]) array);
        }
        if (Short.TYPE.equals(pt)) {
            return toPrimitive((Short[]) array);
        }
        if (Double.TYPE.equals(pt)) {
            return toPrimitive((Double[]) array);
        }
        if (Float.TYPE.equals(pt)) {
            return toPrimitive((Float[]) array);
        }
        return array;
    }

    public static short[] toPrimitive(Short[] array) {
        if (array == null) {
            return null;
        }
        if (array.length == 0) {
            return EMPTY_SHORT_ARRAY;
        }
        short[] result = new short[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i].shortValue();
        }
        return result;
    }

    public static short[] toPrimitive(Short[] array, short valueForNull) {
        if (array == null) {
            return null;
        }
        if (array.length == 0) {
            return EMPTY_SHORT_ARRAY;
        }
        short[] result = new short[array.length];
        for (int i = 0; i < array.length; i++) {
            Short b = array[i];
            result[i] = b == null ? valueForNull : b.shortValue();
        }
        return result;
    }

    public static String toString(Object array) {
        return toString(array, "{}");
    }

    public static String toString(Object array, String stringIfNull) {
        if (array == null) {
            return stringIfNull;
        }
        return new ToStringBuilder(array, ToStringStyle.SIMPLE_STYLE).append(array).toString();
    }

    public static String[] toStringArray(Object[] array) {
        return toStringArray(array, "null");
    }

    public static String[] toStringArray(Object[] array, final String valueForNullElements) {
        if (array == null) {
            return null;
        }
        if (array.length == 0) {
            return EMPTY_STRING_ARRAY;
        }
        return (String[]) map(array, String.class, new FailableFunction() { // from class: org.apache.commons.lang3.ArrayUtils$$ExternalSyntheticLambda10
            @Override // org.apache.commons.lang3.function.FailableFunction
            public final Object apply(Object obj) {
                String objects;
                objects = Objects.toString(obj, valueForNullElements);
                return objects;
            }
        });
    }

    @Deprecated
    public ArrayUtils() {
    }
}
