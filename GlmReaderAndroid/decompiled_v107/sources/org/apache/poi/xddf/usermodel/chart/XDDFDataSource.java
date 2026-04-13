package org.apache.poi.xddf.usermodel.chart;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumData;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumVal;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTStrData;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTStrVal;

/* loaded from: classes10.dex */
public interface XDDFDataSource<T> {
    int getColIndex();

    String getDataRangeReference();

    String getFormatCode();

    T getPointAt(int i);

    int getPointCount();

    boolean isCellRange();

    boolean isLiteral();

    boolean isNumeric();

    boolean isReference();

    default String getFormula() {
        return getDataRangeReference();
    }

    @Internal
    default void fillNumericalCache(CTNumData cache) {
        String formatCode = getFormatCode();
        if (formatCode == null) {
            if (cache.isSetFormatCode()) {
                cache.unsetFormatCode();
            }
        } else {
            cache.setFormatCode(formatCode);
        }
        cache.setPtArray(null);
        int numOfPoints = getPointCount();
        int effectiveNumOfPoints = 0;
        for (int i = 0; i < numOfPoints; i++) {
            Object value = getPointAt(i);
            if (value != null) {
                CTNumVal ctNumVal = cache.addNewPt();
                ctNumVal.setIdx(i);
                ctNumVal.setV(value.toString());
                effectiveNumOfPoints++;
            }
        }
        if (effectiveNumOfPoints == 0) {
            if (cache.isSetPtCount()) {
                cache.unsetPtCount();
            }
        } else if (cache.isSetPtCount()) {
            cache.getPtCount().setVal(numOfPoints);
        } else {
            cache.addNewPtCount().setVal(numOfPoints);
        }
    }

    @Internal
    default void fillStringCache(CTStrData cache) {
        cache.setPtArray(null);
        int numOfPoints = getPointCount();
        int effectiveNumOfPoints = 0;
        for (int i = 0; i < numOfPoints; i++) {
            Object value = getPointAt(i);
            if (value != null) {
                CTStrVal ctStrVal = cache.addNewPt();
                ctStrVal.setIdx(i);
                ctStrVal.setV(value.toString());
                effectiveNumOfPoints++;
            }
        }
        if (effectiveNumOfPoints == 0) {
            if (cache.isSetPtCount()) {
                cache.unsetPtCount();
            }
        } else if (cache.isSetPtCount()) {
            cache.getPtCount().setVal(numOfPoints);
        } else {
            cache.addNewPtCount().setVal(numOfPoints);
        }
    }
}
