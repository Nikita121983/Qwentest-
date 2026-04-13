package org.apache.commons.math3.util;

import androidx.lifecycle.AtomicReference$$ExternalSyntheticBackportWithForwarding0;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

/* loaded from: classes10.dex */
public final class CombinatoricsUtils {
    static final long[] FACTORIALS = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800, 39916800, 479001600, 6227020800L, 87178291200L, 1307674368000L, 20922789888000L, 355687428096000L, 6402373705728000L, 121645100408832000L, 2432902008176640000L};
    static final AtomicReference<long[][]> STIRLING_S2 = new AtomicReference<>(null);

    private CombinatoricsUtils() {
    }

    public static long binomialCoefficient(int n, int k) throws NotPositiveException, NumberIsTooLargeException, MathArithmeticException {
        checkBinomial(n, k);
        if (n == k || k == 0) {
            return 1L;
        }
        if (k == 1 || k == n - 1) {
            return n;
        }
        if (k > n / 2) {
            return binomialCoefficient(n, n - k);
        }
        long result = 1;
        if (n <= 61) {
            int i = (n - k) + 1;
            for (int j = 1; j <= k; j++) {
                result = (i * result) / j;
                i++;
            }
        } else if (n <= 66) {
            int i2 = (n - k) + 1;
            for (int j2 = 1; j2 <= k; j2++) {
                long d = ArithmeticUtils.gcd(i2, j2);
                result = (result / (j2 / d)) * (i2 / d);
                i2++;
            }
        } else {
            int i3 = (n - k) + 1;
            for (int j3 = 1; j3 <= k; j3++) {
                long d2 = ArithmeticUtils.gcd(i3, j3);
                result = ArithmeticUtils.mulAndCheck(result / (j3 / d2), i3 / d2);
                i3++;
            }
        }
        return result;
    }

    public static double binomialCoefficientDouble(int n, int k) throws NotPositiveException, NumberIsTooLargeException, MathArithmeticException {
        checkBinomial(n, k);
        if (n == k || k == 0) {
            return 1.0d;
        }
        if (k == 1 || k == n - 1) {
            double result = n;
            return result;
        }
        if (k > n / 2) {
            return binomialCoefficientDouble(n, n - k);
        }
        if (n < 67) {
            return binomialCoefficient(n, k);
        }
        double result2 = 1.0d;
        for (int i = 1; i <= k; i++) {
            result2 *= ((n - k) + i) / i;
        }
        return FastMath.floor(0.5d + result2);
    }

    public static double binomialCoefficientLog(int n, int k) throws NotPositiveException, NumberIsTooLargeException, MathArithmeticException {
        checkBinomial(n, k);
        if (n == k || k == 0) {
            return 0.0d;
        }
        if (k == 1 || k == n - 1) {
            return FastMath.log(n);
        }
        if (n < 67) {
            return FastMath.log(binomialCoefficient(n, k));
        }
        if (n < 1030) {
            return FastMath.log(binomialCoefficientDouble(n, k));
        }
        if (k > n / 2) {
            return binomialCoefficientLog(n, n - k);
        }
        double logSum = 0.0d;
        for (int i = (n - k) + 1; i <= n; i++) {
            logSum += FastMath.log(i);
        }
        for (int i2 = 2; i2 <= k; i2++) {
            logSum -= FastMath.log(i2);
        }
        return logSum;
    }

    public static long factorial(int n) throws NotPositiveException, MathArithmeticException {
        if (n < 0) {
            throw new NotPositiveException(LocalizedFormats.FACTORIAL_NEGATIVE_PARAMETER, Integer.valueOf(n));
        }
        if (n > 20) {
            throw new MathArithmeticException();
        }
        return FACTORIALS[n];
    }

    public static double factorialDouble(int n) throws NotPositiveException {
        if (n < 0) {
            throw new NotPositiveException(LocalizedFormats.FACTORIAL_NEGATIVE_PARAMETER, Integer.valueOf(n));
        }
        if (n < 21) {
            return FACTORIALS[n];
        }
        return FastMath.floor(FastMath.exp(factorialLog(n)) + 0.5d);
    }

    public static double factorialLog(int n) throws NotPositiveException {
        if (n < 0) {
            throw new NotPositiveException(LocalizedFormats.FACTORIAL_NEGATIVE_PARAMETER, Integer.valueOf(n));
        }
        if (n < 21) {
            return FastMath.log(FACTORIALS[n]);
        }
        double logSum = 0.0d;
        for (int i = 2; i <= n; i++) {
            logSum += FastMath.log(i);
        }
        return logSum;
    }

    public static long stirlingS2(int n, int k) throws NotPositiveException, NumberIsTooLargeException, MathArithmeticException {
        if (k >= 0) {
            if (k > n) {
                throw new NumberIsTooLargeException(Integer.valueOf(k), Integer.valueOf(n), true);
            }
            long[][] stirlingS2 = STIRLING_S2.get();
            if (stirlingS2 == null) {
                stirlingS2 = new long[26];
                long[] jArr = new long[1];
                jArr[0] = 1;
                stirlingS2[0] = jArr;
                for (int i = 1; i < stirlingS2.length; i++) {
                    stirlingS2[i] = new long[i + 1];
                    stirlingS2[i][0] = 0;
                    stirlingS2[i][1] = 1;
                    stirlingS2[i][i] = 1;
                    for (int j = 2; j < i; j++) {
                        stirlingS2[i][j] = (j * stirlingS2[i - 1][j]) + stirlingS2[i - 1][j - 1];
                    }
                }
                AtomicReference$$ExternalSyntheticBackportWithForwarding0.m(STIRLING_S2, null, stirlingS2);
            }
            int maxIndex = stirlingS2.length;
            if (n < maxIndex) {
                return stirlingS2[n][k];
            }
            if (k == 0) {
                return 0L;
            }
            if (k == 1 || k == n) {
                return 1L;
            }
            if (k == 2) {
                return (1 << (n - 1)) - 1;
            }
            if (k == n - 1) {
                return binomialCoefficient(n, 2);
            }
            long sum = 0;
            long sign = (k & 1) != 0 ? -1L : 1L;
            for (int j2 = 1; j2 <= k; j2++) {
                sign = -sign;
                sum += binomialCoefficient(k, j2) * sign * ArithmeticUtils.pow(j2, n);
                if (sum < 0) {
                    throw new MathArithmeticException(LocalizedFormats.ARGUMENT_OUTSIDE_DOMAIN, Integer.valueOf(n), 0, Integer.valueOf(stirlingS2.length - 1));
                }
            }
            return sum / factorial(k);
        }
        throw new NotPositiveException(Integer.valueOf(k));
    }

    public static Iterator<int[]> combinationsIterator(int n, int k) {
        return new Combinations(n, k).iterator();
    }

    public static void checkBinomial(int n, int k) throws NumberIsTooLargeException, NotPositiveException {
        if (n < k) {
            throw new NumberIsTooLargeException(LocalizedFormats.BINOMIAL_INVALID_PARAMETERS_ORDER, Integer.valueOf(k), Integer.valueOf(n), true);
        }
        if (n < 0) {
            throw new NotPositiveException(LocalizedFormats.BINOMIAL_NEGATIVE_PARAMETER, Integer.valueOf(n));
        }
    }
}
