package org.apache.commons.compress.archivers.zip;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Stream;
import org.apache.commons.io.Charsets;

/* loaded from: classes9.dex */
public abstract class ZipEncodingHelper {
    static final ZipEncoding ZIP_ENCODING_UTF_8 = getZipEncoding(StandardCharsets.UTF_8);

    public static ZipEncoding getZipEncoding(Charset charset) {
        return new NioZipEncoding(Charsets.toCharset(charset));
    }

    public static ZipEncoding getZipEncoding(String name) {
        return new NioZipEncoding(toSafeCharset(name));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ByteBuffer growBufferBy(ByteBuffer buffer, int increment) {
        buffer.limit(buffer.position());
        buffer.rewind();
        ByteBuffer on = ByteBuffer.allocate(buffer.capacity() + increment);
        on.put(buffer);
        return on;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isUTF8(Charset charset) {
        return isUTF8Alias(Charsets.toCharset(charset).name());
    }

    private static boolean isUTF8Alias(final String name) {
        if (!StandardCharsets.UTF_8.name().equalsIgnoreCase(name)) {
            Stream<String> stream = StandardCharsets.UTF_8.aliases().stream();
            Objects.requireNonNull(name);
            if (!stream.anyMatch(new Predicate() { // from class: org.apache.commons.compress.archivers.zip.ZipEncodingHelper$$ExternalSyntheticLambda0
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    boolean equalsIgnoreCase;
                    equalsIgnoreCase = name.equalsIgnoreCase((String) obj);
                    return equalsIgnoreCase;
                }
            })) {
                return false;
            }
        }
        return true;
    }

    private static Charset toSafeCharset(String name) {
        try {
            return Charsets.toCharset(name);
        } catch (IllegalArgumentException | NullPointerException e) {
            return Charset.defaultCharset();
        }
    }
}
