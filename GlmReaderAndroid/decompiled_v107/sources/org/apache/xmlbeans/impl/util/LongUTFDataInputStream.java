package org.apache.xmlbeans.impl.util;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UTFDataFormatException;
import kotlin.UByte;

/* loaded from: classes11.dex */
public class LongUTFDataInputStream extends DataInputStream {

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public interface IOCall {
        byte onebyte(int[] iArr, int[] iArr2, int[] iArr3) throws IOException;
    }

    public LongUTFDataInputStream(InputStream in) {
        super(in);
    }

    public int readUnsignedShortOrInt() throws IOException {
        return readUnsignedShortOrInt(this);
    }

    public static int readUnsignedShortOrInt(DataInputStream dis) throws IOException {
        int value = dis.readUnsignedShort();
        if (value == 65534) {
            return dis.readInt();
        }
        return value;
    }

    public String readLongUTF() throws IOException {
        final int utfLen = readUnsignedShortOrInt();
        StringBuilder sb = new StringBuilder(utfLen / 2);
        final byte[] bytearr = new byte[4096];
        IOCall give = new IOCall() { // from class: org.apache.xmlbeans.impl.util.LongUTFDataInputStream$$ExternalSyntheticLambda0
            @Override // org.apache.xmlbeans.impl.util.LongUTFDataInputStream.IOCall
            public final byte onebyte(int[] iArr, int[] iArr2, int[] iArr3) {
                return LongUTFDataInputStream.this.m2692xc5ca5ffe(utfLen, bytearr, iArr, iArr2, iArr3);
            }
        };
        int[] readLen = {0};
        int[] readBuf = {0};
        int[] fillBuf = {0};
        while (readLen[0] < utfLen) {
            int c = give.onebyte(readBuf, fillBuf, readLen) & UByte.MAX_VALUE;
            switch (c >> 4) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    sb.append((char) c);
                    break;
                case 8:
                case 9:
                case 10:
                case 11:
                default:
                    throw new UTFDataFormatException("malformed input around byte " + readLen[0]);
                case 12:
                case 13:
                    int char2 = give.onebyte(readBuf, fillBuf, readLen);
                    if ((char2 & 192) != 128) {
                        throw new UTFDataFormatException("malformed input around byte " + readLen[0]);
                    }
                    sb.append((char) (((c & 31) << 6) | (char2 & 63)));
                    break;
                case 14:
                    int char22 = give.onebyte(readBuf, fillBuf, readLen);
                    int char3 = give.onebyte(readBuf, fillBuf, readLen);
                    if ((char22 & 192) != 128 || (char3 & 192) != 128) {
                        throw new UTFDataFormatException("malformed input around byte " + (readLen[0] - 1));
                    }
                    sb.append((char) (((c & 15) << 12) | ((char22 & 63) << 6) | (char3 & 63)));
                    break;
            }
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$readLongUTF$0$org-apache-xmlbeans-impl-util-LongUTFDataInputStream, reason: not valid java name */
    public /* synthetic */ byte m2692xc5ca5ffe(int utfLen, byte[] bytearr, int[] readBuf, int[] fillBuf, int[] readLen) throws IOException {
        if (readLen[0] + 1 <= utfLen) {
            if (readBuf[0] >= fillBuf[0]) {
                fillBuf[0] = Math.min(bytearr.length, utfLen - readLen[0]);
                readFully(bytearr, 0, fillBuf[0]);
                readBuf[0] = 0;
            }
            readLen[0] = readLen[0] + 1;
            int i = readBuf[0];
            readBuf[0] = i + 1;
            return bytearr[i];
        }
        throw new UTFDataFormatException("malformed input: partial character at end");
    }
}
