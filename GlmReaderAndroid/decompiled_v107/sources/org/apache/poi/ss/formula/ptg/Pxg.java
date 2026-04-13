package org.apache.poi.ss.formula.ptg;

/* loaded from: classes10.dex */
public interface Pxg {
    int getExternalWorkbookNumber();

    String getSheetName();

    void setSheetName(String str);

    String toFormulaString();
}
