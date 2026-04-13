package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;
import org.apache.commons.compress.harmony.unpack200.AttributeLayout;

/* loaded from: classes9.dex */
public class EnclosingMethodAttribute extends Attribute {
    private static CPUTF8 attributeName;
    private int classIndex;
    private final CPClass cpClass;
    private final CPNameAndType method;
    private int methodIndex;

    public static void setAttributeName(CPUTF8 cpUTF8Value) {
        attributeName = cpUTF8Value;
    }

    public EnclosingMethodAttribute(CPClass cpClass, CPNameAndType method) {
        super(attributeName);
        this.cpClass = cpClass;
        this.method = method;
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute
    protected int getLength() {
        return 4;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public ClassFileEntry[] getNestedClassFileEntries() {
        if (this.method != null) {
            return new ClassFileEntry[]{attributeName, this.cpClass, this.method};
        }
        return new ClassFileEntry[]{attributeName, this.cpClass};
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public void resolve(ClassConstantPool pool) {
        super.resolve(pool);
        this.cpClass.resolve(pool);
        this.classIndex = pool.indexOf(this.cpClass);
        if (this.method != null) {
            this.method.resolve(pool);
            this.methodIndex = pool.indexOf(this.method);
        } else {
            this.methodIndex = 0;
        }
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public String toString() {
        return AttributeLayout.ATTRIBUTE_ENCLOSING_METHOD;
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute
    protected void writeBody(DataOutputStream dos) throws IOException {
        dos.writeShort(this.classIndex);
        dos.writeShort(this.methodIndex);
    }
}
