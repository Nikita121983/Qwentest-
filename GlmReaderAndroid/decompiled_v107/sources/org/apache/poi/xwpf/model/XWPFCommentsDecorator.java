package org.apache.poi.xwpf.model;

import java.math.BigInteger;
import org.apache.poi.xwpf.usermodel.XWPFComment;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMarkupRange;

/* loaded from: classes10.dex */
public class XWPFCommentsDecorator extends XWPFParagraphDecorator {
    private StringBuilder commentText;

    public XWPFCommentsDecorator(XWPFParagraphDecorator nextDecorator) {
        this(nextDecorator.paragraph, nextDecorator);
    }

    public XWPFCommentsDecorator(XWPFParagraph paragraph, XWPFParagraphDecorator nextDecorator) {
        super(paragraph, nextDecorator);
        XWPFComment comment;
        this.commentText = new StringBuilder(64);
        for (CTMarkupRange anchor : paragraph.getCTP().getCommentRangeStartArray()) {
            BigInteger id = anchor.getId();
            if (id != null && (comment = paragraph.getDocument().getCommentByID(id.toString())) != null) {
                this.commentText.append("\tComment by ").append(comment.getAuthor()).append(": ").append(comment.getText());
            }
        }
    }

    public String getCommentText() {
        return this.commentText.toString();
    }

    @Override // org.apache.poi.xwpf.model.XWPFParagraphDecorator
    public String getText() {
        return super.getText() + ((Object) this.commentText);
    }
}
