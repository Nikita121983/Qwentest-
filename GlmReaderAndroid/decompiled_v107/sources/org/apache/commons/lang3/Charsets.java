package org.apache.commons.lang3;

import java.nio.charset.Charset;

/* loaded from: classes9.dex */
final class Charsets {
    Charsets() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Charset toCharset(Charset charset) {
        return charset == null ? Charset.defaultCharset() : charset;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Charset toCharset(String charsetName) {
        return charsetName == null ? Charset.defaultCharset() : Charset.forName(charsetName);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String toCharsetName(String charsetName) {
        return charsetName == null ? Charset.defaultCharset().name() : charsetName;
    }
}
