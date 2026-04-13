package org.apache.poi.xwpf.usermodel;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.message.StructuredDataId;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTComment;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtBlock;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;

/* loaded from: classes10.dex */
public class XWPFComment implements IBody {
    protected XWPFComments comments;
    protected CTComment ctComment;
    protected XWPFDocument document;
    private List<XWPFParagraph> paragraphs = new ArrayList();
    private List<XWPFTable> tables = new ArrayList();
    private List<IBodyElement> bodyElements = new ArrayList();

    public XWPFComment(CTComment ctComment, XWPFComments comments) {
        this.comments = comments;
        this.ctComment = ctComment;
        this.document = comments.getXWPFDocument();
        init();
    }

    protected void init() {
        XmlCursor cursor = this.ctComment.newCursor();
        try {
            cursor.selectPath("./*");
            while (cursor.toNextSelection()) {
                XmlObject o = cursor.getObject();
                if (o instanceof CTP) {
                    XWPFParagraph p = new XWPFParagraph((CTP) o, this);
                    this.bodyElements.add(p);
                    this.paragraphs.add(p);
                } else if (o instanceof CTTbl) {
                    XWPFTable t = new XWPFTable((CTTbl) o, this, false);
                    this.bodyElements.add(t);
                    this.tables.add(t);
                } else if (o instanceof CTSdtBlock) {
                    XWPFSDT c = new XWPFSDT((CTSdtBlock) o, this);
                    this.bodyElements.add(c);
                }
            }
            if (cursor != null) {
                cursor.close();
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (cursor != null) {
                    try {
                        cursor.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    @Override // org.apache.poi.xwpf.usermodel.IBody
    public POIXMLDocumentPart getPart() {
        return this.comments;
    }

    @Override // org.apache.poi.xwpf.usermodel.IBody
    public BodyType getPartType() {
        return BodyType.COMMENT;
    }

    @Override // org.apache.poi.xwpf.usermodel.IBody
    public List<IBodyElement> getBodyElements() {
        return Collections.unmodifiableList(this.bodyElements);
    }

    @Override // org.apache.poi.xwpf.usermodel.IBody
    public List<XWPFParagraph> getParagraphs() {
        return Collections.unmodifiableList(this.paragraphs);
    }

    @Override // org.apache.poi.xwpf.usermodel.IBody
    public List<XWPFTable> getTables() {
        return Collections.unmodifiableList(this.tables);
    }

    @Override // org.apache.poi.xwpf.usermodel.IBody
    public XWPFParagraph getParagraph(CTP p) {
        for (XWPFParagraph paragraph : this.paragraphs) {
            if (paragraph.getCTP().equals(p)) {
                return paragraph;
            }
        }
        return null;
    }

    @Override // org.apache.poi.xwpf.usermodel.IBody
    public XWPFTable getTable(CTTbl ctTable) {
        XWPFTable table;
        Iterator<XWPFTable> it = this.tables.iterator();
        while (it.hasNext() && (table = it.next()) != null) {
            if (table.getCTTbl().equals(ctTable)) {
                return table;
            }
        }
        return null;
    }

    @Override // org.apache.poi.xwpf.usermodel.IBody
    public XWPFParagraph getParagraphArray(int pos) {
        if (pos >= 0 && pos < this.paragraphs.size()) {
            return this.paragraphs.get(pos);
        }
        return null;
    }

    @Override // org.apache.poi.xwpf.usermodel.IBody
    public XWPFTable getTableArray(int pos) {
        if (pos >= 0 && pos < this.tables.size()) {
            return this.tables.get(pos);
        }
        return null;
    }

    @Override // org.apache.poi.xwpf.usermodel.IBody
    public XWPFParagraph insertNewParagraph(XmlCursor cursor) {
        if (isCursorInCmt(cursor)) {
            String uri = CTP.type.getName().getNamespaceURI();
            cursor.beginElement("p", uri);
            cursor.toParent();
            CTP p = (CTP) cursor.getObject();
            XWPFParagraph newP = new XWPFParagraph(p, this);
            XmlObject o = null;
            while (!(o instanceof CTP) && cursor.toPrevSibling()) {
                o = cursor.getObject();
            }
            if (!(o instanceof CTP) || o == p) {
                this.paragraphs.add(0, newP);
            } else {
                int pos = this.paragraphs.indexOf(getParagraph((CTP) o)) + 1;
                this.paragraphs.add(pos, newP);
            }
            int i = 0;
            XmlCursor p2 = p.newCursor();
            try {
                cursor.toCursor(p2);
                if (p2 != null) {
                    p2.close();
                }
                while (cursor.toPrevSibling()) {
                    XmlObject o2 = cursor.getObject();
                    if ((o2 instanceof CTP) || (o2 instanceof CTTbl)) {
                        i++;
                    }
                }
                this.bodyElements.add(i, newP);
                p2 = p.newCursor();
                try {
                    cursor.toCursor(p2);
                    cursor.toEndToken();
                    if (p2 != null) {
                        p2.close();
                    }
                    return newP;
                } finally {
                }
            } finally {
                try {
                    throw th;
                } finally {
                }
            }
        } else {
            return null;
        }
    }

    private boolean isCursorInCmt(XmlCursor cursor) {
        XmlCursor verify = cursor.newCursor();
        try {
            verify.toParent();
            boolean z = verify.getObject() == this.ctComment;
            if (verify != null) {
                verify.close();
            }
            return z;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (verify != null) {
                    try {
                        verify.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    @Override // org.apache.poi.xwpf.usermodel.IBody
    public XWPFTable insertNewTbl(XmlCursor cursor) {
        if (isCursorInCmt(cursor)) {
            String uri = CTTbl.type.getName().getNamespaceURI();
            cursor.beginElement("tbl", uri);
            cursor.toParent();
            CTTbl t = (CTTbl) cursor.getObject();
            XWPFTable newT = new XWPFTable(t, this);
            cursor.removeXmlContents();
            XmlObject o = null;
            while (!(o instanceof CTTbl) && cursor.toPrevSibling()) {
                o = cursor.getObject();
            }
            if (!(o instanceof CTTbl)) {
                this.tables.add(0, newT);
            } else {
                int pos = this.tables.indexOf(getTable((CTTbl) o)) + 1;
                this.tables.add(pos, newT);
            }
            int i = 0;
            XmlCursor cursor2 = t.newCursor();
            while (cursor2.toPrevSibling()) {
                try {
                    XmlObject o2 = cursor2.getObject();
                    if ((o2 instanceof CTP) || (o2 instanceof CTTbl)) {
                        i++;
                    }
                } catch (Throwable th) {
                    try {
                        throw th;
                    } finally {
                    }
                }
            }
            if (cursor2 != null) {
                cursor2.close();
            }
            this.bodyElements.add(i, newT);
            cursor2 = t.newCursor();
            try {
                cursor.toCursor(cursor2);
                cursor.toEndToken();
                if (cursor2 != null) {
                    cursor2.close();
                }
                return newT;
            } finally {
            }
        } else {
            return null;
        }
    }

    @Override // org.apache.poi.xwpf.usermodel.IBody
    public void insertTable(int pos, XWPFTable table) {
        this.bodyElements.add(pos, table);
        int i = 0;
        for (CTTbl tbl : this.ctComment.getTblList()) {
            if (tbl == table.getCTTbl()) {
                break;
            } else {
                i++;
            }
        }
        this.tables.add(i, table);
    }

    @Override // org.apache.poi.xwpf.usermodel.IBody
    public XWPFTableCell getTableCell(CTTc cell) {
        XmlCursor cursor = cell.newCursor();
        try {
            cursor.toParent();
            XmlObject o = cursor.getObject();
            if (o instanceof CTRow) {
                CTRow row = (CTRow) o;
                cursor.toParent();
                XmlObject o2 = cursor.getObject();
                if (cursor != null) {
                    cursor.close();
                }
                if (!(o2 instanceof CTTbl)) {
                    return null;
                }
                CTTbl tbl = (CTTbl) o2;
                XWPFTable table = getTable(tbl);
                if (table == null) {
                    return null;
                }
                XWPFTableRow tableRow = table.getRow(row);
                return tableRow.getTableCell(cell);
            }
            if (cursor != null) {
                cursor.close();
            }
            return null;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (cursor != null) {
                    try {
                        cursor.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    @Override // org.apache.poi.xwpf.usermodel.IBody
    public XWPFDocument getXWPFDocument() {
        return this.document;
    }

    public String getText() {
        StringBuilder text = new StringBuilder();
        for (XWPFParagraph p : this.paragraphs) {
            if (text.length() > 0) {
                text.append(StringUtils.LF);
            }
            text.append(p.getText());
        }
        return text.toString();
    }

    public XWPFParagraph createParagraph() {
        XWPFParagraph paragraph = new XWPFParagraph(this.ctComment.addNewP(), this);
        this.paragraphs.add(paragraph);
        this.bodyElements.add(paragraph);
        return paragraph;
    }

    public void removeParagraph(XWPFParagraph paragraph) {
        if (this.paragraphs.contains(paragraph)) {
            CTP ctP = paragraph.getCTP();
            XmlCursor c = ctP.newCursor();
            try {
                c.removeXml();
                if (c != null) {
                    c.close();
                }
                this.paragraphs.remove(paragraph);
                this.bodyElements.remove(paragraph);
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    if (c != null) {
                        try {
                            c.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                    }
                    throw th2;
                }
            }
        }
    }

    public void removeTable(XWPFTable table) {
        if (this.tables.contains(table)) {
            CTTbl ctTbl = table.getCTTbl();
            XmlCursor c = ctTbl.newCursor();
            try {
                c.removeXml();
                if (c != null) {
                    c.close();
                }
                this.tables.remove(table);
                this.bodyElements.remove(table);
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    if (c != null) {
                        try {
                            c.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                    }
                    throw th2;
                }
            }
        }
    }

    public XWPFTable createTable(int rows, int cols) {
        XWPFTable table = new XWPFTable(this.ctComment.addNewTbl(), this, rows, cols);
        this.tables.add(table);
        this.bodyElements.add(table);
        return table;
    }

    public CTComment getCtComment() {
        return this.ctComment;
    }

    public XWPFComments getComments() {
        return this.comments;
    }

    public String getId() {
        BigInteger id = this.ctComment.getId();
        return id == null ? StructuredDataId.RESERVED : id.toString();
    }

    public String getAuthor() {
        return this.ctComment.getAuthor();
    }

    public void setAuthor(String author) {
        this.ctComment.setAuthor(author);
    }

    public String getInitials() {
        return this.ctComment.getInitials();
    }

    public void setInitials(String initials) {
        this.ctComment.setInitials(initials);
    }

    public Calendar getDate() {
        return this.ctComment.getDate();
    }

    public void setDate(Calendar date) {
        this.ctComment.setDate(date);
    }
}
