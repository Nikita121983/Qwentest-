package org.apache.commons.lang3.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.apache.commons.lang3.concurrent.AbstractConcurrentInitializer;
import org.apache.commons.lang3.function.FailableConsumer;
import org.apache.commons.lang3.function.FailableSupplier;

/* loaded from: classes9.dex */
public class BackgroundInitializer<T> extends AbstractConcurrentInitializer<T, Exception> {
    private ExecutorService executor;
    private ExecutorService externalExecutor;
    private Future<T> future;

    /* loaded from: classes9.dex */
    public static class Builder<I extends BackgroundInitializer<T>, T> extends AbstractConcurrentInitializer.AbstractBuilder<I, T, Builder<I, T>, Exception> {
        private ExecutorService externalExecutor;

        @Override // org.apache.commons.lang3.function.FailableSupplier
        public I get() {
            return (I) new BackgroundInitializer(getInitializer(), getCloser(), this.externalExecutor);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public Builder<I, T> setExternalExecutor(ExecutorService externalExecutor) {
            this.externalExecutor = externalExecutor;
            return (Builder) asThis();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public final class InitializationTask implements Callable<T> {
        private final ExecutorService execFinally;

        InitializationTask(ExecutorService exec) {
            this.execFinally = exec;
        }

        @Override // java.util.concurrent.Callable
        public T call() throws Exception {
            try {
                return BackgroundInitializer.this.initialize();
            } finally {
                if (this.execFinally != null) {
                    this.execFinally.shutdown();
                }
            }
        }
    }

    public static <T> Builder<BackgroundInitializer<T>, T> builder() {
        return new Builder<>();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BackgroundInitializer() {
        this(null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BackgroundInitializer(ExecutorService exec) {
        setExternalExecutor(exec);
    }

    private BackgroundInitializer(FailableSupplier<T, ConcurrentException> initializer, FailableConsumer<T, ConcurrentException> closer, ExecutorService exec) {
        super(initializer, closer);
        setExternalExecutor(exec);
    }

    private ExecutorService createExecutor() {
        return Executors.newFixedThreadPool(getTaskCount());
    }

    private Callable<T> createTask(ExecutorService execDestroy) {
        return new InitializationTask(execDestroy);
    }

    @Override // org.apache.commons.lang3.function.FailableSupplier
    public T get() throws ConcurrentException {
        try {
            return getFuture().get();
        } catch (InterruptedException iex) {
            Thread.currentThread().interrupt();
            throw new ConcurrentException(iex);
        } catch (ExecutionException execex) {
            ConcurrentUtils.handleCause(execex);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final synchronized ExecutorService getActiveExecutor() {
        return this.executor;
    }

    public final synchronized ExecutorService getExternalExecutor() {
        return this.externalExecutor;
    }

    public synchronized Future<T> getFuture() {
        if (this.future == null) {
            throw new IllegalStateException("start() must be called first!");
        }
        return this.future;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getTaskCount() {
        return 1;
    }

    @Override // org.apache.commons.lang3.concurrent.AbstractConcurrentInitializer
    protected Exception getTypedException(Exception e) {
        return new Exception(e);
    }

    @Override // org.apache.commons.lang3.concurrent.AbstractConcurrentInitializer
    public synchronized boolean isInitialized() {
        if (this.future == null || !this.future.isDone()) {
            return false;
        }
        try {
            this.future.get();
            return true;
        } catch (InterruptedException | CancellationException | ExecutionException e) {
            return false;
        }
    }

    public synchronized boolean isStarted() {
        return this.future != null;
    }

    public final synchronized void setExternalExecutor(ExecutorService externalExecutor) {
        if (isStarted()) {
            throw new IllegalStateException("Cannot set ExecutorService after start()!");
        }
        this.externalExecutor = externalExecutor;
    }

    public synchronized boolean start() {
        ExecutorService tempExec;
        if (isStarted()) {
            return false;
        }
        this.executor = getExternalExecutor();
        if (this.executor == null) {
            ExecutorService createExecutor = createExecutor();
            tempExec = createExecutor;
            this.executor = createExecutor;
        } else {
            tempExec = null;
        }
        this.future = this.executor.submit(createTask(tempExec));
        return true;
    }
}
