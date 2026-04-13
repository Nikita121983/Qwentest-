package org.apache.logging.log4j.util;

import java.util.Collection;
import java.util.Map;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.status.StatusLogger;
import org.apache.xmlbeans.impl.common.NameUtil;

/* loaded from: classes10.dex */
public class EnvironmentPropertySource implements PropertySource {
    private static final int DEFAULT_PRIORITY = 100;
    private static final String PREFIX = "LOG4J_";
    private static final Logger LOGGER = StatusLogger.getLogger();
    private static final PropertySource INSTANCE = new EnvironmentPropertySource();

    public static PropertySource provider() {
        return INSTANCE;
    }

    @Override // org.apache.logging.log4j.util.PropertySource
    public int getPriority() {
        return 100;
    }

    private static void logException(final SecurityException error) {
        LOGGER.error("The environment variables are not available to Log4j due to security restrictions.", (Throwable) error);
    }

    private static void logException(final SecurityException error, final String key) {
        LOGGER.error("The environment variable {} is not available to Log4j due to security restrictions.", key, error);
    }

    @Override // org.apache.logging.log4j.util.PropertySource
    public void forEach(final BiConsumer<String, String> action) {
        try {
            Map<String, String> getenv = System.getenv();
            for (Map.Entry<String, String> entry : getenv.entrySet()) {
                String key = entry.getKey();
                if (key.startsWith(PREFIX)) {
                    action.accept(key.substring(PREFIX.length()), entry.getValue());
                }
            }
        } catch (SecurityException e) {
            logException(e);
        }
    }

    @Override // org.apache.logging.log4j.util.PropertySource
    public CharSequence getNormalForm(final Iterable<? extends CharSequence> tokens) {
        StringBuilder sb = new StringBuilder("LOG4J");
        boolean empty = true;
        for (CharSequence token : tokens) {
            empty = false;
            sb.append(NameUtil.USCORE);
            for (int i = 0; i < token.length(); i++) {
                sb.append(Character.toUpperCase(token.charAt(i)));
            }
        }
        if (empty) {
            return null;
        }
        return sb.toString();
    }

    @Override // org.apache.logging.log4j.util.PropertySource
    public Collection<String> getPropertyNames() {
        try {
            return System.getenv().keySet();
        } catch (SecurityException e) {
            logException(e);
            return super.getPropertyNames();
        }
    }

    @Override // org.apache.logging.log4j.util.PropertySource
    public String getProperty(final String key) {
        try {
            return System.getenv(key);
        } catch (SecurityException e) {
            logException(e, key);
            return super.getProperty(key);
        }
    }

    @Override // org.apache.logging.log4j.util.PropertySource
    public boolean containsProperty(final String key) {
        try {
            return System.getenv().containsKey(key);
        } catch (SecurityException e) {
            logException(e, key);
            return super.containsProperty(key);
        }
    }
}
