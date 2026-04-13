package org.apache.commons.compress.archivers.sevenz;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Stream;
import org.apache.commons.compress.utils.ByteUtils;

/* loaded from: classes9.dex */
abstract class AbstractCoder {
    private final Class<?>[] optionClasses;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract InputStream decode(String str, InputStream inputStream, long j, Coder coder, byte[] bArr, int i) throws IOException;

    /* JADX INFO: Access modifiers changed from: protected */
    public static int toInt(Object options, int defaultValue) {
        return options instanceof Number ? ((Number) options).intValue() : defaultValue;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractCoder(Class<?>... optionClasses) {
        this.optionClasses = (Class[]) Objects.requireNonNull(optionClasses, "optionClasses");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public OutputStream encode(OutputStream out, Object options) throws IOException {
        throw new UnsupportedOperationException("Method doesn't support writing");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] getOptionsAsProperties(Object options) throws IOException {
        return ByteUtils.EMPTY_BYTE_ARRAY;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Object getOptionsFromCoder(Coder coder, InputStream in) throws IOException {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isOptionInstance(final Object opts) {
        return Stream.of((Object[]) this.optionClasses).anyMatch(new Predicate() { // from class: org.apache.commons.compress.archivers.sevenz.AbstractCoder$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean isInstance;
                isInstance = ((Class) obj).isInstance(opts);
                return isInstance;
            }
        });
    }
}
