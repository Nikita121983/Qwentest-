package org.apache.xmlbeans.impl.xpath;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.store.Cur;
import org.apache.xmlbeans.impl.xpath.XPath;
import org.apache.xmlbeans.impl.xpath.saxon.SaxonXPath;
import org.apache.xmlbeans.impl.xpath.saxon.SaxonXQuery;
import org.apache.xmlbeans.impl.xpath.xmlbeans.XmlbeansXPath;

/* loaded from: classes11.dex */
public class XPathFactory {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int USE_SAXON = 4;
    private static final int USE_XMLBEANS = 1;
    private static final Map<String, WeakReference<Path>> _xmlbeansPathCache = new WeakHashMap();
    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    static String getCurrentNodeVar(XmlOptions options) {
        String currentNodeVar = "this";
        String cnv = XmlOptions.maskNull(options).getXqueryCurrentNodeVar();
        if (cnv != null) {
            currentNodeVar = cnv;
            if (currentNodeVar.startsWith("$")) {
                throw new IllegalArgumentException("Omit the '$' prefix for the current node variable");
            }
        }
        return currentNodeVar;
    }

    public static Path getCompiledPath(String pathExpr, XmlOptions options) {
        XmlOptions options2 = XmlOptions.maskNull(options);
        return getCompiledPath(pathExpr, options2, getCurrentNodeVar(options2));
    }

    public static Path getCompiledPath(String pathExpr, XmlOptions options, String currentVar) {
        int force = options.isXPathUseSaxon() ? 4 : options.isXPathUseXmlBeans() ? 1 : 5;
        WeakReference<Path> pathWeakRef = null;
        Map<String, String> namespaces = (force & 4) != 0 ? new HashMap<>() : null;
        lock.readLock().lock();
        if ((force & 1) != 0) {
            try {
                pathWeakRef = _xmlbeansPathCache.get(pathExpr);
            } catch (Throwable th) {
                lock.readLock().unlock();
                throw th;
            }
        }
        Path path = pathWeakRef != null ? pathWeakRef.get() : null;
        if (path != null) {
            lock.readLock().unlock();
            return path;
        }
        lock.readLock().unlock();
        lock.writeLock().lock();
        if ((force & 1) != 0) {
            try {
                WeakReference<Path> pathWeakRef2 = _xmlbeansPathCache.get(pathExpr);
                if (pathWeakRef2 != null) {
                    path = pathWeakRef2.get();
                }
                if (path == null) {
                    path = getCompiledPathXmlBeans(pathExpr, currentVar, namespaces);
                }
            } catch (Throwable th2) {
                lock.writeLock().unlock();
                throw th2;
            }
        }
        if (path == null && (force & 4) != 0) {
            path = getCompiledPathSaxon(pathExpr, currentVar, namespaces);
        }
        if (path != null) {
            lock.writeLock().unlock();
            return path;
        }
        StringBuilder errMessage = new StringBuilder();
        if ((force & 1) != 0) {
            errMessage.append(" Trying XmlBeans path engine...");
        }
        if ((force & 4) != 0) {
            errMessage.append(" Trying Saxon path engine...");
        }
        throw new RuntimeException(errMessage.toString() + " FAILED on " + pathExpr);
    }

    private static Path getCompiledPathXmlBeans(String pathExpr, String currentVar, Map<String, String> namespaces) {
        try {
            Path path = new XmlbeansXPath(pathExpr, currentVar, XPath.compileXPath(pathExpr, currentVar, namespaces));
            _xmlbeansPathCache.put(pathExpr, new WeakReference<>(path));
            return path;
        } catch (XPath.XPathCompileException e) {
            return null;
        }
    }

    public static Path getCompiledPathSaxon(String pathExpr, String currentVar, Map<String, String> namespaces) {
        if (namespaces == null) {
            namespaces = new HashMap();
        }
        try {
            XPath.compileXPath(pathExpr, currentVar, namespaces);
        } catch (XPath.XPathCompileException e) {
        }
        int offset = Integer.parseInt(namespaces.getOrDefault(XPath._NS_BOUNDARY, "0"));
        namespaces.remove(XPath._NS_BOUNDARY);
        return new SaxonXPath(pathExpr.substring(offset), currentVar, namespaces);
    }

    public static String compilePath(String pathExpr, XmlOptions options) {
        getCompiledPath(pathExpr, options);
        return pathExpr;
    }

    public static XmlObject[] objectExecQuery(Cur c, String queryExpr, XmlOptions options) {
        return getCompiledQuery(queryExpr, options).objectExecute(c, options);
    }

    public static XmlCursor cursorExecQuery(Cur c, String queryExpr, XmlOptions options) {
        return getCompiledQuery(queryExpr, options).cursorExecute(c, options);
    }

    public static synchronized XQuery getCompiledQuery(String queryExpr, XmlOptions options) {
        XQuery compiledQuery;
        synchronized (XPathFactory.class) {
            compiledQuery = getCompiledQuery(queryExpr, getCurrentNodeVar(options), options);
        }
        return compiledQuery;
    }

    static synchronized XQuery getCompiledQuery(String queryExpr, String currentVar, XmlOptions options) {
        String orDefault;
        SaxonXQuery saxonXQuery;
        synchronized (XPathFactory.class) {
            if (queryExpr == null) {
                throw new AssertionError();
            }
            XmlOptions options2 = XmlOptions.maskNull(options);
            Map<String, String> boundary = new HashMap<>();
            try {
                XPath.compileXPath(queryExpr, currentVar, boundary);
                orDefault = boundary.getOrDefault(XPath._NS_BOUNDARY, "0");
            } catch (XPath.XPathCompileException e) {
                orDefault = boundary.getOrDefault(XPath._NS_BOUNDARY, "0");
            } catch (Throwable th) {
                Integer.parseInt(boundary.getOrDefault(XPath._NS_BOUNDARY, "0"));
                throw th;
            }
            int boundaryVal = Integer.parseInt(orDefault);
            saxonXQuery = new SaxonXQuery(queryExpr, currentVar, Integer.valueOf(boundaryVal), options2);
        }
        return saxonXQuery;
    }

    public static synchronized String compileQuery(String queryExpr, XmlOptions options) {
        synchronized (XPathFactory.class) {
            getCompiledQuery(queryExpr, options);
        }
        return queryExpr;
    }
}
