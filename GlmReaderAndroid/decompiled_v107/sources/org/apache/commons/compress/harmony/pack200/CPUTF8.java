package org.apache.commons.compress.harmony.pack200;

/* loaded from: classes9.dex */
public class CPUTF8 extends ConstantPoolEntry implements Comparable {
    private final String string;

    public CPUTF8(String string) {
        this.string = string;
    }

    @Override // java.lang.Comparable
    public int compareTo(Object arg0) {
        return this.string.compareTo(((CPUTF8) arg0).string);
    }

    public String getUnderlyingString() {
        return this.string;
    }

    public String toString() {
        return this.string;
    }
}
