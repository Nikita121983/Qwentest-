package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Objects;

/* loaded from: classes9.dex */
public abstract class Attribute extends ClassFileEntry {
    protected final CPUTF8 attributeName;
    private int attributeNameIndex;

    protected abstract int getLength();

    protected abstract void writeBody(DataOutputStream dataOutputStream) throws IOException;

    public Attribute(CPUTF8 attributeName) {
        this.attributeName = attributeName;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public void doWrite(DataOutputStream dos) throws IOException {
        dos.writeShort(this.attributeNameIndex);
        dos.writeInt(getLength());
        writeBody(dos);
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Attribute other = (Attribute) obj;
        return Objects.equals(this.attributeName, other.attributeName);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CPUTF8 getAttributeName() {
        return this.attributeName;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getLengthIncludingHeader() {
        return getLength() + 2 + 4;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public ClassFileEntry[] getNestedClassFileEntries() {
        return new ClassFileEntry[]{getAttributeName()};
    }

    public boolean hasBCIRenumbering() {
        return false;
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public int hashCode() {
        return Objects.hash(this.attributeName);
    }

    public boolean isSourceFileAttribute() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public void resolve(ClassConstantPool pool) {
        super.resolve(pool);
        this.attributeNameIndex = pool.indexOf(this.attributeName);
    }
}
