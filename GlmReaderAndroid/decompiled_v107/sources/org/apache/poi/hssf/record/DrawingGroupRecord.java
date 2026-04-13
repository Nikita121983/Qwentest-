package org.apache.poi.hssf.record;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.ddf.EscherRecord;
import org.apache.poi.ddf.NullEscherSerializationListener;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.Removal;

/* loaded from: classes10.dex */
public final class DrawingGroupRecord extends AbstractEscherHolderRecord {
    private static final int DEFAULT_MAX_RECORD_SIZE = 8228;
    private static int MAX_RECORD_SIZE = DEFAULT_MAX_RECORD_SIZE;
    public static final short sid = 235;

    public static void setMaxRecordSize(int size) {
        MAX_RECORD_SIZE = size;
    }

    public static int getMaxRecordSize() {
        return MAX_RECORD_SIZE;
    }

    private static int getMaxDataSize() {
        return MAX_RECORD_SIZE - 4;
    }

    public DrawingGroupRecord() {
    }

    public DrawingGroupRecord(DrawingGroupRecord other) {
        super(other);
    }

    public DrawingGroupRecord(RecordInputStream in) {
        super(in);
    }

    @Override // org.apache.poi.hssf.record.AbstractEscherHolderRecord
    protected String getRecordName() {
        return "MSODRAWINGGROUP";
    }

    @Override // org.apache.poi.hssf.record.AbstractEscherHolderRecord, org.apache.poi.hssf.record.Record
    public short getSid() {
        return sid;
    }

    @Override // org.apache.poi.hssf.record.AbstractEscherHolderRecord, org.apache.poi.hssf.record.RecordBase
    public int serialize(int offset, byte[] data) {
        byte[] rawData = getRawData();
        if (getEscherRecords().isEmpty() && rawData != null) {
            return writeData(offset, data, rawData);
        }
        byte[] buffer = new byte[getRawDataSize()];
        int pos = 0;
        for (EscherRecord r : getEscherRecords()) {
            pos += r.serialize(pos, buffer, new NullEscherSerializationListener());
        }
        return writeData(offset, data, buffer);
    }

    @Removal(version = "5.3")
    @Deprecated
    public void processChildRecords() {
        decode();
    }

    @Override // org.apache.poi.hssf.record.AbstractEscherHolderRecord, org.apache.poi.hssf.record.RecordBase
    public int getRecordSize() {
        return grossSizeFromDataSize(getRawDataSize());
    }

    private int getRawDataSize() {
        List<EscherRecord> escherRecords = getEscherRecords();
        byte[] rawData = getRawData();
        if (escherRecords.isEmpty() && rawData != null) {
            return rawData.length;
        }
        int size = 0;
        for (EscherRecord r : escherRecords) {
            size += r.getRecordSize();
        }
        return size;
    }

    static int grossSizeFromDataSize(int dataSize) {
        return ((((dataSize - 1) / getMaxDataSize()) + 1) * 4) + dataSize;
    }

    private int writeData(int offset, byte[] data, byte[] rawData) {
        int writtenActualData = 0;
        int writtenRawData = 0;
        while (writtenRawData < rawData.length) {
            int maxDataSize = getMaxDataSize();
            int segmentLength = Math.min(rawData.length - writtenRawData, maxDataSize);
            if (writtenRawData / maxDataSize >= 2) {
                writeContinueHeader(data, offset, segmentLength);
            } else {
                writeHeader(data, offset, segmentLength);
            }
            int offset2 = offset + 4;
            System.arraycopy(rawData, writtenRawData, data, offset2, segmentLength);
            offset = offset2 + segmentLength;
            writtenRawData += segmentLength;
            writtenActualData = writtenActualData + 4 + segmentLength;
        }
        return writtenActualData;
    }

    private void writeHeader(byte[] data, int offset, int sizeExcludingHeader) {
        LittleEndian.putShort(data, offset, getSid());
        LittleEndian.putShort(data, offset + 2, (short) sizeExcludingHeader);
    }

    private void writeContinueHeader(byte[] data, int offset, int sizeExcludingHeader) {
        LittleEndian.putShort(data, offset, (short) 60);
        LittleEndian.putShort(data, offset + 2, (short) sizeExcludingHeader);
    }

    @Override // org.apache.poi.hssf.record.AbstractEscherHolderRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public DrawingGroupRecord copy() {
        return new DrawingGroupRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.DRAWING_GROUP;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return null;
    }
}
