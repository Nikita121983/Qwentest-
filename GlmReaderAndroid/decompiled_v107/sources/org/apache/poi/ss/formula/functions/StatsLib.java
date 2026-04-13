package org.apache.poi.ss.formula.functions;

import java.util.Arrays;

/* loaded from: classes10.dex */
final class StatsLib {
    private StatsLib() {
    }

    public static double avedev(double[] v) {
        double s = 0.0d;
        for (double item : v) {
            s += item;
        }
        double m = s / v.length;
        double s2 = 0.0d;
        for (double value : v) {
            s2 += Math.abs(value - m);
        }
        double r = s2 / v.length;
        return r;
    }

    public static double stdev(double[] v) {
        if (v == null || v.length <= 1) {
            return Double.NaN;
        }
        double r = Math.sqrt(devsq(v) / (v.length - 1));
        return r;
    }

    public static double stdevp(double[] v) {
        if (v == null || v.length <= 1) {
            return Double.NaN;
        }
        double r = Math.sqrt(devsq(v) / v.length);
        return r;
    }

    public static double var(double[] v) {
        if (v == null || v.length <= 1) {
            return Double.NaN;
        }
        double r = devsq(v) / (v.length - 1);
        return r;
    }

    public static double varp(double[] v) {
        if (v == null || v.length <= 1) {
            return Double.NaN;
        }
        double r = devsq(v) / v.length;
        return r;
    }

    public static double median(double[] v) {
        if (v == null || v.length < 1) {
            return Double.NaN;
        }
        int n = v.length;
        Arrays.sort(v);
        double r = n % 2 == 0 ? (v[n / 2] + v[(n / 2) - 1]) / 2.0d : v[n / 2];
        return r;
    }

    public static double devsq(double[] v) {
        if (v == null || v.length < 1) {
            return Double.NaN;
        }
        double s = 0.0d;
        int n = v.length;
        for (double item : v) {
            s += item;
        }
        double m = s / n;
        double s2 = 0.0d;
        for (double value : v) {
            s2 += (value - m) * (value - m);
        }
        double r = n == 1 ? 0.0d : s2;
        return r;
    }

    public static double kthLargest(double[] v, int k) {
        int index = k - 1;
        if (v == null || v.length <= index || index < 0) {
            return Double.NaN;
        }
        Arrays.sort(v);
        double r = v[(v.length - index) - 1];
        return r;
    }

    public static double kthSmallest(double[] v, int k) {
        int index = k - 1;
        if (v == null || v.length <= index || index < 0) {
            return Double.NaN;
        }
        Arrays.sort(v);
        double r = v[index];
        return r;
    }
}
