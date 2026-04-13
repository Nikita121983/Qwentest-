package org.apache.poi.xwpf.usermodel;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.xml.namespace.QName;
import kotlinx.coroutines.DebugKt;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.compress.compressors.CompressorStreamFactory;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.common.usermodel.PictureType;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.ooxml.util.DocumentHelper;
import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LocaleUtil;
import org.apache.poi.util.Removal;
import org.apache.poi.util.Units;
import org.apache.poi.wp.usermodel.CharacterRun;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.values.XmlAnyTypeImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTRelId;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlip;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObjectData;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualPictureProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetGeometry2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D;
import org.openxmlformats.schemas.drawingml.x2006.main.STShapeType;
import org.openxmlformats.schemas.drawingml.x2006.picture.CTPicture;
import org.openxmlformats.schemas.drawingml.x2006.picture.CTPictureNonVisual;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTInline;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STHexColorRGB;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STOnOff1;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STVerticalAlignRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTColor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDrawing;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEm;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEmpty;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFCheckBox;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnEdnRef;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHighlight;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHpsMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLanguage;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPTab;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRuby;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRubyContent;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSignedHpsMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSignedTwipsMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTextScale;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTUnderline;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTVerticalAlignRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STBrClear;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STBrType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STEm;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STFldCharType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHexColorAuto;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHighlightColor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STThemeColor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STUnderline;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/* loaded from: classes10.dex */
public class XWPFRun implements ISDTContents, IRunElement, CharacterRun {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final List<XWPFChart> charts;
    private final IRunBody parent;
    private final String pictureText;
    private final List<XWPFPicture> pictures;
    private final CTR run;

    /* loaded from: classes10.dex */
    public enum FontCharRange {
        ascii,
        cs,
        eastAsia,
        hAnsi
    }

    public XWPFRun(CTR r, IRunBody p) {
        this.run = r;
        this.parent = p;
        for (CTDrawing ctDrawing : r.getDrawingArray()) {
            for (CTAnchor anchor : ctDrawing.getAnchorArray()) {
                if (anchor.getDocPr() != null) {
                    getDocument().getDrawingIdManager().reserve(anchor.getDocPr().getId());
                }
            }
            for (CTInline inline : ctDrawing.getInlineArray()) {
                if (inline.getDocPr() != null) {
                    getDocument().getDrawingIdManager().reserve(inline.getDocPr().getId());
                }
            }
        }
        StringBuilder text = new StringBuilder();
        List<XmlObject> pictTextObjs = new ArrayList<>();
        pictTextObjs.addAll(Arrays.asList(r.getPictArray()));
        pictTextObjs.addAll(Arrays.asList(r.getDrawingArray()));
        Iterator<XmlObject> it = pictTextObjs.iterator();
        while (it.hasNext()) {
            XmlObject[] ts = it.next().selectPath("declare namespace w='http://schemas.openxmlformats.org/wordprocessingml/2006/main' .//w:t");
            for (XmlObject t : ts) {
                NodeList kids = t.getDomNode().getChildNodes();
                for (int n = 0; n < kids.getLength(); n++) {
                    if (kids.item(n) instanceof Text) {
                        if (text.length() > 0) {
                            text.append(StringUtils.LF);
                        }
                        text.append(kids.item(n).getNodeValue());
                    }
                }
            }
        }
        this.pictureText = text.toString();
        this.pictures = new ArrayList();
        this.charts = new ArrayList();
        for (XmlObject o : pictTextObjs) {
            for (CTPicture pict : getCTPictures(o)) {
                XWPFPicture picture = new XWPFPicture(pict, this);
                this.pictures.add(picture);
            }
            XmlObject[] chartRels = o.selectPath("declare namespace c='" + CTChart.type.getName().getNamespaceURI() + "' .//*/c:chart");
            for (XmlObject chartRel : chartRels) {
                if (chartRel instanceof CTRelId) {
                    POIXMLDocumentPart chart = getDocument().getRelationById(((CTRelId) chartRel).getId());
                    if (chart instanceof XWPFChart) {
                        this.charts.add((XWPFChart) chart);
                    }
                }
            }
        }
    }

    @Removal(version = "7.0.0")
    @Deprecated
    public XWPFRun(CTR r, XWPFParagraph p) {
        this(r, (IRunBody) p);
    }

    static void preserveSpaces(XmlString xs) {
        String text = xs.getStringValue();
        if (text != null && !text.isEmpty()) {
            if (Character.isWhitespace(text.charAt(0)) || Character.isWhitespace(text.charAt(text.length() - 1))) {
                XmlCursor c = xs.newCursor();
                try {
                    c.toNextToken();
                    c.insertAttributeWithValue(new QName("http://www.w3.org/XML/1998/namespace", "space"), "preserve");
                    if (c != null) {
                        c.close();
                    }
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
    }

    private List<CTPicture> getCTPictures(XmlObject o) {
        List<CTPicture> pics = new ArrayList<>();
        XmlObject[] picts = o.selectPath("declare namespace pic='" + CTPicture.type.getName().getNamespaceURI() + "' .//pic:pic");
        int length = picts.length;
        for (int i = 0; i < length; i++) {
            XmlObject pict = picts[i];
            if (pict instanceof XmlAnyTypeImpl) {
                try {
                    pict = CTPicture.Factory.parse(pict.toString(), POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
                } catch (XmlException e) {
                    throw new POIXMLException(e);
                }
            }
            if (pict instanceof CTPicture) {
                pics.add((CTPicture) pict);
            }
        }
        return pics;
    }

    @Internal
    public CTR getCTR() {
        return this.run;
    }

    public IRunBody getParent() {
        return this.parent;
    }

    @Deprecated
    public XWPFParagraph getParagraph() {
        if (this.parent instanceof XWPFParagraph) {
            return (XWPFParagraph) this.parent;
        }
        return null;
    }

    public XWPFDocument getDocument() {
        if (this.parent != null) {
            return this.parent.getDocument();
        }
        return null;
    }

    private static boolean isCTOnOff(CTOnOff onoff) {
        return !onoff.isSetVal() || POIXMLUnits.parseOnOff(onoff);
    }

    public String getLang() {
        CTRPr pr = getRunProperties(false);
        if (pr == null || pr.sizeOfLangArray() == 0) {
            return null;
        }
        return pr.getLangArray(0).getVal();
    }

    public void setLang(String lang) {
        CTRPr pr = getRunProperties(true);
        CTLanguage ctLang = pr.sizeOfLangArray() > 0 ? pr.getLangArray(0) : pr.addNewLang();
        ctLang.setVal(lang);
    }

    @Override // org.apache.poi.wp.usermodel.CharacterRun
    public boolean isBold() {
        CTRPr pr = getRunProperties(false);
        return pr != null && pr.sizeOfBArray() > 0 && isCTOnOff(pr.getBArray(0));
    }

    public boolean isComplexScriptBold() {
        CTRPr pr = getRunProperties(false);
        return pr != null && pr.sizeOfBCsArray() > 0 && isCTOnOff(pr.getBCsArray(0));
    }

    @Override // org.apache.poi.wp.usermodel.CharacterRun
    public void setBold(boolean value) {
        CTRPr pr = getRunProperties(true);
        CTOnOff bold = pr.sizeOfBArray() > 0 ? pr.getBArray(0) : pr.addNewB();
        bold.setVal(value ? STOnOff1.ON : STOnOff1.OFF);
    }

    public void setComplexScriptBold(boolean value) {
        CTRPr pr = getRunProperties(true);
        CTOnOff bold = pr.sizeOfBCsArray() > 0 ? pr.getBCsArray(0) : pr.addNewBCs();
        bold.setVal(value ? STOnOff1.ON : STOnOff1.OFF);
    }

    public String getColor() {
        CTRPr pr;
        if (!this.run.isSetRPr() || (pr = getRunProperties(false)) == null || pr.sizeOfColorArray() <= 0) {
            return null;
        }
        CTColor clr = pr.getColorArray(0);
        String color = clr.xgetVal().getStringValue();
        return color;
    }

    public void setColor(String rgbStr) {
        CTRPr pr = getRunProperties(true);
        CTColor color = pr.sizeOfColorArray() > 0 ? pr.getColorArray(0) : pr.addNewColor();
        color.setVal(rgbStr);
    }

    public int getNumberOfTexts() {
        return this.run.sizeOfTArray();
    }

    public String getText(int pos) {
        if (this.run.sizeOfTArray() == 0) {
            return null;
        }
        return this.run.getTArray(pos).getStringValue();
    }

    public String getPictureText() {
        return this.pictureText;
    }

    public void setText(String value) {
        setText(value, this.run.sizeOfTArray());
    }

    public void setText(String value, int pos) {
        if (pos > this.run.sizeOfTArray()) {
            throw new ArrayIndexOutOfBoundsException("Value too large for the parameter position in XWPFRun.setText(String value,int pos)");
        }
        CTText t = (pos >= this.run.sizeOfTArray() || pos < 0) ? this.run.addNewT() : this.run.getTArray(pos);
        t.setStringValue(value);
        preserveSpaces(t);
    }

    @Override // org.apache.poi.wp.usermodel.CharacterRun
    public boolean isItalic() {
        CTRPr pr = getRunProperties(false);
        return pr != null && pr.sizeOfIArray() > 0 && isCTOnOff(pr.getIArray(0));
    }

    public boolean isComplexScriptItalic() {
        CTRPr pr = getRunProperties(false);
        return pr != null && pr.sizeOfICsArray() > 0 && isCTOnOff(pr.getICsArray(0));
    }

    @Override // org.apache.poi.wp.usermodel.CharacterRun
    public void setItalic(boolean value) {
        CTRPr pr = getRunProperties(true);
        CTOnOff italic = pr.sizeOfIArray() > 0 ? pr.getIArray(0) : pr.addNewI();
        italic.setVal(value ? STOnOff1.ON : STOnOff1.OFF);
    }

    public void setComplexScriptItalic(boolean value) {
        CTRPr pr = getRunProperties(true);
        CTOnOff italic = pr.sizeOfICsArray() > 0 ? pr.getICsArray(0) : pr.addNewICs();
        italic.setVal(value ? STOnOff1.ON : STOnOff1.OFF);
    }

    public UnderlinePatterns getUnderline() {
        STUnderline.Enum baseValue;
        UnderlinePatterns value = UnderlinePatterns.NONE;
        CTUnderline underline = getCTUnderline(false);
        if (underline != null && (baseValue = underline.getVal()) != null) {
            return UnderlinePatterns.valueOf(baseValue.intValue());
        }
        return value;
    }

    public void setUnderline(UnderlinePatterns value) {
        CTUnderline underline = getCTUnderline(true);
        if (underline == null) {
            throw new AssertionError();
        }
        underline.setVal(STUnderline.Enum.forInt(value.getValue()));
    }

    private CTUnderline getCTUnderline(boolean create) {
        CTRPr pr = getRunProperties(create);
        if (pr == null) {
            return null;
        }
        if (pr.sizeOfUArray() > 0) {
            return pr.getUArray(0);
        }
        if (create) {
            return pr.addNewU();
        }
        return null;
    }

    public void setUnderlineColor(String color) {
        SimpleValue svColor;
        CTUnderline underline = getCTUnderline(true);
        if (underline == null) {
            throw new AssertionError();
        }
        if (color.equals(DebugKt.DEBUG_PROPERTY_VALUE_AUTO)) {
            STHexColorAuto hexColor = STHexColorAuto.Factory.newInstance();
            hexColor.setEnumValue(STHexColorAuto.Enum.forString(color));
            svColor = (SimpleValue) hexColor;
        } else {
            STHexColorRGB rgbColor = STHexColorRGB.Factory.newInstance();
            rgbColor.setStringValue(color);
            svColor = (SimpleValue) rgbColor;
        }
        underline.setColor(svColor);
    }

    public void setUnderlineThemeColor(String themeColor) {
        CTUnderline underline = getCTUnderline(true);
        if (underline == null) {
            throw new AssertionError();
        }
        STThemeColor.Enum val = STThemeColor.Enum.forString(themeColor);
        if (val != null) {
            underline.setThemeColor(val);
        }
    }

    public STThemeColor.Enum getUnderlineThemeColor() {
        CTUnderline underline = getCTUnderline(false);
        STThemeColor.Enum color = STThemeColor.NONE;
        if (underline != null) {
            STThemeColor.Enum color2 = underline.getThemeColor();
            return color2;
        }
        return color;
    }

    public String getUnderlineColor() {
        Object rawValue;
        CTUnderline underline = getCTUnderline(false);
        if (underline == null || (rawValue = underline.getColor()) == null) {
            return DebugKt.DEBUG_PROPERTY_VALUE_AUTO;
        }
        if (rawValue instanceof String) {
            String colorName = (String) rawValue;
            return colorName;
        }
        byte[] rgbColor = (byte[]) rawValue;
        String colorName2 = HexDump.toHex(rgbColor[0]) + HexDump.toHex(rgbColor[1]) + HexDump.toHex(rgbColor[2]);
        return colorName2;
    }

    @Override // org.apache.poi.wp.usermodel.CharacterRun
    public boolean isStrikeThrough() {
        CTRPr pr = getRunProperties(false);
        return pr != null && pr.sizeOfStrikeArray() > 0 && isCTOnOff(pr.getStrikeArray(0));
    }

    @Override // org.apache.poi.wp.usermodel.CharacterRun
    public void setStrikeThrough(boolean value) {
        CTRPr pr = getRunProperties(true);
        CTOnOff strike = pr.sizeOfStrikeArray() > 0 ? pr.getStrikeArray(0) : pr.addNewStrike();
        strike.setVal(value ? STOnOff1.ON : STOnOff1.OFF);
    }

    @Deprecated
    public boolean isStrike() {
        return isStrikeThrough();
    }

    @Deprecated
    public void setStrike(boolean value) {
        setStrikeThrough(value);
    }

    @Override // org.apache.poi.wp.usermodel.CharacterRun
    public boolean isDoubleStrikeThrough() {
        CTRPr pr = getRunProperties(false);
        return pr != null && pr.sizeOfDstrikeArray() > 0 && isCTOnOff(pr.getDstrikeArray(0));
    }

    @Override // org.apache.poi.wp.usermodel.CharacterRun
    public void setDoubleStrikethrough(boolean value) {
        CTRPr pr = getRunProperties(true);
        CTOnOff dstrike = pr.sizeOfDstrikeArray() > 0 ? pr.getDstrikeArray(0) : pr.addNewDstrike();
        dstrike.setVal(value ? STOnOff1.ON : STOnOff1.OFF);
    }

    @Override // org.apache.poi.wp.usermodel.CharacterRun
    public boolean isSmallCaps() {
        CTRPr pr = getRunProperties(false);
        return pr != null && pr.sizeOfSmallCapsArray() > 0 && isCTOnOff(pr.getSmallCapsArray(0));
    }

    @Override // org.apache.poi.wp.usermodel.CharacterRun
    public void setSmallCaps(boolean value) {
        CTRPr pr = getRunProperties(true);
        CTOnOff caps = pr.sizeOfSmallCapsArray() > 0 ? pr.getSmallCapsArray(0) : pr.addNewSmallCaps();
        caps.setVal(value ? STOnOff1.ON : STOnOff1.OFF);
    }

    @Override // org.apache.poi.wp.usermodel.CharacterRun
    public boolean isCapitalized() {
        CTRPr pr = getRunProperties(false);
        return pr != null && pr.sizeOfCapsArray() > 0 && isCTOnOff(pr.getCapsArray(0));
    }

    @Override // org.apache.poi.wp.usermodel.CharacterRun
    public void setCapitalized(boolean value) {
        CTRPr pr = getRunProperties(true);
        CTOnOff caps = pr.sizeOfCapsArray() > 0 ? pr.getCapsArray(0) : pr.addNewCaps();
        caps.setVal(value ? STOnOff1.ON : STOnOff1.OFF);
    }

    @Override // org.apache.poi.wp.usermodel.CharacterRun
    public boolean isShadowed() {
        CTRPr pr = getRunProperties(false);
        return pr != null && pr.sizeOfShadowArray() > 0 && isCTOnOff(pr.getShadowArray(0));
    }

    @Override // org.apache.poi.wp.usermodel.CharacterRun
    public void setShadow(boolean value) {
        CTRPr pr = getRunProperties(true);
        CTOnOff shadow = pr.sizeOfShadowArray() > 0 ? pr.getShadowArray(0) : pr.addNewShadow();
        shadow.setVal(value ? STOnOff1.ON : STOnOff1.OFF);
    }

    @Override // org.apache.poi.wp.usermodel.CharacterRun
    public boolean isImprinted() {
        CTRPr pr = getRunProperties(false);
        return pr != null && pr.sizeOfImprintArray() > 0 && isCTOnOff(pr.getImprintArray(0));
    }

    @Override // org.apache.poi.wp.usermodel.CharacterRun
    public void setImprinted(boolean value) {
        CTRPr pr = getRunProperties(true);
        CTOnOff imprinted = pr.sizeOfImprintArray() > 0 ? pr.getImprintArray(0) : pr.addNewImprint();
        imprinted.setVal(value ? STOnOff1.ON : STOnOff1.OFF);
    }

    @Override // org.apache.poi.wp.usermodel.CharacterRun
    public boolean isEmbossed() {
        CTRPr pr = getRunProperties(false);
        return pr != null && pr.sizeOfEmbossArray() > 0 && isCTOnOff(pr.getEmbossArray(0));
    }

    @Override // org.apache.poi.wp.usermodel.CharacterRun
    public void setEmbossed(boolean value) {
        CTRPr pr = getRunProperties(true);
        CTOnOff emboss = pr.sizeOfEmbossArray() > 0 ? pr.getEmbossArray(0) : pr.addNewEmboss();
        emboss.setVal(value ? STOnOff1.ON : STOnOff1.OFF);
    }

    public void setSubscript(VerticalAlign valign) {
        CTRPr pr = getRunProperties(true);
        CTVerticalAlignRun ctValign = pr.sizeOfVertAlignArray() > 0 ? pr.getVertAlignArray(0) : pr.addNewVertAlign();
        ctValign.setVal(STVerticalAlignRun.Enum.forInt(valign.getValue()));
    }

    @Override // org.apache.poi.wp.usermodel.CharacterRun
    public int getKerning() {
        CTRPr pr = getRunProperties(false);
        if (pr == null || pr.sizeOfKernArray() == 0) {
            return 0;
        }
        return (int) POIXMLUnits.parseLength(pr.getKernArray(0).xgetVal());
    }

    @Override // org.apache.poi.wp.usermodel.CharacterRun
    public void setKerning(int kern) {
        CTRPr pr = getRunProperties(true);
        CTHpsMeasure kernmes = pr.sizeOfKernArray() > 0 ? pr.getKernArray(0) : pr.addNewKern();
        kernmes.setVal(BigInteger.valueOf(kern));
    }

    @Override // org.apache.poi.wp.usermodel.CharacterRun
    public boolean isHighlighted() {
        STHighlightColor.Enum val;
        CTRPr pr = getRunProperties(false);
        return (pr == null || pr.sizeOfHighlightArray() == 0 || (val = pr.getHighlightArray(0).getVal()) == null || val == STHighlightColor.NONE) ? false : true;
    }

    @Override // org.apache.poi.wp.usermodel.CharacterRun
    public int getCharacterSpacing() {
        CTRPr pr = getRunProperties(false);
        if (pr == null || pr.sizeOfSpacingArray() == 0) {
            return 0;
        }
        return (int) Units.toDXA(POIXMLUnits.parseLength(pr.getSpacingArray(0).xgetVal()));
    }

    @Override // org.apache.poi.wp.usermodel.CharacterRun
    public void setCharacterSpacing(int twips) {
        CTRPr pr = getRunProperties(true);
        CTSignedTwipsMeasure spc = pr.sizeOfSpacingArray() > 0 ? pr.getSpacingArray(0) : pr.addNewSpacing();
        spc.setVal(BigInteger.valueOf(twips));
    }

    public String getFontFamily() {
        return getFontFamily(null);
    }

    public void setFontFamily(String fontFamily) {
        setFontFamily(fontFamily, null);
    }

    @Override // org.apache.poi.wp.usermodel.CharacterRun
    public String getFontName() {
        return getFontFamily();
    }

    public String getFontFamily(FontCharRange fcr) {
        CTRPr pr = getRunProperties(false);
        if (pr == null || pr.sizeOfRFontsArray() == 0) {
            return null;
        }
        CTFonts fonts = pr.getRFontsArray(0);
        switch (fcr == null ? FontCharRange.ascii : fcr) {
            case cs:
                return fonts.getCs();
            case eastAsia:
                return fonts.getEastAsia();
            case hAnsi:
                return fonts.getHAnsi();
            default:
                return fonts.getAscii();
        }
    }

    public void setFontFamily(String fontFamily, FontCharRange fcr) {
        CTRPr pr = getRunProperties(true);
        CTFonts fonts = pr.sizeOfRFontsArray() > 0 ? pr.getRFontsArray(0) : pr.addNewRFonts();
        if (fcr == null) {
            fonts.setAscii(fontFamily);
            if (!fonts.isSetHAnsi()) {
                fonts.setHAnsi(fontFamily);
            }
            if (!fonts.isSetCs()) {
                fonts.setCs(fontFamily);
            }
            if (!fonts.isSetEastAsia()) {
                fonts.setEastAsia(fontFamily);
                return;
            }
            return;
        }
        switch (fcr) {
            case ascii:
                fonts.setAscii(fontFamily);
                return;
            case cs:
                fonts.setCs(fontFamily);
                return;
            case eastAsia:
                fonts.setEastAsia(fontFamily);
                return;
            case hAnsi:
                fonts.setHAnsi(fontFamily);
                return;
            default:
                return;
        }
    }

    @Override // org.apache.poi.wp.usermodel.CharacterRun
    @Removal(version = "6.0.0")
    @Deprecated
    public int getFontSize() {
        BigDecimal bd = getFontSizeAsBigDecimal(0);
        if (bd == null) {
            return -1;
        }
        return bd.intValue();
    }

    @Override // org.apache.poi.wp.usermodel.CharacterRun
    public Double getFontSizeAsDouble() {
        BigDecimal bd = getFontSizeAsBigDecimal(1);
        if (bd == null) {
            return null;
        }
        return Double.valueOf(bd.doubleValue());
    }

    public Double getComplexScriptFontSizeAsDouble() {
        BigDecimal bd = getComplexScriptFontSizeAsBigDecimal(1);
        if (bd == null) {
            return null;
        }
        return Double.valueOf(bd.doubleValue());
    }

    private BigDecimal getFontSizeAsBigDecimal(int scale) {
        CTRPr pr = getRunProperties(false);
        if (pr == null || pr.sizeOfSzArray() <= 0) {
            return null;
        }
        return BigDecimal.valueOf(Units.toPoints(POIXMLUnits.parseLength(pr.getSzArray(0).xgetVal()))).divide(BigDecimal.valueOf(4L), scale, RoundingMode.HALF_UP);
    }

    private BigDecimal getComplexScriptFontSizeAsBigDecimal(int scale) {
        CTRPr pr = getRunProperties(false);
        if (pr == null || pr.sizeOfSzCsArray() <= 0) {
            return null;
        }
        return BigDecimal.valueOf(Units.toPoints(POIXMLUnits.parseLength(pr.getSzCsArray(0).xgetVal()))).divide(BigDecimal.valueOf(4L), scale, RoundingMode.HALF_UP);
    }

    @Override // org.apache.poi.wp.usermodel.CharacterRun
    public void setFontSize(int size) {
        BigInteger bint = BigInteger.valueOf(size);
        CTRPr pr = getRunProperties(true);
        CTHpsMeasure ctSize = pr.sizeOfSzArray() > 0 ? pr.getSzArray(0) : pr.addNewSz();
        ctSize.setVal(bint.multiply(BigInteger.valueOf(2L)));
    }

    public void setComplexScriptFontSize(int size) {
        CTRPr pr = getRunProperties(true);
        BigInteger bint = BigInteger.valueOf(size);
        CTHpsMeasure ctCsSize = pr.sizeOfSzCsArray() > 0 ? pr.getSzCsArray(0) : pr.addNewSzCs();
        ctCsSize.setVal(bint.multiply(BigInteger.valueOf(2L)));
    }

    @Override // org.apache.poi.wp.usermodel.CharacterRun
    public void setFontSize(double size) {
        BigDecimal bd = BigDecimal.valueOf(size);
        CTRPr pr = getRunProperties(true);
        CTHpsMeasure ctSize = pr.sizeOfSzArray() > 0 ? pr.getSzArray(0) : pr.addNewSz();
        ctSize.setVal(bd.multiply(BigDecimal.valueOf(2L)).setScale(0, RoundingMode.HALF_UP).toBigInteger());
    }

    public void setComplexScriptFontSize(double size) {
        CTRPr pr = getRunProperties(true);
        BigDecimal bd = BigDecimal.valueOf(size);
        CTHpsMeasure ctCsSize = pr.sizeOfSzCsArray() > 0 ? pr.getSzCsArray(0) : pr.addNewSzCs();
        ctCsSize.setVal(bd.multiply(BigDecimal.valueOf(2L)).setScale(0, RoundingMode.HALF_UP).toBigInteger());
    }

    public int getTextPosition() {
        CTRPr pr = getRunProperties(false);
        if (pr == null || pr.sizeOfPositionArray() <= 0) {
            return -1;
        }
        return (int) (Units.toPoints(POIXMLUnits.parseLength(pr.getPositionArray(0).xgetVal())) / 2.0d);
    }

    public void setTextPosition(int val) {
        BigInteger bint = new BigInteger(Integer.toString(val));
        CTRPr pr = getRunProperties(true);
        CTSignedHpsMeasure position = pr.sizeOfPositionArray() > 0 ? pr.getPositionArray(0) : pr.addNewPosition();
        position.setVal(bint);
    }

    public void removeBreak() {
    }

    public void addBreak() {
        this.run.addNewBr();
    }

    public void addBreak(BreakType type) {
        CTBr br = this.run.addNewBr();
        br.setType(STBrType.Enum.forInt(type.getValue()));
    }

    public void addBreak(BreakClear clear) {
        CTBr br = this.run.addNewBr();
        br.setType(STBrType.Enum.forInt(BreakType.TEXT_WRAPPING.getValue()));
        br.setClear(STBrClear.Enum.forInt(clear.getValue()));
    }

    public void addTab() {
        this.run.addNewTab();
    }

    public void removeTab() {
    }

    public void addCarriageReturn() {
        this.run.addNewCr();
    }

    public void removeCarriageReturn() {
    }

    @Removal(version = "7.0.0")
    @Deprecated
    public XWPFPicture addPicture(InputStream pictureData, int pictureType, String filename, int width, int height) throws InvalidFormatException, IOException {
        return addPicture(pictureData, PictureType.findByOoxmlId(pictureType), filename, width, height);
    }

    public XWPFPicture addPicture(InputStream pictureData, PictureType pictureType, String filename, int width, int height) throws InvalidFormatException, IOException {
        XWPFPictureData picData;
        if (pictureType == null) {
            throw new InvalidFormatException("pictureType is not supported");
        }
        if (!(this.parent.getPart() instanceof XWPFHeaderFooter)) {
            if (this.parent.getPart() instanceof XWPFComments) {
                XWPFComments comments = (XWPFComments) this.parent.getPart();
                String relationId = comments.addPictureData(pictureData, pictureType);
                XWPFPictureData picData2 = (XWPFPictureData) comments.getRelationById(relationId);
                picData = picData2;
            } else {
                XWPFDocument doc = this.parent.getDocument();
                String relationId2 = doc.addPictureData(pictureData, pictureType);
                picData = (XWPFPictureData) doc.getRelationById(relationId2);
            }
        } else {
            XWPFHeaderFooter headerFooter = (XWPFHeaderFooter) this.parent.getPart();
            String relationId3 = headerFooter.addPictureData(pictureData, pictureType);
            XWPFPictureData picData3 = (XWPFPictureData) headerFooter.getRelationById(relationId3);
            picData = picData3;
        }
        try {
            CTDrawing drawing = this.run.addNewDrawing();
            CTInline inline = drawing.addNewInline();
            String xml = "<a:graphic xmlns:a=\"" + CTGraphicalObject.type.getName().getNamespaceURI() + "\"><a:graphicData uri=\"" + CTPicture.type.getName().getNamespaceURI() + "\"><pic:pic xmlns:pic=\"" + CTPicture.type.getName().getNamespaceURI() + "\" /></a:graphicData></a:graphic>";
            InputSource is = new InputSource(new StringReader(xml));
            inline.set(XmlToken.Factory.parse(DocumentHelper.readDocument(is).getDocumentElement(), POIXMLTypeLoader.DEFAULT_XML_OPTIONS));
            inline.setDistT(0L);
            inline.setDistR(0L);
            inline.setDistB(0L);
            inline.setDistL(0L);
            CTNonVisualDrawingProps docPr = inline.addNewDocPr();
            long id = getParent().getDocument().getDrawingIdManager().reserveNew();
            docPr.setId(id);
            docPr.setName("Drawing " + id);
            docPr.setDescr(filename);
            CTPositiveSize2D extent = inline.addNewExtent();
            extent.setCx(width);
            extent.setCy(height);
            CTGraphicalObject graphic = inline.getGraphic();
            CTGraphicalObjectData graphicData = graphic.getGraphicData();
            CTPicture pic = getCTPictures(graphicData).get(0);
            CTPictureNonVisual nvPicPr = pic.addNewNvPicPr();
            CTNonVisualDrawingProps cNvPr = nvPicPr.addNewCNvPr();
            cNvPr.setId(0L);
            cNvPr.setName("Picture " + id);
            cNvPr.setDescr(filename);
            CTNonVisualPictureProperties cNvPicPr = nvPicPr.addNewCNvPicPr();
            cNvPicPr.addNewPicLocks().setNoChangeAspect(true);
            CTBlipFillProperties blipFill = pic.addNewBlipFill();
            CTBlip blip = blipFill.addNewBlip();
            blip.setEmbed(this.parent.getPart().getRelationId(picData));
            blipFill.addNewStretch().addNewFillRect();
            CTShapeProperties spPr = pic.addNewSpPr();
            CTTransform2D xfrm = spPr.addNewXfrm();
            CTPoint2D off = xfrm.addNewOff();
            off.setX(0);
            off.setY(0);
            CTPositiveSize2D ext = xfrm.addNewExt();
            ext.setCx(width);
            ext.setCy(height);
            CTPresetGeometry2D prstGeom = spPr.addNewPrstGeom();
            prstGeom.setPrst(STShapeType.RECT);
            prstGeom.addNewAvLst();
            XWPFPicture xwpfPicture = new XWPFPicture(pic, this);
            this.pictures.add(xwpfPicture);
            return xwpfPicture;
        } catch (XmlException | SAXException e) {
            throw new IllegalStateException(e);
        }
    }

    @Internal
    public CTInline addChart(String chartRelId) throws InvalidFormatException, IOException {
        try {
            POIXMLDocumentPart chart = getDocument().getRelationById(chartRelId);
            if (chart instanceof XWPFChart) {
                this.charts.add((XWPFChart) chart);
            }
            CTInline inline = this.run.addNewDrawing().addNewInline();
            String xml = "<a:graphic xmlns:a=\"" + CTGraphicalObject.type.getName().getNamespaceURI() + "\"><a:graphicData uri=\"" + CTChart.type.getName().getNamespaceURI() + "\"><c:chart xmlns:c=\"" + CTChart.type.getName().getNamespaceURI() + "\" xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\" r:id=\"" + chartRelId + "\" /></a:graphicData></a:graphic>";
            InputSource is = new InputSource(new StringReader(xml));
            org.w3c.dom.Document doc = DocumentHelper.readDocument(is);
            inline.set(XmlToken.Factory.parse(doc.getDocumentElement(), POIXMLTypeLoader.DEFAULT_XML_OPTIONS));
            inline.setDistT(0L);
            inline.setDistR(0L);
            inline.setDistB(0L);
            inline.setDistL(0L);
            CTNonVisualDrawingProps docPr = inline.addNewDocPr();
            long id = getParent().getDocument().getDrawingIdManager().reserveNew();
            docPr.setId(id);
            docPr.setName("chart " + id);
            return inline;
        } catch (XmlException | SAXException e) {
            throw new IllegalStateException(e);
        }
    }

    public List<XWPFPicture> getEmbeddedPictures() {
        return Collections.unmodifiableList(this.pictures);
    }

    public void setStyle(String styleId) {
        CTRPr pr = getCTR().getRPr();
        if (pr == null) {
            pr = getCTR().addNewRPr();
        }
        CTString style = pr.sizeOfRStyleArray() > 0 ? pr.getRStyleArray(0) : pr.addNewRStyle();
        style.setVal(styleId);
    }

    public String getStyle() {
        CTString style;
        CTRPr pr = getCTR().getRPr();
        return (pr == null || pr.sizeOfRStyleArray() <= 0 || (style = pr.getRStyleArray(0)) == null) ? "" : style.getVal();
    }

    public String toString() {
        String phonetic = getPhonetic();
        if (!phonetic.isEmpty()) {
            return text() + " (" + phonetic + ")";
        }
        return text();
    }

    @Override // org.apache.poi.wp.usermodel.CharacterRun
    public String text() {
        StringBuilder text = new StringBuilder(64);
        XmlCursor c = this.run.newCursor();
        try {
            c.selectPath("./*");
            while (c.toNextSelection()) {
                XmlObject o = c.getObject();
                if (o instanceof CTRuby) {
                    handleRuby(o, text, false);
                } else {
                    _getText(o, text);
                }
            }
            if (c != null) {
                c.close();
            }
            return text.toString();
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

    public String getPhonetic() {
        StringBuilder text = new StringBuilder(64);
        XmlCursor c = this.run.newCursor();
        try {
            c.selectPath("./*");
            while (c.toNextSelection()) {
                XmlObject o = c.getObject();
                if (o instanceof CTRuby) {
                    handleRuby(o, text, true);
                }
            }
            if (this.pictureText != null && !this.pictureText.isEmpty()) {
                text.append(StringUtils.LF).append(this.pictureText).append(StringUtils.LF);
            }
            if (c != null) {
                c.close();
            }
            return text.toString();
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

    private void handleRuby(XmlObject rubyObj, StringBuilder text, boolean extractPhonetic) {
        XmlCursor c = rubyObj.newCursor();
        try {
            c.selectPath(".//*");
            boolean inRT = false;
            boolean inBase = false;
            while (c.toNextSelection()) {
                XmlObject o = c.getObject();
                if (o instanceof CTRubyContent) {
                    Node node = o.getDomNode();
                    if (XSSFRelation.NS_WORDPROCESSINGML.equals(node.getNamespaceURI())) {
                        String tagName = node.getLocalName();
                        if ("rt".equals(tagName)) {
                            inRT = true;
                        } else if ("rubyBase".equals(tagName)) {
                            inRT = false;
                            inBase = true;
                        }
                    }
                } else if (extractPhonetic && inRT) {
                    _getText(o, text);
                } else if (!extractPhonetic && inBase) {
                    _getText(o, text);
                }
            }
            if (c != null) {
                c.close();
            }
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

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void _getText(XmlObject o, StringBuilder text) {
        StringBuilder sb;
        String str;
        char c;
        String textValue;
        if (o instanceof CTText) {
            Node node = o.getDomNode();
            if (((!"instrText".equals(node.getLocalName()) && !"delInstrText".equals(node.getLocalName())) || !XSSFRelation.NS_WORDPROCESSINGML.equals(node.getNamespaceURI())) && (textValue = ((CTText) o).getStringValue()) != null) {
                if (isCapitalized() || isSmallCaps()) {
                    textValue = textValue.toUpperCase(LocaleUtil.getUserLocale());
                }
                text.append(textValue);
            }
        }
        if (o instanceof CTFldChar) {
            CTFldChar ctfldChar = (CTFldChar) o;
            if (ctfldChar.getFldCharType() == STFldCharType.BEGIN && ctfldChar.getFfData() != null) {
                for (CTFFCheckBox checkBox : ctfldChar.getFfData().getCheckBoxList()) {
                    text.append((checkBox.getDefault() == null || !POIXMLUnits.parseOnOff(checkBox.getDefault().xgetVal())) ? "|_|" : "|X|");
                }
            }
        }
        if (o instanceof CTPTab) {
            text.append('\t');
        }
        if (o instanceof CTBr) {
            text.append('\n');
        }
        if (o instanceof CTEmpty) {
            Node node2 = o.getDomNode();
            if (XSSFRelation.NS_WORDPROCESSINGML.equals(node2.getNamespaceURI())) {
                String localName = node2.getLocalName();
                switch (localName.hashCode()) {
                    case 3152:
                        if (localName.equals(CompressorStreamFactory.BROTLI)) {
                            c = 2;
                            break;
                        }
                        c = 65535;
                        break;
                    case 3183:
                        if (localName.equals("cr")) {
                            c = 3;
                            break;
                        }
                        c = 65535;
                        break;
                    case 114581:
                        if (localName.equals("tab")) {
                            c = 1;
                            break;
                        }
                        c = 65535;
                        break;
                    case 2001013392:
                        if (localName.equals("noBreakHyphen")) {
                            c = 0;
                            break;
                        }
                        c = 65535;
                        break;
                    default:
                        c = 65535;
                        break;
                }
                switch (c) {
                    case 0:
                        text.append((char) 8209);
                        break;
                    case 1:
                        text.append('\t');
                        break;
                    case 2:
                    case 3:
                        text.append('\n');
                        break;
                }
            }
        }
        if (o instanceof CTFtnEdnRef) {
            CTFtnEdnRef ftn = (CTFtnEdnRef) o;
            int i = ftn.getId() != null ? ftn.getId().intValue() : -1;
            if (ftn.getDomNode().getLocalName().equals("footnoteReference")) {
                sb = new StringBuilder();
                str = "[footnoteRef:";
            } else {
                sb = new StringBuilder();
                str = "[endnoteRef:";
            }
            String footnoteRef = sb.append(str).append(i).append(CollectionUtils.DEFAULT_TOSTRING_SUFFIX).toString();
            text.append(footnoteRef);
        }
    }

    public void setTextScale(int percentage) {
        CTRPr pr = getRunProperties(true);
        CTTextScale scale = pr.sizeOfWArray() > 0 ? pr.getWArray(0) : pr.addNewW();
        scale.setVal(Integer.valueOf(percentage));
    }

    public int getTextScale() {
        int value;
        CTRPr pr = getRunProperties(false);
        if (pr == null || pr.sizeOfWArray() == 0 || (value = POIXMLUnits.parsePercent(pr.getWArray(0).xgetVal())) == 0) {
            return 100;
        }
        return value / 1000;
    }

    public void setTextHighlightColor(String colorName) {
        CTRPr pr = getRunProperties(true);
        CTHighlight highlight = pr.sizeOfHighlightArray() > 0 ? pr.getHighlightArray(0) : pr.addNewHighlight();
        STHighlightColor color = highlight.xgetVal();
        if (color == null) {
            color = STHighlightColor.Factory.newInstance();
        }
        STHighlightColor.Enum val = STHighlightColor.Enum.forString(colorName);
        if (val != null) {
            color.setStringValue(val.toString());
            highlight.xsetVal(color);
        }
    }

    @Removal(version = "7.0.0")
    @Deprecated
    public STHighlightColor.Enum getTextHightlightColor() {
        return getTextHighlightColor();
    }

    public STHighlightColor.Enum getTextHighlightColor() {
        CTRPr pr = getRunProperties(false);
        if (pr == null) {
            return STHighlightColor.NONE;
        }
        CTHighlight highlight = pr.sizeOfHighlightArray() > 0 ? pr.getHighlightArray(0) : pr.addNewHighlight();
        STHighlightColor color = highlight.xgetVal();
        if (color == null) {
            color = STHighlightColor.Factory.newInstance();
            color.setEnumValue(STHighlightColor.NONE);
        }
        return (STHighlightColor.Enum) color.getEnumValue();
    }

    public boolean isVanish() {
        CTRPr pr = getRunProperties(false);
        return pr != null && pr.sizeOfVanishArray() > 0 && isCTOnOff(pr.getVanishArray(0));
    }

    public void setVanish(boolean value) {
        CTRPr pr = getRunProperties(true);
        CTOnOff vanish = pr.sizeOfVanishArray() > 0 ? pr.getVanishArray(0) : pr.addNewVanish();
        vanish.setVal(value ? STOnOff1.ON : STOnOff1.OFF);
    }

    public STVerticalAlignRun.Enum getVerticalAlignment() {
        CTRPr pr = getRunProperties(false);
        if (pr == null) {
            return STVerticalAlignRun.BASELINE;
        }
        CTVerticalAlignRun vertAlign = pr.sizeOfVertAlignArray() > 0 ? pr.getVertAlignArray(0) : pr.addNewVertAlign();
        STVerticalAlignRun.Enum val = vertAlign.getVal();
        if (val == null) {
            return STVerticalAlignRun.BASELINE;
        }
        return val;
    }

    public void setVerticalAlignment(String verticalAlignment) {
        CTRPr pr = getRunProperties(true);
        CTVerticalAlignRun vertAlign = pr.sizeOfVertAlignArray() > 0 ? pr.getVertAlignArray(0) : pr.addNewVertAlign();
        STVerticalAlignRun align = vertAlign.xgetVal();
        if (align == null) {
            align = STVerticalAlignRun.Factory.newInstance();
        }
        STVerticalAlignRun.Enum val = STVerticalAlignRun.Enum.forString(verticalAlignment);
        if (val != null) {
            align.setStringValue(val.toString());
            vertAlign.xsetVal(align);
        }
    }

    public STEm.Enum getEmphasisMark() {
        CTRPr pr = getRunProperties(false);
        if (pr == null) {
            return STEm.NONE;
        }
        CTEm emphasis = pr.sizeOfEmArray() > 0 ? pr.getEmArray(0) : pr.addNewEm();
        STEm.Enum val = emphasis.getVal();
        if (val == null) {
            return STEm.NONE;
        }
        return val;
    }

    public void setEmphasisMark(String markType) {
        CTRPr pr = getRunProperties(true);
        CTEm emphasisMark = pr.sizeOfEmArray() > 0 ? pr.getEmArray(0) : pr.addNewEm();
        STEm mark = emphasisMark.xgetVal();
        if (mark == null) {
            mark = STEm.Factory.newInstance();
        }
        STEm.Enum val = STEm.Enum.forString(markType);
        if (val != null) {
            mark.setStringValue(val.toString());
            emphasisMark.xsetVal(mark);
        }
    }

    protected CTRPr getRunProperties(boolean create) {
        CTRPr pr = this.run.isSetRPr() ? this.run.getRPr() : null;
        if (create && pr == null) {
            return this.run.addNewRPr();
        }
        return pr;
    }

    public List<XWPFChart> getEmbeddedCharts() {
        return Collections.unmodifiableList(this.charts);
    }
}
