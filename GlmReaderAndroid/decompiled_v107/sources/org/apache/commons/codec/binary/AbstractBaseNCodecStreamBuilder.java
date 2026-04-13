package org.apache.commons.codec.binary;

import java.util.function.Supplier;
import org.apache.commons.codec.binary.AbstractBaseNCodecStreamBuilder;
import org.apache.commons.codec.binary.BaseNCodec;

/* loaded from: classes9.dex */
public abstract class AbstractBaseNCodecStreamBuilder<T, C extends BaseNCodec, B extends AbstractBaseNCodecStreamBuilder<T, C, B>> implements Supplier<T> {
    private C baseNCodec = newBaseNCodec();
    private boolean encode;

    protected abstract C newBaseNCodec();

    /* JADX INFO: Access modifiers changed from: package-private */
    public B asThis() {
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public C getBaseNCodec() {
        return this.baseNCodec;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean getEncode() {
        return this.encode;
    }

    public B setBaseNCodec(C baseNCodec) {
        this.baseNCodec = baseNCodec != null ? baseNCodec : newBaseNCodec();
        return asThis();
    }

    public B setEncode(boolean encode) {
        this.encode = encode;
        return asThis();
    }
}
