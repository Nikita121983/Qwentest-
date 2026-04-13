package org.apache.xmlbeans.impl.util;

/* loaded from: classes11.dex */
public class ExceptionUtil {
    private ExceptionUtil() {
    }

    public static boolean isFatal(Throwable throwable) {
        return (throwable instanceof VirtualMachineError) || (throwable instanceof ThreadDeath) || (throwable instanceof InterruptedException) || (throwable instanceof LinkageError);
    }

    public static void rethrow(Throwable throwable) {
        if (throwable instanceof Error) {
            throw ((Error) throwable);
        }
        if (throwable instanceof RuntimeException) {
            throw ((RuntimeException) throwable);
        }
        throw new RuntimeException(throwable);
    }
}
