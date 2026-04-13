package org.apache.commons.math3.stat.regression;

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.Localizable;

/* loaded from: classes10.dex */
public class ModelSpecificationException extends MathIllegalArgumentException {
    private static final long serialVersionUID = 4206514456095401070L;

    public ModelSpecificationException(Localizable pattern, Object... args) {
        super(pattern, args);
    }
}
