package org.apache.poi.ss.usermodel;

/* loaded from: classes10.dex */
public interface DifferentialStyleProvider {
    BorderFormatting getBorderFormatting();

    FontFormatting getFontFormatting();

    ExcelNumberFormat getNumberFormat();

    PatternFormatting getPatternFormatting();

    int getStripeSize();
}
