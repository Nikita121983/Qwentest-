package org.apache.commons.compress.archivers.zip;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* loaded from: classes9.dex */
public enum ZipMethod {
    STORED(0),
    UNSHRINKING(1),
    EXPANDING_LEVEL_1(2),
    EXPANDING_LEVEL_2(3),
    EXPANDING_LEVEL_3(4),
    EXPANDING_LEVEL_4(5),
    IMPLODING(6),
    TOKENIZATION(7),
    DEFLATED(8),
    ENHANCED_DEFLATED(9),
    PKWARE_IMPLODING(10),
    BZIP2(12),
    LZMA(14),
    ZSTD_DEPRECATED(20),
    ZSTD(93),
    XZ(95),
    JPEG(96),
    WAVPACK(97),
    PPMD(98),
    AES_ENCRYPTED(99),
    UNKNOWN;

    static final int UNKNOWN_CODE = -1;
    private static final Map<Integer, ZipMethod> codeToEnum = Collections.unmodifiableMap((Map) Stream.of((Object[]) values()).collect(Collectors.toMap(new Function() { // from class: org.apache.commons.compress.archivers.zip.ZipMethod$$ExternalSyntheticLambda0
        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return Integer.valueOf(((ZipMethod) obj).getCode());
        }
    }, Function.identity())));
    private final int code;

    public static ZipMethod getMethodByCode(int code) {
        return codeToEnum.get(Integer.valueOf(code));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isZstd(int method) {
        return method == ZSTD.getCode() || method == ZSTD_DEPRECATED.getCode();
    }

    ZipMethod() {
        this(-1);
    }

    ZipMethod(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
