package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import androidx.core.text.util.LocalePreferences;
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
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAngle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTComplementTransform;
import org.openxmlformats.schemas.drawingml.x2006.main.CTFixedPercentage;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGammaTransform;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGrayscaleTransform;
import org.openxmlformats.schemas.drawingml.x2006.main.CTInverseGammaTransform;
import org.openxmlformats.schemas.drawingml.x2006.main.CTInverseTransform;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPercentage;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveFixedAngle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveFixedPercentage;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositivePercentage;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor;
import org.openxmlformats.schemas.drawingml.x2006.main.STPresetColorVal;

/* loaded from: classes11.dex */
public class CTPresetColorImpl extends XmlComplexContentImpl implements CTPresetColor {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "tint"), new QName(XSSFRelation.NS_DRAWINGML, "shade"), new QName(XSSFRelation.NS_DRAWINGML, "comp"), new QName(XSSFRelation.NS_DRAWINGML, "inv"), new QName(XSSFRelation.NS_DRAWINGML, "gray"), new QName(XSSFRelation.NS_DRAWINGML, "alpha"), new QName(XSSFRelation.NS_DRAWINGML, "alphaOff"), new QName(XSSFRelation.NS_DRAWINGML, "alphaMod"), new QName(XSSFRelation.NS_DRAWINGML, "hue"), new QName(XSSFRelation.NS_DRAWINGML, "hueOff"), new QName(XSSFRelation.NS_DRAWINGML, "hueMod"), new QName(XSSFRelation.NS_DRAWINGML, LocalePreferences.FirstDayOfWeek.SATURDAY), new QName(XSSFRelation.NS_DRAWINGML, "satOff"), new QName(XSSFRelation.NS_DRAWINGML, "satMod"), new QName(XSSFRelation.NS_DRAWINGML, "lum"), new QName(XSSFRelation.NS_DRAWINGML, "lumOff"), new QName(XSSFRelation.NS_DRAWINGML, "lumMod"), new QName(XSSFRelation.NS_DRAWINGML, "red"), new QName(XSSFRelation.NS_DRAWINGML, "redOff"), new QName(XSSFRelation.NS_DRAWINGML, "redMod"), new QName(XSSFRelation.NS_DRAWINGML, "green"), new QName(XSSFRelation.NS_DRAWINGML, "greenOff"), new QName(XSSFRelation.NS_DRAWINGML, "greenMod"), new QName(XSSFRelation.NS_DRAWINGML, "blue"), new QName(XSSFRelation.NS_DRAWINGML, "blueOff"), new QName(XSSFRelation.NS_DRAWINGML, "blueMod"), new QName(XSSFRelation.NS_DRAWINGML, "gamma"), new QName(XSSFRelation.NS_DRAWINGML, "invGamma"), new QName("", "val")};
    private static final long serialVersionUID = 1;

    public CTPresetColorImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public List<CTPositiveFixedPercentage> getTintList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda57
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPresetColorImpl.this.getTintArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda58
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTPresetColorImpl.this.setTintArray(((Integer) obj).intValue(), (CTPositiveFixedPercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda59
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPresetColorImpl.this.insertNewTint(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda60
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTPresetColorImpl.this.removeTint(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda61
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTPresetColorImpl.this.sizeOfTintArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPositiveFixedPercentage[] getTintArray() {
        return (CTPositiveFixedPercentage[]) getXmlObjectArray(PROPERTY_QNAME[0], new CTPositiveFixedPercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPositiveFixedPercentage getTintArray(int i) {
        CTPositiveFixedPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPositiveFixedPercentage) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public int sizeOfTintArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void setTintArray(CTPositiveFixedPercentage[] tintArray) {
        check_orphaned();
        arraySetterHelper(tintArray, PROPERTY_QNAME[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void setTintArray(int i, CTPositiveFixedPercentage tint) {
        generatedSetterHelperImpl(tint, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPositiveFixedPercentage insertNewTint(int i) {
        CTPositiveFixedPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPositiveFixedPercentage) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPositiveFixedPercentage addNewTint() {
        CTPositiveFixedPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPositiveFixedPercentage) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void removeTint(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public List<CTPositiveFixedPercentage> getShadeList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda39
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPresetColorImpl.this.getShadeArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda40
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTPresetColorImpl.this.setShadeArray(((Integer) obj).intValue(), (CTPositiveFixedPercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda41
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPresetColorImpl.this.insertNewShade(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda42
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTPresetColorImpl.this.removeShade(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda43
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTPresetColorImpl.this.sizeOfShadeArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPositiveFixedPercentage[] getShadeArray() {
        return (CTPositiveFixedPercentage[]) getXmlObjectArray(PROPERTY_QNAME[1], new CTPositiveFixedPercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPositiveFixedPercentage getShadeArray(int i) {
        CTPositiveFixedPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPositiveFixedPercentage) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public int sizeOfShadeArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void setShadeArray(CTPositiveFixedPercentage[] shadeArray) {
        check_orphaned();
        arraySetterHelper(shadeArray, PROPERTY_QNAME[1]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void setShadeArray(int i, CTPositiveFixedPercentage shade) {
        generatedSetterHelperImpl(shade, PROPERTY_QNAME[1], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPositiveFixedPercentage insertNewShade(int i) {
        CTPositiveFixedPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPositiveFixedPercentage) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPositiveFixedPercentage addNewShade() {
        CTPositiveFixedPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPositiveFixedPercentage) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void removeShade(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public List<CTComplementTransform> getCompList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda85
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPresetColorImpl.this.getCompArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda86
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTPresetColorImpl.this.setCompArray(((Integer) obj).intValue(), (CTComplementTransform) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda87
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPresetColorImpl.this.insertNewComp(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda88
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTPresetColorImpl.this.removeComp(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda89
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTPresetColorImpl.this.sizeOfCompArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTComplementTransform[] getCompArray() {
        return (CTComplementTransform[]) getXmlObjectArray(PROPERTY_QNAME[2], new CTComplementTransform[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTComplementTransform getCompArray(int i) {
        CTComplementTransform target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTComplementTransform) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public int sizeOfCompArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void setCompArray(CTComplementTransform[] compArray) {
        check_orphaned();
        arraySetterHelper(compArray, PROPERTY_QNAME[2]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void setCompArray(int i, CTComplementTransform comp) {
        generatedSetterHelperImpl(comp, PROPERTY_QNAME[2], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTComplementTransform insertNewComp(int i) {
        CTComplementTransform target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTComplementTransform) get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTComplementTransform addNewComp() {
        CTComplementTransform target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTComplementTransform) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void removeComp(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public List<CTInverseTransform> getInvList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda17
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPresetColorImpl.this.getInvArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda18
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTPresetColorImpl.this.setInvArray(((Integer) obj).intValue(), (CTInverseTransform) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda19
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPresetColorImpl.this.insertNewInv(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda20
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTPresetColorImpl.this.removeInv(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda21
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTPresetColorImpl.this.sizeOfInvArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTInverseTransform[] getInvArray() {
        return getXmlObjectArray(PROPERTY_QNAME[3], (XmlObject[]) new CTInverseTransform[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTInverseTransform getInvArray(int i) {
        CTInverseTransform target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[3], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public int sizeOfInvArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void setInvArray(CTInverseTransform[] invArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) invArray, PROPERTY_QNAME[3]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void setInvArray(int i, CTInverseTransform inv) {
        generatedSetterHelperImpl(inv, PROPERTY_QNAME[3], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTInverseTransform insertNewInv(int i) {
        CTInverseTransform target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[3], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTInverseTransform addNewInv() {
        CTInverseTransform target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void removeInv(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public List<CTGrayscaleTransform> getGrayList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda90
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPresetColorImpl.this.getGrayArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda91
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTPresetColorImpl.this.setGrayArray(((Integer) obj).intValue(), (CTGrayscaleTransform) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda92
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPresetColorImpl.this.insertNewGray(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda93
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTPresetColorImpl.this.removeGray(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda94
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTPresetColorImpl.this.sizeOfGrayArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTGrayscaleTransform[] getGrayArray() {
        return getXmlObjectArray(PROPERTY_QNAME[4], (XmlObject[]) new CTGrayscaleTransform[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTGrayscaleTransform getGrayArray(int i) {
        CTGrayscaleTransform target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[4], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public int sizeOfGrayArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[4]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void setGrayArray(CTGrayscaleTransform[] grayArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) grayArray, PROPERTY_QNAME[4]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void setGrayArray(int i, CTGrayscaleTransform gray) {
        generatedSetterHelperImpl(gray, PROPERTY_QNAME[4], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTGrayscaleTransform insertNewGray(int i) {
        CTGrayscaleTransform target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[4], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTGrayscaleTransform addNewGray() {
        CTGrayscaleTransform target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void removeGray(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public List<CTPositiveFixedPercentage> getAlphaList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPresetColorImpl.this.getAlphaArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda51
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTPresetColorImpl.this.setAlphaArray(((Integer) obj).intValue(), (CTPositiveFixedPercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda62
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPresetColorImpl.this.insertNewAlpha(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda73
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTPresetColorImpl.this.removeAlpha(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda84
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTPresetColorImpl.this.sizeOfAlphaArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPositiveFixedPercentage[] getAlphaArray() {
        return (CTPositiveFixedPercentage[]) getXmlObjectArray(PROPERTY_QNAME[5], new CTPositiveFixedPercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPositiveFixedPercentage getAlphaArray(int i) {
        CTPositiveFixedPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPositiveFixedPercentage) get_store().find_element_user(PROPERTY_QNAME[5], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public int sizeOfAlphaArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[5]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void setAlphaArray(CTPositiveFixedPercentage[] alphaArray) {
        check_orphaned();
        arraySetterHelper(alphaArray, PROPERTY_QNAME[5]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void setAlphaArray(int i, CTPositiveFixedPercentage alpha) {
        generatedSetterHelperImpl(alpha, PROPERTY_QNAME[5], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPositiveFixedPercentage insertNewAlpha(int i) {
        CTPositiveFixedPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPositiveFixedPercentage) get_store().insert_element_user(PROPERTY_QNAME[5], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPositiveFixedPercentage addNewAlpha() {
        CTPositiveFixedPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPositiveFixedPercentage) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void removeAlpha(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public List<CTFixedPercentage> getAlphaOffList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda28
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPresetColorImpl.this.getAlphaOffArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda29
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTPresetColorImpl.this.setAlphaOffArray(((Integer) obj).intValue(), (CTFixedPercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda30
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPresetColorImpl.this.insertNewAlphaOff(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda31
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTPresetColorImpl.this.removeAlphaOff(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda32
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTPresetColorImpl.this.sizeOfAlphaOffArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTFixedPercentage[] getAlphaOffArray() {
        return (CTFixedPercentage[]) getXmlObjectArray(PROPERTY_QNAME[6], new CTFixedPercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTFixedPercentage getAlphaOffArray(int i) {
        CTFixedPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFixedPercentage) get_store().find_element_user(PROPERTY_QNAME[6], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public int sizeOfAlphaOffArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[6]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void setAlphaOffArray(CTFixedPercentage[] alphaOffArray) {
        check_orphaned();
        arraySetterHelper(alphaOffArray, PROPERTY_QNAME[6]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void setAlphaOffArray(int i, CTFixedPercentage alphaOff) {
        generatedSetterHelperImpl(alphaOff, PROPERTY_QNAME[6], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTFixedPercentage insertNewAlphaOff(int i) {
        CTFixedPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFixedPercentage) get_store().insert_element_user(PROPERTY_QNAME[6], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTFixedPercentage addNewAlphaOff() {
        CTFixedPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFixedPercentage) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void removeAlphaOff(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public List<CTPositivePercentage> getAlphaModList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda118
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPresetColorImpl.this.getAlphaModArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda119
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTPresetColorImpl.this.setAlphaModArray(((Integer) obj).intValue(), (CTPositivePercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda120
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPresetColorImpl.this.insertNewAlphaMod(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda121
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTPresetColorImpl.this.removeAlphaMod(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda122
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTPresetColorImpl.this.sizeOfAlphaModArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPositivePercentage[] getAlphaModArray() {
        return (CTPositivePercentage[]) getXmlObjectArray(PROPERTY_QNAME[7], new CTPositivePercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPositivePercentage getAlphaModArray(int i) {
        CTPositivePercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPositivePercentage) get_store().find_element_user(PROPERTY_QNAME[7], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public int sizeOfAlphaModArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[7]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void setAlphaModArray(CTPositivePercentage[] alphaModArray) {
        check_orphaned();
        arraySetterHelper(alphaModArray, PROPERTY_QNAME[7]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void setAlphaModArray(int i, CTPositivePercentage alphaMod) {
        generatedSetterHelperImpl(alphaMod, PROPERTY_QNAME[7], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPositivePercentage insertNewAlphaMod(int i) {
        CTPositivePercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPositivePercentage) get_store().insert_element_user(PROPERTY_QNAME[7], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPositivePercentage addNewAlphaMod() {
        CTPositivePercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPositivePercentage) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void removeAlphaMod(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public List<CTPositiveFixedAngle> getHueList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda107
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPresetColorImpl.this.getHueArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda108
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTPresetColorImpl.this.setHueArray(((Integer) obj).intValue(), (CTPositiveFixedAngle) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda109
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPresetColorImpl.this.insertNewHue(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda110
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTPresetColorImpl.this.removeHue(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda111
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTPresetColorImpl.this.sizeOfHueArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPositiveFixedAngle[] getHueArray() {
        return (CTPositiveFixedAngle[]) getXmlObjectArray(PROPERTY_QNAME[8], new CTPositiveFixedAngle[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPositiveFixedAngle getHueArray(int i) {
        CTPositiveFixedAngle target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPositiveFixedAngle) get_store().find_element_user(PROPERTY_QNAME[8], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public int sizeOfHueArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[8]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void setHueArray(CTPositiveFixedAngle[] hueArray) {
        check_orphaned();
        arraySetterHelper(hueArray, PROPERTY_QNAME[8]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void setHueArray(int i, CTPositiveFixedAngle hue) {
        generatedSetterHelperImpl(hue, PROPERTY_QNAME[8], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPositiveFixedAngle insertNewHue(int i) {
        CTPositiveFixedAngle target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPositiveFixedAngle) get_store().insert_element_user(PROPERTY_QNAME[8], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPositiveFixedAngle addNewHue() {
        CTPositiveFixedAngle target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPositiveFixedAngle) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void removeHue(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public List<CTAngle> getHueOffList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda52
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPresetColorImpl.this.getHueOffArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda53
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTPresetColorImpl.this.setHueOffArray(((Integer) obj).intValue(), (CTAngle) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda54
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPresetColorImpl.this.insertNewHueOff(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda55
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTPresetColorImpl.this.removeHueOff(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda56
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTPresetColorImpl.this.sizeOfHueOffArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTAngle[] getHueOffArray() {
        return getXmlObjectArray(PROPERTY_QNAME[9], (XmlObject[]) new CTAngle[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTAngle getHueOffArray(int i) {
        CTAngle target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[9], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public int sizeOfHueOffArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[9]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void setHueOffArray(CTAngle[] hueOffArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) hueOffArray, PROPERTY_QNAME[9]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void setHueOffArray(int i, CTAngle hueOff) {
        generatedSetterHelperImpl(hueOff, PROPERTY_QNAME[9], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTAngle insertNewHueOff(int i) {
        CTAngle target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[9], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTAngle addNewHueOff() {
        CTAngle target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void removeHueOff(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public List<CTPositivePercentage> getHueModList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda6
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPresetColorImpl.this.getHueModArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda7
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTPresetColorImpl.this.setHueModArray(((Integer) obj).intValue(), (CTPositivePercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda8
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPresetColorImpl.this.insertNewHueMod(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda9
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTPresetColorImpl.this.removeHueMod(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda10
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTPresetColorImpl.this.sizeOfHueModArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPositivePercentage[] getHueModArray() {
        return (CTPositivePercentage[]) getXmlObjectArray(PROPERTY_QNAME[10], new CTPositivePercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPositivePercentage getHueModArray(int i) {
        CTPositivePercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPositivePercentage) get_store().find_element_user(PROPERTY_QNAME[10], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public int sizeOfHueModArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[10]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void setHueModArray(CTPositivePercentage[] hueModArray) {
        check_orphaned();
        arraySetterHelper(hueModArray, PROPERTY_QNAME[10]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void setHueModArray(int i, CTPositivePercentage hueMod) {
        generatedSetterHelperImpl(hueMod, PROPERTY_QNAME[10], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPositivePercentage insertNewHueMod(int i) {
        CTPositivePercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPositivePercentage) get_store().insert_element_user(PROPERTY_QNAME[10], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPositivePercentage addNewHueMod() {
        CTPositivePercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPositivePercentage) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void removeHueMod(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public List<CTPercentage> getSatList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda74
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPresetColorImpl.this.getSatArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda75
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTPresetColorImpl.this.setSatArray(((Integer) obj).intValue(), (CTPercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda76
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPresetColorImpl.this.insertNewSat(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda77
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTPresetColorImpl.this.removeSat(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda78
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTPresetColorImpl.this.sizeOfSatArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPercentage[] getSatArray() {
        return (CTPercentage[]) getXmlObjectArray(PROPERTY_QNAME[11], new CTPercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPercentage getSatArray(int i) {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().find_element_user(PROPERTY_QNAME[11], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public int sizeOfSatArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[11]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void setSatArray(CTPercentage[] satArray) {
        check_orphaned();
        arraySetterHelper(satArray, PROPERTY_QNAME[11]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void setSatArray(int i, CTPercentage sat) {
        generatedSetterHelperImpl(sat, PROPERTY_QNAME[11], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPercentage insertNewSat(int i) {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().insert_element_user(PROPERTY_QNAME[11], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPercentage addNewSat() {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void removeSat(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public List<CTPercentage> getSatOffList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda79
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPresetColorImpl.this.getSatOffArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda80
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTPresetColorImpl.this.setSatOffArray(((Integer) obj).intValue(), (CTPercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda81
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPresetColorImpl.this.insertNewSatOff(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda82
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTPresetColorImpl.this.removeSatOff(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda83
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTPresetColorImpl.this.sizeOfSatOffArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPercentage[] getSatOffArray() {
        return (CTPercentage[]) getXmlObjectArray(PROPERTY_QNAME[12], new CTPercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPercentage getSatOffArray(int i) {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().find_element_user(PROPERTY_QNAME[12], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public int sizeOfSatOffArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[12]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void setSatOffArray(CTPercentage[] satOffArray) {
        check_orphaned();
        arraySetterHelper(satOffArray, PROPERTY_QNAME[12]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void setSatOffArray(int i, CTPercentage satOff) {
        generatedSetterHelperImpl(satOff, PROPERTY_QNAME[12], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPercentage insertNewSatOff(int i) {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().insert_element_user(PROPERTY_QNAME[12], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPercentage addNewSatOff() {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void removeSatOff(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public List<CTPercentage> getSatModList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda134
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPresetColorImpl.this.getSatModArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda135
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTPresetColorImpl.this.setSatModArray(((Integer) obj).intValue(), (CTPercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda136
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPresetColorImpl.this.insertNewSatMod(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda137
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTPresetColorImpl.this.removeSatMod(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda138
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTPresetColorImpl.this.sizeOfSatModArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPercentage[] getSatModArray() {
        return (CTPercentage[]) getXmlObjectArray(PROPERTY_QNAME[13], new CTPercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPercentage getSatModArray(int i) {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().find_element_user(PROPERTY_QNAME[13], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public int sizeOfSatModArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[13]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void setSatModArray(CTPercentage[] satModArray) {
        check_orphaned();
        arraySetterHelper(satModArray, PROPERTY_QNAME[13]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void setSatModArray(int i, CTPercentage satMod) {
        generatedSetterHelperImpl(satMod, PROPERTY_QNAME[13], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPercentage insertNewSatMod(int i) {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().insert_element_user(PROPERTY_QNAME[13], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPercentage addNewSatMod() {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void removeSatMod(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public List<CTPercentage> getLumList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda46
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPresetColorImpl.this.getLumArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda47
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTPresetColorImpl.this.setLumArray(((Integer) obj).intValue(), (CTPercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda48
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPresetColorImpl.this.insertNewLum(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda49
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTPresetColorImpl.this.removeLum(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda50
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTPresetColorImpl.this.sizeOfLumArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPercentage[] getLumArray() {
        return (CTPercentage[]) getXmlObjectArray(PROPERTY_QNAME[14], new CTPercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPercentage getLumArray(int i) {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().find_element_user(PROPERTY_QNAME[14], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public int sizeOfLumArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[14]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void setLumArray(CTPercentage[] lumArray) {
        check_orphaned();
        arraySetterHelper(lumArray, PROPERTY_QNAME[14]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void setLumArray(int i, CTPercentage lum) {
        generatedSetterHelperImpl(lum, PROPERTY_QNAME[14], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPercentage insertNewLum(int i) {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().insert_element_user(PROPERTY_QNAME[14], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPercentage addNewLum() {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().add_element_user(PROPERTY_QNAME[14]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void removeLum(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[14], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public List<CTPercentage> getLumOffList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda101
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPresetColorImpl.this.getLumOffArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda102
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTPresetColorImpl.this.setLumOffArray(((Integer) obj).intValue(), (CTPercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda103
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPresetColorImpl.this.insertNewLumOff(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda104
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTPresetColorImpl.this.removeLumOff(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda105
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTPresetColorImpl.this.sizeOfLumOffArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPercentage[] getLumOffArray() {
        return (CTPercentage[]) getXmlObjectArray(PROPERTY_QNAME[15], new CTPercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPercentage getLumOffArray(int i) {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().find_element_user(PROPERTY_QNAME[15], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public int sizeOfLumOffArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[15]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void setLumOffArray(CTPercentage[] lumOffArray) {
        check_orphaned();
        arraySetterHelper(lumOffArray, PROPERTY_QNAME[15]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void setLumOffArray(int i, CTPercentage lumOff) {
        generatedSetterHelperImpl(lumOff, PROPERTY_QNAME[15], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPercentage insertNewLumOff(int i) {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().insert_element_user(PROPERTY_QNAME[15], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPercentage addNewLumOff() {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().add_element_user(PROPERTY_QNAME[15]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void removeLumOff(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[15], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public List<CTPercentage> getLumModList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda112
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPresetColorImpl.this.getLumModArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda113
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTPresetColorImpl.this.setLumModArray(((Integer) obj).intValue(), (CTPercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda114
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPresetColorImpl.this.insertNewLumMod(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda115
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTPresetColorImpl.this.removeLumMod(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda116
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTPresetColorImpl.this.sizeOfLumModArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPercentage[] getLumModArray() {
        return (CTPercentage[]) getXmlObjectArray(PROPERTY_QNAME[16], new CTPercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPercentage getLumModArray(int i) {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().find_element_user(PROPERTY_QNAME[16], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public int sizeOfLumModArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[16]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void setLumModArray(CTPercentage[] lumModArray) {
        check_orphaned();
        arraySetterHelper(lumModArray, PROPERTY_QNAME[16]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void setLumModArray(int i, CTPercentage lumMod) {
        generatedSetterHelperImpl(lumMod, PROPERTY_QNAME[16], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPercentage insertNewLumMod(int i) {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().insert_element_user(PROPERTY_QNAME[16], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPercentage addNewLumMod() {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().add_element_user(PROPERTY_QNAME[16]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void removeLumMod(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[16], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public List<CTPercentage> getRedList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda123
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPresetColorImpl.this.getRedArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda124
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTPresetColorImpl.this.setRedArray(((Integer) obj).intValue(), (CTPercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda125
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPresetColorImpl.this.insertNewRed(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda126
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTPresetColorImpl.this.removeRed(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda127
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTPresetColorImpl.this.sizeOfRedArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPercentage[] getRedArray() {
        return (CTPercentage[]) getXmlObjectArray(PROPERTY_QNAME[17], new CTPercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPercentage getRedArray(int i) {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().find_element_user(PROPERTY_QNAME[17], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public int sizeOfRedArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[17]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void setRedArray(CTPercentage[] redArray) {
        check_orphaned();
        arraySetterHelper(redArray, PROPERTY_QNAME[17]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void setRedArray(int i, CTPercentage red) {
        generatedSetterHelperImpl(red, PROPERTY_QNAME[17], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPercentage insertNewRed(int i) {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().insert_element_user(PROPERTY_QNAME[17], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPercentage addNewRed() {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().add_element_user(PROPERTY_QNAME[17]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void removeRed(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[17], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public List<CTPercentage> getRedOffList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda63
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPresetColorImpl.this.getRedOffArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda64
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTPresetColorImpl.this.setRedOffArray(((Integer) obj).intValue(), (CTPercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda65
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPresetColorImpl.this.insertNewRedOff(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda66
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTPresetColorImpl.this.removeRedOff(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda67
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTPresetColorImpl.this.sizeOfRedOffArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPercentage[] getRedOffArray() {
        return (CTPercentage[]) getXmlObjectArray(PROPERTY_QNAME[18], new CTPercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPercentage getRedOffArray(int i) {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().find_element_user(PROPERTY_QNAME[18], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public int sizeOfRedOffArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[18]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void setRedOffArray(CTPercentage[] redOffArray) {
        check_orphaned();
        arraySetterHelper(redOffArray, PROPERTY_QNAME[18]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void setRedOffArray(int i, CTPercentage redOff) {
        generatedSetterHelperImpl(redOff, PROPERTY_QNAME[18], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPercentage insertNewRedOff(int i) {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().insert_element_user(PROPERTY_QNAME[18], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPercentage addNewRedOff() {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().add_element_user(PROPERTY_QNAME[18]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void removeRedOff(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[18], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public List<CTPercentage> getRedModList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda12
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPresetColorImpl.this.getRedModArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda13
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTPresetColorImpl.this.setRedModArray(((Integer) obj).intValue(), (CTPercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda14
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPresetColorImpl.this.insertNewRedMod(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda15
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTPresetColorImpl.this.removeRedMod(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda16
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTPresetColorImpl.this.sizeOfRedModArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPercentage[] getRedModArray() {
        return (CTPercentage[]) getXmlObjectArray(PROPERTY_QNAME[19], new CTPercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPercentage getRedModArray(int i) {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().find_element_user(PROPERTY_QNAME[19], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public int sizeOfRedModArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[19]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void setRedModArray(CTPercentage[] redModArray) {
        check_orphaned();
        arraySetterHelper(redModArray, PROPERTY_QNAME[19]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void setRedModArray(int i, CTPercentage redMod) {
        generatedSetterHelperImpl(redMod, PROPERTY_QNAME[19], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPercentage insertNewRedMod(int i) {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().insert_element_user(PROPERTY_QNAME[19], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPercentage addNewRedMod() {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().add_element_user(PROPERTY_QNAME[19]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void removeRedMod(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[19], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public List<CTPercentage> getGreenList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda11
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPresetColorImpl.this.getGreenArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda22
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTPresetColorImpl.this.setGreenArray(((Integer) obj).intValue(), (CTPercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda33
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPresetColorImpl.this.insertNewGreen(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda44
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTPresetColorImpl.this.removeGreen(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda45
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTPresetColorImpl.this.sizeOfGreenArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPercentage[] getGreenArray() {
        return (CTPercentage[]) getXmlObjectArray(PROPERTY_QNAME[20], new CTPercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPercentage getGreenArray(int i) {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().find_element_user(PROPERTY_QNAME[20], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public int sizeOfGreenArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[20]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void setGreenArray(CTPercentage[] greenArray) {
        check_orphaned();
        arraySetterHelper(greenArray, PROPERTY_QNAME[20]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void setGreenArray(int i, CTPercentage green) {
        generatedSetterHelperImpl(green, PROPERTY_QNAME[20], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPercentage insertNewGreen(int i) {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().insert_element_user(PROPERTY_QNAME[20], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPercentage addNewGreen() {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().add_element_user(PROPERTY_QNAME[20]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void removeGreen(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[20], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public List<CTPercentage> getGreenOffList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda96
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPresetColorImpl.this.getGreenOffArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda97
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTPresetColorImpl.this.setGreenOffArray(((Integer) obj).intValue(), (CTPercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda98
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPresetColorImpl.this.insertNewGreenOff(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda99
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTPresetColorImpl.this.removeGreenOff(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda100
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTPresetColorImpl.this.sizeOfGreenOffArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPercentage[] getGreenOffArray() {
        return (CTPercentage[]) getXmlObjectArray(PROPERTY_QNAME[21], new CTPercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPercentage getGreenOffArray(int i) {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().find_element_user(PROPERTY_QNAME[21], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public int sizeOfGreenOffArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[21]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void setGreenOffArray(CTPercentage[] greenOffArray) {
        check_orphaned();
        arraySetterHelper(greenOffArray, PROPERTY_QNAME[21]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void setGreenOffArray(int i, CTPercentage greenOff) {
        generatedSetterHelperImpl(greenOff, PROPERTY_QNAME[21], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPercentage insertNewGreenOff(int i) {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().insert_element_user(PROPERTY_QNAME[21], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPercentage addNewGreenOff() {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().add_element_user(PROPERTY_QNAME[21]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void removeGreenOff(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[21], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public List<CTPercentage> getGreenModList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPresetColorImpl.this.getGreenModArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTPresetColorImpl.this.setGreenModArray(((Integer) obj).intValue(), (CTPercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPresetColorImpl.this.insertNewGreenMod(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTPresetColorImpl.this.removeGreenMod(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda5
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTPresetColorImpl.this.sizeOfGreenModArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPercentage[] getGreenModArray() {
        return (CTPercentage[]) getXmlObjectArray(PROPERTY_QNAME[22], new CTPercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPercentage getGreenModArray(int i) {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().find_element_user(PROPERTY_QNAME[22], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public int sizeOfGreenModArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[22]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void setGreenModArray(CTPercentage[] greenModArray) {
        check_orphaned();
        arraySetterHelper(greenModArray, PROPERTY_QNAME[22]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void setGreenModArray(int i, CTPercentage greenMod) {
        generatedSetterHelperImpl(greenMod, PROPERTY_QNAME[22], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPercentage insertNewGreenMod(int i) {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().insert_element_user(PROPERTY_QNAME[22], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPercentage addNewGreenMod() {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().add_element_user(PROPERTY_QNAME[22]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void removeGreenMod(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[22], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public List<CTPercentage> getBlueList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda68
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPresetColorImpl.this.getBlueArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda69
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTPresetColorImpl.this.setBlueArray(((Integer) obj).intValue(), (CTPercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda70
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPresetColorImpl.this.insertNewBlue(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda71
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTPresetColorImpl.this.removeBlue(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda72
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTPresetColorImpl.this.sizeOfBlueArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPercentage[] getBlueArray() {
        return (CTPercentage[]) getXmlObjectArray(PROPERTY_QNAME[23], new CTPercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPercentage getBlueArray(int i) {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().find_element_user(PROPERTY_QNAME[23], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public int sizeOfBlueArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[23]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void setBlueArray(CTPercentage[] blueArray) {
        check_orphaned();
        arraySetterHelper(blueArray, PROPERTY_QNAME[23]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void setBlueArray(int i, CTPercentage blue) {
        generatedSetterHelperImpl(blue, PROPERTY_QNAME[23], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPercentage insertNewBlue(int i) {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().insert_element_user(PROPERTY_QNAME[23], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPercentage addNewBlue() {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().add_element_user(PROPERTY_QNAME[23]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void removeBlue(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[23], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public List<CTPercentage> getBlueOffList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda95
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPresetColorImpl.this.getBlueOffArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda106
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTPresetColorImpl.this.setBlueOffArray(((Integer) obj).intValue(), (CTPercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda117
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPresetColorImpl.this.insertNewBlueOff(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda128
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTPresetColorImpl.this.removeBlueOff(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda139
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTPresetColorImpl.this.sizeOfBlueOffArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPercentage[] getBlueOffArray() {
        return (CTPercentage[]) getXmlObjectArray(PROPERTY_QNAME[24], new CTPercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPercentage getBlueOffArray(int i) {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().find_element_user(PROPERTY_QNAME[24], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public int sizeOfBlueOffArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[24]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void setBlueOffArray(CTPercentage[] blueOffArray) {
        check_orphaned();
        arraySetterHelper(blueOffArray, PROPERTY_QNAME[24]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void setBlueOffArray(int i, CTPercentage blueOff) {
        generatedSetterHelperImpl(blueOff, PROPERTY_QNAME[24], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPercentage insertNewBlueOff(int i) {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().insert_element_user(PROPERTY_QNAME[24], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPercentage addNewBlueOff() {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().add_element_user(PROPERTY_QNAME[24]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void removeBlueOff(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[24], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public List<CTPercentage> getBlueModList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda23
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPresetColorImpl.this.getBlueModArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda24
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTPresetColorImpl.this.setBlueModArray(((Integer) obj).intValue(), (CTPercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda25
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPresetColorImpl.this.insertNewBlueMod(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda26
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTPresetColorImpl.this.removeBlueMod(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda27
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTPresetColorImpl.this.sizeOfBlueModArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPercentage[] getBlueModArray() {
        return (CTPercentage[]) getXmlObjectArray(PROPERTY_QNAME[25], new CTPercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPercentage getBlueModArray(int i) {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().find_element_user(PROPERTY_QNAME[25], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public int sizeOfBlueModArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[25]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void setBlueModArray(CTPercentage[] blueModArray) {
        check_orphaned();
        arraySetterHelper(blueModArray, PROPERTY_QNAME[25]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void setBlueModArray(int i, CTPercentage blueMod) {
        generatedSetterHelperImpl(blueMod, PROPERTY_QNAME[25], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPercentage insertNewBlueMod(int i) {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().insert_element_user(PROPERTY_QNAME[25], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTPercentage addNewBlueMod() {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().add_element_user(PROPERTY_QNAME[25]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void removeBlueMod(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[25], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public List<CTGammaTransform> getGammaList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda34
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPresetColorImpl.this.getGammaArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda35
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTPresetColorImpl.this.setGammaArray(((Integer) obj).intValue(), (CTGammaTransform) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda36
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPresetColorImpl.this.insertNewGamma(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda37
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTPresetColorImpl.this.removeGamma(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda38
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTPresetColorImpl.this.sizeOfGammaArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTGammaTransform[] getGammaArray() {
        return getXmlObjectArray(PROPERTY_QNAME[26], (XmlObject[]) new CTGammaTransform[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTGammaTransform getGammaArray(int i) {
        CTGammaTransform target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[26], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public int sizeOfGammaArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[26]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void setGammaArray(CTGammaTransform[] gammaArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) gammaArray, PROPERTY_QNAME[26]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void setGammaArray(int i, CTGammaTransform gamma) {
        generatedSetterHelperImpl(gamma, PROPERTY_QNAME[26], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTGammaTransform insertNewGamma(int i) {
        CTGammaTransform target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[26], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTGammaTransform addNewGamma() {
        CTGammaTransform target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[26]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void removeGamma(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[26], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public List<CTInverseGammaTransform> getInvGammaList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda129
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPresetColorImpl.this.getInvGammaArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda130
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTPresetColorImpl.this.setInvGammaArray(((Integer) obj).intValue(), (CTInverseGammaTransform) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda131
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPresetColorImpl.this.insertNewInvGamma(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda132
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTPresetColorImpl.this.removeInvGamma(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPresetColorImpl$$ExternalSyntheticLambda133
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTPresetColorImpl.this.sizeOfInvGammaArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTInverseGammaTransform[] getInvGammaArray() {
        return getXmlObjectArray(PROPERTY_QNAME[27], (XmlObject[]) new CTInverseGammaTransform[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTInverseGammaTransform getInvGammaArray(int i) {
        CTInverseGammaTransform target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[27], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public int sizeOfInvGammaArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[27]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void setInvGammaArray(CTInverseGammaTransform[] invGammaArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) invGammaArray, PROPERTY_QNAME[27]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void setInvGammaArray(int i, CTInverseGammaTransform invGamma) {
        generatedSetterHelperImpl(invGamma, PROPERTY_QNAME[27], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTInverseGammaTransform insertNewInvGamma(int i) {
        CTInverseGammaTransform target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[27], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public CTInverseGammaTransform addNewInvGamma() {
        CTInverseGammaTransform target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[27]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void removeInvGamma(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[27], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public STPresetColorVal.Enum getVal() {
        STPresetColorVal.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[28]);
            r1 = target == null ? null : (STPresetColorVal.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public STPresetColorVal xgetVal() {
        STPresetColorVal target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STPresetColorVal) get_store().find_attribute_user(PROPERTY_QNAME[28]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void setVal(STPresetColorVal.Enum val) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[28]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[28]);
            }
            target.setEnumValue(val);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor
    public void xsetVal(STPresetColorVal val) {
        synchronized (monitor()) {
            check_orphaned();
            STPresetColorVal target = (STPresetColorVal) get_store().find_attribute_user(PROPERTY_QNAME[28]);
            if (target == null) {
                target = (STPresetColorVal) get_store().add_attribute_user(PROPERTY_QNAME[28]);
            }
            target.set(val);
        }
    }
}
