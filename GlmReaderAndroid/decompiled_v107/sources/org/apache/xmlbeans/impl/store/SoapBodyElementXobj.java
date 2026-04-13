package org.apache.xmlbeans.impl.store;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.impl.soap.SOAPBodyElement;

/* loaded from: classes11.dex */
class SoapBodyElementXobj extends SoapElementXobj implements SOAPBodyElement {
    /* JADX INFO: Access modifiers changed from: package-private */
    public SoapBodyElementXobj(Locale l, QName name) {
        super(l, name);
    }

    @Override // org.apache.xmlbeans.impl.store.SoapElementXobj, org.apache.xmlbeans.impl.store.ElementXobj, org.apache.xmlbeans.impl.store.Xobj
    Xobj newNode(Locale l) {
        return new SoapBodyElementXobj(l, this._name);
    }
}
