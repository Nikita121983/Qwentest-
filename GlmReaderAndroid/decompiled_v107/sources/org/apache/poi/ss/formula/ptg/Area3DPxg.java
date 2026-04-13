package org.apache.poi.ss.formula.ptg;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.SheetIdentifier;
import org.apache.poi.ss.formula.SheetRangeAndWorkbookIndexFormatter;
import org.apache.poi.ss.formula.SheetRangeIdentifier;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class Area3DPxg extends AreaPtgBase implements Pxg3D {
    private int externalWorkbookNumber;
    private String firstSheetName;
    private String lastSheetName;

    public Area3DPxg(Area3DPxg other) {
        super(other);
        this.externalWorkbookNumber = -1;
        this.externalWorkbookNumber = other.externalWorkbookNumber;
        this.firstSheetName = other.firstSheetName;
        this.lastSheetName = other.lastSheetName;
    }

    public Area3DPxg(int externalWorkbookNumber, SheetIdentifier sheetName, String arearef) {
        this(externalWorkbookNumber, sheetName, new AreaReference(arearef, SpreadsheetVersion.EXCEL2007));
    }

    public Area3DPxg(int externalWorkbookNumber, SheetIdentifier sheetName, AreaReference arearef) {
        super(arearef);
        this.externalWorkbookNumber = -1;
        this.externalWorkbookNumber = externalWorkbookNumber;
        this.firstSheetName = sheetName.getSheetIdentifier().getName();
        if (sheetName instanceof SheetRangeIdentifier) {
            this.lastSheetName = ((SheetRangeIdentifier) sheetName).getLastSheetIdentifier().getName();
        } else {
            this.lastSheetName = null;
        }
    }

    public Area3DPxg(SheetIdentifier sheetName, String arearef) {
        this(sheetName, new AreaReference(arearef, SpreadsheetVersion.EXCEL2007));
    }

    public Area3DPxg(SheetIdentifier sheetName, AreaReference arearef) {
        this(-1, sheetName, arearef);
    }

    @Override // org.apache.poi.ss.formula.ptg.Pxg
    public int getExternalWorkbookNumber() {
        return this.externalWorkbookNumber;
    }

    @Override // org.apache.poi.ss.formula.ptg.Pxg
    public String getSheetName() {
        return this.firstSheetName;
    }

    @Override // org.apache.poi.ss.formula.ptg.Pxg3D
    public String getLastSheetName() {
        return this.lastSheetName;
    }

    @Override // org.apache.poi.ss.formula.ptg.Pxg
    public void setSheetName(String sheetName) {
        this.firstSheetName = sheetName;
    }

    @Override // org.apache.poi.ss.formula.ptg.Pxg3D
    public void setLastSheetName(String sheetName) {
        this.lastSheetName = sheetName;
    }

    public String format2DRefAsString() {
        return formatReferenceAsString();
    }

    @Override // org.apache.poi.ss.formula.ptg.AreaPtgBase, org.apache.poi.ss.formula.ptg.Ptg
    public String toFormulaString() {
        StringBuilder sb = new StringBuilder(64);
        SheetRangeAndWorkbookIndexFormatter.format(sb, this.externalWorkbookNumber, this.firstSheetName, this.lastSheetName);
        sb.append('!');
        sb.append(formatReferenceAsString());
        return sb.toString();
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public byte getSid() {
        return (byte) -1;
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public int getSize() {
        return 1;
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public void write(LittleEndianOutput out) {
        throw new IllegalStateException("XSSF-only Ptg, should not be serialised");
    }

    @Override // org.apache.poi.ss.formula.ptg.OperandPtg, org.apache.poi.ss.formula.ptg.Ptg, org.apache.poi.common.Duplicatable
    public Area3DPxg copy() {
        return new Area3DPxg(this);
    }

    @Override // org.apache.poi.ss.formula.ptg.AreaPtgBase, org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", new Supplier() { // from class: org.apache.poi.ss.formula.ptg.Area3DPxg$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Area3DPxg.this.m2534x5df6b155();
            }
        }, "externalWorkbookNumber", new Supplier() { // from class: org.apache.poi.ss.formula.ptg.Area3DPxg$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(Area3DPxg.this.getExternalWorkbookNumber());
            }
        }, "sheetName", new Supplier() { // from class: org.apache.poi.ss.formula.ptg.Area3DPxg$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return Area3DPxg.this.getSheetName();
            }
        }, "lastSheetName", new Supplier() { // from class: org.apache.poi.ss.formula.ptg.Area3DPxg$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return Area3DPxg.this.getLastSheetName();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-ss-formula-ptg-Area3DPxg, reason: not valid java name */
    public /* synthetic */ Object m2534x5df6b155() {
        return super.getGenericProperties();
    }
}
