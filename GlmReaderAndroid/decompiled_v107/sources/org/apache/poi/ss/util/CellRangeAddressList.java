package org.apache.poi.ss.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.util.LittleEndianByteArrayOutputStream;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public class CellRangeAddressList implements GenericRecord {
    protected final List<CellRangeAddress> _list = new ArrayList();

    public CellRangeAddressList() {
    }

    public CellRangeAddressList(int firstRow, int lastRow, int firstCol, int lastCol) {
        addCellRangeAddress(firstRow, firstCol, lastRow, lastCol);
    }

    public CellRangeAddressList(RecordInputStream in) {
        int nItems = in.readUShort();
        for (int k = 0; k < nItems; k++) {
            this._list.add(new CellRangeAddress(in));
        }
    }

    public int countRanges() {
        return this._list.size();
    }

    public void addCellRangeAddress(int firstRow, int firstCol, int lastRow, int lastCol) {
        CellRangeAddress region = new CellRangeAddress(firstRow, lastRow, firstCol, lastCol);
        addCellRangeAddress(region);
    }

    public void addCellRangeAddress(CellRangeAddress cra) {
        this._list.add(cra);
    }

    public CellRangeAddress remove(int rangeIndex) {
        if (this._list.isEmpty()) {
            throw new IllegalStateException("List is empty");
        }
        if (rangeIndex < 0 || rangeIndex >= this._list.size()) {
            throw new IllegalStateException("Range index (" + rangeIndex + ") is outside allowable range (0.." + (this._list.size() - 1) + ")");
        }
        return this._list.remove(rangeIndex);
    }

    public CellRangeAddress getCellRangeAddress(int index) {
        return this._list.get(index);
    }

    public int getSize() {
        return getEncodedSize(this._list.size());
    }

    public static int getEncodedSize(int numberOfRanges) {
        return CellRangeAddress.getEncodedSize(numberOfRanges) + 2;
    }

    public int serialize(int offset, byte[] data) {
        int totalSize = getSize();
        try {
            LittleEndianByteArrayOutputStream lebaos = new LittleEndianByteArrayOutputStream(data, offset, totalSize);
            try {
                serialize(lebaos);
                lebaos.close();
                return totalSize;
            } finally {
            }
        } catch (IOException ioe) {
            throw new IllegalStateException(ioe);
        }
    }

    public void serialize(LittleEndianOutput out) {
        int nItems = this._list.size();
        out.writeShort(nItems);
        for (CellRangeAddress region : this._list) {
            region.serialize(out);
        }
    }

    public CellRangeAddressList copy() {
        CellRangeAddressList result = new CellRangeAddressList();
        for (CellRangeAddress region : this._list) {
            result.addCellRangeAddress(region.copy());
        }
        return result;
    }

    public CellRangeAddress[] getCellRangeAddresses() {
        CellRangeAddress[] result = new CellRangeAddress[this._list.size()];
        this._list.toArray(result);
        return result;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return null;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public List<CellRangeAddress> getGenericChildren() {
        return this._list;
    }
}
