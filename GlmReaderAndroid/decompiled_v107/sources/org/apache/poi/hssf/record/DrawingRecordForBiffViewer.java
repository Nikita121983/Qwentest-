package org.apache.poi.hssf.record;

import java.io.IOException;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.commons.io.input.UnsynchronizedByteArrayInputStream;

/* loaded from: classes10.dex */
public final class DrawingRecordForBiffViewer extends AbstractEscherHolderRecord {
    public static final short sid = 236;

    public DrawingRecordForBiffViewer() {
    }

    public DrawingRecordForBiffViewer(DrawingRecordForBiffViewer other) {
        super(other);
    }

    public DrawingRecordForBiffViewer(RecordInputStream in) {
        super(in);
    }

    public DrawingRecordForBiffViewer(DrawingRecord r) {
        super(convertToInputStream(r));
        decode();
    }

    private static RecordInputStream convertToInputStream(DrawingRecord r) {
        byte[] data = r.serialize();
        try {
            RecordInputStream rinp = new RecordInputStream(UnsynchronizedByteArrayInputStream.builder().setByteArray(data).get());
            rinp.nextRecord();
            return rinp;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override // org.apache.poi.hssf.record.AbstractEscherHolderRecord
    protected String getRecordName() {
        return "MSODRAWING";
    }

    @Override // org.apache.poi.hssf.record.AbstractEscherHolderRecord, org.apache.poi.hssf.record.Record
    public short getSid() {
        return (short) 236;
    }

    @Override // org.apache.poi.hssf.record.AbstractEscherHolderRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public DrawingRecordForBiffViewer copy() {
        return new DrawingRecordForBiffViewer(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.DRAWING;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return null;
    }
}
