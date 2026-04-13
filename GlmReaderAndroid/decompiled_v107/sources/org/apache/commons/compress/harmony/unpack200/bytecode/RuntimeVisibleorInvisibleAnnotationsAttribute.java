package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.compress.harmony.unpack200.bytecode.AnnotationsAttribute;

/* loaded from: classes9.dex */
public class RuntimeVisibleorInvisibleAnnotationsAttribute extends AnnotationsAttribute {
    private final AnnotationsAttribute.Annotation[] annotations;

    public RuntimeVisibleorInvisibleAnnotationsAttribute(CPUTF8 name, AnnotationsAttribute.Annotation[] annotations) {
        super(name);
        this.annotations = annotations;
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute
    protected int getLength() {
        int length = 2;
        for (AnnotationsAttribute.Annotation annotation : this.annotations) {
            length += annotation.getLength();
        }
        return length;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public ClassFileEntry[] getNestedClassFileEntries() {
        List<Object> nested = new ArrayList<>();
        nested.add(this.attributeName);
        for (AnnotationsAttribute.Annotation annotation : this.annotations) {
            nested.addAll(annotation.getClassFileEntries());
        }
        return (ClassFileEntry[]) nested.toArray(NONE);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public void resolve(ClassConstantPool pool) {
        super.resolve(pool);
        for (AnnotationsAttribute.Annotation annotation : this.annotations) {
            annotation.resolve(pool);
        }
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public String toString() {
        return this.attributeName.underlyingString() + ": " + this.annotations.length + " annotations";
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute
    protected void writeBody(DataOutputStream dos) throws IOException {
        int size = dos.size();
        dos.writeShort(this.annotations.length);
        for (AnnotationsAttribute.Annotation annotation : this.annotations) {
            annotation.writeBody(dos);
        }
        if (dos.size() - size != getLength()) {
            throw new Error();
        }
    }
}
