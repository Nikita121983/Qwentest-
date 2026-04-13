package org.apache.poi.hssf.record.chart;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.Map;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.hssf.record.chart.ChartTitleFormatRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public class ChartTitleFormatRecord extends StandardRecord {
    public static final short sid = 4176;
    private final CTFormat[] _formats;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static final class CTFormat implements GenericRecord {
        public static final int ENCODED_SIZE = 4;
        private int _fontIndex;
        private int _offset;

        public CTFormat(CTFormat other) {
            this._offset = other._offset;
            this._fontIndex = other._fontIndex;
        }

        public CTFormat(RecordInputStream in) {
            this._offset = in.readShort();
            this._fontIndex = in.readShort();
        }

        public int getOffset() {
            return this._offset;
        }

        public void setOffset(int newOff) {
            this._offset = newOff;
        }

        public int getFontIndex() {
            return this._fontIndex;
        }

        public void serialize(LittleEndianOutput out) {
            out.writeShort(this._offset);
            out.writeShort(this._fontIndex);
        }

        @Override // org.apache.poi.common.usermodel.GenericRecord
        public Map<String, Supplier<?>> getGenericProperties() {
            return GenericRecordUtil.getGenericProperties(TypedValues.CycleType.S_WAVE_OFFSET, new Supplier() { // from class: org.apache.poi.hssf.record.chart.ChartTitleFormatRecord$CTFormat$$ExternalSyntheticLambda0
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(ChartTitleFormatRecord.CTFormat.this.getOffset());
                }
            }, "fontIndex", new Supplier() { // from class: org.apache.poi.hssf.record.chart.ChartTitleFormatRecord$CTFormat$$ExternalSyntheticLambda1
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(ChartTitleFormatRecord.CTFormat.this.getFontIndex());
                }
            });
        }
    }

    public ChartTitleFormatRecord(ChartTitleFormatRecord other) {
        super(other);
        this._formats = (CTFormat[]) Stream.of((Object[]) other._formats).map(new Function() { // from class: org.apache.poi.hssf.record.chart.ChartTitleFormatRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return new ChartTitleFormatRecord.CTFormat((ChartTitleFormatRecord.CTFormat) obj);
            }
        }).toArray(new IntFunction() { // from class: org.apache.poi.hssf.record.chart.ChartTitleFormatRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return ChartTitleFormatRecord.lambda$new$0(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ CTFormat[] lambda$new$0(int x$0) {
        return new CTFormat[x$0];
    }

    public ChartTitleFormatRecord(RecordInputStream in) {
        int nRecs = in.readUShort();
        this._formats = new CTFormat[nRecs];
        for (int i = 0; i < nRecs; i++) {
            this._formats[i] = new CTFormat(in);
        }
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this._formats.length);
        for (CTFormat format : this._formats) {
            format.serialize(out);
        }
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return (this._formats.length * 4) + 2;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return sid;
    }

    public int getFormatCount() {
        return this._formats.length;
    }

    public void modifyFormatRun(short oldPos, short newLen) {
        int shift = 0;
        for (int i = 0; i < this._formats.length; i++) {
            CTFormat ctf = this._formats[i];
            if (shift != 0) {
                ctf.setOffset(ctf.getOffset() + shift);
            } else if (oldPos == ctf.getOffset() && i < this._formats.length - 1) {
                CTFormat nextCTF = this._formats[i + 1];
                shift = newLen - (nextCTF.getOffset() - ctf.getOffset());
            }
        }
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public ChartTitleFormatRecord copy() {
        return new ChartTitleFormatRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.CHART_TITLE_FORMAT;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("formats", new Supplier() { // from class: org.apache.poi.hssf.record.chart.ChartTitleFormatRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return ChartTitleFormatRecord.this.m2438x9df37c91();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-chart-ChartTitleFormatRecord, reason: not valid java name */
    public /* synthetic */ Object m2438x9df37c91() {
        return this._formats;
    }
}
