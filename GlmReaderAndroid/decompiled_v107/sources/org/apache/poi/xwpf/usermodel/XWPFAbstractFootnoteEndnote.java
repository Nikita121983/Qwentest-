package org.apache.poi.xwpf.usermodel;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.util.Internal;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnEdn;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtBlock;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;

/* loaded from: classes10.dex */
public abstract class XWPFAbstractFootnoteEndnote implements Iterable<XWPFParagraph>, IBody {
    protected CTFtnEdn ctFtnEdn;
    protected XWPFDocument document;
    protected XWPFAbstractFootnotesEndnotes footnotes;
    private final List<XWPFParagraph> paragraphs = new ArrayList();
    private final List<XWPFTable> tables = new ArrayList();
    private final List<XWPFPictureData> pictures = new ArrayList();
    private final List<IBodyElement> bodyElements = new ArrayList();

    public abstract void ensureFootnoteRef(XWPFParagraph xWPFParagraph);

    public XWPFAbstractFootnoteEndnote() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public XWPFAbstractFootnoteEndnote(XWPFDocument document, CTFtnEdn body) {
        this.ctFtnEdn = body;
        this.document = document;
        init();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public XWPFAbstractFootnoteEndnote(CTFtnEdn note, XWPFAbstractFootnotesEndnotes footnotes) {
        this.footnotes = footnotes;
        this.ctFtnEdn = note;
        this.document = footnotes.getXWPFDocument();
        init();
    }

    protected void init() {
        XmlCursor cursor = this.ctFtnEdn.newCursor();
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
    public List<XWPFParagraph> getParagraphs() {
        return Collections.unmodifiableList(this.paragraphs);
    }

    @Override // java.lang.Iterable
    public Iterator<XWPFParagraph> iterator() {
        return this.paragraphs.iterator();
    }

    @Override // java.lang.Iterable
    public Spliterator<XWPFParagraph> spliterator() {
        return this.paragraphs.spliterator();
    }

    @Override // org.apache.poi.xwpf.usermodel.IBody
    public List<XWPFTable> getTables() {
        return Collections.unmodifiableList(this.tables);
    }

    public List<XWPFPictureData> getPictures() {
        return Collections.unmodifiableList(this.pictures);
    }

    @Override // org.apache.poi.xwpf.usermodel.IBody
    public List<IBodyElement> getBodyElements() {
        return Collections.unmodifiableList(this.bodyElements);
    }

    public CTFtnEdn getCTFtnEdn() {
        return this.ctFtnEdn;
    }

    public void setCTFtnEdn(CTFtnEdn footnote) {
        this.ctFtnEdn = footnote;
    }

    @Override // org.apache.poi.xwpf.usermodel.IBody
    public XWPFTable getTableArray(int pos) {
        if (pos >= 0 && pos < this.tables.size()) {
            return this.tables.get(pos);
        }
        return null;
    }

    @Override // org.apache.poi.xwpf.usermodel.IBody
    public void insertTable(int pos, XWPFTable table) {
        this.bodyElements.add(pos, table);
        int i = 0;
        for (CTTbl tbl : this.ctFtnEdn.getTblList()) {
            if (tbl == table.getCTTbl()) {
                break;
            } else {
                i++;
            }
        }
        this.tables.add(i, table);
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
    public XWPFParagraph getParagraph(CTP p) {
        for (XWPFParagraph paragraph : this.paragraphs) {
            if (paragraph.getCTP().equals(p)) {
                return paragraph;
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
    public XWPFTableCell getTableCell(CTTc cell) {
        XWPFTableRow tableRow;
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
                if (table == null || (tableRow = table.getRow(row)) == null) {
                    return null;
                }
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

    private boolean isCursorInFtn(XmlCursor cursor) {
        XmlCursor verify = cursor.newCursor();
        try {
            verify.toParent();
            boolean z = verify.getObject() == this.ctFtnEdn;
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

    public POIXMLDocumentPart getOwner() {
        return this.footnotes;
    }

    @Override // org.apache.poi.xwpf.usermodel.IBody
    public XWPFTable insertNewTbl(XmlCursor cursor) {
        if (isCursorInFtn(cursor)) {
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
            XmlCursor c2 = t.newCursor();
            while (c2.toPrevSibling()) {
                try {
                    XmlObject o2 = c2.getObject();
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
            this.bodyElements.add(i, newT);
            if (c2 != null) {
                c2.close();
            }
            c2 = t.newCursor();
            try {
                cursor.toCursor(c2);
                cursor.toEndToken();
                if (c2 != null) {
                    c2.close();
                }
                return newT;
            } finally {
            }
        } else {
            return null;
        }
    }

    @Override // org.apache.poi.xwpf.usermodel.IBody
    public XWPFParagraph insertNewParagraph(XmlCursor cursor) {
        if (isCursorInFtn(cursor)) {
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

    public XWPFTable addNewTbl(CTTbl table) {
        CTTbl newTable = this.ctFtnEdn.addNewTbl();
        newTable.set(table);
        XWPFTable xTable = new XWPFTable(newTable, this);
        this.tables.add(xTable);
        return xTable;
    }

    public XWPFParagraph addNewParagraph(CTP paragraph) {
        CTP newPara = this.ctFtnEdn.addNewP();
        newPara.set(paragraph);
        XWPFParagraph xPara = new XWPFParagraph(newPara, this);
        this.paragraphs.add(xPara);
        return xPara;
    }

    @Override // org.apache.poi.xwpf.usermodel.IBody
    public XWPFDocument getXWPFDocument() {
        return this.document;
    }

    @Override // org.apache.poi.xwpf.usermodel.IBody
    public POIXMLDocumentPart getPart() {
        return this.footnotes;
    }

    @Override // org.apache.poi.xwpf.usermodel.IBody
    public BodyType getPartType() {
        return BodyType.FOOTNOTE;
    }

    public BigInteger getId() {
        return this.ctFtnEdn.getId();
    }

    public XWPFParagraph createParagraph() {
        XWPFParagraph p = new XWPFParagraph(this.ctFtnEdn.addNewP(), this);
        this.paragraphs.add(p);
        this.bodyElements.add(p);
        if (p.equals(getParagraphs().get(0))) {
            ensureFootnoteRef(p);
        }
        return p;
    }

    public XWPFTable createTable() {
        XWPFTable table = new XWPFTable(this.ctFtnEdn.addNewTbl(), this);
        if (this.bodyElements.isEmpty()) {
            XWPFParagraph p = createParagraph();
            ensureFootnoteRef(p);
        }
        this.bodyElements.add(table);
        this.tables.add(table);
        return table;
    }

    public XWPFTable createTable(int rows, int cols) {
        XWPFTable table = new XWPFTable(this.ctFtnEdn.addNewTbl(), this, rows, cols);
        this.bodyElements.add(table);
        this.tables.add(table);
        return table;
    }
}
