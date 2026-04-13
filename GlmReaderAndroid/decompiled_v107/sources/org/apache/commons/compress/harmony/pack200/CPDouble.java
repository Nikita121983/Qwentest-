package org.apache.commons.compress.harmony.pack200;

/* loaded from: classes9.dex */
public class CPDouble extends CPConstant<CPDouble> {
    private final double value;

    public CPDouble(double value) {
        this.value = value;
    }

    @Override // java.lang.Comparable
    public int compareTo(CPDouble obj) {
        return Double.compare(this.value, obj.value);
    }

    public double getDouble() {
        return this.value;
    }
}
