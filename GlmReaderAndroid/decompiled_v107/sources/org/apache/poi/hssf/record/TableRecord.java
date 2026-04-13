package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.util.CellRangeAddress8Bit;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class TableRecord extends SharedValueRecordBase {
    public static final short sid = 566;
    private int field_10_colInputCol;
    private int field_5_flags;
    private int field_6_res;
    private int field_7_rowInputRow;
    private int field_8_colInputRow;
    private int field_9_rowInputCol;
    private static final BitField alwaysCalc = BitFieldFactory.getInstance(1);
    private static final BitField calcOnOpen = BitFieldFactory.getInstance(2);
    private static final BitField rowOrColInpCell = BitFieldFactory.getInstance(4);
    private static final BitField oneOrTwoVar = BitFieldFactory.getInstance(8);
    private static final BitField rowDeleted = BitFieldFactory.getInstance(16);
    private static final BitField colDeleted = BitFieldFactory.getInstance(32);

    public TableRecord(TableRecord other) {
        super(other);
        this.field_5_flags = other.field_5_flags;
        this.field_6_res = other.field_6_res;
        this.field_7_rowInputRow = other.field_7_rowInputRow;
        this.field_8_colInputRow = other.field_8_colInputRow;
        this.field_9_rowInputCol = other.field_9_rowInputCol;
        this.field_10_colInputCol = other.field_10_colInputCol;
    }

    public TableRecord(RecordInputStream in) {
        super(in);
        this.field_5_flags = in.readByte();
        this.field_6_res = in.readByte();
        this.field_7_rowInputRow = in.readShort();
        this.field_8_colInputRow = in.readShort();
        this.field_9_rowInputCol = in.readShort();
        this.field_10_colInputCol = in.readShort();
    }

    public TableRecord(CellRangeAddress8Bit range) {
        super(range);
        this.field_6_res = 0;
    }

    public int getFlags() {
        return this.field_5_flags;
    }

    public void setFlags(int flags) {
        this.field_5_flags = flags;
    }

    public int getRowInputRow() {
        return this.field_7_rowInputRow;
    }

    public void setRowInputRow(int rowInputRow) {
        this.field_7_rowInputRow = rowInputRow;
    }

    public int getColInputRow() {
        return this.field_8_colInputRow;
    }

    public void setColInputRow(int colInputRow) {
        this.field_8_colInputRow = colInputRow;
    }

    public int getRowInputCol() {
        return this.field_9_rowInputCol;
    }

    public void setRowInputCol(int rowInputCol) {
        this.field_9_rowInputCol = rowInputCol;
    }

    public int getColInputCol() {
        return this.field_10_colInputCol;
    }

    public void setColInputCol(int colInputCol) {
        this.field_10_colInputCol = colInputCol;
    }

    public boolean isAlwaysCalc() {
        return alwaysCalc.isSet(this.field_5_flags);
    }

    public void setAlwaysCalc(boolean flag) {
        this.field_5_flags = alwaysCalc.setBoolean(this.field_5_flags, flag);
    }

    public boolean isRowOrColInpCell() {
        return rowOrColInpCell.isSet(this.field_5_flags);
    }

    public void setRowOrColInpCell(boolean flag) {
        this.field_5_flags = rowOrColInpCell.setBoolean(this.field_5_flags, flag);
    }

    public boolean isOneNotTwoVar() {
        return oneOrTwoVar.isSet(this.field_5_flags);
    }

    public void setOneNotTwoVar(boolean flag) {
        this.field_5_flags = oneOrTwoVar.setBoolean(this.field_5_flags, flag);
    }

    public boolean isColDeleted() {
        return colDeleted.isSet(this.field_5_flags);
    }

    public void setColDeleted(boolean flag) {
        this.field_5_flags = colDeleted.setBoolean(this.field_5_flags, flag);
    }

    public boolean isRowDeleted() {
        return rowDeleted.isSet(this.field_5_flags);
    }

    public void setRowDeleted(boolean flag) {
        this.field_5_flags = rowDeleted.setBoolean(this.field_5_flags, flag);
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return sid;
    }

    @Override // org.apache.poi.hssf.record.SharedValueRecordBase
    protected int getExtraDataSize() {
        return 10;
    }

    @Override // org.apache.poi.hssf.record.SharedValueRecordBase
    protected void serializeExtraData(LittleEndianOutput out) {
        out.writeByte(this.field_5_flags);
        out.writeByte(this.field_6_res);
        out.writeShort(this.field_7_rowInputRow);
        out.writeShort(this.field_8_colInputRow);
        out.writeShort(this.field_9_rowInputCol);
        out.writeShort(this.field_10_colInputCol);
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public TableRecord copy() {
        return new TableRecord(this);
    }

    private static CellReference cr(int rowIx, int colIxAndFlags) {
        int colIx = colIxAndFlags & 255;
        boolean isRowAbs = (32768 & colIxAndFlags) == 0;
        boolean isColAbs = (colIxAndFlags & 16384) == 0;
        return new CellReference(rowIx, colIx, isRowAbs, isColAbs);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.TABLE;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("range", new Supplier() { // from class: org.apache.poi.hssf.record.TableRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return TableRecord.this.getRange();
            }
        }, "flags", GenericRecordUtil.getBitsAsString((Supplier<Number>) new Supplier() { // from class: org.apache.poi.hssf.record.TableRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(TableRecord.this.getFlags());
            }
        }, new BitField[]{alwaysCalc, calcOnOpen, rowOrColInpCell, oneOrTwoVar, rowDeleted, colDeleted}, new String[]{"ALWAYS_CALC", "CALC_ON_OPEN", "ROW_OR_COL_INP_CELL", "ONE_OR_TWO_VAR", "ROW_DELETED", "COL_DELETED"}), "reserved", new Supplier() { // from class: org.apache.poi.hssf.record.TableRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return TableRecord.this.m2379x66214c80();
            }
        }, "rowInput", new Supplier() { // from class: org.apache.poi.hssf.record.TableRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return TableRecord.this.m2380xbd3f3d5f();
            }
        }, "colInput", new Supplier() { // from class: org.apache.poi.hssf.record.TableRecord$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return TableRecord.this.m2381x145d2e3e();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-TableRecord, reason: not valid java name */
    public /* synthetic */ Object m2379x66214c80() {
        return Integer.valueOf(this.field_6_res);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-TableRecord, reason: not valid java name */
    public /* synthetic */ Object m2380xbd3f3d5f() {
        return cr(this.field_7_rowInputRow, this.field_8_colInputRow);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$2$org-apache-poi-hssf-record-TableRecord, reason: not valid java name */
    public /* synthetic */ Object m2381x145d2e3e() {
        return cr(this.field_9_rowInputCol, this.field_10_colInputCol);
    }
}
