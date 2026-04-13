package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.function.IOIntConsumer;
import org.apache.commons.io.input.ProxyInputStream;

/* loaded from: classes9.dex */
public final class ThrottledInputStream extends CountingInputStream {
    private final double maxBytesPerSecond;
    private final long startTime;
    private Duration totalSleepDuration;

    /* loaded from: classes9.dex */
    public static class Builder extends ProxyInputStream.AbstractBuilder<ThrottledInputStream, Builder> {
        private double maxBytesPerSecond = Double.MAX_VALUE;

        @Override // org.apache.commons.io.input.ProxyInputStream.AbstractBuilder
        public /* bridge */ /* synthetic */ IOIntConsumer getAfterRead() {
            return super.getAfterRead();
        }

        /* JADX WARN: Type inference failed for: r1v1, types: [org.apache.commons.io.build.AbstractStreamBuilder, org.apache.commons.io.input.ThrottledInputStream$Builder] */
        @Override // org.apache.commons.io.input.ProxyInputStream.AbstractBuilder
        public /* bridge */ /* synthetic */ Builder setAfterRead(IOIntConsumer iOIntConsumer) {
            return super.setAfterRead(iOIntConsumer);
        }

        @Override // org.apache.commons.io.function.IOSupplier
        public ThrottledInputStream get() throws IOException {
            return new ThrottledInputStream(this);
        }

        double getMaxBytesPerSecond() {
            return this.maxBytesPerSecond;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public Builder setMaxBytes(long value, ChronoUnit chronoUnit) {
            setMaxBytes(value, chronoUnit.getDuration());
            return (Builder) asThis();
        }

        /* JADX WARN: Multi-variable type inference failed */
        Builder setMaxBytes(long value, Duration duration) {
            setMaxBytesPerSecond((((Duration) Objects.requireNonNull(duration, "duration")).toMillis() / 1000.0d) * value);
            return (Builder) asThis();
        }

        /* JADX WARN: Multi-variable type inference failed */
        private Builder setMaxBytesPerSecond(double maxBytesPerSecond) {
            if (maxBytesPerSecond <= 0.0d) {
                throw new IllegalArgumentException("Bandwidth " + maxBytesPerSecond + " must be > 0.");
            }
            this.maxBytesPerSecond = maxBytesPerSecond;
            return (Builder) asThis();
        }

        public void setMaxBytesPerSecond(long maxBytesPerSecond) {
            setMaxBytesPerSecond(maxBytesPerSecond);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    static long toSleepMillis(long bytesRead, long elapsedMillis, double maxBytesPerSec) {
        if (bytesRead <= 0 || maxBytesPerSec <= 0.0d || elapsedMillis == 0) {
            return 0L;
        }
        long millis = (long) (((bytesRead / maxBytesPerSec) * 1000.0d) - elapsedMillis);
        if (millis <= 0) {
            return 0L;
        }
        return millis;
    }

    private ThrottledInputStream(Builder builder) throws IOException {
        super(builder);
        this.startTime = System.currentTimeMillis();
        this.totalSleepDuration = Duration.ZERO;
        if (builder.maxBytesPerSecond > 0.0d) {
            this.maxBytesPerSecond = builder.maxBytesPerSecond;
            return;
        }
        throw new IllegalArgumentException("Bandwidth " + builder.maxBytesPerSecond + " is invalid.");
    }

    @Override // org.apache.commons.io.input.ProxyInputStream
    protected void beforeRead(int n) throws IOException {
        throttle();
    }

    private long getBytesPerSecond() {
        long elapsedSeconds = (System.currentTimeMillis() - this.startTime) / 1000;
        if (elapsedSeconds == 0) {
            return getByteCount();
        }
        return getByteCount() / elapsedSeconds;
    }

    double getMaxBytesPerSecond() {
        return this.maxBytesPerSecond;
    }

    private long getSleepMillis() {
        return toSleepMillis(getByteCount(), System.currentTimeMillis() - this.startTime, this.maxBytesPerSecond);
    }

    Duration getTotalSleepDuration() {
        return this.totalSleepDuration;
    }

    private void throttle() throws InterruptedIOException {
        long sleepMillis = getSleepMillis();
        if (sleepMillis > 0) {
            this.totalSleepDuration = this.totalSleepDuration.plus(sleepMillis, ChronoUnit.MILLIS);
            try {
                TimeUnit.MILLISECONDS.sleep(sleepMillis);
            } catch (InterruptedException e) {
                throw new InterruptedIOException("Thread aborted");
            }
        }
    }

    public String toString() {
        return "ThrottledInputStream[bytesRead=" + getByteCount() + ", maxBytesPerSec=" + this.maxBytesPerSecond + ", bytesPerSec=" + getBytesPerSecond() + ", totalSleepDuration=" + this.totalSleepDuration + ']';
    }
}
