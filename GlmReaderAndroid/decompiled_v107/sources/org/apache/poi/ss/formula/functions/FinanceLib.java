package org.apache.poi.ss.formula.functions;

/* loaded from: classes10.dex */
public final class FinanceLib {
    private FinanceLib() {
    }

    public static double fv(double r, double n, double y, double p, boolean t) {
        if (r == 0.0d) {
            return ((n * y) + p) * (-1.0d);
        }
        double r1 = r + 1.0d;
        return ((((1.0d - Math.pow(r1, n)) * (t ? r1 : 1.0d)) * y) / r) - (Math.pow(r1, n) * p);
    }

    public static double pv(double r, double n, double y, double f, boolean t) {
        if (r == 0.0d) {
            return ((n * y) + f) * (-1.0d);
        }
        double r1 = r + 1.0d;
        return (((((1.0d - Math.pow(r1, n)) / r) * (t ? r1 : 1.0d)) * y) - f) / Math.pow(r1, n);
    }

    public static double npv(double r, double[] cfs) {
        double npv = 0.0d;
        double r1 = 1.0d + r;
        double trate = r1;
        for (double cf : cfs) {
            npv += cf / trate;
            trate *= r1;
        }
        return npv;
    }

    public static double pmt(double r, double n, double p, double f, boolean t) {
        if (r == 0.0d) {
            return ((f + p) * (-1.0d)) / n;
        }
        double r1 = r + 1.0d;
        return ((f + (Math.pow(r1, n) * p)) * r) / ((t ? r1 : 1.0d) * (1.0d - Math.pow(r1, n)));
    }

    public static double nper(double r, double y, double p, double f, boolean t) {
        double a1;
        double a2;
        if (r == 0.0d) {
            return ((f + p) * (-1.0d)) / y;
        }
        double r1 = r + 1.0d;
        double ryr = ((t ? r1 : 1.0d) * y) / r;
        if (ryr - f < 0.0d) {
            a1 = Math.log(f - ryr);
        } else {
            a1 = Math.log(ryr - f);
        }
        if (ryr - f < 0.0d) {
            a2 = Math.log((-p) - ryr);
        } else {
            a2 = Math.log(p + ryr);
        }
        double a3 = Math.log(r1);
        return (a1 - a2) / a3;
    }
}
