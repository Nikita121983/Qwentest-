package org.apache.poi.xddf.usermodel.text;

import java.util.LinkedList;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.common.usermodel.fonts.FontGroup;
import org.apache.poi.ooxml.POIXMLRelation;
import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.TargetMode;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Units;
import org.apache.poi.xddf.usermodel.XDDFColor;
import org.apache.poi.xddf.usermodel.XDDFFillProperties;
import org.apache.poi.xddf.usermodel.XDDFLineProperties;
import org.apache.poi.xddf.usermodel.XDDFSolidFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTRegularTextRun;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSolidColorFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextField;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextFont;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextLineBreak;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextCapsType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextPoint;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextStrikeType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextUnderlineType;

/* loaded from: classes10.dex */
public class XDDFTextRun {
    private final XDDFTextParagraph _parent;
    private XDDFRunProperties _properties;
    private CTRegularTextRun _rtr;
    private CTTextField _tf;
    private CTTextLineBreak _tlb;

    @Internal
    public XDDFTextRun(CTTextLineBreak run, XDDFTextParagraph parent) {
        this._tlb = run;
        this._parent = parent;
    }

    @Internal
    public XDDFTextRun(CTTextField run, XDDFTextParagraph parent) {
        this._tf = run;
        this._parent = parent;
    }

    @Internal
    public XDDFTextRun(CTRegularTextRun run, XDDFTextParagraph parent) {
        this._rtr = run;
        this._parent = parent;
    }

    public XDDFTextParagraph getParentParagraph() {
        return this._parent;
    }

    public boolean isLineBreak() {
        return this._tlb != null;
    }

    public boolean isField() {
        return this._tf != null;
    }

    public boolean isRegularRun() {
        return this._rtr != null;
    }

    public String getText() {
        if (isLineBreak()) {
            return StringUtils.LF;
        }
        if (isField()) {
            return this._tf.getT();
        }
        return this._rtr.getT();
    }

    public void setText(String text) {
        if (isField()) {
            this._tf.setT(text);
        } else if (isRegularRun()) {
            this._rtr.setT(text);
        }
    }

    public void setDirty(Boolean dirty) {
        getOrCreateProperties().setDirty(dirty);
    }

    public Boolean getDirty() {
        return (Boolean) findDefinedProperty(new Predicate() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda33
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((CTTextCharacterProperties) obj).isSetDirty();
            }
        }, new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda44
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Boolean.valueOf(((CTTextCharacterProperties) obj).getDirty());
            }
        }).orElse(null);
    }

    public void setSpellError(Boolean error) {
        getOrCreateProperties().setSpellError(error);
    }

    public Boolean getSpellError() {
        return (Boolean) findDefinedProperty(new Predicate() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda28
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((CTTextCharacterProperties) obj).isSetErr();
            }
        }, new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda29
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Boolean.valueOf(((CTTextCharacterProperties) obj).getErr());
            }
        }).orElse(null);
    }

    public void setNoProof(Boolean noproof) {
        getOrCreateProperties().setNoProof(noproof);
    }

    public Boolean getNoProof() {
        return (Boolean) findDefinedProperty(new Predicate() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda6
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((CTTextCharacterProperties) obj).isSetNoProof();
            }
        }, new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda7
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Boolean.valueOf(((CTTextCharacterProperties) obj).getNoProof());
            }
        }).orElse(null);
    }

    public void setNormalizeHeights(Boolean normalize) {
        getOrCreateProperties().setNormalizeHeights(normalize);
    }

    public Boolean getNormalizeHeights() {
        return (Boolean) findDefinedProperty(new Predicate() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda10
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((CTTextCharacterProperties) obj).isSetNormalizeH();
            }
        }, new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda12
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Boolean.valueOf(((CTTextCharacterProperties) obj).getNormalizeH());
            }
        }).orElse(null);
    }

    public void setKumimoji(Boolean kumimoji) {
        getOrCreateProperties().setKumimoji(kumimoji);
    }

    public boolean isKumimoji() {
        return ((Boolean) findDefinedProperty(new Predicate() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda8
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((CTTextCharacterProperties) obj).isSetKumimoji();
            }
        }, new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda9
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Boolean.valueOf(((CTTextCharacterProperties) obj).getKumimoji());
            }
        }).orElse(false)).booleanValue();
    }

    public void setBold(Boolean bold) {
        getOrCreateProperties().setBold(bold);
    }

    public boolean isBold() {
        return ((Boolean) findDefinedProperty(new Predicate() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda65
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((CTTextCharacterProperties) obj).isSetB();
            }
        }, new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda67
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Boolean.valueOf(((CTTextCharacterProperties) obj).getB());
            }
        }).orElse(false)).booleanValue();
    }

    public void setItalic(Boolean italic) {
        getOrCreateProperties().setItalic(italic);
    }

    public boolean isItalic() {
        return ((Boolean) findDefinedProperty(new Predicate() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda34
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((CTTextCharacterProperties) obj).isSetI();
            }
        }, new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda35
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Boolean.valueOf(((CTTextCharacterProperties) obj).getI());
            }
        }).orElse(false)).booleanValue();
    }

    public void setStrikeThrough(StrikeType strike) {
        getOrCreateProperties().setStrikeThrough(strike);
    }

    public boolean isStrikeThrough() {
        return ((Boolean) findDefinedProperty(new XDDFTextRun$$ExternalSyntheticLambda3(), new XDDFTextRun$$ExternalSyntheticLambda4()).map(new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda42
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Boolean valueOf;
                valueOf = Boolean.valueOf(strike != STTextStrikeType.NO_STRIKE);
                return valueOf;
            }
        }).orElse(false)).booleanValue();
    }

    public StrikeType getStrikeThrough() {
        return (StrikeType) findDefinedProperty(new XDDFTextRun$$ExternalSyntheticLambda3(), new XDDFTextRun$$ExternalSyntheticLambda4()).map(new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda5
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return StrikeType.valueOf((STTextStrikeType.Enum) obj);
            }
        }).orElse(null);
    }

    public void setUnderline(UnderlineType underline) {
        getOrCreateProperties().setUnderline(underline);
    }

    public boolean isUnderline() {
        return ((Boolean) findDefinedProperty(new XDDFTextRun$$ExternalSyntheticLambda58(), new XDDFTextRun$$ExternalSyntheticLambda59()).map(new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda61
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Boolean valueOf;
                valueOf = Boolean.valueOf(underline != STTextUnderlineType.NONE);
                return valueOf;
            }
        }).orElse(false)).booleanValue();
    }

    public UnderlineType getUnderline() {
        return (UnderlineType) findDefinedProperty(new XDDFTextRun$$ExternalSyntheticLambda58(), new XDDFTextRun$$ExternalSyntheticLambda59()).map(new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda60
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return UnderlineType.valueOf((STTextUnderlineType.Enum) obj);
            }
        }).orElse(null);
    }

    public void setCapitals(CapsType caps) {
        getOrCreateProperties().setCapitals(caps);
    }

    public boolean isCapitals() {
        return ((Boolean) findDefinedProperty(new XDDFTextRun$$ExternalSyntheticLambda30(), new XDDFTextRun$$ExternalSyntheticLambda31()).map(new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda32
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Boolean valueOf;
                valueOf = Boolean.valueOf(caps != STTextCapsType.NONE);
                return valueOf;
            }
        }).orElse(false)).booleanValue();
    }

    public CapsType getCapitals() {
        return (CapsType) findDefinedProperty(new XDDFTextRun$$ExternalSyntheticLambda30(), new XDDFTextRun$$ExternalSyntheticLambda31()).map(new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda62
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return CapsType.valueOf((STTextCapsType.Enum) obj);
            }
        }).orElse(null);
    }

    public boolean isSubscript() {
        return ((Boolean) findDefinedProperty(new XDDFTextRun$$ExternalSyntheticLambda20(), new XDDFTextRun$$ExternalSyntheticLambda21()).map(new XDDFTextRun$$ExternalSyntheticLambda23()).map(new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda27
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Boolean valueOf;
                valueOf = Boolean.valueOf(baseline.intValue() < 0);
                return valueOf;
            }
        }).orElse(false)).booleanValue();
    }

    public boolean isSuperscript() {
        return ((Boolean) findDefinedProperty(new XDDFTextRun$$ExternalSyntheticLambda20(), new XDDFTextRun$$ExternalSyntheticLambda21()).map(new XDDFTextRun$$ExternalSyntheticLambda23()).map(new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda24
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Boolean valueOf;
                valueOf = Boolean.valueOf(baseline.intValue() > 0);
                return valueOf;
            }
        }).orElse(false)).booleanValue();
    }

    public void setBaseline(Double offset) {
        if (offset == null) {
            getOrCreateProperties().setBaseline(null);
        } else {
            getOrCreateProperties().setBaseline(Integer.valueOf((int) (offset.doubleValue() * 1000.0d)));
        }
    }

    public void setSuperscript(Double offset) {
        setBaseline(offset == null ? null : Double.valueOf(Math.abs(offset.doubleValue())));
    }

    public void setSubscript(Double offset) {
        setBaseline(offset == null ? null : Double.valueOf(-Math.abs(offset.doubleValue())));
    }

    public void setFillProperties(XDDFFillProperties properties) {
        getOrCreateProperties().setFillProperties(properties);
    }

    public void setFontColor(XDDFColor color) {
        XDDFSolidFillProperties props = new XDDFSolidFillProperties();
        props.setColor(color);
        setFillProperties(props);
    }

    public XDDFColor getFontColor() {
        XDDFSolidFillProperties solid = (XDDFSolidFillProperties) findDefinedProperty(new Predicate() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda39
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((CTTextCharacterProperties) obj).isSetSolidFill();
            }
        }, new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda40
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((CTTextCharacterProperties) obj).getSolidFill();
            }
        }).map(new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda41
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return new XDDFSolidFillProperties((CTSolidColorFillProperties) obj);
            }
        }).orElse(new XDDFSolidFillProperties());
        return solid.getColor();
    }

    public void setFonts(XDDFFont[] fonts) {
        getOrCreateProperties().setFonts(fonts);
    }

    public XDDFFont[] getFonts() {
        final LinkedList<XDDFFont> list = new LinkedList<>();
        Optional map = findDefinedProperty(new Predicate() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda43
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((CTTextCharacterProperties) obj).isSetCs();
            }
        }, new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda50
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((CTTextCharacterProperties) obj).getCs();
            }
        }).map(new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda51
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return XDDFTextRun.lambda$getFonts$5((CTTextFont) obj);
            }
        });
        list.getClass();
        map.ifPresent(new Consumer() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda46
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                list.add((XDDFFont) obj);
            }
        });
        Optional map2 = findDefinedProperty(new Predicate() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda52
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((CTTextCharacterProperties) obj).isSetEa();
            }
        }, new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda53
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((CTTextCharacterProperties) obj).getEa();
            }
        }).map(new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda54
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return XDDFTextRun.lambda$getFonts$6((CTTextFont) obj);
            }
        });
        list.getClass();
        map2.ifPresent(new Consumer() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda46
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                list.add((XDDFFont) obj);
            }
        });
        Optional map3 = findDefinedProperty(new Predicate() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda56
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((CTTextCharacterProperties) obj).isSetLatin();
            }
        }, new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda57
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((CTTextCharacterProperties) obj).getLatin();
            }
        }).map(new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda45
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return XDDFTextRun.lambda$getFonts$7((CTTextFont) obj);
            }
        });
        list.getClass();
        map3.ifPresent(new Consumer() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda46
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                list.add((XDDFFont) obj);
            }
        });
        Optional map4 = findDefinedProperty(new Predicate() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda47
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((CTTextCharacterProperties) obj).isSetSym();
            }
        }, new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda48
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((CTTextCharacterProperties) obj).getSym();
            }
        }).map(new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda49
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return XDDFTextRun.lambda$getFonts$8((CTTextFont) obj);
            }
        });
        list.getClass();
        map4.ifPresent(new Consumer() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda46
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                list.add((XDDFFont) obj);
            }
        });
        return (XDDFFont[]) list.toArray(new XDDFFont[0]);
    }

    public static /* synthetic */ XDDFFont lambda$getFonts$5(CTTextFont font) {
        return new XDDFFont(FontGroup.COMPLEX_SCRIPT, font);
    }

    public static /* synthetic */ XDDFFont lambda$getFonts$6(CTTextFont font) {
        return new XDDFFont(FontGroup.EAST_ASIAN, font);
    }

    public static /* synthetic */ XDDFFont lambda$getFonts$7(CTTextFont font) {
        return new XDDFFont(FontGroup.LATIN, font);
    }

    public static /* synthetic */ XDDFFont lambda$getFonts$8(CTTextFont font) {
        return new XDDFFont(FontGroup.SYMBOL, font);
    }

    public void setFontSize(Double size) {
        getOrCreateProperties().setFontSize(size);
    }

    public Double getFontSize() {
        Integer size = (Integer) findDefinedProperty(new Predicate() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda25
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((CTTextCharacterProperties) obj).isSetSz();
            }
        }, new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda26
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Integer.valueOf(((CTTextCharacterProperties) obj).getSz());
            }
        }).orElse(1100);
        double scale = this._parent.getParentBody().getBodyProperties().getAutoFit().getFontScale() / 1.0E7d;
        return Double.valueOf(size.intValue() * scale);
    }

    public void setCharacterKerning(Double kerning) {
        getOrCreateProperties().setCharacterKerning(kerning);
    }

    public Double getCharacterKerning() {
        return (Double) findDefinedProperty(new Predicate() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda15
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((CTTextCharacterProperties) obj).isSetKern();
            }
        }, new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda16
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Integer.valueOf(((CTTextCharacterProperties) obj).getKern());
            }
        }).map(new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda17
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Double valueOf;
                Integer num = (Integer) obj;
                valueOf = Double.valueOf(num.intValue() * 0.01d);
                return valueOf;
            }
        }).orElse(null);
    }

    public void setCharacterSpacing(Double spacing) {
        getOrCreateProperties().setCharacterSpacing(spacing);
    }

    public Double getCharacterSpacing() {
        return (Double) findDefinedProperty(new Predicate() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda55
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((CTTextCharacterProperties) obj).isSetSpc();
            }
        }, new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda66
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((CTTextCharacterProperties) obj).xgetSpc();
            }
        }).map(new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda71
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Long.valueOf(POIXMLUnits.parseLength((STTextPoint) obj));
            }
        }).map(new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda72
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                double points;
                points = Units.toPoints(((Long) obj).longValue());
                return Double.valueOf(points);
            }
        }).orElse(null);
    }

    public void setBookmark(String bookmark) {
        getOrCreateProperties().setBookmark(bookmark);
    }

    public String getBookmark() {
        return (String) findDefinedProperty(new Predicate() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda13
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((CTTextCharacterProperties) obj).isSetBmk();
            }
        }, new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda14
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((CTTextCharacterProperties) obj).getBmk();
            }
        }).orElse(null);
    }

    public XDDFHyperlink linkToExternal(String url, PackagePart localPart, POIXMLRelation relation) {
        PackageRelationship rel = localPart.addExternalRelationship(url, relation.getRelation());
        XDDFHyperlink link = new XDDFHyperlink(rel.getId());
        getOrCreateProperties().setHyperlink(link);
        return link;
    }

    public XDDFHyperlink linkToAction(String action) {
        XDDFHyperlink link = new XDDFHyperlink("", action);
        getOrCreateProperties().setHyperlink(link);
        return link;
    }

    public XDDFHyperlink linkToInternal(String action, PackagePart localPart, POIXMLRelation relation, PackagePartName target) {
        PackageRelationship rel = localPart.addRelationship(target, TargetMode.INTERNAL, relation.getRelation());
        XDDFHyperlink link = new XDDFHyperlink(rel.getId(), action);
        getOrCreateProperties().setHyperlink(link);
        return link;
    }

    public XDDFHyperlink getHyperlink() {
        return (XDDFHyperlink) findDefinedProperty(new Predicate() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda18
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((CTTextCharacterProperties) obj).isSetHlinkClick();
            }
        }, new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda19
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((CTTextCharacterProperties) obj).getHlinkClick();
            }
        }).map(new XDDFTextRun$$ExternalSyntheticLambda2()).orElse(null);
    }

    public XDDFHyperlink createMouseOver(String action) {
        XDDFHyperlink link = new XDDFHyperlink("", action);
        getOrCreateProperties().setMouseOver(link);
        return link;
    }

    public XDDFHyperlink getMouseOver() {
        return (XDDFHyperlink) findDefinedProperty(new Predicate() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda73
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((CTTextCharacterProperties) obj).isSetHlinkMouseOver();
            }
        }, new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda1
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((CTTextCharacterProperties) obj).getHlinkMouseOver();
            }
        }).map(new XDDFTextRun$$ExternalSyntheticLambda2()).orElse(null);
    }

    public void setLanguage(Locale lang) {
        getOrCreateProperties().setLanguage(lang);
    }

    public Locale getLanguage() {
        return (Locale) findDefinedProperty(new Predicate() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda63
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((CTTextCharacterProperties) obj).isSetLang();
            }
        }, new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda64
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((CTTextCharacterProperties) obj).getLang();
            }
        }).map(new XDDFTextRun$$ExternalSyntheticLambda38()).orElse(null);
    }

    public void setAlternativeLanguage(Locale lang) {
        getOrCreateProperties().setAlternativeLanguage(lang);
    }

    public Locale getAlternativeLanguage() {
        return (Locale) findDefinedProperty(new Predicate() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda36
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((CTTextCharacterProperties) obj).isSetAltLang();
            }
        }, new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda37
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((CTTextCharacterProperties) obj).getAltLang();
            }
        }).map(new XDDFTextRun$$ExternalSyntheticLambda38()).orElse(null);
    }

    public void setHighlight(XDDFColor color) {
        getOrCreateProperties().setHighlight(color);
    }

    public XDDFColor getHighlight() {
        return (XDDFColor) findDefinedProperty(new Predicate() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((CTTextCharacterProperties) obj).isSetHighlight();
            }
        }, new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda11
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((CTTextCharacterProperties) obj).getHighlight();
            }
        }).map(new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda22
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return XDDFColor.forColorContainer((CTColor) obj);
            }
        }).orElse(null);
    }

    public void setLineProperties(XDDFLineProperties properties) {
        getOrCreateProperties().setLineProperties(properties);
    }

    public XDDFLineProperties getLineProperties() {
        return (XDDFLineProperties) findDefinedProperty(new Predicate() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda68
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((CTTextCharacterProperties) obj).isSetLn();
            }
        }, new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda69
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((CTTextCharacterProperties) obj).getLn();
            }
        }).map(new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextRun$$ExternalSyntheticLambda70
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return new XDDFLineProperties((CTLineProperties) obj);
            }
        }).orElse(null);
    }

    private <R> Optional<R> findDefinedProperty(Predicate<CTTextCharacterProperties> isSet, Function<CTTextCharacterProperties, R> getter) {
        CTTextCharacterProperties props = getProperties();
        if (props != null && isSet.test(props)) {
            return Optional.ofNullable(getter.apply(props));
        }
        return this._parent.findDefinedRunProperty(isSet, getter);
    }

    @Internal
    public CTTextCharacterProperties getProperties() {
        if (isLineBreak() && this._tlb.isSetRPr()) {
            return this._tlb.getRPr();
        }
        if (isField() && this._tf.isSetRPr()) {
            return this._tf.getRPr();
        }
        if (isRegularRun() && this._rtr.isSetRPr()) {
            return this._rtr.getRPr();
        }
        XDDFRunProperties defaultProperties = this._parent.getDefaultRunProperties();
        if (defaultProperties == null) {
            return null;
        }
        return defaultProperties.getXmlObject();
    }

    private XDDFRunProperties getOrCreateProperties() {
        if (this._properties == null) {
            if (isLineBreak()) {
                this._properties = new XDDFRunProperties(this._tlb.isSetRPr() ? this._tlb.getRPr() : this._tlb.addNewRPr());
            } else if (isField()) {
                this._properties = new XDDFRunProperties(this._tf.isSetRPr() ? this._tf.getRPr() : this._tf.addNewRPr());
            } else if (isRegularRun()) {
                this._properties = new XDDFRunProperties(this._rtr.isSetRPr() ? this._rtr.getRPr() : this._rtr.addNewRPr());
            }
        }
        return this._properties;
    }
}
