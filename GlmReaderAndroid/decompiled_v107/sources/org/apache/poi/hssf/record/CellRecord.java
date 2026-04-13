package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public abstract class CellRecord extends StandardRecord implements CellValueRecordInterface {
    private int _columnIndex;
    private int _formatIndex;
    private int _rowIndex;

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public abstract CellRecord copy();

    protected abstract String getRecordName();

    protected abstract int getValueDataSize();

    protected abstract void serializeValue(LittleEndianOutput littleEndianOutput);

    /* JADX INFO: Access modifiers changed from: protected */
    public CellRecord() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CellRecord(CellRecord other) {
        super(other);
        this._rowIndex = other.getRow();
        this._columnIndex = other.getColumn();
        this._formatIndex = other.getXFIndex();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CellRecord(RecordInputStream in) {
        this._rowIndex = in.readUShort();
        this._columnIndex = in.readUShort();
        this._formatIndex = in.readUShort();
    }

    @Override // org.apache.poi.hssf.record.CellValueRecordInterface
    public final void setRow(int row) {
        this._rowIndex = row;
    }

    @Override // org.apache.poi.hssf.record.CellValueRecordInterface
    public final void setColumn(short col) {
        this._columnIndex = col;
    }

    @Override // org.apache.poi.hssf.record.CellValueRecordInterface
    public final void setXFIndex(short xf) {
        this._formatIndex = xf;
    }

    @Override // org.apache.poi.hssf.record.CellValueRecordInterface
    public final int getRow() {
        return this._rowIndex;
    }

    @Override // org.apache.poi.hssf.record.CellValueRecordInterface
    public final short getColumn() {
        return (short) this._columnIndex;
    }

    @Override // org.apache.poi.hssf.record.CellValueRecordInterface
    public final short getXFIndex() {
        return (short) this._formatIndex;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public final void serialize(LittleEndianOutput out) {
        out.writeShort(getRow());
        out.writeShort(getColumn());
        out.writeShort(getXFIndex());
        serializeValue(out);
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected final int getDataSize() {
        return getValueDataSize() + 6;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("row", new Supplier() { // from class: org.apache.poi.hssf.record.CellRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(CellRecord.this.getRow());
            }
        }, "col", new Supplier() { // from class: org.apache.poi.hssf.record.CellRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(CellRecord.this.getColumn());
            }
        }, "xfIndex", new Supplier() { // from class: org.apache.poi.hssf.record.CellRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(CellRecord.this.getXFIndex());
            }
        });
    }
}
