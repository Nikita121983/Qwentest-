package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class MulBlankRecord extends StandardRecord {
    public static final short sid = 190;
    private final int _firstCol;
    private final int _lastCol;
    private final int _row;
    private final short[] _xfs;

    public MulBlankRecord(int row, int firstCol, short[] xfs) {
        this._row = row;
        this._firstCol = firstCol;
        this._xfs = xfs;
        this._lastCol = (xfs.length + firstCol) - 1;
    }

    public int getRow() {
        return this._row;
    }

    public int getFirstColumn() {
        return this._firstCol;
    }

    public int getLastColumn() {
        return this._lastCol;
    }

    public int getNumColumns() {
        return (this._lastCol - this._firstCol) + 1;
    }

    public short getXFAt(int coffset) {
        return this._xfs[coffset];
    }

    public MulBlankRecord(RecordInputStream in) {
        this._row = in.readUShort();
        this._firstCol = in.readShort();
        this._xfs = parseXFs(in);
        this._lastCol = in.readShort();
    }

    private static short[] parseXFs(RecordInputStream in) {
        short[] retval = new short[(in.remaining() - 2) / 2];
        for (int idx = 0; idx < retval.length; idx++) {
            retval[idx] = in.readShort();
        }
        return retval;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return (short) 190;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this._row);
        out.writeShort(this._firstCol);
        for (short xf : this._xfs) {
            out.writeShort(xf);
        }
        out.writeShort(this._lastCol);
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return (this._xfs.length * 2) + 6;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public MulBlankRecord copy() {
        return this;
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.MUL_BLANK;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("row", new Supplier() { // from class: org.apache.poi.hssf.record.MulBlankRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(MulBlankRecord.this.getRow());
            }
        }, "firstColumn", new Supplier() { // from class: org.apache.poi.hssf.record.MulBlankRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(MulBlankRecord.this.getFirstColumn());
            }
        }, "lastColumn", new Supplier() { // from class: org.apache.poi.hssf.record.MulBlankRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(MulBlankRecord.this.getLastColumn());
            }
        }, "xf", new Supplier() { // from class: org.apache.poi.hssf.record.MulBlankRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return MulBlankRecord.this.m2334xcb8b0a00();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-MulBlankRecord, reason: not valid java name */
    public /* synthetic */ Object m2334xcb8b0a00() {
        return this._xfs;
    }
}
