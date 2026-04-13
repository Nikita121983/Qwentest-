package org.apache.commons.lang3.concurrent;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

/* loaded from: classes9.dex */
public class MultiBackgroundInitializer extends BackgroundInitializer<MultiBackgroundInitializerResults> {
    private final Map<String, BackgroundInitializer<?>> childInitializers;

    /* loaded from: classes9.dex */
    public static class MultiBackgroundInitializerResults {
        private final Map<String, ConcurrentException> exceptions;
        private final Map<String, BackgroundInitializer<?>> initializers;
        private final Map<String, Object> resultObjects;

        private MultiBackgroundInitializerResults(Map<String, BackgroundInitializer<?>> inits, Map<String, Object> results, Map<String, ConcurrentException> excepts) {
            this.initializers = inits;
            this.resultObjects = results;
            this.exceptions = excepts;
        }

        private BackgroundInitializer<?> checkName(String name) {
            BackgroundInitializer<?> init = this.initializers.get(name);
            if (init == null) {
                throw new NoSuchElementException("No child initializer with name " + name);
            }
            return init;
        }

        public ConcurrentException getException(String name) {
            checkName(name);
            return this.exceptions.get(name);
        }

        public BackgroundInitializer<?> getInitializer(String name) {
            return checkName(name);
        }

        public Object getResultObject(String name) {
            checkName(name);
            return this.resultObjects.get(name);
        }

        public Set<String> initializerNames() {
            return Collections.unmodifiableSet(this.initializers.keySet());
        }

        public boolean isException(String name) {
            checkName(name);
            return this.exceptions.containsKey(name);
        }

        public boolean isSuccessful() {
            return this.exceptions.isEmpty();
        }
    }

    public MultiBackgroundInitializer() {
        this.childInitializers = new HashMap();
    }

    public MultiBackgroundInitializer(ExecutorService exec) {
        super(exec);
        this.childInitializers = new HashMap();
    }

    public void addInitializer(String name, BackgroundInitializer<?> backgroundInitializer) {
        Objects.requireNonNull(name, "name");
        Objects.requireNonNull(backgroundInitializer, "backgroundInitializer");
        synchronized (this) {
            if (isStarted()) {
                throw new IllegalStateException("addInitializer() must not be called after start()!");
            }
            this.childInitializers.put(name, backgroundInitializer);
        }
    }

    @Override // org.apache.commons.lang3.concurrent.AbstractConcurrentInitializer
    public void close() throws ConcurrentException {
        ConcurrentException exception = null;
        for (BackgroundInitializer<?> child : this.childInitializers.values()) {
            try {
                child.close();
            } catch (Exception e) {
                if (exception == null) {
                    exception = new ConcurrentException();
                }
                if (e instanceof ConcurrentException) {
                    exception.addSuppressed(e.getCause());
                } else {
                    exception.addSuppressed(e);
                }
            }
        }
        if (exception != null) {
            throw exception;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.lang3.concurrent.BackgroundInitializer
    public int getTaskCount() {
        return this.childInitializers.values().stream().mapToInt(new ToIntFunction() { // from class: org.apache.commons.lang3.concurrent.MultiBackgroundInitializer$$ExternalSyntheticLambda2
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((BackgroundInitializer) obj).getTaskCount();
            }
        }).sum() + 1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.lang3.concurrent.AbstractConcurrentInitializer
    public MultiBackgroundInitializerResults initialize() throws Exception {
        Map<String, BackgroundInitializer<?>> inits;
        synchronized (this) {
            inits = new HashMap<>(this.childInitializers);
        }
        final ExecutorService exec = getActiveExecutor();
        inits.values().forEach(new Consumer() { // from class: org.apache.commons.lang3.concurrent.MultiBackgroundInitializer$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                MultiBackgroundInitializer.lambda$initialize$0(exec, (BackgroundInitializer) obj);
            }
        });
        final Map<String, Object> results = new HashMap<>();
        final Map<String, ConcurrentException> excepts = new HashMap<>();
        inits.forEach(new BiConsumer() { // from class: org.apache.commons.lang3.concurrent.MultiBackgroundInitializer$$ExternalSyntheticLambda1
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                MultiBackgroundInitializer.lambda$initialize$1(results, excepts, (String) obj, (BackgroundInitializer) obj2);
            }
        });
        return new MultiBackgroundInitializerResults(inits, results, excepts);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$initialize$0(ExecutorService exec, BackgroundInitializer bi) {
        if (bi.getExternalExecutor() == null) {
            bi.setExternalExecutor(exec);
        }
        bi.start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$initialize$1(Map results, Map excepts, String k, BackgroundInitializer v) {
        try {
            results.put(k, v.get());
        } catch (ConcurrentException cex) {
            excepts.put(k, cex);
        }
    }

    @Override // org.apache.commons.lang3.concurrent.BackgroundInitializer, org.apache.commons.lang3.concurrent.AbstractConcurrentInitializer
    public boolean isInitialized() {
        if (this.childInitializers.isEmpty()) {
            return false;
        }
        return this.childInitializers.values().stream().allMatch(new Predicate() { // from class: org.apache.commons.lang3.concurrent.MultiBackgroundInitializer$$ExternalSyntheticLambda3
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((BackgroundInitializer) obj).isInitialized();
            }
        });
    }
}
