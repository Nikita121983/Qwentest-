package org.apache.commons.codec.net;

import org.apache.commons.codec.DecoderException;

/* loaded from: classes9.dex */
final class Utils {
    private static final int RADIX = 16;

    Utils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int digit16(byte b) throws DecoderException {
        int i = Character.digit((char) b, 16);
        if (i == -1) {
            throw new DecoderException("Invalid URL encoding: not a valid digit (radix 16): " + ((int) b));
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static char hexChar(int b) {
        return Character.toUpperCase(Character.forDigit(b & 15, 16));
    }
}
