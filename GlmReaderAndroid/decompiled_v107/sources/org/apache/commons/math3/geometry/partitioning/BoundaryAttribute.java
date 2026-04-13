package org.apache.commons.math3.geometry.partitioning;

import org.apache.commons.math3.geometry.Space;

/* loaded from: classes10.dex */
public class BoundaryAttribute<S extends Space> {
    private final SubHyperplane<S> plusInside;
    private final SubHyperplane<S> plusOutside;
    private final NodesSet<S> splitters;

    @Deprecated
    public BoundaryAttribute(SubHyperplane<S> plusOutside, SubHyperplane<S> plusInside) {
        this(plusOutside, plusInside, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BoundaryAttribute(SubHyperplane<S> plusOutside, SubHyperplane<S> plusInside, NodesSet<S> splitters) {
        this.plusOutside = plusOutside;
        this.plusInside = plusInside;
        this.splitters = splitters;
    }

    public SubHyperplane<S> getPlusOutside() {
        return this.plusOutside;
    }

    public SubHyperplane<S> getPlusInside() {
        return this.plusInside;
    }

    public NodesSet<S> getSplitters() {
        return this.splitters;
    }
}
