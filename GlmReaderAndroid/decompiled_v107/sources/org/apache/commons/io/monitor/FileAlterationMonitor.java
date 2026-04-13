package org.apache.commons.io.monitor;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadFactory;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;
import org.apache.commons.io.ThreadUtils;

/* loaded from: classes9.dex */
public final class FileAlterationMonitor implements Runnable {
    private static final FileAlterationObserver[] EMPTY_ARRAY = new FileAlterationObserver[0];
    private final long intervalMillis;
    private final List<FileAlterationObserver> observers;
    private volatile boolean running;
    private Thread thread;
    private ThreadFactory threadFactory;

    public FileAlterationMonitor() {
        this(10000L);
    }

    public FileAlterationMonitor(long intervalMillis) {
        this.observers = new CopyOnWriteArrayList();
        this.intervalMillis = intervalMillis;
    }

    public FileAlterationMonitor(long interval, Collection<FileAlterationObserver> observers) {
        this(interval, (FileAlterationObserver[]) ((Collection) Optional.ofNullable(observers).orElse(Collections.emptyList())).toArray(EMPTY_ARRAY));
    }

    public FileAlterationMonitor(long interval, FileAlterationObserver... observers) {
        this(interval);
        if (observers != null) {
            Stream.of((Object[]) observers).forEach(new Consumer() { // from class: org.apache.commons.io.monitor.FileAlterationMonitor$$ExternalSyntheticLambda1
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    FileAlterationMonitor.this.addObserver((FileAlterationObserver) obj);
                }
            });
        }
    }

    public void addObserver(FileAlterationObserver observer) {
        if (observer != null) {
            this.observers.add(observer);
        }
    }

    public long getInterval() {
        return this.intervalMillis;
    }

    public Iterable<FileAlterationObserver> getObservers() {
        return new ArrayList(this.observers);
    }

    public void removeObserver(final FileAlterationObserver observer) {
        if (observer != null) {
            List<FileAlterationObserver> list = this.observers;
            Objects.requireNonNull(observer);
            list.removeIf(new Predicate() { // from class: org.apache.commons.io.monitor.FileAlterationMonitor$$ExternalSyntheticLambda0
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    boolean equals;
                    equals = FileAlterationObserver.this.equals((FileAlterationObserver) obj);
                    return equals;
                }
            });
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        while (this.running) {
            this.observers.forEach(new Consumer() { // from class: org.apache.commons.io.monitor.FileAlterationMonitor$$ExternalSyntheticLambda2
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((FileAlterationObserver) obj).checkAndNotify();
                }
            });
            if (this.running) {
                try {
                    ThreadUtils.sleep(Duration.ofMillis(this.intervalMillis));
                } catch (InterruptedException e) {
                }
            } else {
                return;
            }
        }
    }

    public synchronized void setThreadFactory(ThreadFactory threadFactory) {
        this.threadFactory = threadFactory;
    }

    public synchronized void start() throws Exception {
        if (this.running) {
            throw new IllegalStateException("Monitor is already running");
        }
        for (FileAlterationObserver observer : this.observers) {
            observer.initialize();
        }
        this.running = true;
        if (this.threadFactory != null) {
            this.thread = this.threadFactory.newThread(this);
        } else {
            this.thread = new Thread(this);
        }
        this.thread.start();
    }

    public synchronized void stop() throws Exception {
        stop(this.intervalMillis);
    }

    public synchronized void stop(long stopInterval) throws Exception {
        if (!this.running) {
            throw new IllegalStateException("Monitor is not running");
        }
        this.running = false;
        try {
            this.thread.interrupt();
            this.thread.join(stopInterval);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        for (FileAlterationObserver observer : this.observers) {
            observer.destroy();
        }
    }
}
