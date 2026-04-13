package org.apache.commons.io.charset;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.function.Supplier;

/* loaded from: classes9.dex */
public final class CharsetEncoders {
    public static CharsetEncoder toCharsetEncoder(CharsetEncoder charsetEncoder) {
        return toCharsetEncoder(charsetEncoder, new Supplier() { // from class: org.apache.commons.io.charset.CharsetEncoders$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                CharsetEncoder newEncoder;
                newEncoder = Charset.defaultCharset().newEncoder();
                return newEncoder;
            }
        });
    }

    public static CharsetEncoder toCharsetEncoder(CharsetEncoder charsetEncoder, Supplier<CharsetEncoder> defaultSupplier) {
        return charsetEncoder != null ? charsetEncoder : defaultSupplier.get();
    }

    private CharsetEncoders() {
    }
}
