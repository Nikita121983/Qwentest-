package org.apache.poi.hssf.record;

import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.hssf.record.PaletteRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class PaletteRecord extends StandardRecord {
    private static final int[] DEFAULT_COLORS = {0, ViewCompat.MEASURED_SIZE_MASK, 16711680, MotionEventCompat.ACTION_POINTER_INDEX_MASK, 255, 16776960, 16711935, 65535, 8388608, 32768, 128, 8421376, 8388736, 32896, 12632256, 8421504, 10066431, 10040166, 16777164, 13434879, 6684774, 16744576, 26316, 13421823, 128, 16711935, 16776960, 65535, 8388736, 8388608, 32896, 255, 52479, 13434879, 13434828, 16777113, 10079487, 16751052, 13408767, 16764057, 3368703, 3394764, 10079232, 16763904, 16750848, 16737792, 6710937, 9868950, 13158, 3381606, 13056, 3355392, 10040064, 10040166, 3355545, 3355443};
    public static final short FIRST_COLOR_INDEX = 8;
    public static final byte STANDARD_PALETTE_SIZE = 56;
    public static final short sid = 146;
    private final ArrayList<PColor> _colors;

    public PaletteRecord() {
        this._colors = new ArrayList<>(100);
        Stream mapToObj = Arrays.stream(DEFAULT_COLORS).mapToObj(new IntFunction() { // from class: org.apache.poi.hssf.record.PaletteRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return new PaletteRecord.PColor(i);
            }
        });
        final ArrayList<PColor> arrayList = this._colors;
        arrayList.getClass();
        mapToObj.forEach(new Consumer() { // from class: org.apache.poi.hssf.record.PaletteRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                arrayList.add((PaletteRecord.PColor) obj);
            }
        });
    }

    public PaletteRecord(PaletteRecord other) {
        super(other);
        this._colors = new ArrayList<>(100);
        this._colors.ensureCapacity(other._colors.size());
        Stream map = other._colors.stream().map(new Function() { // from class: org.apache.poi.hssf.record.PaletteRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return new PaletteRecord.PColor((PaletteRecord.PColor) obj);
            }
        });
        final ArrayList<PColor> arrayList = this._colors;
        arrayList.getClass();
        map.forEach(new Consumer() { // from class: org.apache.poi.hssf.record.PaletteRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                arrayList.add((PaletteRecord.PColor) obj);
            }
        });
    }

    public PaletteRecord(RecordInputStream in) {
        this._colors = new ArrayList<>(100);
        int field_1_numcolors = in.readShort();
        this._colors.ensureCapacity(field_1_numcolors);
        for (int k = 0; k < field_1_numcolors; k++) {
            this._colors.add(new PColor(in));
        }
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this._colors.size());
        Iterator<PColor> it = this._colors.iterator();
        while (it.hasNext()) {
            PColor color = it.next();
            color.serialize(out);
        }
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return (this._colors.size() * 4) + 2;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return (short) 146;
    }

    public byte[] getColor(int byteIndex) {
        int i = byteIndex - 8;
        if (i < 0 || i >= this._colors.size()) {
            return null;
        }
        return this._colors.get(i).getTriplet();
    }

    public void setColor(short byteIndex, byte red, byte green, byte blue) {
        int i = byteIndex - 8;
        if (i < 0 || i >= 56) {
            return;
        }
        while (this._colors.size() <= i) {
            this._colors.add(new PColor(0, 0, 0));
        }
        PColor custColor = new PColor(red, green, blue);
        this._colors.set(i, custColor);
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public PaletteRecord copy() {
        return new PaletteRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.PALETTE;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("colors", new Supplier() { // from class: org.apache.poi.hssf.record.PaletteRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return PaletteRecord.this.m2356x2a35852d();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-PaletteRecord, reason: not valid java name */
    public /* synthetic */ Object m2356x2a35852d() {
        return this._colors;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static final class PColor implements GenericRecord {
        public static final short ENCODED_SIZE = 4;
        private final int _blue;
        private final int _green;
        private final int _red;

        /* JADX INFO: Access modifiers changed from: package-private */
        public PColor(int rgb) {
            this._red = (rgb >>> 16) & 255;
            this._green = (rgb >>> 8) & 255;
            this._blue = rgb & 255;
        }

        PColor(int red, int green, int blue) {
            this._red = red;
            this._green = green;
            this._blue = blue;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public PColor(PColor other) {
            this._red = other._red;
            this._green = other._green;
            this._blue = other._blue;
        }

        PColor(RecordInputStream in) {
            this._red = in.readByte();
            this._green = in.readByte();
            this._blue = in.readByte();
            in.readByte();
        }

        byte[] getTriplet() {
            return new byte[]{(byte) this._red, (byte) this._green, (byte) this._blue};
        }

        void serialize(LittleEndianOutput out) {
            out.writeByte(this._red);
            out.writeByte(this._green);
            out.writeByte(this._blue);
            out.writeByte(0);
        }

        @Override // org.apache.poi.common.usermodel.GenericRecord
        public Map<String, Supplier<?>> getGenericProperties() {
            return GenericRecordUtil.getGenericProperties("red", new Supplier() { // from class: org.apache.poi.hssf.record.PaletteRecord$PColor$$ExternalSyntheticLambda0
                @Override // java.util.function.Supplier
                public final Object get() {
                    return PaletteRecord.PColor.this.m2357x1bd8044a();
                }
            }, "green", new Supplier() { // from class: org.apache.poi.hssf.record.PaletteRecord$PColor$$ExternalSyntheticLambda1
                @Override // java.util.function.Supplier
                public final Object get() {
                    return PaletteRecord.PColor.this.m2358x1b619e4b();
                }
            }, "blue", new Supplier() { // from class: org.apache.poi.hssf.record.PaletteRecord$PColor$$ExternalSyntheticLambda2
                @Override // java.util.function.Supplier
                public final Object get() {
                    return PaletteRecord.PColor.this.m2359x1aeb384c();
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-PaletteRecord$PColor, reason: not valid java name */
        public /* synthetic */ Object m2357x1bd8044a() {
            return Integer.valueOf(this._red & 255);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-PaletteRecord$PColor, reason: not valid java name */
        public /* synthetic */ Object m2358x1b619e4b() {
            return Integer.valueOf(this._green & 255);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$getGenericProperties$2$org-apache-poi-hssf-record-PaletteRecord$PColor, reason: not valid java name */
        public /* synthetic */ Object m2359x1aeb384c() {
            return Integer.valueOf(this._blue & 255);
        }
    }
}
