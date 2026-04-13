package org.apache.commons.collections4;

/* loaded from: classes9.dex */
final class ArrayUtils {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean contains(Object[] array, Object objectToFind) {
        return indexOf(array, objectToFind) != -1;
    }

    static int indexOf(Object[] array, Object objectToFind, int startIndex) {
        if (array == null) {
            return -1;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        if (objectToFind == null) {
            for (int i = startIndex; i < array.length; i++) {
                if (array[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i2 = startIndex; i2 < array.length; i2++) {
                if (objectToFind.equals(array[i2])) {
                    return i2;
                }
            }
        }
        return -1;
    }

    static <T> int indexOf(T[] array, Object objectToFind) {
        return indexOf(array, objectToFind, 0);
    }

    private ArrayUtils() {
    }
}
