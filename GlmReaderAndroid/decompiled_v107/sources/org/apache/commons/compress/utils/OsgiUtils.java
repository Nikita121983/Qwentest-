package org.apache.commons.compress.utils;

/* loaded from: classes9.dex */
public class OsgiUtils {
    private static final boolean inOsgiEnvironment;

    static {
        ClassLoader classLoader = OsgiUtils.class.getClassLoader();
        inOsgiEnvironment = classLoader != null && isBundleReference(classLoader.getClass());
    }

    private static boolean isBundleReference(Class<?> clazz) {
        Class<?> c = clazz;
        while (true) {
            if (c == null) {
                return false;
            }
            if (c.getName().equals("org.osgi.framework.BundleReference")) {
                return true;
            }
            for (Class<?> ifc : c.getInterfaces()) {
                if (isBundleReference(ifc)) {
                    return true;
                }
            }
            c = c.getSuperclass();
        }
    }

    public static boolean isRunningInOsgiEnvironment() {
        return inOsgiEnvironment;
    }
}
