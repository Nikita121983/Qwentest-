package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.RecordFormatException;

/* loaded from: classes10.dex */
public final class OldLabelRecord extends OldCellRecord {
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) OldLabelRecord.class);
    public static final short biff2_sid = 4;
    public static final short biff345_sid = 516;
    private CodepageRecord codepage;
    private short field_4_string_len;
    private final byte[] field_5_bytes;

    public OldLabelRecord(RecordInputStream in) {
        super(in, in.getSid() == 4);
        if (isBiff2()) {
            this.field_4_string_len = (short) in.readUByte();
        } else {
            this.field_4_string_len = in.readShort();
        }
        this.field_5_bytes = IOUtils.safelyAllocate(this.field_4_string_len, HSSFWorkbook.getMaxRecordLength());
        in.read(this.field_5_bytes, 0, this.field_4_string_len);
        if (in.remaining() > 0) {
            LOG.atInfo().log("LabelRecord data remains: {} : {}", Unbox.box(in.remaining()), HexDump.toHex(in.readRemainder()));
        }
    }

    public void setCodePage(CodepageRecord codepage) {
        this.codepage = codepage;
    }

    public short getStringLength() {
        return this.field_4_string_len;
    }

    public String getValue() {
        return OldStringRecord.getString(this.field_5_bytes, this.codepage);
    }

    public int serialize(int offset, byte[] data) {
        throw new RecordFormatException("Old Label Records are supported READ ONLY");
    }

    public int getRecordSize() {
        throw new RecordFormatException("Old Label Records are supported READ ONLY");
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.LABEL;
    }

    @Override // org.apache.poi.hssf.record.OldCellRecord, org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", new Supplier() { // from class: org.apache.poi.hssf.record.OldLabelRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return OldLabelRecord.this.m2347x63256d5d();
            }
        }, "stringLength", new Supplier() { // from class: org.apache.poi.hssf.record.OldLabelRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(OldLabelRecord.this.getStringLength());
            }
        }, "value", new Supplier() { // from class: org.apache.poi.hssf.record.OldLabelRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return OldLabelRecord.this.getValue();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-OldLabelRecord, reason: not valid java name */
    public /* synthetic */ Object m2347x63256d5d() {
        return super.getGenericProperties();
    }
}
