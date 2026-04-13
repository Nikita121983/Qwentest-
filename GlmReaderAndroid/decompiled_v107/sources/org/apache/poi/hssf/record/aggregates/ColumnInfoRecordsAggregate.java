package org.apache.poi.hssf.record.aggregates;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;
import org.apache.poi.common.Duplicatable;
import org.apache.poi.hssf.model.RecordStream;
import org.apache.poi.hssf.record.ColumnInfoRecord;
import org.apache.poi.hssf.record.aggregates.RecordAggregate;

/* loaded from: classes10.dex */
public final class ColumnInfoRecordsAggregate extends RecordAggregate implements Duplicatable {
    private final List<ColumnInfoRecord> records;

    public ColumnInfoRecordsAggregate() {
        this.records = new ArrayList();
    }

    public ColumnInfoRecordsAggregate(ColumnInfoRecordsAggregate other) {
        this.records = new ArrayList();
        Stream<R> map = other.records.stream().map(new Function() { // from class: org.apache.poi.hssf.record.aggregates.ColumnInfoRecordsAggregate$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((ColumnInfoRecord) obj).copy();
            }
        });
        final List<ColumnInfoRecord> list = this.records;
        list.getClass();
        map.forEach(new Consumer() { // from class: org.apache.poi.hssf.record.aggregates.ColumnInfoRecordsAggregate$$ExternalSyntheticLambda1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                list.add((ColumnInfoRecord) obj);
            }
        });
    }

    public ColumnInfoRecordsAggregate(RecordStream rs) {
        this();
        boolean isInOrder = true;
        ColumnInfoRecord cirPrev = null;
        while (rs.peekNextClass() == ColumnInfoRecord.class) {
            ColumnInfoRecord cir = (ColumnInfoRecord) rs.getNext();
            this.records.add(cir);
            if (cirPrev != null && compareColInfos(cirPrev, cir) > 0) {
                isInOrder = false;
            }
            cirPrev = cir;
        }
        if (this.records.size() < 1) {
            throw new IllegalStateException("No column info records found");
        }
        if (!isInOrder) {
            this.records.sort(new ColumnInfoRecordsAggregate$$ExternalSyntheticLambda2());
        }
    }

    @Override // org.apache.poi.common.Duplicatable
    public ColumnInfoRecordsAggregate copy() {
        return new ColumnInfoRecordsAggregate(this);
    }

    public void insertColumn(ColumnInfoRecord col) {
        this.records.add(col);
        this.records.sort(new ColumnInfoRecordsAggregate$$ExternalSyntheticLambda2());
    }

    private void insertColumn(int idx, ColumnInfoRecord col) {
        this.records.add(idx, col);
    }

    int getNumColumns() {
        return this.records.size();
    }

    @Override // org.apache.poi.hssf.record.aggregates.RecordAggregate
    public void visitContainedRecords(RecordAggregate.RecordVisitor rv) {
        int nItems = this.records.size();
        if (nItems < 1) {
            return;
        }
        ColumnInfoRecord cirPrev = null;
        for (ColumnInfoRecord cir : this.records) {
            rv.visitRecord(cir);
            if (cirPrev != null && compareColInfos(cirPrev, cir) > 0) {
                throw new IllegalStateException("Column info records are out of order");
            }
            cirPrev = cir;
        }
    }

    private int findStartOfColumnOutlineGroup(int pIdx) {
        ColumnInfoRecord columnInfo = this.records.get(pIdx);
        int level = columnInfo.getOutlineLevel();
        int idx = pIdx;
        while (idx != 0) {
            ColumnInfoRecord prevColumnInfo = this.records.get(idx - 1);
            if (!prevColumnInfo.isAdjacentBefore(columnInfo) || prevColumnInfo.getOutlineLevel() < level) {
                break;
            }
            idx--;
            columnInfo = prevColumnInfo;
        }
        return idx;
    }

    private int findEndOfColumnOutlineGroup(int colInfoIndex) {
        ColumnInfoRecord columnInfo = this.records.get(colInfoIndex);
        int level = columnInfo.getOutlineLevel();
        int idx = colInfoIndex;
        while (idx < this.records.size() - 1) {
            ColumnInfoRecord nextColumnInfo = this.records.get(idx + 1);
            if (!columnInfo.isAdjacentBefore(nextColumnInfo) || nextColumnInfo.getOutlineLevel() < level) {
                break;
            }
            idx++;
            columnInfo = nextColumnInfo;
        }
        return idx;
    }

    private ColumnInfoRecord getColInfo(int idx) {
        return this.records.get(idx);
    }

    private boolean isColumnGroupCollapsed(int idx) {
        int endOfOutlineGroupIdx = findEndOfColumnOutlineGroup(idx);
        int nextColInfoIx = endOfOutlineGroupIdx + 1;
        if (nextColInfoIx >= this.records.size()) {
            return false;
        }
        ColumnInfoRecord nextColInfo = getColInfo(nextColInfoIx);
        if (getColInfo(endOfOutlineGroupIdx).isAdjacentBefore(nextColInfo)) {
            return nextColInfo.getCollapsed();
        }
        return false;
    }

    private boolean isColumnGroupHiddenByParent(int idx) {
        int endLevel = 0;
        boolean endHidden = false;
        int endOfOutlineGroupIdx = findEndOfColumnOutlineGroup(idx);
        if (endOfOutlineGroupIdx < this.records.size()) {
            ColumnInfoRecord nextInfo = getColInfo(endOfOutlineGroupIdx + 1);
            if (getColInfo(endOfOutlineGroupIdx).isAdjacentBefore(nextInfo)) {
                endLevel = nextInfo.getOutlineLevel();
                endHidden = nextInfo.getHidden();
            }
        }
        int startLevel = 0;
        boolean startHidden = false;
        int startOfOutlineGroupIdx = findStartOfColumnOutlineGroup(idx);
        if (startOfOutlineGroupIdx > 0) {
            ColumnInfoRecord prevInfo = getColInfo(startOfOutlineGroupIdx - 1);
            if (prevInfo.isAdjacentBefore(getColInfo(startOfOutlineGroupIdx))) {
                startLevel = prevInfo.getOutlineLevel();
                startHidden = prevInfo.getHidden();
            }
        }
        if (endLevel > startLevel) {
            return endHidden;
        }
        return startHidden;
    }

    public void collapseColumn(int columnIndex) {
        int colInfoIx = findColInfoIdx(columnIndex, 0);
        if (colInfoIx == -1) {
            return;
        }
        int groupStartColInfoIx = findStartOfColumnOutlineGroup(colInfoIx);
        ColumnInfoRecord columnInfo = getColInfo(groupStartColInfoIx);
        int lastColIx = setGroupHidden(groupStartColInfoIx, columnInfo.getOutlineLevel(), true);
        setColumn(lastColIx + 1, null, null, null, null, Boolean.TRUE);
    }

    private int setGroupHidden(int pIdx, int level, boolean hidden) {
        int idx = pIdx;
        ColumnInfoRecord columnInfo = getColInfo(idx);
        while (idx < this.records.size()) {
            columnInfo.setHidden(hidden);
            if (idx + 1 < this.records.size()) {
                ColumnInfoRecord nextColumnInfo = getColInfo(idx + 1);
                if (!columnInfo.isAdjacentBefore(nextColumnInfo) || nextColumnInfo.getOutlineLevel() < level) {
                    break;
                }
                columnInfo = nextColumnInfo;
            }
            idx++;
        }
        return columnInfo.getLastColumn();
    }

    public void expandColumn(int columnIndex) {
        int idx = findColInfoIdx(columnIndex, 0);
        if (idx == -1 || !isColumnGroupCollapsed(idx)) {
            return;
        }
        int startIdx = findStartOfColumnOutlineGroup(idx);
        int endIdx = findEndOfColumnOutlineGroup(idx);
        ColumnInfoRecord columnInfo = getColInfo(endIdx);
        if (!isColumnGroupHiddenByParent(idx)) {
            int outlineLevel = columnInfo.getOutlineLevel();
            for (int i = startIdx; i <= endIdx; i++) {
                ColumnInfoRecord ci = getColInfo(i);
                if (outlineLevel == ci.getOutlineLevel()) {
                    ci.setHidden(false);
                }
            }
        }
        setColumn(columnInfo.getLastColumn() + 1, null, null, null, null, Boolean.FALSE);
    }

    private static ColumnInfoRecord copyColInfo(ColumnInfoRecord ci) {
        return ci.copy();
    }

    public void setColumn(int targetColumnIx, Short xfIndex, Integer width, Integer level, Boolean hidden, Boolean collapsed) {
        ColumnInfoRecord tci;
        int k = 0;
        while (k < this.records.size()) {
            tci = this.records.get(k);
            if (tci.containsColumn(targetColumnIx)) {
                break;
            } else if (tci.getFirstColumn() > targetColumnIx) {
                break;
            } else {
                k++;
            }
        }
        tci = null;
        if (tci == null) {
            ColumnInfoRecord nci = new ColumnInfoRecord();
            nci.setFirstColumn(targetColumnIx);
            nci.setLastColumn(targetColumnIx);
            setColumnInfoFields(nci, xfIndex, width, level, hidden, collapsed);
            insertColumn(k, nci);
            attemptMergeColInfoRecords(k);
            return;
        }
        boolean styleChanged = (xfIndex == null || tci.getXFIndex() == xfIndex.shortValue()) ? false : true;
        boolean widthChanged = (width == null || tci.getColumnWidth() == width.shortValue()) ? false : true;
        boolean levelChanged = (level == null || tci.getOutlineLevel() == level.intValue()) ? false : true;
        boolean hiddenChanged = (hidden == null || tci.getHidden() == hidden.booleanValue()) ? false : true;
        boolean collapsedChanged = (collapsed == null || tci.getCollapsed() == collapsed.booleanValue()) ? false : true;
        boolean columnChanged = styleChanged || widthChanged || levelChanged || hiddenChanged || collapsedChanged;
        if (!columnChanged) {
            return;
        }
        if (tci.getFirstColumn() == targetColumnIx && tci.getLastColumn() == targetColumnIx) {
            setColumnInfoFields(tci, xfIndex, width, level, hidden, collapsed);
            attemptMergeColInfoRecords(k);
            return;
        }
        if (tci.getFirstColumn() != targetColumnIx && tci.getLastColumn() != targetColumnIx) {
            ColumnInfoRecord ciMid = copyColInfo(tci);
            ColumnInfoRecord ciEnd = copyColInfo(tci);
            int lastcolumn = tci.getLastColumn();
            tci.setLastColumn(targetColumnIx - 1);
            ciMid.setFirstColumn(targetColumnIx);
            ciMid.setLastColumn(targetColumnIx);
            setColumnInfoFields(ciMid, xfIndex, width, level, hidden, collapsed);
            int k2 = k + 1;
            insertColumn(k2, ciMid);
            ciEnd.setFirstColumn(targetColumnIx + 1);
            ciEnd.setLastColumn(lastcolumn);
            insertColumn(k2 + 1, ciEnd);
            return;
        }
        if (tci.getFirstColumn() == targetColumnIx) {
            tci.setFirstColumn(targetColumnIx + 1);
        } else {
            tci.setLastColumn(targetColumnIx - 1);
            k++;
        }
        ColumnInfoRecord nci2 = copyColInfo(tci);
        nci2.setFirstColumn(targetColumnIx);
        nci2.setLastColumn(targetColumnIx);
        setColumnInfoFields(nci2, xfIndex, width, level, hidden, collapsed);
        insertColumn(k, nci2);
        attemptMergeColInfoRecords(k);
    }

    private static void setColumnInfoFields(ColumnInfoRecord ci, Short xfStyle, Integer width, Integer level, Boolean hidden, Boolean collapsed) {
        if (xfStyle != null) {
            ci.setXFIndex(xfStyle.shortValue());
        }
        if (width != null) {
            ci.setColumnWidth(width.intValue());
        }
        if (level != null) {
            ci.setOutlineLevel(level.shortValue());
        }
        if (hidden != null) {
            ci.setHidden(hidden.booleanValue());
        }
        if (collapsed != null) {
            ci.setCollapsed(collapsed.booleanValue());
        }
    }

    private int findColInfoIdx(int columnIx, int fromColInfoIdx) {
        if (columnIx < 0) {
            throw new IllegalArgumentException("column parameter out of range: " + columnIx);
        }
        if (fromColInfoIdx < 0) {
            throw new IllegalArgumentException("fromIdx parameter out of range: " + fromColInfoIdx);
        }
        for (int k = fromColInfoIdx; k < this.records.size(); k++) {
            ColumnInfoRecord ci = getColInfo(k);
            if (ci.containsColumn(columnIx)) {
                return k;
            }
            if (ci.getFirstColumn() > columnIx) {
                return -1;
            }
        }
        return -1;
    }

    private void attemptMergeColInfoRecords(int colInfoIx) {
        int nRecords = this.records.size();
        if (colInfoIx < 0 || colInfoIx >= nRecords) {
            throw new IllegalArgumentException("colInfoIx " + colInfoIx + " is out of range (0.." + (nRecords - 1) + ")");
        }
        ColumnInfoRecord currentCol = getColInfo(colInfoIx);
        int nextIx = colInfoIx + 1;
        if (nextIx < nRecords && mergeColInfoRecords(currentCol, getColInfo(nextIx))) {
            this.records.remove(nextIx);
        }
        if (colInfoIx > 0 && mergeColInfoRecords(getColInfo(colInfoIx - 1), currentCol)) {
            this.records.remove(colInfoIx);
        }
    }

    private static boolean mergeColInfoRecords(ColumnInfoRecord ciA, ColumnInfoRecord ciB) {
        if (ciA.isAdjacentBefore(ciB) && ciA.formatMatches(ciB)) {
            ciA.setLastColumn(ciB.getLastColumn());
            return true;
        }
        return false;
    }

    public void groupColumnRange(int fromColumnIx, int toColumnIx, boolean indent) {
        int colInfoSearchStartIdx = 0;
        for (int i = fromColumnIx; i <= toColumnIx; i++) {
            int level = 1;
            int colInfoIdx = findColInfoIdx(i, colInfoSearchStartIdx);
            if (colInfoIdx != -1) {
                int level2 = getColInfo(colInfoIdx).getOutlineLevel();
                level = Math.min(7, Math.max(0, indent ? level2 + 1 : level2 - 1));
                colInfoSearchStartIdx = Math.max(0, colInfoIdx - 1);
            }
            setColumn(i, null, null, Integer.valueOf(level), null, null);
        }
    }

    public ColumnInfoRecord findColumnInfo(int columnIndex) {
        int nInfos = this.records.size();
        for (int i = 0; i < nInfos; i++) {
            ColumnInfoRecord ci = getColInfo(i);
            if (ci.containsColumn(columnIndex)) {
                return ci;
            }
        }
        return null;
    }

    public int getMaxOutlineLevel() {
        int result = 0;
        int count = this.records.size();
        for (int i = 0; i < count; i++) {
            ColumnInfoRecord columnInfoRecord = getColInfo(i);
            result = Math.max(columnInfoRecord.getOutlineLevel(), result);
        }
        return result;
    }

    public int getOutlineLevel(int columnIndex) {
        ColumnInfoRecord ci = findColumnInfo(columnIndex);
        if (ci != null) {
            return ci.getOutlineLevel();
        }
        return 0;
    }

    public int getMinColumnIndex() {
        if (this.records.isEmpty()) {
            return 0;
        }
        int minIndex = Integer.MAX_VALUE;
        int nInfos = this.records.size();
        for (int i = 0; i < nInfos; i++) {
            ColumnInfoRecord ci = getColInfo(i);
            minIndex = Math.min(minIndex, ci.getFirstColumn());
        }
        return minIndex;
    }

    public int getMaxColumnIndex() {
        if (this.records.isEmpty()) {
            return 0;
        }
        int maxIndex = 0;
        int nInfos = this.records.size();
        for (int i = 0; i < nInfos; i++) {
            ColumnInfoRecord ci = getColInfo(i);
            maxIndex = Math.max(maxIndex, ci.getLastColumn());
        }
        return maxIndex;
    }

    public static int compareColInfos(ColumnInfoRecord a, ColumnInfoRecord b) {
        return a.getFirstColumn() - b.getFirstColumn();
    }
}
