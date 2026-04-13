package org.apache.commons.compress.compressors.brotli;

import org.apache.commons.compress.utils.OsgiUtils;

/* loaded from: classes9.dex */
public class BrotliUtils {
    private static volatile CachedAvailability cachedBrotliAvailability = CachedAvailability.DONT_CACHE;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public enum CachedAvailability {
        DONT_CACHE,
        CACHED_AVAILABLE,
        CACHED_UNAVAILABLE
    }

    static {
        setCacheBrotliAvailablity(!OsgiUtils.isRunningInOsgiEnvironment());
    }

    static CachedAvailability getCachedBrotliAvailability() {
        return cachedBrotliAvailability;
    }

    private static boolean internalIsBrotliCompressionAvailable() {
        try {
            Class.forName("org.brotli.dec.BrotliInputStream");
            return true;
        } catch (Exception | NoClassDefFoundError e) {
            return false;
        }
    }

    public static boolean isBrotliCompressionAvailable() {
        CachedAvailability cachedResult = cachedBrotliAvailability;
        if (cachedResult != CachedAvailability.DONT_CACHE) {
            return cachedResult == CachedAvailability.CACHED_AVAILABLE;
        }
        return internalIsBrotliCompressionAvailable();
    }

    public static void setCacheBrotliAvailablity(boolean doCache) {
        if (!doCache) {
            cachedBrotliAvailability = CachedAvailability.DONT_CACHE;
        } else if (cachedBrotliAvailability == CachedAvailability.DONT_CACHE) {
            boolean hasBrotli = internalIsBrotliCompressionAvailable();
            cachedBrotliAvailability = hasBrotli ? CachedAvailability.CACHED_AVAILABLE : CachedAvailability.CACHED_UNAVAILABLE;
        }
    }

    private BrotliUtils() {
    }
}
