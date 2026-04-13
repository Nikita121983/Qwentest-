package org.apache.xmlbeans.impl.store;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.impl.soap.SOAPHeaderElement;

/* loaded from: classes11.dex */
class SoapHeaderElementXobj extends SoapElementXobj implements SOAPHeaderElement {
    /* JADX INFO: Access modifiers changed from: package-private */
    public SoapHeaderElementXobj(Locale l, QName name) {
        super(l, name);
    }

    @Override // org.apache.xmlbeans.impl.store.SoapElementXobj, org.apache.xmlbeans.impl.store.ElementXobj, org.apache.xmlbeans.impl.store.Xobj
    Xobj newNode(Locale l) {
        return new SoapHeaderElementXobj(l, this._name);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPHeaderElement
    public void setMustUnderstand(boolean mustUnderstand) {
        DomImpl.soapHeaderElement_setMustUnderstand(this, mustUnderstand);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPHeaderElement
    public boolean getMustUnderstand() {
        return DomImpl.soapHeaderElement_getMustUnderstand(this);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPHeaderElement
    public void setActor(String actor) {
        DomImpl.soapHeaderElement_setActor(this, actor);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPHeaderElement
    public String getActor() {
        return DomImpl.soapHeaderElement_getActor(this);
    }
}
