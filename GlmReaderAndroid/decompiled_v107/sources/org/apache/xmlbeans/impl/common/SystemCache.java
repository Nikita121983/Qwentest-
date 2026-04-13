package org.apache.xmlbeans.impl.common;

import java.lang.ref.SoftReference;
import java.lang.reflect.InvocationTargetException;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.SystemProperties;

/* loaded from: classes11.dex */
public class SystemCache {
    private static SystemCache INSTANCE = initCache();
    private ThreadLocal<SoftReference> tl_saxLoaders = new ThreadLocal<>();

    public static synchronized void set(SystemCache instance) {
        synchronized (SystemCache.class) {
            INSTANCE = instance;
        }
    }

    public static synchronized SystemCache get() {
        SystemCache systemCache;
        synchronized (SystemCache.class) {
            systemCache = INSTANCE;
        }
        return systemCache;
    }

    public SchemaTypeLoader getFromTypeLoaderCache(ClassLoader cl) {
        return null;
    }

    public void addToTypeLoaderCache(SchemaTypeLoader stl, ClassLoader cl) {
    }

    public void clearThreadLocals() {
        this.tl_saxLoaders.remove();
    }

    public Object getSaxLoader() {
        SoftReference s = this.tl_saxLoaders.get();
        if (s == null) {
            return null;
        }
        return s.get();
    }

    public void setSaxLoader(Object saxLoader) {
        this.tl_saxLoaders.set(new SoftReference(saxLoader));
    }

    private static SystemCache initCache() {
        String cacheClass = SystemProperties.getProperty("xmlbean.systemcacheimpl");
        if (cacheClass == null) {
            return new SystemCache();
        }
        String errPrefix = "Could not instantiate class " + cacheClass + " as specified by \"xmlbean.systemcacheimpl\". ";
        try {
            return (SystemCache) Class.forName(cacheClass).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (ClassCastException e) {
            throw new ClassCastException(errPrefix + "Class does not derive from SystemCache.");
        } catch (ClassNotFoundException cnfe) {
            throw new RuntimeException(errPrefix + "Class was not found.", cnfe);
        } catch (IllegalAccessException iae) {
            throw new RuntimeException(errPrefix + "A public empty constructor may be missing.", iae);
        } catch (InstantiationException e2) {
            ie = e2;
            throw new RuntimeException(errPrefix + "An empty constructor may be missing.", ie);
        } catch (NoSuchMethodException e3) {
            ie = e3;
            throw new RuntimeException(errPrefix + "An empty constructor may be missing.", ie);
        } catch (InvocationTargetException e4) {
            ie = e4;
            throw new RuntimeException(errPrefix + "An empty constructor may be missing.", ie);
        }
    }
}
