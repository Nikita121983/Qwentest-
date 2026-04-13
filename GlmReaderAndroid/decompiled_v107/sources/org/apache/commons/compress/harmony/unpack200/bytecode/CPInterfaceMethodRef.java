package org.apache.commons.compress.harmony.unpack200.bytecode;

/* loaded from: classes9.dex */
public class CPInterfaceMethodRef extends CPRef {
    private int cachedHashCode;
    private boolean hashCodeComputed;

    public CPInterfaceMethodRef(CPClass className, CPNameAndType descriptor, int globalIndex) {
        super((byte) 11, className, descriptor, globalIndex);
    }

    private void generateHashCode() {
        this.hashCodeComputed = true;
        int result = (1 * 31) + this.className.hashCode();
        this.cachedHashCode = (result * 31) + this.nameAndType.hashCode();
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ConstantPoolEntry, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public int hashCode() {
        if (!this.hashCodeComputed) {
            generateHashCode();
        }
        return this.cachedHashCode;
    }

    public int invokeInterfaceCount() {
        return this.nameAndType.invokeInterfaceCount();
    }
}
