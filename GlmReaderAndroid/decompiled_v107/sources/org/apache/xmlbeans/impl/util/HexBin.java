package org.apache.xmlbeans.impl.util;

import java.nio.charset.StandardCharsets;
import kotlin.UByte;
import org.apache.poi.ss.formula.ptg.IntersectionPtg;

/* loaded from: classes11.dex */
public final class HexBin {
    private static final int BASELENGTH = 255;
    private static final int LOOKUPLENGTH = 16;
    private static final byte[] hexNumberTable = new byte[255];
    private static final byte[] lookUpHexAlphabet = new byte[16];

    static {
        for (int i = 0; i < 255; i++) {
            hexNumberTable[i] = -1;
        }
        for (int i2 = 57; i2 >= 48; i2--) {
            hexNumberTable[i2] = (byte) (i2 - 48);
        }
        for (int i3 = 70; i3 >= 65; i3--) {
            hexNumberTable[i3] = (byte) ((i3 - 65) + 10);
        }
        for (int i4 = 102; i4 >= 97; i4--) {
            hexNumberTable[i4] = (byte) ((i4 - 97) + 10);
        }
        for (int i5 = 0; i5 < 10; i5++) {
            lookUpHexAlphabet[i5] = (byte) (i5 + 48);
        }
        for (int i6 = 10; i6 <= 15; i6++) {
            lookUpHexAlphabet[i6] = (byte) ((i6 + 65) - 10);
        }
    }

    static boolean isHex(byte octect) {
        return hexNumberTable[octect & UByte.MAX_VALUE] != -1;
    }

    public static String bytesToString(byte[] binaryData) {
        if (binaryData == null) {
            return null;
        }
        return new String(encode(binaryData), StandardCharsets.ISO_8859_1);
    }

    public static byte[] stringToBytes(String hexEncoded) {
        return decode(hexEncoded.getBytes(StandardCharsets.ISO_8859_1));
    }

    public static byte[] encode(byte[] binaryData) {
        if (binaryData == null) {
            return null;
        }
        int lengthData = binaryData.length;
        int lengthEncode = lengthData * 2;
        byte[] encodedData = new byte[lengthEncode];
        for (int i = 0; i < lengthData; i++) {
            encodedData[i * 2] = lookUpHexAlphabet[(binaryData[i] >> 4) & 15];
            encodedData[(i * 2) + 1] = lookUpHexAlphabet[binaryData[i] & IntersectionPtg.sid];
        }
        return encodedData;
    }

    public static byte[] decode(byte[] binaryData) {
        if (binaryData == null) {
            return null;
        }
        int lengthData = binaryData.length;
        if (lengthData % 2 != 0) {
            return null;
        }
        int lengthDecode = lengthData / 2;
        byte[] decodedData = new byte[lengthDecode];
        for (int i = 0; i < lengthDecode; i++) {
            if (!isHex(binaryData[i * 2]) || !isHex(binaryData[(i * 2) + 1])) {
                return null;
            }
            decodedData[i] = (byte) ((hexNumberTable[binaryData[i * 2]] << 4) | hexNumberTable[binaryData[(i * 2) + 1]]);
        }
        return decodedData;
    }

    public static String decode(String binaryData) {
        byte[] decoded;
        if (binaryData == null || (decoded = decode(binaryData.getBytes(StandardCharsets.ISO_8859_1))) == null) {
            return null;
        }
        return new String(decoded, StandardCharsets.UTF_8);
    }

    public static String encode(String binaryData) {
        byte[] encoded;
        if (binaryData == null || (encoded = encode(binaryData.getBytes(StandardCharsets.UTF_8))) == null) {
            return null;
        }
        return new String(encoded, StandardCharsets.ISO_8859_1);
    }
}
