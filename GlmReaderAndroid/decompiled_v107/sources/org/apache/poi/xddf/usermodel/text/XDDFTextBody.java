package org.apache.poi.xddf.usermodel.text;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties;

/* loaded from: classes10.dex */
public class XDDFTextBody {
    private CTTextBody _body;
    private TextContainer _parent;

    public XDDFTextBody(TextContainer parent) {
        this(parent, CTTextBody.Factory.newInstance());
    }

    @Internal
    public XDDFTextBody(TextContainer parent, CTTextBody body) {
        this._parent = parent;
        this._body = body;
    }

    @Internal
    public CTTextBody getXmlObject() {
        return this._body;
    }

    public TextContainer getParentShape() {
        return this._parent;
    }

    public XDDFTextParagraph initialize() {
        this._body.addNewLstStyle();
        this._body.addNewBodyPr();
        XDDFBodyProperties bp = getBodyProperties();
        bp.setAnchoring(AnchorType.TOP);
        bp.setRightToLeft(false);
        XDDFTextParagraph p = addNewParagraph();
        p.setTextAlignment(TextAlignment.LEFT);
        p.appendRegularRun("");
        XDDFRunProperties end = p.addAfterLastRunProperties();
        end.setLanguage(Locale.US);
        end.setFontSize(Double.valueOf(11.0d));
        return p;
    }

    public void setText(String text) {
        if (this._body.sizeOfPArray() > 0) {
            for (int i = this._body.sizeOfPArray() - 1; i > 0; i--) {
                this._body.removeP(i);
            }
            getParagraph(0).setText(text);
            return;
        }
        initialize().setText(text);
    }

    public XDDFTextParagraph addNewParagraph() {
        return new XDDFTextParagraph(this._body.addNewP(), this);
    }

    public XDDFTextParagraph insertNewParagraph(int index) {
        return new XDDFTextParagraph(this._body.insertNewP(index), this);
    }

    public void removeParagraph(int index) {
        this._body.removeP(index);
    }

    public XDDFTextParagraph getParagraph(int index) {
        return new XDDFTextParagraph(this._body.getPArray(index), this);
    }

    public List<XDDFTextParagraph> getParagraphs() {
        return Collections.unmodifiableList((List) this._body.getPList().stream().map(new Function() { // from class: org.apache.poi.xddf.usermodel.text.XDDFTextBody$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return XDDFTextBody.this.m2568x31cc1ace((CTTextParagraph) obj);
            }
        }).collect(Collectors.toList()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getParagraphs$0$org-apache-poi-xddf-usermodel-text-XDDFTextBody, reason: not valid java name */
    public /* synthetic */ XDDFTextParagraph m2568x31cc1ace(CTTextParagraph ds) {
        return new XDDFTextParagraph(ds, this);
    }

    public XDDFBodyProperties getBodyProperties() {
        return new XDDFBodyProperties(this._body.getBodyPr());
    }

    public void setBodyProperties(XDDFBodyProperties properties) {
        if (properties == null) {
            this._body.addNewBodyPr();
        } else {
            this._body.setBodyPr(properties.getXmlObject());
        }
    }

    public XDDFParagraphProperties getDefaultProperties() {
        if (this._body.isSetLstStyle() && this._body.getLstStyle().isSetDefPPr()) {
            return new XDDFParagraphProperties(this._body.getLstStyle().getDefPPr());
        }
        return null;
    }

    public void setDefaultProperties(XDDFParagraphProperties properties) {
        if (properties == null) {
            if (this._body.isSetLstStyle()) {
                CTTextListStyle style = this._body.getLstStyle();
                if (style.isSetDefPPr()) {
                    style.unsetDefPPr();
                    return;
                }
                return;
            }
            return;
        }
        (this._body.isSetLstStyle() ? this._body.getLstStyle() : this._body.addNewLstStyle()).setDefPPr(properties.getXmlObject());
    }

    public XDDFParagraphProperties getLevel1Properties() {
        if (this._body.isSetLstStyle() && this._body.getLstStyle().isSetLvl1PPr()) {
            return new XDDFParagraphProperties(this._body.getLstStyle().getLvl1PPr());
        }
        return null;
    }

    public void setLevel1Properties(XDDFParagraphProperties properties) {
        if (properties == null) {
            if (this._body.isSetLstStyle()) {
                CTTextListStyle style = this._body.getLstStyle();
                if (style.isSetLvl1PPr()) {
                    style.unsetLvl1PPr();
                    return;
                }
                return;
            }
            return;
        }
        (this._body.isSetLstStyle() ? this._body.getLstStyle() : this._body.addNewLstStyle()).setLvl1PPr(properties.getXmlObject());
    }

    public XDDFParagraphProperties getLevel2Properties() {
        if (this._body.isSetLstStyle() && this._body.getLstStyle().isSetLvl2PPr()) {
            return new XDDFParagraphProperties(this._body.getLstStyle().getLvl2PPr());
        }
        return null;
    }

    public void setLevel2Properties(XDDFParagraphProperties properties) {
        if (properties == null) {
            if (this._body.isSetLstStyle()) {
                CTTextListStyle style = this._body.getLstStyle();
                if (style.isSetLvl2PPr()) {
                    style.unsetLvl2PPr();
                    return;
                }
                return;
            }
            return;
        }
        (this._body.isSetLstStyle() ? this._body.getLstStyle() : this._body.addNewLstStyle()).setLvl2PPr(properties.getXmlObject());
    }

    public XDDFParagraphProperties getLevel3Properties() {
        if (this._body.isSetLstStyle() && this._body.getLstStyle().isSetLvl3PPr()) {
            return new XDDFParagraphProperties(this._body.getLstStyle().getLvl3PPr());
        }
        return null;
    }

    public void setLevel3Properties(XDDFParagraphProperties properties) {
        if (properties == null) {
            if (this._body.isSetLstStyle()) {
                CTTextListStyle style = this._body.getLstStyle();
                if (style.isSetLvl3PPr()) {
                    style.unsetLvl3PPr();
                    return;
                }
                return;
            }
            return;
        }
        (this._body.isSetLstStyle() ? this._body.getLstStyle() : this._body.addNewLstStyle()).setLvl3PPr(properties.getXmlObject());
    }

    public XDDFParagraphProperties getLevel4Properties() {
        if (this._body.isSetLstStyle() && this._body.getLstStyle().isSetLvl4PPr()) {
            return new XDDFParagraphProperties(this._body.getLstStyle().getLvl4PPr());
        }
        return null;
    }

    public void setLevel4Properties(XDDFParagraphProperties properties) {
        if (properties == null) {
            if (this._body.isSetLstStyle()) {
                CTTextListStyle style = this._body.getLstStyle();
                if (style.isSetLvl4PPr()) {
                    style.unsetLvl4PPr();
                    return;
                }
                return;
            }
            return;
        }
        (this._body.isSetLstStyle() ? this._body.getLstStyle() : this._body.addNewLstStyle()).setLvl4PPr(properties.getXmlObject());
    }

    public XDDFParagraphProperties getLevel5Properties() {
        if (this._body.isSetLstStyle() && this._body.getLstStyle().isSetLvl5PPr()) {
            return new XDDFParagraphProperties(this._body.getLstStyle().getLvl5PPr());
        }
        return null;
    }

    public void setLevel5Properties(XDDFParagraphProperties properties) {
        if (properties == null) {
            if (this._body.isSetLstStyle()) {
                CTTextListStyle style = this._body.getLstStyle();
                if (style.isSetLvl5PPr()) {
                    style.unsetLvl5PPr();
                    return;
                }
                return;
            }
            return;
        }
        (this._body.isSetLstStyle() ? this._body.getLstStyle() : this._body.addNewLstStyle()).setLvl5PPr(properties.getXmlObject());
    }

    public XDDFParagraphProperties getLevel6Properties() {
        if (this._body.isSetLstStyle() && this._body.getLstStyle().isSetLvl6PPr()) {
            return new XDDFParagraphProperties(this._body.getLstStyle().getLvl6PPr());
        }
        return null;
    }

    public void setLevel6Properties(XDDFParagraphProperties properties) {
        if (properties == null) {
            if (this._body.isSetLstStyle()) {
                CTTextListStyle style = this._body.getLstStyle();
                if (style.isSetLvl6PPr()) {
                    style.unsetLvl6PPr();
                    return;
                }
                return;
            }
            return;
        }
        (this._body.isSetLstStyle() ? this._body.getLstStyle() : this._body.addNewLstStyle()).setLvl6PPr(properties.getXmlObject());
    }

    public XDDFParagraphProperties getLevel7Properties() {
        if (this._body.isSetLstStyle() && this._body.getLstStyle().isSetLvl7PPr()) {
            return new XDDFParagraphProperties(this._body.getLstStyle().getLvl7PPr());
        }
        return null;
    }

    public void setLevel7Properties(XDDFParagraphProperties properties) {
        if (properties == null) {
            if (this._body.isSetLstStyle()) {
                CTTextListStyle style = this._body.getLstStyle();
                if (style.isSetLvl7PPr()) {
                    style.unsetLvl7PPr();
                    return;
                }
                return;
            }
            return;
        }
        (this._body.isSetLstStyle() ? this._body.getLstStyle() : this._body.addNewLstStyle()).setLvl7PPr(properties.getXmlObject());
    }

    public XDDFParagraphProperties getLevel8Properties() {
        if (this._body.isSetLstStyle() && this._body.getLstStyle().isSetLvl8PPr()) {
            return new XDDFParagraphProperties(this._body.getLstStyle().getLvl8PPr());
        }
        return null;
    }

    public void setLevel8Properties(XDDFParagraphProperties properties) {
        if (properties == null) {
            if (this._body.isSetLstStyle()) {
                CTTextListStyle style = this._body.getLstStyle();
                if (style.isSetLvl8PPr()) {
                    style.unsetLvl8PPr();
                    return;
                }
                return;
            }
            return;
        }
        (this._body.isSetLstStyle() ? this._body.getLstStyle() : this._body.addNewLstStyle()).setLvl8PPr(properties.getXmlObject());
    }

    public XDDFParagraphProperties getLevel9Properties() {
        if (this._body.isSetLstStyle() && this._body.getLstStyle().isSetLvl9PPr()) {
            return new XDDFParagraphProperties(this._body.getLstStyle().getLvl9PPr());
        }
        return null;
    }

    public void setLevel9Properties(XDDFParagraphProperties properties) {
        if (properties == null) {
            if (this._body.isSetLstStyle()) {
                CTTextListStyle style = this._body.getLstStyle();
                if (style.isSetLvl9PPr()) {
                    style.unsetLvl9PPr();
                    return;
                }
                return;
            }
            return;
        }
        (this._body.isSetLstStyle() ? this._body.getLstStyle() : this._body.addNewLstStyle()).setLvl9PPr(properties.getXmlObject());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public <R> Optional<R> findDefinedParagraphProperty(Predicate<CTTextParagraphProperties> isSet, Function<CTTextParagraphProperties, R> getter, int level) {
        if (this._body.isSetLstStyle() && level >= 0) {
            CTTextListStyle list = this._body.getLstStyle();
            CTTextParagraphProperties props = level == 0 ? list.getDefPPr() : retrieveProperties(list, level);
            if (props != null && isSet.test(props)) {
                return Optional.of(getter.apply(props));
            }
            return findDefinedParagraphProperty(isSet, getter, level - 1);
        }
        if (this._parent != null) {
            return this._parent.findDefinedParagraphProperty(isSet, getter);
        }
        return Optional.empty();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public <R> Optional<R> findDefinedRunProperty(Predicate<CTTextCharacterProperties> isSet, Function<CTTextCharacterProperties, R> getter, int level) {
        if (this._body.isSetLstStyle() && level >= 0) {
            CTTextListStyle list = this._body.getLstStyle();
            CTTextParagraphProperties props = level == 0 ? list.getDefPPr() : retrieveProperties(list, level);
            if (props != null && props.isSetDefRPr() && isSet.test(props.getDefRPr())) {
                return Optional.of(getter.apply(props.getDefRPr()));
            }
            return findDefinedRunProperty(isSet, getter, level - 1);
        }
        if (this._parent != null) {
            return this._parent.findDefinedRunProperty(isSet, getter);
        }
        return Optional.empty();
    }

    private CTTextParagraphProperties retrieveProperties(CTTextListStyle list, int level) {
        switch (level) {
            case 1:
                if (!list.isSetLvl1PPr()) {
                    return null;
                }
                return list.getLvl1PPr();
            case 2:
                if (!list.isSetLvl2PPr()) {
                    return null;
                }
                return list.getLvl2PPr();
            case 3:
                if (!list.isSetLvl3PPr()) {
                    return null;
                }
                return list.getLvl3PPr();
            case 4:
                if (!list.isSetLvl4PPr()) {
                    return null;
                }
                return list.getLvl4PPr();
            case 5:
                if (!list.isSetLvl5PPr()) {
                    return null;
                }
                return list.getLvl5PPr();
            case 6:
                if (!list.isSetLvl6PPr()) {
                    return null;
                }
                return list.getLvl6PPr();
            case 7:
                if (!list.isSetLvl7PPr()) {
                    return null;
                }
                return list.getLvl7PPr();
            case 8:
                if (!list.isSetLvl8PPr()) {
                    return null;
                }
                return list.getLvl8PPr();
            case 9:
                if (!list.isSetLvl9PPr()) {
                    return null;
                }
                return list.getLvl9PPr();
            default:
                return null;
        }
    }
}
