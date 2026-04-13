package org.apache.logging.log4j.util;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Collection;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.function.BooleanSupplier;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.status.StatusLogger;

/* loaded from: classes10.dex */
public final class LoaderUtil {
    public static final String IGNORE_TCCL_PROPERTY = "log4j.ignoreTCL";
    private static Boolean ignoreTCCL;
    private static final Logger LOGGER = StatusLogger.getLogger();
    static final RuntimePermission GET_CLASS_LOADER = new RuntimePermission("getClassLoader");
    static final LazyBoolean GET_CLASS_LOADER_DISABLED = new LazyBoolean(new BooleanSupplier() { // from class: org.apache.logging.log4j.util.LoaderUtil$$ExternalSyntheticLambda3
        @Override // java.util.function.BooleanSupplier
        public final boolean getAsBoolean() {
            return LoaderUtil.lambda$static$1();
        }
    });
    private static final PrivilegedAction<ClassLoader> TCCL_GETTER = new ThreadContextClassLoaderGetter();

    static /* synthetic */ ClassLoader access$100() {
        return getThisClassLoader();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$static$1() {
        if (System.getSecurityManager() == null) {
            return false;
        }
        try {
            AccessController.checkPermission(GET_CLASS_LOADER);
            return false;
        } catch (SecurityException e) {
            try {
                AccessController.doPrivileged(new PrivilegedAction() { // from class: org.apache.logging.log4j.util.LoaderUtil$$ExternalSyntheticLambda2
                    @Override // java.security.PrivilegedAction
                    public final Object run() {
                        return LoaderUtil.lambda$static$0();
                    }
                });
                return false;
            } catch (SecurityException e2) {
                return true;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Void lambda$static$0() {
        AccessController.checkPermission(GET_CLASS_LOADER);
        return null;
    }

    private LoaderUtil() {
    }

    public static ClassLoader getClassLoader() {
        return getClassLoader(LoaderUtil.class, null);
    }

    public static ClassLoader getClassLoader(final Class<?> class1, final Class<?> class2) {
        PrivilegedAction<ClassLoader> action = new PrivilegedAction() { // from class: org.apache.logging.log4j.util.LoaderUtil$$ExternalSyntheticLambda0
            @Override // java.security.PrivilegedAction
            public final Object run() {
                return LoaderUtil.lambda$getClassLoader$2(class1, class2);
            }
        };
        return (ClassLoader) runPrivileged(action);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ ClassLoader lambda$getClassLoader$2(Class class1, Class class2) {
        ClassLoader referenceLoader;
        ClassLoader loader1 = class1 == null ? null : class1.getClassLoader();
        ClassLoader loader2 = class2 != null ? class2.getClassLoader() : null;
        if (GET_CLASS_LOADER_DISABLED.getAsBoolean()) {
            referenceLoader = getThisClassLoader();
        } else {
            referenceLoader = Thread.currentThread().getContextClassLoader();
        }
        return isChild(referenceLoader, loader1) ? isChild(referenceLoader, loader2) ? referenceLoader : loader2 : isChild(loader1, loader2) ? loader1 : loader2;
    }

    private static boolean isChild(final ClassLoader loader1, final ClassLoader loader2) {
        if (loader1 == null || loader2 == null) {
            return loader1 != null;
        }
        ClassLoader parent = loader1.getParent();
        while (parent != null && parent != loader2) {
            parent = parent.getParent();
        }
        return parent != null;
    }

    public static ClassLoader getThreadContextClassLoader() {
        try {
            return GET_CLASS_LOADER_DISABLED.getAsBoolean() ? getThisClassLoader() : (ClassLoader) runPrivileged(TCCL_GETTER);
        } catch (SecurityException e) {
            return null;
        }
    }

    private static ClassLoader getThisClassLoader() {
        return LoaderUtil.class.getClassLoader();
    }

    private static <T> T runPrivileged(PrivilegedAction<T> privilegedAction) {
        return System.getSecurityManager() != null ? (T) AccessController.doPrivileged(privilegedAction) : privilegedAction.run();
    }

    /* loaded from: classes10.dex */
    private static class ThreadContextClassLoaderGetter implements PrivilegedAction<ClassLoader> {
        private ThreadContextClassLoaderGetter() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.security.PrivilegedAction
        public ClassLoader run() {
            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            if (contextClassLoader != null) {
                return contextClassLoader;
            }
            ClassLoader thisClassLoader = LoaderUtil.access$100();
            if (thisClassLoader != null || LoaderUtil.GET_CLASS_LOADER_DISABLED.getAsBoolean()) {
                return thisClassLoader;
            }
            return ClassLoader.getSystemClassLoader();
        }
    }

    public static boolean isClassAvailable(final String className) {
        try {
            loadClass(className);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        } catch (LinkageError e2) {
            return false;
        } catch (Throwable error) {
            LOGGER.error("Unknown error while checking existence of class `{}`", className, error);
            return false;
        }
    }

    public static Class<?> loadClass(final String className) throws ClassNotFoundException {
        ClassLoader classLoader = isIgnoreTccl() ? getThisClassLoader() : getThreadContextClassLoader();
        if (classLoader == null) {
            classLoader = getThisClassLoader();
        }
        return Class.forName(className, true, classLoader);
    }

    public static Class<?> loadClassUnchecked(final String className) {
        try {
            return loadClass(className);
        } catch (ClassNotFoundException e) {
            NoClassDefFoundError error = new NoClassDefFoundError(e.getMessage());
            error.initCause(e);
            throw error;
        }
    }

    public static <T> T newInstanceOf(final Class<T> clazz) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Constructor<T> constructor = clazz.getDeclaredConstructor(new Class[0]);
        return constructor.newInstance(new Object[0]);
    }

    public static <T> T newInstanceOfUnchecked(Class<T> cls) {
        try {
            return (T) newInstanceOf(cls);
        } catch (IllegalAccessException e) {
            IllegalAccessError illegalAccessError = new IllegalAccessError(e.getMessage());
            illegalAccessError.initCause(e);
            throw illegalAccessError;
        } catch (InstantiationException e2) {
            InstantiationError instantiationError = new InstantiationError(e2.getMessage());
            instantiationError.initCause(e2);
            throw instantiationError;
        } catch (NoSuchMethodException e3) {
            NoSuchMethodError noSuchMethodError = new NoSuchMethodError(e3.getMessage());
            noSuchMethodError.initCause(e3);
            throw noSuchMethodError;
        } catch (InvocationTargetException e4) {
            throw new InternalException(e4.getCause());
        }
    }

    public static <T> T newInstanceOf(String str) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        return (T) newInstanceOf((Class) Cast.cast(loadClass(str)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object lambda$newCheckedInstanceOfProperty$3() {
        return null;
    }

    public static <T> T newCheckedInstanceOfProperty(String str, Class<T> cls) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        return (T) newCheckedInstanceOfProperty(str, cls, new java.util.function.Supplier() { // from class: org.apache.logging.log4j.util.LoaderUtil$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return LoaderUtil.lambda$newCheckedInstanceOfProperty$3();
            }
        });
    }

    public static <T> T newCheckedInstanceOfProperty(String str, Class<T> cls, java.util.function.Supplier<T> supplier) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        String stringProperty = PropertiesUtil.getProperties().getStringProperty(str);
        if (stringProperty == null) {
            return supplier.get();
        }
        return (T) newCheckedInstanceOf(stringProperty, cls);
    }

    public static <T> T newInstanceOfUnchecked(String str) {
        return (T) newInstanceOfUnchecked((Class) Cast.cast(loadClassUnchecked(str)));
    }

    public static <T> T newCheckedInstanceOf(String str, Class<T> cls) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        return (T) newInstanceOf(loadClass(str).asSubclass(cls));
    }

    public static <T> T newInstanceOfUnchecked(String str, Class<T> cls) {
        return (T) newInstanceOfUnchecked(loadClassUnchecked(str).asSubclass(cls));
    }

    private static boolean isIgnoreTccl() {
        if (ignoreTCCL == null) {
            String ignoreTccl = PropertiesUtil.getProperties().getStringProperty(IGNORE_TCCL_PROPERTY, null);
            ignoreTCCL = Boolean.valueOf((ignoreTccl == null || "false".equalsIgnoreCase(ignoreTccl.trim())) ? false : true);
        }
        return ignoreTCCL.booleanValue();
    }

    public static Collection<URL> findResources(final String resource) {
        return findResources(resource, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Collection<URL> findResources(final String resource, final boolean useTccl) {
        Collection<UrlResource> urlResources = findUrlResources(resource, useTccl);
        Collection<URL> resources = new LinkedHashSet<>(urlResources.size());
        for (UrlResource urlResource : urlResources) {
            resources.add(urlResource.getUrl());
        }
        return resources;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Collection<UrlResource> findUrlResources(final String resource, final boolean useTccl) {
        ClassLoader[] candidates = new ClassLoader[3];
        candidates[0] = useTccl ? getThreadContextClassLoader() : null;
        candidates[1] = LoaderUtil.class.getClassLoader();
        candidates[2] = GET_CLASS_LOADER_DISABLED.getAsBoolean() ? null : ClassLoader.getSystemClassLoader();
        Collection<UrlResource> resources = new LinkedHashSet<>();
        for (ClassLoader cl : candidates) {
            if (cl != null) {
                try {
                    Enumeration<URL> resourceEnum = cl.getResources(resource);
                    while (resourceEnum.hasMoreElements()) {
                        resources.add(new UrlResource(cl, resourceEnum.nextElement()));
                    }
                } catch (IOException error) {
                    LOGGER.error("failed to collect resources of name `{}`", resource, error);
                }
            }
        }
        return resources;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public static class UrlResource {
        private final ClassLoader classLoader;
        private final URL url;

        UrlResource(final ClassLoader classLoader, final URL url) {
            this.classLoader = classLoader;
            this.url = url;
        }

        public ClassLoader getClassLoader() {
            return this.classLoader;
        }

        public URL getUrl() {
            return this.url;
        }

        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof UrlResource)) {
                return false;
            }
            UrlResource that = (UrlResource) o;
            return Objects.equals(this.classLoader, that.classLoader) && Objects.equals(this.url, that.url);
        }

        public int hashCode() {
            return Objects.hashCode(this.classLoader) + Objects.hashCode(this.url);
        }
    }
}
