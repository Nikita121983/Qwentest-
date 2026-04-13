package org.apache.logging.log4j.util;

import java.util.Collection;
import java.util.Objects;
import java.util.Properties;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.status.StatusLogger;
import org.apache.logging.log4j.util.PropertySource;

/* loaded from: classes10.dex */
public class SystemPropertiesPropertySource implements PropertySource {
    private static final int DEFAULT_PRIORITY = 0;
    private static final String PREFIX = "log4j2.";
    private static final Logger LOGGER = StatusLogger.getLogger();
    private static final PropertySource INSTANCE = new SystemPropertiesPropertySource();

    public static PropertySource provider() {
        return INSTANCE;
    }

    public static String getSystemProperty(final String key, final String defaultValue) {
        String value = INSTANCE.getProperty(key);
        return value != null ? value : defaultValue;
    }

    private static void logException(final SecurityException error) {
        LOGGER.error("The Java system properties are not available to Log4j due to security restrictions.", (Throwable) error);
    }

    private static void logException(final SecurityException error, final String key) {
        LOGGER.error("The Java system property {} is not available to Log4j due to security restrictions.", key, error);
    }

    @Override // org.apache.logging.log4j.util.PropertySource
    public int getPriority() {
        return 0;
    }

    @Override // org.apache.logging.log4j.util.PropertySource
    public void forEach(final BiConsumer<String, String> action) {
        Object[] keySet;
        try {
            Properties properties = System.getProperties();
            synchronized (properties) {
                keySet = properties.keySet().toArray();
            }
            for (Object key : keySet) {
                String keyStr = Objects.toString(key, null);
                action.accept(keyStr, properties.getProperty(keyStr));
            }
        } catch (SecurityException e) {
            logException(e);
        }
    }

    @Override // org.apache.logging.log4j.util.PropertySource
    public CharSequence getNormalForm(final Iterable<? extends CharSequence> tokens) {
        return PREFIX + ((Object) PropertySource.Util.joinAsCamelCase(tokens));
    }

    @Override // org.apache.logging.log4j.util.PropertySource
    public Collection<String> getPropertyNames() {
        try {
            return System.getProperties().stringPropertyNames();
        } catch (SecurityException e) {
            logException(e);
            return super.getPropertyNames();
        }
    }

    @Override // org.apache.logging.log4j.util.PropertySource
    public String getProperty(final String key) {
        try {
            return System.getProperty(key);
        } catch (SecurityException e) {
            logException(e, key);
            return super.getProperty(key);
        }
    }

    @Override // org.apache.logging.log4j.util.PropertySource
    public boolean containsProperty(final String key) {
        return getProperty(key) != null;
    }
}
