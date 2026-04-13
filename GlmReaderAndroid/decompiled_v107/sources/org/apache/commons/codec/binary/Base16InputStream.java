package org.apache.commons.codec.binary;

import java.io.InputStream;
import org.apache.commons.codec.CodecPolicy;
import org.apache.commons.codec.binary.BaseNCodecInputStream;

/* loaded from: classes9.dex */
public class Base16InputStream extends BaseNCodecInputStream<Base16, Base16InputStream, Builder> {

    /* loaded from: classes9.dex */
    public static class Builder extends BaseNCodecInputStream.AbstracBuilder<Base16InputStream, Base16, Builder> {
        @Override // java.util.function.Supplier
        public Base16InputStream get() {
            return new Base16InputStream(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.apache.commons.codec.binary.AbstractBaseNCodecStreamBuilder
        public Base16 newBaseNCodec() {
            return new Base16();
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private Base16InputStream(Builder builder) {
        super(builder);
    }

    public Base16InputStream(InputStream inputStream) {
        super(builder().setInputStream(inputStream));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    public Base16InputStream(InputStream inputStream, boolean encode) {
        super((BaseNCodecInputStream.AbstracBuilder) builder().setInputStream(inputStream).setEncode(encode));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    public Base16InputStream(InputStream inputStream, boolean encode, boolean lowerCase) {
        super((BaseNCodecInputStream.AbstracBuilder) ((Builder) builder().setInputStream(inputStream).setEncode(encode)).setBaseNCodec(new Base16(lowerCase)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    public Base16InputStream(InputStream inputStream, boolean encode, boolean lowerCase, CodecPolicy decodingPolicy) {
        super((BaseNCodecInputStream.AbstracBuilder) ((Builder) builder().setInputStream(inputStream).setEncode(encode)).setBaseNCodec(new Base16(lowerCase, decodingPolicy)));
    }
}
