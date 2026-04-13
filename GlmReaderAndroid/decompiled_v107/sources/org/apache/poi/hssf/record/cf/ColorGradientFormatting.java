package org.apache.poi.hssf.record.cf;

import java.util.Map;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.stream.Stream;
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
public final class ColorGradientFormatting implements Duplicatable, GenericRecord {
    private ExtendedColor[] colors;
    private final byte options;
    private ColorGradientThreshold[] thresholds;
    private static final Logger LOGGER = PoiLogManager.getLogger((Class<?>) ColorGradientFormatting.class);
    private static final BitField clamp = BitFieldFactory.getInstance(1);
    private static final BitField background = BitFieldFactory.getInstance(2);

    public ColorGradientFormatting() {
        this.options = (byte) 3;
        this.thresholds = new ColorGradientThreshold[3];
        this.colors = new ExtendedColor[3];
    }

    public ColorGradientFormatting(ColorGradientFormatting other) {
        this.options = other.options;
        if (other.thresholds != null) {
            this.thresholds = (ColorGradientThreshold[]) Stream.of((Object[]) other.thresholds).map(new Function() { // from class: org.apache.poi.hssf.record.cf.ColorGradientFormatting$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((ColorGradientThreshold) obj).copy();
                }
            }).toArray(new IntFunction() { // from class: org.apache.poi.hssf.record.cf.ColorGradientFormatting$$ExternalSyntheticLambda1
                @Override // java.util.function.IntFunction
                public final Object apply(int i) {
                    return ColorGradientFormatting.lambda$new$0(i);
                }
            });
        }
        if (other.colors != null) {
            this.colors = (ExtendedColor[]) Stream.of((Object[]) other.colors).map(new Function() { // from class: org.apache.poi.hssf.record.cf.ColorGradientFormatting$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((ExtendedColor) obj).copy();
                }
            }).toArray(new IntFunction() { // from class: org.apache.poi.hssf.record.cf.ColorGradientFormatting$$ExternalSyntheticLambda3
                @Override // java.util.function.IntFunction
                public final Object apply(int i) {
                    return ColorGradientFormatting.lambda$new$1(i);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ ColorGradientThreshold[] lambda$new$0(int x$0) {
        return new ColorGradientThreshold[x$0];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ ExtendedColor[] lambda$new$1(int x$0) {
        return new ExtendedColor[x$0];
    }

    public ColorGradientFormatting(LittleEndianInput in) {
        in.readShort();
        in.readByte();
        int numI = in.readByte();
        int numG = in.readByte();
        if (numI != numG) {
            LOGGER.atWarn().log("Inconsistent Color Gradient definition, found {} vs {} entries", Unbox.box(numI), Unbox.box(numG));
        }
        this.options = in.readByte();
        this.thresholds = new ColorGradientThreshold[numI];
        for (int i = 0; i < this.thresholds.length; i++) {
            this.thresholds[i] = new ColorGradientThreshold(in);
        }
        this.colors = new ExtendedColor[numG];
        for (int i2 = 0; i2 < this.colors.length; i2++) {
            in.readDouble();
            this.colors[i2] = new ExtendedColor(in);
        }
    }

    public int getNumControlPoints() {
        return this.thresholds.length;
    }

    public void setNumControlPoints(int num) {
        if (num != this.thresholds.length) {
            ColorGradientThreshold[] nt = new ColorGradientThreshold[num];
            ExtendedColor[] nc = new ExtendedColor[num];
            int copy = Math.min(this.thresholds.length, num);
            System.arraycopy(this.thresholds, 0, nt, 0, copy);
            System.arraycopy(this.colors, 0, nc, 0, copy);
            this.thresholds = nt;
            this.colors = nc;
            updateThresholdPositions();
        }
    }

    public ColorGradientThreshold[] getThresholds() {
        return this.thresholds;
    }

    public void setThresholds(ColorGradientThreshold[] thresholds) {
        this.thresholds = thresholds == null ? null : (ColorGradientThreshold[]) thresholds.clone();
        updateThresholdPositions();
    }

    public ExtendedColor[] getColors() {
        return this.colors;
    }

    public void setColors(ExtendedColor[] colors) {
        this.colors = colors == null ? null : (ExtendedColor[]) colors.clone();
    }

    public boolean isClampToCurve() {
        return getOptionFlag(clamp);
    }

    public boolean isAppliesToBackground() {
        return getOptionFlag(background);
    }

    private boolean getOptionFlag(BitField field) {
        return field.isSet(this.options);
    }

    private void updateThresholdPositions() {
        double step = 1.0d / (this.thresholds.length - 1);
        for (int i = 0; i < this.thresholds.length; i++) {
            this.thresholds[i].setPosition(i * step);
        }
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("clampToCurve", new Supplier() { // from class: org.apache.poi.hssf.record.cf.ColorGradientFormatting$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(ColorGradientFormatting.this.isClampToCurve());
            }
        }, "background", new Supplier() { // from class: org.apache.poi.hssf.record.cf.ColorGradientFormatting$$ExternalSyntheticLambda5
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(ColorGradientFormatting.this.isAppliesToBackground());
            }
        }, "thresholds", new Supplier() { // from class: org.apache.poi.hssf.record.cf.ColorGradientFormatting$$ExternalSyntheticLambda6
            @Override // java.util.function.Supplier
            public final Object get() {
                return ColorGradientFormatting.this.getThresholds();
            }
        }, "colors", new Supplier() { // from class: org.apache.poi.hssf.record.cf.ColorGradientFormatting$$ExternalSyntheticLambda7
            @Override // java.util.function.Supplier
            public final Object get() {
                return ColorGradientFormatting.this.getColors();
            }
        });
    }

    public String toString() {
        return GenericRecordJsonWriter.marshal(this);
    }

    @Override // org.apache.poi.common.Duplicatable
    public ColorGradientFormatting copy() {
        return new ColorGradientFormatting(this);
    }

    public int getDataLength() {
        int len = 6;
        for (Threshold t : this.thresholds) {
            len += t.getDataLength();
        }
        for (ExtendedColor c : this.colors) {
            len = len + c.getDataLength() + 8;
        }
        return len;
    }

    public void serialize(LittleEndianOutput out) {
        out.writeShort(0);
        out.writeByte(0);
        out.writeByte(this.thresholds.length);
        out.writeByte(this.thresholds.length);
        out.writeByte(this.options);
        for (ColorGradientThreshold t : this.thresholds) {
            t.serialize(out);
        }
        double step = 1.0d / (this.colors.length - 1);
        for (int i = 0; i < this.colors.length; i++) {
            out.writeDouble(i * step);
            ExtendedColor c = this.colors[i];
            c.serialize(out);
        }
    }
}
