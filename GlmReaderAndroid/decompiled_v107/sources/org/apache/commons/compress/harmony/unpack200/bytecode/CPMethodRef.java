package org.apache.commons.compress.harmony.unpack200.bytecode;

/* loaded from: classes9.dex */
public class CPMethodRef extends CPRef {
    private int cachedHashCode;
    private boolean hashCodeComputed;

    public CPMethodRef(CPClass className, CPNameAndType descriptor, int globalIndex) {
        super((byte) 10, className, descriptor, globalIndex);
    }

    private void generateHashCode() {
        this.hashCodeComputed = true;
        int result = (1 * 31) + this.className.hashCode();
        this.cachedHashCode = (result * 31) + this.nameAndType.hashCode();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.CPRef, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public ClassFileEntry[] getNestedClassFileEntries() {
        return new ClassFileEntry[]{this.className, this.nameAndType};
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ConstantPoolEntry, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public int hashCode() {
        if (!this.hashCodeComputed) {
            generateHashCode();
        }
        return this.cachedHashCode;
    }
}
