package org.apache.logging.log4j.util;

import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Predicate;
import org.apache.commons.lang3.SystemProperties;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.status.StatusLogger;

/* loaded from: classes10.dex */
public final class StackLocator {
    private static final Method GET_CALLER_CLASS_METHOD;
    private static final StackLocator INSTANCE;
    static final int JDK_7U25_OFFSET;
    private static final Logger LOGGER = StatusLogger.getLogger();
    private static final Class<?> DEFAULT_CALLER_CLASS = null;

    static {
        String str;
        Method getCallerClassMethod;
        Class<?> sunReflectionClass;
        Object o;
        int java7u25CompensationOffset = 0;
        try {
            sunReflectionClass = Class.forName("sun.reflect.Reflection", true, ClassLoader.getSystemClassLoader());
            getCallerClassMethod = sunReflectionClass.getDeclaredMethod("getCallerClass", Integer.TYPE);
            o = getCallerClassMethod.invoke(null, 0);
            getCallerClassMethod.invoke(null, 0);
        } catch (Exception | LinkageError e) {
            Logger logger = LOGGER;
            if (System.getProperty(SystemProperties.JAVA_VERSION, "").startsWith("1.8")) {
                str = "`sun.reflect.Reflection.getCallerClass(int)` is not supported. This will impact location-based features.";
            } else {
                str = "Runtime environment or build system does not support multi-release JARs. This will impact location-based features.";
            }
            logger.warn(str);
            getCallerClassMethod = null;
            java7u25CompensationOffset = -1;
        }
        if (o != null && o == sunReflectionClass) {
            if (getCallerClassMethod.invoke(null, 1) == sunReflectionClass) {
                LOGGER.warn("Unexpected result from `sun.reflect.Reflection.getCallerClass(int)`, adjusting offset for future calls.");
                java7u25CompensationOffset = 1;
            }
            GET_CALLER_CLASS_METHOD = getCallerClassMethod;
            JDK_7U25_OFFSET = java7u25CompensationOffset;
            INSTANCE = new StackLocator();
        }
        getCallerClassMethod = null;
        java7u25CompensationOffset = -1;
        GET_CALLER_CLASS_METHOD = getCallerClassMethod;
        JDK_7U25_OFFSET = java7u25CompensationOffset;
        INSTANCE = new StackLocator();
    }

    public static StackLocator getInstance() {
        return INSTANCE;
    }

    private StackLocator() {
    }

    public Class<?> getCallerClass(final Class<?> sentinelClass, final Predicate<Class<?>> callerPredicate) {
        if (sentinelClass == null) {
            throw new IllegalArgumentException("sentinelClass cannot be null");
        }
        if (callerPredicate == null) {
            throw new IllegalArgumentException("callerPredicate cannot be null");
        }
        boolean foundSentinel = false;
        int i = 2;
        while (true) {
            Class<?> clazz = getCallerClass(i);
            if (clazz != null) {
                if (sentinelClass.equals(clazz)) {
                    foundSentinel = true;
                } else if (foundSentinel && callerPredicate.test(clazz)) {
                    return clazz;
                }
                i++;
            } else {
                return DEFAULT_CALLER_CLASS;
            }
        }
    }

    public Class<?> getCallerClass(final int depth) {
        if (depth < 0) {
            throw new IndexOutOfBoundsException(Integer.toString(depth));
        }
        if (GET_CALLER_CLASS_METHOD == null) {
            return DEFAULT_CALLER_CLASS;
        }
        try {
            return (Class) GET_CALLER_CLASS_METHOD.invoke(null, Integer.valueOf(depth + 1 + JDK_7U25_OFFSET));
        } catch (Exception e) {
            return DEFAULT_CALLER_CLASS;
        }
    }

    public Class<?> getCallerClass(final String fqcn, final String pkg) {
        boolean next = false;
        int i = 2;
        while (true) {
            Class<?> clazz = getCallerClass(i);
            if (clazz != null) {
                if (fqcn.equals(clazz.getName())) {
                    next = true;
                } else if (next && clazz.getName().startsWith(pkg)) {
                    return clazz;
                }
                i++;
            } else {
                return DEFAULT_CALLER_CLASS;
            }
        }
    }

    public Class<?> getCallerClass(final Class<?> anchor) {
        boolean next = false;
        int i = 2;
        while (true) {
            Class<?> clazz = getCallerClass(i);
            if (clazz != null) {
                if (anchor.equals(clazz)) {
                    next = true;
                } else if (next) {
                    return clazz;
                }
                i++;
            } else {
                return Object.class;
            }
        }
    }

    public Deque<Class<?>> getCurrentStackTrace() {
        if (PrivateSecurityManagerStackTraceUtil.isEnabled()) {
            return PrivateSecurityManagerStackTraceUtil.getCurrentStackTrace();
        }
        Deque<Class<?>> classes = new ArrayDeque<>();
        int i = 1;
        while (true) {
            Class<?> clazz = getCallerClass(i);
            if (clazz != null) {
                classes.addLast(clazz);
                i++;
            } else {
                return classes;
            }
        }
    }

    public StackTraceElement calcLocation(final String fqcnOfLogger) {
        if (fqcnOfLogger == null) {
            return null;
        }
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        boolean found = false;
        for (int i = 0; i < stackTrace.length; i++) {
            String className = stackTrace[i].getClassName();
            if (fqcnOfLogger.equals(className)) {
                found = true;
            } else if (found && !fqcnOfLogger.equals(className)) {
                return stackTrace[i];
            }
        }
        return null;
    }

    public StackTraceElement getStackTraceElement(final int depth) {
        int i = 0;
        for (StackTraceElement element : new Throwable().getStackTrace()) {
            if (isValid(element)) {
                if (i == depth) {
                    return element;
                }
                i++;
            }
        }
        throw new IndexOutOfBoundsException(Integer.toString(depth));
    }

    private boolean isValid(final StackTraceElement element) {
        if (element.isNativeMethod()) {
            return false;
        }
        String cn = element.getClassName();
        if (cn.startsWith("sun.reflect.")) {
            return false;
        }
        String mn = element.getMethodName();
        if ((cn.startsWith("java.lang.reflect.") && (mn.equals("invoke") || mn.equals("newInstance"))) || cn.startsWith("jdk.internal.reflect.")) {
            return false;
        }
        if (cn.equals("java.lang.Class") && mn.equals("newInstance")) {
            return false;
        }
        return (cn.equals("java.lang.invoke.MethodHandle") && mn.startsWith("invoke")) ? false : true;
    }
}
