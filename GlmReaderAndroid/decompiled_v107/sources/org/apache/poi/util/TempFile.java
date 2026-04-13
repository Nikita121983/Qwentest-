package org.apache.poi.util;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.function.Supplier;

/* loaded from: classes10.dex */
public final class TempFile {
    public static final String JAVA_IO_TMPDIR = "java.io.tmpdir";
    private static TempFileCreationStrategy strategy = new DefaultTempFileCreationStrategy();
    private static final ThreadLocal<TempFileCreationStrategy> threadLocalStrategy = new ThreadLocal<>();

    static {
        final ThreadLocal<TempFileCreationStrategy> threadLocal = threadLocalStrategy;
        threadLocal.getClass();
        ThreadLocalUtil.registerCleaner(new Runnable() { // from class: org.apache.poi.util.TempFile$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                threadLocal.remove();
            }
        });
    }

    private TempFile() {
    }

    public static void setTempFileCreationStrategy(TempFileCreationStrategy strategy2) {
        if (strategy2 == null) {
            throw new IllegalArgumentException("strategy == null");
        }
        strategy = strategy2;
    }

    public static void setThreadLocalTempFileCreationStrategy(TempFileCreationStrategy strategy2) {
        if (strategy2 == null) {
            threadLocalStrategy.remove();
        } else {
            threadLocalStrategy.set(strategy2);
        }
    }

    public static File createTempFile(String prefix, String suffix) throws IOException {
        return getStrategy().createTempFile(prefix, suffix);
    }

    public static File createTempDirectory(String name) throws IOException {
        return getStrategy().createTempDirectory(name);
    }

    public static <R> R withStrategy(TempFileCreationStrategy newStrategy, Supplier<? extends R> task) {
        Objects.requireNonNull(newStrategy, "newStrategy");
        Objects.requireNonNull(task, "task");
        TempFileCreationStrategy oldStrategy = threadLocalStrategy.get();
        try {
            threadLocalStrategy.set(newStrategy);
            return task.get();
        } finally {
            setThreadLocalTempFileCreationStrategy(oldStrategy);
        }
    }

    private static TempFileCreationStrategy getStrategy() {
        TempFileCreationStrategy s = threadLocalStrategy.get();
        if (s == null) {
            threadLocalStrategy.remove();
            return strategy;
        }
        return s;
    }
}
