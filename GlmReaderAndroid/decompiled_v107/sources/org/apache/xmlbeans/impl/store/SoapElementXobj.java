package org.apache.xmlbeans.impl.store;

import java.util.Iterator;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.impl.soap.Name;
import org.apache.xmlbeans.impl.soap.Node;
import org.apache.xmlbeans.impl.soap.SOAPElement;
import org.apache.xmlbeans.impl.soap.SOAPException;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class SoapElementXobj extends ElementXobj implements SOAPElement, Node {
    /* JADX INFO: Access modifiers changed from: package-private */
    public SoapElementXobj(Locale l, QName name) {
        super(l, name);
    }

    @Override // org.apache.xmlbeans.impl.store.ElementXobj, org.apache.xmlbeans.impl.store.Xobj
    Xobj newNode(Locale l) {
        return new SoapElementXobj(l, this._name);
    }

    @Override // org.apache.xmlbeans.impl.soap.Node
    public void detachNode() {
        DomImpl._soapNode_detachNode(this);
    }

    @Override // org.apache.xmlbeans.impl.soap.Node
    public void recycleNode() {
        DomImpl._soapNode_recycleNode(this);
    }

    @Override // org.apache.xmlbeans.impl.soap.Node
    public String getValue() {
        return DomImpl._soapNode_getValue(this);
    }

    @Override // org.apache.xmlbeans.impl.soap.Node
    public void setValue(String value) {
        DomImpl._soapNode_setValue(this, value);
    }

    @Override // org.apache.xmlbeans.impl.soap.Node
    public SOAPElement getParentElement() {
        return DomImpl._soapNode_getParentElement(this);
    }

    @Override // org.apache.xmlbeans.impl.soap.Node
    public void setParentElement(SOAPElement p) {
        DomImpl._soapNode_setParentElement(this, p);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPElement
    public void removeContents() {
        DomImpl._soapElement_removeContents(this);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPElement
    public String getEncodingStyle() {
        return DomImpl._soapElement_getEncodingStyle(this);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPElement
    public void setEncodingStyle(String encodingStyle) {
        DomImpl._soapElement_setEncodingStyle(this, encodingStyle);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPElement
    public boolean removeNamespaceDeclaration(String prefix) {
        return DomImpl._soapElement_removeNamespaceDeclaration(this, prefix);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPElement
    public Iterator<Name> getAllAttributes() {
        return DomImpl._soapElement_getAllAttributes(this);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPElement
    public Iterator<SOAPElement> getChildElements() {
        return DomImpl._soapElement_getChildElements(this);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPElement
    public Iterator<String> getNamespacePrefixes() {
        return DomImpl._soapElement_getNamespacePrefixes(this);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPElement
    public SOAPElement addAttribute(Name name, String value) throws SOAPException {
        return DomImpl._soapElement_addAttribute(this, name, value);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPElement
    public SOAPElement addChildElement(SOAPElement oldChild) throws SOAPException {
        return DomImpl._soapElement_addChildElement(this, oldChild);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPElement
    public SOAPElement addChildElement(Name name) throws SOAPException {
        return DomImpl._soapElement_addChildElement(this, name);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPElement
    public SOAPElement addChildElement(String localName) throws SOAPException {
        return DomImpl._soapElement_addChildElement(this, localName);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPElement
    public SOAPElement addChildElement(String localName, String prefix) throws SOAPException {
        return DomImpl._soapElement_addChildElement(this, localName, prefix);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPElement
    public SOAPElement addChildElement(String localName, String prefix, String uri) throws SOAPException {
        return DomImpl._soapElement_addChildElement(this, localName, prefix, uri);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPElement
    public SOAPElement addNamespaceDeclaration(String prefix, String uri) {
        return DomImpl._soapElement_addNamespaceDeclaration(this, prefix, uri);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPElement
    public SOAPElement addTextNode(String data) {
        return DomImpl._soapElement_addTextNode(this, data);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPElement
    public String getAttributeValue(Name name) {
        return DomImpl._soapElement_getAttributeValue(this, name);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPElement
    public Iterator<SOAPElement> getChildElements(Name name) {
        return DomImpl._soapElement_getChildElements(this, name);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPElement
    public Name getElementName() {
        return DomImpl._soapElement_getElementName(this);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPElement
    public String getNamespaceURI(String prefix) {
        return DomImpl._soapElement_getNamespaceURI(this, prefix);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPElement
    public Iterator<String> getVisibleNamespacePrefixes() {
        return DomImpl._soapElement_getVisibleNamespacePrefixes(this);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPElement
    public boolean removeAttribute(Name name) {
        return DomImpl._soapElement_removeAttribute(this, name);
    }
}
