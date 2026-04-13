package org.apache.logging.log4j.spi;

import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.Objects;
import java.util.Properties;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.simple.SimpleLoggerContextFactory;
import org.apache.logging.log4j.status.StatusLogger;
import org.apache.logging.log4j.util.LoaderUtil;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.jspecify.annotations.NullMarked;

@NullMarked
/* loaded from: classes10.dex */
public class Provider {
    protected static final String CURRENT_VERSION = "2.6.0";
    private static final int DEFAULT_PRIORITY = -1;
    private static final String DISABLE_CONTEXT_MAP = "log4j2.disableThreadContextMap";
    private static final String DISABLE_THREAD_CONTEXT = "log4j2.disableThreadContext";

    @Deprecated
    public static final String FACTORY_PRIORITY = "FactoryPriority";
    private static final Logger LOGGER = StatusLogger.getLogger();

    @Deprecated
    public static final String LOGGER_CONTEXT_FACTORY = "LoggerContextFactory";
    public static final String PROVIDER_PROPERTY_NAME = "log4j.provider";

    @Deprecated
    public static final String THREAD_CONTEXT_MAP = "ThreadContextMap";

    @Deprecated
    private final WeakReference<ClassLoader> classLoader;

    @Deprecated
    private final String className;
    private final Class<? extends LoggerContextFactory> loggerContextFactoryClass;
    private final int priority;

    @Deprecated
    private final String threadContextMap;
    private final Class<? extends ThreadContextMap> threadContextMapClass;

    @Deprecated
    private final URL url;
    private final String versions;

    @Deprecated
    public Provider(final Properties props, final URL url, final ClassLoader classLoader) {
        this.url = url;
        this.classLoader = new WeakReference<>(classLoader);
        String weight = props.getProperty(FACTORY_PRIORITY);
        this.priority = weight == null ? -1 : Integer.parseInt(weight);
        this.className = props.getProperty(LOGGER_CONTEXT_FACTORY);
        this.threadContextMap = props.getProperty(THREAD_CONTEXT_MAP);
        this.loggerContextFactoryClass = null;
        this.threadContextMapClass = null;
        this.versions = null;
    }

    public Provider(final Integer priority, final String versions) {
        this(priority, versions, null, null);
    }

    public Provider(final Integer priority, final String versions, final Class<? extends LoggerContextFactory> loggerContextFactoryClass) {
        this(priority, versions, loggerContextFactoryClass, null);
    }

    public Provider(final Integer priority, final String versions, final Class<? extends LoggerContextFactory> loggerContextFactoryClass, final Class<? extends ThreadContextMap> threadContextMapClass) {
        this.priority = priority != null ? priority.intValue() : -1;
        this.versions = versions;
        this.loggerContextFactoryClass = loggerContextFactoryClass;
        this.threadContextMapClass = threadContextMapClass;
        this.className = null;
        this.threadContextMap = null;
        this.url = null;
        this.classLoader = new WeakReference<>(null);
    }

    public String getVersions() {
        return this.versions != null ? this.versions : "";
    }

    public Integer getPriority() {
        return Integer.valueOf(this.priority);
    }

    public String getClassName() {
        return this.loggerContextFactoryClass != null ? this.loggerContextFactoryClass.getName() : this.className;
    }

    public Class<? extends LoggerContextFactory> loadLoggerContextFactory() {
        Class<?> clazz;
        if (this.loggerContextFactoryClass != null) {
            return this.loggerContextFactoryClass;
        }
        String className = getClassName();
        ClassLoader loader = this.classLoader.get();
        if (loader == null || className == null) {
            return null;
        }
        try {
            clazz = loader.loadClass(className);
        } catch (Exception e) {
            LOGGER.error("Unable to create class {} specified in {}", className, getUrl(), e);
        }
        if (LoggerContextFactory.class.isAssignableFrom(clazz)) {
            return clazz.asSubclass(LoggerContextFactory.class);
        }
        LOGGER.error("Class {} specified in {} does not extend {}", className, getUrl(), LoggerContextFactory.class.getName());
        return null;
    }

    public LoggerContextFactory getLoggerContextFactory() {
        Class<? extends LoggerContextFactory> implementation = loadLoggerContextFactory();
        if (implementation != null) {
            try {
                return (LoggerContextFactory) LoaderUtil.newInstanceOf(implementation);
            } catch (ReflectiveOperationException e) {
                LOGGER.error("Failed to instantiate logger context factory {}.", implementation.getName(), e);
            }
        }
        LOGGER.error("Falling back to simple logger context factory: {}", SimpleLoggerContextFactory.class.getName());
        return SimpleLoggerContextFactory.INSTANCE;
    }

    public String getThreadContextMap() {
        return this.threadContextMapClass != null ? this.threadContextMapClass.getName() : this.threadContextMap;
    }

    public Class<? extends ThreadContextMap> loadThreadContextMap() {
        Class<?> clazz;
        if (this.threadContextMapClass != null) {
            return this.threadContextMapClass;
        }
        String threadContextMap = getThreadContextMap();
        ClassLoader loader = this.classLoader.get();
        if (loader == null || threadContextMap == null) {
            return null;
        }
        try {
            clazz = loader.loadClass(threadContextMap);
        } catch (Exception e) {
            LOGGER.error("Unable to load class {} specified in {}", threadContextMap, this.url, e);
        }
        if (ThreadContextMap.class.isAssignableFrom(clazz)) {
            return clazz.asSubclass(ThreadContextMap.class);
        }
        LOGGER.error("Class {} specified in {} does not extend {}", threadContextMap, getUrl(), ThreadContextMap.class.getName());
        return null;
    }

    public ThreadContextMap getThreadContextMapInstance() {
        Class<? extends ThreadContextMap> implementation = loadThreadContextMap();
        if (implementation != null) {
            try {
                return (ThreadContextMap) LoaderUtil.newInstanceOf(implementation);
            } catch (ReflectiveOperationException e) {
                LOGGER.error("Failed to instantiate logger context factory {}.", implementation.getName(), e);
            }
        }
        PropertiesUtil props = PropertiesUtil.getProperties();
        if (props.getBooleanProperty(DISABLE_CONTEXT_MAP) || props.getBooleanProperty(DISABLE_THREAD_CONTEXT)) {
            return NoOpThreadContextMap.INSTANCE;
        }
        return new DefaultThreadContextMap();
    }

    @Deprecated
    public URL getUrl() {
        return this.url;
    }

    public String toString() {
        StringBuilder result = new StringBuilder("Provider '").append(getClass().getName()).append("'");
        if (this.priority != -1) {
            result.append("\n\tpriority = ").append(this.priority);
        }
        String threadContextMap = getThreadContextMap();
        if (threadContextMap != null) {
            result.append("\n\tthreadContextMap = ").append(threadContextMap);
        }
        String loggerContextFactory = getClassName();
        if (loggerContextFactory != null) {
            result.append("\n\tloggerContextFactory = ").append(loggerContextFactory);
        }
        if (this.url != null) {
            result.append("\n\turl = ").append(this.url);
        }
        if (Provider.class.equals(getClass())) {
            ClassLoader loader = this.classLoader.get();
            if (loader == null) {
                result.append("\n\tclassLoader = null or not reachable");
            } else {
                result.append("\n\tclassLoader = ").append(loader);
            }
        }
        return result.toString();
    }

    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Provider)) {
            return false;
        }
        Provider provider = (Provider) o;
        return Objects.equals(Integer.valueOf(this.priority), Integer.valueOf(provider.priority)) && Objects.equals(this.className, provider.className) && Objects.equals(this.loggerContextFactoryClass, provider.loggerContextFactoryClass) && Objects.equals(this.versions, provider.versions);
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.priority), this.className, this.loggerContextFactoryClass, this.versions);
    }
}
