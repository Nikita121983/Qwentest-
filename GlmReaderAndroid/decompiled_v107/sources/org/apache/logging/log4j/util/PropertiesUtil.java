package org.apache.logging.log4j.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.SystemProperties;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.status.StatusLogger;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.apache.logging.log4j.util.PropertySource;

/* loaded from: classes10.dex */
public final class PropertiesUtil {
    private static final String LOG4J_PROPERTIES_FILE_NAME = "log4j2.component.properties";
    private static final String LOG4J_SYSTEM_PROPERTIES_FILE_NAME = "log4j2.system.properties";
    private final Environment environment;
    private static final Logger LOGGER = StatusLogger.getLogger();
    private static final Lazy<PropertiesUtil> COMPONENT_PROPERTIES = Lazy.lazy(new java.util.function.Supplier() { // from class: org.apache.logging.log4j.util.PropertiesUtil$$ExternalSyntheticLambda0
        @Override // java.util.function.Supplier
        public final Object get() {
            return PropertiesUtil.lambda$static$0();
        }
    });
    private static final Pattern DURATION_PATTERN = Pattern.compile("([+-]?\\d+)\\s*(\\w+)?", 2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ PropertiesUtil lambda$static$0() {
        return new PropertiesUtil(LOG4J_PROPERTIES_FILE_NAME, false);
    }

    public PropertiesUtil(final Properties props) {
        this(new PropertiesPropertySource(props));
    }

    public PropertiesUtil(final String propertiesFileName) {
        this(propertiesFileName, true);
    }

    private PropertiesUtil(final String propertiesFileName, final boolean useTccl) {
        this(new PropertyFilePropertySource(propertiesFileName, useTccl));
    }

    PropertiesUtil(final PropertySource source) {
        this.environment = new Environment(source);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Properties loadClose(final InputStream in, final Object source) {
        Properties props = new Properties();
        try {
            if (in != null) {
                try {
                    try {
                        props.load(in);
                        in.close();
                    } catch (IOException error) {
                        LOGGER.error("Unable to read source `{}`", source, error);
                        in.close();
                    }
                } catch (Throwable th) {
                    try {
                        in.close();
                    } catch (IOException error2) {
                        LOGGER.error("Unable to close source `{}`", source, error2);
                    }
                    throw th;
                }
            }
        } catch (IOException error3) {
            LOGGER.error("Unable to close source `{}`", source, error3);
        }
        return props;
    }

    public static PropertiesUtil getProperties() {
        return COMPONENT_PROPERTIES.get();
    }

    public void addPropertySource(final PropertySource propertySource) {
        this.environment.addPropertySource((PropertySource) Objects.requireNonNull(propertySource));
    }

    public void removePropertySource(final PropertySource propertySource) {
        this.environment.removePropertySource((PropertySource) Objects.requireNonNull(propertySource));
    }

    public boolean hasProperty(final String name) {
        return this.environment.containsKey(name);
    }

    public boolean getBooleanProperty(final String name) {
        return getBooleanProperty(name, false);
    }

    public boolean getBooleanProperty(final String name, final boolean defaultValue) {
        String prop = getStringProperty(name);
        return prop == null ? defaultValue : "true".equalsIgnoreCase(prop);
    }

    public boolean getBooleanProperty(final String name, final boolean defaultValueIfAbsent, final boolean defaultValueIfPresent) {
        String prop = getStringProperty(name);
        if (prop == null) {
            return defaultValueIfAbsent;
        }
        return prop.isEmpty() ? defaultValueIfPresent : "true".equalsIgnoreCase(prop);
    }

    public Boolean getBooleanProperty(final String[] prefixes, final String key, final Supplier<Boolean> supplier) {
        for (String prefix : prefixes) {
            if (hasProperty(prefix + key)) {
                return Boolean.valueOf(getBooleanProperty(prefix + key));
            }
        }
        if (supplier != null) {
            return supplier.get();
        }
        return null;
    }

    public Charset getCharsetProperty(final String name) {
        return getCharsetProperty(name, Charset.defaultCharset());
    }

    public Charset getCharsetProperty(final String name, final Charset defaultValue) {
        String charsetName = getStringProperty(name);
        if (charsetName == null) {
            return defaultValue;
        }
        if (Charset.isSupported(charsetName)) {
            return Charset.forName(charsetName);
        }
        ResourceBundle bundle = getCharsetsResourceBundle();
        if (bundle.containsKey(name)) {
            String mapped = bundle.getString(name);
            if (Charset.isSupported(mapped)) {
                return Charset.forName(mapped);
            }
        }
        LOGGER.warn("Unable to read charset `{}` from property `{}`. Falling back to the default: `{}`", charsetName, name, defaultValue);
        return defaultValue;
    }

    public double getDoubleProperty(final String name, final double defaultValue) {
        String prop = getStringProperty(name);
        if (prop != null) {
            try {
                return Double.parseDouble(prop);
            } catch (NumberFormatException e) {
                LOGGER.warn("Unable to read double `{}` from property `{}`. Falling back to the default: `{}`", prop, name, Double.valueOf(defaultValue), e);
            }
        }
        return defaultValue;
    }

    public int getIntegerProperty(final String name, final int defaultValue) {
        String prop = getStringProperty(name);
        if (prop != null) {
            try {
                return Integer.parseInt(prop.trim());
            } catch (NumberFormatException e) {
                LOGGER.warn("Unable to read int `{}` from property `{}`. Falling back to the default: `{}`", prop, name, Integer.valueOf(defaultValue), e);
            }
        }
        return defaultValue;
    }

    public Integer getIntegerProperty(final String[] prefixes, final String key, final Supplier<Integer> supplier) {
        for (String prefix : prefixes) {
            if (hasProperty(prefix + key)) {
                return Integer.valueOf(getIntegerProperty(prefix + key, 0));
            }
        }
        if (supplier != null) {
            return supplier.get();
        }
        return null;
    }

    public long getLongProperty(final String name, final long defaultValue) {
        String prop = getStringProperty(name);
        if (prop != null) {
            try {
                return Long.parseLong(prop);
            } catch (NumberFormatException e) {
                LOGGER.warn("Unable to read long `{}` from property `{}`. Falling back to the default: `{}`", prop, name, Long.valueOf(defaultValue), e);
            }
        }
        return defaultValue;
    }

    public Long getLongProperty(final String[] prefixes, final String key, final Supplier<Long> supplier) {
        for (String prefix : prefixes) {
            if (hasProperty(prefix + key)) {
                return Long.valueOf(getLongProperty(prefix + key, 0L));
            }
        }
        if (supplier != null) {
            return supplier.get();
        }
        return null;
    }

    public Duration getDurationProperty(final String name, final Duration defaultValue) {
        String prop = getStringProperty(name);
        try {
            return parseDuration(prop);
        } catch (IllegalArgumentException e) {
            LOGGER.warn("Unable to read duration `{}` from property `{}`.\nExpected format 'n unit', where 'n' is an integer and 'unit' is one of: {}.", prop, name, TimeUnit.access$400().collect(Collectors.joining(", ")), e);
            return defaultValue;
        }
    }

    public Duration getDurationProperty(final String[] prefixes, final String key, final Supplier<Duration> supplier) {
        for (String prefix : prefixes) {
            if (hasProperty(prefix + key)) {
                return getDurationProperty(prefix + key, null);
            }
        }
        if (supplier != null) {
            return supplier.get();
        }
        return null;
    }

    public String getStringProperty(final String[] prefixes, final String key, final Supplier<String> supplier) {
        for (String prefix : prefixes) {
            String result = getStringProperty(prefix + key);
            if (result != null) {
                return result;
            }
        }
        if (supplier != null) {
            return supplier.get();
        }
        return null;
    }

    public String getStringProperty(final String name) {
        return this.environment.get(name);
    }

    public String getStringProperty(final String name, final String defaultValue) {
        String prop = getStringProperty(name);
        return prop == null ? defaultValue : prop;
    }

    public static Properties getSystemProperties() {
        try {
            return new Properties(System.getProperties());
        } catch (SecurityException error) {
            LOGGER.error("Unable to access system properties.", (Throwable) error);
            return new Properties();
        }
    }

    @Deprecated
    public void reload() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static final class Environment {
        private final ThreadLocal<PropertySource> CURRENT_PROPERTY_SOURCE;
        private final Set<PropertySource> sources;

        private Environment(final PropertySource propertySource) {
            this.sources = ConcurrentHashMap.newKeySet();
            this.CURRENT_PROPERTY_SOURCE = new ThreadLocal<>();
            PropertySource sysProps = new PropertyFilePropertySource(PropertiesUtil.LOG4J_SYSTEM_PROPERTIES_FILE_NAME, false);
            try {
                sysProps.forEach(new BiConsumer() { // from class: org.apache.logging.log4j.util.PropertiesUtil$Environment$$ExternalSyntheticLambda2
                    @Override // org.apache.logging.log4j.util.BiConsumer, java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        PropertiesUtil.Environment.lambda$new$0((String) obj, (String) obj2);
                    }
                });
            } catch (SecurityException e) {
                PropertiesUtil.LOGGER.warn("Unable to set Java system properties from {} file, due to security restrictions.", PropertiesUtil.LOG4J_SYSTEM_PROPERTIES_FILE_NAME, e);
            }
            this.sources.add(propertySource);
            Stream safeStream = ServiceLoaderUtil.safeStream(PropertySource.class, ServiceLoader.load(PropertySource.class, PropertiesUtil.class.getClassLoader()), PropertiesUtil.LOGGER);
            final Set<PropertySource> set = this.sources;
            Objects.requireNonNull(set);
            safeStream.forEach(new Consumer() { // from class: org.apache.logging.log4j.util.PropertiesUtil$Environment$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    set.add((PropertySource) obj);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$new$0(String key, String value) {
            if (System.getProperty(key) == null) {
                System.setProperty(key, value);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addPropertySource(final PropertySource propertySource) {
            this.sources.add(propertySource);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removePropertySource(final PropertySource propertySource) {
            this.sources.remove(propertySource);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String get(final String key) {
            final List<CharSequence> tokens = PropertySource.Util.tokenize(key);
            return (String) this.sources.stream().sorted(PropertySource.Comparator.INSTANCE).map(new Function() { // from class: org.apache.logging.log4j.util.PropertiesUtil$Environment$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return PropertiesUtil.Environment.this.m2208xb254f456(tokens, key, (PropertySource) obj);
                }
            }).filter(new Predicate() { // from class: org.apache.logging.log4j.util.PropertiesUtil$Environment$$ExternalSyntheticLambda1
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    boolean nonNull;
                    nonNull = Objects.nonNull((String) obj);
                    return nonNull;
                }
            }).findFirst().orElse(null);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$get$1$org-apache-logging-log4j-util-PropertiesUtil$Environment, reason: not valid java name */
        public /* synthetic */ String m2208xb254f456(List tokens, String key, PropertySource source) {
            String normalKey;
            if (!tokens.isEmpty() && (normalKey = Objects.toString(source.getNormalForm(tokens), null)) != null && sourceContainsProperty(source, normalKey)) {
                return sourceGetProperty(source, normalKey);
            }
            return sourceGetProperty(source, key);
        }

        private boolean sourceContainsProperty(final PropertySource source, final String key) {
            PropertySource recursiveSource = this.CURRENT_PROPERTY_SOURCE.get();
            if (recursiveSource == null) {
                this.CURRENT_PROPERTY_SOURCE.set(source);
                try {
                    return source.containsProperty(key);
                } catch (Exception e) {
                    PropertiesUtil.LOGGER.warn("Failed to retrieve Log4j property {} from property source {}.", key, source, e);
                } finally {
                    this.CURRENT_PROPERTY_SOURCE.remove();
                }
            }
            PropertiesUtil.LOGGER.warn("Recursive call to `containsProperty()` from property source {}.", recursiveSource);
            return false;
        }

        private String sourceGetProperty(final PropertySource source, final String key) {
            PropertySource recursiveSource = this.CURRENT_PROPERTY_SOURCE.get();
            if (recursiveSource == null) {
                this.CURRENT_PROPERTY_SOURCE.set(source);
                try {
                    return source.getProperty(key);
                } catch (Exception e) {
                    PropertiesUtil.LOGGER.warn("Failed to retrieve Log4j property {} from property source {}.", key, source, e);
                } finally {
                    this.CURRENT_PROPERTY_SOURCE.remove();
                }
            }
            PropertiesUtil.LOGGER.warn("Recursive call to `getProperty()` from property source {}.", recursiveSource);
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean containsKey(final String key) {
            final List<CharSequence> tokens = PropertySource.Util.tokenize(key);
            return this.sources.stream().anyMatch(new Predicate() { // from class: org.apache.logging.log4j.util.PropertiesUtil$Environment$$ExternalSyntheticLambda4
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return PropertiesUtil.Environment.this.m2207x2f46fbcb(tokens, key, (PropertySource) obj);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$containsKey$2$org-apache-logging-log4j-util-PropertiesUtil$Environment, reason: not valid java name */
        public /* synthetic */ boolean m2207x2f46fbcb(List tokens, String key, PropertySource s) {
            CharSequence normalizedKey = tokens.isEmpty() ? null : s.getNormalForm(tokens);
            return sourceContainsProperty(s, key) || (normalizedKey != null && sourceContainsProperty(s, normalizedKey.toString()));
        }
    }

    public static Properties extractSubset(final Properties properties, final String prefix) {
        Properties subset = new Properties();
        if (prefix == null || prefix.isEmpty()) {
            return subset;
        }
        String prefixToMatch = prefix.charAt(prefix.length() + (-1)) != '.' ? prefix + '.' : prefix;
        Collection<String> keys = new ArrayList<>();
        for (String key : properties.stringPropertyNames()) {
            if (key.startsWith(prefixToMatch)) {
                subset.setProperty(key.substring(prefixToMatch.length()), properties.getProperty(key));
                keys.add(key);
            }
        }
        Iterator<String> it = keys.iterator();
        while (it.hasNext()) {
            properties.remove(it.next());
        }
        return subset;
    }

    static ResourceBundle getCharsetsResourceBundle() {
        return ResourceBundle.getBundle("Log4j-charsets");
    }

    public static Map<String, Properties> partitionOnCommonPrefixes(final Properties properties) {
        return partitionOnCommonPrefixes(properties, false);
    }

    public static Map<String, Properties> partitionOnCommonPrefixes(final Properties properties, final boolean includeBaseKey) {
        Map<String, Properties> parts = new ConcurrentHashMap<>();
        for (String key : properties.stringPropertyNames()) {
            int idx = key.indexOf(46);
            if (idx < 0) {
                if (includeBaseKey) {
                    if (!parts.containsKey(key)) {
                        parts.put(key, new Properties());
                    }
                    parts.get(key).setProperty("", properties.getProperty(key));
                }
            } else {
                String prefix = key.substring(0, idx);
                if (!parts.containsKey(prefix)) {
                    parts.put(prefix, new Properties());
                }
                parts.get(prefix).setProperty(key.substring(idx + 1), properties.getProperty(key));
            }
        }
        return parts;
    }

    public boolean isOsWindows() {
        return SystemPropertiesPropertySource.getSystemProperty(SystemProperties.OS_NAME, "").startsWith("Windows");
    }

    static Duration parseDuration(final CharSequence value) {
        Matcher matcher = DURATION_PATTERN.matcher(value);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid duration value '" + ((Object) value) + "'.");
        }
        return Duration.of(parseDurationAmount(matcher.group(1)), TimeUnit.parseUnit(matcher.group(2)));
    }

    private static long parseDurationAmount(final String amount) {
        try {
            return Long.parseLong(amount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid duration amount '" + amount + "'", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public enum TimeUnit {
        NANOS(new String[]{"ns", "nano", "nanos", "nanosecond", "nanoseconds"}, ChronoUnit.NANOS),
        MICROS(new String[]{"us", "micro", "micros", "microsecond", "microseconds"}, ChronoUnit.MICROS),
        MILLIS(new String[]{"ms", "milli", "millis", "millisecond", "milliseconds"}, ChronoUnit.MILLIS),
        SECONDS(new String[]{"s", "second", "seconds"}, ChronoUnit.SECONDS),
        MINUTES(new String[]{"m", "minute", "minutes"}, ChronoUnit.MINUTES),
        HOURS(new String[]{"h", "hour", "hours"}, ChronoUnit.HOURS),
        DAYS(new String[]{"d", "day", "days"}, ChronoUnit.DAYS);

        private final String[] descriptions;
        private final TemporalUnit timeUnit;

        static /* synthetic */ Stream access$400() {
            return getValidUnits();
        }

        TimeUnit(final String[] descriptions, final TemporalUnit timeUnit) {
            this.descriptions = descriptions;
            this.timeUnit = timeUnit;
        }

        private static Stream<String> getValidUnits() {
            return Arrays.stream(values()).flatMap(new Function() { // from class: org.apache.logging.log4j.util.PropertiesUtil$TimeUnit$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    Stream stream;
                    stream = Arrays.stream(((PropertiesUtil.TimeUnit) obj).descriptions);
                    return stream;
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static TemporalUnit parseUnit(final String unit) {
            if (unit != null) {
                for (TimeUnit value : values()) {
                    for (String description : value.descriptions) {
                        if (unit.equals(description)) {
                            return value.timeUnit;
                        }
                    }
                }
                throw new IllegalArgumentException("Invalid duration unit '" + unit + "'");
            }
            return ChronoUnit.MILLIS;
        }
    }
}
