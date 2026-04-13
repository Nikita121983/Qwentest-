package com.graphbuilder.curve;

import com.graphbuilder.geom.Geom;
import com.graphbuilder.org.apache.harmony.awt.gl.Crossing;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/* loaded from: classes.dex */
public class ShapeMultiPath extends MultiPath implements Shape {
    private int ai0;
    private int ai1;
    private int windingRule;

    public ShapeMultiPath() {
        super(2);
        this.windingRule = 0;
        this.ai0 = 0;
        this.ai1 = 1;
    }

    public ShapeMultiPath(int dimension) {
        super(dimension);
        this.windingRule = 0;
        this.ai0 = 0;
        this.ai1 = 1;
        if (dimension < 2) {
            throw new IllegalArgumentException("dimension >= 2 required");
        }
    }

    public void setBasisVectors(int[] b) {
        int b0 = b[0];
        int b1 = b[1];
        int dimension = getDimension();
        if (b0 < 0 || b1 < 0 || b0 >= dimension || b1 >= dimension) {
            throw new IllegalArgumentException("basis vectors must be >= 0 and < dimension");
        }
        this.ai0 = b0;
        this.ai1 = b1;
    }

    public int[] getBasisVectors() {
        return new int[]{this.ai0, this.ai1};
    }

    public double getDistSq(double x, double y) {
        int i;
        double x1;
        double y1;
        int n = getNumPoints();
        if (n != 0) {
            double[] p = get(0);
            double x2 = p[this.ai0];
            double y2 = p[this.ai1];
            double dist = Double.MAX_VALUE;
            double x22 = x2;
            int i2 = 1;
            double y22 = y2;
            while (i2 < n) {
                double[] p2 = get(i2);
                double x12 = p2[this.ai0];
                double y12 = p2[this.ai1];
                if (getType(i2) != MultiPath.LINE_TO) {
                    i = i2;
                    x1 = x12;
                    y1 = y12;
                } else {
                    i = i2;
                    x1 = x12;
                    y1 = y12;
                    double d = Geom.ptSegDistSq(x1, y1, x22, y22, x, y, null);
                    if (d < dist) {
                        dist = d;
                    }
                }
                x22 = x1;
                y22 = y1;
                i2 = i + 1;
            }
            return dist;
        }
        return Double.MAX_VALUE;
    }

    public int getWindingRule() {
        return this.windingRule;
    }

    public void setWindingRule(int rule) {
        if (rule != 0 && rule != 1) {
            throw new IllegalArgumentException("winding rule must be WIND_EVEN_ODD or WIND_NON_ZERO");
        }
        this.windingRule = rule;
    }

    public PathIterator getPathIterator(AffineTransform at) {
        return new ShapeMultiPathIterator(this, at);
    }

    public PathIterator getPathIterator(AffineTransform at, double flatness) {
        return new ShapeMultiPathIterator(this, at);
    }

    public Rectangle getBounds() {
        Rectangle2D r = getBounds2D();
        if (r == null) {
            return null;
        }
        return r.getBounds();
    }

    public Rectangle2D getBounds2D() {
        int n = getNumPoints();
        double x2 = -1.7976931348623157E308d;
        double y2 = -1.7976931348623157E308d;
        boolean defined = false;
        double x1 = Double.MAX_VALUE;
        double y1 = Double.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            double[] p = get(i);
            boolean b = false;
            if (getType(i) == MultiPath.MOVE_TO) {
                if (i < n - 1 && getType(i + 1) == MultiPath.LINE_TO) {
                    b = true;
                }
            } else {
                b = true;
            }
            if (b) {
                if (p[this.ai0] < x1) {
                    x1 = p[this.ai0];
                }
                if (p[this.ai1] < y1) {
                    y1 = p[this.ai1];
                }
                if (p[this.ai0] > x2) {
                    x2 = p[this.ai0];
                }
                if (p[this.ai1] > y2) {
                    y2 = p[this.ai1];
                }
                defined = true;
            }
        }
        if (!defined) {
            return null;
        }
        return new Rectangle2D.Double(x1, y1, x2 - x1, y2 - y1);
    }

    public boolean contains(double x, double y) {
        int cross = Crossing.crossPath(getPathIterator(null), x, y);
        return this.windingRule == 1 ? cross != 0 : (cross & 1) != 0;
    }

    public boolean contains(Point2D p) {
        return contains(p.getX(), p.getY());
    }

    public boolean contains(double x1, double y1, double w, double h) {
        int n;
        int n2;
        double ya;
        double y2;
        int i;
        double x2;
        double y22;
        boolean z;
        double d = x1;
        double y23 = y1;
        double x22 = d + w;
        double y24 = y23 + h;
        if (!contains(x1, y1) || !contains(d, y24) || !contains(x22, y23) || !contains(x22, y24) || (n = getNumPoints()) == 0) {
            return false;
        }
        double[] p = get(0);
        double xb = p[this.ai0];
        double yb = p[this.ai1];
        int i2 = 1;
        while (i2 < n) {
            double[] p2 = get(i2);
            double xa = p2[this.ai0];
            double ya2 = p2[this.ai1];
            if (getType(i2) != MultiPath.LINE_TO) {
                n2 = n;
                ya = ya2;
                y2 = y24;
                i = i2;
                x2 = x22;
                y22 = xa;
                z = false;
            } else {
                double xb2 = xb;
                double yb2 = yb;
                double xa2 = y24;
                y22 = xa;
                n2 = n;
                i = i2;
                z = false;
                ya = ya2;
                double x23 = x22;
                if (Geom.getSegSegIntersection(y22, ya, xb2, yb2, d, y23, x22, y1, null) == Geom.INTERSECT || Geom.getSegSegIntersection(y22, ya, xb2, yb2, x1, y1, x1, xa2, null) == Geom.INTERSECT || Geom.getSegSegIntersection(y22, ya, xb2, yb2, x1, xa2, x23, xa2, null) == Geom.INTERSECT) {
                    return false;
                }
                x2 = x23;
                y2 = xa2;
                if (Geom.getSegSegIntersection(y22, ya, xb2, yb2, x2, y1, x23, xa2, null) == Geom.INTERSECT) {
                    return false;
                }
            }
            xb = y22;
            yb = ya;
            i2 = i + 1;
            d = x1;
            y24 = y2;
            n = n2;
            x22 = x2;
            y23 = y1;
        }
        return true;
    }

    public boolean contains(Rectangle2D r) {
        return contains(r.getX(), r.getY(), r.getWidth(), r.getHeight());
    }

    public boolean intersects(double x1, double y1, double w, double h) {
        int n;
        double ya;
        double y2;
        int i;
        double x2;
        double y22;
        boolean z;
        double d = x1;
        double y23 = y1;
        double x22 = d + w;
        double y24 = y23 + h;
        if (contains(x1, y1) || contains(d, y24) || contains(x22, y23) || contains(x22, y24)) {
            return true;
        }
        int n2 = getNumPoints();
        boolean z2 = false;
        if (n2 == 0) {
            return false;
        }
        double[] p = get(0);
        double xb = p[this.ai0];
        double yb = p[this.ai1];
        int i2 = 1;
        while (i2 < n2) {
            double[] p2 = get(i2);
            double xa = p2[this.ai0];
            double ya2 = p2[this.ai1];
            if (getType(i2) != MultiPath.LINE_TO) {
                n = n2;
                ya = ya2;
                y2 = y24;
                i = i2;
                x2 = x22;
                y22 = xa;
                z = false;
            } else {
                double xb2 = xb;
                double yb2 = yb;
                double xa2 = y24;
                y22 = xa;
                n = n2;
                i = i2;
                z = false;
                ya = ya2;
                double x23 = x22;
                if (Geom.getSegSegIntersection(y22, ya, xb2, yb2, d, y23, x22, y1, null) == Geom.INTERSECT || Geom.getSegSegIntersection(y22, ya, xb2, yb2, x1, y1, x1, xa2, null) == Geom.INTERSECT || Geom.getSegSegIntersection(y22, ya, xb2, yb2, x1, xa2, x23, xa2, null) == Geom.INTERSECT) {
                    return true;
                }
                x2 = x23;
                y2 = xa2;
                if (Geom.getSegSegIntersection(y22, ya, xb2, yb2, x2, y1, x23, xa2, null) == Geom.INTERSECT) {
                    return true;
                }
                if (y22 >= x1 && ya >= y1 && y22 <= x2 && ya <= y2) {
                    return true;
                }
                if (xb2 >= x1 && yb2 >= y1 && xb2 <= x2 && yb2 <= y2) {
                    return true;
                }
            }
            xb = y22;
            yb = ya;
            i2 = i + 1;
            d = x1;
            y24 = y2;
            n2 = n;
            z2 = z;
            x22 = x2;
            y23 = y1;
        }
        return z2;
    }

    public boolean intersects(Rectangle2D r) {
        return intersects(r.getX(), r.getY(), r.getWidth(), r.getHeight());
    }
}
