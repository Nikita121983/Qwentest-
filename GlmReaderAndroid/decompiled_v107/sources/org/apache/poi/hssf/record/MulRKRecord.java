package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.hssf.record.MulRKRecord;
import org.apache.poi.hssf.util.RKUtil;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.RecordFormatException;

/* loaded from: classes10.dex */
public final class MulRKRecord extends StandardRecord {
    public static final short sid = 189;
    private final int field_1_row;
    private final short field_2_first_col;
    private final RkRec[] field_3_rks;
    private final short field_4_last_col;

    public int getRow() {
        return this.field_1_row;
    }

    public short getFirstColumn() {
        return this.field_2_first_col;
    }

    public short getLastColumn() {
        return this.field_4_last_col;
    }

    public int getNumColumns() {
        return (this.field_4_last_col - this.field_2_first_col) + 1;
    }

    public short getXFAt(int coffset) {
        return this.field_3_rks[coffset].xf;
    }

    public double getRKNumberAt(int coffset) {
        return RKUtil.decodeNumber(this.field_3_rks[coffset].rk);
    }

    public MulRKRecord(RecordInputStream in) {
        this.field_1_row = in.readUShort();
        this.field_2_first_col = in.readShort();
        this.field_3_rks = RkRec.parseRKs(in);
        this.field_4_last_col = in.readShort();
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return (short) 189;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        throw new RecordFormatException("Sorry, you can't serialize MulRK in this release");
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        throw new RecordFormatException("Sorry, you can't serialize MulRK in this release");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static final class RkRec implements GenericRecord {
        public static final int ENCODED_SIZE = 6;
        public final int rk;
        public final short xf;

        private RkRec(RecordInputStream in) {
            this.xf = in.readShort();
            this.rk = in.readInt();
        }

        public static RkRec[] parseRKs(RecordInputStream in) {
            int nItems = (in.remaining() - 2) / 6;
            RkRec[] retval = new RkRec[nItems];
            for (int i = 0; i < nItems; i++) {
                retval[i] = new RkRec(in);
            }
            return retval;
        }

        @Override // org.apache.poi.common.usermodel.GenericRecord
        public Map<String, Supplier<?>> getGenericProperties() {
            return GenericRecordUtil.getGenericProperties("xf", new Supplier() { // from class: org.apache.poi.hssf.record.MulRKRecord$RkRec$$ExternalSyntheticLambda0
                @Override // java.util.function.Supplier
                public final Object get() {
                    return MulRKRecord.RkRec.this.m2336x5bb71502();
                }
            }, "rk", new Supplier() { // from class: org.apache.poi.hssf.record.MulRKRecord$RkRec$$ExternalSyntheticLambda1
                @Override // java.util.function.Supplier
                public final Object get() {
                    return MulRKRecord.RkRec.this.m2337x152ea2a1();
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-MulRKRecord$RkRec, reason: not valid java name */
        public /* synthetic */ Object m2336x5bb71502() {
            return Short.valueOf(this.xf);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-MulRKRecord$RkRec, reason: not valid java name */
        public /* synthetic */ Object m2337x152ea2a1() {
            return Integer.valueOf(this.rk);
        }
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public MulRKRecord copy() {
        return this;
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.MUL_RK;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("row", new Supplier() { // from class: org.apache.poi.hssf.record.MulRKRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(MulRKRecord.this.getRow());
            }
        }, "firstColumn", new Supplier() { // from class: org.apache.poi.hssf.record.MulRKRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(MulRKRecord.this.getFirstColumn());
            }
        }, "lastColumn", new Supplier() { // from class: org.apache.poi.hssf.record.MulRKRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(MulRKRecord.this.getLastColumn());
            }
        }, "rk", new Supplier() { // from class: org.apache.poi.hssf.record.MulRKRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return MulRKRecord.this.m2335x4809026f();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-MulRKRecord, reason: not valid java name */
    public /* synthetic */ Object m2335x4809026f() {
        return this.field_3_rks;
    }
}
