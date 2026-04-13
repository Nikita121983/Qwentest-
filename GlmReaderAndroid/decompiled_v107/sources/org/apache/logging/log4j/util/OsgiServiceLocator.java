package org.apache.logging.log4j.util;

import java.lang.invoke.MethodHandles;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.status.StatusLogger;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.wiring.BundleRevision;

/* loaded from: classes10.dex */
public class OsgiServiceLocator {
    private static final Logger LOGGER = StatusLogger.getLogger();
    private static final boolean OSGI_AVAILABLE = checkOsgiAvailable();

    private static boolean checkOsgiAvailable() {
        try {
            Class<?> clazz = Class.forName("org.osgi.framework.FrameworkUtil");
            return clazz.getMethod("getBundle", Class.class).invoke(null, OsgiServiceLocator.class) != null;
        } catch (ClassNotFoundException e) {
            return false;
        } catch (LinkageError e2) {
            return false;
        } catch (NoSuchMethodException e3) {
            return false;
        } catch (Throwable error) {
            LOGGER.error("Unknown error checking OSGI environment.", error);
            return false;
        }
    }

    public static boolean isAvailable() {
        return OSGI_AVAILABLE;
    }

    public static <T> Stream<T> loadServices(final Class<T> serviceType, final MethodHandles.Lookup lookup) {
        return loadServices((Class) serviceType, lookup, true);
    }

    public static <T> Stream<T> loadServices(final Class<T> serviceType, final MethodHandles.Lookup lookup, final boolean verbose) {
        Class<?> lookupClass = ((MethodHandles.Lookup) Objects.requireNonNull(lookup, "lookup")).lookupClass();
        return loadServices(serviceType, lookupClass, StatusLogger.getLogger());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> Stream<T> loadServices(final Class<T> serviceType, final Class<?> callerClass, final Logger logger) {
        final Bundle bundle = FrameworkUtil.getBundle(callerClass);
        if (bundle != null && !isFragment(bundle)) {
            final BundleContext ctx = bundle.getBundleContext();
            if (ctx == null) {
                Objects.requireNonNull(serviceType);
                Objects.requireNonNull(bundle);
                logger.warn("Unable to load OSGi services for service {}: bundle {} (state {}) does not have a valid BundleContext", new Supplier() { // from class: org.apache.logging.log4j.util.OsgiServiceLocator$$ExternalSyntheticLambda0
                    @Override // org.apache.logging.log4j.util.Supplier, java.util.function.Supplier
                    public final Object get() {
                        String name;
                        name = serviceType.getName();
                        return name;
                    }
                }, new Supplier() { // from class: org.apache.logging.log4j.util.OsgiServiceLocator$$ExternalSyntheticLambda1
                    @Override // org.apache.logging.log4j.util.Supplier, java.util.function.Supplier
                    public final Object get() {
                        return bundle.getSymbolicName();
                    }
                }, new Supplier() { // from class: org.apache.logging.log4j.util.OsgiServiceLocator$$ExternalSyntheticLambda2
                    @Override // org.apache.logging.log4j.util.Supplier, java.util.function.Supplier
                    public final Object get() {
                        return OsgiServiceLocator.lambda$loadServices$0(bundle);
                    }
                });
            } else {
                try {
                    Stream stream = ctx.getServiceReferences(serviceType, (String) null).stream();
                    Objects.requireNonNull(ctx);
                    return stream.map(new Function() { // from class: org.apache.logging.log4j.util.OsgiServiceLocator$$ExternalSyntheticLambda3
                        @Override // java.util.function.Function
                        public final Object apply(Object obj) {
                            return ctx.getService((ServiceReference) obj);
                        }
                    });
                } catch (Exception e) {
                    logger.error("Unable to load OSGI services for service {}", serviceType, e);
                }
            }
        }
        return Stream.empty();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object lambda$loadServices$0(Bundle bundle) {
        switch (bundle.getState()) {
            case 1:
                return "UNINSTALLED";
            case 2:
                return "INSTALLED";
            case 4:
                return "RESOLVED";
            case 8:
                return "STARTING";
            case 16:
                return "STOPPING";
            case 32:
                return "ACTIVE";
            default:
                return "UNKNOWN";
        }
    }

    private static boolean isFragment(final Bundle bundle) {
        try {
            return (((BundleRevision) bundle.adapt(BundleRevision.class)).getTypes() & 1) != 0;
        } catch (SecurityException e) {
            return false;
        }
    }
}
