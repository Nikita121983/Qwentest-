package org.apache.commons.math3.exception;

import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;

/* loaded from: classes10.dex */
public class MathInternalError extends MathIllegalStateException {
    private static final String REPORT_URL = "https://issues.apache.org/jira/browse/MATH";
    private static final long serialVersionUID = -6276776513966934846L;

    public MathInternalError() {
        getContext().addMessage(LocalizedFormats.INTERNAL_ERROR, REPORT_URL);
    }

    public MathInternalError(Throwable cause) {
        super(cause, LocalizedFormats.INTERNAL_ERROR, REPORT_URL);
    }

    public MathInternalError(Localizable pattern, Object... args) {
        super(pattern, args);
    }
}
