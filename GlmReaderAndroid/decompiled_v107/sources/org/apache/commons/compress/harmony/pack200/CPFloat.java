package org.apache.commons.compress.harmony.pack200;

/* loaded from: classes9.dex */
public class CPFloat extends CPConstant<CPFloat> {
    private final float value;

    public CPFloat(float value) {
        this.value = value;
    }

    @Override // java.lang.Comparable
    public int compareTo(CPFloat obj) {
        return Float.compare(this.value, obj.value);
    }

    public float getFloat() {
        return this.value;
    }
}
