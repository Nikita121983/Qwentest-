package org.apache.poi.xddf.usermodel.chart;

/* loaded from: classes10.dex */
public interface XDDFCategoryDataSource extends XDDFDataSource<String> {
    @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
    default int getColIndex() {
        return 0;
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
    default boolean isLiteral() {
        return false;
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
    default boolean isNumeric() {
        return false;
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
    default boolean isReference() {
        return true;
    }
}
