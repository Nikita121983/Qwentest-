package org.apache.poi.hpsf;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.LittleEndianByteArrayInputStream;

@Internal
/* loaded from: classes10.dex */
public class ClipboardData {
    private int _format;
    private byte[] _value;
    private static final int DEFAULT_MAX_RECORD_LENGTH = 100000000;
    private static int MAX_RECORD_LENGTH = DEFAULT_MAX_RECORD_LENGTH;
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) ClipboardData.class);

    public static void setMaxRecordLength(int length) {
        MAX_RECORD_LENGTH = length;
    }

    public static int getMaxRecordLength() {
        return MAX_RECORD_LENGTH;
    }

    public void read(LittleEndianByteArrayInputStream lei) {
        int offset = lei.getReadIndex();
        long size = lei.readInt();
        if (size < 4) {
            LOG.atWarn().log("ClipboardData at offset {} size less than 4 bytes (doesn't even have format field!). Setting to format == 0 and hope for the best", Unbox.box(offset));
            this._format = 0;
            this._value = new byte[0];
        } else {
            this._format = lei.readInt();
            this._value = IOUtils.safelyAllocate(size - 4, MAX_RECORD_LENGTH);
            lei.readFully(this._value);
        }
    }

    public byte[] getValue() {
        return this._value;
    }

    public byte[] toByteArray() {
        byte[] result = new byte[this._value.length + 8];
        LittleEndian.putInt(result, 0, this._value.length + 4);
        LittleEndian.putInt(result, 4, this._format);
        System.arraycopy(this._value, 0, result, 8, this._value.length);
        return result;
    }

    public void setValue(byte[] value) {
        this._value = (byte[]) value.clone();
    }
}
