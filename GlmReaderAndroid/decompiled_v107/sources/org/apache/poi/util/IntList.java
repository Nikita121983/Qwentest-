package org.apache.poi.util;

/* loaded from: classes10.dex */
public class IntList {
    private static final int _default_size = 128;
    private int[] _array;
    private int _limit;

    public IntList() {
        this(128);
    }

    public IntList(int initialCapacity) {
        this._array = new int[initialCapacity];
        this._limit = 0;
    }

    public IntList(IntList list) {
        this(list._array.length);
        System.arraycopy(list._array, 0, this._array, 0, this._array.length);
        this._limit = list._limit;
    }

    public void add(int index, int value) {
        if (index > this._limit) {
            throw new IndexOutOfBoundsException();
        }
        if (index == this._limit) {
            add(value);
            return;
        }
        if (this._limit == this._array.length) {
            growArray(this._limit * 2);
        }
        System.arraycopy(this._array, index, this._array, index + 1, this._limit - index);
        this._array[index] = value;
        this._limit++;
    }

    public boolean add(int value) {
        if (this._limit == this._array.length) {
            growArray(this._limit * 2);
        }
        int[] iArr = this._array;
        int i = this._limit;
        this._limit = i + 1;
        iArr[i] = value;
        return true;
    }

    public boolean addAll(IntList c) {
        if (c._limit != 0) {
            if (this._limit + c._limit > this._array.length) {
                growArray(this._limit + c._limit);
            }
            System.arraycopy(c._array, 0, this._array, this._limit, c._limit);
            this._limit += c._limit;
            return true;
        }
        return true;
    }

    public boolean addAll(int index, IntList c) {
        if (index > this._limit) {
            throw new IndexOutOfBoundsException();
        }
        if (c._limit != 0) {
            if (this._limit + c._limit > this._array.length) {
                growArray(this._limit + c._limit);
            }
            System.arraycopy(this._array, index, this._array, c._limit + index, this._limit - index);
            System.arraycopy(c._array, 0, this._array, index, c._limit);
            this._limit += c._limit;
            return true;
        }
        return true;
    }

    public void clear() {
        this._limit = 0;
    }

    public boolean contains(int o) {
        for (int j = 0; j < this._limit; j++) {
            if (this._array[j] == o) {
                return true;
            }
        }
        return false;
    }

    public boolean containsAll(IntList c) {
        if (this != c) {
            for (int j = 0; j < c._limit; j++) {
                if (!contains(c._array[j])) {
                    return false;
                }
            }
            return true;
        }
        return true;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof IntList)) {
            return false;
        }
        IntList other = (IntList) o;
        if (other._limit != this._limit) {
            return false;
        }
        for (int i = 0; i < this._limit; i++) {
            if (other._array[i] != this._array[i]) {
                return false;
            }
        }
        return true;
    }

    public int get(int index) {
        if (index >= this._limit) {
            throw new IndexOutOfBoundsException(index + " not accessible in a list of length " + this._limit);
        }
        return this._array[index];
    }

    public int hashCode() {
        int hash = 0;
        for (int j = 0; j < this._limit; j++) {
            hash = (hash * 31) + this._array[j];
        }
        return hash;
    }

    public int indexOf(int o) {
        for (int i = 0; i < this._limit; i++) {
            if (this._array[i] == o) {
                return i;
            }
        }
        return -1;
    }

    public boolean isEmpty() {
        return this._limit == 0;
    }

    public int lastIndexOf(int o) {
        for (int i = this._limit - 1; i >= 0; i--) {
            if (this._array[i] == o) {
                return i;
            }
        }
        return -1;
    }

    public int remove(int index) {
        if (index >= this._limit) {
            throw new IndexOutOfBoundsException();
        }
        int rval = this._array[index];
        if (this._limit == this._array.length) {
            growArray(this._limit + 1);
        }
        System.arraycopy(this._array, index + 1, this._array, index, this._limit - index);
        this._limit--;
        return rval;
    }

    public boolean removeValue(int o) {
        for (int j = 0; j < this._limit; j++) {
            if (o == this._array[j]) {
                if (j + 1 < this._limit) {
                    if (this._limit == this._array.length) {
                        growArray(this._limit + 1);
                    }
                    System.arraycopy(this._array, j + 1, this._array, j, this._limit - j);
                }
                this._limit--;
                return true;
            }
        }
        return false;
    }

    public boolean removeAll(IntList c) {
        boolean rval = false;
        for (int j = 0; j < c._limit; j++) {
            if (removeValue(c._array[j])) {
                rval = true;
            }
        }
        return rval;
    }

    public boolean retainAll(IntList c) {
        boolean rval = false;
        int j = 0;
        while (j < this._limit) {
            if (!c.contains(this._array[j])) {
                remove(j);
                rval = true;
            } else {
                j++;
            }
        }
        return rval;
    }

    public int set(int index, int element) {
        if (index >= this._limit) {
            throw new IndexOutOfBoundsException();
        }
        int rval = this._array[index];
        this._array[index] = element;
        return rval;
    }

    public int size() {
        return this._limit;
    }

    public int[] toArray() {
        int[] rval = new int[this._limit];
        System.arraycopy(this._array, 0, rval, 0, this._limit);
        return rval;
    }

    public int[] toArray(int[] a) {
        if (a.length == this._limit) {
            System.arraycopy(this._array, 0, a, 0, this._limit);
            return a;
        }
        int[] rval = toArray();
        return rval;
    }

    private void growArray(int new_size) {
        int size = new_size == this._array.length ? new_size + 1 : new_size;
        int[] new_array = new int[size];
        System.arraycopy(this._array, 0, new_array, 0, this._limit);
        this._array = new_array;
    }
}
