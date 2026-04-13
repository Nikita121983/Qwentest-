package org.apache.commons.io;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.TemporalAmount;

/* loaded from: classes9.dex */
public final class ThreadUtils {
    private static int getNanosOfMilli(Duration duration) {
        return duration.getNano() % 1000000;
    }

    public static void sleep(Duration duration) throws InterruptedException {
        long nowNano;
        try {
            long nanoStart = System.nanoTime();
            long finishNanos = duration.toNanos() + nanoStart;
            Duration remainingDuration = duration;
            do {
                Thread.sleep(remainingDuration.toMillis(), getNanosOfMilli(remainingDuration));
                nowNano = System.nanoTime();
                remainingDuration = Duration.ofNanos(finishNanos - nowNano);
            } while (nowNano - finishNanos < 0);
        } catch (ArithmeticException e) {
            Instant finishInstant = Instant.now().plus((TemporalAmount) duration);
            Duration remainingDuration2 = duration;
            do {
                Thread.sleep(remainingDuration2.toMillis(), getNanosOfMilli(remainingDuration2));
                remainingDuration2 = Duration.between(Instant.now(), finishInstant);
            } while (!remainingDuration2.isNegative());
        }
    }

    @Deprecated
    public ThreadUtils() {
    }
}
