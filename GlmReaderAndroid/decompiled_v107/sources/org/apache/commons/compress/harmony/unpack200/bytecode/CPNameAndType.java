package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Objects;
import org.apache.commons.compress.harmony.unpack200.SegmentUtils;

/* loaded from: classes9.dex */
public class CPNameAndType extends ConstantPoolEntry {
    private int cachedHashCode;
    CPUTF8 descriptor;
    transient int descriptorIndex;
    private boolean hashCodeComputed;
    CPUTF8 name;
    transient int nameIndex;

    public CPNameAndType(CPUTF8 name, CPUTF8 descriptor, int globalIndex) {
        super((byte) 12, globalIndex);
        this.name = (CPUTF8) Objects.requireNonNull(name, "name");
        this.descriptor = (CPUTF8) Objects.requireNonNull(descriptor, "descriptor");
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ConstantPoolEntry, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CPNameAndType other = (CPNameAndType) obj;
        if (Objects.equals(this.descriptor, other.descriptor) && Objects.equals(this.name, other.name)) {
            return true;
        }
        return false;
    }

    private void generateHashCode() {
        this.hashCodeComputed = true;
        int result = (1 * 31) + this.descriptor.hashCode();
        this.cachedHashCode = (result * 31) + this.name.hashCode();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public ClassFileEntry[] getNestedClassFileEntries() {
        return new ClassFileEntry[]{this.name, this.descriptor};
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ConstantPoolEntry, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public int hashCode() {
        if (!this.hashCodeComputed) {
            generateHashCode();
        }
        return this.cachedHashCode;
    }

    public int invokeInterfaceCount() {
        return SegmentUtils.countInvokeInterfaceArgs(this.descriptor.underlyingString()) + 1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public void resolve(ClassConstantPool pool) {
        super.resolve(pool);
        this.descriptorIndex = pool.indexOf(this.descriptor);
        this.nameIndex = pool.indexOf(this.name);
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public String toString() {
        return "NameAndType: " + this.name + "(" + this.descriptor + ")";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ConstantPoolEntry
    public void writeBody(DataOutputStream dos) throws IOException {
        dos.writeShort(this.nameIndex);
        dos.writeShort(this.descriptorIndex);
    }
}
