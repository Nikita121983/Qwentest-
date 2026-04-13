package org.apache.logging.log4j.message;

import java.lang.Thread;
import org.apache.logging.log4j.util.Chars;
import org.apache.logging.log4j.util.StringBuilders;

/* loaded from: classes10.dex */
class BasicThreadInformation implements ThreadInformation {
    private static final int HASH_MULTIPLIER = 31;
    private static final int HASH_SHIFT = 32;
    private final long id;
    private final boolean isDaemon;
    private final String name;
    private final int priority;
    private final Thread.State state;
    private final String threadGroupName;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BasicThreadInformation(final Thread thread) {
        this.id = thread.getId();
        this.name = thread.getName();
        this.state = thread.getState();
        this.priority = thread.getPriority();
        this.isDaemon = thread.isDaemon();
        ThreadGroup group = thread.getThreadGroup();
        this.threadGroupName = group == null ? null : group.getName();
    }

    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BasicThreadInformation)) {
            return false;
        }
        BasicThreadInformation that = (BasicThreadInformation) o;
        if (this.id != that.id) {
            return false;
        }
        return this.name != null ? this.name.equals(that.name) : that.name == null;
    }

    public int hashCode() {
        int result = (int) (this.id ^ (this.id >>> 32));
        return (result * 31) + (this.name != null ? this.name.hashCode() : 0);
    }

    @Override // org.apache.logging.log4j.message.ThreadInformation
    public void printThreadInfo(final StringBuilder sb) {
        StringBuilders.appendDqValue(sb, this.name).append(Chars.SPACE);
        if (this.isDaemon) {
            sb.append("daemon ");
        }
        sb.append("prio=").append(this.priority).append(" tid=").append(this.id).append(Chars.SPACE);
        if (this.threadGroupName != null) {
            StringBuilders.appendKeyDqValue(sb, "group", this.threadGroupName);
        }
        sb.append('\n');
        sb.append("\tThread state: ").append(this.state.name()).append('\n');
    }

    @Override // org.apache.logging.log4j.message.ThreadInformation
    public void printStack(final StringBuilder sb, final StackTraceElement[] trace) {
        for (StackTraceElement element : trace) {
            sb.append("\tat ").append(element).append('\n');
        }
    }
}
