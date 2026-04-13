package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.util.List;

/* loaded from: classes9.dex */
public class CPMethod extends CPMember {
    private int cachedHashCode;
    private boolean hashCodeComputed;

    public CPMethod(CPUTF8 name, CPUTF8 descriptor, long flags, List<Attribute> attributes) {
        super(name, descriptor, flags, attributes);
    }

    private void generateHashCode() {
        this.hashCodeComputed = true;
        int result = (1 * 31) + this.name.hashCode();
        this.cachedHashCode = (result * 31) + this.descriptor.hashCode();
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.CPMember, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public int hashCode() {
        if (!this.hashCodeComputed) {
            generateHashCode();
        }
        return this.cachedHashCode;
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.CPMember, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public String toString() {
        return "Method: " + this.name + "(" + this.descriptor + ")";
    }
}
