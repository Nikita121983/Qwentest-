package org.apache.xmlbeans.impl.xpath;

import java.util.Map;
import org.apache.xmlbeans.XmlError;
import org.apache.xmlbeans.XmlException;

/* loaded from: classes11.dex */
public class XPath {
    public static final String _DEFAULT_ELT_NS = "$xmlbeans!default_uri";
    public static final String _NS_BOUNDARY = "$xmlbeans!ns_boundary";
    private final boolean _sawDeepDot;
    final Selector _selector;

    /* loaded from: classes11.dex */
    public static class XPathCompileException extends XmlException {
        /* JADX INFO: Access modifiers changed from: package-private */
        public XPathCompileException(XmlError err) {
            super(err.toString(), (Throwable) null, err);
        }
    }

    public static XPath compileXPath(String xpath) throws XPathCompileException {
        return compileXPath(xpath, "$this", null);
    }

    public static XPath compileXPath(String xpath, String currentNodeVar) throws XPathCompileException {
        return compileXPath(xpath, currentNodeVar, null);
    }

    public static XPath compileXPath(String xpath, Map<String, String> namespaces) throws XPathCompileException {
        return compileXPath(xpath, "$this", namespaces);
    }

    public static XPath compileXPath(String xpath, String currentNodeVar, Map<String, String> namespaces) throws XPathCompileException {
        return new XPathCompilationContext(namespaces, currentNodeVar).compile(xpath);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class Selector {
        final XPathStep[] _paths;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Selector(XPathStep[] paths) {
            this._paths = paths;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public XPath(Selector selector, boolean sawDeepDot) {
        this._selector = selector;
        this._sawDeepDot = sawDeepDot;
    }

    public boolean sawDeepDot() {
        return this._sawDeepDot;
    }
}
