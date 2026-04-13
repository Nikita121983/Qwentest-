package org.apache.xmlbeans.impl.xpathgen;

import java.util.Iterator;
import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.common.NameUtil;

/* loaded from: classes11.dex */
public class XPathGenerator {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    public static String generateXPath(XmlCursor node, XmlCursor context, NamespaceContext nsctx) throws XPathGenerationException {
        if (node == null) {
            throw new IllegalArgumentException("Null node");
        }
        if (nsctx == null) {
            throw new IllegalArgumentException("Null namespace context");
        }
        XmlCursor.TokenType tt = node.currentTokenType();
        if (context != null && node.isAtSamePositionAs(context)) {
            return ".";
        }
        switch (tt.intValue()) {
            case 1:
            case 3:
                return generateInternal(node, context, nsctx);
            case 2:
            case 4:
            default:
                throw new XPathGenerationException("Cannot generate XPath for cursor position: " + tt.toString());
            case 5:
                int nrOfTextTokens = countTextTokens(node);
                node.toParent();
                String pathToParent = generateInternal(node, context, nsctx);
                if (nrOfTextTokens == 0) {
                    return pathToParent + "/text()";
                }
                return pathToParent + "/text()[position()=" + nrOfTextTokens + ']';
            case 6:
                QName name = node.getName();
                node.toParent();
                return generateInternal(node, context, nsctx) + "/@" + qnameToString(name, nsctx);
            case 7:
                QName name2 = node.getName();
                node.toParent();
                String pathToParent2 = generateInternal(node, context, nsctx);
                String prefix = name2.getLocalPart();
                if (prefix.isEmpty()) {
                    return pathToParent2 + "/@xmlns";
                }
                return pathToParent2 + "/@xmlns:" + prefix;
        }
    }

    private static String generateInternal(XmlCursor node, XmlCursor context, NamespaceContext nsctx) throws XPathGenerationException {
        if (node.isStartdoc()) {
            return "";
        }
        if (context != null && node.isAtSamePositionAs(context)) {
            return ".";
        }
        if (!node.isStart()) {
            throw new AssertionError();
        }
        QName name = node.getName();
        int elemIndex = 0;
        int i = 1;
        XmlCursor d = node.newCursor();
        try {
            if (!node.toParent()) {
                String str = PackagingURIHelper.FORWARD_SLASH_STRING + name;
                if (d != null) {
                    d.close();
                }
                return str;
            }
            node.push();
            if (!node.toChild(name)) {
                throw new IllegalStateException("Must have at least one child with name: " + name);
            }
            do {
                if (node.isAtSamePositionAs(d)) {
                    elemIndex = i;
                } else {
                    i++;
                }
            } while (node.toNextSibling(name));
            node.pop();
            if (d != null) {
                d.close();
            }
            String pathToParent = generateInternal(node, context, nsctx);
            if (i == 1) {
                return pathToParent + '/' + qnameToString(name, nsctx);
            }
            return pathToParent + '/' + qnameToString(name, nsctx) + '[' + elemIndex + ']';
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (d != null) {
                    try {
                        d.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    private static String qnameToString(QName qname, NamespaceContext ctx) throws XPathGenerationException {
        String localName = qname.getLocalPart();
        String uri = qname.getNamespaceURI();
        if (uri.isEmpty()) {
            return localName;
        }
        String prefix = qname.getPrefix();
        if (prefix != null && prefix.length() > 0) {
            String mappedUri = ctx.getNamespaceURI(prefix);
            if (uri.equals(mappedUri)) {
                return prefix + NameUtil.COLON + localName;
            }
        }
        String prefix2 = ctx.getPrefix(uri);
        if (prefix2 == null) {
            throw new XPathGenerationException("Could not obtain a prefix for URI: " + uri);
        }
        if (!prefix2.isEmpty()) {
            return prefix2 + NameUtil.COLON + localName;
        }
        throw new XPathGenerationException("Can not use default prefix in XPath for URI: " + uri);
    }

    private static int countTextTokens(XmlCursor c) {
        int k = 0;
        int l = 0;
        XmlCursor d = c.newCursor();
        try {
            c.push();
            c.toParent();
            XmlCursor.TokenType tt = c.toFirstContentToken();
            while (!tt.isEnd()) {
                if (tt.isText()) {
                    if (c.comparePosition(d) > 0) {
                        l++;
                    } else {
                        k++;
                    }
                } else if (tt.isStart()) {
                    c.toEndToken();
                }
                tt = c.toNextToken();
            }
            if (d != null) {
                d.close();
            }
            c.pop();
            if (l == 0) {
                return 0;
            }
            return k;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (d != null) {
                    try {
                        d.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    public static void main(String[] args) throws XmlException {
        NamespaceContext ns = new NamespaceContext() { // from class: org.apache.xmlbeans.impl.xpathgen.XPathGenerator.1
            @Override // javax.xml.namespace.NamespaceContext
            public String getNamespaceURI(String prefix) {
                if ("ns".equals(prefix)) {
                    return "http://a.com";
                }
                return null;
            }

            @Override // javax.xml.namespace.NamespaceContext
            public String getPrefix(String namespaceUri) {
                return null;
            }

            @Override // javax.xml.namespace.NamespaceContext
            public Iterator getPrefixes(String namespaceUri) {
                return null;
            }
        };
        XmlCursor c = XmlObject.Factory.parse("<root>\n<ns:a xmlns:ns=\"http://a.com\"><b foo=\"value\">text1<c/>text2<c/>text3<c>text</c>text4</b></ns:a>\n</root>").newCursor();
        try {
            c.toFirstContentToken();
            c.toFirstContentToken();
            c.toFirstChild();
            c.toFirstChild();
            c.push();
            System.out.println(generateXPath(c, null, ns));
            c.pop();
            c.toNextSibling();
            c.toNextSibling();
            c.push();
            System.out.println(generateXPath(c, null, ns));
            c.pop();
            XmlCursor d = c.newCursor();
            try {
                d.toParent();
                c.push();
                System.out.println(generateXPath(c, d, ns));
                c.pop();
                d.toParent();
                c.push();
                System.out.println(generateXPath(c, d, ns));
                c.pop();
                c.toFirstContentToken();
                c.push();
                System.out.println(generateXPath(c, d, ns));
                c.pop();
                c.toParent();
                c.toPrevToken();
                c.push();
                System.out.println(generateXPath(c, d, ns));
                c.pop();
                c.toParent();
                c.push();
                System.out.println(generateXPath(c, d, ns));
                c.pop();
                c.toFirstAttribute();
                c.push();
                System.out.println(generateXPath(c, d, ns));
                c.pop();
                c.toParent();
                c.toParent();
                c.toNextToken();
                c.push();
                System.out.println(generateXPath(c, d, ns));
                c.pop();
                if (d != null) {
                    d.close();
                }
                c.push();
                System.out.println(generateXPath(c, null, ns));
                c.pop();
                if (c != null) {
                    c.close();
                }
            } finally {
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (c != null) {
                    try {
                        c.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }
}
