package org.apache.poi.hssf.record;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.StringUtil;

/* loaded from: classes10.dex */
public final class BoundSheetRecord extends StandardRecord {
    public static final short sid = 133;
    private int field_1_position_of_BOF;
    private int field_2_option_flags;
    private int field_4_isMultibyteUnicode;
    private String field_5_sheetname;
    private static final BitField hiddenFlag = BitFieldFactory.getInstance(1);
    private static final BitField veryHiddenFlag = BitFieldFactory.getInstance(2);

    public BoundSheetRecord(String sheetname) {
        this.field_2_option_flags = 0;
        setSheetname(sheetname);
    }

    public BoundSheetRecord(BoundSheetRecord other) {
        super(other);
        this.field_1_position_of_BOF = other.field_1_position_of_BOF;
        this.field_2_option_flags = other.field_2_option_flags;
        this.field_4_isMultibyteUnicode = other.field_4_isMultibyteUnicode;
        this.field_5_sheetname = other.field_5_sheetname;
    }

    public BoundSheetRecord(RecordInputStream in) {
        byte[] buf = new byte[4];
        in.readPlain(buf, 0, buf.length);
        this.field_1_position_of_BOF = LittleEndian.getInt(buf);
        this.field_2_option_flags = in.readUShort();
        int field_3_sheetname_length = in.readUByte();
        this.field_4_isMultibyteUnicode = in.readByte();
        if (isMultibyte()) {
            this.field_5_sheetname = in.readUnicodeLEString(field_3_sheetname_length);
        } else {
            this.field_5_sheetname = in.readCompressedUnicode(field_3_sheetname_length);
        }
    }

    public void setPositionOfBof(int pos) {
        this.field_1_position_of_BOF = pos;
    }

    public void setSheetname(String str) {
        WorkbookUtil.validateSheetName(str);
        this.field_5_sheetname = str;
        this.field_4_isMultibyteUnicode = StringUtil.hasMultibyte(str) ? 1 : 0;
    }

    public int getPositionOfBof() {
        return this.field_1_position_of_BOF;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isMultibyte() {
        return (this.field_4_isMultibyteUnicode & 1) != 0;
    }

    public String getSheetname() {
        return this.field_5_sheetname;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return (this.field_5_sheetname.length() * (isMultibyte() ? 2 : 1)) + 8;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeInt(getPositionOfBof());
        out.writeShort(this.field_2_option_flags);
        String name = this.field_5_sheetname;
        out.writeByte(name.length());
        out.writeByte(this.field_4_isMultibyteUnicode);
        if (isMultibyte()) {
            StringUtil.putUnicodeLE(name, out);
        } else {
            StringUtil.putCompressedUnicode(name, out);
        }
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return (short) 133;
    }

    public boolean isHidden() {
        return hiddenFlag.isSet(this.field_2_option_flags);
    }

    public void setHidden(boolean hidden) {
        this.field_2_option_flags = hiddenFlag.setBoolean(this.field_2_option_flags, hidden);
    }

    public boolean isVeryHidden() {
        return veryHiddenFlag.isSet(this.field_2_option_flags);
    }

    public void setVeryHidden(boolean veryHidden) {
        this.field_2_option_flags = veryHiddenFlag.setBoolean(this.field_2_option_flags, veryHidden);
    }

    public static BoundSheetRecord[] orderByBofPosition(List<BoundSheetRecord> boundSheetRecords) {
        BoundSheetRecord[] bsrs = new BoundSheetRecord[boundSheetRecords.size()];
        boundSheetRecords.toArray(bsrs);
        Arrays.sort(bsrs, new Comparator() { // from class: org.apache.poi.hssf.record.BoundSheetRecord$$ExternalSyntheticLambda0
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int compareRecords;
                compareRecords = BoundSheetRecord.compareRecords((BoundSheetRecord) obj, (BoundSheetRecord) obj2);
                return compareRecords;
            }
        });
        return bsrs;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int compareRecords(BoundSheetRecord bsr1, BoundSheetRecord bsr2) {
        return bsr1.getPositionOfBof() - bsr2.getPositionOfBof();
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public BoundSheetRecord copy() {
        return new BoundSheetRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.BOUND_SHEET;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("bof", new Supplier() { // from class: org.apache.poi.hssf.record.BoundSheetRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(BoundSheetRecord.this.getPositionOfBof());
            }
        }, "optionFlags", new Supplier() { // from class: org.apache.poi.hssf.record.BoundSheetRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return BoundSheetRecord.this.m2255x54146c11();
            }
        }, "multiByte", new Supplier() { // from class: org.apache.poi.hssf.record.BoundSheetRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                boolean isMultibyte;
                isMultibyte = BoundSheetRecord.this.isMultibyte();
                return Boolean.valueOf(isMultibyte);
            }
        }, "sheetName", new Supplier() { // from class: org.apache.poi.hssf.record.BoundSheetRecord$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return BoundSheetRecord.this.getSheetname();
            }
        }, CellUtil.HIDDEN, new Supplier() { // from class: org.apache.poi.hssf.record.BoundSheetRecord$$ExternalSyntheticLambda5
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(BoundSheetRecord.this.isHidden());
            }
        }, "veryHidden", new Supplier() { // from class: org.apache.poi.hssf.record.BoundSheetRecord$$ExternalSyntheticLambda6
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(BoundSheetRecord.this.isVeryHidden());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-BoundSheetRecord, reason: not valid java name */
    public /* synthetic */ Object m2255x54146c11() {
        return Integer.valueOf(this.field_2_option_flags);
    }
}
