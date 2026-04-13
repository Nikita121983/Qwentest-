package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/* loaded from: classes9.dex */
public class CPUTF8 extends ConstantPoolEntry {
    private int cachedHashCode;
    private boolean hashCodeComputed;
    private final String utf8;

    public CPUTF8(String string) {
        this(string, -1);
    }

    public CPUTF8(String string, int globalIndex) {
        super((byte) 1, globalIndex);
        this.utf8 = (String) Objects.requireNonNull(string, "utf8");
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ConstantPoolEntry, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CPUTF8 other = (CPUTF8) obj;
        return this.utf8.equals(other.utf8);
    }

    private void generateHashCode() {
        this.hashCodeComputed = true;
        this.cachedHashCode = this.utf8.hashCode() + 31;
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ConstantPoolEntry, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public int hashCode() {
        if (!this.hashCodeComputed) {
            generateHashCode();
        }
        return this.cachedHashCode;
    }

    public void setGlobalIndex(int index) {
        this.globalIndex = index;
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public String toString() {
        return StandardCharsets.UTF_8.name() + ":" + this.utf8;
    }

    public String underlyingString() {
        return this.utf8;
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ConstantPoolEntry
    protected void writeBody(DataOutputStream dos) throws IOException {
        dos.writeUTF(this.utf8);
    }
}
