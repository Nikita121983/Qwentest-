package org.apache.poi.ss.formula.functions;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.util.Internal;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public final class MathX {
    private MathX() {
    }

    public static double round(double n, int p) {
        return round(n, p, RoundingMode.HALF_UP);
    }

    public static double round(double n, double p) {
        return round(n, (int) p);
    }

    public static double roundUp(double n, int p) {
        return round(n, p, RoundingMode.UP);
    }

    public static double roundUp(double n, double p) {
        return roundUp(n, (int) p);
    }

    public static double roundDown(double n, int p) {
        return round(n, p, RoundingMode.DOWN);
    }

    public static double roundDown(double n, double p) {
        return roundDown(n, (int) p);
    }

    private static double round(double n, int p, RoundingMode rounding) {
        if (Double.isNaN(n) || Double.isInfinite(n)) {
            return Double.NaN;
        }
        String excelNumber = NumberToTextConverter.toText(n);
        return new BigDecimal(excelNumber).setScale(p, rounding).doubleValue();
    }

    public static short sign(double d) {
        return (short) (d == 0.0d ? 0 : d < 0.0d ? -1 : 1);
    }

    public static double average(double[] values) {
        double sum = 0.0d;
        for (double value : values) {
            sum += value;
        }
        double ave = sum / values.length;
        return ave;
    }

    public static double sum(double[] values) {
        double sum = 0.0d;
        for (double value : values) {
            sum += value;
        }
        return sum;
    }

    public static double sumsq(double[] values) {
        double sumsq = 0.0d;
        for (double value : values) {
            sumsq += value * value;
        }
        return sumsq;
    }

    public static double product(double[] values) {
        double product = 0.0d;
        if (values != null && values.length > 0) {
            product = 1.0d;
            for (double value : values) {
                product *= value;
            }
        }
        return product;
    }

    public static double min(double[] values) {
        double min = Double.POSITIVE_INFINITY;
        for (double value : values) {
            min = Math.min(min, value);
        }
        return min;
    }

    public static double max(double[] values) {
        double max = Double.NEGATIVE_INFINITY;
        for (double value : values) {
            max = Math.max(max, value);
        }
        return max;
    }

    public static double floor(double n, double s) {
        if (s == 0.0d && n != 0.0d) {
            return Double.NaN;
        }
        if (n == 0.0d || s == 0.0d) {
            return 0.0d;
        }
        if (s == 1.0d) {
            return Math.floor(n);
        }
        if (s >= 0.0d || n < 0.0d) {
            return scaledRoundUsingBigDecimal(n, s, RoundingMode.FLOOR);
        }
        return Double.NaN;
    }

    public static double ceiling(double n, double s) {
        if (n > 0.0d && s < 0.0d) {
            return Double.NaN;
        }
        if (n == 0.0d || s == 0.0d) {
            return 0.0d;
        }
        if (s == 1.0d) {
            return Math.ceil(n);
        }
        return scaledRoundUsingBigDecimal(n, s, RoundingMode.CEILING);
    }

    @Internal
    public static double scaledRoundUsingBigDecimal(double xval, double multiplier, RoundingMode mode) {
        BigDecimal multiplierDecimal = BigDecimal.valueOf(multiplier);
        BigDecimal bd = BigDecimal.valueOf(xval).divide(multiplierDecimal, MathContext.DECIMAL128).setScale(0, mode).multiply(multiplierDecimal);
        return bd.doubleValue();
    }

    public static double factorial(int n) {
        double d = 1.0d;
        if (n >= 0) {
            if (n <= 170) {
                for (int i = 1; i <= n; i++) {
                    d *= i;
                }
                return d;
            }
            return Double.POSITIVE_INFINITY;
        }
        return Double.NaN;
    }

    public static double factorial(double d) {
        return factorial((int) d);
    }

    public static double mod(double n, double d) {
        if (d == 0.0d) {
            return Double.NaN;
        }
        if (sign(n) == sign(d)) {
            return n % d;
        }
        return ((n % d) + d) % d;
    }

    public static double acosh(double d) {
        return Math.log(Math.sqrt(Math.pow(d, 2.0d) - 1.0d) + d);
    }

    public static double asinh(double d) {
        return Math.log(Math.sqrt((d * d) + 1.0d) + d);
    }

    public static double atanh(double d) {
        return Math.log((d + 1.0d) / (1.0d - d)) / 2.0d;
    }

    public static double cosh(double d) {
        double ePowX = Math.pow(2.718281828459045d, d);
        double ePowNegX = Math.pow(2.718281828459045d, -d);
        return (ePowX + ePowNegX) / 2.0d;
    }

    public static double sinh(double d) {
        double ePowX = Math.pow(2.718281828459045d, d);
        double ePowNegX = Math.pow(2.718281828459045d, -d);
        return (ePowX - ePowNegX) / 2.0d;
    }

    public static double tanh(double d) {
        double ePowX = Math.pow(2.718281828459045d, d);
        double ePowNegX = Math.pow(2.718281828459045d, -d);
        return (ePowX - ePowNegX) / (ePowX + ePowNegX);
    }

    public static double nChooseK(int n, int k) {
        double d = 1.0d;
        if (k < 0 || n < k) {
            return Double.NaN;
        }
        int minnk = Math.min(n - k, k);
        int maxnk = Math.max(n - k, k);
        for (int i = maxnk; i < n; i++) {
            d *= i + 1;
        }
        return d / factorial(minnk);
    }
}
