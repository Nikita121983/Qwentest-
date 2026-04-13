package org.apache.commons.math3.geometry.euclidean.threed;

import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D;
import org.apache.commons.math3.geometry.euclidean.oned.Vector1D;
import org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D;
import org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.geometry.partitioning.AbstractSubHyperplane;
import org.apache.commons.math3.geometry.partitioning.BSPTree;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.geometry.partitioning.Region;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;

/* loaded from: classes10.dex */
public class SubPlane extends AbstractSubHyperplane<Euclidean3D, Euclidean2D> {
    public SubPlane(Hyperplane<Euclidean3D> hyperplane, Region<Euclidean2D> remainingRegion) {
        super(hyperplane, remainingRegion);
    }

    @Override // org.apache.commons.math3.geometry.partitioning.AbstractSubHyperplane
    protected AbstractSubHyperplane<Euclidean3D, Euclidean2D> buildNew(Hyperplane<Euclidean3D> hyperplane, Region<Euclidean2D> remainingRegion) {
        return new SubPlane(hyperplane, remainingRegion);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.math3.geometry.partitioning.AbstractSubHyperplane, org.apache.commons.math3.geometry.partitioning.SubHyperplane
    public SubHyperplane.SplitSubHyperplane<Euclidean3D> split(Hyperplane<Euclidean3D> hyperplane) {
        Plane plane = (Plane) hyperplane;
        Plane plane2 = (Plane) getHyperplane();
        Line intersection = plane.intersection(plane2);
        double tolerance = plane2.getTolerance();
        if (intersection == null) {
            double offset = plane.getOffset(plane2);
            if (offset < (-tolerance)) {
                return new SubHyperplane.SplitSubHyperplane<>(null, this);
            }
            if (offset > tolerance) {
                return new SubHyperplane.SplitSubHyperplane<>(this, null);
            }
            return new SubHyperplane.SplitSubHyperplane<>(null, null);
        }
        Point<Euclidean2D> subSpace = plane2.toSubSpace(intersection.toSpace((Point<Euclidean1D>) Vector1D.ZERO));
        Point<Euclidean2D> subSpace2 = plane2.toSubSpace(intersection.toSpace((Point<Euclidean1D>) Vector1D.ONE));
        Vector2D vector2D = subSpace;
        Vector2D vector2D2 = subSpace2;
        if (Vector3D.crossProduct(intersection.getDirection(), plane2.getNormal()).dotProduct(plane.getNormal()) < 0.0d) {
            vector2D = subSpace2;
            vector2D2 = subSpace;
        }
        SubHyperplane<Euclidean2D> wholeHyperplane = new org.apache.commons.math3.geometry.euclidean.twod.Line(vector2D, vector2D2, tolerance).wholeHyperplane();
        SubHyperplane<Euclidean2D> wholeHyperplane2 = new org.apache.commons.math3.geometry.euclidean.twod.Line(vector2D2, vector2D, tolerance).wholeHyperplane();
        BSPTree<Euclidean2D> split = getRemainingRegion().getTree(false).split(wholeHyperplane);
        return new SubHyperplane.SplitSubHyperplane<>(new SubPlane(plane2.copySelf(), new PolygonsSet((BSPTree<Euclidean2D>) (getRemainingRegion().isEmpty(split.getPlus()) ? new BSPTree(Boolean.FALSE) : new BSPTree(wholeHyperplane2, new BSPTree(Boolean.FALSE), split.getPlus(), null)), tolerance)), new SubPlane(plane2.copySelf(), new PolygonsSet((BSPTree<Euclidean2D>) (getRemainingRegion().isEmpty(split.getMinus()) ? new BSPTree(Boolean.FALSE) : new BSPTree(wholeHyperplane, new BSPTree(Boolean.FALSE), split.getMinus(), null)), tolerance)));
    }
}
