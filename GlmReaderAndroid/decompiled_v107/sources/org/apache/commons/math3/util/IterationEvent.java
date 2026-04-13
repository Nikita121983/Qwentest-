package org.apache.commons.math3.util;

import java.util.EventObject;

/* loaded from: classes10.dex */
public class IterationEvent extends EventObject {
    private static final long serialVersionUID = 20120128;
    private final int iterations;

    public IterationEvent(Object source, int iterations) {
        super(source);
        this.iterations = iterations;
    }

    public int getIterations() {
        return this.iterations;
    }
}
