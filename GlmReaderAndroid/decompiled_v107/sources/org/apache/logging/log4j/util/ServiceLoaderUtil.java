package org.apache.logging.log4j.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.apache.logging.log4j.Logger;

/* loaded from: classes10.dex */
public final class ServiceLoaderUtil {
    private static final int MAX_BROKEN_SERVICES = 8;

    private ServiceLoaderUtil() {
    }

    public static <S> Stream<S> safeStream(final Class<S> serviceType, final ServiceLoader<? extends S> serviceLoader, final Logger logger) {
        Stream<S> allServices;
        Objects.requireNonNull(serviceLoader, "serviceLoader");
        final Collection<Class<?>> classes = new HashSet<>();
        Stream<S> services = StreamSupport.stream(new ServiceLoaderSpliterator(serviceType, serviceLoader, logger), false);
        Class<?> callerClass = StackLocatorUtil.getCallerClass(2);
        if (OsgiServiceLocator.isAvailable() && callerClass != null) {
            allServices = Stream.concat(services, OsgiServiceLocator.loadServices(serviceType, callerClass, logger));
        } else {
            allServices = services;
        }
        return allServices.filter(new Predicate() { // from class: org.apache.logging.log4j.util.ServiceLoaderUtil$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean add;
                add = classes.add(obj.getClass());
                return add;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static final class ServiceLoaderSpliterator<S> extends Spliterators.AbstractSpliterator<S> {
        private final Logger logger;
        private final Iterator<? extends S> serviceIterator;
        private final String serviceName;

        private ServiceLoaderSpliterator(final Class<S> serviceType, final Iterable<? extends S> serviceLoader, final Logger logger) {
            super(Long.MAX_VALUE, 1296);
            this.serviceName = serviceType.getName();
            this.serviceIterator = serviceLoader.iterator();
            this.logger = logger;
        }

        @Override // java.util.Spliterator
        public boolean tryAdvance(Consumer<? super S> consumer) {
            int i = 8;
            while (true) {
                int i2 = i - 1;
                if (i > 0) {
                    try {
                    } catch (Exception e) {
                        this.logger.warn("Unexpected exception  while loading implementation for service {}", this.serviceName, e);
                        throw e;
                    } catch (LinkageError e2) {
                        e = e2;
                        this.logger.warn("Unable to load implementation for service {}", this.serviceName, e);
                        i = i2;
                    } catch (ServiceConfigurationError e3) {
                        e = e3;
                        this.logger.warn("Unable to load implementation for service {}", this.serviceName, e);
                        i = i2;
                    }
                    if (!this.serviceIterator.hasNext()) {
                        continue;
                        i = i2;
                    } else {
                        consumer.accept(this.serviceIterator.next());
                        return true;
                    }
                } else {
                    return false;
                }
            }
        }
    }
}
