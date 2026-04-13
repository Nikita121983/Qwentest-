package org.apache.poi.util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import kotlin.UByte;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemProperties;
import org.apache.logging.log4j.util.Chars;

@Internal
/* loaded from: classes10.dex */
public final class HexDump {
    public static final String EOL = System.getProperty(SystemProperties.LINE_SEPARATOR);
    public static final Charset UTF8 = StandardCharsets.UTF_8;

    private HexDump() {
    }

    public static void dump(byte[] data, long offset, OutputStream stream, int index, int length) throws IOException, ArrayIndexOutOfBoundsException, IllegalArgumentException {
        if (stream == null) {
            throw new IllegalArgumentException("cannot write to nullstream");
        }
        OutputStreamWriter osw = new OutputStreamWriter(stream, UTF8);
        osw.write(dump(data, offset, index, length));
        osw.flush();
    }

    public static synchronized void dump(byte[] data, long offset, OutputStream stream, int index) throws IOException, ArrayIndexOutOfBoundsException, IllegalArgumentException {
        synchronized (HexDump.class) {
            dump(data, offset, stream, index, Integer.MAX_VALUE);
        }
    }

    public static String dump(byte[] data, long offset, int index) {
        return dump(data, offset, index, Integer.MAX_VALUE);
    }

    public static String dump(byte[] data, long offset, int index, int length) {
        int data_length;
        if (data == null || data.length == 0) {
            return "No Data" + EOL;
        }
        if (length == Integer.MAX_VALUE || length < 0 || index + length < 0) {
            data_length = data.length;
        } else {
            data_length = Math.min(data.length, index + length);
        }
        if (index < 0 || index >= data.length) {
            String err = "illegal index: " + index + " into array of length " + data.length;
            throw new ArrayIndexOutOfBoundsException(err);
        }
        long display_offset = index + offset;
        StringBuilder buffer = new StringBuilder(74);
        for (int j = index; j < data_length; j += 16) {
            int chars_read = data_length - j;
            if (chars_read > 16) {
                chars_read = 16;
            }
            writeHex(buffer, display_offset, 8, "");
            for (int k = 0; k < 16; k++) {
                if (k < chars_read) {
                    writeHex(buffer, data[k + j], 2, StringUtils.SPACE);
                } else {
                    buffer.append("   ");
                }
            }
            buffer.append(Chars.SPACE);
            for (int k2 = 0; k2 < chars_read; k2++) {
                buffer.append(toAscii(data[k2 + j]));
            }
            buffer.append(EOL);
            display_offset += chars_read;
        }
        return buffer.toString();
    }

    public static char toAscii(int dataB) {
        char charB = (char) (dataB & 255);
        if (Character.isISOControl(charB)) {
            return '.';
        }
        switch (charB) {
            case 221:
            case 255:
                return '.';
            default:
                return charB;
        }
    }

    public static String toHex(byte[] value) {
        StringBuilder retVal = new StringBuilder();
        retVal.append('[');
        if (value != null && value.length > 0) {
            for (int x = 0; x < value.length; x++) {
                if (x > 0) {
                    retVal.append(", ");
                }
                retVal.append(toHex(value[x]));
            }
        }
        retVal.append(']');
        return retVal.toString();
    }

    public static String toHex(short value) {
        StringBuilder sb = new StringBuilder(4);
        writeHex(sb, 65535 & value, 4, "");
        return sb.toString();
    }

    public static String toHex(byte value) {
        StringBuilder sb = new StringBuilder(2);
        writeHex(sb, value & UByte.MAX_VALUE, 2, "");
        return sb.toString();
    }

    public static String toHex(int value) {
        StringBuilder sb = new StringBuilder(8);
        writeHex(sb, value & 4294967295L, 8, "");
        return sb.toString();
    }

    public static String toHex(long value) {
        StringBuilder sb = new StringBuilder(16);
        writeHex(sb, value, 16, "");
        return sb.toString();
    }

    public static String longToHex(long value) {
        StringBuilder sb = new StringBuilder(18);
        writeHex(sb, value, 16, "0x");
        return sb.toString();
    }

    public static String intToHex(int value) {
        StringBuilder sb = new StringBuilder(10);
        writeHex(sb, value & 4294967295L, 8, "0x");
        return sb.toString();
    }

    public static String shortToHex(int value) {
        StringBuilder sb = new StringBuilder(6);
        writeHex(sb, value & 65535, 4, "0x");
        return sb.toString();
    }

    public static String byteToHex(int value) {
        StringBuilder sb = new StringBuilder(4);
        writeHex(sb, value & 255, 2, "0x");
        return sb.toString();
    }

    private static void writeHex(StringBuilder sb, long value, int nDigits, String prefix) {
        sb.append(prefix);
        char[] buf = new char[nDigits];
        long acc = value;
        for (int i = nDigits - 1; i >= 0; i--) {
            int digit = (int) (15 & acc);
            buf[i] = (char) (digit < 10 ? digit + 48 : (digit + 65) - 10);
            acc >>>= 4;
        }
        sb.append(buf);
    }
}
