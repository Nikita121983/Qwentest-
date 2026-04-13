package org.apache.xmlbeans.impl.store;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.impl.soap.Name;
import org.apache.xmlbeans.impl.soap.SOAPBody;
import org.apache.xmlbeans.impl.soap.SOAPEnvelope;
import org.apache.xmlbeans.impl.soap.SOAPException;
import org.apache.xmlbeans.impl.soap.SOAPHeader;

/* loaded from: classes11.dex */
class SoapEnvelopeXobj extends SoapElementXobj implements SOAPEnvelope {
    /* JADX INFO: Access modifiers changed from: package-private */
    public SoapEnvelopeXobj(Locale l, QName name) {
        super(l, name);
    }

    @Override // org.apache.xmlbeans.impl.store.SoapElementXobj, org.apache.xmlbeans.impl.store.ElementXobj, org.apache.xmlbeans.impl.store.Xobj
    Xobj newNode(Locale l) {
        return new SoapEnvelopeXobj(l, this._name);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPEnvelope
    public SOAPBody addBody() throws SOAPException {
        return DomImpl._soapEnvelope_addBody(this);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPEnvelope
    public SOAPBody getBody() throws SOAPException {
        return DomImpl._soapEnvelope_getBody(this);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPEnvelope
    public SOAPHeader getHeader() throws SOAPException {
        return DomImpl._soapEnvelope_getHeader(this);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPEnvelope
    public SOAPHeader addHeader() throws SOAPException {
        return DomImpl._soapEnvelope_addHeader(this);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPEnvelope
    public Name createName(String localName) {
        return DomImpl._soapEnvelope_createName(this, localName);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPEnvelope
    public Name createName(String localName, String prefix, String namespaceURI) {
        return DomImpl._soapEnvelope_createName(this, localName, prefix, namespaceURI);
    }
}
