package org.apache.commons.io.function;

import java.util.Objects;
import java.util.stream.BaseStream;
import org.apache.commons.io.function.IOBaseStream;

/* loaded from: classes9.dex */
abstract class IOBaseStreamAdapter<T, S extends IOBaseStream<T, S, B>, B extends BaseStream<T, B>> implements IOBaseStream<T, S, B> {
    private final B delegate;

    /* JADX INFO: Access modifiers changed from: package-private */
    public IOBaseStreamAdapter(B delegate) {
        this.delegate = (B) Objects.requireNonNull(delegate, "delegate");
    }

    @Override // org.apache.commons.io.function.IOBaseStream
    public B unwrap() {
        return this.delegate;
    }
}
