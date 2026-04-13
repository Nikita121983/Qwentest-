package org.apache.commons.lang3.concurrent;

import java.lang.Thread;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes9.dex */
public class BasicThreadFactory implements ThreadFactory {
    private final Boolean daemon;
    private final String namingPattern;
    private final Integer priority;
    private final AtomicLong threadCounter;
    private final Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
    private final ThreadFactory wrappedFactory;

    /* loaded from: classes9.dex */
    public static class Builder implements org.apache.commons.lang3.builder.Builder<BasicThreadFactory> {
        private Boolean daemon;
        private Thread.UncaughtExceptionHandler exceptionHandler;
        private ThreadFactory factory;
        private String namingPattern;
        private Integer priority;

        @Deprecated
        public Builder() {
        }

        @Override // org.apache.commons.lang3.builder.Builder
        public BasicThreadFactory build() {
            BasicThreadFactory factory = new BasicThreadFactory(this);
            reset();
            return factory;
        }

        public Builder daemon() {
            return daemon(true);
        }

        public Builder daemon(boolean daemon) {
            this.daemon = Boolean.valueOf(daemon);
            return this;
        }

        public Builder namingPattern(String namingPattern) {
            this.namingPattern = (String) Objects.requireNonNull(namingPattern, "pattern");
            return this;
        }

        public Builder priority(int priority) {
            this.priority = Integer.valueOf(priority);
            return this;
        }

        public void reset() {
            this.factory = null;
            this.exceptionHandler = null;
            this.namingPattern = null;
            this.priority = null;
            this.daemon = null;
        }

        public Builder uncaughtExceptionHandler(Thread.UncaughtExceptionHandler exceptionHandler) {
            this.exceptionHandler = (Thread.UncaughtExceptionHandler) Objects.requireNonNull(exceptionHandler, "handler");
            return this;
        }

        public Builder wrappedFactory(ThreadFactory factory) {
            this.factory = (ThreadFactory) Objects.requireNonNull(factory, "factory");
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private BasicThreadFactory(Builder builder) {
        this.wrappedFactory = builder.factory != null ? builder.factory : Executors.defaultThreadFactory();
        this.namingPattern = builder.namingPattern;
        this.priority = builder.priority;
        this.daemon = builder.daemon;
        this.uncaughtExceptionHandler = builder.exceptionHandler;
        this.threadCounter = new AtomicLong();
    }

    public final Boolean getDaemonFlag() {
        return this.daemon;
    }

    public final String getNamingPattern() {
        return this.namingPattern;
    }

    public final Integer getPriority() {
        return this.priority;
    }

    public long getThreadCount() {
        return this.threadCounter.get();
    }

    public final Thread.UncaughtExceptionHandler getUncaughtExceptionHandler() {
        return this.uncaughtExceptionHandler;
    }

    public final ThreadFactory getWrappedFactory() {
        return this.wrappedFactory;
    }

    private void initializeThread(Thread thread) {
        if (getNamingPattern() != null) {
            Long count = Long.valueOf(this.threadCounter.incrementAndGet());
            thread.setName(String.format(getNamingPattern(), count));
        }
        if (getUncaughtExceptionHandler() != null) {
            thread.setUncaughtExceptionHandler(getUncaughtExceptionHandler());
        }
        if (getPriority() != null) {
            thread.setPriority(getPriority().intValue());
        }
        if (getDaemonFlag() != null) {
            thread.setDaemon(getDaemonFlag().booleanValue());
        }
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        Thread thread = getWrappedFactory().newThread(runnable);
        initializeThread(thread);
        return thread;
    }
}
