package org.apache.poi.ss.formula;

import org.apache.poi.util.Internal;

@Internal
/* loaded from: classes10.dex */
public interface EvaluationSheet {
    void clearAllCachedResultValues();

    EvaluationCell getCell(int i, int i2);

    int getLastRowNum();

    boolean isRowHidden(int i);
}
