package org.apache.commons.compress.compressors;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes9.dex */
public class FileNameUtil {
    private final Map<String, String> compressSuffix = new HashMap();
    private final String defaultExtension;
    private final int longestCompressedSuffix;
    private final int longestUncompressedSuffix;
    private final int shortestCompressedSuffix;
    private final int shortestUncompressedSuffix;
    private final Map<String, String> uncompressSuffix;

    public FileNameUtil(Map<String, String> uncompressSuffix, String defaultExtension) {
        this.uncompressSuffix = Collections.unmodifiableMap(uncompressSuffix);
        int lc = Integer.MIN_VALUE;
        int sc = Integer.MAX_VALUE;
        int lu = Integer.MIN_VALUE;
        int su = Integer.MAX_VALUE;
        for (final Map.Entry<String, String> ent : uncompressSuffix.entrySet()) {
            int cl = ent.getKey().length();
            lc = cl > lc ? cl : lc;
            sc = cl < sc ? cl : sc;
            String u = ent.getValue();
            int ul = u.length();
            if (ul > 0) {
                this.compressSuffix.computeIfAbsent(u, new Function() { // from class: org.apache.commons.compress.compressors.FileNameUtil$$ExternalSyntheticLambda0
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return FileNameUtil.lambda$new$0(ent, (String) obj);
                    }
                });
                lu = ul > lu ? ul : lu;
                if (ul < su) {
                    su = ul;
                }
            }
        }
        this.longestCompressedSuffix = lc;
        this.longestUncompressedSuffix = lu;
        this.shortestCompressedSuffix = sc;
        this.shortestUncompressedSuffix = su;
        this.defaultExtension = defaultExtension;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String lambda$new$0(Map.Entry ent, String k) {
        return (String) ent.getKey();
    }

    @Deprecated
    public String getCompressedFilename(String fileName) {
        return getCompressedFileName(fileName);
    }

    public String getCompressedFileName(String fileName) {
        String lower = StringUtils.toRootLowerCase(fileName);
        int n = lower.length();
        for (int i = this.shortestUncompressedSuffix; i <= this.longestUncompressedSuffix && i < n; i++) {
            String suffix = this.compressSuffix.get(lower.substring(n - i));
            if (suffix != null) {
                return fileName.substring(0, n - i) + suffix;
            }
        }
        return fileName + this.defaultExtension;
    }

    @Deprecated
    public String getUncompressedFilename(String fileName) {
        return getUncompressedFileName(fileName);
    }

    public String getUncompressedFileName(String fileName) {
        String lower = StringUtils.toRootLowerCase(fileName);
        int n = lower.length();
        for (int i = this.shortestCompressedSuffix; i <= this.longestCompressedSuffix && i < n; i++) {
            String suffix = this.uncompressSuffix.get(lower.substring(n - i));
            if (suffix != null) {
                return fileName.substring(0, n - i) + suffix;
            }
        }
        return fileName;
    }

    @Deprecated
    public boolean isCompressedFilename(String fileName) {
        return isCompressedFileName(fileName);
    }

    public boolean isCompressedFileName(String fileName) {
        String lower = StringUtils.toRootLowerCase(fileName);
        int n = lower.length();
        for (int i = this.shortestCompressedSuffix; i <= this.longestCompressedSuffix && i < n; i++) {
            if (this.uncompressSuffix.containsKey(lower.substring(n - i))) {
                return true;
            }
        }
        return false;
    }
}
