package org.apache.poi.ss.formula.eval;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.formula.ptg.StringPtg;

/* loaded from: classes10.dex */
public final class StringEval implements StringValueEval {
    public static final StringEval EMPTY_INSTANCE = new StringEval("");
    private final String _value;

    public StringEval(Ptg ptg) {
        this(((StringPtg) ptg).getValue());
    }

    public StringEval(String value) {
        if (value == null) {
            throw new IllegalArgumentException("value must not be null");
        }
        this._value = value;
    }

    @Override // org.apache.poi.ss.formula.eval.StringValueEval
    public String getStringValue() {
        return this._value;
    }

    public String toString() {
        return getClass().getName() + " [" + this._value + CollectionUtils.DEFAULT_TOSTRING_SUFFIX;
    }
}
