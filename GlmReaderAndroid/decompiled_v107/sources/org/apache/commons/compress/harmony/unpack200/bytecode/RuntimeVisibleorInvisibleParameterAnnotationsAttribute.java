package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.compress.harmony.unpack200.bytecode.AnnotationsAttribute;

/* loaded from: classes9.dex */
public class RuntimeVisibleorInvisibleParameterAnnotationsAttribute extends AnnotationsAttribute {
    private final ParameterAnnotation[] parameterAnnotations;

    /* loaded from: classes9.dex */
    public static class ParameterAnnotation {
        private final AnnotationsAttribute.Annotation[] annotations;

        public ParameterAnnotation(AnnotationsAttribute.Annotation[] annotations) {
            this.annotations = annotations;
        }

        public List<Object> getClassFileEntries() {
            List<Object> nested = new ArrayList<>();
            for (AnnotationsAttribute.Annotation annotation : this.annotations) {
                nested.addAll(annotation.getClassFileEntries());
            }
            return nested;
        }

        public int getLength() {
            int length = 2;
            for (AnnotationsAttribute.Annotation annotation : this.annotations) {
                length += annotation.getLength();
            }
            return length;
        }

        public void resolve(ClassConstantPool pool) {
            for (AnnotationsAttribute.Annotation annotation : this.annotations) {
                annotation.resolve(pool);
            }
        }

        public void writeBody(DataOutputStream dos) throws IOException {
            dos.writeShort(this.annotations.length);
            for (AnnotationsAttribute.Annotation annotation : this.annotations) {
                annotation.writeBody(dos);
            }
        }
    }

    public RuntimeVisibleorInvisibleParameterAnnotationsAttribute(CPUTF8 name, ParameterAnnotation[] parameterAnnotations) {
        super(name);
        this.parameterAnnotations = parameterAnnotations;
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute
    protected int getLength() {
        int length = 1;
        for (ParameterAnnotation parameterAnnotation : this.parameterAnnotations) {
            length += parameterAnnotation.getLength();
        }
        return length;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public ClassFileEntry[] getNestedClassFileEntries() {
        List<Object> nested = new ArrayList<>();
        nested.add(this.attributeName);
        for (ParameterAnnotation parameterAnnotation : this.parameterAnnotations) {
            nested.addAll(parameterAnnotation.getClassFileEntries());
        }
        return (ClassFileEntry[]) nested.toArray(NONE);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public void resolve(ClassConstantPool pool) {
        super.resolve(pool);
        for (ParameterAnnotation parameterAnnotation : this.parameterAnnotations) {
            parameterAnnotation.resolve(pool);
        }
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public String toString() {
        return this.attributeName.underlyingString() + ": " + this.parameterAnnotations.length + " parameter annotations";
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute
    protected void writeBody(DataOutputStream dos) throws IOException {
        dos.writeByte(this.parameterAnnotations.length);
        for (ParameterAnnotation parameterAnnotation : this.parameterAnnotations) {
            parameterAnnotation.writeBody(dos);
        }
    }
}
