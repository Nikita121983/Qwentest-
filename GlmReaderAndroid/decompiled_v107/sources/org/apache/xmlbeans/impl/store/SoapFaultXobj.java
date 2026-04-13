package org.apache.xmlbeans.impl.store;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.impl.soap.Detail;
import org.apache.xmlbeans.impl.soap.Name;
import org.apache.xmlbeans.impl.soap.SOAPException;
import org.apache.xmlbeans.impl.soap.SOAPFault;

/* loaded from: classes11.dex */
class SoapFaultXobj extends SoapBodyElementXobj implements SOAPFault {
    /* JADX INFO: Access modifiers changed from: package-private */
    public SoapFaultXobj(Locale l, QName name) {
        super(l, name);
    }

    @Override // org.apache.xmlbeans.impl.store.SoapBodyElementXobj, org.apache.xmlbeans.impl.store.SoapElementXobj, org.apache.xmlbeans.impl.store.ElementXobj, org.apache.xmlbeans.impl.store.Xobj
    Xobj newNode(Locale l) {
        return new SoapFaultXobj(l, this._name);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPFault
    public void setFaultString(String faultString) {
        DomImpl.soapFault_setFaultString(this, faultString);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPFault
    public void setFaultString(String faultString, java.util.Locale locale) {
        DomImpl.soapFault_setFaultString(this, faultString, locale);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPFault
    public void setFaultCode(Name faultCodeName) throws SOAPException {
        DomImpl.soapFault_setFaultCode(this, faultCodeName);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPFault
    public void setFaultActor(String faultActorString) {
        DomImpl.soapFault_setFaultActor(this, faultActorString);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPFault
    public String getFaultActor() {
        return DomImpl.soapFault_getFaultActor(this);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPFault
    public String getFaultCode() {
        return DomImpl.soapFault_getFaultCode(this);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPFault
    public void setFaultCode(String faultCode) throws SOAPException {
        DomImpl.soapFault_setFaultCode(this, faultCode);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPFault
    public java.util.Locale getFaultStringLocale() {
        return DomImpl.soapFault_getFaultStringLocale(this);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPFault
    public Name getFaultCodeAsName() {
        return DomImpl.soapFault_getFaultCodeAsName(this);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPFault
    public String getFaultString() {
        return DomImpl.soapFault_getFaultString(this);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPFault
    public Detail addDetail() throws SOAPException {
        return DomImpl.soapFault_addDetail(this);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPFault
    public Detail getDetail() {
        return DomImpl.soapFault_getDetail(this);
    }
}
