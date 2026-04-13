package org.apache.commons.math3.geometry.partitioning;

import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;

/* loaded from: classes10.dex */
class InsideFinder<S extends Space> {
    private final Region<S> region;
    private boolean plusFound = false;
    private boolean minusFound = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    public InsideFinder(Region<S> region) {
        this.region = region;
    }

    public void recurseSides(BSPTree<S> node, SubHyperplane<S> sub) {
        if (node.getCut() == null) {
            if (((Boolean) node.getAttribute()).booleanValue()) {
                this.plusFound = true;
                this.minusFound = true;
                return;
            }
            return;
        }
        Hyperplane<S> hyperplane = node.getCut().getHyperplane();
        SubHyperplane.SplitSubHyperplane<S> split = sub.split(hyperplane);
        switch (split.getSide()) {
            case PLUS:
                if (node.getCut().split(sub.getHyperplane()).getSide() == Side.PLUS) {
                    if (!this.region.isEmpty(node.getMinus())) {
                        this.plusFound = true;
                    }
                } else if (!this.region.isEmpty(node.getMinus())) {
                    this.minusFound = true;
                }
                if (!this.plusFound || !this.minusFound) {
                    recurseSides(node.getPlus(), sub);
                    return;
                }
                return;
            case MINUS:
                if (node.getCut().split(sub.getHyperplane()).getSide() == Side.PLUS) {
                    if (!this.region.isEmpty(node.getPlus())) {
                        this.plusFound = true;
                    }
                } else if (!this.region.isEmpty(node.getPlus())) {
                    this.minusFound = true;
                }
                if (!this.plusFound || !this.minusFound) {
                    recurseSides(node.getMinus(), sub);
                    return;
                }
                return;
            case BOTH:
                recurseSides(node.getPlus(), split.getPlus());
                if (!this.plusFound || !this.minusFound) {
                    recurseSides(node.getMinus(), split.getMinus());
                    return;
                }
                return;
            default:
                if (node.getCut().getHyperplane().sameOrientationAs(sub.getHyperplane())) {
                    if (node.getPlus().getCut() != null || ((Boolean) node.getPlus().getAttribute()).booleanValue()) {
                        this.plusFound = true;
                    }
                    if (node.getMinus().getCut() != null || ((Boolean) node.getMinus().getAttribute()).booleanValue()) {
                        this.minusFound = true;
                        return;
                    }
                    return;
                }
                if (node.getPlus().getCut() != null || ((Boolean) node.getPlus().getAttribute()).booleanValue()) {
                    this.minusFound = true;
                }
                if (node.getMinus().getCut() != null || ((Boolean) node.getMinus().getAttribute()).booleanValue()) {
                    this.plusFound = true;
                    return;
                }
                return;
        }
    }

    public boolean plusFound() {
        return this.plusFound;
    }

    public boolean minusFound() {
        return this.minusFound;
    }
}
