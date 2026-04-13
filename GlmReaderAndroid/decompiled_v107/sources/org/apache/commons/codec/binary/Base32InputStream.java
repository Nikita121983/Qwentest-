package org.apache.commons.codec.binary;

import java.io.InputStream;
import org.apache.commons.codec.CodecPolicy;
import org.apache.commons.codec.binary.BaseNCodecInputStream;

/* loaded from: classes9.dex */
public class Base32InputStream extends BaseNCodecInputStream<Base32, Base32InputStream, Builder> {

    /* loaded from: classes9.dex */
    public static class Builder extends BaseNCodecInputStream.AbstracBuilder<Base32InputStream, Base32, Builder> {
        @Override // java.util.function.Supplier
        public Base32InputStream get() {
            return new Base32InputStream(this);
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

    private Base32InputStream(Builder builder) {
        super(builder);
    }

    public Base32InputStream(InputStream inputStream) {
        super(builder().setInputStream(inputStream));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    public Base32InputStream(InputStream inputStream, boolean encode) {
        super((BaseNCodecInputStream.AbstracBuilder) builder().setInputStream(inputStream).setEncode(encode));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    public Base32InputStream(InputStream inputStream, boolean encode, int lineLength, byte[] lineSeparator) {
        super((BaseNCodecInputStream.AbstracBuilder) ((Builder) builder().setInputStream(inputStream).setEncode(encode)).setBaseNCodec(Base32.builder().setLineLength(lineLength).setLineSeparator(lineSeparator).get()));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    public Base32InputStream(InputStream inputStream, boolean encode, int lineLength, byte[] lineSeparator, CodecPolicy decodingPolicy) {
        super((BaseNCodecInputStream.AbstracBuilder) ((Builder) builder().setInputStream(inputStream).setEncode(encode)).setBaseNCodec(Base32.builder().setLineLength(lineLength).setLineSeparator(lineSeparator).setDecodingPolicy(decodingPolicy).get()));
    }
}
