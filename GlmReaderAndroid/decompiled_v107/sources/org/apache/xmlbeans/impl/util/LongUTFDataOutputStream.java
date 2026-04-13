package org.apache.xmlbeans.impl.util;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes11.dex */
public class LongUTFDataOutputStream extends DataOutputStream {
    static final int MAX_UNSIGNED_SHORT = 65534;

    public LongUTFDataOutputStream(OutputStream out) {
        super(out);
    }

    public void writeShortOrInt(int value) throws IOException {
        writeShortOrInt(this, value);
    }

    public static void writeShortOrInt(DataOutputStream dos, int value) throws IOException {
        if (value < MAX_UNSIGNED_SHORT) {
            dos.writeShort(value);
        } else {
            dos.writeShort(MAX_UNSIGNED_SHORT);
            dos.writeInt(value);
        }
    }

    public void writeLongUTF(String str) throws IOException {
        int count;
        int count2;
        int utfLen = countUTF(str);
        writeShortOrInt(utfLen);
        byte[] bytearr = new byte[4096];
        int strlen = str.length();
        int count3 = 0;
        for (int i = 0; i < strlen; i++) {
            if (count3 >= bytearr.length - 3) {
                write(bytearr, 0, count3);
                count3 = 0;
            }
            char c = str.charAt(i);
            if (c >= 1 && c <= 127) {
                count2 = count3 + 1;
                bytearr[count3] = (byte) c;
            } else {
                if (c > 2047) {
                    int count4 = count3 + 1;
                    bytearr[count3] = (byte) (((c >> '\f') & 15) | 224);
                    count = count4 + 1;
                    bytearr[count4] = (byte) (((c >> 6) & 63) | 128);
                } else {
                    bytearr[count3] = (byte) (((c >> 6) & 31) | 192);
                    count = count3 + 1;
                }
                count2 = count + 1;
                bytearr[count] = (byte) ((c & '?') | 128);
            }
            count3 = count2;
        }
        write(bytearr, 0, count3);
    }

    public static int countUTF(String str) {
        int strlen = str.length();
        int count = 0;
        for (int i = 0; i < strlen; i++) {
            char c = str.charAt(i);
            if (c >= 1 && c <= 127) {
                count++;
            } else if (c > 2047) {
                count += 3;
            } else {
                count += 2;
            }
        }
        return count;
    }
}
