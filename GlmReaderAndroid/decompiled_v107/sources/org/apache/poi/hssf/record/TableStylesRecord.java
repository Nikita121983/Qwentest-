package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.StringUtil;

/* loaded from: classes10.dex */
public final class TableStylesRecord extends StandardRecord {
    public static final short sid = 2190;
    private int cts;
    private int grbitFrt;
    private String rgchDefListStyle;
    private String rgchDefPivotStyle;
    private int rt;
    private final byte[] unused;

    public TableStylesRecord(TableStylesRecord other) {
        super(other);
        this.unused = new byte[8];
        this.rt = other.rt;
        this.grbitFrt = other.grbitFrt;
        System.arraycopy(other.unused, 0, this.unused, 0, this.unused.length);
        this.cts = other.cts;
        this.rgchDefListStyle = other.rgchDefListStyle;
        this.rgchDefPivotStyle = other.rgchDefPivotStyle;
    }

    public TableStylesRecord(RecordInputStream in) {
        this.unused = new byte[8];
        this.rt = in.readUShort();
        this.grbitFrt = in.readUShort();
        in.readFully(this.unused);
        this.cts = in.readInt();
        int cchDefListStyle = in.readUShort();
        int cchDefPivotStyle = in.readUShort();
        this.rgchDefListStyle = in.readUnicodeLEString(cchDefListStyle);
        this.rgchDefPivotStyle = in.readUnicodeLEString(cchDefPivotStyle);
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected void serialize(LittleEndianOutput out) {
        out.writeShort(this.rt);
        out.writeShort(this.grbitFrt);
        out.write(this.unused);
        out.writeInt(this.cts);
        out.writeShort(this.rgchDefListStyle.length());
        out.writeShort(this.rgchDefPivotStyle.length());
        StringUtil.putUnicodeLE(this.rgchDefListStyle, out);
        StringUtil.putUnicodeLE(this.rgchDefPivotStyle, out);
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return (this.rgchDefListStyle.length() * 2) + 20 + (this.rgchDefPivotStyle.length() * 2);
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return sid;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public TableStylesRecord copy() {
        return new TableStylesRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.TABLE_STYLES;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("rt", new Supplier() { // from class: org.apache.poi.hssf.record.TableStylesRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return TableStylesRecord.this.m2382xbd6a00c2();
            }
        }, "grbitFrt", new Supplier() { // from class: org.apache.poi.hssf.record.TableStylesRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return TableStylesRecord.this.m2383x76e18e61();
            }
        }, "unused", new Supplier() { // from class: org.apache.poi.hssf.record.TableStylesRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return TableStylesRecord.this.m2384x30591c00();
            }
        }, "cts", new Supplier() { // from class: org.apache.poi.hssf.record.TableStylesRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return TableStylesRecord.this.m2385xe9d0a99f();
            }
        }, "rgchDefListStyle", new Supplier() { // from class: org.apache.poi.hssf.record.TableStylesRecord$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return TableStylesRecord.this.m2386xa348373e();
            }
        }, "rgchDefPivotStyle", new Supplier() { // from class: org.apache.poi.hssf.record.TableStylesRecord$$ExternalSyntheticLambda5
            @Override // java.util.function.Supplier
            public final Object get() {
                return TableStylesRecord.this.m2387x5cbfc4dd();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-TableStylesRecord, reason: not valid java name */
    public /* synthetic */ Object m2382xbd6a00c2() {
        return Integer.valueOf(this.rt);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-TableStylesRecord, reason: not valid java name */
    public /* synthetic */ Object m2383x76e18e61() {
        return Integer.valueOf(this.grbitFrt);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$2$org-apache-poi-hssf-record-TableStylesRecord, reason: not valid java name */
    public /* synthetic */ Object m2384x30591c00() {
        return this.unused;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$3$org-apache-poi-hssf-record-TableStylesRecord, reason: not valid java name */
    public /* synthetic */ Object m2385xe9d0a99f() {
        return Integer.valueOf(this.cts);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$4$org-apache-poi-hssf-record-TableStylesRecord, reason: not valid java name */
    public /* synthetic */ Object m2386xa348373e() {
        return this.rgchDefListStyle;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$5$org-apache-poi-hssf-record-TableStylesRecord, reason: not valid java name */
    public /* synthetic */ Object m2387x5cbfc4dd() {
        return this.rgchDefPivotStyle;
    }
}
