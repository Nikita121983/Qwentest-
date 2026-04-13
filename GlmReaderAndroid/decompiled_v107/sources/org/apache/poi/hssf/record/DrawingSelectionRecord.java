package org.apache.poi.hssf.record;

import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.hssf.record.DrawingSelectionRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class DrawingSelectionRecord extends StandardRecord {
    public static final short sid = 237;
    private int _cpsp;
    private int _dgslk;
    private OfficeArtRecordHeader _header;
    private int[] _shapeIds;
    private int _spidFocus;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static final class OfficeArtRecordHeader implements GenericRecord {
        public static final int ENCODED_SIZE = 8;
        private final int _length;
        private final int _type;
        private final int _verAndInstance;

        public OfficeArtRecordHeader(LittleEndianInput in) {
            this._verAndInstance = in.readUShort();
            this._type = in.readUShort();
            this._length = in.readInt();
        }

        public void serialize(LittleEndianOutput out) {
            out.writeShort(this._verAndInstance);
            out.writeShort(this._type);
            out.writeInt(this._length);
        }

        @Override // org.apache.poi.common.usermodel.GenericRecord
        public Map<String, Supplier<?>> getGenericProperties() {
            return GenericRecordUtil.getGenericProperties("verAndInstance", new Supplier() { // from class: org.apache.poi.hssf.record.DrawingSelectionRecord$OfficeArtRecordHeader$$ExternalSyntheticLambda0
                @Override // java.util.function.Supplier
                public final Object get() {
                    return DrawingSelectionRecord.OfficeArtRecordHeader.this.m2284x8abb713f();
                }
            }, ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, new Supplier() { // from class: org.apache.poi.hssf.record.DrawingSelectionRecord$OfficeArtRecordHeader$$ExternalSyntheticLambda1
                @Override // java.util.function.Supplier
                public final Object get() {
                    return DrawingSelectionRecord.OfficeArtRecordHeader.this.m2285x51c75840();
                }
            }, "length", new Supplier() { // from class: org.apache.poi.hssf.record.DrawingSelectionRecord$OfficeArtRecordHeader$$ExternalSyntheticLambda2
                @Override // java.util.function.Supplier
                public final Object get() {
                    return DrawingSelectionRecord.OfficeArtRecordHeader.this.m2286x18d33f41();
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-DrawingSelectionRecord$OfficeArtRecordHeader, reason: not valid java name */
        public /* synthetic */ Object m2284x8abb713f() {
            return Integer.valueOf(this._verAndInstance);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-DrawingSelectionRecord$OfficeArtRecordHeader, reason: not valid java name */
        public /* synthetic */ Object m2285x51c75840() {
            return Integer.valueOf(this._type);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$getGenericProperties$2$org-apache-poi-hssf-record-DrawingSelectionRecord$OfficeArtRecordHeader, reason: not valid java name */
        public /* synthetic */ Object m2286x18d33f41() {
            return Integer.valueOf(this._length);
        }
    }

    public DrawingSelectionRecord(RecordInputStream in) {
        this._header = new OfficeArtRecordHeader(in);
        this._cpsp = in.readInt();
        this._dgslk = in.readInt();
        this._spidFocus = in.readInt();
        int nShapes = in.available() / 4;
        int[] shapeIds = new int[nShapes];
        for (int i = 0; i < nShapes; i++) {
            shapeIds[i] = in.readInt();
        }
        this._shapeIds = shapeIds;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return sid;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return (this._shapeIds.length * 4) + 20;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        this._header.serialize(out);
        out.writeInt(this._cpsp);
        out.writeInt(this._dgslk);
        out.writeInt(this._spidFocus);
        for (int shapeId : this._shapeIds) {
            out.writeInt(shapeId);
        }
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public DrawingSelectionRecord copy() {
        return this;
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.DRAWING_SELECTION;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("rh", new Supplier() { // from class: org.apache.poi.hssf.record.DrawingSelectionRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return DrawingSelectionRecord.this.m2279x32ea979e();
            }
        }, "cpsp", new Supplier() { // from class: org.apache.poi.hssf.record.DrawingSelectionRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return DrawingSelectionRecord.this.m2280x7675b55f();
            }
        }, "dgslk", new Supplier() { // from class: org.apache.poi.hssf.record.DrawingSelectionRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return DrawingSelectionRecord.this.m2281xba00d320();
            }
        }, "spidFocus", new Supplier() { // from class: org.apache.poi.hssf.record.DrawingSelectionRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return DrawingSelectionRecord.this.m2282xfd8bf0e1();
            }
        }, "shapeIds", new Supplier() { // from class: org.apache.poi.hssf.record.DrawingSelectionRecord$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return DrawingSelectionRecord.this.m2283x41170ea2();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-DrawingSelectionRecord, reason: not valid java name */
    public /* synthetic */ Object m2279x32ea979e() {
        return this._header;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-DrawingSelectionRecord, reason: not valid java name */
    public /* synthetic */ Object m2280x7675b55f() {
        return Integer.valueOf(this._cpsp);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$2$org-apache-poi-hssf-record-DrawingSelectionRecord, reason: not valid java name */
    public /* synthetic */ Object m2281xba00d320() {
        return Integer.valueOf(this._dgslk);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$3$org-apache-poi-hssf-record-DrawingSelectionRecord, reason: not valid java name */
    public /* synthetic */ Object m2282xfd8bf0e1() {
        return Integer.valueOf(this._spidFocus);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$4$org-apache-poi-hssf-record-DrawingSelectionRecord, reason: not valid java name */
    public /* synthetic */ Object m2283x41170ea2() {
        return this._shapeIds;
    }
}
