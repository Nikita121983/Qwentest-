package org.apache.logging.log4j.util;

import java.io.Serializable;
import java.text.DecimalFormat;
import org.apache.commons.compress.harmony.pack200.PackingOptions;

/* loaded from: classes10.dex */
public class Timer implements Serializable, StringBuilderFormattable {
    private static final long serialVersionUID = 9175191792439630013L;
    private long elapsedTime;
    private final int iterations;
    private final String name;
    private ThreadLocal<Long> startTime;
    private Status status;
    private static long NANO_PER_SECOND = 1000000000;
    private static long NANO_PER_MINUTE = NANO_PER_SECOND * 60;
    private static long NANO_PER_HOUR = NANO_PER_MINUTE * 60;

    /* loaded from: classes10.dex */
    public enum Status {
        Started,
        Stopped,
        Paused
    }

    public Timer(final String name) {
        this(name, 0);
    }

    public Timer(final String name, final int iterations) {
        this.startTime = new ThreadLocal<Long>() { // from class: org.apache.logging.log4j.util.Timer.1
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.lang.ThreadLocal
            public Long initialValue() {
                return 0L;
            }
        };
        this.name = name;
        this.status = Status.Stopped;
        this.iterations = iterations > 0 ? iterations : 0;
    }

    public synchronized void start() {
        this.startTime.set(Long.valueOf(System.nanoTime()));
        this.elapsedTime = 0L;
        this.status = Status.Started;
    }

    public synchronized void startOrResume() {
        if (this.status == Status.Stopped) {
            start();
        } else {
            resume();
        }
    }

    public synchronized String stop() {
        this.elapsedTime += System.nanoTime() - this.startTime.get().longValue();
        this.startTime.set(0L);
        this.status = Status.Stopped;
        return toString();
    }

    public synchronized void pause() {
        this.elapsedTime += System.nanoTime() - this.startTime.get().longValue();
        this.startTime.set(0L);
        this.status = Status.Paused;
    }

    public synchronized void resume() {
        this.startTime.set(Long.valueOf(System.nanoTime()));
        this.status = Status.Started;
    }

    public String getName() {
        return this.name;
    }

    public long getElapsedTime() {
        return this.elapsedTime / PackingOptions.SEGMENT_LIMIT;
    }

    public long getElapsedNanoTime() {
        return this.elapsedTime;
    }

    public Status getStatus() {
        return this.status;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        formatTo(result);
        return result.toString();
    }

    @Override // org.apache.logging.log4j.util.StringBuilderFormattable
    public void formatTo(final StringBuilder buffer) {
        buffer.append("Timer ").append(this.name);
        switch (this.status) {
            case Started:
                buffer.append(" started");
                return;
            case Paused:
                buffer.append(" paused");
                return;
            case Stopped:
                long nanoseconds = this.elapsedTime;
                long hours = nanoseconds / NANO_PER_HOUR;
                long nanoseconds2 = nanoseconds % NANO_PER_HOUR;
                long minutes = nanoseconds2 / NANO_PER_MINUTE;
                long nanoseconds3 = nanoseconds2 % NANO_PER_MINUTE;
                long seconds = nanoseconds3 / NANO_PER_SECOND;
                long nanoseconds4 = nanoseconds3 % NANO_PER_SECOND;
                String elapsed = hours > 0 ? "" + hours + " hours " : "";
                if (minutes > 0 || hours > 0) {
                    elapsed = elapsed + minutes + " minutes ";
                }
                DecimalFormat numFormat = new DecimalFormat("#0");
                String elapsed2 = elapsed + numFormat.format(seconds) + '.';
                DecimalFormat numFormat2 = new DecimalFormat("000000000");
                buffer.append(" stopped. Elapsed time: ").append(elapsed2 + numFormat2.format(nanoseconds4) + " seconds");
                if (this.iterations > 0) {
                    long nanoseconds5 = this.elapsedTime / this.iterations;
                    long nanoseconds6 = nanoseconds5 / NANO_PER_HOUR;
                    long nanoseconds7 = nanoseconds5 % NANO_PER_HOUR;
                    long minutes2 = nanoseconds7 / NANO_PER_MINUTE;
                    long nanoseconds8 = nanoseconds7 % NANO_PER_MINUTE;
                    long seconds2 = nanoseconds8 / NANO_PER_SECOND;
                    long nanoseconds9 = nanoseconds8 % NANO_PER_SECOND;
                    String elapsed3 = nanoseconds6 > 0 ? "" + nanoseconds6 + " hours " : "";
                    if (minutes2 > 0 || nanoseconds6 > 0) {
                        elapsed3 = elapsed3 + minutes2 + " minutes ";
                    }
                    DecimalFormat numFormat3 = new DecimalFormat("#0");
                    String elapsed4 = elapsed3 + numFormat3.format(seconds2) + '.';
                    DecimalFormat numFormat4 = new DecimalFormat("000000000");
                    buffer.append(" Average per iteration: ").append(elapsed4 + numFormat4.format(nanoseconds9) + " seconds");
                    return;
                }
                return;
            default:
                buffer.append(Chars.SPACE).append(this.status);
                return;
        }
    }

    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Timer)) {
            return false;
        }
        Timer timer = (Timer) o;
        if (this.elapsedTime != timer.elapsedTime || this.startTime != timer.startTime) {
            return false;
        }
        if (this.name == null ? timer.name == null : this.name.equals(timer.name)) {
            return this.status == null ? timer.status == null : this.status.equals(timer.status);
        }
        return false;
    }

    public int hashCode() {
        int result = this.name != null ? this.name.hashCode() : 0;
        int result2 = (result * 29) + (this.status != null ? this.status.hashCode() : 0);
        long time = this.startTime.get().longValue();
        return (((result2 * 29) + ((int) ((time >>> 32) ^ time))) * 29) + ((int) (this.elapsedTime ^ (this.elapsedTime >>> 32)));
    }
}
