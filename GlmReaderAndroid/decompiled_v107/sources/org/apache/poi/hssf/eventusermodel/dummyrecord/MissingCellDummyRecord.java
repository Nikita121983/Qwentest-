package org.apache.poi.hssf.eventusermodel.dummyrecord;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.util.GenericRecordUtil;

/* loaded from: classes10.dex */
public final class MissingCellDummyRecord extends DummyRecordBase {
    private final int column;
    private final int row;

    @Override // org.apache.poi.hssf.eventusermodel.dummyrecord.DummyRecordBase, org.apache.poi.hssf.record.RecordBase
    public /* bridge */ /* synthetic */ int serialize(int i, byte[] bArr) {
        return super.serialize(i, bArr);
    }

    public MissingCellDummyRecord(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public MissingCellDummyRecord copy() {
        return this;
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return null;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("row", new Supplier() { // from class: org.apache.poi.hssf.eventusermodel.dummyrecord.MissingCellDummyRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(MissingCellDummyRecord.this.getRow());
            }
        }, "column", new Supplier() { // from class: org.apache.poi.hssf.eventusermodel.dummyrecord.MissingCellDummyRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(MissingCellDummyRecord.this.getColumn());
            }
        });
    }
}
