package org.apache.xmlbeans.impl.xpath;

import javax.xml.namespace.QName;

/* loaded from: classes11.dex */
class XPathStep {
    final boolean _attr;
    XPathStep _backtrack;
    final boolean _deep;
    int _flags;
    boolean _hasBacktrack;
    final QName _name;
    XPathStep _next;
    XPathStep _prev;

    /* JADX INFO: Access modifiers changed from: package-private */
    public XPathStep(boolean deep, boolean attr, QName name) {
        this._name = name;
        this._deep = deep;
        this._attr = attr;
        int flags = (this._deep || !this._attr) ? 0 | 2 : 0;
        this._flags = this._attr ? flags | 4 : flags;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isWild() {
        return this._name.getLocalPart().isEmpty();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean match(QName name) {
        String local = this._name.getLocalPart();
        String nameLocal = name.getLocalPart();
        int localLength = local.length();
        if (localLength == 0) {
            String uri = this._name.getNamespaceURI();
            return uri.isEmpty() || uri.equals(name.getNamespaceURI());
        }
        if (localLength != nameLocal.length()) {
            return false;
        }
        String uri2 = this._name.getNamespaceURI();
        String nameUri = name.getNamespaceURI();
        if (uri2.length() != nameUri.length()) {
            return false;
        }
        return local.equals(nameLocal) && uri2.equals(nameUri);
    }
}
