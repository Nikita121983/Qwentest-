package org.apache.poi.xslf.usermodel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.sl.draw.DrawPaint;
import org.apache.poi.sl.usermodel.AutoNumberingScheme;
import org.apache.poi.sl.usermodel.PaintStyle;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.sl.usermodel.TabStop;
import org.apache.poi.sl.usermodel.TextParagraph;
import org.apache.poi.sl.usermodel.TextShape;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Units;
import org.apache.poi.xslf.model.ParagraphPropertyFetcher;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTRegularTextRun;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSRgbColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextAutonumberBullet;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBulletSizePercent;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBulletSizePoint;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharBullet;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextField;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextFont;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextLineBreak;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextNormalAutofit;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacingPercent;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacingPoint;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextTabStop;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextTabStopList;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextAlignType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextAutonumberScheme;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextFontAlignType;
import org.openxmlformats.schemas.presentationml.x2006.main.CTPlaceholder;

/* loaded from: classes10.dex */
public class XSLFTextParagraph implements TextParagraph<XSLFShape, XSLFTextParagraph, XSLFTextRun> {
    private final CTTextParagraph _p;
    private final List<XSLFTextRun> _runs = new ArrayList();
    private final XSLFTextShape _shape;

    /* JADX INFO: Access modifiers changed from: private */
    @FunctionalInterface
    /* loaded from: classes10.dex */
    public interface Procedure {
        void accept();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0032, code lost:
    
        if ((r1 instanceof org.openxmlformats.schemas.drawingml.x2006.main.CTRegularTextRun) != false) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0036, code lost:
    
        if ((r1 instanceof org.openxmlformats.schemas.drawingml.x2006.main.CTTextField) == false) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0038, code lost:
    
        r5._runs.add(newTextRun(r1));
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0047, code lost:
    
        if (r0 == null) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0049, code lost:
    
        r0.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x004c, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x0018, code lost:
    
        if (r0.toFirstChild() != false) goto L5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x001a, code lost:
    
        r1 = r0.getObject();
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0020, code lost:
    
        if ((r1 instanceof org.openxmlformats.schemas.drawingml.x2006.main.CTTextLineBreak) == false) goto L8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0022, code lost:
    
        r5._runs.add(new org.apache.poi.xslf.usermodel.XSLFLineBreak((org.openxmlformats.schemas.drawingml.x2006.main.CTTextLineBreak) r1, r5));
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0045, code lost:
    
        if (r0.toNextSibling() != false) goto L34;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public XSLFTextParagraph(org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph r6, org.apache.poi.xslf.usermodel.XSLFTextShape r7) {
        /*
            r5 = this;
            r5.<init>()
            r5._p = r6
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r5._runs = r0
            r5._shape = r7
            org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph r0 = r5._p
            org.apache.xmlbeans.XmlCursor r0 = r0.newCursor()
            boolean r1 = r0.toFirstChild()     // Catch: java.lang.Throwable -> L4d
            if (r1 == 0) goto L47
        L1a:
            org.apache.xmlbeans.XmlObject r1 = r0.getObject()     // Catch: java.lang.Throwable -> L4d
            boolean r2 = r1 instanceof org.openxmlformats.schemas.drawingml.x2006.main.CTTextLineBreak     // Catch: java.lang.Throwable -> L4d
            if (r2 == 0) goto L30
            java.util.List<org.apache.poi.xslf.usermodel.XSLFTextRun> r2 = r5._runs     // Catch: java.lang.Throwable -> L4d
            org.apache.poi.xslf.usermodel.XSLFLineBreak r3 = new org.apache.poi.xslf.usermodel.XSLFLineBreak     // Catch: java.lang.Throwable -> L4d
            r4 = r1
            org.openxmlformats.schemas.drawingml.x2006.main.CTTextLineBreak r4 = (org.openxmlformats.schemas.drawingml.x2006.main.CTTextLineBreak) r4     // Catch: java.lang.Throwable -> L4d
            r3.<init>(r4, r5)     // Catch: java.lang.Throwable -> L4d
            r2.add(r3)     // Catch: java.lang.Throwable -> L4d
            goto L41
        L30:
            boolean r2 = r1 instanceof org.openxmlformats.schemas.drawingml.x2006.main.CTRegularTextRun     // Catch: java.lang.Throwable -> L4d
            if (r2 != 0) goto L38
            boolean r2 = r1 instanceof org.openxmlformats.schemas.drawingml.x2006.main.CTTextField     // Catch: java.lang.Throwable -> L4d
            if (r2 == 0) goto L41
        L38:
            java.util.List<org.apache.poi.xslf.usermodel.XSLFTextRun> r2 = r5._runs     // Catch: java.lang.Throwable -> L4d
            org.apache.poi.xslf.usermodel.XSLFTextRun r3 = r5.newTextRun(r1)     // Catch: java.lang.Throwable -> L4d
            r2.add(r3)     // Catch: java.lang.Throwable -> L4d
        L41:
            boolean r1 = r0.toNextSibling()     // Catch: java.lang.Throwable -> L4d
            if (r1 != 0) goto L1a
        L47:
            if (r0 == 0) goto L4c
            r0.close()
        L4c:
            return
        L4d:
            r1 = move-exception
            throw r1     // Catch: java.lang.Throwable -> L4f
        L4f:
            r2 = move-exception
            if (r0 == 0) goto L5a
            r0.close()     // Catch: java.lang.Throwable -> L56
            goto L5a
        L56:
            r3 = move-exception
            r1.addSuppressed(r3)
        L5a:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.usermodel.XSLFTextParagraph.<init>(org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph, org.apache.poi.xslf.usermodel.XSLFTextShape):void");
    }

    public String getText() {
        StringBuilder out = new StringBuilder();
        for (XSLFTextRun r : this._runs) {
            out.append(r.getRawText());
        }
        return out.toString();
    }

    @Internal
    public CTTextParagraph getXmlObject() {
        return this._p;
    }

    @Override // org.apache.poi.sl.usermodel.TextParagraph
    public TextShape<XSLFShape, XSLFTextParagraph> getParentShape() {
        return this._shape;
    }

    @Override // org.apache.poi.sl.usermodel.TextParagraph
    public List<XSLFTextRun> getTextRuns() {
        return Collections.unmodifiableList(this._runs);
    }

    @Override // java.lang.Iterable
    public Iterator<XSLFTextRun> iterator() {
        return getTextRuns().iterator();
    }

    public XSLFTextRun addNewTextRun() {
        CTRegularTextRun r = this._p.addNewR();
        CTTextCharacterProperties rPr = r.addNewRPr();
        rPr.setLang("en-US");
        XSLFTextRun run = newTextRun(r);
        this._runs.add(run);
        return run;
    }

    public boolean removeTextRun(XSLFTextRun textRun) {
        if (!this._runs.remove(textRun)) {
            return false;
        }
        XmlObject xo = textRun.getXmlObject();
        if (xo instanceof CTRegularTextRun) {
            for (int i = 0; i < getXmlObject().sizeOfRArray(); i++) {
                if (getXmlObject().getRArray(i).equals(xo)) {
                    getXmlObject().removeR(i);
                    return true;
                }
            }
        } else if (xo instanceof CTTextField) {
            for (int i2 = 0; i2 < getXmlObject().sizeOfFldArray(); i2++) {
                if (getXmlObject().getFldArray(i2).equals(xo)) {
                    getXmlObject().removeFld(i2);
                    return true;
                }
            }
        } else if (xo instanceof CTTextLineBreak) {
            for (int i3 = 0; i3 < getXmlObject().sizeOfBrArray(); i3++) {
                if (getXmlObject().getBrArray(i3).equals(xo)) {
                    getXmlObject().removeBr(i3);
                    return true;
                }
            }
        }
        return false;
    }

    public XSLFTextRun addLineBreak() {
        XSLFLineBreak run = new XSLFLineBreak(this._p.addNewBr(), this);
        CTTextCharacterProperties brProps = run.getRPr(true);
        if (!this._runs.isEmpty()) {
            CTTextCharacterProperties prevRun = this._runs.get(this._runs.size() - 1).getRPr(true);
            brProps.set(prevRun);
            if (brProps.isSetHlinkClick()) {
                brProps.unsetHlinkClick();
            }
            if (brProps.isSetHlinkMouseOver()) {
                brProps.unsetHlinkMouseOver();
            }
        }
        this._runs.add(run);
        return run;
    }

    @Override // org.apache.poi.sl.usermodel.TextParagraph
    public TextParagraph.TextAlign getTextAlign() {
        return (TextParagraph.TextAlign) fetchParagraphProperty(new ParagraphPropertyFetcher.ParaPropFetcher() { // from class: org.apache.poi.xslf.usermodel.XSLFTextParagraph$$ExternalSyntheticLambda14
            @Override // org.apache.poi.xslf.model.ParagraphPropertyFetcher.ParaPropFetcher
            public final void fetch(CTTextParagraphProperties cTTextParagraphProperties, Consumer consumer) {
                XSLFTextParagraph.lambda$getTextAlign$0(cTTextParagraphProperties, consumer);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getTextAlign$0(CTTextParagraphProperties props, Consumer val) {
        if (props.isSetAlgn()) {
            val.accept(TextParagraph.TextAlign.values()[props.getAlgn().intValue() - 1]);
        }
    }

    @Override // org.apache.poi.sl.usermodel.TextParagraph
    public void setTextAlign(TextParagraph.TextAlign align) {
        CTTextParagraphProperties pr = this._p.isSetPPr() ? this._p.getPPr() : this._p.addNewPPr();
        if (align == null) {
            if (pr.isSetAlgn()) {
                pr.unsetAlgn();
                return;
            }
            return;
        }
        pr.setAlgn(STTextAlignType.Enum.forInt(align.ordinal() + 1));
    }

    @Override // org.apache.poi.sl.usermodel.TextParagraph
    public TextParagraph.FontAlign getFontAlign() {
        return (TextParagraph.FontAlign) fetchParagraphProperty(new ParagraphPropertyFetcher.ParaPropFetcher() { // from class: org.apache.poi.xslf.usermodel.XSLFTextParagraph$$ExternalSyntheticLambda19
            @Override // org.apache.poi.xslf.model.ParagraphPropertyFetcher.ParaPropFetcher
            public final void fetch(CTTextParagraphProperties cTTextParagraphProperties, Consumer consumer) {
                XSLFTextParagraph.lambda$getFontAlign$1(cTTextParagraphProperties, consumer);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getFontAlign$1(CTTextParagraphProperties props, Consumer val) {
        if (props.isSetFontAlgn()) {
            val.accept(TextParagraph.FontAlign.values()[props.getFontAlgn().intValue() - 1]);
        }
    }

    public void setFontAlign(TextParagraph.FontAlign align) {
        CTTextParagraphProperties pr = this._p.isSetPPr() ? this._p.getPPr() : this._p.addNewPPr();
        if (align == null) {
            if (pr.isSetFontAlgn()) {
                pr.unsetFontAlgn();
                return;
            }
            return;
        }
        pr.setFontAlgn(STTextFontAlignType.Enum.forInt(align.ordinal() + 1));
    }

    public String getBulletFont() {
        return (String) fetchParagraphProperty(new ParagraphPropertyFetcher.ParaPropFetcher() { // from class: org.apache.poi.xslf.usermodel.XSLFTextParagraph$$ExternalSyntheticLambda23
            @Override // org.apache.poi.xslf.model.ParagraphPropertyFetcher.ParaPropFetcher
            public final void fetch(CTTextParagraphProperties cTTextParagraphProperties, Consumer consumer) {
                XSLFTextParagraph.lambda$getBulletFont$2(cTTextParagraphProperties, consumer);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getBulletFont$2(CTTextParagraphProperties props, Consumer val) {
        if (props.isSetBuFont()) {
            val.accept(props.getBuFont().getTypeface());
        }
    }

    public void setBulletFont(String typeface) {
        CTTextParagraphProperties pr = this._p.isSetPPr() ? this._p.getPPr() : this._p.addNewPPr();
        CTTextFont font = pr.isSetBuFont() ? pr.getBuFont() : pr.addNewBuFont();
        font.setTypeface(typeface);
    }

    public String getBulletCharacter() {
        return (String) fetchParagraphProperty(new ParagraphPropertyFetcher.ParaPropFetcher() { // from class: org.apache.poi.xslf.usermodel.XSLFTextParagraph$$ExternalSyntheticLambda4
            @Override // org.apache.poi.xslf.model.ParagraphPropertyFetcher.ParaPropFetcher
            public final void fetch(CTTextParagraphProperties cTTextParagraphProperties, Consumer consumer) {
                XSLFTextParagraph.lambda$getBulletCharacter$3(cTTextParagraphProperties, consumer);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getBulletCharacter$3(CTTextParagraphProperties props, Consumer val) {
        if (props.isSetBuChar()) {
            val.accept(props.getBuChar().getChar());
        }
    }

    public void setBulletCharacter(String str) {
        CTTextParagraphProperties pr = this._p.isSetPPr() ? this._p.getPPr() : this._p.addNewPPr();
        CTTextCharBullet c = pr.isSetBuChar() ? pr.getBuChar() : pr.addNewBuChar();
        c.setChar(str);
    }

    public PaintStyle getBulletFontColor() {
        Color col = (Color) fetchParagraphProperty(new ParagraphPropertyFetcher.ParaPropFetcher() { // from class: org.apache.poi.xslf.usermodel.XSLFTextParagraph$$ExternalSyntheticLambda20
            @Override // org.apache.poi.xslf.model.ParagraphPropertyFetcher.ParaPropFetcher
            public final void fetch(CTTextParagraphProperties cTTextParagraphProperties, Consumer consumer) {
                XSLFTextParagraph.this.fetchBulletFontColor(cTTextParagraphProperties, consumer);
            }
        });
        if (col == null) {
            return null;
        }
        return DrawPaint.createSolidPaint(col);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r0v1, types: [org.apache.poi.xslf.usermodel.XSLFSheet] */
    public void fetchBulletFontColor(CTTextParagraphProperties props, Consumer<Color> val) {
        ?? sheet = getParentShape().getSheet();
        XSLFTheme theme = sheet.getTheme();
        if (props.isSetBuClr()) {
            XSLFColor c = new XSLFColor(props.getBuClr(), theme, null, sheet);
            val.accept(c.getColor());
        }
    }

    public void setBulletFontColor(Color color) {
        setBulletFontColor(DrawPaint.createSolidPaint(color));
    }

    public void setBulletFontColor(PaintStyle color) {
        if (!(color instanceof PaintStyle.SolidPaint)) {
            throw new IllegalArgumentException("Currently XSLF only supports SolidPaint");
        }
        PaintStyle.SolidPaint sp = (PaintStyle.SolidPaint) color;
        Color col = DrawPaint.applyColorTransform(sp.getSolidColor());
        CTTextParagraphProperties pr = this._p.isSetPPr() ? this._p.getPPr() : this._p.addNewPPr();
        CTColor c = pr.isSetBuClr() ? pr.getBuClr() : pr.addNewBuClr();
        CTSRgbColor clr = c.isSetSrgbClr() ? c.getSrgbClr() : c.addNewSrgbClr();
        clr.setVal(new byte[]{(byte) col.getRed(), (byte) col.getGreen(), (byte) col.getBlue()});
    }

    public Double getBulletFontSize() {
        return (Double) fetchParagraphProperty(new ParagraphPropertyFetcher.ParaPropFetcher() { // from class: org.apache.poi.xslf.usermodel.XSLFTextParagraph$$ExternalSyntheticLambda8
            @Override // org.apache.poi.xslf.model.ParagraphPropertyFetcher.ParaPropFetcher
            public final void fetch(CTTextParagraphProperties cTTextParagraphProperties, Consumer consumer) {
                XSLFTextParagraph.fetchBulletFontSize(cTTextParagraphProperties, consumer);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void fetchBulletFontSize(CTTextParagraphProperties props, Consumer<Double> val) {
        if (props.isSetBuSzPct()) {
            val.accept(Double.valueOf(POIXMLUnits.parsePercent(props.getBuSzPct().xgetVal()) * 0.001d));
        }
        if (props.isSetBuSzPts()) {
            val.accept(Double.valueOf((-props.getBuSzPts().getVal()) * 0.01d));
        }
    }

    public void setBulletFontSize(double bulletSize) {
        CTTextParagraphProperties pr = this._p.isSetPPr() ? this._p.getPPr() : this._p.addNewPPr();
        if (bulletSize >= 0.0d) {
            CTTextBulletSizePercent pt = pr.isSetBuSzPct() ? pr.getBuSzPct() : pr.addNewBuSzPct();
            pt.setVal(Integer.toString((int) (1000.0d * bulletSize)));
            if (pr.isSetBuSzPts()) {
                pr.unsetBuSzPts();
                return;
            }
            return;
        }
        CTTextBulletSizePoint pt2 = pr.isSetBuSzPts() ? pr.getBuSzPts() : pr.addNewBuSzPts();
        pt2.setVal((int) ((-bulletSize) * 100.0d));
        if (pr.isSetBuSzPct()) {
            pr.unsetBuSzPct();
        }
    }

    public AutoNumberingScheme getAutoNumberingScheme() {
        return (AutoNumberingScheme) fetchParagraphProperty(new ParagraphPropertyFetcher.ParaPropFetcher() { // from class: org.apache.poi.xslf.usermodel.XSLFTextParagraph$$ExternalSyntheticLambda16
            @Override // org.apache.poi.xslf.model.ParagraphPropertyFetcher.ParaPropFetcher
            public final void fetch(CTTextParagraphProperties cTTextParagraphProperties, Consumer consumer) {
                XSLFTextParagraph.fetchAutoNumberingScheme(cTTextParagraphProperties, consumer);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void fetchAutoNumberingScheme(CTTextParagraphProperties props, Consumer<AutoNumberingScheme> val) {
        AutoNumberingScheme ans;
        if (props.isSetBuAutoNum() && (ans = AutoNumberingScheme.forOoxmlID(props.getBuAutoNum().getType().intValue())) != null) {
            val.accept(ans);
        }
    }

    public Integer getAutoNumberingStartAt() {
        return (Integer) fetchParagraphProperty(new ParagraphPropertyFetcher.ParaPropFetcher() { // from class: org.apache.poi.xslf.usermodel.XSLFTextParagraph$$ExternalSyntheticLambda34
            @Override // org.apache.poi.xslf.model.ParagraphPropertyFetcher.ParaPropFetcher
            public final void fetch(CTTextParagraphProperties cTTextParagraphProperties, Consumer consumer) {
                XSLFTextParagraph.lambda$getAutoNumberingStartAt$4(cTTextParagraphProperties, consumer);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getAutoNumberingStartAt$4(CTTextParagraphProperties props, Consumer val) {
        if (props.isSetBuAutoNum() && props.getBuAutoNum().isSetStartAt()) {
            val.accept(Integer.valueOf(props.getBuAutoNum().getStartAt()));
        }
    }

    @Override // org.apache.poi.sl.usermodel.TextParagraph
    public void setIndent(Double indent) {
        if (indent == null && !this._p.isSetPPr()) {
            return;
        }
        CTTextParagraphProperties pr = this._p.isSetPPr() ? this._p.getPPr() : this._p.addNewPPr();
        if (indent == null) {
            if (pr.isSetIndent()) {
                pr.unsetIndent();
                return;
            }
            return;
        }
        pr.setIndent(Units.toEMU(indent.doubleValue()));
    }

    @Override // org.apache.poi.sl.usermodel.TextParagraph
    public Double getIndent() {
        return (Double) fetchParagraphProperty(new ParagraphPropertyFetcher.ParaPropFetcher() { // from class: org.apache.poi.xslf.usermodel.XSLFTextParagraph$$ExternalSyntheticLambda21
            @Override // org.apache.poi.xslf.model.ParagraphPropertyFetcher.ParaPropFetcher
            public final void fetch(CTTextParagraphProperties cTTextParagraphProperties, Consumer consumer) {
                XSLFTextParagraph.lambda$getIndent$5(cTTextParagraphProperties, consumer);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getIndent$5(CTTextParagraphProperties props, Consumer val) {
        if (props.isSetIndent()) {
            val.accept(Double.valueOf(Units.toPoints(props.getIndent())));
        }
    }

    @Override // org.apache.poi.sl.usermodel.TextParagraph
    public void setLeftMargin(Double leftMargin) {
        if (leftMargin == null && !this._p.isSetPPr()) {
            return;
        }
        CTTextParagraphProperties pr = this._p.isSetPPr() ? this._p.getPPr() : this._p.addNewPPr();
        if (leftMargin == null) {
            if (pr.isSetMarL()) {
                pr.unsetMarL();
                return;
            }
            return;
        }
        pr.setMarL(Units.toEMU(leftMargin.doubleValue()));
    }

    @Override // org.apache.poi.sl.usermodel.TextParagraph
    public Double getLeftMargin() {
        return (Double) fetchParagraphProperty(new ParagraphPropertyFetcher.ParaPropFetcher() { // from class: org.apache.poi.xslf.usermodel.XSLFTextParagraph$$ExternalSyntheticLambda0
            @Override // org.apache.poi.xslf.model.ParagraphPropertyFetcher.ParaPropFetcher
            public final void fetch(CTTextParagraphProperties cTTextParagraphProperties, Consumer consumer) {
                XSLFTextParagraph.lambda$getLeftMargin$6(cTTextParagraphProperties, consumer);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getLeftMargin$6(CTTextParagraphProperties props, Consumer val) {
        if (props.isSetMarL()) {
            val.accept(Double.valueOf(Units.toPoints(props.getMarL())));
        }
    }

    @Override // org.apache.poi.sl.usermodel.TextParagraph
    public void setRightMargin(Double rightMargin) {
        if (rightMargin == null && !this._p.isSetPPr()) {
            return;
        }
        CTTextParagraphProperties pr = this._p.isSetPPr() ? this._p.getPPr() : this._p.addNewPPr();
        if (rightMargin == null) {
            if (pr.isSetMarR()) {
                pr.unsetMarR();
                return;
            }
            return;
        }
        pr.setMarR(Units.toEMU(rightMargin.doubleValue()));
    }

    @Override // org.apache.poi.sl.usermodel.TextParagraph
    public Double getRightMargin() {
        return (Double) fetchParagraphProperty(new ParagraphPropertyFetcher.ParaPropFetcher() { // from class: org.apache.poi.xslf.usermodel.XSLFTextParagraph$$ExternalSyntheticLambda9
            @Override // org.apache.poi.xslf.model.ParagraphPropertyFetcher.ParaPropFetcher
            public final void fetch(CTTextParagraphProperties cTTextParagraphProperties, Consumer consumer) {
                XSLFTextParagraph.lambda$getRightMargin$7(cTTextParagraphProperties, consumer);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getRightMargin$7(CTTextParagraphProperties props, Consumer val) {
        if (props.isSetMarR()) {
            val.accept(Double.valueOf(Units.toPoints(props.getMarR())));
        }
    }

    @Override // org.apache.poi.sl.usermodel.TextParagraph
    public Double getDefaultTabSize() {
        return (Double) fetchParagraphProperty(new ParagraphPropertyFetcher.ParaPropFetcher() { // from class: org.apache.poi.xslf.usermodel.XSLFTextParagraph$$ExternalSyntheticLambda27
            @Override // org.apache.poi.xslf.model.ParagraphPropertyFetcher.ParaPropFetcher
            public final void fetch(CTTextParagraphProperties cTTextParagraphProperties, Consumer consumer) {
                XSLFTextParagraph.lambda$getDefaultTabSize$8(cTTextParagraphProperties, consumer);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getDefaultTabSize$8(CTTextParagraphProperties props, Consumer val) {
        if (props.isSetDefTabSz()) {
            val.accept(Double.valueOf(Units.toPoints(POIXMLUnits.parseLength(props.xgetDefTabSz()))));
        }
    }

    public double getTabStop(final int idx) {
        Double d = (Double) fetchParagraphProperty(new ParagraphPropertyFetcher.ParaPropFetcher() { // from class: org.apache.poi.xslf.usermodel.XSLFTextParagraph$$ExternalSyntheticLambda17
            @Override // org.apache.poi.xslf.model.ParagraphPropertyFetcher.ParaPropFetcher
            public final void fetch(CTTextParagraphProperties cTTextParagraphProperties, Consumer consumer) {
                XSLFTextParagraph.fetchTabStop(idx, cTTextParagraphProperties, consumer);
            }
        });
        if (d == null) {
            return 0.0d;
        }
        return d.doubleValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void fetchTabStop(int idx, CTTextParagraphProperties props, Consumer<Double> val) {
        if (props.isSetTabLst()) {
            CTTextTabStopList tabStops = props.getTabLst();
            if (idx < tabStops.sizeOfTabArray()) {
                CTTextTabStop ts = tabStops.getTabArray(idx);
                val.accept(Double.valueOf(Units.toPoints(POIXMLUnits.parseLength(ts.xgetPos()))));
            }
        }
    }

    public void addTabStop(double value) {
        CTTextParagraphProperties pr = this._p.isSetPPr() ? this._p.getPPr() : this._p.addNewPPr();
        CTTextTabStopList tabStops = pr.isSetTabLst() ? pr.getTabLst() : pr.addNewTabLst();
        tabStops.addNewTab().setPos(Integer.valueOf(Units.toEMU(value)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Supplier lambda$setLineSpacing$10(CTTextParagraphProperties props) {
        props.getClass();
        return new XSLFTextParagraph$$ExternalSyntheticLambda30(props);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Supplier lambda$setLineSpacing$11(final CTTextParagraphProperties props) {
        props.getClass();
        return new Supplier() { // from class: org.apache.poi.xslf.usermodel.XSLFTextParagraph$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return CTTextParagraphProperties.this.addNewLnSpc();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Procedure lambda$setLineSpacing$12(final CTTextParagraphProperties props) {
        props.getClass();
        return new Procedure() { // from class: org.apache.poi.xslf.usermodel.XSLFTextParagraph$$ExternalSyntheticLambda15
            @Override // org.apache.poi.xslf.usermodel.XSLFTextParagraph.Procedure
            public final void accept() {
                CTTextParagraphProperties.this.unsetLnSpc();
            }
        };
    }

    @Override // org.apache.poi.sl.usermodel.TextParagraph
    public void setLineSpacing(Double lineSpacing) {
        setSpacing(lineSpacing, new Function() { // from class: org.apache.poi.xslf.usermodel.XSLFTextParagraph$$ExternalSyntheticLambda31
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return XSLFTextParagraph.lambda$setLineSpacing$10((CTTextParagraphProperties) obj);
            }
        }, new Function() { // from class: org.apache.poi.xslf.usermodel.XSLFTextParagraph$$ExternalSyntheticLambda32
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return XSLFTextParagraph.lambda$setLineSpacing$11((CTTextParagraphProperties) obj);
            }
        }, new Function() { // from class: org.apache.poi.xslf.usermodel.XSLFTextParagraph$$ExternalSyntheticLambda33
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return XSLFTextParagraph.lambda$setLineSpacing$12((CTTextParagraphProperties) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Supplier lambda$getLineSpacing$13(CTTextParagraphProperties props) {
        props.getClass();
        return new XSLFTextParagraph$$ExternalSyntheticLambda30(props);
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [org.apache.poi.xslf.usermodel.XSLFTextShape] */
    @Override // org.apache.poi.sl.usermodel.TextParagraph
    public Double getLineSpacing() {
        CTTextNormalAutofit normAutofit;
        Double lnSpc = getSpacing(new Function() { // from class: org.apache.poi.xslf.usermodel.XSLFTextParagraph$$ExternalSyntheticLambda10
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return XSLFTextParagraph.lambda$getLineSpacing$13((CTTextParagraphProperties) obj);
            }
        });
        if (lnSpc != null && lnSpc.doubleValue() > 0.0d && (normAutofit = getParentShape().getTextBodyPr().getNormAutofit()) != null) {
            double scale = 1.0d - (POIXMLUnits.parsePercent(normAutofit.xgetLnSpcReduction()) / 100000.0d);
            return Double.valueOf(lnSpc.doubleValue() * scale);
        }
        return lnSpc;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Supplier lambda$setSpaceBefore$14(CTTextParagraphProperties props) {
        props.getClass();
        return new XSLFTextParagraph$$ExternalSyntheticLambda3(props);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Supplier lambda$setSpaceBefore$15(final CTTextParagraphProperties props) {
        props.getClass();
        return new Supplier() { // from class: org.apache.poi.xslf.usermodel.XSLFTextParagraph$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return CTTextParagraphProperties.this.addNewSpcBef();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Procedure lambda$setSpaceBefore$16(final CTTextParagraphProperties props) {
        props.getClass();
        return new Procedure() { // from class: org.apache.poi.xslf.usermodel.XSLFTextParagraph$$ExternalSyntheticLambda13
            @Override // org.apache.poi.xslf.usermodel.XSLFTextParagraph.Procedure
            public final void accept() {
                CTTextParagraphProperties.this.unsetSpcBef();
            }
        };
    }

    @Override // org.apache.poi.sl.usermodel.TextParagraph
    public void setSpaceBefore(Double spaceBefore) {
        setSpacing(spaceBefore, new Function() { // from class: org.apache.poi.xslf.usermodel.XSLFTextParagraph$$ExternalSyntheticLambda24
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return XSLFTextParagraph.lambda$setSpaceBefore$14((CTTextParagraphProperties) obj);
            }
        }, new Function() { // from class: org.apache.poi.xslf.usermodel.XSLFTextParagraph$$ExternalSyntheticLambda25
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return XSLFTextParagraph.lambda$setSpaceBefore$15((CTTextParagraphProperties) obj);
            }
        }, new Function() { // from class: org.apache.poi.xslf.usermodel.XSLFTextParagraph$$ExternalSyntheticLambda26
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return XSLFTextParagraph.lambda$setSpaceBefore$16((CTTextParagraphProperties) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Supplier lambda$getSpaceBefore$17(CTTextParagraphProperties props) {
        props.getClass();
        return new XSLFTextParagraph$$ExternalSyntheticLambda3(props);
    }

    @Override // org.apache.poi.sl.usermodel.TextParagraph
    public Double getSpaceBefore() {
        return getSpacing(new Function() { // from class: org.apache.poi.xslf.usermodel.XSLFTextParagraph$$ExternalSyntheticLambda12
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return XSLFTextParagraph.lambda$getSpaceBefore$17((CTTextParagraphProperties) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Supplier lambda$setSpaceAfter$18(CTTextParagraphProperties props) {
        props.getClass();
        return new XSLFTextParagraph$$ExternalSyntheticLambda11(props);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Supplier lambda$setSpaceAfter$19(final CTTextParagraphProperties props) {
        props.getClass();
        return new Supplier() { // from class: org.apache.poi.xslf.usermodel.XSLFTextParagraph$$ExternalSyntheticLambda29
            @Override // java.util.function.Supplier
            public final Object get() {
                return CTTextParagraphProperties.this.addNewSpcAft();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Procedure lambda$setSpaceAfter$20(final CTTextParagraphProperties props) {
        props.getClass();
        return new Procedure() { // from class: org.apache.poi.xslf.usermodel.XSLFTextParagraph$$ExternalSyntheticLambda28
            @Override // org.apache.poi.xslf.usermodel.XSLFTextParagraph.Procedure
            public final void accept() {
                CTTextParagraphProperties.this.unsetSpcAft();
            }
        };
    }

    @Override // org.apache.poi.sl.usermodel.TextParagraph
    public void setSpaceAfter(Double spaceAfter) {
        setSpacing(spaceAfter, new Function() { // from class: org.apache.poi.xslf.usermodel.XSLFTextParagraph$$ExternalSyntheticLambda5
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return XSLFTextParagraph.lambda$setSpaceAfter$18((CTTextParagraphProperties) obj);
            }
        }, new Function() { // from class: org.apache.poi.xslf.usermodel.XSLFTextParagraph$$ExternalSyntheticLambda6
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return XSLFTextParagraph.lambda$setSpaceAfter$19((CTTextParagraphProperties) obj);
            }
        }, new Function() { // from class: org.apache.poi.xslf.usermodel.XSLFTextParagraph$$ExternalSyntheticLambda7
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return XSLFTextParagraph.lambda$setSpaceAfter$20((CTTextParagraphProperties) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Supplier lambda$getSpaceAfter$21(CTTextParagraphProperties props) {
        props.getClass();
        return new XSLFTextParagraph$$ExternalSyntheticLambda11(props);
    }

    @Override // org.apache.poi.sl.usermodel.TextParagraph
    public Double getSpaceAfter() {
        return getSpacing(new Function() { // from class: org.apache.poi.xslf.usermodel.XSLFTextParagraph$$ExternalSyntheticLambda36
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return XSLFTextParagraph.lambda$getSpaceAfter$21((CTTextParagraphProperties) obj);
            }
        });
    }

    private void setSpacing(Double space, Function<CTTextParagraphProperties, Supplier<CTTextSpacing>> getSpc, Function<CTTextParagraphProperties, Supplier<CTTextSpacing>> addSpc, Function<CTTextParagraphProperties, Procedure> unsetSpc) {
        CTTextParagraphProperties pPr = (space == null || this._p.isSetPPr()) ? this._p.getPPr() : this._p.addNewPPr();
        if (pPr == null) {
            return;
        }
        CTTextSpacing spc = getSpc.apply(pPr).get();
        if (space == null) {
            if (spc != null) {
                unsetSpc.apply(pPr).accept();
                return;
            }
            return;
        }
        if (spc == null) {
            spc = addSpc.apply(pPr).get();
        }
        if (space.doubleValue() >= 0.0d) {
            if (spc.isSetSpcPts()) {
                spc.unsetSpcPts();
            }
            CTTextSpacingPercent pct = spc.isSetSpcPct() ? spc.getSpcPct() : spc.addNewSpcPct();
            pct.setVal(Integer.valueOf((int) (space.doubleValue() * 1000.0d)));
            return;
        }
        if (spc.isSetSpcPct()) {
            spc.unsetSpcPct();
        }
        CTTextSpacingPoint pts = spc.isSetSpcPts() ? spc.getSpcPts() : spc.addNewSpcPts();
        pts.setVal((int) ((-space.doubleValue()) * 100.0d));
    }

    private Double getSpacing(final Function<CTTextParagraphProperties, Supplier<CTTextSpacing>> getSpc) {
        return (Double) fetchParagraphProperty(new ParagraphPropertyFetcher.ParaPropFetcher() { // from class: org.apache.poi.xslf.usermodel.XSLFTextParagraph$$ExternalSyntheticLambda22
            @Override // org.apache.poi.xslf.model.ParagraphPropertyFetcher.ParaPropFetcher
            public final void fetch(CTTextParagraphProperties cTTextParagraphProperties, Consumer consumer) {
                XSLFTextParagraph.fetchSpacing(getSpc, cTTextParagraphProperties, consumer);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void fetchSpacing(Function<CTTextParagraphProperties, Supplier<CTTextSpacing>> getSpc, CTTextParagraphProperties props, Consumer<Double> val) {
        CTTextSpacing spc = getSpc.apply(props).get();
        if (spc != null) {
            if (spc.isSetSpcPct()) {
                val.accept(Double.valueOf(POIXMLUnits.parsePercent(spc.getSpcPct().xgetVal()) * 0.001d));
            } else if (spc.isSetSpcPts()) {
                val.accept(Double.valueOf((-spc.getSpcPts().getVal()) * 0.01d));
            }
        }
    }

    @Override // org.apache.poi.sl.usermodel.TextParagraph
    public void setIndentLevel(int level) {
        CTTextParagraphProperties pr = this._p.isSetPPr() ? this._p.getPPr() : this._p.addNewPPr();
        pr.setLvl(level);
    }

    @Override // org.apache.poi.sl.usermodel.TextParagraph
    public int getIndentLevel() {
        CTTextParagraphProperties pr = this._p.getPPr();
        if (pr == null || !pr.isSetLvl()) {
            return 0;
        }
        return pr.getLvl();
    }

    public boolean isBullet() {
        Boolean b = (Boolean) fetchParagraphProperty(new ParagraphPropertyFetcher.ParaPropFetcher() { // from class: org.apache.poi.xslf.usermodel.XSLFTextParagraph$$ExternalSyntheticLambda35
            @Override // org.apache.poi.xslf.model.ParagraphPropertyFetcher.ParaPropFetcher
            public final void fetch(CTTextParagraphProperties cTTextParagraphProperties, Consumer consumer) {
                XSLFTextParagraph.fetchIsBullet(cTTextParagraphProperties, consumer);
            }
        });
        if (b == null) {
            return false;
        }
        return b.booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void fetchIsBullet(CTTextParagraphProperties props, Consumer<Boolean> val) {
        if (props.isSetBuNone()) {
            val.accept(false);
        } else if (props.isSetBuFont() || props.isSetBuChar()) {
            val.accept(true);
        }
    }

    public void setBullet(boolean flag) {
        if (isBullet() == flag) {
            return;
        }
        CTTextParagraphProperties pr = this._p.isSetPPr() ? this._p.getPPr() : this._p.addNewPPr();
        if (flag) {
            pr.addNewBuFont().setTypeface(HSSFFont.FONT_ARIAL);
            pr.addNewBuChar().setChar("•");
            return;
        }
        if (pr.isSetBuFont()) {
            pr.unsetBuFont();
        }
        if (pr.isSetBuChar()) {
            pr.unsetBuChar();
        }
        if (pr.isSetBuAutoNum()) {
            pr.unsetBuAutoNum();
        }
        if (pr.isSetBuBlip()) {
            pr.unsetBuBlip();
        }
        if (pr.isSetBuClr()) {
            pr.unsetBuClr();
        }
        if (pr.isSetBuClrTx()) {
            pr.unsetBuClrTx();
        }
        if (pr.isSetBuFont()) {
            pr.unsetBuFont();
        }
        if (pr.isSetBuFontTx()) {
            pr.unsetBuFontTx();
        }
        if (pr.isSetBuSzPct()) {
            pr.unsetBuSzPct();
        }
        if (pr.isSetBuSzPts()) {
            pr.unsetBuSzPts();
        }
        if (pr.isSetBuSzTx()) {
            pr.unsetBuSzTx();
        }
        pr.addNewBuNone();
    }

    public void setBulletAutoNumber(AutoNumberingScheme scheme, int startAt) {
        if (startAt < 1) {
            throw new IllegalArgumentException("Start Number must be greater or equal that 1");
        }
        CTTextParagraphProperties pr = this._p.isSetPPr() ? this._p.getPPr() : this._p.addNewPPr();
        CTTextAutonumberBullet lst = pr.isSetBuAutoNum() ? pr.getBuAutoNum() : pr.addNewBuAutoNum();
        lst.setType(STTextAutonumberScheme.Enum.forInt(scheme.ooxmlId));
        lst.setStartAt(startAt);
    }

    public String toString() {
        return CollectionUtils.DEFAULT_TOSTRING_PREFIX + getClass() + CollectionUtils.DEFAULT_TOSTRING_SUFFIX + getText();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v2, types: [org.apache.poi.xslf.usermodel.XSLFSheet] */
    @Internal
    public CTTextParagraphProperties getDefaultMasterStyle() {
        String defaultStyleSelector;
        CTPlaceholder ph = this._shape.getPlaceholderDetails().getCTPlaceholder(false);
        switch (ph == null ? -1 : ph.getType().intValue()) {
            case -1:
            case 5:
            case 6:
            case 7:
                defaultStyleSelector = "otherStyle";
                break;
            case 0:
            case 2:
            case 4:
            default:
                defaultStyleSelector = "bodyStyle";
                break;
            case 1:
            case 3:
                defaultStyleSelector = "titleStyle";
                break;
        }
        int level = getIndentLevel();
        for (Sheet<XSLFShape, XSLFTextParagraph> sheet = this._shape.getSheet(); sheet != null; sheet = (XSLFSheet) sheet.getMasterSheet()) {
            XmlObject xo = sheet.getXmlObject();
            XmlCursor cur = xo.newCursor();
            try {
                cur.push();
                if ((cur.toChild(XSSFRelation.NS_PRESENTATIONML, "txStyles") && cur.toChild(XSSFRelation.NS_PRESENTATIONML, defaultStyleSelector)) || (cur.pop() && cur.toChild(XSSFRelation.NS_PRESENTATIONML, "notesStyle"))) {
                    while (level >= 0) {
                        cur.push();
                        if (cur.toChild(XSSFRelation.NS_DRAWINGML, "lvl" + (level + 1) + "pPr")) {
                            CTTextParagraphProperties cTTextParagraphProperties = (CTTextParagraphProperties) cur.getObject();
                            if (cur != null) {
                                cur.close();
                            }
                            return cTTextParagraphProperties;
                        }
                        cur.pop();
                        level--;
                    }
                }
                if (cur != null) {
                    cur.close();
                }
            } finally {
            }
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [org.apache.poi.xslf.usermodel.XSLFShape, org.apache.poi.xslf.usermodel.XSLFTextShape] */
    private <T> T fetchParagraphProperty(ParagraphPropertyFetcher.ParaPropFetcher<T> paraPropFetcher) {
        return (T) new ParagraphPropertyFetcher(this, paraPropFetcher).fetchProperty(getParentShape());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void copy(XSLFTextParagraph other) {
        if (other == this) {
            return;
        }
        CTTextParagraph thisP = getXmlObject();
        other.getXmlObject();
        if (thisP.isSetPPr()) {
            thisP.unsetPPr();
        }
        if (thisP.isSetEndParaRPr()) {
            thisP.unsetEndParaRPr();
        }
        this._runs.clear();
        for (int i = thisP.sizeOfBrArray(); i > 0; i--) {
            thisP.removeBr(i - 1);
        }
        for (int i2 = thisP.sizeOfRArray(); i2 > 0; i2--) {
            thisP.removeR(i2 - 1);
        }
        for (int i3 = thisP.sizeOfFldArray(); i3 > 0; i3--) {
            thisP.removeFld(i3 - 1);
        }
        for (XSLFTextRun tr : other.getTextRuns()) {
            XmlObject xo = tr.getXmlObject().copy();
            XSLFTextRun run = addNewTextRun();
            run.getXmlObject().set(xo);
            run.copy(tr);
        }
        TextParagraph.TextAlign srcAlign = other.getTextAlign();
        if (srcAlign != getTextAlign()) {
            setTextAlign(srcAlign);
        }
        boolean isBullet = other.isBullet();
        if (isBullet != isBullet()) {
            setBullet(isBullet);
            if (isBullet) {
                String buFont = other.getBulletFont();
                if (buFont != null && !buFont.equals(getBulletFont())) {
                    setBulletFont(buFont);
                }
                String buChar = other.getBulletCharacter();
                if (buChar != null && !buChar.equals(getBulletCharacter())) {
                    setBulletCharacter(buChar);
                }
                PaintStyle buColor = other.getBulletFontColor();
                if (buColor != null && !buColor.equals(getBulletFontColor())) {
                    setBulletFontColor(buColor);
                }
                Double buSize = other.getBulletFontSize();
                if (doubleNotEquals(buSize, getBulletFontSize())) {
                    setBulletFontSize(buSize.doubleValue());
                }
            }
        }
        Double leftMargin = other.getLeftMargin();
        if (doubleNotEquals(leftMargin, getLeftMargin())) {
            setLeftMargin(leftMargin);
        }
        Double indent = other.getIndent();
        if (doubleNotEquals(indent, getIndent())) {
            setIndent(indent);
        }
        Double spaceAfter = other.getSpaceAfter();
        if (doubleNotEquals(spaceAfter, getSpaceAfter())) {
            setSpaceAfter(spaceAfter);
        }
        Double spaceBefore = other.getSpaceBefore();
        if (doubleNotEquals(spaceBefore, getSpaceBefore())) {
            setSpaceBefore(spaceBefore);
        }
        Double lineSpacing = other.getLineSpacing();
        if (doubleNotEquals(lineSpacing, getLineSpacing())) {
            setLineSpacing(lineSpacing);
        }
    }

    private static boolean doubleNotEquals(Double d1, Double d2) {
        return !Objects.equals(d1, d2);
    }

    @Override // org.apache.poi.sl.usermodel.TextParagraph
    public Double getDefaultFontSize() {
        CTTextParagraphProperties masterStyle;
        CTTextCharacterProperties endPr = this._p.getEndParaRPr();
        if ((endPr == null || !endPr.isSetSz()) && (masterStyle = getDefaultMasterStyle()) != null) {
            endPr = masterStyle.getDefRPr();
        }
        return Double.valueOf((endPr == null || !endPr.isSetSz()) ? 12.0d : endPr.getSz() / 100.0d);
    }

    @Override // org.apache.poi.sl.usermodel.TextParagraph
    public String getDefaultFontFamily() {
        String family = this._runs.isEmpty() ? null : this._runs.get(0).getFontFamily();
        return family == null ? HSSFFont.FONT_ARIAL : family;
    }

    @Override // org.apache.poi.sl.usermodel.TextParagraph
    public TextParagraph.BulletStyle getBulletStyle() {
        if (!isBullet()) {
            return null;
        }
        return new TextParagraph.BulletStyle() { // from class: org.apache.poi.xslf.usermodel.XSLFTextParagraph.1
            @Override // org.apache.poi.sl.usermodel.TextParagraph.BulletStyle
            public String getBulletCharacter() {
                return XSLFTextParagraph.this.getBulletCharacter();
            }

            @Override // org.apache.poi.sl.usermodel.TextParagraph.BulletStyle
            public String getBulletFont() {
                return XSLFTextParagraph.this.getBulletFont();
            }

            @Override // org.apache.poi.sl.usermodel.TextParagraph.BulletStyle
            public Double getBulletFontSize() {
                return XSLFTextParagraph.this.getBulletFontSize();
            }

            @Override // org.apache.poi.sl.usermodel.TextParagraph.BulletStyle
            public PaintStyle getBulletFontColor() {
                return XSLFTextParagraph.this.getBulletFontColor();
            }

            @Override // org.apache.poi.sl.usermodel.TextParagraph.BulletStyle
            public void setBulletFontColor(Color color) {
                setBulletFontColor(DrawPaint.createSolidPaint(color));
            }

            @Override // org.apache.poi.sl.usermodel.TextParagraph.BulletStyle
            public void setBulletFontColor(PaintStyle color) {
                XSLFTextParagraph.this.setBulletFontColor(color);
            }

            @Override // org.apache.poi.sl.usermodel.TextParagraph.BulletStyle
            public AutoNumberingScheme getAutoNumberingScheme() {
                return XSLFTextParagraph.this.getAutoNumberingScheme();
            }

            @Override // org.apache.poi.sl.usermodel.TextParagraph.BulletStyle
            public Integer getAutoNumberingStartAt() {
                return XSLFTextParagraph.this.getAutoNumberingStartAt();
            }
        };
    }

    @Override // org.apache.poi.sl.usermodel.TextParagraph
    public void setBulletStyle(Object... styles) {
        if (styles.length == 0) {
            setBullet(false);
            return;
        }
        setBullet(true);
        for (Object ostyle : styles) {
            if (ostyle instanceof Number) {
                setBulletFontSize(((Number) ostyle).doubleValue());
            } else if (ostyle instanceof Color) {
                setBulletFontColor((Color) ostyle);
            } else if (ostyle instanceof Character) {
                setBulletCharacter(ostyle.toString());
            } else if (ostyle instanceof String) {
                setBulletFont((String) ostyle);
            } else if (ostyle instanceof AutoNumberingScheme) {
                setBulletAutoNumber((AutoNumberingScheme) ostyle, 1);
            }
        }
    }

    @Override // org.apache.poi.sl.usermodel.TextParagraph
    public List<XSLFTabStop> getTabStops() {
        return (List) fetchParagraphProperty(new ParagraphPropertyFetcher.ParaPropFetcher() { // from class: org.apache.poi.xslf.usermodel.XSLFTextParagraph$$ExternalSyntheticLambda18
            @Override // org.apache.poi.xslf.model.ParagraphPropertyFetcher.ParaPropFetcher
            public final void fetch(CTTextParagraphProperties cTTextParagraphProperties, Consumer consumer) {
                XSLFTextParagraph.fetchTabStops(cTTextParagraphProperties, consumer);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void fetchTabStops(CTTextParagraphProperties props, Consumer<List<XSLFTabStop>> val) {
        if (props.isSetTabLst()) {
            List<XSLFTabStop> list = new ArrayList<>();
            for (CTTextTabStop ta : props.getTabLst().getTabArray()) {
                list.add(new XSLFTabStop(ta));
            }
            val.accept(list);
        }
    }

    @Override // org.apache.poi.sl.usermodel.TextParagraph
    public void addTabStops(double positionInPoints, TabStop.TabStopType tabStopType) {
        CTTextParagraphProperties tpp;
        if (getParentShape().getSheet() instanceof XSLFSlideMaster) {
            tpp = getDefaultMasterStyle();
        } else {
            CTTextParagraph xo = getXmlObject();
            tpp = xo.isSetPPr() ? xo.getPPr() : xo.addNewPPr();
        }
        if (tpp == null) {
            return;
        }
        CTTextTabStopList stl = tpp.isSetTabLst() ? tpp.getTabLst() : tpp.addNewTabLst();
        XSLFTabStop tab = new XSLFTabStop(stl.addNewTab());
        tab.setPositionInPoints(positionInPoints);
        tab.setType(tabStopType);
    }

    @Override // org.apache.poi.sl.usermodel.TextParagraph
    public void clearTabStops() {
        CTTextParagraphProperties tpp = getParentShape().getSheet() instanceof XSLFSlideMaster ? getDefaultMasterStyle() : getXmlObject().getPPr();
        if (tpp != null && tpp.isSetTabLst()) {
            tpp.unsetTabLst();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clearButKeepProperties() {
        CTTextParagraph thisP = getXmlObject();
        for (int i = thisP.sizeOfBrArray(); i > 0; i--) {
            thisP.removeBr(i - 1);
        }
        for (int i2 = thisP.sizeOfFldArray(); i2 > 0; i2--) {
            thisP.removeFld(i2 - 1);
        }
        if (!this._runs.isEmpty()) {
            int size = this._runs.size();
            XSLFTextRun lastRun = this._runs.get(size - 1);
            CTTextCharacterProperties cpOther = lastRun.getRPr(false);
            if (cpOther != null) {
                if (thisP.isSetEndParaRPr()) {
                    thisP.unsetEndParaRPr();
                }
                CTTextCharacterProperties cp = thisP.addNewEndParaRPr();
                cp.set(cpOther);
            }
            for (int i3 = size; i3 > 0; i3--) {
                thisP.removeR(i3 - 1);
            }
            this._runs.clear();
        }
    }

    @Override // org.apache.poi.sl.usermodel.TextParagraph
    public boolean isHeaderOrFooter() {
        CTPlaceholder ph = this._shape.getPlaceholderDetails().getCTPlaceholder(false);
        int phId = ph == null ? -1 : ph.getType().intValue();
        switch (phId) {
            case 5:
            case 6:
            case 7:
            case 8:
                return true;
            default:
                return false;
        }
    }

    protected XSLFTextRun newTextRun(XmlObject r) {
        return new XSLFTextRun(r, this);
    }

    protected XSLFTextRun newTextRun(CTTextLineBreak r) {
        return new XSLFLineBreak(r, this);
    }
}
