package org.apache.xmlbeans.impl.store;

import javax.xml.namespace.QName;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.TypeInfo;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class AttrXobj extends NamedNodeXobj implements Attr {
    /* JADX INFO: Access modifiers changed from: package-private */
    public AttrXobj(Locale l, QName name) {
        super(l, 3, 2);
        this._name = name;
    }

    @Override // org.apache.xmlbeans.impl.store.Xobj
    Xobj newNode(Locale l) {
        return new AttrXobj(l, this._name);
    }

    @Override // org.apache.xmlbeans.impl.store.NodeXobj, org.w3c.dom.Node
    public Node getNextSibling() {
        return null;
    }

    @Override // org.w3c.dom.Attr
    public String getName() {
        return DomImpl._node_getNodeName(this);
    }

    @Override // org.w3c.dom.Attr
    public Element getOwnerElement() {
        return DomImpl._attr_getOwnerElement(this);
    }

    @Override // org.w3c.dom.Attr
    public boolean getSpecified() {
        return DomImpl._attr_getSpecified(this);
    }

    @Override // org.w3c.dom.Attr
    public String getValue() {
        return DomImpl._node_getNodeValue(this);
    }

    @Override // org.w3c.dom.Attr
    public void setValue(String value) {
        DomImpl._node_setNodeValue(this, value);
    }

    @Override // org.w3c.dom.Attr
    public TypeInfo getSchemaTypeInfo() {
        throw new RuntimeException("DOM Level 3 Not implemented");
    }

    public boolean isId() {
        return false;
    }
}
