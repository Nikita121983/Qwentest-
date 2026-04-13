package org.apache.xmlbeans.impl.store;

import javax.xml.namespace.QName;

/* loaded from: classes11.dex */
class AttrIdXobj extends AttrXobj {
    /* JADX INFO: Access modifiers changed from: package-private */
    public AttrIdXobj(Locale l, QName name) {
        super(l, name);
    }

    @Override // org.apache.xmlbeans.impl.store.AttrXobj, org.w3c.dom.Attr
    public boolean isId() {
        return true;
    }
}
