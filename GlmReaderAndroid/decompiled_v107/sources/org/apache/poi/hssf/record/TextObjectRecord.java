package org.apache.poi.hssf.record;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.cont.ContinuableRecord;
import org.apache.poi.hssf.record.cont.ContinuableRecordOutput;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.ss.formula.ptg.OperandPtg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.RecordFormatException;

/* loaded from: classes10.dex */
public final class TextObjectRecord extends ContinuableRecord {
    private static final int FORMAT_RUN_ENCODED_SIZE = 8;
    public static final short HORIZONTAL_TEXT_ALIGNMENT_CENTERED = 2;
    public static final short HORIZONTAL_TEXT_ALIGNMENT_JUSTIFIED = 4;
    public static final short HORIZONTAL_TEXT_ALIGNMENT_LEFT_ALIGNED = 1;
    public static final short HORIZONTAL_TEXT_ALIGNMENT_RIGHT_ALIGNED = 3;
    public static final short TEXT_ORIENTATION_NONE = 0;
    public static final short TEXT_ORIENTATION_ROT_LEFT = 3;
    public static final short TEXT_ORIENTATION_ROT_RIGHT = 2;
    public static final short TEXT_ORIENTATION_TOP_TO_BOTTOM = 1;
    public static final short VERTICAL_TEXT_ALIGNMENT_BOTTOM = 3;
    public static final short VERTICAL_TEXT_ALIGNMENT_CENTER = 2;
    public static final short VERTICAL_TEXT_ALIGNMENT_JUSTIFY = 4;
    public static final short VERTICAL_TEXT_ALIGNMENT_TOP = 1;
    public static final short sid = 438;
    private OperandPtg _linkRefPtg;
    private HSSFRichTextString _text;
    private Byte _unknownPostFormulaByte;
    private int _unknownPreFormulaInt;
    private int field_1_options;
    private int field_2_textOrientation;
    private int field_3_reserved4;
    private int field_4_reserved5;
    private int field_5_reserved6;
    private int field_8_reserved7;
    private static final BitField HorizontalTextAlignment = BitFieldFactory.getInstance(14);
    private static final BitField VerticalTextAlignment = BitFieldFactory.getInstance(112);
    private static final BitField textLocked = BitFieldFactory.getInstance(512);

    public TextObjectRecord() {
    }

    public TextObjectRecord(TextObjectRecord other) {
        super(other);
        this.field_1_options = other.field_1_options;
        this.field_2_textOrientation = other.field_2_textOrientation;
        this.field_3_reserved4 = other.field_3_reserved4;
        this.field_4_reserved5 = other.field_4_reserved5;
        this.field_5_reserved6 = other.field_5_reserved6;
        this.field_8_reserved7 = other.field_8_reserved7;
        this._text = other._text;
        if (other._linkRefPtg != null) {
            this._unknownPreFormulaInt = other._unknownPreFormulaInt;
            this._linkRefPtg = other._linkRefPtg.copy();
            this._unknownPostFormulaByte = other._unknownPostFormulaByte;
        }
    }

    public TextObjectRecord(RecordInputStream in) {
        String text;
        this.field_1_options = in.readUShort();
        this.field_2_textOrientation = in.readUShort();
        this.field_3_reserved4 = in.readUShort();
        this.field_4_reserved5 = in.readUShort();
        this.field_5_reserved6 = in.readUShort();
        int field_6_textLength = in.readUShort();
        int field_7_formattingDataLength = in.readUShort();
        this.field_8_reserved7 = in.readInt();
        if (in.remaining() > 0) {
            if (in.remaining() < 11) {
                throw new RecordFormatException("Not enough remaining data for a link formula");
            }
            int formulaSize = in.readUShort();
            this._unknownPreFormulaInt = in.readInt();
            Ptg[] ptgs = Ptg.readTokens(formulaSize, in);
            if (ptgs.length != 1) {
                throw new RecordFormatException("Read " + ptgs.length + " tokens but expected exactly 1");
            }
            if (!(ptgs[0] instanceof OperandPtg)) {
                throw new IllegalArgumentException("Had unexpected type of ptg at index 0: " + ptgs[0].getClass());
            }
            this._linkRefPtg = (OperandPtg) ptgs[0];
            this._unknownPostFormulaByte = in.remaining() > 0 ? Byte.valueOf(in.readByte()) : null;
        } else {
            this._linkRefPtg = null;
        }
        if (in.remaining() > 0) {
            throw new RecordFormatException("Unused " + in.remaining() + " bytes at end of record");
        }
        if (field_6_textLength > 0) {
            text = readRawString(in, field_6_textLength);
        } else {
            text = "";
        }
        this._text = new HSSFRichTextString(text);
        if (field_7_formattingDataLength > 0) {
            processFontRuns(in, this._text, field_7_formattingDataLength);
        }
    }

    private static String readRawString(RecordInputStream in, int textLength) {
        byte compressByte = in.readByte();
        boolean isCompressed = (compressByte & 1) == 0;
        if (isCompressed) {
            return in.readCompressedUnicode(textLength);
        }
        return in.readUnicodeLEString(textLength);
    }

    private static void processFontRuns(RecordInputStream in, HSSFRichTextString str, int formattingRunDataLength) {
        if (formattingRunDataLength % 8 != 0) {
            throw new RecordFormatException("Bad format run data length " + formattingRunDataLength + ")");
        }
        int nRuns = formattingRunDataLength / 8;
        for (int i = 0; i < nRuns; i++) {
            short index = in.readShort();
            short iFont = in.readShort();
            in.readInt();
            str.applyFont(index, str.length(), iFont);
        }
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return sid;
    }

    private void serializeTXORecord(ContinuableRecordOutput out) {
        out.writeShort(this.field_1_options);
        out.writeShort(this.field_2_textOrientation);
        out.writeShort(this.field_3_reserved4);
        out.writeShort(this.field_4_reserved5);
        out.writeShort(this.field_5_reserved6);
        out.writeShort(this._text.length());
        out.writeShort(getFormattingDataLength());
        out.writeInt(this.field_8_reserved7);
        if (this._linkRefPtg != null) {
            int formulaSize = this._linkRefPtg.getSize();
            out.writeShort(formulaSize);
            out.writeInt(this._unknownPreFormulaInt);
            this._linkRefPtg.write(out);
            if (this._unknownPostFormulaByte != null) {
                out.writeByte(this._unknownPostFormulaByte.byteValue());
            }
        }
    }

    private void serializeTrailingRecords(ContinuableRecordOutput out) {
        out.writeContinue();
        out.writeStringData(this._text.getString());
        out.writeContinue();
        writeFormatData(out, this._text);
    }

    @Override // org.apache.poi.hssf.record.cont.ContinuableRecord
    protected void serialize(ContinuableRecordOutput out) {
        serializeTXORecord(out);
        if (!this._text.getString().isEmpty()) {
            serializeTrailingRecords(out);
        }
    }

    private int getFormattingDataLength() {
        if (this._text.length() < 1) {
            return 0;
        }
        return (this._text.numFormattingRuns() + 1) * 8;
    }

    private static void writeFormatData(ContinuableRecordOutput out, HSSFRichTextString str) {
        int nRuns = str.numFormattingRuns();
        for (int i = 0; i < nRuns; i++) {
            out.writeShort(str.getIndexOfFormattingRun(i));
            int fontIndex = str.getFontOfFormattingRun(i);
            out.writeShort(fontIndex == 0 ? 0 : fontIndex);
            out.writeInt(0);
        }
        int i2 = str.length();
        out.writeShort(i2);
        out.writeShort(0);
        out.writeInt(0);
    }

    public void setHorizontalTextAlignment(int value) {
        this.field_1_options = HorizontalTextAlignment.setValue(this.field_1_options, value);
    }

    public int getHorizontalTextAlignment() {
        return HorizontalTextAlignment.getValue(this.field_1_options);
    }

    public void setVerticalTextAlignment(int value) {
        this.field_1_options = VerticalTextAlignment.setValue(this.field_1_options, value);
    }

    public int getVerticalTextAlignment() {
        return VerticalTextAlignment.getValue(this.field_1_options);
    }

    public void setTextLocked(boolean value) {
        this.field_1_options = textLocked.setBoolean(this.field_1_options, value);
    }

    public boolean isTextLocked() {
        return textLocked.isSet(this.field_1_options);
    }

    public int getTextOrientation() {
        return this.field_2_textOrientation;
    }

    public void setTextOrientation(int textOrientation) {
        this.field_2_textOrientation = textOrientation;
    }

    public HSSFRichTextString getStr() {
        return this._text;
    }

    public void setStr(HSSFRichTextString str) {
        this._text = str;
    }

    public Ptg getLinkRefPtg() {
        return this._linkRefPtg;
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public TextObjectRecord copy() {
        return new TextObjectRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.TEXT_OBJECT;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        Map<String, Supplier<?>> m = new LinkedHashMap<>();
        m.put("isHorizontal", new Supplier() { // from class: org.apache.poi.hssf.record.TextObjectRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(TextObjectRecord.this.getHorizontalTextAlignment());
            }
        });
        m.put("isVertical", new Supplier() { // from class: org.apache.poi.hssf.record.TextObjectRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(TextObjectRecord.this.getVerticalTextAlignment());
            }
        });
        m.put("textLocked", new Supplier() { // from class: org.apache.poi.hssf.record.TextObjectRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(TextObjectRecord.this.isTextLocked());
            }
        });
        m.put("textOrientation", new Supplier() { // from class: org.apache.poi.hssf.record.TextObjectRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(TextObjectRecord.this.getTextOrientation());
            }
        });
        m.put(TypedValues.Custom.S_STRING, new Supplier() { // from class: org.apache.poi.hssf.record.TextObjectRecord$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return TextObjectRecord.this.getStr();
            }
        });
        m.put("reserved4", new Supplier() { // from class: org.apache.poi.hssf.record.TextObjectRecord$$ExternalSyntheticLambda5
            @Override // java.util.function.Supplier
            public final Object get() {
                return TextObjectRecord.this.m2388x1b13d41c();
            }
        });
        m.put("reserved5", new Supplier() { // from class: org.apache.poi.hssf.record.TextObjectRecord$$ExternalSyntheticLambda6
            @Override // java.util.function.Supplier
            public final Object get() {
                return TextObjectRecord.this.m2389xb5b4969d();
            }
        });
        m.put("reserved6", new Supplier() { // from class: org.apache.poi.hssf.record.TextObjectRecord$$ExternalSyntheticLambda7
            @Override // java.util.function.Supplier
            public final Object get() {
                return TextObjectRecord.this.m2390x5055591e();
            }
        });
        m.put("reserved7", new Supplier() { // from class: org.apache.poi.hssf.record.TextObjectRecord$$ExternalSyntheticLambda8
            @Override // java.util.function.Supplier
            public final Object get() {
                return TextObjectRecord.this.m2391xeaf61b9f();
            }
        });
        return Collections.unmodifiableMap(m);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-TextObjectRecord, reason: not valid java name */
    public /* synthetic */ Object m2388x1b13d41c() {
        return Integer.valueOf(this.field_3_reserved4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-TextObjectRecord, reason: not valid java name */
    public /* synthetic */ Object m2389xb5b4969d() {
        return Integer.valueOf(this.field_4_reserved5);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$2$org-apache-poi-hssf-record-TextObjectRecord, reason: not valid java name */
    public /* synthetic */ Object m2390x5055591e() {
        return Integer.valueOf(this.field_5_reserved6);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$3$org-apache-poi-hssf-record-TextObjectRecord, reason: not valid java name */
    public /* synthetic */ Object m2391xeaf61b9f() {
        return Integer.valueOf(this.field_8_reserved7);
    }
}
