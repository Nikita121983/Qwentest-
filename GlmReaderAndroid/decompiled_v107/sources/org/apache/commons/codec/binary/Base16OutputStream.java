package org.apache.commons.codec.binary;

import java.io.OutputStream;
import org.apache.commons.codec.CodecPolicy;
import org.apache.commons.codec.binary.BaseNCodecOutputStream;

/* loaded from: classes9.dex */
public class Base16OutputStream extends BaseNCodecOutputStream<Base16, Base16OutputStream, Builder> {

    /* loaded from: classes9.dex */
    public static class Builder extends BaseNCodecOutputStream.AbstractBuilder<Base16OutputStream, Base16, Builder> {
        public Builder() {
            setEncode(true);
        }

        @Override // java.util.function.Supplier
        public Base16OutputStream get() {
            return new Base16OutputStream(this);
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

    private Base16OutputStream(Builder builder) {
        super(builder);
    }

    public Base16OutputStream(OutputStream outputStream) {
        super(builder().setOutputStream(outputStream));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    public Base16OutputStream(OutputStream outputStream, boolean encode) {
        super((BaseNCodecOutputStream.AbstractBuilder) builder().setOutputStream(outputStream).setEncode(encode));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    public Base16OutputStream(OutputStream outputStream, boolean encode, boolean lowerCase) {
        super((BaseNCodecOutputStream.AbstractBuilder) ((Builder) builder().setOutputStream(outputStream).setEncode(encode)).setBaseNCodec(new Base16(lowerCase)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    public Base16OutputStream(OutputStream outputStream, boolean encode, boolean lowerCase, CodecPolicy decodingPolicy) {
        super((BaseNCodecOutputStream.AbstractBuilder) ((Builder) builder().setOutputStream(outputStream).setEncode(encode)).setBaseNCodec(new Base16(lowerCase, decodingPolicy)));
    }
}
