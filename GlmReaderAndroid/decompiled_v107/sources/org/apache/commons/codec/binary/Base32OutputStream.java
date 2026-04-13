package org.apache.commons.codec.binary;

import java.io.OutputStream;
import org.apache.commons.codec.CodecPolicy;
import org.apache.commons.codec.binary.BaseNCodecOutputStream;

/* loaded from: classes9.dex */
public class Base32OutputStream extends BaseNCodecOutputStream<Base32, Base32OutputStream, Builder> {

    /* loaded from: classes9.dex */
    public static class Builder extends BaseNCodecOutputStream.AbstractBuilder<Base32OutputStream, Base32, Builder> {
        public Builder() {
            setEncode(true);
        }

        @Override // java.util.function.Supplier
        public Base32OutputStream get() {
            return new Base32OutputStream(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.apache.commons.codec.binary.AbstractBaseNCodecStreamBuilder
        public Base32 newBaseNCodec() {
            return new Base32();
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private Base32OutputStream(Builder builder) {
        super(builder);
    }

    public Base32OutputStream(OutputStream outputStream) {
        this(builder().setOutputStream(outputStream));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    public Base32OutputStream(OutputStream outputStream, boolean encode) {
        super((BaseNCodecOutputStream.AbstractBuilder) builder().setOutputStream(outputStream).setEncode(encode));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    public Base32OutputStream(OutputStream outputStream, boolean encode, int lineLength, byte[] lineSeparator) {
        super((BaseNCodecOutputStream.AbstractBuilder) ((Builder) builder().setOutputStream(outputStream).setEncode(encode)).setBaseNCodec(Base32.builder().setLineLength(lineLength).setLineSeparator(lineSeparator).get()));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    public Base32OutputStream(OutputStream outputStream, boolean encode, int lineLength, byte[] lineSeparator, CodecPolicy decodingPolicy) {
        super((BaseNCodecOutputStream.AbstractBuilder) ((Builder) builder().setOutputStream(outputStream).setEncode(encode)).setBaseNCodec(Base32.builder().setLineLength(lineLength).setLineSeparator(lineSeparator).setDecodingPolicy(decodingPolicy).get()));
    }
}
