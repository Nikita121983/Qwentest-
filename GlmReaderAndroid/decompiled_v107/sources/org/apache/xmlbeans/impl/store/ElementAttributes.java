package org.apache.xmlbeans.impl.store;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/* loaded from: classes11.dex */
final class ElementAttributes implements NamedNodeMap {
    private ElementXobj _elementXobj;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ElementAttributes(ElementXobj elementXobj) {
        this._elementXobj = elementXobj;
    }

    @Override // org.w3c.dom.NamedNodeMap
    public int getLength() {
        return DomImpl._attributes_getLength(this._elementXobj);
    }

    @Override // org.w3c.dom.NamedNodeMap
    public Node getNamedItem(String name) {
        return DomImpl._attributes_getNamedItem(this._elementXobj, name);
    }

    @Override // org.w3c.dom.NamedNodeMap
    public Node getNamedItemNS(String namespaceURI, String localName) {
        return DomImpl._attributes_getNamedItemNS(this._elementXobj, namespaceURI, localName);
    }

    @Override // org.w3c.dom.NamedNodeMap
    public Node item(int index) {
        return DomImpl._attributes_item(this._elementXobj, index);
    }

    @Override // org.w3c.dom.NamedNodeMap
    public Node removeNamedItem(String name) {
        return DomImpl._attributes_removeNamedItem(this._elementXobj, name);
    }

    @Override // org.w3c.dom.NamedNodeMap
    public Node removeNamedItemNS(String namespaceURI, String localName) {
        return DomImpl._attributes_removeNamedItemNS(this._elementXobj, namespaceURI, localName);
    }

    @Override // org.w3c.dom.NamedNodeMap
    public Node setNamedItem(Node arg) {
        return DomImpl._attributes_setNamedItem(this._elementXobj, arg);
    }

    @Override // org.w3c.dom.NamedNodeMap
    public Node setNamedItemNS(Node arg) {
        return DomImpl._attributes_setNamedItemNS(this._elementXobj, arg);
    }
}
