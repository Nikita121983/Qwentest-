package org.apache.commons.io;

import java.io.File;
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

/* loaded from: classes9.dex */
public class FileCleaningTracker {
    volatile boolean exitWhenFinished;
    Thread reaper;
    ReferenceQueue<Object> q = new ReferenceQueue<>();
    final Collection<Tracker> trackers = Collections.synchronizedSet(new HashSet());
    final List<String> deleteFailures = Collections.synchronizedList(new ArrayList());

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public final class Reaper extends Thread {
        Reaper() {
            super("File Reaper");
            setPriority(10);
            setDaemon(true);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (true) {
                if (!FileCleaningTracker.this.exitWhenFinished || !FileCleaningTracker.this.trackers.isEmpty()) {
                    try {
                        Tracker tracker = (Tracker) FileCleaningTracker.this.q.remove();
                        FileCleaningTracker.this.trackers.remove(tracker);
                        if (!tracker.delete()) {
                            FileCleaningTracker.this.deleteFailures.add(tracker.getPath());
                        }
                        tracker.clear();
                    } catch (InterruptedException e) {
                    }
                } else {
                    return;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class Tracker extends PhantomReference<Object> {
        private final FileDeleteStrategy deleteStrategy;
        private final String path;

        Tracker(String path, FileDeleteStrategy deleteStrategy, Object marker, ReferenceQueue<? super Object> queue) {
            super(marker, queue);
            this.path = path;
            this.deleteStrategy = deleteStrategy == null ? FileDeleteStrategy.NORMAL : deleteStrategy;
        }

        public boolean delete() {
            return this.deleteStrategy.deleteQuietly(new File(this.path));
        }

        public String getPath() {
            return this.path;
        }
    }

    private synchronized void addTracker(String path, Object marker, FileDeleteStrategy deleteStrategy) {
        if (this.exitWhenFinished) {
            throw new IllegalStateException("No new trackers can be added once exitWhenFinished() is called");
        }
        if (this.reaper == null) {
            this.reaper = new Reaper();
            this.reaper.start();
        }
        this.trackers.add(new Tracker(path, deleteStrategy, marker, this.q));
    }

    public synchronized void exitWhenFinished() {
        this.exitWhenFinished = true;
        if (this.reaper != null) {
            synchronized (this.reaper) {
                try {
                    this.reaper.interrupt();
                } finally {
                    th = th;
                    while (true) {
                        try {
                            break;
                        } catch (Throwable th) {
                            th = th;
                        }
                    }
                }
            }
        }
    }

    public List<String> getDeleteFailures() {
        return new ArrayList(this.deleteFailures);
    }

    public int getTrackCount() {
        return this.trackers.size();
    }

    public void track(File file, Object marker) {
        track(file, marker, (FileDeleteStrategy) null);
    }

    public void track(File file, Object marker, FileDeleteStrategy deleteStrategy) {
        Objects.requireNonNull(file, "file");
        addTracker(file.getPath(), marker, deleteStrategy);
    }

    public void track(Path file, Object marker) {
        track(file, marker, (FileDeleteStrategy) null);
    }

    public void track(Path file, Object marker, FileDeleteStrategy deleteStrategy) {
        Objects.requireNonNull(file, "file");
        addTracker(file.toAbsolutePath().toString(), marker, deleteStrategy);
    }

    public void track(String path, Object marker) {
        track(path, marker, (FileDeleteStrategy) null);
    }

    public void track(String path, Object marker, FileDeleteStrategy deleteStrategy) {
        Objects.requireNonNull(path, "path");
        addTracker(path, marker, deleteStrategy);
    }
}
