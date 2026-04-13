package org.apache.commons.codec.digest;

import kotlin.UByte;

/* loaded from: classes9.dex */
final class MurmurHash {
    MurmurHash() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getLittleEndianInt(byte[] data, int index) {
        return (data[index] & UByte.MAX_VALUE) | ((data[index + 1] & UByte.MAX_VALUE) << 8) | ((data[index + 2] & UByte.MAX_VALUE) << 16) | ((data[index + 3] & UByte.MAX_VALUE) << 24);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long getLittleEndianLong(byte[] data, int index) {
        return (data[index] & 255) | ((data[index + 1] & 255) << 8) | ((data[index + 2] & 255) << 16) | ((data[index + 3] & 255) << 24) | ((data[index + 4] & 255) << 32) | ((data[index + 5] & 255) << 40) | ((data[index + 6] & 255) << 48) | ((255 & data[index + 7]) << 56);
    }
}
