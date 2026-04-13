package org.apache.commons.math3.geometry.euclidean.threed;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D;
import org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D;
import org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.geometry.partitioning.AbstractRegion;
import org.apache.commons.math3.geometry.partitioning.BSPTree;
import org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor;
import org.apache.commons.math3.geometry.partitioning.BoundaryAttribute;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.geometry.partitioning.Region;
import org.apache.commons.math3.geometry.partitioning.RegionFactory;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
import org.apache.commons.math3.geometry.partitioning.Transform;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes10.dex */
public class PolyhedronsSet extends AbstractRegion<Euclidean3D, Euclidean2D> {
    private static final double DEFAULT_TOLERANCE = 1.0E-10d;

    @Override // org.apache.commons.math3.geometry.partitioning.AbstractRegion, org.apache.commons.math3.geometry.partitioning.Region
    public /* bridge */ /* synthetic */ AbstractRegion buildNew(BSPTree bSPTree) {
        return buildNew((BSPTree<Euclidean3D>) bSPTree);
    }

    @Override // org.apache.commons.math3.geometry.partitioning.AbstractRegion, org.apache.commons.math3.geometry.partitioning.Region
    public /* bridge */ /* synthetic */ Region buildNew(BSPTree bSPTree) {
        return buildNew((BSPTree<Euclidean3D>) bSPTree);
    }

    public PolyhedronsSet(double tolerance) {
        super(tolerance);
    }

    public PolyhedronsSet(BSPTree<Euclidean3D> tree, double tolerance) {
        super(tree, tolerance);
    }

    public PolyhedronsSet(Collection<SubHyperplane<Euclidean3D>> boundary, double tolerance) {
        super(boundary, tolerance);
    }

    public PolyhedronsSet(List<Vector3D> vertices, List<int[]> facets, double tolerance) {
        super(buildBoundary(vertices, facets, tolerance), tolerance);
    }

    public PolyhedronsSet(double xMin, double xMax, double yMin, double yMax, double zMin, double zMax, double tolerance) {
        super(buildBoundary(xMin, xMax, yMin, yMax, zMin, zMax, tolerance), tolerance);
    }

    @Deprecated
    public PolyhedronsSet() {
        this(1.0E-10d);
    }

    @Deprecated
    public PolyhedronsSet(BSPTree<Euclidean3D> tree) {
        this(tree, 1.0E-10d);
    }

    @Deprecated
    public PolyhedronsSet(Collection<SubHyperplane<Euclidean3D>> boundary) {
        this(boundary, 1.0E-10d);
    }

    @Deprecated
    public PolyhedronsSet(double xMin, double xMax, double yMin, double yMax, double zMin, double zMax) {
        this(xMin, xMax, yMin, yMax, zMin, zMax, 1.0E-10d);
    }

    private static BSPTree<Euclidean3D> buildBoundary(double xMin, double xMax, double yMin, double yMax, double zMin, double zMax, double tolerance) {
        if (xMin < xMax - tolerance && yMin < yMax - tolerance && zMin < zMax - tolerance) {
            Plane pxMin = new Plane(new Vector3D(xMin, 0.0d, 0.0d), Vector3D.MINUS_I, tolerance);
            Plane pxMax = new Plane(new Vector3D(xMax, 0.0d, 0.0d), Vector3D.PLUS_I, tolerance);
            Plane pyMin = new Plane(new Vector3D(0.0d, yMin, 0.0d), Vector3D.MINUS_J, tolerance);
            Plane pyMax = new Plane(new Vector3D(0.0d, yMax, 0.0d), Vector3D.PLUS_J, tolerance);
            Plane pzMin = new Plane(new Vector3D(0.0d, 0.0d, zMin), Vector3D.MINUS_K, tolerance);
            Plane pzMax = new Plane(new Vector3D(0.0d, 0.0d, zMax), Vector3D.PLUS_K, tolerance);
            Region<Euclidean3D> boundary = new RegionFactory().buildConvex(pxMin, pxMax, pyMin, pyMax, pzMin, pzMax);
            return boundary.getTree(false);
        }
        return new BSPTree<>(Boolean.FALSE);
    }

    private static List<SubHyperplane<Euclidean3D>> buildBoundary(List<Vector3D> vertices, List<int[]> facets, double tolerance) {
        List<Vector3D> list = vertices;
        for (int i = 0; i < list.size() - 1; i++) {
            Vector3D vi = list.get(i);
            for (int j = i + 1; j < list.size(); j++) {
                if (Vector3D.distance(vi, list.get(j)) <= tolerance) {
                    throw new MathIllegalArgumentException(LocalizedFormats.CLOSE_VERTICES, Double.valueOf(vi.getX()), Double.valueOf(vi.getY()), Double.valueOf(vi.getZ()));
                }
            }
        }
        int[][] references = findReferences(vertices, facets);
        int[][] successors = successors(list, facets, references);
        int vA = 0;
        while (vA < list.size()) {
            int[] arr$ = successors[vA];
            for (int vB : arr$) {
                if (vB >= 0) {
                    boolean found = false;
                    for (int i2 : successors[vB]) {
                        found = found || i2 == vA;
                    }
                    if (!found) {
                        Vector3D start = list.get(vA);
                        Vector3D end = list.get(vB);
                        throw new MathIllegalArgumentException(LocalizedFormats.EDGE_CONNECTED_TO_ONE_FACET, Double.valueOf(start.getX()), Double.valueOf(start.getY()), Double.valueOf(start.getZ()), Double.valueOf(end.getX()), Double.valueOf(end.getY()), Double.valueOf(end.getZ()));
                    }
                }
            }
            vA++;
        }
        List<SubHyperplane<Euclidean3D>> boundary = new ArrayList<>();
        for (int[] facet : facets) {
            Plane plane = new Plane(list.get(facet[0]), list.get(facet[1]), list.get(facet[2]), tolerance);
            Vector2D[] two2Points = new Vector2D[facet.length];
            for (int i3 = 0; i3 < facet.length; i3++) {
                Vector3D v = list.get(facet[i3]);
                if (!plane.contains(v)) {
                    throw new MathIllegalArgumentException(LocalizedFormats.OUT_OF_PLANE, Double.valueOf(v.getX()), Double.valueOf(v.getY()), Double.valueOf(v.getZ()));
                }
                two2Points[i3] = plane.toSubSpace((Vector<Euclidean3D>) v);
            }
            boundary.add(new SubPlane(plane, new PolygonsSet(tolerance, two2Points)));
            list = vertices;
        }
        return boundary;
    }

    private static int[][] findReferences(List<Vector3D> vertices, List<int[]> facets) {
        int[] nbFacets = new int[vertices.size()];
        int maxFacets = 0;
        for (int[] facet : facets) {
            if (facet.length < 3) {
                throw new NumberIsTooSmallException(LocalizedFormats.WRONG_NUMBER_OF_POINTS, 3, Integer.valueOf(facet.length), true);
            }
            for (int index : facet) {
                int i = nbFacets[index] + 1;
                nbFacets[index] = i;
                maxFacets = FastMath.max(maxFacets, i);
            }
        }
        int[][] references = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, vertices.size(), maxFacets);
        for (int[] r : references) {
            Arrays.fill(r, -1);
        }
        for (int f = 0; f < facets.size(); f++) {
            int[] arr$ = facets.get(f);
            for (int v : arr$) {
                int k = 0;
                while (k < maxFacets && references[v][k] >= 0) {
                    k++;
                }
                references[v][k] = f;
            }
        }
        return references;
    }

    private static int[][] successors(List<Vector3D> vertices, List<int[]> facets, int[][] references) {
        int[][] successors = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, vertices.size(), references[0].length);
        for (int[] s : successors) {
            Arrays.fill(s, -1);
        }
        for (int v = 0; v < vertices.size(); v++) {
            for (int k = 0; k < successors[v].length && references[v][k] >= 0; k++) {
                int[] facet = facets.get(references[v][k]);
                int i = 0;
                while (i < facet.length && facet[i] != v) {
                    i++;
                }
                successors[v][k] = facet[(i + 1) % facet.length];
                for (int l = 0; l < k; l++) {
                    if (successors[v][l] == successors[v][k]) {
                        Vector3D start = vertices.get(v);
                        Vector3D end = vertices.get(successors[v][k]);
                        throw new MathIllegalArgumentException(LocalizedFormats.FACET_ORIENTATION_MISMATCH, Double.valueOf(start.getX()), Double.valueOf(start.getY()), Double.valueOf(start.getZ()), Double.valueOf(end.getX()), Double.valueOf(end.getY()), Double.valueOf(end.getZ()));
                    }
                }
            }
        }
        return successors;
    }

    @Override // org.apache.commons.math3.geometry.partitioning.AbstractRegion, org.apache.commons.math3.geometry.partitioning.Region
    public PolyhedronsSet buildNew(BSPTree<Euclidean3D> tree) {
        return new PolyhedronsSet(tree, getTolerance());
    }

    @Override // org.apache.commons.math3.geometry.partitioning.AbstractRegion
    protected void computeGeometricalProperties() {
        getTree(true).visit(new FacetsContributionVisitor());
        if (getSize() < 0.0d) {
            setSize(Double.POSITIVE_INFINITY);
            setBarycenter((Point) Vector3D.NaN);
        } else {
            setSize(getSize() / 3.0d);
            setBarycenter((Point) new Vector3D(1.0d / (getSize() * 4.0d), (Vector3D) getBarycenter()));
        }
    }

    /* loaded from: classes10.dex */
    private class FacetsContributionVisitor implements BSPTreeVisitor<Euclidean3D> {
        FacetsContributionVisitor() {
            PolyhedronsSet.this.setSize(0.0d);
            PolyhedronsSet.this.setBarycenter((Point) new Vector3D(0.0d, 0.0d, 0.0d));
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

        /* JADX WARN: Type inference failed for: r11v0, types: [org.apache.commons.math3.geometry.euclidean.threed.Vector3D] */
        private void addContribution(SubHyperplane<Euclidean3D> facet, boolean reversed) {
            double scaled;
            Region<Euclidean2D> polygon = ((SubPlane) facet).getRemainingRegion();
            double area = polygon.getSize();
            if (Double.isInfinite(area)) {
                PolyhedronsSet.this.setSize(Double.POSITIVE_INFINITY);
                PolyhedronsSet.this.setBarycenter((Point) Vector3D.NaN);
                return;
            }
            Plane plane = (Plane) facet.getHyperplane();
            ?? space = plane.toSpace(polygon.getBarycenter());
            double scaled2 = space.dotProduct(plane.getNormal()) * area;
            if (!reversed) {
                scaled = scaled2;
            } else {
                scaled = -scaled2;
            }
            PolyhedronsSet.this.setSize(PolyhedronsSet.this.getSize() + scaled);
            PolyhedronsSet.this.setBarycenter((Point) new Vector3D(1.0d, (Vector3D) PolyhedronsSet.this.getBarycenter(), scaled, space));
        }
    }

    public SubHyperplane<Euclidean3D> firstIntersection(Vector3D point, Line line) {
        return recurseFirstIntersection(getTree(true), point, line);
    }

    private SubHyperplane<Euclidean3D> recurseFirstIntersection(BSPTree<Euclidean3D> node, Vector3D point, Line line) {
        BSPTree<Euclidean3D> near;
        BSPTree<Euclidean3D> far;
        Vector3D hit3D;
        SubHyperplane<Euclidean3D> facet;
        SubHyperplane<Euclidean3D> facet2;
        SubHyperplane<Euclidean3D> cut = node.getCut();
        if (cut == null) {
            return null;
        }
        BSPTree<Euclidean3D> minus = node.getMinus();
        BSPTree<Euclidean3D> plus = node.getPlus();
        Plane plane = (Plane) cut.getHyperplane();
        double offset = plane.getOffset((Point<Euclidean3D>) point);
        boolean in = FastMath.abs(offset) < getTolerance();
        if (offset < 0.0d) {
            near = minus;
            far = plus;
        } else {
            near = plus;
            far = minus;
        }
        if (in && (facet2 = boundaryFacet(point, node)) != null) {
            return facet2;
        }
        SubHyperplane<Euclidean3D> crossed = recurseFirstIntersection(near, point, line);
        if (crossed != null) {
            return crossed;
        }
        if (!in && (hit3D = plane.intersection(line)) != null && line.getAbscissa(hit3D) > line.getAbscissa(point) && (facet = boundaryFacet(hit3D, node)) != null) {
            return facet;
        }
        return recurseFirstIntersection(far, point, line);
    }

    private SubHyperplane<Euclidean3D> boundaryFacet(Vector3D point, BSPTree<Euclidean3D> node) {
        Point<Euclidean2D> subSpace = ((Plane) node.getCut().getHyperplane()).toSubSpace((Point<Euclidean3D>) point);
        BoundaryAttribute<Euclidean3D> attribute = (BoundaryAttribute) node.getAttribute();
        if (attribute.getPlusOutside() != null && ((SubPlane) attribute.getPlusOutside()).getRemainingRegion().checkPoint(subSpace) == Region.Location.INSIDE) {
            return attribute.getPlusOutside();
        }
        if (attribute.getPlusInside() != null && ((SubPlane) attribute.getPlusInside()).getRemainingRegion().checkPoint(subSpace) == Region.Location.INSIDE) {
            return attribute.getPlusInside();
        }
        return null;
    }

    public PolyhedronsSet rotate(Vector3D center, Rotation rotation) {
        return (PolyhedronsSet) applyTransform(new RotationTransform(center, rotation));
    }

    /* loaded from: classes10.dex */
    private static class RotationTransform implements Transform<Euclidean3D, Euclidean2D> {
        private Plane cachedOriginal;
        private Transform<Euclidean2D, Euclidean1D> cachedTransform;
        private Vector3D center;
        private Rotation rotation;

        RotationTransform(Vector3D center, Rotation rotation) {
            this.center = center;
            this.rotation = rotation;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v2, types: [org.apache.commons.math3.geometry.euclidean.threed.Vector3D] */
        @Override // org.apache.commons.math3.geometry.partitioning.Transform
        public Point<Euclidean3D> apply(Point<Euclidean3D> point) {
            return new Vector3D(1.0d, this.center, 1.0d, this.rotation.applyTo((Vector3D) ((Vector3D) point).subtract((Vector<Euclidean3D>) this.center)));
        }

        @Override // org.apache.commons.math3.geometry.partitioning.Transform
        public Hyperplane<Euclidean3D> apply(Hyperplane<Euclidean3D> hyperplane) {
            return ((Plane) hyperplane).rotate(this.center, this.rotation);
        }

        /* JADX WARN: Type inference failed for: r7v1, types: [org.apache.commons.math3.geometry.euclidean.twod.Vector2D] */
        /* JADX WARN: Type inference failed for: r8v2, types: [org.apache.commons.math3.geometry.euclidean.twod.Vector2D] */
        /* JADX WARN: Type inference failed for: r9v1, types: [org.apache.commons.math3.geometry.euclidean.twod.Vector2D] */
        @Override // org.apache.commons.math3.geometry.partitioning.Transform
        public SubHyperplane<Euclidean2D> apply(SubHyperplane<Euclidean2D> sub, Hyperplane<Euclidean3D> original, Hyperplane<Euclidean3D> transformed) {
            if (original != this.cachedOriginal) {
                Plane oPlane = (Plane) original;
                Plane tPlane = (Plane) transformed;
                Vector3D p00 = oPlane.getOrigin();
                Point<Euclidean3D> space = oPlane.toSpace((Point<Euclidean2D>) new Vector2D(1.0d, 0.0d));
                Point<Euclidean3D> space2 = oPlane.toSpace((Point<Euclidean2D>) new Vector2D(0.0d, 1.0d));
                ?? subSpace = tPlane.toSubSpace(apply((Point<Euclidean3D>) p00));
                ?? subSpace2 = tPlane.toSubSpace(apply(space));
                ?? subSpace3 = tPlane.toSubSpace(apply(space2));
                this.cachedOriginal = (Plane) original;
                this.cachedTransform = org.apache.commons.math3.geometry.euclidean.twod.Line.getTransform(subSpace2.getX() - subSpace.getX(), subSpace2.getY() - subSpace.getY(), subSpace3.getX() - subSpace.getX(), subSpace3.getY() - subSpace.getY(), subSpace.getX(), subSpace.getY());
            }
            return ((org.apache.commons.math3.geometry.euclidean.twod.SubLine) sub).applyTransform(this.cachedTransform);
        }
    }

    public PolyhedronsSet translate(Vector3D translation) {
        return (PolyhedronsSet) applyTransform(new TranslationTransform(translation));
    }

    /* loaded from: classes10.dex */
    private static class TranslationTransform implements Transform<Euclidean3D, Euclidean2D> {
        private Plane cachedOriginal;
        private Transform<Euclidean2D, Euclidean1D> cachedTransform;
        private Vector3D translation;

        TranslationTransform(Vector3D translation) {
            this.translation = translation;
        }

        @Override // org.apache.commons.math3.geometry.partitioning.Transform
        public Point<Euclidean3D> apply(Point<Euclidean3D> point) {
            return new Vector3D(1.0d, (Vector3D) point, 1.0d, this.translation);
        }

        @Override // org.apache.commons.math3.geometry.partitioning.Transform
        public Hyperplane<Euclidean3D> apply(Hyperplane<Euclidean3D> hyperplane) {
            return ((Plane) hyperplane).translate(this.translation);
        }

        /* JADX WARN: Type inference failed for: r4v2, types: [org.apache.commons.math3.geometry.euclidean.twod.Vector2D] */
        @Override // org.apache.commons.math3.geometry.partitioning.Transform
        public SubHyperplane<Euclidean2D> apply(SubHyperplane<Euclidean2D> sub, Hyperplane<Euclidean3D> original, Hyperplane<Euclidean3D> transformed) {
            if (original != this.cachedOriginal) {
                Plane oPlane = (Plane) original;
                Plane tPlane = (Plane) transformed;
                ?? subSpace = tPlane.toSubSpace(apply((Point<Euclidean3D>) oPlane.getOrigin()));
                this.cachedOriginal = (Plane) original;
                this.cachedTransform = org.apache.commons.math3.geometry.euclidean.twod.Line.getTransform(1.0d, 0.0d, 0.0d, 1.0d, subSpace.getX(), subSpace.getY());
            }
            return ((org.apache.commons.math3.geometry.euclidean.twod.SubLine) sub).applyTransform(this.cachedTransform);
        }
    }
}
