package org.apache.poi.xssf.usermodel;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.Function;
import java.util.function.Predicate;
import kotlin.text.Typography;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.common.usermodel.fonts.FontHeader;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.SimpleShape;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.XDDFColor;
import org.apache.poi.xddf.usermodel.XDDFColorRgbBinary;
import org.apache.poi.xddf.usermodel.XDDFFillProperties;
import org.apache.poi.xddf.usermodel.XDDFSolidFillProperties;
import org.apache.poi.xddf.usermodel.text.TextContainer;
import org.apache.poi.xddf.usermodel.text.XDDFRunProperties;
import org.apache.poi.xddf.usermodel.text.XDDFTextBody;
import org.apache.poi.xddf.usermodel.text.XDDFTextParagraph;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetGeometry2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTRegularTextRun;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSRgbColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSolidColorFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextFont;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D;
import org.openxmlformats.schemas.drawingml.x2006.main.STShapeType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextAnchoringType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextHorzOverflowType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextUnderlineType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextVertOverflowType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextVerticalType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextWrappingType;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTShape;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTShapeNonVisual;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRElt;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRPrElt;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STUnderlineValues;

/* loaded from: classes10.dex */
public class XSSFSimpleShape extends XSSFShape implements Iterable<XSSFTextParagraph>, SimpleShape, TextContainer {
    private static CTShape prototype;
    private final List<XSSFTextParagraph> _paragraphs;
    private final XDDFTextBody _textBody;
    private CTShape ctShape;
    private static String[] _romanChars = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    private static int[] _romanAlphaValues = {1000, TypedValues.Custom.TYPE_INT, 500, FontHeader.REGULAR_WEIGHT, 100, 90, 50, 40, 10, 9, 5, 4, 1};

    /* JADX INFO: Access modifiers changed from: protected */
    public XSSFSimpleShape(XSSFDrawing drawing, CTShape ctShape) {
        this.drawing = drawing;
        this.ctShape = ctShape;
        this._paragraphs = new ArrayList();
        CTTextBody body = ctShape.getTxBody();
        if (body == null) {
            this._textBody = null;
            return;
        }
        this._textBody = new XDDFTextBody(this, body);
        for (int i = 0; i < body.sizeOfPArray(); i++) {
            this._paragraphs.add(new XSSFTextParagraph(body.getPArray(i), ctShape));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static CTShape prototype() {
        if (prototype == null) {
            CTShape shape = CTShape.Factory.newInstance();
            CTShapeNonVisual nv = shape.addNewNvSpPr();
            CTNonVisualDrawingProps nvp = nv.addNewCNvPr();
            nvp.setId(1L);
            nvp.setName("Shape 1");
            nv.addNewCNvSpPr();
            CTShapeProperties sp = shape.addNewSpPr();
            CTTransform2D t2d = sp.addNewXfrm();
            CTPositiveSize2D p1 = t2d.addNewExt();
            p1.setCx(0L);
            p1.setCy(0L);
            CTPoint2D p2 = t2d.addNewOff();
            p2.setX(0);
            p2.setY(0);
            CTPresetGeometry2D geom = sp.addNewPrstGeom();
            geom.setPrst(STShapeType.RECT);
            geom.addNewAvLst();
            XDDFTextBody body = new XDDFTextBody(null, shape.addNewTxBody());
            XDDFTextParagraph p = body.initialize();
            XDDFRunProperties rp = p.getAfterLastRunProperties();
            XDDFColor black = new XDDFColorRgbBinary(new byte[]{0, 0, 0});
            XDDFFillProperties fp = new XDDFSolidFillProperties(black);
            rp.setFillProperties(fp);
            prototype = shape;
        }
        return prototype;
    }

    @Internal
    public CTShape getCTShape() {
        return this.ctShape;
    }

    public XDDFTextBody getTextBody() {
        return this._textBody;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setXfrm(CTTransform2D t2d) {
        this.ctShape.getSpPr().setXfrm(t2d);
    }

    @Override // java.lang.Iterable
    public Iterator<XSSFTextParagraph> iterator() {
        return this._paragraphs.iterator();
    }

    @Override // java.lang.Iterable
    public Spliterator<XSSFTextParagraph> spliterator() {
        return this._paragraphs.spliterator();
    }

    public String getText() {
        StringBuilder out = new StringBuilder();
        List<Integer> levelCount = new ArrayList<>(9);
        for (int k = 0; k < 9; k++) {
            levelCount.add(0);
        }
        int i = 0;
        while (i < this._paragraphs.size()) {
            if (out.length() > 0) {
                out.append('\n');
            }
            XSSFTextParagraph p = this._paragraphs.get(i);
            String pText = p.getText();
            if (p.isBullet() && !pText.isEmpty()) {
                int level = Math.min(p.getLevel(), 8);
                if (p.isBulletAutoNumber()) {
                    i = processAutoNumGroup(i, level, levelCount, out);
                } else {
                    for (int j = 0; j < level; j++) {
                        out.append('\t');
                    }
                    String character = p.getBulletCharacter();
                    out.append(!character.isEmpty() ? character + StringUtils.SPACE : "- ");
                    out.append(pText);
                }
            } else {
                out.append(pText);
                for (int k2 = 0; k2 < 9; k2++) {
                    levelCount.set(k2, 0);
                }
            }
            i++;
        }
        return out.toString();
    }

    private int processAutoNumGroup(int index, int level, List<Integer> levelCount, StringBuilder out) {
        XSSFTextParagraph p = this._paragraphs.get(index);
        int startAt = p.getBulletAutoNumberStart();
        ListAutoNumber scheme = p.getBulletAutoNumberScheme();
        if (levelCount.get(level).intValue() == 0) {
            levelCount.set(level, Integer.valueOf(startAt == 0 ? 1 : startAt));
        }
        for (int j = 0; j < level; j++) {
            out.append('\t');
        }
        String pText = p.getText();
        if (!pText.isEmpty()) {
            out.append(getBulletPrefix(scheme, levelCount.get(level).intValue()));
            out.append(pText);
        }
        while (true) {
            XSSFTextParagraph nextp = index + 1 == this._paragraphs.size() ? null : this._paragraphs.get(index + 1);
            if (nextp == null || !nextp.isBullet() || !p.isBulletAutoNumber()) {
                break;
            }
            if (nextp.getLevel() > level) {
                if (out.length() > 0) {
                    out.append('\n');
                }
                index = processAutoNumGroup(index + 1, nextp.getLevel(), levelCount, out);
            } else {
                if (nextp.getLevel() < level) {
                    break;
                }
                ListAutoNumber nextScheme = nextp.getBulletAutoNumberScheme();
                int nextStartAt = nextp.getBulletAutoNumberStart();
                if (nextScheme != scheme || nextStartAt != startAt) {
                    break;
                }
                index++;
                if (out.length() > 0) {
                    out.append('\n');
                }
                for (int j2 = 0; j2 < level; j2++) {
                    out.append('\t');
                }
                String npText = nextp.getText();
                if (!npText.isEmpty()) {
                    levelCount.set(level, Integer.valueOf(levelCount.get(level).intValue() + 1));
                    out.append(getBulletPrefix(nextScheme, levelCount.get(level).intValue()));
                    out.append(npText);
                }
            }
        }
        levelCount.set(level, 0);
        return index;
    }

    private String getBulletPrefix(ListAutoNumber scheme, int value) {
        StringBuilder out = new StringBuilder();
        switch (scheme) {
            case ALPHA_LC_PARENT_BOTH:
            case ALPHA_LC_PARENT_R:
                if (scheme == ListAutoNumber.ALPHA_LC_PARENT_BOTH) {
                    out.append('(');
                }
                out.append(valueToAlpha(value).toLowerCase(Locale.ROOT));
                out.append(')');
                break;
            case ALPHA_UC_PARENT_BOTH:
            case ALPHA_UC_PARENT_R:
                if (scheme == ListAutoNumber.ALPHA_UC_PARENT_BOTH) {
                    out.append('(');
                }
                out.append(valueToAlpha(value));
                out.append(')');
                break;
            case ALPHA_LC_PERIOD:
                out.append(valueToAlpha(value).toLowerCase(Locale.ROOT));
                out.append('.');
                break;
            case ALPHA_UC_PERIOD:
                out.append(valueToAlpha(value));
                out.append('.');
                break;
            case ARABIC_PARENT_BOTH:
            case ARABIC_PARENT_R:
                if (scheme == ListAutoNumber.ARABIC_PARENT_BOTH) {
                    out.append('(');
                }
                out.append(value);
                out.append(')');
                break;
            case ARABIC_PERIOD:
                out.append(value);
                out.append('.');
                break;
            case ARABIC_PLAIN:
                out.append(value);
                break;
            case ROMAN_LC_PARENT_BOTH:
            case ROMAN_LC_PARENT_R:
                if (scheme == ListAutoNumber.ROMAN_LC_PARENT_BOTH) {
                    out.append('(');
                }
                out.append(valueToRoman(value).toLowerCase(Locale.ROOT));
                out.append(')');
                break;
            case ROMAN_UC_PARENT_BOTH:
            case ROMAN_UC_PARENT_R:
                if (scheme == ListAutoNumber.ROMAN_UC_PARENT_BOTH) {
                    out.append('(');
                }
                out.append(valueToRoman(value));
                out.append(')');
                break;
            case ROMAN_LC_PERIOD:
                out.append(valueToRoman(value).toLowerCase(Locale.ROOT));
                out.append('.');
                break;
            case ROMAN_UC_PERIOD:
                out.append(valueToRoman(value));
                out.append('.');
                break;
            default:
                out.append(Typography.bullet);
                break;
        }
        out.append(StringUtils.SPACE);
        return out.toString();
    }

    private String valueToAlpha(int value) {
        StringBuilder alpha = new StringBuilder();
        while (value > 0) {
            int modulo = (value - 1) % 26;
            alpha.append((char) (modulo + 65));
            value = (value - modulo) / 26;
        }
        alpha.reverse();
        return alpha.toString();
    }

    private String valueToRoman(int value) {
        StringBuilder out = new StringBuilder();
        for (int i = 0; value > 0 && i < _romanChars.length; i++) {
            while (_romanAlphaValues[i] <= value) {
                out.append(_romanChars[i]);
                value -= _romanAlphaValues[i];
            }
        }
        return out.toString();
    }

    public void clearText() {
        this._paragraphs.clear();
        CTTextBody txBody = this.ctShape.getTxBody();
        txBody.setPArray(null);
    }

    public void setText(String text) {
        clearText();
        addNewTextParagraph().addNewTextRun().setText(text);
    }

    public void setText(XSSFRichTextString str) {
        XSSFWorkbook wb = (XSSFWorkbook) getDrawing().getParent().getParent();
        str.setStylesTableReference(wb.getStylesSource());
        CTTextParagraph p = CTTextParagraph.Factory.newInstance();
        if (str.numFormattingRuns() == 0) {
            CTRegularTextRun r = p.addNewR();
            CTTextCharacterProperties rPr = r.addNewRPr();
            rPr.setLang("en-US");
            rPr.setSz(1100);
            r.setT(str.getString());
        } else {
            for (int i = 0; i < str.getCTRst().sizeOfRArray(); i++) {
                CTRElt lt = str.getCTRst().getRArray(i);
                CTRPrElt ltPr = lt.getRPr();
                if (ltPr == null) {
                    ltPr = lt.addNewRPr();
                }
                CTRegularTextRun r2 = p.addNewR();
                CTTextCharacterProperties rPr2 = r2.addNewRPr();
                rPr2.setLang("en-US");
                applyAttributes(ltPr, rPr2);
                r2.setT(lt.getT());
            }
        }
        clearText();
        this.ctShape.getTxBody().setPArray(new CTTextParagraph[]{p});
        this._paragraphs.add(new XSSFTextParagraph(this.ctShape.getTxBody().getPArray(0), this.ctShape));
    }

    public List<XSSFTextParagraph> getTextParagraphs() {
        return this._paragraphs;
    }

    public XSSFTextParagraph addNewTextParagraph() {
        CTTextBody txBody = this.ctShape.getTxBody();
        CTTextParagraph p = txBody.addNewP();
        XSSFTextParagraph paragraph = new XSSFTextParagraph(p, this.ctShape);
        this._paragraphs.add(paragraph);
        return paragraph;
    }

    public XSSFTextParagraph addNewTextParagraph(String text) {
        XSSFTextParagraph paragraph = addNewTextParagraph();
        paragraph.addNewTextRun().setText(text);
        return paragraph;
    }

    public XSSFTextParagraph addNewTextParagraph(XSSFRichTextString str) {
        CTTextBody txBody = this.ctShape.getTxBody();
        CTTextParagraph p = txBody.addNewP();
        if (str.numFormattingRuns() == 0) {
            CTRegularTextRun r = p.addNewR();
            CTTextCharacterProperties rPr = r.addNewRPr();
            rPr.setLang("en-US");
            rPr.setSz(1100);
            r.setT(str.getString());
        } else {
            for (int i = 0; i < str.getCTRst().sizeOfRArray(); i++) {
                CTRElt lt = str.getCTRst().getRArray(i);
                CTRPrElt ltPr = lt.getRPr();
                if (ltPr == null) {
                    ltPr = lt.addNewRPr();
                }
                CTRegularTextRun r2 = p.addNewR();
                CTTextCharacterProperties rPr2 = r2.addNewRPr();
                rPr2.setLang("en-US");
                applyAttributes(ltPr, rPr2);
                r2.setT(lt.getT());
            }
        }
        XSSFTextParagraph paragraph = new XSSFTextParagraph(p, this.ctShape);
        this._paragraphs.add(paragraph);
        return paragraph;
    }

    public void setTextHorizontalOverflow(TextHorizontalOverflow overflow) {
        CTTextBodyProperties bodyPr = this.ctShape.getTxBody().getBodyPr();
        if (bodyPr != null) {
            if (overflow == null) {
                if (bodyPr.isSetHorzOverflow()) {
                    bodyPr.unsetHorzOverflow();
                    return;
                }
                return;
            }
            bodyPr.setHorzOverflow(STTextHorzOverflowType.Enum.forInt(overflow.ordinal() + 1));
        }
    }

    public TextHorizontalOverflow getTextHorizontalOverflow() {
        CTTextBodyProperties bodyPr = this.ctShape.getTxBody().getBodyPr();
        if (bodyPr != null && bodyPr.isSetHorzOverflow()) {
            return TextHorizontalOverflow.values()[bodyPr.getHorzOverflow().intValue() - 1];
        }
        return TextHorizontalOverflow.OVERFLOW;
    }

    public void setTextVerticalOverflow(TextVerticalOverflow overflow) {
        CTTextBodyProperties bodyPr = this.ctShape.getTxBody().getBodyPr();
        if (bodyPr != null) {
            if (overflow == null) {
                if (bodyPr.isSetVertOverflow()) {
                    bodyPr.unsetVertOverflow();
                    return;
                }
                return;
            }
            bodyPr.setVertOverflow(STTextVertOverflowType.Enum.forInt(overflow.ordinal() + 1));
        }
    }

    public TextVerticalOverflow getTextVerticalOverflow() {
        CTTextBodyProperties bodyPr = this.ctShape.getTxBody().getBodyPr();
        if (bodyPr != null && bodyPr.isSetVertOverflow()) {
            return TextVerticalOverflow.values()[bodyPr.getVertOverflow().intValue() - 1];
        }
        return TextVerticalOverflow.OVERFLOW;
    }

    public void setVerticalAlignment(VerticalAlignment anchor) {
        CTTextBodyProperties bodyPr = this.ctShape.getTxBody().getBodyPr();
        if (bodyPr != null) {
            if (anchor == null) {
                if (bodyPr.isSetAnchor()) {
                    bodyPr.unsetAnchor();
                    return;
                }
                return;
            }
            bodyPr.setAnchor(STTextAnchoringType.Enum.forInt(anchor.ordinal() + 1));
        }
    }

    public VerticalAlignment getVerticalAlignment() {
        CTTextBodyProperties bodyPr = this.ctShape.getTxBody().getBodyPr();
        if (bodyPr != null && bodyPr.isSetAnchor()) {
            return VerticalAlignment.values()[bodyPr.getAnchor().intValue() - 1];
        }
        return VerticalAlignment.TOP;
    }

    public void setTextDirection(TextDirection orientation) {
        CTTextBodyProperties bodyPr = this.ctShape.getTxBody().getBodyPr();
        if (bodyPr != null) {
            if (orientation == null) {
                if (bodyPr.isSetVert()) {
                    bodyPr.unsetVert();
                    return;
                }
                return;
            }
            bodyPr.setVert(STTextVerticalType.Enum.forInt(orientation.ordinal() + 1));
        }
    }

    public TextDirection getTextDirection() {
        STTextVerticalType.Enum val;
        CTTextBodyProperties bodyPr = this.ctShape.getTxBody().getBodyPr();
        if (bodyPr != null && (val = bodyPr.getVert()) != null) {
            return TextDirection.values()[val.intValue() - 1];
        }
        return TextDirection.HORIZONTAL;
    }

    public double getBottomInset() {
        Double inset = this._textBody.getBodyProperties().getBottomInset();
        if (inset == null) {
            return 3.6d;
        }
        return inset.doubleValue();
    }

    public double getLeftInset() {
        Double inset = this._textBody.getBodyProperties().getLeftInset();
        if (inset == null) {
            return 3.6d;
        }
        return inset.doubleValue();
    }

    public double getRightInset() {
        Double inset = this._textBody.getBodyProperties().getRightInset();
        if (inset == null) {
            return 3.6d;
        }
        return inset.doubleValue();
    }

    public double getTopInset() {
        Double inset = this._textBody.getBodyProperties().getTopInset();
        if (inset == null) {
            return 3.6d;
        }
        return inset.doubleValue();
    }

    public void setBottomInset(double margin) {
        if (margin == -1.0d) {
            this._textBody.getBodyProperties().setBottomInset(null);
        } else {
            this._textBody.getBodyProperties().setBottomInset(Double.valueOf(margin));
        }
    }

    public void setLeftInset(double margin) {
        if (margin == -1.0d) {
            this._textBody.getBodyProperties().setLeftInset(null);
        } else {
            this._textBody.getBodyProperties().setLeftInset(Double.valueOf(margin));
        }
    }

    public void setRightInset(double margin) {
        if (margin == -1.0d) {
            this._textBody.getBodyProperties().setRightInset(null);
        } else {
            this._textBody.getBodyProperties().setRightInset(Double.valueOf(margin));
        }
    }

    public void setTopInset(double margin) {
        if (margin == -1.0d) {
            this._textBody.getBodyProperties().setTopInset(null);
        } else {
            this._textBody.getBodyProperties().setTopInset(Double.valueOf(margin));
        }
    }

    public boolean getWordWrap() {
        CTTextBodyProperties bodyPr = this.ctShape.getTxBody().getBodyPr();
        return bodyPr == null || !bodyPr.isSetWrap() || bodyPr.getWrap() == STTextWrappingType.SQUARE;
    }

    public void setWordWrap(boolean wrap) {
        CTTextBodyProperties bodyPr = this.ctShape.getTxBody().getBodyPr();
        if (bodyPr != null) {
            bodyPr.setWrap(wrap ? STTextWrappingType.SQUARE : STTextWrappingType.NONE);
        }
    }

    public void setTextAutofit(TextAutofit value) {
        CTTextBodyProperties bodyPr = this.ctShape.getTxBody().getBodyPr();
        if (bodyPr != null) {
            if (bodyPr.isSetSpAutoFit()) {
                bodyPr.unsetSpAutoFit();
            }
            if (bodyPr.isSetNoAutofit()) {
                bodyPr.unsetNoAutofit();
            }
            if (bodyPr.isSetNormAutofit()) {
                bodyPr.unsetNormAutofit();
            }
            switch (value) {
                case NONE:
                    bodyPr.addNewNoAutofit();
                    return;
                case NORMAL:
                    bodyPr.addNewNormAutofit();
                    return;
                case SHAPE:
                    bodyPr.addNewSpAutoFit();
                    return;
                default:
                    return;
            }
        }
    }

    public TextAutofit getTextAutofit() {
        CTTextBodyProperties bodyPr = this.ctShape.getTxBody().getBodyPr();
        if (bodyPr != null) {
            if (bodyPr.isSetNoAutofit()) {
                return TextAutofit.NONE;
            }
            if (bodyPr.isSetNormAutofit()) {
                return TextAutofit.NORMAL;
            }
            if (bodyPr.isSetSpAutoFit()) {
                return TextAutofit.SHAPE;
            }
        }
        return TextAutofit.NORMAL;
    }

    public int getShapeType() {
        return this.ctShape.getSpPr().getPrstGeom().getPrst().intValue();
    }

    public void setShapeType(int type) {
        this.ctShape.getSpPr().getPrstGeom().setPrst(STShapeType.Enum.forInt(type));
    }

    @Override // org.apache.poi.xssf.usermodel.XSSFShape
    protected CTShapeProperties getShapeProperties() {
        return this.ctShape.getSpPr();
    }

    private static void applyAttributes(CTRPrElt pr, CTTextCharacterProperties rPr) {
        HSSFColor indexed;
        if (pr.sizeOfBArray() > 0) {
            rPr.setB(pr.getBArray(0).getVal());
        }
        if (pr.sizeOfUArray() > 0) {
            STUnderlineValues.Enum u1 = pr.getUArray(0).getVal();
            if (u1 == STUnderlineValues.SINGLE) {
                rPr.setU(STTextUnderlineType.SNG);
            } else if (u1 == STUnderlineValues.DOUBLE) {
                rPr.setU(STTextUnderlineType.DBL);
            } else if (u1 == STUnderlineValues.NONE) {
                rPr.setU(STTextUnderlineType.NONE);
            }
        }
        if (pr.sizeOfIArray() > 0) {
            rPr.setI(pr.getIArray(0).getVal());
        }
        if (pr.sizeOfRFontArray() > 0) {
            CTTextFont rFont = rPr.isSetLatin() ? rPr.getLatin() : rPr.addNewLatin();
            rFont.setTypeface(pr.getRFontArray(0).getVal());
        }
        if (pr.sizeOfSzArray() > 0) {
            int sz = (int) (pr.getSzArray(0).getVal() * 100.0d);
            rPr.setSz(sz);
        }
        int sz2 = pr.sizeOfColorArray();
        if (sz2 > 0) {
            CTSolidColorFillProperties fill = rPr.isSetSolidFill() ? rPr.getSolidFill() : rPr.addNewSolidFill();
            CTColor xlsColor = pr.getColorArray(0);
            if (xlsColor.isSetRgb()) {
                CTSRgbColor clr = fill.isSetSrgbClr() ? fill.getSrgbClr() : fill.addNewSrgbClr();
                clr.setVal(xlsColor.getRgb());
            } else if (xlsColor.isSetIndexed() && (indexed = HSSFColor.getIndexHash().get(Integer.valueOf((int) xlsColor.getIndexed()))) != null) {
                byte[] rgb = {(byte) indexed.getTriplet()[0], (byte) indexed.getTriplet()[1], (byte) indexed.getTriplet()[2]};
                CTSRgbColor clr2 = fill.isSetSrgbClr() ? fill.getSrgbClr() : fill.addNewSrgbClr();
                clr2.setVal(rgb);
            }
        }
    }

    @Override // org.apache.poi.ss.usermodel.Shape
    public String getShapeName() {
        return this.ctShape.getNvSpPr().getCNvPr().getName();
    }

    @Override // org.apache.poi.ss.usermodel.SimpleShape
    public int getShapeId() {
        return (int) this.ctShape.getNvSpPr().getCNvPr().getId();
    }

    @Override // org.apache.poi.xddf.usermodel.text.TextContainer
    public <R> Optional<R> findDefinedParagraphProperty(Predicate<CTTextParagraphProperties> isSet, Function<CTTextParagraphProperties, R> getter) {
        return Optional.empty();
    }

    @Override // org.apache.poi.xddf.usermodel.text.TextContainer
    public <R> Optional<R> findDefinedRunProperty(Predicate<CTTextCharacterProperties> isSet, Function<CTTextCharacterProperties, R> getter) {
        return Optional.empty();
    }
}
