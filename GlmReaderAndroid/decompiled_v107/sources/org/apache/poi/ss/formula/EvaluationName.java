package org.apache.poi.ss.formula;

import org.apache.poi.ss.formula.ptg.NamePtg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.util.Internal;

@Internal
/* loaded from: classes10.dex */
public interface EvaluationName {
    NamePtg createPtg();

    Ptg[] getNameDefinition();

    String getNameText();

    boolean hasFormula();

    boolean isFunctionName();

    boolean isRange();
}
