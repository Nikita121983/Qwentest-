package org.apache.logging.log4j.status;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.MessageFactory;
import org.apache.logging.log4j.message.ParameterizedNoReferenceMessageFactory;
import org.apache.logging.log4j.spi.AbstractLogger;
import org.apache.logging.log4j.status.StatusLogger;

/* loaded from: classes10.dex */
public class StatusLogger extends AbstractLogger {
    private static final String DEBUG_PROPERTY_NAME = "log4j2.debug";
    static final int DEFAULT_FALLBACK_LISTENER_BUFFER_CAPACITY = 0;
    static final Level DEFAULT_FALLBACK_LISTENER_LEVEL = Level.ERROR;
    public static final String DEFAULT_STATUS_LISTENER_LEVEL = "log4j2.StatusLogger.level";
    public static final String MAX_STATUS_ENTRIES = "log4j2.status.entries";
    public static final String PROPERTIES_FILE_NAME = "log4j2.StatusLogger.properties";
    public static final String STATUS_DATE_FORMAT = "log4j2.StatusLogger.dateFormat";
    static final String STATUS_DATE_FORMAT_ZONE = "log4j2.StatusLogger.dateFormatZone";
    private static final long serialVersionUID = 2;
    private final Queue<StatusData> buffer;
    private final Config config;
    private final StatusConsoleListener fallbackListener;
    private final transient ReadWriteLock listenerLock;
    private final transient Lock listenerReadLock;
    private final transient Lock listenerWriteLock;
    private final List<StatusListener> listeners;

    /* loaded from: classes10.dex */
    public static final class Config {
        private static final Config INSTANCE = new Config();
        final int bufferCapacity;
        final boolean debugEnabled;
        final Level fallbackListenerLevel;
        final DateTimeFormatter instantFormatter;

        public Config(boolean debugEnabled, int bufferCapacity, DateTimeFormatter instantFormatter) {
            this.debugEnabled = debugEnabled;
            if (bufferCapacity < 0) {
                throw new IllegalArgumentException("was expecting a positive `bufferCapacity`, found: " + bufferCapacity);
            }
            this.bufferCapacity = bufferCapacity;
            this.fallbackListenerLevel = null;
            this.instantFormatter = instantFormatter;
        }

        private Config() {
            this(PropertiesUtilsDouble.readAllAvailableProperties());
        }

        Config(final Properties... propertiesList) {
            this((Map<String, Object>) PropertiesUtilsDouble.normalizeProperties(propertiesList));
        }

        private Config(final Map<String, Object> normalizedProperties) {
            this.debugEnabled = readDebugEnabled(normalizedProperties);
            this.bufferCapacity = readBufferCapacity(normalizedProperties);
            this.fallbackListenerLevel = readFallbackListenerLevel(normalizedProperties);
            this.instantFormatter = readInstantFormatter(normalizedProperties);
        }

        public static Config getInstance() {
            return INSTANCE;
        }

        private static boolean readDebugEnabled(final Map<String, Object> normalizedProperties) {
            String debug = PropertiesUtilsDouble.readProperty(normalizedProperties, "log4j2.debug");
            return (debug == null || "false".equalsIgnoreCase(debug)) ? false : true;
        }

        private static int readBufferCapacity(final Map<String, Object> normalizedProperties) {
            String capacityString = PropertiesUtilsDouble.readProperty(normalizedProperties, StatusLogger.MAX_STATUS_ENTRIES);
            if (capacityString == null) {
                return 0;
            }
            try {
                int capacity = Integer.parseInt(capacityString);
                if (capacity < 0) {
                    String message = String.format("was expecting a positive buffer capacity, found: %d", Integer.valueOf(capacity));
                    throw new IllegalArgumentException(message);
                }
                return capacity;
            } catch (Exception error) {
                String message2 = String.format("Failed reading the buffer capacity from the `%s` property: `%s`. Falling back to the default: %d.", StatusLogger.MAX_STATUS_ENTRIES, capacityString, 0);
                IllegalArgumentException extendedError = new IllegalArgumentException(message2, error);
                extendedError.printStackTrace(System.err);
                return 0;
            }
        }

        private static Level readFallbackListenerLevel(final Map<String, Object> normalizedProperties) {
            String level = PropertiesUtilsDouble.readProperty(normalizedProperties, StatusLogger.DEFAULT_STATUS_LISTENER_LEVEL);
            Level defaultLevel = StatusLogger.DEFAULT_FALLBACK_LISTENER_LEVEL;
            if (level == null) {
                return defaultLevel;
            }
            try {
                return Level.valueOf(level);
            } catch (Exception error) {
                String message = String.format("Failed reading the level from the `%s` property: `%s`. Falling back to the default: `%s`.", StatusLogger.DEFAULT_STATUS_LISTENER_LEVEL, level, defaultLevel);
                IllegalArgumentException extendedError = new IllegalArgumentException(message, error);
                extendedError.printStackTrace(System.err);
                return defaultLevel;
            }
        }

        private static DateTimeFormatter readInstantFormatter(final Map<String, Object> normalizedProperties) {
            String format = PropertiesUtilsDouble.readProperty(normalizedProperties, StatusLogger.STATUS_DATE_FORMAT);
            if (format == null) {
                return null;
            }
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
                String zoneIdString = PropertiesUtilsDouble.readProperty(normalizedProperties, StatusLogger.STATUS_DATE_FORMAT_ZONE);
                ZoneId defaultZoneId = ZoneId.systemDefault();
                ZoneId zoneId = defaultZoneId;
                if (zoneIdString != null) {
                    try {
                        zoneId = ZoneId.of(zoneIdString);
                    } catch (Exception error) {
                        String message = String.format("Failed reading the instant formatting zone ID from the `%s` property: `%s`. Falling back to the default: `%s`.", StatusLogger.STATUS_DATE_FORMAT_ZONE, zoneIdString, defaultZoneId);
                        IllegalArgumentException extendedError = new IllegalArgumentException(message, error);
                        extendedError.printStackTrace(System.err);
                    }
                }
                return formatter.withZone(zoneId);
            } catch (Exception error2) {
                String message2 = String.format("failed reading the instant format from the `%s` property: `%s`", StatusLogger.STATUS_DATE_FORMAT, format);
                IllegalArgumentException extendedError2 = new IllegalArgumentException(message2, error2);
                extendedError2.printStackTrace(System.err);
                return null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public static final class PropertiesUtilsDouble {
        PropertiesUtilsDouble() {
        }

        static String readProperty(final Map<String, Object> normalizedProperties, final String propertyName) {
            String normalizedPropertyName = normalizePropertyName(propertyName);
            Object value = normalizedProperties.get(normalizedPropertyName);
            if (value instanceof String) {
                return (String) value;
            }
            return null;
        }

        static Map<String, Object> readAllAvailableProperties() {
            Properties systemProperties = System.getProperties();
            Properties environmentProperties = readEnvironmentProperties();
            Properties fileProvidedProperties = readPropertiesFile(StatusLogger.PROPERTIES_FILE_NAME);
            return normalizeProperties(systemProperties, environmentProperties, fileProvidedProperties);
        }

        private static Properties readEnvironmentProperties() {
            Properties properties = new Properties();
            properties.putAll(System.getenv());
            return properties;
        }

        static Properties readPropertiesFile(final String propertiesFileName) {
            Properties properties = new Properties();
            String resourceName = '/' + propertiesFileName;
            URL url = StatusLogger.class.getResource(resourceName);
            if (url == null) {
                return properties;
            }
            try {
                InputStream stream = url.openStream();
                try {
                    properties.load(stream);
                    if (stream != null) {
                        stream.close();
                    }
                } finally {
                }
            } catch (IOException error) {
                String message = String.format("failed reading properties from `%s`", propertiesFileName);
                RuntimeException extendedError = new RuntimeException(message, error);
                extendedError.printStackTrace(System.err);
            }
            return properties;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Map<String, Object> normalizeProperties(Properties... propertiesList) {
            final Map<String, Object> map = new HashMap<>();
            for (Properties properties : propertiesList) {
                properties.forEach(new BiConsumer() { // from class: org.apache.logging.log4j.status.StatusLogger$PropertiesUtilsDouble$$ExternalSyntheticLambda0
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        StatusLogger.PropertiesUtilsDouble.lambda$normalizeProperties$0(map, obj, obj2);
                    }
                });
            }
            return map;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$normalizeProperties$0(Map map, Object name, Object value) {
            boolean relevant = isRelevantPropertyName(name);
            if (relevant) {
                String normalizedName = normalizePropertyName((String) name);
                map.put(normalizedName, value);
            }
        }

        private static boolean isRelevantPropertyName(final Object propertyName) {
            return (propertyName instanceof String) && ((String) propertyName).matches("^(?i)log4j.*");
        }

        private static String normalizePropertyName(final String propertyName) {
            return propertyName.replaceAll("[._-]", "").replaceAll("\\P{InBasic_Latin}", ".").toLowerCase(Locale.US).replaceAll("^log4j2", "log4j");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static final class InstanceHolder {
        private static volatile StatusLogger INSTANCE = new StatusLogger();

        private InstanceHolder() {
        }
    }

    StatusLogger() {
        this(StatusLogger.class.getSimpleName(), ParameterizedNoReferenceMessageFactory.INSTANCE, Config.getInstance(), new StatusConsoleListener((Level) Objects.requireNonNull(Config.getInstance().fallbackListenerLevel)));
    }

    public StatusLogger(final String name, final MessageFactory messageFactory, final Config config, final StatusConsoleListener fallbackListener) {
        super((String) Objects.requireNonNull(name, "name"), (MessageFactory) Objects.requireNonNull(messageFactory, "messageFactory"));
        this.listenerLock = new ReentrantReadWriteLock();
        this.listenerReadLock = this.listenerLock.readLock();
        this.listenerWriteLock = this.listenerLock.writeLock();
        this.buffer = new ConcurrentLinkedQueue();
        this.config = (Config) Objects.requireNonNull(config, "config");
        this.fallbackListener = (StatusConsoleListener) Objects.requireNonNull(fallbackListener, "fallbackListener");
        this.listeners = new ArrayList();
    }

    public static StatusLogger getLogger() {
        return InstanceHolder.INSTANCE;
    }

    public static void setLogger(final StatusLogger logger) {
        StatusLogger unused = InstanceHolder.INSTANCE = (StatusLogger) Objects.requireNonNull(logger, "logger");
    }

    public StatusConsoleListener getFallbackListener() {
        return this.fallbackListener;
    }

    @Deprecated
    public void setLevel(final Level level) {
        Objects.requireNonNull(level, "level");
        this.fallbackListener.setLevel(level);
    }

    public void registerListener(final StatusListener listener) {
        Objects.requireNonNull(listener, "listener");
        this.listenerWriteLock.lock();
        try {
            this.listeners.add(listener);
        } finally {
            this.listenerWriteLock.unlock();
        }
    }

    public void removeListener(final StatusListener listener) {
        Objects.requireNonNull(listener, "listener");
        this.listenerWriteLock.lock();
        try {
            this.listeners.remove(listener);
            closeListenerSafely(listener);
        } finally {
            this.listenerWriteLock.unlock();
        }
    }

    @Deprecated
    public void updateListenerLevel(final Level level) {
        Objects.requireNonNull(level, "level");
        this.fallbackListener.setLevel(level);
    }

    public Iterable<StatusListener> getListeners() {
        this.listenerReadLock.lock();
        try {
            return Collections.unmodifiableCollection(this.listeners);
        } finally {
            this.listenerReadLock.unlock();
        }
    }

    public void reset() {
        this.listenerWriteLock.lock();
        try {
            Iterator<StatusListener> listenerIterator = this.listeners.iterator();
            while (listenerIterator.hasNext()) {
                StatusListener listener = listenerIterator.next();
                closeListenerSafely(listener);
                listenerIterator.remove();
            }
            this.listenerWriteLock.unlock();
            this.fallbackListener.close();
            this.buffer.clear();
        } catch (Throwable th) {
            this.listenerWriteLock.unlock();
            throw th;
        }
    }

    private static void closeListenerSafely(final StatusListener listener) {
        try {
            listener.close();
        } catch (IOException error) {
            String message = String.format("failed closing listener: %s", listener);
            RuntimeException extendedError = new RuntimeException(message, error);
            extendedError.printStackTrace(System.err);
        }
    }

    @Deprecated
    public List<StatusData> getStatusData() {
        return Collections.unmodifiableList(new ArrayList(this.buffer));
    }

    @Deprecated
    public void clear() {
        this.buffer.clear();
    }

    @Override // org.apache.logging.log4j.Logger
    public Level getLevel() {
        Level leastSpecificLevel = this.fallbackListener.getStatusLevel();
        for (int listenerIndex = 0; listenerIndex < this.listeners.size(); listenerIndex++) {
            StatusListener listener = this.listeners.get(listenerIndex);
            Level listenerLevel = listener.getStatusLevel();
            if (listenerLevel.isLessSpecificThan(leastSpecificLevel)) {
                leastSpecificLevel = listenerLevel;
            }
        }
        return leastSpecificLevel;
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public void logMessage(final String fqcn, final Level level, final Marker marker, final Message message, final Throwable throwable) {
        try {
            StatusData statusData = createStatusData(fqcn, level, message, throwable);
            buffer(statusData);
            notifyListeners(statusData);
        } catch (Exception error) {
            error.printStackTrace(System.err);
        }
    }

    private void buffer(final StatusData statusData) {
        if (this.config.bufferCapacity == 0) {
            return;
        }
        this.buffer.add(statusData);
        while (this.buffer.size() >= this.config.bufferCapacity) {
            this.buffer.remove();
        }
    }

    private void notifyListeners(final StatusData statusData) {
        this.listenerReadLock.lock();
        try {
            boolean foundListeners = !this.listeners.isEmpty();
            this.listeners.forEach(new Consumer() { // from class: org.apache.logging.log4j.status.StatusLogger$$ExternalSyntheticLambda0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    StatusLogger.this.m2205x576699e5(statusData, (StatusListener) obj);
                }
            });
            if (!foundListeners) {
                m2205x576699e5(this.fallbackListener, statusData);
            }
        } finally {
            this.listenerReadLock.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: notifyListener, reason: merged with bridge method [inline-methods] */
    public void m2205x576699e5(final StatusListener listener, final StatusData statusData) {
        boolean levelEnabled = isLevelEnabled(listener.getStatusLevel(), statusData.getLevel());
        if (levelEnabled) {
            listener.log(statusData);
        }
    }

    private StatusData createStatusData(final String fqcn, final Level level, final Message message, final Throwable throwable) {
        StackTraceElement caller = getStackTraceElement(fqcn);
        Instant instant = Instant.now();
        return new StatusData(caller, level, message, throwable, null, this.config.instantFormatter, instant);
    }

    private static StackTraceElement getStackTraceElement(final String fqcn) {
        if (fqcn == null) {
            return null;
        }
        boolean next = false;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement element : stackTrace) {
            String className = element.getClassName();
            if (next && !fqcn.equals(className)) {
                return element;
            }
            if (fqcn.equals(className)) {
                next = true;
            } else if ("?".equals(className)) {
                break;
            }
        }
        return null;
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(final Level level, final Marker marker, final String message, final Throwable throwable) {
        return isEnabled(level, marker);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(final Level level, final Marker marker, final String message) {
        return isEnabled(level, marker);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(final Level level, final Marker marker, final String message, final Object... params) {
        return isEnabled(level, marker);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(final Level level, final Marker marker, final String message, final Object p0) {
        return isEnabled(level, marker);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(final Level level, final Marker marker, final String message, final Object p0, final Object p1) {
        return isEnabled(level, marker);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(final Level level, final Marker marker, final String message, final Object p0, final Object p1, final Object p2) {
        return isEnabled(level, marker);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(final Level level, final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3) {
        return isEnabled(level, marker);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(final Level level, final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4) {
        return isEnabled(level, marker);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(final Level level, final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5) {
        return isEnabled(level, marker);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(final Level level, final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6) {
        return isEnabled(level, marker);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(final Level level, final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7) {
        return isEnabled(level, marker);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(final Level level, final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8) {
        return isEnabled(level, marker);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(final Level level, final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8, final Object p9) {
        return isEnabled(level, marker);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(final Level level, final Marker marker, final CharSequence message, final Throwable throwable) {
        return isEnabled(level, marker);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(final Level level, final Marker marker, final Object message, final Throwable throwable) {
        return isEnabled(level, marker);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(final Level level, final Marker marker, final Message message, final Throwable throwable) {
        return isEnabled(level, marker);
    }

    @Override // org.apache.logging.log4j.spi.AbstractLogger, org.apache.logging.log4j.Logger
    public boolean isEnabled(final Level messageLevel, final Marker marker) {
        Objects.requireNonNull(messageLevel, "messageLevel");
        Level loggerLevel = getLevel();
        return isLevelEnabled(loggerLevel, messageLevel);
    }

    private boolean isLevelEnabled(final Level filteringLevel, final Level messageLevel) {
        return this.config.debugEnabled || filteringLevel.isLessSpecificThan(messageLevel);
    }
}
