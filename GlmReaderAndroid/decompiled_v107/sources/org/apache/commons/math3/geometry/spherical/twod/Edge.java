package org.apache.commons.math3.geometry.spherical.twod;

import java.util.List;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.geometry.spherical.oned.Arc;
import org.apache.commons.math3.util.MathUtils;

/* loaded from: classes10.dex */
public class Edge {
    private final Circle circle;
    private Vertex end;
    private final double length;
    private final Vertex start;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Edge(Vertex start, Vertex end, double length, Circle circle) {
        this.start = start;
        this.end = end;
        this.length = length;
        this.circle = circle;
        start.setOutgoing(this);
        end.setIncoming(this);
    }

    public Vertex getStart() {
        return this.start;
    }

    public Vertex getEnd() {
        return this.end;
    }

    public double getLength() {
        return this.length;
    }

    public Circle getCircle() {
        return this.circle;
    }

    public Vector3D getPointAt(double alpha) {
        return this.circle.getPointAt(this.circle.getPhase(this.start.getLocation().getVector()) + alpha);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setNextEdge(Edge next) {
        this.end = next.getStart();
        this.end.setIncoming(this);
        this.end.bindWith(getCircle());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void split(Circle splitCircle, List<Edge> outsideList, List<Edge> insideList) {
        double unwrappedEnd;
        Vertex previousVertex;
        double edgeStart = this.circle.getPhase(this.start.getLocation().getVector());
        Arc arc = this.circle.getInsideArc(splitCircle);
        double arcRelativeStart = MathUtils.normalizeAngle(arc.getInf(), 3.141592653589793d + edgeStart) - edgeStart;
        double arcRelativeEnd = arcRelativeStart + arc.getSize();
        double unwrappedEnd2 = arcRelativeEnd - 6.283185307179586d;
        double tolerance = this.circle.getTolerance();
        Vertex previousVertex2 = this.start;
        if (unwrappedEnd2 >= this.length - tolerance) {
            insideList.add(this);
            return;
        }
        double alreadyManagedLength = 0.0d;
        if (unwrappedEnd2 >= 0.0d) {
            unwrappedEnd = unwrappedEnd2;
            previousVertex = addSubEdge(previousVertex2, new Vertex(new S2Point(this.circle.getPointAt(edgeStart + unwrappedEnd))), unwrappedEnd, insideList, splitCircle);
            alreadyManagedLength = unwrappedEnd;
        } else {
            unwrappedEnd = unwrappedEnd2;
            previousVertex = previousVertex2;
        }
        if (arcRelativeStart >= this.length - tolerance) {
            if (unwrappedEnd >= 0.0d) {
                addSubEdge(previousVertex, this.end, this.length - alreadyManagedLength, outsideList, splitCircle);
                return;
            } else {
                outsideList.add(this);
                return;
            }
        }
        Vertex previousVertex3 = addSubEdge(previousVertex, new Vertex(new S2Point(this.circle.getPointAt(edgeStart + arcRelativeStart))), arcRelativeStart - alreadyManagedLength, outsideList, splitCircle);
        if (arcRelativeEnd >= this.length - tolerance) {
            addSubEdge(previousVertex3, this.end, this.length - arcRelativeStart, insideList, splitCircle);
        } else {
            addSubEdge(addSubEdge(previousVertex3, new Vertex(new S2Point(this.circle.getPointAt(edgeStart + arcRelativeStart))), arcRelativeStart - arcRelativeStart, insideList, splitCircle), this.end, this.length - arcRelativeStart, outsideList, splitCircle);
        }
    }

    private Vertex addSubEdge(Vertex subStart, Vertex subEnd, double subLength, List<Edge> list, Circle splitCircle) {
        if (subLength <= this.circle.getTolerance()) {
            return subStart;
        }
        subEnd.bindWith(splitCircle);
        Edge edge = new Edge(subStart, subEnd, subLength, this.circle);
        list.add(edge);
        return subEnd;
    }
}
