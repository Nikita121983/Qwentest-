package org.apache.xmlbeans.impl.store;

import java.io.PrintStream;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.impl.store.DomImpl;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.UserDataHandler;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public abstract class CharNode implements DomImpl.Dom, Node, CharacterData {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    int _cch;
    private Locale _locale;
    CharNode _next;
    int _off;
    CharNode _prev;
    private Object _src;

    public CharNode(Locale l) {
        if (l == null) {
            throw new AssertionError();
        }
        this._locale = l;
    }

    @Override // org.apache.xmlbeans.impl.store.DomImpl.Dom
    public QName getQName() {
        return null;
    }

    @Override // org.apache.xmlbeans.impl.store.DomImpl.Dom
    public Locale locale() {
        if (isValid()) {
            return this._locale == null ? ((DomImpl.Dom) this._src).locale() : this._locale;
        }
        throw new AssertionError();
    }

    public void setChars(Object src, int off, int cch) {
        if (!CharUtil.isValid(src, off, cch)) {
            throw new AssertionError();
        }
        if (this._locale == null && !(this._src instanceof DomImpl.Dom)) {
            throw new AssertionError();
        }
        if (this._locale == null) {
            this._locale = ((DomImpl.Dom) this._src).locale();
        }
        this._src = src;
        this._off = off;
        this._cch = cch;
    }

    public DomImpl.Dom getDom() {
        if (!isValid()) {
            throw new AssertionError();
        }
        if (this._src instanceof DomImpl.Dom) {
            return (DomImpl.Dom) this._src;
        }
        return null;
    }

    public void setDom(DomImpl.Dom d) {
        if (d == null) {
            throw new AssertionError();
        }
        this._src = d;
        this._locale = null;
    }

    @Override // org.apache.xmlbeans.impl.store.DomImpl.Dom
    public Cur tempCur() {
        if (!isValid()) {
            throw new AssertionError();
        }
        if (!(this._src instanceof DomImpl.Dom)) {
            return null;
        }
        Cur c = locale().tempCur();
        c.moveToCharNode(this);
        return c;
    }

    private boolean isValid() {
        return (this._src instanceof DomImpl.Dom) == (this._locale == null);
    }

    public static boolean isOnList(CharNode nodes, CharNode node) {
        if (node == null) {
            throw new AssertionError();
        }
        for (CharNode cn = nodes; cn != null; cn = cn._next) {
            if (cn == node) {
                return true;
            }
        }
        return false;
    }

    public static CharNode remove(CharNode nodes, CharNode node) {
        if (!isOnList(nodes, node)) {
            throw new AssertionError();
        }
        if (nodes == node) {
            nodes = node._next;
        } else {
            node._prev._next = node._next;
        }
        if (node._next != null) {
            node._next._prev = node._prev;
        }
        node._next = null;
        node._prev = null;
        return nodes;
    }

    public static CharNode insertNode(CharNode nodes, CharNode newNode, CharNode before) {
        if (isOnList(nodes, newNode)) {
            throw new AssertionError();
        }
        if (before != null && !isOnList(nodes, before)) {
            throw new AssertionError();
        }
        if (newNode == null) {
            throw new AssertionError();
        }
        if (newNode._prev != null || newNode._next != null) {
            throw new AssertionError();
        }
        if (nodes == null) {
            if (before != null) {
                throw new AssertionError();
            }
            return newNode;
        }
        if (nodes == before) {
            nodes._prev = newNode;
            newNode._next = nodes;
            return newNode;
        }
        CharNode n = nodes;
        while (n._next != before) {
            n = n._next;
        }
        CharNode charNode = n._next;
        newNode._next = charNode;
        if (charNode != null) {
            n._next._prev = newNode;
        }
        newNode._prev = n;
        n._next = newNode;
        return nodes;
    }

    public static CharNode appendNode(CharNode nodes, CharNode newNode) {
        return insertNode(nodes, newNode, null);
    }

    public static CharNode appendNodes(CharNode nodes, CharNode newNodes) {
        if (newNodes == null) {
            throw new AssertionError();
        }
        if (newNodes._prev != null) {
            throw new AssertionError();
        }
        if (nodes == null) {
            return newNodes;
        }
        CharNode n = nodes;
        while (n._next != null) {
            n = n._next;
        }
        n._next = newNodes;
        newNodes._prev = n;
        return nodes;
    }

    public static CharNode copyNodes(CharNode nodes, Object newSrc) {
        CharNode newNode;
        CharNode newNodes = null;
        CharNode n = null;
        while (nodes != null) {
            if (nodes instanceof TextNode) {
                newNode = nodes.locale().createTextNode();
            } else {
                newNode = nodes.locale().createCdataNode();
            }
            newNode.setChars(newSrc, nodes._off, nodes._cch);
            if (newNodes == null) {
                newNodes = newNode;
            }
            if (n != null) {
                n._next = newNode;
                newNode._prev = n;
            }
            n = newNode;
            nodes = nodes._next;
        }
        return newNodes;
    }

    @Override // org.apache.xmlbeans.impl.store.DomImpl.Dom
    public boolean nodeCanHavePrefixUri() {
        return false;
    }

    public boolean isNodeAftertext() {
        if (!(this._src instanceof Xobj)) {
            throw new AssertionError("this method is to only be used for nodes backed up by Xobjs");
        }
        Xobj src = (Xobj) this._src;
        if (src._charNodesValue == null) {
            return true;
        }
        if (src._charNodesAfter == null) {
            return false;
        }
        return isOnList(src._charNodesAfter, this);
    }

    @Override // org.apache.xmlbeans.impl.store.DomImpl.Dom
    public void dump(PrintStream o, Object ref) {
        if (this._src instanceof DomImpl.Dom) {
            ((DomImpl.Dom) this._src).dump(o, ref);
        } else {
            o.println("Lonely CharNode: \"" + CharUtil.getString(this._src, this._off, this._cch) + "\"");
        }
    }

    @Override // org.apache.xmlbeans.impl.store.DomImpl.Dom
    public void dump(PrintStream o) {
        dump(o, this);
    }

    @Override // org.apache.xmlbeans.impl.store.DomImpl.Dom
    public void dump() {
        dump(System.out);
    }

    @Override // org.w3c.dom.Node
    public Node appendChild(Node newChild) {
        return DomImpl._node_appendChild(this, newChild);
    }

    @Override // org.w3c.dom.Node
    public Node cloneNode(boolean deep) {
        return DomImpl._node_cloneNode(this, deep);
    }

    @Override // org.w3c.dom.Node
    public NamedNodeMap getAttributes() {
        return null;
    }

    @Override // org.w3c.dom.Node
    public NodeList getChildNodes() {
        return DomImpl._emptyNodeList;
    }

    @Override // org.w3c.dom.Node
    public Node getParentNode() {
        return DomImpl._node_getParentNode(this);
    }

    @Override // org.w3c.dom.Node
    public Node removeChild(Node oldChild) {
        return DomImpl._node_removeChild(this, oldChild);
    }

    @Override // org.w3c.dom.Node
    public Node getFirstChild() {
        return null;
    }

    @Override // org.w3c.dom.Node
    public Node getLastChild() {
        return null;
    }

    @Override // org.w3c.dom.Node
    public String getLocalName() {
        return DomImpl._node_getLocalName(this);
    }

    @Override // org.w3c.dom.Node
    public String getNamespaceURI() {
        return DomImpl._node_getNamespaceURI(this);
    }

    @Override // org.w3c.dom.Node
    public Node getNextSibling() {
        return DomImpl._node_getNextSibling(this);
    }

    @Override // org.w3c.dom.Node
    public String getNodeName() {
        return DomImpl._node_getNodeName(this);
    }

    @Override // org.w3c.dom.Node
    public short getNodeType() {
        return DomImpl._node_getNodeType(this);
    }

    @Override // org.w3c.dom.Node
    public String getNodeValue() {
        return DomImpl._node_getNodeValue(this);
    }

    @Override // org.w3c.dom.Node
    public Document getOwnerDocument() {
        return DomImpl._node_getOwnerDocument(this);
    }

    @Override // org.w3c.dom.Node
    public String getPrefix() {
        return DomImpl._node_getPrefix(this);
    }

    @Override // org.w3c.dom.Node
    public Node getPreviousSibling() {
        return DomImpl._node_getPreviousSibling(this);
    }

    @Override // org.w3c.dom.Node
    public boolean hasAttributes() {
        return false;
    }

    @Override // org.w3c.dom.Node
    public boolean hasChildNodes() {
        return false;
    }

    @Override // org.w3c.dom.Node
    public Node insertBefore(Node newChild, Node refChild) {
        return DomImpl._node_insertBefore(this, newChild, refChild);
    }

    @Override // org.w3c.dom.Node
    public boolean isSupported(String feature, String version) {
        return DomImpl._node_isSupported(this, feature, version);
    }

    @Override // org.w3c.dom.Node
    public void normalize() {
        DomImpl._node_normalize(this);
    }

    @Override // org.w3c.dom.Node
    public Node replaceChild(Node newChild, Node oldChild) {
        return DomImpl._node_replaceChild(this, newChild, oldChild);
    }

    @Override // org.w3c.dom.Node
    public void setNodeValue(String nodeValue) {
        DomImpl._node_setNodeValue(this, nodeValue);
    }

    @Override // org.w3c.dom.Node
    public void setPrefix(String prefix) {
        DomImpl._node_setPrefix(this, prefix);
    }

    @Override // org.w3c.dom.Node
    public Object getUserData(String key) {
        return DomImpl._node_getUserData(this, key);
    }

    @Override // org.w3c.dom.Node
    public Object setUserData(String key, Object data, UserDataHandler handler) {
        return DomImpl._node_setUserData(this, key, data, handler);
    }

    @Override // org.w3c.dom.Node
    public Object getFeature(String feature, String version) {
        return DomImpl._node_getFeature(this, feature, version);
    }

    @Override // org.w3c.dom.Node
    public boolean isEqualNode(Node arg) {
        return DomImpl._node_isEqualNode(this, arg);
    }

    @Override // org.w3c.dom.Node
    public boolean isSameNode(Node arg) {
        return DomImpl._node_isSameNode(this, arg);
    }

    @Override // org.w3c.dom.Node
    public String lookupNamespaceURI(String prefix) {
        return DomImpl._node_lookupNamespaceURI(this, prefix);
    }

    @Override // org.w3c.dom.Node
    public String lookupPrefix(String namespaceURI) {
        return DomImpl._node_lookupPrefix(this, namespaceURI);
    }

    @Override // org.w3c.dom.Node
    public boolean isDefaultNamespace(String namespaceURI) {
        return DomImpl._node_isDefaultNamespace(this, namespaceURI);
    }

    @Override // org.w3c.dom.Node
    public void setTextContent(String textContent) {
        DomImpl._node_setTextContent(this, textContent);
    }

    @Override // org.w3c.dom.Node
    public String getTextContent() {
        return DomImpl._node_getTextContent(this);
    }

    @Override // org.w3c.dom.Node
    public short compareDocumentPosition(Node other) {
        return DomImpl._node_compareDocumentPosition(this, other);
    }

    @Override // org.w3c.dom.Node
    public String getBaseURI() {
        return DomImpl._node_getBaseURI(this);
    }

    @Override // org.w3c.dom.CharacterData
    public void appendData(String arg) {
        DomImpl._characterData_appendData(this, arg);
    }

    @Override // org.w3c.dom.CharacterData
    public void deleteData(int offset, int count) {
        DomImpl._characterData_deleteData(this, offset, count);
    }

    @Override // org.w3c.dom.CharacterData
    public String getData() {
        return DomImpl._characterData_getData(this);
    }

    @Override // org.w3c.dom.CharacterData
    public int getLength() {
        return DomImpl._characterData_getLength(this);
    }

    @Override // org.w3c.dom.CharacterData
    public void insertData(int offset, String arg) {
        DomImpl._characterData_insertData(this, offset, arg);
    }

    @Override // org.w3c.dom.CharacterData
    public void replaceData(int offset, int count, String arg) {
        DomImpl._characterData_replaceData(this, offset, count, arg);
    }

    @Override // org.w3c.dom.CharacterData
    public void setData(String data) {
        DomImpl._characterData_setData(this, data);
    }

    @Override // org.w3c.dom.CharacterData
    public String substringData(int offset, int count) {
        return DomImpl._characterData_substringData(this, offset, count);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Object getObject() {
        return this._src;
    }
}
