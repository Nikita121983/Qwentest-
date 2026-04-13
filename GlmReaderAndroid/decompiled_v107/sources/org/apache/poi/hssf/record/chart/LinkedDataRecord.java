package org.apache.poi.hssf.record.chart;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.ss.formula.Formula;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class LinkedDataRecord extends StandardRecord {
    public static final byte LINK_TYPE_CATEGORIES = 2;
    public static final byte LINK_TYPE_SECONDARY_CATEGORIES = 3;
    public static final byte LINK_TYPE_TITLE_OR_TEXT = 0;
    public static final byte LINK_TYPE_VALUES = 1;
    public static final byte REFERENCE_TYPE_DEFAULT_CATEGORIES = 0;
    public static final byte REFERENCE_TYPE_DIRECT = 1;
    public static final byte REFERENCE_TYPE_ERROR_REPORTED = 4;
    public static final byte REFERENCE_TYPE_NOT_USED = 3;
    public static final byte REFERENCE_TYPE_WORKSHEET = 2;
    private static final BitField customNumberFormat = BitFieldFactory.getInstance(1);
    public static final short sid = 4177;
    private byte field_1_linkType;
    private byte field_2_referenceType;
    private short field_3_options;
    private short field_4_indexNumberFmtRecord;
    private Formula field_5_formulaOfLink;

    public LinkedDataRecord() {
    }

    public LinkedDataRecord(LinkedDataRecord other) {
        super(other);
        this.field_1_linkType = other.field_1_linkType;
        this.field_2_referenceType = other.field_2_referenceType;
        this.field_3_options = other.field_3_options;
        this.field_4_indexNumberFmtRecord = other.field_4_indexNumberFmtRecord;
        this.field_5_formulaOfLink = other.field_5_formulaOfLink == null ? null : other.field_5_formulaOfLink.copy();
    }

    public LinkedDataRecord(RecordInputStream in) {
        this.field_1_linkType = in.readByte();
        this.field_2_referenceType = in.readByte();
        this.field_3_options = in.readShort();
        this.field_4_indexNumberFmtRecord = in.readShort();
        int encodedTokenLen = in.readUShort();
        this.field_5_formulaOfLink = Formula.read(encodedTokenLen, in);
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeByte(this.field_1_linkType);
        out.writeByte(this.field_2_referenceType);
        out.writeShort(this.field_3_options);
        out.writeShort(this.field_4_indexNumberFmtRecord);
        this.field_5_formulaOfLink.serialize(out);
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return this.field_5_formulaOfLink.getEncodedSize() + 6;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return sid;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public LinkedDataRecord copy() {
        return new LinkedDataRecord(this);
    }

    public byte getLinkType() {
        return this.field_1_linkType;
    }

    public void setLinkType(byte field_1_linkType) {
        this.field_1_linkType = field_1_linkType;
    }

    public byte getReferenceType() {
        return this.field_2_referenceType;
    }

    public void setReferenceType(byte field_2_referenceType) {
        this.field_2_referenceType = field_2_referenceType;
    }

    public short getOptions() {
        return this.field_3_options;
    }

    public void setOptions(short field_3_options) {
        this.field_3_options = field_3_options;
    }

    public short getIndexNumberFmtRecord() {
        return this.field_4_indexNumberFmtRecord;
    }

    public void setIndexNumberFmtRecord(short field_4_indexNumberFmtRecord) {
        this.field_4_indexNumberFmtRecord = field_4_indexNumberFmtRecord;
    }

    public Ptg[] getFormulaOfLink() {
        return this.field_5_formulaOfLink.getTokens();
    }

    public void setFormulaOfLink(Ptg[] ptgs) {
        this.field_5_formulaOfLink = Formula.create(ptgs);
    }

    public void setCustomNumberFormat(boolean value) {
        this.field_3_options = customNumberFormat.setShortBoolean(this.field_3_options, value);
    }

    public boolean isCustomNumberFormat() {
        return customNumberFormat.isSet(this.field_3_options);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.LINKED_DATA;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("linkType", GenericRecordUtil.getEnumBitsAsString(new Supplier() { // from class: org.apache.poi.hssf.record.chart.LinkedDataRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Byte.valueOf(LinkedDataRecord.this.getLinkType());
            }
        }, new int[]{0, 1, 2, 3}, new String[]{"TITLE_OR_TEXT", "VALUES", "CATEGORIES", "SECONDARY_CATEGORIES"}), "referenceType", GenericRecordUtil.getEnumBitsAsString(new Supplier() { // from class: org.apache.poi.hssf.record.chart.LinkedDataRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Byte.valueOf(LinkedDataRecord.this.getReferenceType());
            }
        }, new int[]{0, 1, 2, 3, 4}, new String[]{"DEFAULT_CATEGORIES", "DIRECT", "WORKSHEET", "NOT_USED", "ERROR_REPORTED"}), "options", new Supplier() { // from class: org.apache.poi.hssf.record.chart.LinkedDataRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(LinkedDataRecord.this.getOptions());
            }
        }, "customNumberFormat", new Supplier() { // from class: org.apache.poi.hssf.record.chart.LinkedDataRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(LinkedDataRecord.this.isCustomNumberFormat());
            }
        }, "indexNumberFmtRecord", new Supplier() { // from class: org.apache.poi.hssf.record.chart.LinkedDataRecord$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(LinkedDataRecord.this.getIndexNumberFmtRecord());
            }
        }, "formulaOfLink", new Supplier() { // from class: org.apache.poi.hssf.record.chart.LinkedDataRecord$$ExternalSyntheticLambda5
            @Override // java.util.function.Supplier
            public final Object get() {
                return LinkedDataRecord.this.m2442x5f64b842();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-chart-LinkedDataRecord, reason: not valid java name */
    public /* synthetic */ Object m2442x5f64b842() {
        return this.field_5_formulaOfLink;
    }
}
