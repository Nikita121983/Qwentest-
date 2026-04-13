package org.apache.commons.compress;

import java.io.IOException;
import java.util.function.Supplier;
import org.apache.commons.lang3.function.Suppliers;

/* loaded from: classes9.dex */
public class CompressException extends IOException {
    private static final long serialVersionUID = 1;

    /* JADX INFO: Access modifiers changed from: protected */
    public static <T, E extends Throwable> T requireNonNull(Class<? super E> cls, T obj, Supplier<String> messageSupplier) throws Throwable {
        if (obj == null) {
            try {
                cls.getConstructor(String.class).newInstance(Suppliers.get(messageSupplier));
            } catch (ReflectiveOperationException | SecurityException e) {
                new CompressException((String) Suppliers.get(messageSupplier), e);
            }
        }
        return obj;
    }

    public CompressException() {
    }

    public CompressException(String message) {
        super(message);
    }

    public CompressException(String message, Throwable cause) {
        super(message, cause);
    }

    public CompressException(Throwable cause) {
        super(cause);
    }
}
