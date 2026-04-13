package org.apache.commons.lang3.concurrent.locks;

import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;
import java.util.function.Supplier;
import org.apache.commons.lang3.builder.AbstractSupplier;
import org.apache.commons.lang3.concurrent.locks.LockingVisitors;
import org.apache.commons.lang3.function.Failable;
import org.apache.commons.lang3.function.FailableConsumer;
import org.apache.commons.lang3.function.FailableFunction;
import org.apache.commons.lang3.function.Suppliers;

/* loaded from: classes9.dex */
public class LockingVisitors {

    /* loaded from: classes9.dex */
    public static class LockVisitor<O, L> {
        private final L lock;
        private final O object;
        private final Supplier<Lock> readLockSupplier;
        private final Supplier<Lock> writeLockSupplier;

        /* loaded from: classes9.dex */
        public static class LVBuilder<O, L, B extends LVBuilder<O, L, B>> extends AbstractSupplier<LockVisitor<O, L>, B, RuntimeException> {
            L lock;
            O object;
            private Supplier<Lock> readLockSupplier;
            private Supplier<Lock> writeLockSupplier;

            @Override // org.apache.commons.lang3.function.FailableSupplier
            public LockVisitor<O, L> get() {
                return new LockVisitor<>(this);
            }

            Supplier<Lock> getReadLockSupplier() {
                return this.readLockSupplier;
            }

            Supplier<Lock> getWriteLockSupplier() {
                return this.writeLockSupplier;
            }

            public B setLock(L lock) {
                this.lock = lock;
                return (B) asThis();
            }

            public B setObject(O object) {
                this.object = object;
                return (B) asThis();
            }

            public B setReadLockSupplier(Supplier<Lock> readLockSupplier) {
                this.readLockSupplier = readLockSupplier;
                return (B) asThis();
            }

            public B setWriteLockSupplier(Supplier<Lock> writeLockSupplier) {
                this.writeLockSupplier = writeLockSupplier;
                return (B) asThis();
            }
        }

        private LockVisitor(LVBuilder<O, L, ?> lVBuilder) {
            this.object = (O) Objects.requireNonNull(lVBuilder.object, "object");
            this.lock = (L) Objects.requireNonNull(lVBuilder.lock, "lock");
            this.readLockSupplier = (Supplier) Objects.requireNonNull(((LVBuilder) lVBuilder).readLockSupplier, "readLockSupplier");
            this.writeLockSupplier = (Supplier) Objects.requireNonNull(((LVBuilder) lVBuilder).writeLockSupplier, "writeLockSupplier");
        }

        protected LockVisitor(O o, L l, Supplier<Lock> supplier, Supplier<Lock> supplier2) {
            this.object = (O) Objects.requireNonNull(o, "object");
            this.lock = (L) Objects.requireNonNull(l, "lock");
            this.readLockSupplier = (Supplier) Objects.requireNonNull(supplier, "readLockSupplier");
            this.writeLockSupplier = (Supplier) Objects.requireNonNull(supplier2, "writeLockSupplier");
        }

        public void acceptReadLocked(FailableConsumer<O, ?> consumer) {
            lockAcceptUnlock(this.readLockSupplier, consumer);
        }

        public void acceptWriteLocked(FailableConsumer<O, ?> consumer) {
            lockAcceptUnlock(this.writeLockSupplier, consumer);
        }

        public <T> T applyReadLocked(FailableFunction<O, T, ?> failableFunction) {
            return (T) lockApplyUnlock(this.readLockSupplier, failableFunction);
        }

        public <T> T applyWriteLocked(FailableFunction<O, T, ?> failableFunction) {
            return (T) lockApplyUnlock(this.writeLockSupplier, failableFunction);
        }

        public L getLock() {
            return this.lock;
        }

        public O getObject() {
            return this.object;
        }

        protected void lockAcceptUnlock(Supplier<Lock> lockSupplier, FailableConsumer<O, ?> consumer) {
            Lock lock = (Lock) Objects.requireNonNull((Lock) Suppliers.get(lockSupplier), "lock");
            lock.lock();
            try {
                Failable.accept((FailableConsumer<O, E>) consumer, this.object);
            } finally {
                lock.unlock();
            }
        }

        protected <T> T lockApplyUnlock(Supplier<Lock> supplier, FailableFunction<O, T, ?> failableFunction) {
            Lock lock = (Lock) Objects.requireNonNull((Lock) Suppliers.get(supplier), "lock");
            lock.lock();
            try {
                return (T) Failable.apply(failableFunction, this.object);
            } finally {
                lock.unlock();
            }
        }
    }

    /* loaded from: classes9.dex */
    public static class ReadWriteLockVisitor<O> extends LockVisitor<O, ReadWriteLock> {

        /* loaded from: classes9.dex */
        public static class Builder<O> extends LockVisitor.LVBuilder<O, ReadWriteLock, Builder<O>> {
            @Override // org.apache.commons.lang3.concurrent.locks.LockingVisitors.LockVisitor.LVBuilder, org.apache.commons.lang3.function.FailableSupplier
            public ReadWriteLockVisitor<O> get() {
                return new ReadWriteLockVisitor<>(this);
            }

            @Override // org.apache.commons.lang3.concurrent.locks.LockingVisitors.LockVisitor.LVBuilder
            public Builder<O> setLock(ReadWriteLock readWriteLock) {
                Objects.requireNonNull(readWriteLock);
                setReadLockSupplier(new LockingVisitors$ReadWriteLockVisitor$$ExternalSyntheticLambda0(readWriteLock));
                Objects.requireNonNull(readWriteLock);
                setWriteLockSupplier(new LockingVisitors$ReadWriteLockVisitor$$ExternalSyntheticLambda1(readWriteLock));
                return (Builder) super.setLock((Builder<O>) readWriteLock);
            }
        }

        public static <O> Builder<O> builder() {
            return new Builder<>();
        }

        private ReadWriteLockVisitor(Builder<O> builder) {
            super(builder);
        }

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        protected ReadWriteLockVisitor(O r3, java.util.concurrent.locks.ReadWriteLock r4) {
            /*
                r2 = this;
                java.util.Objects.requireNonNull(r4)
                org.apache.commons.lang3.concurrent.locks.LockingVisitors$ReadWriteLockVisitor$$ExternalSyntheticLambda0 r0 = new org.apache.commons.lang3.concurrent.locks.LockingVisitors$ReadWriteLockVisitor$$ExternalSyntheticLambda0
                r0.<init>(r4)
                java.util.Objects.requireNonNull(r4)
                org.apache.commons.lang3.concurrent.locks.LockingVisitors$ReadWriteLockVisitor$$ExternalSyntheticLambda1 r1 = new org.apache.commons.lang3.concurrent.locks.LockingVisitors$ReadWriteLockVisitor$$ExternalSyntheticLambda1
                r1.<init>(r4)
                r2.<init>(r3, r4, r0, r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.concurrent.locks.LockingVisitors.ReadWriteLockVisitor.<init>(java.lang.Object, java.util.concurrent.locks.ReadWriteLock):void");
        }
    }

    /* loaded from: classes9.dex */
    public static class ReentrantLockVisitor<O> extends LockVisitor<O, ReentrantLock> {

        /* loaded from: classes9.dex */
        public static class Builder<O> extends LockVisitor.LVBuilder<O, ReentrantLock, Builder<O>> {
            @Override // org.apache.commons.lang3.concurrent.locks.LockingVisitors.LockVisitor.LVBuilder, org.apache.commons.lang3.function.FailableSupplier
            public ReentrantLockVisitor<O> get() {
                return new ReentrantLockVisitor<>(this);
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            public static /* synthetic */ Lock lambda$setLock$0(ReentrantLock reentrantLock) {
                return reentrantLock;
            }

            @Override // org.apache.commons.lang3.concurrent.locks.LockingVisitors.LockVisitor.LVBuilder
            public Builder<O> setLock(final ReentrantLock reentrantLock) {
                setReadLockSupplier(new Supplier() { // from class: org.apache.commons.lang3.concurrent.locks.LockingVisitors$ReentrantLockVisitor$Builder$$ExternalSyntheticLambda0
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return LockingVisitors.ReentrantLockVisitor.Builder.lambda$setLock$0(reentrantLock);
                    }
                });
                setWriteLockSupplier(new Supplier() { // from class: org.apache.commons.lang3.concurrent.locks.LockingVisitors$ReentrantLockVisitor$Builder$$ExternalSyntheticLambda1
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return LockingVisitors.ReentrantLockVisitor.Builder.lambda$setLock$1(reentrantLock);
                    }
                });
                return (Builder) super.setLock((Builder<O>) reentrantLock);
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            public static /* synthetic */ Lock lambda$setLock$1(ReentrantLock reentrantLock) {
                return reentrantLock;
            }
        }

        public static <O> Builder<O> builder() {
            return new Builder<>();
        }

        private ReentrantLockVisitor(Builder<O> builder) {
            super(builder);
        }

        protected ReentrantLockVisitor(O object, final ReentrantLock reentrantLock) {
            super(object, reentrantLock, new Supplier() { // from class: org.apache.commons.lang3.concurrent.locks.LockingVisitors$ReentrantLockVisitor$$ExternalSyntheticLambda0
                @Override // java.util.function.Supplier
                public final Object get() {
                    return LockingVisitors.ReentrantLockVisitor.lambda$new$0(reentrantLock);
                }
            }, new Supplier() { // from class: org.apache.commons.lang3.concurrent.locks.LockingVisitors$ReentrantLockVisitor$$ExternalSyntheticLambda1
                @Override // java.util.function.Supplier
                public final Object get() {
                    return LockingVisitors.ReentrantLockVisitor.lambda$new$1(reentrantLock);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ Lock lambda$new$0(ReentrantLock reentrantLock) {
            return reentrantLock;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ Lock lambda$new$1(ReentrantLock reentrantLock) {
            return reentrantLock;
        }
    }

    /* loaded from: classes9.dex */
    public static class StampedLockVisitor<O> extends LockVisitor<O, StampedLock> {

        /* loaded from: classes9.dex */
        public static class Builder<O> extends LockVisitor.LVBuilder<O, StampedLock, Builder<O>> {
            @Override // org.apache.commons.lang3.concurrent.locks.LockingVisitors.LockVisitor.LVBuilder, org.apache.commons.lang3.function.FailableSupplier
            public StampedLockVisitor<O> get() {
                return new StampedLockVisitor<>(this);
            }

            @Override // org.apache.commons.lang3.concurrent.locks.LockingVisitors.LockVisitor.LVBuilder
            public Builder<O> setLock(final StampedLock stampedLock) {
                Objects.requireNonNull(stampedLock);
                setReadLockSupplier(new Supplier() { // from class: org.apache.commons.lang3.concurrent.locks.LockingVisitors$StampedLockVisitor$Builder$$ExternalSyntheticLambda0
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        Lock asReadLock;
                        asReadLock = stampedLock.asReadLock();
                        return asReadLock;
                    }
                });
                Objects.requireNonNull(stampedLock);
                setWriteLockSupplier(new Supplier() { // from class: org.apache.commons.lang3.concurrent.locks.LockingVisitors$StampedLockVisitor$Builder$$ExternalSyntheticLambda1
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        Lock asWriteLock;
                        asWriteLock = stampedLock.asWriteLock();
                        return asWriteLock;
                    }
                });
                return (Builder) super.setLock((Builder<O>) stampedLock);
            }
        }

        public static <O> Builder<O> builder() {
            return new Builder<>();
        }

        private StampedLockVisitor(Builder<O> builder) {
            super(builder);
        }

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        protected StampedLockVisitor(O r3, final java.util.concurrent.locks.StampedLock r4) {
            /*
                r2 = this;
                java.util.Objects.requireNonNull(r4)
                org.apache.commons.lang3.concurrent.locks.LockingVisitors$StampedLockVisitor$$ExternalSyntheticLambda0 r0 = new org.apache.commons.lang3.concurrent.locks.LockingVisitors$StampedLockVisitor$$ExternalSyntheticLambda0
                r0.<init>()
                java.util.Objects.requireNonNull(r4)
                org.apache.commons.lang3.concurrent.locks.LockingVisitors$StampedLockVisitor$$ExternalSyntheticLambda1 r1 = new org.apache.commons.lang3.concurrent.locks.LockingVisitors$StampedLockVisitor$$ExternalSyntheticLambda1
                r1.<init>()
                r2.<init>(r3, r4, r0, r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.concurrent.locks.LockingVisitors.StampedLockVisitor.<init>(java.lang.Object, java.util.concurrent.locks.StampedLock):void");
        }
    }

    public static <O> ReadWriteLockVisitor<O> create(O object, ReadWriteLock readWriteLock) {
        return new ReadWriteLockVisitor<>(object, readWriteLock);
    }

    public static <O> ReentrantLockVisitor<O> create(O object, ReentrantLock reentrantLock) {
        return new ReentrantLockVisitor<>(object, reentrantLock);
    }

    public static <O> ReentrantLockVisitor<O> reentrantLockVisitor(O object) {
        return create(object, new ReentrantLock());
    }

    public static <O> ReadWriteLockVisitor<O> reentrantReadWriteLockVisitor(O object) {
        return create(object, new ReentrantReadWriteLock());
    }

    public static <O> StampedLockVisitor<O> stampedLockVisitor(O object) {
        return new StampedLockVisitor<>(object, new StampedLock());
    }

    @Deprecated
    public LockingVisitors() {
    }
}
