package org.apache.poi.hssf.record;

import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.model.HSSFFormulaParser;
import org.apache.poi.hssf.record.cf.BorderFormatting;
import org.apache.poi.hssf.record.cf.FontFormatting;
import org.apache.poi.hssf.record.cf.PatternFormatting;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.ss.formula.Formula;
import org.apache.poi.ss.formula.FormulaType;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public abstract class CFRuleBase extends StandardRecord {
    public static final byte CONDITION_TYPE_CELL_VALUE_IS = 1;
    public static final byte CONDITION_TYPE_COLOR_SCALE = 3;
    public static final byte CONDITION_TYPE_DATA_BAR = 4;
    public static final byte CONDITION_TYPE_FILTER = 5;
    public static final byte CONDITION_TYPE_FORMULA = 2;
    public static final byte CONDITION_TYPE_ICON_SET = 6;
    public static final int TEMPLATE_ABOVE_AVERAGE = 25;
    public static final int TEMPLATE_ABOVE_OR_EQUAL_TO_AVERAGE = 29;
    public static final int TEMPLATE_BELOW_AVERAGE = 26;
    public static final int TEMPLATE_BELOW_OR_EQUAL_TO_AVERAGE = 30;
    public static final int TEMPLATE_CELL_VALUE = 0;
    public static final int TEMPLATE_COLOR_SCALE_FORMATTING = 2;
    public static final int TEMPLATE_CONTAINS_BLANKS = 9;
    public static final int TEMPLATE_CONTAINS_ERRORS = 11;
    public static final int TEMPLATE_CONTAINS_NO_BLANKS = 10;
    public static final int TEMPLATE_CONTAINS_NO_ERRORS = 12;
    public static final int TEMPLATE_CONTAINS_TEXT = 8;
    public static final int TEMPLATE_DATA_BAR_FORMATTING = 3;
    public static final int TEMPLATE_DUPLICATE_VALUES = 27;
    public static final int TEMPLATE_FILTER = 5;
    public static final int TEMPLATE_FORMULA = 1;
    public static final int TEMPLATE_ICON_SET_FORMATTING = 4;
    public static final int TEMPLATE_LAST_7_DAYS = 18;
    public static final int TEMPLATE_LAST_MONTH = 19;
    public static final int TEMPLATE_LAST_WEEK = 23;
    public static final int TEMPLATE_NEXT_MONTH = 20;
    public static final int TEMPLATE_NEXT_WEEK = 22;
    public static final int TEMPLATE_THIS_MONTH = 24;
    public static final int TEMPLATE_THIS_WEEK = 21;
    public static final int TEMPLATE_TODAY = 15;
    public static final int TEMPLATE_TOMORROW = 16;
    public static final int TEMPLATE_UNIQUE_VALUES = 7;
    public static final int TEMPLATE_YESTERDAY = 17;
    protected BorderFormatting _borderFormatting;
    protected FontFormatting _fontFormatting;
    protected PatternFormatting _patternFormatting;
    private byte comparison_operator;
    private byte condition_type;
    protected short formatting_not_used;
    protected int formatting_options;
    private Formula formula1;
    private Formula formula2;
    protected static final Logger LOG = PoiLogManager.getLogger((Class<?>) CFRuleBase.class);
    static final BitField modificationBits = bf(4194303);
    static final BitField alignHor = bf(1);
    static final BitField alignVer = bf(2);
    static final BitField alignWrap = bf(4);
    static final BitField alignRot = bf(8);
    static final BitField alignJustLast = bf(16);
    static final BitField alignIndent = bf(32);
    static final BitField alignShrin = bf(64);
    static final BitField mergeCell = bf(128);
    static final BitField protLocked = bf(256);
    static final BitField protHidden = bf(512);
    static final BitField bordLeft = bf(1024);
    static final BitField bordRight = bf(2048);
    static final BitField bordTop = bf(4096);
    static final BitField bordBot = bf(8192);
    static final BitField bordTlBr = bf(16384);
    static final BitField bordBlTr = bf(32768);
    static final BitField pattStyle = bf(65536);
    static final BitField pattCol = bf(131072);
    static final BitField pattBgCol = bf(262144);
    static final BitField notUsed2 = bf(3670016);
    static final BitField undocumented = bf(62914560);
    static final BitField fmtBlockBits = bf(2080374784);
    static final BitField font = bf(AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL);
    static final BitField align = bf(134217728);
    static final BitField bord = bf(268435456);
    static final BitField patt = bf(536870912);
    static final BitField prot = bf(BasicMeasure.EXACTLY);
    static final BitField alignTextDir = bf(Integer.MIN_VALUE);

    /* loaded from: classes10.dex */
    public interface ComparisonOperator {
        public static final byte BETWEEN = 1;
        public static final byte EQUAL = 3;
        public static final byte GE = 7;
        public static final byte GT = 5;
        public static final byte LE = 8;
        public static final byte LT = 6;
        public static final byte NOT_BETWEEN = 2;
        public static final byte NOT_EQUAL = 4;
        public static final byte NO_COMPARISON = 0;
        public static final byte max_operator = 8;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public abstract CFRuleBase copy();

    private static BitField bf(int i) {
        return BitFieldFactory.getInstance(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CFRuleBase(byte conditionType, byte comparisonOperation) {
        setConditionType(conditionType);
        setComparisonOperation(comparisonOperation);
        this.formula1 = Formula.create(Ptg.EMPTY_PTG_ARRAY);
        this.formula2 = Formula.create(Ptg.EMPTY_PTG_ARRAY);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CFRuleBase(byte conditionType, byte comparisonOperation, Ptg[] formula1, Ptg[] formula2) {
        this(conditionType, comparisonOperation);
        this.formula1 = Formula.create(formula1);
        this.formula2 = Formula.create(formula2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CFRuleBase() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CFRuleBase(CFRuleBase other) {
        super(other);
        setConditionType(other.getConditionType());
        setComparisonOperation(other.getComparisonOperation());
        this.formatting_options = other.formatting_options;
        this.formatting_not_used = other.formatting_not_used;
        this._fontFormatting = !other.containsFontFormattingBlock() ? null : other.getFontFormatting().copy();
        this._borderFormatting = !other.containsBorderFormattingBlock() ? null : other.getBorderFormatting().copy();
        this._patternFormatting = other.containsPatternFormattingBlock() ? other.getPatternFormatting().copy() : null;
        this.formula1 = other.getFormula1().copy();
        this.formula2 = other.getFormula2().copy();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int readFormatOptions(RecordInputStream in) {
        this.formatting_options = in.readInt();
        this.formatting_not_used = in.readShort();
        int len = 6;
        if (containsFontFormattingBlock()) {
            this._fontFormatting = new FontFormatting(in);
            len = 6 + this._fontFormatting.getDataLength();
        }
        if (containsBorderFormattingBlock()) {
            this._borderFormatting = new BorderFormatting(in);
            len += this._borderFormatting.getDataLength();
        }
        if (containsPatternFormattingBlock()) {
            this._patternFormatting = new PatternFormatting(in);
            return len + this._patternFormatting.getDataLength();
        }
        return len;
    }

    public byte getConditionType() {
        return this.condition_type;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setConditionType(byte condition_type) {
        if ((this instanceof CFRuleRecord) && condition_type != 1 && condition_type != 2) {
            throw new IllegalArgumentException("CFRuleRecord only accepts Value-Is and Formula types");
        }
        this.condition_type = condition_type;
    }

    public void setComparisonOperation(byte operation) {
        if (operation < 0 || operation > 8) {
            throw new IllegalArgumentException("Valid operators are only in the range 0 to 8");
        }
        this.comparison_operator = operation;
    }

    public byte getComparisonOperation() {
        return this.comparison_operator;
    }

    public boolean containsFontFormattingBlock() {
        return getOptionFlag(font);
    }

    public void setFontFormatting(FontFormatting fontFormatting) {
        this._fontFormatting = fontFormatting;
        setOptionFlag(fontFormatting != null, font);
    }

    public FontFormatting getFontFormatting() {
        if (containsFontFormattingBlock()) {
            return this._fontFormatting;
        }
        return null;
    }

    public boolean containsAlignFormattingBlock() {
        return getOptionFlag(align);
    }

    public void setAlignFormattingUnchanged() {
        setOptionFlag(false, align);
    }

    public boolean containsBorderFormattingBlock() {
        return getOptionFlag(bord);
    }

    public void setBorderFormatting(BorderFormatting borderFormatting) {
        this._borderFormatting = borderFormatting;
        setOptionFlag(borderFormatting != null, bord);
    }

    public BorderFormatting getBorderFormatting() {
        if (containsBorderFormattingBlock()) {
            return this._borderFormatting;
        }
        return null;
    }

    public boolean containsPatternFormattingBlock() {
        return getOptionFlag(patt);
    }

    public void setPatternFormatting(PatternFormatting patternFormatting) {
        this._patternFormatting = patternFormatting;
        setOptionFlag(patternFormatting != null, patt);
    }

    public PatternFormatting getPatternFormatting() {
        if (containsPatternFormattingBlock()) {
            return this._patternFormatting;
        }
        return null;
    }

    public boolean containsProtectionFormattingBlock() {
        return getOptionFlag(prot);
    }

    public void setProtectionFormattingUnchanged() {
        setOptionFlag(false, prot);
    }

    public int getOptions() {
        return this.formatting_options;
    }

    private boolean isModified(BitField field) {
        return !field.isSet(this.formatting_options);
    }

    private void setModified(boolean modified, BitField field) {
        this.formatting_options = field.setBoolean(this.formatting_options, !modified);
    }

    public boolean isLeftBorderModified() {
        return isModified(bordLeft);
    }

    public void setLeftBorderModified(boolean modified) {
        setModified(modified, bordLeft);
    }

    public boolean isRightBorderModified() {
        return isModified(bordRight);
    }

    public void setRightBorderModified(boolean modified) {
        setModified(modified, bordRight);
    }

    public boolean isTopBorderModified() {
        return isModified(bordTop);
    }

    public void setTopBorderModified(boolean modified) {
        setModified(modified, bordTop);
    }

    public boolean isBottomBorderModified() {
        return isModified(bordBot);
    }

    public void setBottomBorderModified(boolean modified) {
        setModified(modified, bordBot);
    }

    public boolean isTopLeftBottomRightBorderModified() {
        return isModified(bordTlBr);
    }

    public void setTopLeftBottomRightBorderModified(boolean modified) {
        setModified(modified, bordTlBr);
    }

    public boolean isBottomLeftTopRightBorderModified() {
        return isModified(bordBlTr);
    }

    public void setBottomLeftTopRightBorderModified(boolean modified) {
        setModified(modified, bordBlTr);
    }

    public boolean isPatternStyleModified() {
        return isModified(pattStyle);
    }

    public void setPatternStyleModified(boolean modified) {
        setModified(modified, pattStyle);
    }

    public boolean isPatternColorModified() {
        return isModified(pattCol);
    }

    public void setPatternColorModified(boolean modified) {
        setModified(modified, pattCol);
    }

    public boolean isPatternBackgroundColorModified() {
        return isModified(pattBgCol);
    }

    public void setPatternBackgroundColorModified(boolean modified) {
        setModified(modified, pattBgCol);
    }

    private boolean getOptionFlag(BitField field) {
        return field.isSet(this.formatting_options);
    }

    private void setOptionFlag(boolean flag, BitField field) {
        this.formatting_options = field.setBoolean(this.formatting_options, flag);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getFormattingBlockSize() {
        return (containsFontFormattingBlock() ? this._fontFormatting.getRawRecord().length : 0) + 6 + (containsBorderFormattingBlock() ? 8 : 0) + (containsPatternFormattingBlock() ? 4 : 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void serializeFormattingBlock(LittleEndianOutput out) {
        out.writeInt(this.formatting_options);
        out.writeShort(this.formatting_not_used);
        if (containsFontFormattingBlock()) {
            byte[] fontFormattingRawRecord = this._fontFormatting.getRawRecord();
            out.write(fontFormattingRawRecord);
        }
        if (containsBorderFormattingBlock()) {
            this._borderFormatting.serialize(out);
        }
        if (containsPatternFormattingBlock()) {
            this._patternFormatting.serialize(out);
        }
    }

    public Ptg[] getParsedExpression1() {
        return this.formula1.getTokens();
    }

    public void setParsedExpression1(Ptg[] ptgs) {
        this.formula1 = Formula.create(ptgs);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Formula getFormula1() {
        return this.formula1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setFormula1(Formula formula1) {
        this.formula1 = formula1;
    }

    public Ptg[] getParsedExpression2() {
        return Formula.getTokens(this.formula2);
    }

    public void setParsedExpression2(Ptg[] ptgs) {
        this.formula2 = Formula.create(ptgs);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Formula getFormula2() {
        return this.formula2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setFormula2(Formula formula2) {
        this.formula2 = formula2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static int getFormulaSize(Formula formula) {
        return formula.getEncodedTokenSize();
    }

    public static Ptg[] parseFormula(String formula, HSSFSheet sheet) {
        if (formula == null) {
            return null;
        }
        int sheetIndex = sheet.getWorkbook().getSheetIndex(sheet);
        return HSSFFormulaParser.parse(formula, sheet.getWorkbook(), FormulaType.CELL, sheetIndex);
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("conditionType", new Supplier() { // from class: org.apache.poi.hssf.record.CFRuleBase$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Byte.valueOf(CFRuleBase.this.getConditionType());
            }
        }, "comparisonOperation", new Supplier() { // from class: org.apache.poi.hssf.record.CFRuleBase$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Byte.valueOf(CFRuleBase.this.getComparisonOperation());
            }
        }, "formattingOptions", new Supplier() { // from class: org.apache.poi.hssf.record.CFRuleBase$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(CFRuleBase.this.getOptions());
            }
        }, "formattingNotUsed", new Supplier() { // from class: org.apache.poi.hssf.record.CFRuleBase$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return CFRuleBase.this.m2263x3f918a6f();
            }
        }, "fontFormatting", new Supplier() { // from class: org.apache.poi.hssf.record.CFRuleBase$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return CFRuleBase.this.getFontFormatting();
            }
        }, "borderFormatting", new Supplier() { // from class: org.apache.poi.hssf.record.CFRuleBase$$ExternalSyntheticLambda5
            @Override // java.util.function.Supplier
            public final Object get() {
                return CFRuleBase.this.getBorderFormatting();
            }
        }, "patternFormatting", new Supplier() { // from class: org.apache.poi.hssf.record.CFRuleBase$$ExternalSyntheticLambda6
            @Override // java.util.function.Supplier
            public final Object get() {
                return CFRuleBase.this.getPatternFormatting();
            }
        }, "formula1", new Supplier() { // from class: org.apache.poi.hssf.record.CFRuleBase$$ExternalSyntheticLambda7
            @Override // java.util.function.Supplier
            public final Object get() {
                return CFRuleBase.this.getFormula1();
            }
        }, "formula2", new Supplier() { // from class: org.apache.poi.hssf.record.CFRuleBase$$ExternalSyntheticLambda8
            @Override // java.util.function.Supplier
            public final Object get() {
                return CFRuleBase.this.getFormula2();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-CFRuleBase, reason: not valid java name */
    public /* synthetic */ Object m2263x3f918a6f() {
        return Short.valueOf(this.formatting_not_used);
    }
}
