package org.apache.commons.math3.genetics;

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.Localizable;

/* loaded from: classes10.dex */
public class InvalidRepresentationException extends MathIllegalArgumentException {
    private static final long serialVersionUID = 1;

    public InvalidRepresentationException(Localizable pattern, Object... args) {
        super(pattern, args);
    }
}
