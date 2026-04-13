package org.apache.poi.xddf.usermodel.chart;

import java.lang.Number;

/* loaded from: classes10.dex */
public interface XDDFNumericalDataSource<T extends Number> extends XDDFDataSource<T> {
    void setFormatCode(String str);

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
    default boolean isLiteral() {
        return false;
    }
}
