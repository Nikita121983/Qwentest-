package org.apache.logging.log4j.status;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.util.Chars;

/* loaded from: classes10.dex */
public class StatusData implements Serializable {
    private static final long serialVersionUID = -4341916115118014017L;
    private final StackTraceElement caller;
    private final Instant instant;
    private final DateTimeFormatter instantFormatter;
    private final Level level;
    private final Message message;
    private final String threadName;
    private final Throwable throwable;

    public StatusData(final StackTraceElement caller, final Level level, final Message message, final Throwable throwable, final String threadName) {
        this(caller, level, message, throwable, threadName, null, Instant.now());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public StatusData(final StackTraceElement caller, final Level level, final Message message, final Throwable throwable, final String threadName, final DateTimeFormatter instantFormatter, final Instant instant) {
        this.instantFormatter = instantFormatter != null ? instantFormatter : DateTimeFormatter.ISO_INSTANT;
        this.instant = instant;
        this.caller = caller;
        this.level = (Level) Objects.requireNonNull(level, "level");
        this.message = (Message) Objects.requireNonNull(message, "message");
        this.throwable = throwable;
        this.threadName = threadName != null ? threadName : Thread.currentThread().getName();
    }

    public Instant getInstant() {
        return this.instant;
    }

    @Deprecated
    public long getTimestamp() {
        return this.instant.toEpochMilli();
    }

    public StackTraceElement getStackTraceElement() {
        return this.caller;
    }

    public Level getLevel() {
        return this.level;
    }

    public Message getMessage() {
        return this.message;
    }

    public String getThreadName() {
        return this.threadName;
    }

    public Throwable getThrowable() {
        return this.throwable;
    }

    public String getFormattedStatus() {
        Throwable effectiveThrowable;
        StringBuilder sb = new StringBuilder();
        this.instantFormatter.formatTo(this.instant, sb);
        sb.append(Chars.SPACE);
        sb.append(getThreadName());
        sb.append(Chars.SPACE);
        sb.append(this.level.toString());
        sb.append(Chars.SPACE);
        sb.append(this.message.getFormattedMessage());
        Object[] parameters = this.message.getParameters();
        if (this.throwable == null && parameters != null && (parameters[parameters.length - 1] instanceof Throwable)) {
            effectiveThrowable = (Throwable) parameters[parameters.length - 1];
        } else {
            effectiveThrowable = this.throwable;
        }
        if (effectiveThrowable != null) {
            sb.append(Chars.SPACE);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            effectiveThrowable.printStackTrace(new PrintStream(baos));
            sb.append(baos);
        }
        return sb.toString();
    }

    public String toString() {
        return getMessage().getFormattedMessage();
    }
}
