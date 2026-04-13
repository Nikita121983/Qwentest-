package org.apache.commons.math3.geometry.euclidean.threed;

import org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D;
import org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.geometry.partitioning.BSPTree;
import org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor;
import org.apache.commons.math3.geometry.partitioning.BoundaryAttribute;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes10.dex */
public class OutlineExtractor {
    private Vector3D u;
    private Vector3D v;
    private Vector3D w;

    public OutlineExtractor(Vector3D u, Vector3D v) {
        this.u = u;
        this.v = v;
        this.w = Vector3D.crossProduct(u, v);
    }

    public Vector2D[][] getOutline(PolyhedronsSet polyhedronsSet) {
        BoundaryProjector projector = new BoundaryProjector(polyhedronsSet.getTolerance());
        polyhedronsSet.getTree(true).visit(projector);
        PolygonsSet projected = projector.getProjected();
        Vector2D[][] outline = projected.getVertices();
        for (int i = 0; i < outline.length; i++) {
            Vector2D[] rawLoop = outline[i];
            int end = rawLoop.length;
            int j = 0;
            while (j < end) {
                if (pointIsBetween(rawLoop, end, j)) {
                    for (int k = j; k < end - 1; k++) {
                        rawLoop[k] = rawLoop[k + 1];
                    }
                    end--;
                } else {
                    j++;
                }
            }
            if (end != rawLoop.length) {
                outline[i] = new Vector2D[end];
                System.arraycopy(rawLoop, 0, outline[i], 0, end);
            }
        }
        return outline;
    }

    private boolean pointIsBetween(Vector2D[] loop, int n, int i) {
        Vector2D previous = loop[((i + n) - 1) % n];
        Vector2D current = loop[i];
        Vector2D next = loop[(i + 1) % n];
        double dx1 = current.getX() - previous.getX();
        double dy1 = current.getY() - previous.getY();
        double dx2 = next.getX() - current.getX();
        double dy2 = next.getY() - current.getY();
        double cross = (dx1 * dy2) - (dx2 * dy1);
        double dot = (dx1 * dx2) + (dy1 * dy2);
        double d1d2 = FastMath.sqrt(((dx1 * dx1) + (dy1 * dy1)) * ((dx2 * dx2) + (dy2 * dy2)));
        return FastMath.abs(cross) <= 1.0E-6d * d1d2 && dot >= 0.0d;
    }

    /* loaded from: classes10.dex */
    private class BoundaryProjector implements BSPTreeVisitor<Euclidean3D> {
        private PolygonsSet projected;
        private final double tolerance;

        BoundaryProjector(double tolerance) {
            this.projected = new PolygonsSet((BSPTree<Euclidean2D>) new BSPTree(Boolean.FALSE), tolerance);
            this.tolerance = tolerance;
        }

        @Override // org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor
        public BSPTreeVisitor.Order visitOrder(BSPTree<Euclidean3D> node) {
            return BSPTreeVisitor.Order.MINUS_SUB_PLUS;
        }

        @Override // org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor
        public void visitInternalNode(BSPTree<Euclidean3D> node) {
            BoundaryAttribute<Euclidean3D> attribute = (BoundaryAttribute) node.getAttribute();
            if (attribute.getPlusOutside() != null) {
                addContribution(attribute.getPlusOutside(), false);
            }
            if (attribute.getPlusInside() != null) {
                addContribution(attribute.getPlusInside(), true);
            }
        }

        @Override // org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor
        public void visitLeafNode(BSPTree<Euclidean3D> node) {
        }

        /* JADX WARN: Code restructure failed: missing block: B:39:0x00f4, code lost:
        
            if (r14 != 1) goto L44;
         */
        /* JADX WARN: Type inference failed for: r15v1, types: [org.apache.commons.math3.geometry.euclidean.threed.Vector3D] */
        /* JADX WARN: Type inference failed for: r4v4, types: [org.apache.commons.math3.geometry.euclidean.threed.Vector3D] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private void addContribution(org.apache.commons.math3.geometry.partitioning.SubHyperplane<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D> r32, boolean r33) {
            /*
                Method dump skipped, instructions count: 398
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.BoundaryProjector.addContribution(org.apache.commons.math3.geometry.partitioning.SubHyperplane, boolean):void");
        }

        public PolygonsSet getProjected() {
            return this.projected;
        }
    }
}
