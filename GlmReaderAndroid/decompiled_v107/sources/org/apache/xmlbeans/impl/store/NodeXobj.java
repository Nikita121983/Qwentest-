package org.apache.xmlbeans.impl.store;

import org.apache.xmlbeans.impl.store.DomImpl;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.UserDataHandler;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public abstract class NodeXobj extends Xobj implements DomImpl.Dom, Node, NodeList {
    /* JADX INFO: Access modifiers changed from: package-private */
    public NodeXobj(Locale l, int kind, int domType) {
        super(l, kind, domType);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.xmlbeans.impl.store.Xobj
    public DomImpl.Dom getDom() {
        return this;
    }

    public int getLength() {
        return DomImpl._childNodes_getLength(this);
    }

    @Override // org.w3c.dom.NodeList
    public Node item(int i) {
        return DomImpl._childNodes_item(this, i);
    }

    @Override // org.w3c.dom.Node
    public Node appendChild(Node newChild) {
        return DomImpl._node_appendChild(this, newChild);
    }

    @Override // org.w3c.dom.Node
    public Node cloneNode(boolean deep) {
        return DomImpl._node_cloneNode(this, deep);
    }

    public NamedNodeMap getAttributes() {
        return null;
    }

    public NodeList getChildNodes() {
        return this;
    }

    @Override // org.w3c.dom.Node
    public Node getParentNode() {
        return DomImpl._node_getParentNode(this);
    }

    @Override // org.w3c.dom.Node
    public Node removeChild(Node oldChild) {
        return DomImpl._node_removeChild(this, oldChild);
    }

    public Node getFirstChild() {
        return DomImpl._node_getFirstChild(this);
    }

    @Override // org.w3c.dom.Node
    public Node getLastChild() {
        return DomImpl._node_getLastChild(this);
    }

    @Override // org.w3c.dom.Node
    public String getLocalName() {
        return DomImpl._node_getLocalName(this);
    }

    @Override // org.w3c.dom.Node
    public String getNamespaceURI() {
        return DomImpl._node_getNamespaceURI(this);
    }

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
        return DomImpl._node_hasAttributes(this);
    }

    @Override // org.w3c.dom.Node
    public boolean hasChildNodes() {
        return DomImpl._node_hasChildNodes(this);
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

    public boolean nodeCanHavePrefixUri() {
        return false;
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
}
