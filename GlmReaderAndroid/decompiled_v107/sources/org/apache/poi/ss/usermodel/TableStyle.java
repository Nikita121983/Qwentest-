package org.apache.poi.ss.usermodel;

/* loaded from: classes10.dex */
public interface TableStyle {
    int getIndex();

    String getName();

    DifferentialStyleProvider getStyle(TableStyleType tableStyleType);

    boolean isBuiltin();
}
