package org.apache.xmlbeans.impl.store;

import org.w3c.dom.Text;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class TextNode extends CharNode implements Text {
    /* JADX INFO: Access modifiers changed from: package-private */
    public TextNode(Locale l) {
        super(l);
    }

    @Override // org.apache.xmlbeans.impl.store.DomImpl.Dom
    public int nodeType() {
        return 3;
    }

    public String name() {
        return "#text";
    }

    @Override // org.w3c.dom.Text
    public Text splitText(int offset) {
        return DomImpl._text_splitText(this, offset);
    }

    @Override // org.w3c.dom.Text
    public String getWholeText() {
        return DomImpl._text_getWholeText(this);
    }

    @Override // org.w3c.dom.Text
    public boolean isElementContentWhitespace() {
        return DomImpl._text_isElementContentWhitespace(this);
    }

    @Override // org.w3c.dom.Text
    public Text replaceWholeText(String content) {
        return DomImpl._text_replaceWholeText(this, content);
    }
}
