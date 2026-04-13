package org.apache.poi.xslf.usermodel;

import java.awt.geom.Point2D;
import java.util.Calendar;
import java.util.Date;
import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.sl.usermodel.Comment;
import org.apache.poi.util.LocaleUtil;
import org.apache.poi.util.Units;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;
import org.openxmlformats.schemas.presentationml.x2006.main.CTComment;
import org.openxmlformats.schemas.presentationml.x2006.main.CTCommentAuthor;
import org.openxmlformats.schemas.presentationml.x2006.main.CTCommentAuthorList;

/* loaded from: classes10.dex */
public class XSLFComment implements Comment {
    final XSLFCommentAuthors authors;
    final CTComment comment;

    /* JADX INFO: Access modifiers changed from: package-private */
    public XSLFComment(CTComment comment, XSLFCommentAuthors authors) {
        this.comment = comment;
        this.authors = authors;
    }

    @Override // org.apache.poi.sl.usermodel.Comment
    public String getAuthor() {
        return this.authors.getAuthorById(this.comment.getAuthorId()).getName();
    }

    @Override // org.apache.poi.sl.usermodel.Comment
    public void setAuthor(String author) {
        if (author == null) {
            throw new IllegalArgumentException("author must not be null");
        }
        CTCommentAuthorList list = this.authors.getCTCommentAuthorsList();
        long maxId = -1;
        for (CTCommentAuthor aut : list.getCmAuthorArray()) {
            maxId = Math.max(aut.getId(), maxId);
            if (author.equals(aut.getName())) {
                this.comment.setAuthorId(aut.getId());
                return;
            }
        }
        CTCommentAuthor newAuthor = list.addNewCmAuthor();
        newAuthor.setName(author);
        newAuthor.setId(maxId + 1);
        newAuthor.setInitials(author.replaceAll("\\s*(\\w)\\S*", "$1").toUpperCase(LocaleUtil.getUserLocale()));
        this.comment.setAuthorId(1 + maxId);
    }

    @Override // org.apache.poi.sl.usermodel.Comment
    public String getAuthorInitials() {
        CTCommentAuthor aut = this.authors.getAuthorById(this.comment.getAuthorId());
        if (aut == null) {
            return null;
        }
        return aut.getInitials();
    }

    @Override // org.apache.poi.sl.usermodel.Comment
    public void setAuthorInitials(String initials) {
        CTCommentAuthor aut = this.authors.getAuthorById(this.comment.getAuthorId());
        if (aut != null) {
            aut.setInitials(initials);
        }
    }

    @Override // org.apache.poi.sl.usermodel.Comment
    public String getText() {
        return this.comment.getText();
    }

    @Override // org.apache.poi.sl.usermodel.Comment
    public void setText(String text) {
        this.comment.setText(text);
    }

    @Override // org.apache.poi.sl.usermodel.Comment
    public Date getDate() {
        Calendar cal = this.comment.getDt();
        if (cal == null) {
            return null;
        }
        return cal.getTime();
    }

    @Override // org.apache.poi.sl.usermodel.Comment
    public void setDate(Date date) {
        Calendar cal = LocaleUtil.getLocaleCalendar();
        cal.setTime(date);
        this.comment.setDt(cal);
    }

    @Override // org.apache.poi.sl.usermodel.Comment
    public Point2D getOffset() {
        CTPoint2D pos = this.comment.getPos();
        return new Point2D.Double(Units.toPoints(POIXMLUnits.parseLength(pos.xgetX())), Units.toPoints(POIXMLUnits.parseLength(pos.xgetY())));
    }

    @Override // org.apache.poi.sl.usermodel.Comment
    public void setOffset(Point2D offset) {
        CTPoint2D pos = this.comment.getPos();
        if (pos == null) {
            pos = this.comment.addNewPos();
        }
        pos.setX(Integer.valueOf(Units.toEMU(offset.getX())));
        pos.setY(Integer.valueOf(Units.toEMU(offset.getY())));
    }
}
