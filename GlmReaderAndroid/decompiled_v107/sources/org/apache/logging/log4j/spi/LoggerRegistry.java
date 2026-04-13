package org.apache.logging.log4j.spi;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.logging.log4j.message.MessageFactory;
import org.apache.logging.log4j.message.ParameterizedMessageFactory;
import org.apache.logging.log4j.spi.ExtendedLogger;

/* loaded from: classes10.dex */
public class LoggerRegistry<T extends ExtendedLogger> {
    private final ReadWriteLock lock;
    private final Map<String, Map<MessageFactory, T>> loggerByMessageFactoryByName;
    private final Lock readLock;
    private final Lock writeLock;

    /* loaded from: classes10.dex */
    public interface MapFactory<T extends ExtendedLogger> {
        Map<String, T> createInnerMap();

        Map<String, Map<String, T>> createOuterMap();

        void putIfAbsent(Map<String, T> innerMap, String name, T logger);
    }

    /* loaded from: classes10.dex */
    public static class ConcurrentMapFactory<T extends ExtendedLogger> implements MapFactory<T> {
        @Override // org.apache.logging.log4j.spi.LoggerRegistry.MapFactory
        public Map<String, T> createInnerMap() {
            return new ConcurrentHashMap();
        }

        @Override // org.apache.logging.log4j.spi.LoggerRegistry.MapFactory
        public Map<String, Map<String, T>> createOuterMap() {
            return new ConcurrentHashMap();
        }

        @Override // org.apache.logging.log4j.spi.LoggerRegistry.MapFactory
        public void putIfAbsent(final Map<String, T> innerMap, final String name, final T logger) {
            innerMap.putIfAbsent(name, logger);
        }
    }

    /* loaded from: classes10.dex */
    public static class WeakMapFactory<T extends ExtendedLogger> implements MapFactory<T> {
        @Override // org.apache.logging.log4j.spi.LoggerRegistry.MapFactory
        public Map<String, T> createInnerMap() {
            return new WeakHashMap();
        }

        @Override // org.apache.logging.log4j.spi.LoggerRegistry.MapFactory
        public Map<String, Map<String, T>> createOuterMap() {
            return new WeakHashMap();
        }

        @Override // org.apache.logging.log4j.spi.LoggerRegistry.MapFactory
        public void putIfAbsent(final Map<String, T> innerMap, final String name, final T logger) {
            innerMap.put(name, logger);
        }
    }

    public LoggerRegistry() {
        this.loggerByMessageFactoryByName = new HashMap();
        this.lock = new ReentrantReadWriteLock();
        this.readLock = this.lock.readLock();
        this.writeLock = this.lock.writeLock();
    }

    public LoggerRegistry(final MapFactory<T> mapFactory) {
        this();
    }

    public T getLogger(final String name) {
        Objects.requireNonNull(name, "name");
        return getLogger(name, null);
    }

    public T getLogger(final String name, final MessageFactory messageFactory) {
        Objects.requireNonNull(name, "name");
        this.readLock.lock();
        try {
            Map<MessageFactory, T> loggerByMessageFactory = this.loggerByMessageFactoryByName.get(name);
            MessageFactory effectiveMessageFactory = messageFactory != null ? messageFactory : ParameterizedMessageFactory.INSTANCE;
            return loggerByMessageFactory == null ? null : loggerByMessageFactory.get(effectiveMessageFactory);
        } finally {
            this.readLock.unlock();
        }
    }

    public Collection<T> getLoggers() {
        this.readLock.lock();
        try {
            return (Collection) this.loggerByMessageFactoryByName.values().stream().flatMap(new Function() { // from class: org.apache.logging.log4j.spi.LoggerRegistry$$ExternalSyntheticLambda1
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    Stream stream;
                    stream = ((Map) obj).values().stream();
                    return stream;
                }
            }).collect(Collectors.toList());
        } finally {
            this.readLock.unlock();
        }
    }

    public Collection<T> getLoggers(final Collection<T> destination) {
        Objects.requireNonNull(destination, "destination");
        destination.addAll(getLoggers());
        return destination;
    }

    public boolean hasLogger(final String name) {
        Objects.requireNonNull(name, "name");
        T logger = getLogger(name);
        return logger != null;
    }

    public boolean hasLogger(final String name, final MessageFactory messageFactory) {
        Objects.requireNonNull(name, "name");
        T logger = getLogger(name, messageFactory);
        return logger != null;
    }

    public boolean hasLogger(final String name, final Class<? extends MessageFactory> messageFactoryClass) {
        Objects.requireNonNull(name, "name");
        Objects.requireNonNull(messageFactoryClass, "messageFactoryClass");
        this.readLock.lock();
        try {
            return this.loggerByMessageFactoryByName.getOrDefault(name, Collections.emptyMap()).keySet().stream().anyMatch(new Predicate() { // from class: org.apache.logging.log4j.spi.LoggerRegistry$$ExternalSyntheticLambda0
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    boolean equals;
                    equals = messageFactoryClass.equals(((MessageFactory) obj).getClass());
                    return equals;
                }
            });
        } finally {
            this.readLock.unlock();
        }
    }

    public void putIfAbsent(final String name, final MessageFactory messageFactory, final T logger) {
        MessageFactory effectiveMessageFactory;
        Objects.requireNonNull(name, "name");
        Objects.requireNonNull(logger, "logger");
        this.writeLock.lock();
        if (messageFactory != null) {
            effectiveMessageFactory = messageFactory;
        } else {
            try {
                effectiveMessageFactory = ParameterizedMessageFactory.INSTANCE;
            } finally {
                this.writeLock.unlock();
            }
        }
        this.loggerByMessageFactoryByName.computeIfAbsent(name, new Function() { // from class: org.apache.logging.log4j.spi.LoggerRegistry$$ExternalSyntheticLambda2
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Map createLoggerRefByMessageFactoryMap;
                createLoggerRefByMessageFactoryMap = LoggerRegistry.this.createLoggerRefByMessageFactoryMap((String) obj);
                return createLoggerRefByMessageFactoryMap;
            }
        }).putIfAbsent(effectiveMessageFactory, logger);
        if (!name.equals(logger.getName()) || !effectiveMessageFactory.equals(logger.getMessageFactory())) {
            this.loggerByMessageFactoryByName.computeIfAbsent(logger.getName(), new Function() { // from class: org.apache.logging.log4j.spi.LoggerRegistry$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    Map createLoggerRefByMessageFactoryMap;
                    createLoggerRefByMessageFactoryMap = LoggerRegistry.this.createLoggerRefByMessageFactoryMap((String) obj);
                    return createLoggerRefByMessageFactoryMap;
                }
            }).putIfAbsent(logger.getMessageFactory(), logger);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map<MessageFactory, T> createLoggerRefByMessageFactoryMap(final String ignored) {
        return new WeakHashMap();
    }
}
