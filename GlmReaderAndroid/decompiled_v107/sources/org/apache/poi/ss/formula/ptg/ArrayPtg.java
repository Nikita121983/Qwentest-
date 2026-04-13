package org.apache.poi.ss.formula.ptg;

import java.lang.reflect.Array;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.math3.geometry.VectorFormat;
import org.apache.poi.ss.formula.constant.ConstantValueParser;
import org.apache.poi.ss.formula.constant.ErrorConstant;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class ArrayPtg extends Ptg {
    public static final int PLAIN_TOKEN_SIZE = 8;
    private static final int RESERVED_FIELD_LEN = 7;
    public static final byte sid = 32;
    private final Object[] _arrayValues;
    private final int _nColumns;
    private final int _nRows;
    private final int _reserved0Int;
    private final int _reserved1Short;
    private final int _reserved2Byte;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ArrayPtg(int reserved0, int reserved1, int reserved2, int nColumns, int nRows, Object[] arrayValues) {
        this._reserved0Int = reserved0;
        this._reserved1Short = reserved1;
        this._reserved2Byte = reserved2;
        this._nColumns = nColumns;
        this._nRows = nRows;
        this._arrayValues = (Object[]) arrayValues.clone();
    }

    public ArrayPtg(ArrayPtg other) {
        this._reserved0Int = other._reserved0Int;
        this._reserved1Short = other._reserved1Short;
        this._reserved2Byte = other._reserved2Byte;
        this._nColumns = other._nColumns;
        this._nRows = other._nRows;
        this._arrayValues = other._arrayValues == null ? null : (Object[]) other._arrayValues.clone();
    }

    public ArrayPtg(Object[][] values2d) {
        int nColumns = values2d[0].length;
        int nRows = values2d.length;
        this._nColumns = (short) nColumns;
        this._nRows = (short) nRows;
        Object[] vv = new Object[this._nColumns * this._nRows];
        for (int r = 0; r < nRows; r++) {
            Object[] rowData = values2d[r];
            for (int c = 0; c < nColumns; c++) {
                vv[getValueIndex(c, r)] = rowData[c];
            }
        }
        this._arrayValues = vv;
        this._reserved0Int = 0;
        this._reserved1Short = 0;
        this._reserved2Byte = 0;
    }

    public Object[][] getTokenArrayValues() {
        if (this._arrayValues == null) {
            throw new IllegalStateException("array values not read yet");
        }
        Object[][] result = (Object[][]) Array.newInstance((Class<?>) Object.class, this._nRows, this._nColumns);
        for (int r = 0; r < this._nRows; r++) {
            Object[] rowData = result[r];
            for (int c = 0; c < this._nColumns; c++) {
                rowData[c] = this._arrayValues[getValueIndex(c, r)];
            }
        }
        return result;
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public boolean isBaseToken() {
        return false;
    }

    int getValueIndex(int colIx, int rowIx) {
        if (colIx < 0 || colIx >= this._nColumns) {
            throw new IllegalArgumentException("Specified colIx (" + colIx + ") is outside the allowed range (0.." + (this._nColumns - 1) + ")");
        }
        if (rowIx < 0 || rowIx >= this._nRows) {
            throw new IllegalArgumentException("Specified rowIx (" + rowIx + ") is outside the allowed range (0.." + (this._nRows - 1) + ")");
        }
        return (this._nColumns * rowIx) + colIx;
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public void write(LittleEndianOutput out) {
        out.writeByte(getPtgClass() + 32);
        out.writeInt(this._reserved0Int);
        out.writeShort(this._reserved1Short);
        out.writeByte(this._reserved2Byte);
    }

    public int writeTokenValueBytes(LittleEndianOutput out) {
        out.writeByte(this._nColumns - 1);
        out.writeShort(this._nRows - 1);
        ConstantValueParser.encode(out, this._arrayValues);
        return ConstantValueParser.getEncodedSize(this._arrayValues) + 3;
    }

    public int getRowCount() {
        return this._nRows;
    }

    public int getColumnCount() {
        return this._nColumns;
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public int getSize() {
        return ConstantValueParser.getEncodedSize(this._arrayValues) + 11;
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public byte getSid() {
        return (byte) 32;
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public String toFormulaString() {
        StringBuilder b = new StringBuilder();
        b.append(VectorFormat.DEFAULT_PREFIX);
        for (int y = 0; y < this._nRows; y++) {
            if (y > 0) {
                b.append(";");
            }
            for (int x = 0; x < this._nColumns; x++) {
                if (x > 0) {
                    b.append(CollectionUtils.COMMA);
                }
                Object o = this._arrayValues[getValueIndex(x, y)];
                b.append(getConstantText(o));
            }
        }
        b.append(VectorFormat.DEFAULT_SUFFIX);
        return b.toString();
    }

    private static String getConstantText(Object o) {
        if (o == null) {
            throw new IllegalStateException("Array item cannot be null");
        }
        if (o instanceof String) {
            return "\"" + o + "\"";
        }
        if (o instanceof Double) {
            return NumberToTextConverter.toText(((Double) o).doubleValue());
        }
        if (o instanceof Boolean) {
            return ((Boolean) o).booleanValue() ? "TRUE" : "FALSE";
        }
        if (o instanceof ErrorConstant) {
            return ((ErrorConstant) o).getText();
        }
        throw new IllegalArgumentException("Unexpected constant class (" + o.getClass().getName() + ")");
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public byte getDefaultOperandClass() {
        return Ptg.CLASS_ARRAY;
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg, org.apache.poi.common.Duplicatable
    public ArrayPtg copy() {
        return new ArrayPtg(this);
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("reserved0", new Supplier() { // from class: org.apache.poi.ss.formula.ptg.ArrayPtg$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return ArrayPtg.this.m2540x5b677cb6();
            }
        }, "reserved1", new Supplier() { // from class: org.apache.poi.ss.formula.ptg.ArrayPtg$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return ArrayPtg.this.m2541xb2856d95();
            }
        }, "reserved2", new Supplier() { // from class: org.apache.poi.ss.formula.ptg.ArrayPtg$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return ArrayPtg.this.m2542x9a35e74();
            }
        }, "columnCount", new Supplier() { // from class: org.apache.poi.ss.formula.ptg.ArrayPtg$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(ArrayPtg.this.getColumnCount());
            }
        }, "rowCount", new Supplier() { // from class: org.apache.poi.ss.formula.ptg.ArrayPtg$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(ArrayPtg.this.getRowCount());
            }
        }, "arrayValues", new Supplier() { // from class: org.apache.poi.ss.formula.ptg.ArrayPtg$$ExternalSyntheticLambda5
            @Override // java.util.function.Supplier
            public final Object get() {
                return ArrayPtg.this.m2543x60c14f53();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-ss-formula-ptg-ArrayPtg, reason: not valid java name */
    public /* synthetic */ Object m2540x5b677cb6() {
        return Integer.valueOf(this._reserved0Int);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-ss-formula-ptg-ArrayPtg, reason: not valid java name */
    public /* synthetic */ Object m2541xb2856d95() {
        return Integer.valueOf(this._reserved1Short);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$2$org-apache-poi-ss-formula-ptg-ArrayPtg, reason: not valid java name */
    public /* synthetic */ Object m2542x9a35e74() {
        return Integer.valueOf(this._reserved2Byte);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$3$org-apache-poi-ss-formula-ptg-ArrayPtg, reason: not valid java name */
    public /* synthetic */ Object m2543x60c14f53() {
        return this._arrayValues == null ? "#values#uninitialised#" : toFormulaString();
    }
}
