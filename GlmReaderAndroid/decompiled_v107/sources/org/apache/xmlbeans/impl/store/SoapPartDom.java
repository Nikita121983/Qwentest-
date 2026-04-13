package org.apache.xmlbeans.impl.store;

import java.io.PrintStream;
import java.util.Iterator;
import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import org.apache.xmlbeans.impl.soap.MimeHeader;
import org.apache.xmlbeans.impl.soap.SOAPEnvelope;
import org.apache.xmlbeans.impl.soap.SOAPPart;
import org.apache.xmlbeans.impl.store.DomImpl;
import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.EntityReference;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;
import org.w3c.dom.UserDataHandler;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class SoapPartDom extends SOAPPart implements DomImpl.Dom, Document, NodeList {
    SoapPartDocXobj _docXobj;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SoapPartDom(SoapPartDocXobj docXobj) {
        this._docXobj = docXobj;
    }

    @Override // org.apache.xmlbeans.impl.store.DomImpl.Dom
    public int nodeType() {
        return 9;
    }

    @Override // org.apache.xmlbeans.impl.store.DomImpl.Dom
    public Locale locale() {
        return this._docXobj._locale;
    }

    @Override // org.apache.xmlbeans.impl.store.DomImpl.Dom
    public Cur tempCur() {
        return this._docXobj.tempCur();
    }

    @Override // org.apache.xmlbeans.impl.store.DomImpl.Dom
    public QName getQName() {
        return this._docXobj._name;
    }

    @Override // org.apache.xmlbeans.impl.store.DomImpl.Dom
    public void dump() {
        dump(System.out);
    }

    @Override // org.apache.xmlbeans.impl.store.DomImpl.Dom
    public void dump(PrintStream o) {
        this._docXobj.dump(o);
    }

    @Override // org.apache.xmlbeans.impl.store.DomImpl.Dom
    public void dump(PrintStream o, Object ref) {
        this._docXobj.dump(o, ref);
    }

    public String name() {
        return "#document";
    }

    @Override // org.w3c.dom.Node
    public Node appendChild(Node newChild) {
        return DomImpl._node_appendChild(this, newChild);
    }

    @Override // org.w3c.dom.Node
    public Node cloneNode(boolean deep) {
        return DomImpl._node_cloneNode(this, deep);
    }

    @Override // org.w3c.dom.Node
    public NamedNodeMap getAttributes() {
        return null;
    }

    @Override // org.w3c.dom.Node
    public NodeList getChildNodes() {
        return this;
    }

    @Override // org.w3c.dom.Node
    public Node getParentNode() {
        return DomImpl._node_getParentNode(this);
    }

    @Override // org.w3c.dom.Node
    public Node removeChild(Node oldChild) {
        return DomImpl._node_removeChild(this, oldChild);
    }

    @Override // org.w3c.dom.Node
    public Node getFirstChild() {
        return DomImpl._node_getFirstChild(this);
    }

    @Override // org.w3c.dom.Node
    public Node getLastChild() {
        return DomImpl._node_getLastChild(this);
    }

    @Override // org.w3c.dom.Node
    public String getLocalName() {
        return DomImpl._node_getLocalName(this);
    }

    @Override // org.w3c.dom.Node
    public String getNamespaceURI() {
        return DomImpl._node_getNamespaceURI(this);
    }

    @Override // org.w3c.dom.Node
    public Node getNextSibling() {
        return DomImpl._node_getNextSibling(this);
    }

    @Override // org.w3c.dom.Node
    public String getNodeName() {
        return DomImpl._node_getNodeName(this);
    }

    @Override // org.w3c.dom.Node
    public short getNodeType() {
        return DomImpl._node_getNodeType(this);
    }

    @Override // org.w3c.dom.Node
    public String getNodeValue() {
        return DomImpl._node_getNodeValue(this);
    }

    @Override // org.w3c.dom.Node
    public Document getOwnerDocument() {
        return DomImpl._node_getOwnerDocument(this);
    }

    @Override // org.w3c.dom.Node
    public String getPrefix() {
        return DomImpl._node_getPrefix(this);
    }

    @Override // org.w3c.dom.Node
    public Node getPreviousSibling() {
        return DomImpl._node_getPreviousSibling(this);
    }

    @Override // org.w3c.dom.Node
    public boolean hasAttributes() {
        return DomImpl._node_hasAttributes(this);
    }

    @Override // org.w3c.dom.Node
    public boolean hasChildNodes() {
        return DomImpl._node_hasChildNodes(this);
    }

    @Override // org.w3c.dom.Node
    public Node insertBefore(Node newChild, Node refChild) {
        return DomImpl._node_insertBefore(this, newChild, refChild);
    }

    @Override // org.w3c.dom.Node
    public boolean isSupported(String feature, String version) {
        return DomImpl._node_isSupported(this, feature, version);
    }

    @Override // org.w3c.dom.Node
    public void normalize() {
        DomImpl._node_normalize(this);
    }

    @Override // org.w3c.dom.Node
    public Node replaceChild(Node newChild, Node oldChild) {
        return DomImpl._node_replaceChild(this, newChild, oldChild);
    }

    @Override // org.w3c.dom.Node
    public void setNodeValue(String nodeValue) {
        DomImpl._node_setNodeValue(this, nodeValue);
    }

    @Override // org.w3c.dom.Node
    public void setPrefix(String prefix) {
        DomImpl._node_setPrefix(this, prefix);
    }

    @Override // org.w3c.dom.Node
    public Object getUserData(String key) {
        return DomImpl._node_getUserData(this, key);
    }

    @Override // org.w3c.dom.Node
    public Object setUserData(String key, Object data, UserDataHandler handler) {
        return DomImpl._node_setUserData(this, key, data, handler);
    }

    @Override // org.w3c.dom.Node
    public Object getFeature(String feature, String version) {
        return DomImpl._node_getFeature(this, feature, version);
    }

    @Override // org.w3c.dom.Node
    public boolean isEqualNode(Node arg) {
        return DomImpl._node_isEqualNode(this, arg);
    }

    @Override // org.w3c.dom.Node
    public boolean isSameNode(Node arg) {
        return DomImpl._node_isSameNode(this, arg);
    }

    @Override // org.w3c.dom.Node
    public String lookupNamespaceURI(String prefix) {
        return DomImpl._node_lookupNamespaceURI(this, prefix);
    }

    @Override // org.w3c.dom.Node
    public String lookupPrefix(String namespaceURI) {
        return DomImpl._node_lookupPrefix(this, namespaceURI);
    }

    @Override // org.w3c.dom.Node
    public boolean isDefaultNamespace(String namespaceURI) {
        return DomImpl._node_isDefaultNamespace(this, namespaceURI);
    }

    @Override // org.w3c.dom.Node
    public void setTextContent(String textContent) {
        DomImpl._node_setTextContent(this, textContent);
    }

    @Override // org.w3c.dom.Node
    public String getTextContent() {
        return DomImpl._node_getTextContent(this);
    }

    @Override // org.w3c.dom.Node
    public short compareDocumentPosition(Node other) {
        return DomImpl._node_compareDocumentPosition(this, other);
    }

    @Override // org.w3c.dom.Node
    public String getBaseURI() {
        return DomImpl._node_getBaseURI(this);
    }

    @Override // org.w3c.dom.Document
    public Node adoptNode(Node source) {
        throw new RuntimeException("DOM Level 3 Not implemented");
    }

    @Override // org.w3c.dom.Document
    public String getDocumentURI() {
        throw new RuntimeException("DOM Level 3 Not implemented");
    }

    @Override // org.w3c.dom.Document
    public DOMConfiguration getDomConfig() {
        throw new RuntimeException("DOM Level 3 Not implemented");
    }

    @Override // org.w3c.dom.Document
    public String getInputEncoding() {
        throw new RuntimeException("DOM Level 3 Not implemented");
    }

    @Override // org.w3c.dom.Document
    public boolean getStrictErrorChecking() {
        throw new RuntimeException("DOM Level 3 Not implemented");
    }

    @Override // org.w3c.dom.Document
    public String getXmlEncoding() {
        throw new RuntimeException("DOM Level 3 Not implemented");
    }

    @Override // org.w3c.dom.Document
    public boolean getXmlStandalone() {
        throw new RuntimeException("DOM Level 3 Not implemented");
    }

    @Override // org.w3c.dom.Document
    public String getXmlVersion() {
        throw new RuntimeException("DOM Level 3 Not implemented");
    }

    @Override // org.w3c.dom.Document
    public void normalizeDocument() {
        throw new RuntimeException("DOM Level 3 Not implemented");
    }

    @Override // org.w3c.dom.Document
    public Node renameNode(Node n, String namespaceURI, String qualifiedName) {
        throw new RuntimeException("DOM Level 3 Not implemented");
    }

    @Override // org.w3c.dom.Document
    public void setDocumentURI(String documentURI) {
        throw new RuntimeException("DOM Level 3 Not implemented");
    }

    @Override // org.w3c.dom.Document
    public void setStrictErrorChecking(boolean strictErrorChecking) {
        throw new RuntimeException("DOM Level 3 Not implemented");
    }

    @Override // org.w3c.dom.Document
    public void setXmlStandalone(boolean xmlStandalone) {
        throw new RuntimeException("DOM Level 3 Not implemented");
    }

    @Override // org.w3c.dom.Document
    public void setXmlVersion(String xmlVersion) {
        throw new RuntimeException("DOM Level 3 Not implemented");
    }

    @Override // org.w3c.dom.Document
    public Attr createAttribute(String name) {
        return DomImpl._document_createAttribute(this, name);
    }

    @Override // org.w3c.dom.Document
    public Attr createAttributeNS(String namespaceURI, String qualifiedName) {
        return DomImpl._document_createAttributeNS(this, namespaceURI, qualifiedName);
    }

    @Override // org.w3c.dom.Document
    public CDATASection createCDATASection(String data) {
        return DomImpl._document_createCDATASection(this, data);
    }

    @Override // org.w3c.dom.Document
    public Comment createComment(String data) {
        return DomImpl._document_createComment(this, data);
    }

    @Override // org.w3c.dom.Document
    public DocumentFragment createDocumentFragment() {
        return DomImpl._document_createDocumentFragment(this);
    }

    @Override // org.w3c.dom.Document
    public Element createElement(String tagName) {
        return DomImpl._document_createElement(this, tagName);
    }

    @Override // org.w3c.dom.Document
    public Element createElementNS(String namespaceURI, String qualifiedName) {
        return DomImpl._document_createElementNS(this, namespaceURI, qualifiedName);
    }

    @Override // org.w3c.dom.Document
    public EntityReference createEntityReference(String name) {
        return DomImpl._document_createEntityReference(this, name);
    }

    @Override // org.w3c.dom.Document
    public ProcessingInstruction createProcessingInstruction(String target, String data) {
        return DomImpl._document_createProcessingInstruction(this, target, data);
    }

    @Override // org.w3c.dom.Document
    public Text createTextNode(String data) {
        return DomImpl._document_createTextNode(this, data);
    }

    @Override // org.w3c.dom.Document
    public DocumentType getDoctype() {
        return DomImpl._document_getDoctype(this);
    }

    @Override // org.w3c.dom.Document
    public Element getDocumentElement() {
        return DomImpl._document_getDocumentElement(this);
    }

    @Override // org.w3c.dom.Document
    public Element getElementById(String elementId) {
        return DomImpl._document_getElementById(this, elementId);
    }

    @Override // org.w3c.dom.Document
    public NodeList getElementsByTagName(String tagname) {
        return DomImpl._document_getElementsByTagName(this, tagname);
    }

    @Override // org.w3c.dom.Document
    public NodeList getElementsByTagNameNS(String namespaceURI, String localName) {
        return DomImpl._document_getElementsByTagNameNS(this, namespaceURI, localName);
    }

    @Override // org.w3c.dom.Document
    public DOMImplementation getImplementation() {
        return DomImpl._document_getImplementation(this);
    }

    @Override // org.w3c.dom.Document
    public Node importNode(Node importedNode, boolean deep) {
        return DomImpl._document_importNode(this, importedNode, deep);
    }

    @Override // org.w3c.dom.NodeList
    public int getLength() {
        return DomImpl._childNodes_getLength(this);
    }

    @Override // org.w3c.dom.NodeList
    public Node item(int i) {
        return DomImpl._childNodes_item(this, i);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPPart
    public void removeAllMimeHeaders() {
        DomImpl._soapPart_removeAllMimeHeaders(this);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPPart
    public void removeMimeHeader(String name) {
        DomImpl._soapPart_removeMimeHeader(this, name);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPPart
    public Iterator<MimeHeader> getAllMimeHeaders() {
        return DomImpl._soapPart_getAllMimeHeaders(this);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPPart
    public SOAPEnvelope getEnvelope() {
        return DomImpl._soapPart_getEnvelope(this);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPPart
    public Source getContent() {
        return DomImpl._soapPart_getContent(this);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPPart
    public void setContent(Source source) {
        DomImpl._soapPart_setContent(this, source);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPPart
    public String[] getMimeHeader(String name) {
        return DomImpl._soapPart_getMimeHeader(this, name);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPPart
    public void addMimeHeader(String name, String value) {
        DomImpl._soapPart_addMimeHeader(this, name, value);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPPart
    public void setMimeHeader(String name, String value) {
        DomImpl._soapPart_setMimeHeader(this, name, value);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPPart
    public Iterator<MimeHeader> getMatchingMimeHeaders(String[] names) {
        return DomImpl._soapPart_getMatchingMimeHeaders(this, names);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPPart
    public Iterator<MimeHeader> getNonMatchingMimeHeaders(String[] names) {
        return DomImpl._soapPart_getNonMatchingMimeHeaders(this, names);
    }

    @Override // org.apache.xmlbeans.impl.store.DomImpl.Dom
    public boolean nodeCanHavePrefixUri() {
        return true;
    }
}
