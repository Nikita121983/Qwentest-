package org.apache.poi.xwpf.usermodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import kotlinx.coroutines.DebugKt;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Units;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtBlock;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTVerticalJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STVerticalJc;

/* loaded from: classes10.dex */
public class XWPFTableCell implements IBody, ICell {
    private static final int MAX_RECURSION_DEPTH = 1000;
    private static final EnumMap<XWPFVertAlign, STVerticalJc.Enum> alignMap = new EnumMap<>(XWPFVertAlign.class);
    private static final HashMap<Integer, XWPFVertAlign> stVertAlignTypeMap;
    protected List<IBodyElement> bodyElements;
    private final CTTc ctTc;
    protected List<XWPFParagraph> paragraphs;
    protected IBody part;
    private final XWPFTableRow tableRow;
    protected List<XWPFTable> tables;
    private final XWPFDocument xwpfDocument;

    /* loaded from: classes10.dex */
    public enum XWPFVertAlign {
        TOP,
        CENTER,
        BOTH,
        BOTTOM
    }

    static {
        alignMap.put((EnumMap<XWPFVertAlign, STVerticalJc.Enum>) XWPFVertAlign.TOP, (XWPFVertAlign) STVerticalJc.TOP);
        alignMap.put((EnumMap<XWPFVertAlign, STVerticalJc.Enum>) XWPFVertAlign.CENTER, (XWPFVertAlign) STVerticalJc.CENTER);
        alignMap.put((EnumMap<XWPFVertAlign, STVerticalJc.Enum>) XWPFVertAlign.BOTH, (XWPFVertAlign) STVerticalJc.BOTH);
        alignMap.put((EnumMap<XWPFVertAlign, STVerticalJc.Enum>) XWPFVertAlign.BOTTOM, (XWPFVertAlign) STVerticalJc.BOTTOM);
        stVertAlignTypeMap = new HashMap<>();
        stVertAlignTypeMap.put(1, XWPFVertAlign.TOP);
        stVertAlignTypeMap.put(2, XWPFVertAlign.CENTER);
        stVertAlignTypeMap.put(3, XWPFVertAlign.BOTH);
        stVertAlignTypeMap.put(4, XWPFVertAlign.BOTTOM);
    }

    public XWPFTableCell(CTTc cell, XWPFTableRow tableRow, IBody part) {
        if (cell == null) {
            throw new IllegalArgumentException("CTTc cannot be null");
        }
        if (tableRow == null) {
            throw new IllegalArgumentException("tableRow cannot be null");
        }
        this.ctTc = cell;
        this.part = part;
        this.tableRow = tableRow;
        this.xwpfDocument = part == null ? null : part.getXWPFDocument();
        this.bodyElements = new ArrayList();
        this.paragraphs = new ArrayList();
        this.tables = new ArrayList();
        XmlCursor cursor = this.ctTc.newCursor();
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
                if (o instanceof CTSdtBlock) {
                    XWPFSDT c = new XWPFSDT((CTSdtBlock) o, this);
                    this.bodyElements.add(c);
                }
                if (o instanceof CTSdtRun) {
                    XWPFSDT c2 = new XWPFSDT((CTSdtRun) o, this);
                    this.bodyElements.add(c2);
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

    @Internal
    public CTTc getCTTc() {
        return this.ctTc;
    }

    @Override // org.apache.poi.xwpf.usermodel.IBody
    public List<IBodyElement> getBodyElements() {
        return Collections.unmodifiableList(this.bodyElements);
    }

    public void setParagraph(XWPFParagraph p) {
        if (this.ctTc.sizeOfPArray() == 0) {
            this.ctTc.addNewP();
        }
        this.ctTc.setPArray(0, p.getCTP());
    }

    @Override // org.apache.poi.xwpf.usermodel.IBody
    public List<XWPFParagraph> getParagraphs() {
        return Collections.unmodifiableList(this.paragraphs);
    }

    public XWPFParagraph addParagraph() {
        XWPFParagraph p = new XWPFParagraph(this.ctTc.addNewP(), this);
        addParagraph(p);
        return p;
    }

    public void addParagraph(XWPFParagraph p) {
        this.paragraphs.add(p);
        this.bodyElements.add(p);
    }

    public void removeParagraph(int pos) {
        XWPFParagraph removedParagraph = this.paragraphs.get(pos);
        this.paragraphs.remove(pos);
        this.ctTc.removeP(pos);
        this.bodyElements.remove(removedParagraph);
    }

    @Override // org.apache.poi.xwpf.usermodel.IBody
    public XWPFParagraph getParagraph(CTP p) {
        for (XWPFParagraph paragraph : this.paragraphs) {
            if (p.equals(paragraph.getCTP())) {
                return paragraph;
            }
        }
        return null;
    }

    public XWPFTableRow getTableRow() {
        return this.tableRow;
    }

    public String getColor() {
        CTShd ctshd;
        CTTcPr tcpr = this.ctTc.getTcPr();
        if (tcpr == null || (ctshd = tcpr.getShd()) == null) {
            return null;
        }
        String color = ctshd.xgetFill().getStringValue();
        return color;
    }

    public void setColor(String rgbStr) {
        CTTcPr tcpr = getTcPr();
        CTShd ctshd = tcpr.isSetShd() ? tcpr.getShd() : tcpr.addNewShd();
        ctshd.setColor(DebugKt.DEBUG_PROPERTY_VALUE_AUTO);
        ctshd.setVal(STShd.CLEAR);
        ctshd.setFill(rgbStr);
    }

    public XWPFVertAlign getVerticalAlignment() {
        CTVerticalJc va;
        CTTcPr tcpr = this.ctTc.getTcPr();
        if (tcpr == null || (va = tcpr.getVAlign()) == null) {
            return null;
        }
        XWPFVertAlign vAlign = stVertAlignTypeMap.get(Integer.valueOf(va.getVal().intValue()));
        return vAlign;
    }

    public void setVerticalAlignment(XWPFVertAlign vAlign) {
        CTTcPr tcpr = getTcPr();
        CTVerticalJc va = tcpr.addNewVAlign();
        va.setVal(alignMap.get(vAlign));
    }

    @Override // org.apache.poi.xwpf.usermodel.IBody
    public XWPFParagraph insertNewParagraph(XmlCursor cursor) {
        if (!isCursorInTableCell(cursor)) {
            return null;
        }
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
                if (p3 != null) {
                    p3.close();
                }
                cursor.toEndToken();
                return newP;
            } finally {
            }
        } finally {
            try {
                throw th;
            } finally {
            }
        }
    }

    @Override // org.apache.poi.xwpf.usermodel.IBody
    public XWPFTable insertNewTbl(XmlCursor cursor) {
        if (isCursorInTableCell(cursor)) {
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

    private boolean isCursorInTableCell(XmlCursor cursor) {
        XmlCursor verify = cursor.newCursor();
        try {
            verify.toParent();
            boolean z = verify.getObject() == this.ctTc;
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
    public XWPFParagraph getParagraphArray(int pos) {
        if (pos >= 0 && pos < this.paragraphs.size()) {
            return this.paragraphs.get(pos);
        }
        return null;
    }

    @Override // org.apache.poi.xwpf.usermodel.IBody
    public POIXMLDocumentPart getPart() {
        return this.tableRow.getTable().getPart();
    }

    @Override // org.apache.poi.xwpf.usermodel.IBody
    public BodyType getPartType() {
        return BodyType.TABLECELL;
    }

    @Override // org.apache.poi.xwpf.usermodel.IBody
    public XWPFTable getTable(CTTbl ctTable) {
        for (int i = 0; i < this.tables.size(); i++) {
            if (getTables().get(i).getCTTbl() == ctTable) {
                return getTables().get(i);
            }
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
    public List<XWPFTable> getTables() {
        return Collections.unmodifiableList(this.tables);
    }

    @Override // org.apache.poi.xwpf.usermodel.IBody
    public void insertTable(int pos, XWPFTable table) {
        this.bodyElements.add(pos, table);
        int i = 0;
        for (CTTbl tbl : this.ctTc.getTblList()) {
            if (tbl == table.getCTTbl()) {
                break;
            } else {
                i++;
            }
        }
        this.tables.add(i, table);
    }

    public void removeTable(int pos) {
        XWPFTable removedTable = this.tables.get(pos);
        this.tables.remove(pos);
        this.ctTc.removeTbl(pos);
        this.bodyElements.remove(removedTable);
    }

    public String getText() {
        StringBuilder text = new StringBuilder();
        for (XWPFParagraph p : this.paragraphs) {
            text.append(p.getText());
        }
        return text.toString();
    }

    public void setText(String text) {
        XWPFParagraph par = this.paragraphs.isEmpty() ? addParagraph() : this.paragraphs.get(0);
        while (!par.runsIsEmpty()) {
            par.removeRun(0);
        }
        par.createRun().setText(text);
    }

    public void appendText(String text) {
        XWPFParagraph par = this.paragraphs.isEmpty() ? addParagraph() : this.paragraphs.get(0);
        par.createRun().setText(text);
    }

    public String getTextRecursively() {
        StringBuilder text = new StringBuilder(64);
        for (int i = 0; i < this.bodyElements.size(); i++) {
            boolean isLast = true;
            if (i != this.bodyElements.size() - 1) {
                isLast = false;
            }
            appendBodyElementText(text, this.bodyElements.get(i), isLast);
        }
        return text.toString();
    }

    private void appendBodyElementText(StringBuilder text, IBodyElement e, boolean isLast) {
        if (e instanceof XWPFParagraph) {
            text.append(((XWPFParagraph) e).getText());
            if (!isLast) {
                text.append('\t');
                return;
            }
            return;
        }
        if (e instanceof XWPFTable) {
            XWPFTable eTable = (XWPFTable) e;
            for (XWPFTableRow row : eTable.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    List<IBodyElement> localBodyElements = cell.getBodyElements();
                    for (int i = 0; i < localBodyElements.size(); i++) {
                        boolean localIsLast = true;
                        if (i != localBodyElements.size() - 1) {
                            localIsLast = false;
                        }
                        appendBodyElementText(text, localBodyElements.get(i), localIsLast);
                    }
                }
            }
            if (!isLast) {
                text.append('\n');
                return;
            }
            return;
        }
        if (e instanceof XWPFSDT) {
            text.append(((XWPFSDT) e).getContent().getText());
            if (!isLast) {
                text.append('\t');
            }
        }
    }

    @Override // org.apache.poi.xwpf.usermodel.IBody
    public XWPFTableCell getTableCell(CTTc cell) {
        XWPFTableRow tr;
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
                if (table == null || (tr = table.getRow(row)) == null) {
                    return null;
                }
                return tr.getTableCell(cell);
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
        if (this.xwpfDocument != null) {
            return this.xwpfDocument;
        }
        if (this.part instanceof XWPFTableCell) {
            return getCellDocument((XWPFTableCell) this.part, 0);
        }
        if (this.part != null) {
            return this.part.getXWPFDocument();
        }
        return null;
    }

    private static XWPFDocument getCellDocument(XWPFTableCell cell, int depth) {
        if (depth > 1000) {
            throw new IllegalStateException("Recursion depth exceeded while trying to get XWPFDocument from XWPFTableCell");
        }
        if (cell.part instanceof XWPFTableCell) {
            return getCellDocument((XWPFTableCell) cell.part, depth + 1);
        }
        return cell.part.getXWPFDocument();
    }

    public double getWidthDecimal() {
        return XWPFTable.getWidthDecimal(getTcWidth());
    }

    public TableWidthType getWidthType() {
        return XWPFTable.getWidthType(getTcWidth());
    }

    public void setWidth(String widthValue) {
        XWPFTable.setWidthValue(widthValue, getTcWidth());
    }

    private CTTblWidth getTcWidth() {
        CTTcPr tcPr = getTcPr();
        return tcPr.isSetTcW() ? tcPr.getTcW() : tcPr.addNewTcW();
    }

    protected CTTcPr getTcPr() {
        return this.ctTc.isSetTcPr() ? this.ctTc.getTcPr() : this.ctTc.addNewTcPr();
    }

    public void setWidthType(TableWidthType widthType) {
        XWPFTable.setWidthType(widthType, getTcWidth());
    }

    public int getWidth() {
        return (int) Units.toDXA(POIXMLUnits.parseLength(getTcWidth().xgetW()));
    }
}
