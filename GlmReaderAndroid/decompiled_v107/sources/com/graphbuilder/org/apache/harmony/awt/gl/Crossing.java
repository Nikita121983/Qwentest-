package com.graphbuilder.org.apache.harmony.awt.gl;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;

/* loaded from: classes.dex */
public class Crossing {
    public static final int CROSSING = 255;
    static final double DELTA = 1.0E-5d;
    static final double ROOT_DELTA = 1.0E-10d;
    static final int UNKNOWN = 254;

    public static int solveQuad(double[] eqn, double[] res) {
        int rc;
        double a = eqn[2];
        double b = eqn[1];
        double c = eqn[0];
        if (a != 0.0d) {
            double d = (b * b) - ((4.0d * a) * c);
            if (d < 0.0d) {
                return 0;
            }
            double d2 = Math.sqrt(d);
            rc = 0 + 1;
            res[0] = ((-b) + d2) / (a * 2.0d);
            if (d2 != 0.0d) {
                res[rc] = ((-b) - d2) / (2.0d * a);
                rc++;
            }
        } else {
            if (b == 0.0d) {
                return -1;
            }
            rc = 0 + 1;
            res[0] = (-c) / b;
        }
        return fixRoots(res, rc);
    }

    public static int solveCubic(double[] eqn, double[] res) {
        int rc;
        double d = eqn[3];
        if (d == 0.0d) {
            return solveQuad(eqn, res);
        }
        double a = eqn[2] / d;
        double b = eqn[1] / d;
        double c = eqn[0] / d;
        double Q = ((a * a) - (b * 3.0d)) / 9.0d;
        double R = (((((a * 2.0d) * a) * a) - ((9.0d * a) * b)) + (27.0d * c)) / 54.0d;
        double Q3 = Q * Q * Q;
        double R2 = R * R;
        double n = (-a) / 3.0d;
        if (R2 < Q3) {
            double t = Math.acos(R / Math.sqrt(Q3)) / 3.0d;
            double m = Math.sqrt(Q) * (-2.0d);
            int rc2 = 0 + 1;
            res[0] = (Math.cos(t) * m) + n;
            int rc3 = rc2 + 1;
            res[rc2] = (Math.cos(t + 2.0943951023931953d) * m) + n;
            res[rc3] = (Math.cos(t - 2.0943951023931953d) * m) + n;
            rc = rc3 + 1;
        } else {
            double A = Math.pow(Math.abs(R) + Math.sqrt(R2 - Q3), 0.3333333333333333d);
            if (R > 0.0d) {
                A = -A;
            }
            if (-1.0E-10d < A && A < 1.0E-10d) {
                res[0] = n;
                rc = 0 + 1;
            } else {
                double B = Q / A;
                int rc4 = 0 + 1;
                res[0] = A + B + n;
                double delta = R2 - Q3;
                if (-1.0E-10d < delta && delta < 1.0E-10d) {
                    rc = rc4 + 1;
                    res[rc4] = ((-(A + B)) / 2.0d) + n;
                } else {
                    rc = rc4;
                }
            }
        }
        return fixRoots(res, rc);
    }

    static int fixRoots(double[] res, int rc) {
        int tc = 0;
        for (int i = 0; i < rc; i++) {
            int j = i + 1;
            while (true) {
                if (j < rc) {
                    if (isZero(res[i] - res[j])) {
                        break;
                    }
                    j++;
                } else {
                    int j2 = tc + 1;
                    res[tc] = res[i];
                    tc = j2;
                    break;
                }
            }
        }
        return tc;
    }

    /* loaded from: classes.dex */
    public static class QuadCurve {
        double Ax;
        double Ay;
        double Bx;
        double By;
        double ax;
        double ay;
        double bx;
        double by;

        public QuadCurve(double x1, double y1, double cx, double cy, double x2, double y2) {
            this.ax = x2 - x1;
            this.ay = y2 - y1;
            this.bx = cx - x1;
            this.by = cy - y1;
            this.Bx = this.bx + this.bx;
            this.Ax = this.ax - this.Bx;
            this.By = this.by + this.by;
            this.Ay = this.ay - this.By;
        }

        int cross(double[] res, int rc, double py1, double py2) {
            int cross = 0;
            for (int i = 0; i < rc; i++) {
                double t = res[i];
                if (t >= -1.0E-5d && t <= 1.00001d) {
                    if (t < 1.0E-5d) {
                        if (py1 < 0.0d) {
                            if ((this.bx != 0.0d ? this.bx : this.ax - this.bx) < 0.0d) {
                                cross--;
                            }
                        }
                    } else if (t <= 0.99999d) {
                        double ry = ((this.Ay * t) + this.By) * t;
                        if (ry > py2) {
                            double rxt = (this.Ax * t) + this.bx;
                            if (rxt <= -1.0E-5d || rxt >= 1.0E-5d) {
                                cross += rxt > 0.0d ? 1 : -1;
                            }
                        }
                    } else if (py1 < this.ay) {
                        if ((this.ax != this.bx ? this.ax - this.bx : this.bx) > 0.0d) {
                            cross++;
                        }
                    }
                }
            }
            return cross;
        }

        int solvePoint(double[] res, double px) {
            double[] eqn = {-px, this.Bx, this.Ax};
            return Crossing.solveQuad(eqn, res);
        }

        int solveExtrem(double[] res) {
            int rc = 0;
            if (this.Ax != 0.0d) {
                int rc2 = 0 + 1;
                res[0] = (-this.Bx) / (this.Ax + this.Ax);
                rc = rc2;
            }
            if (this.Ay != 0.0d) {
                int rc3 = rc + 1;
                res[rc] = (-this.By) / (this.Ay + this.Ay);
                return rc3;
            }
            return rc;
        }

        int addBound(double[] bound, int bc, double[] res, int rc, double minX, double maxX, boolean changeId, int id) {
            int id2 = id;
            for (int i = 0; i < rc; i++) {
                double t = res[i];
                if (t > -1.0E-5d && t < 1.00001d) {
                    double rx = ((this.Ax * t) + this.Bx) * t;
                    if (minX <= rx && rx <= maxX) {
                        int bc2 = bc + 1;
                        bound[bc] = t;
                        int bc3 = bc2 + 1;
                        bound[bc2] = rx;
                        int bc4 = bc3 + 1;
                        bound[bc3] = ((this.Ay * t) + this.By) * t;
                        bc = bc4 + 1;
                        bound[bc4] = id2;
                        if (changeId) {
                            id2++;
                        }
                    }
                }
            }
            return bc;
        }
    }

    /* loaded from: classes.dex */
    public static class CubicCurve {
        double Ax;
        double Ax3;
        double Ay;
        double Bx;
        double Bx2;
        double By;
        double Cx;
        double Cy;
        double ax;
        double ay;
        double bx;
        double by;
        double cx;
        double cy;

        public CubicCurve(double x1, double y1, double cx1, double cy1, double cx2, double cy2, double x2, double y2) {
            this.ax = x2 - x1;
            this.ay = y2 - y1;
            this.bx = cx1 - x1;
            this.by = cy1 - y1;
            this.cx = cx2 - x1;
            this.cy = cy2 - y1;
            this.Cx = this.bx + this.bx + this.bx;
            this.Bx = (((this.cx + this.cx) + this.cx) - this.Cx) - this.Cx;
            this.Ax = (this.ax - this.Bx) - this.Cx;
            this.Cy = this.by + this.by + this.by;
            this.By = (((this.cy + this.cy) + this.cy) - this.Cy) - this.Cy;
            this.Ay = (this.ay - this.By) - this.Cy;
            this.Ax3 = this.Ax + this.Ax + this.Ax;
            this.Bx2 = this.Bx + this.Bx;
        }

        /* JADX WARN: Removed duplicated region for block: B:37:0x0080  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x00c8 A[SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        int cross(double[] r22, int r23, double r24, double r26) {
            /*
                Method dump skipped, instructions count: 205
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.graphbuilder.org.apache.harmony.awt.gl.Crossing.CubicCurve.cross(double[], int, double, double):int");
        }

        int solvePoint(double[] res, double px) {
            double[] eqn = {-px, this.Cx, this.Bx, this.Ax};
            return Crossing.solveCubic(eqn, res);
        }

        int solveExtremX(double[] res) {
            double[] eqn = {this.Cx, this.Bx2, this.Ax3};
            return Crossing.solveQuad(eqn, res);
        }

        int solveExtremY(double[] res) {
            double[] eqn = {this.Cy, this.By + this.By, this.Ay + this.Ay + this.Ay};
            return Crossing.solveQuad(eqn, res);
        }

        int addBound(double[] bound, int bc, double[] res, int rc, double minX, double maxX, boolean changeId, int id) {
            int id2 = id;
            for (int i = 0; i < rc; i++) {
                double t = res[i];
                if (t > -1.0E-5d && t < 1.00001d) {
                    double rx = ((((this.Ax * t) + this.Bx) * t) + this.Cx) * t;
                    if (minX <= rx && rx <= maxX) {
                        int bc2 = bc + 1;
                        bound[bc] = t;
                        int bc3 = bc2 + 1;
                        bound[bc2] = rx;
                        int bc4 = bc3 + 1;
                        bound[bc3] = ((((this.Ay * t) + this.By) * t) + this.Cy) * t;
                        bc = bc4 + 1;
                        bound[bc4] = id2;
                        if (changeId) {
                            id2++;
                        }
                    }
                }
            }
            return bc;
        }
    }

    public static int crossLine(double x1, double y1, double x2, double y2, double x, double y) {
        if ((x < x1 && x < x2) || ((x > x1 && x > x2) || ((y > y1 && y > y2) || x1 == x2))) {
            return 0;
        }
        if ((y >= y1 || y >= y2) && ((y2 - y1) * (x - x1)) / (x2 - x1) <= y - y1) {
            return 0;
        }
        return x == x1 ? x1 < x2 ? 0 : -1 : x == x2 ? x1 < x2 ? 1 : 0 : x1 < x2 ? 1 : -1;
    }

    public static int crossQuad(double x1, double y1, double cx, double cy, double x2, double y2, double x, double y) {
        if ((x < x1 && x < cx && x < x2) || ((x > x1 && x > cx && x > x2) || ((y > y1 && y > cy && y > y2) || (x1 == cx && cx == x2)))) {
            return 0;
        }
        if (y < y1 && y < cy && y < y2 && x != x1 && x != x2) {
            return x1 < x2 ? (x1 >= x || x >= x2) ? 0 : 1 : (x2 >= x || x >= x1) ? 0 : -1;
        }
        QuadCurve c = new QuadCurve(x1, y1, cx, cy, x2, y2);
        double px = x - x1;
        double py = y - y1;
        double[] res = new double[3];
        int rc = c.solvePoint(res, px);
        return c.cross(res, rc, py, py);
    }

    public static int crossCubic(double x1, double y1, double cx1, double cy1, double cx2, double cy2, double x2, double y2, double x, double y) {
        if ((x < x1 && x < cx1 && x < cx2 && x < x2) || ((x > x1 && x > cx1 && x > cx2 && x > x2) || ((y > y1 && y > cy1 && y > cy2 && y > y2) || (x1 == cx1 && cx1 == cx2 && cx2 == x2)))) {
            return 0;
        }
        if (y < y1 && y < cy1 && y < cy2 && y < y2 && x != x1 && x != x2) {
            return x1 < x2 ? (x1 >= x || x >= x2) ? 0 : 1 : (x2 >= x || x >= x1) ? 0 : -1;
        }
        CubicCurve c = new CubicCurve(x1, y1, cx1, cy1, cx2, cy2, x2, y2);
        double px = x - x1;
        double py = y - y1;
        double[] res = new double[3];
        int rc = c.solvePoint(res, px);
        return c.cross(res, rc, py, py);
    }

    public static int crossPath(PathIterator p, double x, double y) {
        double my;
        double cy;
        double cy2;
        double mx;
        double cy3;
        double cx;
        int cross = 0;
        double my2 = 0.0d;
        double[] coords = new double[6];
        double mx2 = 0.0d;
        double cy4 = 0.0d;
        double my3 = 0.0d;
        while (true) {
            if (p.isDone()) {
                double cx2 = my3;
                double cx3 = my2;
                my = cx3;
                cy = cy4;
                cy2 = cx2;
                mx = mx2;
            } else {
                switch (p.currentSegment(coords)) {
                    case 0:
                        if (my3 != mx2 || cy4 != my2) {
                            double cx4 = my3;
                            double cx5 = my2;
                            cross += crossLine(cx4, cy4, mx2, cx5, x, y);
                        }
                        double mx3 = coords[0];
                        double my4 = coords[1];
                        mx2 = mx3;
                        my3 = mx3;
                        cy4 = my4;
                        my2 = my4;
                        break;
                    case 1:
                        double cx6 = coords[0];
                        double cy5 = coords[1];
                        cross += crossLine(my3, cy4, cx6, cy5, x, y);
                        my3 = cx6;
                        cy4 = cy5;
                        break;
                    case 2:
                        double d = coords[0];
                        double d2 = coords[1];
                        double cx7 = coords[2];
                        double cy6 = coords[3];
                        cross += crossQuad(my3, cy4, d, d2, cx7, cy6, x, y);
                        cy4 = cy6;
                        my3 = cx7;
                        break;
                    case 3:
                        double d3 = coords[0];
                        double d4 = coords[1];
                        double d5 = coords[2];
                        double d6 = coords[3];
                        double cx8 = coords[4];
                        double cy7 = coords[5];
                        cross += crossCubic(my3, cy4, d3, d4, d5, d6, cx8, cy7, x, y);
                        my3 = cx8;
                        cy4 = cy7;
                        break;
                    case 4:
                        if (cy4 != my2 || my3 != mx2) {
                            double cx9 = mx2;
                            double cy8 = my2;
                            double cx10 = my3;
                            cross += crossLine(cx10, cy4, mx2, my2, x, y);
                            my3 = cx9;
                            cy4 = cy8;
                            break;
                        } else {
                            cy3 = my3;
                            cx = my2;
                            break;
                        }
                    default:
                        cy3 = my3;
                        cx = my2;
                        break;
                }
                my2 = cx;
                my3 = cy3;
                if (x == my3 && y == cy4) {
                    cross = 0;
                    double cy9 = my2;
                    my = my2;
                    cy = cy9;
                    cy2 = my3;
                    mx = mx2;
                } else {
                    p.next();
                }
            }
        }
        if (cy != my) {
            return cross + crossLine(cy2, cy, mx, my, x, y);
        }
        return cross;
    }

    public static int crossShape(Shape s, double x, double y) {
        if (!s.getBounds2D().contains(x, y)) {
            return 0;
        }
        return crossPath(s.getPathIterator((AffineTransform) null), x, y);
    }

    public static boolean isZero(double val) {
        return -1.0E-5d < val && val < 1.0E-5d;
    }

    static void sortBound(double[] bound, int bc) {
        for (int i = 0; i < bc - 4; i += 4) {
            int k = i;
            for (int j = i + 4; j < bc; j += 4) {
                if (bound[k] > bound[j]) {
                    k = j;
                }
            }
            if (k != i) {
                double tmp = bound[i];
                bound[i] = bound[k];
                bound[k] = tmp;
                double tmp2 = bound[i + 1];
                bound[i + 1] = bound[k + 1];
                bound[k + 1] = tmp2;
                double tmp3 = bound[i + 2];
                bound[i + 2] = bound[k + 2];
                bound[k + 2] = tmp3;
                double tmp4 = bound[i + 3];
                bound[i + 3] = bound[k + 3];
                bound[k + 3] = tmp4;
            }
        }
    }

    static int crossBound(double[] bound, int bc, double py1, double py2) {
        if (bc == 0) {
            return 0;
        }
        int up = 0;
        int down = 0;
        for (int i = 2; i < bc; i += 4) {
            if (bound[i] < py1) {
                up++;
            } else {
                if (bound[i] <= py2) {
                    return 255;
                }
                down++;
            }
        }
        if (down == 0) {
            return 0;
        }
        if (up != 0) {
            sortBound(bound, bc);
            boolean sign = bound[2] > py2;
            for (int i2 = 6; i2 < bc; i2 += 4) {
                boolean sign2 = bound[i2] > py2;
                if (sign != sign2 && bound[i2 + 1] != bound[i2 - 3]) {
                    return 255;
                }
                sign = sign2;
            }
            return UNKNOWN;
        }
        return UNKNOWN;
    }

    public static int intersectLine(double x1, double y1, double x2, double y2, double rx1, double ry1, double rx2, double ry2) {
        double bx1;
        double bx2;
        if ((rx2 < x1 && rx2 < x2) || ((rx1 > x1 && rx1 > x2) || (ry1 > y1 && ry1 > y2))) {
            return 0;
        }
        if (ry2 >= y1 || ry2 >= y2) {
            if (x1 == x2) {
                return 255;
            }
            if (x1 < x2) {
                bx1 = x1 < rx1 ? rx1 : x1;
                bx2 = x2 < rx2 ? x2 : rx2;
            } else {
                bx1 = x2 < rx1 ? rx1 : x2;
                bx2 = x1 < rx2 ? x1 : rx2;
            }
            double k = (y2 - y1) / (x2 - x1);
            double by1 = ((bx1 - x1) * k) + y1;
            double by2 = ((bx2 - x1) * k) + y1;
            if (by1 < ry1 && by2 < ry1) {
                return 0;
            }
            if (by1 <= ry2 || by2 <= ry2) {
                return 255;
            }
        }
        if (x1 == x2) {
            return 0;
        }
        return rx1 == x1 ? x1 < x2 ? 0 : -1 : rx1 == x2 ? x1 < x2 ? 1 : 0 : x1 < x2 ? (x1 >= rx1 || rx1 >= x2) ? 0 : 1 : (x2 >= rx1 || rx1 >= x1) ? 0 : -1;
    }

    public static int intersectQuad(double x1, double y1, double cx, double cy, double x2, double y2, double rx1, double ry1, double rx2, double ry2) {
        int bc;
        if ((rx2 < x1 && rx2 < cx && rx2 < x2) || ((rx1 > x1 && rx1 > cx && rx1 > x2) || (ry1 > y1 && ry1 > cy && ry1 > y2))) {
            return 0;
        }
        if (ry2 < y1 && ry2 < cy && ry2 < y2 && rx1 != x1 && rx1 != x2) {
            return x1 < x2 ? (x1 >= rx1 || rx1 >= x2) ? 0 : 1 : (x2 >= rx1 || rx1 >= x1) ? 0 : -1;
        }
        QuadCurve c = new QuadCurve(x1, y1, cx, cy, x2, y2);
        double px1 = rx1 - x1;
        double py1 = ry1 - y1;
        double px2 = rx2 - x1;
        double py2 = ry2 - y1;
        double[] res1 = new double[3];
        double[] res2 = new double[3];
        int rc1 = c.solvePoint(res1, px1);
        int rc2 = c.solvePoint(res2, px2);
        if (rc1 == 0 && rc2 == 0) {
            return 0;
        }
        double minX = px1 - 1.0E-5d;
        double maxX = 1.0E-5d + px2;
        double[] bound = new double[28];
        int bc2 = c.addBound(bound, c.addBound(bound, c.addBound(bound, 0, res1, rc1, minX, maxX, false, 0), res2, rc2, minX, maxX, false, 1), res2, c.solveExtrem(res2), minX, maxX, true, 2);
        if (rx1 < x1 && x1 < rx2) {
            int bc3 = bc2 + 1;
            bound[bc2] = 0.0d;
            int bc4 = bc3 + 1;
            bound[bc3] = 0.0d;
            int bc5 = bc4 + 1;
            bound[bc4] = 0.0d;
            bc2 = bc5 + 1;
            bound[bc5] = 4.0d;
        }
        if (rx1 < x2 && x2 < rx2) {
            int bc6 = bc2 + 1;
            bound[bc2] = 1.0d;
            int bc7 = bc6 + 1;
            bound[bc6] = c.ax;
            int bc8 = bc7 + 1;
            bound[bc7] = c.ay;
            bound[bc8] = 5.0d;
            bc = bc8 + 1;
        } else {
            bc = bc2;
        }
        int cross = crossBound(bound, bc, py1, py2);
        if (cross != UNKNOWN) {
            return cross;
        }
        return c.cross(res1, rc1, py1, py2);
    }

    public static int intersectCubic(double x1, double y1, double cx1, double cy1, double cx2, double cy2, double x2, double y2, double rx1, double ry1, double rx2, double ry2) {
        int bc;
        if ((rx2 < x1 && rx2 < cx1 && rx2 < cx2 && rx2 < x2) || ((rx1 > x1 && rx1 > cx1 && rx1 > cx2 && rx1 > x2) || (ry1 > y1 && ry1 > cy1 && ry1 > cy2 && ry1 > y2))) {
            return 0;
        }
        if (ry2 < y1 && ry2 < cy1 && ry2 < cy2 && ry2 < y2 && rx1 != x1 && rx1 != x2) {
            return x1 < x2 ? (x1 >= rx1 || rx1 >= x2) ? 0 : 1 : (x2 >= rx1 || rx1 >= x1) ? 0 : -1;
        }
        CubicCurve c = new CubicCurve(x1, y1, cx1, cy1, cx2, cy2, x2, y2);
        double px1 = rx1 - x1;
        double py1 = ry1 - y1;
        double px2 = rx2 - x1;
        double py2 = ry2 - y1;
        double[] res1 = new double[3];
        double[] res2 = new double[3];
        int rc1 = c.solvePoint(res1, px1);
        int rc2 = c.solvePoint(res2, px2);
        if (rc1 == 0 && rc2 == 0) {
            return 0;
        }
        double minX = px1 - 1.0E-5d;
        double maxX = 1.0E-5d + px2;
        double[] bound = new double[40];
        int bc2 = c.addBound(bound, c.addBound(bound, c.addBound(bound, c.addBound(bound, 0, res1, rc1, minX, maxX, false, 0), res2, rc2, minX, maxX, false, 1), res2, c.solveExtremX(res2), minX, maxX, true, 2), res2, c.solveExtremY(res2), minX, maxX, true, 4);
        if (rx1 < x1 && x1 < rx2) {
            int bc3 = bc2 + 1;
            bound[bc2] = 0.0d;
            int bc4 = bc3 + 1;
            bound[bc3] = 0.0d;
            int bc5 = bc4 + 1;
            bound[bc4] = 0.0d;
            bc2 = bc5 + 1;
            bound[bc5] = 6.0d;
        }
        if (rx1 < x2 && x2 < rx2) {
            int bc6 = bc2 + 1;
            bound[bc2] = 1.0d;
            int bc7 = bc6 + 1;
            bound[bc6] = c.ax;
            int bc8 = bc7 + 1;
            bound[bc7] = c.ay;
            bound[bc8] = 7.0d;
            bc = bc8 + 1;
        } else {
            bc = bc2;
        }
        int cross = crossBound(bound, bc, py1, py2);
        if (cross != UNKNOWN) {
            return cross;
        }
        return c.cross(res1, rc1, py1, py2);
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x00f9 A[LOOP:0: B:2:0x0016->B:24:0x00f9, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00f8 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int intersectPath(java.awt.geom.PathIterator r42, double r43, double r45, double r47, double r49) {
        /*
            Method dump skipped, instructions count: 284
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.graphbuilder.org.apache.harmony.awt.gl.Crossing.intersectPath(java.awt.geom.PathIterator, double, double, double, double):int");
    }

    public static int intersectShape(Shape s, double x, double y, double w, double h) {
        if (!s.getBounds2D().intersects(x, y, w, h)) {
            return 0;
        }
        return intersectPath(s.getPathIterator((AffineTransform) null), x, y, w, h);
    }

    public static boolean isInsideNonZero(int cross) {
        return cross != 0;
    }

    public static boolean isInsideEvenOdd(int cross) {
        return (cross & 1) != 0;
    }
}
