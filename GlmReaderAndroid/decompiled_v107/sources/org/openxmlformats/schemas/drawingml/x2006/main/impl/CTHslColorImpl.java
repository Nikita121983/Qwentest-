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
import org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTInverseGammaTransform;
import org.openxmlformats.schemas.drawingml.x2006.main.CTInverseTransform;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPercentage;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveFixedAngle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveFixedPercentage;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositivePercentage;
import org.openxmlformats.schemas.drawingml.x2006.main.STPercentage;
import org.openxmlformats.schemas.drawingml.x2006.main.STPositiveFixedAngle;

/* loaded from: classes11.dex */
public class CTHslColorImpl extends XmlComplexContentImpl implements CTHslColor {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "tint"), new QName(XSSFRelation.NS_DRAWINGML, "shade"), new QName(XSSFRelation.NS_DRAWINGML, "comp"), new QName(XSSFRelation.NS_DRAWINGML, "inv"), new QName(XSSFRelation.NS_DRAWINGML, "gray"), new QName(XSSFRelation.NS_DRAWINGML, "alpha"), new QName(XSSFRelation.NS_DRAWINGML, "alphaOff"), new QName(XSSFRelation.NS_DRAWINGML, "alphaMod"), new QName(XSSFRelation.NS_DRAWINGML, "hue"), new QName(XSSFRelation.NS_DRAWINGML, "hueOff"), new QName(XSSFRelation.NS_DRAWINGML, "hueMod"), new QName(XSSFRelation.NS_DRAWINGML, LocalePreferences.FirstDayOfWeek.SATURDAY), new QName(XSSFRelation.NS_DRAWINGML, "satOff"), new QName(XSSFRelation.NS_DRAWINGML, "satMod"), new QName(XSSFRelation.NS_DRAWINGML, "lum"), new QName(XSSFRelation.NS_DRAWINGML, "lumOff"), new QName(XSSFRelation.NS_DRAWINGML, "lumMod"), new QName(XSSFRelation.NS_DRAWINGML, "red"), new QName(XSSFRelation.NS_DRAWINGML, "redOff"), new QName(XSSFRelation.NS_DRAWINGML, "redMod"), new QName(XSSFRelation.NS_DRAWINGML, "green"), new QName(XSSFRelation.NS_DRAWINGML, "greenOff"), new QName(XSSFRelation.NS_DRAWINGML, "greenMod"), new QName(XSSFRelation.NS_DRAWINGML, "blue"), new QName(XSSFRelation.NS_DRAWINGML, "blueOff"), new QName(XSSFRelation.NS_DRAWINGML, "blueMod"), new QName(XSSFRelation.NS_DRAWINGML, "gamma"), new QName(XSSFRelation.NS_DRAWINGML, "invGamma"), new QName("", "hue"), new QName("", LocalePreferences.FirstDayOfWeek.SATURDAY), new QName("", "lum")};
    private static final long serialVersionUID = 1;

    public CTHslColorImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public List<CTPositiveFixedPercentage> getTintList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda96
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTHslColorImpl.this.getTintArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda97
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTHslColorImpl.this.setTintArray(((Integer) obj).intValue(), (CTPositiveFixedPercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda98
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTHslColorImpl.this.insertNewTint(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda99
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTHslColorImpl.this.removeTint(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda100
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTHslColorImpl.this.sizeOfTintArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPositiveFixedPercentage[] getTintArray() {
        return (CTPositiveFixedPercentage[]) getXmlObjectArray(PROPERTY_QNAME[0], new CTPositiveFixedPercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public int sizeOfTintArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void setTintArray(CTPositiveFixedPercentage[] tintArray) {
        check_orphaned();
        arraySetterHelper(tintArray, PROPERTY_QNAME[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void setTintArray(int i, CTPositiveFixedPercentage tint) {
        generatedSetterHelperImpl(tint, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPositiveFixedPercentage insertNewTint(int i) {
        CTPositiveFixedPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPositiveFixedPercentage) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPositiveFixedPercentage addNewTint() {
        CTPositiveFixedPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPositiveFixedPercentage) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void removeTint(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public List<CTPositiveFixedPercentage> getShadeList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda68
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTHslColorImpl.this.getShadeArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda69
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTHslColorImpl.this.setShadeArray(((Integer) obj).intValue(), (CTPositiveFixedPercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda70
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTHslColorImpl.this.insertNewShade(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda71
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTHslColorImpl.this.removeShade(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda72
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTHslColorImpl.this.sizeOfShadeArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPositiveFixedPercentage[] getShadeArray() {
        return (CTPositiveFixedPercentage[]) getXmlObjectArray(PROPERTY_QNAME[1], new CTPositiveFixedPercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public int sizeOfShadeArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void setShadeArray(CTPositiveFixedPercentage[] shadeArray) {
        check_orphaned();
        arraySetterHelper(shadeArray, PROPERTY_QNAME[1]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void setShadeArray(int i, CTPositiveFixedPercentage shade) {
        generatedSetterHelperImpl(shade, PROPERTY_QNAME[1], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPositiveFixedPercentage insertNewShade(int i) {
        CTPositiveFixedPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPositiveFixedPercentage) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPositiveFixedPercentage addNewShade() {
        CTPositiveFixedPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPositiveFixedPercentage) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void removeShade(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public List<CTComplementTransform> getCompList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda95
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTHslColorImpl.this.getCompArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda106
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTHslColorImpl.this.setCompArray(((Integer) obj).intValue(), (CTComplementTransform) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda117
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTHslColorImpl.this.insertNewComp(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda128
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTHslColorImpl.this.removeComp(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda139
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTHslColorImpl.this.sizeOfCompArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTComplementTransform[] getCompArray() {
        return (CTComplementTransform[]) getXmlObjectArray(PROPERTY_QNAME[2], new CTComplementTransform[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public int sizeOfCompArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void setCompArray(CTComplementTransform[] compArray) {
        check_orphaned();
        arraySetterHelper(compArray, PROPERTY_QNAME[2]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void setCompArray(int i, CTComplementTransform comp) {
        generatedSetterHelperImpl(comp, PROPERTY_QNAME[2], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTComplementTransform insertNewComp(int i) {
        CTComplementTransform target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTComplementTransform) get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTComplementTransform addNewComp() {
        CTComplementTransform target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTComplementTransform) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void removeComp(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public List<CTInverseTransform> getInvList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda85
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTHslColorImpl.this.getInvArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda86
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTHslColorImpl.this.setInvArray(((Integer) obj).intValue(), (CTInverseTransform) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda87
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTHslColorImpl.this.insertNewInv(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda88
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTHslColorImpl.this.removeInv(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda89
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTHslColorImpl.this.sizeOfInvArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTInverseTransform[] getInvArray() {
        return getXmlObjectArray(PROPERTY_QNAME[3], (XmlObject[]) new CTInverseTransform[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public int sizeOfInvArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void setInvArray(CTInverseTransform[] invArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) invArray, PROPERTY_QNAME[3]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void setInvArray(int i, CTInverseTransform inv) {
        generatedSetterHelperImpl(inv, PROPERTY_QNAME[3], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTInverseTransform insertNewInv(int i) {
        CTInverseTransform target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[3], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTInverseTransform addNewInv() {
        CTInverseTransform target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void removeInv(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public List<CTGrayscaleTransform> getGrayList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda28
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTHslColorImpl.this.getGrayArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda29
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTHslColorImpl.this.setGrayArray(((Integer) obj).intValue(), (CTGrayscaleTransform) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda30
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTHslColorImpl.this.insertNewGray(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda31
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTHslColorImpl.this.removeGray(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda32
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTHslColorImpl.this.sizeOfGrayArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTGrayscaleTransform[] getGrayArray() {
        return getXmlObjectArray(PROPERTY_QNAME[4], (XmlObject[]) new CTGrayscaleTransform[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public int sizeOfGrayArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[4]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void setGrayArray(CTGrayscaleTransform[] grayArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) grayArray, PROPERTY_QNAME[4]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void setGrayArray(int i, CTGrayscaleTransform gray) {
        generatedSetterHelperImpl(gray, PROPERTY_QNAME[4], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTGrayscaleTransform insertNewGray(int i) {
        CTGrayscaleTransform target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[4], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTGrayscaleTransform addNewGray() {
        CTGrayscaleTransform target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void removeGray(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public List<CTPositiveFixedPercentage> getAlphaList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda118
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTHslColorImpl.this.getAlphaArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda119
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTHslColorImpl.this.setAlphaArray(((Integer) obj).intValue(), (CTPositiveFixedPercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda120
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTHslColorImpl.this.insertNewAlpha(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda121
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTHslColorImpl.this.removeAlpha(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda122
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTHslColorImpl.this.sizeOfAlphaArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPositiveFixedPercentage[] getAlphaArray() {
        return (CTPositiveFixedPercentage[]) getXmlObjectArray(PROPERTY_QNAME[5], new CTPositiveFixedPercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public int sizeOfAlphaArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[5]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void setAlphaArray(CTPositiveFixedPercentage[] alphaArray) {
        check_orphaned();
        arraySetterHelper(alphaArray, PROPERTY_QNAME[5]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void setAlphaArray(int i, CTPositiveFixedPercentage alpha) {
        generatedSetterHelperImpl(alpha, PROPERTY_QNAME[5], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPositiveFixedPercentage insertNewAlpha(int i) {
        CTPositiveFixedPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPositiveFixedPercentage) get_store().insert_element_user(PROPERTY_QNAME[5], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPositiveFixedPercentage addNewAlpha() {
        CTPositiveFixedPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPositiveFixedPercentage) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void removeAlpha(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public List<CTFixedPercentage> getAlphaOffList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda46
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTHslColorImpl.this.getAlphaOffArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda47
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTHslColorImpl.this.setAlphaOffArray(((Integer) obj).intValue(), (CTFixedPercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda48
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTHslColorImpl.this.insertNewAlphaOff(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda49
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTHslColorImpl.this.removeAlphaOff(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda50
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTHslColorImpl.this.sizeOfAlphaOffArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTFixedPercentage[] getAlphaOffArray() {
        return (CTFixedPercentage[]) getXmlObjectArray(PROPERTY_QNAME[6], new CTFixedPercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public int sizeOfAlphaOffArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[6]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void setAlphaOffArray(CTFixedPercentage[] alphaOffArray) {
        check_orphaned();
        arraySetterHelper(alphaOffArray, PROPERTY_QNAME[6]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void setAlphaOffArray(int i, CTFixedPercentage alphaOff) {
        generatedSetterHelperImpl(alphaOff, PROPERTY_QNAME[6], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTFixedPercentage insertNewAlphaOff(int i) {
        CTFixedPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFixedPercentage) get_store().insert_element_user(PROPERTY_QNAME[6], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTFixedPercentage addNewAlphaOff() {
        CTFixedPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFixedPercentage) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void removeAlphaOff(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public List<CTPositivePercentage> getAlphaModList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda34
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTHslColorImpl.this.getAlphaModArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda35
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTHslColorImpl.this.setAlphaModArray(((Integer) obj).intValue(), (CTPositivePercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda36
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTHslColorImpl.this.insertNewAlphaMod(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda37
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTHslColorImpl.this.removeAlphaMod(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda38
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTHslColorImpl.this.sizeOfAlphaModArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPositivePercentage[] getAlphaModArray() {
        return (CTPositivePercentage[]) getXmlObjectArray(PROPERTY_QNAME[7], new CTPositivePercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public int sizeOfAlphaModArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[7]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void setAlphaModArray(CTPositivePercentage[] alphaModArray) {
        check_orphaned();
        arraySetterHelper(alphaModArray, PROPERTY_QNAME[7]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void setAlphaModArray(int i, CTPositivePercentage alphaMod) {
        generatedSetterHelperImpl(alphaMod, PROPERTY_QNAME[7], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPositivePercentage insertNewAlphaMod(int i) {
        CTPositivePercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPositivePercentage) get_store().insert_element_user(PROPERTY_QNAME[7], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPositivePercentage addNewAlphaMod() {
        CTPositivePercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPositivePercentage) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void removeAlphaMod(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public List<CTPositiveFixedAngle> getHueList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda11
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTHslColorImpl.this.getHueArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda22
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTHslColorImpl.this.setHueArray(((Integer) obj).intValue(), (CTPositiveFixedAngle) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda33
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTHslColorImpl.this.insertNewHue(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda44
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTHslColorImpl.this.removeHue(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda45
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTHslColorImpl.this.sizeOfHueArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPositiveFixedAngle[] getHueArray() {
        return (CTPositiveFixedAngle[]) getXmlObjectArray(PROPERTY_QNAME[8], new CTPositiveFixedAngle[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public int sizeOfHueArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[8]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void setHueArray(CTPositiveFixedAngle[] hueArray) {
        check_orphaned();
        arraySetterHelper(hueArray, PROPERTY_QNAME[8]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void setHueArray(int i, CTPositiveFixedAngle hue) {
        generatedSetterHelperImpl(hue, PROPERTY_QNAME[8], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPositiveFixedAngle insertNewHue(int i) {
        CTPositiveFixedAngle target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPositiveFixedAngle) get_store().insert_element_user(PROPERTY_QNAME[8], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPositiveFixedAngle addNewHue() {
        CTPositiveFixedAngle target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPositiveFixedAngle) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void removeHue(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public List<CTAngle> getHueOffList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda6
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTHslColorImpl.this.getHueOffArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda7
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTHslColorImpl.this.setHueOffArray(((Integer) obj).intValue(), (CTAngle) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda8
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTHslColorImpl.this.insertNewHueOff(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda9
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTHslColorImpl.this.removeHueOff(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda10
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTHslColorImpl.this.sizeOfHueOffArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTAngle[] getHueOffArray() {
        return getXmlObjectArray(PROPERTY_QNAME[9], (XmlObject[]) new CTAngle[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public int sizeOfHueOffArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[9]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void setHueOffArray(CTAngle[] hueOffArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) hueOffArray, PROPERTY_QNAME[9]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void setHueOffArray(int i, CTAngle hueOff) {
        generatedSetterHelperImpl(hueOff, PROPERTY_QNAME[9], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTAngle insertNewHueOff(int i) {
        CTAngle target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[9], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTAngle addNewHueOff() {
        CTAngle target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void removeHueOff(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public List<CTPositivePercentage> getHueModList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda74
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTHslColorImpl.this.getHueModArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda75
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTHslColorImpl.this.setHueModArray(((Integer) obj).intValue(), (CTPositivePercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda76
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTHslColorImpl.this.insertNewHueMod(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda77
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTHslColorImpl.this.removeHueMod(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda78
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTHslColorImpl.this.sizeOfHueModArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPositivePercentage[] getHueModArray() {
        return (CTPositivePercentage[]) getXmlObjectArray(PROPERTY_QNAME[10], new CTPositivePercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public int sizeOfHueModArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[10]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void setHueModArray(CTPositivePercentage[] hueModArray) {
        check_orphaned();
        arraySetterHelper(hueModArray, PROPERTY_QNAME[10]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void setHueModArray(int i, CTPositivePercentage hueMod) {
        generatedSetterHelperImpl(hueMod, PROPERTY_QNAME[10], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPositivePercentage insertNewHueMod(int i) {
        CTPositivePercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPositivePercentage) get_store().insert_element_user(PROPERTY_QNAME[10], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPositivePercentage addNewHueMod() {
        CTPositivePercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPositivePercentage) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void removeHueMod(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public List<CTPercentage> getSatList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda23
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTHslColorImpl.this.getSatArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda24
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTHslColorImpl.this.setSatArray(((Integer) obj).intValue(), (CTPercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda25
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTHslColorImpl.this.insertNewSat(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda26
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTHslColorImpl.this.removeSat(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda27
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTHslColorImpl.this.sizeOfSatArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPercentage[] getSatArray() {
        return (CTPercentage[]) getXmlObjectArray(PROPERTY_QNAME[11], new CTPercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public int sizeOfSatArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[11]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void setSatArray(CTPercentage[] satArray) {
        check_orphaned();
        arraySetterHelper(satArray, PROPERTY_QNAME[11]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void setSatArray(int i, CTPercentage sat) {
        generatedSetterHelperImpl(sat, PROPERTY_QNAME[11], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPercentage insertNewSat(int i) {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().insert_element_user(PROPERTY_QNAME[11], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPercentage addNewSat() {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void removeSat(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public List<CTPercentage> getSatOffList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda123
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTHslColorImpl.this.getSatOffArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda124
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTHslColorImpl.this.setSatOffArray(((Integer) obj).intValue(), (CTPercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda125
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTHslColorImpl.this.insertNewSatOff(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda126
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTHslColorImpl.this.removeSatOff(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda127
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTHslColorImpl.this.sizeOfSatOffArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPercentage[] getSatOffArray() {
        return (CTPercentage[]) getXmlObjectArray(PROPERTY_QNAME[12], new CTPercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public int sizeOfSatOffArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[12]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void setSatOffArray(CTPercentage[] satOffArray) {
        check_orphaned();
        arraySetterHelper(satOffArray, PROPERTY_QNAME[12]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void setSatOffArray(int i, CTPercentage satOff) {
        generatedSetterHelperImpl(satOff, PROPERTY_QNAME[12], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPercentage insertNewSatOff(int i) {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().insert_element_user(PROPERTY_QNAME[12], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPercentage addNewSatOff() {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void removeSatOff(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public List<CTPercentage> getSatModList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda63
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTHslColorImpl.this.getSatModArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda64
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTHslColorImpl.this.setSatModArray(((Integer) obj).intValue(), (CTPercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda65
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTHslColorImpl.this.insertNewSatMod(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda66
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTHslColorImpl.this.removeSatMod(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda67
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTHslColorImpl.this.sizeOfSatModArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPercentage[] getSatModArray() {
        return (CTPercentage[]) getXmlObjectArray(PROPERTY_QNAME[13], new CTPercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public int sizeOfSatModArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[13]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void setSatModArray(CTPercentage[] satModArray) {
        check_orphaned();
        arraySetterHelper(satModArray, PROPERTY_QNAME[13]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void setSatModArray(int i, CTPercentage satMod) {
        generatedSetterHelperImpl(satMod, PROPERTY_QNAME[13], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPercentage insertNewSatMod(int i) {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().insert_element_user(PROPERTY_QNAME[13], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPercentage addNewSatMod() {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void removeSatMod(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public List<CTPercentage> getLumList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda134
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTHslColorImpl.this.getLumArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda135
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTHslColorImpl.this.setLumArray(((Integer) obj).intValue(), (CTPercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda136
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTHslColorImpl.this.insertNewLum(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda137
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTHslColorImpl.this.removeLum(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda138
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTHslColorImpl.this.sizeOfLumArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPercentage[] getLumArray() {
        return (CTPercentage[]) getXmlObjectArray(PROPERTY_QNAME[14], new CTPercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public int sizeOfLumArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[14]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void setLumArray(CTPercentage[] lumArray) {
        check_orphaned();
        arraySetterHelper(lumArray, PROPERTY_QNAME[14]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void setLumArray(int i, CTPercentage lum) {
        generatedSetterHelperImpl(lum, PROPERTY_QNAME[14], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPercentage insertNewLum(int i) {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().insert_element_user(PROPERTY_QNAME[14], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPercentage addNewLum() {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().add_element_user(PROPERTY_QNAME[14]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void removeLum(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[14], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public List<CTPercentage> getLumOffList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda112
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTHslColorImpl.this.getLumOffArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda113
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTHslColorImpl.this.setLumOffArray(((Integer) obj).intValue(), (CTPercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda114
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTHslColorImpl.this.insertNewLumOff(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda115
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTHslColorImpl.this.removeLumOff(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda116
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTHslColorImpl.this.sizeOfLumOffArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPercentage[] getLumOffArray() {
        return (CTPercentage[]) getXmlObjectArray(PROPERTY_QNAME[15], new CTPercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public int sizeOfLumOffArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[15]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void setLumOffArray(CTPercentage[] lumOffArray) {
        check_orphaned();
        arraySetterHelper(lumOffArray, PROPERTY_QNAME[15]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void setLumOffArray(int i, CTPercentage lumOff) {
        generatedSetterHelperImpl(lumOff, PROPERTY_QNAME[15], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPercentage insertNewLumOff(int i) {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().insert_element_user(PROPERTY_QNAME[15], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPercentage addNewLumOff() {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().add_element_user(PROPERTY_QNAME[15]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void removeLumOff(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[15], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public List<CTPercentage> getLumModList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTHslColorImpl.this.getLumModArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTHslColorImpl.this.setLumModArray(((Integer) obj).intValue(), (CTPercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTHslColorImpl.this.insertNewLumMod(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTHslColorImpl.this.removeLumMod(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda5
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTHslColorImpl.this.sizeOfLumModArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPercentage[] getLumModArray() {
        return (CTPercentage[]) getXmlObjectArray(PROPERTY_QNAME[16], new CTPercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public int sizeOfLumModArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[16]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void setLumModArray(CTPercentage[] lumModArray) {
        check_orphaned();
        arraySetterHelper(lumModArray, PROPERTY_QNAME[16]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void setLumModArray(int i, CTPercentage lumMod) {
        generatedSetterHelperImpl(lumMod, PROPERTY_QNAME[16], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPercentage insertNewLumMod(int i) {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().insert_element_user(PROPERTY_QNAME[16], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPercentage addNewLumMod() {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().add_element_user(PROPERTY_QNAME[16]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void removeLumMod(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[16], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public List<CTPercentage> getRedList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda129
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTHslColorImpl.this.getRedArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda130
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTHslColorImpl.this.setRedArray(((Integer) obj).intValue(), (CTPercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda131
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTHslColorImpl.this.insertNewRed(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda132
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTHslColorImpl.this.removeRed(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda133
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTHslColorImpl.this.sizeOfRedArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPercentage[] getRedArray() {
        return (CTPercentage[]) getXmlObjectArray(PROPERTY_QNAME[17], new CTPercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public int sizeOfRedArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[17]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void setRedArray(CTPercentage[] redArray) {
        check_orphaned();
        arraySetterHelper(redArray, PROPERTY_QNAME[17]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void setRedArray(int i, CTPercentage red) {
        generatedSetterHelperImpl(red, PROPERTY_QNAME[17], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPercentage insertNewRed(int i) {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().insert_element_user(PROPERTY_QNAME[17], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPercentage addNewRed() {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().add_element_user(PROPERTY_QNAME[17]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void removeRed(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[17], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public List<CTPercentage> getRedOffList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTHslColorImpl.this.getRedOffArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda51
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTHslColorImpl.this.setRedOffArray(((Integer) obj).intValue(), (CTPercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda62
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTHslColorImpl.this.insertNewRedOff(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda73
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTHslColorImpl.this.removeRedOff(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda84
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTHslColorImpl.this.sizeOfRedOffArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPercentage[] getRedOffArray() {
        return (CTPercentage[]) getXmlObjectArray(PROPERTY_QNAME[18], new CTPercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public int sizeOfRedOffArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[18]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void setRedOffArray(CTPercentage[] redOffArray) {
        check_orphaned();
        arraySetterHelper(redOffArray, PROPERTY_QNAME[18]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void setRedOffArray(int i, CTPercentage redOff) {
        generatedSetterHelperImpl(redOff, PROPERTY_QNAME[18], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPercentage insertNewRedOff(int i) {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().insert_element_user(PROPERTY_QNAME[18], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPercentage addNewRedOff() {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().add_element_user(PROPERTY_QNAME[18]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void removeRedOff(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[18], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public List<CTPercentage> getRedModList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda12
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTHslColorImpl.this.getRedModArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda13
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTHslColorImpl.this.setRedModArray(((Integer) obj).intValue(), (CTPercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda14
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTHslColorImpl.this.insertNewRedMod(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda15
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTHslColorImpl.this.removeRedMod(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda16
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTHslColorImpl.this.sizeOfRedModArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPercentage[] getRedModArray() {
        return (CTPercentage[]) getXmlObjectArray(PROPERTY_QNAME[19], new CTPercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public int sizeOfRedModArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[19]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void setRedModArray(CTPercentage[] redModArray) {
        check_orphaned();
        arraySetterHelper(redModArray, PROPERTY_QNAME[19]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void setRedModArray(int i, CTPercentage redMod) {
        generatedSetterHelperImpl(redMod, PROPERTY_QNAME[19], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPercentage insertNewRedMod(int i) {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().insert_element_user(PROPERTY_QNAME[19], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPercentage addNewRedMod() {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().add_element_user(PROPERTY_QNAME[19]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void removeRedMod(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[19], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public List<CTPercentage> getGreenList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda39
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTHslColorImpl.this.getGreenArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda40
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTHslColorImpl.this.setGreenArray(((Integer) obj).intValue(), (CTPercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda41
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTHslColorImpl.this.insertNewGreen(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda42
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTHslColorImpl.this.removeGreen(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda43
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTHslColorImpl.this.sizeOfGreenArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPercentage[] getGreenArray() {
        return (CTPercentage[]) getXmlObjectArray(PROPERTY_QNAME[20], new CTPercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public int sizeOfGreenArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[20]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void setGreenArray(CTPercentage[] greenArray) {
        check_orphaned();
        arraySetterHelper(greenArray, PROPERTY_QNAME[20]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void setGreenArray(int i, CTPercentage green) {
        generatedSetterHelperImpl(green, PROPERTY_QNAME[20], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPercentage insertNewGreen(int i) {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().insert_element_user(PROPERTY_QNAME[20], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPercentage addNewGreen() {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().add_element_user(PROPERTY_QNAME[20]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void removeGreen(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[20], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public List<CTPercentage> getGreenOffList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda52
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTHslColorImpl.this.getGreenOffArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda53
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTHslColorImpl.this.setGreenOffArray(((Integer) obj).intValue(), (CTPercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda54
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTHslColorImpl.this.insertNewGreenOff(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda55
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTHslColorImpl.this.removeGreenOff(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda56
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTHslColorImpl.this.sizeOfGreenOffArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPercentage[] getGreenOffArray() {
        return (CTPercentage[]) getXmlObjectArray(PROPERTY_QNAME[21], new CTPercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public int sizeOfGreenOffArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[21]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void setGreenOffArray(CTPercentage[] greenOffArray) {
        check_orphaned();
        arraySetterHelper(greenOffArray, PROPERTY_QNAME[21]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void setGreenOffArray(int i, CTPercentage greenOff) {
        generatedSetterHelperImpl(greenOff, PROPERTY_QNAME[21], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPercentage insertNewGreenOff(int i) {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().insert_element_user(PROPERTY_QNAME[21], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPercentage addNewGreenOff() {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().add_element_user(PROPERTY_QNAME[21]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void removeGreenOff(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[21], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public List<CTPercentage> getGreenModList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda79
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTHslColorImpl.this.getGreenModArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda80
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTHslColorImpl.this.setGreenModArray(((Integer) obj).intValue(), (CTPercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda81
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTHslColorImpl.this.insertNewGreenMod(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda82
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTHslColorImpl.this.removeGreenMod(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda83
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTHslColorImpl.this.sizeOfGreenModArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPercentage[] getGreenModArray() {
        return (CTPercentage[]) getXmlObjectArray(PROPERTY_QNAME[22], new CTPercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public int sizeOfGreenModArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[22]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void setGreenModArray(CTPercentage[] greenModArray) {
        check_orphaned();
        arraySetterHelper(greenModArray, PROPERTY_QNAME[22]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void setGreenModArray(int i, CTPercentage greenMod) {
        generatedSetterHelperImpl(greenMod, PROPERTY_QNAME[22], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPercentage insertNewGreenMod(int i) {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().insert_element_user(PROPERTY_QNAME[22], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPercentage addNewGreenMod() {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().add_element_user(PROPERTY_QNAME[22]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void removeGreenMod(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[22], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public List<CTPercentage> getBlueList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda90
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTHslColorImpl.this.getBlueArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda91
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTHslColorImpl.this.setBlueArray(((Integer) obj).intValue(), (CTPercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda92
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTHslColorImpl.this.insertNewBlue(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda93
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTHslColorImpl.this.removeBlue(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda94
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTHslColorImpl.this.sizeOfBlueArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPercentage[] getBlueArray() {
        return (CTPercentage[]) getXmlObjectArray(PROPERTY_QNAME[23], new CTPercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public int sizeOfBlueArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[23]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void setBlueArray(CTPercentage[] blueArray) {
        check_orphaned();
        arraySetterHelper(blueArray, PROPERTY_QNAME[23]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void setBlueArray(int i, CTPercentage blue) {
        generatedSetterHelperImpl(blue, PROPERTY_QNAME[23], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPercentage insertNewBlue(int i) {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().insert_element_user(PROPERTY_QNAME[23], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPercentage addNewBlue() {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().add_element_user(PROPERTY_QNAME[23]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void removeBlue(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[23], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public List<CTPercentage> getBlueOffList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda107
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTHslColorImpl.this.getBlueOffArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda108
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTHslColorImpl.this.setBlueOffArray(((Integer) obj).intValue(), (CTPercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda109
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTHslColorImpl.this.insertNewBlueOff(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda110
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTHslColorImpl.this.removeBlueOff(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda111
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTHslColorImpl.this.sizeOfBlueOffArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPercentage[] getBlueOffArray() {
        return (CTPercentage[]) getXmlObjectArray(PROPERTY_QNAME[24], new CTPercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public int sizeOfBlueOffArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[24]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void setBlueOffArray(CTPercentage[] blueOffArray) {
        check_orphaned();
        arraySetterHelper(blueOffArray, PROPERTY_QNAME[24]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void setBlueOffArray(int i, CTPercentage blueOff) {
        generatedSetterHelperImpl(blueOff, PROPERTY_QNAME[24], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPercentage insertNewBlueOff(int i) {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().insert_element_user(PROPERTY_QNAME[24], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPercentage addNewBlueOff() {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().add_element_user(PROPERTY_QNAME[24]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void removeBlueOff(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[24], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public List<CTPercentage> getBlueModList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda57
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTHslColorImpl.this.getBlueModArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda58
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTHslColorImpl.this.setBlueModArray(((Integer) obj).intValue(), (CTPercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda59
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTHslColorImpl.this.insertNewBlueMod(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda60
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTHslColorImpl.this.removeBlueMod(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda61
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTHslColorImpl.this.sizeOfBlueModArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPercentage[] getBlueModArray() {
        return (CTPercentage[]) getXmlObjectArray(PROPERTY_QNAME[25], new CTPercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public int sizeOfBlueModArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[25]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void setBlueModArray(CTPercentage[] blueModArray) {
        check_orphaned();
        arraySetterHelper(blueModArray, PROPERTY_QNAME[25]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void setBlueModArray(int i, CTPercentage blueMod) {
        generatedSetterHelperImpl(blueMod, PROPERTY_QNAME[25], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPercentage insertNewBlueMod(int i) {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().insert_element_user(PROPERTY_QNAME[25], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTPercentage addNewBlueMod() {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().add_element_user(PROPERTY_QNAME[25]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void removeBlueMod(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[25], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public List<CTGammaTransform> getGammaList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda101
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTHslColorImpl.this.getGammaArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda102
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTHslColorImpl.this.setGammaArray(((Integer) obj).intValue(), (CTGammaTransform) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda103
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTHslColorImpl.this.insertNewGamma(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda104
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTHslColorImpl.this.removeGamma(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda105
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTHslColorImpl.this.sizeOfGammaArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTGammaTransform[] getGammaArray() {
        return getXmlObjectArray(PROPERTY_QNAME[26], (XmlObject[]) new CTGammaTransform[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public int sizeOfGammaArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[26]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void setGammaArray(CTGammaTransform[] gammaArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) gammaArray, PROPERTY_QNAME[26]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void setGammaArray(int i, CTGammaTransform gamma) {
        generatedSetterHelperImpl(gamma, PROPERTY_QNAME[26], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTGammaTransform insertNewGamma(int i) {
        CTGammaTransform target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[26], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTGammaTransform addNewGamma() {
        CTGammaTransform target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[26]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void removeGamma(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[26], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public List<CTInverseGammaTransform> getInvGammaList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda17
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTHslColorImpl.this.getInvGammaArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda18
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTHslColorImpl.this.setInvGammaArray(((Integer) obj).intValue(), (CTInverseGammaTransform) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda19
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTHslColorImpl.this.insertNewInvGamma(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda20
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTHslColorImpl.this.removeInvGamma(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTHslColorImpl$$ExternalSyntheticLambda21
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTHslColorImpl.this.sizeOfInvGammaArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTInverseGammaTransform[] getInvGammaArray() {
        return getXmlObjectArray(PROPERTY_QNAME[27], (XmlObject[]) new CTInverseGammaTransform[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public int sizeOfInvGammaArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[27]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void setInvGammaArray(CTInverseGammaTransform[] invGammaArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) invGammaArray, PROPERTY_QNAME[27]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void setInvGammaArray(int i, CTInverseGammaTransform invGamma) {
        generatedSetterHelperImpl(invGamma, PROPERTY_QNAME[27], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTInverseGammaTransform insertNewInvGamma(int i) {
        CTInverseGammaTransform target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[27], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public CTInverseGammaTransform addNewInvGamma() {
        CTInverseGammaTransform target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[27]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void removeInvGamma(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[27], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public int getHue2() {
        int intValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[28]);
            intValue = target == null ? 0 : target.getIntValue();
        }
        return intValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public STPositiveFixedAngle xgetHue2() {
        STPositiveFixedAngle target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STPositiveFixedAngle) get_store().find_attribute_user(PROPERTY_QNAME[28]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void setHue2(int hue2) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[28]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[28]);
            }
            target.setIntValue(hue2);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void xsetHue2(STPositiveFixedAngle hue2) {
        synchronized (monitor()) {
            check_orphaned();
            STPositiveFixedAngle target = (STPositiveFixedAngle) get_store().find_attribute_user(PROPERTY_QNAME[28]);
            if (target == null) {
                target = (STPositiveFixedAngle) get_store().add_attribute_user(PROPERTY_QNAME[28]);
            }
            target.set(hue2);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public Object getSat2() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[29]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public STPercentage xgetSat2() {
        STPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STPercentage) get_store().find_attribute_user(PROPERTY_QNAME[29]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void setSat2(Object sat2) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[29]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[29]);
            }
            target.setObjectValue(sat2);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void xsetSat2(STPercentage sat2) {
        synchronized (monitor()) {
            check_orphaned();
            STPercentage target = (STPercentage) get_store().find_attribute_user(PROPERTY_QNAME[29]);
            if (target == null) {
                target = (STPercentage) get_store().add_attribute_user(PROPERTY_QNAME[29]);
            }
            target.set(sat2);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public Object getLum2() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[30]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public STPercentage xgetLum2() {
        STPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STPercentage) get_store().find_attribute_user(PROPERTY_QNAME[30]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void setLum2(Object lum2) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[30]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[30]);
            }
            target.setObjectValue(lum2);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor
    public void xsetLum2(STPercentage lum2) {
        synchronized (monitor()) {
            check_orphaned();
            STPercentage target = (STPercentage) get_store().find_attribute_user(PROPERTY_QNAME[30]);
            if (target == null) {
                target = (STPercentage) get_store().add_attribute_user(PROPERTY_QNAME[30]);
            }
            target.set(lum2);
        }
    }
}
