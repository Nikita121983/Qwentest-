package org.apache.poi.hssf.record;

import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import java.io.IOException;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.util.GenericRecordJsonWriter;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.RecordFormatException;

/* loaded from: classes10.dex */
public final class OldSheetRecord implements GenericRecord {
    public static final short sid = 133;
    private CodepageRecord codepage;
    private final int field_1_position_of_BOF;
    private final int field_2_visibility;
    private final int field_3_type;
    private final byte[] field_5_sheetname;

    public OldSheetRecord(RecordInputStream in) {
        this.field_1_position_of_BOF = in.readInt();
        this.field_2_visibility = in.readUByte();
        this.field_3_type = in.readUByte();
        int field_4_sheetname_length = in.readUByte();
        if (field_4_sheetname_length > 0) {
            in.mark(1);
            byte b = in.readByte();
            if (b != 0) {
                try {
                    in.reset();
                } catch (IOException e) {
                    throw new RecordFormatException(e);
                }
            }
        }
        this.field_5_sheetname = IOUtils.safelyAllocate(field_4_sheetname_length, HSSFWorkbook.getMaxRecordLength());
        in.read(this.field_5_sheetname, 0, field_4_sheetname_length);
    }

    public void setCodePage(CodepageRecord codepage) {
        this.codepage = codepage;
    }

    public short getSid() {
        return (short) 133;
    }

    public int getPositionOfBof() {
        return this.field_1_position_of_BOF;
    }

    public String getSheetname() {
        return OldStringRecord.getString(this.field_5_sheetname, this.codepage);
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.BOUND_SHEET;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("bof", new Supplier() { // from class: org.apache.poi.hssf.record.OldSheetRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(OldSheetRecord.this.getPositionOfBof());
            }
        }, "visibility", new Supplier() { // from class: org.apache.poi.hssf.record.OldSheetRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return OldSheetRecord.this.m2348x87678f88();
            }
        }, ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, new Supplier() { // from class: org.apache.poi.hssf.record.OldSheetRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return OldSheetRecord.this.m2349x6ca8fe49();
            }
        }, "sheetName", new Supplier() { // from class: org.apache.poi.hssf.record.OldSheetRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return OldSheetRecord.this.getSheetname();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-OldSheetRecord, reason: not valid java name */
    public /* synthetic */ Object m2348x87678f88() {
        return Integer.valueOf(this.field_2_visibility);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-OldSheetRecord, reason: not valid java name */
    public /* synthetic */ Object m2349x6ca8fe49() {
        return Integer.valueOf(this.field_3_type);
    }

    public String toString() {
        return GenericRecordJsonWriter.marshal(this);
    }
}
