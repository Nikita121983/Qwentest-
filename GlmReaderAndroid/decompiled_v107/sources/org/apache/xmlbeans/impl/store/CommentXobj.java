package org.apache.xmlbeans.impl.store;

import org.w3c.dom.Comment;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* loaded from: classes11.dex */
class CommentXobj extends NodeXobj implements Comment {
    /* JADX INFO: Access modifiers changed from: package-private */
    public CommentXobj(Locale l) {
        super(l, 4, 8);
    }

    @Override // org.apache.xmlbeans.impl.store.Xobj
    Xobj newNode(Locale l) {
        return new CommentXobj(l);
    }

    @Override // org.apache.xmlbeans.impl.store.NodeXobj, org.w3c.dom.Node
    public NodeList getChildNodes() {
        return DomImpl._emptyNodeList;
    }

    @Override // org.w3c.dom.CharacterData
    public void appendData(String arg) {
        DomImpl._characterData_appendData(this, arg);
    }

    @Override // org.w3c.dom.CharacterData
    public void deleteData(int offset, int count) {
        DomImpl._characterData_deleteData(this, offset, count);
    }

    @Override // org.w3c.dom.CharacterData
    public String getData() {
        return DomImpl._characterData_getData(this);
    }

    @Override // org.apache.xmlbeans.impl.store.NodeXobj, org.w3c.dom.NodeList, org.w3c.dom.CharacterData
    public int getLength() {
        return DomImpl._characterData_getLength(this);
    }

    @Override // org.apache.xmlbeans.impl.store.NodeXobj, org.w3c.dom.Node
    public Node getFirstChild() {
        return null;
    }

    @Override // org.w3c.dom.CharacterData
    public void insertData(int offset, String arg) {
        DomImpl._characterData_insertData(this, offset, arg);
    }

    @Override // org.w3c.dom.CharacterData
    public void replaceData(int offset, int count, String arg) {
        DomImpl._characterData_replaceData(this, offset, count, arg);
    }

    @Override // org.w3c.dom.CharacterData
    public void setData(String data) {
        DomImpl._characterData_setData(this, data);
    }

    @Override // org.w3c.dom.CharacterData
    public String substringData(int offset, int count) {
        return DomImpl._characterData_substringData(this, offset, count);
    }
}
