package org.apache.poi.hssf.record;

import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class BOFRecord extends StandardRecord {
    public static final int BUILD = 4307;
    public static final int BUILD_YEAR = 1996;
    public static final int HISTORY_MASK = 65;
    public static final int TYPE_CHART = 32;
    public static final int TYPE_EXCEL_4_MACRO = 64;
    public static final int TYPE_VB_MODULE = 6;
    public static final int TYPE_WORKBOOK = 5;
    public static final int TYPE_WORKSHEET = 16;
    public static final int TYPE_WORKSPACE_FILE = 256;
    public static final int VERSION = 1536;
    public static final short biff2_sid = 9;
    public static final short biff3_sid = 521;
    public static final short biff4_sid = 1033;
    public static final short biff5_sid = 2057;
    public static final short sid = 2057;
    private int field_1_version;
    private int field_2_type;
    private int field_3_build;
    private int field_4_year;
    private int field_5_history;
    private int field_6_rversion;

    public BOFRecord() {
    }

    public BOFRecord(BOFRecord other) {
        super(other);
        this.field_1_version = other.field_1_version;
        this.field_2_type = other.field_2_type;
        this.field_3_build = other.field_3_build;
        this.field_4_year = other.field_4_year;
        this.field_5_history = other.field_5_history;
        this.field_6_rversion = other.field_6_rversion;
    }

    private BOFRecord(int type) {
        this.field_1_version = VERSION;
        this.field_2_type = type;
        this.field_3_build = BUILD;
        this.field_4_year = BUILD_YEAR;
        this.field_5_history = 1;
        this.field_6_rversion = VERSION;
    }

    public static BOFRecord createSheetBOF() {
        return new BOFRecord(16);
    }

    public BOFRecord(RecordInputStream in) {
        this.field_1_version = in.readShort();
        this.field_2_type = in.readShort();
        if (in.remaining() >= 2) {
            this.field_3_build = in.readShort();
        }
        if (in.remaining() >= 2) {
            this.field_4_year = in.readShort();
        }
        if (in.remaining() >= 4) {
            this.field_5_history = in.readInt();
        }
        if (in.remaining() >= 4) {
            this.field_6_rversion = in.readInt();
        }
    }

    public void setVersion(int version) {
        this.field_1_version = version;
    }

    public void setType(int type) {
        this.field_2_type = type;
    }

    public void setBuild(int build) {
        this.field_3_build = build;
    }

    public void setBuildYear(int year) {
        this.field_4_year = year;
    }

    public void setHistoryBitMask(int bitmask) {
        this.field_5_history = bitmask;
    }

    public void setRequiredVersion(int version) {
        this.field_6_rversion = version;
    }

    public int getVersion() {
        return this.field_1_version;
    }

    public int getType() {
        return this.field_2_type;
    }

    public int getBuild() {
        return this.field_3_build;
    }

    public int getBuildYear() {
        return this.field_4_year;
    }

    public int getHistoryBitMask() {
        return this.field_5_history;
    }

    public int getRequiredVersion() {
        return this.field_6_rversion;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getTypeName() {
        switch (this.field_2_type) {
            case 5:
                return "workbook";
            case 6:
                return "vb module";
            case 16:
                return "worksheet";
            case 32:
                return "chart";
            case 64:
                return "excel 4 macro";
            case 256:
                return "workspace file";
            default:
                return "#error unknown type#";
        }
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort(getVersion());
        out.writeShort(getType());
        out.writeShort(getBuild());
        out.writeShort(getBuildYear());
        out.writeInt(getHistoryBitMask());
        out.writeInt(getRequiredVersion());
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return 16;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return (short) 2057;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public BOFRecord copy() {
        return new BOFRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.BOF;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        Map<String, Supplier<?>> m = new LinkedHashMap<>();
        m.put("version", new Supplier() { // from class: org.apache.poi.hssf.record.BOFRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(BOFRecord.this.getVersion());
            }
        });
        m.put(ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, new Supplier() { // from class: org.apache.poi.hssf.record.BOFRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(BOFRecord.this.getType());
            }
        });
        m.put("typeName", new Supplier() { // from class: org.apache.poi.hssf.record.BOFRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                String typeName;
                typeName = BOFRecord.this.getTypeName();
                return typeName;
            }
        });
        m.put("build", new Supplier() { // from class: org.apache.poi.hssf.record.BOFRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(BOFRecord.this.getBuild());
            }
        });
        m.put("buildYear", new Supplier() { // from class: org.apache.poi.hssf.record.BOFRecord$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(BOFRecord.this.getBuildYear());
            }
        });
        m.put("history", new Supplier() { // from class: org.apache.poi.hssf.record.BOFRecord$$ExternalSyntheticLambda5
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(BOFRecord.this.getHistoryBitMask());
            }
        });
        m.put("requiredVersion", new Supplier() { // from class: org.apache.poi.hssf.record.BOFRecord$$ExternalSyntheticLambda6
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(BOFRecord.this.getRequiredVersion());
            }
        });
        return Collections.unmodifiableMap(m);
    }
}
