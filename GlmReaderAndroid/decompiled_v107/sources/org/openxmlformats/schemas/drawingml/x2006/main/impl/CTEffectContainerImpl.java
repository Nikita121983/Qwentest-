package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaBiLevelEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaCeilingEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaFloorEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaInverseEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaModulateEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaModulateFixedEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaOutsetEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaReplaceEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBiLevelEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlendEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlurEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColorChangeEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColorReplaceEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEffectReference;
import org.openxmlformats.schemas.drawingml.x2006.main.CTFillEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTFillOverlayEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGlowEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGrayscaleEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTHSLEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTInnerShadowEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLuminanceEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetShadowEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTReflectionEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTRelativeOffsetEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSoftEdgesEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTintEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTransformEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.STEffectContainerType;
import org.openxmlformats.schemas.drawingml.x2006.main.STEffectContainerType$Enum;

/* loaded from: classes11.dex */
public class CTEffectContainerImpl extends XmlComplexContentImpl implements CTEffectContainer {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "cont"), new QName(XSSFRelation.NS_DRAWINGML, "effect"), new QName(XSSFRelation.NS_DRAWINGML, "alphaBiLevel"), new QName(XSSFRelation.NS_DRAWINGML, "alphaCeiling"), new QName(XSSFRelation.NS_DRAWINGML, "alphaFloor"), new QName(XSSFRelation.NS_DRAWINGML, "alphaInv"), new QName(XSSFRelation.NS_DRAWINGML, "alphaMod"), new QName(XSSFRelation.NS_DRAWINGML, "alphaModFix"), new QName(XSSFRelation.NS_DRAWINGML, "alphaOutset"), new QName(XSSFRelation.NS_DRAWINGML, "alphaRepl"), new QName(XSSFRelation.NS_DRAWINGML, "biLevel"), new QName(XSSFRelation.NS_DRAWINGML, "blend"), new QName(XSSFRelation.NS_DRAWINGML, "blur"), new QName(XSSFRelation.NS_DRAWINGML, "clrChange"), new QName(XSSFRelation.NS_DRAWINGML, "clrRepl"), new QName(XSSFRelation.NS_DRAWINGML, "duotone"), new QName(XSSFRelation.NS_DRAWINGML, "fill"), new QName(XSSFRelation.NS_DRAWINGML, "fillOverlay"), new QName(XSSFRelation.NS_DRAWINGML, "glow"), new QName(XSSFRelation.NS_DRAWINGML, "grayscl"), new QName(XSSFRelation.NS_DRAWINGML, "hsl"), new QName(XSSFRelation.NS_DRAWINGML, "innerShdw"), new QName(XSSFRelation.NS_DRAWINGML, "lum"), new QName(XSSFRelation.NS_DRAWINGML, "outerShdw"), new QName(XSSFRelation.NS_DRAWINGML, "prstShdw"), new QName(XSSFRelation.NS_DRAWINGML, "reflection"), new QName(XSSFRelation.NS_DRAWINGML, "relOff"), new QName(XSSFRelation.NS_DRAWINGML, "softEdge"), new QName(XSSFRelation.NS_DRAWINGML, "tint"), new QName(XSSFRelation.NS_DRAWINGML, "xfrm"), new QName("", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY), new QName("", "name")};
    private static final long serialVersionUID = 1;

    public CTEffectContainerImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public List<CTEffectContainer> getContList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda100
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectContainerImpl.this.getContArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda101
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTEffectContainerImpl.this.setContArray(((Integer) obj).intValue(), (CTEffectContainer) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda102
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectContainerImpl.this.insertNewCont(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda103
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTEffectContainerImpl.this.removeCont(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda104
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTEffectContainerImpl.this.sizeOfContArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTEffectContainer[] getContArray() {
        return (CTEffectContainer[]) getXmlObjectArray(PROPERTY_QNAME[0], new CTEffectContainer[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTEffectContainer getContArray(int i) {
        CTEffectContainer target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEffectContainer) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public int sizeOfContArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setContArray(CTEffectContainer[] contArray) {
        check_orphaned();
        arraySetterHelper(contArray, PROPERTY_QNAME[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setContArray(int i, CTEffectContainer cont) {
        generatedSetterHelperImpl(cont, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTEffectContainer insertNewCont(int i) {
        CTEffectContainer target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEffectContainer) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTEffectContainer addNewCont() {
        CTEffectContainer target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEffectContainer) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void removeCont(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public List<CTEffectReference> getEffectList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda45
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectContainerImpl.this.getEffectArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda46
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTEffectContainerImpl.this.setEffectArray(((Integer) obj).intValue(), (CTEffectReference) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda47
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectContainerImpl.this.insertNewEffect(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda48
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTEffectContainerImpl.this.removeEffect(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda49
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTEffectContainerImpl.this.sizeOfEffectArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTEffectReference[] getEffectArray() {
        return getXmlObjectArray(PROPERTY_QNAME[1], (XmlObject[]) new CTEffectReference[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTEffectReference getEffectArray(int i) {
        CTEffectReference target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public int sizeOfEffectArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setEffectArray(CTEffectReference[] effectArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) effectArray, PROPERTY_QNAME[1]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setEffectArray(int i, CTEffectReference effect) {
        generatedSetterHelperImpl(effect, PROPERTY_QNAME[1], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTEffectReference insertNewEffect(int i) {
        CTEffectReference target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTEffectReference addNewEffect() {
        CTEffectReference target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void removeEffect(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public List<CTAlphaBiLevelEffect> getAlphaBiLevelList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda73
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectContainerImpl.this.getAlphaBiLevelArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda74
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTEffectContainerImpl.this.setAlphaBiLevelArray(((Integer) obj).intValue(), (CTAlphaBiLevelEffect) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda75
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectContainerImpl.this.insertNewAlphaBiLevel(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda76
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTEffectContainerImpl.this.removeAlphaBiLevel(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda77
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTEffectContainerImpl.this.sizeOfAlphaBiLevelArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTAlphaBiLevelEffect[] getAlphaBiLevelArray() {
        return getXmlObjectArray(PROPERTY_QNAME[2], (XmlObject[]) new CTAlphaBiLevelEffect[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTAlphaBiLevelEffect getAlphaBiLevelArray(int i) {
        CTAlphaBiLevelEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public int sizeOfAlphaBiLevelArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setAlphaBiLevelArray(CTAlphaBiLevelEffect[] alphaBiLevelArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) alphaBiLevelArray, PROPERTY_QNAME[2]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setAlphaBiLevelArray(int i, CTAlphaBiLevelEffect alphaBiLevel) {
        generatedSetterHelperImpl(alphaBiLevel, PROPERTY_QNAME[2], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTAlphaBiLevelEffect insertNewAlphaBiLevel(int i) {
        CTAlphaBiLevelEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTAlphaBiLevelEffect addNewAlphaBiLevel() {
        CTAlphaBiLevelEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void removeAlphaBiLevel(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public List<CTAlphaCeilingEffect> getAlphaCeilingList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda34
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectContainerImpl.this.getAlphaCeilingArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda35
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTEffectContainerImpl.this.setAlphaCeilingArray(((Integer) obj).intValue(), (CTAlphaCeilingEffect) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda36
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectContainerImpl.this.insertNewAlphaCeiling(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda37
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTEffectContainerImpl.this.removeAlphaCeiling(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda38
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTEffectContainerImpl.this.sizeOfAlphaCeilingArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTAlphaCeilingEffect[] getAlphaCeilingArray() {
        return getXmlObjectArray(PROPERTY_QNAME[3], (XmlObject[]) new CTAlphaCeilingEffect[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTAlphaCeilingEffect getAlphaCeilingArray(int i) {
        CTAlphaCeilingEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[3], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public int sizeOfAlphaCeilingArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setAlphaCeilingArray(CTAlphaCeilingEffect[] alphaCeilingArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) alphaCeilingArray, PROPERTY_QNAME[3]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setAlphaCeilingArray(int i, CTAlphaCeilingEffect alphaCeiling) {
        generatedSetterHelperImpl(alphaCeiling, PROPERTY_QNAME[3], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTAlphaCeilingEffect insertNewAlphaCeiling(int i) {
        CTAlphaCeilingEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[3], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTAlphaCeilingEffect addNewAlphaCeiling() {
        CTAlphaCeilingEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void removeAlphaCeiling(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public List<CTAlphaFloorEffect> getAlphaFloorList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda67
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectContainerImpl.this.getAlphaFloorArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda68
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTEffectContainerImpl.this.setAlphaFloorArray(((Integer) obj).intValue(), (CTAlphaFloorEffect) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda69
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectContainerImpl.this.insertNewAlphaFloor(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda70
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTEffectContainerImpl.this.removeAlphaFloor(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda71
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTEffectContainerImpl.this.sizeOfAlphaFloorArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTAlphaFloorEffect[] getAlphaFloorArray() {
        return getXmlObjectArray(PROPERTY_QNAME[4], (XmlObject[]) new CTAlphaFloorEffect[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTAlphaFloorEffect getAlphaFloorArray(int i) {
        CTAlphaFloorEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[4], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public int sizeOfAlphaFloorArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[4]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setAlphaFloorArray(CTAlphaFloorEffect[] alphaFloorArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) alphaFloorArray, PROPERTY_QNAME[4]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setAlphaFloorArray(int i, CTAlphaFloorEffect alphaFloor) {
        generatedSetterHelperImpl(alphaFloor, PROPERTY_QNAME[4], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTAlphaFloorEffect insertNewAlphaFloor(int i) {
        CTAlphaFloorEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[4], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTAlphaFloorEffect addNewAlphaFloor() {
        CTAlphaFloorEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void removeAlphaFloor(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public List<CTAlphaInverseEffect> getAlphaInvList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda17
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectContainerImpl.this.getAlphaInvArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda18
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTEffectContainerImpl.this.setAlphaInvArray(((Integer) obj).intValue(), (CTAlphaInverseEffect) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda19
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectContainerImpl.this.insertNewAlphaInv(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda20
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTEffectContainerImpl.this.removeAlphaInv(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda21
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTEffectContainerImpl.this.sizeOfAlphaInvArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTAlphaInverseEffect[] getAlphaInvArray() {
        return getXmlObjectArray(PROPERTY_QNAME[5], (XmlObject[]) new CTAlphaInverseEffect[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTAlphaInverseEffect getAlphaInvArray(int i) {
        CTAlphaInverseEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[5], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public int sizeOfAlphaInvArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[5]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setAlphaInvArray(CTAlphaInverseEffect[] alphaInvArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) alphaInvArray, PROPERTY_QNAME[5]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setAlphaInvArray(int i, CTAlphaInverseEffect alphaInv) {
        generatedSetterHelperImpl(alphaInv, PROPERTY_QNAME[5], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTAlphaInverseEffect insertNewAlphaInv(int i) {
        CTAlphaInverseEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[5], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTAlphaInverseEffect addNewAlphaInv() {
        CTAlphaInverseEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void removeAlphaInv(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public List<CTAlphaModulateEffect> getAlphaModList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda128
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectContainerImpl.this.getAlphaModArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda129
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTEffectContainerImpl.this.setAlphaModArray(((Integer) obj).intValue(), (CTAlphaModulateEffect) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda130
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectContainerImpl.this.insertNewAlphaMod(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda131
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTEffectContainerImpl.this.removeAlphaMod(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda132
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTEffectContainerImpl.this.sizeOfAlphaModArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTAlphaModulateEffect[] getAlphaModArray() {
        return getXmlObjectArray(PROPERTY_QNAME[6], (XmlObject[]) new CTAlphaModulateEffect[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTAlphaModulateEffect getAlphaModArray(int i) {
        CTAlphaModulateEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[6], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public int sizeOfAlphaModArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[6]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setAlphaModArray(CTAlphaModulateEffect[] alphaModArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) alphaModArray, PROPERTY_QNAME[6]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setAlphaModArray(int i, CTAlphaModulateEffect alphaMod) {
        generatedSetterHelperImpl(alphaMod, PROPERTY_QNAME[6], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTAlphaModulateEffect insertNewAlphaMod(int i) {
        CTAlphaModulateEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[6], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTAlphaModulateEffect addNewAlphaMod() {
        CTAlphaModulateEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void removeAlphaMod(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public List<CTAlphaModulateFixedEffect> getAlphaModFixList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda105
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectContainerImpl.this.getAlphaModFixArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda116
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTEffectContainerImpl.this.setAlphaModFixArray(((Integer) obj).intValue(), (CTAlphaModulateFixedEffect) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda127
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectContainerImpl.this.insertNewAlphaModFix(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda138
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTEffectContainerImpl.this.removeAlphaModFix(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda149
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTEffectContainerImpl.this.sizeOfAlphaModFixArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTAlphaModulateFixedEffect[] getAlphaModFixArray() {
        return (CTAlphaModulateFixedEffect[]) getXmlObjectArray(PROPERTY_QNAME[7], new CTAlphaModulateFixedEffect[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTAlphaModulateFixedEffect getAlphaModFixArray(int i) {
        CTAlphaModulateFixedEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTAlphaModulateFixedEffect) get_store().find_element_user(PROPERTY_QNAME[7], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public int sizeOfAlphaModFixArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[7]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setAlphaModFixArray(CTAlphaModulateFixedEffect[] alphaModFixArray) {
        check_orphaned();
        arraySetterHelper(alphaModFixArray, PROPERTY_QNAME[7]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setAlphaModFixArray(int i, CTAlphaModulateFixedEffect alphaModFix) {
        generatedSetterHelperImpl(alphaModFix, PROPERTY_QNAME[7], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTAlphaModulateFixedEffect insertNewAlphaModFix(int i) {
        CTAlphaModulateFixedEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTAlphaModulateFixedEffect) get_store().insert_element_user(PROPERTY_QNAME[7], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTAlphaModulateFixedEffect addNewAlphaModFix() {
        CTAlphaModulateFixedEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTAlphaModulateFixedEffect) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void removeAlphaModFix(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public List<CTAlphaOutsetEffect> getAlphaOutsetList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda6
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectContainerImpl.this.getAlphaOutsetArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda7
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTEffectContainerImpl.this.setAlphaOutsetArray(((Integer) obj).intValue(), (CTAlphaOutsetEffect) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda8
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectContainerImpl.this.insertNewAlphaOutset(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda9
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTEffectContainerImpl.this.removeAlphaOutset(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda10
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTEffectContainerImpl.this.sizeOfAlphaOutsetArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTAlphaOutsetEffect[] getAlphaOutsetArray() {
        return getXmlObjectArray(PROPERTY_QNAME[8], (XmlObject[]) new CTAlphaOutsetEffect[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTAlphaOutsetEffect getAlphaOutsetArray(int i) {
        CTAlphaOutsetEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[8], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public int sizeOfAlphaOutsetArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[8]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setAlphaOutsetArray(CTAlphaOutsetEffect[] alphaOutsetArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) alphaOutsetArray, PROPERTY_QNAME[8]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setAlphaOutsetArray(int i, CTAlphaOutsetEffect alphaOutset) {
        generatedSetterHelperImpl(alphaOutset, PROPERTY_QNAME[8], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTAlphaOutsetEffect insertNewAlphaOutset(int i) {
        CTAlphaOutsetEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[8], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTAlphaOutsetEffect addNewAlphaOutset() {
        CTAlphaOutsetEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void removeAlphaOutset(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public List<CTAlphaReplaceEffect> getAlphaReplList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda78
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectContainerImpl.this.getAlphaReplArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda79
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTEffectContainerImpl.this.setAlphaReplArray(((Integer) obj).intValue(), (CTAlphaReplaceEffect) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda80
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectContainerImpl.this.insertNewAlphaRepl(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda81
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTEffectContainerImpl.this.removeAlphaRepl(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda82
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTEffectContainerImpl.this.sizeOfAlphaReplArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTAlphaReplaceEffect[] getAlphaReplArray() {
        return getXmlObjectArray(PROPERTY_QNAME[9], (XmlObject[]) new CTAlphaReplaceEffect[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTAlphaReplaceEffect getAlphaReplArray(int i) {
        CTAlphaReplaceEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[9], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public int sizeOfAlphaReplArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[9]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setAlphaReplArray(CTAlphaReplaceEffect[] alphaReplArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) alphaReplArray, PROPERTY_QNAME[9]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setAlphaReplArray(int i, CTAlphaReplaceEffect alphaRepl) {
        generatedSetterHelperImpl(alphaRepl, PROPERTY_QNAME[9], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTAlphaReplaceEffect insertNewAlphaRepl(int i) {
        CTAlphaReplaceEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[9], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTAlphaReplaceEffect addNewAlphaRepl() {
        CTAlphaReplaceEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void removeAlphaRepl(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public List<CTBiLevelEffect> getBiLevelList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda39
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectContainerImpl.this.getBiLevelArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda40
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTEffectContainerImpl.this.setBiLevelArray(((Integer) obj).intValue(), (CTBiLevelEffect) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda41
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectContainerImpl.this.insertNewBiLevel(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda42
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTEffectContainerImpl.this.removeBiLevel(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda43
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTEffectContainerImpl.this.sizeOfBiLevelArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTBiLevelEffect[] getBiLevelArray() {
        return getXmlObjectArray(PROPERTY_QNAME[10], (XmlObject[]) new CTBiLevelEffect[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTBiLevelEffect getBiLevelArray(int i) {
        CTBiLevelEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[10], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public int sizeOfBiLevelArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[10]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setBiLevelArray(CTBiLevelEffect[] biLevelArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) biLevelArray, PROPERTY_QNAME[10]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setBiLevelArray(int i, CTBiLevelEffect biLevel) {
        generatedSetterHelperImpl(biLevel, PROPERTY_QNAME[10], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTBiLevelEffect insertNewBiLevel(int i) {
        CTBiLevelEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[10], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTBiLevelEffect addNewBiLevel() {
        CTBiLevelEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void removeBiLevel(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public List<CTBlendEffect> getBlendList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda50
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectContainerImpl.this.getBlendArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda51
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTEffectContainerImpl.this.setBlendArray(((Integer) obj).intValue(), (CTBlendEffect) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda52
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectContainerImpl.this.insertNewBlend(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda53
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTEffectContainerImpl.this.removeBlend(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda54
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTEffectContainerImpl.this.sizeOfBlendArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTBlendEffect[] getBlendArray() {
        return getXmlObjectArray(PROPERTY_QNAME[11], (XmlObject[]) new CTBlendEffect[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTBlendEffect getBlendArray(int i) {
        CTBlendEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[11], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public int sizeOfBlendArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[11]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setBlendArray(CTBlendEffect[] blendArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) blendArray, PROPERTY_QNAME[11]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setBlendArray(int i, CTBlendEffect blend) {
        generatedSetterHelperImpl(blend, PROPERTY_QNAME[11], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTBlendEffect insertNewBlend(int i) {
        CTBlendEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[11], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTBlendEffect addNewBlend() {
        CTBlendEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void removeBlend(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public List<CTBlurEffect> getBlurList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda84
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectContainerImpl.this.getBlurArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda85
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTEffectContainerImpl.this.setBlurArray(((Integer) obj).intValue(), (CTBlurEffect) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda86
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectContainerImpl.this.insertNewBlur(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda87
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTEffectContainerImpl.this.removeBlur(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda88
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTEffectContainerImpl.this.sizeOfBlurArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTBlurEffect[] getBlurArray() {
        return getXmlObjectArray(PROPERTY_QNAME[12], (XmlObject[]) new CTBlurEffect[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTBlurEffect getBlurArray(int i) {
        CTBlurEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[12], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public int sizeOfBlurArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[12]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setBlurArray(CTBlurEffect[] blurArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) blurArray, PROPERTY_QNAME[12]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setBlurArray(int i, CTBlurEffect blur) {
        generatedSetterHelperImpl(blur, PROPERTY_QNAME[12], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTBlurEffect insertNewBlur(int i) {
        CTBlurEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[12], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTBlurEffect addNewBlur() {
        CTBlurEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void removeBlur(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public List<CTColorChangeEffect> getClrChangeList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda28
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectContainerImpl.this.getClrChangeArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda29
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTEffectContainerImpl.this.setClrChangeArray(((Integer) obj).intValue(), (CTColorChangeEffect) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda30
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectContainerImpl.this.insertNewClrChange(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda31
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTEffectContainerImpl.this.removeClrChange(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda32
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTEffectContainerImpl.this.sizeOfClrChangeArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTColorChangeEffect[] getClrChangeArray() {
        return getXmlObjectArray(PROPERTY_QNAME[13], (XmlObject[]) new CTColorChangeEffect[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTColorChangeEffect getClrChangeArray(int i) {
        CTColorChangeEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[13], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public int sizeOfClrChangeArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[13]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setClrChangeArray(CTColorChangeEffect[] clrChangeArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) clrChangeArray, PROPERTY_QNAME[13]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setClrChangeArray(int i, CTColorChangeEffect clrChange) {
        generatedSetterHelperImpl(clrChange, PROPERTY_QNAME[13], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTColorChangeEffect insertNewClrChange(int i) {
        CTColorChangeEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[13], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTColorChangeEffect addNewClrChange() {
        CTColorChangeEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void removeClrChange(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public List<CTColorReplaceEffect> getClrReplList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda95
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectContainerImpl.this.getClrReplArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda96
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTEffectContainerImpl.this.setClrReplArray(((Integer) obj).intValue(), (CTColorReplaceEffect) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda97
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectContainerImpl.this.insertNewClrRepl(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda98
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTEffectContainerImpl.this.removeClrRepl(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda99
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTEffectContainerImpl.this.sizeOfClrReplArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTColorReplaceEffect[] getClrReplArray() {
        return getXmlObjectArray(PROPERTY_QNAME[14], (XmlObject[]) new CTColorReplaceEffect[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTColorReplaceEffect getClrReplArray(int i) {
        CTColorReplaceEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[14], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public int sizeOfClrReplArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[14]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setClrReplArray(CTColorReplaceEffect[] clrReplArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) clrReplArray, PROPERTY_QNAME[14]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setClrReplArray(int i, CTColorReplaceEffect clrRepl) {
        generatedSetterHelperImpl(clrRepl, PROPERTY_QNAME[14], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTColorReplaceEffect insertNewClrRepl(int i) {
        CTColorReplaceEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[14], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTColorReplaceEffect addNewClrRepl() {
        CTColorReplaceEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[14]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void removeClrRepl(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[14], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public List<CTDuotoneEffect> getDuotoneList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda111
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectContainerImpl.this.getDuotoneArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda112
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTEffectContainerImpl.this.setDuotoneArray(((Integer) obj).intValue(), (CTDuotoneEffect) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda113
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectContainerImpl.this.insertNewDuotone(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda114
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTEffectContainerImpl.this.removeDuotone(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda115
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTEffectContainerImpl.this.sizeOfDuotoneArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTDuotoneEffect[] getDuotoneArray() {
        return (CTDuotoneEffect[]) getXmlObjectArray(PROPERTY_QNAME[15], new CTDuotoneEffect[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTDuotoneEffect getDuotoneArray(int i) {
        CTDuotoneEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDuotoneEffect) get_store().find_element_user(PROPERTY_QNAME[15], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public int sizeOfDuotoneArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[15]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setDuotoneArray(CTDuotoneEffect[] duotoneArray) {
        check_orphaned();
        arraySetterHelper(duotoneArray, PROPERTY_QNAME[15]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setDuotoneArray(int i, CTDuotoneEffect duotone) {
        generatedSetterHelperImpl(duotone, PROPERTY_QNAME[15], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTDuotoneEffect insertNewDuotone(int i) {
        CTDuotoneEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDuotoneEffect) get_store().insert_element_user(PROPERTY_QNAME[15], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTDuotoneEffect addNewDuotone() {
        CTDuotoneEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDuotoneEffect) get_store().add_element_user(PROPERTY_QNAME[15]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void removeDuotone(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[15], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public List<CTFillEffect> getFillList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda144
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectContainerImpl.this.getFillArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda145
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTEffectContainerImpl.this.setFillArray(((Integer) obj).intValue(), (CTFillEffect) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda146
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectContainerImpl.this.insertNewFill(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda147
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTEffectContainerImpl.this.removeFill(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda148
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTEffectContainerImpl.this.sizeOfFillArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTFillEffect[] getFillArray() {
        return getXmlObjectArray(PROPERTY_QNAME[16], (XmlObject[]) new CTFillEffect[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTFillEffect getFillArray(int i) {
        CTFillEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[16], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public int sizeOfFillArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[16]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setFillArray(CTFillEffect[] fillArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) fillArray, PROPERTY_QNAME[16]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setFillArray(int i, CTFillEffect fill) {
        generatedSetterHelperImpl(fill, PROPERTY_QNAME[16], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTFillEffect insertNewFill(int i) {
        CTFillEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[16], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTFillEffect addNewFill() {
        CTFillEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[16]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void removeFill(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[16], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public List<CTFillOverlayEffect> getFillOverlayList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda12
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectContainerImpl.this.getFillOverlayArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda13
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTEffectContainerImpl.this.setFillOverlayArray(((Integer) obj).intValue(), (CTFillOverlayEffect) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda14
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectContainerImpl.this.insertNewFillOverlay(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda15
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTEffectContainerImpl.this.removeFillOverlay(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda16
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTEffectContainerImpl.this.sizeOfFillOverlayArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTFillOverlayEffect[] getFillOverlayArray() {
        return getXmlObjectArray(PROPERTY_QNAME[17], (XmlObject[]) new CTFillOverlayEffect[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTFillOverlayEffect getFillOverlayArray(int i) {
        CTFillOverlayEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[17], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public int sizeOfFillOverlayArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[17]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setFillOverlayArray(CTFillOverlayEffect[] fillOverlayArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) fillOverlayArray, PROPERTY_QNAME[17]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setFillOverlayArray(int i, CTFillOverlayEffect fillOverlay) {
        generatedSetterHelperImpl(fillOverlay, PROPERTY_QNAME[17], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTFillOverlayEffect insertNewFillOverlay(int i) {
        CTFillOverlayEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[17], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTFillOverlayEffect addNewFillOverlay() {
        CTFillOverlayEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[17]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void removeFillOverlay(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[17], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public List<CTGlowEffect> getGlowList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda133
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectContainerImpl.this.getGlowArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda134
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTEffectContainerImpl.this.setGlowArray(((Integer) obj).intValue(), (CTGlowEffect) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda135
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectContainerImpl.this.insertNewGlow(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda136
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTEffectContainerImpl.this.removeGlow(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda137
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTEffectContainerImpl.this.sizeOfGlowArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTGlowEffect[] getGlowArray() {
        return getXmlObjectArray(PROPERTY_QNAME[18], (XmlObject[]) new CTGlowEffect[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTGlowEffect getGlowArray(int i) {
        CTGlowEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[18], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public int sizeOfGlowArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[18]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setGlowArray(CTGlowEffect[] glowArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) glowArray, PROPERTY_QNAME[18]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setGlowArray(int i, CTGlowEffect glow) {
        generatedSetterHelperImpl(glow, PROPERTY_QNAME[18], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTGlowEffect insertNewGlow(int i) {
        CTGlowEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[18], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTGlowEffect addNewGlow() {
        CTGlowEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[18]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void removeGlow(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[18], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public List<CTGrayscaleEffect> getGraysclList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda11
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectContainerImpl.this.getGraysclArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda22
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTEffectContainerImpl.this.setGraysclArray(((Integer) obj).intValue(), (CTGrayscaleEffect) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda33
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectContainerImpl.this.insertNewGrayscl(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda44
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTEffectContainerImpl.this.removeGrayscl(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda55
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTEffectContainerImpl.this.sizeOfGraysclArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTGrayscaleEffect[] getGraysclArray() {
        return getXmlObjectArray(PROPERTY_QNAME[19], (XmlObject[]) new CTGrayscaleEffect[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTGrayscaleEffect getGraysclArray(int i) {
        CTGrayscaleEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[19], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public int sizeOfGraysclArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[19]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setGraysclArray(CTGrayscaleEffect[] graysclArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) graysclArray, PROPERTY_QNAME[19]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setGraysclArray(int i, CTGrayscaleEffect grayscl) {
        generatedSetterHelperImpl(grayscl, PROPERTY_QNAME[19], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTGrayscaleEffect insertNewGrayscl(int i) {
        CTGrayscaleEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[19], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTGrayscaleEffect addNewGrayscl() {
        CTGrayscaleEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[19]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void removeGrayscl(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[19], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public List<CTHSLEffect> getHslList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda117
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectContainerImpl.this.getHslArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda118
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTEffectContainerImpl.this.setHslArray(((Integer) obj).intValue(), (CTHSLEffect) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda119
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectContainerImpl.this.insertNewHsl(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda120
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTEffectContainerImpl.this.removeHsl(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda121
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTEffectContainerImpl.this.sizeOfHslArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTHSLEffect[] getHslArray() {
        return getXmlObjectArray(PROPERTY_QNAME[20], (XmlObject[]) new CTHSLEffect[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTHSLEffect getHslArray(int i) {
        CTHSLEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[20], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public int sizeOfHslArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[20]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setHslArray(CTHSLEffect[] hslArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) hslArray, PROPERTY_QNAME[20]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setHslArray(int i, CTHSLEffect hsl) {
        generatedSetterHelperImpl(hsl, PROPERTY_QNAME[20], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTHSLEffect insertNewHsl(int i) {
        CTHSLEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[20], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTHSLEffect addNewHsl() {
        CTHSLEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[20]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void removeHsl(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[20], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public List<CTInnerShadowEffect> getInnerShdwList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectContainerImpl.this.getInnerShdwArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda61
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTEffectContainerImpl.this.setInnerShdwArray(((Integer) obj).intValue(), (CTInnerShadowEffect) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda72
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectContainerImpl.this.insertNewInnerShdw(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda83
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTEffectContainerImpl.this.removeInnerShdw(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda94
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTEffectContainerImpl.this.sizeOfInnerShdwArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTInnerShadowEffect[] getInnerShdwArray() {
        return getXmlObjectArray(PROPERTY_QNAME[21], (XmlObject[]) new CTInnerShadowEffect[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTInnerShadowEffect getInnerShdwArray(int i) {
        CTInnerShadowEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[21], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public int sizeOfInnerShdwArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[21]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setInnerShdwArray(CTInnerShadowEffect[] innerShdwArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) innerShdwArray, PROPERTY_QNAME[21]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setInnerShdwArray(int i, CTInnerShadowEffect innerShdw) {
        generatedSetterHelperImpl(innerShdw, PROPERTY_QNAME[21], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTInnerShadowEffect insertNewInnerShdw(int i) {
        CTInnerShadowEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[21], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTInnerShadowEffect addNewInnerShdw() {
        CTInnerShadowEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[21]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void removeInnerShdw(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[21], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public List<CTLuminanceEffect> getLumList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda122
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectContainerImpl.this.getLumArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda123
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTEffectContainerImpl.this.setLumArray(((Integer) obj).intValue(), (CTLuminanceEffect) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda124
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectContainerImpl.this.insertNewLum(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda125
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTEffectContainerImpl.this.removeLum(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda126
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTEffectContainerImpl.this.sizeOfLumArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTLuminanceEffect[] getLumArray() {
        return getXmlObjectArray(PROPERTY_QNAME[22], (XmlObject[]) new CTLuminanceEffect[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTLuminanceEffect getLumArray(int i) {
        CTLuminanceEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[22], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public int sizeOfLumArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[22]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setLumArray(CTLuminanceEffect[] lumArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) lumArray, PROPERTY_QNAME[22]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setLumArray(int i, CTLuminanceEffect lum) {
        generatedSetterHelperImpl(lum, PROPERTY_QNAME[22], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTLuminanceEffect insertNewLum(int i) {
        CTLuminanceEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[22], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTLuminanceEffect addNewLum() {
        CTLuminanceEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[22]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void removeLum(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[22], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public List<CTOuterShadowEffect> getOuterShdwList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda139
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectContainerImpl.this.getOuterShdwArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda140
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTEffectContainerImpl.this.setOuterShdwArray(((Integer) obj).intValue(), (CTOuterShadowEffect) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda141
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectContainerImpl.this.insertNewOuterShdw(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda142
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTEffectContainerImpl.this.removeOuterShdw(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda143
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTEffectContainerImpl.this.sizeOfOuterShdwArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTOuterShadowEffect[] getOuterShdwArray() {
        return (CTOuterShadowEffect[]) getXmlObjectArray(PROPERTY_QNAME[23], new CTOuterShadowEffect[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTOuterShadowEffect getOuterShdwArray(int i) {
        CTOuterShadowEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOuterShadowEffect) get_store().find_element_user(PROPERTY_QNAME[23], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public int sizeOfOuterShdwArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[23]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setOuterShdwArray(CTOuterShadowEffect[] outerShdwArray) {
        check_orphaned();
        arraySetterHelper(outerShdwArray, PROPERTY_QNAME[23]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setOuterShdwArray(int i, CTOuterShadowEffect outerShdw) {
        generatedSetterHelperImpl(outerShdw, PROPERTY_QNAME[23], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTOuterShadowEffect insertNewOuterShdw(int i) {
        CTOuterShadowEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOuterShadowEffect) get_store().insert_element_user(PROPERTY_QNAME[23], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTOuterShadowEffect addNewOuterShdw() {
        CTOuterShadowEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOuterShadowEffect) get_store().add_element_user(PROPERTY_QNAME[23]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void removeOuterShdw(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[23], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public List<CTPresetShadowEffect> getPrstShdwList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda62
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectContainerImpl.this.getPrstShdwArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda63
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTEffectContainerImpl.this.setPrstShdwArray(((Integer) obj).intValue(), (CTPresetShadowEffect) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda64
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectContainerImpl.this.insertNewPrstShdw(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda65
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTEffectContainerImpl.this.removePrstShdw(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda66
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTEffectContainerImpl.this.sizeOfPrstShdwArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTPresetShadowEffect[] getPrstShdwArray() {
        return getXmlObjectArray(PROPERTY_QNAME[24], (XmlObject[]) new CTPresetShadowEffect[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTPresetShadowEffect getPrstShdwArray(int i) {
        CTPresetShadowEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[24], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public int sizeOfPrstShdwArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[24]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setPrstShdwArray(CTPresetShadowEffect[] prstShdwArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) prstShdwArray, PROPERTY_QNAME[24]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setPrstShdwArray(int i, CTPresetShadowEffect prstShdw) {
        generatedSetterHelperImpl(prstShdw, PROPERTY_QNAME[24], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTPresetShadowEffect insertNewPrstShdw(int i) {
        CTPresetShadowEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[24], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTPresetShadowEffect addNewPrstShdw() {
        CTPresetShadowEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[24]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void removePrstShdw(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[24], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public List<CTReflectionEffect> getReflectionList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda56
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectContainerImpl.this.getReflectionArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda57
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTEffectContainerImpl.this.setReflectionArray(((Integer) obj).intValue(), (CTReflectionEffect) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda58
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectContainerImpl.this.insertNewReflection(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda59
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTEffectContainerImpl.this.removeReflection(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda60
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTEffectContainerImpl.this.sizeOfReflectionArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTReflectionEffect[] getReflectionArray() {
        return getXmlObjectArray(PROPERTY_QNAME[25], (XmlObject[]) new CTReflectionEffect[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTReflectionEffect getReflectionArray(int i) {
        CTReflectionEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[25], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public int sizeOfReflectionArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[25]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setReflectionArray(CTReflectionEffect[] reflectionArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) reflectionArray, PROPERTY_QNAME[25]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setReflectionArray(int i, CTReflectionEffect reflection) {
        generatedSetterHelperImpl(reflection, PROPERTY_QNAME[25], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTReflectionEffect insertNewReflection(int i) {
        CTReflectionEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[25], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTReflectionEffect addNewReflection() {
        CTReflectionEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[25]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void removeReflection(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[25], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public List<CTRelativeOffsetEffect> getRelOffList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectContainerImpl.this.getRelOffArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTEffectContainerImpl.this.setRelOffArray(((Integer) obj).intValue(), (CTRelativeOffsetEffect) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectContainerImpl.this.insertNewRelOff(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTEffectContainerImpl.this.removeRelOff(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda5
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTEffectContainerImpl.this.sizeOfRelOffArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTRelativeOffsetEffect[] getRelOffArray() {
        return getXmlObjectArray(PROPERTY_QNAME[26], (XmlObject[]) new CTRelativeOffsetEffect[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTRelativeOffsetEffect getRelOffArray(int i) {
        CTRelativeOffsetEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[26], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public int sizeOfRelOffArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[26]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setRelOffArray(CTRelativeOffsetEffect[] relOffArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) relOffArray, PROPERTY_QNAME[26]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setRelOffArray(int i, CTRelativeOffsetEffect relOff) {
        generatedSetterHelperImpl(relOff, PROPERTY_QNAME[26], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTRelativeOffsetEffect insertNewRelOff(int i) {
        CTRelativeOffsetEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[26], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTRelativeOffsetEffect addNewRelOff() {
        CTRelativeOffsetEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[26]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void removeRelOff(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[26], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public List<CTSoftEdgesEffect> getSoftEdgeList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda106
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectContainerImpl.this.getSoftEdgeArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda107
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTEffectContainerImpl.this.setSoftEdgeArray(((Integer) obj).intValue(), (CTSoftEdgesEffect) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda108
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectContainerImpl.this.insertNewSoftEdge(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda109
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTEffectContainerImpl.this.removeSoftEdge(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda110
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTEffectContainerImpl.this.sizeOfSoftEdgeArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTSoftEdgesEffect[] getSoftEdgeArray() {
        return getXmlObjectArray(PROPERTY_QNAME[27], (XmlObject[]) new CTSoftEdgesEffect[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTSoftEdgesEffect getSoftEdgeArray(int i) {
        CTSoftEdgesEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[27], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public int sizeOfSoftEdgeArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[27]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setSoftEdgeArray(CTSoftEdgesEffect[] softEdgeArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) softEdgeArray, PROPERTY_QNAME[27]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setSoftEdgeArray(int i, CTSoftEdgesEffect softEdge) {
        generatedSetterHelperImpl(softEdge, PROPERTY_QNAME[27], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTSoftEdgesEffect insertNewSoftEdge(int i) {
        CTSoftEdgesEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[27], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTSoftEdgesEffect addNewSoftEdge() {
        CTSoftEdgesEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[27]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void removeSoftEdge(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[27], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public List<CTTintEffect> getTintList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda23
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectContainerImpl.this.getTintArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda24
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTEffectContainerImpl.this.setTintArray(((Integer) obj).intValue(), (CTTintEffect) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda25
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectContainerImpl.this.insertNewTint(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda26
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTEffectContainerImpl.this.removeTint(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda27
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTEffectContainerImpl.this.sizeOfTintArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTTintEffect[] getTintArray() {
        return getXmlObjectArray(PROPERTY_QNAME[28], (XmlObject[]) new CTTintEffect[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTTintEffect getTintArray(int i) {
        CTTintEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[28], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public int sizeOfTintArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[28]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setTintArray(CTTintEffect[] tintArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) tintArray, PROPERTY_QNAME[28]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setTintArray(int i, CTTintEffect tint) {
        generatedSetterHelperImpl(tint, PROPERTY_QNAME[28], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTTintEffect insertNewTint(int i) {
        CTTintEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[28], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTTintEffect addNewTint() {
        CTTintEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[28]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void removeTint(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[28], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public List<CTTransformEffect> getXfrmList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda89
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectContainerImpl.this.getXfrmArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda90
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTEffectContainerImpl.this.setXfrmArray(((Integer) obj).intValue(), (CTTransformEffect) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda91
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectContainerImpl.this.insertNewXfrm(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda92
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTEffectContainerImpl.this.removeXfrm(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl$$ExternalSyntheticLambda93
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTEffectContainerImpl.this.sizeOfXfrmArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTTransformEffect[] getXfrmArray() {
        return getXmlObjectArray(PROPERTY_QNAME[29], (XmlObject[]) new CTTransformEffect[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTTransformEffect getXfrmArray(int i) {
        CTTransformEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[29], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public int sizeOfXfrmArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[29]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setXfrmArray(CTTransformEffect[] xfrmArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) xfrmArray, PROPERTY_QNAME[29]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setXfrmArray(int i, CTTransformEffect xfrm) {
        generatedSetterHelperImpl(xfrm, PROPERTY_QNAME[29], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTTransformEffect insertNewXfrm(int i) {
        CTTransformEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[29], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public CTTransformEffect addNewXfrm() {
        CTTransformEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[29]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void removeXfrm(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[29], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public STEffectContainerType$Enum getType() {
        STEffectContainerType$Enum sTEffectContainerType$Enum;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[30]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[30]);
            }
            sTEffectContainerType$Enum = target == null ? null : (STEffectContainerType$Enum) target.getEnumValue();
        }
        return sTEffectContainerType$Enum;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public STEffectContainerType xgetType() {
        STEffectContainerType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_attribute_user(PROPERTY_QNAME[30]);
            if (target == null) {
                target = (STEffectContainerType) get_default_attribute_value(PROPERTY_QNAME[30]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public boolean isSetType() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[30]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setType(STEffectContainerType$Enum type) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[30]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[30]);
            }
            target.setEnumValue(type);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void xsetType(STEffectContainerType type) {
        synchronized (monitor()) {
            check_orphaned();
            STEffectContainerType target = get_store().find_attribute_user(PROPERTY_QNAME[30]);
            if (target == null) {
                target = (STEffectContainerType) get_store().add_attribute_user(PROPERTY_QNAME[30]);
            }
            target.set(type);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void unsetType() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[30]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public String getName() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[31]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public XmlToken xgetName() {
        XmlToken target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlToken) get_store().find_attribute_user(PROPERTY_QNAME[31]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public boolean isSetName() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[31]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void setName(String name) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[31]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[31]);
            }
            target.setStringValue(name);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void xsetName(XmlToken name) {
        synchronized (monitor()) {
            check_orphaned();
            XmlToken target = (XmlToken) get_store().find_attribute_user(PROPERTY_QNAME[31]);
            if (target == null) {
                target = (XmlToken) get_store().add_attribute_user(PROPERTY_QNAME[31]);
            }
            target.set(name);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer
    public void unsetName() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[31]);
        }
    }
}
