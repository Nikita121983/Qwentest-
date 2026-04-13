package org.apache.commons.math3.util;

import java.math.BigInteger;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;

/* loaded from: classes10.dex */
public final class ArithmeticUtils {
    private ArithmeticUtils() {
    }

    public static int addAndCheck(int x, int y) throws MathArithmeticException {
        long s = x + y;
        if (s < -2147483648L || s > 2147483647L) {
            throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_ADDITION, Integer.valueOf(x), Integer.valueOf(y));
        }
        return (int) s;
    }

    public static long addAndCheck(long a, long b) throws MathArithmeticException {
        return addAndCheck(a, b, LocalizedFormats.OVERFLOW_IN_ADDITION);
    }

    @Deprecated
    public static long binomialCoefficient(int n, int k) throws NotPositiveException, NumberIsTooLargeException, MathArithmeticException {
        return CombinatoricsUtils.binomialCoefficient(n, k);
    }

    @Deprecated
    public static double binomialCoefficientDouble(int n, int k) throws NotPositiveException, NumberIsTooLargeException, MathArithmeticException {
        return CombinatoricsUtils.binomialCoefficientDouble(n, k);
    }

    @Deprecated
    public static double binomialCoefficientLog(int n, int k) throws NotPositiveException, NumberIsTooLargeException, MathArithmeticException {
        return CombinatoricsUtils.binomialCoefficientLog(n, k);
    }

    @Deprecated
    public static long factorial(int n) throws NotPositiveException, MathArithmeticException {
        return CombinatoricsUtils.factorial(n);
    }

    @Deprecated
    public static double factorialDouble(int n) throws NotPositiveException {
        return CombinatoricsUtils.factorialDouble(n);
    }

    @Deprecated
    public static double factorialLog(int n) throws NotPositiveException {
        return CombinatoricsUtils.factorialLog(n);
    }

    public static int gcd(int p, int q) throws MathArithmeticException {
        int a = p;
        int b = q;
        if (a == 0 || b == 0) {
            if (a == Integer.MIN_VALUE || b == Integer.MIN_VALUE) {
                throw new MathArithmeticException(LocalizedFormats.GCD_OVERFLOW_32_BITS, Integer.valueOf(p), Integer.valueOf(q));
            }
            return FastMath.abs(a + b);
        }
        long al = a;
        long bl = b;
        boolean useLong = false;
        if (a < 0) {
            if (Integer.MIN_VALUE == a) {
                useLong = true;
            } else {
                a = -a;
            }
            al = -al;
        }
        if (b < 0) {
            if (Integer.MIN_VALUE == b) {
                useLong = true;
            } else {
                b = -b;
            }
            bl = -bl;
        }
        if (useLong) {
            if (al == bl) {
                throw new MathArithmeticException(LocalizedFormats.GCD_OVERFLOW_32_BITS, Integer.valueOf(p), Integer.valueOf(q));
            }
            long blbu = bl;
            long bl2 = al;
            long al2 = blbu % al;
            if (al2 == 0) {
                if (bl2 > 2147483647L) {
                    throw new MathArithmeticException(LocalizedFormats.GCD_OVERFLOW_32_BITS, Integer.valueOf(p), Integer.valueOf(q));
                }
                return (int) bl2;
            }
            b = (int) al2;
            a = (int) (bl2 % al2);
        }
        return gcdPositive(a, b);
    }

    private static int gcdPositive(int a, int b) {
        if (a == 0) {
            return b;
        }
        if (b == 0) {
            return a;
        }
        int aTwos = Integer.numberOfTrailingZeros(a);
        int a2 = a >> aTwos;
        int bTwos = Integer.numberOfTrailingZeros(b);
        int b2 = b >> bTwos;
        int shift = FastMath.min(aTwos, bTwos);
        while (a2 != b2) {
            int delta = a2 - b2;
            b2 = Math.min(a2, b2);
            int a3 = Math.abs(delta);
            a2 = a3 >> Integer.numberOfTrailingZeros(a3);
        }
        return a2 << shift;
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0038, code lost:
    
        if ((r0 & 1) != 1) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x003a, code lost:
    
        r9 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0043, code lost:
    
        if ((r9 & 1) != 0) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0049, code lost:
    
        if (r9 <= 0) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x004b, code lost:
    
        r0 = -r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x004e, code lost:
    
        r9 = (r2 - r0) / 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0054, code lost:
    
        if (r9 != 0) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0059, code lost:
    
        return (-r0) * (1 << r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x004d, code lost:
    
        r2 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0045, code lost:
    
        r9 = r9 / 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x003c, code lost:
    
        r9 = -(r0 / 2);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static long gcd(long r15, long r17) throws org.apache.commons.math3.exception.MathArithmeticException {
        /*
            r0 = r15
            r2 = r17
            r4 = 0
            int r6 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r6 == 0) goto L6e
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 != 0) goto Le
            goto L6e
        Le:
            int r6 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r6 <= 0) goto L13
            long r0 = -r0
        L13:
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 <= 0) goto L18
            long r2 = -r2
        L18:
            r6 = 0
        L19:
            r7 = 1
            long r9 = r0 & r7
            int r9 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1))
            r10 = 63
            r11 = 2
            if (r9 != 0) goto L32
            long r13 = r2 & r7
            int r9 = (r13 > r4 ? 1 : (r13 == r4 ? 0 : -1))
            if (r9 != 0) goto L32
            if (r6 >= r10) goto L32
            long r0 = r0 / r11
            long r2 = r2 / r11
            int r6 = r6 + 1
            goto L19
        L32:
            if (r6 == r10) goto L5a
            long r9 = r0 & r7
            int r9 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r9 != 0) goto L3c
            r9 = r2
            goto L3f
        L3c:
            long r9 = r0 / r11
            long r9 = -r9
        L3f:
            long r13 = r9 & r7
            int r13 = (r13 > r4 ? 1 : (r13 == r4 ? 0 : -1))
            if (r13 != 0) goto L47
            long r9 = r9 / r11
            goto L3f
        L47:
            int r13 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1))
            if (r13 <= 0) goto L4d
            long r0 = -r9
            goto L4e
        L4d:
            r2 = r9
        L4e:
            long r13 = r2 - r0
            long r9 = r13 / r11
            int r13 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1))
            if (r13 != 0) goto L3f
            long r4 = -r0
            long r7 = r7 << r6
            long r4 = r4 * r7
            return r4
        L5a:
            org.apache.commons.math3.exception.MathArithmeticException r4 = new org.apache.commons.math3.exception.MathArithmeticException
            org.apache.commons.math3.exception.util.LocalizedFormats r5 = org.apache.commons.math3.exception.util.LocalizedFormats.GCD_OVERFLOW_64_BITS
            java.lang.Long r7 = java.lang.Long.valueOf(r15)
            java.lang.Long r8 = java.lang.Long.valueOf(r17)
            java.lang.Object[] r7 = new java.lang.Object[]{r7, r8}
            r4.<init>(r5, r7)
            throw r4
        L6e:
            r4 = -9223372036854775808
            int r6 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r6 == 0) goto L82
            int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r4 == 0) goto L82
            long r4 = org.apache.commons.math3.util.FastMath.abs(r0)
            long r6 = org.apache.commons.math3.util.FastMath.abs(r2)
            long r4 = r4 + r6
            return r4
        L82:
            org.apache.commons.math3.exception.MathArithmeticException r4 = new org.apache.commons.math3.exception.MathArithmeticException
            org.apache.commons.math3.exception.util.LocalizedFormats r5 = org.apache.commons.math3.exception.util.LocalizedFormats.GCD_OVERFLOW_64_BITS
            java.lang.Long r6 = java.lang.Long.valueOf(r15)
            java.lang.Long r7 = java.lang.Long.valueOf(r17)
            java.lang.Object[] r6 = new java.lang.Object[]{r6, r7}
            r4.<init>(r5, r6)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.util.ArithmeticUtils.gcd(long, long):long");
    }

    public static int lcm(int a, int b) throws MathArithmeticException {
        if (a == 0 || b == 0) {
            return 0;
        }
        int lcm = FastMath.abs(mulAndCheck(a / gcd(a, b), b));
        if (lcm == Integer.MIN_VALUE) {
            throw new MathArithmeticException(LocalizedFormats.LCM_OVERFLOW_32_BITS, Integer.valueOf(a), Integer.valueOf(b));
        }
        return lcm;
    }

    public static long lcm(long a, long b) throws MathArithmeticException {
        if (a == 0 || b == 0) {
            return 0L;
        }
        long lcm = FastMath.abs(mulAndCheck(a / gcd(a, b), b));
        if (lcm == Long.MIN_VALUE) {
            throw new MathArithmeticException(LocalizedFormats.LCM_OVERFLOW_64_BITS, Long.valueOf(a), Long.valueOf(b));
        }
        return lcm;
    }

    public static int mulAndCheck(int x, int y) throws MathArithmeticException {
        long m = x * y;
        if (m < -2147483648L || m > 2147483647L) {
            throw new MathArithmeticException();
        }
        return (int) m;
    }

    public static long mulAndCheck(long a, long b) throws MathArithmeticException {
        if (a > b) {
            long ret = mulAndCheck(b, a);
            return ret;
        }
        if (a >= 0) {
            if (a <= 0) {
                return 0L;
            }
            if (a <= Long.MAX_VALUE / b) {
                long ret2 = a * b;
                return ret2;
            }
            throw new MathArithmeticException();
        }
        if (b < 0) {
            if (a >= Long.MAX_VALUE / b) {
                long ret3 = a * b;
                return ret3;
            }
            throw new MathArithmeticException();
        }
        if (b <= 0) {
            return 0L;
        }
        if (Long.MIN_VALUE / b <= a) {
            long ret4 = a * b;
            return ret4;
        }
        throw new MathArithmeticException();
    }

    public static int subAndCheck(int x, int y) throws MathArithmeticException {
        long s = x - y;
        if (s < -2147483648L || s > 2147483647L) {
            throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_SUBTRACTION, Integer.valueOf(x), Integer.valueOf(y));
        }
        return (int) s;
    }

    public static long subAndCheck(long a, long b) throws MathArithmeticException {
        if (b != Long.MIN_VALUE) {
            long ret = addAndCheck(a, -b, LocalizedFormats.OVERFLOW_IN_ADDITION);
            return ret;
        }
        if (a < 0) {
            long ret2 = a - b;
            return ret2;
        }
        throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_ADDITION, Long.valueOf(a), Long.valueOf(-b));
    }

    public static int pow(int k, int e) throws NotPositiveException, MathArithmeticException {
        if (e < 0) {
            throw new NotPositiveException(LocalizedFormats.EXPONENT, Integer.valueOf(e));
        }
        int exp = e;
        int result = 1;
        int k2p = k;
        while (true) {
            if ((exp & 1) != 0) {
                try {
                    result = mulAndCheck(result, k2p);
                } catch (MathArithmeticException mae) {
                    mae.getContext().addMessage(LocalizedFormats.OVERFLOW, new Object[0]);
                    mae.getContext().addMessage(LocalizedFormats.BASE, Integer.valueOf(k));
                    mae.getContext().addMessage(LocalizedFormats.EXPONENT, Integer.valueOf(e));
                    throw mae;
                }
            }
            exp >>= 1;
            if (exp != 0) {
                k2p = mulAndCheck(k2p, k2p);
            } else {
                return result;
            }
        }
    }

    @Deprecated
    public static int pow(int k, long e) throws NotPositiveException {
        if (e < 0) {
            throw new NotPositiveException(LocalizedFormats.EXPONENT, Long.valueOf(e));
        }
        int result = 1;
        int k2p = k;
        while (e != 0) {
            if ((1 & e) != 0) {
                result *= k2p;
            }
            k2p *= k2p;
            e >>= 1;
        }
        return result;
    }

    public static long pow(long k, int e) throws NotPositiveException, MathArithmeticException {
        if (e < 0) {
            throw new NotPositiveException(LocalizedFormats.EXPONENT, Integer.valueOf(e));
        }
        int exp = e;
        long result = 1;
        long k2p = k;
        while (true) {
            if ((exp & 1) != 0) {
                try {
                    result = mulAndCheck(result, k2p);
                } catch (MathArithmeticException mae) {
                    mae.getContext().addMessage(LocalizedFormats.OVERFLOW, new Object[0]);
                    mae.getContext().addMessage(LocalizedFormats.BASE, Long.valueOf(k));
                    mae.getContext().addMessage(LocalizedFormats.EXPONENT, Integer.valueOf(e));
                    throw mae;
                }
            }
            exp >>= 1;
            if (exp != 0) {
                k2p = mulAndCheck(k2p, k2p);
            } else {
                return result;
            }
        }
    }

    @Deprecated
    public static long pow(long k, long e) throws NotPositiveException {
        if (e < 0) {
            throw new NotPositiveException(LocalizedFormats.EXPONENT, Long.valueOf(e));
        }
        long result = 1;
        long k2p = k;
        while (e != 0) {
            if ((1 & e) != 0) {
                result *= k2p;
            }
            k2p *= k2p;
            e >>= 1;
        }
        return result;
    }

    public static BigInteger pow(BigInteger k, int e) throws NotPositiveException {
        if (e < 0) {
            throw new NotPositiveException(LocalizedFormats.EXPONENT, Integer.valueOf(e));
        }
        return k.pow(e);
    }

    public static BigInteger pow(BigInteger k, long e) throws NotPositiveException {
        if (e < 0) {
            throw new NotPositiveException(LocalizedFormats.EXPONENT, Long.valueOf(e));
        }
        BigInteger result = BigInteger.ONE;
        BigInteger k2p = k;
        while (e != 0) {
            if ((1 & e) != 0) {
                result = result.multiply(k2p);
            }
            k2p = k2p.multiply(k2p);
            e >>= 1;
        }
        return result;
    }

    public static BigInteger pow(BigInteger k, BigInteger e) throws NotPositiveException {
        if (e.compareTo(BigInteger.ZERO) < 0) {
            throw new NotPositiveException(LocalizedFormats.EXPONENT, e);
        }
        BigInteger result = BigInteger.ONE;
        BigInteger k2p = k;
        while (!BigInteger.ZERO.equals(e)) {
            if (e.testBit(0)) {
                result = result.multiply(k2p);
            }
            k2p = k2p.multiply(k2p);
            e = e.shiftRight(1);
        }
        return result;
    }

    @Deprecated
    public static long stirlingS2(int n, int k) throws NotPositiveException, NumberIsTooLargeException, MathArithmeticException {
        return CombinatoricsUtils.stirlingS2(n, k);
    }

    private static long addAndCheck(long a, long b, Localizable pattern) throws MathArithmeticException {
        long result = a + b;
        if (!(((a ^ b) < 0) | ((a ^ result) >= 0))) {
            throw new MathArithmeticException(pattern, Long.valueOf(a), Long.valueOf(b));
        }
        return result;
    }

    public static boolean isPowerOfTwo(long n) {
        return n > 0 && ((n - 1) & n) == 0;
    }
}
