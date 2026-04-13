package org.apache.commons.math3.special;

import org.apache.commons.math3.util.FastMath;

/* loaded from: classes10.dex */
public class Erf {
    private static final double X_CRIT = 0.4769362762044697d;

    private Erf() {
    }

    public static double erf(double x) {
        if (FastMath.abs(x) > 40.0d) {
            return x > 0.0d ? 1.0d : -1.0d;
        }
        double ret = Gamma.regularizedGammaP(0.5d, x * x, 1.0E-15d, 10000);
        return x < 0.0d ? -ret : ret;
    }

    public static double erfc(double x) {
        if (FastMath.abs(x) > 40.0d) {
            return x > 0.0d ? 0.0d : 2.0d;
        }
        double ret = Gamma.regularizedGammaQ(0.5d, x * x, 1.0E-15d, 10000);
        return x < 0.0d ? 2.0d - ret : ret;
    }

    public static double erf(double x1, double x2) {
        double erfc;
        double erfc2;
        if (x1 > x2) {
            return -erf(x2, x1);
        }
        if (x1 < -0.4769362762044697d) {
            if (x2 < 0.0d) {
                erfc = erfc(-x2);
                erfc2 = erfc(-x1);
            }
            erfc = erf(x2);
            erfc2 = erf(x1);
        } else {
            if (x2 > X_CRIT && x1 > 0.0d) {
                erfc = erfc(x1);
                erfc2 = erfc(x2);
            }
            erfc = erf(x2);
            erfc2 = erf(x1);
        }
        return erfc - erfc2;
    }

    public static double erfInv(double x) {
        double p;
        double w = -FastMath.log((1.0d - x) * (1.0d + x));
        if (w < 6.25d) {
            double w2 = w - 3.125d;
            double p2 = ((-3.64441206401782E-21d) * w2) - 1.6850591381820166E-19d;
            p = (((((((((((((((((((((((((((((((((((((((((p2 * w2) + 1.28584807152564E-18d) * w2) + 1.1157877678025181E-17d) * w2) - 1.333171662854621E-16d) * w2) + 2.0972767875968562E-17d) * w2) + 6.637638134358324E-15d) * w2) - 4.054566272975207E-14d) * w2) - 8.151934197605472E-14d) * w2) + 2.6335093153082323E-12d) * w2) - 1.2975133253453532E-11d) * w2) - 5.415412054294628E-11d) * w2) + 1.0512122733215323E-9d) * w2) - 4.112633980346984E-9d) * w2) - 2.9070369957882005E-8d) * w2) + 4.2347877827932404E-7d) * w2) - 1.3654692000834679E-6d) * w2) - 1.3882523362786469E-5d) * w2) + 1.8673420803405714E-4d) * w2) - 7.40702534166267E-4d) * w2) - 0.006033670871430149d) * w2) + 0.24015818242558962d) * w2) + 1.6536545626831027d;
        } else if (w < 16.0d) {
            double w3 = FastMath.sqrt(w) - 3.25d;
            double p3 = (2.2137376921775787E-9d * w3) + 9.075656193888539E-8d;
            p = (((((((((((((((((((((((((((((((((p3 * w3) - 2.7517406297064545E-7d) * w3) + 1.8239629214389228E-8d) * w3) + 1.5027403968909828E-6d) * w3) - 4.013867526981546E-6d) * w3) + 2.9234449089955446E-6d) * w3) + 1.2475304481671779E-5d) * w3) - 4.7318229009055734E-5d) * w3) + 6.828485145957318E-5d) * w3) + 2.4031110387097894E-5d) * w3) - 3.550375203628475E-4d) * w3) + 9.532893797373805E-4d) * w3) - 0.0016882755560235047d) * w3) + 0.002491442096107851d) * w3) - 0.003751208507569241d) * w3) + 0.005370914553590064d) * w3) + 1.0052589676941592d) * w3) + 3.0838856104922208d;
        } else if (!Double.isInfinite(w)) {
            double w4 = FastMath.sqrt(w) - 5.0d;
            double p4 = ((-2.7109920616438573E-11d) * w4) - 2.555641816996525E-10d;
            p = (((((((((((((((((((((((((((((p4 * w4) + 1.5076572693500548E-9d) * w4) - 3.789465440126737E-9d) * w4) + 7.61570120807834E-9d) * w4) - 1.496002662714924E-8d) * w4) + 2.914795345090108E-8d) * w4) - 6.771199775845234E-8d) * w4) + 2.2900482228026655E-7d) * w4) - 9.9298272942317E-7d) * w4) + 4.526062597223154E-6d) * w4) - 1.968177810553167E-5d) * w4) + 7.599527703001776E-5d) * w4) - 2.1503011930044477E-4d) * w4) - 1.3871931833623122E-4d) * w4) + 1.0103004648645344d) * w4) + 4.849906401408584d;
        } else {
            p = Double.POSITIVE_INFINITY;
        }
        return p * x;
    }

    public static double erfcInv(double x) {
        return erfInv(1.0d - x);
    }
}
