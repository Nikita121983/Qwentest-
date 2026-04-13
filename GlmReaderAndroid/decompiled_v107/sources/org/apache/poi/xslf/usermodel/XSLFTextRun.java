package org.apache.poi.xslf.usermodel;

import java.awt.Color;
import java.util.function.Consumer;
import kotlin.UByte;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.poi.common.usermodel.fonts.FontCharset;
import org.apache.poi.common.usermodel.fonts.FontFamily;
import org.apache.poi.common.usermodel.fonts.FontGroup;
import org.apache.poi.common.usermodel.fonts.FontInfo;
import org.apache.poi.common.usermodel.fonts.FontPitch;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.openxml4j.exceptions.OpenXML4JRuntimeException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.sl.draw.DrawPaint;
import org.apache.poi.sl.usermodel.HighlightColorSupport;
import org.apache.poi.sl.usermodel.PaintStyle;
import org.apache.poi.sl.usermodel.TextRun;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Units;
import org.apache.poi.xslf.model.CharacterPropertyFetcher;
import org.apache.poi.xslf.usermodel.XSLFPropertiesDelegate;
import org.apache.poi.xslf.usermodel.XSLFTextRun;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTFontCollection;
import org.openxmlformats.schemas.drawingml.x2006.main.CTFontScheme;
import org.openxmlformats.schemas.drawingml.x2006.main.CTHyperlink;
import org.openxmlformats.schemas.drawingml.x2006.main.CTRegularTextRun;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSRgbColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSolidColorFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextField;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextFont;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextLineBreak;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextNormalAutofit;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextStrikeType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextUnderlineType;

/* loaded from: classes10.dex */
public class XSLFTextRun implements TextRun, HighlightColorSupport {
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) XSLFTextRun.class);
    private final XSLFTextParagraph _p;
    private final XmlObject _r;

    /* JADX INFO: Access modifiers changed from: protected */
    public XSLFTextRun(XmlObject r, XSLFTextParagraph p) {
        this._r = r;
        this._p = p;
        if (!(r instanceof CTRegularTextRun) && !(r instanceof CTTextLineBreak) && !(r instanceof CTTextField)) {
            throw new OpenXML4JRuntimeException("unsupported text run of type " + r.getClass());
        }
    }

    @Override // org.apache.poi.sl.usermodel.TextRun
    public String getRawText() {
        if (this._r instanceof CTTextField) {
            return ((CTTextField) this._r).getT();
        }
        if (this._r instanceof CTTextLineBreak) {
            return StringUtils.LF;
        }
        return ((CTRegularTextRun) this._r).getT();
    }

    @Override // org.apache.poi.sl.usermodel.TextRun
    public void setText(String text) {
        if (this._r instanceof CTTextField) {
            ((CTTextField) this._r).setT(text);
        } else if (!(this._r instanceof CTTextLineBreak)) {
            ((CTRegularTextRun) this._r).setT(text);
        }
    }

    @Internal
    public XmlObject getXmlObject() {
        return this._r;
    }

    @Override // org.apache.poi.sl.usermodel.TextRun
    public void setFontColor(Color color) {
        setFontColor(DrawPaint.createSolidPaint(color));
    }

    /* JADX WARN: Type inference failed for: r4v2, types: [org.apache.poi.xslf.usermodel.XSLFSheet] */
    @Override // org.apache.poi.sl.usermodel.TextRun
    public void setFontColor(PaintStyle color) {
        if (!(color instanceof PaintStyle.SolidPaint)) {
            LOG.atWarn().log("Currently only SolidPaint is supported!");
            return;
        }
        PaintStyle.SolidPaint sp = (PaintStyle.SolidPaint) color;
        Color c = DrawPaint.applyColorTransform(sp.getSolidColor());
        CTTextCharacterProperties rPr = getRPr(true);
        CTSolidColorFillProperties fill = rPr.isSetSolidFill() ? rPr.getSolidFill() : rPr.addNewSolidFill();
        ?? sheet = getParagraph().getParentShape().getSheet();
        XSLFColor col = new XSLFColor(fill, sheet.getTheme(), fill.getSchemeClr(), sheet);
        col.setColor(c);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [org.apache.poi.xslf.usermodel.XSLFShape, org.apache.poi.xslf.usermodel.XSLFTextShape] */
    @Override // org.apache.poi.sl.usermodel.TextRun
    public PaintStyle getFontColor() {
        final ?? parentShape = getParagraph().getParentShape();
        final boolean hasPlaceholder = parentShape.getPlaceholder() != null;
        return (PaintStyle) fetchCharacterProperty(new CharacterPropertyFetcher.CharPropFetcher() { // from class: org.apache.poi.xslf.usermodel.XSLFTextRun$$ExternalSyntheticLambda4
            @Override // org.apache.poi.xslf.model.CharacterPropertyFetcher.CharPropFetcher
            public final void fetch(CTTextCharacterProperties cTTextCharacterProperties, Consumer consumer) {
                XSLFTextRun.fetchFontColor(cTTextCharacterProperties, consumer, XSLFShape.this, hasPlaceholder);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r1v1, types: [org.apache.poi.xslf.usermodel.XSLFSheet] */
    public static void fetchFontColor(CTTextCharacterProperties props, Consumer<PaintStyle> val, XSLFShape shape, boolean hasPlaceholder) {
        CTSchemeColor phClr;
        if (props == null) {
            return;
        }
        CTShapeStyle style = shape.getSpStyle();
        if (style != null && style.getFontRef() != null) {
            CTSchemeColor phClr2 = style.getFontRef().getSchemeClr();
            phClr = phClr2;
        } else {
            phClr = null;
        }
        XSLFPropertiesDelegate.XSLFFillProperties fp = XSLFPropertiesDelegate.getFillDelegate(props);
        ?? sheet = shape.getSheet();
        PackagePart pp = sheet.getPackagePart();
        XSLFTheme theme = sheet.getTheme();
        PaintStyle ps = shape.selectPaint(fp, phClr, pp, theme, hasPlaceholder);
        if (ps != null) {
            val.accept(ps);
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [org.apache.poi.xslf.usermodel.XSLFShape, org.apache.poi.xslf.usermodel.XSLFTextShape] */
    @Override // org.apache.poi.sl.usermodel.HighlightColorSupport
    public PaintStyle getHighlightColor() {
        final ?? parentShape = getParagraph().getParentShape();
        final boolean hasPlaceholder = parentShape.getPlaceholder() != null;
        return (PaintStyle) fetchCharacterProperty(new CharacterPropertyFetcher.CharPropFetcher() { // from class: org.apache.poi.xslf.usermodel.XSLFTextRun$$ExternalSyntheticLambda1
            @Override // org.apache.poi.xslf.model.CharacterPropertyFetcher.CharPropFetcher
            public final void fetch(CTTextCharacterProperties cTTextCharacterProperties, Consumer consumer) {
                XSLFTextRun.fetchHighlightColor(cTTextCharacterProperties, consumer, XSLFShape.this, hasPlaceholder);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void fetchHighlightColor(CTTextCharacterProperties props, Consumer<PaintStyle> highlightColor, XSLFShape shape, boolean hasPlaceholder) {
        CTColor col;
        if (props == null || (col = props.getHighlight()) == null) {
            return;
        }
        CTSRgbColor rgbCol = col.getSrgbClr();
        byte[] cols = rgbCol.getVal();
        PaintStyle.SolidPaint paint = DrawPaint.createSolidPaint(new Color(cols[0] & UByte.MAX_VALUE, cols[1] & UByte.MAX_VALUE, cols[2] & UByte.MAX_VALUE));
        highlightColor.accept(paint);
    }

    @Override // org.apache.poi.sl.usermodel.HighlightColorSupport
    public void setHighlightColor(Color color) {
        setHighlightColor(DrawPaint.createSolidPaint(color));
    }

    @Override // org.apache.poi.sl.usermodel.HighlightColorSupport
    public void setHighlightColor(PaintStyle color) {
        if (color == null) {
            CTTextCharacterProperties rPr = getRPr(true);
            if (rPr.isSetHighlight()) {
                rPr.unsetHighlight();
                return;
            }
            return;
        }
        if (!(color instanceof PaintStyle.SolidPaint)) {
            throw new IllegalArgumentException("Currently only SolidPaint is supported!");
        }
        PaintStyle.SolidPaint sp = (PaintStyle.SolidPaint) color;
        Color c = DrawPaint.applyColorTransform(sp.getSolidColor());
        CTTextCharacterProperties rPr2 = getRPr(true);
        CTColor highlight = rPr2.isSetHighlight() ? rPr2.getHighlight() : rPr2.addNewHighlight();
        CTSRgbColor col = CTSRgbColor.Factory.newInstance();
        col.setVal(new byte[]{(byte) c.getRed(), (byte) c.getGreen(), (byte) c.getBlue()});
        highlight.setSrgbClr(col);
    }

    @Override // org.apache.poi.sl.usermodel.TextRun
    public void setFontSize(Double fontSize) {
        CTTextCharacterProperties rPr = getRPr(true);
        if (fontSize == null) {
            if (rPr.isSetSz()) {
                rPr.unsetSz();
            }
        } else {
            if (fontSize.doubleValue() < 1.0d) {
                throw new IllegalArgumentException("Minimum font size is 1pt but was " + fontSize);
            }
            rPr.setSz((int) (fontSize.doubleValue() * 100.0d));
        }
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [org.apache.poi.xslf.usermodel.XSLFTextShape] */
    @Override // org.apache.poi.sl.usermodel.TextRun
    public Double getFontSize() {
        CTTextBodyProperties tbp;
        CTTextNormalAutofit afit;
        double scale = 1.0d;
        ?? parentShape = getParagraph().getParentShape();
        if (parentShape != 0 && (tbp = parentShape.getTextBodyPr()) != null && (afit = tbp.getNormAutofit()) != null && afit.isSetFontScale()) {
            scale = POIXMLUnits.parsePercent(afit.xgetFontScale()) / 100000.0d;
        }
        Double d = (Double) fetchCharacterProperty(new CharacterPropertyFetcher.CharPropFetcher() { // from class: org.apache.poi.xslf.usermodel.XSLFTextRun$$ExternalSyntheticLambda9
            @Override // org.apache.poi.xslf.model.CharacterPropertyFetcher.CharPropFetcher
            public final void fetch(CTTextCharacterProperties cTTextCharacterProperties, Consumer consumer) {
                XSLFTextRun.lambda$getFontSize$2(cTTextCharacterProperties, consumer);
            }
        });
        if (d == null) {
            return null;
        }
        return Double.valueOf(d.doubleValue() * scale);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getFontSize$2(CTTextCharacterProperties props, Consumer val) {
        if (props.isSetSz()) {
            val.accept(Double.valueOf(props.getSz() * 0.01d));
        }
    }

    public double getCharacterSpacing() {
        Double d = (Double) fetchCharacterProperty(new CharacterPropertyFetcher.CharPropFetcher() { // from class: org.apache.poi.xslf.usermodel.XSLFTextRun$$ExternalSyntheticLambda6
            @Override // org.apache.poi.xslf.model.CharacterPropertyFetcher.CharPropFetcher
            public final void fetch(CTTextCharacterProperties cTTextCharacterProperties, Consumer consumer) {
                XSLFTextRun.lambda$getCharacterSpacing$3(cTTextCharacterProperties, consumer);
            }
        });
        if (d == null) {
            return 0.0d;
        }
        return d.doubleValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getCharacterSpacing$3(CTTextCharacterProperties props, Consumer val) {
        if (props.isSetSpc()) {
            val.accept(Double.valueOf(Units.toPoints(POIXMLUnits.parseLength(props.xgetSpc()))));
        }
    }

    public void setCharacterSpacing(double spc) {
        CTTextCharacterProperties rPr = getRPr(true);
        if (spc == 0.0d) {
            if (rPr.isSetSpc()) {
                rPr.unsetSpc();
                return;
            }
            return;
        }
        rPr.setSpc(Integer.valueOf((int) (100.0d * spc)));
    }

    @Override // org.apache.poi.sl.usermodel.TextRun
    public void setFontFamily(String typeface) {
        FontGroup fg = FontGroup.getFontGroupFirst(getRawText());
        new XSLFFontInfo(fg).setTypeface(typeface);
    }

    @Override // org.apache.poi.sl.usermodel.TextRun
    public void setFontFamily(String typeface, FontGroup fontGroup) {
        new XSLFFontInfo(fontGroup).setTypeface(typeface);
    }

    @Override // org.apache.poi.sl.usermodel.TextRun
    public void setFontInfo(FontInfo fontInfo, FontGroup fontGroup) {
        new XSLFFontInfo(fontGroup).copyFrom(fontInfo);
    }

    @Override // org.apache.poi.sl.usermodel.TextRun
    public String getFontFamily() {
        FontGroup fg = FontGroup.getFontGroupFirst(getRawText());
        return new XSLFFontInfo(fg).getTypeface();
    }

    @Override // org.apache.poi.sl.usermodel.TextRun
    public String getFontFamily(FontGroup fontGroup) {
        return new XSLFFontInfo(fontGroup).getTypeface();
    }

    @Override // org.apache.poi.sl.usermodel.TextRun
    public FontInfo getFontInfo(FontGroup fontGroup) {
        XSLFFontInfo fontInfo = new XSLFFontInfo(fontGroup);
        if (fontInfo.getTypeface() != null) {
            return fontInfo;
        }
        return null;
    }

    @Override // org.apache.poi.sl.usermodel.TextRun
    public byte getPitchAndFamily() {
        FontGroup fg = FontGroup.getFontGroupFirst(getRawText());
        XSLFFontInfo fontInfo = new XSLFFontInfo(fg);
        FontPitch pitch = fontInfo.getPitch();
        if (pitch == null) {
            pitch = FontPitch.VARIABLE;
        }
        FontFamily family = fontInfo.getFamily();
        if (family == null) {
            family = FontFamily.FF_SWISS;
        }
        return FontPitch.getNativeId(pitch, family);
    }

    @Override // org.apache.poi.sl.usermodel.TextRun
    public void setStrikethrough(boolean strike) {
        getRPr(true).setStrike(strike ? STTextStrikeType.SNG_STRIKE : STTextStrikeType.NO_STRIKE);
    }

    @Override // org.apache.poi.sl.usermodel.TextRun
    public boolean isStrikethrough() {
        Boolean b = (Boolean) fetchCharacterProperty(new CharacterPropertyFetcher.CharPropFetcher() { // from class: org.apache.poi.xslf.usermodel.XSLFTextRun$$ExternalSyntheticLambda0
            @Override // org.apache.poi.xslf.model.CharacterPropertyFetcher.CharPropFetcher
            public final void fetch(CTTextCharacterProperties cTTextCharacterProperties, Consumer consumer) {
                XSLFTextRun.lambda$isStrikethrough$4(cTTextCharacterProperties, consumer);
            }
        });
        return b != null && b.booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$isStrikethrough$4(CTTextCharacterProperties props, Consumer val) {
        if (props.isSetStrike()) {
            val.accept(Boolean.valueOf(props.getStrike() != STTextStrikeType.NO_STRIKE));
        }
    }

    @Override // org.apache.poi.sl.usermodel.TextRun
    public boolean isSuperscript() {
        Boolean b = (Boolean) fetchCharacterProperty(new CharacterPropertyFetcher.CharPropFetcher() { // from class: org.apache.poi.xslf.usermodel.XSLFTextRun$$ExternalSyntheticLambda10
            @Override // org.apache.poi.xslf.model.CharacterPropertyFetcher.CharPropFetcher
            public final void fetch(CTTextCharacterProperties cTTextCharacterProperties, Consumer consumer) {
                XSLFTextRun.lambda$isSuperscript$5(cTTextCharacterProperties, consumer);
            }
        });
        return b != null && b.booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$isSuperscript$5(CTTextCharacterProperties props, Consumer val) {
        if (props.isSetBaseline()) {
            val.accept(Boolean.valueOf(POIXMLUnits.parsePercent(props.xgetBaseline()) > 0));
        }
    }

    public void setBaselineOffset(double baselineOffset) {
        getRPr(true).setBaseline(Integer.valueOf(((int) baselineOffset) * 1000));
    }

    public void setSuperscript(boolean flag) {
        setBaselineOffset(flag ? 30.0d : 0.0d);
    }

    public void setSubscript(boolean flag) {
        setBaselineOffset(flag ? -25.0d : 0.0d);
    }

    @Override // org.apache.poi.sl.usermodel.TextRun
    public boolean isSubscript() {
        Boolean b = (Boolean) fetchCharacterProperty(new CharacterPropertyFetcher.CharPropFetcher() { // from class: org.apache.poi.xslf.usermodel.XSLFTextRun$$ExternalSyntheticLambda3
            @Override // org.apache.poi.xslf.model.CharacterPropertyFetcher.CharPropFetcher
            public final void fetch(CTTextCharacterProperties cTTextCharacterProperties, Consumer consumer) {
                XSLFTextRun.lambda$isSubscript$6(cTTextCharacterProperties, consumer);
            }
        });
        return b != null && b.booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$isSubscript$6(CTTextCharacterProperties props, Consumer val) {
        if (props.isSetBaseline()) {
            val.accept(Boolean.valueOf(POIXMLUnits.parsePercent(props.xgetBaseline()) < 0));
        }
    }

    @Override // org.apache.poi.sl.usermodel.TextRun
    public TextRun.TextCap getTextCap() {
        TextRun.TextCap textCap = (TextRun.TextCap) fetchCharacterProperty(new CharacterPropertyFetcher.CharPropFetcher() { // from class: org.apache.poi.xslf.usermodel.XSLFTextRun$$ExternalSyntheticLambda5
            @Override // org.apache.poi.xslf.model.CharacterPropertyFetcher.CharPropFetcher
            public final void fetch(CTTextCharacterProperties cTTextCharacterProperties, Consumer consumer) {
                XSLFTextRun.lambda$getTextCap$7(cTTextCharacterProperties, consumer);
            }
        });
        return textCap == null ? TextRun.TextCap.NONE : textCap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getTextCap$7(CTTextCharacterProperties props, Consumer val) {
        if (props.isSetCap()) {
            val.accept(TextRun.TextCap.values()[props.getCap().intValue() - 1]);
        }
    }

    @Override // org.apache.poi.sl.usermodel.TextRun
    public void setBold(boolean bold) {
        getRPr(true).setB(bold);
    }

    @Override // org.apache.poi.sl.usermodel.TextRun
    public boolean isBold() {
        Boolean b = (Boolean) fetchCharacterProperty(new CharacterPropertyFetcher.CharPropFetcher() { // from class: org.apache.poi.xslf.usermodel.XSLFTextRun$$ExternalSyntheticLambda8
            @Override // org.apache.poi.xslf.model.CharacterPropertyFetcher.CharPropFetcher
            public final void fetch(CTTextCharacterProperties cTTextCharacterProperties, Consumer consumer) {
                XSLFTextRun.lambda$isBold$8(cTTextCharacterProperties, consumer);
            }
        });
        return b != null && b.booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$isBold$8(CTTextCharacterProperties props, Consumer val) {
        if (props.isSetB()) {
            val.accept(Boolean.valueOf(props.getB()));
        }
    }

    @Override // org.apache.poi.sl.usermodel.TextRun
    public void setItalic(boolean italic) {
        getRPr(true).setI(italic);
    }

    @Override // org.apache.poi.sl.usermodel.TextRun
    public boolean isItalic() {
        Boolean b = (Boolean) fetchCharacterProperty(new CharacterPropertyFetcher.CharPropFetcher() { // from class: org.apache.poi.xslf.usermodel.XSLFTextRun$$ExternalSyntheticLambda7
            @Override // org.apache.poi.xslf.model.CharacterPropertyFetcher.CharPropFetcher
            public final void fetch(CTTextCharacterProperties cTTextCharacterProperties, Consumer consumer) {
                XSLFTextRun.lambda$isItalic$9(cTTextCharacterProperties, consumer);
            }
        });
        return b != null && b.booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$isItalic$9(CTTextCharacterProperties props, Consumer val) {
        if (props.isSetI()) {
            val.accept(Boolean.valueOf(props.getI()));
        }
    }

    @Override // org.apache.poi.sl.usermodel.TextRun
    public void setUnderlined(boolean underline) {
        getRPr(true).setU(underline ? STTextUnderlineType.SNG : STTextUnderlineType.NONE);
    }

    @Override // org.apache.poi.sl.usermodel.TextRun
    public boolean isUnderlined() {
        Boolean b = (Boolean) fetchCharacterProperty(new CharacterPropertyFetcher.CharPropFetcher() { // from class: org.apache.poi.xslf.usermodel.XSLFTextRun$$ExternalSyntheticLambda2
            @Override // org.apache.poi.xslf.model.CharacterPropertyFetcher.CharPropFetcher
            public final void fetch(CTTextCharacterProperties cTTextCharacterProperties, Consumer consumer) {
                XSLFTextRun.lambda$isUnderlined$10(cTTextCharacterProperties, consumer);
            }
        });
        return b != null && b.booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$isUnderlined$10(CTTextCharacterProperties props, Consumer val) {
        if (props.isSetU()) {
            val.accept(Boolean.valueOf(props.getU() != STTextUnderlineType.NONE));
        }
    }

    @Internal
    public CTTextCharacterProperties getRPr(boolean create) {
        if (this._r instanceof CTTextField) {
            CTTextField tf = (CTTextField) this._r;
            if (tf.isSetRPr()) {
                return tf.getRPr();
            }
            if (create) {
                return tf.addNewRPr();
            }
        } else if (this._r instanceof CTTextLineBreak) {
            CTTextLineBreak tlb = (CTTextLineBreak) this._r;
            if (tlb.isSetRPr()) {
                return tlb.getRPr();
            }
            if (create) {
                return tlb.addNewRPr();
            }
        } else {
            CTRegularTextRun tr = (CTRegularTextRun) this._r;
            if (tr.isSetRPr()) {
                return tr.getRPr();
            }
            if (create) {
                return tr.addNewRPr();
            }
        }
        if (this._p.getXmlObject().isSetPPr() && this._p.getXmlObject().getPPr().isSetDefRPr()) {
            return this._p.getXmlObject().getPPr().getDefRPr();
        }
        return null;
    }

    public String toString() {
        return CollectionUtils.DEFAULT_TOSTRING_PREFIX + getClass() + CollectionUtils.DEFAULT_TOSTRING_SUFFIX + getRawText();
    }

    /* JADX WARN: Type inference failed for: r4v2, types: [org.apache.poi.xslf.usermodel.XSLFSheet] */
    @Override // org.apache.poi.sl.usermodel.TextRun
    public XSLFHyperlink createHyperlink() {
        XSLFHyperlink hl = getHyperlink();
        if (hl != null) {
            return hl;
        }
        CTTextCharacterProperties rPr = getRPr(true);
        return new XSLFHyperlink(rPr.addNewHlinkClick(), this._p.getParentShape().getSheet());
    }

    /* JADX WARN: Type inference failed for: r3v2, types: [org.apache.poi.xslf.usermodel.XSLFSheet] */
    @Override // org.apache.poi.sl.usermodel.TextRun
    public XSLFHyperlink getHyperlink() {
        CTHyperlink hl;
        CTTextCharacterProperties rPr = getRPr(false);
        if (rPr == null || (hl = rPr.getHlinkClick()) == null) {
            return null;
        }
        return new XSLFHyperlink(hl, this._p.getParentShape().getSheet());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [org.apache.poi.xslf.usermodel.XSLFShape, org.apache.poi.xslf.usermodel.XSLFTextShape] */
    public <T> T fetchCharacterProperty(CharacterPropertyFetcher.CharPropFetcher<T> charPropFetcher) {
        return (T) new CharacterPropertyFetcher(this, charPropFetcher).fetchProperty(this._p.getParentShape());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void copy(XSLFTextRun r) {
        String srcFontFamily = r.getFontFamily();
        if (srcFontFamily != null && !srcFontFamily.equals(getFontFamily())) {
            setFontFamily(srcFontFamily);
        }
        PaintStyle srcFontColor = r.getFontColor();
        if (srcFontColor != null && !srcFontColor.equals(getFontColor())) {
            setFontColor(srcFontColor);
        }
        Double srcFontSize = r.getFontSize();
        if (srcFontSize == null) {
            if (getFontSize() != null) {
                setFontSize(null);
            }
        } else if (!srcFontSize.equals(getFontSize())) {
            setFontSize(srcFontSize);
        }
        boolean bold = r.isBold();
        if (bold != isBold()) {
            setBold(bold);
        }
        boolean italic = r.isItalic();
        if (italic != isItalic()) {
            setItalic(italic);
        }
        boolean underline = r.isUnderlined();
        if (underline != isUnderlined()) {
            setUnderlined(underline);
        }
        boolean strike = r.isStrikethrough();
        if (strike != isStrikethrough()) {
            setStrikethrough(strike);
        }
        XSLFHyperlink hyperSrc = r.getHyperlink();
        if (hyperSrc != null) {
            XSLFHyperlink hyperDst = getHyperlink();
            hyperDst.copy(hyperSrc);
        }
    }

    @Override // org.apache.poi.sl.usermodel.TextRun
    public TextRun.FieldType getFieldType() {
        if (this._r instanceof CTTextField) {
            CTTextField tf = (CTTextField) this._r;
            if ("slidenum".equals(tf.getType())) {
                return TextRun.FieldType.SLIDE_NUMBER;
            }
            return null;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public final class XSLFFontInfo implements FontInfo {
        private final FontGroup fontGroup;

        private XSLFFontInfo(FontGroup fontGroup) {
            this.fontGroup = fontGroup != null ? fontGroup : FontGroup.getFontGroupFirst(XSLFTextRun.this.getRawText());
        }

        void copyFrom(FontInfo fontInfo) {
            CTTextFont tf = getXmlObject(true);
            if (tf == null) {
                return;
            }
            setTypeface(fontInfo.getTypeface());
            setCharset(fontInfo.getCharset());
            FontPitch pitch = fontInfo.getPitch();
            FontFamily family = fontInfo.getFamily();
            if (pitch == null && family == null) {
                if (tf.isSetPitchFamily()) {
                    tf.unsetPitchFamily();
                }
            } else {
                setPitch(pitch);
                setFamily(family);
            }
        }

        @Override // org.apache.poi.common.usermodel.fonts.FontInfo
        public String getTypeface() {
            CTTextFont tf = getXmlObject(false);
            if (tf != null) {
                return tf.getTypeface();
            }
            return null;
        }

        @Override // org.apache.poi.common.usermodel.fonts.FontInfo
        public void setTypeface(String typeface) {
            if (typeface != null) {
                CTTextFont tf = getXmlObject(true);
                if (tf != null) {
                    tf.setTypeface(typeface);
                    return;
                }
                return;
            }
            CTTextCharacterProperties props = XSLFTextRun.this.getRPr(false);
            if (props == null) {
                return;
            }
            FontGroup fg = FontGroup.getFontGroupFirst(XSLFTextRun.this.getRawText());
            switch (fg) {
                case EAST_ASIAN:
                    if (props.isSetEa()) {
                        props.unsetEa();
                        return;
                    }
                    return;
                case COMPLEX_SCRIPT:
                    if (props.isSetCs()) {
                        props.unsetCs();
                        return;
                    }
                    return;
                case SYMBOL:
                    if (props.isSetSym()) {
                        props.unsetSym();
                        return;
                    }
                    return;
                default:
                    if (props.isSetLatin()) {
                        props.unsetLatin();
                        return;
                    }
                    return;
            }
        }

        @Override // org.apache.poi.common.usermodel.fonts.FontInfo
        public FontCharset getCharset() {
            CTTextFont tf = getXmlObject(false);
            if (tf == null || !tf.isSetCharset()) {
                return null;
            }
            return FontCharset.valueOf(tf.getCharset() & UByte.MAX_VALUE);
        }

        @Override // org.apache.poi.common.usermodel.fonts.FontInfo
        public void setCharset(FontCharset charset) {
            CTTextFont tf = getXmlObject(true);
            if (tf == null) {
                return;
            }
            if (charset != null) {
                tf.setCharset((byte) charset.getNativeId());
            } else if (tf.isSetCharset()) {
                tf.unsetCharset();
            }
        }

        @Override // org.apache.poi.common.usermodel.fonts.FontInfo
        public FontFamily getFamily() {
            CTTextFont tf = getXmlObject(false);
            if (tf == null || !tf.isSetPitchFamily()) {
                return null;
            }
            return FontFamily.valueOfPitchFamily(tf.getPitchFamily());
        }

        @Override // org.apache.poi.common.usermodel.fonts.FontInfo
        public void setFamily(FontFamily family) {
            CTTextFont tf = getXmlObject(true);
            if (tf != null) {
                if (family == null && !tf.isSetPitchFamily()) {
                    return;
                }
                FontPitch pitch = tf.isSetPitchFamily() ? FontPitch.valueOfPitchFamily(tf.getPitchFamily()) : FontPitch.VARIABLE;
                byte pitchFamily = FontPitch.getNativeId(pitch, family != null ? family : FontFamily.FF_SWISS);
                tf.setPitchFamily(pitchFamily);
            }
        }

        @Override // org.apache.poi.common.usermodel.fonts.FontInfo
        public FontPitch getPitch() {
            CTTextFont tf = getXmlObject(false);
            if (tf == null || !tf.isSetPitchFamily()) {
                return null;
            }
            return FontPitch.valueOfPitchFamily(tf.getPitchFamily());
        }

        @Override // org.apache.poi.common.usermodel.fonts.FontInfo
        public void setPitch(FontPitch pitch) {
            CTTextFont tf = getXmlObject(true);
            if (tf != null) {
                if (pitch == null && !tf.isSetPitchFamily()) {
                    return;
                }
                FontFamily family = tf.isSetPitchFamily() ? FontFamily.valueOfPitchFamily(tf.getPitchFamily()) : FontFamily.FF_SWISS;
                byte pitchFamily = FontPitch.getNativeId(pitch != null ? pitch : FontPitch.VARIABLE, family);
                tf.setPitchFamily(pitchFamily);
            }
        }

        private CTTextFont getXmlObject(boolean create) {
            if (!create) {
                return (CTTextFont) XSLFTextRun.this.fetchCharacterProperty(new CharacterPropertyFetcher.CharPropFetcher() { // from class: org.apache.poi.xslf.usermodel.XSLFTextRun$XSLFFontInfo$$ExternalSyntheticLambda0
                    @Override // org.apache.poi.xslf.model.CharacterPropertyFetcher.CharPropFetcher
                    public final void fetch(CTTextCharacterProperties cTTextCharacterProperties, Consumer consumer) {
                        XSLFTextRun.XSLFFontInfo.this.m2580x99373bd6(cTTextCharacterProperties, consumer);
                    }
                });
            }
            return getCTTextFont(XSLFTextRun.this.getRPr(true), true);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$getXmlObject$0$org-apache-poi-xslf-usermodel-XSLFTextRun$XSLFFontInfo, reason: not valid java name */
        public /* synthetic */ void m2580x99373bd6(CTTextCharacterProperties props, Consumer val) {
            CTTextFont font = getCTTextFont(props, false);
            if (font != null) {
                val.accept(font);
            }
        }

        /* JADX WARN: Type inference failed for: r4v4, types: [org.apache.poi.xslf.usermodel.XSLFSheet] */
        private CTTextFont getCTTextFont(CTTextCharacterProperties props, boolean create) {
            CTTextFont font;
            if (props == null) {
                return null;
            }
            switch (this.fontGroup) {
                case EAST_ASIAN:
                    font = props.getEa();
                    if (font == null && create) {
                        font = props.addNewEa();
                        break;
                    }
                    break;
                case COMPLEX_SCRIPT:
                    font = props.getCs();
                    if (font == null && create) {
                        font = props.addNewCs();
                        break;
                    }
                    break;
                case SYMBOL:
                    font = props.getSym();
                    if (font == null && create) {
                        font = props.addNewSym();
                        break;
                    }
                    break;
                default:
                    font = props.getLatin();
                    if (font == null && create) {
                        font = props.addNewLatin();
                        break;
                    }
                    break;
            }
            if (font == null) {
                return null;
            }
            String typeface = font.getTypeface();
            if (typeface == null) {
                typeface = "";
            }
            if (typeface.startsWith("+mj-") || typeface.startsWith("+mn-")) {
                XSLFTheme theme = XSLFTextRun.this._p.getParentShape().getSheet().getTheme();
                CTFontScheme fontTheme = theme.getXmlObject().getThemeElements().getFontScheme();
                CTFontCollection coll = typeface.startsWith("+mj-") ? fontTheme.getMajorFont() : fontTheme.getMinorFont();
                String fgStr = typeface.substring(4);
                if ("ea".equals(fgStr)) {
                    font = coll.getEa();
                } else if ("cs".equals(fgStr)) {
                    font = coll.getCs();
                } else {
                    font = coll.getLatin();
                }
                if (font == null || font.getTypeface() == null || "".equals(font.getTypeface())) {
                    return null;
                }
            }
            return font;
        }
    }

    @Override // org.apache.poi.sl.usermodel.TextRun
    public XSLFTextParagraph getParagraph() {
        return this._p;
    }
}
