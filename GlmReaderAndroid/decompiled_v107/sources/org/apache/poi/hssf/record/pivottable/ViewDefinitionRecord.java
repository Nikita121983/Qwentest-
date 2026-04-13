package org.apache.poi.hssf.record.pivottable;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.StringUtil;

/* loaded from: classes10.dex */
public final class ViewDefinitionRecord extends StandardRecord {
    public static final short sid = 176;
    private int cCol;
    private int cDim;
    private int cDimCol;
    private int cDimData;
    private int cDimPg;
    private int cDimRw;
    private int cRw;
    private int colFirst;
    private int colFirstData;
    private int colLast;
    private String dataField;
    private int grbit;
    private int iCache;
    private int ipos4Data;
    private int itblAutoFmt;
    private String name;
    private int reserved;
    private int rwFirst;
    private int rwFirstData;
    private int rwFirstHead;
    private int rwLast;
    private int sxaxis4Data;

    public ViewDefinitionRecord(ViewDefinitionRecord other) {
        super(other);
        this.rwFirst = other.rwFirst;
        this.rwLast = other.rwLast;
        this.colFirst = other.colFirst;
        this.colLast = other.colLast;
        this.rwFirstHead = other.rwFirstHead;
        this.rwFirstData = other.rwFirstData;
        this.colFirstData = other.colFirstData;
        this.iCache = other.iCache;
        this.reserved = other.reserved;
        this.sxaxis4Data = other.sxaxis4Data;
        this.ipos4Data = other.ipos4Data;
        this.cDim = other.cDim;
        this.cDimRw = other.cDimRw;
        this.cDimCol = other.cDimCol;
        this.cDimPg = other.cDimPg;
        this.cDimData = other.cDimData;
        this.cRw = other.cRw;
        this.cCol = other.cCol;
        this.grbit = other.grbit;
        this.itblAutoFmt = other.itblAutoFmt;
        this.name = other.name;
        this.dataField = other.dataField;
    }

    public ViewDefinitionRecord(RecordInputStream in) {
        this.rwFirst = in.readUShort();
        this.rwLast = in.readUShort();
        this.colFirst = in.readUShort();
        this.colLast = in.readUShort();
        this.rwFirstHead = in.readUShort();
        this.rwFirstData = in.readUShort();
        this.colFirstData = in.readUShort();
        this.iCache = in.readUShort();
        this.reserved = in.readUShort();
        this.sxaxis4Data = in.readUShort();
        this.ipos4Data = in.readUShort();
        this.cDim = in.readUShort();
        this.cDimRw = in.readUShort();
        this.cDimCol = in.readUShort();
        this.cDimPg = in.readUShort();
        this.cDimData = in.readUShort();
        this.cRw = in.readUShort();
        this.cCol = in.readUShort();
        this.grbit = in.readUShort();
        this.itblAutoFmt = in.readUShort();
        int cchName = in.readUShort();
        int cchData = in.readUShort();
        this.name = StringUtil.readUnicodeString(in, cchName);
        this.dataField = StringUtil.readUnicodeString(in, cchData);
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected void serialize(LittleEndianOutput out) {
        out.writeShort(this.rwFirst);
        out.writeShort(this.rwLast);
        out.writeShort(this.colFirst);
        out.writeShort(this.colLast);
        out.writeShort(this.rwFirstHead);
        out.writeShort(this.rwFirstData);
        out.writeShort(this.colFirstData);
        out.writeShort(this.iCache);
        out.writeShort(this.reserved);
        out.writeShort(this.sxaxis4Data);
        out.writeShort(this.ipos4Data);
        out.writeShort(this.cDim);
        out.writeShort(this.cDimRw);
        out.writeShort(this.cDimCol);
        out.writeShort(this.cDimPg);
        out.writeShort(this.cDimData);
        out.writeShort(this.cRw);
        out.writeShort(this.cCol);
        out.writeShort(this.grbit);
        out.writeShort(this.itblAutoFmt);
        out.writeShort(this.name.length());
        out.writeShort(this.dataField.length());
        StringUtil.writeUnicodeStringFlagAndData(out, this.name);
        StringUtil.writeUnicodeStringFlagAndData(out, this.dataField);
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return StringUtil.getEncodedSize(this.name) + 40 + StringUtil.getEncodedSize(this.dataField);
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return (short) 176;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public ViewDefinitionRecord copy() {
        return new ViewDefinitionRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.VIEW_DEFINITION;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        Map<String, Supplier<?>> m = new LinkedHashMap<>();
        m.put("rwFirst", new Supplier() { // from class: org.apache.poi.hssf.record.pivottable.ViewDefinitionRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return ViewDefinitionRecord.this.m2466xe30f4b2b();
            }
        });
        m.put("rwLast", new Supplier() { // from class: org.apache.poi.hssf.record.pivottable.ViewDefinitionRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return ViewDefinitionRecord.this.m2467xe913168a();
            }
        });
        m.put("colFirst", new Supplier() { // from class: org.apache.poi.hssf.record.pivottable.ViewDefinitionRecord$$ExternalSyntheticLambda5
            @Override // java.util.function.Supplier
            public final Object get() {
                return ViewDefinitionRecord.this.m2478xef16e1e9();
            }
        });
        m.put("colLast", new Supplier() { // from class: org.apache.poi.hssf.record.pivottable.ViewDefinitionRecord$$ExternalSyntheticLambda6
            @Override // java.util.function.Supplier
            public final Object get() {
                return ViewDefinitionRecord.this.m2481xf51aad48();
            }
        });
        m.put("rwFirstHead", new Supplier() { // from class: org.apache.poi.hssf.record.pivottable.ViewDefinitionRecord$$ExternalSyntheticLambda7
            @Override // java.util.function.Supplier
            public final Object get() {
                return ViewDefinitionRecord.this.m2482xfb1e78a7();
            }
        });
        m.put("rwFirstData", new Supplier() { // from class: org.apache.poi.hssf.record.pivottable.ViewDefinitionRecord$$ExternalSyntheticLambda8
            @Override // java.util.function.Supplier
            public final Object get() {
                return ViewDefinitionRecord.this.m2483x1224406();
            }
        });
        m.put("colFirstData", new Supplier() { // from class: org.apache.poi.hssf.record.pivottable.ViewDefinitionRecord$$ExternalSyntheticLambda9
            @Override // java.util.function.Supplier
            public final Object get() {
                return ViewDefinitionRecord.this.m2484x7260f65();
            }
        });
        m.put("iCache", new Supplier() { // from class: org.apache.poi.hssf.record.pivottable.ViewDefinitionRecord$$ExternalSyntheticLambda10
            @Override // java.util.function.Supplier
            public final Object get() {
                return ViewDefinitionRecord.this.m2485xd29dac4();
            }
        });
        m.put("reserved", new Supplier() { // from class: org.apache.poi.hssf.record.pivottable.ViewDefinitionRecord$$ExternalSyntheticLambda12
            @Override // java.util.function.Supplier
            public final Object get() {
                return ViewDefinitionRecord.this.m2486x132da623();
            }
        });
        m.put("sxaxis4Data", new Supplier() { // from class: org.apache.poi.hssf.record.pivottable.ViewDefinitionRecord$$ExternalSyntheticLambda13
            @Override // java.util.function.Supplier
            public final Object get() {
                return ViewDefinitionRecord.this.m2487x19317182();
            }
        });
        m.put("ipos4Data", new Supplier() { // from class: org.apache.poi.hssf.record.pivottable.ViewDefinitionRecord$$ExternalSyntheticLambda11
            @Override // java.util.function.Supplier
            public final Object get() {
                return ViewDefinitionRecord.this.m2468x7e26fb96();
            }
        });
        m.put("cDim", new Supplier() { // from class: org.apache.poi.hssf.record.pivottable.ViewDefinitionRecord$$ExternalSyntheticLambda14
            @Override // java.util.function.Supplier
            public final Object get() {
                return ViewDefinitionRecord.this.m2469x842ac6f5();
            }
        });
        m.put("cDimRw", new Supplier() { // from class: org.apache.poi.hssf.record.pivottable.ViewDefinitionRecord$$ExternalSyntheticLambda15
            @Override // java.util.function.Supplier
            public final Object get() {
                return ViewDefinitionRecord.this.m2470x8a2e9254();
            }
        });
        m.put("cDimCol", new Supplier() { // from class: org.apache.poi.hssf.record.pivottable.ViewDefinitionRecord$$ExternalSyntheticLambda16
            @Override // java.util.function.Supplier
            public final Object get() {
                return ViewDefinitionRecord.this.m2471x90325db3();
            }
        });
        m.put("cDimPg", new Supplier() { // from class: org.apache.poi.hssf.record.pivottable.ViewDefinitionRecord$$ExternalSyntheticLambda17
            @Override // java.util.function.Supplier
            public final Object get() {
                return ViewDefinitionRecord.this.m2472x96362912();
            }
        });
        m.put("cDimData", new Supplier() { // from class: org.apache.poi.hssf.record.pivottable.ViewDefinitionRecord$$ExternalSyntheticLambda18
            @Override // java.util.function.Supplier
            public final Object get() {
                return ViewDefinitionRecord.this.m2473x9c39f471();
            }
        });
        m.put("cRw", new Supplier() { // from class: org.apache.poi.hssf.record.pivottable.ViewDefinitionRecord$$ExternalSyntheticLambda19
            @Override // java.util.function.Supplier
            public final Object get() {
                return ViewDefinitionRecord.this.m2474xa23dbfd0();
            }
        });
        m.put("cCol", new Supplier() { // from class: org.apache.poi.hssf.record.pivottable.ViewDefinitionRecord$$ExternalSyntheticLambda20
            @Override // java.util.function.Supplier
            public final Object get() {
                return ViewDefinitionRecord.this.m2475xa8418b2f();
            }
        });
        m.put("grbit", new Supplier() { // from class: org.apache.poi.hssf.record.pivottable.ViewDefinitionRecord$$ExternalSyntheticLambda21
            @Override // java.util.function.Supplier
            public final Object get() {
                return ViewDefinitionRecord.this.m2476xae45568e();
            }
        });
        m.put("itblAutoFmt", new Supplier() { // from class: org.apache.poi.hssf.record.pivottable.ViewDefinitionRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return ViewDefinitionRecord.this.m2477xb44921ed();
            }
        });
        m.put("name", new Supplier() { // from class: org.apache.poi.hssf.record.pivottable.ViewDefinitionRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return ViewDefinitionRecord.this.m2479x389c9c17();
            }
        });
        m.put("dataField", new Supplier() { // from class: org.apache.poi.hssf.record.pivottable.ViewDefinitionRecord$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return ViewDefinitionRecord.this.m2480x3ea06776();
            }
        });
        return Collections.unmodifiableMap(m);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-pivottable-ViewDefinitionRecord, reason: not valid java name */
    public /* synthetic */ Object m2466xe30f4b2b() {
        return Integer.valueOf(this.rwFirst);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-pivottable-ViewDefinitionRecord, reason: not valid java name */
    public /* synthetic */ Object m2467xe913168a() {
        return Integer.valueOf(this.rwLast);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$2$org-apache-poi-hssf-record-pivottable-ViewDefinitionRecord, reason: not valid java name */
    public /* synthetic */ Object m2478xef16e1e9() {
        return Integer.valueOf(this.colFirst);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$3$org-apache-poi-hssf-record-pivottable-ViewDefinitionRecord, reason: not valid java name */
    public /* synthetic */ Object m2481xf51aad48() {
        return Integer.valueOf(this.colLast);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$4$org-apache-poi-hssf-record-pivottable-ViewDefinitionRecord, reason: not valid java name */
    public /* synthetic */ Object m2482xfb1e78a7() {
        return Integer.valueOf(this.rwFirstHead);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$5$org-apache-poi-hssf-record-pivottable-ViewDefinitionRecord, reason: not valid java name */
    public /* synthetic */ Object m2483x1224406() {
        return Integer.valueOf(this.rwFirstData);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$6$org-apache-poi-hssf-record-pivottable-ViewDefinitionRecord, reason: not valid java name */
    public /* synthetic */ Object m2484x7260f65() {
        return Integer.valueOf(this.colFirstData);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$7$org-apache-poi-hssf-record-pivottable-ViewDefinitionRecord, reason: not valid java name */
    public /* synthetic */ Object m2485xd29dac4() {
        return Integer.valueOf(this.iCache);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$8$org-apache-poi-hssf-record-pivottable-ViewDefinitionRecord, reason: not valid java name */
    public /* synthetic */ Object m2486x132da623() {
        return Integer.valueOf(this.reserved);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$9$org-apache-poi-hssf-record-pivottable-ViewDefinitionRecord, reason: not valid java name */
    public /* synthetic */ Object m2487x19317182() {
        return Integer.valueOf(this.sxaxis4Data);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$10$org-apache-poi-hssf-record-pivottable-ViewDefinitionRecord, reason: not valid java name */
    public /* synthetic */ Object m2468x7e26fb96() {
        return Integer.valueOf(this.ipos4Data);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$11$org-apache-poi-hssf-record-pivottable-ViewDefinitionRecord, reason: not valid java name */
    public /* synthetic */ Object m2469x842ac6f5() {
        return Integer.valueOf(this.cDim);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$12$org-apache-poi-hssf-record-pivottable-ViewDefinitionRecord, reason: not valid java name */
    public /* synthetic */ Object m2470x8a2e9254() {
        return Integer.valueOf(this.cDimRw);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$13$org-apache-poi-hssf-record-pivottable-ViewDefinitionRecord, reason: not valid java name */
    public /* synthetic */ Object m2471x90325db3() {
        return Integer.valueOf(this.cDimCol);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$14$org-apache-poi-hssf-record-pivottable-ViewDefinitionRecord, reason: not valid java name */
    public /* synthetic */ Object m2472x96362912() {
        return Integer.valueOf(this.cDimPg);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$15$org-apache-poi-hssf-record-pivottable-ViewDefinitionRecord, reason: not valid java name */
    public /* synthetic */ Object m2473x9c39f471() {
        return Integer.valueOf(this.cDimData);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$16$org-apache-poi-hssf-record-pivottable-ViewDefinitionRecord, reason: not valid java name */
    public /* synthetic */ Object m2474xa23dbfd0() {
        return Integer.valueOf(this.cRw);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$17$org-apache-poi-hssf-record-pivottable-ViewDefinitionRecord, reason: not valid java name */
    public /* synthetic */ Object m2475xa8418b2f() {
        return Integer.valueOf(this.cCol);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$18$org-apache-poi-hssf-record-pivottable-ViewDefinitionRecord, reason: not valid java name */
    public /* synthetic */ Object m2476xae45568e() {
        return Integer.valueOf(this.grbit);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$19$org-apache-poi-hssf-record-pivottable-ViewDefinitionRecord, reason: not valid java name */
    public /* synthetic */ Object m2477xb44921ed() {
        return Integer.valueOf(this.itblAutoFmt);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$20$org-apache-poi-hssf-record-pivottable-ViewDefinitionRecord, reason: not valid java name */
    public /* synthetic */ Object m2479x389c9c17() {
        return this.name;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$21$org-apache-poi-hssf-record-pivottable-ViewDefinitionRecord, reason: not valid java name */
    public /* synthetic */ Object m2480x3ea06776() {
        return this.dataField;
    }
}
