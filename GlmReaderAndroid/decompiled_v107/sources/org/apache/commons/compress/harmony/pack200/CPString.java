package org.apache.commons.compress.harmony.pack200;

/* loaded from: classes9.dex */
public class CPString extends CPConstant<CPString> {
    private final String string;
    private final CPUTF8 value;

    public CPString(CPUTF8 value) {
        this.value = value;
        this.string = value.getUnderlyingString();
    }

    @Override // java.lang.Comparable
    public int compareTo(CPString arg0) {
        return this.string.compareTo(arg0.string);
    }

    public int getIndexInCpUtf8() {
        return this.value.getIndex();
    }

    public String toString() {
        return this.string;
    }
}
