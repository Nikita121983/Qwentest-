package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class InnerClassesAttribute extends Attribute {
    private static CPUTF8 attributeName;
    private final List<InnerClassesEntry> innerClasses;
    private final List<ConstantPoolEntry> nestedClassFileEntries;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class InnerClassesEntry {
        int innerClassAccessFlags;
        CPClass innerClassInfo;
        CPUTF8 innerClassName;
        CPClass outerClassInfo;
        int innerClassInfoIndex = -1;
        int outerClassInfoIndex = -1;
        int innerNameIndex = -1;

        InnerClassesEntry(CPClass innerClass, CPClass outerClass, CPUTF8 innerName, int flags) {
            this.innerClassAccessFlags = -1;
            this.innerClassInfo = innerClass;
            this.outerClassInfo = outerClass;
            this.innerClassName = innerName;
            this.innerClassAccessFlags = flags;
        }

        public void resolve(ClassConstantPool pool) {
            if (this.innerClassInfo != null) {
                this.innerClassInfo.resolve(pool);
                this.innerClassInfoIndex = pool.indexOf(this.innerClassInfo);
            } else {
                this.innerClassInfoIndex = 0;
            }
            if (this.innerClassName != null) {
                this.innerClassName.resolve(pool);
                this.innerNameIndex = pool.indexOf(this.innerClassName);
            } else {
                this.innerNameIndex = 0;
            }
            if (this.outerClassInfo != null) {
                this.outerClassInfo.resolve(pool);
                this.outerClassInfoIndex = pool.indexOf(this.outerClassInfo);
            } else {
                this.outerClassInfoIndex = 0;
            }
        }

        public void write(DataOutputStream dos) throws IOException {
            dos.writeShort(this.innerClassInfoIndex);
            dos.writeShort(this.outerClassInfoIndex);
            dos.writeShort(this.innerNameIndex);
            dos.writeShort(this.innerClassAccessFlags);
        }
    }

    public static void setAttributeName(CPUTF8 cpUTF8Value) {
        attributeName = cpUTF8Value;
    }

    public InnerClassesAttribute(String name) {
        super(attributeName);
        this.innerClasses = new ArrayList();
        this.nestedClassFileEntries = new ArrayList();
        this.nestedClassFileEntries.add(getAttributeName());
    }

    public void addInnerClassesEntry(CPClass innerClass, CPClass outerClass, CPUTF8 innerName, int flags) {
        if (innerClass != null) {
            this.nestedClassFileEntries.add(innerClass);
        }
        if (outerClass != null) {
            this.nestedClassFileEntries.add(outerClass);
        }
        if (innerName != null) {
            this.nestedClassFileEntries.add(innerName);
        }
        addInnerClassesEntry(new InnerClassesEntry(innerClass, outerClass, innerName, flags));
    }

    private void addInnerClassesEntry(InnerClassesEntry innerClassesEntry) {
        this.innerClasses.add(innerClassesEntry);
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj) || getClass() != obj.getClass()) {
            return false;
        }
        InnerClassesAttribute other = (InnerClassesAttribute) obj;
        if (getAttributeName() == null) {
            if (other.getAttributeName() != null) {
                return false;
            }
        } else if (!getAttributeName().equals(other.getAttributeName())) {
            return false;
        }
        return true;
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute
    protected int getLength() {
        return (this.innerClasses.size() * 8) + 2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public ClassFileEntry[] getNestedClassFileEntries() {
        return (ClassFileEntry[]) this.nestedClassFileEntries.toArray(NONE);
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public int hashCode() {
        int result = super.hashCode();
        return (result * 31) + (getAttributeName() == null ? 0 : getAttributeName().hashCode());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public void resolve(ClassConstantPool pool) {
        super.resolve(pool);
        for (InnerClassesEntry entry : this.innerClasses) {
            entry.resolve(pool);
        }
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public String toString() {
        return "InnerClasses: " + getAttributeName();
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute
    protected void writeBody(DataOutputStream dos) throws IOException {
        dos.writeShort(this.innerClasses.size());
        for (InnerClassesEntry entry : this.innerClasses) {
            entry.write(dos);
        }
    }
}
