package org.apache.commons.compress.harmony.unpack200.bytecode;

/* loaded from: classes9.dex */
public abstract class CPConstantNumber extends CPConstant {
    public CPConstantNumber(byte tag, Object value, int globalIndex) {
        super(tag, value, globalIndex);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Number getNumber() {
        return (Number) getValue();
    }
}
