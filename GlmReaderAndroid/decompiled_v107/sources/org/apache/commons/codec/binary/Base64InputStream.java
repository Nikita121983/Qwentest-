package org.apache.commons.codec.binary;

import java.io.InputStream;
import org.apache.commons.codec.CodecPolicy;
import org.apache.commons.codec.binary.BaseNCodecInputStream;

/* loaded from: classes9.dex */
public class Base64InputStream extends BaseNCodecInputStream<Base64, Base64InputStream, Builder> {

    /* loaded from: classes9.dex */
    public static class Builder extends BaseNCodecInputStream.AbstracBuilder<Base64InputStream, Base64, Builder> {
        @Override // java.util.function.Supplier
        public Base64InputStream get() {
            return new Base64InputStream(this);
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

    private Base64InputStream(Builder builder) {
        super(builder);
    }

    public Base64InputStream(InputStream inputStream) {
        super(builder().setInputStream(inputStream));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    public Base64InputStream(InputStream inputStream, boolean encode) {
        super((BaseNCodecInputStream.AbstracBuilder) builder().setInputStream(inputStream).setEncode(encode));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    public Base64InputStream(InputStream inputStream, boolean encode, int lineLength, byte[] lineSeparator) {
        super((BaseNCodecInputStream.AbstracBuilder) ((Builder) builder().setInputStream(inputStream).setEncode(encode)).setBaseNCodec(Base64.builder().setLineLength(lineLength).setLineSeparator(lineSeparator).get()));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    public Base64InputStream(InputStream inputStream, boolean encode, int lineLength, byte[] lineSeparator, CodecPolicy decodingPolicy) {
        super((BaseNCodecInputStream.AbstracBuilder) ((Builder) builder().setInputStream(inputStream).setEncode(encode)).setBaseNCodec(Base64.builder().setLineLength(lineLength).setLineSeparator(lineSeparator).setDecodingPolicy(decodingPolicy).get()));
    }
}
