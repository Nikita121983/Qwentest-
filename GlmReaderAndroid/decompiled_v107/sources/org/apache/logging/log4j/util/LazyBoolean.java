package org.apache.logging.log4j.util;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BooleanSupplier;

/* loaded from: classes10.dex */
public class LazyBoolean implements BooleanSupplier {
    private volatile boolean initialized;
    private final Lock lock = new ReentrantLock();
    private final BooleanSupplier supplier;
    private volatile boolean value;

    public LazyBoolean(final BooleanSupplier supplier) {
        this.supplier = supplier;
    }

    @Override // java.util.function.BooleanSupplier
    public boolean getAsBoolean() {
        boolean uninitialized = !this.initialized;
        boolean value = this.value;
        if (uninitialized) {
            this.lock.lock();
            try {
                boolean uninitialized2 = !this.initialized;
                if (uninitialized2) {
                    boolean asBoolean = this.supplier.getAsBoolean();
                    value = asBoolean;
                    this.value = asBoolean;
                    this.initialized = true;
                }
            } finally {
                this.lock.unlock();
            }
        }
        return value;
    }

    public void setAsBoolean(final boolean b) {
        this.lock.lock();
        try {
            this.initialized = false;
            this.value = b;
            this.initialized = true;
        } finally {
            this.lock.unlock();
        }
    }

    public void reset() {
        this.initialized = false;
    }
}
