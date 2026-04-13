package org.apache.poi.xwpf.usermodel;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import kotlinx.coroutines.DebugKt;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Units;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTJcTable;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblBorders;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblCellMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STBorder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJcTable;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;

/* loaded from: classes10.dex */
public class XWPFTable implements IBodyElement, ISDTContents {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final String DEFAULT_PERCENTAGE_WIDTH = "100%";
    public static final String REGEX_PERCENTAGE = "[0-9]+(\\.[0-9]+)?%";
    public static final String REGEX_WIDTH_VALUE = "auto|[0-9]+|[0-9]+(\\.[0-9]+)?%";
    private static final HashMap<Integer, XWPFBorderType> stBorderTypeMap;
    private static final EnumMap<XWPFBorderType, STBorder.Enum> xwpfBorderTypeMap = new EnumMap<>(XWPFBorderType.class);
    private final CTTbl ctTbl;
    protected IBody part;
    protected final List<XWPFTableRow> tableRows;
    protected StringBuilder text;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public enum Border {
        INSIDE_V,
        INSIDE_H,
        LEFT,
        TOP,
        BOTTOM,
        RIGHT
    }

    /* loaded from: classes10.dex */
    public enum XWPFBorderType {
        NIL,
        NONE,
        SINGLE,
        THICK,
        DOUBLE,
        DOTTED,
        DASHED,
        DOT_DASH,
        DOT_DOT_DASH,
        TRIPLE,
        THIN_THICK_SMALL_GAP,
        THICK_THIN_SMALL_GAP,
        THIN_THICK_THIN_SMALL_GAP,
        THIN_THICK_MEDIUM_GAP,
        THICK_THIN_MEDIUM_GAP,
        THIN_THICK_THIN_MEDIUM_GAP,
        THIN_THICK_LARGE_GAP,
        THICK_THIN_LARGE_GAP,
        THIN_THICK_THIN_LARGE_GAP,
        WAVE,
        DOUBLE_WAVE,
        DASH_SMALL_GAP,
        DASH_DOT_STROKED,
        THREE_D_EMBOSS,
        THREE_D_ENGRAVE,
        OUTSET,
        INSET
    }

    static {
        xwpfBorderTypeMap.put((EnumMap<XWPFBorderType, STBorder.Enum>) XWPFBorderType.NIL, (XWPFBorderType) STBorder.NIL);
        xwpfBorderTypeMap.put((EnumMap<XWPFBorderType, STBorder.Enum>) XWPFBorderType.NONE, (XWPFBorderType) STBorder.NONE);
        xwpfBorderTypeMap.put((EnumMap<XWPFBorderType, STBorder.Enum>) XWPFBorderType.SINGLE, (XWPFBorderType) STBorder.SINGLE);
        xwpfBorderTypeMap.put((EnumMap<XWPFBorderType, STBorder.Enum>) XWPFBorderType.THICK, (XWPFBorderType) STBorder.THICK);
        xwpfBorderTypeMap.put((EnumMap<XWPFBorderType, STBorder.Enum>) XWPFBorderType.DOUBLE, (XWPFBorderType) STBorder.DOUBLE);
        xwpfBorderTypeMap.put((EnumMap<XWPFBorderType, STBorder.Enum>) XWPFBorderType.DOTTED, (XWPFBorderType) STBorder.DOTTED);
        xwpfBorderTypeMap.put((EnumMap<XWPFBorderType, STBorder.Enum>) XWPFBorderType.DASHED, (XWPFBorderType) STBorder.DASHED);
        xwpfBorderTypeMap.put((EnumMap<XWPFBorderType, STBorder.Enum>) XWPFBorderType.DOT_DASH, (XWPFBorderType) STBorder.DOT_DASH);
        xwpfBorderTypeMap.put((EnumMap<XWPFBorderType, STBorder.Enum>) XWPFBorderType.DOT_DOT_DASH, (XWPFBorderType) STBorder.DOT_DOT_DASH);
        xwpfBorderTypeMap.put((EnumMap<XWPFBorderType, STBorder.Enum>) XWPFBorderType.TRIPLE, (XWPFBorderType) STBorder.TRIPLE);
        xwpfBorderTypeMap.put((EnumMap<XWPFBorderType, STBorder.Enum>) XWPFBorderType.THIN_THICK_SMALL_GAP, (XWPFBorderType) STBorder.THIN_THICK_SMALL_GAP);
        xwpfBorderTypeMap.put((EnumMap<XWPFBorderType, STBorder.Enum>) XWPFBorderType.THICK_THIN_SMALL_GAP, (XWPFBorderType) STBorder.THICK_THIN_SMALL_GAP);
        xwpfBorderTypeMap.put((EnumMap<XWPFBorderType, STBorder.Enum>) XWPFBorderType.THIN_THICK_THIN_SMALL_GAP, (XWPFBorderType) STBorder.THIN_THICK_THIN_SMALL_GAP);
        xwpfBorderTypeMap.put((EnumMap<XWPFBorderType, STBorder.Enum>) XWPFBorderType.THIN_THICK_MEDIUM_GAP, (XWPFBorderType) STBorder.THIN_THICK_MEDIUM_GAP);
        xwpfBorderTypeMap.put((EnumMap<XWPFBorderType, STBorder.Enum>) XWPFBorderType.THICK_THIN_MEDIUM_GAP, (XWPFBorderType) STBorder.THICK_THIN_MEDIUM_GAP);
        xwpfBorderTypeMap.put((EnumMap<XWPFBorderType, STBorder.Enum>) XWPFBorderType.THIN_THICK_THIN_MEDIUM_GAP, (XWPFBorderType) STBorder.THIN_THICK_THIN_MEDIUM_GAP);
        xwpfBorderTypeMap.put((EnumMap<XWPFBorderType, STBorder.Enum>) XWPFBorderType.THIN_THICK_LARGE_GAP, (XWPFBorderType) STBorder.THIN_THICK_LARGE_GAP);
        xwpfBorderTypeMap.put((EnumMap<XWPFBorderType, STBorder.Enum>) XWPFBorderType.THICK_THIN_LARGE_GAP, (XWPFBorderType) STBorder.THICK_THIN_LARGE_GAP);
        xwpfBorderTypeMap.put((EnumMap<XWPFBorderType, STBorder.Enum>) XWPFBorderType.THIN_THICK_THIN_LARGE_GAP, (XWPFBorderType) STBorder.THIN_THICK_THIN_LARGE_GAP);
        xwpfBorderTypeMap.put((EnumMap<XWPFBorderType, STBorder.Enum>) XWPFBorderType.WAVE, (XWPFBorderType) STBorder.WAVE);
        xwpfBorderTypeMap.put((EnumMap<XWPFBorderType, STBorder.Enum>) XWPFBorderType.DOUBLE_WAVE, (XWPFBorderType) STBorder.DOUBLE_WAVE);
        xwpfBorderTypeMap.put((EnumMap<XWPFBorderType, STBorder.Enum>) XWPFBorderType.DASH_SMALL_GAP, (XWPFBorderType) STBorder.DASH_SMALL_GAP);
        xwpfBorderTypeMap.put((EnumMap<XWPFBorderType, STBorder.Enum>) XWPFBorderType.DASH_DOT_STROKED, (XWPFBorderType) STBorder.DASH_DOT_STROKED);
        xwpfBorderTypeMap.put((EnumMap<XWPFBorderType, STBorder.Enum>) XWPFBorderType.THREE_D_EMBOSS, (XWPFBorderType) STBorder.THREE_D_EMBOSS);
        xwpfBorderTypeMap.put((EnumMap<XWPFBorderType, STBorder.Enum>) XWPFBorderType.THREE_D_ENGRAVE, (XWPFBorderType) STBorder.THREE_D_ENGRAVE);
        xwpfBorderTypeMap.put((EnumMap<XWPFBorderType, STBorder.Enum>) XWPFBorderType.OUTSET, (XWPFBorderType) STBorder.OUTSET);
        xwpfBorderTypeMap.put((EnumMap<XWPFBorderType, STBorder.Enum>) XWPFBorderType.INSET, (XWPFBorderType) STBorder.INSET);
        stBorderTypeMap = new HashMap<>();
        stBorderTypeMap.put(1, XWPFBorderType.NIL);
        stBorderTypeMap.put(2, XWPFBorderType.NONE);
        stBorderTypeMap.put(3, XWPFBorderType.SINGLE);
        stBorderTypeMap.put(4, XWPFBorderType.THICK);
        stBorderTypeMap.put(5, XWPFBorderType.DOUBLE);
        stBorderTypeMap.put(6, XWPFBorderType.DOTTED);
        stBorderTypeMap.put(7, XWPFBorderType.DASHED);
        stBorderTypeMap.put(8, XWPFBorderType.DOT_DASH);
        stBorderTypeMap.put(9, XWPFBorderType.DOT_DOT_DASH);
        stBorderTypeMap.put(10, XWPFBorderType.TRIPLE);
        stBorderTypeMap.put(11, XWPFBorderType.THIN_THICK_SMALL_GAP);
        stBorderTypeMap.put(12, XWPFBorderType.THICK_THIN_SMALL_GAP);
        stBorderTypeMap.put(13, XWPFBorderType.THIN_THICK_THIN_SMALL_GAP);
        stBorderTypeMap.put(14, XWPFBorderType.THIN_THICK_MEDIUM_GAP);
        stBorderTypeMap.put(15, XWPFBorderType.THICK_THIN_MEDIUM_GAP);
        stBorderTypeMap.put(16, XWPFBorderType.THIN_THICK_THIN_MEDIUM_GAP);
        stBorderTypeMap.put(17, XWPFBorderType.THIN_THICK_LARGE_GAP);
        stBorderTypeMap.put(18, XWPFBorderType.THICK_THIN_LARGE_GAP);
        stBorderTypeMap.put(19, XWPFBorderType.THIN_THICK_THIN_LARGE_GAP);
        stBorderTypeMap.put(20, XWPFBorderType.WAVE);
        stBorderTypeMap.put(21, XWPFBorderType.DOUBLE_WAVE);
        stBorderTypeMap.put(22, XWPFBorderType.DASH_SMALL_GAP);
        stBorderTypeMap.put(23, XWPFBorderType.DASH_DOT_STROKED);
        stBorderTypeMap.put(24, XWPFBorderType.THREE_D_EMBOSS);
        stBorderTypeMap.put(25, XWPFBorderType.THREE_D_ENGRAVE);
        stBorderTypeMap.put(26, XWPFBorderType.OUTSET);
        stBorderTypeMap.put(27, XWPFBorderType.INSET);
    }

    public XWPFTable(CTTbl table, IBody part, int row, int col) {
        this(table, part);
        for (int i = 0; i < row; i++) {
            XWPFTableRow tabRow = getRow(i) == null ? createRow() : getRow(i);
            for (int k = 0; k < col; k++) {
                if (tabRow.getCell(k) == null) {
                    tabRow.createCell();
                }
            }
        }
    }

    public XWPFTable(CTTbl table, IBody part) {
        this(table, part, true);
    }

    public XWPFTable(CTTbl table, IBody part, boolean initRow) {
        this.text = new StringBuilder(64);
        this.tableRows = new ArrayList();
        this.part = part;
        this.ctTbl = table;
        if (initRow && table.sizeOfTrArray() == 0) {
            createEmptyTable(table);
        }
        for (CTRow row : table.getTrList()) {
            StringBuilder rowText = new StringBuilder();
            XWPFTableRow tabRow = new XWPFTableRow(row, this);
            this.tableRows.add(tabRow);
            for (CTTc cell : row.getTcList()) {
                for (CTP ctp : cell.getPList()) {
                    XWPFParagraph p = new XWPFParagraph(ctp, part);
                    if (rowText.length() > 0) {
                        rowText.append('\t');
                    }
                    rowText.append(p.getText());
                }
            }
            if (rowText.length() > 0) {
                this.text.append((CharSequence) rowText);
                this.text.append('\n');
            }
        }
    }

    private void createEmptyTable(CTTbl table) {
        table.addNewTr().addNewTc().addNewP();
        CTTblPr tblpro = table.addNewTblPr();
        tblpro.addNewTblW().setW(BigInteger.valueOf(0L));
        tblpro.getTblW().setType(STTblWidth.AUTO);
        CTTblBorders borders = tblpro.addNewTblBorders();
        borders.addNewBottom().setVal(STBorder.SINGLE);
        borders.addNewInsideH().setVal(STBorder.SINGLE);
        borders.addNewInsideV().setVal(STBorder.SINGLE);
        borders.addNewLeft().setVal(STBorder.SINGLE);
        borders.addNewRight().setVal(STBorder.SINGLE);
        borders.addNewTop().setVal(STBorder.SINGLE);
    }

    @Internal
    public CTTbl getCTTbl() {
        return this.ctTbl;
    }

    public String getText() {
        return this.text.toString();
    }

    public void addNewCol() {
        if (this.tableRows.isEmpty()) {
            createRow();
        }
        for (XWPFTableRow tableRow : this.tableRows) {
            tableRow.createCell();
        }
    }

    public XWPFTableRow createRow() {
        int sizeCol = this.ctTbl.sizeOfTrArray() > 0 ? this.ctTbl.getTrArray(0).sizeOfTcArray() : 0;
        XWPFTableRow tabRow = new XWPFTableRow(this.ctTbl.addNewTr(), this);
        addColumn(tabRow, sizeCol);
        this.tableRows.add(tabRow);
        return tabRow;
    }

    public XWPFTableRow getRow(int pos) {
        if (pos >= 0 && pos < this.ctTbl.sizeOfTrArray()) {
            return getRows().get(pos);
        }
        return null;
    }

    public int getWidth() {
        CTTblPr tblPr = getTblPr(false);
        if (tblPr == null || !tblPr.isSetTblW()) {
            return -1;
        }
        return (int) Units.toDXA(POIXMLUnits.parseLength(tblPr.getTblW().xgetW()));
    }

    public void setWidth(int width) {
        CTTblPr tblPr = getTblPr();
        CTTblWidth tblWidth = tblPr.isSetTblW() ? tblPr.getTblW() : tblPr.addNewTblW();
        tblWidth.setW(BigInteger.valueOf(width));
        tblWidth.setType(STTblWidth.DXA);
    }

    public int getNumberOfRows() {
        return this.ctTbl.sizeOfTrArray();
    }

    public int getIndent() {
        CTTblPr tblPr = getTblPr(false);
        if (tblPr.isSetTblInd()) {
            STTblWidth.Enum typeValue = tblPr.getTblInd().getType();
            if (typeValue == null) {
                typeValue = STTblWidth.DXA;
            }
            switch (typeValue.intValue()) {
                case 1:
                    return 0;
                case 2:
                case 4:
                    return 0;
                case 3:
                    return (int) Units.toDXA(POIXMLUnits.parseLength(tblPr.getTblInd().xgetW()));
            }
        }
        return 0;
    }

    public void setIndent(int indent) {
        CTTblPr tblPr = getTblPr();
        CTTblWidth tblInd = tblPr.isSetTblInd() ? tblPr.getTblInd() : tblPr.addNewTblInd();
        tblInd.setW(BigInteger.valueOf(indent));
        tblInd.setType(STTblWidth.DXA);
    }

    public boolean hasIndent() {
        CTTblPr tblPr = getTblPr(false);
        return (!tblPr.isSetTblInd() || tblPr.getTblInd().getType() == STTblWidth.PCT || tblPr.getTblInd().getType() == STTblWidth.AUTO) ? false : true;
    }

    public void removeIndent() {
        CTTblPr tPr = getTblPr(false);
        if (tPr != null && tPr.isSetTblInd()) {
            tPr.unsetTblInd();
        }
    }

    private CTTblPr getTblPr() {
        return getTblPr(true);
    }

    private CTTblPr getTblPr(boolean force) {
        if (this.ctTbl.getTblPr() != null) {
            return this.ctTbl.getTblPr();
        }
        if (force) {
            return this.ctTbl.addNewTblPr();
        }
        return null;
    }

    private CTTblBorders getTblBorders(boolean force) {
        CTTblPr tblPr = getTblPr(force);
        if (tblPr == null) {
            return null;
        }
        if (tblPr.isSetTblBorders()) {
            return tblPr.getTblBorders();
        }
        if (!force) {
            return null;
        }
        return tblPr.addNewTblBorders();
    }

    private CTBorder getTblBorder(boolean force, Border border) {
        Function<CTTblBorders, Boolean> isSet;
        Function<CTTblBorders, CTBorder> get;
        Function<CTTblBorders, CTBorder> addNew;
        switch (border) {
            case INSIDE_V:
                isSet = new XWPFTable$$ExternalSyntheticLambda12();
                get = new Function() { // from class: org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda27
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return ((CTTblBorders) obj).getInsideV();
                    }
                };
                addNew = new Function() { // from class: org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda28
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return ((CTTblBorders) obj).addNewInsideV();
                    }
                };
                break;
            case INSIDE_H:
                isSet = new XWPFTable$$ExternalSyntheticLambda7();
                get = new Function() { // from class: org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda29
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return ((CTTblBorders) obj).getInsideH();
                    }
                };
                addNew = new Function() { // from class: org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda30
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return ((CTTblBorders) obj).addNewInsideH();
                    }
                };
                break;
            case LEFT:
                isSet = new XWPFTable$$ExternalSyntheticLambda14();
                get = new Function() { // from class: org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda31
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return ((CTTblBorders) obj).getLeft();
                    }
                };
                addNew = new Function() { // from class: org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda32
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return ((CTTblBorders) obj).addNewLeft();
                    }
                };
                break;
            case TOP:
                isSet = new XWPFTable$$ExternalSyntheticLambda16();
                get = new Function() { // from class: org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda20
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return ((CTTblBorders) obj).getTop();
                    }
                };
                addNew = new Function() { // from class: org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda21
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return ((CTTblBorders) obj).addNewTop();
                    }
                };
                break;
            case RIGHT:
                isSet = new XWPFTable$$ExternalSyntheticLambda18();
                get = new Function() { // from class: org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda23
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return ((CTTblBorders) obj).getRight();
                    }
                };
                addNew = new Function() { // from class: org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda24
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return ((CTTblBorders) obj).addNewRight();
                    }
                };
                break;
            case BOTTOM:
                isSet = new XWPFTable$$ExternalSyntheticLambda8();
                get = new Function() { // from class: org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda25
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return ((CTTblBorders) obj).getBottom();
                    }
                };
                addNew = new Function() { // from class: org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda26
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return ((CTTblBorders) obj).addNewBottom();
                    }
                };
                break;
            default:
                return null;
        }
        CTTblBorders ctb = getTblBorders(force);
        if (ctb == null) {
            return null;
        }
        if (isSet.apply(ctb).booleanValue()) {
            return get.apply(ctb);
        }
        if (force) {
            return addNew.apply(ctb);
        }
        return null;
    }

    public TableRowAlign getTableAlignment() {
        CTTblPr tPr = getTblPr(false);
        if (tPr != null && tPr.isSetJc()) {
            return TableRowAlign.valueOf(tPr.getJc().getVal().intValue());
        }
        return null;
    }

    public void setTableAlignment(TableRowAlign tra) {
        CTTblPr tPr = getTblPr(true);
        CTJcTable jc = tPr.isSetJc() ? tPr.getJc() : tPr.addNewJc();
        jc.setVal(STJcTable.Enum.forInt(tra.getValue()));
    }

    public void removeTableAlignment() {
        CTTblPr tPr = getTblPr(false);
        if (tPr != null && tPr.isSetJc()) {
            tPr.unsetJc();
        }
    }

    private void addColumn(XWPFTableRow tabRow, int sizeCol) {
        if (sizeCol > 0) {
            for (int i = 0; i < sizeCol; i++) {
                tabRow.createCell();
            }
        }
    }

    public String getStyleID() {
        CTString styleStr;
        CTTblPr tblPr = getTblPr(false);
        if (tblPr == null || (styleStr = tblPr.getTblStyle()) == null) {
            return null;
        }
        String styleId = styleStr.getVal();
        return styleId;
    }

    public void setStyleID(String styleName) {
        CTTblPr tblPr = getTblPr();
        CTString styleStr = tblPr.getTblStyle();
        if (styleStr == null) {
            styleStr = tblPr.addNewTblStyle();
        }
        styleStr.setVal(styleName);
    }

    public XWPFBorderType getInsideHBorderType() {
        return getBorderType(Border.INSIDE_H);
    }

    public int getInsideHBorderSize() {
        return getBorderSize(Border.INSIDE_H);
    }

    public int getInsideHBorderSpace() {
        return getBorderSpace(Border.INSIDE_H);
    }

    public String getInsideHBorderColor() {
        return getBorderColor(Border.INSIDE_H);
    }

    public XWPFBorderType getInsideVBorderType() {
        return getBorderType(Border.INSIDE_V);
    }

    public int getInsideVBorderSize() {
        return getBorderSize(Border.INSIDE_V);
    }

    public int getInsideVBorderSpace() {
        return getBorderSpace(Border.INSIDE_V);
    }

    public String getInsideVBorderColor() {
        return getBorderColor(Border.INSIDE_V);
    }

    public XWPFBorderType getTopBorderType() {
        return getBorderType(Border.TOP);
    }

    public int getTopBorderSize() {
        return getBorderSize(Border.TOP);
    }

    public int getTopBorderSpace() {
        return getBorderSpace(Border.TOP);
    }

    public String getTopBorderColor() {
        return getBorderColor(Border.TOP);
    }

    public XWPFBorderType getBottomBorderType() {
        return getBorderType(Border.BOTTOM);
    }

    public int getBottomBorderSize() {
        return getBorderSize(Border.BOTTOM);
    }

    public int getBottomBorderSpace() {
        return getBorderSpace(Border.BOTTOM);
    }

    public String getBottomBorderColor() {
        return getBorderColor(Border.BOTTOM);
    }

    public XWPFBorderType getLeftBorderType() {
        return getBorderType(Border.LEFT);
    }

    public int getLeftBorderSize() {
        return getBorderSize(Border.LEFT);
    }

    public int getLeftBorderSpace() {
        return getBorderSpace(Border.LEFT);
    }

    public String getLeftBorderColor() {
        return getBorderColor(Border.LEFT);
    }

    public XWPFBorderType getRightBorderType() {
        return getBorderType(Border.RIGHT);
    }

    public int getRightBorderSize() {
        return getBorderSize(Border.RIGHT);
    }

    public int getRightBorderSpace() {
        return getBorderSpace(Border.RIGHT);
    }

    public String getRightBorderColor() {
        return getBorderColor(Border.RIGHT);
    }

    private XWPFBorderType getBorderType(Border border) {
        CTBorder b = getTblBorder(false, border);
        if (b != null) {
            return stBorderTypeMap.get(Integer.valueOf(b.getVal().intValue()));
        }
        return null;
    }

    private int getBorderSize(Border border) {
        CTBorder b = getTblBorder(false, border);
        if (b == null || !b.isSetSz()) {
            return -1;
        }
        return b.getSz().intValue();
    }

    private int getBorderSpace(Border border) {
        CTBorder b = getTblBorder(false, border);
        if (b == null || !b.isSetSpace()) {
            return -1;
        }
        return b.getSpace().intValue();
    }

    private String getBorderColor(Border border) {
        CTBorder b = getTblBorder(false, border);
        if (b == null || !b.isSetColor()) {
            return null;
        }
        return b.xgetColor().getStringValue();
    }

    public int getRowBandSize() {
        CTTblPr tblPr = getTblPr(false);
        if (tblPr == null || !tblPr.isSetTblStyleRowBandSize()) {
            return 0;
        }
        CTDecimalNumber rowSize = tblPr.getTblStyleRowBandSize();
        int size = rowSize.getVal().intValue();
        return size;
    }

    public void setRowBandSize(int size) {
        CTTblPr tblPr = getTblPr();
        CTDecimalNumber rowSize = tblPr.isSetTblStyleRowBandSize() ? tblPr.getTblStyleRowBandSize() : tblPr.addNewTblStyleRowBandSize();
        rowSize.setVal(BigInteger.valueOf(size));
    }

    public int getColBandSize() {
        CTTblPr tblPr = getTblPr(false);
        if (tblPr == null || !tblPr.isSetTblStyleColBandSize()) {
            return 0;
        }
        CTDecimalNumber colSize = tblPr.getTblStyleColBandSize();
        int size = colSize.getVal().intValue();
        return size;
    }

    public void setColBandSize(int size) {
        CTTblPr tblPr = getTblPr();
        CTDecimalNumber colSize = tblPr.isSetTblStyleColBandSize() ? tblPr.getTblStyleColBandSize() : tblPr.addNewTblStyleColBandSize();
        colSize.setVal(BigInteger.valueOf(size));
    }

    public void setInsideHBorder(XWPFBorderType type, int size, int space, String rgbColor) {
        setBorder(Border.INSIDE_H, type, size, space, rgbColor);
    }

    public void setInsideVBorder(XWPFBorderType type, int size, int space, String rgbColor) {
        setBorder(Border.INSIDE_V, type, size, space, rgbColor);
    }

    public void setTopBorder(XWPFBorderType type, int size, int space, String rgbColor) {
        setBorder(Border.TOP, type, size, space, rgbColor);
    }

    public void setBottomBorder(XWPFBorderType type, int size, int space, String rgbColor) {
        setBorder(Border.BOTTOM, type, size, space, rgbColor);
    }

    public void setLeftBorder(XWPFBorderType type, int size, int space, String rgbColor) {
        setBorder(Border.LEFT, type, size, space, rgbColor);
    }

    public void setRightBorder(XWPFBorderType type, int size, int space, String rgbColor) {
        setBorder(Border.RIGHT, type, size, space, rgbColor);
    }

    private void setBorder(Border border, XWPFBorderType type, int size, int space, String rgbColor) {
        CTBorder b = getTblBorder(true, border);
        if (b == null) {
            throw new AssertionError();
        }
        b.setVal(xwpfBorderTypeMap.get(type));
        b.setSz(BigInteger.valueOf(size));
        b.setSpace(BigInteger.valueOf(space));
        b.setColor(rgbColor);
    }

    public void removeInsideHBorder() {
        removeBorder(Border.INSIDE_H);
    }

    public void removeInsideVBorder() {
        removeBorder(Border.INSIDE_V);
    }

    public void removeTopBorder() {
        removeBorder(Border.TOP);
    }

    public void removeBottomBorder() {
        removeBorder(Border.BOTTOM);
    }

    public void removeLeftBorder() {
        removeBorder(Border.LEFT);
    }

    public void removeRightBorder() {
        removeBorder(Border.RIGHT);
    }

    public void removeBorders() {
        CTTblPr pr = getTblPr(false);
        if (pr != null && pr.isSetTblBorders()) {
            pr.unsetTblBorders();
        }
    }

    private void removeBorder(Border border) {
        Function<CTTblBorders, Boolean> isSet;
        Consumer<CTTblBorders> unSet;
        switch (border) {
            case INSIDE_V:
                isSet = new XWPFTable$$ExternalSyntheticLambda12();
                unSet = new Consumer() { // from class: org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda13
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ((CTTblBorders) obj).unsetInsideV();
                    }
                };
                break;
            case INSIDE_H:
                isSet = new XWPFTable$$ExternalSyntheticLambda7();
                unSet = new Consumer() { // from class: org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda10
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ((CTTblBorders) obj).unsetInsideH();
                    }
                };
                break;
            case LEFT:
                isSet = new XWPFTable$$ExternalSyntheticLambda14();
                unSet = new Consumer() { // from class: org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda15
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ((CTTblBorders) obj).unsetLeft();
                    }
                };
                break;
            case TOP:
                isSet = new XWPFTable$$ExternalSyntheticLambda16();
                unSet = new Consumer() { // from class: org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda17
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ((CTTblBorders) obj).unsetTop();
                    }
                };
                break;
            case RIGHT:
                isSet = new XWPFTable$$ExternalSyntheticLambda18();
                unSet = new Consumer() { // from class: org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda19
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ((CTTblBorders) obj).unsetRight();
                    }
                };
                break;
            case BOTTOM:
                isSet = new XWPFTable$$ExternalSyntheticLambda8();
                unSet = new Consumer() { // from class: org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda9
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ((CTTblBorders) obj).unsetBottom();
                    }
                };
                break;
            default:
                return;
        }
        CTTblBorders tbl = getTblBorders(false);
        if (tbl != null && isSet.apply(tbl).booleanValue()) {
            unSet.accept(tbl);
            cleanupTblBorders();
        }
    }

    private void cleanupTblBorders() {
        CTTblPr pr = getTblPr(false);
        if (pr != null && pr.isSetTblBorders()) {
            CTTblBorders b = pr.getTblBorders();
            if (!b.isSetInsideH() && !b.isSetInsideV() && !b.isSetTop() && !b.isSetBottom() && !b.isSetLeft() && !b.isSetRight()) {
                pr.unsetTblBorders();
            }
        }
    }

    public int getCellMarginTop() {
        return getCellMargin(new XWPFTable$$ExternalSyntheticLambda38());
    }

    public int getCellMarginLeft() {
        return getCellMargin(new XWPFTable$$ExternalSyntheticLambda3());
    }

    public int getCellMarginBottom() {
        return getCellMargin(new XWPFTable$$ExternalSyntheticLambda0());
    }

    public int getCellMarginRight() {
        return getCellMargin(new XWPFTable$$ExternalSyntheticLambda35());
    }

    private int getCellMargin(Function<CTTblCellMar, CTTblWidth> margin) {
        CTTblCellMar tcm;
        CTTblWidth tw;
        CTTblPr tblPr = getTblPr(false);
        if (tblPr == null || (tcm = tblPr.getTblCellMar()) == null || (tw = margin.apply(tcm)) == null) {
            return 0;
        }
        return (int) Units.toDXA(POIXMLUnits.parseLength(tw.xgetW()));
    }

    public void setCellMargins(int top, int left, int bottom, int right) {
        CTTblPr tblPr = getTblPr();
        CTTblCellMar tcm = tblPr.isSetTblCellMar() ? tblPr.getTblCellMar() : tblPr.addNewTblCellMar();
        setCellMargin(tcm, new Function() { // from class: org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda11
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Boolean.valueOf(((CTTblCellMar) obj).isSetTop());
            }
        }, new XWPFTable$$ExternalSyntheticLambda38(), new Function() { // from class: org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda39
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((CTTblCellMar) obj).addNewTop();
            }
        }, new Consumer() { // from class: org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((CTTblCellMar) obj).unsetTop();
            }
        }, top);
        setCellMargin(tcm, new Function() { // from class: org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda2
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Boolean.valueOf(((CTTblCellMar) obj).isSetLeft());
            }
        }, new XWPFTable$$ExternalSyntheticLambda3(), new Function() { // from class: org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda4
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((CTTblCellMar) obj).addNewLeft();
            }
        }, new Consumer() { // from class: org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda5
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((CTTblCellMar) obj).unsetLeft();
            }
        }, left);
        setCellMargin(tcm, new Function() { // from class: org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda6
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Boolean.valueOf(((CTTblCellMar) obj).isSetBottom());
            }
        }, new XWPFTable$$ExternalSyntheticLambda0(), new Function() { // from class: org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda22
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((CTTblCellMar) obj).addNewBottom();
            }
        }, new Consumer() { // from class: org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda33
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((CTTblCellMar) obj).unsetBottom();
            }
        }, bottom);
        setCellMargin(tcm, new Function() { // from class: org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda34
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Boolean.valueOf(((CTTblCellMar) obj).isSetRight());
            }
        }, new XWPFTable$$ExternalSyntheticLambda35(), new Function() { // from class: org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda36
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((CTTblCellMar) obj).addNewRight();
            }
        }, new Consumer() { // from class: org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda37
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((CTTblCellMar) obj).unsetRight();
            }
        }, right);
    }

    private void setCellMargin(CTTblCellMar tcm, Function<CTTblCellMar, Boolean> isSet, Function<CTTblCellMar, CTTblWidth> get, Function<CTTblCellMar, CTTblWidth> addNew, Consumer<CTTblCellMar> unSet, int margin) {
        if (margin == 0) {
            if (isSet.apply(tcm).booleanValue()) {
                unSet.accept(tcm);
            }
        } else {
            CTTblWidth tw = (isSet.apply(tcm).booleanValue() ? get : addNew).apply(tcm);
            tw.setType(STTblWidth.DXA);
            tw.setW(BigInteger.valueOf(margin));
        }
    }

    public void addRow(XWPFTableRow row) {
        this.ctTbl.addNewTr();
        this.ctTbl.setTrArray(getNumberOfRows() - 1, row.getCtRow());
        this.tableRows.add(row);
    }

    public boolean addRow(XWPFTableRow row, int pos) {
        if (pos >= 0 && pos <= this.tableRows.size()) {
            this.ctTbl.insertNewTr(pos);
            this.ctTbl.setTrArray(pos, row.getCtRow());
            this.tableRows.add(pos, row);
            return true;
        }
        return false;
    }

    public XWPFTableRow insertNewTableRow(int pos) {
        if (pos >= 0 && pos <= this.tableRows.size()) {
            CTRow row = this.ctTbl.insertNewTr(pos);
            XWPFTableRow tableRow = new XWPFTableRow(row, this);
            this.tableRows.add(pos, tableRow);
            return tableRow;
        }
        return null;
    }

    public boolean removeRow(int pos) throws IndexOutOfBoundsException {
        if (pos >= 0 && pos < this.tableRows.size()) {
            if (this.ctTbl.sizeOfTrArray() > 0) {
                this.ctTbl.removeTr(pos);
            }
            this.tableRows.remove(pos);
            return true;
        }
        return false;
    }

    public List<XWPFTableRow> getRows() {
        return Collections.unmodifiableList(this.tableRows);
    }

    @Override // org.apache.poi.xwpf.usermodel.IBodyElement
    public BodyElementType getElementType() {
        return BodyElementType.TABLE;
    }

    @Override // org.apache.poi.xwpf.usermodel.IBodyElement
    public IBody getBody() {
        return this.part;
    }

    @Override // org.apache.poi.xwpf.usermodel.IBodyElement, org.apache.poi.xwpf.usermodel.IRunBody
    public POIXMLDocumentPart getPart() {
        if (this.part != null) {
            return this.part.getPart();
        }
        return null;
    }

    @Override // org.apache.poi.xwpf.usermodel.IBodyElement
    public BodyType getPartType() {
        return this.part.getPartType();
    }

    public XWPFTableRow getRow(CTRow row) {
        for (int i = 0; i < getRows().size(); i++) {
            if (getRows().get(i).getCtRow() == row) {
                return getRow(i);
            }
        }
        return null;
    }

    public double getWidthDecimal() {
        CTTblPr pr = getTblPr(false);
        if (pr == null) {
            return 0.0d;
        }
        return getWidthDecimal(pr.getTblW());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static double getWidthDecimal(CTTblWidth ctWidth) {
        STTblWidth.Enum typeValue = ctWidth.getType();
        if (typeValue == STTblWidth.DXA || typeValue == STTblWidth.AUTO || typeValue == STTblWidth.NIL) {
            double result = Units.toDXA(POIXMLUnits.parseLength(ctWidth.xgetW())) + 0.0d;
            return result;
        }
        if (typeValue != STTblWidth.PCT) {
            return 0.0d;
        }
        double result2 = Units.toDXA(POIXMLUnits.parseLength(ctWidth.xgetW())) / 50.0d;
        return result2;
    }

    public TableWidthType getWidthType() {
        CTTblPr pr = getTblPr(false);
        return (pr == null || !getTblPr().isSetTblW()) ? TableWidthType.AUTO : getWidthType(pr.getTblW());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static TableWidthType getWidthType(CTTblWidth ctWidth) {
        STTblWidth.Enum typeValue = ctWidth.getType();
        if (typeValue == null) {
            typeValue = STTblWidth.NIL;
            ctWidth.setType(typeValue);
        }
        switch (typeValue.intValue()) {
            case 1:
                return TableWidthType.NIL;
            case 2:
                return TableWidthType.PCT;
            case 3:
                return TableWidthType.DXA;
            default:
                return TableWidthType.AUTO;
        }
    }

    public void setWidth(String widthValue) {
        setWidthValue(widthValue, getTblPr().getTblW());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void setWidthValue(String widthValue, CTTblWidth ctWidth) {
        if (!widthValue.matches(REGEX_WIDTH_VALUE)) {
            throw new IllegalStateException("Table width value \"" + widthValue + "\" must match regular expression \"" + REGEX_WIDTH_VALUE + "\".");
        }
        if (widthValue.matches(DebugKt.DEBUG_PROPERTY_VALUE_AUTO)) {
            ctWidth.setType(STTblWidth.AUTO);
            ctWidth.setW(BigInteger.ZERO);
        } else if (widthValue.matches(REGEX_PERCENTAGE)) {
            setWidthPercentage(ctWidth, widthValue);
        } else {
            ctWidth.setW(new BigInteger(widthValue));
            ctWidth.setType(STTblWidth.DXA);
        }
    }

    protected static void setWidthPercentage(CTTblWidth ctWidth, String widthValue) {
        ctWidth.setType(STTblWidth.PCT);
        if (!widthValue.matches(REGEX_PERCENTAGE)) {
            if (widthValue.matches("[0-9]+")) {
                ctWidth.setW(new BigInteger(widthValue));
                return;
            }
            throw new IllegalStateException("setWidthPercentage(): Width value must be a percentage (\"33.3%\" or an integer, was \"" + widthValue + "\"");
        }
        String numberPart = widthValue.substring(0, widthValue.length() - 1);
        double percentage = Double.parseDouble(numberPart) * 50.0d;
        long intValue = Math.round(percentage);
        ctWidth.setW(BigInteger.valueOf(intValue));
    }

    public void setWidthType(TableWidthType widthType) {
        setWidthType(widthType, getTblPr().getTblW());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void setWidthType(TableWidthType widthType, CTTblWidth ctWidth) {
        TableWidthType currentType = getWidthType(ctWidth);
        if (!currentType.equals(widthType)) {
            STTblWidth.Enum stWidthType = widthType.getStWidthType();
            ctWidth.setType(stWidthType);
            if (stWidthType.intValue() == 2) {
                setWidthPercentage(ctWidth, DEFAULT_PERCENTAGE_WIDTH);
            } else {
                ctWidth.setW(BigInteger.ZERO);
            }
        }
    }
}
