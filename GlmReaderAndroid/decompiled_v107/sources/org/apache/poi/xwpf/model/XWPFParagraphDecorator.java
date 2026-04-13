package org.apache.poi.xwpf.model;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;

/* loaded from: classes10.dex */
public abstract class XWPFParagraphDecorator {
    protected XWPFParagraphDecorator nextDecorator;
    protected XWPFParagraph paragraph;

    public XWPFParagraphDecorator(XWPFParagraph paragraph) {
        this(paragraph, null);
    }

    public XWPFParagraphDecorator(XWPFParagraph paragraph, XWPFParagraphDecorator nextDecorator) {
        this.paragraph = paragraph;
        this.nextDecorator = nextDecorator;
    }

    public String getText() {
        if (this.nextDecorator != null) {
            return this.nextDecorator.getText();
        }
        return this.paragraph.getText();
    }
}
