package org.apache.commons.math3.primes;

import java.util.List;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

/* loaded from: classes10.dex */
public class Primes {
    private Primes() {
    }

    public static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        int[] arr$ = SmallPrimes.PRIMES;
        int len$ = arr$.length;
        for (int i$ = 0; i$ < len$; i$++) {
            int p = arr$[i$];
            if (n % p == 0) {
                return n == p;
            }
        }
        return SmallPrimes.millerRabinPrimeTest(n);
    }

    public static int nextPrime(int n) {
        int n2;
        if (n < 0) {
            throw new MathIllegalArgumentException(LocalizedFormats.NUMBER_TOO_SMALL, Integer.valueOf(n), 0);
        }
        if (n == 2 || (n2 = n | 1) == 1) {
            return 2;
        }
        if (isPrime(n2)) {
            return n2;
        }
        int rem = n2 % 3;
        if (rem == 0) {
            n2 += 2;
        } else if (1 == rem) {
            n2 += 4;
        }
        while (!isPrime(n2)) {
            int n3 = n2 + 2;
            if (isPrime(n3)) {
                return n3;
            }
            n2 = n3 + 4;
        }
        return n2;
    }

    public static List<Integer> primeFactors(int n) {
        if (n < 2) {
            throw new MathIllegalArgumentException(LocalizedFormats.NUMBER_TOO_SMALL, Integer.valueOf(n), 2);
        }
        return SmallPrimes.trialDivision(n);
    }
}
