package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.common.UnicodeString;
import org.apache.poi.ss.formula.Formula;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.util.BitField;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.StringUtil;

/* loaded from: classes10.dex */
public final class DVRecord extends StandardRecord {
    public static final short sid = 446;
    private final UnicodeString _errorText;
    private final UnicodeString _errorTitle;
    private final Formula _formula1;
    private final Formula _formula2;
    private short _not_used_1;
    private short _not_used_2;
    private int _option_flags;
    private final UnicodeString _promptText;
    private final UnicodeString _promptTitle;
    private final CellRangeAddressList _regions;
    private static final UnicodeString NULL_TEXT_STRING = new UnicodeString("\u0000");
    private static final BitField opt_data_type = new BitField(15);
    private static final BitField opt_error_style = new BitField(112);
    private static final BitField opt_string_list_formula = new BitField(128);
    private static final BitField opt_empty_cell_allowed = new BitField(256);
    private static final BitField opt_suppress_dropdown_arrow = new BitField(512);
    private static final BitField opt_show_prompt_on_cell_selected = new BitField(262144);
    private static final BitField opt_show_error_on_invalid_value = new BitField(524288);
    private static final BitField opt_condition_operator = new BitField(7340032);
    private static final int[] FLAG_MASKS = {15, 112, 128, 256, 512, 262144, 524288, 7340032};
    private static final String[] FLAG_NAMES = {"DATA_TYPE", "ERROR_STYLE", "STRING_LIST_FORMULA", "EMPTY_CELL_ALLOWED", "SUPPRESS_DROPDOWN_ARROW", "SHOW_PROMPT_ON_CELL_SELECTED", "SHOW_ERROR_ON_INVALID_VALUE", "CONDITION_OPERATOR"};

    public DVRecord(DVRecord other) {
        super(other);
        this._not_used_1 = (short) 16352;
        this._not_used_2 = (short) 0;
        this._option_flags = other._option_flags;
        this._promptTitle = other._promptTitle.copy();
        this._errorTitle = other._errorTitle.copy();
        this._promptText = other._promptText.copy();
        this._errorText = other._errorText.copy();
        this._not_used_1 = other._not_used_1;
        this._formula1 = other._formula1 == null ? null : other._formula1.copy();
        this._not_used_2 = other._not_used_2;
        this._formula2 = other._formula2 == null ? null : other._formula2.copy();
        this._regions = other._regions != null ? other._regions.copy() : null;
    }

    public DVRecord(int validationType, int operator, int errorStyle, boolean emptyCellAllowed, boolean suppressDropDownArrow, boolean isExplicitList, boolean showPromptBox, String promptTitle, String promptText, boolean showErrorBox, String errorTitle, String errorText, Ptg[] formula1, Ptg[] formula2, CellRangeAddressList regions) {
        this._not_used_1 = (short) 16352;
        this._not_used_2 = (short) 0;
        if (promptTitle != null && promptTitle.length() > 32) {
            throw new IllegalStateException("Prompt-title cannot be longer than 32 characters, but had: " + promptTitle);
        }
        if (promptText != null && promptText.length() > 255) {
            throw new IllegalStateException("Prompt-text cannot be longer than 255 characters, but had: " + promptText);
        }
        if (errorTitle != null && errorTitle.length() > 32) {
            throw new IllegalStateException("Error-title cannot be longer than 32 characters, but had: " + errorTitle);
        }
        if (errorText != null && errorText.length() > 255) {
            throw new IllegalStateException("Error-text cannot be longer than 255 characters, but had: " + errorText);
        }
        int flags = opt_data_type.setValue(0, validationType);
        this._option_flags = opt_show_error_on_invalid_value.setBoolean(opt_show_prompt_on_cell_selected.setBoolean(opt_string_list_formula.setBoolean(opt_suppress_dropdown_arrow.setBoolean(opt_empty_cell_allowed.setBoolean(opt_error_style.setValue(opt_condition_operator.setValue(flags, operator), errorStyle), emptyCellAllowed), suppressDropDownArrow), isExplicitList), showPromptBox), showErrorBox);
        this._promptTitle = resolveTitleText(promptTitle);
        this._promptText = resolveTitleText(promptText);
        this._errorTitle = resolveTitleText(errorTitle);
        this._errorText = resolveTitleText(errorText);
        this._formula1 = Formula.create(formula1);
        this._formula2 = Formula.create(formula2);
        this._regions = regions;
    }

    public DVRecord(RecordInputStream in) {
        this._not_used_1 = (short) 16352;
        this._not_used_2 = (short) 0;
        this._option_flags = in.readInt();
        this._promptTitle = readUnicodeString(in);
        this._errorTitle = readUnicodeString(in);
        this._promptText = readUnicodeString(in);
        this._errorText = readUnicodeString(in);
        int field_size_first_formula = in.readUShort();
        this._not_used_1 = in.readShort();
        this._formula1 = Formula.read(field_size_first_formula, in);
        int field_size_sec_formula = in.readUShort();
        this._not_used_2 = in.readShort();
        this._formula2 = Formula.read(field_size_sec_formula, in);
        this._regions = new CellRangeAddressList(in);
    }

    public int getDataType() {
        return opt_data_type.getValue(this._option_flags);
    }

    public int getErrorStyle() {
        return opt_error_style.getValue(this._option_flags);
    }

    public boolean getListExplicitFormula() {
        return opt_string_list_formula.isSet(this._option_flags);
    }

    public boolean getEmptyCellAllowed() {
        return opt_empty_cell_allowed.isSet(this._option_flags);
    }

    public boolean getSuppressDropdownArrow() {
        return opt_suppress_dropdown_arrow.isSet(this._option_flags);
    }

    public boolean getShowPromptOnCellSelected() {
        return opt_show_prompt_on_cell_selected.isSet(this._option_flags);
    }

    public boolean getShowErrorOnInvalidValue() {
        return opt_show_error_on_invalid_value.isSet(this._option_flags);
    }

    public int getConditionOperator() {
        return opt_condition_operator.getValue(this._option_flags);
    }

    public String getPromptTitle() {
        return resolveTitleString(this._promptTitle);
    }

    public String getErrorTitle() {
        return resolveTitleString(this._errorTitle);
    }

    public String getPromptText() {
        return resolveTitleString(this._promptText);
    }

    public String getErrorText() {
        return resolveTitleString(this._errorText);
    }

    public Ptg[] getFormula1() {
        return Formula.getTokens(this._formula1);
    }

    public Ptg[] getFormula2() {
        return Formula.getTokens(this._formula2);
    }

    public CellRangeAddressList getCellRangeAddress() {
        return this._regions;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeInt(this._option_flags);
        serializeUnicodeString(this._promptTitle, out);
        serializeUnicodeString(this._errorTitle, out);
        serializeUnicodeString(this._promptText, out);
        serializeUnicodeString(this._errorText, out);
        out.writeShort(this._formula1.getEncodedTokenSize());
        out.writeShort(this._not_used_1);
        this._formula1.serializeTokens(out);
        out.writeShort(this._formula2.getEncodedTokenSize());
        out.writeShort(this._not_used_2);
        this._formula2.serializeTokens(out);
        this._regions.serialize(out);
    }

    private static UnicodeString resolveTitleText(String str) {
        if (str == null || str.length() < 1) {
            return NULL_TEXT_STRING;
        }
        return new UnicodeString(str);
    }

    private static String resolveTitleString(UnicodeString us) {
        if (us == null || us.equals(NULL_TEXT_STRING)) {
            return null;
        }
        return us.getString();
    }

    private static UnicodeString readUnicodeString(RecordInputStream in) {
        return new UnicodeString(in);
    }

    private static void serializeUnicodeString(UnicodeString us, LittleEndianOutput out) {
        StringUtil.writeUnicodeString(out, us.getString());
    }

    private static int getUnicodeStringSize(UnicodeString us) {
        String str = us.getString();
        return (str.length() * (StringUtil.hasMultibyte(str) ? 2 : 1)) + 3;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        int size = 12 + getUnicodeStringSize(this._promptTitle);
        return size + getUnicodeStringSize(this._errorTitle) + getUnicodeStringSize(this._promptText) + getUnicodeStringSize(this._errorText) + this._formula1.getEncodedTokenSize() + this._formula2.getEncodedTokenSize() + this._regions.getSize();
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return sid;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public DVRecord copy() {
        return new DVRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.DV;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("optionFlags", GenericRecordUtil.getBitsAsString((Supplier<Number>) new Supplier() { // from class: org.apache.poi.hssf.record.DVRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return DVRecord.this.m2275x886b5c42();
            }
        }, FLAG_MASKS, FLAG_NAMES), "promptTitle", new Supplier() { // from class: org.apache.poi.hssf.record.DVRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return DVRecord.this.getPromptTitle();
            }
        }, "errorTitle", new Supplier() { // from class: org.apache.poi.hssf.record.DVRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return DVRecord.this.getErrorTitle();
            }
        }, "promptText", new Supplier() { // from class: org.apache.poi.hssf.record.DVRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return DVRecord.this.getPromptText();
            }
        }, "errorText", new Supplier() { // from class: org.apache.poi.hssf.record.DVRecord$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return DVRecord.this.getErrorText();
            }
        }, "formula1", new Supplier() { // from class: org.apache.poi.hssf.record.DVRecord$$ExternalSyntheticLambda5
            @Override // java.util.function.Supplier
            public final Object get() {
                return DVRecord.this.getFormula1();
            }
        }, "formula2", new Supplier() { // from class: org.apache.poi.hssf.record.DVRecord$$ExternalSyntheticLambda6
            @Override // java.util.function.Supplier
            public final Object get() {
                return DVRecord.this.getFormula2();
            }
        }, "regions", new Supplier() { // from class: org.apache.poi.hssf.record.DVRecord$$ExternalSyntheticLambda7
            @Override // java.util.function.Supplier
            public final Object get() {
                return DVRecord.this.m2276x9c132fc3();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-DVRecord, reason: not valid java name */
    public /* synthetic */ Number m2275x886b5c42() {
        return Integer.valueOf(this._option_flags);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-DVRecord, reason: not valid java name */
    public /* synthetic */ Object m2276x9c132fc3() {
        return this._regions;
    }
}
