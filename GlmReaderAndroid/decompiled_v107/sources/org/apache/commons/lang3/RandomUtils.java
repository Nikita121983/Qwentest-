package org.apache.commons.lang3;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.exception.UncheckedException;

/* loaded from: classes9.dex */
public class RandomUtils {
    private final Supplier<Random> random;
    private static RandomUtils INSECURE = new RandomUtils(new Supplier() { // from class: org.apache.commons.lang3.RandomUtils$$ExternalSyntheticLambda0
        @Override // java.util.function.Supplier
        public final Object get() {
            ThreadLocalRandom current;
            current = ThreadLocalRandom.current();
            return current;
        }
    });
    private static RandomUtils SECURE = new RandomUtils(new Supplier() { // from class: org.apache.commons.lang3.RandomUtils$$ExternalSyntheticLambda1
        @Override // java.util.function.Supplier
        public final Object get() {
            return RandomUtils.m2180$r8$lambda$EAudPwko910NaGekEJs4TLij_c();
        }
    });
    private static final Supplier<Random> SECURE_STRONG_SUPPLIER = new Supplier() { // from class: org.apache.commons.lang3.RandomUtils$$ExternalSyntheticLambda2
        @Override // java.util.function.Supplier
        public final Object get() {
            Random random;
            random = RandomUtils.SECURE_RANDOM_STRONG.get();
            return random;
        }
    };
    private static RandomUtils SECURE_STRONG = new RandomUtils(SECURE_STRONG_SUPPLIER);
    private static final ThreadLocal<SecureRandom> SECURE_RANDOM_STRONG = ThreadLocal.withInitial(new Supplier() { // from class: org.apache.commons.lang3.RandomUtils$$ExternalSyntheticLambda3
        @Override // java.util.function.Supplier
        public final Object get() {
            return RandomUtils.lambda$static$1();
        }
    });

    /* renamed from: $r8$lambda$EAudPwk-o910NaGekEJs4TLij_c, reason: not valid java name */
    public static /* synthetic */ SecureRandom m2180$r8$lambda$EAudPwko910NaGekEJs4TLij_c() {
        return new SecureRandom();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ SecureRandom lambda$static$1() {
        try {
            return SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            throw new UncheckedException(e);
        }
    }

    public static RandomUtils insecure() {
        return INSECURE;
    }

    @Deprecated
    public static boolean nextBoolean() {
        return secure().randomBoolean();
    }

    @Deprecated
    public static byte[] nextBytes(int count) {
        return secure().randomBytes(count);
    }

    @Deprecated
    public static double nextDouble() {
        return secure().randomDouble();
    }

    @Deprecated
    public static double nextDouble(double startInclusive, double endExclusive) {
        return secure().randomDouble(startInclusive, endExclusive);
    }

    @Deprecated
    public static float nextFloat() {
        return secure().randomFloat();
    }

    @Deprecated
    public static float nextFloat(float startInclusive, float endExclusive) {
        return secure().randomFloat(startInclusive, endExclusive);
    }

    @Deprecated
    public static int nextInt() {
        return secure().randomInt();
    }

    @Deprecated
    public static int nextInt(int startInclusive, int endExclusive) {
        return secure().randomInt(startInclusive, endExclusive);
    }

    @Deprecated
    public static long nextLong() {
        return secure().randomLong();
    }

    @Deprecated
    public static long nextLong(long startInclusive, long endExclusive) {
        return secure().randomLong(startInclusive, endExclusive);
    }

    public static RandomUtils secure() {
        return SECURE;
    }

    static SecureRandom secureRandom() {
        return SECURE_RANDOM_STRONG.get();
    }

    public static RandomUtils secureStrong() {
        return SECURE_STRONG;
    }

    @Deprecated
    public RandomUtils() {
        this(SECURE_STRONG_SUPPLIER);
    }

    private RandomUtils(Supplier<Random> random) {
        this.random = random;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Random random() {
        return this.random.get();
    }

    public boolean randomBoolean() {
        return random().nextBoolean();
    }

    public byte[] randomBytes(int count) {
        Validate.isTrue(count >= 0, "Count cannot be negative.", new Object[0]);
        byte[] result = new byte[count];
        random().nextBytes(result);
        return result;
    }

    public double randomDouble() {
        return randomDouble(0.0d, Double.MAX_VALUE);
    }

    public double randomDouble(double startInclusive, double endExclusive) {
        Validate.isTrue(endExclusive >= startInclusive, "Start value must be smaller or equal to end value.", new Object[0]);
        Validate.isTrue(startInclusive >= 0.0d, "Both range values must be non-negative.", new Object[0]);
        if (startInclusive == endExclusive) {
            return startInclusive;
        }
        return ((endExclusive - startInclusive) * random().nextDouble()) + startInclusive;
    }

    public float randomFloat() {
        return randomFloat(0.0f, Float.MAX_VALUE);
    }

    public float randomFloat(float startInclusive, float endExclusive) {
        Validate.isTrue(endExclusive >= startInclusive, "Start value must be smaller or equal to end value.", new Object[0]);
        Validate.isTrue(startInclusive >= 0.0f, "Both range values must be non-negative.", new Object[0]);
        if (startInclusive == endExclusive) {
            return startInclusive;
        }
        return ((endExclusive - startInclusive) * random().nextFloat()) + startInclusive;
    }

    public int randomInt() {
        return randomInt(0, Integer.MAX_VALUE);
    }

    public int randomInt(int startInclusive, int endExclusive) {
        Validate.isTrue(endExclusive >= startInclusive, "Start value must be smaller or equal to end value.", new Object[0]);
        Validate.isTrue(startInclusive >= 0, "Both range values must be non-negative.", new Object[0]);
        if (startInclusive == endExclusive) {
            return startInclusive;
        }
        return random().nextInt(endExclusive - startInclusive) + startInclusive;
    }

    public long randomLong() {
        return randomLong(Long.MAX_VALUE);
    }

    private long randomLong(long n) {
        long bits;
        long val;
        do {
            bits = random().nextLong() >>> 1;
            val = bits % n;
        } while (((bits - val) + n) - 1 < 0);
        return val;
    }

    public long randomLong(long startInclusive, long endExclusive) {
        Validate.isTrue(endExclusive >= startInclusive, "Start value must be smaller or equal to end value.", new Object[0]);
        Validate.isTrue(startInclusive >= 0, "Both range values must be non-negative.", new Object[0]);
        if (startInclusive == endExclusive) {
            return startInclusive;
        }
        return randomLong(endExclusive - startInclusive) + startInclusive;
    }

    public String toString() {
        return "RandomUtils [random=" + random() + CollectionUtils.DEFAULT_TOSTRING_SUFFIX;
    }
}
