package org.apache.commons.compress.harmony.pack200;

/* loaded from: classes9.dex */
public class CPClass extends CPConstant<CPClass> {
    private final String className;
    private final boolean isInnerClass;
    private final CPUTF8 value;

    public CPClass(CPUTF8 value) {
        this.value = value;
        this.className = value.getUnderlyingString();
        char[] chars = this.className.toCharArray();
        for (char element : chars) {
            if (element <= '-') {
                this.isInnerClass = true;
                return;
            }
        }
        this.isInnerClass = false;
    }

    @Override // java.lang.Comparable
    public int compareTo(CPClass arg0) {
        return this.className.compareTo(arg0.className);
    }

    public int getIndexInCpUtf8() {
        return this.value.getIndex();
    }

    public boolean isInnerClass() {
        return this.isInnerClass;
    }

    public String toString() {
        return this.className;
    }
}
