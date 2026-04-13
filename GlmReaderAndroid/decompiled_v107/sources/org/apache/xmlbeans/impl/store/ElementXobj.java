package org.apache.xmlbeans.impl.store;

import javax.xml.namespace.QName;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.w3c.dom.TypeInfo;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class ElementXobj extends NamedNodeXobj implements Element {
    private ElementAttributes _attributes;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ElementXobj(Locale l, QName name) {
        super(l, 2, 1);
        this._name = name;
    }

    @Override // org.apache.xmlbeans.impl.store.Xobj
    Xobj newNode(Locale l) {
        return new ElementXobj(l, this._name);
    }

    @Override // org.apache.xmlbeans.impl.store.NodeXobj, org.w3c.dom.Node
    public NamedNodeMap getAttributes() {
        if (this._attributes == null) {
            this._attributes = new ElementAttributes(this);
        }
        return this._attributes;
    }

    @Override // org.w3c.dom.Element
    public String getAttribute(String name) {
        return DomImpl._element_getAttribute(this, name);
    }

    @Override // org.w3c.dom.Element
    public Attr getAttributeNode(String name) {
        return DomImpl._element_getAttributeNode(this, name);
    }

    @Override // org.w3c.dom.Element
    public Attr getAttributeNodeNS(String namespaceURI, String localName) {
        return DomImpl._element_getAttributeNodeNS(this, namespaceURI, localName);
    }

    @Override // org.w3c.dom.Element
    public String getAttributeNS(String namespaceURI, String localName) {
        return DomImpl._element_getAttributeNS(this, namespaceURI, localName);
    }

    @Override // org.w3c.dom.Element
    public NodeList getElementsByTagName(String name) {
        return DomImpl._element_getElementsByTagName(this, name);
    }

    @Override // org.w3c.dom.Element
    public NodeList getElementsByTagNameNS(String namespaceURI, String localName) {
        return DomImpl._element_getElementsByTagNameNS(this, namespaceURI, localName);
    }

    @Override // org.w3c.dom.Element
    public String getTagName() {
        return DomImpl._element_getTagName(this);
    }

    @Override // org.w3c.dom.Element
    public boolean hasAttribute(String name) {
        return DomImpl._element_hasAttribute(this, name);
    }

    @Override // org.w3c.dom.Element
    public boolean hasAttributeNS(String namespaceURI, String localName) {
        return DomImpl._element_hasAttributeNS(this, namespaceURI, localName);
    }

    @Override // org.w3c.dom.Element
    public void removeAttribute(String name) {
        DomImpl._element_removeAttribute(this, name);
    }

    @Override // org.w3c.dom.Element
    public Attr removeAttributeNode(Attr oldAttr) {
        return DomImpl._element_removeAttributeNode(this, oldAttr);
    }

    @Override // org.w3c.dom.Element
    public void removeAttributeNS(String namespaceURI, String localName) {
        DomImpl._element_removeAttributeNS(this, namespaceURI, localName);
    }

    @Override // org.w3c.dom.Element
    public void setAttribute(String name, String value) {
        DomImpl._element_setAttribute(this, name, value);
    }

    @Override // org.w3c.dom.Element
    public Attr setAttributeNode(Attr newAttr) {
        return DomImpl._element_setAttributeNode(this, newAttr);
    }

    @Override // org.w3c.dom.Element
    public Attr setAttributeNodeNS(Attr newAttr) {
        return DomImpl._element_setAttributeNodeNS(this, newAttr);
    }

    @Override // org.w3c.dom.Element
    public void setAttributeNS(String namespaceURI, String qualifiedName, String value) {
        DomImpl._element_setAttributeNS(this, namespaceURI, qualifiedName, value);
    }

    @Override // org.w3c.dom.Element
    public TypeInfo getSchemaTypeInfo() {
        throw new RuntimeException("DOM Level 3 Not implemented");
    }

    @Override // org.w3c.dom.Element
    public void setIdAttribute(String name, boolean isId) {
        throw new RuntimeException("DOM Level 3 Not implemented");
    }

    @Override // org.w3c.dom.Element
    public void setIdAttributeNS(String namespaceURI, String localName, boolean isId) {
        throw new RuntimeException("DOM Level 3 Not implemented");
    }

    @Override // org.w3c.dom.Element
    public void setIdAttributeNode(Attr idAttr, boolean isId) {
        throw new RuntimeException("DOM Level 3 Not implemented");
    }
}
