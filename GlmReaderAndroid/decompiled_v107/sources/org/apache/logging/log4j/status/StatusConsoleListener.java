package org.apache.logging.log4j.status;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.logging.log4j.Level;

/* loaded from: classes10.dex */
public class StatusConsoleListener implements StatusListener {
    private final Level initialLevel;
    private final PrintStream initialStream;
    private volatile Level level;
    private final Lock lock;
    private volatile PrintStream stream;

    public StatusConsoleListener(final Level level) {
        this(level, System.out);
    }

    public StatusConsoleListener(final Level level, final PrintStream stream) {
        this.lock = new ReentrantLock();
        Level level2 = (Level) Objects.requireNonNull(level, "level");
        this.level = level2;
        this.initialLevel = level2;
        PrintStream printStream = (PrintStream) Objects.requireNonNull(stream, "stream");
        this.stream = printStream;
        this.initialStream = printStream;
    }

    public void setLevel(final Level level) {
        Objects.requireNonNull(level, "level");
        if (!this.level.equals(level)) {
            this.lock.lock();
            try {
                this.level = level;
            } finally {
                this.lock.unlock();
            }
        }
    }

    public void setStream(final PrintStream stream) {
        Objects.requireNonNull(stream, "stream");
        if (this.stream != stream) {
            OutputStream oldStream = null;
            this.lock.lock();
            try {
                if (this.stream != stream) {
                    oldStream = this.stream;
                    this.stream = stream;
                }
                if (oldStream != null) {
                    closeNonSystemStream(oldStream);
                }
            } finally {
                this.lock.unlock();
            }
        }
    }

    @Override // org.apache.logging.log4j.status.StatusListener
    public Level getStatusLevel() {
        return this.level;
    }

    @Override // org.apache.logging.log4j.status.StatusListener
    public void log(final StatusData data) {
        Objects.requireNonNull(data, "data");
        String formattedStatus = data.getFormattedStatus();
        this.stream.println(formattedStatus);
    }

    @Deprecated
    public void setFilters(final String... filters) {
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.lock.lock();
        try {
            OutputStream oldStream = this.stream;
            this.stream = this.initialStream;
            this.level = this.initialLevel;
            this.lock.unlock();
            closeNonSystemStream(oldStream);
        } catch (Throwable th) {
            this.lock.unlock();
            throw th;
        }
    }

    private static void closeNonSystemStream(final OutputStream stream) {
        if (stream != System.out && stream != System.err) {
            try {
                stream.close();
            } catch (IOException error) {
                error.printStackTrace(System.err);
            }
        }
    }
}
