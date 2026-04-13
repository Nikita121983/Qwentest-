package org.apache.commons.math3.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;

/* loaded from: classes10.dex */
public class OpenIntToFieldHashMap<T extends FieldElement<T>> implements Serializable {
    private static final int DEFAULT_EXPECTED_SIZE = 16;
    protected static final byte FREE = 0;
    protected static final byte FULL = 1;
    private static final float LOAD_FACTOR = 0.5f;
    private static final int PERTURB_SHIFT = 5;
    protected static final byte REMOVED = 2;
    private static final int RESIZE_MULTIPLIER = 2;
    private static final long serialVersionUID = -9179080286849120720L;
    private transient int count;
    private final Field<T> field;
    private int[] keys;
    private int mask;
    private final T missingEntries;
    private int size;
    private byte[] states;
    private T[] values;

    public OpenIntToFieldHashMap(Field<T> field) {
        this(field, 16, field.getZero());
    }

    public OpenIntToFieldHashMap(Field<T> field, T missingEntries) {
        this(field, 16, missingEntries);
    }

    public OpenIntToFieldHashMap(Field<T> field, int expectedSize) {
        this(field, expectedSize, field.getZero());
    }

    public OpenIntToFieldHashMap(Field<T> field, int expectedSize, T missingEntries) {
        this.field = field;
        int capacity = computeCapacity(expectedSize);
        this.keys = new int[capacity];
        this.values = buildArray(capacity);
        this.states = new byte[capacity];
        this.missingEntries = missingEntries;
        this.mask = capacity - 1;
    }

    public OpenIntToFieldHashMap(OpenIntToFieldHashMap<T> source) {
        this.field = source.field;
        int length = source.keys.length;
        this.keys = new int[length];
        System.arraycopy(source.keys, 0, this.keys, 0, length);
        this.values = buildArray(length);
        System.arraycopy(source.values, 0, this.values, 0, length);
        this.states = new byte[length];
        System.arraycopy(source.states, 0, this.states, 0, length);
        this.missingEntries = source.missingEntries;
        this.size = source.size;
        this.mask = source.mask;
        this.count = source.count;
    }

    private static int computeCapacity(int expectedSize) {
        if (expectedSize == 0) {
            return 1;
        }
        int capacity = (int) FastMath.ceil(expectedSize / 0.5f);
        int powerOfTwo = Integer.highestOneBit(capacity);
        if (powerOfTwo == capacity) {
            return capacity;
        }
        return nextPowerOfTwo(capacity);
    }

    private static int nextPowerOfTwo(int i) {
        return Integer.highestOneBit(i) << 1;
    }

    public T get(int key) {
        int hash = hashOf(key);
        int index = this.mask & hash;
        if (containsKey(key, index)) {
            return this.values[index];
        }
        if (this.states[index] == 0) {
            return this.missingEntries;
        }
        int j = index;
        int perturb = perturb(hash);
        while (this.states[index] != 0) {
            j = probe(perturb, j);
            index = j & this.mask;
            if (!containsKey(key, index)) {
                perturb >>= 5;
            } else {
                return this.values[index];
            }
        }
        return this.missingEntries;
    }

    public boolean containsKey(int key) {
        int hash = hashOf(key);
        int index = this.mask & hash;
        if (containsKey(key, index)) {
            return true;
        }
        if (this.states[index] == 0) {
            return false;
        }
        int j = index;
        int perturb = perturb(hash);
        while (this.states[index] != 0) {
            j = probe(perturb, j);
            index = j & this.mask;
            if (containsKey(key, index)) {
                return true;
            }
            perturb >>= 5;
        }
        return false;
    }

    public OpenIntToFieldHashMap<T>.Iterator iterator() {
        return new Iterator();
    }

    private static int perturb(int hash) {
        return Integer.MAX_VALUE & hash;
    }

    private int findInsertionIndex(int key) {
        return findInsertionIndex(this.keys, this.states, key, this.mask);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0020, code lost:
    
        if (r8[r1] == 1) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0022, code lost:
    
        r4 = probe(r2, r4);
        r1 = r4 & r10;
        r2 = r2 >> 5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x002c, code lost:
    
        if (r8[r1] != 1) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0030, code lost:
    
        if (r7[r1] != r9) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0035, code lost:
    
        if (r8[r1] != 0) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0037, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x003a, code lost:
    
        if (r8[r1] != 1) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0040, code lost:
    
        return changeIndexSign(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0041, code lost:
    
        r5 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0042, code lost:
    
        r4 = probe(r2, r4);
        r1 = r4 & r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x004a, code lost:
    
        if (r8[r1] != 0) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x004f, code lost:
    
        if (r8[r1] != 1) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0053, code lost:
    
        if (r7[r1] != r9) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0059, code lost:
    
        return changeIndexSign(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x005a, code lost:
    
        r2 = r2 >> 5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x004c, code lost:
    
        return r5;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int findInsertionIndex(int[] r7, byte[] r8, int r9, int r10) {
        /*
            int r0 = hashOf(r9)
            r1 = r0 & r10
            r2 = r8[r1]
            if (r2 != 0) goto Lb
            return r1
        Lb:
            r2 = r8[r1]
            r3 = 1
            if (r2 != r3) goto L19
            r2 = r7[r1]
            if (r2 != r9) goto L19
            int r2 = changeIndexSign(r1)
            return r2
        L19:
            int r2 = perturb(r0)
            r4 = r1
            r5 = r8[r1]
            if (r5 != r3) goto L33
        L22:
            int r4 = probe(r2, r4)
            r1 = r4 & r10
            int r2 = r2 >> 5
            r5 = r8[r1]
            if (r5 != r3) goto L33
            r5 = r7[r1]
            if (r5 != r9) goto L22
        L33:
            r5 = r8[r1]
            if (r5 != 0) goto L38
            return r1
        L38:
            r5 = r8[r1]
            if (r5 != r3) goto L41
            int r3 = changeIndexSign(r1)
            return r3
        L41:
            r5 = r1
        L42:
            int r4 = probe(r2, r4)
            r1 = r4 & r10
            r6 = r8[r1]
            if (r6 != 0) goto L4d
            return r5
        L4d:
            r6 = r8[r1]
            if (r6 != r3) goto L5a
            r6 = r7[r1]
            if (r6 != r9) goto L5a
            int r3 = changeIndexSign(r1)
            return r3
        L5a:
            int r2 = r2 >> 5
            goto L42
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.util.OpenIntToFieldHashMap.findInsertionIndex(int[], byte[], int, int):int");
    }

    private static int probe(int perturb, int j) {
        return (j << 2) + j + perturb + 1;
    }

    private static int changeIndexSign(int index) {
        return (-index) - 1;
    }

    public int size() {
        return this.size;
    }

    public T remove(int key) {
        int hash = hashOf(key);
        int index = this.mask & hash;
        if (containsKey(key, index)) {
            return doRemove(index);
        }
        if (this.states[index] == 0) {
            return this.missingEntries;
        }
        int j = index;
        int perturb = perturb(hash);
        while (this.states[index] != 0) {
            j = probe(perturb, j);
            index = j & this.mask;
            if (!containsKey(key, index)) {
                perturb >>= 5;
            } else {
                return doRemove(index);
            }
        }
        return this.missingEntries;
    }

    private boolean containsKey(int key, int index) {
        return (key != 0 || this.states[index] == 1) && this.keys[index] == key;
    }

    private T doRemove(int index) {
        this.keys[index] = 0;
        this.states[index] = 2;
        T previous = this.values[index];
        this.values[index] = this.missingEntries;
        this.size--;
        this.count++;
        return previous;
    }

    public T put(int key, T value) {
        int index = findInsertionIndex(key);
        T previous = this.missingEntries;
        boolean newMapping = true;
        if (index < 0) {
            index = changeIndexSign(index);
            previous = this.values[index];
            newMapping = false;
        }
        this.keys[index] = key;
        this.states[index] = 1;
        this.values[index] = value;
        if (newMapping) {
            this.size++;
            if (shouldGrowTable()) {
                growTable();
            }
            this.count++;
        }
        return previous;
    }

    private void growTable() {
        int oldLength = this.states.length;
        int[] oldKeys = this.keys;
        T[] oldValues = this.values;
        byte[] oldStates = this.states;
        int newLength = oldLength * 2;
        int[] newKeys = new int[newLength];
        T[] newValues = buildArray(newLength);
        byte[] newStates = new byte[newLength];
        int newMask = newLength - 1;
        for (int i = 0; i < oldLength; i++) {
            if (oldStates[i] == 1) {
                int key = oldKeys[i];
                int index = findInsertionIndex(newKeys, newStates, key, newMask);
                newKeys[index] = key;
                newValues[index] = oldValues[i];
                newStates[index] = 1;
            }
        }
        this.mask = newMask;
        this.keys = newKeys;
        this.values = newValues;
        this.states = newStates;
    }

    private boolean shouldGrowTable() {
        return ((float) this.size) > ((float) (this.mask + 1)) * 0.5f;
    }

    private static int hashOf(int key) {
        int h = ((key >>> 20) ^ (key >>> 12)) ^ key;
        return ((h >>> 7) ^ h) ^ (h >>> 4);
    }

    /* loaded from: classes10.dex */
    public class Iterator {
        private int current;
        private int next;
        private final int referenceCount;

        private Iterator() {
            this.referenceCount = OpenIntToFieldHashMap.this.count;
            this.next = -1;
            try {
                advance();
            } catch (NoSuchElementException e) {
            }
        }

        public boolean hasNext() {
            return this.next >= 0;
        }

        public int key() throws ConcurrentModificationException, NoSuchElementException {
            if (this.referenceCount != OpenIntToFieldHashMap.this.count) {
                throw new ConcurrentModificationException();
            }
            if (this.current >= 0) {
                return OpenIntToFieldHashMap.this.keys[this.current];
            }
            throw new NoSuchElementException();
        }

        public T value() throws ConcurrentModificationException, NoSuchElementException {
            if (this.referenceCount != OpenIntToFieldHashMap.this.count) {
                throw new ConcurrentModificationException();
            }
            if (this.current >= 0) {
                return (T) OpenIntToFieldHashMap.this.values[this.current];
            }
            throw new NoSuchElementException();
        }

        public void advance() throws ConcurrentModificationException, NoSuchElementException {
            byte[] bArr;
            int i;
            if (this.referenceCount != OpenIntToFieldHashMap.this.count) {
                throw new ConcurrentModificationException();
            }
            this.current = this.next;
            do {
                try {
                    bArr = OpenIntToFieldHashMap.this.states;
                    i = this.next + 1;
                    this.next = i;
                } catch (ArrayIndexOutOfBoundsException e) {
                    this.next = -2;
                    if (this.current < 0) {
                        throw new NoSuchElementException();
                    }
                    return;
                }
            } while (bArr[i] != 1);
        }
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.count = 0;
    }

    private T[] buildArray(int i) {
        return (T[]) ((FieldElement[]) Array.newInstance(this.field.getRuntimeClass(), i));
    }
}
