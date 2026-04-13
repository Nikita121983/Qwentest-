package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import kotlin.jvm.internal.ShortCompanionObject;
import org.apache.poi.ss.formula.Formula;
import org.apache.poi.ss.formula.constant.ConstantValueParser;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.StringUtil;

/* loaded from: classes10.dex */
public final class ExternalNameRecord extends StandardRecord {
    private static final int[] OPTION_FLAGS = {1, 2, 4, 8, 16, 32768};
    private static final String[] OPTION_NAMES = {"BUILTIN_NAME", "AUTOMATIC_LINK", "PICTURE_LINK", "STD_DOCUMENT_NAME", "OLE_LINK", "ICONIFIED_PICTURE_LINK"};
    private static final int OPT_AUTOMATIC_LINK = 2;
    private static final int OPT_BUILTIN_NAME = 1;
    private static final int OPT_ICONIFIED_PICTURE_LINK = 32768;
    private static final int OPT_OLE_LINK = 16;
    private static final int OPT_PICTURE_LINK = 4;
    private static final int OPT_STD_DOCUMENT_NAME = 8;
    public static final short sid = 35;
    private Object[] _ddeValues;
    private int _nColumns;
    private int _nRows;
    private short field_1_option_flag;
    private short field_2_ixals;
    private short field_3_not_used;
    private String field_4_name;
    private Formula field_5_name_definition;

    public ExternalNameRecord() {
        this.field_2_ixals = (short) 0;
    }

    public ExternalNameRecord(ExternalNameRecord other) {
        super(other);
        this.field_1_option_flag = other.field_1_option_flag;
        this.field_2_ixals = other.field_2_ixals;
        this.field_3_not_used = other.field_3_not_used;
        this.field_4_name = other.field_4_name;
        this.field_5_name_definition = other.field_5_name_definition == null ? null : other.field_5_name_definition.copy();
        this._ddeValues = other._ddeValues != null ? (Object[]) other._ddeValues.clone() : null;
        this._nColumns = other._nColumns;
        this._nRows = other._nRows;
    }

    public ExternalNameRecord(RecordInputStream in) {
        this.field_1_option_flag = in.readShort();
        this.field_2_ixals = in.readShort();
        this.field_3_not_used = in.readShort();
        int numChars = in.readUByte();
        this.field_4_name = StringUtil.readUnicodeString(in, numChars);
        if (!isOLELink() && !isStdDocumentNameIdentifier()) {
            if (isAutomaticLink()) {
                if (in.available() > 0) {
                    int nColumns = in.readUByte() + 1;
                    int nRows = in.readShort() + 1;
                    int totalCount = nRows * nColumns;
                    this._ddeValues = ConstantValueParser.parse(in, totalCount);
                    this._nColumns = nColumns;
                    this._nRows = nRows;
                    return;
                }
                return;
            }
            int formulaLen = in.readUShort();
            this.field_5_name_definition = Formula.read(formulaLen, in);
        }
    }

    public boolean isBuiltInName() {
        return (this.field_1_option_flag & 1) != 0;
    }

    public boolean isAutomaticLink() {
        return (this.field_1_option_flag & 2) != 0;
    }

    public boolean isPicureLink() {
        return (this.field_1_option_flag & 4) != 0;
    }

    public boolean isStdDocumentNameIdentifier() {
        return (this.field_1_option_flag & 8) != 0;
    }

    public boolean isOLELink() {
        return (this.field_1_option_flag & 16) != 0;
    }

    public boolean isIconifiedPictureLink() {
        return (this.field_1_option_flag & ShortCompanionObject.MIN_VALUE) != 0;
    }

    public String getText() {
        return this.field_4_name;
    }

    public void setText(String str) {
        this.field_4_name = str;
    }

    public short getIx() {
        return this.field_2_ixals;
    }

    public void setIx(short ix) {
        this.field_2_ixals = ix;
    }

    public Ptg[] getParsedExpression() {
        return Formula.getTokens(this.field_5_name_definition);
    }

    public void setParsedExpression(Ptg[] ptgs) {
        this.field_5_name_definition = Formula.create(ptgs);
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        int result = 6 + (StringUtil.getEncodedSize(this.field_4_name) - 1);
        if (!isOLELink() && !isStdDocumentNameIdentifier()) {
            if (isAutomaticLink()) {
                if (this._ddeValues != null) {
                    return result + 3 + ConstantValueParser.getEncodedSize(this._ddeValues);
                }
                return result;
            }
            return result + this.field_5_name_definition.getEncodedSize();
        }
        return result;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.field_1_option_flag);
        out.writeShort(this.field_2_ixals);
        out.writeShort(this.field_3_not_used);
        out.writeByte(this.field_4_name.length());
        StringUtil.writeUnicodeStringFlagAndData(out, this.field_4_name);
        if (!isOLELink() && !isStdDocumentNameIdentifier()) {
            if (isAutomaticLink()) {
                if (this._ddeValues != null) {
                    out.writeByte(this._nColumns - 1);
                    out.writeShort(this._nRows - 1);
                    ConstantValueParser.encode(out, this._ddeValues);
                    return;
                }
                return;
            }
            this.field_5_name_definition.serialize(out);
        }
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return (short) 35;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public ExternalNameRecord copy() {
        return new ExternalNameRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.EXTERNAL_NAME;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v4, types: [org.apache.poi.hssf.record.ExternalNameRecord$$ExternalSyntheticLambda3] */
    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        ExternalNameRecord$$ExternalSyntheticLambda4 externalNameRecord$$ExternalSyntheticLambda4;
        Supplier<GenericRecordUtil.AnnotatedFlag> bitsAsString = GenericRecordUtil.getBitsAsString((Supplier<Number>) new Supplier() { // from class: org.apache.poi.hssf.record.ExternalNameRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return ExternalNameRecord.this.m2296xfbe40706();
            }
        }, OPTION_FLAGS, OPTION_NAMES);
        Supplier supplier = new Supplier() { // from class: org.apache.poi.hssf.record.ExternalNameRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(ExternalNameRecord.this.getIx());
            }
        };
        Supplier supplier2 = new Supplier() { // from class: org.apache.poi.hssf.record.ExternalNameRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return ExternalNameRecord.this.getText();
            }
        };
        if (this.field_5_name_definition == null) {
            externalNameRecord$$ExternalSyntheticLambda4 = new Supplier() { // from class: org.apache.poi.hssf.record.ExternalNameRecord$$ExternalSyntheticLambda3
                @Override // java.util.function.Supplier
                public final Object get() {
                    return ExternalNameRecord.lambda$getGenericProperties$1();
                }
            };
        } else {
            Formula formula = this.field_5_name_definition;
            formula.getClass();
            externalNameRecord$$ExternalSyntheticLambda4 = new ExternalNameRecord$$ExternalSyntheticLambda4(formula);
        }
        return GenericRecordUtil.getGenericProperties("options", bitsAsString, "ix", supplier, "name", supplier2, "nameDefinition", externalNameRecord$$ExternalSyntheticLambda4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-ExternalNameRecord, reason: not valid java name */
    public /* synthetic */ Number m2296xfbe40706() {
        return Short.valueOf(this.field_1_option_flag);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object lambda$getGenericProperties$1() {
        return null;
    }
}
