package org.apache.poi.ss.formula.eval;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.formula.EvaluationName;

/* loaded from: classes10.dex */
public final class ExternalNameEval implements ValueEval {
    private final EvaluationName _name;

    public ExternalNameEval(EvaluationName name) {
        this._name = name;
    }

    public EvaluationName getName() {
        return this._name;
    }

    public String toString() {
        return getClass().getName() + " [" + this._name.getNameText() + CollectionUtils.DEFAULT_TOSTRING_SUFFIX;
    }
}
