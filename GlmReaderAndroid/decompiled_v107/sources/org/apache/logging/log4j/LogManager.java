package org.apache.logging.log4j;

import java.net.URI;
import org.apache.logging.log4j.internal.LogManagerStatus;
import org.apache.logging.log4j.message.MessageFactory;
import org.apache.logging.log4j.message.StringFormatterMessageFactory;
import org.apache.logging.log4j.simple.SimpleLoggerContextFactory;
import org.apache.logging.log4j.spi.LoggerContext;
import org.apache.logging.log4j.spi.LoggerContextFactory;
import org.apache.logging.log4j.spi.Terminable;
import org.apache.logging.log4j.status.StatusLogger;
import org.apache.logging.log4j.util.ProviderUtil;
import org.apache.logging.log4j.util.StackLocatorUtil;

/* loaded from: classes10.dex */
public class LogManager {

    @Deprecated
    public static final String FACTORY_PROPERTY_NAME = "log4j2.loggerContextFactory";
    public static final String ROOT_LOGGER_NAME = "";
    private static final Logger LOGGER = StatusLogger.getLogger();
    private static final String FQCN = LogManager.class.getName();
    private static volatile LoggerContextFactory factory = ProviderUtil.getProvider().getLoggerContextFactory();

    static {
        LogManagerStatus.setInitialized(true);
    }

    protected LogManager() {
    }

    public static boolean exists(final String name) {
        return getContext().hasLogger(name);
    }

    public static LoggerContext getContext() {
        try {
            return factory.getContext(FQCN, null, null, true);
        } catch (IllegalStateException ex) {
            LOGGER.warn("{} Using SimpleLogger", ex.getMessage());
            return SimpleLoggerContextFactory.INSTANCE.getContext(FQCN, null, null, true);
        }
    }

    public static LoggerContext getContext(final boolean currentContext) {
        boolean currentContext2;
        IllegalStateException ex;
        try {
            currentContext2 = currentContext;
            try {
                return factory.getContext(FQCN, null, null, currentContext2, null, null);
            } catch (IllegalStateException e) {
                ex = e;
                LOGGER.warn("{} Using SimpleLogger", ex.getMessage());
                return SimpleLoggerContextFactory.INSTANCE.getContext(FQCN, null, null, currentContext2, null, null);
            }
        } catch (IllegalStateException e2) {
            currentContext2 = currentContext;
            ex = e2;
        }
    }

    public static LoggerContext getContext(final ClassLoader loader, final boolean currentContext) {
        try {
            return factory.getContext(FQCN, loader, null, currentContext);
        } catch (IllegalStateException ex) {
            LOGGER.warn("{} Using SimpleLogger", ex.getMessage());
            return SimpleLoggerContextFactory.INSTANCE.getContext(FQCN, loader, null, currentContext);
        }
    }

    public static LoggerContext getContext(final ClassLoader loader, final boolean currentContext, final Object externalContext) {
        try {
            return factory.getContext(FQCN, loader, externalContext, currentContext);
        } catch (IllegalStateException ex) {
            LOGGER.warn("{} Using SimpleLogger", ex.getMessage());
            return SimpleLoggerContextFactory.INSTANCE.getContext(FQCN, loader, externalContext, currentContext);
        }
    }

    public static LoggerContext getContext(final ClassLoader loader, final boolean currentContext, final URI configLocation) {
        ClassLoader loader2;
        boolean currentContext2;
        URI configLocation2;
        IllegalStateException ex;
        try {
            loader2 = loader;
            currentContext2 = currentContext;
            configLocation2 = configLocation;
            try {
                return factory.getContext(FQCN, loader2, null, currentContext2, configLocation2, null);
            } catch (IllegalStateException e) {
                ex = e;
                LOGGER.warn("{} Using SimpleLogger", ex.getMessage());
                return SimpleLoggerContextFactory.INSTANCE.getContext(FQCN, loader2, null, currentContext2, configLocation2, null);
            }
        } catch (IllegalStateException e2) {
            loader2 = loader;
            currentContext2 = currentContext;
            configLocation2 = configLocation;
            ex = e2;
        }
    }

    public static LoggerContext getContext(final ClassLoader loader, final boolean currentContext, final Object externalContext, final URI configLocation) {
        ClassLoader loader2;
        boolean currentContext2;
        Object externalContext2;
        URI configLocation2;
        IllegalStateException ex;
        try {
            loader2 = loader;
            currentContext2 = currentContext;
            externalContext2 = externalContext;
            configLocation2 = configLocation;
            try {
                return factory.getContext(FQCN, loader2, externalContext2, currentContext2, configLocation2, null);
            } catch (IllegalStateException e) {
                ex = e;
                LOGGER.warn("{} Using SimpleLogger", ex.getMessage());
                return SimpleLoggerContextFactory.INSTANCE.getContext(FQCN, loader2, externalContext2, currentContext2, configLocation2, null);
            }
        } catch (IllegalStateException e2) {
            loader2 = loader;
            currentContext2 = currentContext;
            externalContext2 = externalContext;
            configLocation2 = configLocation;
            ex = e2;
        }
    }

    public static LoggerContext getContext(final ClassLoader loader, final boolean currentContext, final Object externalContext, final URI configLocation, final String name) {
        ClassLoader loader2;
        boolean currentContext2;
        Object externalContext2;
        URI configLocation2;
        String name2;
        IllegalStateException ex;
        try {
            loader2 = loader;
            currentContext2 = currentContext;
            externalContext2 = externalContext;
            configLocation2 = configLocation;
            name2 = name;
            try {
                return factory.getContext(FQCN, loader2, externalContext2, currentContext2, configLocation2, name2);
            } catch (IllegalStateException e) {
                ex = e;
                LOGGER.warn("{} Using SimpleLogger", ex.getMessage());
                return SimpleLoggerContextFactory.INSTANCE.getContext(FQCN, loader2, externalContext2, currentContext2, configLocation2, name2);
            }
        } catch (IllegalStateException e2) {
            loader2 = loader;
            currentContext2 = currentContext;
            externalContext2 = externalContext;
            configLocation2 = configLocation;
            name2 = name;
            ex = e2;
        }
    }

    protected static LoggerContext getContext(final String fqcn, final boolean currentContext) {
        try {
            return factory.getContext(fqcn, null, null, currentContext);
        } catch (IllegalStateException ex) {
            LOGGER.warn("{} Using SimpleLogger", ex.getMessage());
            return SimpleLoggerContextFactory.INSTANCE.getContext(fqcn, null, null, currentContext);
        }
    }

    protected static LoggerContext getContext(final String fqcn, final ClassLoader loader, final boolean currentContext) {
        try {
            return factory.getContext(fqcn, loader, null, currentContext);
        } catch (IllegalStateException ex) {
            LOGGER.warn("{} Using SimpleLogger", ex.getMessage());
            return SimpleLoggerContextFactory.INSTANCE.getContext(fqcn, loader, null, currentContext);
        }
    }

    protected static LoggerContext getContext(final String fqcn, final ClassLoader loader, final boolean currentContext, final URI configLocation, final String name) {
        String fqcn2;
        ClassLoader loader2;
        boolean currentContext2;
        IllegalStateException ex;
        try {
            fqcn2 = fqcn;
            loader2 = loader;
            currentContext2 = currentContext;
        } catch (IllegalStateException e) {
            fqcn2 = fqcn;
            loader2 = loader;
            currentContext2 = currentContext;
            ex = e;
        }
        try {
            return factory.getContext(fqcn2, loader2, null, currentContext2, configLocation, name);
        } catch (IllegalStateException e2) {
            ex = e2;
            LOGGER.warn("{} Using SimpleLogger", ex.getMessage());
            return SimpleLoggerContextFactory.INSTANCE.getContext(fqcn2, loader2, null, currentContext2);
        }
    }

    public static void shutdown() {
        shutdown(false);
    }

    public static void shutdown(final boolean currentContext) {
        factory.shutdown(FQCN, null, currentContext, false);
    }

    public static void shutdown(final boolean currentContext, final boolean allContexts) {
        factory.shutdown(FQCN, null, currentContext, allContexts);
    }

    public static void shutdown(final LoggerContext context) {
        if (context instanceof Terminable) {
            ((Terminable) context).terminate();
        }
    }

    public static LoggerContextFactory getFactory() {
        return factory;
    }

    public static void setFactory(final LoggerContextFactory factory2) {
        factory = factory2;
    }

    public static Logger getFormatterLogger() {
        return getFormatterLogger(StackLocatorUtil.getCallerClass(2));
    }

    public static Logger getFormatterLogger(final Class<?> clazz) {
        return getLogger(clazz != null ? clazz : StackLocatorUtil.getCallerClass(2), (MessageFactory) StringFormatterMessageFactory.INSTANCE);
    }

    public static Logger getFormatterLogger(final Object value) {
        return getLogger(value != null ? value.getClass() : StackLocatorUtil.getCallerClass(2), (MessageFactory) StringFormatterMessageFactory.INSTANCE);
    }

    public static Logger getFormatterLogger(final String name) {
        if (name == null) {
            return getFormatterLogger(StackLocatorUtil.getCallerClass(2));
        }
        return getLogger(name, (MessageFactory) StringFormatterMessageFactory.INSTANCE);
    }

    private static Class<?> callerClass(final Class<?> clazz) {
        if (clazz != null) {
            return clazz;
        }
        Class<?> candidate = StackLocatorUtil.getCallerClass(3);
        if (candidate == null) {
            throw new UnsupportedOperationException("No class provided, and an appropriate one cannot be found.");
        }
        return candidate;
    }

    public static Logger getLogger() {
        return getLogger(StackLocatorUtil.getCallerClass(2));
    }

    public static Logger getLogger(final Class<?> clazz) {
        Class<?> cls = callerClass(clazz);
        return getContext(cls.getClassLoader(), false).getLogger(cls);
    }

    public static Logger getLogger(final Class<?> clazz, final MessageFactory messageFactory) {
        Class<?> cls = callerClass(clazz);
        return getContext(cls.getClassLoader(), false).getLogger(cls, messageFactory);
    }

    public static Logger getLogger(final MessageFactory messageFactory) {
        return getLogger(StackLocatorUtil.getCallerClass(2), messageFactory);
    }

    public static Logger getLogger(final Object value) {
        return getLogger(value != null ? value.getClass() : StackLocatorUtil.getCallerClass(2));
    }

    public static Logger getLogger(final Object value, final MessageFactory messageFactory) {
        return getLogger(value != null ? value.getClass() : StackLocatorUtil.getCallerClass(2), messageFactory);
    }

    public static Logger getLogger(final String name) {
        return name != null ? getContext(false).getLogger(name) : getLogger(StackLocatorUtil.getCallerClass(2));
    }

    public static Logger getLogger(final String name, final MessageFactory messageFactory) {
        if (name != null) {
            return getContext(false).getLogger(name, messageFactory);
        }
        return getLogger(StackLocatorUtil.getCallerClass(2), messageFactory);
    }

    protected static Logger getLogger(final String fqcn, final String name) {
        return factory.getContext(fqcn, null, null, false).getLogger(name);
    }

    public static Logger getRootLogger() {
        return getLogger("");
    }
}
