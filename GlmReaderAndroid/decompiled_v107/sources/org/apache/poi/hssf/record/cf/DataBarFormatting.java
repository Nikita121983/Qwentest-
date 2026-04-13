package org.apache.poi.hssf.record.cf;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.common.Duplicatable;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.hssf.record.common.ExtendedColor;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.GenericRecordJsonWriter;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class DataBarFormatting implements Duplicatable, GenericRecord {
    private ExtendedColor color;
    private byte options;
    private byte percentMax;
    private byte percentMin;
    private DataBarThreshold thresholdMax;
    private DataBarThreshold thresholdMin;
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) DataBarFormatting.class);
    private static final BitField ICON_ONLY = BitFieldFactory.getInstance(1);
    private static final BitField REVERSED = BitFieldFactory.getInstance(4);

    public DataBarFormatting() {
        this.options = (byte) 2;
    }

    public DataBarFormatting(DataBarFormatting other) {
        this.options = other.options;
        this.percentMin = other.percentMin;
        this.percentMax = other.percentMax;
        this.color = other.color == null ? null : other.color.copy();
        this.thresholdMin = other.thresholdMin == null ? null : other.thresholdMin.copy();
        this.thresholdMax = other.thresholdMax != null ? other.thresholdMax.copy() : null;
    }

    public DataBarFormatting(LittleEndianInput in) {
        in.readShort();
        in.readByte();
        this.options = in.readByte();
        this.percentMin = in.readByte();
        this.percentMax = in.readByte();
        if (this.percentMin < 0 || this.percentMin > 100) {
            LOG.atWarn().log("Inconsistent Minimum Percentage found {}", Unbox.box(this.percentMin));
        }
        if (this.percentMax < 0 || this.percentMax > 100) {
            LOG.atWarn().log("Inconsistent Maximum Percentage found {}", Unbox.box(this.percentMax));
        }
        this.color = new ExtendedColor(in);
        this.thresholdMin = new DataBarThreshold(in);
        this.thresholdMax = new DataBarThreshold(in);
    }

    public boolean isIconOnly() {
        return getOptionFlag(ICON_ONLY);
    }

    public void setIconOnly(boolean only) {
        setOptionFlag(only, ICON_ONLY);
    }

    public boolean isReversed() {
        return getOptionFlag(REVERSED);
    }

    public void setReversed(boolean rev) {
        setOptionFlag(rev, REVERSED);
    }

    private boolean getOptionFlag(BitField field) {
        int value = field.getValue(this.options);
        return value != 0;
    }

    private void setOptionFlag(boolean option, BitField field) {
        this.options = field.setByteBoolean(this.options, option);
    }

    public byte getPercentMin() {
        return this.percentMin;
    }

    public void setPercentMin(byte percentMin) {
        this.percentMin = percentMin;
    }

    public byte getPercentMax() {
        return this.percentMax;
    }

    public void setPercentMax(byte percentMax) {
        this.percentMax = percentMax;
    }

    public ExtendedColor getColor() {
        return this.color;
    }

    public void setColor(ExtendedColor color) {
        this.color = color;
    }

    public DataBarThreshold getThresholdMin() {
        return this.thresholdMin;
    }

    public void setThresholdMin(DataBarThreshold thresholdMin) {
        this.thresholdMin = thresholdMin;
    }

    public DataBarThreshold getThresholdMax() {
        return this.thresholdMax;
    }

    public void setThresholdMax(DataBarThreshold thresholdMax) {
        this.thresholdMax = thresholdMax;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("options", GenericRecordUtil.getBitsAsString((Supplier<Number>) new Supplier() { // from class: org.apache.poi.hssf.record.cf.DataBarFormatting$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return DataBarFormatting.this.m2402xffa57b83();
            }
        }, new BitField[]{ICON_ONLY, REVERSED}, new String[]{"ICON_ONLY", "REVERSED"}), TypedValues.Custom.S_COLOR, new Supplier() { // from class: org.apache.poi.hssf.record.cf.DataBarFormatting$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return DataBarFormatting.this.getColor();
            }
        }, "percentMin", new Supplier() { // from class: org.apache.poi.hssf.record.cf.DataBarFormatting$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return Byte.valueOf(DataBarFormatting.this.getPercentMin());
            }
        }, "percentMax", new Supplier() { // from class: org.apache.poi.hssf.record.cf.DataBarFormatting$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return Byte.valueOf(DataBarFormatting.this.getPercentMax());
            }
        }, "thresholdMin", new Supplier() { // from class: org.apache.poi.hssf.record.cf.DataBarFormatting$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return DataBarFormatting.this.getThresholdMin();
            }
        }, "thresholdMax", new Supplier() { // from class: org.apache.poi.hssf.record.cf.DataBarFormatting$$ExternalSyntheticLambda5
            @Override // java.util.function.Supplier
            public final Object get() {
                return DataBarFormatting.this.getThresholdMax();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-cf-DataBarFormatting, reason: not valid java name */
    public /* synthetic */ Number m2402xffa57b83() {
        return Byte.valueOf(this.options);
    }

    public String toString() {
        return GenericRecordJsonWriter.marshal(this);
    }

    @Override // org.apache.poi.common.Duplicatable
    public DataBarFormatting copy() {
        return new DataBarFormatting(this);
    }

    public int getDataLength() {
        return this.color.getDataLength() + 6 + this.thresholdMin.getDataLength() + this.thresholdMax.getDataLength();
    }

    public void serialize(LittleEndianOutput out) {
        out.writeShort(0);
        out.writeByte(0);
        out.writeByte(this.options);
        out.writeByte(this.percentMin);
        out.writeByte(this.percentMax);
        this.color.serialize(out);
        this.thresholdMin.serialize(out);
        this.thresholdMax.serialize(out);
    }
}
