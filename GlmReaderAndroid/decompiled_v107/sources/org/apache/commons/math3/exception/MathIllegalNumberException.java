package org.apache.commons.math3.exception;

import org.apache.commons.math3.exception.util.Localizable;

/* loaded from: classes10.dex */
public class MathIllegalNumberException extends MathIllegalArgumentException {
    protected static final Integer INTEGER_ZERO = 0;
    private static final long serialVersionUID = -7447085893598031110L;
    private final Number argument;

    /* JADX INFO: Access modifiers changed from: protected */
    public MathIllegalNumberException(Localizable pattern, Number wrong, Object... arguments) {
        super(pattern, wrong, arguments);
        this.argument = wrong;
    }

    public Number getArgument() {
        return this.argument;
    }
}
