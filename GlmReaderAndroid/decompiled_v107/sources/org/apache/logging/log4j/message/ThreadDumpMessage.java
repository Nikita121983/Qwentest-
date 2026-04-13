package org.apache.logging.log4j.message;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.function.Supplier;
import org.apache.logging.log4j.message.ThreadDumpMessage;
import org.apache.logging.log4j.status.StatusLogger;
import org.apache.logging.log4j.util.Lazy;
import org.apache.logging.log4j.util.ServiceLoaderUtil;
import org.apache.logging.log4j.util.StringBuilderFormattable;

@AsynchronouslyFormattable
/* loaded from: classes10.dex */
public class ThreadDumpMessage implements Message, StringBuilderFormattable {
    private static final Lazy<ThreadInfoFactory> FACTORY = Lazy.lazy(new Supplier() { // from class: org.apache.logging.log4j.message.ThreadDumpMessage$$ExternalSyntheticLambda0
        @Override // java.util.function.Supplier
        public final Object get() {
            ThreadDumpMessage.ThreadInfoFactory initFactory;
            initFactory = ThreadDumpMessage.initFactory();
            return initFactory;
        }
    });
    private static final long serialVersionUID = -1103400781608841088L;
    private String formattedMessage;
    private volatile Map<ThreadInformation, StackTraceElement[]> threads;
    private final String title;

    /* loaded from: classes10.dex */
    public interface ThreadInfoFactory {
        Map<ThreadInformation, StackTraceElement[]> createThreadInfo();
    }

    public ThreadDumpMessage(final String title) {
        this.title = title == null ? "" : title;
        this.threads = FACTORY.get().createThreadInfo();
    }

    private ThreadDumpMessage(final String formattedMsg, final String title) {
        this.formattedMessage = formattedMsg;
        this.title = title == null ? "" : title;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ThreadInfoFactory initFactory() {
        return (ThreadInfoFactory) ServiceLoaderUtil.safeStream(ThreadInfoFactory.class, ServiceLoader.load(ThreadInfoFactory.class, ThreadDumpMessage.class.getClassLoader()), StatusLogger.getLogger()).findFirst().orElseGet(new Supplier() { // from class: org.apache.logging.log4j.message.ThreadDumpMessage$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return ThreadDumpMessage.lambda$initFactory$0();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ ThreadInfoFactory lambda$initFactory$0() {
        return new BasicThreadInfoFactory();
    }

    public String toString() {
        return getFormattedMessage();
    }

    @Override // org.apache.logging.log4j.message.Message
    public String getFormattedMessage() {
        if (this.formattedMessage != null) {
            return this.formattedMessage;
        }
        StringBuilder sb = new StringBuilder(255);
        formatTo(sb);
        return sb.toString();
    }

    @Override // org.apache.logging.log4j.util.StringBuilderFormattable
    public void formatTo(final StringBuilder sb) {
        sb.append(this.title);
        if (this.title.length() > 0) {
            sb.append('\n');
        }
        for (Map.Entry<ThreadInformation, StackTraceElement[]> entry : this.threads.entrySet()) {
            ThreadInformation info = entry.getKey();
            info.printThreadInfo(sb);
            info.printStack(sb, entry.getValue());
            sb.append('\n');
        }
    }

    @Override // org.apache.logging.log4j.message.Message
    public String getFormat() {
        return this.title == null ? "" : this.title;
    }

    @Override // org.apache.logging.log4j.message.Message
    public Object[] getParameters() {
        return null;
    }

    protected Object writeReplace() {
        return new ThreadDumpMessageProxy(this);
    }

    private void readObject(final ObjectInputStream stream) throws InvalidObjectException {
        throw new InvalidObjectException("Proxy required");
    }

    /* loaded from: classes10.dex */
    private static class ThreadDumpMessageProxy implements Serializable {
        private static final long serialVersionUID = -3476620450287648269L;
        private final String formattedMsg;
        private final String title;

        ThreadDumpMessageProxy(final ThreadDumpMessage msg) {
            this.formattedMsg = msg.getFormattedMessage();
            this.title = msg.title;
        }

        protected Object readResolve() {
            return new ThreadDumpMessage(this.formattedMsg, this.title);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static class BasicThreadInfoFactory implements ThreadInfoFactory {
        private BasicThreadInfoFactory() {
        }

        @Override // org.apache.logging.log4j.message.ThreadDumpMessage.ThreadInfoFactory
        public Map<ThreadInformation, StackTraceElement[]> createThreadInfo() {
            Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();
            Map<ThreadInformation, StackTraceElement[]> threads = new HashMap<>(map.size());
            for (Map.Entry<Thread, StackTraceElement[]> entry : map.entrySet()) {
                threads.put(new BasicThreadInformation(entry.getKey()), entry.getValue());
            }
            return threads;
        }
    }

    @Override // org.apache.logging.log4j.message.Message
    public Throwable getThrowable() {
        return null;
    }
}
