package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Objects;

/* loaded from: classes9.dex */
public class CPFieldRef extends ConstantPoolEntry {
    private int cachedHashCode;
    CPClass className;
    transient int classNameIndex;
    private boolean hashCodeComputed;
    private final CPNameAndType nameAndType;
    transient int nameAndTypeIndex;

    public CPFieldRef(CPClass className, CPNameAndType descriptor, int globalIndex) {
        super((byte) 9, globalIndex);
        this.className = className;
        this.nameAndType = descriptor;
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ConstantPoolEntry, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CPFieldRef other = (CPFieldRef) obj;
        if (Objects.equals(this.className, other.className) && Objects.equals(this.nameAndType, other.nameAndType)) {
            return true;
        }
        return false;
    }

    private void generateHashCode() {
        this.hashCodeComputed = true;
        int result = (1 * 31) + (this.className == null ? 0 : this.className.hashCode());
        this.cachedHashCode = (result * 31) + (this.nameAndType != null ? this.nameAndType.hashCode() : 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
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

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public void resolve(ClassConstantPool pool) {
        super.resolve(pool);
        this.nameAndTypeIndex = pool.indexOf(this.nameAndType);
        this.classNameIndex = pool.indexOf(this.className);
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public String toString() {
        return "FieldRef: " + this.className + "#" + this.nameAndType;
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ConstantPoolEntry
    protected void writeBody(DataOutputStream dos) throws IOException {
        dos.writeShort(this.classNameIndex);
        dos.writeShort(this.nameAndTypeIndex);
    }
}
