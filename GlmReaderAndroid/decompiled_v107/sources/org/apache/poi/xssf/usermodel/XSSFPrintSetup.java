package org.apache.poi.xssf.usermodel;

import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ss.usermodel.PageOrder;
import org.apache.poi.ss.usermodel.PaperSize;
import org.apache.poi.ss.usermodel.PrintCellComments;
import org.apache.poi.ss.usermodel.PrintOrientation;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageMargins;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageSetup;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STCellComments;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STOrientation;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STPageOrder;

/* loaded from: classes10.dex */
public class XSSFPrintSetup implements PrintSetup {
    private CTWorksheet ctWorksheet;
    private CTPageMargins pageMargins;
    private CTPageSetup pageSetup;

    /* JADX INFO: Access modifiers changed from: protected */
    public XSSFPrintSetup(CTWorksheet worksheet) {
        this.ctWorksheet = worksheet;
        if (this.ctWorksheet.isSetPageSetup()) {
            this.pageSetup = this.ctWorksheet.getPageSetup();
        } else {
            this.pageSetup = this.ctWorksheet.addNewPageSetup();
        }
        if (this.ctWorksheet.isSetPageMargins()) {
            this.pageMargins = this.ctWorksheet.getPageMargins();
        } else {
            this.pageMargins = this.ctWorksheet.addNewPageMargins();
        }
    }

    @Override // org.apache.poi.ss.usermodel.PrintSetup
    public void setPaperSize(short size) {
        this.pageSetup.setPaperSize(size);
    }

    public void setPaperSize(PaperSize size) {
        setPaperSize((short) (size.ordinal() + 1));
    }

    @Override // org.apache.poi.ss.usermodel.PrintSetup
    public void setScale(short scale) {
        if (scale < 10 || scale > 400) {
            throw new POIXMLException("Scale value not accepted: you must choose a value between 10 and 400.");
        }
        this.pageSetup.setScale(scale);
    }

    @Override // org.apache.poi.ss.usermodel.PrintSetup
    public void setPageStart(short start) {
        this.pageSetup.setFirstPageNumber(start);
    }

    @Override // org.apache.poi.ss.usermodel.PrintSetup
    public void setFitWidth(short width) {
        this.pageSetup.setFitToWidth(width);
    }

    @Override // org.apache.poi.ss.usermodel.PrintSetup
    public void setFitHeight(short height) {
        this.pageSetup.setFitToHeight(height);
    }

    @Override // org.apache.poi.ss.usermodel.PrintSetup
    public void setLeftToRight(boolean leftToRight) {
        if (leftToRight) {
            setPageOrder(PageOrder.OVER_THEN_DOWN);
        } else {
            setPageOrder(PageOrder.DOWN_THEN_OVER);
        }
    }

    @Override // org.apache.poi.ss.usermodel.PrintSetup
    public void setLandscape(boolean ls) {
        if (ls) {
            setOrientation(PrintOrientation.LANDSCAPE);
        } else {
            setOrientation(PrintOrientation.PORTRAIT);
        }
    }

    @Override // org.apache.poi.ss.usermodel.PrintSetup
    public void setValidSettings(boolean valid) {
        this.pageSetup.setUsePrinterDefaults(valid);
    }

    @Override // org.apache.poi.ss.usermodel.PrintSetup
    public void setNoColor(boolean mono) {
        this.pageSetup.setBlackAndWhite(mono);
    }

    @Override // org.apache.poi.ss.usermodel.PrintSetup
    public void setDraft(boolean d) {
        this.pageSetup.setDraft(d);
    }

    @Override // org.apache.poi.ss.usermodel.PrintSetup
    public void setNotes(boolean printNotes) {
        if (printNotes) {
            this.pageSetup.setCellComments(STCellComments.AS_DISPLAYED);
        }
    }

    @Override // org.apache.poi.ss.usermodel.PrintSetup
    public void setNoOrientation(boolean orientation) {
        if (orientation) {
            setOrientation(PrintOrientation.DEFAULT);
        }
    }

    @Override // org.apache.poi.ss.usermodel.PrintSetup
    public void setUsePage(boolean page) {
        this.pageSetup.setUseFirstPageNumber(page);
    }

    @Override // org.apache.poi.ss.usermodel.PrintSetup
    public void setHResolution(short resolution) {
        this.pageSetup.setHorizontalDpi(resolution);
    }

    @Override // org.apache.poi.ss.usermodel.PrintSetup
    public void setVResolution(short resolution) {
        this.pageSetup.setVerticalDpi(resolution);
    }

    @Override // org.apache.poi.ss.usermodel.PrintSetup
    public void setHeaderMargin(double headerMargin) {
        this.pageMargins.setHeader(headerMargin);
    }

    @Override // org.apache.poi.ss.usermodel.PrintSetup
    public void setFooterMargin(double footerMargin) {
        this.pageMargins.setFooter(footerMargin);
    }

    @Override // org.apache.poi.ss.usermodel.PrintSetup
    public void setCopies(short copies) {
        this.pageSetup.setCopies(copies);
    }

    public void setOrientation(PrintOrientation orientation) {
        STOrientation.Enum v = STOrientation.Enum.forInt(orientation.getValue());
        this.pageSetup.setOrientation(v);
    }

    public PrintOrientation getOrientation() {
        STOrientation.Enum val = this.pageSetup.getOrientation();
        return val == null ? PrintOrientation.DEFAULT : PrintOrientation.valueOf(val.intValue());
    }

    public PrintCellComments getCellComment() {
        STCellComments.Enum val = this.pageSetup.getCellComments();
        return val == null ? PrintCellComments.NONE : PrintCellComments.valueOf(val.intValue());
    }

    public void setPageOrder(PageOrder pageOrder) {
        STPageOrder.Enum v = STPageOrder.Enum.forInt(pageOrder.getValue());
        this.pageSetup.setPageOrder(v);
    }

    public PageOrder getPageOrder() {
        if (this.pageSetup.getPageOrder() == null) {
            return null;
        }
        return PageOrder.valueOf(this.pageSetup.getPageOrder().intValue());
    }

    @Override // org.apache.poi.ss.usermodel.PrintSetup
    public short getPaperSize() {
        return (short) this.pageSetup.getPaperSize();
    }

    public PaperSize getPaperSizeEnum() {
        return PaperSize.values()[getPaperSize() - 1];
    }

    @Override // org.apache.poi.ss.usermodel.PrintSetup
    public short getScale() {
        return (short) this.pageSetup.getScale();
    }

    @Override // org.apache.poi.ss.usermodel.PrintSetup
    public short getPageStart() {
        return (short) this.pageSetup.getFirstPageNumber();
    }

    @Override // org.apache.poi.ss.usermodel.PrintSetup
    public short getFitWidth() {
        return (short) this.pageSetup.getFitToWidth();
    }

    @Override // org.apache.poi.ss.usermodel.PrintSetup
    public short getFitHeight() {
        return (short) this.pageSetup.getFitToHeight();
    }

    @Override // org.apache.poi.ss.usermodel.PrintSetup
    public boolean getLeftToRight() {
        return getPageOrder() == PageOrder.OVER_THEN_DOWN;
    }

    @Override // org.apache.poi.ss.usermodel.PrintSetup
    public boolean getLandscape() {
        return getOrientation() == PrintOrientation.LANDSCAPE;
    }

    @Override // org.apache.poi.ss.usermodel.PrintSetup
    public boolean getValidSettings() {
        return this.pageSetup.getUsePrinterDefaults();
    }

    @Override // org.apache.poi.ss.usermodel.PrintSetup
    public boolean getNoColor() {
        return this.pageSetup.getBlackAndWhite();
    }

    @Override // org.apache.poi.ss.usermodel.PrintSetup
    public boolean getDraft() {
        return this.pageSetup.getDraft();
    }

    @Override // org.apache.poi.ss.usermodel.PrintSetup
    public boolean getNotes() {
        return getCellComment() == PrintCellComments.AS_DISPLAYED;
    }

    @Override // org.apache.poi.ss.usermodel.PrintSetup
    public boolean getNoOrientation() {
        return getOrientation() == PrintOrientation.DEFAULT;
    }

    @Override // org.apache.poi.ss.usermodel.PrintSetup
    public boolean getUsePage() {
        return this.pageSetup.getUseFirstPageNumber();
    }

    @Override // org.apache.poi.ss.usermodel.PrintSetup
    public short getHResolution() {
        return (short) this.pageSetup.getHorizontalDpi();
    }

    @Override // org.apache.poi.ss.usermodel.PrintSetup
    public short getVResolution() {
        return (short) this.pageSetup.getVerticalDpi();
    }

    @Override // org.apache.poi.ss.usermodel.PrintSetup
    public double getHeaderMargin() {
        return this.pageMargins.getHeader();
    }

    @Override // org.apache.poi.ss.usermodel.PrintSetup
    public double getFooterMargin() {
        return this.pageMargins.getFooter();
    }

    @Override // org.apache.poi.ss.usermodel.PrintSetup
    public short getCopies() {
        return (short) this.pageSetup.getCopies();
    }

    public void setTopMargin(double topMargin) {
        this.pageMargins.setTop(topMargin);
    }

    public double getTopMargin() {
        return this.pageMargins.getTop();
    }

    public void setBottomMargin(double bottomMargin) {
        this.pageMargins.setBottom(bottomMargin);
    }

    public double getBottomMargin() {
        return this.pageMargins.getBottom();
    }

    public void setLeftMargin(double leftMargin) {
        this.pageMargins.setLeft(leftMargin);
    }

    public double getLeftMargin() {
        return this.pageMargins.getLeft();
    }

    public void setRightMargin(double rightMargin) {
        this.pageMargins.setRight(rightMargin);
    }

    public double getRightMargin() {
        return this.pageMargins.getRight();
    }
}
