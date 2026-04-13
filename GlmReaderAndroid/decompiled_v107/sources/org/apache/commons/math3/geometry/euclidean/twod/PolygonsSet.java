package org.apache.commons.math3.geometry.euclidean.twod;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D;
import org.apache.commons.math3.geometry.euclidean.oned.Interval;
import org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet;
import org.apache.commons.math3.geometry.euclidean.oned.Vector1D;
import org.apache.commons.math3.geometry.partitioning.AbstractRegion;
import org.apache.commons.math3.geometry.partitioning.AbstractSubHyperplane;
import org.apache.commons.math3.geometry.partitioning.BSPTree;
import org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor;
import org.apache.commons.math3.geometry.partitioning.BoundaryAttribute;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.geometry.partitioning.Region;
import org.apache.commons.math3.geometry.partitioning.Side;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

/* loaded from: classes10.dex */
public class PolygonsSet extends AbstractRegion<Euclidean2D, Euclidean1D> {
    private static final double DEFAULT_TOLERANCE = 1.0E-10d;
    private Vector2D[][] vertices;

    @Override // org.apache.commons.math3.geometry.partitioning.AbstractRegion, org.apache.commons.math3.geometry.partitioning.Region
    public /* bridge */ /* synthetic */ AbstractRegion buildNew(BSPTree bSPTree) {
        return buildNew((BSPTree<Euclidean2D>) bSPTree);
    }

    @Override // org.apache.commons.math3.geometry.partitioning.AbstractRegion, org.apache.commons.math3.geometry.partitioning.Region
    public /* bridge */ /* synthetic */ Region buildNew(BSPTree bSPTree) {
        return buildNew((BSPTree<Euclidean2D>) bSPTree);
    }

    public PolygonsSet(double tolerance) {
        super(tolerance);
    }

    public PolygonsSet(BSPTree<Euclidean2D> tree, double tolerance) {
        super(tree, tolerance);
    }

    public PolygonsSet(Collection<SubHyperplane<Euclidean2D>> boundary, double tolerance) {
        super(boundary, tolerance);
    }

    public PolygonsSet(double xMin, double xMax, double yMin, double yMax, double tolerance) {
        super(boxBoundary(xMin, xMax, yMin, yMax, tolerance), tolerance);
    }

    public PolygonsSet(double hyperplaneThickness, Vector2D... vertices) {
        super(verticesToTree(hyperplaneThickness, vertices), hyperplaneThickness);
    }

    @Deprecated
    public PolygonsSet() {
        this(1.0E-10d);
    }

    @Deprecated
    public PolygonsSet(BSPTree<Euclidean2D> tree) {
        this(tree, 1.0E-10d);
    }

    @Deprecated
    public PolygonsSet(Collection<SubHyperplane<Euclidean2D>> boundary) {
        this(boundary, 1.0E-10d);
    }

    @Deprecated
    public PolygonsSet(double xMin, double xMax, double yMin, double yMax) {
        this(xMin, xMax, yMin, yMax, 1.0E-10d);
    }

    private static Line[] boxBoundary(double xMin, double xMax, double yMin, double yMax, double tolerance) {
        if (xMin < xMax - tolerance && yMin < yMax - tolerance) {
            Vector2D minMin = new Vector2D(xMin, yMin);
            Vector2D minMax = new Vector2D(xMin, yMax);
            Vector2D maxMin = new Vector2D(xMax, yMin);
            Vector2D maxMax = new Vector2D(xMax, yMax);
            return new Line[]{new Line(minMin, maxMin, tolerance), new Line(maxMin, maxMax, tolerance), new Line(maxMax, minMax, tolerance), new Line(minMax, minMin, tolerance)};
        }
        return null;
    }

    private static BSPTree<Euclidean2D> verticesToTree(double hyperplaneThickness, Vector2D... vertices) {
        int n = vertices.length;
        if (n == 0) {
            return new BSPTree<>(Boolean.TRUE);
        }
        Vertex[] vArray = new Vertex[n];
        for (int i = 0; i < n; i++) {
            vArray[i] = new Vertex(vertices[i]);
        }
        List<Edge> edges = new ArrayList<>(n);
        for (int i2 = 0; i2 < n; i2++) {
            Vertex start = vArray[i2];
            Vertex end = vArray[(i2 + 1) % n];
            Line line = start.sharedLineWith(end);
            if (line == null) {
                line = new Line(start.getLocation(), end.getLocation(), hyperplaneThickness);
            }
            edges.add(new Edge(start, end, line));
            for (Vertex vertex : vArray) {
                if (vertex != start && vertex != end && FastMath.abs(line.getOffset((Point<Euclidean2D>) vertex.getLocation())) <= hyperplaneThickness) {
                    vertex.bindWith(line);
                }
            }
        }
        BSPTree<Euclidean2D> tree = new BSPTree<>();
        insertEdges(hyperplaneThickness, tree, edges);
        return tree;
    }

    private static void insertEdges(double hyperplaneThickness, BSPTree<Euclidean2D> node, List<Edge> edges) {
        int index = 0;
        Edge inserted = null;
        while (inserted == null && index < edges.size()) {
            int index2 = index + 1;
            Edge inserted2 = edges.get(index);
            inserted = inserted2;
            if (inserted.getNode() == null) {
                if (node.insertCut(inserted.getLine())) {
                    inserted.setNode(node);
                    index = index2;
                } else {
                    inserted = null;
                    index = index2;
                }
            } else {
                inserted = null;
                index = index2;
            }
        }
        if (inserted == null) {
            BSPTree<Euclidean2D> parent = node.getParent();
            if (parent == null || node == parent.getMinus()) {
                node.setAttribute(Boolean.TRUE);
                return;
            } else {
                node.setAttribute(Boolean.FALSE);
                return;
            }
        }
        List<Edge> plusList = new ArrayList<>();
        List<Edge> minusList = new ArrayList<>();
        for (Edge edge : edges) {
            if (edge != inserted) {
                double startOffset = inserted.getLine().getOffset((Point<Euclidean2D>) edge.getStart().getLocation());
                double endOffset = inserted.getLine().getOffset((Point<Euclidean2D>) edge.getEnd().getLocation());
                Side startSide = FastMath.abs(startOffset) <= hyperplaneThickness ? Side.HYPER : startOffset < 0.0d ? Side.MINUS : Side.PLUS;
                Side endSide = FastMath.abs(endOffset) <= hyperplaneThickness ? Side.HYPER : endOffset < 0.0d ? Side.MINUS : Side.PLUS;
                switch (startSide) {
                    case PLUS:
                        if (endSide == Side.MINUS) {
                            Vertex splitPoint = edge.split(inserted.getLine());
                            minusList.add(splitPoint.getOutgoing());
                            plusList.add(splitPoint.getIncoming());
                            break;
                        } else {
                            plusList.add(edge);
                            break;
                        }
                    case MINUS:
                        if (endSide == Side.PLUS) {
                            Vertex splitPoint2 = edge.split(inserted.getLine());
                            minusList.add(splitPoint2.getIncoming());
                            plusList.add(splitPoint2.getOutgoing());
                            break;
                        } else {
                            minusList.add(edge);
                            break;
                        }
                    default:
                        if (endSide == Side.PLUS) {
                            plusList.add(edge);
                            break;
                        } else if (endSide == Side.MINUS) {
                            minusList.add(edge);
                            break;
                        } else {
                            break;
                        }
                }
            }
        }
        if (!plusList.isEmpty()) {
            insertEdges(hyperplaneThickness, node.getPlus(), plusList);
        } else {
            node.getPlus().setAttribute(Boolean.FALSE);
        }
        if (!minusList.isEmpty()) {
            insertEdges(hyperplaneThickness, node.getMinus(), minusList);
        } else {
            node.getMinus().setAttribute(Boolean.TRUE);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static class Vertex {
        private final Vector2D location;
        private Edge incoming = null;
        private Edge outgoing = null;
        private final List<Line> lines = new ArrayList();

        Vertex(Vector2D location) {
            this.location = location;
        }

        public Vector2D getLocation() {
            return this.location;
        }

        public void bindWith(Line line) {
            this.lines.add(line);
        }

        public Line sharedLineWith(Vertex vertex) {
            for (Line line1 : this.lines) {
                for (Line line2 : vertex.lines) {
                    if (line1 == line2) {
                        return line1;
                    }
                }
            }
            return null;
        }

        public void setIncoming(Edge incoming) {
            this.incoming = incoming;
            bindWith(incoming.getLine());
        }

        public Edge getIncoming() {
            return this.incoming;
        }

        public void setOutgoing(Edge outgoing) {
            this.outgoing = outgoing;
            bindWith(outgoing.getLine());
        }

        public Edge getOutgoing() {
            return this.outgoing;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static class Edge {
        private final Vertex end;
        private final Line line;
        private BSPTree<Euclidean2D> node = null;
        private final Vertex start;

        Edge(Vertex start, Vertex end, Line line) {
            this.start = start;
            this.end = end;
            this.line = line;
            start.setOutgoing(this);
            end.setIncoming(this);
        }

        public Vertex getStart() {
            return this.start;
        }

        public Vertex getEnd() {
            return this.end;
        }

        public Line getLine() {
            return this.line;
        }

        public void setNode(BSPTree<Euclidean2D> node) {
            this.node = node;
        }

        public BSPTree<Euclidean2D> getNode() {
            return this.node;
        }

        public Vertex split(Line splitLine) {
            Vertex splitVertex = new Vertex(this.line.intersection(splitLine));
            splitVertex.bindWith(splitLine);
            Edge startHalf = new Edge(this.start, splitVertex, this.line);
            Edge endHalf = new Edge(splitVertex, this.end, this.line);
            startHalf.node = this.node;
            endHalf.node = this.node;
            return splitVertex;
        }
    }

    @Override // org.apache.commons.math3.geometry.partitioning.AbstractRegion, org.apache.commons.math3.geometry.partitioning.Region
    public PolygonsSet buildNew(BSPTree<Euclidean2D> tree) {
        return new PolygonsSet(tree, getTolerance());
    }

    @Override // org.apache.commons.math3.geometry.partitioning.AbstractRegion
    protected void computeGeometricalProperties() {
        Vector2D[][] v = getVertices();
        double d = 0.0d;
        if (v.length == 0) {
            BSPTree<Euclidean2D> tree = getTree(false);
            if (tree.getCut() == null && ((Boolean) tree.getAttribute()).booleanValue()) {
                setSize(Double.POSITIVE_INFINITY);
                setBarycenter((Point) Vector2D.NaN);
            } else {
                setSize(0.0d);
                setBarycenter((Point) new Vector2D(0.0d, 0.0d));
            }
            return;
        }
        if (v[0][0] == null) {
            setSize(Double.POSITIVE_INFINITY);
            setBarycenter((Point) Vector2D.NaN);
            return;
        }
        double sum = 0.0d;
        double sumX = 0.0d;
        double sumY = 0.0d;
        Vector2D[][] arr$ = v;
        int len$ = arr$.length;
        int i$ = 0;
        while (i$ < len$) {
            Vector2D[] loop = arr$[i$];
            double d2 = d;
            double x1 = loop[loop.length - 1].getX();
            double y1 = loop[loop.length - 1].getY();
            Vector2D[][] v2 = v;
            Vector2D[][] arr$2 = arr$;
            for (Vector2D point : loop) {
                double x0 = x1;
                double y0 = y1;
                x1 = point.getX();
                y1 = point.getY();
                double factor = (x0 * y1) - (y0 * x1);
                sum += factor;
                sumX += (x0 + x1) * factor;
                sumY += (y0 + y1) * factor;
            }
            i$++;
            d = d2;
            arr$ = arr$2;
            v = v2;
        }
        if (sum < d) {
            setSize(Double.POSITIVE_INFINITY);
            setBarycenter((Point) Vector2D.NaN);
        } else {
            setSize(sum / 2.0d);
            setBarycenter((Point) new Vector2D(sumX / (sum * 3.0d), sumY / (3.0d * sum)));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v7, types: [org.apache.commons.math3.geometry.euclidean.oned.Vector1D] */
    /* JADX WARN: Type inference failed for: r1v19, types: [org.apache.commons.math3.geometry.euclidean.oned.Vector1D] */
    public Vector2D[][] getVertices() {
        SegmentsBuilder visitor;
        int i;
        List<ConnectableSegment> segments;
        Object obj;
        int i2;
        SegmentsBuilder visitor2;
        double d;
        if (this.vertices == null) {
            int i3 = 0;
            if (getTree(false).getCut() == null) {
                this.vertices = new Vector2D[0];
            } else {
                SegmentsBuilder visitor3 = new SegmentsBuilder(getTolerance());
                int j = 1;
                getTree(true).visit(visitor3);
                List<ConnectableSegment> segments2 = visitor3.getSegments();
                int pending = segments2.size() - naturalFollowerConnections(segments2);
                if (pending > 0) {
                    pending -= splitEdgeConnections(segments2);
                }
                if (pending > 0) {
                    int closeVerticesConnections = pending - closeVerticesConnections(segments2);
                }
                ArrayList<List<Segment>> loops = new ArrayList<>();
                ConnectableSegment s = getUnprocessed(segments2);
                while (s != null) {
                    List<Segment> loop = followLoop(s);
                    if (loop != null) {
                        if (loop.get(0).getStart() == null) {
                            loops.add(0, loop);
                        } else {
                            loops.add(loop);
                        }
                    }
                    s = getUnprocessed(segments2);
                }
                this.vertices = new Vector2D[loops.size()];
                int i4 = 0;
                Iterator i$ = loops.iterator();
                while (i$.hasNext()) {
                    List<Segment> loop2 = i$.next();
                    if (loop2.size() < 2) {
                        visitor = visitor3;
                        i = j;
                        segments = segments2;
                        obj = null;
                    } else if (loop2.size() == 2 && loop2.get(i3).getStart() == null && loop2.get(j).getEnd() == null) {
                        visitor = visitor3;
                        i = j;
                        segments = segments2;
                        obj = null;
                    } else {
                        if (loop2.get(i3).getStart() == null) {
                            Vector2D[] vector2DArr = new Vector2D[loop2.size() + 2];
                            int j2 = 0;
                            for (Segment segment : loop2) {
                                int i5 = j;
                                List<ConnectableSegment> segments3 = segments2;
                                if (j2 != 0) {
                                    visitor2 = visitor3;
                                    d = 2.0d;
                                } else {
                                    d = 2.0d;
                                    double x = segment.getLine().toSubSpace((Point<Euclidean2D>) segment.getEnd()).getX();
                                    visitor2 = visitor3;
                                    double x2 = x - FastMath.max(1.0d, FastMath.abs(x / 2.0d));
                                    int j3 = j2 + 1;
                                    vector2DArr[j2] = 0;
                                    j2 = j3 + 1;
                                    vector2DArr[j3] = segment.getLine().toSpace((Point<Euclidean1D>) new Vector1D(x2));
                                }
                                if (j2 < vector2DArr.length - 1) {
                                    vector2DArr[j2] = segment.getEnd();
                                    j2++;
                                }
                                int j4 = vector2DArr.length;
                                if (j2 == j4 - 1) {
                                    double x3 = segment.getLine().toSubSpace((Point<Euclidean2D>) segment.getStart()).getX();
                                    vector2DArr[j2] = segment.getLine().toSpace((Point<Euclidean1D>) new Vector1D(x3 + FastMath.max(1.0d, FastMath.abs(x3 / d))));
                                    j2++;
                                }
                                j = i5;
                                segments2 = segments3;
                                visitor3 = visitor2;
                            }
                            visitor = visitor3;
                            i = j;
                            segments = segments2;
                            this.vertices[i4] = vector2DArr;
                            i4++;
                            i2 = 0;
                        } else {
                            visitor = visitor3;
                            i = j;
                            segments = segments2;
                            Vector2D[] array = new Vector2D[loop2.size()];
                            int j5 = 0;
                            Iterator i$2 = loop2.iterator();
                            while (i$2.hasNext()) {
                                array[j5] = i$2.next().getStart();
                                j5++;
                            }
                            this.vertices[i4] = array;
                            i4++;
                            i2 = 0;
                        }
                        i3 = i2;
                        j = i;
                        segments2 = segments;
                        visitor3 = visitor;
                    }
                    i2 = 0;
                    Line line = loop2.get(0).getLine();
                    Vector2D[][] vector2DArr2 = this.vertices;
                    Vector2D[] vector2DArr3 = new Vector2D[3];
                    vector2DArr3[0] = obj;
                    vector2DArr3[i] = line.toSpace((Point<Euclidean1D>) new Vector1D(-3.4028234663852886E38d));
                    vector2DArr3[2] = line.toSpace((Point<Euclidean1D>) new Vector1D(3.4028234663852886E38d));
                    vector2DArr2[i4] = vector2DArr3;
                    i4++;
                    i3 = i2;
                    j = i;
                    segments2 = segments;
                    visitor3 = visitor;
                }
            }
        }
        return (Vector2D[][]) this.vertices.clone();
    }

    private int naturalFollowerConnections(List<ConnectableSegment> segments) {
        int connected = 0;
        for (ConnectableSegment segment : segments) {
            if (segment.getNext() == null) {
                BSPTree<Euclidean2D> node = segment.getNode();
                BSPTree<Euclidean2D> end = segment.getEndNode();
                Iterator i$ = segments.iterator();
                while (true) {
                    if (i$.hasNext()) {
                        ConnectableSegment candidateNext = i$.next();
                        if (candidateNext.getPrevious() == null && candidateNext.getNode() == end && candidateNext.getStartNode() == node) {
                            segment.setNext(candidateNext);
                            candidateNext.setPrevious(segment);
                            connected++;
                            break;
                        }
                    }
                }
            }
        }
        return connected;
    }

    private int splitEdgeConnections(List<ConnectableSegment> segments) {
        int connected = 0;
        for (ConnectableSegment segment : segments) {
            if (segment.getNext() == null) {
                Hyperplane<Euclidean2D> hyperplane = segment.getNode().getCut().getHyperplane();
                BSPTree<Euclidean2D> end = segment.getEndNode();
                Iterator i$ = segments.iterator();
                while (true) {
                    if (i$.hasNext()) {
                        ConnectableSegment candidateNext = i$.next();
                        if (candidateNext.getPrevious() == null && candidateNext.getNode().getCut().getHyperplane() == hyperplane && candidateNext.getStartNode() == end) {
                            segment.setNext(candidateNext);
                            candidateNext.setPrevious(segment);
                            connected++;
                            break;
                        }
                    }
                }
            }
        }
        return connected;
    }

    private int closeVerticesConnections(List<ConnectableSegment> segments) {
        int connected = 0;
        for (ConnectableSegment segment : segments) {
            if (segment.getNext() == null && segment.getEnd() != null) {
                Vector2D end = segment.getEnd();
                ConnectableSegment selectedNext = null;
                double min = Double.POSITIVE_INFINITY;
                for (ConnectableSegment candidateNext : segments) {
                    if (candidateNext.getPrevious() == null && candidateNext.getStart() != null) {
                        double distance = Vector2D.distance(end, candidateNext.getStart());
                        if (distance < min) {
                            selectedNext = candidateNext;
                            min = distance;
                        }
                    }
                }
                if (min <= getTolerance()) {
                    segment.setNext(selectedNext);
                    selectedNext.setPrevious(segment);
                    connected++;
                }
            }
        }
        return connected;
    }

    private ConnectableSegment getUnprocessed(List<ConnectableSegment> segments) {
        for (ConnectableSegment segment : segments) {
            if (!segment.isProcessed()) {
                return segment;
            }
        }
        return null;
    }

    private List<Segment> followLoop(ConnectableSegment defining) {
        List<Segment> loop = new ArrayList<>();
        loop.add(defining);
        defining.setProcessed(true);
        ConnectableSegment next = defining.getNext();
        while (next != defining && next != null) {
            loop.add(next);
            next.setProcessed(true);
            next = next.getNext();
        }
        if (next == null) {
            for (ConnectableSegment previous = defining.getPrevious(); previous != null; previous = previous.getPrevious()) {
                loop.add(0, previous);
                previous.setProcessed(true);
            }
        }
        filterSpuriousVertices(loop);
        if (loop.size() == 2 && loop.get(0).getStart() != null) {
            return null;
        }
        return loop;
    }

    private void filterSpuriousVertices(List<Segment> loop) {
        int i = 0;
        while (i < loop.size()) {
            Segment previous = loop.get(i);
            int j = (i + 1) % loop.size();
            Segment next = loop.get(j);
            if (next != null && Precision.equals(previous.getLine().getAngle(), next.getLine().getAngle(), Precision.EPSILON)) {
                loop.set(j, new Segment(previous.getStart(), next.getEnd(), previous.getLine()));
                loop.remove(i);
                i--;
            }
            i++;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static class ConnectableSegment extends Segment {
        private final BSPTree<Euclidean2D> endNode;
        private ConnectableSegment next;
        private final BSPTree<Euclidean2D> node;
        private ConnectableSegment previous;
        private boolean processed;
        private final BSPTree<Euclidean2D> startNode;

        ConnectableSegment(Vector2D start, Vector2D end, Line line, BSPTree<Euclidean2D> node, BSPTree<Euclidean2D> startNode, BSPTree<Euclidean2D> endNode) {
            super(start, end, line);
            this.node = node;
            this.startNode = startNode;
            this.endNode = endNode;
            this.previous = null;
            this.next = null;
            this.processed = false;
        }

        public BSPTree<Euclidean2D> getNode() {
            return this.node;
        }

        public BSPTree<Euclidean2D> getStartNode() {
            return this.startNode;
        }

        public BSPTree<Euclidean2D> getEndNode() {
            return this.endNode;
        }

        public ConnectableSegment getPrevious() {
            return this.previous;
        }

        public void setPrevious(ConnectableSegment previous) {
            this.previous = previous;
        }

        public ConnectableSegment getNext() {
            return this.next;
        }

        public void setNext(ConnectableSegment next) {
            this.next = next;
        }

        public void setProcessed(boolean processed) {
            this.processed = processed;
        }

        public boolean isProcessed() {
            return this.processed;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static class SegmentsBuilder implements BSPTreeVisitor<Euclidean2D> {
        private final List<ConnectableSegment> segments = new ArrayList();
        private final double tolerance;

        SegmentsBuilder(double tolerance) {
            this.tolerance = tolerance;
        }

        @Override // org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor
        public BSPTreeVisitor.Order visitOrder(BSPTree<Euclidean2D> node) {
            return BSPTreeVisitor.Order.MINUS_SUB_PLUS;
        }

        @Override // org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor
        public void visitInternalNode(BSPTree<Euclidean2D> node) {
            BoundaryAttribute<Euclidean2D> attribute = (BoundaryAttribute) node.getAttribute();
            Iterable<BSPTree<Euclidean2D>> splitters = attribute.getSplitters();
            if (attribute.getPlusOutside() != null) {
                addContribution(attribute.getPlusOutside(), node, splitters, false);
            }
            if (attribute.getPlusInside() != null) {
                addContribution(attribute.getPlusInside(), node, splitters, true);
            }
        }

        @Override // org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor
        public void visitLeafNode(BSPTree<Euclidean2D> node) {
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r4v5, types: [org.apache.commons.math3.geometry.euclidean.twod.Vector2D] */
        /* JADX WARN: Type inference failed for: r5v4, types: [org.apache.commons.math3.geometry.euclidean.twod.Vector2D] */
        private void addContribution(SubHyperplane<Euclidean2D> subHyperplane, BSPTree<Euclidean2D> bSPTree, Iterable<BSPTree<Euclidean2D>> iterable, boolean z) {
            Line line = (Line) subHyperplane.getHyperplane();
            for (Interval interval : ((IntervalsSet) ((AbstractSubHyperplane) subHyperplane).getRemainingRegion()).asList()) {
                Vector2D space = Double.isInfinite(interval.getInf()) ? null : line.toSpace((Point<Euclidean1D>) new Vector1D(interval.getInf()));
                Vector2D space2 = Double.isInfinite(interval.getSup()) ? null : line.toSpace((Point<Euclidean1D>) new Vector1D(interval.getSup()));
                BSPTree<Euclidean2D> selectClosest = selectClosest(space, iterable);
                BSPTree<Euclidean2D> selectClosest2 = selectClosest(space2, iterable);
                if (z) {
                    this.segments.add(new ConnectableSegment(space2, space, line.getReverse(), bSPTree, selectClosest2, selectClosest));
                } else {
                    this.segments.add(new ConnectableSegment(space, space2, line, bSPTree, selectClosest, selectClosest2));
                }
            }
        }

        private BSPTree<Euclidean2D> selectClosest(Vector2D point, Iterable<BSPTree<Euclidean2D>> candidates) {
            BSPTree<Euclidean2D> selected = null;
            double min = Double.POSITIVE_INFINITY;
            for (BSPTree<Euclidean2D> node : candidates) {
                double distance = FastMath.abs(node.getCut().getHyperplane().getOffset(point));
                if (distance < min) {
                    selected = node;
                    min = distance;
                }
            }
            if (min <= this.tolerance) {
                return selected;
            }
            return null;
        }

        public List<ConnectableSegment> getSegments() {
            return this.segments;
        }
    }
}
