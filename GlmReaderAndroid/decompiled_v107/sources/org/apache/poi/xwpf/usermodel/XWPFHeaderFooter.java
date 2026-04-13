package org.apache.poi.xwpf.usermodel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.common.usermodel.PictureType;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ooxml.POIXMLRelation;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Removal;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHdrFtr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;

/* loaded from: classes10.dex */
public abstract class XWPFHeaderFooter extends POIXMLDocumentPart implements IBody {
    List<IBodyElement> bodyElements;
    XWPFDocument document;
    CTHdrFtr headerFooter;
    List<XWPFParagraph> paragraphs;
    List<XWPFPictureData> pictures;
    List<XWPFTable> tables;

    /* JADX INFO: Access modifiers changed from: package-private */
    public XWPFHeaderFooter(XWPFDocument doc, CTHdrFtr hdrFtr) {
        this.paragraphs = new ArrayList();
        this.tables = new ArrayList();
        this.pictures = new ArrayList();
        this.bodyElements = new ArrayList();
        if (doc == null) {
            throw new NullPointerException();
        }
        this.document = doc;
        this.headerFooter = hdrFtr;
        readHdrFtr();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public XWPFHeaderFooter() {
        this.paragraphs = new ArrayList();
        this.tables = new ArrayList();
        this.pictures = new ArrayList();
        this.bodyElements = new ArrayList();
        this.headerFooter = CTHdrFtr.Factory.newInstance();
        readHdrFtr();
    }

    public XWPFHeaderFooter(POIXMLDocumentPart parent, PackagePart part) {
        super(parent, part);
        this.paragraphs = new ArrayList();
        this.tables = new ArrayList();
        this.pictures = new ArrayList();
        this.bodyElements = new ArrayList();
        POIXMLDocumentPart p = getParent();
        if (!(p instanceof XWPFDocument)) {
            throw new IllegalArgumentException("Had unexpected type of parent: " + (p == null ? "<null>" : p.getClass()));
        }
        this.document = (XWPFDocument) p;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.ooxml.POIXMLDocumentPart
    public void onDocumentRead() throws IOException {
        for (POIXMLDocumentPart poixmlDocumentPart : getRelations()) {
            if (poixmlDocumentPart instanceof XWPFPictureData) {
                XWPFPictureData xwpfPicData = (XWPFPictureData) poixmlDocumentPart;
                this.pictures.add(xwpfPicData);
                this.document.registerPackagePictureData(xwpfPicData);
            }
        }
    }

    @Internal
    public CTHdrFtr _getHdrFtr() {
        return this.headerFooter;
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
    public List<XWPFTable> getTables() throws ArrayIndexOutOfBoundsException {
        return Collections.unmodifiableList(this.tables);
    }

    public String getText() {
        String text;
        StringBuilder t = new StringBuilder(64);
        for (XWPFParagraph paragraph : this.paragraphs) {
            if (!paragraph.isEmpty() && (text = paragraph.getText()) != null && text.length() > 0) {
                t.append(text);
                t.append('\n');
            }
        }
        for (XWPFTable table : this.tables) {
            String text2 = table.getText();
            if (text2 != null && text2.length() > 0) {
                t.append(text2);
                t.append('\n');
            }
        }
        for (IBodyElement bodyElement : getBodyElements()) {
            if (bodyElement instanceof XWPFSDT) {
                t.append(((XWPFSDT) bodyElement).getContent().getText()).append('\n');
            }
        }
        return t.toString();
    }

    public void setHeaderFooter(CTHdrFtr headerFooter) {
        this.headerFooter = headerFooter;
        readHdrFtr();
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

    public List<XWPFParagraph> getListParagraph() {
        return Collections.unmodifiableList(this.paragraphs);
    }

    public List<XWPFPictureData> getAllPictures() {
        return Collections.unmodifiableList(this.pictures);
    }

    public List<XWPFPictureData> getAllPackagePictures() {
        return this.document.getAllPackagePictures();
    }

    @Removal(version = "7.0.0")
    @Deprecated
    public String addPictureData(byte[] pictureData, int format) throws InvalidFormatException {
        return addPictureData(pictureData, PictureType.findByOoxmlId(format));
    }

    public String addPictureData(byte[] pictureData, PictureType pictureType) throws InvalidFormatException {
        if (pictureType == null) {
            throw new InvalidFormatException("pictureType is not supported");
        }
        XWPFPictureData xwpfPicData = this.document.findPackagePictureData(pictureData);
        POIXMLRelation relDesc = XWPFPictureData.RELATIONS[pictureType.ooxmlId];
        if (xwpfPicData == null) {
            int idx = this.document.getNextPicNameNumber(pictureType);
            XWPFPictureData xwpfPicData2 = (XWPFPictureData) createRelationship(relDesc, XWPFFactory.getInstance(), idx);
            PackagePart picDataPart = xwpfPicData2.getPackagePart();
            try {
                OutputStream out = picDataPart.getOutputStream();
                try {
                    out.write(pictureData);
                    if (out != null) {
                        out.close();
                    }
                    this.document.registerPackagePictureData(xwpfPicData2);
                    this.pictures.add(xwpfPicData2);
                    return getRelationId(xwpfPicData2);
                } finally {
                }
            } catch (IOException e) {
                throw new POIXMLException(e);
            }
        } else {
            if (!getRelations().contains(xwpfPicData)) {
                POIXMLDocumentPart.RelationPart rp = addRelation(null, XWPFRelation.IMAGES, xwpfPicData);
                this.pictures.add(xwpfPicData);
                return rp.getRelationship().getId();
            }
            return getRelationId(xwpfPicData);
        }
    }

    public String addPictureData(InputStream is, int format) throws InvalidFormatException, IOException {
        byte[] data = IOUtils.toByteArrayWithMaxLength(is, XWPFPictureData.getMaxImageSize());
        return addPictureData(data, format);
    }

    public String addPictureData(InputStream is, PictureType pictureType) throws InvalidFormatException, IOException {
        byte[] data = IOUtils.toByteArrayWithMaxLength(is, XWPFPictureData.getMaxImageSize());
        return addPictureData(data, pictureType);
    }

    public XWPFPictureData getPictureDataByID(String blipID) {
        POIXMLDocumentPart relatedPart = getRelationById(blipID);
        if (relatedPart instanceof XWPFPictureData) {
            return (XWPFPictureData) relatedPart;
        }
        return null;
    }

    public XWPFParagraph createParagraph() {
        XWPFParagraph paragraph = new XWPFParagraph(this.headerFooter.addNewP(), this);
        this.paragraphs.add(paragraph);
        this.bodyElements.add(paragraph);
        return paragraph;
    }

    public XWPFTable createTable(int rows, int cols) {
        XWPFTable table = new XWPFTable(this.headerFooter.addNewTbl(), this, rows, cols);
        this.tables.add(table);
        this.bodyElements.add(table);
        return table;
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

    public void clearHeaderFooter() {
        XmlCursor c = this.headerFooter.newCursor();
        try {
            c.removeXmlContents();
            if (c != null) {
                c.close();
            }
            this.paragraphs.clear();
            this.tables.clear();
            this.bodyElements.clear();
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

    @Override // org.apache.poi.xwpf.usermodel.IBody
    public XWPFParagraph insertNewParagraph(XmlCursor cursor) {
        if (isCursorInHdrF(cursor)) {
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
            XmlCursor p3 = p.newCursor();
            try {
                cursor.toCursor(p3);
                if (p3 != null) {
                    p3.close();
                }
                while (cursor.toPrevSibling()) {
                    XmlObject o2 = cursor.getObject();
                    if ((o2 instanceof CTP) || (o2 instanceof CTTbl)) {
                        i++;
                    }
                }
                this.bodyElements.add(i, newP);
                p3 = p.newCursor();
                try {
                    cursor.toCursor(p3);
                    cursor.toEndToken();
                    if (p3 != null) {
                        p3.close();
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

    @Override // org.apache.poi.xwpf.usermodel.IBody
    public XWPFTable insertNewTbl(XmlCursor cursor) {
        if (isCursorInHdrF(cursor)) {
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
            XmlCursor cursor3 = t.newCursor();
            while (cursor3.toPrevSibling()) {
                try {
                    XmlObject o2 = cursor3.getObject();
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
            if (cursor3 != null) {
                cursor3.close();
            }
            this.bodyElements.add(i, newT);
            cursor3 = t.newCursor();
            try {
                cursor.toCursor(cursor3);
                cursor.toEndToken();
                if (cursor3 != null) {
                    cursor3.close();
                }
                return newT;
            } finally {
            }
        } else {
            return null;
        }
    }

    private boolean isCursorInHdrF(XmlCursor cursor) {
        XmlCursor verify = cursor.newCursor();
        try {
            verify.toParent();
            boolean z = verify.getObject() == this.headerFooter;
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
        return this;
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
        for (CTTbl tbl : this.headerFooter.getTblArray()) {
            if (tbl == table.getCTTbl()) {
                break;
            }
            i++;
        }
        this.tables.add(i, table);
    }

    public void readHdrFtr() {
        this.bodyElements = new ArrayList();
        this.paragraphs = new ArrayList();
        this.tables = new ArrayList();
        XmlCursor cursor = this.headerFooter.newCursor();
        try {
            cursor.selectPath("./*");
            while (cursor.toNextSelection()) {
                XmlObject o = cursor.getObject();
                if (o instanceof CTP) {
                    XWPFParagraph p = new XWPFParagraph((CTP) o, this);
                    this.paragraphs.add(p);
                    this.bodyElements.add(p);
                }
                if (o instanceof CTTbl) {
                    XWPFTable t = new XWPFTable((CTTbl) o, this, false);
                    this.tables.add(t);
                    this.bodyElements.add(t);
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
        if (this.document != null) {
            return this.document;
        }
        return (XWPFDocument) getParent();
    }

    public void setXWPFDocument(XWPFDocument doc) {
        this.document = doc;
    }

    @Override // org.apache.poi.xwpf.usermodel.IBody
    public POIXMLDocumentPart getPart() {
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.ooxml.POIXMLDocumentPart
    public void prepareForCommit() {
        if (this.bodyElements.isEmpty()) {
            createParagraph();
        }
        for (XWPFTable tbl : this.tables) {
            for (XWPFTableRow row : tbl.tableRows) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    if (cell.getBodyElements().isEmpty()) {
                        cell.addParagraph();
                    }
                }
            }
        }
        super.prepareForCommit();
    }
}
