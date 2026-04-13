package org.apache.commons.compress.harmony.pack200;

/* loaded from: classes9.dex */
public class CPLong extends CPConstant<CPLong> {
    private final long value;

    public CPLong(long value) {
        this.value = value;
    }

    @Override // java.lang.Comparable
    public int compareTo(CPLong obj) {
        return Long.compare(this.value, obj.value);
    }

    public long getLong() {
        return this.value;
    }

    public String toString() {
        return "" + this.value;
    }
}
