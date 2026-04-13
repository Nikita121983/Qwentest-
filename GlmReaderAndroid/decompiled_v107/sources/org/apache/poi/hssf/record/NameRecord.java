package org.apache.poi.hssf.record;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.cont.ContinuableRecord;
import org.apache.poi.hssf.record.cont.ContinuableRecordOutput;
import org.apache.poi.ss.formula.Formula;
import org.apache.poi.ss.formula.ptg.Area3DPtg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.formula.ptg.Ref3DPtg;
import org.apache.poi.util.LittleEndianByteArrayInputStream;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.StringUtil;

/* loaded from: classes10.dex */
public final class NameRecord extends ContinuableRecord {
    public static final byte BUILTIN_AUTO_ACTIVATE = 10;
    public static final byte BUILTIN_AUTO_CLOSE = 3;
    public static final byte BUILTIN_AUTO_DEACTIVATE = 11;
    public static final byte BUILTIN_AUTO_OPEN = 2;
    public static final byte BUILTIN_CONSOLIDATE_AREA = 1;
    public static final byte BUILTIN_CRITERIA = 5;
    public static final byte BUILTIN_DATABASE = 4;
    public static final byte BUILTIN_DATA_FORM = 9;
    public static final byte BUILTIN_FILTER_DB = 13;
    public static final byte BUILTIN_PRINT_AREA = 6;
    public static final byte BUILTIN_PRINT_TITLE = 7;
    public static final byte BUILTIN_RECORDER = 8;
    public static final byte BUILTIN_SHEET_TITLE = 12;
    public static final short sid = 24;
    private boolean field_11_nameIsMultibyte;
    private byte field_12_built_in_code;
    private String field_12_name_text;
    private Formula field_13_name_definition;
    private String field_14_custom_menu_text;
    private String field_15_description_text;
    private String field_16_help_topic_text;
    private String field_17_status_bar_text;
    private short field_1_option_flag;
    private byte field_2_keyboard_shortcut;
    private short field_5_externSheetIndex_plus1;
    private int field_6_sheetNumber;

    /* loaded from: classes10.dex */
    private static final class Option {
        public static final int OPT_BINDATA = 4096;
        public static final int OPT_BUILTIN = 32;
        public static final int OPT_COMMAND_NAME = 4;
        public static final int OPT_COMPLEX = 16;
        public static final int OPT_FUNCTION_NAME = 2;
        public static final int OPT_HIDDEN_NAME = 1;
        public static final int OPT_MACRO = 8;

        private Option() {
        }

        public static boolean isFormula(int optValue) {
            return (optValue & 15) == 0;
        }
    }

    public NameRecord() {
        this.field_13_name_definition = Formula.create(Ptg.EMPTY_PTG_ARRAY);
        this.field_12_name_text = "";
        this.field_14_custom_menu_text = "";
        this.field_15_description_text = "";
        this.field_16_help_topic_text = "";
        this.field_17_status_bar_text = "";
    }

    public NameRecord(NameRecord other) {
        super(other);
        this.field_1_option_flag = other.field_1_option_flag;
        this.field_2_keyboard_shortcut = other.field_2_keyboard_shortcut;
        this.field_5_externSheetIndex_plus1 = other.field_5_externSheetIndex_plus1;
        this.field_6_sheetNumber = other.field_6_sheetNumber;
        this.field_11_nameIsMultibyte = other.field_11_nameIsMultibyte;
        this.field_12_built_in_code = other.field_12_built_in_code;
        this.field_12_name_text = other.field_12_name_text;
        this.field_13_name_definition = other.field_13_name_definition;
        this.field_14_custom_menu_text = other.field_14_custom_menu_text;
        this.field_15_description_text = other.field_15_description_text;
        this.field_16_help_topic_text = other.field_16_help_topic_text;
        this.field_17_status_bar_text = other.field_17_status_bar_text;
    }

    public NameRecord(byte builtin, int sheetNumber) {
        this();
        this.field_12_built_in_code = builtin;
        setOptionFlag((short) (this.field_1_option_flag | 32));
        this.field_6_sheetNumber = sheetNumber;
    }

    public void setOptionFlag(short flag) {
        this.field_1_option_flag = flag;
    }

    public void setKeyboardShortcut(byte shortcut) {
        this.field_2_keyboard_shortcut = shortcut;
    }

    public int getSheetNumber() {
        return this.field_6_sheetNumber;
    }

    public byte getFnGroup() {
        int masked = this.field_1_option_flag & 4032;
        return (byte) (masked >> 4);
    }

    public void setSheetNumber(int value) {
        this.field_6_sheetNumber = value;
    }

    public void setNameText(String name) {
        this.field_12_name_text = name;
        this.field_11_nameIsMultibyte = StringUtil.hasMultibyte(name);
    }

    public void setCustomMenuText(String text) {
        this.field_14_custom_menu_text = text;
    }

    public void setDescriptionText(String text) {
        this.field_15_description_text = text;
    }

    public void setHelpTopicText(String text) {
        this.field_16_help_topic_text = text;
    }

    public void setStatusBarText(String text) {
        this.field_17_status_bar_text = text;
    }

    public short getOptionFlag() {
        return this.field_1_option_flag;
    }

    public byte getKeyboardShortcut() {
        return this.field_2_keyboard_shortcut;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getNameTextLength() {
        if (isBuiltInName()) {
            return 1;
        }
        return this.field_12_name_text.length();
    }

    public boolean isHiddenName() {
        return (this.field_1_option_flag & 1) != 0;
    }

    public void setHidden(boolean b) {
        if (b) {
            this.field_1_option_flag = (short) (this.field_1_option_flag | 1);
        } else {
            this.field_1_option_flag = (short) (this.field_1_option_flag & (-2));
        }
    }

    public boolean isFunctionName() {
        return (this.field_1_option_flag & 2) != 0;
    }

    public void setFunction(boolean function) {
        if (function) {
            this.field_1_option_flag = (short) (this.field_1_option_flag | 2);
        } else {
            this.field_1_option_flag = (short) (this.field_1_option_flag & (-3));
        }
    }

    public boolean hasFormula() {
        return Option.isFormula(this.field_1_option_flag) && this.field_13_name_definition.getEncodedTokenSize() > 0;
    }

    public boolean isCommandName() {
        return (this.field_1_option_flag & 4) != 0;
    }

    public boolean isMacro() {
        return (this.field_1_option_flag & 8) != 0;
    }

    public boolean isComplexFunction() {
        return (this.field_1_option_flag & 16) != 0;
    }

    public boolean isBuiltInName() {
        return (this.field_1_option_flag & 32) != 0;
    }

    public String getNameText() {
        return isBuiltInName() ? translateBuiltInName(getBuiltInName()) : this.field_12_name_text;
    }

    public byte getBuiltInName() {
        return this.field_12_built_in_code;
    }

    public Ptg[] getNameDefinition() {
        return this.field_13_name_definition.getTokens();
    }

    public void setNameDefinition(Ptg[] ptgs) {
        this.field_13_name_definition = Formula.create(ptgs);
    }

    public String getCustomMenuText() {
        return this.field_14_custom_menu_text;
    }

    public String getDescriptionText() {
        return this.field_15_description_text;
    }

    public String getHelpTopicText() {
        return this.field_16_help_topic_text;
    }

    public String getStatusBarText() {
        return this.field_17_status_bar_text;
    }

    @Override // org.apache.poi.hssf.record.cont.ContinuableRecord
    public void serialize(ContinuableRecordOutput continuableRecordOutput) {
        int length = this.field_14_custom_menu_text.length();
        int length2 = this.field_15_description_text.length();
        int length3 = this.field_16_help_topic_text.length();
        int length4 = this.field_17_status_bar_text.length();
        continuableRecordOutput.writeShort(getOptionFlag());
        continuableRecordOutput.writeByte(getKeyboardShortcut());
        continuableRecordOutput.writeByte(getNameTextLength());
        continuableRecordOutput.writeShort(this.field_13_name_definition.getEncodedTokenSize());
        continuableRecordOutput.writeShort(this.field_5_externSheetIndex_plus1);
        continuableRecordOutput.writeShort(this.field_6_sheetNumber);
        continuableRecordOutput.writeByte(length);
        continuableRecordOutput.writeByte(length2);
        continuableRecordOutput.writeByte(length3);
        continuableRecordOutput.writeByte(length4);
        continuableRecordOutput.writeByte(this.field_11_nameIsMultibyte ? 1 : 0);
        if (isBuiltInName()) {
            continuableRecordOutput.writeByte(this.field_12_built_in_code);
        } else {
            String str = this.field_12_name_text;
            if (this.field_11_nameIsMultibyte) {
                StringUtil.putUnicodeLE(str, continuableRecordOutput);
            } else {
                StringUtil.putCompressedUnicode(str, continuableRecordOutput);
            }
        }
        this.field_13_name_definition.serializeTokens(continuableRecordOutput);
        this.field_13_name_definition.serializeArrayConstantData(continuableRecordOutput);
        StringUtil.putCompressedUnicode(getCustomMenuText(), continuableRecordOutput);
        StringUtil.putCompressedUnicode(getDescriptionText(), continuableRecordOutput);
        StringUtil.putCompressedUnicode(getHelpTopicText(), continuableRecordOutput);
        StringUtil.putCompressedUnicode(getStatusBarText(), continuableRecordOutput);
    }

    private int getNameRawSize() {
        if (isBuiltInName()) {
            return 1;
        }
        int nChars = this.field_12_name_text.length();
        if (this.field_11_nameIsMultibyte) {
            return nChars * 2;
        }
        return nChars;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getDataSize() {
        return getNameRawSize() + 13 + this.field_14_custom_menu_text.length() + this.field_15_description_text.length() + this.field_16_help_topic_text.length() + this.field_17_status_bar_text.length() + this.field_13_name_definition.getEncodedSize();
    }

    public int getExternSheetNumber() {
        Ptg[] tokens = this.field_13_name_definition.getTokens();
        if (tokens.length == 0) {
            return 0;
        }
        Ptg ptg = tokens[0];
        if (ptg.getClass() == Area3DPtg.class) {
            return ((Area3DPtg) ptg).getExternSheetIndex();
        }
        if (ptg.getClass() == Ref3DPtg.class) {
            return ((Ref3DPtg) ptg).getExternSheetIndex();
        }
        return 0;
    }

    public NameRecord(RecordInputStream ris) {
        byte[] remainder = ris.readAllContinuedRemainder();
        LittleEndianInput in = new LittleEndianByteArrayInputStream(remainder);
        this.field_1_option_flag = in.readShort();
        this.field_2_keyboard_shortcut = in.readByte();
        int field_3_length_name_text = in.readUByte();
        int field_4_length_name_definition = in.readShort();
        this.field_5_externSheetIndex_plus1 = in.readShort();
        this.field_6_sheetNumber = in.readUShort();
        int f7_customMenuLen = in.readUByte();
        int f8_descriptionTextLen = in.readUByte();
        int f9_helpTopicTextLen = in.readUByte();
        int f10_statusBarTextLen = in.readUByte();
        this.field_11_nameIsMultibyte = in.readByte() != 0;
        if (isBuiltInName()) {
            this.field_12_built_in_code = in.readByte();
        } else if (this.field_11_nameIsMultibyte) {
            this.field_12_name_text = StringUtil.readUnicodeLE(in, field_3_length_name_text);
        } else {
            this.field_12_name_text = StringUtil.readCompressedUnicode(in, field_3_length_name_text);
        }
        int nBytesAvailable = in.available() - (((f7_customMenuLen + f8_descriptionTextLen) + f9_helpTopicTextLen) + f10_statusBarTextLen);
        this.field_13_name_definition = Formula.read(field_4_length_name_definition, in, nBytesAvailable);
        this.field_14_custom_menu_text = StringUtil.readCompressedUnicode(in, f7_customMenuLen);
        this.field_15_description_text = StringUtil.readCompressedUnicode(in, f8_descriptionTextLen);
        this.field_16_help_topic_text = StringUtil.readCompressedUnicode(in, f9_helpTopicTextLen);
        this.field_17_status_bar_text = StringUtil.readCompressedUnicode(in, f10_statusBarTextLen);
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return (short) 24;
    }

    private static String translateBuiltInName(byte name) {
        switch (name) {
            case 1:
                return "Consolidate_Area";
            case 2:
                return "Auto_Open";
            case 3:
                return "Auto_Close";
            case 4:
                return "Database";
            case 5:
                return "Criteria";
            case 6:
                return "Print_Area";
            case 7:
                return "Print_Titles";
            case 8:
                return "Recorder";
            case 9:
                return "Data_Form";
            case 10:
                return "Auto_Activate";
            case 11:
                return "Auto_Deactivate";
            case 12:
                return "Sheet_Title";
            case 13:
                return "_FilterDatabase";
            default:
                return "Unknown";
        }
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public NameRecord copy() {
        return new NameRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.NAME;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        Map<String, Supplier<?>> m = new LinkedHashMap<>();
        m.put("dataSize", new Supplier() { // from class: org.apache.poi.hssf.record.NameRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(NameRecord.this.getDataSize());
            }
        });
        m.put("optionFlag", new Supplier() { // from class: org.apache.poi.hssf.record.NameRecord$$ExternalSyntheticLambda9
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(NameRecord.this.getOptionFlag());
            }
        });
        m.put("keyboardShortcut", new Supplier() { // from class: org.apache.poi.hssf.record.NameRecord$$ExternalSyntheticLambda10
            @Override // java.util.function.Supplier
            public final Object get() {
                return Byte.valueOf(NameRecord.this.getKeyboardShortcut());
            }
        });
        m.put("externSheetIndex", new Supplier() { // from class: org.apache.poi.hssf.record.NameRecord$$ExternalSyntheticLambda11
            @Override // java.util.function.Supplier
            public final Object get() {
                return NameRecord.this.m2340xdbfe57db();
            }
        });
        m.put("sheetNumber", new Supplier() { // from class: org.apache.poi.hssf.record.NameRecord$$ExternalSyntheticLambda12
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(NameRecord.this.getSheetNumber());
            }
        });
        m.put("nameIsMultibyte", new Supplier() { // from class: org.apache.poi.hssf.record.NameRecord$$ExternalSyntheticLambda13
            @Override // java.util.function.Supplier
            public final Object get() {
                return NameRecord.this.m2341xa4ff4f1c();
            }
        });
        m.put("builtInName", new Supplier() { // from class: org.apache.poi.hssf.record.NameRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Byte.valueOf(NameRecord.this.getBuiltInName());
            }
        });
        m.put("nameLength", new Supplier() { // from class: org.apache.poi.hssf.record.NameRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                int nameTextLength;
                nameTextLength = NameRecord.this.getNameTextLength();
                return Integer.valueOf(nameTextLength);
            }
        });
        m.put("nameText", new Supplier() { // from class: org.apache.poi.hssf.record.NameRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return NameRecord.this.getNameText();
            }
        });
        m.put("formula", new Supplier() { // from class: org.apache.poi.hssf.record.NameRecord$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return NameRecord.this.getNameDefinition();
            }
        });
        m.put("customMenuText", new Supplier() { // from class: org.apache.poi.hssf.record.NameRecord$$ExternalSyntheticLambda5
            @Override // java.util.function.Supplier
            public final Object get() {
                return NameRecord.this.getCustomMenuText();
            }
        });
        m.put("descriptionText", new Supplier() { // from class: org.apache.poi.hssf.record.NameRecord$$ExternalSyntheticLambda6
            @Override // java.util.function.Supplier
            public final Object get() {
                return NameRecord.this.getDescriptionText();
            }
        });
        m.put("helpTopicText", new Supplier() { // from class: org.apache.poi.hssf.record.NameRecord$$ExternalSyntheticLambda7
            @Override // java.util.function.Supplier
            public final Object get() {
                return NameRecord.this.getHelpTopicText();
            }
        });
        m.put("statusBarText", new Supplier() { // from class: org.apache.poi.hssf.record.NameRecord$$ExternalSyntheticLambda8
            @Override // java.util.function.Supplier
            public final Object get() {
                return NameRecord.this.getStatusBarText();
            }
        });
        return Collections.unmodifiableMap(m);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-NameRecord, reason: not valid java name */
    public /* synthetic */ Object m2340xdbfe57db() {
        return Short.valueOf(this.field_5_externSheetIndex_plus1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-NameRecord, reason: not valid java name */
    public /* synthetic */ Object m2341xa4ff4f1c() {
        return Boolean.valueOf(this.field_11_nameIsMultibyte);
    }
}
