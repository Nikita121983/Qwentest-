package org.apache.poi.ss.extractor;

/* loaded from: classes10.dex */
public interface ExcelExtractor {
    String getText();

    void setFormulasNotResults(boolean z);

    void setIncludeCellComments(boolean z);

    void setIncludeHeadersFooters(boolean z);

    void setIncludeSheetNames(boolean z);
}
