package org.apache.commons.math3.primes;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes10.dex */
class PollardRho {
    private PollardRho() {
    }

    public static List<Integer> primeFactors(int n) {
        List<Integer> factors = new ArrayList<>();
        int n2 = SmallPrimes.smallTrialDivision(n, factors);
        if (1 == n2) {
            return factors;
        }
        if (SmallPrimes.millerRabinPrimeTest(n2)) {
            factors.add(Integer.valueOf(n2));
            return factors;
        }
        int divisor = rhoBrent(n2);
        factors.add(Integer.valueOf(divisor));
        factors.add(Integer.valueOf(n2 / divisor));
        return factors;
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x006a, code lost:
    
        r1 = org.apache.commons.math3.util.FastMath.abs(r9);
        r1 = gcdPositive(r1, r19);
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0073, code lost:
    
        if (1 == r1) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0076, code lost:
    
        r7 = r7 + 25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0079, code lost:
    
        if (r7 < r5) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0075, code lost:
    
        return r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static int rhoBrent(int r19) {
        /*
            r0 = r19
            r1 = 2
            r2 = 25
            int r3 = org.apache.commons.math3.primes.SmallPrimes.PRIMES_LAST
            r4 = 2
            r5 = 1
        L9:
            r6 = r4
            r7 = 0
        Lb:
            if (r7 >= r5) goto L18
            long r8 = (long) r4
            long r10 = (long) r4
            long r8 = r8 * r10
            long r10 = (long) r3
            long r10 = r10 + r8
            long r12 = (long) r0
            long r10 = r10 % r12
            int r4 = (int) r10
            int r7 = r7 + 1
            goto Lb
        L18:
            r7 = 0
        L19:
            int r8 = r5 - r7
            r9 = 25
            int r8 = org.apache.commons.math3.util.FastMath.min(r9, r8)
            r9 = 1
            r10 = -3
        L23:
            if (r10 >= r8) goto L65
            long r11 = (long) r4
            long r13 = (long) r4
            long r11 = r11 * r13
            long r13 = (long) r3
            long r13 = r13 + r11
            r15 = r1
            r16 = r2
            long r1 = (long) r0
            long r13 = r13 % r1
            int r4 = (int) r13
            int r1 = r6 - r4
            int r1 = org.apache.commons.math3.util.FastMath.abs(r1)
            long r1 = (long) r1
            r13 = 0
            int r13 = (r13 > r1 ? 1 : (r13 == r1 ? 0 : -1))
            if (r13 != 0) goto L45
            int r13 = org.apache.commons.math3.primes.SmallPrimes.PRIMES_LAST
            int r3 = r3 + r13
            r7 = -25
            r4 = 2
            r5 = 1
            goto L6a
        L45:
            long r13 = (long) r9
            long r13 = r13 * r1
            r17 = r3
            r18 = r4
            long r3 = (long) r0
            long r3 = r13 % r3
            int r9 = (int) r3
            if (r9 != 0) goto L5b
            int r3 = (int) r1
            int r3 = org.apache.commons.math3.util.FastMath.abs(r3)
            int r3 = gcdPositive(r3, r0)
            return r3
        L5b:
            int r10 = r10 + 1
            r1 = r15
            r2 = r16
            r3 = r17
            r4 = r18
            goto L23
        L65:
            r15 = r1
            r16 = r2
            r17 = r3
        L6a:
            int r1 = org.apache.commons.math3.util.FastMath.abs(r9)
            int r1 = gcdPositive(r1, r0)
            r2 = 1
            if (r2 == r1) goto L76
            return r1
        L76:
            int r7 = r7 + 25
            if (r7 < r5) goto L81
            int r5 = r5 * 2
            r1 = r15
            r2 = r16
            goto L9
        L81:
            r1 = r15
            r2 = r16
            goto L19
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.primes.PollardRho.rhoBrent(int):int");
    }

    static int gcdPositive(int a, int b) {
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
            b2 = FastMath.min(a2, b2);
            int a3 = FastMath.abs(delta);
            a2 = a3 >> Integer.numberOfTrailingZeros(a3);
        }
        return a2 << shift;
    }
}
