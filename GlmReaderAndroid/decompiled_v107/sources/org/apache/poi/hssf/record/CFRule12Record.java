package org.apache.poi.hssf.record;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.hssf.record.cf.ColorGradientFormatting;
import org.apache.poi.hssf.record.cf.ColorGradientThreshold;
import org.apache.poi.hssf.record.cf.DataBarFormatting;
import org.apache.poi.hssf.record.cf.DataBarThreshold;
import org.apache.poi.hssf.record.cf.IconMultiStateFormatting;
import org.apache.poi.hssf.record.cf.IconMultiStateThreshold;
import org.apache.poi.hssf.record.cf.Threshold;
import org.apache.poi.hssf.record.common.ExtendedColor;
import org.apache.poi.hssf.record.common.FtrHeader;
import org.apache.poi.hssf.record.common.FutureRecord;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.Formula;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.formula.ptg.UnionPtg;
import org.apache.poi.ss.usermodel.ConditionalFormattingThreshold;
import org.apache.poi.ss.usermodel.IconMultiStateFormatting;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class CFRule12Record extends CFRuleBase implements FutureRecord {
    public static final short sid = 2170;
    private ColorGradientFormatting color_gradient;
    private DataBarFormatting data_bar;
    private byte[] ext_formatting_data;
    private int ext_formatting_length;
    private byte ext_opts;
    private byte[] filter_data;
    private Formula formula_scale;
    private FtrHeader futureHeader;
    private IconMultiStateFormatting multistate;
    private int priority;
    private byte template_param_length;
    private byte[] template_params;
    private int template_type;

    public CFRule12Record(CFRule12Record other) {
        super(other);
        this.futureHeader = other.futureHeader == null ? null : other.futureHeader.copy();
        this.ext_formatting_length = Math.min(other.ext_formatting_length, other.ext_formatting_data.length);
        this.ext_formatting_data = (byte[]) other.ext_formatting_data.clone();
        this.formula_scale = other.formula_scale.copy();
        this.ext_opts = other.ext_opts;
        this.priority = other.priority;
        this.template_type = other.template_type;
        this.template_param_length = other.template_param_length;
        this.template_params = other.template_params == null ? null : (byte[]) other.template_params.clone();
        this.color_gradient = other.color_gradient == null ? null : other.color_gradient.copy();
        this.multistate = other.multistate == null ? null : other.multistate.copy();
        this.data_bar = other.data_bar == null ? null : other.data_bar.copy();
        this.filter_data = other.filter_data != null ? (byte[]) other.filter_data.clone() : null;
    }

    private CFRule12Record(byte conditionType, byte comparisonOperation) {
        super(conditionType, comparisonOperation);
        setDefaults();
    }

    private CFRule12Record(byte conditionType, byte comparisonOperation, Ptg[] formula1, Ptg[] formula2, Ptg[] formulaScale) {
        super(conditionType, comparisonOperation, formula1, formula2);
        setDefaults();
        this.formula_scale = Formula.create(formulaScale);
    }

    private void setDefaults() {
        this.futureHeader = new FtrHeader();
        this.futureHeader.setRecordType(sid);
        this.ext_formatting_length = 0;
        this.ext_formatting_data = new byte[4];
        this.formula_scale = Formula.create(Ptg.EMPTY_PTG_ARRAY);
        this.ext_opts = (byte) 0;
        this.priority = 0;
        this.template_type = getConditionType();
        this.template_param_length = UnionPtg.sid;
        this.template_params = IOUtils.safelyAllocate(this.template_param_length, HSSFWorkbook.getMaxRecordLength());
    }

    public static CFRule12Record create(HSSFSheet sheet, String formulaText) {
        Ptg[] formula1 = parseFormula(formulaText, sheet);
        return new CFRule12Record((byte) 2, (byte) 0, formula1, null, null);
    }

    public static CFRule12Record create(HSSFSheet sheet, byte comparisonOperation, String formulaText1, String formulaText2) {
        Ptg[] formula1 = parseFormula(formulaText1, sheet);
        Ptg[] formula2 = parseFormula(formulaText2, sheet);
        return new CFRule12Record((byte) 1, comparisonOperation, formula1, formula2, null);
    }

    public static CFRule12Record create(HSSFSheet sheet, byte comparisonOperation, String formulaText1, String formulaText2, String formulaTextScale) {
        Ptg[] formula1 = parseFormula(formulaText1, sheet);
        Ptg[] formula2 = parseFormula(formulaText2, sheet);
        Ptg[] formula3 = parseFormula(formulaTextScale, sheet);
        return new CFRule12Record((byte) 1, comparisonOperation, formula1, formula2, formula3);
    }

    public static CFRule12Record create(HSSFSheet sheet, ExtendedColor color) {
        CFRule12Record r = new CFRule12Record((byte) 4, (byte) 0);
        DataBarFormatting dbf = r.createDataBarFormatting();
        dbf.setColor(color);
        dbf.setPercentMin((byte) 0);
        dbf.setPercentMax((byte) 100);
        DataBarThreshold min = new DataBarThreshold();
        min.setType(ConditionalFormattingThreshold.RangeType.MIN.id);
        dbf.setThresholdMin(min);
        DataBarThreshold max = new DataBarThreshold();
        max.setType(ConditionalFormattingThreshold.RangeType.MAX.id);
        dbf.setThresholdMax(max);
        return r;
    }

    public static CFRule12Record create(HSSFSheet sheet, IconMultiStateFormatting.IconSet iconSet) {
        Threshold[] ts = new Threshold[iconSet.num];
        for (int i = 0; i < ts.length; i++) {
            ts[i] = new IconMultiStateThreshold();
        }
        CFRule12Record r = new CFRule12Record((byte) 6, (byte) 0);
        org.apache.poi.hssf.record.cf.IconMultiStateFormatting imf = r.createMultiStateFormatting();
        imf.setIconSet(iconSet);
        imf.setThresholds(ts);
        return r;
    }

    public static CFRule12Record createColorScale(HSSFSheet sheet) {
        ExtendedColor[] colors = new ExtendedColor[3];
        ColorGradientThreshold[] ts = new ColorGradientThreshold[3];
        for (int i = 0; i < ts.length; i++) {
            ts[i] = new ColorGradientThreshold();
            colors[i] = new ExtendedColor();
        }
        CFRule12Record r = new CFRule12Record((byte) 3, (byte) 0);
        ColorGradientFormatting cgf = r.createColorGradientFormatting();
        cgf.setNumControlPoints(3);
        cgf.setThresholds(ts);
        cgf.setColors(colors);
        return r;
    }

    public CFRule12Record(RecordInputStream in) {
        this.futureHeader = new FtrHeader(in);
        setConditionType(in.readByte());
        setComparisonOperation(in.readByte());
        int field_3_formula1_len = in.readUShort();
        int field_4_formula2_len = in.readUShort();
        this.ext_formatting_length = in.readInt();
        this.ext_formatting_data = new byte[0];
        if (this.ext_formatting_length == 0) {
            in.readUShort();
        } else {
            long len = readFormatOptions(in);
            if (len < this.ext_formatting_length) {
                this.ext_formatting_data = IOUtils.safelyAllocate(this.ext_formatting_length - len, HSSFWorkbook.getMaxRecordLength());
                in.readFully(this.ext_formatting_data);
            }
        }
        setFormula1(Formula.read(field_3_formula1_len, in));
        setFormula2(Formula.read(field_4_formula2_len, in));
        int formula_scale_len = in.readUShort();
        this.formula_scale = Formula.read(formula_scale_len, in);
        this.ext_opts = in.readByte();
        this.priority = in.readUShort();
        this.template_type = in.readUShort();
        this.template_param_length = in.readByte();
        if (this.template_param_length == 0 || this.template_param_length == 16) {
            this.template_params = IOUtils.safelyAllocate(this.template_param_length, HSSFWorkbook.getMaxRecordLength());
            in.readFully(this.template_params);
        } else {
            LOG.atWarn().log("CF Rule v12 template params length should be 0 or 16, found {}", Unbox.box(this.template_param_length));
            in.readRemainder();
        }
        byte type = getConditionType();
        if (type == 3) {
            this.color_gradient = new ColorGradientFormatting(in);
            return;
        }
        if (type == 4) {
            this.data_bar = new DataBarFormatting(in);
        } else if (type == 5) {
            this.filter_data = in.readRemainder();
        } else if (type == 6) {
            this.multistate = new org.apache.poi.hssf.record.cf.IconMultiStateFormatting(in);
        }
    }

    public boolean containsDataBarBlock() {
        return this.data_bar != null;
    }

    public DataBarFormatting getDataBarFormatting() {
        return this.data_bar;
    }

    public DataBarFormatting createDataBarFormatting() {
        if (this.data_bar != null) {
            return this.data_bar;
        }
        setConditionType((byte) 4);
        this.data_bar = new DataBarFormatting();
        return this.data_bar;
    }

    public boolean containsMultiStateBlock() {
        return this.multistate != null;
    }

    public org.apache.poi.hssf.record.cf.IconMultiStateFormatting getMultiStateFormatting() {
        return this.multistate;
    }

    public org.apache.poi.hssf.record.cf.IconMultiStateFormatting createMultiStateFormatting() {
        if (this.multistate != null) {
            return this.multistate;
        }
        setConditionType((byte) 6);
        this.multistate = new org.apache.poi.hssf.record.cf.IconMultiStateFormatting();
        return this.multistate;
    }

    public boolean containsColorGradientBlock() {
        return this.color_gradient != null;
    }

    public ColorGradientFormatting getColorGradientFormatting() {
        return this.color_gradient;
    }

    public ColorGradientFormatting createColorGradientFormatting() {
        if (this.color_gradient != null) {
            return this.color_gradient;
        }
        setConditionType((byte) 3);
        this.color_gradient = new ColorGradientFormatting();
        return this.color_gradient;
    }

    public Ptg[] getParsedExpressionScale() {
        return this.formula_scale.getTokens();
    }

    public void setParsedExpressionScale(Ptg[] ptgs) {
        this.formula_scale = Formula.create(ptgs);
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return sid;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        this.futureHeader.serialize(out);
        int formula1Len = getFormulaSize(getFormula1());
        int formula2Len = getFormulaSize(getFormula2());
        out.writeByte(getConditionType());
        out.writeByte(getComparisonOperation());
        out.writeShort(formula1Len);
        out.writeShort(formula2Len);
        if (this.ext_formatting_length == 0) {
            out.writeInt(0);
            out.writeShort(0);
        } else {
            out.writeInt(this.ext_formatting_length);
            serializeFormattingBlock(out);
            out.write(this.ext_formatting_data);
        }
        getFormula1().serializeTokens(out);
        getFormula2().serializeTokens(out);
        out.writeShort(getFormulaSize(this.formula_scale));
        this.formula_scale.serializeTokens(out);
        out.writeByte(this.ext_opts);
        out.writeShort(this.priority);
        out.writeShort(this.template_type);
        out.writeByte(this.template_param_length);
        if (this.template_params != null) {
            out.write(this.template_params);
        }
        byte type = getConditionType();
        if (type == 3) {
            this.color_gradient.serialize(out);
            return;
        }
        if (type == 4) {
            this.data_bar.serialize(out);
        } else if (type == 5) {
            out.write(this.filter_data);
        } else if (type == 6) {
            this.multistate.serialize(out);
        }
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        int len;
        int len2 = FtrHeader.getDataSize() + 6;
        if (this.ext_formatting_length == 0) {
            len = len2 + 6;
        } else {
            len = len2 + getFormattingBlockSize() + 4 + this.ext_formatting_data.length;
        }
        int len3 = len + getFormulaSize(getFormula1()) + getFormulaSize(getFormula2()) + getFormulaSize(this.formula_scale) + 2 + (this.template_params == null ? 0 : this.template_params.length) + 6;
        byte type = getConditionType();
        if (type == 3) {
            return len3 + this.color_gradient.getDataLength();
        }
        if (type == 4) {
            return len3 + this.data_bar.getDataLength();
        }
        if (type == 5) {
            return len3 + this.filter_data.length;
        }
        if (type == 6) {
            return len3 + this.multistate.getDataLength();
        }
        return len3;
    }

    @Override // org.apache.poi.hssf.record.CFRuleBase, org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public CFRule12Record copy() {
        return new CFRule12Record(this);
    }

    @Override // org.apache.poi.hssf.record.common.FutureRecord
    public short getFutureRecordType() {
        return this.futureHeader.getRecordType();
    }

    @Override // org.apache.poi.hssf.record.common.FutureRecord
    public FtrHeader getFutureHeader() {
        return this.futureHeader;
    }

    @Override // org.apache.poi.hssf.record.common.FutureRecord
    public CellRangeAddress getAssociatedRange() {
        return this.futureHeader.getAssociatedRange();
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.CF_RULE_12;
    }

    @Override // org.apache.poi.hssf.record.CFRuleBase, org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        Map<String, Supplier<?>> m = new LinkedHashMap<>(super.getGenericProperties());
        m.put("dxFn12Length", new Supplier() { // from class: org.apache.poi.hssf.record.CFRule12Record$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return CFRule12Record.this.m2257xd97ea250();
            }
        });
        m.put("futureHeader", new Supplier() { // from class: org.apache.poi.hssf.record.CFRule12Record$$ExternalSyntheticLambda5
            @Override // java.util.function.Supplier
            public final Object get() {
                return CFRule12Record.this.getFutureHeader();
            }
        });
        m.put("dxFn12Ext", new Supplier() { // from class: org.apache.poi.hssf.record.CFRule12Record$$ExternalSyntheticLambda6
            @Override // java.util.function.Supplier
            public final Object get() {
                return CFRule12Record.this.m2258xbec01111();
            }
        });
        m.put("formulaScale", new Supplier() { // from class: org.apache.poi.hssf.record.CFRule12Record$$ExternalSyntheticLambda7
            @Override // java.util.function.Supplier
            public final Object get() {
                return CFRule12Record.this.getParsedExpressionScale();
            }
        });
        m.put("extOptions", new Supplier() { // from class: org.apache.poi.hssf.record.CFRule12Record$$ExternalSyntheticLambda8
            @Override // java.util.function.Supplier
            public final Object get() {
                return CFRule12Record.this.m2259xa4017fd2();
            }
        });
        m.put("priority", new Supplier() { // from class: org.apache.poi.hssf.record.CFRule12Record$$ExternalSyntheticLambda9
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(CFRule12Record.this.getPriority());
            }
        });
        m.put("templateType", new Supplier() { // from class: org.apache.poi.hssf.record.CFRule12Record$$ExternalSyntheticLambda10
            @Override // java.util.function.Supplier
            public final Object get() {
                return CFRule12Record.this.m2260x8942ee93();
            }
        });
        m.put("templateParams", new Supplier() { // from class: org.apache.poi.hssf.record.CFRule12Record$$ExternalSyntheticLambda11
            @Override // java.util.function.Supplier
            public final Object get() {
                return CFRule12Record.this.m2261x6e845d54();
            }
        });
        m.put("filterData", new Supplier() { // from class: org.apache.poi.hssf.record.CFRule12Record$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return CFRule12Record.this.m2262x53c5cc15();
            }
        });
        m.put("dataBar", new Supplier() { // from class: org.apache.poi.hssf.record.CFRule12Record$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return CFRule12Record.this.getDataBarFormatting();
            }
        });
        m.put("multiState", new Supplier() { // from class: org.apache.poi.hssf.record.CFRule12Record$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return CFRule12Record.this.getMultiStateFormatting();
            }
        });
        m.put("colorGradient", new Supplier() { // from class: org.apache.poi.hssf.record.CFRule12Record$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return CFRule12Record.this.getColorGradientFormatting();
            }
        });
        return Collections.unmodifiableMap(m);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-CFRule12Record, reason: not valid java name */
    public /* synthetic */ Object m2257xd97ea250() {
        return Integer.valueOf(this.ext_formatting_length);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-CFRule12Record, reason: not valid java name */
    public /* synthetic */ Object m2258xbec01111() {
        return this.ext_formatting_data;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$2$org-apache-poi-hssf-record-CFRule12Record, reason: not valid java name */
    public /* synthetic */ Object m2259xa4017fd2() {
        return Byte.valueOf(this.ext_opts);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$3$org-apache-poi-hssf-record-CFRule12Record, reason: not valid java name */
    public /* synthetic */ Object m2260x8942ee93() {
        return Integer.valueOf(this.template_type);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$4$org-apache-poi-hssf-record-CFRule12Record, reason: not valid java name */
    public /* synthetic */ Object m2261x6e845d54() {
        return this.template_params;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$5$org-apache-poi-hssf-record-CFRule12Record, reason: not valid java name */
    public /* synthetic */ Object m2262x53c5cc15() {
        return this.filter_data;
    }
}
