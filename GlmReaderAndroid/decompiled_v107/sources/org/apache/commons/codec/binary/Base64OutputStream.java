package org.apache.commons.codec.binary;

import java.io.OutputStream;
import org.apache.commons.codec.CodecPolicy;
import org.apache.commons.codec.binary.BaseNCodecOutputStream;

/* loaded from: classes9.dex */
public class Base64OutputStream extends BaseNCodecOutputStream<Base64, Base64OutputStream, Builder> {

    /* loaded from: classes9.dex */
    public static class Builder extends BaseNCodecOutputStream.AbstractBuilder<Base64OutputStream, Base64, Builder> {
        public Builder() {
            setEncode(true);
        }

        @Override // java.util.function.Supplier
        public Base64OutputStream get() {
            return new Base64OutputStream(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.apache.commons.codec.binary.AbstractBaseNCodecStreamBuilder
        public Base64 newBaseNCodec() {
            return new Base64();
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private Base64OutputStream(Builder builder) {
        super(builder);
    }

    public Base64OutputStream(OutputStream outputStream) {
        this(builder().setOutputStream(outputStream));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    public Base64OutputStream(OutputStream outputStream, boolean encode) {
        super((BaseNCodecOutputStream.AbstractBuilder) builder().setOutputStream(outputStream).setEncode(encode));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    public Base64OutputStream(OutputStream outputStream, boolean encode, int lineLength, byte[] lineSeparator) {
        super((BaseNCodecOutputStream.AbstractBuilder) ((Builder) builder().setOutputStream(outputStream).setEncode(encode)).setBaseNCodec(Base64.builder().setLineLength(lineLength).setLineSeparator(lineSeparator).get()));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    public Base64OutputStream(OutputStream outputStream, boolean encode, int lineLength, byte[] lineSeparator, CodecPolicy decodingPolicy) {
        super((BaseNCodecOutputStream.AbstractBuilder) ((Builder) builder().setOutputStream(outputStream).setEncode(encode)).setBaseNCodec(Base64.builder().setLineLength(lineLength).setLineSeparator(lineSeparator).setDecodingPolicy(decodingPolicy).get()));
    }
}
