package org.apache.poi.hssf.record.cf;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.common.Duplicatable;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class ColorGradientThreshold extends Threshold implements Duplicatable, GenericRecord {
    private double position;

    public ColorGradientThreshold() {
        this.position = 0.0d;
    }

    public ColorGradientThreshold(ColorGradientThreshold other) {
        super(other);
        this.position = other.position;
    }

    public ColorGradientThreshold(LittleEndianInput in) {
        super(in);
        this.position = in.readDouble();
    }

    public double getPosition() {
        return this.position;
    }

    public void setPosition(double position) {
        this.position = position;
    }

    @Override // org.apache.poi.hssf.record.cf.Threshold
    public int getDataLength() {
        return super.getDataLength() + 8;
    }

    @Override // org.apache.poi.hssf.record.cf.Threshold, org.apache.poi.common.Duplicatable
    public ColorGradientThreshold copy() {
        return new ColorGradientThreshold(this);
    }

    @Override // org.apache.poi.hssf.record.cf.Threshold
    public void serialize(LittleEndianOutput out) {
        super.serialize(out);
        out.writeDouble(this.position);
    }

    @Override // org.apache.poi.hssf.record.cf.Threshold, org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("position", new Supplier() { // from class: org.apache.poi.hssf.record.cf.ColorGradientThreshold$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Double.valueOf(ColorGradientThreshold.this.getPosition());
            }
        });
    }
}
