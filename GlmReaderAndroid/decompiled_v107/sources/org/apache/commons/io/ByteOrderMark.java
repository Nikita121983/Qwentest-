package org.apache.commons.io;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Objects;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.hssf.record.UnknownRecord;

/* loaded from: classes9.dex */
public class ByteOrderMark implements Serializable {
    public static final char UTF_BOM = 65279;
    private static final long serialVersionUID = 1;
    private final int[] bytes;
    private final String charsetName;
    public static final ByteOrderMark UTF_8 = new ByteOrderMark(StandardCharsets.UTF_8.name(), UnknownRecord.PHONETICPR_00EF, 187, 191);
    public static final ByteOrderMark UTF_16BE = new ByteOrderMark(StandardCharsets.UTF_16BE.name(), 254, 255);
    public static final ByteOrderMark UTF_16LE = new ByteOrderMark(StandardCharsets.UTF_16LE.name(), 255, 254);
    public static final ByteOrderMark UTF_32BE = new ByteOrderMark("UTF-32BE", 0, 0, 254, 255);
    public static final ByteOrderMark UTF_32LE = new ByteOrderMark("UTF-32LE", 255, 254, 0, 0);

    public ByteOrderMark(String charsetName, int... bytes) {
        Objects.requireNonNull(charsetName, "charsetName");
        Objects.requireNonNull(bytes, "bytes");
        if (charsetName.isEmpty()) {
            throw new IllegalArgumentException("No charsetName specified");
        }
        if (bytes.length == 0) {
            throw new IllegalArgumentException("No bytes specified");
        }
        this.charsetName = charsetName;
        this.bytes = (int[]) bytes.clone();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ByteOrderMark)) {
            return false;
        }
        ByteOrderMark bom = (ByteOrderMark) obj;
        if (this.bytes.length != bom.length()) {
            return false;
        }
        for (int i = 0; i < this.bytes.length; i++) {
            if (this.bytes[i] != bom.get(i)) {
                return false;
            }
        }
        return true;
    }

    public int get(int pos) {
        return this.bytes[pos];
    }

    public byte[] getBytes() {
        byte[] copy = IOUtils.byteArray(this.bytes.length);
        for (int i = 0; i < this.bytes.length; i++) {
            copy[i] = (byte) this.bytes[i];
        }
        return copy;
    }

    public String getCharsetName() {
        return this.charsetName;
    }

    int[] getRawBytes() {
        return this.bytes;
    }

    public int hashCode() {
        int hashCode = getClass().hashCode();
        for (int b : this.bytes) {
            hashCode += b;
        }
        return hashCode;
    }

    public int length() {
        return this.bytes.length;
    }

    public boolean matches(int[] test) {
        int length;
        if (this.bytes == test) {
            return true;
        }
        if (test == null || test.length < (length = this.bytes.length)) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (this.bytes[i] != test[i]) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getSimpleName());
        builder.append('[');
        builder.append(this.charsetName);
        builder.append(": ");
        for (int i = 0; i < this.bytes.length; i++) {
            if (i > 0) {
                builder.append(CollectionUtils.COMMA);
            }
            builder.append("0x");
            builder.append(Integer.toHexString(this.bytes[i] & 255).toUpperCase(Locale.ROOT));
        }
        builder.append(']');
        return builder.toString();
    }
}
