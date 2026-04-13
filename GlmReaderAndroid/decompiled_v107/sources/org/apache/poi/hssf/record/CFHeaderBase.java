package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.ss.util.CellRangeUtil;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public abstract class CFHeaderBase extends StandardRecord {
    private int field_1_numcf;
    private int field_2_need_recalculation_and_id;
    private CellRangeAddress field_3_enclosing_cell_range;
    private CellRangeAddressList field_4_cell_ranges;

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public abstract CFHeaderBase copy();

    protected abstract String getRecordName();

    /* JADX INFO: Access modifiers changed from: protected */
    public CFHeaderBase() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CFHeaderBase(CFHeaderBase other) {
        super(other);
        this.field_1_numcf = other.field_1_numcf;
        this.field_2_need_recalculation_and_id = other.field_2_need_recalculation_and_id;
        this.field_3_enclosing_cell_range = other.field_3_enclosing_cell_range.copy();
        this.field_4_cell_ranges = other.field_4_cell_ranges.copy();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CFHeaderBase(CellRangeAddress[] regions, int nRules) {
        CellRangeAddress[] mergeCellRanges = CellRangeUtil.mergeCellRanges(regions);
        setCellRanges(mergeCellRanges);
        this.field_1_numcf = nRules;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void createEmpty() {
        this.field_3_enclosing_cell_range = new CellRangeAddress(0, 0, 0, 0);
        this.field_4_cell_ranges = new CellRangeAddressList();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void read(RecordInputStream in) {
        this.field_1_numcf = in.readShort();
        this.field_2_need_recalculation_and_id = in.readShort();
        this.field_3_enclosing_cell_range = new CellRangeAddress(in);
        this.field_4_cell_ranges = new CellRangeAddressList(in);
    }

    public int getNumberOfConditionalFormats() {
        return this.field_1_numcf;
    }

    public void setNumberOfConditionalFormats(int n) {
        this.field_1_numcf = n;
    }

    public boolean getNeedRecalculation() {
        return (this.field_2_need_recalculation_and_id & 1) == 1;
    }

    public void setNeedRecalculation(boolean b) {
        if (b == getNeedRecalculation()) {
            return;
        }
        if (b) {
            this.field_2_need_recalculation_and_id++;
        } else {
            this.field_2_need_recalculation_and_id--;
        }
    }

    public int getID() {
        return this.field_2_need_recalculation_and_id >> 1;
    }

    public void setID(int id) {
        boolean needsRecalc = getNeedRecalculation();
        this.field_2_need_recalculation_and_id = id << 1;
        if (needsRecalc) {
            this.field_2_need_recalculation_and_id++;
        }
    }

    public CellRangeAddress getEnclosingCellRange() {
        return this.field_3_enclosing_cell_range;
    }

    public void setEnclosingCellRange(CellRangeAddress cr) {
        this.field_3_enclosing_cell_range = cr;
    }

    public void setCellRanges(CellRangeAddress[] cellRanges) {
        if (cellRanges == null) {
            throw new IllegalArgumentException("cellRanges must not be null");
        }
        CellRangeAddressList cral = new CellRangeAddressList();
        CellRangeAddress enclosingRange = null;
        for (CellRangeAddress cr : cellRanges) {
            enclosingRange = CellRangeUtil.createEnclosingCellRange(cr, enclosingRange);
            cral.addCellRangeAddress(cr);
        }
        this.field_3_enclosing_cell_range = enclosingRange;
        this.field_4_cell_ranges = cral;
    }

    public CellRangeAddress[] getCellRanges() {
        return this.field_4_cell_ranges.getCellRangeAddresses();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.hssf.record.StandardRecord
    public int getDataSize() {
        return this.field_4_cell_ranges.getSize() + 12;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.field_1_numcf);
        out.writeShort(this.field_2_need_recalculation_and_id);
        this.field_3_enclosing_cell_range.serialize(out);
        this.field_4_cell_ranges.serialize(out);
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("id", new Supplier() { // from class: org.apache.poi.hssf.record.CFHeaderBase$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(CFHeaderBase.this.getID());
            }
        }, "numCF", new Supplier() { // from class: org.apache.poi.hssf.record.CFHeaderBase$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(CFHeaderBase.this.getNumberOfConditionalFormats());
            }
        }, "needRecalculationAndId", new Supplier() { // from class: org.apache.poi.hssf.record.CFHeaderBase$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(CFHeaderBase.this.getNeedRecalculation());
            }
        }, "enclosingCellRange", new Supplier() { // from class: org.apache.poi.hssf.record.CFHeaderBase$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return CFHeaderBase.this.getEnclosingCellRange();
            }
        }, "cfRanges", new Supplier() { // from class: org.apache.poi.hssf.record.CFHeaderBase$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return CFHeaderBase.this.getCellRanges();
            }
        });
    }
}
