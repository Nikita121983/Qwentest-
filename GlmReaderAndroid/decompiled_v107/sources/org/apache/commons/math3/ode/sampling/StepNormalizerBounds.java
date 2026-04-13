package org.apache.commons.math3.ode.sampling;

/* loaded from: classes10.dex */
public enum StepNormalizerBounds {
    NEITHER(false, false),
    FIRST(true, false),
    LAST(false, true),
    BOTH(true, true);

    private final boolean first;
    private final boolean last;

    StepNormalizerBounds(boolean first, boolean last) {
        this.first = first;
        this.last = last;
    }

    public boolean firstIncluded() {
        return this.first;
    }

    public boolean lastIncluded() {
        return this.last;
    }
}
