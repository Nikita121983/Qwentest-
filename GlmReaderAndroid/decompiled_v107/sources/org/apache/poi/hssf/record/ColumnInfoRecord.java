package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class ColumnInfoRecord extends StandardRecord {
    public static final short sid = 125;
    private int _colWidth;
    private int _firstCol;
    private int _lastCol;
    private int _options;
    private int _xfIndex;
    private int field_6_reserved;
    private static final BitField hidden = BitFieldFactory.getInstance(1);
    private static final BitField outlevel = BitFieldFactory.getInstance(1792);
    private static final BitField collapsed = BitFieldFactory.getInstance(4096);

    public ColumnInfoRecord() {
        setColumnWidth(2275);
        this._options = 2;
        this._xfIndex = 15;
        this.field_6_reserved = 2;
    }

    public ColumnInfoRecord(ColumnInfoRecord other) {
        super(other);
        this._firstCol = other._firstCol;
        this._lastCol = other._lastCol;
        this._colWidth = other._colWidth;
        this._xfIndex = other._xfIndex;
        this._options = other._options;
        this.field_6_reserved = other.field_6_reserved;
    }

    public ColumnInfoRecord(RecordInputStream in) {
        this._firstCol = in.readUShort();
        this._lastCol = in.readUShort();
        this._colWidth = in.readUShort();
        this._xfIndex = in.readUShort();
        this._options = in.readUShort();
        switch (in.remaining()) {
            case 0:
                this.field_6_reserved = 0;
                return;
            case 1:
                this.field_6_reserved = in.readByte();
                return;
            case 2:
                this.field_6_reserved = in.readUShort();
                return;
            default:
                throw new IllegalArgumentException("Unusual record size remaining=(" + in.remaining() + ")");
        }
    }

    public void setFirstColumn(int fc) {
        this._firstCol = fc;
    }

    public void setLastColumn(int lc) {
        this._lastCol = lc;
    }

    public void setColumnWidth(int cw) {
        this._colWidth = cw;
    }

    public void setXFIndex(int xfi) {
        this._xfIndex = xfi;
    }

    public void setHidden(boolean ishidden) {
        this._options = hidden.setBoolean(this._options, ishidden);
    }

    public void setOutlineLevel(int olevel) {
        this._options = outlevel.setValue(this._options, olevel);
    }

    public void setCollapsed(boolean isCollapsed) {
        this._options = collapsed.setBoolean(this._options, isCollapsed);
    }

    public int getFirstColumn() {
        return this._firstCol;
    }

    public int getLastColumn() {
        return this._lastCol;
    }

    public int getColumnWidth() {
        return this._colWidth;
    }

    public int getXFIndex() {
        return this._xfIndex;
    }

    public boolean getHidden() {
        return hidden.isSet(this._options);
    }

    public int getOutlineLevel() {
        return outlevel.getValue(this._options);
    }

    public boolean getCollapsed() {
        return collapsed.isSet(this._options);
    }

    public boolean containsColumn(int columnIndex) {
        return this._firstCol <= columnIndex && columnIndex <= this._lastCol;
    }

    public boolean isAdjacentBefore(ColumnInfoRecord other) {
        return this._lastCol == other._firstCol - 1;
    }

    public boolean formatMatches(ColumnInfoRecord other) {
        return this._xfIndex == other._xfIndex && this._options == other._options && this._colWidth == other._colWidth;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return (short) 125;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort(getFirstColumn());
        out.writeShort(getLastColumn());
        out.writeShort(getColumnWidth());
        out.writeShort(getXFIndex());
        out.writeShort(this._options);
        out.writeShort(this.field_6_reserved);
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return 12;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public ColumnInfoRecord copy() {
        return new ColumnInfoRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.COLUMN_INFO;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("firstColumn", new Supplier() { // from class: org.apache.poi.hssf.record.ColumnInfoRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(ColumnInfoRecord.this.getFirstColumn());
            }
        }, "lastColumn", new Supplier() { // from class: org.apache.poi.hssf.record.ColumnInfoRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(ColumnInfoRecord.this.getLastColumn());
            }
        }, "columnWidth", new Supplier() { // from class: org.apache.poi.hssf.record.ColumnInfoRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(ColumnInfoRecord.this.getColumnWidth());
            }
        }, "xfIndex", new Supplier() { // from class: org.apache.poi.hssf.record.ColumnInfoRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(ColumnInfoRecord.this.getXFIndex());
            }
        }, "options", new Supplier() { // from class: org.apache.poi.hssf.record.ColumnInfoRecord$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return ColumnInfoRecord.this.m2269xc4b24474();
            }
        }, CellUtil.HIDDEN, new Supplier() { // from class: org.apache.poi.hssf.record.ColumnInfoRecord$$ExternalSyntheticLambda5
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(ColumnInfoRecord.this.getHidden());
            }
        }, "outlineLevel", new Supplier() { // from class: org.apache.poi.hssf.record.ColumnInfoRecord$$ExternalSyntheticLambda6
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(ColumnInfoRecord.this.getOutlineLevel());
            }
        }, "collapsed", new Supplier() { // from class: org.apache.poi.hssf.record.ColumnInfoRecord$$ExternalSyntheticLambda7
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(ColumnInfoRecord.this.getCollapsed());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-ColumnInfoRecord, reason: not valid java name */
    public /* synthetic */ Object m2269xc4b24474() {
        return Integer.valueOf(this._options);
    }
}
