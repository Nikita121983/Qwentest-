package org.apache.commons.math3.optim;

/* loaded from: classes10.dex */
public interface ConvergenceChecker<PAIR> {
    boolean converged(int i, PAIR pair, PAIR pair2);
}
