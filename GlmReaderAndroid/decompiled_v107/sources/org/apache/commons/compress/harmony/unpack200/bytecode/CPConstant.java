package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.util.Objects;

/* loaded from: classes9.dex */
public abstract class CPConstant extends ConstantPoolEntry {
    private final Object value;

    public CPConstant(byte tag, Object value, int globalIndex) {
        super(tag, globalIndex);
        this.value = Objects.requireNonNull(value, "value");
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ConstantPoolEntry, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CPConstant other = (CPConstant) obj;
        return Objects.equals(this.value, other.value);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Object getValue() {
        return this.value;
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ConstantPoolEntry, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public int hashCode() {
        return Objects.hash(this.value);
    }
}
