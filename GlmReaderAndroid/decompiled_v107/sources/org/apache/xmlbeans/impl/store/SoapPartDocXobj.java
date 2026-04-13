package org.apache.xmlbeans.impl.store;

import org.apache.xmlbeans.impl.store.DomImpl;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class SoapPartDocXobj extends DocumentXobj {
    SoapPartDom _soapPartDom;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SoapPartDocXobj(Locale l) {
        super(l);
        this._soapPartDom = new SoapPartDom(this);
    }

    @Override // org.apache.xmlbeans.impl.store.NodeXobj, org.apache.xmlbeans.impl.store.Xobj
    DomImpl.Dom getDom() {
        return this._soapPartDom;
    }

    @Override // org.apache.xmlbeans.impl.store.DocumentXobj, org.apache.xmlbeans.impl.store.Xobj
    Xobj newNode(Locale l) {
        return new SoapPartDocXobj(l);
    }
}
