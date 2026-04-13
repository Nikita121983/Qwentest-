package org.apache.commons.io.function;

import java.util.stream.Stream;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public final class IOStreamAdapter<T> extends IOBaseStreamAdapter<T, IOStream<T>, Stream<T>> implements IOStream<T> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> IOStream<T> adapt(Stream<T> delegate) {
        return delegate != null ? new IOStreamAdapter(delegate) : IOStream.empty();
    }

    private IOStreamAdapter(Stream<T> delegate) {
        super(delegate);
    }

    @Override // org.apache.commons.io.function.IOBaseStream
    public IOStream<T> wrap(Stream<T> delegate) {
        return unwrap() == delegate ? this : adapt(delegate);
    }
}
