package org.apache.poi.hssf.record.cf;

import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.ss.formula.Formula;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.usermodel.ConditionalFormattingThreshold;
import org.apache.poi.util.GenericRecordJsonWriter;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public abstract class Threshold implements GenericRecord {
    private Formula formula;
    private byte type;
    private Double value;

    public abstract Threshold copy();

    /* JADX INFO: Access modifiers changed from: protected */
    public Threshold() {
        this.type = (byte) ConditionalFormattingThreshold.RangeType.NUMBER.id;
        this.formula = Formula.create(null);
        this.value = Double.valueOf(0.0d);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Threshold(Threshold other) {
        this.type = other.type;
        this.formula = other.formula.copy();
        this.value = other.value;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Threshold(LittleEndianInput in) {
        this.type = in.readByte();
        short formulaLen = in.readShort();
        if (formulaLen > 0) {
            this.formula = Formula.read(formulaLen, in);
        } else {
            this.formula = Formula.create(null);
        }
        if (formulaLen == 0 && this.type != ConditionalFormattingThreshold.RangeType.MIN.id && this.type != ConditionalFormattingThreshold.RangeType.MAX.id) {
            this.value = Double.valueOf(in.readDouble());
        }
    }

    public byte getType() {
        return this.type;
    }

    public void setType(byte type) {
        this.type = type;
        if (type == ConditionalFormattingThreshold.RangeType.MIN.id || type == ConditionalFormattingThreshold.RangeType.MAX.id || type == ConditionalFormattingThreshold.RangeType.FORMULA.id) {
            this.value = null;
        } else if (this.value == null) {
            this.value = Double.valueOf(0.0d);
        }
    }

    public void setType(int type) {
        this.type = (byte) type;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Formula getFormula() {
        return this.formula;
    }

    public Ptg[] getParsedExpression() {
        return this.formula.getTokens();
    }

    public void setParsedExpression(Ptg[] ptgs) {
        this.formula = Formula.create(ptgs);
        if (ptgs.length > 0) {
            this.value = null;
        }
    }

    public Double getValue() {
        return this.value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public int getDataLength() {
        int len = this.formula.getEncodedSize() + 1;
        if (this.value != null) {
            return len + 8;
        }
        return len;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties(ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, new Supplier() { // from class: org.apache.poi.hssf.record.cf.Threshold$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Byte.valueOf(Threshold.this.getType());
            }
        }, "formula", new Supplier() { // from class: org.apache.poi.hssf.record.cf.Threshold$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Threshold.this.getFormula();
            }
        }, "value", new Supplier() { // from class: org.apache.poi.hssf.record.cf.Threshold$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return Threshold.this.getValue();
            }
        });
    }

    public String toString() {
        return GenericRecordJsonWriter.marshal(this);
    }

    public void serialize(LittleEndianOutput out) {
        out.writeByte(this.type);
        if (this.formula.getTokens().length == 0) {
            out.writeShort(0);
        } else {
            this.formula.serialize(out);
        }
        if (this.value != null) {
            out.writeDouble(this.value.doubleValue());
        }
    }
}
