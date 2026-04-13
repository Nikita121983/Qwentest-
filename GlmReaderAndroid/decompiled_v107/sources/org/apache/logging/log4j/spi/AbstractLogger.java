package org.apache.logging.log4j.spi;

import java.io.Serializable;
import java.util.function.Supplier;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogBuilder;
import org.apache.logging.log4j.LoggingException;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.apache.logging.log4j.internal.DefaultLogBuilder;
import org.apache.logging.log4j.message.DefaultFlowMessageFactory;
import org.apache.logging.log4j.message.EntryMessage;
import org.apache.logging.log4j.message.FlowMessageFactory;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.MessageFactory;
import org.apache.logging.log4j.message.MessageFactory2;
import org.apache.logging.log4j.message.ParameterizedMessageFactory;
import org.apache.logging.log4j.message.ReusableMessageFactory;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.apache.logging.log4j.status.StatusLogger;
import org.apache.logging.log4j.util.Constants;
import org.apache.logging.log4j.util.LambdaUtil;
import org.apache.logging.log4j.util.MessageSupplier;
import org.apache.logging.log4j.util.StackLocatorUtil;

/* loaded from: classes10.dex */
public abstract class AbstractLogger implements ExtendedLogger, LocationAwareLogger, Serializable {
    private static final String CATCHING = "Catching";
    private static final String THROWING = "Throwing";
    private static final long serialVersionUID = 2;
    private final FlowMessageFactory flowMessageFactory;
    private final MessageFactory2 messageFactory;
    protected final String name;
    public static final Marker FLOW_MARKER = MarkerManager.getMarker("FLOW");
    public static final Marker ENTRY_MARKER = MarkerManager.getMarker("ENTER").setParents(FLOW_MARKER);
    public static final Marker EXIT_MARKER = MarkerManager.getMarker("EXIT").setParents(FLOW_MARKER);
    public static final Marker EXCEPTION_MARKER = MarkerManager.getMarker("EXCEPTION");
    public static final Marker THROWING_MARKER = MarkerManager.getMarker("THROWING").setParents(EXCEPTION_MARKER);
    public static final Marker CATCHING_MARKER = MarkerManager.getMarker("CATCHING").setParents(EXCEPTION_MARKER);
    public static final Class<? extends MessageFactory> DEFAULT_MESSAGE_FACTORY_CLASS = ParameterizedMessageFactory.class;
    public static final Class<? extends FlowMessageFactory> DEFAULT_FLOW_MESSAGE_FACTORY_CLASS = DefaultFlowMessageFactory.class;
    private static final String FQCN = AbstractLogger.class.getName();
    private static final ThreadLocal<int[]> recursionDepthHolder = new ThreadLocal<>();
    private static final ThreadLocal<DefaultLogBuilder> logBuilder = ThreadLocal.withInitial(new Supplier() { // from class: org.apache.logging.log4j.spi.AbstractLogger$$ExternalSyntheticLambda0
        @Override // java.util.function.Supplier
        public final Object get() {
            return new DefaultLogBuilder();
        }
    });

    public AbstractLogger() {
        this(null, null, null);
    }

    public AbstractLogger(final String name) {
        this(name, null, null);
    }

    public AbstractLogger(final String name, final MessageFactory messageFactory) {
        this(name, messageFactory, null);
    }

    protected AbstractLogger(final String name, final MessageFactory messageFactory, final FlowMessageFactory flowMessageFactory) {
        if (name != null) {
            this.name = name;
        } else {
            Class<?> cls = getClass();
            String canonicalName = cls.getCanonicalName();
            this.name = canonicalName != null ? canonicalName : cls.getName();
        }
        this.messageFactory = messageFactory != null ? adaptMessageFactory(messageFactory) : ParameterizedMessageFactory.INSTANCE;
        this.flowMessageFactory = flowMessageFactory != null ? flowMessageFactory : DefaultFlowMessageFactory.INSTANCE;
    }

    private static MessageFactory2 adaptMessageFactory(final MessageFactory result) {
        if (result instanceof MessageFactory2) {
            return (MessageFactory2) result;
        }
        return new MessageFactory2Adapter(result);
    }

    public static void checkMessageFactory(final ExtendedLogger logger, final MessageFactory messageFactory) {
        String name = logger.getName();
        MessageFactory loggerMessageFactory = logger.getMessageFactory();
        if (messageFactory != null && !loggerMessageFactory.equals(messageFactory)) {
            StatusLogger.getLogger().warn("The Logger {} was created with the message factory {} and is now requested with the message factory {}, which may create log events with unexpected formatting.", name, loggerMessageFactory, messageFactory);
        } else if (messageFactory == null && !loggerMessageFactory.getClass().equals(DEFAULT_MESSAGE_FACTORY_CLASS)) {
            StatusLogger.getLogger().warn("The Logger {} was created with the message factory {} and is now requested with a null message factory (defaults to {}), which may create log events with unexpected formatting.", name, loggerMessageFactory, DEFAULT_MESSAGE_FACTORY_CLASS.getName());
        }
    }

    @Override // org.apache.logging.log4j.Logger
    public void catching(final Level level, final Throwable throwable) {
        catching(FQCN, level, throwable);
    }

    protected void catching(final String fqcn, final Level level, final Throwable throwable) {
        if (isEnabled(level, CATCHING_MARKER, (Object) null, (Throwable) null)) {
            logMessageSafely(fqcn, level, CATCHING_MARKER, catchingMsg(throwable), throwable);
        }
    }

    @Override // org.apache.logging.log4j.Logger
    public void catching(final Throwable throwable) {
        if (isEnabled(Level.ERROR, CATCHING_MARKER, (Object) null, (Throwable) null)) {
            logMessageSafely(FQCN, Level.ERROR, CATCHING_MARKER, catchingMsg(throwable), throwable);
        }
    }

    protected Message catchingMsg(final Throwable throwable) {
        return this.messageFactory.newMessage(CATCHING);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(final Marker marker, final CharSequence message) {
        logIfEnabled(FQCN, Level.DEBUG, marker, message, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(final Marker marker, final CharSequence message, final Throwable throwable) {
        logIfEnabled(FQCN, Level.DEBUG, marker, message, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(final Marker marker, final Message message) {
        logIfEnabled(FQCN, Level.DEBUG, marker, message, message != null ? message.getThrowable() : null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(final Marker marker, final Message message, final Throwable throwable) {
        logIfEnabled(FQCN, Level.DEBUG, marker, message, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(final Marker marker, final Object message) {
        logIfEnabled(FQCN, Level.DEBUG, marker, message, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(final Marker marker, final Object message, final Throwable throwable) {
        logIfEnabled(FQCN, Level.DEBUG, marker, message, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(final Marker marker, final String message) {
        logIfEnabled(FQCN, Level.DEBUG, marker, message, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(final Marker marker, final String message, final Object... params) {
        logIfEnabled(FQCN, Level.DEBUG, marker, message, params);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(final Marker marker, final String message, final Throwable throwable) {
        logIfEnabled(FQCN, Level.DEBUG, marker, message, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(final Message message) {
        logIfEnabled(FQCN, Level.DEBUG, (Marker) null, message, message != null ? message.getThrowable() : null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(final Message message, final Throwable throwable) {
        logIfEnabled(FQCN, Level.DEBUG, (Marker) null, message, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(final CharSequence message) {
        logIfEnabled(FQCN, Level.DEBUG, (Marker) null, message, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(final CharSequence message, final Throwable throwable) {
        logIfEnabled(FQCN, Level.DEBUG, (Marker) null, message, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(final Object message) {
        logIfEnabled(FQCN, Level.DEBUG, (Marker) null, message, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(final Object message, final Throwable throwable) {
        logIfEnabled(FQCN, Level.DEBUG, (Marker) null, message, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(final String message) {
        logIfEnabled(FQCN, Level.DEBUG, (Marker) null, message, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(final String message, final Object... params) {
        logIfEnabled(FQCN, Level.DEBUG, (Marker) null, message, params);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(final String message, final Throwable throwable) {
        logIfEnabled(FQCN, Level.DEBUG, (Marker) null, message, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(final org.apache.logging.log4j.util.Supplier<?> messageSupplier) {
        logIfEnabled(FQCN, Level.DEBUG, (Marker) null, messageSupplier, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(final org.apache.logging.log4j.util.Supplier<?> messageSupplier, final Throwable throwable) {
        logIfEnabled(FQCN, Level.DEBUG, (Marker) null, messageSupplier, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(final Marker marker, final org.apache.logging.log4j.util.Supplier<?> messageSupplier) {
        logIfEnabled(FQCN, Level.DEBUG, marker, messageSupplier, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(final Marker marker, final String message, final org.apache.logging.log4j.util.Supplier<?>... paramSuppliers) {
        logIfEnabled(FQCN, Level.DEBUG, marker, message, paramSuppliers);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(final Marker marker, final org.apache.logging.log4j.util.Supplier<?> messageSupplier, final Throwable throwable) {
        logIfEnabled(FQCN, Level.DEBUG, marker, messageSupplier, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(final String message, final org.apache.logging.log4j.util.Supplier<?>... paramSuppliers) {
        logIfEnabled(FQCN, Level.DEBUG, (Marker) null, message, paramSuppliers);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(final Marker marker, final MessageSupplier messageSupplier) {
        logIfEnabled(FQCN, Level.DEBUG, marker, messageSupplier, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(final Marker marker, final MessageSupplier messageSupplier, final Throwable throwable) {
        logIfEnabled(FQCN, Level.DEBUG, marker, messageSupplier, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(final MessageSupplier messageSupplier) {
        logIfEnabled(FQCN, Level.DEBUG, (Marker) null, messageSupplier, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(final MessageSupplier messageSupplier, final Throwable throwable) {
        logIfEnabled(FQCN, Level.DEBUG, (Marker) null, messageSupplier, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(final Marker marker, final String message, final Object p0) {
        logIfEnabled(FQCN, Level.DEBUG, marker, message, p0);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(final Marker marker, final String message, final Object p0, final Object p1) {
        logIfEnabled(FQCN, Level.DEBUG, marker, message, p0, p1);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(final Marker marker, final String message, final Object p0, final Object p1, final Object p2) {
        logIfEnabled(FQCN, Level.DEBUG, marker, message, p0, p1, p2);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3) {
        logIfEnabled(FQCN, Level.DEBUG, marker, message, p0, p1, p2, p3);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4) {
        logIfEnabled(FQCN, Level.DEBUG, marker, message, p0, p1, p2, p3, p4);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5) {
        logIfEnabled(FQCN, Level.DEBUG, marker, message, p0, p1, p2, p3, p4, p5);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6) {
        logIfEnabled(FQCN, Level.DEBUG, marker, message, p0, p1, p2, p3, p4, p5, p6);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7) {
        logIfEnabled(FQCN, Level.DEBUG, marker, message, p0, p1, p2, p3, p4, p5, p6, p7);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8) {
        logIfEnabled(FQCN, Level.DEBUG, marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8, final Object p9) {
        logIfEnabled(FQCN, Level.DEBUG, marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(final String message, final Object p0) {
        logIfEnabled(FQCN, Level.DEBUG, (Marker) null, message, p0);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(final String message, final Object p0, final Object p1) {
        logIfEnabled(FQCN, Level.DEBUG, null, message, p0, p1);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(final String message, final Object p0, final Object p1, final Object p2) {
        logIfEnabled(FQCN, Level.DEBUG, null, message, p0, p1, p2);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(final String message, final Object p0, final Object p1, final Object p2, final Object p3) {
        logIfEnabled(FQCN, Level.DEBUG, null, message, p0, p1, p2, p3);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4) {
        logIfEnabled(FQCN, Level.DEBUG, null, message, p0, p1, p2, p3, p4);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5) {
        logIfEnabled(FQCN, Level.DEBUG, null, message, p0, p1, p2, p3, p4, p5);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6) {
        logIfEnabled(FQCN, Level.DEBUG, null, message, p0, p1, p2, p3, p4, p5, p6);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7) {
        logIfEnabled(FQCN, Level.DEBUG, null, message, p0, p1, p2, p3, p4, p5, p6, p7);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8) {
        logIfEnabled(FQCN, Level.DEBUG, null, message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8, final Object p9) {
        logIfEnabled(FQCN, Level.DEBUG, null, message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
    }

    protected EntryMessage enter(final String fqcn, final String format, final org.apache.logging.log4j.util.Supplier<?>... paramSuppliers) {
        if (!isEnabled(Level.TRACE, ENTRY_MARKER, (Object) null, (Throwable) null)) {
            return null;
        }
        Level level = Level.TRACE;
        Marker marker = ENTRY_MARKER;
        EntryMessage entryMsg = this.flowMessageFactory.newEntryMessage(format, LambdaUtil.getAll(paramSuppliers));
        logMessageSafely(fqcn, level, marker, entryMsg, null);
        return entryMsg;
    }

    @Deprecated
    protected EntryMessage enter(final String fqcn, final String format, final MessageSupplier... paramSuppliers) {
        if (!isEnabled(Level.TRACE, ENTRY_MARKER, (Object) null, (Throwable) null)) {
            return null;
        }
        Level level = Level.TRACE;
        Marker marker = ENTRY_MARKER;
        EntryMessage entryMsg = entryMsg(format, paramSuppliers);
        logMessageSafely(fqcn, level, marker, entryMsg, null);
        return entryMsg;
    }

    protected EntryMessage enter(final String fqcn, final String format, final Object... params) {
        if (!isEnabled(Level.TRACE, ENTRY_MARKER, (Object) null, (Throwable) null)) {
            return null;
        }
        Level level = Level.TRACE;
        Marker marker = ENTRY_MARKER;
        EntryMessage entryMsg = this.flowMessageFactory.newEntryMessage(format, params);
        logMessageSafely(fqcn, level, marker, entryMsg, null);
        return entryMsg;
    }

    @Deprecated
    protected EntryMessage enter(final String fqcn, final MessageSupplier messageSupplier) {
        if (!isEnabled(Level.TRACE, ENTRY_MARKER, (Object) null, (Throwable) null)) {
            return null;
        }
        Level level = Level.TRACE;
        Marker marker = ENTRY_MARKER;
        EntryMessage message = this.flowMessageFactory.newEntryMessage(messageSupplier.get());
        logMessageSafely(fqcn, level, marker, message, null);
        return message;
    }

    protected EntryMessage enter(final String fqcn, final Message message) {
        if (!isEnabled(Level.TRACE, ENTRY_MARKER, (Object) null, (Throwable) null)) {
            return null;
        }
        Level level = Level.TRACE;
        Marker marker = ENTRY_MARKER;
        EntryMessage flowMessage = this.flowMessageFactory.newEntryMessage(message);
        logMessageSafely(fqcn, level, marker, flowMessage, null);
        return flowMessage;
    }

    @Override // org.apache.logging.log4j.Logger
    @Deprecated
    public void entry() {
        entry(FQCN, null);
    }

    @Override // org.apache.logging.log4j.Logger
    @Deprecated
    public void entry(final Object... params) {
        entry(FQCN, params);
    }

    protected void entry(final String fqcn, final Object... params) {
        if (isEnabled(Level.TRACE, ENTRY_MARKER, (Object) null, (Throwable) null)) {
            if (params == null) {
                logMessageSafely(fqcn, Level.TRACE, ENTRY_MARKER, entryMsg((String) null, (org.apache.logging.log4j.util.Supplier<?>[]) null), null);
            } else {
                logMessageSafely(fqcn, Level.TRACE, ENTRY_MARKER, entryMsg((String) null, params), null);
            }
        }
    }

    protected EntryMessage entryMsg(final String format, final Object... params) {
        return this.flowMessageFactory.newEntryMessage(format, params);
    }

    protected EntryMessage entryMsg(final String format, final MessageSupplier... paramSuppliers) {
        int count = paramSuppliers == null ? 0 : paramSuppliers.length;
        Object[] params = new Object[count];
        for (int i = 0; i < count; i++) {
            params[i] = paramSuppliers[i].get();
        }
        return entryMsg(format, params);
    }

    protected EntryMessage entryMsg(final String format, final org.apache.logging.log4j.util.Supplier<?>... paramSuppliers) {
        return entryMsg(format, LambdaUtil.getAll(paramSuppliers));
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(final Marker marker, final Message message) {
        logIfEnabled(FQCN, Level.ERROR, marker, message, message != null ? message.getThrowable() : null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(final Marker marker, final Message message, final Throwable throwable) {
        logIfEnabled(FQCN, Level.ERROR, marker, message, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(final Marker marker, final CharSequence message) {
        logIfEnabled(FQCN, Level.ERROR, marker, message, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(final Marker marker, final CharSequence message, final Throwable throwable) {
        logIfEnabled(FQCN, Level.ERROR, marker, message, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(final Marker marker, final Object message) {
        logIfEnabled(FQCN, Level.ERROR, marker, message, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(final Marker marker, final Object message, final Throwable throwable) {
        logIfEnabled(FQCN, Level.ERROR, marker, message, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(final Marker marker, final String message) {
        logIfEnabled(FQCN, Level.ERROR, marker, message, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(final Marker marker, final String message, final Object... params) {
        logIfEnabled(FQCN, Level.ERROR, marker, message, params);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(final Marker marker, final String message, final Throwable throwable) {
        logIfEnabled(FQCN, Level.ERROR, marker, message, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(final Message message) {
        logIfEnabled(FQCN, Level.ERROR, (Marker) null, message, message != null ? message.getThrowable() : null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(final Message message, final Throwable throwable) {
        logIfEnabled(FQCN, Level.ERROR, (Marker) null, message, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(final CharSequence message) {
        logIfEnabled(FQCN, Level.ERROR, (Marker) null, message, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(final CharSequence message, final Throwable throwable) {
        logIfEnabled(FQCN, Level.ERROR, (Marker) null, message, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(final Object message) {
        logIfEnabled(FQCN, Level.ERROR, (Marker) null, message, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(final Object message, final Throwable throwable) {
        logIfEnabled(FQCN, Level.ERROR, (Marker) null, message, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(final String message) {
        logIfEnabled(FQCN, Level.ERROR, (Marker) null, message, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(final String message, final Object... params) {
        logIfEnabled(FQCN, Level.ERROR, (Marker) null, message, params);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(final String message, final Throwable throwable) {
        logIfEnabled(FQCN, Level.ERROR, (Marker) null, message, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(final org.apache.logging.log4j.util.Supplier<?> messageSupplier) {
        logIfEnabled(FQCN, Level.ERROR, (Marker) null, messageSupplier, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(final org.apache.logging.log4j.util.Supplier<?> messageSupplier, final Throwable throwable) {
        logIfEnabled(FQCN, Level.ERROR, (Marker) null, messageSupplier, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(final Marker marker, final org.apache.logging.log4j.util.Supplier<?> messageSupplier) {
        logIfEnabled(FQCN, Level.ERROR, marker, messageSupplier, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(final Marker marker, final String message, final org.apache.logging.log4j.util.Supplier<?>... paramSuppliers) {
        logIfEnabled(FQCN, Level.ERROR, marker, message, paramSuppliers);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(final Marker marker, final org.apache.logging.log4j.util.Supplier<?> messageSupplier, final Throwable throwable) {
        logIfEnabled(FQCN, Level.ERROR, marker, messageSupplier, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(final String message, final org.apache.logging.log4j.util.Supplier<?>... paramSuppliers) {
        logIfEnabled(FQCN, Level.ERROR, (Marker) null, message, paramSuppliers);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(final Marker marker, final MessageSupplier messageSupplier) {
        logIfEnabled(FQCN, Level.ERROR, marker, messageSupplier, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(final Marker marker, final MessageSupplier messageSupplier, final Throwable throwable) {
        logIfEnabled(FQCN, Level.ERROR, marker, messageSupplier, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(final MessageSupplier messageSupplier) {
        logIfEnabled(FQCN, Level.ERROR, (Marker) null, messageSupplier, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(final MessageSupplier messageSupplier, final Throwable throwable) {
        logIfEnabled(FQCN, Level.ERROR, (Marker) null, messageSupplier, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(final Marker marker, final String message, final Object p0) {
        logIfEnabled(FQCN, Level.ERROR, marker, message, p0);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(final Marker marker, final String message, final Object p0, final Object p1) {
        logIfEnabled(FQCN, Level.ERROR, marker, message, p0, p1);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(final Marker marker, final String message, final Object p0, final Object p1, final Object p2) {
        logIfEnabled(FQCN, Level.ERROR, marker, message, p0, p1, p2);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3) {
        logIfEnabled(FQCN, Level.ERROR, marker, message, p0, p1, p2, p3);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4) {
        logIfEnabled(FQCN, Level.ERROR, marker, message, p0, p1, p2, p3, p4);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5) {
        logIfEnabled(FQCN, Level.ERROR, marker, message, p0, p1, p2, p3, p4, p5);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6) {
        logIfEnabled(FQCN, Level.ERROR, marker, message, p0, p1, p2, p3, p4, p5, p6);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7) {
        logIfEnabled(FQCN, Level.ERROR, marker, message, p0, p1, p2, p3, p4, p5, p6, p7);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8) {
        logIfEnabled(FQCN, Level.ERROR, marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8, final Object p9) {
        logIfEnabled(FQCN, Level.ERROR, marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(final String message, final Object p0) {
        logIfEnabled(FQCN, Level.ERROR, (Marker) null, message, p0);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(final String message, final Object p0, final Object p1) {
        logIfEnabled(FQCN, Level.ERROR, null, message, p0, p1);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(final String message, final Object p0, final Object p1, final Object p2) {
        logIfEnabled(FQCN, Level.ERROR, null, message, p0, p1, p2);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(final String message, final Object p0, final Object p1, final Object p2, final Object p3) {
        logIfEnabled(FQCN, Level.ERROR, null, message, p0, p1, p2, p3);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4) {
        logIfEnabled(FQCN, Level.ERROR, null, message, p0, p1, p2, p3, p4);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5) {
        logIfEnabled(FQCN, Level.ERROR, null, message, p0, p1, p2, p3, p4, p5);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6) {
        logIfEnabled(FQCN, Level.ERROR, null, message, p0, p1, p2, p3, p4, p5, p6);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7) {
        logIfEnabled(FQCN, Level.ERROR, null, message, p0, p1, p2, p3, p4, p5, p6, p7);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8) {
        logIfEnabled(FQCN, Level.ERROR, null, message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8, final Object p9) {
        logIfEnabled(FQCN, Level.ERROR, null, message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
    }

    @Override // org.apache.logging.log4j.Logger
    @Deprecated
    public void exit() {
        exit(FQCN, null);
    }

    @Override // org.apache.logging.log4j.Logger
    @Deprecated
    public <R> R exit(R r) {
        return (R) exit(FQCN, r);
    }

    protected <R> R exit(final String fqcn, final R result) {
        if (isEnabled(Level.TRACE, EXIT_MARKER, (CharSequence) null, (Throwable) null)) {
            logMessageSafely(fqcn, Level.TRACE, EXIT_MARKER, this.flowMessageFactory.newExitMessage((String) null, result), null);
        }
        return result;
    }

    protected <R> R exit(final String fqcn, final String format, final R result) {
        if (isEnabled(Level.TRACE, EXIT_MARKER, (CharSequence) null, (Throwable) null)) {
            logMessageSafely(fqcn, Level.TRACE, EXIT_MARKER, this.flowMessageFactory.newExitMessage(format, result), null);
        }
        return result;
    }

    protected Message exitMsg(final String format, final Object result) {
        return this.flowMessageFactory.newExitMessage(format, result);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(final Marker marker, final Message message) {
        logIfEnabled(FQCN, Level.FATAL, marker, message, message != null ? message.getThrowable() : null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(final Marker marker, final Message message, final Throwable throwable) {
        logIfEnabled(FQCN, Level.FATAL, marker, message, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(final Marker marker, final CharSequence message) {
        logIfEnabled(FQCN, Level.FATAL, marker, message, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(final Marker marker, final CharSequence message, final Throwable throwable) {
        logIfEnabled(FQCN, Level.FATAL, marker, message, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(final Marker marker, final Object message) {
        logIfEnabled(FQCN, Level.FATAL, marker, message, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(final Marker marker, final Object message, final Throwable throwable) {
        logIfEnabled(FQCN, Level.FATAL, marker, message, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(final Marker marker, final String message) {
        logIfEnabled(FQCN, Level.FATAL, marker, message, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(final Marker marker, final String message, final Object... params) {
        logIfEnabled(FQCN, Level.FATAL, marker, message, params);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(final Marker marker, final String message, final Throwable throwable) {
        logIfEnabled(FQCN, Level.FATAL, marker, message, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(final Message message) {
        logIfEnabled(FQCN, Level.FATAL, (Marker) null, message, message != null ? message.getThrowable() : null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(final Message message, final Throwable throwable) {
        logIfEnabled(FQCN, Level.FATAL, (Marker) null, message, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(final CharSequence message) {
        logIfEnabled(FQCN, Level.FATAL, (Marker) null, message, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(final CharSequence message, final Throwable throwable) {
        logIfEnabled(FQCN, Level.FATAL, (Marker) null, message, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(final Object message) {
        logIfEnabled(FQCN, Level.FATAL, (Marker) null, message, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(final Object message, final Throwable throwable) {
        logIfEnabled(FQCN, Level.FATAL, (Marker) null, message, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(final String message) {
        logIfEnabled(FQCN, Level.FATAL, (Marker) null, message, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(final String message, final Object... params) {
        logIfEnabled(FQCN, Level.FATAL, (Marker) null, message, params);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(final String message, final Throwable throwable) {
        logIfEnabled(FQCN, Level.FATAL, (Marker) null, message, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(final org.apache.logging.log4j.util.Supplier<?> messageSupplier) {
        logIfEnabled(FQCN, Level.FATAL, (Marker) null, messageSupplier, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(final org.apache.logging.log4j.util.Supplier<?> messageSupplier, final Throwable throwable) {
        logIfEnabled(FQCN, Level.FATAL, (Marker) null, messageSupplier, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(final Marker marker, final org.apache.logging.log4j.util.Supplier<?> messageSupplier) {
        logIfEnabled(FQCN, Level.FATAL, marker, messageSupplier, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(final Marker marker, final String message, final org.apache.logging.log4j.util.Supplier<?>... paramSuppliers) {
        logIfEnabled(FQCN, Level.FATAL, marker, message, paramSuppliers);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(final Marker marker, final org.apache.logging.log4j.util.Supplier<?> messageSupplier, final Throwable throwable) {
        logIfEnabled(FQCN, Level.FATAL, marker, messageSupplier, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(final String message, final org.apache.logging.log4j.util.Supplier<?>... paramSuppliers) {
        logIfEnabled(FQCN, Level.FATAL, (Marker) null, message, paramSuppliers);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(final Marker marker, final MessageSupplier messageSupplier) {
        logIfEnabled(FQCN, Level.FATAL, marker, messageSupplier, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(final Marker marker, final MessageSupplier messageSupplier, final Throwable throwable) {
        logIfEnabled(FQCN, Level.FATAL, marker, messageSupplier, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(final MessageSupplier messageSupplier) {
        logIfEnabled(FQCN, Level.FATAL, (Marker) null, messageSupplier, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(final MessageSupplier messageSupplier, final Throwable throwable) {
        logIfEnabled(FQCN, Level.FATAL, (Marker) null, messageSupplier, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(final Marker marker, final String message, final Object p0) {
        logIfEnabled(FQCN, Level.FATAL, marker, message, p0);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(final Marker marker, final String message, final Object p0, final Object p1) {
        logIfEnabled(FQCN, Level.FATAL, marker, message, p0, p1);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(final Marker marker, final String message, final Object p0, final Object p1, final Object p2) {
        logIfEnabled(FQCN, Level.FATAL, marker, message, p0, p1, p2);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3) {
        logIfEnabled(FQCN, Level.FATAL, marker, message, p0, p1, p2, p3);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4) {
        logIfEnabled(FQCN, Level.FATAL, marker, message, p0, p1, p2, p3, p4);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5) {
        logIfEnabled(FQCN, Level.FATAL, marker, message, p0, p1, p2, p3, p4, p5);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6) {
        logIfEnabled(FQCN, Level.FATAL, marker, message, p0, p1, p2, p3, p4, p5, p6);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7) {
        logIfEnabled(FQCN, Level.FATAL, marker, message, p0, p1, p2, p3, p4, p5, p6, p7);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8) {
        logIfEnabled(FQCN, Level.FATAL, marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8, final Object p9) {
        logIfEnabled(FQCN, Level.FATAL, marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(final String message, final Object p0) {
        logIfEnabled(FQCN, Level.FATAL, (Marker) null, message, p0);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(final String message, final Object p0, final Object p1) {
        logIfEnabled(FQCN, Level.FATAL, null, message, p0, p1);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(final String message, final Object p0, final Object p1, final Object p2) {
        logIfEnabled(FQCN, Level.FATAL, null, message, p0, p1, p2);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(final String message, final Object p0, final Object p1, final Object p2, final Object p3) {
        logIfEnabled(FQCN, Level.FATAL, null, message, p0, p1, p2, p3);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4) {
        logIfEnabled(FQCN, Level.FATAL, null, message, p0, p1, p2, p3, p4);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5) {
        logIfEnabled(FQCN, Level.FATAL, null, message, p0, p1, p2, p3, p4, p5);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6) {
        logIfEnabled(FQCN, Level.FATAL, null, message, p0, p1, p2, p3, p4, p5, p6);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7) {
        logIfEnabled(FQCN, Level.FATAL, null, message, p0, p1, p2, p3, p4, p5, p6, p7);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8) {
        logIfEnabled(FQCN, Level.FATAL, null, message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8, final Object p9) {
        logIfEnabled(FQCN, Level.FATAL, null, message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
    }

    @Override // org.apache.logging.log4j.Logger
    public <MF extends MessageFactory> MF getMessageFactory() {
        return this.messageFactory;
    }

    @Override // org.apache.logging.log4j.Logger
    public FlowMessageFactory getFlowMessageFactory() {
        return this.flowMessageFactory;
    }

    @Override // org.apache.logging.log4j.Logger
    public String getName() {
        return this.name;
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(final Marker marker, final Message message) {
        logIfEnabled(FQCN, Level.INFO, marker, message, message != null ? message.getThrowable() : null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(final Marker marker, final Message message, final Throwable throwable) {
        logIfEnabled(FQCN, Level.INFO, marker, message, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(final Marker marker, final CharSequence message) {
        logIfEnabled(FQCN, Level.INFO, marker, message, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(final Marker marker, final CharSequence message, final Throwable throwable) {
        logIfEnabled(FQCN, Level.INFO, marker, message, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(final Marker marker, final Object message) {
        logIfEnabled(FQCN, Level.INFO, marker, message, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(final Marker marker, final Object message, final Throwable throwable) {
        logIfEnabled(FQCN, Level.INFO, marker, message, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(final Marker marker, final String message) {
        logIfEnabled(FQCN, Level.INFO, marker, message, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(final Marker marker, final String message, final Object... params) {
        logIfEnabled(FQCN, Level.INFO, marker, message, params);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(final Marker marker, final String message, final Throwable throwable) {
        logIfEnabled(FQCN, Level.INFO, marker, message, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(final Message message) {
        logIfEnabled(FQCN, Level.INFO, (Marker) null, message, message != null ? message.getThrowable() : null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(final Message message, final Throwable throwable) {
        logIfEnabled(FQCN, Level.INFO, (Marker) null, message, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(final CharSequence message) {
        logIfEnabled(FQCN, Level.INFO, (Marker) null, message, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(final CharSequence message, final Throwable throwable) {
        logIfEnabled(FQCN, Level.INFO, (Marker) null, message, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(final Object message) {
        logIfEnabled(FQCN, Level.INFO, (Marker) null, message, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(final Object message, final Throwable throwable) {
        logIfEnabled(FQCN, Level.INFO, (Marker) null, message, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(final String message) {
        logIfEnabled(FQCN, Level.INFO, (Marker) null, message, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(final String message, final Object... params) {
        logIfEnabled(FQCN, Level.INFO, (Marker) null, message, params);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(final String message, final Throwable throwable) {
        logIfEnabled(FQCN, Level.INFO, (Marker) null, message, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(final org.apache.logging.log4j.util.Supplier<?> messageSupplier) {
        logIfEnabled(FQCN, Level.INFO, (Marker) null, messageSupplier, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(final org.apache.logging.log4j.util.Supplier<?> messageSupplier, final Throwable throwable) {
        logIfEnabled(FQCN, Level.INFO, (Marker) null, messageSupplier, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(final Marker marker, final org.apache.logging.log4j.util.Supplier<?> messageSupplier) {
        logIfEnabled(FQCN, Level.INFO, marker, messageSupplier, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(final Marker marker, final String message, final org.apache.logging.log4j.util.Supplier<?>... paramSuppliers) {
        logIfEnabled(FQCN, Level.INFO, marker, message, paramSuppliers);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(final Marker marker, final org.apache.logging.log4j.util.Supplier<?> messageSupplier, final Throwable throwable) {
        logIfEnabled(FQCN, Level.INFO, marker, messageSupplier, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(final String message, final org.apache.logging.log4j.util.Supplier<?>... paramSuppliers) {
        logIfEnabled(FQCN, Level.INFO, (Marker) null, message, paramSuppliers);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(final Marker marker, final MessageSupplier messageSupplier) {
        logIfEnabled(FQCN, Level.INFO, marker, messageSupplier, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(final Marker marker, final MessageSupplier messageSupplier, final Throwable throwable) {
        logIfEnabled(FQCN, Level.INFO, marker, messageSupplier, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(final MessageSupplier messageSupplier) {
        logIfEnabled(FQCN, Level.INFO, (Marker) null, messageSupplier, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(final MessageSupplier messageSupplier, final Throwable throwable) {
        logIfEnabled(FQCN, Level.INFO, (Marker) null, messageSupplier, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(final Marker marker, final String message, final Object p0) {
        logIfEnabled(FQCN, Level.INFO, marker, message, p0);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(final Marker marker, final String message, final Object p0, final Object p1) {
        logIfEnabled(FQCN, Level.INFO, marker, message, p0, p1);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(final Marker marker, final String message, final Object p0, final Object p1, final Object p2) {
        logIfEnabled(FQCN, Level.INFO, marker, message, p0, p1, p2);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3) {
        logIfEnabled(FQCN, Level.INFO, marker, message, p0, p1, p2, p3);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4) {
        logIfEnabled(FQCN, Level.INFO, marker, message, p0, p1, p2, p3, p4);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5) {
        logIfEnabled(FQCN, Level.INFO, marker, message, p0, p1, p2, p3, p4, p5);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6) {
        logIfEnabled(FQCN, Level.INFO, marker, message, p0, p1, p2, p3, p4, p5, p6);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7) {
        logIfEnabled(FQCN, Level.INFO, marker, message, p0, p1, p2, p3, p4, p5, p6, p7);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8) {
        logIfEnabled(FQCN, Level.INFO, marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8, final Object p9) {
        logIfEnabled(FQCN, Level.INFO, marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(final String message, final Object p0) {
        logIfEnabled(FQCN, Level.INFO, (Marker) null, message, p0);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(final String message, final Object p0, final Object p1) {
        logIfEnabled(FQCN, Level.INFO, null, message, p0, p1);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(final String message, final Object p0, final Object p1, final Object p2) {
        logIfEnabled(FQCN, Level.INFO, null, message, p0, p1, p2);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(final String message, final Object p0, final Object p1, final Object p2, final Object p3) {
        logIfEnabled(FQCN, Level.INFO, null, message, p0, p1, p2, p3);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4) {
        logIfEnabled(FQCN, Level.INFO, null, message, p0, p1, p2, p3, p4);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5) {
        logIfEnabled(FQCN, Level.INFO, null, message, p0, p1, p2, p3, p4, p5);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6) {
        logIfEnabled(FQCN, Level.INFO, null, message, p0, p1, p2, p3, p4, p5, p6);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7) {
        logIfEnabled(FQCN, Level.INFO, null, message, p0, p1, p2, p3, p4, p5, p6, p7);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8) {
        logIfEnabled(FQCN, Level.INFO, null, message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8, final Object p9) {
        logIfEnabled(FQCN, Level.INFO, null, message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
    }

    @Override // org.apache.logging.log4j.Logger
    public boolean isDebugEnabled() {
        return isEnabled(Level.DEBUG, null, null);
    }

    @Override // org.apache.logging.log4j.Logger
    public boolean isDebugEnabled(final Marker marker) {
        return isEnabled(Level.DEBUG, marker, (Object) null, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public boolean isEnabled(final Level level) {
        return isEnabled(level, (Marker) null, (Object) null, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public boolean isEnabled(final Level level, final Marker marker) {
        return isEnabled(level, marker, (Object) null, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public boolean isErrorEnabled() {
        return isEnabled(Level.ERROR, (Marker) null, (Object) null, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public boolean isErrorEnabled(final Marker marker) {
        return isEnabled(Level.ERROR, marker, (Object) null, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public boolean isFatalEnabled() {
        return isEnabled(Level.FATAL, (Marker) null, (Object) null, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public boolean isFatalEnabled(final Marker marker) {
        return isEnabled(Level.FATAL, marker, (Object) null, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public boolean isInfoEnabled() {
        return isEnabled(Level.INFO, (Marker) null, (Object) null, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public boolean isInfoEnabled(final Marker marker) {
        return isEnabled(Level.INFO, marker, (Object) null, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public boolean isTraceEnabled() {
        return isEnabled(Level.TRACE, (Marker) null, (Object) null, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public boolean isTraceEnabled(final Marker marker) {
        return isEnabled(Level.TRACE, marker, (Object) null, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public boolean isWarnEnabled() {
        return isEnabled(Level.WARN, (Marker) null, (Object) null, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public boolean isWarnEnabled(final Marker marker) {
        return isEnabled(Level.WARN, marker, (Object) null, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(final Level level, final Marker marker, final Message message) {
        logIfEnabled(FQCN, level, marker, message, message != null ? message.getThrowable() : null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(final Level level, final Marker marker, final Message message, final Throwable throwable) {
        logIfEnabled(FQCN, level, marker, message, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(final Level level, final Marker marker, final CharSequence message) {
        logIfEnabled(FQCN, level, marker, message, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(final Level level, final Marker marker, final CharSequence message, final Throwable throwable) {
        if (isEnabled(level, marker, message, throwable)) {
            logMessage(FQCN, level, marker, message, throwable);
        }
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(final Level level, final Marker marker, final Object message) {
        logIfEnabled(FQCN, level, marker, message, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(final Level level, final Marker marker, final Object message, final Throwable throwable) {
        if (isEnabled(level, marker, message, throwable)) {
            logMessage(FQCN, level, marker, message, throwable);
        }
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(final Level level, final Marker marker, final String message) {
        logIfEnabled(FQCN, level, marker, message, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(final Level level, final Marker marker, final String message, final Object... params) {
        logIfEnabled(FQCN, level, marker, message, params);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(final Level level, final Marker marker, final String message, final Throwable throwable) {
        logIfEnabled(FQCN, level, marker, message, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(final Level level, final Message message) {
        logIfEnabled(FQCN, level, (Marker) null, message, message != null ? message.getThrowable() : null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(final Level level, final Message message, final Throwable throwable) {
        logIfEnabled(FQCN, level, (Marker) null, message, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(final Level level, final CharSequence message) {
        logIfEnabled(FQCN, level, (Marker) null, message, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(final Level level, final CharSequence message, final Throwable throwable) {
        logIfEnabled(FQCN, level, (Marker) null, message, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(final Level level, final Object message) {
        logIfEnabled(FQCN, level, (Marker) null, message, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(final Level level, final Object message, final Throwable throwable) {
        logIfEnabled(FQCN, level, (Marker) null, message, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(final Level level, final String message) {
        logIfEnabled(FQCN, level, (Marker) null, message, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(final Level level, final String message, final Object... params) {
        logIfEnabled(FQCN, level, (Marker) null, message, params);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(final Level level, final String message, final Throwable throwable) {
        logIfEnabled(FQCN, level, (Marker) null, message, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(final Level level, final org.apache.logging.log4j.util.Supplier<?> messageSupplier) {
        logIfEnabled(FQCN, level, (Marker) null, messageSupplier, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(final Level level, final org.apache.logging.log4j.util.Supplier<?> messageSupplier, final Throwable throwable) {
        logIfEnabled(FQCN, level, (Marker) null, messageSupplier, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(final Level level, final Marker marker, final org.apache.logging.log4j.util.Supplier<?> messageSupplier) {
        logIfEnabled(FQCN, level, marker, messageSupplier, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(final Level level, final Marker marker, final String message, final org.apache.logging.log4j.util.Supplier<?>... paramSuppliers) {
        logIfEnabled(FQCN, level, marker, message, paramSuppliers);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(final Level level, final Marker marker, final org.apache.logging.log4j.util.Supplier<?> messageSupplier, final Throwable throwable) {
        logIfEnabled(FQCN, level, marker, messageSupplier, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(final Level level, final String message, final org.apache.logging.log4j.util.Supplier<?>... paramSuppliers) {
        logIfEnabled(FQCN, level, (Marker) null, message, paramSuppliers);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(final Level level, final Marker marker, final MessageSupplier messageSupplier) {
        logIfEnabled(FQCN, level, marker, messageSupplier, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(final Level level, final Marker marker, final MessageSupplier messageSupplier, final Throwable throwable) {
        logIfEnabled(FQCN, level, marker, messageSupplier, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(final Level level, final MessageSupplier messageSupplier) {
        logIfEnabled(FQCN, level, (Marker) null, messageSupplier, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(final Level level, final MessageSupplier messageSupplier, final Throwable throwable) {
        logIfEnabled(FQCN, level, (Marker) null, messageSupplier, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(final Level level, final Marker marker, final String message, final Object p0) {
        logIfEnabled(FQCN, level, marker, message, p0);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(final Level level, final Marker marker, final String message, final Object p0, final Object p1) {
        logIfEnabled(FQCN, level, marker, message, p0, p1);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(final Level level, final Marker marker, final String message, final Object p0, final Object p1, final Object p2) {
        logIfEnabled(FQCN, level, marker, message, p0, p1, p2);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(final Level level, final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3) {
        logIfEnabled(FQCN, level, marker, message, p0, p1, p2, p3);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(final Level level, final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4) {
        logIfEnabled(FQCN, level, marker, message, p0, p1, p2, p3, p4);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(final Level level, final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5) {
        logIfEnabled(FQCN, level, marker, message, p0, p1, p2, p3, p4, p5);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(final Level level, final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6) {
        logIfEnabled(FQCN, level, marker, message, p0, p1, p2, p3, p4, p5, p6);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(final Level level, final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7) {
        logIfEnabled(FQCN, level, marker, message, p0, p1, p2, p3, p4, p5, p6, p7);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(final Level level, final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8) {
        logIfEnabled(FQCN, level, marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(final Level level, final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8, final Object p9) {
        logIfEnabled(FQCN, level, marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(final Level level, final String message, final Object p0) {
        logIfEnabled(FQCN, level, (Marker) null, message, p0);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(final Level level, final String message, final Object p0, final Object p1) {
        logIfEnabled(FQCN, level, null, message, p0, p1);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(final Level level, final String message, final Object p0, final Object p1, final Object p2) {
        logIfEnabled(FQCN, level, null, message, p0, p1, p2);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(final Level level, final String message, final Object p0, final Object p1, final Object p2, final Object p3) {
        logIfEnabled(FQCN, level, null, message, p0, p1, p2, p3);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(final Level level, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4) {
        logIfEnabled(FQCN, level, null, message, p0, p1, p2, p3, p4);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(final Level level, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5) {
        logIfEnabled(FQCN, level, null, message, p0, p1, p2, p3, p4, p5);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(final Level level, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6) {
        logIfEnabled(FQCN, level, null, message, p0, p1, p2, p3, p4, p5, p6);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(final Level level, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7) {
        logIfEnabled(FQCN, level, null, message, p0, p1, p2, p3, p4, p5, p6, p7);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(final Level level, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8) {
        logIfEnabled(FQCN, level, null, message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(final Level level, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8, final Object p9) {
        logIfEnabled(FQCN, level, null, message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public void logIfEnabled(final String fqcn, final Level level, final Marker marker, final Message message, final Throwable throwable) {
        if (isEnabled(level, marker, message, throwable)) {
            logMessageSafely(fqcn, level, marker, message, throwable);
        }
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public void logIfEnabled(final String fqcn, final Level level, final Marker marker, final MessageSupplier messageSupplier, final Throwable throwable) {
        if (isEnabled(level, marker, messageSupplier, throwable)) {
            logMessage(fqcn, level, marker, messageSupplier, throwable);
        }
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public void logIfEnabled(final String fqcn, final Level level, final Marker marker, final Object message, final Throwable throwable) {
        if (isEnabled(level, marker, message, throwable)) {
            logMessage(fqcn, level, marker, message, throwable);
        }
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public void logIfEnabled(final String fqcn, final Level level, final Marker marker, final CharSequence message, final Throwable throwable) {
        if (isEnabled(level, marker, message, throwable)) {
            logMessage(fqcn, level, marker, message, throwable);
        }
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public void logIfEnabled(final String fqcn, final Level level, final Marker marker, final org.apache.logging.log4j.util.Supplier<?> messageSupplier, final Throwable throwable) {
        if (isEnabled(level, marker, messageSupplier, throwable)) {
            logMessage(fqcn, level, marker, messageSupplier, throwable);
        }
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public void logIfEnabled(final String fqcn, final Level level, final Marker marker, final String message) {
        if (isEnabled(level, marker, message)) {
            logMessage(fqcn, level, marker, message);
        }
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public void logIfEnabled(final String fqcn, final Level level, final Marker marker, final String message, final org.apache.logging.log4j.util.Supplier<?>... paramSuppliers) {
        if (isEnabled(level, marker, message)) {
            logMessage(fqcn, level, marker, message, paramSuppliers);
        }
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public void logIfEnabled(final String fqcn, final Level level, final Marker marker, final String message, final Object... params) {
        if (isEnabled(level, marker, message, params)) {
            logMessage(fqcn, level, marker, message, params);
        }
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public void logIfEnabled(final String fqcn, final Level level, final Marker marker, final String message, final Object p0) {
        if (isEnabled(level, marker, message, p0)) {
            logMessage(fqcn, level, marker, message, p0);
        }
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public void logIfEnabled(final String fqcn, final Level level, final Marker marker, final String message, final Object p0, final Object p1) {
        if (isEnabled(level, marker, message, p0, p1)) {
            logMessage(fqcn, level, marker, message, p0, p1);
        }
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public void logIfEnabled(final String fqcn, final Level level, final Marker marker, final String message, final Object p0, final Object p1, final Object p2) {
        if (isEnabled(level, marker, message, p0, p1, p2)) {
            logMessage(fqcn, level, marker, message, p0, p1, p2);
        }
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public void logIfEnabled(final String fqcn, final Level level, final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3) {
        if (isEnabled(level, marker, message, p0, p1, p2, p3)) {
            logMessage(fqcn, level, marker, message, p0, p1, p2, p3);
        }
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public void logIfEnabled(final String fqcn, final Level level, final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4) {
        if (isEnabled(level, marker, message, p0, p1, p2, p3, p4)) {
            logMessage(fqcn, level, marker, message, p0, p1, p2, p3, p4);
        }
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public void logIfEnabled(final String fqcn, final Level level, final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5) {
        if (isEnabled(level, marker, message, p0, p1, p2, p3, p4, p5)) {
            logMessage(fqcn, level, marker, message, p0, p1, p2, p3, p4, p5);
        }
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public void logIfEnabled(final String fqcn, final Level level, final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6) {
        if (isEnabled(level, marker, message, p0, p1, p2, p3, p4, p5, p6)) {
            logMessage(fqcn, level, marker, message, p0, p1, p2, p3, p4, p5, p6);
        }
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public void logIfEnabled(final String fqcn, final Level level, final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7) {
        if (isEnabled(level, marker, message, p0, p1, p2, p3, p4, p5, p6, p7)) {
            logMessage(fqcn, level, marker, message, p0, p1, p2, p3, p4, p5, p6, p7);
        }
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public void logIfEnabled(final String fqcn, final Level level, final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8) {
        if (isEnabled(level, marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8)) {
            logMessage(fqcn, level, marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
        }
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public void logIfEnabled(final String fqcn, final Level level, final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8, final Object p9) {
        if (isEnabled(level, marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9)) {
            logMessage(fqcn, level, marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
        }
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public void logIfEnabled(final String fqcn, final Level level, final Marker marker, final String message, final Throwable throwable) {
        if (isEnabled(level, marker, message, throwable)) {
            logMessage(fqcn, level, marker, message, throwable);
        }
    }

    protected void logMessage(final String fqcn, final Level level, final Marker marker, final CharSequence message, final Throwable throwable) {
        logMessageSafely(fqcn, level, marker, this.messageFactory.newMessage(message), throwable);
    }

    protected void logMessage(final String fqcn, final Level level, final Marker marker, final Object message, final Throwable throwable) {
        logMessageSafely(fqcn, level, marker, this.messageFactory.newMessage(message), throwable);
    }

    protected void logMessage(final String fqcn, final Level level, final Marker marker, final MessageSupplier messageSupplier, final Throwable throwable) {
        Message message = LambdaUtil.get(messageSupplier);
        Throwable effectiveThrowable = (throwable != null || message == null) ? throwable : message.getThrowable();
        logMessageSafely(fqcn, level, marker, message, effectiveThrowable);
    }

    protected void logMessage(final String fqcn, final Level level, final Marker marker, final org.apache.logging.log4j.util.Supplier<?> messageSupplier, final Throwable throwable) {
        Message message = LambdaUtil.getMessage(messageSupplier, this.messageFactory);
        Throwable effectiveThrowable = (throwable != null || message == null) ? throwable : message.getThrowable();
        logMessageSafely(fqcn, level, marker, message, effectiveThrowable);
    }

    protected void logMessage(final String fqcn, final Level level, final Marker marker, final String message, final Throwable throwable) {
        logMessageSafely(fqcn, level, marker, this.messageFactory.newMessage(message), throwable);
    }

    protected void logMessage(final String fqcn, final Level level, final Marker marker, final String message) {
        Message msg = this.messageFactory.newMessage(message);
        logMessageSafely(fqcn, level, marker, msg, msg.getThrowable());
    }

    protected void logMessage(final String fqcn, final Level level, final Marker marker, final String message, final Object... params) {
        Message msg = this.messageFactory.newMessage(message, params);
        logMessageSafely(fqcn, level, marker, msg, msg.getThrowable());
    }

    protected void logMessage(final String fqcn, final Level level, final Marker marker, final String message, final Object p0) {
        Message msg = this.messageFactory.newMessage(message, p0);
        logMessageSafely(fqcn, level, marker, msg, msg.getThrowable());
    }

    protected void logMessage(final String fqcn, final Level level, final Marker marker, final String message, final Object p0, final Object p1) {
        Message msg = this.messageFactory.newMessage(message, p0, p1);
        logMessageSafely(fqcn, level, marker, msg, msg.getThrowable());
    }

    protected void logMessage(final String fqcn, final Level level, final Marker marker, final String message, final Object p0, final Object p1, final Object p2) {
        Message msg = this.messageFactory.newMessage(message, p0, p1, p2);
        logMessageSafely(fqcn, level, marker, msg, msg.getThrowable());
    }

    protected void logMessage(final String fqcn, final Level level, final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3) {
        Message msg = this.messageFactory.newMessage(message, p0, p1, p2, p3);
        logMessageSafely(fqcn, level, marker, msg, msg.getThrowable());
    }

    protected void logMessage(final String fqcn, final Level level, final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4) {
        Message msg = this.messageFactory.newMessage(message, p0, p1, p2, p3, p4);
        logMessageSafely(fqcn, level, marker, msg, msg.getThrowable());
    }

    protected void logMessage(final String fqcn, final Level level, final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5) {
        Message msg = this.messageFactory.newMessage(message, p0, p1, p2, p3, p4, p5);
        logMessageSafely(fqcn, level, marker, msg, msg.getThrowable());
    }

    protected void logMessage(final String fqcn, final Level level, final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6) {
        Message msg = this.messageFactory.newMessage(message, p0, p1, p2, p3, p4, p5, p6);
        logMessageSafely(fqcn, level, marker, msg, msg.getThrowable());
    }

    protected void logMessage(final String fqcn, final Level level, final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7) {
        Message msg = this.messageFactory.newMessage(message, p0, p1, p2, p3, p4, p5, p6, p7);
        logMessageSafely(fqcn, level, marker, msg, msg.getThrowable());
    }

    protected void logMessage(final String fqcn, final Level level, final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8) {
        Message msg = this.messageFactory.newMessage(message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
        logMessageSafely(fqcn, level, marker, msg, msg.getThrowable());
    }

    protected void logMessage(final String fqcn, final Level level, final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8, final Object p9) {
        Message msg = this.messageFactory.newMessage(message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
        logMessageSafely(fqcn, level, marker, msg, msg.getThrowable());
    }

    protected void logMessage(final String fqcn, final Level level, final Marker marker, final String message, final org.apache.logging.log4j.util.Supplier<?>... paramSuppliers) {
        Message msg = this.messageFactory.newMessage(message, LambdaUtil.getAll(paramSuppliers));
        logMessageSafely(fqcn, level, marker, msg, msg.getThrowable());
    }

    @Override // org.apache.logging.log4j.Logger, org.apache.logging.log4j.spi.LocationAwareLogger
    public void logMessage(final Level level, final Marker marker, final String fqcn, final StackTraceElement location, final Message message, final Throwable throwable) {
        try {
            incrementRecursionDepth();
            log(level, marker, fqcn, location, message, throwable);
            Message message2 = message;
        } finally {
            try {
            } finally {
            }
        }
    }

    protected void log(final Level level, final Marker marker, final String fqcn, final StackTraceElement location, final Message message, final Throwable throwable) {
        logMessage(fqcn, level, marker, message, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void printf(final Level level, final Marker marker, final String format, final Object... params) {
        if (isEnabled(level, marker, format, params)) {
            Message message = new StringFormattedMessage(format, params);
            logMessageSafely(FQCN, level, marker, message, message.getThrowable());
        }
    }

    @Override // org.apache.logging.log4j.Logger
    public void printf(final Level level, final String format, final Object... params) {
        if (isEnabled(level, (Marker) null, format, params)) {
            Message message = new StringFormattedMessage(format, params);
            logMessageSafely(FQCN, level, null, message, message.getThrowable());
        }
    }

    private void logMessageSafely(final String fqcn, final Level level, final Marker marker, final Message message, final Throwable throwable) {
        try {
            logMessageTrackRecursion(fqcn, level, marker, message, throwable);
        } finally {
            ReusableMessageFactory.release(message);
        }
    }

    private void logMessageTrackRecursion(final String fqcn, final Level level, final Marker marker, final Message message, final Throwable throwable) {
        Throwable th;
        try {
            incrementRecursionDepth();
            try {
                tryLogMessage(fqcn, getLocation(fqcn), level, marker, message, throwable);
                decrementRecursionDepth();
            } catch (Throwable th2) {
                th = th2;
                decrementRecursionDepth();
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    private static int[] getRecursionDepthHolder() {
        int[] result = recursionDepthHolder.get();
        if (result == null) {
            int[] result2 = new int[1];
            recursionDepthHolder.set(result2);
            return result2;
        }
        return result;
    }

    private static void incrementRecursionDepth() {
        int[] recursionDepthHolder2 = getRecursionDepthHolder();
        recursionDepthHolder2[0] = recursionDepthHolder2[0] + 1;
    }

    private static void decrementRecursionDepth() {
        int[] recursionDepthHolder2 = getRecursionDepthHolder();
        int newDepth = recursionDepthHolder2[0] - 1;
        recursionDepthHolder2[0] = newDepth;
        if (newDepth < 0) {
            throw new IllegalStateException("Recursion depth became negative: " + newDepth);
        }
    }

    public static int getRecursionDepth() {
        return getRecursionDepthHolder()[0];
    }

    private void tryLogMessage(final String fqcn, final StackTraceElement location, final Level level, final Marker marker, final Message message, final Throwable throwable) {
        try {
            log(level, marker, fqcn, location, message, throwable);
        } catch (Throwable t) {
            handleLogMessageException(t, fqcn, message);
        }
    }

    private StackTraceElement getLocation(final String fqcn) {
        if (requiresLocation()) {
            return StackLocatorUtil.calcLocation(fqcn);
        }
        return null;
    }

    private void handleLogMessageException(final Throwable throwable, final String fqcn, final Message message) {
        if (throwable instanceof LoggingException) {
            throw ((LoggingException) throwable);
        }
        StatusLogger.getLogger().warn("{} caught {} logging {}: {}", fqcn, throwable.getClass().getName(), message.getClass().getSimpleName(), message.getFormat(), throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public <T extends Throwable> T throwing(T t) {
        return (T) throwing(FQCN, Level.ERROR, t);
    }

    @Override // org.apache.logging.log4j.Logger
    public <T extends Throwable> T throwing(Level level, T t) {
        return (T) throwing(FQCN, level, t);
    }

    protected <T extends Throwable> T throwing(final String fqcn, final Level level, final T throwable) {
        if (!isEnabled(level, THROWING_MARKER, (Object) null, (Throwable) null)) {
            return throwable;
        }
        logMessageSafely(fqcn, level, THROWING_MARKER, throwingMsg(throwable), throwable);
        return throwable;
    }

    protected Message throwingMsg(final Throwable throwable) {
        return this.messageFactory.newMessage(THROWING);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(final Marker marker, final Message message) {
        logIfEnabled(FQCN, Level.TRACE, marker, message, message != null ? message.getThrowable() : null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(final Marker marker, final Message message, final Throwable throwable) {
        logIfEnabled(FQCN, Level.TRACE, marker, message, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(final Marker marker, final CharSequence message) {
        logIfEnabled(FQCN, Level.TRACE, marker, message, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(final Marker marker, final CharSequence message, final Throwable throwable) {
        logIfEnabled(FQCN, Level.TRACE, marker, message, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(final Marker marker, final Object message) {
        logIfEnabled(FQCN, Level.TRACE, marker, message, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(final Marker marker, final Object message, final Throwable throwable) {
        logIfEnabled(FQCN, Level.TRACE, marker, message, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(final Marker marker, final String message) {
        logIfEnabled(FQCN, Level.TRACE, marker, message, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(final Marker marker, final String message, final Object... params) {
        logIfEnabled(FQCN, Level.TRACE, marker, message, params);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(final Marker marker, final String message, final Throwable throwable) {
        logIfEnabled(FQCN, Level.TRACE, marker, message, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(final Message message) {
        logIfEnabled(FQCN, Level.TRACE, (Marker) null, message, message != null ? message.getThrowable() : null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(final Message message, final Throwable throwable) {
        logIfEnabled(FQCN, Level.TRACE, (Marker) null, message, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(final CharSequence message) {
        logIfEnabled(FQCN, Level.TRACE, (Marker) null, message, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(final CharSequence message, final Throwable throwable) {
        logIfEnabled(FQCN, Level.TRACE, (Marker) null, message, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(final Object message) {
        logIfEnabled(FQCN, Level.TRACE, (Marker) null, message, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(final Object message, final Throwable throwable) {
        logIfEnabled(FQCN, Level.TRACE, (Marker) null, message, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(final String message) {
        logIfEnabled(FQCN, Level.TRACE, (Marker) null, message, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(final String message, final Object... params) {
        logIfEnabled(FQCN, Level.TRACE, (Marker) null, message, params);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(final String message, final Throwable throwable) {
        logIfEnabled(FQCN, Level.TRACE, (Marker) null, message, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(final org.apache.logging.log4j.util.Supplier<?> messageSupplier) {
        logIfEnabled(FQCN, Level.TRACE, (Marker) null, messageSupplier, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(final org.apache.logging.log4j.util.Supplier<?> messageSupplier, final Throwable throwable) {
        logIfEnabled(FQCN, Level.TRACE, (Marker) null, messageSupplier, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(final Marker marker, final org.apache.logging.log4j.util.Supplier<?> messageSupplier) {
        logIfEnabled(FQCN, Level.TRACE, marker, messageSupplier, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(final Marker marker, final String message, final org.apache.logging.log4j.util.Supplier<?>... paramSuppliers) {
        logIfEnabled(FQCN, Level.TRACE, marker, message, paramSuppliers);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(final Marker marker, final org.apache.logging.log4j.util.Supplier<?> messageSupplier, final Throwable throwable) {
        logIfEnabled(FQCN, Level.TRACE, marker, messageSupplier, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(final String message, final org.apache.logging.log4j.util.Supplier<?>... paramSuppliers) {
        logIfEnabled(FQCN, Level.TRACE, (Marker) null, message, paramSuppliers);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(final Marker marker, final MessageSupplier messageSupplier) {
        logIfEnabled(FQCN, Level.TRACE, marker, messageSupplier, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(final Marker marker, final MessageSupplier messageSupplier, final Throwable throwable) {
        logIfEnabled(FQCN, Level.TRACE, marker, messageSupplier, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(final MessageSupplier messageSupplier) {
        logIfEnabled(FQCN, Level.TRACE, (Marker) null, messageSupplier, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(final MessageSupplier messageSupplier, final Throwable throwable) {
        logIfEnabled(FQCN, Level.TRACE, (Marker) null, messageSupplier, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(final Marker marker, final String message, final Object p0) {
        logIfEnabled(FQCN, Level.TRACE, marker, message, p0);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(final Marker marker, final String message, final Object p0, final Object p1) {
        logIfEnabled(FQCN, Level.TRACE, marker, message, p0, p1);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(final Marker marker, final String message, final Object p0, final Object p1, final Object p2) {
        logIfEnabled(FQCN, Level.TRACE, marker, message, p0, p1, p2);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3) {
        logIfEnabled(FQCN, Level.TRACE, marker, message, p0, p1, p2, p3);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4) {
        logIfEnabled(FQCN, Level.TRACE, marker, message, p0, p1, p2, p3, p4);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5) {
        logIfEnabled(FQCN, Level.TRACE, marker, message, p0, p1, p2, p3, p4, p5);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6) {
        logIfEnabled(FQCN, Level.TRACE, marker, message, p0, p1, p2, p3, p4, p5, p6);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7) {
        logIfEnabled(FQCN, Level.TRACE, marker, message, p0, p1, p2, p3, p4, p5, p6, p7);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8) {
        logIfEnabled(FQCN, Level.TRACE, marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8, final Object p9) {
        logIfEnabled(FQCN, Level.TRACE, marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(final String message, final Object p0) {
        logIfEnabled(FQCN, Level.TRACE, (Marker) null, message, p0);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(final String message, final Object p0, final Object p1) {
        logIfEnabled(FQCN, Level.TRACE, null, message, p0, p1);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(final String message, final Object p0, final Object p1, final Object p2) {
        logIfEnabled(FQCN, Level.TRACE, null, message, p0, p1, p2);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(final String message, final Object p0, final Object p1, final Object p2, final Object p3) {
        logIfEnabled(FQCN, Level.TRACE, null, message, p0, p1, p2, p3);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4) {
        logIfEnabled(FQCN, Level.TRACE, null, message, p0, p1, p2, p3, p4);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5) {
        logIfEnabled(FQCN, Level.TRACE, null, message, p0, p1, p2, p3, p4, p5);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6) {
        logIfEnabled(FQCN, Level.TRACE, null, message, p0, p1, p2, p3, p4, p5, p6);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7) {
        logIfEnabled(FQCN, Level.TRACE, null, message, p0, p1, p2, p3, p4, p5, p6, p7);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8) {
        logIfEnabled(FQCN, Level.TRACE, null, message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8, final Object p9) {
        logIfEnabled(FQCN, Level.TRACE, null, message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
    }

    @Override // org.apache.logging.log4j.Logger
    public EntryMessage traceEntry() {
        return enter(FQCN, (String) null, (Object[]) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public EntryMessage traceEntry(final String format, final Object... params) {
        return enter(FQCN, format, params);
    }

    @Override // org.apache.logging.log4j.Logger
    public EntryMessage traceEntry(final org.apache.logging.log4j.util.Supplier<?>... paramSuppliers) {
        return enter(FQCN, (String) null, paramSuppliers);
    }

    @Override // org.apache.logging.log4j.Logger
    public EntryMessage traceEntry(final String format, final org.apache.logging.log4j.util.Supplier<?>... paramSuppliers) {
        return enter(FQCN, format, paramSuppliers);
    }

    @Override // org.apache.logging.log4j.Logger
    public EntryMessage traceEntry(final Message message) {
        return enter(FQCN, message);
    }

    @Override // org.apache.logging.log4j.Logger
    public void traceExit() {
        exit(FQCN, null, null);
    }

    @Override // org.apache.logging.log4j.Logger
    public <R> R traceExit(R r) {
        return (R) exit(FQCN, null, r);
    }

    @Override // org.apache.logging.log4j.Logger
    public <R> R traceExit(String str, R r) {
        return (R) exit(FQCN, str, r);
    }

    @Override // org.apache.logging.log4j.Logger
    public void traceExit(final EntryMessage message) {
        if (message != null && isEnabled(Level.TRACE, EXIT_MARKER, (Message) message, (Throwable) null)) {
            logMessageSafely(FQCN, Level.TRACE, EXIT_MARKER, this.flowMessageFactory.newExitMessage(message), null);
        }
    }

    @Override // org.apache.logging.log4j.Logger
    public <R> R traceExit(final EntryMessage message, final R result) {
        if (message != null && isEnabled(Level.TRACE, EXIT_MARKER, (Message) message, (Throwable) null)) {
            logMessageSafely(FQCN, Level.TRACE, EXIT_MARKER, this.flowMessageFactory.newExitMessage((Object) result, message), null);
        }
        return result;
    }

    @Override // org.apache.logging.log4j.Logger
    public <R> R traceExit(final Message message, final R result) {
        if (message != null && isEnabled(Level.TRACE, EXIT_MARKER, message, (Throwable) null)) {
            logMessageSafely(FQCN, Level.TRACE, EXIT_MARKER, this.flowMessageFactory.newExitMessage(result, message), null);
        }
        return result;
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(final Marker marker, final Message message) {
        logIfEnabled(FQCN, Level.WARN, marker, message, message != null ? message.getThrowable() : null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(final Marker marker, final Message message, final Throwable throwable) {
        logIfEnabled(FQCN, Level.WARN, marker, message, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(final Marker marker, final CharSequence message) {
        logIfEnabled(FQCN, Level.WARN, marker, message, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(final Marker marker, final CharSequence message, final Throwable throwable) {
        logIfEnabled(FQCN, Level.WARN, marker, message, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(final Marker marker, final Object message) {
        logIfEnabled(FQCN, Level.WARN, marker, message, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(final Marker marker, final Object message, final Throwable throwable) {
        logIfEnabled(FQCN, Level.WARN, marker, message, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(final Marker marker, final String message) {
        logIfEnabled(FQCN, Level.WARN, marker, message, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(final Marker marker, final String message, final Object... params) {
        logIfEnabled(FQCN, Level.WARN, marker, message, params);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(final Marker marker, final String message, final Throwable throwable) {
        logIfEnabled(FQCN, Level.WARN, marker, message, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(final Message message) {
        logIfEnabled(FQCN, Level.WARN, (Marker) null, message, message != null ? message.getThrowable() : null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(final Message message, final Throwable throwable) {
        logIfEnabled(FQCN, Level.WARN, (Marker) null, message, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(final CharSequence message) {
        logIfEnabled(FQCN, Level.WARN, (Marker) null, message, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(final CharSequence message, final Throwable throwable) {
        logIfEnabled(FQCN, Level.WARN, (Marker) null, message, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(final Object message) {
        logIfEnabled(FQCN, Level.WARN, (Marker) null, message, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(final Object message, final Throwable throwable) {
        logIfEnabled(FQCN, Level.WARN, (Marker) null, message, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(final String message) {
        logIfEnabled(FQCN, Level.WARN, (Marker) null, message, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(final String message, final Object... params) {
        logIfEnabled(FQCN, Level.WARN, (Marker) null, message, params);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(final String message, final Throwable throwable) {
        logIfEnabled(FQCN, Level.WARN, (Marker) null, message, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(final org.apache.logging.log4j.util.Supplier<?> messageSupplier) {
        logIfEnabled(FQCN, Level.WARN, (Marker) null, messageSupplier, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(final org.apache.logging.log4j.util.Supplier<?> messageSupplier, final Throwable throwable) {
        logIfEnabled(FQCN, Level.WARN, (Marker) null, messageSupplier, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(final Marker marker, final org.apache.logging.log4j.util.Supplier<?> messageSupplier) {
        logIfEnabled(FQCN, Level.WARN, marker, messageSupplier, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(final Marker marker, final String message, final org.apache.logging.log4j.util.Supplier<?>... paramSuppliers) {
        logIfEnabled(FQCN, Level.WARN, marker, message, paramSuppliers);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(final Marker marker, final org.apache.logging.log4j.util.Supplier<?> messageSupplier, final Throwable throwable) {
        logIfEnabled(FQCN, Level.WARN, marker, messageSupplier, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(final String message, final org.apache.logging.log4j.util.Supplier<?>... paramSuppliers) {
        logIfEnabled(FQCN, Level.WARN, (Marker) null, message, paramSuppliers);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(final Marker marker, final MessageSupplier messageSupplier) {
        logIfEnabled(FQCN, Level.WARN, marker, messageSupplier, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(final Marker marker, final MessageSupplier messageSupplier, final Throwable throwable) {
        logIfEnabled(FQCN, Level.WARN, marker, messageSupplier, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(final MessageSupplier messageSupplier) {
        logIfEnabled(FQCN, Level.WARN, (Marker) null, messageSupplier, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(final MessageSupplier messageSupplier, final Throwable throwable) {
        logIfEnabled(FQCN, Level.WARN, (Marker) null, messageSupplier, throwable);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(final Marker marker, final String message, final Object p0) {
        logIfEnabled(FQCN, Level.WARN, marker, message, p0);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(final Marker marker, final String message, final Object p0, final Object p1) {
        logIfEnabled(FQCN, Level.WARN, marker, message, p0, p1);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(final Marker marker, final String message, final Object p0, final Object p1, final Object p2) {
        logIfEnabled(FQCN, Level.WARN, marker, message, p0, p1, p2);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3) {
        logIfEnabled(FQCN, Level.WARN, marker, message, p0, p1, p2, p3);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4) {
        logIfEnabled(FQCN, Level.WARN, marker, message, p0, p1, p2, p3, p4);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5) {
        logIfEnabled(FQCN, Level.WARN, marker, message, p0, p1, p2, p3, p4, p5);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6) {
        logIfEnabled(FQCN, Level.WARN, marker, message, p0, p1, p2, p3, p4, p5, p6);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7) {
        logIfEnabled(FQCN, Level.WARN, marker, message, p0, p1, p2, p3, p4, p5, p6, p7);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8) {
        logIfEnabled(FQCN, Level.WARN, marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8, final Object p9) {
        logIfEnabled(FQCN, Level.WARN, marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(final String message, final Object p0) {
        logIfEnabled(FQCN, Level.WARN, (Marker) null, message, p0);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(final String message, final Object p0, final Object p1) {
        logIfEnabled(FQCN, Level.WARN, null, message, p0, p1);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(final String message, final Object p0, final Object p1, final Object p2) {
        logIfEnabled(FQCN, Level.WARN, null, message, p0, p1, p2);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(final String message, final Object p0, final Object p1, final Object p2, final Object p3) {
        logIfEnabled(FQCN, Level.WARN, null, message, p0, p1, p2, p3);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4) {
        logIfEnabled(FQCN, Level.WARN, null, message, p0, p1, p2, p3, p4);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5) {
        logIfEnabled(FQCN, Level.WARN, null, message, p0, p1, p2, p3, p4, p5);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6) {
        logIfEnabled(FQCN, Level.WARN, null, message, p0, p1, p2, p3, p4, p5, p6);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7) {
        logIfEnabled(FQCN, Level.WARN, null, message, p0, p1, p2, p3, p4, p5, p6, p7);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8) {
        logIfEnabled(FQCN, Level.WARN, null, message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8, final Object p9) {
        logIfEnabled(FQCN, Level.WARN, null, message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean requiresLocation() {
        return false;
    }

    @Override // org.apache.logging.log4j.Logger
    public LogBuilder atTrace() {
        return atLevel(Level.TRACE);
    }

    @Override // org.apache.logging.log4j.Logger
    public LogBuilder atDebug() {
        return atLevel(Level.DEBUG);
    }

    @Override // org.apache.logging.log4j.Logger
    public LogBuilder atInfo() {
        return atLevel(Level.INFO);
    }

    @Override // org.apache.logging.log4j.Logger
    public LogBuilder atWarn() {
        return atLevel(Level.WARN);
    }

    @Override // org.apache.logging.log4j.Logger
    public LogBuilder atError() {
        return atLevel(Level.ERROR);
    }

    @Override // org.apache.logging.log4j.Logger
    public LogBuilder atFatal() {
        return atLevel(Level.FATAL);
    }

    @Override // org.apache.logging.log4j.Logger
    public LogBuilder always() {
        return getLogBuilder(Level.OFF);
    }

    @Override // org.apache.logging.log4j.Logger
    public LogBuilder atLevel(final Level level) {
        if (isEnabled(level)) {
            return getLogBuilder(level);
        }
        return LogBuilder.NOOP;
    }

    protected LogBuilder getLogBuilder(final Level level) {
        if (Constants.ENABLE_THREADLOCALS) {
            DefaultLogBuilder builder = logBuilder.get();
            if (!builder.isInUse()) {
                return builder.reset(this, level);
            }
        }
        return new DefaultLogBuilder(this, level);
    }
}
