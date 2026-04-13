package org.apache.poi.ss.formula.eval;

import org.apache.commons.collections4.CollectionUtils;

/* loaded from: classes10.dex */
public final class FunctionNameEval implements ValueEval {
    private final String _functionName;

    public FunctionNameEval(String functionName) {
        this._functionName = functionName;
    }

    public String getFunctionName() {
        return this._functionName;
    }

    public String toString() {
        return getClass().getName() + " [" + this._functionName + CollectionUtils.DEFAULT_TOSTRING_SUFFIX;
    }
}
