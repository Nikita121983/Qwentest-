package org.apache.commons.math3.exception;

import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;

/* loaded from: classes10.dex */
public class OutOfRangeException extends MathIllegalNumberException {
    private static final long serialVersionUID = 111601815794403609L;
    private final Number hi;
    private final Number lo;

    public OutOfRangeException(Number wrong, Number lo, Number hi) {
        this(LocalizedFormats.OUT_OF_RANGE_SIMPLE, wrong, lo, hi);
    }

    public OutOfRangeException(Localizable specific, Number wrong, Number lo, Number hi) {
        super(specific, wrong, lo, hi);
        this.lo = lo;
        this.hi = hi;
    }

    public Number getLo() {
        return this.lo;
    }

    public Number getHi() {
        return this.hi;
    }
}
