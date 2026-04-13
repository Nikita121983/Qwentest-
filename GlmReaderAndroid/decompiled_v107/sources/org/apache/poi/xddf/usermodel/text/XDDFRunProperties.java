package org.apache.poi.xddf.usermodel.text;

import java.util.Locale;
import java.util.function.Consumer;
import java.util.function.Supplier;
import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.XDDFColor;
import org.apache.poi.xddf.usermodel.XDDFEffectContainer;
import org.apache.poi.xddf.usermodel.XDDFEffectList;
import org.apache.poi.xddf.usermodel.XDDFExtensionList;
import org.apache.poi.xddf.usermodel.XDDFFillProperties;
import org.apache.poi.xddf.usermodel.XDDFGradientFillProperties;
import org.apache.poi.xddf.usermodel.XDDFGroupFillProperties;
import org.apache.poi.xddf.usermodel.XDDFLineProperties;
import org.apache.poi.xddf.usermodel.XDDFNoFillProperties;
import org.apache.poi.xddf.usermodel.XDDFPatternFillProperties;
import org.apache.poi.xddf.usermodel.XDDFPictureFillProperties;
import org.apache.poi.xddf.usermodel.XDDFSolidFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTHyperlink;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextFont;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextCapsType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextStrikeType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextUnderlineType;

/* loaded from: classes10.dex */
public class XDDFRunProperties {
    private CTTextCharacterProperties props;

    public XDDFRunProperties() {
        this(CTTextCharacterProperties.Factory.newInstance());
    }

    @Internal
    public XDDFRunProperties(CTTextCharacterProperties properties) {
        this.props = properties;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public CTTextCharacterProperties getXmlObject() {
        return this.props;
    }

    public void setBaseline(Integer value) {
        final CTTextCharacterProperties cTTextCharacterProperties = this.props;
        cTTextCharacterProperties.getClass();
        Supplier supplier = new Supplier() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda19
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(CTTextCharacterProperties.this.isSetBaseline());
            }
        };
        final CTTextCharacterProperties cTTextCharacterProperties2 = this.props;
        cTTextCharacterProperties2.getClass();
        Runnable runnable = new Runnable() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda20
            @Override // java.lang.Runnable
            public final void run() {
                CTTextCharacterProperties.this.unsetBaseline();
            }
        };
        final CTTextCharacterProperties cTTextCharacterProperties3 = this.props;
        cTTextCharacterProperties3.getClass();
        update(supplier, runnable, new Consumer() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda21
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                CTTextCharacterProperties.this.setBaseline((Integer) obj);
            }
        }, value);
    }

    public void setDirty(Boolean dirty) {
        final CTTextCharacterProperties cTTextCharacterProperties = this.props;
        cTTextCharacterProperties.getClass();
        Supplier supplier = new Supplier() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda32
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(CTTextCharacterProperties.this.isSetDirty());
            }
        };
        final CTTextCharacterProperties cTTextCharacterProperties2 = this.props;
        cTTextCharacterProperties2.getClass();
        Runnable runnable = new Runnable() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda34
            @Override // java.lang.Runnable
            public final void run() {
                CTTextCharacterProperties.this.unsetDirty();
            }
        };
        final CTTextCharacterProperties cTTextCharacterProperties3 = this.props;
        cTTextCharacterProperties3.getClass();
        update(supplier, runnable, new Consumer() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda35
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                CTTextCharacterProperties.this.setDirty(((Boolean) obj).booleanValue());
            }
        }, dirty);
    }

    public void setSpellError(Boolean error) {
        final CTTextCharacterProperties cTTextCharacterProperties = this.props;
        cTTextCharacterProperties.getClass();
        Supplier supplier = new Supplier() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda79
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(CTTextCharacterProperties.this.isSetErr());
            }
        };
        final CTTextCharacterProperties cTTextCharacterProperties2 = this.props;
        cTTextCharacterProperties2.getClass();
        Runnable runnable = new Runnable() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda80
            @Override // java.lang.Runnable
            public final void run() {
                CTTextCharacterProperties.this.unsetErr();
            }
        };
        final CTTextCharacterProperties cTTextCharacterProperties3 = this.props;
        cTTextCharacterProperties3.getClass();
        update(supplier, runnable, new Consumer() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda81
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                CTTextCharacterProperties.this.setErr(((Boolean) obj).booleanValue());
            }
        }, error);
    }

    public void setNoProof(Boolean noproof) {
        final CTTextCharacterProperties cTTextCharacterProperties = this.props;
        cTTextCharacterProperties.getClass();
        Supplier supplier = new Supplier() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda66
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(CTTextCharacterProperties.this.isSetNoProof());
            }
        };
        final CTTextCharacterProperties cTTextCharacterProperties2 = this.props;
        cTTextCharacterProperties2.getClass();
        Runnable runnable = new Runnable() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda77
            @Override // java.lang.Runnable
            public final void run() {
                CTTextCharacterProperties.this.unsetNoProof();
            }
        };
        final CTTextCharacterProperties cTTextCharacterProperties3 = this.props;
        cTTextCharacterProperties3.getClass();
        update(supplier, runnable, new Consumer() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda82
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                CTTextCharacterProperties.this.setNoProof(((Boolean) obj).booleanValue());
            }
        }, noproof);
    }

    public void setNormalizeHeights(Boolean normalize) {
        final CTTextCharacterProperties cTTextCharacterProperties = this.props;
        cTTextCharacterProperties.getClass();
        Supplier supplier = new Supplier() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda75
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(CTTextCharacterProperties.this.isSetNormalizeH());
            }
        };
        final CTTextCharacterProperties cTTextCharacterProperties2 = this.props;
        cTTextCharacterProperties2.getClass();
        Runnable runnable = new Runnable() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda76
            @Override // java.lang.Runnable
            public final void run() {
                CTTextCharacterProperties.this.unsetNormalizeH();
            }
        };
        final CTTextCharacterProperties cTTextCharacterProperties3 = this.props;
        cTTextCharacterProperties3.getClass();
        update(supplier, runnable, new Consumer() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda78
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                CTTextCharacterProperties.this.setNormalizeH(((Boolean) obj).booleanValue());
            }
        }, normalize);
    }

    public void setKumimoji(Boolean kumimoji) {
        final CTTextCharacterProperties cTTextCharacterProperties = this.props;
        cTTextCharacterProperties.getClass();
        Supplier supplier = new Supplier() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda16
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(CTTextCharacterProperties.this.isSetKumimoji());
            }
        };
        final CTTextCharacterProperties cTTextCharacterProperties2 = this.props;
        cTTextCharacterProperties2.getClass();
        Runnable runnable = new Runnable() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda17
            @Override // java.lang.Runnable
            public final void run() {
                CTTextCharacterProperties.this.unsetKumimoji();
            }
        };
        final CTTextCharacterProperties cTTextCharacterProperties3 = this.props;
        cTTextCharacterProperties3.getClass();
        update(supplier, runnable, new Consumer() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda18
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                CTTextCharacterProperties.this.setKumimoji(((Boolean) obj).booleanValue());
            }
        }, kumimoji);
    }

    public void setBold(Boolean bold) {
        final CTTextCharacterProperties cTTextCharacterProperties = this.props;
        cTTextCharacterProperties.getClass();
        Supplier supplier = new Supplier() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda23
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(CTTextCharacterProperties.this.isSetB());
            }
        };
        final CTTextCharacterProperties cTTextCharacterProperties2 = this.props;
        cTTextCharacterProperties2.getClass();
        Runnable runnable = new Runnable() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda24
            @Override // java.lang.Runnable
            public final void run() {
                CTTextCharacterProperties.this.unsetB();
            }
        };
        final CTTextCharacterProperties cTTextCharacterProperties3 = this.props;
        cTTextCharacterProperties3.getClass();
        update(supplier, runnable, new Consumer() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda25
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                CTTextCharacterProperties.this.setB(((Boolean) obj).booleanValue());
            }
        }, bold);
    }

    public void setItalic(Boolean italic) {
        final CTTextCharacterProperties cTTextCharacterProperties = this.props;
        cTTextCharacterProperties.getClass();
        Supplier supplier = new Supplier() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda72
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(CTTextCharacterProperties.this.isSetI());
            }
        };
        final CTTextCharacterProperties cTTextCharacterProperties2 = this.props;
        cTTextCharacterProperties2.getClass();
        Runnable runnable = new Runnable() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda73
            @Override // java.lang.Runnable
            public final void run() {
                CTTextCharacterProperties.this.unsetI();
            }
        };
        final CTTextCharacterProperties cTTextCharacterProperties3 = this.props;
        cTTextCharacterProperties3.getClass();
        update(supplier, runnable, new Consumer() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda74
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                CTTextCharacterProperties.this.setI(((Boolean) obj).booleanValue());
            }
        }, italic);
    }

    public void setFontSize(Double size) {
        if (size != null && (size.doubleValue() < 1.0d || 400.0d < size.doubleValue())) {
            throw new IllegalArgumentException("Minimum inclusive = 1. Maximum inclusive = 400.");
        }
        final CTTextCharacterProperties cTTextCharacterProperties = this.props;
        cTTextCharacterProperties.getClass();
        Supplier supplier = new Supplier() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda52
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(CTTextCharacterProperties.this.isSetSz());
            }
        };
        final CTTextCharacterProperties cTTextCharacterProperties2 = this.props;
        cTTextCharacterProperties2.getClass();
        Runnable runnable = new Runnable() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda53
            @Override // java.lang.Runnable
            public final void run() {
                CTTextCharacterProperties.this.unsetSz();
            }
        };
        final CTTextCharacterProperties cTTextCharacterProperties3 = this.props;
        cTTextCharacterProperties3.getClass();
        update(supplier, runnable, new Consumer() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda54
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                CTTextCharacterProperties.this.setSz(((Integer) obj).intValue());
            }
        }, size == null ? null : Integer.valueOf((int) (size.doubleValue() * 100.0d)));
    }

    public void setFillProperties(XDDFFillProperties properties) {
        if (this.props.isSetBlipFill()) {
            this.props.unsetBlipFill();
        }
        if (this.props.isSetGradFill()) {
            this.props.unsetGradFill();
        }
        if (this.props.isSetGrpFill()) {
            this.props.unsetGrpFill();
        }
        if (this.props.isSetNoFill()) {
            this.props.unsetNoFill();
        }
        if (this.props.isSetPattFill()) {
            this.props.unsetPattFill();
        }
        if (this.props.isSetSolidFill()) {
            this.props.unsetSolidFill();
        }
        if (properties == null) {
            return;
        }
        if (properties instanceof XDDFGradientFillProperties) {
            this.props.setGradFill(((XDDFGradientFillProperties) properties).getXmlObject());
            return;
        }
        if (properties instanceof XDDFGroupFillProperties) {
            this.props.setGrpFill(((XDDFGroupFillProperties) properties).getXmlObject());
            return;
        }
        if (properties instanceof XDDFNoFillProperties) {
            this.props.setNoFill(((XDDFNoFillProperties) properties).getXmlObject());
            return;
        }
        if (properties instanceof XDDFPatternFillProperties) {
            this.props.setPattFill(((XDDFPatternFillProperties) properties).getXmlObject());
        } else if (properties instanceof XDDFPictureFillProperties) {
            this.props.setBlipFill(((XDDFPictureFillProperties) properties).getXmlObject());
        } else if (properties instanceof XDDFSolidFillProperties) {
            this.props.setSolidFill(((XDDFSolidFillProperties) properties).getXmlObject());
        }
    }

    public void setCharacterKerning(Double kerning) {
        if (kerning != null && (kerning.doubleValue() < 0.0d || 4000.0d < kerning.doubleValue())) {
            throw new IllegalArgumentException("Minimum inclusive = 0. Maximum inclusive = 4000.");
        }
        final CTTextCharacterProperties cTTextCharacterProperties = this.props;
        cTTextCharacterProperties.getClass();
        Supplier supplier = new Supplier() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda29
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(CTTextCharacterProperties.this.isSetKern());
            }
        };
        final CTTextCharacterProperties cTTextCharacterProperties2 = this.props;
        cTTextCharacterProperties2.getClass();
        Runnable runnable = new Runnable() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda30
            @Override // java.lang.Runnable
            public final void run() {
                CTTextCharacterProperties.this.unsetKern();
            }
        };
        final CTTextCharacterProperties cTTextCharacterProperties3 = this.props;
        cTTextCharacterProperties3.getClass();
        update(supplier, runnable, new Consumer() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda31
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                CTTextCharacterProperties.this.setKern(((Integer) obj).intValue());
            }
        }, kerning == null ? null : Integer.valueOf((int) (kerning.doubleValue() * 100.0d)));
    }

    public void setCharacterSpacing(Double spacing) {
        if (spacing != null && (spacing.doubleValue() < -4000.0d || 4000.0d < spacing.doubleValue())) {
            throw new IllegalArgumentException("Minimum inclusive = -4000. Maximum inclusive = 4000.");
        }
        final CTTextCharacterProperties cTTextCharacterProperties = this.props;
        cTTextCharacterProperties.getClass();
        Supplier supplier = new Supplier() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda39
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(CTTextCharacterProperties.this.isSetSpc());
            }
        };
        final CTTextCharacterProperties cTTextCharacterProperties2 = this.props;
        cTTextCharacterProperties2.getClass();
        Runnable runnable = new Runnable() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda40
            @Override // java.lang.Runnable
            public final void run() {
                CTTextCharacterProperties.this.unsetSpc();
            }
        };
        final CTTextCharacterProperties cTTextCharacterProperties3 = this.props;
        cTTextCharacterProperties3.getClass();
        update(supplier, runnable, new Consumer() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda41
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                CTTextCharacterProperties.this.setSpc((Integer) obj);
            }
        }, spacing == null ? null : Integer.valueOf((int) (spacing.doubleValue() * 100.0d)));
    }

    public void setFonts(XDDFFont[] fonts) {
        for (XDDFFont font : fonts) {
            CTTextFont xml = font.getXmlObject();
            switch (font.getGroup()) {
                case COMPLEX_SCRIPT:
                    final CTTextCharacterProperties cTTextCharacterProperties = this.props;
                    cTTextCharacterProperties.getClass();
                    Supplier supplier = new Supplier() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda59
                        @Override // java.util.function.Supplier
                        public final Object get() {
                            return Boolean.valueOf(CTTextCharacterProperties.this.isSetCs());
                        }
                    };
                    final CTTextCharacterProperties cTTextCharacterProperties2 = this.props;
                    cTTextCharacterProperties2.getClass();
                    Runnable runnable = new Runnable() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda62
                        @Override // java.lang.Runnable
                        public final void run() {
                            CTTextCharacterProperties.this.unsetCs();
                        }
                    };
                    final CTTextCharacterProperties cTTextCharacterProperties3 = this.props;
                    cTTextCharacterProperties3.getClass();
                    update(supplier, runnable, new Consumer() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda63
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            CTTextCharacterProperties.this.setCs((CTTextFont) obj);
                        }
                    }, xml);
                    break;
                case EAST_ASIAN:
                    final CTTextCharacterProperties cTTextCharacterProperties4 = this.props;
                    cTTextCharacterProperties4.getClass();
                    Supplier supplier2 = new Supplier() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda64
                        @Override // java.util.function.Supplier
                        public final Object get() {
                            return Boolean.valueOf(CTTextCharacterProperties.this.isSetEa());
                        }
                    };
                    final CTTextCharacterProperties cTTextCharacterProperties5 = this.props;
                    cTTextCharacterProperties5.getClass();
                    Runnable runnable2 = new Runnable() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda65
                        @Override // java.lang.Runnable
                        public final void run() {
                            CTTextCharacterProperties.this.unsetEa();
                        }
                    };
                    final CTTextCharacterProperties cTTextCharacterProperties6 = this.props;
                    cTTextCharacterProperties6.getClass();
                    update(supplier2, runnable2, new Consumer() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda67
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            CTTextCharacterProperties.this.setEa((CTTextFont) obj);
                        }
                    }, xml);
                    break;
                case LATIN:
                    final CTTextCharacterProperties cTTextCharacterProperties7 = this.props;
                    cTTextCharacterProperties7.getClass();
                    Supplier supplier3 = new Supplier() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda68
                        @Override // java.util.function.Supplier
                        public final Object get() {
                            return Boolean.valueOf(CTTextCharacterProperties.this.isSetLatin());
                        }
                    };
                    final CTTextCharacterProperties cTTextCharacterProperties8 = this.props;
                    cTTextCharacterProperties8.getClass();
                    Runnable runnable3 = new Runnable() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda69
                        @Override // java.lang.Runnable
                        public final void run() {
                            CTTextCharacterProperties.this.unsetLatin();
                        }
                    };
                    final CTTextCharacterProperties cTTextCharacterProperties9 = this.props;
                    cTTextCharacterProperties9.getClass();
                    update(supplier3, runnable3, new Consumer() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda70
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            CTTextCharacterProperties.this.setLatin((CTTextFont) obj);
                        }
                    }, xml);
                    break;
                case SYMBOL:
                    final CTTextCharacterProperties cTTextCharacterProperties10 = this.props;
                    cTTextCharacterProperties10.getClass();
                    Supplier supplier4 = new Supplier() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda71
                        @Override // java.util.function.Supplier
                        public final Object get() {
                            return Boolean.valueOf(CTTextCharacterProperties.this.isSetSym());
                        }
                    };
                    final CTTextCharacterProperties cTTextCharacterProperties11 = this.props;
                    cTTextCharacterProperties11.getClass();
                    Runnable runnable4 = new Runnable() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda60
                        @Override // java.lang.Runnable
                        public final void run() {
                            CTTextCharacterProperties.this.unsetSym();
                        }
                    };
                    final CTTextCharacterProperties cTTextCharacterProperties12 = this.props;
                    cTTextCharacterProperties12.getClass();
                    update(supplier4, runnable4, new Consumer() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda61
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            CTTextCharacterProperties.this.setSym((CTTextFont) obj);
                        }
                    }, xml);
                    break;
            }
        }
    }

    public void setUnderline(UnderlineType underline) {
        final CTTextCharacterProperties cTTextCharacterProperties = this.props;
        cTTextCharacterProperties.getClass();
        Supplier supplier = new Supplier() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(CTTextCharacterProperties.this.isSetU());
            }
        };
        final CTTextCharacterProperties cTTextCharacterProperties2 = this.props;
        cTTextCharacterProperties2.getClass();
        Runnable runnable = new Runnable() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda11
            @Override // java.lang.Runnable
            public final void run() {
                CTTextCharacterProperties.this.unsetU();
            }
        };
        final CTTextCharacterProperties cTTextCharacterProperties3 = this.props;
        cTTextCharacterProperties3.getClass();
        update(supplier, runnable, new Consumer() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda22
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                CTTextCharacterProperties.this.setU((STTextUnderlineType.Enum) obj);
            }
        }, underline == null ? null : underline.underlying);
    }

    public void setStrikeThrough(StrikeType strike) {
        final CTTextCharacterProperties cTTextCharacterProperties = this.props;
        cTTextCharacterProperties.getClass();
        Supplier supplier = new Supplier() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda42
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(CTTextCharacterProperties.this.isSetStrike());
            }
        };
        final CTTextCharacterProperties cTTextCharacterProperties2 = this.props;
        cTTextCharacterProperties2.getClass();
        Runnable runnable = new Runnable() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda43
            @Override // java.lang.Runnable
            public final void run() {
                CTTextCharacterProperties.this.unsetStrike();
            }
        };
        final CTTextCharacterProperties cTTextCharacterProperties3 = this.props;
        cTTextCharacterProperties3.getClass();
        update(supplier, runnable, new Consumer() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda45
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                CTTextCharacterProperties.this.setStrike((STTextStrikeType.Enum) obj);
            }
        }, strike == null ? null : strike.underlying);
    }

    public void setCapitals(CapsType caps) {
        final CTTextCharacterProperties cTTextCharacterProperties = this.props;
        cTTextCharacterProperties.getClass();
        Supplier supplier = new Supplier() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda6
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(CTTextCharacterProperties.this.isSetCap());
            }
        };
        final CTTextCharacterProperties cTTextCharacterProperties2 = this.props;
        cTTextCharacterProperties2.getClass();
        Runnable runnable = new Runnable() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                CTTextCharacterProperties.this.unsetCap();
            }
        };
        final CTTextCharacterProperties cTTextCharacterProperties3 = this.props;
        cTTextCharacterProperties3.getClass();
        update(supplier, runnable, new Consumer() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda8
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                CTTextCharacterProperties.this.setCap((STTextCapsType.Enum) obj);
            }
        }, caps == null ? null : caps.underlying);
    }

    public void setHyperlink(XDDFHyperlink link) {
        final CTTextCharacterProperties cTTextCharacterProperties = this.props;
        cTTextCharacterProperties.getClass();
        Supplier supplier = new Supplier() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda56
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(CTTextCharacterProperties.this.isSetHlinkClick());
            }
        };
        final CTTextCharacterProperties cTTextCharacterProperties2 = this.props;
        cTTextCharacterProperties2.getClass();
        Runnable runnable = new Runnable() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda57
            @Override // java.lang.Runnable
            public final void run() {
                CTTextCharacterProperties.this.unsetHlinkClick();
            }
        };
        final CTTextCharacterProperties cTTextCharacterProperties3 = this.props;
        cTTextCharacterProperties3.getClass();
        update(supplier, runnable, new Consumer() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda58
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                CTTextCharacterProperties.this.setHlinkClick((CTHyperlink) obj);
            }
        }, link == null ? null : link.getXmlObject());
    }

    public void setMouseOver(XDDFHyperlink link) {
        final CTTextCharacterProperties cTTextCharacterProperties = this.props;
        cTTextCharacterProperties.getClass();
        Supplier supplier = new Supplier() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda13
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(CTTextCharacterProperties.this.isSetHlinkMouseOver());
            }
        };
        final CTTextCharacterProperties cTTextCharacterProperties2 = this.props;
        cTTextCharacterProperties2.getClass();
        Runnable runnable = new Runnable() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda14
            @Override // java.lang.Runnable
            public final void run() {
                CTTextCharacterProperties.this.unsetHlinkMouseOver();
            }
        };
        final CTTextCharacterProperties cTTextCharacterProperties3 = this.props;
        cTTextCharacterProperties3.getClass();
        update(supplier, runnable, new Consumer() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda15
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                CTTextCharacterProperties.this.setHlinkMouseOver((CTHyperlink) obj);
            }
        }, link == null ? null : link.getXmlObject());
    }

    public void setLanguage(Locale lang) {
        final CTTextCharacterProperties cTTextCharacterProperties = this.props;
        cTTextCharacterProperties.getClass();
        Supplier supplier = new Supplier() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda9
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(CTTextCharacterProperties.this.isSetLang());
            }
        };
        final CTTextCharacterProperties cTTextCharacterProperties2 = this.props;
        cTTextCharacterProperties2.getClass();
        Runnable runnable = new Runnable() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda10
            @Override // java.lang.Runnable
            public final void run() {
                CTTextCharacterProperties.this.unsetLang();
            }
        };
        final CTTextCharacterProperties cTTextCharacterProperties3 = this.props;
        cTTextCharacterProperties3.getClass();
        update(supplier, runnable, new Consumer() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda12
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                CTTextCharacterProperties.this.setLang((String) obj);
            }
        }, lang == null ? null : lang.toLanguageTag());
    }

    public void setAlternativeLanguage(Locale lang) {
        final CTTextCharacterProperties cTTextCharacterProperties = this.props;
        cTTextCharacterProperties.getClass();
        Supplier supplier = new Supplier() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda33
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(CTTextCharacterProperties.this.isSetAltLang());
            }
        };
        final CTTextCharacterProperties cTTextCharacterProperties2 = this.props;
        cTTextCharacterProperties2.getClass();
        Runnable runnable = new Runnable() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda44
            @Override // java.lang.Runnable
            public final void run() {
                CTTextCharacterProperties.this.unsetAltLang();
            }
        };
        final CTTextCharacterProperties cTTextCharacterProperties3 = this.props;
        cTTextCharacterProperties3.getClass();
        update(supplier, runnable, new Consumer() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda55
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                CTTextCharacterProperties.this.setAltLang((String) obj);
            }
        }, lang == null ? null : lang.toLanguageTag());
    }

    public void setHighlight(XDDFColor color) {
        final CTTextCharacterProperties cTTextCharacterProperties = this.props;
        cTTextCharacterProperties.getClass();
        Supplier supplier = new Supplier() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda26
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(CTTextCharacterProperties.this.isSetHighlight());
            }
        };
        final CTTextCharacterProperties cTTextCharacterProperties2 = this.props;
        cTTextCharacterProperties2.getClass();
        Runnable runnable = new Runnable() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda27
            @Override // java.lang.Runnable
            public final void run() {
                CTTextCharacterProperties.this.unsetHighlight();
            }
        };
        final CTTextCharacterProperties cTTextCharacterProperties3 = this.props;
        cTTextCharacterProperties3.getClass();
        update(supplier, runnable, new Consumer() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda28
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                CTTextCharacterProperties.this.setHighlight((CTColor) obj);
            }
        }, color == null ? null : color.getColorContainer());
    }

    public void setLineProperties(XDDFLineProperties properties) {
        final CTTextCharacterProperties cTTextCharacterProperties = this.props;
        cTTextCharacterProperties.getClass();
        Supplier supplier = new Supplier() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda83
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(CTTextCharacterProperties.this.isSetLn());
            }
        };
        final CTTextCharacterProperties cTTextCharacterProperties2 = this.props;
        cTTextCharacterProperties2.getClass();
        Runnable runnable = new Runnable() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                CTTextCharacterProperties.this.unsetLn();
            }
        };
        final CTTextCharacterProperties cTTextCharacterProperties3 = this.props;
        cTTextCharacterProperties3.getClass();
        update(supplier, runnable, new Consumer() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda2
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                CTTextCharacterProperties.this.setLn((CTLineProperties) obj);
            }
        }, properties == null ? null : properties.getXmlObject());
    }

    public void setBookmark(String bookmark) {
        final CTTextCharacterProperties cTTextCharacterProperties = this.props;
        cTTextCharacterProperties.getClass();
        Supplier supplier = new Supplier() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda49
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(CTTextCharacterProperties.this.isSetBmk());
            }
        };
        final CTTextCharacterProperties cTTextCharacterProperties2 = this.props;
        cTTextCharacterProperties2.getClass();
        Runnable runnable = new Runnable() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda50
            @Override // java.lang.Runnable
            public final void run() {
                CTTextCharacterProperties.this.unsetBmk();
            }
        };
        final CTTextCharacterProperties cTTextCharacterProperties3 = this.props;
        cTTextCharacterProperties3.getClass();
        update(supplier, runnable, new Consumer() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda51
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                CTTextCharacterProperties.this.setBmk((String) obj);
            }
        }, bookmark);
    }

    public XDDFExtensionList getExtensionList() {
        if (this.props.isSetExtLst()) {
            return new XDDFExtensionList(this.props.getExtLst());
        }
        return null;
    }

    public void setExtensionList(XDDFExtensionList list) {
        final CTTextCharacterProperties cTTextCharacterProperties = this.props;
        cTTextCharacterProperties.getClass();
        Supplier supplier = new Supplier() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(CTTextCharacterProperties.this.isSetExtLst());
            }
        };
        final CTTextCharacterProperties cTTextCharacterProperties2 = this.props;
        cTTextCharacterProperties2.getClass();
        Runnable runnable = new Runnable() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                CTTextCharacterProperties.this.unsetExtLst();
            }
        };
        final CTTextCharacterProperties cTTextCharacterProperties3 = this.props;
        cTTextCharacterProperties3.getClass();
        update(supplier, runnable, new Consumer() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda5
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                CTTextCharacterProperties.this.setExtLst((CTOfficeArtExtensionList) obj);
            }
        }, list == null ? null : list.getXmlObject());
    }

    public XDDFEffectContainer getEffectContainer() {
        if (this.props.isSetEffectDag()) {
            return new XDDFEffectContainer(this.props.getEffectDag());
        }
        return null;
    }

    public void setEffectContainer(XDDFEffectContainer container) {
        final CTTextCharacterProperties cTTextCharacterProperties = this.props;
        cTTextCharacterProperties.getClass();
        Supplier supplier = new Supplier() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda46
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(CTTextCharacterProperties.this.isSetEffectDag());
            }
        };
        final CTTextCharacterProperties cTTextCharacterProperties2 = this.props;
        cTTextCharacterProperties2.getClass();
        Runnable runnable = new Runnable() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda47
            @Override // java.lang.Runnable
            public final void run() {
                CTTextCharacterProperties.this.unsetEffectDag();
            }
        };
        final CTTextCharacterProperties cTTextCharacterProperties3 = this.props;
        cTTextCharacterProperties3.getClass();
        update(supplier, runnable, new Consumer() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda48
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                CTTextCharacterProperties.this.setEffectDag((CTEffectContainer) obj);
            }
        }, container == null ? null : container.getXmlObject());
    }

    public XDDFEffectList getEffectList() {
        if (this.props.isSetEffectLst()) {
            return new XDDFEffectList(this.props.getEffectLst());
        }
        return null;
    }

    public void setEffectList(XDDFEffectList list) {
        final CTTextCharacterProperties cTTextCharacterProperties = this.props;
        cTTextCharacterProperties.getClass();
        Supplier supplier = new Supplier() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda36
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(CTTextCharacterProperties.this.isSetEffectLst());
            }
        };
        final CTTextCharacterProperties cTTextCharacterProperties2 = this.props;
        cTTextCharacterProperties2.getClass();
        Runnable runnable = new Runnable() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda37
            @Override // java.lang.Runnable
            public final void run() {
                CTTextCharacterProperties.this.unsetEffectLst();
            }
        };
        final CTTextCharacterProperties cTTextCharacterProperties3 = this.props;
        cTTextCharacterProperties3.getClass();
        update(supplier, runnable, new Consumer() { // from class: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$$ExternalSyntheticLambda38
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                CTTextCharacterProperties.this.setEffectLst((CTEffectList) obj);
            }
        }, list == null ? null : list.getXmlObject());
    }

    private static <T> void update(Supplier<Boolean> isSet, Runnable unset, Consumer<T> setter, T val) {
        if (val != null) {
            setter.accept(val);
        } else if (isSet.get().booleanValue()) {
            unset.run();
        }
    }
}
