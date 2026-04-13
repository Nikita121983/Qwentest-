package org.apache.logging.log4j.util;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Properties;
import java.util.ServiceLoader;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.simple.internal.SimpleProvider;
import org.apache.logging.log4j.spi.LoggerContextFactory;
import org.apache.logging.log4j.spi.Provider;
import org.apache.logging.log4j.status.StatusLogger;
import org.apache.logging.log4j.util.LoaderUtil;

/* loaded from: classes10.dex */
public final class ProviderUtil {
    private static volatile Provider PROVIDER = null;
    static final String PROVIDER_RESOURCE = "META-INF/log4j-provider.properties";
    static final Collection<Provider> PROVIDERS = new HashSet();
    static final Lock STARTUP_LOCK = new ReentrantLock();
    private static final String[] COMPATIBLE_API_VERSIONS = {"2.6.0"};
    private static final Logger LOGGER = StatusLogger.getLogger();

    private ProviderUtil() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void addProvider(final Provider provider) {
        if (validVersion(provider.getVersions())) {
            PROVIDERS.add(provider);
            LOGGER.debug("Loaded provider:\n{}", provider);
        } else {
            LOGGER.warn("Ignoring provider for incompatible version {}:\n{}", provider.getVersions(), provider);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void loadProvider(final URL url, final ClassLoader cl) {
        try {
            Properties props = PropertiesUtil.loadClose(url.openStream(), url);
            addProvider(new Provider(props, url, cl));
        } catch (IOException e) {
            LOGGER.error("Unable to open {}", url, e);
        }
    }

    @Deprecated
    static void loadProviders(final Enumeration<URL> urls, final ClassLoader cl) {
        if (urls != null) {
            while (urls.hasMoreElements()) {
                loadProvider(urls.nextElement(), cl);
            }
        }
    }

    public static Provider getProvider() {
        lazyInit();
        return PROVIDER;
    }

    public static Iterable<Provider> getProviders() {
        lazyInit();
        return PROVIDERS;
    }

    public static boolean hasProviders() {
        lazyInit();
        return !PROVIDERS.isEmpty();
    }

    static void lazyInit() {
        if (PROVIDER == null) {
            try {
                STARTUP_LOCK.lockInterruptibly();
                try {
                    if (PROVIDER == null) {
                        ServiceLoaderUtil.safeStream(Provider.class, ServiceLoader.load(Provider.class, ProviderUtil.class.getClassLoader()), LOGGER).filter(new Predicate() { // from class: org.apache.logging.log4j.util.ProviderUtil$$ExternalSyntheticLambda0
                            @Override // java.util.function.Predicate
                            public final boolean test(Object obj) {
                                boolean validVersion;
                                validVersion = ProviderUtil.validVersion(((Provider) obj).getVersions());
                                return validVersion;
                            }
                        }).forEach(new Consumer() { // from class: org.apache.logging.log4j.util.ProviderUtil$$ExternalSyntheticLambda1
                            @Override // java.util.function.Consumer
                            public final void accept(Object obj) {
                                ProviderUtil.addProvider((Provider) obj);
                            }
                        });
                        for (LoaderUtil.UrlResource resource : LoaderUtil.findUrlResources(PROVIDER_RESOURCE, false)) {
                            loadProvider(resource.getUrl(), resource.getClassLoader());
                        }
                        PROVIDER = selectProvider(PropertiesUtil.getProperties(), PROVIDERS, LOGGER);
                    }
                    STARTUP_LOCK.unlock();
                } catch (Throwable th) {
                    STARTUP_LOCK.unlock();
                    throw th;
                }
            } catch (InterruptedException e) {
                LOGGER.fatal("Interrupted before Log4j Providers could be loaded.", (Throwable) e);
                Thread.currentThread().interrupt();
            }
        }
    }

    static Provider selectProvider(final PropertiesUtil properties, final Collection<Provider> providers, final Logger statusLogger) {
        Provider selected = null;
        String providerClass = properties.getStringProperty(Provider.PROVIDER_PROPERTY_NAME);
        if (providerClass != null) {
            if (SimpleProvider.class.getName().equals(providerClass)) {
                selected = new SimpleProvider();
            } else {
                try {
                    selected = (Provider) LoaderUtil.newInstanceOf(providerClass);
                } catch (Exception e) {
                    statusLogger.error("Unable to create provider {}.\nFalling back to default selection process.", PROVIDER, e);
                }
            }
        }
        String factoryClassName = properties.getStringProperty(LogManager.FACTORY_PROPERTY_NAME);
        if (factoryClassName != null) {
            if (selected != null) {
                statusLogger.warn("Ignoring {} system property, since {} was set.", LogManager.FACTORY_PROPERTY_NAME, Provider.PROVIDER_PROPERTY_NAME);
            } else {
                statusLogger.warn("Usage of the {} property is deprecated. Use the {} property instead.", LogManager.FACTORY_PROPERTY_NAME, Provider.PROVIDER_PROPERTY_NAME);
                Iterator<Provider> it = providers.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Provider provider = it.next();
                    if (factoryClassName.equals(provider.getClassName())) {
                        selected = provider;
                        break;
                    }
                }
            }
            if (selected == null) {
                statusLogger.warn("No provider found using {} as logger context factory. The factory will be instantiated directly.", factoryClassName);
                try {
                    Class<?> clazz = LoaderUtil.loadClass(factoryClassName);
                    if (LoggerContextFactory.class.isAssignableFrom(clazz)) {
                        selected = new Provider((Integer) null, "", (Class<? extends LoggerContextFactory>) clazz.asSubclass(LoggerContextFactory.class));
                    } else {
                        statusLogger.error("Class {} specified in the {} system property does not extend {}", factoryClassName, LogManager.FACTORY_PROPERTY_NAME, LoggerContextFactory.class.getName());
                    }
                } catch (Exception e2) {
                    statusLogger.error("Unable to create class {} specified in the {} system property", factoryClassName, LogManager.FACTORY_PROPERTY_NAME, e2);
                }
            }
        }
        if (selected == null) {
            Comparator<Provider> comparator = Comparator.comparing(new Function() { // from class: org.apache.logging.log4j.util.ProviderUtil$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((Provider) obj).getPriority();
                }
            });
            switch (providers.size()) {
                case 0:
                    statusLogger.error("Log4j API could not find a logging provider.");
                    break;
                case 1:
                    break;
                default:
                    statusLogger.warn((String) providers.stream().sorted(comparator).map(new Function() { // from class: org.apache.logging.log4j.util.ProviderUtil$$ExternalSyntheticLambda3
                        @Override // java.util.function.Function
                        public final Object apply(Object obj) {
                            return ((Provider) obj).toString();
                        }
                    }).collect(Collectors.joining(StringUtils.LF, "Log4j API found multiple logging providers:\n", "")));
                    break;
            }
            selected = providers.stream().max(comparator).orElseGet(new java.util.function.Supplier() { // from class: org.apache.logging.log4j.util.ProviderUtil$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return new SimpleProvider();
                }
            });
        }
        statusLogger.info("Using provider:\n{}", selected);
        return selected;
    }

    public static ClassLoader findClassLoader() {
        return LoaderUtil.getThreadContextClassLoader();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean validVersion(final String version) {
        for (String v : COMPATIBLE_API_VERSIONS) {
            if (version.startsWith(v)) {
                return true;
            }
        }
        return false;
    }
}
