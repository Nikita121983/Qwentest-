package org.apache.xmlbeans.impl.store;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Source;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlRuntimeException;
import org.apache.xmlbeans.impl.common.Sax2Dom;
import org.apache.xmlbeans.impl.common.XMLChar;
import org.apache.xmlbeans.impl.soap.Detail;
import org.apache.xmlbeans.impl.soap.DetailEntry;
import org.apache.xmlbeans.impl.soap.MimeHeader;
import org.apache.xmlbeans.impl.soap.Name;
import org.apache.xmlbeans.impl.soap.SOAPBody;
import org.apache.xmlbeans.impl.soap.SOAPBodyElement;
import org.apache.xmlbeans.impl.soap.SOAPElement;
import org.apache.xmlbeans.impl.soap.SOAPEnvelope;
import org.apache.xmlbeans.impl.soap.SOAPException;
import org.apache.xmlbeans.impl.soap.SOAPFault;
import org.apache.xmlbeans.impl.soap.SOAPHeader;
import org.apache.xmlbeans.impl.soap.SOAPHeaderElement;
import org.apache.xmlbeans.impl.soap.SOAPPart;
import org.apache.xmlbeans.impl.store.DomImpl;
import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.EntityReference;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;
import org.w3c.dom.UserDataHandler;

/* loaded from: classes11.dex */
public final class DomImpl {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final int ATTR = 2;
    static final int CDATA = 4;
    static final int COMMENT = 8;
    static final int DOCFRAG = 11;
    static final int DOCTYPE = 10;
    static final int DOCUMENT = 9;
    static final int ELEMENT = 1;
    static final int ENTITY = 6;
    static final int ENTITYREF = 5;
    static final int NOTATION = 12;
    static final int PROCINST = 7;
    static final int TEXT = 3;
    public static final NodeList _emptyNodeList = new EmptyNodeList();

    /* loaded from: classes11.dex */
    public interface Dom {
        void dump();

        void dump(PrintStream printStream);

        void dump(PrintStream printStream, Object obj);

        QName getQName();

        Locale locale();

        boolean nodeCanHavePrefixUri();

        int nodeType();

        Cur tempCur();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public interface WrapSoapEx<T> {
        T get() throws SOAPException;
    }

    static Node parent(Dom d) {
        return node_getParentNode(d);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Node firstChild(Dom d) {
        return node_getFirstChild(d);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Node nextSibling(Dom d) {
        return node_getNextSibling(d);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Node prevSibling(Dom d) {
        return node_getPreviousSibling(d);
    }

    public static Node append(Dom n, Dom p) {
        return node_insertBefore(p, n, null);
    }

    public static Node insert(Dom n, Dom b) {
        if (b == null) {
            throw new AssertionError();
        }
        return node_insertBefore((Dom) parent(b), n, b);
    }

    public static Node remove(Dom n) {
        Node p = parent(n);
        if (p != null) {
            node_removeChild((Dom) p, n);
        }
        return (Node) n;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class HierarchyRequestErr extends DOMException {
        HierarchyRequestErr(String message) {
            super((short) 3, message);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class WrongDocumentErr extends DOMException {
        WrongDocumentErr(String message) {
            super((short) 4, message);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class NotFoundErr extends DOMException {
        NotFoundErr(String message) {
            super((short) 8, message);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class NamespaceErr extends DOMException {
        NamespaceErr(String message) {
            super((short) 14, message);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class NoModificationAllowedErr extends DOMException {
        NoModificationAllowedErr(String message) {
            super((short) 7, message);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class InuseAttributeError extends DOMException {
        InuseAttributeError() {
            this("Attribute currently in use error");
        }

        InuseAttributeError(String message) {
            super((short) 10, message);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class IndexSizeError extends DOMException {
        IndexSizeError() {
            this("Index Size Error");
        }

        IndexSizeError(String message) {
            super((short) 1, message);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class NotSupportedError extends DOMException {
        NotSupportedError(String message) {
            super((short) 9, message);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class InvalidCharacterError extends DOMException {
        InvalidCharacterError() {
            this("The name contains an invalid character");
        }

        InvalidCharacterError(String message) {
            super((short) 5, message);
        }
    }

    /* loaded from: classes11.dex */
    private static final class EmptyNodeList implements NodeList {
        private EmptyNodeList() {
        }

        @Override // org.w3c.dom.NodeList
        public int getLength() {
            return 0;
        }

        @Override // org.w3c.dom.NodeList
        public Node item(int i) {
            return null;
        }
    }

    static String nodeKindName(int t) {
        switch (t) {
            case 1:
                return "element";
            case 2:
                return "attribute";
            case 3:
                return "text";
            case 4:
                return "cdata section";
            case 5:
                return "entity reference";
            case 6:
                return "entity";
            case 7:
                return "processing instruction";
            case 8:
                return "comment";
            case 9:
                return "document";
            case 10:
                return "document type";
            case 11:
                return "document fragment";
            case 12:
                return "notation";
            default:
                throw new RuntimeException("Unknown node type");
        }
    }

    private static String isValidChild(Dom parent, Dom child) {
        int pk = parent.nodeType();
        int ck = child.nodeType();
        switch (pk) {
            case 1:
            case 5:
            case 6:
            case 11:
                switch (ck) {
                    case 1:
                    case 3:
                    case 4:
                    case 5:
                    case 7:
                    case 8:
                        return null;
                }
            case 2:
                if (ck == 3 || ck == 5) {
                    return null;
                }
                break;
            case 3:
            case 4:
            case 7:
            case 8:
            case 10:
            case 12:
                return nodeKindName(pk) + " nodes may not have any children";
            case 9:
                switch (ck) {
                    case 1:
                        if (document_getDocumentElement(parent) == null) {
                            return null;
                        }
                        return "Documents may only have a maximum of one document element";
                    case 7:
                    case 8:
                        return null;
                    case 10:
                        if (document_getDoctype(parent) == null) {
                            return null;
                        }
                        return "Documents may only have a maximum of one document type node";
                }
        }
        return nodeKindName(pk) + " nodes may not have " + nodeKindName(ck) + " nodes as children";
    }

    private static void validateNewChild(Dom parent, Dom child) {
        String msg = isValidChild(parent, child);
        if (msg != null) {
            throw new HierarchyRequestErr(msg);
        }
        if (parent == child) {
            throw new HierarchyRequestErr("New child and parent are the same node");
        }
        Node p = (Node) parent;
        do {
            Node parent2 = parent((Dom) p);
            p = parent2;
            if (parent2 != null) {
                if (child.nodeType() == 5) {
                    throw new NoModificationAllowedErr("Entity reference trees may not be modified");
                }
            } else {
                return;
            }
        } while (child != p);
        throw new HierarchyRequestErr("New child is an ancestor node of the parent node");
    }

    private static String validatePrefix(String prefix, String uri, String local, boolean isAttr) {
        if (prefix != null && prefix.contains(":")) {
            throw new NamespaceErr("Invalid prefix - contains ':' character");
        }
        validateNcName(prefix);
        if (prefix == null) {
            prefix = "";
        }
        if (uri == null) {
            uri = "";
        }
        if (!prefix.isEmpty() && uri.isEmpty()) {
            throw new NamespaceErr("Attempt to give a prefix for no namespace");
        }
        if (prefix.equals("xml") && !uri.equals("http://www.w3.org/XML/1998/namespace")) {
            throw new NamespaceErr("Invalid prefix - begins with 'xml'");
        }
        if (isAttr) {
            if (prefix.length() > 0) {
                if (local.equals(Sax2Dom.XMLNS_PREFIX)) {
                    throw new NamespaceErr("Invalid namespace - attr is default namespace already");
                }
                if (Locale.beginsWithXml(local)) {
                    throw new NamespaceErr("Invalid namespace - attr prefix begins with 'xml'");
                }
                if (prefix.equals(Sax2Dom.XMLNS_PREFIX) && !uri.equals("http://www.w3.org/2000/xmlns/")) {
                    throw new NamespaceErr("Invalid namespace - uri is not 'http://www.w3.org/2000/xmlns/;");
                }
            } else if (local.equals(Sax2Dom.XMLNS_PREFIX) && !uri.equals("http://www.w3.org/2000/xmlns/")) {
                throw new NamespaceErr("Invalid namespace - uri is not 'http://www.w3.org/2000/xmlns/;");
            }
        } else if (Locale.beginsWithXml(prefix)) {
            throw new NamespaceErr("Invalid prefix - begins with 'xml'");
        }
        return prefix;
    }

    private static void validateName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name is null");
        }
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Name is empty");
        }
        if (!XMLChar.isValidName(name)) {
            throw new InvalidCharacterError("Name has an invalid character");
        }
    }

    private static void validateNcName(String name) {
        if (name != null && name.length() > 0 && !XMLChar.isValidNCName(name)) {
            throw new InvalidCharacterError();
        }
    }

    private static void validateQualifiedName(String name, String uri, boolean isAttr) {
        String local;
        if (name == null) {
            throw new AssertionError();
        }
        if (uri == null) {
            uri = "";
        }
        int i = name.indexOf(58);
        if (i < 0) {
            local = name;
            validateNcName(name);
            if (isAttr && local.equals(Sax2Dom.XMLNS_PREFIX) && !uri.equals("http://www.w3.org/2000/xmlns/")) {
                throw new NamespaceErr("Default xmlns attribute does not have namespace: http://www.w3.org/2000/xmlns/");
            }
        } else {
            if (i == 0) {
                throw new NamespaceErr("Invalid qualified name, no prefix specified");
            }
            String prefix = name.substring(0, i);
            validateNcName(prefix);
            if (uri.isEmpty()) {
                throw new NamespaceErr("Attempt to give a prefix for no namespace");
            }
            String local2 = name.substring(i + 1);
            if (local2.indexOf(58) >= 0) {
                throw new NamespaceErr("Invalid qualified name, more than one colon");
            }
            validateNcName(local2);
            if (prefix.equals("xml") && !uri.equals("http://www.w3.org/XML/1998/namespace")) {
                throw new NamespaceErr("Invalid prefix - begins with 'xml'");
            }
            local = local2;
        }
        if (local.isEmpty()) {
            throw new NamespaceErr("Invalid qualified name, no local part specified");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static void removeNode(Dom dom) {
        CharNode fromNodes;
        if (dom.nodeType() == 3 || dom.nodeType() == 4) {
            throw new AssertionError();
        }
        Cur cFrom = dom.tempCur();
        cFrom.toEnd();
        if (cFrom.next() && (fromNodes = cFrom.getCharNodes()) != null) {
            cFrom.setCharNodes(null);
            Cur cTo = dom.tempCur();
            cTo.setCharNodes(CharNode.appendNodes(cTo.getCharNodes(), fromNodes));
            cTo.release();
        }
        cFrom.release();
        Cur.moveNode((Xobj) dom, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static abstract class ElementsNodeList implements NodeList {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private ArrayList<Dom> _elements;
        private final Locale _locale;
        private final Dom _root;
        private long _version;

        protected abstract boolean match(Dom dom);

        ElementsNodeList(Dom root) {
            if (root.nodeType() != 9 && root.nodeType() != 1) {
                throw new AssertionError();
            }
            this._root = root;
            this._locale = this._root.locale();
            this._version = 0L;
        }

        @Override // org.w3c.dom.NodeList
        public int getLength() {
            ensureElements();
            return this._elements.size();
        }

        @Override // org.w3c.dom.NodeList
        public Node item(int i) {
            ensureElements();
            if (i < 0 || i >= this._elements.size()) {
                return null;
            }
            return (Node) this._elements.get(i);
        }

        private void ensureElements() {
            if (this._version == this._locale.version()) {
                return;
            }
            this._version = this._locale.version();
            this._elements = new ArrayList<>();
            DomImpl.syncWrapHelper(this._locale, true, new Supplier() { // from class: org.apache.xmlbeans.impl.store.DomImpl$ElementsNodeList$$ExternalSyntheticLambda0
                @Override // java.util.function.Supplier
                public final Object get() {
                    return DomImpl.ElementsNodeList.this.m2686x184fa361();
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$ensureElements$0$org-apache-xmlbeans-impl-store-DomImpl$ElementsNodeList, reason: not valid java name */
        public /* synthetic */ Object m2686x184fa361() {
            addElements(this._root);
            return null;
        }

        private void addElements(Dom node) {
            for (Object firstChild = DomImpl.firstChild(node); firstChild != null; firstChild = DomImpl.nextSibling((Dom) firstChild)) {
                if (((Dom) firstChild).nodeType() == 1) {
                    if (match((Dom) firstChild)) {
                        this._elements.add((Dom) firstChild);
                    }
                    addElements((Dom) firstChild);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static class ElementsByTagNameNodeList extends ElementsNodeList {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final String _name;

        ElementsByTagNameNodeList(Dom root, String name) {
            super(root);
            this._name = name;
            if (this._name == null) {
                throw new AssertionError();
            }
        }

        @Override // org.apache.xmlbeans.impl.store.DomImpl.ElementsNodeList
        protected boolean match(Dom element) {
            return this._name.equals("*") || this._name.equals(DomImpl._node_getNodeName(element));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static class ElementsByTagNameNSNodeList extends ElementsNodeList {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final String _local;
        private final String _uri;

        ElementsByTagNameNSNodeList(Dom root, String uri, String local) {
            super(root);
            this._uri = uri == null ? "" : uri;
            this._local = local;
            if (local == null) {
                throw new AssertionError();
            }
        }

        @Override // org.apache.xmlbeans.impl.store.DomImpl.ElementsNodeList
        protected boolean match(Dom element) {
            if (this._uri.equals("*") || this._uri.equals(DomImpl._node_getNamespaceURI(element))) {
                return this._local.equals("*") || this._local.equals(DomImpl._node_getLocalName(element));
            }
            return false;
        }
    }

    public static Document _domImplementation_createDocument(final Locale l, final String u, final String n, final DocumentType t) {
        return (Document) syncWrapHelper(l, true, new Supplier() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda129
            @Override // java.util.function.Supplier
            public final Object get() {
                Document domImplementation_createDocument;
                domImplementation_createDocument = DomImpl.domImplementation_createDocument(Locale.this, u, n, t);
                return domImplementation_createDocument;
            }
        });
    }

    public static Document domImplementation_createDocument(Locale l, String namespaceURI, String qualifiedName, DocumentType doctype) {
        validateQualifiedName(qualifiedName, namespaceURI, false);
        Cur c = l.tempCur();
        c.createDomDocumentRoot();
        Document doc = (Document) c.getDom();
        c.next();
        c.createElement(l.makeQualifiedQName(namespaceURI, qualifiedName));
        if (doctype != null) {
            throw new RuntimeException("Not impl");
        }
        c.toParent();
        try {
            Locale.autoTypeDocument(c, null, null);
            c.release();
            return doc;
        } catch (XmlException e) {
            throw new XmlRuntimeException(e);
        }
    }

    public static boolean _domImplementation_hasFeature(Locale l, String feature, String version) {
        if (feature == null) {
            return false;
        }
        if (version != null && version.length() > 0 && !version.equals("1.0") && !version.equals("2.0")) {
            return false;
        }
        if (feature.equalsIgnoreCase("core")) {
            return true;
        }
        return feature.equalsIgnoreCase("xml");
    }

    public static Element _document_getDocumentElement(Dom d) {
        return (Element) syncWrap(d, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda12
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return DomImpl.document_getDocumentElement((DomImpl.Dom) obj);
            }
        });
    }

    public static Element document_getDocumentElement(Dom d) {
        for (Node n = firstChild(d); n != null; n = nextSibling((Dom) n)) {
            if (((Dom) n).nodeType() == 1) {
                return (Element) n;
            }
        }
        return null;
    }

    public static DocumentFragment _document_createDocumentFragment(Dom d) {
        return (DocumentFragment) syncWrap(d, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda32
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return DomImpl.document_createDocumentFragment((DomImpl.Dom) obj);
            }
        });
    }

    public static DocumentFragment document_createDocumentFragment(Dom d) {
        Cur c = d.locale().tempCur();
        c.createDomDocFragRoot();
        Dom f = c.getDom();
        c.release();
        return (DocumentFragment) f;
    }

    public static Element _document_createElement(Dom d, final String name) {
        return (Element) syncWrap(d, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda117
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Element document_createElement;
                document_createElement = DomImpl.document_createElement((DomImpl.Dom) obj, name);
                return document_createElement;
            }
        });
    }

    public static Element document_createElement(Dom d, String name) {
        validateName(name);
        Locale l = d.locale();
        Cur c = l.tempCur();
        c.createElement(l.makeQualifiedQName("", name));
        ElementXobj e = (ElementXobj) c.getDom();
        c.release();
        e._canHavePrefixUri = false;
        return e;
    }

    public static Element _document_createElementNS(Dom d, final String uri, final String qname) {
        return (Element) syncWrap(d, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda83
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Element document_createElementNS;
                document_createElementNS = DomImpl.document_createElementNS((DomImpl.Dom) obj, uri, qname);
                return document_createElementNS;
            }
        });
    }

    public static Element document_createElementNS(Dom d, String uri, String qname) {
        validateQualifiedName(qname, uri, false);
        Locale l = d.locale();
        Cur c = l.tempCur();
        c.createElement(l.makeQualifiedQName(uri, qname));
        Dom e = c.getDom();
        c.release();
        return (Element) e;
    }

    public static Attr _document_createAttribute(Dom d, final String name) {
        return (Attr) syncWrap(d, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda126
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Attr document_createAttribute;
                document_createAttribute = DomImpl.document_createAttribute((DomImpl.Dom) obj, name);
                return document_createAttribute;
            }
        });
    }

    public static Attr document_createAttribute(Dom d, String name) {
        validateName(name);
        Locale l = d.locale();
        Cur c = l.tempCur();
        c.createAttr(l.makeQualifiedQName("", name));
        AttrXobj e = (AttrXobj) c.getDom();
        c.release();
        e._canHavePrefixUri = false;
        return e;
    }

    public static Attr _document_createAttributeNS(Dom d, final String uri, final String qname) {
        return (Attr) syncWrap(d, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda67
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Attr document_createAttributeNS;
                document_createAttributeNS = DomImpl.document_createAttributeNS((DomImpl.Dom) obj, uri, qname);
                return document_createAttributeNS;
            }
        });
    }

    public static Attr document_createAttributeNS(Dom d, String uri, String qname) {
        validateQualifiedName(qname, uri, true);
        Locale l = d.locale();
        Cur c = l.tempCur();
        c.createAttr(l.makeQualifiedQName(uri, qname));
        Dom e = c.getDom();
        c.release();
        return (Attr) e;
    }

    public static Comment _document_createComment(Dom d, final String data) {
        return (Comment) syncWrap(d, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda46
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Comment document_createComment;
                document_createComment = DomImpl.document_createComment((DomImpl.Dom) obj, data);
                return document_createComment;
            }
        });
    }

    public static Comment document_createComment(Dom d, String data) {
        Locale l = d.locale();
        Cur c = l.tempCur();
        c.createComment();
        Dom comment = c.getDom();
        if (data != null) {
            c.next();
            c.insertString(data);
        }
        c.release();
        return (Comment) comment;
    }

    public static ProcessingInstruction _document_createProcessingInstruction(Dom d, final String target, final String data) {
        return (ProcessingInstruction) syncWrap(d, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda118
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                ProcessingInstruction document_createProcessingInstruction;
                document_createProcessingInstruction = DomImpl.document_createProcessingInstruction((DomImpl.Dom) obj, target, data);
                return document_createProcessingInstruction;
            }
        });
    }

    public static ProcessingInstruction document_createProcessingInstruction(Dom d, String target, String data) {
        if (target == null) {
            throw new IllegalArgumentException("Target is null");
        }
        if (target.isEmpty()) {
            throw new IllegalArgumentException("Target is empty");
        }
        if (!XMLChar.isValidName(target)) {
            throw new InvalidCharacterError("Target has an invalid character");
        }
        if (Locale.beginsWithXml(target) && target.length() == 3) {
            throw new InvalidCharacterError("Invalid target - is 'xml'");
        }
        Locale l = d.locale();
        Cur c = l.tempCur();
        c.createProcinst(target);
        Dom pi = c.getDom();
        if (data != null) {
            c.next();
            c.insertString(data);
        }
        c.release();
        return (ProcessingInstruction) pi;
    }

    public static CDATASection _document_createCDATASection(Dom d, String data) {
        return document_createCDATASection(d, data);
    }

    public static CDATASection document_createCDATASection(Dom d, String data) {
        CdataNode createCdataNode = d.locale().createCdataNode();
        if (data == null) {
            data = "";
        }
        createCdataNode.setChars(data, 0, data.length());
        return createCdataNode;
    }

    public static Text _document_createTextNode(Dom d, String data) {
        return document_createTextNode(d, data);
    }

    public static Text document_createTextNode(Dom d, String data) {
        TextNode t = d.locale().createTextNode();
        if (data == null) {
            data = "";
        }
        t.setChars(data, 0, data.length());
        return t;
    }

    public static EntityReference _document_createEntityReference(Dom d, String name) {
        throw new RuntimeException("Not implemented");
    }

    public static Element _document_getElementById(Dom d, String elementId) {
        throw new RuntimeException("Not implemented");
    }

    public static NodeList _document_getElementsByTagName(Dom d, final String name) {
        return (NodeList) syncWrap(d, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda81
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                NodeList document_getElementsByTagName;
                document_getElementsByTagName = DomImpl.document_getElementsByTagName((DomImpl.Dom) obj, name);
                return document_getElementsByTagName;
            }
        });
    }

    public static NodeList document_getElementsByTagName(Dom d, String name) {
        return new ElementsByTagNameNodeList(d, name);
    }

    public static NodeList _document_getElementsByTagNameNS(Dom d, final String uri, final String local) {
        return (NodeList) syncWrap(d, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda42
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                NodeList document_getElementsByTagNameNS;
                document_getElementsByTagNameNS = DomImpl.document_getElementsByTagNameNS((DomImpl.Dom) obj, uri, local);
                return document_getElementsByTagNameNS;
            }
        });
    }

    public static NodeList document_getElementsByTagNameNS(Dom d, String uri, String local) {
        return new ElementsByTagNameNSNodeList(d, uri, local);
    }

    public static DOMImplementation _document_getImplementation(Dom d) {
        return d.locale();
    }

    public static Node _document_importNode(Dom d, final Node n, final boolean deep) {
        return (Node) syncWrap(d, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda106
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Node document_importNode;
                document_importNode = DomImpl.document_importNode((DomImpl.Dom) obj, n, deep);
                return document_importNode;
            }
        });
    }

    public static Node document_importNode(Dom d, Node n, boolean deep) {
        Node i;
        Node i2;
        Node i3;
        if (n == null) {
            return null;
        }
        boolean copyChildren = false;
        switch (n.getNodeType()) {
            case 1:
                String local = n.getLocalName();
                if (local == null || local.isEmpty()) {
                    i = document_createElement(d, n.getNodeName());
                } else {
                    String prefix = n.getPrefix();
                    String name = (prefix == null || prefix.isEmpty()) ? local : prefix + ":" + local;
                    String uri = n.getNamespaceURI();
                    if (uri == null || uri.isEmpty()) {
                        i = document_createElement(d, name);
                    } else {
                        i = document_createElementNS(d, uri, name);
                    }
                }
                NamedNodeMap attrs = n.getAttributes();
                for (int a = 0; a < attrs.getLength(); a++) {
                    attributes_setNamedItem((Dom) i, (Dom) document_importNode(d, attrs.item(a), true));
                }
                copyChildren = deep;
                i2 = i;
                break;
            case 2:
                String local2 = n.getLocalName();
                if (local2 == null || local2.isEmpty()) {
                    i3 = document_createAttribute(d, n.getNodeName());
                } else {
                    String prefix2 = n.getPrefix();
                    String name2 = (prefix2 == null || prefix2.isEmpty()) ? local2 : prefix2 + ":" + local2;
                    String uri2 = n.getNamespaceURI();
                    if (uri2 == null || uri2.isEmpty()) {
                        i3 = document_createAttribute(d, name2);
                    } else {
                        i3 = document_createAttributeNS(d, uri2, name2);
                    }
                }
                copyChildren = true;
                i2 = i3;
                break;
            case 3:
                i2 = document_createTextNode(d, n.getNodeValue());
                break;
            case 4:
                i2 = document_createCDATASection(d, n.getNodeValue());
                break;
            case 5:
            case 6:
            case 12:
                throw new RuntimeException("Not impl");
            case 7:
                i2 = document_createProcessingInstruction(d, n.getNodeName(), n.getNodeValue());
                break;
            case 8:
                i2 = document_createComment(d, n.getNodeValue());
                break;
            case 9:
                throw new NotSupportedError("Document nodes may not be imported");
            case 10:
                throw new NotSupportedError("Document type nodes may not be imported");
            case 11:
                i2 = document_createDocumentFragment(d);
                copyChildren = deep;
                break;
            default:
                throw new RuntimeException("Unknown kind");
        }
        if (copyChildren) {
            NodeList children = n.getChildNodes();
            for (int c = 0; c < children.getLength(); c++) {
                node_insertBefore((Dom) i2, (Dom) document_importNode(d, children.item(c), true), null);
            }
        }
        return i2;
    }

    public static DocumentType _document_getDoctype(Dom d) {
        return (DocumentType) syncWrap(d, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda52
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return DomImpl.document_getDoctype((DomImpl.Dom) obj);
            }
        });
    }

    public static DocumentType document_getDoctype(Dom d) {
        return null;
    }

    public static Document _node_getOwnerDocument(Dom d) {
        return (Document) syncWrap(d, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda33
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return DomImpl.node_getOwnerDocument((DomImpl.Dom) obj);
            }
        });
    }

    public static Document node_getOwnerDocument(Dom n) {
        if (n.nodeType() == 9) {
            return null;
        }
        Locale l = n.locale();
        if (l._ownerDoc == null) {
            Cur c = l.tempCur();
            c.createDomDocumentRoot();
            l._ownerDoc = c.getDom();
            c.release();
        }
        return (Document) l._ownerDoc;
    }

    public static Node _node_getParentNode(Dom d) {
        return (Node) syncWrap(d, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda29
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return DomImpl.node_getParentNode((DomImpl.Dom) obj);
            }
        });
    }

    public static Node node_getParentNode(Dom n) {
        Cur c = null;
        switch (n.nodeType()) {
            case 1:
            case 7:
            case 8:
                Cur tempCur = n.tempCur();
                c = tempCur;
                if (!tempCur.toParentRaw()) {
                    c.release();
                    c = null;
                    break;
                }
                break;
            case 2:
            case 9:
            case 11:
                break;
            case 3:
            case 4:
                Cur tempCur2 = n.tempCur();
                c = tempCur2;
                if (tempCur2 != null) {
                    c.toParent();
                    break;
                }
                break;
            case 5:
                throw new RuntimeException("Not impl");
            case 6:
            case 10:
            case 12:
                throw new RuntimeException("Not impl");
            default:
                throw new RuntimeException("Unknown kind");
        }
        if (c == null) {
            return null;
        }
        Dom d = c.getDom();
        c.release();
        return (Node) d;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Node _node_getFirstChild(Dom dom) {
        if (!(dom instanceof Xobj)) {
            throw new AssertionError();
        }
        Xobj node = (Xobj) dom;
        if (!node.isVacant()) {
            if (node.isFirstChildPtrDomUsable()) {
                return (Node) node._firstChild;
            }
            Xobj lastAttr = node.lastAttr();
            if (lastAttr != null && lastAttr.isNextSiblingPtrDomUsable()) {
                return (NodeXobj) lastAttr._nextSibling;
            }
            if (node.isExistingCharNodesValueUsable()) {
                return node._charNodesValue;
            }
        }
        return (Node) syncWrapNoEnter(dom, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda37
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return DomImpl.node_getFirstChild((DomImpl.Dom) obj);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Node node_getFirstChild(Dom dom) {
        switch (dom.nodeType()) {
            case 1:
            case 2:
            case 9:
            case 11:
                Xobj node = (Xobj) dom;
                node.ensureOccupancy();
                if (node.isFirstChildPtrDomUsable()) {
                    return (NodeXobj) node._firstChild;
                }
                Xobj lastAttr = node.lastAttr();
                if (lastAttr != null) {
                    if (lastAttr.isNextSiblingPtrDomUsable()) {
                        return (NodeXobj) lastAttr._nextSibling;
                    }
                    if (lastAttr.isCharNodesAfterUsable()) {
                        return lastAttr._charNodesAfter;
                    }
                }
                if (node.isCharNodesValueUsable()) {
                    return node._charNodesValue;
                }
                break;
            case 5:
                throw new RuntimeException("Not impl");
            case 6:
            case 10:
            case 12:
                throw new RuntimeException("Not impl");
        }
        return (Node) null;
    }

    public static Node _node_getLastChild(Dom n) {
        return (Node) syncWrap(n, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda114
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return DomImpl.node_getLastChild((DomImpl.Dom) obj);
            }
        });
    }

    public static Node node_getLastChild(Dom n) {
        CharNode nodes;
        switch (n.nodeType()) {
            case 3:
            case 4:
            case 7:
            case 8:
                return null;
            case 5:
                throw new RuntimeException("Not impl");
            case 6:
            case 10:
            case 12:
                throw new RuntimeException("Not impl");
            case 9:
            case 11:
            default:
                Dom lc = null;
                Cur c = n.tempCur();
                if (c.toLastChild()) {
                    lc = c.getDom();
                    c.skip();
                    CharNode charNodes = c.getCharNodes();
                    nodes = charNodes;
                    if (charNodes != null) {
                        lc = null;
                    }
                } else {
                    c.next();
                    nodes = c.getCharNodes();
                }
                if (lc == null && nodes != null) {
                    while (nodes._next != null) {
                        nodes = nodes._next;
                    }
                    lc = nodes;
                }
                c.release();
                return (Node) lc;
        }
    }

    public static Node _node_getNextSibling(Dom n) {
        return (Node) syncWrapNoEnter(n, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda93
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return DomImpl.node_getNextSibling((DomImpl.Dom) obj);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Node node_getNextSibling(Dom dom) {
        Node node = null;
        switch (dom.nodeType()) {
            case 1:
            case 7:
            case 8:
                if (!(dom instanceof Xobj)) {
                    throw new AssertionError("PI, Comments and Elements always backed up by Xobj");
                }
                Xobj node2 = (Xobj) dom;
                node2.ensureOccupancy();
                if (node2.isNextSiblingPtrDomUsable()) {
                    return (NodeXobj) node2._nextSibling;
                }
                if (node2.isCharNodesAfterUsable()) {
                    return node2._charNodesAfter;
                }
                break;
            case 3:
            case 4:
                CharNode cn = (CharNode) dom;
                if (!(cn.getObject() instanceof Xobj)) {
                    return null;
                }
                Xobj src = (Xobj) cn.getObject();
                src._charNodesAfter = Cur.updateCharNodes(src._locale, src, src._charNodesAfter, src._cchAfter);
                src._charNodesValue = Cur.updateCharNodes(src._locale, src, src._charNodesValue, src._cchValue);
                if (cn._next != null) {
                    node = cn._next;
                    break;
                } else {
                    boolean isThisNodeAfterText = cn.isNodeAftertext();
                    if (isThisNodeAfterText) {
                        node = (NodeXobj) src._nextSibling;
                        break;
                    } else {
                        node = (NodeXobj) src._firstChild;
                        break;
                    }
                }
            case 5:
            case 6:
            case 10:
            case 12:
                throw new RuntimeException("Not implemented");
        }
        return node;
    }

    public static Node _node_getPreviousSibling(Dom n) {
        return (Node) syncWrapNoEnter(n, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda34
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return DomImpl.node_getPreviousSibling((DomImpl.Dom) obj);
            }
        });
    }

    public static Node node_getPreviousSibling(Dom n) {
        Node prev;
        switch (n.nodeType()) {
            case 3:
            case 4:
                if (!(n instanceof CharNode)) {
                    throw new AssertionError("Text/CData should be a CharNode");
                }
                CharNode node = (CharNode) n;
                if (!(node.getObject() instanceof Xobj)) {
                    return null;
                }
                NodeXobj src = (NodeXobj) node.getObject();
                src.ensureOccupancy();
                boolean isThisNodeAfterText = node.isNodeAftertext();
                prev = node._prev;
                if (prev == null) {
                    prev = isThisNodeAfterText ? src : src._charNodesValue;
                    break;
                }
                break;
            default:
                if (!(n instanceof NodeXobj)) {
                    throw new AssertionError();
                }
                NodeXobj node2 = (NodeXobj) n;
                prev = (NodeXobj) node2._prevSibling;
                if ((prev == null || (!(node2 instanceof AttrXobj) && (prev instanceof AttrXobj))) && node2._parent != null) {
                    prev = node_getFirstChild((Dom) node2._parent);
                    break;
                }
                break;
        }
        Node temp = prev;
        while (temp != null) {
            Node node_getNextSibling = node_getNextSibling((Dom) temp);
            temp = node_getNextSibling;
            if (node_getNextSibling != n) {
                prev = temp;
            } else {
                return prev;
            }
        }
        return prev;
    }

    public static boolean _node_hasAttributes(Dom n) {
        return ((Boolean) syncWrap(n, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda59
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Boolean.valueOf(DomImpl.node_hasAttributes((DomImpl.Dom) obj));
            }
        })).booleanValue();
    }

    public static boolean node_hasAttributes(Dom n) {
        if (n.nodeType() != 1) {
            return false;
        }
        Cur c = n.tempCur();
        boolean hasAttrs = c.hasAttrs();
        c.release();
        return hasAttrs;
    }

    public static boolean _node_isSupported(Dom n, String feature, String version) {
        return _domImplementation_hasFeature(n.locale(), feature, version);
    }

    public static void _node_normalize(Dom n) {
        syncWrapVoid(n, new Consumer() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda23
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                DomImpl.node_normalize((DomImpl.Dom) obj);
            }
        });
    }

    public static void node_normalize(Dom n) {
        switch (n.nodeType()) {
            case 3:
            case 4:
            case 7:
            case 8:
                return;
            case 5:
                throw new RuntimeException("Not impl");
            case 6:
            case 10:
            case 12:
                throw new RuntimeException("Not impl");
            case 9:
            case 11:
            default:
                Cur c = n.tempCur();
                c.push();
                do {
                    c.nextWithAttrs();
                    CharNode cn = c.getCharNodes();
                    if (cn != null) {
                        if (!c.isText()) {
                            while (cn != null) {
                                cn.setChars(null, 0, 0);
                                cn = CharNode.remove(cn, cn);
                            }
                        } else if (cn._next != null) {
                            while (cn._next != null) {
                                cn.setChars(null, 0, 0);
                                cn = CharNode.remove(cn, cn._next);
                            }
                            cn._cch = Integer.MAX_VALUE;
                        }
                        c.setCharNodes(cn);
                    }
                } while (!c.isAtEndOfLastPush());
                c.release();
                n.locale().invalidateDomCaches(n);
                return;
        }
    }

    public static boolean _node_hasChildNodes(Dom n) {
        return (n instanceof Xobj) && _node_getFirstChild(n) != null;
    }

    public static Node _node_appendChild(Dom p, Node newChild) {
        return _node_insertBefore(p, newChild, null);
    }

    public static Node _node_replaceChild(Dom p, Node newChild, Node oldChild) {
        Locale l = p.locale();
        if (newChild == null) {
            throw new IllegalArgumentException("Child to add is null");
        }
        if (oldChild == null) {
            throw new NotFoundErr("Child to replace is null");
        }
        if (newChild instanceof Dom) {
            final Dom nc = (Dom) newChild;
            if (nc.locale() == l) {
                if (oldChild instanceof Dom) {
                    final Dom oc = (Dom) oldChild;
                    if (oc.locale() == l) {
                        return (Node) syncWrap(p, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda102
                            @Override // java.util.function.Function
                            public final Object apply(Object obj) {
                                Node node_replaceChild;
                                node_replaceChild = DomImpl.node_replaceChild((DomImpl.Dom) obj, DomImpl.Dom.this, oc);
                                return node_replaceChild;
                            }
                        });
                    }
                }
                throw new WrongDocumentErr("Child to replace is from another document");
            }
        }
        throw new WrongDocumentErr("Child to add is from another document");
    }

    public static Node node_replaceChild(Dom p, Dom newChild, Dom oldChild) {
        Node nextNode = node_getNextSibling(oldChild);
        node_removeChild(p, oldChild);
        try {
            node_insertBefore(p, newChild, (Dom) nextNode);
            return (Node) oldChild;
        } catch (DOMException e) {
            node_insertBefore(p, oldChild, (Dom) nextNode);
            throw e;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0023, code lost:
    
        if (r3.locale() == r0) goto L17;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static org.w3c.dom.Node _node_insertBefore(org.apache.xmlbeans.impl.store.DomImpl.Dom r5, org.w3c.dom.Node r6, org.w3c.dom.Node r7) {
        /*
            org.apache.xmlbeans.impl.store.Locale r0 = r5.locale()
            if (r6 == 0) goto L43
            boolean r1 = r6 instanceof org.apache.xmlbeans.impl.store.DomImpl.Dom
            if (r1 == 0) goto L3b
            r1 = r6
            org.apache.xmlbeans.impl.store.DomImpl$Dom r1 = (org.apache.xmlbeans.impl.store.DomImpl.Dom) r1
            r2 = r1
            org.apache.xmlbeans.impl.store.Locale r1 = r1.locale()
            if (r1 != r0) goto L3b
            r1 = 0
            if (r7 == 0) goto L2e
            boolean r3 = r7 instanceof org.apache.xmlbeans.impl.store.DomImpl.Dom
            if (r3 == 0) goto L26
            r3 = r7
            org.apache.xmlbeans.impl.store.DomImpl$Dom r3 = (org.apache.xmlbeans.impl.store.DomImpl.Dom) r3
            r1 = r3
            org.apache.xmlbeans.impl.store.Locale r3 = r3.locale()
            if (r3 != r0) goto L26
            goto L2e
        L26:
            org.apache.xmlbeans.impl.store.DomImpl$WrongDocumentErr r3 = new org.apache.xmlbeans.impl.store.DomImpl$WrongDocumentErr
            java.lang.String r4 = "Reference child is from another document"
            r3.<init>(r4)
            throw r3
        L2e:
            r3 = r1
            org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda35 r4 = new org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda35
            r4.<init>()
            java.lang.Object r4 = syncWrap(r5, r4)
            org.w3c.dom.Node r4 = (org.w3c.dom.Node) r4
            return r4
        L3b:
            org.apache.xmlbeans.impl.store.DomImpl$WrongDocumentErr r1 = new org.apache.xmlbeans.impl.store.DomImpl$WrongDocumentErr
            java.lang.String r2 = "Child to add is from another document"
            r1.<init>(r2)
            throw r1
        L43:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r2 = "Child to add is null"
            r1.<init>(r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.store.DomImpl._node_insertBefore(org.apache.xmlbeans.impl.store.DomImpl$Dom, org.w3c.dom.Node, org.w3c.dom.Node):org.w3c.dom.Node");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Node node_insertBefore(Dom p, Dom dom, Dom rc) {
        if (dom == 0) {
            throw new AssertionError();
        }
        if (dom == rc) {
            return (Node) dom;
        }
        if (rc != null && parent(rc) != p) {
            throw new NotFoundErr("RefChild is not a child of this node");
        }
        int nck = dom.nodeType();
        if (nck == 11) {
            for (Node c = firstChild(dom); c != null; c = nextSibling((Dom) c)) {
                validateNewChild(p, (Dom) c);
            }
            Node c2 = firstChild(dom);
            while (c2 != null) {
                Node n = nextSibling((Dom) c2);
                if (rc == null) {
                    append((Dom) c2, p);
                } else {
                    insert((Dom) c2, rc);
                }
                c2 = n;
            }
            return (Node) dom;
        }
        validateNewChild(p, dom);
        remove(dom);
        int pk = p.nodeType();
        if (pk != 2 && pk != 11 && pk != 9 && pk != 1) {
            throw new AssertionError();
        }
        switch (nck) {
            case 1:
            case 7:
            case 8:
                if (rc == null) {
                    Cur cTo = p.tempCur();
                    cTo.toEnd();
                    Cur.moveNode((Xobj) dom, cTo);
                    cTo.release();
                    break;
                } else {
                    int rck = rc.nodeType();
                    if (rck == 3 || rck == 4) {
                        List<Dom> charNodes = new ArrayList<>();
                        Dom rc2 = rc;
                        while (rc2 != null && (rc2.nodeType() == 3 || rc2.nodeType() == 4)) {
                            Node next = nextSibling(rc2);
                            charNodes.add((Dom) remove(rc2));
                            rc2 = (Dom) next;
                        }
                        if (rc2 == null) {
                            append(dom, p);
                        } else {
                            insert(dom, rc2);
                        }
                        Dom rc22 = (Dom) nextSibling(dom);
                        for (Object charNode : charNodes) {
                            Dom n2 = (Dom) charNode;
                            if (rc22 == null) {
                                append(n2, p);
                            } else {
                                insert(n2, rc22);
                            }
                        }
                        break;
                    } else {
                        if (rck == 5) {
                            throw new RuntimeException("Not implemented");
                        }
                        if (rck != 1 && rck != 7 && rck != 8) {
                            throw new AssertionError();
                        }
                        Cur cTo2 = rc.tempCur();
                        Cur.moveNode((Xobj) dom, cTo2);
                        cTo2.release();
                        break;
                    }
                }
                break;
            case 2:
            case 6:
            case 9:
            default:
                throw new RuntimeException("Unexpected child node type");
            case 3:
            case 4:
                CharNode n3 = (CharNode) dom;
                if (n3._prev != null || n3._next != null) {
                    throw new AssertionError();
                }
                CharNode refCharNode = null;
                Cur c3 = p.tempCur();
                if (rc == null) {
                    c3.toEnd();
                } else {
                    int rck2 = rc.nodeType();
                    if (rck2 == 3 || rck2 == 4) {
                        CharNode charNode2 = (CharNode) rc;
                        refCharNode = charNode2;
                        c3.moveToCharNode(charNode2);
                    } else {
                        if (rck2 == 5) {
                            throw new RuntimeException("Not implemented");
                        }
                        c3.moveToDom(rc);
                    }
                }
                CharNode nodes = c3.getCharNodes();
                CharNode nodes2 = CharNode.insertNode(nodes, n3, refCharNode);
                c3.insertChars(n3.getObject(), n3._off, n3._cch);
                c3.setCharNodes(nodes2);
                c3.release();
                break;
            case 5:
                throw new RuntimeException("Not implemented");
            case 10:
                throw new RuntimeException("Not implemented");
        }
        return (Node) dom;
    }

    public static Node _node_removeChild(Dom p, Node child) {
        Locale l = p.locale();
        if (child == null) {
            throw new NotFoundErr("Child to remove is null");
        }
        if (child instanceof Dom) {
            final Dom c = (Dom) child;
            if (c.locale() == l) {
                return (Node) syncWrap(p, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda14
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        Node node_removeChild;
                        node_removeChild = DomImpl.node_removeChild((DomImpl.Dom) obj, DomImpl.Dom.this);
                        return node_removeChild;
                    }
                });
            }
        }
        throw new WrongDocumentErr("Child to remove is from another document");
    }

    public static Node node_removeChild(Dom parent, Dom child) {
        if (parent(child) != parent) {
            throw new NotFoundErr("Child to remove is not a child of given parent");
        }
        switch (child.nodeType()) {
            case 1:
            case 7:
            case 8:
                removeNode(child);
                break;
            case 2:
            case 9:
            case 11:
                throw new IllegalStateException();
            case 3:
            case 4:
                Cur c = child.tempCur();
                CharNode nodes = c.getCharNodes();
                CharNode cn = (CharNode) child;
                if (cn.getDom() == null) {
                    throw new AssertionError();
                }
                cn.setChars(c.moveChars(null, cn._cch), c._offSrc, c._cchSrc);
                c.setCharNodes(CharNode.remove(nodes, cn));
                c.release();
                break;
            case 5:
                throw new RuntimeException("Not impl");
            case 6:
            case 10:
            case 12:
                throw new RuntimeException("Not impl");
            default:
                throw new RuntimeException("Unknown kind");
        }
        return (Node) child;
    }

    public static Node _node_cloneNode(Dom n, final boolean deep) {
        return (Node) syncWrap(n, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda50
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Node node_cloneNode;
                node_cloneNode = DomImpl.node_cloneNode((DomImpl.Dom) obj, deep);
                return node_cloneNode;
            }
        });
    }

    public static Node node_cloneNode(Dom n, boolean deep) {
        Locale l = n.locale();
        Dom clone = null;
        if (!deep) {
            Cur shallow = null;
            switch (n.nodeType()) {
                case 1:
                    shallow = l.tempCur();
                    shallow.createElement(n.getQName());
                    Element elem = (Element) shallow.getDom();
                    NamedNodeMap attrs = ((Element) n).getAttributes();
                    for (int i = 0; i < attrs.getLength(); i++) {
                        elem.setAttributeNodeNS((Attr) attrs.item(i).cloneNode(true));
                    }
                    break;
                case 2:
                    shallow = l.tempCur();
                    shallow.createAttr(n.getQName());
                    break;
                case 9:
                    shallow = l.tempCur();
                    shallow.createDomDocumentRoot();
                    break;
                case 11:
                    shallow = l.tempCur();
                    shallow.createDomDocFragRoot();
                    break;
            }
            if (shallow != null) {
                clone = shallow.getDom();
                shallow.release();
            }
        }
        if (clone == null) {
            switch (n.nodeType()) {
                case 1:
                case 2:
                case 7:
                case 8:
                case 9:
                case 11:
                    Cur cClone = l.tempCur();
                    Cur cSrc = n.tempCur();
                    cSrc.copyNode(cClone);
                    clone = cClone.getDom();
                    cClone.release();
                    cSrc.release();
                    break;
                case 3:
                case 4:
                    Cur c = n.tempCur();
                    CharNode cn = n.nodeType() == 3 ? l.createTextNode() : l.createCdataNode();
                    cn.setChars(c.getChars(((CharNode) n)._cch), c._offSrc, c._cchSrc);
                    clone = cn;
                    c.release();
                    break;
                case 5:
                case 6:
                case 10:
                case 12:
                    throw new RuntimeException("Not impl");
                default:
                    throw new RuntimeException("Unknown kind");
            }
        }
        return (Node) clone;
    }

    public static String _node_getLocalName(Dom n) {
        if (!n.nodeCanHavePrefixUri()) {
            return null;
        }
        QName name = n.getQName();
        return name == null ? "" : name.getLocalPart();
    }

    public static String _node_getNamespaceURI(Dom n) {
        if (!n.nodeCanHavePrefixUri()) {
            return null;
        }
        QName name = n.getQName();
        return name == null ? "" : name.getNamespaceURI();
    }

    public static void _node_setPrefix(Dom n, final String prefix) {
        syncWrapVoid(n, new Consumer() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda58
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                DomImpl.node_setPrefix((DomImpl.Dom) obj, prefix);
            }
        });
    }

    public static void node_setPrefix(Dom n, String prefix) {
        if (n.nodeType() == 1 || n.nodeType() == 2) {
            Cur c = n.tempCur();
            QName name = c.getName();
            String uri = name.getNamespaceURI();
            String local = name.getLocalPart();
            c.setName(n.locale().makeQName(uri, local, validatePrefix(prefix, uri, local, n.nodeType() == 2)));
            c.release();
            return;
        }
        validatePrefix(prefix, "", "", false);
    }

    public static String _node_getPrefix(Dom n) {
        if (!n.nodeCanHavePrefixUri()) {
            return null;
        }
        QName name = n.getQName();
        return name == null ? "" : name.getPrefix();
    }

    public static String _node_getNodeName(Dom n) {
        switch (n.nodeType()) {
            case 1:
            case 2:
                QName name = n.getQName();
                String prefix = name.getPrefix();
                return prefix.isEmpty() ? name.getLocalPart() : prefix + ":" + name.getLocalPart();
            case 3:
                return "#text";
            case 4:
                return "#cdata-section";
            case 5:
            case 6:
            case 10:
            case 12:
                throw new RuntimeException("Not impl");
            case 7:
                return n.getQName().getLocalPart();
            case 8:
                return "#comment";
            case 9:
                return "#document";
            case 11:
                return "#document-fragment";
            default:
                throw new RuntimeException("Unknown node type");
        }
    }

    public static short _node_getNodeType(Dom n) {
        return (short) n.nodeType();
    }

    public static void _node_setNodeValue(Dom n, final String nodeValue) {
        syncWrapVoid(n, new Consumer() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda116
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                DomImpl.node_setNodeValue((DomImpl.Dom) obj, nodeValue);
            }
        });
    }

    public static void node_setNodeValue(Dom n, String nodeValue) {
        if (nodeValue == null) {
            nodeValue = "";
        }
        switch (n.nodeType()) {
            case 2:
                NodeList children = ((Node) n).getChildNodes();
                while (children.getLength() > 1) {
                    node_removeChild(n, (Dom) children.item(1));
                }
                if (children.getLength() == 0) {
                    TextNode tn = n.locale().createTextNode();
                    tn.setChars(nodeValue, 0, nodeValue.length());
                    node_insertBefore(n, tn, null);
                } else {
                    if (children.getLength() != 1) {
                        throw new AssertionError();
                    }
                    children.item(0).setNodeValue(nodeValue);
                }
                if (((AttrXobj) n).isId()) {
                    Document d = node_getOwnerDocument(n);
                    String val = node_getNodeValue(n);
                    if (d instanceof DocumentXobj) {
                        DocumentXobj dox = (DocumentXobj) d;
                        dox.removeIdElement(val);
                        dox.addIdElement(nodeValue, (Dom) attr_getOwnerElement(n));
                        return;
                    }
                    return;
                }
                return;
            case 3:
            case 4:
                CharNode cn = (CharNode) n;
                Cur c = cn.tempCur();
                if (c != null) {
                    c.moveChars(null, cn._cch);
                    cn._cch = nodeValue.length();
                    c.insertString(nodeValue);
                    c.release();
                    return;
                }
                cn.setChars(nodeValue, 0, nodeValue.length());
                return;
            case 5:
            case 6:
            default:
                return;
            case 7:
            case 8:
                Cur c2 = n.tempCur();
                c2.next();
                c2.getChars(-1);
                c2.moveChars(null, c2._cchSrc);
                c2.insertString(nodeValue);
                c2.release();
                return;
        }
    }

    public static String _node_getNodeValue(Dom n) {
        return (String) syncWrapNoEnter(n, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda128
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return DomImpl.node_getNodeValue((DomImpl.Dom) obj);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static String node_getNodeValue(Dom dom) {
        switch (dom.nodeType()) {
            case 2:
            case 7:
            case 8:
                String s = ((Xobj) dom).getValueAsString();
                return s;
            case 3:
            case 4:
                if (!(dom instanceof CharNode)) {
                    throw new AssertionError("Text/CData should be a CharNode");
                }
                CharNode node = (CharNode) dom;
                if (!(node.getObject() instanceof Xobj)) {
                    String s2 = CharUtil.getString(node.getObject(), node._off, node._cch);
                    return s2;
                }
                Xobj src = (Xobj) node.getObject();
                src.ensureOccupancy();
                boolean isThisNodeAfterText = node.isNodeAftertext();
                if (isThisNodeAfterText) {
                    src._charNodesAfter = Cur.updateCharNodes(src._locale, src, src._charNodesAfter, src._cchAfter);
                    String s3 = src.getCharsAfterAsString(node._off, node._cch);
                    return s3;
                }
                src._charNodesValue = Cur.updateCharNodes(src._locale, src, src._charNodesValue, src._cchValue);
                String s4 = src.getCharsValueAsString(node._off, node._cch);
                return s4;
            case 5:
            case 6:
            default:
                return null;
        }
    }

    public static Object _node_getUserData(Dom n, String key) {
        throw new DomLevel3NotImplemented();
    }

    public static Object _node_setUserData(Dom n, String key, Object data, UserDataHandler handler) {
        throw new DomLevel3NotImplemented();
    }

    public static Object _node_getFeature(Dom n, String feature, String version) {
        throw new DomLevel3NotImplemented();
    }

    public static boolean _node_isEqualNode(Dom n, Node arg) {
        throw new DomLevel3NotImplemented();
    }

    public static boolean _node_isSameNode(Dom n, Node arg) {
        if (n instanceof CharNode) {
            boolean ret = n.equals(arg);
            return ret;
        }
        boolean ret2 = n instanceof NodeXobj;
        if (ret2) {
            boolean ret3 = ((NodeXobj) n).getDom().equals(arg);
            return ret3;
        }
        throw new DomLevel3NotImplemented();
    }

    public static String _node_lookupNamespaceURI(Dom n, String prefix) {
        throw new DomLevel3NotImplemented();
    }

    public static boolean _node_isDefaultNamespace(Dom n, String namespaceURI) {
        throw new DomLevel3NotImplemented();
    }

    public static String _node_lookupPrefix(Dom n, String namespaceURI) {
        throw new DomLevel3NotImplemented();
    }

    public static void _node_setTextContent(Dom n, String textContent) {
        throw new DomLevel3NotImplemented();
    }

    public static String _node_getTextContent(Dom n) {
        throw new DomLevel3NotImplemented();
    }

    public static short _node_compareDocumentPosition(Dom n, Node other) {
        Node nAnc;
        Node oAnc;
        boolean isEqual;
        if (!(n instanceof Node)) {
            return (short) 32;
        }
        Iterator<Node> nAncIter = ancestorAndSelf((Node) n).iterator();
        Iterator<Node> oAncIter = ancestorAndSelf(other).iterator();
        boolean isFirst = true;
        do {
            nAnc = nAncIter.next();
            oAnc = oAncIter.next();
            isEqual = Objects.equals(nAnc, oAnc);
            if (isFirst && !isEqual) {
                return (short) 1;
            }
            isFirst = false;
            if (!isEqual || !nAncIter.hasNext()) {
                break;
            }
        } while (oAncIter.hasNext());
        if (isEqual) {
            if (nAncIter.hasNext()) {
                return (short) 10;
            }
            return oAncIter.hasNext() ? (short) 20 : (short) 32;
        }
        Node prevSib = nAnc;
        do {
            Node previousSibling = prevSib.getPreviousSibling();
            prevSib = previousSibling;
            if (previousSibling == null) {
                return (short) 4;
            }
        } while (!Objects.equals(prevSib, oAnc));
        return (short) 2;
    }

    private static List<Node> ancestorAndSelf(Node node) {
        LinkedList<Node> nodes = new LinkedList<>();
        Node n = node;
        do {
            nodes.addFirst(n);
            n = n.getParentNode();
        } while (n != null);
        return nodes;
    }

    public static String _node_getBaseURI(Dom n) {
        throw new DomLevel3NotImplemented();
    }

    public static Node _childNodes_item(Dom n, final int i) {
        if (i == 0) {
            return _node_getFirstChild(n);
        }
        return (Node) syncWrapNoEnter(n, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda122
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Node childNodes_item;
                childNodes_item = DomImpl.childNodes_item((DomImpl.Dom) obj, i);
                return childNodes_item;
            }
        });
    }

    public static Node childNodes_item(Dom n, int i) {
        if (i < 0) {
            return null;
        }
        switch (n.nodeType()) {
            case 3:
            case 4:
            case 7:
            case 8:
                return null;
            case 5:
                throw new RuntimeException("Not impl");
            case 6:
            case 10:
            case 12:
                throw new RuntimeException("Not impl");
            case 9:
            case 11:
            default:
                if (i == 0) {
                    return node_getFirstChild(n);
                }
                return (Node) n.locale().findDomNthChild(n, i);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static int _childNodes_getLength(Dom dom) {
        int count;
        if (!(dom instanceof Xobj)) {
            throw new AssertionError();
        }
        Xobj node = (Xobj) dom;
        return (node.isVacant() || (count = node.getDomZeroOneChildren()) >= 2) ? ((Integer) syncWrapNoEnter(dom, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda94
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Integer.valueOf(DomImpl.childNodes_getLength((DomImpl.Dom) obj));
            }
        })).intValue() : count;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static int childNodes_getLength(Dom dom) {
        switch (dom.nodeType()) {
            case 3:
            case 4:
            case 7:
            case 8:
                return 0;
            case 5:
                throw new RuntimeException("Not impl");
            case 6:
            case 10:
            case 12:
                throw new RuntimeException("Not impl");
            case 9:
            case 11:
            default:
                if (!(dom instanceof Xobj)) {
                    throw new AssertionError();
                }
                Xobj node = (Xobj) dom;
                node.ensureOccupancy();
                int count = node.getDomZeroOneChildren();
                return count < 2 ? count : dom.locale().domLength(dom);
        }
    }

    public static String _element_getTagName(Dom e) {
        return _node_getNodeName(e);
    }

    public static Attr _element_getAttributeNode(Dom e, String name) {
        return (Attr) _attributes_getNamedItem(e, name);
    }

    public static Attr _element_getAttributeNodeNS(Dom e, String uri, String local) {
        return (Attr) _attributes_getNamedItemNS(e, uri, local);
    }

    public static Attr _element_setAttributeNode(Dom e, Attr newAttr) {
        return (Attr) _attributes_setNamedItem(e, newAttr);
    }

    public static Attr _element_setAttributeNodeNS(Dom e, Attr newAttr) {
        return (Attr) _attributes_setNamedItemNS(e, newAttr);
    }

    public static String _element_getAttribute(Dom e, String name) {
        Node a = _attributes_getNamedItem(e, name);
        return a == null ? "" : a.getNodeValue();
    }

    public static String _element_getAttributeNS(Dom e, String uri, String local) {
        Node a = _attributes_getNamedItemNS(e, uri, local);
        return a == null ? "" : a.getNodeValue();
    }

    public static boolean _element_hasAttribute(Dom e, String name) {
        return _attributes_getNamedItem(e, name) != null;
    }

    public static boolean _element_hasAttributeNS(Dom e, String uri, String local) {
        return _attributes_getNamedItemNS(e, uri, local) != null;
    }

    public static void _element_removeAttribute(Dom e, String name) {
        try {
            _attributes_removeNamedItem(e, name);
        } catch (NotFoundErr e2) {
        }
    }

    public static void _element_removeAttributeNS(Dom e, String uri, String local) {
        try {
            _attributes_removeNamedItemNS(e, uri, local);
        } catch (NotFoundErr e2) {
        }
    }

    public static Attr _element_removeAttributeNode(Dom e, Attr oldAttr) {
        if (oldAttr == null) {
            throw new NotFoundErr("Attribute to remove is null");
        }
        if (oldAttr.getOwnerElement() != e) {
            throw new NotFoundErr("Attribute to remove does not belong to this element");
        }
        return (Attr) _attributes_removeNamedItem(e, oldAttr.getNodeName());
    }

    public static void _element_setAttribute(Dom e, final String name, final String value) {
        syncWrapVoid(e, new Consumer() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda63
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                DomImpl.element_setAttribute((DomImpl.Dom) obj, name, value);
            }
        });
    }

    public static void element_setAttribute(Dom e, String name, String value) {
        Object e2 = attributes_getNamedItem(e, name);
        if (e2 == null) {
            Dom e22 = (Dom) node_getOwnerDocument(e);
            if (e22 == null) {
                throw new NotFoundErr("Document element can't be determined.");
            }
            e2 = document_createAttribute(e22, name);
            attributes_setNamedItem(e, (Dom) e2);
        }
        node_setNodeValue((Dom) e2, value);
    }

    public static void _element_setAttributeNS(Dom e, final String uri, final String qname, final String value) {
        syncWrapVoid(e, new Consumer() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda64
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                DomImpl.element_setAttributeNS((DomImpl.Dom) obj, uri, qname, value);
            }
        });
    }

    public static void element_setAttributeNS(Dom e, String uri, String qname, String value) {
        validateQualifiedName(qname, uri, true);
        QName name = e.locale().makeQualifiedQName(uri, qname);
        String local = name.getLocalPart();
        String prefix = validatePrefix(name.getPrefix(), uri, local, true);
        Object attributes_getNamedItemNS = attributes_getNamedItemNS(e, uri, local);
        if (attributes_getNamedItemNS == null) {
            attributes_getNamedItemNS = document_createAttributeNS((Dom) node_getOwnerDocument(e), uri, local);
            attributes_setNamedItemNS(e, (Dom) attributes_getNamedItemNS);
        }
        node_setPrefix((Dom) attributes_getNamedItemNS, prefix);
        node_setNodeValue((Dom) attributes_getNamedItemNS, value);
    }

    public static NodeList _element_getElementsByTagName(Dom e, final String name) {
        return (NodeList) syncWrap(e, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda39
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                NodeList element_getElementsByTagName;
                element_getElementsByTagName = DomImpl.element_getElementsByTagName((DomImpl.Dom) obj, name);
                return element_getElementsByTagName;
            }
        });
    }

    public static NodeList element_getElementsByTagName(Dom e, String name) {
        return new ElementsByTagNameNodeList(e, name);
    }

    public static NodeList _element_getElementsByTagNameNS(Dom e, final String uri, final String local) {
        return (NodeList) syncWrap(e, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda1
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                NodeList element_getElementsByTagNameNS;
                element_getElementsByTagNameNS = DomImpl.element_getElementsByTagNameNS((DomImpl.Dom) obj, uri, local);
                return element_getElementsByTagNameNS;
            }
        });
    }

    public static NodeList element_getElementsByTagNameNS(Dom e, String uri, String local) {
        return new ElementsByTagNameNSNodeList(e, uri, local);
    }

    public static int _attributes_getLength(Dom e) {
        return ((Integer) syncWrap(e, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda6
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Integer.valueOf(DomImpl.attributes_getLength((DomImpl.Dom) obj));
            }
        })).intValue();
    }

    public static int attributes_getLength(Dom e) {
        int n = 0;
        Cur c = e.tempCur();
        while (c.toNextAttr()) {
            n++;
        }
        c.release();
        return n;
    }

    public static Node _attributes_setNamedItem(Dom e, Node attr) {
        Locale l = e.locale();
        if (attr == null) {
            throw new IllegalArgumentException("Attr to set is null");
        }
        if (attr instanceof Dom) {
            final Dom a = (Dom) attr;
            if (a.locale() == l) {
                return (Node) syncWrap(e, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda28
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        Node attributes_setNamedItem;
                        attributes_setNamedItem = DomImpl.attributes_setNamedItem((DomImpl.Dom) obj, DomImpl.Dom.this);
                        return attributes_setNamedItem;
                    }
                });
            }
        }
        throw new WrongDocumentErr("Attr to set is from another document");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Node attributes_setNamedItem(Dom e, Dom dom) {
        if (attr_getOwnerElement(dom) != null) {
            throw new InuseAttributeError();
        }
        if (dom.nodeType() != 2) {
            throw new HierarchyRequestErr("Node is not an attribute");
        }
        String name = _node_getNodeName(dom);
        Dom oldAttr = null;
        Cur c = e.tempCur();
        while (c.toNextAttr()) {
            Dom aa = c.getDom();
            if (_node_getNodeName(aa).equals(name)) {
                if (oldAttr == null) {
                    oldAttr = aa;
                } else {
                    removeNode(aa);
                    c.toPrevAttr();
                }
            }
        }
        if (oldAttr == null) {
            c.moveToDom(e);
            c.next();
            Cur.moveNode((Xobj) dom, c);
        } else {
            c.moveToDom(oldAttr);
            Cur.moveNode((Xobj) dom, c);
            removeNode(oldAttr);
        }
        c.release();
        return (Node) oldAttr;
    }

    public static Node _attributes_getNamedItem(final Dom e, final String name) {
        return (Node) syncWrap(e, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda120
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Node attributes_getNamedItem;
                attributes_getNamedItem = DomImpl.attributes_getNamedItem(DomImpl.Dom.this, name);
                return attributes_getNamedItem;
            }
        });
    }

    public static Node attributes_getNamedItem(Dom e, String name) {
        Dom a = null;
        Cur c = e.tempCur();
        while (true) {
            if (!c.toNextAttr()) {
                break;
            }
            Dom d = c.getDom();
            if (_node_getNodeName(d).equals(name)) {
                a = d;
                break;
            }
        }
        c.release();
        return (Node) a;
    }

    public static Node _attributes_getNamedItemNS(Dom e, final String uri, final String local) {
        return (Node) syncWrap(e, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda104
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Node attributes_getNamedItemNS;
                attributes_getNamedItemNS = DomImpl.attributes_getNamedItemNS((DomImpl.Dom) obj, uri, local);
                return attributes_getNamedItemNS;
            }
        });
    }

    public static Node attributes_getNamedItemNS(Dom e, String uri, String local) {
        if (uri == null) {
            uri = "";
        }
        Dom a = null;
        Cur c = e.tempCur();
        while (true) {
            if (!c.toNextAttr()) {
                break;
            }
            Dom d = c.getDom();
            QName n = d.getQName();
            if (n.getNamespaceURI().equals(uri) && n.getLocalPart().equals(local)) {
                a = d;
                break;
            }
        }
        c.release();
        return (Node) a;
    }

    public static Node _attributes_removeNamedItem(Dom e, final String name) {
        return (Node) syncWrap(e, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda3
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Node attributes_removeNamedItem;
                attributes_removeNamedItem = DomImpl.attributes_removeNamedItem((DomImpl.Dom) obj, name);
                return attributes_removeNamedItem;
            }
        });
    }

    public static Node attributes_removeNamedItem(Dom e, String name) {
        Dom oldAttr = null;
        Cur c = e.tempCur();
        while (c.toNextAttr()) {
            Dom aa = c.getDom();
            if (_node_getNodeName(aa).equals(name)) {
                if (oldAttr == null) {
                    oldAttr = aa;
                }
                if (((AttrXobj) aa).isId()) {
                    Node d = node_getOwnerDocument(aa);
                    String val = node_getNodeValue(aa);
                    if (d instanceof DocumentXobj) {
                        ((DocumentXobj) d).removeIdElement(val);
                    }
                }
                removeNode(aa);
                c.toPrevAttr();
            }
        }
        c.release();
        if (oldAttr == null) {
            throw new NotFoundErr("Named item not found: " + name);
        }
        return (Node) oldAttr;
    }

    public static Node _attributes_removeNamedItemNS(Dom e, final String uri, final String local) {
        return (Node) syncWrap(e, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda70
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Node attributes_removeNamedItemNS;
                attributes_removeNamedItemNS = DomImpl.attributes_removeNamedItemNS((DomImpl.Dom) obj, uri, local);
                return attributes_removeNamedItemNS;
            }
        });
    }

    public static Node attributes_removeNamedItemNS(Dom e, String uri, String local) {
        if (uri == null) {
            uri = "";
        }
        Dom oldAttr = null;
        Cur c = e.tempCur();
        while (c.toNextAttr()) {
            Dom aa = c.getDom();
            QName qn = aa.getQName();
            if (qn.getNamespaceURI().equals(uri) && qn.getLocalPart().equals(local)) {
                if (oldAttr == null) {
                    oldAttr = aa;
                }
                if (((AttrXobj) aa).isId()) {
                    Node d = node_getOwnerDocument(aa);
                    String val = node_getNodeValue(aa);
                    if (d instanceof DocumentXobj) {
                        ((DocumentXobj) d).removeIdElement(val);
                    }
                }
                removeNode(aa);
                c.toPrevAttr();
            }
        }
        c.release();
        if (oldAttr == null) {
            throw new NotFoundErr("Named item not found: uri=" + uri + ", local=" + local);
        }
        return (Node) oldAttr;
    }

    public static Node _attributes_setNamedItemNS(Dom e, Node attr) {
        Locale l = e.locale();
        if (attr == null) {
            throw new IllegalArgumentException("Attr to set is null");
        }
        if (attr instanceof Dom) {
            final Dom a = (Dom) attr;
            if (a.locale() == l) {
                return (Node) syncWrap(e, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda86
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        Node attributes_setNamedItemNS;
                        attributes_setNamedItemNS = DomImpl.attributes_setNamedItemNS((DomImpl.Dom) obj, DomImpl.Dom.this);
                        return attributes_setNamedItemNS;
                    }
                });
            }
        }
        throw new WrongDocumentErr("Attr to set is from another document");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Node attributes_setNamedItemNS(Dom e, Dom dom) {
        Node owner = attr_getOwnerElement(dom);
        if (owner == e) {
            return (Node) dom;
        }
        if (owner != null) {
            throw new InuseAttributeError();
        }
        if (dom.nodeType() != 2) {
            throw new HierarchyRequestErr("Node is not an attribute");
        }
        QName name = dom.getQName();
        Dom oldAttr = null;
        Cur c = e.tempCur();
        while (c.toNextAttr()) {
            Dom aa = c.getDom();
            if (aa.getQName().equals(name)) {
                if (oldAttr == null) {
                    oldAttr = aa;
                } else {
                    removeNode(aa);
                    c.toPrevAttr();
                }
            }
        }
        if (oldAttr == null) {
            c.moveToDom(e);
            c.next();
            Cur.moveNode((Xobj) dom, c);
        } else {
            c.moveToDom(oldAttr);
            Cur.moveNode((Xobj) dom, c);
            removeNode(oldAttr);
        }
        c.release();
        return (Node) oldAttr;
    }

    public static Node _attributes_item(Dom e, final int index) {
        return (Node) syncWrap(e, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda4
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Node attributes_item;
                attributes_item = DomImpl.attributes_item((DomImpl.Dom) obj, index);
                return attributes_item;
            }
        });
    }

    public static Node attributes_item(Dom e, int index) {
        if (index < 0) {
            return null;
        }
        Cur c = e.tempCur();
        Dom a = null;
        while (true) {
            if (!c.toNextAttr()) {
                break;
            }
            int index2 = index - 1;
            if (index != 0) {
                index = index2;
            } else {
                a = c.getDom();
                break;
            }
        }
        c.release();
        return (Node) a;
    }

    public static String _processingInstruction_getData(Dom p) {
        return _node_getNodeValue(p);
    }

    public static String _processingInstruction_getTarget(Dom p) {
        return _node_getNodeName(p);
    }

    public static void _processingInstruction_setData(Dom p, String data) {
        _node_setNodeValue(p, data);
    }

    public static boolean _attr_getSpecified(Dom a) {
        return true;
    }

    public static Element _attr_getOwnerElement(Dom a) {
        return (Element) syncWrap(a, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda113
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return DomImpl.attr_getOwnerElement((DomImpl.Dom) obj);
            }
        });
    }

    public static Element attr_getOwnerElement(Dom n) {
        Cur c = n.tempCur();
        if (!c.toParentRaw()) {
            c.release();
            return null;
        }
        Dom p = c.getDom();
        c.release();
        return (Element) p;
    }

    public static void _characterData_appendData(Dom cd, String arg) {
        if (arg != null && !arg.isEmpty()) {
            _node_setNodeValue(cd, _node_getNodeValue(cd) + arg);
        }
    }

    public static void _characterData_deleteData(Dom c, int offset, int count) {
        String s = _characterData_getData(c);
        if (offset < 0 || offset > s.length() || count < 0) {
            throw new IndexSizeError();
        }
        if (offset + count > s.length()) {
            count = s.length() - offset;
        }
        if (count > 0) {
            _characterData_setData(c, s.substring(0, offset) + s.substring(offset + count));
        }
    }

    public static String _characterData_getData(Dom c) {
        return _node_getNodeValue(c);
    }

    public static int _characterData_getLength(Dom c) {
        return _characterData_getData(c).length();
    }

    public static void _characterData_insertData(Dom c, int offset, String arg) {
        String s = _characterData_getData(c);
        if (offset < 0 || offset > s.length()) {
            throw new IndexSizeError();
        }
        if (arg != null && arg.length() > 0) {
            _characterData_setData(c, s.substring(0, offset) + arg + s.substring(offset));
        }
    }

    public static void _characterData_replaceData(Dom c, int offset, int count, String arg) {
        String s = _characterData_getData(c);
        if (offset < 0 || offset > s.length() || count < 0) {
            throw new IndexSizeError();
        }
        if (offset + count > s.length()) {
            count = s.length() - offset;
        }
        if (count > 0) {
            _characterData_setData(c, s.substring(0, offset) + (arg == null ? "" : arg) + s.substring(offset + count));
        }
    }

    public static void _characterData_setData(Dom c, String data) {
        _node_setNodeValue(c, data);
    }

    public static String _characterData_substringData(Dom c, int offset, int count) {
        String s = _characterData_getData(c);
        if (offset < 0 || offset > s.length() || count < 0) {
            throw new IndexSizeError();
        }
        if (offset + count > s.length()) {
            count = s.length() - offset;
        }
        return s.substring(offset, offset + count);
    }

    public static Text _text_splitText(Dom t, int offset) {
        if (t.nodeType() != 3) {
            throw new AssertionError();
        }
        String s = _characterData_getData(t);
        if (offset < 0 || offset > s.length()) {
            throw new IndexSizeError();
        }
        _characterData_deleteData(t, offset, s.length() - offset);
        Dom t2 = (Dom) _document_createTextNode(t, s.substring(offset));
        Dom p = (Dom) _node_getParentNode(t);
        if (p != null) {
            _node_insertBefore(p, (Text) t2, _node_getNextSibling(t));
            t.locale().invalidateDomCaches(p);
        }
        return (Text) t2;
    }

    public static String _text_getWholeText(Dom t) {
        throw new DomLevel3NotImplemented();
    }

    public static boolean _text_isElementContentWhitespace(Dom t) {
        throw new DomLevel3NotImplemented();
    }

    public static Text _text_replaceWholeText(Dom t, String content) {
        throw new DomLevel3NotImplemented();
    }

    public static XMLStreamReader _getXmlStreamReader(Dom n) {
        return (XMLStreamReader) syncWrap(n, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda41
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return DomImpl.getXmlStreamReader((DomImpl.Dom) obj);
            }
        });
    }

    public static XMLStreamReader getXmlStreamReader(Dom n) {
        XMLStreamReader xs;
        switch (n.nodeType()) {
            case 1:
            case 2:
            case 7:
            case 8:
            case 9:
            case 11:
                Cur c = n.tempCur();
                XMLStreamReader xs2 = Jsr173.newXmlStreamReader(c, null);
                c.release();
                return xs2;
            case 3:
            case 4:
                CharNode cn = (CharNode) n;
                Cur tempCur = cn.tempCur();
                Cur c2 = tempCur;
                if (tempCur == null) {
                    c2 = n.locale().tempCur();
                    xs = Jsr173.newXmlStreamReader(c2, cn.getObject(), cn._off, cn._cch);
                } else {
                    xs = Jsr173.newXmlStreamReader(c2, c2.getChars(cn._cch), c2._offSrc, c2._cchSrc);
                }
                c2.release();
                return xs;
            case 5:
            case 6:
            case 10:
            case 12:
                throw new RuntimeException("Not impl");
            default:
                throw new RuntimeException("Unknown kind");
        }
    }

    public static XmlCursor _getXmlCursor(Dom n) {
        return (XmlCursor) syncWrap(n, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda95
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return DomImpl.getXmlCursor((DomImpl.Dom) obj);
            }
        });
    }

    public static XmlCursor getXmlCursor(Dom n) {
        Cur c = n.tempCur();
        Cursor xc = new Cursor(c);
        c.release();
        return xc;
    }

    public static XmlObject _getXmlObject(Dom n) {
        return (XmlObject) syncWrap(n, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda125
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return DomImpl.getXmlObject((DomImpl.Dom) obj);
            }
        });
    }

    public static XmlObject getXmlObject(Dom n) {
        Cur c = n.tempCur();
        XmlObject x = c.getObject();
        c.release();
        return x;
    }

    public static boolean _soapText_isComment(Dom n) {
        final org.apache.xmlbeans.impl.soap.Text text = (org.apache.xmlbeans.impl.soap.Text) n;
        return ((Boolean) syncWrap(n, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda85
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Boolean valueOf;
                valueOf = Boolean.valueOf(((DomImpl.Dom) obj).locale()._saaj.soapText_isComment(org.apache.xmlbeans.impl.soap.Text.this));
                return valueOf;
            }
        })).booleanValue();
    }

    public static void _soapNode_detachNode(Dom n) {
        final org.apache.xmlbeans.impl.soap.Node node = (org.apache.xmlbeans.impl.soap.Node) n;
        syncWrapVoid(n, new Consumer() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda62
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((DomImpl.Dom) obj).locale()._saaj.soapNode_detachNode(org.apache.xmlbeans.impl.soap.Node.this);
            }
        });
    }

    public static void _soapNode_recycleNode(Dom n) {
        final org.apache.xmlbeans.impl.soap.Node node = (org.apache.xmlbeans.impl.soap.Node) n;
        syncWrapVoid(n, new Consumer() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda48
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((DomImpl.Dom) obj).locale()._saaj.soapNode_recycleNode(org.apache.xmlbeans.impl.soap.Node.this);
            }
        });
    }

    public static String _soapNode_getValue(Dom n) {
        final org.apache.xmlbeans.impl.soap.Node node = (org.apache.xmlbeans.impl.soap.Node) n;
        return (String) syncWrap(n, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda51
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                String soapNode_getValue;
                soapNode_getValue = ((DomImpl.Dom) obj).locale()._saaj.soapNode_getValue(org.apache.xmlbeans.impl.soap.Node.this);
                return soapNode_getValue;
            }
        });
    }

    public static void _soapNode_setValue(Dom n, final String value) {
        final org.apache.xmlbeans.impl.soap.Node node = (org.apache.xmlbeans.impl.soap.Node) n;
        syncWrapVoid(n, new Consumer() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda11
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((DomImpl.Dom) obj).locale()._saaj.soapNode_setValue(org.apache.xmlbeans.impl.soap.Node.this, value);
            }
        });
    }

    public static SOAPElement _soapNode_getParentElement(Dom n) {
        final org.apache.xmlbeans.impl.soap.Node node = (org.apache.xmlbeans.impl.soap.Node) n;
        return (SOAPElement) syncWrap(n, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda21
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                SOAPElement soapNode_getParentElement;
                soapNode_getParentElement = ((DomImpl.Dom) obj).locale()._saaj.soapNode_getParentElement(org.apache.xmlbeans.impl.soap.Node.this);
                return soapNode_getParentElement;
            }
        });
    }

    public static void _soapNode_setParentElement(Dom n, final SOAPElement p) {
        final org.apache.xmlbeans.impl.soap.Node node = (org.apache.xmlbeans.impl.soap.Node) n;
        syncWrapVoid(n, new Consumer() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda13
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((DomImpl.Dom) obj).locale()._saaj.soapNode_setParentElement(org.apache.xmlbeans.impl.soap.Node.this, p);
            }
        });
    }

    public static void _soapElement_removeContents(Dom d) {
        final SOAPElement se = (SOAPElement) d;
        syncWrapVoid(d, new Consumer() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda121
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((DomImpl.Dom) obj).locale()._saaj.soapElement_removeContents(SOAPElement.this);
            }
        });
    }

    public static String _soapElement_getEncodingStyle(Dom d) {
        final SOAPElement se = (SOAPElement) d;
        return (String) syncWrap(d, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda89
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                String soapElement_getEncodingStyle;
                soapElement_getEncodingStyle = ((DomImpl.Dom) obj).locale()._saaj.soapElement_getEncodingStyle(SOAPElement.this);
                return soapElement_getEncodingStyle;
            }
        });
    }

    public static void _soapElement_setEncodingStyle(Dom d, final String encodingStyle) {
        final SOAPElement se = (SOAPElement) d;
        syncWrapVoid(d, new Consumer() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda96
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((DomImpl.Dom) obj).locale()._saaj.soapElement_setEncodingStyle(SOAPElement.this, encodingStyle);
            }
        });
    }

    public static boolean _soapElement_removeNamespaceDeclaration(Dom d, final String prefix) {
        final SOAPElement se = (SOAPElement) d;
        return ((Boolean) syncWrap(d, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda22
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Boolean valueOf;
                valueOf = Boolean.valueOf(((DomImpl.Dom) obj).locale()._saaj.soapElement_removeNamespaceDeclaration(SOAPElement.this, prefix));
                return valueOf;
            }
        })).booleanValue();
    }

    public static Iterator<Name> _soapElement_getAllAttributes(Dom d) {
        final SOAPElement se = (SOAPElement) d;
        return (Iterator) syncWrap(d, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda19
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Iterator soapElement_getAllAttributes;
                soapElement_getAllAttributes = ((DomImpl.Dom) obj).locale()._saaj.soapElement_getAllAttributes(SOAPElement.this);
                return soapElement_getAllAttributes;
            }
        });
    }

    public static Iterator<SOAPElement> _soapElement_getChildElements(Dom d) {
        final SOAPElement se = (SOAPElement) d;
        return (Iterator) syncWrap(d, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda79
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Iterator soapElement_getChildElements;
                soapElement_getChildElements = ((DomImpl.Dom) obj).locale()._saaj.soapElement_getChildElements(SOAPElement.this);
                return soapElement_getChildElements;
            }
        });
    }

    public static Iterator<String> _soapElement_getNamespacePrefixes(Dom d) {
        final SOAPElement se = (SOAPElement) d;
        return (Iterator) syncWrap(d, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda30
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Iterator soapElement_getNamespacePrefixes;
                soapElement_getNamespacePrefixes = ((DomImpl.Dom) obj).locale()._saaj.soapElement_getNamespacePrefixes(SOAPElement.this);
                return soapElement_getNamespacePrefixes;
            }
        });
    }

    public static SOAPElement _soapElement_addAttribute(final Dom d, final Name name, final String value) throws SOAPException {
        final SOAPElement se = (SOAPElement) d;
        return (SOAPElement) syncWrapEx(d, new WrapSoapEx() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda60
            @Override // org.apache.xmlbeans.impl.store.DomImpl.WrapSoapEx
            public final Object get() {
                SOAPElement soapElement_addAttribute;
                soapElement_addAttribute = DomImpl.Dom.this.locale()._saaj.soapElement_addAttribute(se, name, value);
                return soapElement_addAttribute;
            }
        });
    }

    public static SOAPElement _soapElement_addChildElement(final Dom d, final SOAPElement oldChild) throws SOAPException {
        final SOAPElement se = (SOAPElement) d;
        return (SOAPElement) syncWrapEx(d, new WrapSoapEx() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda123
            @Override // org.apache.xmlbeans.impl.store.DomImpl.WrapSoapEx
            public final Object get() {
                SOAPElement soapElement_addChildElement;
                soapElement_addChildElement = DomImpl.Dom.this.locale()._saaj.soapElement_addChildElement(se, oldChild);
                return soapElement_addChildElement;
            }
        });
    }

    public static SOAPElement _soapElement_addChildElement(final Dom d, final Name name) throws SOAPException {
        final SOAPElement se = (SOAPElement) d;
        return (SOAPElement) syncWrapEx(d, new WrapSoapEx() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda84
            @Override // org.apache.xmlbeans.impl.store.DomImpl.WrapSoapEx
            public final Object get() {
                SOAPElement soapElement_addChildElement;
                soapElement_addChildElement = DomImpl.Dom.this.locale()._saaj.soapElement_addChildElement(se, name);
                return soapElement_addChildElement;
            }
        });
    }

    public static SOAPElement _soapElement_addChildElement(final Dom d, final String localName) throws SOAPException {
        final SOAPElement se = (SOAPElement) d;
        return (SOAPElement) syncWrapEx(d, new WrapSoapEx() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda100
            @Override // org.apache.xmlbeans.impl.store.DomImpl.WrapSoapEx
            public final Object get() {
                SOAPElement soapElement_addChildElement;
                soapElement_addChildElement = DomImpl.Dom.this.locale()._saaj.soapElement_addChildElement(se, localName);
                return soapElement_addChildElement;
            }
        });
    }

    public static SOAPElement _soapElement_addChildElement(final Dom d, final String localName, final String prefix) throws SOAPException {
        final SOAPElement se = (SOAPElement) d;
        return (SOAPElement) syncWrapEx(d, new WrapSoapEx() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda87
            @Override // org.apache.xmlbeans.impl.store.DomImpl.WrapSoapEx
            public final Object get() {
                SOAPElement soapElement_addChildElement;
                soapElement_addChildElement = DomImpl.Dom.this.locale()._saaj.soapElement_addChildElement(se, localName, prefix);
                return soapElement_addChildElement;
            }
        });
    }

    public static SOAPElement _soapElement_addChildElement(final Dom d, final String localName, final String prefix, final String uri) throws SOAPException {
        final SOAPElement se = (SOAPElement) d;
        return (SOAPElement) syncWrapEx(d, new WrapSoapEx() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda80
            @Override // org.apache.xmlbeans.impl.store.DomImpl.WrapSoapEx
            public final Object get() {
                SOAPElement soapElement_addChildElement;
                soapElement_addChildElement = DomImpl.Dom.this.locale()._saaj.soapElement_addChildElement(se, localName, prefix, uri);
                return soapElement_addChildElement;
            }
        });
    }

    public static SOAPElement _soapElement_addNamespaceDeclaration(Dom d, final String prefix, final String uri) {
        final SOAPElement se = (SOAPElement) d;
        return (SOAPElement) syncWrap(d, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda108
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                SOAPElement soapElement_addNamespaceDeclaration;
                soapElement_addNamespaceDeclaration = ((DomImpl.Dom) obj).locale()._saaj.soapElement_addNamespaceDeclaration(SOAPElement.this, prefix, uri);
                return soapElement_addNamespaceDeclaration;
            }
        });
    }

    public static SOAPElement _soapElement_addTextNode(Dom d, final String data) {
        final SOAPElement se = (SOAPElement) d;
        return (SOAPElement) syncWrap(d, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda92
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                SOAPElement soapElement_addTextNode;
                soapElement_addTextNode = ((DomImpl.Dom) obj).locale()._saaj.soapElement_addTextNode(SOAPElement.this, data);
                return soapElement_addTextNode;
            }
        });
    }

    public static String _soapElement_getAttributeValue(Dom d, final Name name) {
        final SOAPElement se = (SOAPElement) d;
        return (String) syncWrap(d, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda71
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                String soapElement_getAttributeValue;
                soapElement_getAttributeValue = ((DomImpl.Dom) obj).locale()._saaj.soapElement_getAttributeValue(SOAPElement.this, name);
                return soapElement_getAttributeValue;
            }
        });
    }

    public static Iterator<SOAPElement> _soapElement_getChildElements(Dom d, final Name name) {
        final SOAPElement se = (SOAPElement) d;
        return (Iterator) syncWrap(d, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda31
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Iterator soapElement_getChildElements;
                soapElement_getChildElements = ((DomImpl.Dom) obj).locale()._saaj.soapElement_getChildElements(SOAPElement.this, name);
                return soapElement_getChildElements;
            }
        });
    }

    public static Name _soapElement_getElementName(Dom d) {
        final SOAPElement se = (SOAPElement) d;
        return (Name) syncWrap(d, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda36
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Name soapElement_getElementName;
                soapElement_getElementName = ((DomImpl.Dom) obj).locale()._saaj.soapElement_getElementName(SOAPElement.this);
                return soapElement_getElementName;
            }
        });
    }

    public static String _soapElement_getNamespaceURI(Dom d, final String prefix) {
        final SOAPElement se = (SOAPElement) d;
        return (String) syncWrap(d, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda24
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                String soapElement_getNamespaceURI;
                soapElement_getNamespaceURI = ((DomImpl.Dom) obj).locale()._saaj.soapElement_getNamespaceURI(SOAPElement.this, prefix);
                return soapElement_getNamespaceURI;
            }
        });
    }

    public static Iterator<String> _soapElement_getVisibleNamespacePrefixes(Dom d) {
        final SOAPElement se = (SOAPElement) d;
        return (Iterator) syncWrap(d, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda75
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Iterator soapElement_getVisibleNamespacePrefixes;
                soapElement_getVisibleNamespacePrefixes = ((DomImpl.Dom) obj).locale()._saaj.soapElement_getVisibleNamespacePrefixes(SOAPElement.this);
                return soapElement_getVisibleNamespacePrefixes;
            }
        });
    }

    public static boolean _soapElement_removeAttribute(Dom d, final Name name) {
        final SOAPElement se = (SOAPElement) d;
        return ((Boolean) syncWrap(d, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda124
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Boolean valueOf;
                valueOf = Boolean.valueOf(((DomImpl.Dom) obj).locale()._saaj.soapElement_removeAttribute(SOAPElement.this, name));
                return valueOf;
            }
        })).booleanValue();
    }

    public static SOAPBody _soapEnvelope_addBody(final Dom d) throws SOAPException {
        final SOAPEnvelope se = (SOAPEnvelope) d;
        return (SOAPBody) syncWrapEx(d, new WrapSoapEx() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda105
            @Override // org.apache.xmlbeans.impl.store.DomImpl.WrapSoapEx
            public final Object get() {
                SOAPBody soapEnvelope_addBody;
                soapEnvelope_addBody = DomImpl.Dom.this.locale()._saaj.soapEnvelope_addBody(se);
                return soapEnvelope_addBody;
            }
        });
    }

    public static SOAPBody _soapEnvelope_getBody(final Dom d) throws SOAPException {
        final SOAPEnvelope se = (SOAPEnvelope) d;
        return (SOAPBody) syncWrapEx(d, new WrapSoapEx() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda15
            @Override // org.apache.xmlbeans.impl.store.DomImpl.WrapSoapEx
            public final Object get() {
                SOAPBody soapEnvelope_getBody;
                soapEnvelope_getBody = DomImpl.Dom.this.locale()._saaj.soapEnvelope_getBody(se);
                return soapEnvelope_getBody;
            }
        });
    }

    public static SOAPHeader _soapEnvelope_getHeader(final Dom d) throws SOAPException {
        final SOAPEnvelope se = (SOAPEnvelope) d;
        return (SOAPHeader) syncWrapEx(d, new WrapSoapEx() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda20
            @Override // org.apache.xmlbeans.impl.store.DomImpl.WrapSoapEx
            public final Object get() {
                SOAPHeader soapEnvelope_getHeader;
                soapEnvelope_getHeader = DomImpl.Dom.this.locale()._saaj.soapEnvelope_getHeader(se);
                return soapEnvelope_getHeader;
            }
        });
    }

    public static SOAPHeader _soapEnvelope_addHeader(final Dom d) throws SOAPException {
        final SOAPEnvelope se = (SOAPEnvelope) d;
        return (SOAPHeader) syncWrapEx(d, new WrapSoapEx() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda38
            @Override // org.apache.xmlbeans.impl.store.DomImpl.WrapSoapEx
            public final Object get() {
                SOAPHeader soapEnvelope_addHeader;
                soapEnvelope_addHeader = DomImpl.Dom.this.locale()._saaj.soapEnvelope_addHeader(se);
                return soapEnvelope_addHeader;
            }
        });
    }

    public static Name _soapEnvelope_createName(Dom d, final String localName) {
        final SOAPEnvelope se = (SOAPEnvelope) d;
        return (Name) syncWrap(d, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda54
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Name soapEnvelope_createName;
                soapEnvelope_createName = ((DomImpl.Dom) obj).locale()._saaj.soapEnvelope_createName(SOAPEnvelope.this, localName);
                return soapEnvelope_createName;
            }
        });
    }

    public static Name _soapEnvelope_createName(Dom d, final String localName, final String prefix, final String namespaceURI) {
        final SOAPEnvelope se = (SOAPEnvelope) d;
        return (Name) syncWrap(d, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda45
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Name soapEnvelope_createName;
                soapEnvelope_createName = ((DomImpl.Dom) obj).locale()._saaj.soapEnvelope_createName(SOAPEnvelope.this, localName, prefix, namespaceURI);
                return soapEnvelope_createName;
            }
        });
    }

    public static Iterator<SOAPHeaderElement> soapHeader_examineAllHeaderElements(Dom d) {
        final SOAPHeader sh = (SOAPHeader) d;
        return (Iterator) syncWrap(d, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda107
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Iterator soapHeader_examineAllHeaderElements;
                soapHeader_examineAllHeaderElements = ((DomImpl.Dom) obj).locale()._saaj.soapHeader_examineAllHeaderElements(SOAPHeader.this);
                return soapHeader_examineAllHeaderElements;
            }
        });
    }

    public static Iterator<SOAPHeaderElement> soapHeader_extractAllHeaderElements(Dom d) {
        final SOAPHeader sh = (SOAPHeader) d;
        return (Iterator) syncWrap(d, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda55
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Iterator soapHeader_extractAllHeaderElements;
                soapHeader_extractAllHeaderElements = ((DomImpl.Dom) obj).locale()._saaj.soapHeader_extractAllHeaderElements(SOAPHeader.this);
                return soapHeader_extractAllHeaderElements;
            }
        });
    }

    public static Iterator<SOAPHeaderElement> soapHeader_examineHeaderElements(Dom d, final String actor) {
        final SOAPHeader sh = (SOAPHeader) d;
        return (Iterator) syncWrap(d, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda26
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Iterator soapHeader_examineHeaderElements;
                soapHeader_examineHeaderElements = ((DomImpl.Dom) obj).locale()._saaj.soapHeader_examineHeaderElements(SOAPHeader.this, actor);
                return soapHeader_examineHeaderElements;
            }
        });
    }

    public static Iterator<SOAPHeaderElement> soapHeader_examineMustUnderstandHeaderElements(Dom d, final String mustUnderstandString) {
        final SOAPHeader sh = (SOAPHeader) d;
        return (Iterator) syncWrap(d, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda47
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Iterator soapHeader_examineMustUnderstandHeaderElements;
                soapHeader_examineMustUnderstandHeaderElements = ((DomImpl.Dom) obj).locale()._saaj.soapHeader_examineMustUnderstandHeaderElements(SOAPHeader.this, mustUnderstandString);
                return soapHeader_examineMustUnderstandHeaderElements;
            }
        });
    }

    public static Iterator<SOAPHeaderElement> soapHeader_extractHeaderElements(Dom d, final String actor) {
        final SOAPHeader sh = (SOAPHeader) d;
        return (Iterator) syncWrap(d, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda16
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Iterator soapHeader_extractHeaderElements;
                soapHeader_extractHeaderElements = ((DomImpl.Dom) obj).locale()._saaj.soapHeader_extractHeaderElements(SOAPHeader.this, actor);
                return soapHeader_extractHeaderElements;
            }
        });
    }

    public static SOAPHeaderElement soapHeader_addHeaderElement(Dom d, final Name name) {
        final SOAPHeader sh = (SOAPHeader) d;
        return (SOAPHeaderElement) syncWrap(d, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda61
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                SOAPHeaderElement soapHeader_addHeaderElement;
                soapHeader_addHeaderElement = ((DomImpl.Dom) obj).locale()._saaj.soapHeader_addHeaderElement(SOAPHeader.this, name);
                return soapHeader_addHeaderElement;
            }
        });
    }

    public static boolean soapBody_hasFault(Dom d) {
        final SOAPBody sb = (SOAPBody) d;
        return ((Boolean) syncWrap(d, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda65
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Boolean valueOf;
                valueOf = Boolean.valueOf(((DomImpl.Dom) obj).locale()._saaj.soapBody_hasFault(SOAPBody.this));
                return valueOf;
            }
        })).booleanValue();
    }

    public static SOAPFault soapBody_addFault(final Dom d) throws SOAPException {
        final SOAPBody sb = (SOAPBody) d;
        return (SOAPFault) syncWrapEx(d, new WrapSoapEx() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda103
            @Override // org.apache.xmlbeans.impl.store.DomImpl.WrapSoapEx
            public final Object get() {
                SOAPFault soapBody_addFault;
                soapBody_addFault = DomImpl.Dom.this.locale()._saaj.soapBody_addFault(sb);
                return soapBody_addFault;
            }
        });
    }

    public static SOAPFault soapBody_getFault(Dom d) {
        final SOAPBody sb = (SOAPBody) d;
        return (SOAPFault) syncWrap(d, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda78
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                SOAPFault soapBody_getFault;
                soapBody_getFault = ((DomImpl.Dom) obj).locale()._saaj.soapBody_getFault(SOAPBody.this);
                return soapBody_getFault;
            }
        });
    }

    public static SOAPBodyElement soapBody_addBodyElement(Dom d, final Name name) {
        final SOAPBody sb = (SOAPBody) d;
        return (SOAPBodyElement) syncWrap(d, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda10
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                SOAPBodyElement soapBody_addBodyElement;
                soapBody_addBodyElement = ((DomImpl.Dom) obj).locale()._saaj.soapBody_addBodyElement(SOAPBody.this, name);
                return soapBody_addBodyElement;
            }
        });
    }

    public static SOAPBodyElement soapBody_addDocument(Dom d, final Document document) {
        final SOAPBody sb = (SOAPBody) d;
        return (SOAPBodyElement) syncWrap(d, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda97
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                SOAPBodyElement soapBody_addDocument;
                soapBody_addDocument = ((DomImpl.Dom) obj).locale()._saaj.soapBody_addDocument(SOAPBody.this, document);
                return soapBody_addDocument;
            }
        });
    }

    public static SOAPFault soapBody_addFault(final Dom d, final Name name, final String s) throws SOAPException {
        final SOAPBody sb = (SOAPBody) d;
        return (SOAPFault) syncWrapEx(d, new WrapSoapEx() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda98
            @Override // org.apache.xmlbeans.impl.store.DomImpl.WrapSoapEx
            public final Object get() {
                SOAPFault soapBody_addFault;
                soapBody_addFault = DomImpl.Dom.this.locale()._saaj.soapBody_addFault(sb, name, s);
                return soapBody_addFault;
            }
        });
    }

    public static SOAPFault soapBody_addFault(final Dom d, final Name faultCode, final String faultString, final java.util.Locale locale) throws SOAPException {
        final SOAPBody sb = (SOAPBody) d;
        return (SOAPFault) syncWrapEx(d, new WrapSoapEx() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda74
            @Override // org.apache.xmlbeans.impl.store.DomImpl.WrapSoapEx
            public final Object get() {
                SOAPFault soapBody_addFault;
                soapBody_addFault = DomImpl.Dom.this.locale()._saaj.soapBody_addFault(sb, faultCode, faultString, locale);
                return soapBody_addFault;
            }
        });
    }

    public static void soapFault_setFaultString(Dom d, final String faultString) {
        final SOAPFault sf = (SOAPFault) d;
        syncWrapVoid(d, new Consumer() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda17
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((DomImpl.Dom) obj).locale()._saaj.soapFault_setFaultString(SOAPFault.this, faultString);
            }
        });
    }

    public static void soapFault_setFaultString(Dom d, final String faultString, final java.util.Locale locale) {
        final SOAPFault sf = (SOAPFault) d;
        syncWrapVoid(d, new Consumer() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda43
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((DomImpl.Dom) obj).locale()._saaj.soapFault_setFaultString(SOAPFault.this, faultString, locale);
            }
        });
    }

    public static void soapFault_setFaultCode(final Dom d, final Name faultCodeName) throws SOAPException {
        final SOAPFault sf = (SOAPFault) d;
        syncWrapEx(d, new WrapSoapEx() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda9
            @Override // org.apache.xmlbeans.impl.store.DomImpl.WrapSoapEx
            public final Object get() {
                return DomImpl.lambda$soapFault_setFaultCode$77(DomImpl.Dom.this, sf, faultCodeName);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object lambda$soapFault_setFaultCode$77(Dom d, SOAPFault sf, Name faultCodeName) throws SOAPException {
        d.locale()._saaj.soapFault_setFaultCode(sf, faultCodeName);
        return null;
    }

    public static void soapFault_setFaultActor(Dom d, final String faultActorString) {
        final SOAPFault sf = (SOAPFault) d;
        syncWrapVoid(d, new Consumer() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda53
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((DomImpl.Dom) obj).locale()._saaj.soapFault_setFaultActor(SOAPFault.this, faultActorString);
            }
        });
    }

    public static String soapFault_getFaultActor(Dom d) {
        final SOAPFault sf = (SOAPFault) d;
        return (String) syncWrap(d, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda127
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                String soapFault_getFaultActor;
                soapFault_getFaultActor = ((DomImpl.Dom) obj).locale()._saaj.soapFault_getFaultActor(SOAPFault.this);
                return soapFault_getFaultActor;
            }
        });
    }

    public static String soapFault_getFaultCode(Dom d) {
        final SOAPFault sf = (SOAPFault) d;
        return (String) syncWrap(d, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda56
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                String soapFault_getFaultCode;
                soapFault_getFaultCode = ((DomImpl.Dom) obj).locale()._saaj.soapFault_getFaultCode(SOAPFault.this);
                return soapFault_getFaultCode;
            }
        });
    }

    public static void soapFault_setFaultCode(final Dom d, final String faultCode) throws SOAPException {
        final SOAPFault sf = (SOAPFault) d;
        syncWrapEx(d, new WrapSoapEx() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda111
            @Override // org.apache.xmlbeans.impl.store.DomImpl.WrapSoapEx
            public final Object get() {
                return DomImpl.lambda$soapFault_setFaultCode$81(DomImpl.Dom.this, sf, faultCode);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object lambda$soapFault_setFaultCode$81(Dom d, SOAPFault sf, String faultCode) throws SOAPException {
        d.locale()._saaj.soapFault_setFaultCode(sf, faultCode);
        return null;
    }

    public static java.util.Locale soapFault_getFaultStringLocale(Dom d) {
        final SOAPFault sf = (SOAPFault) d;
        return (java.util.Locale) syncWrap(d, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda76
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                java.util.Locale soapFault_getFaultStringLocale;
                soapFault_getFaultStringLocale = ((DomImpl.Dom) obj).locale()._saaj.soapFault_getFaultStringLocale(SOAPFault.this);
                return soapFault_getFaultStringLocale;
            }
        });
    }

    public static Name soapFault_getFaultCodeAsName(Dom d) {
        final SOAPFault sf = (SOAPFault) d;
        return (Name) syncWrap(d, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda101
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Name soapFault_getFaultCodeAsName;
                soapFault_getFaultCodeAsName = ((DomImpl.Dom) obj).locale()._saaj.soapFault_getFaultCodeAsName(SOAPFault.this);
                return soapFault_getFaultCodeAsName;
            }
        });
    }

    public static String soapFault_getFaultString(Dom d) {
        final SOAPFault sf = (SOAPFault) d;
        return (String) syncWrap(d, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda110
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                String soapFault_getFaultString;
                soapFault_getFaultString = ((DomImpl.Dom) obj).locale()._saaj.soapFault_getFaultString(SOAPFault.this);
                return soapFault_getFaultString;
            }
        });
    }

    public static Detail soapFault_addDetail(final Dom d) throws SOAPException {
        final SOAPFault sf = (SOAPFault) d;
        return (Detail) syncWrapEx(d, new WrapSoapEx() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda99
            @Override // org.apache.xmlbeans.impl.store.DomImpl.WrapSoapEx
            public final Object get() {
                Detail soapFault_addDetail;
                soapFault_addDetail = DomImpl.Dom.this.locale()._saaj.soapFault_addDetail(sf);
                return soapFault_addDetail;
            }
        });
    }

    public static Detail soapFault_getDetail(Dom d) {
        final SOAPFault sf = (SOAPFault) d;
        return (Detail) syncWrap(d, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda91
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Detail soapFault_getDetail;
                soapFault_getDetail = ((DomImpl.Dom) obj).locale()._saaj.soapFault_getDetail(SOAPFault.this);
                return soapFault_getDetail;
            }
        });
    }

    public static void soapHeaderElement_setMustUnderstand(Dom d, final boolean mustUnderstand) {
        final SOAPHeaderElement she = (SOAPHeaderElement) d;
        syncWrapVoid(d, new Consumer() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda88
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((DomImpl.Dom) obj).locale()._saaj.soapHeaderElement_setMustUnderstand(SOAPHeaderElement.this, mustUnderstand);
            }
        });
    }

    public static boolean soapHeaderElement_getMustUnderstand(Dom d) {
        final SOAPHeaderElement she = (SOAPHeaderElement) d;
        return ((Boolean) syncWrap(d, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda18
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Boolean valueOf;
                valueOf = Boolean.valueOf(((DomImpl.Dom) obj).locale()._saaj.soapHeaderElement_getMustUnderstand(SOAPHeaderElement.this));
                return valueOf;
            }
        })).booleanValue();
    }

    public static void soapHeaderElement_setActor(Dom d, final String actor) {
        final SOAPHeaderElement she = (SOAPHeaderElement) d;
        syncWrapVoid(d, new Consumer() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda109
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((DomImpl.Dom) obj).locale()._saaj.soapHeaderElement_setActor(SOAPHeaderElement.this, actor);
            }
        });
    }

    public static String soapHeaderElement_getActor(Dom d) {
        final SOAPHeaderElement she = (SOAPHeaderElement) d;
        return (String) syncWrap(d, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda44
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                String soapHeaderElement_getActor;
                soapHeaderElement_getActor = ((DomImpl.Dom) obj).locale()._saaj.soapHeaderElement_getActor(SOAPHeaderElement.this);
                return soapHeaderElement_getActor;
            }
        });
    }

    public static DetailEntry detail_addDetailEntry(Dom d, final Name name) {
        final Detail detail = (Detail) d;
        return (DetailEntry) syncWrap(d, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda5
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                DetailEntry detail_addDetailEntry;
                detail_addDetailEntry = ((DomImpl.Dom) obj).locale()._saaj.detail_addDetailEntry(Detail.this, name);
                return detail_addDetailEntry;
            }
        });
    }

    public static Iterator<DetailEntry> detail_getDetailEntries(Dom d) {
        final Detail detail = (Detail) d;
        return (Iterator) syncWrap(d, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda82
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Iterator detail_getDetailEntries;
                detail_getDetailEntries = ((DomImpl.Dom) obj).locale()._saaj.detail_getDetailEntries(Detail.this);
                return detail_getDetailEntries;
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void _soapPart_removeAllMimeHeaders(Dom dom) {
        final SOAPPart sp = (SOAPPart) dom;
        syncWrapVoid(dom, new Consumer() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda8
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((DomImpl.Dom) obj).locale()._saaj.soapPart_removeAllMimeHeaders(SOAPPart.this);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void _soapPart_removeMimeHeader(Dom dom, final String name) {
        final SOAPPart sp = (SOAPPart) dom;
        syncWrapVoid(dom, new Consumer() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda25
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((DomImpl.Dom) obj).locale()._saaj.soapPart_removeMimeHeader(SOAPPart.this, name);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Iterator<MimeHeader> _soapPart_getAllMimeHeaders(Dom dom) {
        final SOAPPart sp = (SOAPPart) dom;
        return (Iterator) syncWrap(dom, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda66
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Iterator soapPart_getAllMimeHeaders;
                soapPart_getAllMimeHeaders = ((DomImpl.Dom) obj).locale()._saaj.soapPart_getAllMimeHeaders(SOAPPart.this);
                return soapPart_getAllMimeHeaders;
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static SOAPEnvelope _soapPart_getEnvelope(Dom dom) {
        final SOAPPart sp = (SOAPPart) dom;
        return (SOAPEnvelope) syncWrap(dom, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda40
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                SOAPEnvelope soapPart_getEnvelope;
                soapPart_getEnvelope = ((DomImpl.Dom) obj).locale()._saaj.soapPart_getEnvelope(SOAPPart.this);
                return soapPart_getEnvelope;
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Source _soapPart_getContent(Dom dom) {
        final SOAPPart sp = (SOAPPart) dom;
        return (Source) syncWrap(dom, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda7
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Source soapPart_getContent;
                soapPart_getContent = ((DomImpl.Dom) obj).locale()._saaj.soapPart_getContent(SOAPPart.this);
                return soapPart_getContent;
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void _soapPart_setContent(Dom dom, final Source source) {
        final SOAPPart sp = (SOAPPart) dom;
        syncWrapVoid(dom, new Consumer() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda112
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((DomImpl.Dom) obj).locale()._saaj.soapPart_setContent(SOAPPart.this, source);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static String[] _soapPart_getMimeHeader(Dom dom, final String name) {
        final SOAPPart sp = (SOAPPart) dom;
        return (String[]) syncWrap(dom, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda119
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                String[] soapPart_getMimeHeader;
                soapPart_getMimeHeader = ((DomImpl.Dom) obj).locale()._saaj.soapPart_getMimeHeader(SOAPPart.this, name);
                return soapPart_getMimeHeader;
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void _soapPart_addMimeHeader(Dom dom, final String name, final String value) {
        final SOAPPart sp = (SOAPPart) dom;
        syncWrapVoid(dom, new Consumer() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda68
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((DomImpl.Dom) obj).locale()._saaj.soapPart_addMimeHeader(SOAPPart.this, name, value);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void _soapPart_setMimeHeader(Dom dom, final String name, final String value) {
        final SOAPPart sp = (SOAPPart) dom;
        syncWrapVoid(dom, new Consumer() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((DomImpl.Dom) obj).locale()._saaj.soapPart_setMimeHeader(SOAPPart.this, name, value);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Iterator<MimeHeader> _soapPart_getMatchingMimeHeaders(Dom dom, final String[] names) {
        final SOAPPart sp = (SOAPPart) dom;
        return (Iterator) syncWrap(dom, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda115
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Iterator soapPart_getMatchingMimeHeaders;
                soapPart_getMatchingMimeHeaders = ((DomImpl.Dom) obj).locale()._saaj.soapPart_getMatchingMimeHeaders(SOAPPart.this, names);
                return soapPart_getMatchingMimeHeaders;
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Iterator<MimeHeader> _soapPart_getNonMatchingMimeHeaders(Dom dom, final String[] names) {
        final SOAPPart sp = (SOAPPart) dom;
        return (Iterator) syncWrap(dom, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda77
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Iterator soapPart_getNonMatchingMimeHeaders;
                soapPart_getNonMatchingMimeHeaders = ((DomImpl.Dom) obj).locale()._saaj.soapPart_getNonMatchingMimeHeaders(SOAPPart.this, names);
                return soapPart_getNonMatchingMimeHeaders;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static class SaajData {
        Object _obj;

        private SaajData() {
        }
    }

    public static void saajCallback_setSaajData(Dom d, final Object o) {
        syncWrapVoid(d, new Consumer() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda72
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                DomImpl.impl_saajCallback_setSaajData((DomImpl.Dom) obj, o);
            }
        });
    }

    public static void impl_saajCallback_setSaajData(Dom d, Object o) {
        Locale l = d.locale();
        Cur c = l.tempCur();
        c.moveToDom(d);
        SaajData sd = null;
        if (o != null) {
            sd = (SaajData) c.getBookmark(SaajData.class);
            if (sd == null) {
                sd = new SaajData();
            }
            sd._obj = o;
        }
        c.setBookmark(SaajData.class, sd);
        c.release();
    }

    public static Object saajCallback_getSaajData(Dom d) {
        return syncWrap(d, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda69
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return DomImpl.impl_saajCallback_getSaajData((DomImpl.Dom) obj);
            }
        });
    }

    public static Object impl_saajCallback_getSaajData(Dom d) {
        Locale l = d.locale();
        Cur c = l.tempCur();
        c.moveToDom(d);
        SaajData sd = (SaajData) c.getBookmark(SaajData.class);
        Object o = sd == null ? null : sd._obj;
        c.release();
        return o;
    }

    public static Element saajCallback_createSoapElement(Dom d, final QName name, final QName parentName) {
        return (Element) syncWrap(d, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda2
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Element impl_saajCallback_createSoapElement;
                impl_saajCallback_createSoapElement = DomImpl.impl_saajCallback_createSoapElement((DomImpl.Dom) obj, name, parentName);
                return impl_saajCallback_createSoapElement;
            }
        });
    }

    public static Element impl_saajCallback_createSoapElement(Dom d, QName name, QName parentName) {
        Cur c = d.locale().tempCur();
        c.createElement(name, parentName);
        Dom e = c.getDom();
        c.release();
        return (Element) e;
    }

    public static Element saajCallback_importSoapElement(Dom d, final Element elem, final boolean deep, final QName parentName) {
        return (Element) syncWrap(d, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda90
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Element impl_saajCallback_importSoapElement;
                impl_saajCallback_importSoapElement = DomImpl.impl_saajCallback_importSoapElement((DomImpl.Dom) obj, elem, deep, parentName);
                return impl_saajCallback_importSoapElement;
            }
        });
    }

    public static Element impl_saajCallback_importSoapElement(Dom d, Element elem, boolean deep, QName parentName) {
        throw new RuntimeException("Not impl");
    }

    public static Text saajCallback_ensureSoapTextNode(Dom d) {
        return (Text) syncWrap(d, new Function() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda27
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return DomImpl.impl_saajCallback_ensureSoapTextNode((DomImpl.Dom) obj);
            }
        });
    }

    public static Text impl_saajCallback_ensureSoapTextNode(Dom d) {
        return null;
    }

    /* loaded from: classes11.dex */
    public static class DomLevel3NotImplemented extends RuntimeException {
        DomLevel3NotImplemented() {
            super("DOM Level 3 Not implemented");
        }
    }

    private static <T> T syncWrap(final Dom dom, final Function<Dom, T> function) {
        return (T) syncWrapHelper(dom.locale(), true, new Supplier() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda49
            @Override // java.util.function.Supplier
            public final Object get() {
                Object apply;
                apply = function.apply(dom);
                return apply;
            }
        });
    }

    private static <T> T syncWrapNoEnter(final Dom dom, final Function<Dom, T> function) {
        return (T) syncWrapHelper(dom.locale(), false, new Supplier() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda73
            @Override // java.util.function.Supplier
            public final Object get() {
                Object apply;
                apply = function.apply(dom);
                return apply;
            }
        });
    }

    private static void syncWrapVoid(final Dom d, final Consumer<Dom> inner) {
        syncWrapHelper(d.locale(), true, new Supplier() { // from class: org.apache.xmlbeans.impl.store.DomImpl$$ExternalSyntheticLambda57
            @Override // java.util.function.Supplier
            public final Object get() {
                return DomImpl.lambda$syncWrapVoid$109(inner, d);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object lambda$syncWrapVoid$109(Consumer inner, Dom d) {
        inner.accept(d);
        return null;
    }

    private static <T> T syncWrapEx(Dom dom, WrapSoapEx<T> wrapSoapEx) throws SOAPException {
        return (T) syncWrapHelperEx(dom.locale(), true, wrapSoapEx);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> T syncWrapHelper(Locale locale, boolean z, Supplier<T> supplier) {
        T t;
        if (locale.noSync()) {
            return (T) syncWrapHelper2(locale, z, supplier);
        }
        synchronized (locale) {
            t = (T) syncWrapHelper2(locale, z, supplier);
        }
        return t;
    }

    private static <T> T syncWrapHelper2(Locale l, boolean enter, Supplier<T> inner) {
        if (enter) {
            l.enter();
        }
        try {
            return inner.get();
        } finally {
            if (enter) {
                l.exit();
            }
        }
    }

    private static <T> T syncWrapHelperEx(Locale locale, boolean z, WrapSoapEx<T> wrapSoapEx) throws SOAPException {
        T t;
        if (locale.noSync()) {
            return (T) syncWrapHelperEx2(locale, z, wrapSoapEx);
        }
        synchronized (locale) {
            t = (T) syncWrapHelperEx2(locale, z, wrapSoapEx);
        }
        return t;
    }

    private static <T> T syncWrapHelperEx2(Locale l, boolean enter, WrapSoapEx<T> inner) throws SOAPException {
        if (enter) {
            l.enter();
        }
        try {
            return inner.get();
        } finally {
            if (enter) {
                l.exit();
            }
        }
    }
}
