package org.apache.xmlbeans.impl.xpath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.xml.namespace.QName;
import org.apache.logging.log4j.util.Chars;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.xmlbeans.XmlError;
import org.apache.xmlbeans.impl.common.XMLChar;
import org.apache.xmlbeans.impl.xpath.XPath;

/* loaded from: classes11.dex */
class XPathCompilationContext {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private int _column;
    private final String _currentNodeVar;
    private String _expr;
    private final Map<String, String> _externalNamespaces;
    private boolean _lastDeepDot;
    private int _line;
    protected final Map<String, String> _namespaces = new HashMap();
    private int _offset;
    private boolean _sawDeepDot;

    /* JADX INFO: Access modifiers changed from: package-private */
    public XPathCompilationContext(Map<String, String> namespaces, String currentNodeVar) {
        this._currentNodeVar = currentNodeVar == null ? "$this" : currentNodeVar;
        this._externalNamespaces = namespaces == null ? new HashMap<>() : namespaces;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public XPath compile(String expr) throws XPath.XPathCompileException {
        this._offset = 0;
        this._line = 1;
        this._column = 1;
        this._expr = expr;
        return tokenizeXPath();
    }

    int currChar() {
        return currChar(0);
    }

    int currChar(int offset) {
        if (this._offset + offset >= this._expr.length()) {
            return -1;
        }
        return this._expr.charAt(this._offset + offset);
    }

    void advance() {
        if (this._offset < this._expr.length()) {
            char ch = this._expr.charAt(this._offset);
            this._offset++;
            this._column++;
            if (ch == '\r' || ch == '\n') {
                this._line++;
                this._column = 1;
                if (this._offset + 1 < this._expr.length()) {
                    char nextCh = this._expr.charAt(this._offset + 1);
                    if ((nextCh == '\r' || nextCh == '\n') && ch != nextCh) {
                        this._offset++;
                    }
                }
            }
        }
    }

    void advance(int count) {
        if (count < 0) {
            throw new AssertionError();
        }
        while (true) {
            int count2 = count - 1;
            if (count > 0) {
                advance();
                count = count2;
            } else {
                return;
            }
        }
    }

    boolean isWhitespace() {
        return isWhitespace(0);
    }

    boolean isWhitespace(int offset) {
        int ch = currChar(offset);
        return ch == 32 || ch == 9 || ch == 10 || ch == 13;
    }

    boolean isNCNameStart() {
        return currChar() != -1 && XMLChar.isNCNameStart(currChar());
    }

    boolean isNCName() {
        return currChar() != -1 && XMLChar.isNCName(currChar());
    }

    boolean startsWith(String s, int offset) {
        if (this._offset + offset >= this._expr.length()) {
            return false;
        }
        return this._expr.startsWith(s, this._offset + offset);
    }

    private XPath.XPathCompileException newError(String msg) {
        XmlError err = XmlError.forLocation(msg, 0, null, this._line, this._column, this._offset);
        return new XPath.XPathCompileException(err);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    String lookupPrefix(String prefix) throws XPath.XPathCompileException {
        char c;
        if (this._namespaces.containsKey(prefix)) {
            return this._namespaces.get(prefix);
        }
        if (this._externalNamespaces.containsKey(prefix)) {
            return this._externalNamespaces.get(prefix);
        }
        String str = prefix != null ? prefix : "";
        switch (str.hashCode()) {
            case 3272:
                if (str.equals("fn")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 3835:
                if (str.equals("xs")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 118536:
                if (str.equals("xdt")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 118807:
                if (str.equals("xml")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 118990:
                if (str.equals("xsi")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 103145323:
                if (str.equals("local")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                return "http://www.w3.org/XML/1998/namespace";
            case 1:
                return "http://www.w3.org/2001/XMLSchema";
            case 2:
                return "http://www.w3.org/2001/XMLSchema-instance";
            case 3:
                return "http://www.w3.org/2002/11/xquery-functions";
            case 4:
                return "http://www.w3.org/2003/11/xpath-datatypes";
            case 5:
                return "http://www.w3.org/2003/11/xquery-local-functions";
            default:
                throw newError("Undefined prefix: " + prefix);
        }
    }

    private boolean parseWhitespace() {
        boolean sawSpace = false;
        while (isWhitespace()) {
            advance();
            sawSpace = true;
        }
        return sawSpace;
    }

    private boolean tokenize(String... tokens) {
        int offset = 0;
        for (String s : tokens) {
            if (s == null || s.isEmpty()) {
                throw new AssertionError();
            }
            while (isWhitespace(offset)) {
                offset++;
            }
            if (!startsWith(s, offset)) {
                return false;
            }
            offset += s.length();
        }
        advance(offset);
        return true;
    }

    private String tokenizeNCName() throws XPath.XPathCompileException {
        parseWhitespace();
        if (!isNCNameStart()) {
            throw newError("Expected non-colonized name");
        }
        StringBuilder sb = new StringBuilder();
        sb.append((char) currChar());
        while (true) {
            advance();
            if (isNCName()) {
                sb.append((char) currChar());
            } else {
                return sb.toString();
            }
        }
    }

    private QName getAnyQName() {
        return new QName("", "");
    }

    private QName tokenizeQName() throws XPath.XPathCompileException {
        if (tokenize("*")) {
            return getAnyQName();
        }
        String ncName = tokenizeNCName();
        if (!tokenize(":")) {
            return new QName(lookupPrefix(""), ncName);
        }
        return new QName(lookupPrefix(ncName), tokenize("*") ? "" : tokenizeNCName());
    }

    private String tokenizeQuotedUri() throws XPath.XPathCompileException {
        char quote;
        if (tokenize("\"")) {
            quote = '\"';
        } else if (tokenize("'")) {
            quote = Chars.QUOTE;
        } else {
            throw newError("Expected quote (\" or ')");
        }
        StringBuilder sb = new StringBuilder();
        while (currChar() != -1) {
            if (currChar() == quote) {
                advance();
                if (currChar() != quote) {
                    return sb.toString();
                }
            }
            sb.append((char) currChar());
            advance();
        }
        throw newError("Path terminated in URI literal");
    }

    private XPathStep addStep(boolean deep, boolean attr, QName name, XPathStep steps) {
        XPathStep step = new XPathStep(deep, attr, name);
        if (steps == null) {
            return step;
        }
        while (steps._next != null) {
            steps = steps._next;
        }
        steps._next = step;
        step._prev = steps;
        return steps;
    }

    private XPathStep tokenizeSteps() throws XPath.XPathCompileException {
        boolean deep;
        if (tokenize(PackagingURIHelper.FORWARD_SLASH_STRING)) {
            throw newError("Absolute paths unsupported");
        }
        if (tokenize("$", this._currentNodeVar, "//") || tokenize(".", "//")) {
            deep = true;
        } else if (tokenize("$", this._currentNodeVar, PackagingURIHelper.FORWARD_SLASH_STRING) || tokenize(".", PackagingURIHelper.FORWARD_SLASH_STRING)) {
            deep = false;
        } else {
            if (tokenize("$", this._currentNodeVar) || tokenize(".")) {
                return addStep(false, false, null, null);
            }
            deep = false;
        }
        XPathStep steps = null;
        boolean deepDot = false;
        while (!tokenize("attribute", "::") && !tokenize("@")) {
            if (tokenize(".")) {
                deepDot = deepDot || deep;
            } else {
                tokenize("child", "::");
                QName name = tokenizeQName();
                steps = addStep(deep, false, name, steps);
                deep = false;
            }
            if (!tokenize("//")) {
                if (!tokenize(PackagingURIHelper.FORWARD_SLASH_STRING)) {
                    break;
                }
                if (deepDot) {
                    deep = true;
                }
            } else {
                deep = true;
                deepDot = false;
            }
        }
        steps = addStep(deep, true, tokenizeQName(), steps);
        this._lastDeepDot = deepDot;
        if (deepDot) {
            this._lastDeepDot = true;
            steps = addStep(true, false, getAnyQName(), steps);
        }
        return addStep(false, false, null, steps);
    }

    private void computeBacktrack(XPathStep steps) {
        XPathStep s = steps;
        while (s != null) {
            XPathStep t = s._next;
            while (t != null && !t._deep) {
                t = t._next;
            }
            if (!s._deep) {
                for (XPathStep u = s; u != t; u = u._next) {
                    u._hasBacktrack = true;
                }
            } else {
                int n = 0;
                XPathStep u2 = s;
                while (u2 != t && u2._name != null && !u2.isWild() && !u2._attr) {
                    n++;
                    u2 = u2._next;
                }
                QName[] pattern = new QName[n + 1];
                int[] kmp = new int[n + 1];
                XPathStep v = s;
                for (int i = 0; i < n; i++) {
                    pattern[i] = v._name;
                    v = v._next;
                }
                pattern[n] = getAnyQName();
                int i2 = 0;
                kmp[0] = -1;
                int j = -1;
                while (i2 < n) {
                    while (j > -1 && !pattern[i2].equals(pattern[j])) {
                        j = kmp[j];
                    }
                    i2++;
                    j++;
                    kmp[i2] = pattern[i2].equals(pattern[j]) ? kmp[j] : j;
                }
                int i3 = 0;
                for (XPathStep v2 = s; v2 != u2; v2 = v2._next) {
                    v2._hasBacktrack = true;
                    v2._backtrack = s;
                    for (int j2 = kmp[i3]; j2 > 0; j2--) {
                        v2._backtrack = v2._backtrack._next;
                    }
                    i3++;
                }
                XPathStep v3 = s;
                if (n > 1) {
                    for (int j3 = kmp[n - 1]; j3 > 0; j3--) {
                        v3 = v3._next;
                    }
                }
                if (u2 != t && u2._attr) {
                    u2._hasBacktrack = true;
                    u2._backtrack = v3;
                    u2 = u2._next;
                }
                if (u2 != t && u2._name == null) {
                    u2._hasBacktrack = true;
                    u2._backtrack = v3;
                }
                if (!s._deep) {
                    throw new AssertionError();
                }
                s._hasBacktrack = true;
                s._backtrack = s;
            }
            s = t;
        }
    }

    private void tokenizePath(ArrayList<XPathStep> paths) throws XPath.XPathCompileException {
        boolean attr;
        this._lastDeepDot = false;
        XPathStep steps = tokenizeSteps();
        computeBacktrack(steps);
        paths.add(steps);
        if (this._lastDeepDot) {
            this._sawDeepDot = true;
            XPathStep s = null;
            for (XPathStep t = steps; t != null; t = t._next) {
                if ((t._next == null || t._next._next != null) && !t._attr) {
                    attr = false;
                } else {
                    attr = true;
                }
                s = addStep(t._deep, attr, t._name, s);
            }
            computeBacktrack(s);
            paths.add(s);
        }
    }

    private XPath.Selector tokenizeSelector() throws XPath.XPathCompileException {
        ArrayList<XPathStep> paths = new ArrayList<>();
        tokenizePath(paths);
        while (tokenize("|")) {
            tokenizePath(paths);
        }
        return new XPath.Selector((XPathStep[]) paths.toArray(new XPathStep[0]));
    }

    private XPath tokenizeXPath() throws XPath.XPathCompileException {
        while (true) {
            if (tokenize("declare", "namespace")) {
                if (!parseWhitespace()) {
                    throw newError("Expected prefix after 'declare namespace'");
                }
                String prefix = tokenizeNCName();
                if (!tokenize("=")) {
                    throw newError("Expected '='");
                }
                String uri = tokenizeQuotedUri();
                if (this._namespaces.containsKey(prefix)) {
                    throw newError("Redefinition of namespace prefix: " + prefix);
                }
                this._namespaces.put(prefix, uri);
                if (this._externalNamespaces.containsKey(prefix)) {
                    throw newError("Redefinition of namespace prefix: " + prefix);
                }
                this._externalNamespaces.put(prefix, uri);
                tokenize(";");
                this._externalNamespaces.put(XPath._NS_BOUNDARY, Integer.toString(this._offset));
            } else if (tokenize("declare", "default", "element", "namespace")) {
                String uri2 = tokenizeQuotedUri();
                if (this._namespaces.containsKey("")) {
                    throw newError("Redefinition of default element namespace");
                }
                this._namespaces.put("", uri2);
                if (this._externalNamespaces.containsKey(XPath._DEFAULT_ELT_NS)) {
                    throw newError("Redefinition of default element namespace : ");
                }
                this._externalNamespaces.put(XPath._DEFAULT_ELT_NS, uri2);
                if (!tokenize(";")) {
                    throw newError("Default Namespace declaration must end with ;");
                }
                this._externalNamespaces.put(XPath._NS_BOUNDARY, Integer.toString(this._offset));
            } else {
                if (!this._namespaces.containsKey("")) {
                    this._namespaces.put("", "");
                }
                XPath.Selector selector = tokenizeSelector();
                parseWhitespace();
                if (currChar() != -1) {
                    throw newError("Unexpected char '" + ((char) currChar()) + "'");
                }
                return new XPath(selector, this._sawDeepDot);
            }
        }
    }

    private void processNonXpathDecls() {
    }
}
