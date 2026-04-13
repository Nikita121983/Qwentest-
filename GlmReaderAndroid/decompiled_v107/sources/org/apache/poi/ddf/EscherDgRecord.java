package org.apache.poi.ddf;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndian;

/* loaded from: classes10.dex */
public class EscherDgRecord extends EscherRecord {
    public static final short RECORD_ID = EscherRecordTypes.DG.typeID;
    private int field_1_numShapes;
    private int field_2_lastMSOSPID;

    public EscherDgRecord() {
    }

    public EscherDgRecord(EscherDgRecord other) {
        super(other);
        this.field_1_numShapes = other.field_1_numShapes;
        this.field_2_lastMSOSPID = other.field_2_lastMSOSPID;
    }

    @Override // org.apache.poi.ddf.EscherRecord
    public int fillFields(byte[] data, int offset, EscherRecordFactory recordFactory) {
        readHeader(data, offset);
        int pos = offset + 8;
        this.field_1_numShapes = LittleEndian.getInt(data, pos + 0);
        int size = 0 + 4;
        this.field_2_lastMSOSPID = LittleEndian.getInt(data, pos + size);
        int i = size + 4;
        return getRecordSize();
    }

    @Override // org.apache.poi.ddf.EscherRecord
    public int serialize(int offset, byte[] data, EscherSerializationListener listener) {
        listener.beforeRecordSerialize(offset, getRecordId(), this);
        LittleEndian.putShort(data, offset, getOptions());
        LittleEndian.putShort(data, offset + 2, getRecordId());
        LittleEndian.putInt(data, offset + 4, 8);
        LittleEndian.putInt(data, offset + 8, this.field_1_numShapes);
        LittleEndian.putInt(data, offset + 12, this.field_2_lastMSOSPID);
        listener.afterRecordSerialize(offset + 16, getRecordId(), getRecordSize(), this);
        return getRecordSize();
    }

    @Override // org.apache.poi.ddf.EscherRecord
    public int getRecordSize() {
        return 16;
    }

    @Override // org.apache.poi.ddf.EscherRecord
    public short getRecordId() {
        return RECORD_ID;
    }

    @Override // org.apache.poi.ddf.EscherRecord
    public String getRecordName() {
        return EscherRecordTypes.DG.recordName;
    }

    public int getNumShapes() {
        return this.field_1_numShapes;
    }

    public void setNumShapes(int field_1_numShapes) {
        this.field_1_numShapes = field_1_numShapes;
    }

    public int getLastMSOSPID() {
        return this.field_2_lastMSOSPID;
    }

    public void setLastMSOSPID(int field_2_lastMSOSPID) {
        this.field_2_lastMSOSPID = field_2_lastMSOSPID;
    }

    public short getDrawingGroupId() {
        return (short) (getOptions() >> 4);
    }

    public void incrementShapeCount() {
        this.field_1_numShapes++;
    }

    @Override // org.apache.poi.ddf.EscherRecord, org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", new Supplier() { // from class: org.apache.poi.ddf.EscherDgRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return EscherDgRecord.this.m2240lambda$getGenericProperties$0$orgapachepoiddfEscherDgRecord();
            }
        }, "numShapes", new Supplier() { // from class: org.apache.poi.ddf.EscherDgRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(EscherDgRecord.this.getNumShapes());
            }
        }, "lastMSOSPID", new Supplier() { // from class: org.apache.poi.ddf.EscherDgRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(EscherDgRecord.this.getLastMSOSPID());
            }
        }, "drawingGroupId", new Supplier() { // from class: org.apache.poi.ddf.EscherDgRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(EscherDgRecord.this.getDrawingGroupId());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-ddf-EscherDgRecord, reason: not valid java name */
    public /* synthetic */ Object m2240lambda$getGenericProperties$0$orgapachepoiddfEscherDgRecord() {
        return super.getGenericProperties();
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Enum getGenericRecordType() {
        return EscherRecordTypes.DG;
    }

    @Override // org.apache.poi.ddf.EscherRecord, org.apache.poi.common.Duplicatable
    public EscherDgRecord copy() {
        return new EscherDgRecord(this);
    }
}
