package org.apache.commons.compress.harmony.pack200;

/* loaded from: classes9.dex */
public class CPInt extends CPConstant<CPInt> {
    private final int value;

    public CPInt(int value) {
        this.value = value;
    }

    @Override // java.lang.Comparable
    public int compareTo(CPInt obj) {
        return Integer.compare(this.value, obj.value);
    }

    public int getInt() {
        return this.value;
    }
}
