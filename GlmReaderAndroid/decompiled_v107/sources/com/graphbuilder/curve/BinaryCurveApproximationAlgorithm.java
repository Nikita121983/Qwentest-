package com.graphbuilder.curve;

import com.graphbuilder.geom.Geom;

/* loaded from: classes.dex */
public final class BinaryCurveApproximationAlgorithm {
    private BinaryCurveApproximationAlgorithm() {
    }

    public static void genPts(ParametricCurve pc, double t_min, double t_max, MultiPath mp) {
        double t2;
        if (t_min > t_max) {
            throw new IllegalArgumentException("t_min <= t_max required.");
        }
        int n = mp.getDimension();
        double t1 = t_min;
        double t22 = t_max;
        double[][] stack = new double[10];
        double[] rdy = new double[n + 1];
        rdy[n] = t1;
        pc.eval(rdy);
        double[] p = new double[n + 1];
        p[n] = t22;
        pc.eval(p);
        int count = 0 + 1;
        stack[0] = p;
        double[][] limit = new double[pc.getSampleLimit()];
        double flatSq = mp.getFlatness() * mp.getFlatness();
        double[] d = new double[n + 1];
        while (true) {
            double m = (t1 + t22) / 2.0d;
            double t12 = t1;
            double[] pt = new double[n + 1];
            pt[n] = m;
            pc.eval(pt);
            double dist = Geom.ptSegDistSq(rdy, stack[count - 1], pt, d, n);
            if (Double.isNaN(dist) || Double.isInfinite(dist)) {
                break;
            }
            boolean flag = false;
            if (dist < flatSq) {
                double mm = 0.0d;
                int i = 0;
                while (true) {
                    t2 = t22;
                    if (i >= limit.length) {
                        break;
                    }
                    mm = (t12 + m) / 2.0d;
                    double[] q = new double[n + 1];
                    limit[i] = q;
                    q[n] = mm;
                    pc.eval(q);
                    if (Geom.ptSegDistSq(rdy, pt, q, d, n) >= flatSq) {
                        break;
                    }
                    m = mm;
                    i++;
                    t22 = t2;
                }
                if (i == limit.length) {
                    flag = true;
                    t22 = t2;
                } else {
                    double[][] stack2 = checkSpace(stack, count);
                    stack2[count] = pt;
                    int j = 0;
                    count++;
                    while (j <= i) {
                        stack2 = checkSpace(stack2, count);
                        stack2[count] = limit[j];
                        j++;
                        count++;
                    }
                    stack = stack2;
                    t22 = mm;
                    flag = false;
                }
            }
            if (flag) {
                mp.lineTo(rdy);
                mp.lineTo(pt);
                count--;
                rdy = stack[count];
                if (count != 0) {
                    double t13 = t22;
                    t22 = stack[count - 1][n];
                    t1 = t13;
                } else {
                    mp.lineTo(rdy);
                    return;
                }
            } else if (t22 <= m) {
                t1 = t12;
            } else {
                stack = checkSpace(stack, count);
                stack[count] = pt;
                t22 = m;
                count++;
                t1 = t12;
            }
        }
        String msg = "NaN or infinity resulted from calling the eval method of the " + pc.getClass().getName() + " class.";
        throw new RuntimeException(msg);
    }

    private static double[][] checkSpace(double[][] stack, int size) {
        if (size == stack.length) {
            double[][] arr = new double[size * 2];
            for (int i = 0; i < size; i++) {
                arr[i] = stack[i];
            }
            return arr;
        }
        return stack;
    }
}
