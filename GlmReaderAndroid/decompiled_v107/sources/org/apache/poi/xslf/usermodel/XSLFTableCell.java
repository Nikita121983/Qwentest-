package org.apache.poi.xslf.usermodel;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import org.apache.poi.sl.draw.DrawPaint;
import org.apache.poi.sl.usermodel.ColorStyle;
import org.apache.poi.sl.usermodel.PaintStyle;
import org.apache.poi.sl.usermodel.StrokeStyle;
import org.apache.poi.sl.usermodel.TableCell;
import org.apache.poi.sl.usermodel.TextShape;
import org.apache.poi.sl.usermodel.VerticalAlignment;
import org.apache.poi.util.Units;
import org.apache.poi.xddf.usermodel.XDDFLineProperties;
import org.apache.poi.xddf.usermodel.text.XDDFTextBody;
import org.apache.poi.xslf.usermodel.XSLFPropertiesDelegate;
import org.apache.poi.xslf.usermodel.XSLFTableStyle;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTFontReference;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineEndProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSolidColorFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTable;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableCell;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTablePartStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D;
import org.openxmlformats.schemas.drawingml.x2006.main.STCompoundLine;
import org.openxmlformats.schemas.drawingml.x2006.main.STLineCap;
import org.openxmlformats.schemas.drawingml.x2006.main.STLineEndLength;
import org.openxmlformats.schemas.drawingml.x2006.main.STLineEndType;
import org.openxmlformats.schemas.drawingml.x2006.main.STLineEndWidth;
import org.openxmlformats.schemas.drawingml.x2006.main.STPenAlignment;
import org.openxmlformats.schemas.drawingml.x2006.main.STPresetLineDashVal;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextAnchoringType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextVerticalType;

/* loaded from: classes10.dex */
public class XSLFTableCell extends XSLFTextShape implements TableCell<XSLFShape, XSLFTextParagraph> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private CTTableCellProperties _tcPr;
    private Rectangle2D anchor;
    private int col;
    private int row;
    private final XSLFTable table;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Type inference failed for: r0v0, types: [org.apache.poi.xslf.usermodel.XSLFSheet] */
    public XSLFTableCell(CTTableCell cell, XSLFTable table) {
        super(cell, table.getSheet());
        this.table = table;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.xslf.usermodel.XSLFTextShape
    public CTTextBody getTextBody(boolean create) {
        CTTableCell cell = getCell();
        CTTextBody txBody = cell.getTxBody();
        if (txBody == null && create) {
            XDDFTextBody body = new XDDFTextBody(this);
            cell.setTxBody(body.getXmlObject());
            return cell.getTxBody();
        }
        return txBody;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CTTableCell prototype() {
        CTTableCell cell = CTTableCell.Factory.newInstance();
        CTTableCellProperties pr = cell.addNewTcPr();
        pr.addNewLnL().addNewNoFill();
        pr.addNewLnR().addNewNoFill();
        pr.addNewLnT().addNewNoFill();
        pr.addNewLnB().addNewNoFill();
        return cell;
    }

    protected CTTableCellProperties getCellProperties(boolean create) {
        if (this._tcPr == null) {
            CTTableCell cell = getCell();
            this._tcPr = cell.getTcPr();
            if (this._tcPr == null && create) {
                this._tcPr = cell.addNewTcPr();
            }
        }
        return this._tcPr;
    }

    @Override // org.apache.poi.xslf.usermodel.XSLFTextShape
    public void setLeftInset(double margin) {
        CTTableCellProperties pr = getCellProperties(true);
        pr.setMarL(Integer.valueOf(Units.toEMU(margin)));
    }

    @Override // org.apache.poi.xslf.usermodel.XSLFTextShape
    public void setRightInset(double margin) {
        CTTableCellProperties pr = getCellProperties(true);
        pr.setMarR(Integer.valueOf(Units.toEMU(margin)));
    }

    @Override // org.apache.poi.xslf.usermodel.XSLFTextShape
    public void setTopInset(double margin) {
        CTTableCellProperties pr = getCellProperties(true);
        pr.setMarT(Integer.valueOf(Units.toEMU(margin)));
    }

    @Override // org.apache.poi.xslf.usermodel.XSLFTextShape
    public void setBottomInset(double margin) {
        CTTableCellProperties pr = getCellProperties(true);
        pr.setMarB(Integer.valueOf(Units.toEMU(margin)));
    }

    private CTLineProperties getCTLine(TableCell.BorderEdge edge, boolean create) {
        if (edge == null) {
            throw new IllegalArgumentException("BorderEdge needs to be specified.");
        }
        CTTableCellProperties pr = getCellProperties(create);
        if (pr == null) {
            return null;
        }
        switch (edge) {
            case bottom:
                if (pr.isSetLnB()) {
                    return pr.getLnB();
                }
                if (create) {
                    return pr.addNewLnB();
                }
                return null;
            case left:
                if (pr.isSetLnL()) {
                    return pr.getLnL();
                }
                if (create) {
                    return pr.addNewLnL();
                }
                return null;
            case top:
                if (pr.isSetLnT()) {
                    return pr.getLnT();
                }
                if (create) {
                    return pr.addNewLnT();
                }
                return null;
            case right:
                if (pr.isSetLnR()) {
                    return pr.getLnR();
                }
                if (create) {
                    return pr.addNewLnR();
                }
                return null;
            default:
                return null;
        }
    }

    public XDDFLineProperties getBorderProperties(TableCell.BorderEdge edge) {
        CTLineProperties props = getCTLine(edge, false);
        if (props == null) {
            return null;
        }
        return new XDDFLineProperties(props);
    }

    public void setBorderProperties(TableCell.BorderEdge edge, XDDFLineProperties properties) {
        CTLineProperties props = getCTLine(edge, true);
        if (props != null) {
            props.set(properties.getXmlObject().copy());
        }
    }

    @Override // org.apache.poi.sl.usermodel.TableCell
    public void removeBorder(TableCell.BorderEdge edge) {
        CTTableCellProperties pr = getCellProperties(false);
        if (pr == null) {
            return;
        }
        switch (edge) {
            case bottom:
                if (pr.isSetLnB()) {
                    pr.unsetLnB();
                    return;
                }
                return;
            case left:
                if (pr.isSetLnL()) {
                    pr.unsetLnL();
                    return;
                }
                return;
            case top:
                if (pr.isSetLnT()) {
                    pr.unsetLnT();
                    return;
                }
                return;
            case right:
                if (pr.isSetLnR()) {
                    pr.unsetLnR();
                    return;
                }
                return;
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override // org.apache.poi.sl.usermodel.TableCell
    public StrokeStyle getBorderStyle(final TableCell.BorderEdge edge) {
        final Double width = getBorderWidth(edge);
        if (width == null) {
            return null;
        }
        return new StrokeStyle() { // from class: org.apache.poi.xslf.usermodel.XSLFTableCell.1
            @Override // org.apache.poi.sl.usermodel.StrokeStyle
            public PaintStyle getPaint() {
                return DrawPaint.createSolidPaint(XSLFTableCell.this.getBorderColor(edge));
            }

            @Override // org.apache.poi.sl.usermodel.StrokeStyle
            public StrokeStyle.LineCap getLineCap() {
                return XSLFTableCell.this.getBorderCap(edge);
            }

            @Override // org.apache.poi.sl.usermodel.StrokeStyle
            public StrokeStyle.LineDash getLineDash() {
                return XSLFTableCell.this.getBorderDash(edge);
            }

            @Override // org.apache.poi.sl.usermodel.StrokeStyle
            public StrokeStyle.LineCompound getLineCompound() {
                return XSLFTableCell.this.getBorderCompound(edge);
            }

            @Override // org.apache.poi.sl.usermodel.StrokeStyle
            public double getLineWidth() {
                return width.doubleValue();
            }
        };
    }

    @Override // org.apache.poi.sl.usermodel.TableCell
    public void setBorderStyle(TableCell.BorderEdge edge, StrokeStyle style) {
        if (style == null) {
            throw new IllegalArgumentException("StrokeStyle needs to be specified.");
        }
        StrokeStyle.LineCap cap = style.getLineCap();
        if (cap != null) {
            setBorderCap(edge, cap);
        }
        StrokeStyle.LineCompound compound = style.getLineCompound();
        if (compound != null) {
            setBorderCompound(edge, compound);
        }
        StrokeStyle.LineDash dash = style.getLineDash();
        if (dash != null) {
            setBorderDash(edge, dash);
        }
        double width = style.getLineWidth();
        setBorderWidth(edge, width);
    }

    public Double getBorderWidth(TableCell.BorderEdge edge) {
        CTLineProperties ln = getCTLine(edge, false);
        if (ln == null || !ln.isSetW()) {
            return null;
        }
        return Double.valueOf(Units.toPoints(ln.getW()));
    }

    @Override // org.apache.poi.sl.usermodel.TableCell
    public void setBorderWidth(TableCell.BorderEdge edge, double width) {
        CTLineProperties ln = getCTLine(edge, true);
        if (ln == null) {
            return;
        }
        ln.setW(Units.toEMU(width));
    }

    private CTLineProperties setBorderDefaults(TableCell.BorderEdge edge) {
        CTLineProperties ln = getCTLine(edge, true);
        if (ln == null) {
            throw new IllegalStateException("CTLineProperties couldn't be initialized");
        }
        if (ln.isSetNoFill()) {
            ln.unsetNoFill();
        }
        if (!ln.isSetPrstDash()) {
            ln.addNewPrstDash().setVal(STPresetLineDashVal.SOLID);
        }
        if (!ln.isSetCmpd()) {
            ln.setCmpd(STCompoundLine.SNG);
        }
        if (!ln.isSetAlgn()) {
            ln.setAlgn(STPenAlignment.CTR);
        }
        if (!ln.isSetCap()) {
            ln.setCap(STLineCap.FLAT);
        }
        if (!ln.isSetRound()) {
            ln.addNewRound();
        }
        if (!ln.isSetHeadEnd()) {
            CTLineEndProperties hd = ln.addNewHeadEnd();
            hd.setType(STLineEndType.NONE);
            hd.setW(STLineEndWidth.MED);
            hd.setLen(STLineEndLength.MED);
        }
        if (!ln.isSetTailEnd()) {
            CTLineEndProperties tl = ln.addNewTailEnd();
            tl.setType(STLineEndType.NONE);
            tl.setW(STLineEndWidth.MED);
            tl.setLen(STLineEndLength.MED);
        }
        return ln;
    }

    /* JADX WARN: Type inference failed for: r3v0, types: [org.apache.poi.xslf.usermodel.XSLFSheet] */
    /* JADX WARN: Type inference failed for: r5v0, types: [org.apache.poi.xslf.usermodel.XSLFSheet] */
    @Override // org.apache.poi.sl.usermodel.TableCell
    public void setBorderColor(TableCell.BorderEdge edge, Color color) {
        if (color == null) {
            throw new IllegalArgumentException("Colors need to be specified.");
        }
        CTLineProperties ln = setBorderDefaults(edge);
        CTSolidColorFillProperties fill = ln.addNewSolidFill();
        XSLFColor c = new XSLFColor(fill, getSheet().getTheme(), fill.getSchemeClr(), getSheet());
        c.setColor(color);
    }

    /* JADX WARN: Type inference failed for: r3v0, types: [org.apache.poi.xslf.usermodel.XSLFSheet] */
    /* JADX WARN: Type inference failed for: r5v0, types: [org.apache.poi.xslf.usermodel.XSLFSheet] */
    public Color getBorderColor(TableCell.BorderEdge edge) {
        CTLineProperties ln = getCTLine(edge, false);
        if (ln == null || ln.isSetNoFill() || !ln.isSetSolidFill()) {
            return null;
        }
        CTSolidColorFillProperties fill = ln.getSolidFill();
        XSLFColor c = new XSLFColor(fill, getSheet().getTheme(), fill.getSchemeClr(), getSheet());
        return c.getColor();
    }

    public StrokeStyle.LineCompound getBorderCompound(TableCell.BorderEdge edge) {
        CTLineProperties ln = getCTLine(edge, false);
        if (ln == null || ln.isSetNoFill() || !ln.isSetSolidFill() || !ln.isSetCmpd()) {
            return null;
        }
        return StrokeStyle.LineCompound.fromOoxmlId(ln.getCmpd().intValue());
    }

    @Override // org.apache.poi.sl.usermodel.TableCell
    public void setBorderCompound(TableCell.BorderEdge edge, StrokeStyle.LineCompound compound) {
        if (compound == null) {
            throw new IllegalArgumentException("LineCompound need to be specified.");
        }
        CTLineProperties ln = setBorderDefaults(edge);
        ln.setCmpd(STCompoundLine.Enum.forInt(compound.ooxmlId));
    }

    public StrokeStyle.LineDash getBorderDash(TableCell.BorderEdge edge) {
        CTLineProperties ln = getCTLine(edge, false);
        if (ln == null || ln.isSetNoFill() || !ln.isSetSolidFill() || !ln.isSetPrstDash()) {
            return null;
        }
        return StrokeStyle.LineDash.fromOoxmlId(ln.getPrstDash().getVal().intValue());
    }

    @Override // org.apache.poi.sl.usermodel.TableCell
    public void setBorderDash(TableCell.BorderEdge edge, StrokeStyle.LineDash dash) {
        if (dash == null) {
            throw new IllegalArgumentException("LineDash need to be specified.");
        }
        CTLineProperties ln = setBorderDefaults(edge);
        if (!ln.isSetPrstDash()) {
            ln.addNewPrstDash();
        }
        ln.getPrstDash().setVal(STPresetLineDashVal.Enum.forInt(dash.ooxmlId));
    }

    public StrokeStyle.LineCap getBorderCap(TableCell.BorderEdge edge) {
        CTLineProperties ln = getCTLine(edge, false);
        if (ln == null || ln.isSetNoFill() || !ln.isSetSolidFill() || !ln.isSetCap()) {
            return null;
        }
        return StrokeStyle.LineCap.fromOoxmlId(ln.getCap().intValue());
    }

    public void setBorderCap(TableCell.BorderEdge edge, StrokeStyle.LineCap cap) {
        if (cap == null) {
            throw new IllegalArgumentException("LineCap need to be specified.");
        }
        CTLineProperties ln = setBorderDefaults(edge);
        ln.setCap(STLineCap.Enum.forInt(cap.ooxmlId));
    }

    /* JADX WARN: Type inference failed for: r3v0, types: [org.apache.poi.xslf.usermodel.XSLFSheet] */
    /* JADX WARN: Type inference failed for: r5v0, types: [org.apache.poi.xslf.usermodel.XSLFSheet] */
    @Override // org.apache.poi.xslf.usermodel.XSLFSimpleShape, org.apache.poi.sl.usermodel.SimpleShape
    public void setFillColor(Color color) {
        CTTableCellProperties spPr = getCellProperties(true);
        if (color == null) {
            if (spPr.isSetSolidFill()) {
                spPr.unsetSolidFill();
            }
        } else {
            CTSolidColorFillProperties fill = spPr.isSetSolidFill() ? spPr.getSolidFill() : spPr.addNewSolidFill();
            XSLFColor c = new XSLFColor(fill, getSheet().getTheme(), fill.getSchemeClr(), getSheet());
            c.setColor(color);
        }
    }

    @Override // org.apache.poi.xslf.usermodel.XSLFSimpleShape, org.apache.poi.sl.usermodel.SimpleShape
    public Color getFillColor() {
        PaintStyle ps = getFillPaint();
        if (ps instanceof PaintStyle.SolidPaint) {
            ColorStyle cs = ((PaintStyle.SolidPaint) ps).getSolidColor();
            return DrawPaint.applyColorTransform(cs);
        }
        return null;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [org.apache.poi.xslf.usermodel.XSLFSheet] */
    /* JADX WARN: Type inference failed for: r10v0, types: [org.apache.poi.xslf.usermodel.XMLSlideShow] */
    @Override // org.apache.poi.xslf.usermodel.XSLFShape
    public PaintStyle getFillPaint() {
        XSLFTableCell xSLFTableCell;
        CTTablePartStyle tps;
        XmlObject props;
        PaintStyle paint;
        ?? sheet = getSheet();
        XSLFTheme theme = sheet.getTheme();
        boolean hasPlaceholder = getPlaceholder() != null;
        XmlObject props2 = getCellProperties(false);
        XSLFPropertiesDelegate.XSLFFillProperties fp = XSLFPropertiesDelegate.getFillDelegate(props2);
        if (fp == null) {
            xSLFTableCell = this;
        } else {
            xSLFTableCell = this;
            PaintStyle paint2 = xSLFTableCell.selectPaint(fp, null, sheet.getPackagePart(), theme, hasPlaceholder);
            if (paint2 != null) {
                return paint2;
            }
        }
        CTTablePartStyle tps2 = getTablePartStyle(null);
        if (tps2 == null || !tps2.isSetTcStyle()) {
            CTTablePartStyle tps3 = getTablePartStyle(XSLFTableStyle.TablePartStyle.wholeTbl);
            if (tps3 == null || !tps3.isSetTcStyle()) {
                return null;
            }
            tps = tps3;
        } else {
            tps = tps2;
        }
        ?? slideShow = sheet.getSlideShow();
        CTTableStyleCellStyle tcStyle = tps.getTcStyle();
        if (tcStyle.isSetFill()) {
            props = tcStyle.getFill();
        } else {
            if (!tcStyle.isSetFillRef()) {
                return null;
            }
            props = tcStyle.getFillRef();
        }
        XSLFPropertiesDelegate.XSLFFillProperties fp2 = XSLFPropertiesDelegate.getFillDelegate(props);
        if (fp2 == null || (paint = xSLFTableCell.selectPaint(fp2, null, slideShow.getPackagePart(), theme, hasPlaceholder)) == null) {
            return null;
        }
        return paint;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CTTablePartStyle getTablePartStyle(XSLFTableStyle.TablePartStyle tablePartStyle) {
        XSLFTableStyle.TablePartStyle tps;
        CTTable ct = this.table.getCTTable();
        if (!ct.isSetTblPr()) {
            return null;
        }
        CTTableProperties pr = ct.getTblPr();
        boolean bandRow = pr.isSetBandRow() && pr.getBandRow();
        boolean firstRow = pr.isSetFirstRow() && pr.getFirstRow();
        boolean lastRow = pr.isSetLastRow() && pr.getLastRow();
        boolean bandCol = pr.isSetBandCol() && pr.getBandCol();
        boolean firstCol = pr.isSetFirstCol() && pr.getFirstCol();
        boolean lastCol = pr.isSetLastCol() && pr.getLastCol();
        if (tablePartStyle != null) {
            tps = tablePartStyle;
        } else if (this.row == 0 && firstRow) {
            tps = XSLFTableStyle.TablePartStyle.firstRow;
        } else if (this.row == this.table.getNumberOfRows() - 1 && lastRow) {
            tps = XSLFTableStyle.TablePartStyle.lastRow;
        } else if (this.col == 0 && firstCol) {
            tps = XSLFTableStyle.TablePartStyle.firstCol;
        } else if (this.col == this.table.getNumberOfColumns() - 1 && lastCol) {
            tps = XSLFTableStyle.TablePartStyle.lastCol;
        } else {
            XSLFTableStyle.TablePartStyle tps2 = XSLFTableStyle.TablePartStyle.wholeTbl;
            int br = this.row + (firstRow ? 1 : 0);
            int bc = this.col + (firstCol ? 1 : 0);
            if (bandRow && (br & 1) == 0) {
                tps = XSLFTableStyle.TablePartStyle.band1H;
            } else if (bandCol && (bc & 1) == 0) {
                tps = XSLFTableStyle.TablePartStyle.band1V;
            } else {
                tps = tps2;
            }
        }
        XSLFTableStyle tabStyle = this.table.getTableStyle();
        if (tabStyle == null) {
            return null;
        }
        CTTablePartStyle part = tabStyle.getTablePartStyle(tps);
        return part == null ? tabStyle.getTablePartStyle(XSLFTableStyle.TablePartStyle.wholeTbl) : part;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setGridSpan(int gridSpan_) {
        getCell().setGridSpan(gridSpan_);
    }

    @Override // org.apache.poi.sl.usermodel.TableCell
    public int getGridSpan() {
        CTTableCell c = getCell();
        if (c.isSetGridSpan()) {
            return c.getGridSpan();
        }
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setRowSpan(int rowSpan_) {
        getCell().setRowSpan(rowSpan_);
    }

    @Override // org.apache.poi.sl.usermodel.TableCell
    public int getRowSpan() {
        CTTableCell c = getCell();
        if (c.isSetRowSpan()) {
            return c.getRowSpan();
        }
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setHMerge() {
        getCell().setHMerge(true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setVMerge() {
        getCell().setVMerge(true);
    }

    @Override // org.apache.poi.xslf.usermodel.XSLFTextShape, org.apache.poi.sl.usermodel.TextShape
    public void setVerticalAlignment(VerticalAlignment anchor) {
        CTTableCellProperties cellProps = getCellProperties(true);
        if (anchor != null) {
            cellProps.setAnchor(STTextAnchoringType.Enum.forInt(anchor.ordinal() + 1));
        } else if (cellProps.isSetAnchor()) {
            cellProps.unsetAnchor();
        }
    }

    @Override // org.apache.poi.xslf.usermodel.XSLFTextShape, org.apache.poi.sl.usermodel.TextShape
    public VerticalAlignment getVerticalAlignment() {
        CTTableCellProperties cellProps = getCellProperties(false);
        VerticalAlignment align = VerticalAlignment.TOP;
        if (cellProps != null && cellProps.isSetAnchor()) {
            int ival = cellProps.getAnchor().intValue();
            return VerticalAlignment.values()[ival - 1];
        }
        return align;
    }

    @Override // org.apache.poi.xslf.usermodel.XSLFTextShape, org.apache.poi.sl.usermodel.TextShape
    public void setTextDirection(TextShape.TextDirection orientation) {
        STTextVerticalType.Enum vt;
        CTTableCellProperties cellProps = getCellProperties(true);
        if (orientation == null) {
            if (cellProps.isSetVert()) {
                cellProps.unsetVert();
                return;
            }
            return;
        }
        switch (orientation) {
            case VERTICAL:
                vt = STTextVerticalType.VERT;
                break;
            case VERTICAL_270:
                vt = STTextVerticalType.VERT_270;
                break;
            case STACKED:
                vt = STTextVerticalType.WORD_ART_VERT;
                break;
            default:
                vt = STTextVerticalType.HORZ;
                break;
        }
        cellProps.setVert(vt);
    }

    @Override // org.apache.poi.xslf.usermodel.XSLFTextShape, org.apache.poi.sl.usermodel.TextShape
    public TextShape.TextDirection getTextDirection() {
        STTextVerticalType.Enum orientation;
        CTTableCellProperties cellProps = getCellProperties(false);
        if (cellProps != null && cellProps.isSetVert()) {
            orientation = cellProps.getVert();
        } else {
            orientation = STTextVerticalType.HORZ;
        }
        switch (orientation.intValue()) {
            case 2:
            case 5:
            case 6:
                return TextShape.TextDirection.VERTICAL;
            case 3:
                return TextShape.TextDirection.VERTICAL_270;
            case 4:
            case 7:
                return TextShape.TextDirection.STACKED;
            default:
                return TextShape.TextDirection.HORIZONTAL;
        }
    }

    private CTTableCell getCell() {
        return (CTTableCell) getXmlObject();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setRowColIndex(int row, int col) {
        this.row = row;
        this.col = col;
    }

    protected CTTransform2D getXfrm() {
        Rectangle2D anc = getAnchor();
        CTTransform2D xfrm = CTTransform2D.Factory.newInstance();
        CTPoint2D off = xfrm.addNewOff();
        off.setX(Integer.valueOf(Units.toEMU(anc.getX())));
        off.setY(Integer.valueOf(Units.toEMU(anc.getY())));
        CTPositiveSize2D size = xfrm.addNewExt();
        size.setCx(Units.toEMU(anc.getWidth()));
        size.setCy(Units.toEMU(anc.getHeight()));
        return xfrm;
    }

    @Override // org.apache.poi.xslf.usermodel.XSLFSimpleShape, org.apache.poi.sl.usermodel.PlaceableShape
    public void setAnchor(Rectangle2D anchor) {
        if (this.anchor == null) {
            this.anchor = (Rectangle2D) anchor.clone();
        } else {
            this.anchor.setRect(anchor);
        }
    }

    @Override // org.apache.poi.xslf.usermodel.XSLFSimpleShape, org.apache.poi.sl.usermodel.Shape, org.apache.poi.sl.usermodel.PlaceableShape
    public Rectangle2D getAnchor() {
        if (this.anchor == null) {
            this.table.updateCellAnchor();
        }
        if (this.anchor == null) {
            throw new AssertionError();
        }
        return this.anchor;
    }

    @Override // org.apache.poi.sl.usermodel.TableCell
    public boolean isMerged() {
        CTTableCell c = getCell();
        return (c.isSetHMerge() && c.getHMerge()) || (c.isSetVMerge() && c.getVMerge());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.xslf.usermodel.XSLFTextShape
    public XSLFCellTextParagraph newTextParagraph(CTTextParagraph p) {
        return new XSLFCellTextParagraph(p, this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.xslf.usermodel.XSLFShape
    public XmlObject getShapeProperties() {
        return getCellProperties(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public final class XSLFCellTextParagraph extends XSLFTextParagraph {
        private XSLFCellTextParagraph(CTTextParagraph p, XSLFTextShape shape) {
            super(p, shape);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.apache.poi.xslf.usermodel.XSLFTextParagraph
        public XSLFCellTextRun newTextRun(XmlObject r) {
            return new XSLFCellTextRun(r, this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public final class XSLFCellTextRun extends XSLFTextRun {
        private XSLFCellTextRun(XmlObject r, XSLFTextParagraph p) {
            super(r, p);
        }

        /* JADX WARN: Type inference failed for: r5v1, types: [org.apache.poi.xslf.usermodel.XSLFSheet] */
        /* JADX WARN: Type inference failed for: r7v2, types: [org.apache.poi.xslf.usermodel.XSLFSheet] */
        @Override // org.apache.poi.xslf.usermodel.XSLFTextRun, org.apache.poi.sl.usermodel.TextRun
        public PaintStyle getFontColor() {
            CTTableStyleTextStyle txStyle = getTextStyle();
            if (txStyle == null) {
                return super.getFontColor();
            }
            CTTextCharacterProperties props = getRPr(false);
            XSLFPropertiesDelegate.XSLFFillProperties fp = XSLFPropertiesDelegate.getFillDelegate(props);
            if (fp != null && (fp.isSetSolidFill() || fp.isSetGradFill() || fp.isSetPattFill())) {
                return super.getFontColor();
            }
            CTSchemeColor phClr = null;
            CTFontReference fontRef = txStyle.getFontRef();
            if (fontRef != null) {
                phClr = fontRef.getSchemeClr();
            }
            XSLFTheme theme = XSLFTableCell.this.getSheet().getTheme();
            try {
                XSLFColor c = new XSLFColor(txStyle, theme, phClr, XSLFTableCell.this.getSheet());
                return DrawPaint.createSolidPaint(c.getColorStyle());
            } catch (IllegalArgumentException e) {
                return super.getFontColor();
            }
        }

        @Override // org.apache.poi.xslf.usermodel.XSLFTextRun, org.apache.poi.sl.usermodel.TextRun
        public boolean isBold() {
            CTTableStyleTextStyle txStyle = getTextStyle();
            if (txStyle == null) {
                return super.isBold();
            }
            CTTextCharacterProperties rPr = super.getRPr(false);
            if (rPr == null || !rPr.isSetB()) {
                return txStyle.isSetB() && txStyle.getB().intValue() == 1;
            }
            return rPr.getB();
        }

        @Override // org.apache.poi.xslf.usermodel.XSLFTextRun, org.apache.poi.sl.usermodel.TextRun
        public boolean isItalic() {
            CTTableStyleTextStyle txStyle = getTextStyle();
            if (txStyle == null) {
                return super.isItalic();
            }
            CTTextCharacterProperties rPr = super.getRPr(false);
            if (rPr == null || !rPr.isSetI()) {
                return txStyle.isSetI() && txStyle.getI().intValue() == 1;
            }
            return rPr.getI();
        }

        private CTTableStyleTextStyle getTextStyle() {
            CTTablePartStyle tps = XSLFTableCell.this.getTablePartStyle(null);
            if (tps == null || !tps.isSetTcTxStyle()) {
                tps = XSLFTableCell.this.getTablePartStyle(XSLFTableStyle.TablePartStyle.wholeTbl);
            }
            if (tps == null) {
                return null;
            }
            return tps.getTcTxStyle();
        }
    }
}
