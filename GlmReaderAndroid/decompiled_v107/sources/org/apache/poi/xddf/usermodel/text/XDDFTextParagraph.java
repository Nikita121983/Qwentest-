package org.apache.poi.xddf.usermodel.text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.Function;
import java.util.function.Predicate;
import org.apache.commons.collections4.iterators.IteratorIterable;
import org.apache.commons.collections4.iterators.ReverseListIterator;
import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LocaleUtil;
import org.apache.poi.util.Units;
import org.apache.poi.xddf.usermodel.XDDFColor;
import org.apache.xmlbeans.QNameSet;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTRegularTextRun;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextField;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextLineBreak;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing;
import org.openxmlformats.schemas.drawingml.x2006.main.STCoordinate32;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextAlignType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextFontAlignType;

/* loaded from: classes10.dex */
public class XDDFTextParagraph implements Iterable<XDDFTextRun> {
    private final CTTextParagraph _p;
    private XDDFTextBody _parent;
    private XDDFParagraphProperties _properties;
    private final ArrayList<XDDFTextRun> _runs;

    @Internal
    public XDDFTextParagraph(CTTextParagraph paragraph, XDDFTextBody parent) {
        this._p = paragraph;
        this._parent = parent;
        int count = paragraph.sizeOfBrArray() + paragraph.sizeOfFldArray() + paragraph.sizeOfRArray();
        this._runs = new ArrayList<>(count);
        for (XmlObject xo : paragraph.selectChildren(QNameSet.ALL)) {
            if (xo instanceof CTTextLineBreak) {
                this._runs.add(new XDDFTextRun((CTTextLineBreak) xo, this));
            } else if (xo instanceof CTTextField) {
                this._runs.add(new XDDFTextRun((CTTextField) xo, this));
            } else if (xo instanceof CTRegularTextRun) {
                this._runs.add(new XDDFTextRun((CTRegularTextRun) xo, this));
            }
        }
        addDefaultRunProperties();
        addAfterLastRunProperties();
    }

    public void setText(String text) {
        XmlObject existing = null;
        if (!this._runs.isEmpty()) {
            existing = this._runs.get(this._runs.size() - 1).getProperties().copy();
        }
        for (int i = this._p.sizeOfBrArray() - 1; i >= 0; i--) {
            this._p.removeBr(i);
        }
        for (int i2 = this._p.sizeOfFldArray() - 1; i2 >= 0; i2--) {
            this._p.removeFld(i2);
        }
        for (int i3 = this._p.sizeOfRArray() - 1; i3 >= 0; i3--) {
            this._p.removeR(i3);
        }
        this._runs.clear();
        XDDFTextRun run = appendRegularRun(text);
        if (existing != null) {
            run.getProperties().set(existing);
        }
    }

    public String getText() {
        StringBuilder out = new StringBuilder();
        Iterator<XDDFTextRun> it = this._runs.iterator();
        while (it.hasNext()) {
            XDDFTextRun r = it.next();
            out.append(r.getText());
        }
        return out.toString();
    }

    public XDDFTextBody getParentBody() {
        return this._parent;
    }

    public List<XDDFTextRun> getTextRuns() {
        return Collections.unmodifiableList(this._runs);
    }

    @Override // java.lang.Iterable
    public Iterator<XDDFTextRun> iterator() {
        return this._runs.iterator();
    }

    @Override // java.lang.Iterable
    public Spliterator<XDDFTextRun> spliterator() {
        return this._runs.spliterator();
    }

    public XDDFTextRun appendLineBreak() {
        CTTextLineBreak br = this._p.addNewBr();
        Iterator it = new IteratorIterable(new ReverseListIterator(this._runs)).iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            XDDFTextRun tr = (XDDFTextRun) it.next();
            CTTextCharacterProperties prevProps = tr.getProperties();
            if (prevProps != null) {
                br.setRPr((CTTextCharacterProperties) prevProps.copy());
                break;
            }
        }
        XDDFTextRun run = new XDDFTextRun(br, this);
        this._runs.add(run);
        return run;
    }

    public XDDFTextRun appendField(String id, String type, String text) {
        CTTextField f = this._p.addNewFld();
        f.setId(id);
        f.setType(type);
        f.setT(text);
        CTTextCharacterProperties rPr = f.addNewRPr();
        rPr.setLang(LocaleUtil.getUserLocale().toLanguageTag());
        XDDFTextRun run = new XDDFTextRun(f, this);
        this._runs.add(run);
        return run;
    }

    public XDDFTextRun appendRegularRun(String text) {
        CTRegularTextRun r = this._p.addNewR();
        r.setT(text);
        CTTextCharacterProperties rPr = r.addNewRPr();
        rPr.setLang(LocaleUtil.getUserLocale().toLanguageTag());
        XDDFTextRun run = new XDDFTextRun(r, this);
        this._runs.add(run);
        return run;
    }

    public int getIndentationLevel() {
        if (this._p.isSetPPr()) {
            return getProperties().getLevel();
        }
        return 0;
    }

    public void setIndentationLevel(Integer level) {
        if (this._p.isSetPPr()) {
            getProperties().setLevel(level);
        }
    }

    public TextAlignment getTextAlignment() {
        return (TextAlignment) findDefinedParagraphProperty(new Predicate() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextParagraph$$ExternalSyntheticLambda19
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((CTTextParagraphProperties) obj).isSetAlgn();
            }
        }, new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextParagraph$$ExternalSyntheticLambda20
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((CTTextParagraphProperties) obj).getAlgn();
            }
        }).map(new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextParagraph$$ExternalSyntheticLambda21
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return TextAlignment.valueOf((STTextAlignType.Enum) obj);
            }
        }).orElse(null);
    }

    public void setTextAlignment(TextAlignment align) {
        if (align != null || this._p.isSetPPr()) {
            getOrCreateProperties().setTextAlignment(align);
        }
    }

    public FontAlignment getFontAlignment() {
        return (FontAlignment) findDefinedParagraphProperty(new Predicate() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextParagraph$$ExternalSyntheticLambda35
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((CTTextParagraphProperties) obj).isSetFontAlgn();
            }
        }, new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextParagraph$$ExternalSyntheticLambda36
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((CTTextParagraphProperties) obj).getFontAlgn();
            }
        }).map(new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextParagraph$$ExternalSyntheticLambda37
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return FontAlignment.valueOf((STTextFontAlignType.Enum) obj);
            }
        }).orElse(null);
    }

    public void setFontAlignment(FontAlignment align) {
        if (align != null || this._p.isSetPPr()) {
            getOrCreateProperties().setFontAlignment(align);
        }
    }

    public Double getIndentation() {
        return (Double) findDefinedParagraphProperty(new Predicate() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextParagraph$$ExternalSyntheticLambda15
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((CTTextParagraphProperties) obj).isSetIndent();
            }
        }, new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextParagraph$$ExternalSyntheticLambda16
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Integer.valueOf(((CTTextParagraphProperties) obj).getIndent());
            }
        }).map(new XDDFTextParagraph$$ExternalSyntheticLambda5()).orElse(null);
    }

    public void setIndentation(Double points) {
        if (points != null || this._p.isSetPPr()) {
            getOrCreateProperties().setIndentation(points);
        }
    }

    public Double getMarginLeft() {
        return (Double) findDefinedParagraphProperty(new Predicate() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextParagraph$$ExternalSyntheticLambda3
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((CTTextParagraphProperties) obj).isSetMarL();
            }
        }, new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextParagraph$$ExternalSyntheticLambda4
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Integer.valueOf(((CTTextParagraphProperties) obj).getMarL());
            }
        }).map(new XDDFTextParagraph$$ExternalSyntheticLambda5()).orElse(null);
    }

    public void setMarginLeft(Double points) {
        if (points != null || this._p.isSetPPr()) {
            getOrCreateProperties().setMarginLeft(points);
        }
    }

    public Double getMarginRight() {
        return (Double) findDefinedParagraphProperty(new Predicate() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextParagraph$$ExternalSyntheticLambda10
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((CTTextParagraphProperties) obj).isSetMarR();
            }
        }, new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextParagraph$$ExternalSyntheticLambda12
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Integer.valueOf(((CTTextParagraphProperties) obj).getMarR());
            }
        }).map(new XDDFTextParagraph$$ExternalSyntheticLambda5()).orElse(null);
    }

    public void setMarginRight(Double points) {
        if (points != null || this._p.isSetPPr()) {
            getOrCreateProperties().setMarginRight(points);
        }
    }

    public Double getDefaultTabSize() {
        return (Double) findDefinedParagraphProperty(new Predicate() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextParagraph$$ExternalSyntheticLambda38
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((CTTextParagraphProperties) obj).isSetDefTabSz();
            }
        }, new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextParagraph$$ExternalSyntheticLambda39
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((CTTextParagraphProperties) obj).xgetDefTabSz();
            }
        }).map(new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextParagraph$$ExternalSyntheticLambda1
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Long.valueOf(POIXMLUnits.parseLength((STCoordinate32) obj));
            }
        }).map(new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextParagraph$$ExternalSyntheticLambda2
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                double points;
                points = Units.toPoints(((Long) obj).longValue());
                return Double.valueOf(points);
            }
        }).orElse(null);
    }

    public void setDefaultTabSize(Double points) {
        if (points != null || this._p.isSetPPr()) {
            getOrCreateProperties().setDefaultTabSize(points);
        }
    }

    public XDDFSpacing getLineSpacing() {
        return (XDDFSpacing) findDefinedParagraphProperty(new Predicate() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextParagraph$$ExternalSyntheticLambda23
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((CTTextParagraphProperties) obj).isSetLnSpc();
            }
        }, new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextParagraph$$ExternalSyntheticLambda24
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((CTTextParagraphProperties) obj).getLnSpc();
            }
        }).map(new XDDFTextParagraph$$ExternalSyntheticLambda34(this)).orElse(null);
    }

    public void setLineSpacing(XDDFSpacing linespacing) {
        if (linespacing != null || this._p.isSetPPr()) {
            getOrCreateProperties().setLineSpacing(linespacing);
        }
    }

    public XDDFSpacing getSpaceBefore() {
        return (XDDFSpacing) findDefinedParagraphProperty(new Predicate() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextParagraph$$ExternalSyntheticLambda31
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((CTTextParagraphProperties) obj).isSetSpcBef();
            }
        }, new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextParagraph$$ExternalSyntheticLambda32
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((CTTextParagraphProperties) obj).getSpcBef();
            }
        }).map(new XDDFTextParagraph$$ExternalSyntheticLambda34(this)).orElse(null);
    }

    public void setSpaceBefore(XDDFSpacing spaceBefore) {
        if (spaceBefore != null || this._p.isSetPPr()) {
            getOrCreateProperties().setSpaceBefore(spaceBefore);
        }
    }

    public XDDFSpacing getSpaceAfter() {
        return (XDDFSpacing) findDefinedParagraphProperty(new Predicate() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextParagraph$$ExternalSyntheticLambda22
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((CTTextParagraphProperties) obj).isSetSpcAft();
            }
        }, new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextParagraph$$ExternalSyntheticLambda33
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((CTTextParagraphProperties) obj).getSpcAft();
            }
        }).map(new XDDFTextParagraph$$ExternalSyntheticLambda34(this)).orElse(null);
    }

    public void setSpaceAfter(XDDFSpacing spaceAfter) {
        if (spaceAfter != null || this._p.isSetPPr()) {
            getOrCreateProperties().setSpaceAfter(spaceAfter);
        }
    }

    public static /* synthetic */ boolean lambda$getBulletColor$0(CTTextParagraphProperties props) {
        return props.isSetBuClr() || props.isSetBuClrTx();
    }

    public XDDFColor getBulletColor() {
        return (XDDFColor) findDefinedParagraphProperty(new Predicate() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextParagraph$$ExternalSyntheticLambda6
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return XDDFTextParagraph.lambda$getBulletColor$0((CTTextParagraphProperties) obj);
            }
        }, new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextParagraph$$ExternalSyntheticLambda7
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                XDDFColor bulletColor;
                bulletColor = new XDDFParagraphBulletProperties((CTTextParagraphProperties) obj).getBulletColor();
                return bulletColor;
            }
        }).orElse(null);
    }

    public void setBulletColor(XDDFColor color) {
        if (color != null || this._p.isSetPPr()) {
            getOrCreateBulletProperties().setBulletColor(color);
        }
    }

    public void setBulletColorFollowText() {
        getOrCreateBulletProperties().setBulletColorFollowText();
    }

    public static /* synthetic */ boolean lambda$getBulletFont$2(CTTextParagraphProperties props) {
        return props.isSetBuFont() || props.isSetBuFontTx();
    }

    public XDDFFont getBulletFont() {
        return (XDDFFont) findDefinedParagraphProperty(new Predicate() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextParagraph$$ExternalSyntheticLambda17
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return XDDFTextParagraph.lambda$getBulletFont$2((CTTextParagraphProperties) obj);
            }
        }, new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextParagraph$$ExternalSyntheticLambda18
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                XDDFFont bulletFont;
                bulletFont = new XDDFParagraphBulletProperties((CTTextParagraphProperties) obj).getBulletFont();
                return bulletFont;
            }
        }).orElse(null);
    }

    public void setBulletFont(XDDFFont font) {
        if (font != null || this._p.isSetPPr()) {
            getOrCreateBulletProperties().setBulletFont(font);
        }
    }

    public void setBulletFontFollowText() {
        getOrCreateBulletProperties().setBulletFontFollowText();
    }

    public XDDFBulletSize getBulletSize() {
        return (XDDFBulletSize) findDefinedParagraphProperty(new Predicate() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextParagraph$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return XDDFTextParagraph.lambda$getBulletSize$4((CTTextParagraphProperties) obj);
            }
        }, new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextParagraph$$ExternalSyntheticLambda11
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                XDDFBulletSize bulletSize;
                bulletSize = new XDDFParagraphBulletProperties((CTTextParagraphProperties) obj).getBulletSize();
                return bulletSize;
            }
        }).orElse(null);
    }

    public static /* synthetic */ boolean lambda$getBulletSize$4(CTTextParagraphProperties props) {
        return props.isSetBuSzPct() || props.isSetBuSzPts() || props.isSetBuSzTx();
    }

    public void setBulletSize(XDDFBulletSize size) {
        if (size != null || this._p.isSetPPr()) {
            getOrCreateBulletProperties().setBulletSize(size);
        }
    }

    public XDDFBulletStyle getBulletStyle() {
        return (XDDFBulletStyle) findDefinedParagraphProperty(new Predicate() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextParagraph$$ExternalSyntheticLambda25
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return XDDFTextParagraph.lambda$getBulletStyle$6((CTTextParagraphProperties) obj);
            }
        }, new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextParagraph$$ExternalSyntheticLambda26
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                XDDFBulletStyle bulletStyle;
                bulletStyle = new XDDFParagraphBulletProperties((CTTextParagraphProperties) obj).getBulletStyle();
                return bulletStyle;
            }
        }).orElse(null);
    }

    public static /* synthetic */ boolean lambda$getBulletStyle$6(CTTextParagraphProperties props) {
        return props.isSetBuAutoNum() || props.isSetBuBlip() || props.isSetBuChar() || props.isSetBuNone();
    }

    public void setBulletStyle(XDDFBulletStyle style) {
        if (style != null || this._p.isSetPPr()) {
            getOrCreateBulletProperties().setBulletStyle(style);
        }
    }

    public boolean hasEastAsianLineBreak() {
        return ((Boolean) findDefinedParagraphProperty(new Predicate() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextParagraph$$ExternalSyntheticLambda13
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((CTTextParagraphProperties) obj).isSetEaLnBrk();
            }
        }, new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextParagraph$$ExternalSyntheticLambda14
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Boolean.valueOf(((CTTextParagraphProperties) obj).getEaLnBrk());
            }
        }).orElse(false)).booleanValue();
    }

    public void setEastAsianLineBreak(Boolean value) {
        if (value != null || this._p.isSetPPr()) {
            getOrCreateProperties().setEastAsianLineBreak(value);
        }
    }

    public boolean hasLatinLineBreak() {
        return ((Boolean) findDefinedParagraphProperty(new Predicate() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextParagraph$$ExternalSyntheticLambda29
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((CTTextParagraphProperties) obj).isSetLatinLnBrk();
            }
        }, new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextParagraph$$ExternalSyntheticLambda30
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Boolean.valueOf(((CTTextParagraphProperties) obj).getLatinLnBrk());
            }
        }).orElse(false)).booleanValue();
    }

    public void setLatinLineBreak(Boolean value) {
        if (value != null || this._p.isSetPPr()) {
            getOrCreateProperties().setLatinLineBreak(value);
        }
    }

    public boolean hasHangingPunctuation() {
        return ((Boolean) findDefinedParagraphProperty(new Predicate() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextParagraph$$ExternalSyntheticLambda8
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((CTTextParagraphProperties) obj).isSetHangingPunct();
            }
        }, new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextParagraph$$ExternalSyntheticLambda9
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Boolean.valueOf(((CTTextParagraphProperties) obj).getHangingPunct());
            }
        }).orElse(false)).booleanValue();
    }

    public void setHangingPunctuation(Boolean value) {
        if (value != null || this._p.isSetPPr()) {
            getOrCreateProperties().setHangingPunctuation(value);
        }
    }

    public boolean isRightToLeft() {
        return ((Boolean) findDefinedParagraphProperty(new Predicate() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextParagraph$$ExternalSyntheticLambda27
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((CTTextParagraphProperties) obj).isSetRtl();
            }
        }, new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextParagraph$$ExternalSyntheticLambda28
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Boolean.valueOf(((CTTextParagraphProperties) obj).getRtl());
            }
        }).orElse(false)).booleanValue();
    }

    public void setRightToLeft(Boolean value) {
        if (value != null || this._p.isSetPPr()) {
            getOrCreateProperties().setRightToLeft(value);
        }
    }

    public XDDFTabStop addTabStop() {
        return getOrCreateProperties().addTabStop();
    }

    public XDDFTabStop insertTabStop(int index) {
        return getOrCreateProperties().insertTabStop(index);
    }

    public void removeTabStop(int index) {
        if (this._p.isSetPPr()) {
            getProperties().removeTabStop(index);
        }
    }

    public XDDFTabStop getTabStop(int index) {
        if (this._p.isSetPPr()) {
            return getProperties().getTabStop(index);
        }
        return null;
    }

    public List<XDDFTabStop> getTabStops() {
        if (this._p.isSetPPr()) {
            return getProperties().getTabStops();
        }
        return Collections.emptyList();
    }

    public int countTabStops() {
        if (this._p.isSetPPr()) {
            return getProperties().countTabStops();
        }
        return 0;
    }

    public XDDFParagraphBulletProperties getOrCreateBulletProperties() {
        return getOrCreateProperties().getBulletProperties();
    }

    public XDDFParagraphBulletProperties getBulletProperties() {
        if (this._p.isSetPPr()) {
            return getProperties().getBulletProperties();
        }
        return null;
    }

    public XDDFRunProperties addDefaultRunProperties() {
        return getOrCreateProperties().addDefaultRunProperties();
    }

    public XDDFRunProperties getDefaultRunProperties() {
        if (this._p.isSetPPr()) {
            return getProperties().getDefaultRunProperties();
        }
        return null;
    }

    public void setDefaultRunProperties(XDDFRunProperties properties) {
        if (properties != null || this._p.isSetPPr()) {
            getOrCreateProperties().setDefaultRunProperties(properties);
        }
    }

    public XDDFRunProperties addAfterLastRunProperties() {
        if (!this._p.isSetEndParaRPr()) {
            this._p.addNewEndParaRPr();
        }
        return getAfterLastRunProperties();
    }

    public XDDFRunProperties getAfterLastRunProperties() {
        if (this._p.isSetEndParaRPr()) {
            return new XDDFRunProperties(this._p.getEndParaRPr());
        }
        return null;
    }

    public void setAfterLastRunProperties(XDDFRunProperties properties) {
        if (properties == null) {
            if (this._p.isSetEndParaRPr()) {
                this._p.unsetEndParaRPr();
                return;
            }
            return;
        }
        this._p.setEndParaRPr(properties.getXmlObject());
    }

    public XDDFSpacing extractSpacing(CTTextSpacing spacing) {
        if (spacing.isSetSpcPct()) {
            double scale = 1.0d - (this._parent.getBodyProperties().getAutoFit().getLineSpaceReduction() / 100000.0d);
            return new XDDFSpacingPercent(spacing, spacing.getSpcPct(), Double.valueOf(scale));
        }
        if (spacing.isSetSpcPts()) {
            return new XDDFSpacingPoints(spacing, spacing.getSpcPts());
        }
        return null;
    }

    private XDDFParagraphProperties getProperties() {
        if (this._properties == null) {
            this._properties = new XDDFParagraphProperties(this._p.getPPr());
        }
        return this._properties;
    }

    private XDDFParagraphProperties getOrCreateProperties() {
        if (!this._p.isSetPPr()) {
            this._properties = new XDDFParagraphProperties(this._p.addNewPPr());
        }
        return getProperties();
    }

    protected <R> Optional<R> findDefinedParagraphProperty(Predicate<CTTextParagraphProperties> isSet, Function<CTTextParagraphProperties, R> getter) {
        if (this._p.isSetPPr()) {
            int level = this._p.getPPr().isSetLvl() ? this._p.getPPr().getLvl() + 1 : 0;
            return findDefinedParagraphProperty(isSet, getter, level);
        }
        return this._parent.findDefinedParagraphProperty(isSet, getter, 0);
    }

    private <R> Optional<R> findDefinedParagraphProperty(Predicate<CTTextParagraphProperties> isSet, Function<CTTextParagraphProperties, R> getter, int level) {
        CTTextParagraphProperties props = this._p.getPPr();
        if (props != null && isSet.test(props)) {
            return Optional.ofNullable(getter.apply(props));
        }
        return this._parent.findDefinedParagraphProperty(isSet, getter, level);
    }

    public <R> Optional<R> findDefinedRunProperty(Predicate<CTTextCharacterProperties> isSet, Function<CTTextCharacterProperties, R> getter) {
        if (this._p.isSetPPr()) {
            int level = this._p.getPPr().isSetLvl() ? this._p.getPPr().getLvl() + 1 : 0;
            return findDefinedRunProperty(isSet, getter, level);
        }
        return this._parent.findDefinedRunProperty(isSet, getter, 0);
    }

    private <R> Optional<R> findDefinedRunProperty(Predicate<CTTextCharacterProperties> isSet, Function<CTTextCharacterProperties, R> getter, int level) {
        CTTextCharacterProperties props = this._p.getPPr().isSetDefRPr() ? this._p.getPPr().getDefRPr() : null;
        if (props != null && isSet.test(props)) {
            return Optional.ofNullable(getter.apply(props));
        }
        return this._parent.findDefinedRunProperty(isSet, getter, level);
    }
}
