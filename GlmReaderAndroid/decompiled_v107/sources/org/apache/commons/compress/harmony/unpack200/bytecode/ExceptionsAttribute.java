package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import org.apache.logging.log4j.util.Chars;

/* loaded from: classes9.dex */
public class ExceptionsAttribute extends Attribute {
    private static CPUTF8 attributeName;
    private transient int[] exceptionIndexes;
    private final CPClass[] exceptions;

    private static int hashCode(Object[] array) {
        if (array == null) {
            return 0;
        }
        int result = 1;
        int length = array.length;
        for (int i = 0; i < length; i++) {
            Object element = array[i];
            result = (result * 31) + (element == null ? 0 : element.hashCode());
        }
        return result;
    }

    public static void setAttributeName(CPUTF8 cpUTF8Value) {
        attributeName = cpUTF8Value;
    }

    public ExceptionsAttribute(CPClass[] exceptions) {
        super(attributeName);
        this.exceptions = exceptions;
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj) || getClass() != obj.getClass()) {
            return false;
        }
        ExceptionsAttribute other = (ExceptionsAttribute) obj;
        return Arrays.equals(this.exceptions, other.exceptions);
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute
    protected int getLength() {
        return (this.exceptions.length * 2) + 2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public ClassFileEntry[] getNestedClassFileEntries() {
        ClassFileEntry[] result = new ClassFileEntry[this.exceptions.length + 1];
        System.arraycopy(this.exceptions, 0, result, 0, this.exceptions.length);
        result[this.exceptions.length] = getAttributeName();
        return result;
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public int hashCode() {
        int result = super.hashCode();
        return (result * 31) + hashCode(this.exceptions);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public void resolve(ClassConstantPool pool) {
        super.resolve(pool);
        this.exceptionIndexes = new int[this.exceptions.length];
        for (int i = 0; i < this.exceptions.length; i++) {
            this.exceptions[i].resolve(pool);
            this.exceptionIndexes[i] = pool.indexOf(this.exceptions[i]);
        }
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public String toString() {
        StringBuilder sb = new StringBuilder("Exceptions: ");
        for (CPClass exception : this.exceptions) {
            sb.append(exception);
            sb.append(Chars.SPACE);
        }
        return sb.toString();
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute
    protected void writeBody(DataOutputStream dos) throws IOException {
        dos.writeShort(this.exceptionIndexes.length);
        for (int element : this.exceptionIndexes) {
            dos.writeShort(element);
        }
    }
}
