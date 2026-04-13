package org.apache.poi.hssf.record;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.LittleEndianByteArrayInputStream;
import org.apache.poi.util.LittleEndianByteArrayOutputStream;
import org.apache.poi.util.RecordFormatException;

/* loaded from: classes10.dex */
public final class ObjRecord extends Record {
    private static int MAX_PAD_ALIGNMENT = 4;
    private static final int NORMAL_PAD_ALIGNMENT = 2;
    public static final short sid = 93;
    private boolean _isPaddedToQuadByteMultiple;
    private final byte[] _uninterpretedData;
    private final List<SubRecord> subrecords;

    public ObjRecord() {
        this.subrecords = new ArrayList();
        this._uninterpretedData = null;
    }

    public ObjRecord(ObjRecord other) {
        this.subrecords = new ArrayList();
        Stream<R> map = other.subrecords.stream().map(new Function() { // from class: org.apache.poi.hssf.record.ObjRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((SubRecord) obj).copy();
            }
        });
        final List<SubRecord> list = this.subrecords;
        list.getClass();
        map.forEach(new Consumer() { // from class: org.apache.poi.hssf.record.ObjRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                list.add((SubRecord) obj);
            }
        });
        this._uninterpretedData = other._uninterpretedData == null ? null : (byte[]) other._uninterpretedData.clone();
        this._isPaddedToQuadByteMultiple = other._isPaddedToQuadByteMultiple;
    }

    public ObjRecord(RecordInputStream in) {
        SubRecord subRecord;
        this.subrecords = new ArrayList();
        byte[] subRecordData = in.readRemainder();
        if (LittleEndian.getUShort(subRecordData, 0) != 21) {
            this._uninterpretedData = subRecordData;
            return;
        }
        LittleEndianByteArrayInputStream subRecStream = new LittleEndianByteArrayInputStream(subRecordData);
        CommonObjectDataSubRecord cmo = (CommonObjectDataSubRecord) SubRecord.createSubRecord(subRecStream, 0);
        this.subrecords.add(cmo);
        do {
            subRecord = SubRecord.createSubRecord(subRecStream, cmo.getObjectType());
            this.subrecords.add(subRecord);
        } while (!subRecord.isTerminating());
        int nRemainingBytes = subRecordData.length - subRecStream.getReadIndex();
        if (nRemainingBytes > 0) {
            this._isPaddedToQuadByteMultiple = subRecordData.length % MAX_PAD_ALIGNMENT == 0;
            if (nRemainingBytes >= (this._isPaddedToQuadByteMultiple ? MAX_PAD_ALIGNMENT : 2)) {
                if (!canPaddingBeDiscarded(subRecordData, nRemainingBytes)) {
                    String msg = "Leftover " + nRemainingBytes + " bytes in subrecord data " + HexDump.toHex(subRecordData);
                    throw new RecordFormatException(msg);
                }
                this._isPaddedToQuadByteMultiple = false;
            }
        } else {
            this._isPaddedToQuadByteMultiple = false;
        }
        this._uninterpretedData = null;
    }

    private static boolean canPaddingBeDiscarded(byte[] data, int nRemainingBytes) {
        for (int i = data.length - nRemainingBytes; i < data.length; i++) {
            if (data[i] != 0) {
                return false;
            }
        }
        return true;
    }

    @Override // org.apache.poi.hssf.record.RecordBase
    public int getRecordSize() {
        if (this._uninterpretedData != null) {
            return this._uninterpretedData.length + 4;
        }
        int size = 0;
        for (SubRecord record : this.subrecords) {
            size += record.getDataSize() + 4;
        }
        if (this._isPaddedToQuadByteMultiple) {
            while (size % MAX_PAD_ALIGNMENT != 0) {
                size++;
            }
        } else {
            while (size % 2 != 0) {
                size++;
            }
        }
        return size + 4;
    }

    @Override // org.apache.poi.hssf.record.RecordBase
    public int serialize(int offset, byte[] data) {
        int recSize = getRecordSize();
        int dataSize = recSize - 4;
        try {
            LittleEndianByteArrayOutputStream out = new LittleEndianByteArrayOutputStream(data, offset, recSize);
            try {
                out.writeShort(93);
                out.writeShort(dataSize);
                if (this._uninterpretedData == null) {
                    for (SubRecord record : this.subrecords) {
                        record.serialize(out);
                    }
                    int expectedEndIx = offset + dataSize;
                    while (out.getWriteIndex() < expectedEndIx) {
                        out.writeByte(0);
                    }
                } else {
                    out.write(this._uninterpretedData);
                }
                out.close();
                return recSize;
            } finally {
            }
        } catch (IOException ioe) {
            throw new IllegalStateException(ioe);
        }
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return (short) 93;
    }

    public List<SubRecord> getSubRecords() {
        return this.subrecords;
    }

    public void clearSubRecords() {
        this.subrecords.clear();
    }

    public void addSubRecord(int index, SubRecord element) {
        this.subrecords.add(index, element);
    }

    public boolean addSubRecord(SubRecord o) {
        return this.subrecords.add(o);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public ObjRecord copy() {
        return new ObjRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.OBJ;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("uninterpretedData", new Supplier() { // from class: org.apache.poi.hssf.record.ObjRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return ObjRecord.this.m2344x97bf3729();
            }
        }, "paddedToQuadByteMultiple", new Supplier() { // from class: org.apache.poi.hssf.record.ObjRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return ObjRecord.this.m2345xf911d3c8();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-ObjRecord, reason: not valid java name */
    public /* synthetic */ Object m2344x97bf3729() {
        return this._uninterpretedData;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-ObjRecord, reason: not valid java name */
    public /* synthetic */ Object m2345xf911d3c8() {
        return Boolean.valueOf(this._isPaddedToQuadByteMultiple);
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public List<SubRecord> getGenericChildren() {
        return getSubRecords();
    }
}
