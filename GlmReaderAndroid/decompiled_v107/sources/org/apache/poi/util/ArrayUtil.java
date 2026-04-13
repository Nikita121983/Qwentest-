package org.apache.poi.util;

import java.util.Arrays;

@Internal
/* loaded from: classes10.dex */
public final class ArrayUtil {
    private ArrayUtil() {
    }

    public static void arrayMoveWithin(Object[] array, int moveFrom, int moveTo, int numToMove) {
        Object[] toShift;
        int shiftTo;
        if (numToMove > 0 && moveFrom != moveTo) {
            if (moveFrom < 0 || moveFrom >= array.length) {
                throw new IllegalArgumentException("The moveFrom must be a valid array index");
            }
            if (moveTo < 0 || moveTo >= array.length) {
                throw new IllegalArgumentException("The moveTo must be a valid array index");
            }
            if (moveFrom + numToMove > array.length) {
                throw new IllegalArgumentException("Asked to move more entries than the array has");
            }
            if (moveTo + numToMove > array.length) {
                throw new IllegalArgumentException("Asked to move to a position that doesn't have enough space");
            }
            Object[] toMove = Arrays.copyOfRange(array, moveFrom, moveFrom + numToMove);
            if (moveFrom > moveTo) {
                toShift = Arrays.copyOfRange(array, moveTo, moveFrom);
                shiftTo = moveTo + numToMove;
            } else {
                toShift = Arrays.copyOfRange(array, moveFrom + numToMove, moveTo + numToMove);
                shiftTo = moveFrom;
            }
            System.arraycopy(toMove, 0, array, moveTo, toMove.length);
            System.arraycopy(toShift, 0, array, shiftTo, toShift.length);
        }
    }
}
