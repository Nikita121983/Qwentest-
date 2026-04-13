package org.apache.commons.io.input;

import java.io.InputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.build.AbstractStreamBuilder;
import org.apache.commons.io.output.QueueOutputStream;

/* loaded from: classes9.dex */
public class QueueInputStream extends InputStream {
    private final BlockingQueue<Integer> blockingQueue;
    private final long timeoutNanos;

    /* loaded from: classes9.dex */
    public static class Builder extends AbstractStreamBuilder<QueueInputStream, Builder> {
        private BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue();
        private Duration timeout = Duration.ZERO;

        @Override // org.apache.commons.io.function.IOSupplier
        public QueueInputStream get() {
            return new QueueInputStream(this);
        }

        public Builder setBlockingQueue(BlockingQueue<Integer> blockingQueue) {
            this.blockingQueue = blockingQueue != null ? blockingQueue : new LinkedBlockingQueue<>();
            return this;
        }

        public Builder setTimeout(Duration timeout) {
            if (timeout != null && timeout.toNanos() < 0) {
                throw new IllegalArgumentException("timeout must not be negative");
            }
            this.timeout = timeout != null ? timeout : Duration.ZERO;
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public QueueInputStream() {
        this(new LinkedBlockingQueue());
    }

    @Deprecated
    public QueueInputStream(BlockingQueue<Integer> blockingQueue) {
        this(builder().setBlockingQueue(blockingQueue));
    }

    private QueueInputStream(Builder builder) {
        this.blockingQueue = (BlockingQueue) Objects.requireNonNull(builder.blockingQueue, "blockingQueue");
        this.timeoutNanos = ((Duration) Objects.requireNonNull(builder.timeout, "timeout")).toNanos();
    }

    BlockingQueue<Integer> getBlockingQueue() {
        return this.blockingQueue;
    }

    Duration getTimeout() {
        return Duration.ofNanos(this.timeoutNanos);
    }

    public QueueOutputStream newQueueOutputStream() {
        return new QueueOutputStream(this.blockingQueue);
    }

    @Override // java.io.InputStream
    public int read() {
        try {
            Integer value = this.blockingQueue.poll(this.timeoutNanos, TimeUnit.NANOSECONDS);
            if (value == null) {
                return -1;
            }
            return value.intValue() & 255;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException(e);
        }
    }

    @Override // java.io.InputStream
    public int read(byte[] b, int offset, int length) {
        IOUtils.checkFromIndexSize(b, offset, length);
        if (length == 0) {
            return 0;
        }
        List<Integer> drain = new ArrayList<>(Math.min(length, this.blockingQueue.size()));
        this.blockingQueue.drainTo(drain, length);
        if (drain.isEmpty()) {
            int value = read();
            if (value == -1) {
                return -1;
            }
            drain.add(Integer.valueOf(value));
            this.blockingQueue.drainTo(drain, length - 1);
        }
        int i = 0;
        Iterator<Integer> it = drain.iterator();
        while (it.hasNext()) {
            b[offset + i] = (byte) (it.next().intValue() & 255);
            i++;
        }
        return i;
    }
}
