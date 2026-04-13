package org.apache.commons.lang3;

import java.util.Random;
import java.util.function.Supplier;
import org.apache.commons.collections4.CollectionUtils;

/* loaded from: classes9.dex */
public class RandomStringUtils {
    private static final int ASCII_0 = 48;
    private static final int ASCII_9 = 57;
    private static final int ASCII_A = 65;
    private static final int ASCII_z = 122;
    private static final int BASE_CACHE_SIZE_PADDING = 10;
    private static final int BITS_TO_BYTES_DIVISOR = 5;
    private static final int CACHE_PADDING_BITS = 3;
    private final Supplier<RandomUtils> random;
    private static final Supplier<RandomUtils> SECURE_SUPPLIER = new Supplier() { // from class: org.apache.commons.lang3.RandomStringUtils$$ExternalSyntheticLambda0
        @Override // java.util.function.Supplier
        public final Object get() {
            return RandomUtils.secure();
        }
    };
    private static RandomStringUtils INSECURE = new RandomStringUtils(new Supplier() { // from class: org.apache.commons.lang3.RandomStringUtils$$ExternalSyntheticLambda1
        @Override // java.util.function.Supplier
        public final Object get() {
            return RandomUtils.insecure();
        }
    });
    private static RandomStringUtils SECURE = new RandomStringUtils(SECURE_SUPPLIER);
    private static RandomStringUtils SECURE_STRONG = new RandomStringUtils(new Supplier() { // from class: org.apache.commons.lang3.RandomStringUtils$$ExternalSyntheticLambda2
        @Override // java.util.function.Supplier
        public final Object get() {
            return RandomUtils.secureStrong();
        }
    });
    private static final char[] ALPHANUMERICAL_CHARS = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public static RandomStringUtils insecure() {
        return INSECURE;
    }

    @Deprecated
    public static String random(int count) {
        return secure().next(count);
    }

    @Deprecated
    public static String random(int count, boolean letters, boolean numbers) {
        return secure().next(count, letters, numbers);
    }

    @Deprecated
    public static String random(int count, char... chars) {
        return secure().next(count, chars);
    }

    @Deprecated
    public static String random(int count, int start, int end, boolean letters, boolean numbers) {
        return secure().next(count, start, end, letters, numbers);
    }

    @Deprecated
    public static String random(int count, int start, int end, boolean letters, boolean numbers, char... chars) {
        return secure().next(count, start, end, letters, numbers, chars);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r13v0, types: [char] */
    /* JADX WARN: Type inference failed for: r13v1, types: [int] */
    /* JADX WARN: Type inference failed for: r13v2, types: [int] */
    public static String random(int count, int start, int end, boolean letters, boolean numbers, char[] cArr, Random random) {
        int start2;
        int end2;
        int randomValue;
        char c;
        int end3 = end;
        if (count == 0) {
            return "";
        }
        if (count < 0) {
            throw new IllegalArgumentException("Requested random string length " + count + " is less than 0.");
        }
        if (cArr == 0 || cArr.length != 0) {
            if (start == 0 && end3 == 0) {
                if (cArr != 0) {
                    end3 = cArr.length;
                    start2 = start;
                } else if (!letters && !numbers) {
                    end3 = 1114111;
                    start2 = start;
                } else {
                    end3 = 123;
                    start2 = 32;
                }
            } else {
                if (end3 <= start) {
                    throw new IllegalArgumentException("Parameter end (" + end3 + ") must be greater than start (" + start + ")");
                }
                if (start >= 0 && end3 >= 0) {
                    start2 = start;
                } else {
                    throw new IllegalArgumentException("Character positions MUST be >= 0");
                }
            }
            if (end3 <= 1114111) {
                end2 = end3;
            } else {
                end2 = 1114111;
            }
            if (cArr != 0 || end2 > 127) {
                randomValue = count;
            } else if (!letters || !numbers || start2 > 48 || end2 < 123) {
                randomValue = count;
                if ((numbers && end2 <= 48) || (letters && end2 <= 65)) {
                    throw new IllegalArgumentException("Parameter end (" + end2 + ") must be greater then (48) for generating digits or greater then (65) for generating letters.");
                }
                if (letters && numbers) {
                    start2 = Math.max(48, start2);
                    end2 = Math.min(123, end2);
                } else if (numbers) {
                    start2 = Math.max(48, start2);
                    end2 = Math.min(58, end2);
                } else if (letters) {
                    start2 = Math.max(65, start2);
                    end2 = Math.min(123, end2);
                }
            } else {
                return random(count, 0, 0, false, false, ALPHANUMERICAL_CHARS, random);
            }
            ?? sb = new StringBuilder(randomValue);
            int gap = end2 - start2;
            int gapBits = 32 - Integer.numberOfLeadingZeros(gap);
            long desiredCacheSize = (((randomValue * gapBits) + 3) / 5) + 10;
            int cacheSize = (int) Math.min(desiredCacheSize, 429496739L);
            CachedRandomBits arb = new CachedRandomBits(cacheSize, random);
            while (true) {
                int count2 = randomValue - 1;
                if (randomValue != 0) {
                    int randomValue2 = arb.nextBits(gapBits) + start2;
                    if (randomValue2 >= end2) {
                        randomValue = count2 + 1;
                    } else {
                        if (cArr == 0) {
                            c = randomValue2;
                            switch (Character.getType((int) c)) {
                                case 0:
                                case 18:
                                case 19:
                                    randomValue = count2 + 1;
                                    continue;
                            }
                        } else {
                            c = cArr[randomValue2];
                        }
                        int numberOfChars = Character.charCount(c);
                        if (count2 == 0 && numberOfChars > 1) {
                            randomValue = count2 + 1;
                        } else if ((letters && Character.isLetter((int) c)) || ((numbers && Character.isDigit((int) c)) || (!letters && !numbers))) {
                            sb.appendCodePoint(c);
                            if (numberOfChars != 2) {
                                randomValue = count2;
                            } else {
                                randomValue = count2 - 1;
                            }
                        } else {
                            randomValue = count2 + 1;
                        }
                    }
                } else {
                    return sb.toString();
                }
            }
        } else {
            throw new IllegalArgumentException("The chars array must not be empty");
        }
    }

    @Deprecated
    public static String random(int count, String chars) {
        return secure().next(count, chars);
    }

    @Deprecated
    public static String randomAlphabetic(int count) {
        return secure().nextAlphabetic(count);
    }

    @Deprecated
    public static String randomAlphabetic(int minLengthInclusive, int maxLengthExclusive) {
        return secure().nextAlphabetic(minLengthInclusive, maxLengthExclusive);
    }

    @Deprecated
    public static String randomAlphanumeric(int count) {
        return secure().nextAlphanumeric(count);
    }

    @Deprecated
    public static String randomAlphanumeric(int minLengthInclusive, int maxLengthExclusive) {
        return secure().nextAlphanumeric(minLengthInclusive, maxLengthExclusive);
    }

    @Deprecated
    public static String randomAscii(int count) {
        return secure().nextAscii(count);
    }

    @Deprecated
    public static String randomAscii(int minLengthInclusive, int maxLengthExclusive) {
        return secure().nextAscii(minLengthInclusive, maxLengthExclusive);
    }

    @Deprecated
    public static String randomGraph(int count) {
        return secure().nextGraph(count);
    }

    @Deprecated
    public static String randomGraph(int minLengthInclusive, int maxLengthExclusive) {
        return secure().nextGraph(minLengthInclusive, maxLengthExclusive);
    }

    @Deprecated
    public static String randomNumeric(int count) {
        return secure().nextNumeric(count);
    }

    @Deprecated
    public static String randomNumeric(int minLengthInclusive, int maxLengthExclusive) {
        return secure().nextNumeric(minLengthInclusive, maxLengthExclusive);
    }

    @Deprecated
    public static String randomPrint(int count) {
        return secure().nextPrint(count);
    }

    @Deprecated
    public static String randomPrint(int minLengthInclusive, int maxLengthExclusive) {
        return secure().nextPrint(minLengthInclusive, maxLengthExclusive);
    }

    public static RandomStringUtils secure() {
        return SECURE;
    }

    public static RandomStringUtils secureStrong() {
        return SECURE_STRONG;
    }

    @Deprecated
    public RandomStringUtils() {
        this(SECURE_SUPPLIER);
    }

    private RandomStringUtils(Supplier<RandomUtils> random) {
        this.random = random;
    }

    public String next(int count) {
        return next(count, false, false);
    }

    public String next(int count, boolean letters, boolean numbers) {
        return next(count, 0, 0, letters, numbers);
    }

    public String next(int count, char... chars) {
        if (chars == null) {
            return random(count, 0, 0, false, false, null, random());
        }
        return random(count, 0, chars.length, false, false, chars, random());
    }

    public String next(int count, int start, int end, boolean letters, boolean numbers) {
        return random(count, start, end, letters, numbers, null, random());
    }

    public String next(int count, int start, int end, boolean letters, boolean numbers, char... chars) {
        return random(count, start, end, letters, numbers, chars, random());
    }

    public String next(int count, String chars) {
        if (chars == null) {
            return random(count, 0, 0, false, false, null, random());
        }
        return next(count, chars.toCharArray());
    }

    public String nextAlphabetic(int count) {
        return next(count, true, false);
    }

    public String nextAlphabetic(int minLengthInclusive, int maxLengthExclusive) {
        return nextAlphabetic(randomUtils().randomInt(minLengthInclusive, maxLengthExclusive));
    }

    public String nextAlphanumeric(int count) {
        return next(count, true, true);
    }

    public String nextAlphanumeric(int minLengthInclusive, int maxLengthExclusive) {
        return nextAlphanumeric(randomUtils().randomInt(minLengthInclusive, maxLengthExclusive));
    }

    public String nextAscii(int count) {
        return next(count, 32, 127, false, false);
    }

    public String nextAscii(int minLengthInclusive, int maxLengthExclusive) {
        return nextAscii(randomUtils().randomInt(minLengthInclusive, maxLengthExclusive));
    }

    public String nextGraph(int count) {
        return next(count, 33, 126, false, false);
    }

    public String nextGraph(int minLengthInclusive, int maxLengthExclusive) {
        return nextGraph(randomUtils().randomInt(minLengthInclusive, maxLengthExclusive));
    }

    public String nextNumeric(int count) {
        return next(count, false, true);
    }

    public String nextNumeric(int minLengthInclusive, int maxLengthExclusive) {
        return nextNumeric(randomUtils().randomInt(minLengthInclusive, maxLengthExclusive));
    }

    public String nextPrint(int count) {
        return next(count, 32, 126, false, false);
    }

    public String nextPrint(int minLengthInclusive, int maxLengthExclusive) {
        return nextPrint(randomUtils().randomInt(minLengthInclusive, maxLengthExclusive));
    }

    private Random random() {
        return randomUtils().random();
    }

    private RandomUtils randomUtils() {
        return this.random.get();
    }

    public String toString() {
        return "RandomStringUtils [random=" + random() + CollectionUtils.DEFAULT_TOSTRING_SUFFIX;
    }
}
