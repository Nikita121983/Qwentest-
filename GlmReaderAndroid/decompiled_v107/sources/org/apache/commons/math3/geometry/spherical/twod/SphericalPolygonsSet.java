package org.apache.commons.math3.geometry.spherical.twod;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.geometry.enclosing.EnclosingBall;
import org.apache.commons.math3.geometry.enclosing.WelzlEncloser;
import org.apache.commons.math3.geometry.euclidean.threed.Rotation;
import org.apache.commons.math3.geometry.euclidean.threed.RotationConvention;
import org.apache.commons.math3.geometry.euclidean.threed.SphereGenerator;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.geometry.partitioning.AbstractRegion;
import org.apache.commons.math3.geometry.partitioning.BSPTree;
import org.apache.commons.math3.geometry.partitioning.BoundaryProjection;
import org.apache.commons.math3.geometry.partitioning.Region;
import org.apache.commons.math3.geometry.partitioning.RegionFactory;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
import org.apache.commons.math3.geometry.spherical.oned.Sphere1D;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes10.dex */
public class SphericalPolygonsSet extends AbstractRegion<Sphere2D, Sphere1D> {
    private List<Vertex> loops;

    @Override // org.apache.commons.math3.geometry.partitioning.AbstractRegion, org.apache.commons.math3.geometry.partitioning.Region
    public /* bridge */ /* synthetic */ AbstractRegion buildNew(BSPTree bSPTree) {
        return buildNew((BSPTree<Sphere2D>) bSPTree);
    }

    @Override // org.apache.commons.math3.geometry.partitioning.AbstractRegion, org.apache.commons.math3.geometry.partitioning.Region
    public /* bridge */ /* synthetic */ Region buildNew(BSPTree bSPTree) {
        return buildNew((BSPTree<Sphere2D>) bSPTree);
    }

    public SphericalPolygonsSet(double tolerance) {
        super(tolerance);
    }

    public SphericalPolygonsSet(Vector3D pole, double tolerance) {
        super(new BSPTree(new Circle(pole, tolerance).wholeHyperplane(), new BSPTree(Boolean.FALSE), new BSPTree(Boolean.TRUE), null), tolerance);
    }

    public SphericalPolygonsSet(Vector3D center, Vector3D meridian, double outsideRadius, int n, double tolerance) {
        this(tolerance, createRegularPolygonVertices(center, meridian, outsideRadius, n));
    }

    public SphericalPolygonsSet(BSPTree<Sphere2D> tree, double tolerance) {
        super(tree, tolerance);
    }

    public SphericalPolygonsSet(Collection<SubHyperplane<Sphere2D>> boundary, double tolerance) {
        super(boundary, tolerance);
    }

    public SphericalPolygonsSet(double hyperplaneThickness, S2Point... vertices) {
        super(verticesToTree(hyperplaneThickness, vertices), hyperplaneThickness);
    }

    private static S2Point[] createRegularPolygonVertices(Vector3D center, Vector3D meridian, double outsideRadius, int n) {
        S2Point[] array = new S2Point[n];
        Rotation r0 = new Rotation(Vector3D.crossProduct(center, meridian), outsideRadius, RotationConvention.VECTOR_OPERATOR);
        array[0] = new S2Point(r0.applyTo(center));
        Rotation r = new Rotation(center, 6.283185307179586d / n, RotationConvention.VECTOR_OPERATOR);
        for (int i = 1; i < n; i++) {
            array[i] = new S2Point(r.applyTo(array[i - 1].getVector()));
        }
        return array;
    }

    private static BSPTree<Sphere2D> verticesToTree(double hyperplaneThickness, S2Point... vertices) {
        Circle circle;
        int n = vertices.length;
        if (n == 0) {
            return new BSPTree<>(Boolean.TRUE);
        }
        Vertex[] vArray = new Vertex[n];
        for (int i = 0; i < n; i++) {
            vArray[i] = new Vertex(vertices[i]);
        }
        List<Edge> edges = new ArrayList<>(n);
        Vertex end = vArray[n - 1];
        int i2 = 0;
        while (i2 < n) {
            Vertex start = end;
            Vertex end2 = vArray[i2];
            Circle circle2 = start.sharedCircleWith(end2);
            if (circle2 != null) {
                circle = circle2;
            } else {
                circle = new Circle(start.getLocation(), end2.getLocation(), hyperplaneThickness);
            }
            edges.add(new Edge(start, end2, Vector3D.angle(start.getLocation().getVector(), end2.getLocation().getVector()), circle));
            for (Vertex vertex : vArray) {
                if (vertex != start && vertex != end2 && FastMath.abs(circle.getOffset(vertex.getLocation())) <= hyperplaneThickness) {
                    vertex.bindWith(circle);
                }
            }
            i2++;
            end = end2;
        }
        BSPTree<Sphere2D> tree = new BSPTree<>();
        insertEdges(hyperplaneThickness, tree, edges);
        return tree;
    }

    private static void insertEdges(double hyperplaneThickness, BSPTree<Sphere2D> node, List<Edge> edges) {
        int index = 0;
        Edge inserted = null;
        while (inserted == null && index < edges.size()) {
            int index2 = index + 1;
            Edge inserted2 = edges.get(index);
            inserted = inserted2;
            if (node.insertCut(inserted.getCircle())) {
                index = index2;
            } else {
                inserted = null;
                index = index2;
            }
        }
        if (inserted == null) {
            BSPTree<Sphere2D> parent = node.getParent();
            if (parent == null || node == parent.getMinus()) {
                node.setAttribute(Boolean.TRUE);
                return;
            } else {
                node.setAttribute(Boolean.FALSE);
                return;
            }
        }
        List<Edge> outsideList = new ArrayList<>();
        List<Edge> insideList = new ArrayList<>();
        for (Edge edge : edges) {
            if (edge != inserted) {
                edge.split(inserted.getCircle(), outsideList, insideList);
            }
        }
        if (!outsideList.isEmpty()) {
            insertEdges(hyperplaneThickness, node.getPlus(), outsideList);
        } else {
            node.getPlus().setAttribute(Boolean.FALSE);
        }
        if (!insideList.isEmpty()) {
            insertEdges(hyperplaneThickness, node.getMinus(), insideList);
        } else {
            node.getMinus().setAttribute(Boolean.TRUE);
        }
    }

    @Override // org.apache.commons.math3.geometry.partitioning.AbstractRegion, org.apache.commons.math3.geometry.partitioning.Region
    public SphericalPolygonsSet buildNew(BSPTree<Sphere2D> tree) {
        return new SphericalPolygonsSet(tree, getTolerance());
    }

    @Override // org.apache.commons.math3.geometry.partitioning.AbstractRegion
    protected void computeGeometricalProperties() throws MathIllegalStateException {
        BSPTree<Sphere2D> tree = getTree(true);
        if (tree.getCut() == null) {
            if (tree.getCut() == null && ((Boolean) tree.getAttribute()).booleanValue()) {
                setSize(12.566370614359172d);
                setBarycenter(new S2Point(0.0d, 0.0d));
                return;
            } else {
                setSize(0.0d);
                setBarycenter(S2Point.NaN);
                return;
            }
        }
        PropertiesComputer pc = new PropertiesComputer(getTolerance());
        tree.visit(pc);
        setSize(pc.getArea());
        setBarycenter(pc.getBarycenter());
    }

    public List<Vertex> getBoundaryLoops() throws MathIllegalStateException {
        if (this.loops == null) {
            if (getTree(false).getCut() == null) {
                this.loops = Collections.emptyList();
            } else {
                BSPTree<Sphere2D> root = getTree(true);
                EdgesBuilder visitor = new EdgesBuilder(root, getTolerance());
                root.visit(visitor);
                List<Edge> edges = visitor.getEdges();
                this.loops = new ArrayList();
                while (!edges.isEmpty()) {
                    Edge edge = edges.get(0);
                    Vertex startVertex = edge.getStart();
                    this.loops.add(startVertex);
                    do {
                        Iterator<Edge> iterator = edges.iterator();
                        while (true) {
                            if (!iterator.hasNext()) {
                                break;
                            }
                            if (iterator.next() == edge) {
                                iterator.remove();
                                break;
                            }
                        }
                        edge = edge.getEnd().getOutgoing();
                    } while (edge.getStart() != startVertex);
                }
            }
        }
        return Collections.unmodifiableList(this.loops);
    }

    public EnclosingBall<Sphere2D, S2Point> getEnclosingCap() {
        Iterator<Vector3D> it;
        boolean z;
        SphericalPolygonsSet sphericalPolygonsSet = this;
        boolean z2 = false;
        if (sphericalPolygonsSet.isEmpty()) {
            return new EnclosingBall<>(S2Point.PLUS_K, Double.NEGATIVE_INFINITY, new S2Point[0]);
        }
        if (sphericalPolygonsSet.isFull()) {
            return new EnclosingBall<>(S2Point.PLUS_K, Double.POSITIVE_INFINITY, new S2Point[0]);
        }
        BSPTree<Sphere2D> tree = sphericalPolygonsSet.getTree(false);
        if (sphericalPolygonsSet.isEmpty(tree.getMinus()) && sphericalPolygonsSet.isFull(tree.getPlus())) {
            return new EnclosingBall<>(new S2Point(((Circle) tree.getCut().getHyperplane()).getPole()).negate(), 1.5707963267948966d, new S2Point[0]);
        }
        if (sphericalPolygonsSet.isFull(tree.getMinus()) && sphericalPolygonsSet.isEmpty(tree.getPlus())) {
            return new EnclosingBall<>(new S2Point(((Circle) tree.getCut().getHyperplane()).getPole()), 1.5707963267948966d, new S2Point[0]);
        }
        List<Vector3D> insidePoints = sphericalPolygonsSet.getInsidePoints();
        for (Vertex vertex : sphericalPolygonsSet.getBoundaryLoops()) {
            int i = 0;
            Vertex vertex2 = vertex;
            while (true) {
                if (i == 0 || vertex2 != vertex) {
                    i++;
                    insidePoints.add(vertex2.getLocation().getVector());
                    vertex2 = vertex2.getOutgoing().getEnd();
                }
            }
        }
        EnclosingBall enclose = new WelzlEncloser(sphericalPolygonsSet.getTolerance(), new SphereGenerator()).enclose(insidePoints);
        Vector3D[] vector3DArr = (Vector3D[]) enclose.getSupport();
        double radius = enclose.getRadius();
        double norm = ((Vector3D) enclose.getCenter()).getNorm();
        if (norm < sphericalPolygonsSet.getTolerance()) {
            EnclosingBall<Sphere2D, S2Point> enclosingBall = new EnclosingBall<>(S2Point.PLUS_K, Double.POSITIVE_INFINITY, new S2Point[0]);
            Iterator<Vector3D> it2 = sphericalPolygonsSet.getOutsidePoints().iterator();
            while (it2.hasNext()) {
                S2Point s2Point = new S2Point(it2.next());
                BoundaryProjection<Sphere2D> projectToBoundary = sphericalPolygonsSet.projectToBoundary(s2Point);
                if (3.141592653589793d - projectToBoundary.getOffset() >= enclosingBall.getRadius()) {
                    it = it2;
                    z = z2;
                } else {
                    z = z2;
                    S2Point negate = s2Point.negate();
                    double offset = 3.141592653589793d - projectToBoundary.getOffset();
                    it = it2;
                    S2Point[] s2PointArr = new S2Point[1];
                    s2PointArr[z ? 1 : 0] = (S2Point) projectToBoundary.getProjected();
                    enclosingBall = new EnclosingBall<>(negate, offset, s2PointArr);
                }
                sphericalPolygonsSet = this;
                z2 = z;
                it2 = it;
            }
            return enclosingBall;
        }
        S2Point[] s2PointArr2 = new S2Point[vector3DArr.length];
        for (int i2 = 0; i2 < vector3DArr.length; i2++) {
            s2PointArr2[i2] = new S2Point(vector3DArr[i2]);
        }
        return new EnclosingBall<>(new S2Point((Vector3D) enclose.getCenter()), FastMath.acos((((norm * norm) + 1.0d) - (radius * radius)) / (2.0d * norm)), s2PointArr2);
    }

    private List<Vector3D> getInsidePoints() {
        PropertiesComputer pc = new PropertiesComputer(getTolerance());
        getTree(true).visit(pc);
        return pc.getConvexCellsInsidePoints();
    }

    private List<Vector3D> getOutsidePoints() {
        SphericalPolygonsSet complement = (SphericalPolygonsSet) new RegionFactory().getComplement(this);
        PropertiesComputer pc = new PropertiesComputer(getTolerance());
        complement.getTree(true).visit(pc);
        return pc.getConvexCellsInsidePoints();
    }
}
