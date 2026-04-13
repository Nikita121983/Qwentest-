package org.apache.xmlbeans.impl.store;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.impl.soap.Name;
import org.apache.xmlbeans.impl.soap.SOAPBody;
import org.apache.xmlbeans.impl.soap.SOAPBodyElement;
import org.apache.xmlbeans.impl.soap.SOAPException;
import org.apache.xmlbeans.impl.soap.SOAPFault;
import org.w3c.dom.Document;

/* loaded from: classes11.dex */
class SoapBodyXobj extends SoapElementXobj implements SOAPBody {
    /* JADX INFO: Access modifiers changed from: package-private */
    public SoapBodyXobj(Locale l, QName name) {
        super(l, name);
    }

    @Override // org.apache.xmlbeans.impl.store.SoapElementXobj, org.apache.xmlbeans.impl.store.ElementXobj, org.apache.xmlbeans.impl.store.Xobj
    Xobj newNode(Locale l) {
        return new SoapBodyXobj(l, this._name);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPBody
    public boolean hasFault() {
        return DomImpl.soapBody_hasFault(this);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPBody
    public SOAPFault addFault() throws SOAPException {
        return DomImpl.soapBody_addFault(this);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPBody
    public SOAPFault getFault() {
        return DomImpl.soapBody_getFault(this);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPBody
    public SOAPBodyElement addBodyElement(Name name) {
        return DomImpl.soapBody_addBodyElement(this, name);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPBody
    public SOAPBodyElement addDocument(Document document) {
        return DomImpl.soapBody_addDocument(this, document);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPBody
    public SOAPFault addFault(Name name, String s) throws SOAPException {
        return DomImpl.soapBody_addFault(this, name, s);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPBody
    public SOAPFault addFault(Name faultCode, String faultString, java.util.Locale locale) throws SOAPException {
        return DomImpl.soapBody_addFault(this, faultCode, faultString, locale);
    }
}
