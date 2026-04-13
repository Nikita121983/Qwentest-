package org.apache.commons.math3.geometry.euclidean.twod;

import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes10.dex */
public class Segment {
    private final Vector2D end;
    private final Line line;
    private final Vector2D start;

    public Segment(Vector2D start, Vector2D end, Line line) {
        this.start = start;
        this.end = end;
        this.line = line;
    }

    public Vector2D getStart() {
        return this.start;
    }

    public Vector2D getEnd() {
        return this.end;
    }

    public Line getLine() {
        return this.line;
    }

    public double distance(Vector2D p) {
        double deltaX = this.end.getX() - this.start.getX();
        double deltaY = this.end.getY() - this.start.getY();
        double r = (((p.getX() - this.start.getX()) * deltaX) + ((p.getY() - this.start.getY()) * deltaY)) / ((deltaX * deltaX) + (deltaY * deltaY));
        if (r < 0.0d || r > 1.0d) {
            double dist1 = getStart().distance((Point<Euclidean2D>) p);
            double dist2 = getEnd().distance((Point<Euclidean2D>) p);
            return FastMath.min(dist1, dist2);
        }
        double px = this.start.getX() + (r * deltaX);
        double py = this.start.getY() + (r * deltaY);
        Vector2D interPt = new Vector2D(px, py);
        return interPt.distance((Point<Euclidean2D>) p);
    }
}
