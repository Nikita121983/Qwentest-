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
import org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor;
import org.openxmlformats.schemas.drawingml.x2006.main.STSchemeColorVal;

/* loaded from: classes11.dex */
public class CTSchemeColorImpl extends XmlComplexContentImpl implements CTSchemeColor {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "tint"), new QName(XSSFRelation.NS_DRAWINGML, "shade"), new QName(XSSFRelation.NS_DRAWINGML, "comp"), new QName(XSSFRelation.NS_DRAWINGML, "inv"), new QName(XSSFRelation.NS_DRAWINGML, "gray"), new QName(XSSFRelation.NS_DRAWINGML, "alpha"), new QName(XSSFRelation.NS_DRAWINGML, "alphaOff"), new QName(XSSFRelation.NS_DRAWINGML, "alphaMod"), new QName(XSSFRelation.NS_DRAWINGML, "hue"), new QName(XSSFRelation.NS_DRAWINGML, "hueOff"), new QName(XSSFRelation.NS_DRAWINGML, "hueMod"), new QName(XSSFRelation.NS_DRAWINGML, LocalePreferences.FirstDayOfWeek.SATURDAY), new QName(XSSFRelation.NS_DRAWINGML, "satOff"), new QName(XSSFRelation.NS_DRAWINGML, "satMod"), new QName(XSSFRelation.NS_DRAWINGML, "lum"), new QName(XSSFRelation.NS_DRAWINGML, "lumOff"), new QName(XSSFRelation.NS_DRAWINGML, "lumMod"), new QName(XSSFRelation.NS_DRAWINGML, "red"), new QName(XSSFRelation.NS_DRAWINGML, "redOff"), new QName(XSSFRelation.NS_DRAWINGML, "redMod"), new QName(XSSFRelation.NS_DRAWINGML, "green"), new QName(XSSFRelation.NS_DRAWINGML, "greenOff"), new QName(XSSFRelation.NS_DRAWINGML, "greenMod"), new QName(XSSFRelation.NS_DRAWINGML, "blue"), new QName(XSSFRelation.NS_DRAWINGML, "blueOff"), new QName(XSSFRelation.NS_DRAWINGML, "blueMod"), new QName(XSSFRelation.NS_DRAWINGML, "gamma"), new QName(XSSFRelation.NS_DRAWINGML, "invGamma"), new QName("", "val")};
    private static final long serialVersionUID = 1;

    public CTSchemeColorImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public List<CTPositiveFixedPercentage> getTintList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda112
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSchemeColorImpl.this.getTintArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda113
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTSchemeColorImpl.this.setTintArray(((Integer) obj).intValue(), (CTPositiveFixedPercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda114
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSchemeColorImpl.this.insertNewTint(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda115
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTSchemeColorImpl.this.removeTint(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda116
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTSchemeColorImpl.this.sizeOfTintArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPositiveFixedPercentage[] getTintArray() {
        return (CTPositiveFixedPercentage[]) getXmlObjectArray(PROPERTY_QNAME[0], new CTPositiveFixedPercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public int sizeOfTintArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void setTintArray(CTPositiveFixedPercentage[] tintArray) {
        check_orphaned();
        arraySetterHelper(tintArray, PROPERTY_QNAME[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void setTintArray(int i, CTPositiveFixedPercentage tint) {
        generatedSetterHelperImpl(tint, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPositiveFixedPercentage insertNewTint(int i) {
        CTPositiveFixedPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPositiveFixedPercentage) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPositiveFixedPercentage addNewTint() {
        CTPositiveFixedPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPositiveFixedPercentage) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void removeTint(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public List<CTPositiveFixedPercentage> getShadeList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda39
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSchemeColorImpl.this.getShadeArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda40
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTSchemeColorImpl.this.setShadeArray(((Integer) obj).intValue(), (CTPositiveFixedPercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda41
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSchemeColorImpl.this.insertNewShade(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda42
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTSchemeColorImpl.this.removeShade(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda43
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTSchemeColorImpl.this.sizeOfShadeArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPositiveFixedPercentage[] getShadeArray() {
        return (CTPositiveFixedPercentage[]) getXmlObjectArray(PROPERTY_QNAME[1], new CTPositiveFixedPercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public int sizeOfShadeArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void setShadeArray(CTPositiveFixedPercentage[] shadeArray) {
        check_orphaned();
        arraySetterHelper(shadeArray, PROPERTY_QNAME[1]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void setShadeArray(int i, CTPositiveFixedPercentage shade) {
        generatedSetterHelperImpl(shade, PROPERTY_QNAME[1], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPositiveFixedPercentage insertNewShade(int i) {
        CTPositiveFixedPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPositiveFixedPercentage) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPositiveFixedPercentage addNewShade() {
        CTPositiveFixedPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPositiveFixedPercentage) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void removeShade(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public List<CTComplementTransform> getCompList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda123
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSchemeColorImpl.this.getCompArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda124
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTSchemeColorImpl.this.setCompArray(((Integer) obj).intValue(), (CTComplementTransform) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda125
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSchemeColorImpl.this.insertNewComp(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda126
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTSchemeColorImpl.this.removeComp(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda127
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTSchemeColorImpl.this.sizeOfCompArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTComplementTransform[] getCompArray() {
        return (CTComplementTransform[]) getXmlObjectArray(PROPERTY_QNAME[2], new CTComplementTransform[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public int sizeOfCompArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void setCompArray(CTComplementTransform[] compArray) {
        check_orphaned();
        arraySetterHelper(compArray, PROPERTY_QNAME[2]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void setCompArray(int i, CTComplementTransform comp) {
        generatedSetterHelperImpl(comp, PROPERTY_QNAME[2], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTComplementTransform insertNewComp(int i) {
        CTComplementTransform target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTComplementTransform) get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTComplementTransform addNewComp() {
        CTComplementTransform target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTComplementTransform) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void removeComp(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public List<CTInverseTransform> getInvList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda85
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSchemeColorImpl.this.getInvArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda86
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTSchemeColorImpl.this.setInvArray(((Integer) obj).intValue(), (CTInverseTransform) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda87
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSchemeColorImpl.this.insertNewInv(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda88
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTSchemeColorImpl.this.removeInv(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda89
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTSchemeColorImpl.this.sizeOfInvArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTInverseTransform[] getInvArray() {
        return getXmlObjectArray(PROPERTY_QNAME[3], (XmlObject[]) new CTInverseTransform[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public int sizeOfInvArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void setInvArray(CTInverseTransform[] invArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) invArray, PROPERTY_QNAME[3]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void setInvArray(int i, CTInverseTransform inv) {
        generatedSetterHelperImpl(inv, PROPERTY_QNAME[3], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTInverseTransform insertNewInv(int i) {
        CTInverseTransform target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[3], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTInverseTransform addNewInv() {
        CTInverseTransform target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void removeInv(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public List<CTGrayscaleTransform> getGrayList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda95
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSchemeColorImpl.this.getGrayArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda106
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTSchemeColorImpl.this.setGrayArray(((Integer) obj).intValue(), (CTGrayscaleTransform) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda117
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSchemeColorImpl.this.insertNewGray(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda128
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTSchemeColorImpl.this.removeGray(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda139
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTSchemeColorImpl.this.sizeOfGrayArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTGrayscaleTransform[] getGrayArray() {
        return getXmlObjectArray(PROPERTY_QNAME[4], (XmlObject[]) new CTGrayscaleTransform[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public int sizeOfGrayArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[4]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void setGrayArray(CTGrayscaleTransform[] grayArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) grayArray, PROPERTY_QNAME[4]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void setGrayArray(int i, CTGrayscaleTransform gray) {
        generatedSetterHelperImpl(gray, PROPERTY_QNAME[4], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTGrayscaleTransform insertNewGray(int i) {
        CTGrayscaleTransform target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[4], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTGrayscaleTransform addNewGray() {
        CTGrayscaleTransform target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void removeGray(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public List<CTPositiveFixedPercentage> getAlphaList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda57
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSchemeColorImpl.this.getAlphaArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda58
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTSchemeColorImpl.this.setAlphaArray(((Integer) obj).intValue(), (CTPositiveFixedPercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda59
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSchemeColorImpl.this.insertNewAlpha(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda60
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTSchemeColorImpl.this.removeAlpha(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda61
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTSchemeColorImpl.this.sizeOfAlphaArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPositiveFixedPercentage[] getAlphaArray() {
        return (CTPositiveFixedPercentage[]) getXmlObjectArray(PROPERTY_QNAME[5], new CTPositiveFixedPercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public int sizeOfAlphaArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[5]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void setAlphaArray(CTPositiveFixedPercentage[] alphaArray) {
        check_orphaned();
        arraySetterHelper(alphaArray, PROPERTY_QNAME[5]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void setAlphaArray(int i, CTPositiveFixedPercentage alpha) {
        generatedSetterHelperImpl(alpha, PROPERTY_QNAME[5], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPositiveFixedPercentage insertNewAlpha(int i) {
        CTPositiveFixedPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPositiveFixedPercentage) get_store().insert_element_user(PROPERTY_QNAME[5], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPositiveFixedPercentage addNewAlpha() {
        CTPositiveFixedPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPositiveFixedPercentage) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void removeAlpha(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public List<CTFixedPercentage> getAlphaOffList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda17
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSchemeColorImpl.this.getAlphaOffArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda18
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTSchemeColorImpl.this.setAlphaOffArray(((Integer) obj).intValue(), (CTFixedPercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda19
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSchemeColorImpl.this.insertNewAlphaOff(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda20
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTSchemeColorImpl.this.removeAlphaOff(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda21
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTSchemeColorImpl.this.sizeOfAlphaOffArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTFixedPercentage[] getAlphaOffArray() {
        return (CTFixedPercentage[]) getXmlObjectArray(PROPERTY_QNAME[6], new CTFixedPercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public int sizeOfAlphaOffArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[6]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void setAlphaOffArray(CTFixedPercentage[] alphaOffArray) {
        check_orphaned();
        arraySetterHelper(alphaOffArray, PROPERTY_QNAME[6]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void setAlphaOffArray(int i, CTFixedPercentage alphaOff) {
        generatedSetterHelperImpl(alphaOff, PROPERTY_QNAME[6], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTFixedPercentage insertNewAlphaOff(int i) {
        CTFixedPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFixedPercentage) get_store().insert_element_user(PROPERTY_QNAME[6], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTFixedPercentage addNewAlphaOff() {
        CTFixedPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFixedPercentage) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void removeAlphaOff(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public List<CTPositivePercentage> getAlphaModList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda6
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSchemeColorImpl.this.getAlphaModArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda7
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTSchemeColorImpl.this.setAlphaModArray(((Integer) obj).intValue(), (CTPositivePercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda8
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSchemeColorImpl.this.insertNewAlphaMod(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda9
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTSchemeColorImpl.this.removeAlphaMod(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda10
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTSchemeColorImpl.this.sizeOfAlphaModArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPositivePercentage[] getAlphaModArray() {
        return (CTPositivePercentage[]) getXmlObjectArray(PROPERTY_QNAME[7], new CTPositivePercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public int sizeOfAlphaModArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[7]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void setAlphaModArray(CTPositivePercentage[] alphaModArray) {
        check_orphaned();
        arraySetterHelper(alphaModArray, PROPERTY_QNAME[7]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void setAlphaModArray(int i, CTPositivePercentage alphaMod) {
        generatedSetterHelperImpl(alphaMod, PROPERTY_QNAME[7], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPositivePercentage insertNewAlphaMod(int i) {
        CTPositivePercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPositivePercentage) get_store().insert_element_user(PROPERTY_QNAME[7], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPositivePercentage addNewAlphaMod() {
        CTPositivePercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPositivePercentage) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void removeAlphaMod(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public List<CTPositiveFixedAngle> getHueList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda118
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSchemeColorImpl.this.getHueArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda119
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTSchemeColorImpl.this.setHueArray(((Integer) obj).intValue(), (CTPositiveFixedAngle) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda120
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSchemeColorImpl.this.insertNewHue(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda121
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTSchemeColorImpl.this.removeHue(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda122
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTSchemeColorImpl.this.sizeOfHueArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPositiveFixedAngle[] getHueArray() {
        return (CTPositiveFixedAngle[]) getXmlObjectArray(PROPERTY_QNAME[8], new CTPositiveFixedAngle[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public int sizeOfHueArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[8]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void setHueArray(CTPositiveFixedAngle[] hueArray) {
        check_orphaned();
        arraySetterHelper(hueArray, PROPERTY_QNAME[8]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void setHueArray(int i, CTPositiveFixedAngle hue) {
        generatedSetterHelperImpl(hue, PROPERTY_QNAME[8], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPositiveFixedAngle insertNewHue(int i) {
        CTPositiveFixedAngle target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPositiveFixedAngle) get_store().insert_element_user(PROPERTY_QNAME[8], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPositiveFixedAngle addNewHue() {
        CTPositiveFixedAngle target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPositiveFixedAngle) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void removeHue(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public List<CTAngle> getHueOffList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSchemeColorImpl.this.getHueOffArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTSchemeColorImpl.this.setHueOffArray(((Integer) obj).intValue(), (CTAngle) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSchemeColorImpl.this.insertNewHueOff(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTSchemeColorImpl.this.removeHueOff(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda5
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTSchemeColorImpl.this.sizeOfHueOffArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTAngle[] getHueOffArray() {
        return getXmlObjectArray(PROPERTY_QNAME[9], (XmlObject[]) new CTAngle[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public int sizeOfHueOffArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[9]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void setHueOffArray(CTAngle[] hueOffArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) hueOffArray, PROPERTY_QNAME[9]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void setHueOffArray(int i, CTAngle hueOff) {
        generatedSetterHelperImpl(hueOff, PROPERTY_QNAME[9], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTAngle insertNewHueOff(int i) {
        CTAngle target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[9], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTAngle addNewHueOff() {
        CTAngle target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void removeHueOff(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public List<CTPositivePercentage> getHueModList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda96
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSchemeColorImpl.this.getHueModArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda97
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTSchemeColorImpl.this.setHueModArray(((Integer) obj).intValue(), (CTPositivePercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda98
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSchemeColorImpl.this.insertNewHueMod(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda99
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTSchemeColorImpl.this.removeHueMod(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda100
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTSchemeColorImpl.this.sizeOfHueModArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPositivePercentage[] getHueModArray() {
        return (CTPositivePercentage[]) getXmlObjectArray(PROPERTY_QNAME[10], new CTPositivePercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public int sizeOfHueModArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[10]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void setHueModArray(CTPositivePercentage[] hueModArray) {
        check_orphaned();
        arraySetterHelper(hueModArray, PROPERTY_QNAME[10]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void setHueModArray(int i, CTPositivePercentage hueMod) {
        generatedSetterHelperImpl(hueMod, PROPERTY_QNAME[10], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPositivePercentage insertNewHueMod(int i) {
        CTPositivePercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPositivePercentage) get_store().insert_element_user(PROPERTY_QNAME[10], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPositivePercentage addNewHueMod() {
        CTPositivePercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPositivePercentage) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void removeHueMod(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public List<CTPercentage> getSatList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda12
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSchemeColorImpl.this.getSatArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda13
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTSchemeColorImpl.this.setSatArray(((Integer) obj).intValue(), (CTPercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda14
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSchemeColorImpl.this.insertNewSat(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda15
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTSchemeColorImpl.this.removeSat(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda16
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTSchemeColorImpl.this.sizeOfSatArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPercentage[] getSatArray() {
        return (CTPercentage[]) getXmlObjectArray(PROPERTY_QNAME[11], new CTPercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public int sizeOfSatArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[11]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void setSatArray(CTPercentage[] satArray) {
        check_orphaned();
        arraySetterHelper(satArray, PROPERTY_QNAME[11]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void setSatArray(int i, CTPercentage sat) {
        generatedSetterHelperImpl(sat, PROPERTY_QNAME[11], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPercentage insertNewSat(int i) {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().insert_element_user(PROPERTY_QNAME[11], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPercentage addNewSat() {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void removeSat(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public List<CTPercentage> getSatOffList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda90
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSchemeColorImpl.this.getSatOffArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda91
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTSchemeColorImpl.this.setSatOffArray(((Integer) obj).intValue(), (CTPercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda92
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSchemeColorImpl.this.insertNewSatOff(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda93
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTSchemeColorImpl.this.removeSatOff(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda94
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTSchemeColorImpl.this.sizeOfSatOffArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPercentage[] getSatOffArray() {
        return (CTPercentage[]) getXmlObjectArray(PROPERTY_QNAME[12], new CTPercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public int sizeOfSatOffArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[12]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void setSatOffArray(CTPercentage[] satOffArray) {
        check_orphaned();
        arraySetterHelper(satOffArray, PROPERTY_QNAME[12]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void setSatOffArray(int i, CTPercentage satOff) {
        generatedSetterHelperImpl(satOff, PROPERTY_QNAME[12], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPercentage insertNewSatOff(int i) {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().insert_element_user(PROPERTY_QNAME[12], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPercentage addNewSatOff() {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void removeSatOff(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public List<CTPercentage> getSatModList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda68
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSchemeColorImpl.this.getSatModArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda69
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTSchemeColorImpl.this.setSatModArray(((Integer) obj).intValue(), (CTPercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda70
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSchemeColorImpl.this.insertNewSatMod(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda71
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTSchemeColorImpl.this.removeSatMod(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda72
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTSchemeColorImpl.this.sizeOfSatModArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPercentage[] getSatModArray() {
        return (CTPercentage[]) getXmlObjectArray(PROPERTY_QNAME[13], new CTPercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public int sizeOfSatModArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[13]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void setSatModArray(CTPercentage[] satModArray) {
        check_orphaned();
        arraySetterHelper(satModArray, PROPERTY_QNAME[13]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void setSatModArray(int i, CTPercentage satMod) {
        generatedSetterHelperImpl(satMod, PROPERTY_QNAME[13], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPercentage insertNewSatMod(int i) {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().insert_element_user(PROPERTY_QNAME[13], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPercentage addNewSatMod() {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void removeSatMod(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public List<CTPercentage> getLumList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSchemeColorImpl.this.getLumArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda51
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTSchemeColorImpl.this.setLumArray(((Integer) obj).intValue(), (CTPercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda62
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSchemeColorImpl.this.insertNewLum(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda73
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTSchemeColorImpl.this.removeLum(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda84
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTSchemeColorImpl.this.sizeOfLumArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPercentage[] getLumArray() {
        return (CTPercentage[]) getXmlObjectArray(PROPERTY_QNAME[14], new CTPercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public int sizeOfLumArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[14]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void setLumArray(CTPercentage[] lumArray) {
        check_orphaned();
        arraySetterHelper(lumArray, PROPERTY_QNAME[14]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void setLumArray(int i, CTPercentage lum) {
        generatedSetterHelperImpl(lum, PROPERTY_QNAME[14], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPercentage insertNewLum(int i) {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().insert_element_user(PROPERTY_QNAME[14], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPercentage addNewLum() {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().add_element_user(PROPERTY_QNAME[14]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void removeLum(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[14], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public List<CTPercentage> getLumOffList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda107
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSchemeColorImpl.this.getLumOffArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda108
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTSchemeColorImpl.this.setLumOffArray(((Integer) obj).intValue(), (CTPercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda109
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSchemeColorImpl.this.insertNewLumOff(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda110
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTSchemeColorImpl.this.removeLumOff(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda111
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTSchemeColorImpl.this.sizeOfLumOffArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPercentage[] getLumOffArray() {
        return (CTPercentage[]) getXmlObjectArray(PROPERTY_QNAME[15], new CTPercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public int sizeOfLumOffArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[15]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void setLumOffArray(CTPercentage[] lumOffArray) {
        check_orphaned();
        arraySetterHelper(lumOffArray, PROPERTY_QNAME[15]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void setLumOffArray(int i, CTPercentage lumOff) {
        generatedSetterHelperImpl(lumOff, PROPERTY_QNAME[15], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPercentage insertNewLumOff(int i) {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().insert_element_user(PROPERTY_QNAME[15], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPercentage addNewLumOff() {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().add_element_user(PROPERTY_QNAME[15]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void removeLumOff(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[15], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public List<CTPercentage> getLumModList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda74
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSchemeColorImpl.this.getLumModArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda75
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTSchemeColorImpl.this.setLumModArray(((Integer) obj).intValue(), (CTPercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda76
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSchemeColorImpl.this.insertNewLumMod(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda77
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTSchemeColorImpl.this.removeLumMod(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda78
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTSchemeColorImpl.this.sizeOfLumModArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPercentage[] getLumModArray() {
        return (CTPercentage[]) getXmlObjectArray(PROPERTY_QNAME[16], new CTPercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public int sizeOfLumModArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[16]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void setLumModArray(CTPercentage[] lumModArray) {
        check_orphaned();
        arraySetterHelper(lumModArray, PROPERTY_QNAME[16]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void setLumModArray(int i, CTPercentage lumMod) {
        generatedSetterHelperImpl(lumMod, PROPERTY_QNAME[16], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPercentage insertNewLumMod(int i) {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().insert_element_user(PROPERTY_QNAME[16], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPercentage addNewLumMod() {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().add_element_user(PROPERTY_QNAME[16]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void removeLumMod(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[16], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public List<CTPercentage> getRedList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda79
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSchemeColorImpl.this.getRedArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda80
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTSchemeColorImpl.this.setRedArray(((Integer) obj).intValue(), (CTPercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda81
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSchemeColorImpl.this.insertNewRed(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda82
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTSchemeColorImpl.this.removeRed(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda83
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTSchemeColorImpl.this.sizeOfRedArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPercentage[] getRedArray() {
        return (CTPercentage[]) getXmlObjectArray(PROPERTY_QNAME[17], new CTPercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public int sizeOfRedArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[17]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void setRedArray(CTPercentage[] redArray) {
        check_orphaned();
        arraySetterHelper(redArray, PROPERTY_QNAME[17]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void setRedArray(int i, CTPercentage red) {
        generatedSetterHelperImpl(red, PROPERTY_QNAME[17], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPercentage insertNewRed(int i) {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().insert_element_user(PROPERTY_QNAME[17], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPercentage addNewRed() {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().add_element_user(PROPERTY_QNAME[17]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void removeRed(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[17], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public List<CTPercentage> getRedOffList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda11
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSchemeColorImpl.this.getRedOffArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda22
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTSchemeColorImpl.this.setRedOffArray(((Integer) obj).intValue(), (CTPercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda33
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSchemeColorImpl.this.insertNewRedOff(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda44
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTSchemeColorImpl.this.removeRedOff(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda45
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTSchemeColorImpl.this.sizeOfRedOffArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPercentage[] getRedOffArray() {
        return (CTPercentage[]) getXmlObjectArray(PROPERTY_QNAME[18], new CTPercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public int sizeOfRedOffArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[18]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void setRedOffArray(CTPercentage[] redOffArray) {
        check_orphaned();
        arraySetterHelper(redOffArray, PROPERTY_QNAME[18]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void setRedOffArray(int i, CTPercentage redOff) {
        generatedSetterHelperImpl(redOff, PROPERTY_QNAME[18], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPercentage insertNewRedOff(int i) {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().insert_element_user(PROPERTY_QNAME[18], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPercentage addNewRedOff() {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().add_element_user(PROPERTY_QNAME[18]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void removeRedOff(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[18], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public List<CTPercentage> getRedModList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda28
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSchemeColorImpl.this.getRedModArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda29
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTSchemeColorImpl.this.setRedModArray(((Integer) obj).intValue(), (CTPercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda30
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSchemeColorImpl.this.insertNewRedMod(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda31
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTSchemeColorImpl.this.removeRedMod(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda32
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTSchemeColorImpl.this.sizeOfRedModArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPercentage[] getRedModArray() {
        return (CTPercentage[]) getXmlObjectArray(PROPERTY_QNAME[19], new CTPercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public int sizeOfRedModArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[19]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void setRedModArray(CTPercentage[] redModArray) {
        check_orphaned();
        arraySetterHelper(redModArray, PROPERTY_QNAME[19]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void setRedModArray(int i, CTPercentage redMod) {
        generatedSetterHelperImpl(redMod, PROPERTY_QNAME[19], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPercentage insertNewRedMod(int i) {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().insert_element_user(PROPERTY_QNAME[19], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPercentage addNewRedMod() {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().add_element_user(PROPERTY_QNAME[19]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void removeRedMod(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[19], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public List<CTPercentage> getGreenList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda129
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSchemeColorImpl.this.getGreenArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda130
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTSchemeColorImpl.this.setGreenArray(((Integer) obj).intValue(), (CTPercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda131
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSchemeColorImpl.this.insertNewGreen(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda132
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTSchemeColorImpl.this.removeGreen(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda133
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTSchemeColorImpl.this.sizeOfGreenArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPercentage[] getGreenArray() {
        return (CTPercentage[]) getXmlObjectArray(PROPERTY_QNAME[20], new CTPercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public int sizeOfGreenArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[20]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void setGreenArray(CTPercentage[] greenArray) {
        check_orphaned();
        arraySetterHelper(greenArray, PROPERTY_QNAME[20]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void setGreenArray(int i, CTPercentage green) {
        generatedSetterHelperImpl(green, PROPERTY_QNAME[20], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPercentage insertNewGreen(int i) {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().insert_element_user(PROPERTY_QNAME[20], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPercentage addNewGreen() {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().add_element_user(PROPERTY_QNAME[20]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void removeGreen(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[20], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public List<CTPercentage> getGreenOffList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda101
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSchemeColorImpl.this.getGreenOffArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda102
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTSchemeColorImpl.this.setGreenOffArray(((Integer) obj).intValue(), (CTPercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda103
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSchemeColorImpl.this.insertNewGreenOff(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda104
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTSchemeColorImpl.this.removeGreenOff(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda105
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTSchemeColorImpl.this.sizeOfGreenOffArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPercentage[] getGreenOffArray() {
        return (CTPercentage[]) getXmlObjectArray(PROPERTY_QNAME[21], new CTPercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public int sizeOfGreenOffArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[21]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void setGreenOffArray(CTPercentage[] greenOffArray) {
        check_orphaned();
        arraySetterHelper(greenOffArray, PROPERTY_QNAME[21]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void setGreenOffArray(int i, CTPercentage greenOff) {
        generatedSetterHelperImpl(greenOff, PROPERTY_QNAME[21], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPercentage insertNewGreenOff(int i) {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().insert_element_user(PROPERTY_QNAME[21], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPercentage addNewGreenOff() {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().add_element_user(PROPERTY_QNAME[21]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void removeGreenOff(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[21], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public List<CTPercentage> getGreenModList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda134
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSchemeColorImpl.this.getGreenModArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda135
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTSchemeColorImpl.this.setGreenModArray(((Integer) obj).intValue(), (CTPercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda136
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSchemeColorImpl.this.insertNewGreenMod(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda137
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTSchemeColorImpl.this.removeGreenMod(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda138
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTSchemeColorImpl.this.sizeOfGreenModArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPercentage[] getGreenModArray() {
        return (CTPercentage[]) getXmlObjectArray(PROPERTY_QNAME[22], new CTPercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public int sizeOfGreenModArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[22]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void setGreenModArray(CTPercentage[] greenModArray) {
        check_orphaned();
        arraySetterHelper(greenModArray, PROPERTY_QNAME[22]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void setGreenModArray(int i, CTPercentage greenMod) {
        generatedSetterHelperImpl(greenMod, PROPERTY_QNAME[22], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPercentage insertNewGreenMod(int i) {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().insert_element_user(PROPERTY_QNAME[22], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPercentage addNewGreenMod() {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().add_element_user(PROPERTY_QNAME[22]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void removeGreenMod(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[22], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public List<CTPercentage> getBlueList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda52
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSchemeColorImpl.this.getBlueArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda53
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTSchemeColorImpl.this.setBlueArray(((Integer) obj).intValue(), (CTPercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda54
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSchemeColorImpl.this.insertNewBlue(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda55
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTSchemeColorImpl.this.removeBlue(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda56
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTSchemeColorImpl.this.sizeOfBlueArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPercentage[] getBlueArray() {
        return (CTPercentage[]) getXmlObjectArray(PROPERTY_QNAME[23], new CTPercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public int sizeOfBlueArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[23]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void setBlueArray(CTPercentage[] blueArray) {
        check_orphaned();
        arraySetterHelper(blueArray, PROPERTY_QNAME[23]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void setBlueArray(int i, CTPercentage blue) {
        generatedSetterHelperImpl(blue, PROPERTY_QNAME[23], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPercentage insertNewBlue(int i) {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().insert_element_user(PROPERTY_QNAME[23], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPercentage addNewBlue() {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().add_element_user(PROPERTY_QNAME[23]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void removeBlue(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[23], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public List<CTPercentage> getBlueOffList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda63
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSchemeColorImpl.this.getBlueOffArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda64
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTSchemeColorImpl.this.setBlueOffArray(((Integer) obj).intValue(), (CTPercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda65
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSchemeColorImpl.this.insertNewBlueOff(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda66
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTSchemeColorImpl.this.removeBlueOff(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda67
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTSchemeColorImpl.this.sizeOfBlueOffArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPercentage[] getBlueOffArray() {
        return (CTPercentage[]) getXmlObjectArray(PROPERTY_QNAME[24], new CTPercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public int sizeOfBlueOffArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[24]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void setBlueOffArray(CTPercentage[] blueOffArray) {
        check_orphaned();
        arraySetterHelper(blueOffArray, PROPERTY_QNAME[24]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void setBlueOffArray(int i, CTPercentage blueOff) {
        generatedSetterHelperImpl(blueOff, PROPERTY_QNAME[24], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPercentage insertNewBlueOff(int i) {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().insert_element_user(PROPERTY_QNAME[24], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPercentage addNewBlueOff() {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().add_element_user(PROPERTY_QNAME[24]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void removeBlueOff(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[24], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public List<CTPercentage> getBlueModList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda34
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSchemeColorImpl.this.getBlueModArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda35
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTSchemeColorImpl.this.setBlueModArray(((Integer) obj).intValue(), (CTPercentage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda36
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSchemeColorImpl.this.insertNewBlueMod(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda37
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTSchemeColorImpl.this.removeBlueMod(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda38
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTSchemeColorImpl.this.sizeOfBlueModArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPercentage[] getBlueModArray() {
        return (CTPercentage[]) getXmlObjectArray(PROPERTY_QNAME[25], new CTPercentage[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public int sizeOfBlueModArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[25]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void setBlueModArray(CTPercentage[] blueModArray) {
        check_orphaned();
        arraySetterHelper(blueModArray, PROPERTY_QNAME[25]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void setBlueModArray(int i, CTPercentage blueMod) {
        generatedSetterHelperImpl(blueMod, PROPERTY_QNAME[25], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPercentage insertNewBlueMod(int i) {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().insert_element_user(PROPERTY_QNAME[25], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTPercentage addNewBlueMod() {
        CTPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPercentage) get_store().add_element_user(PROPERTY_QNAME[25]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void removeBlueMod(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[25], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public List<CTGammaTransform> getGammaList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda23
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSchemeColorImpl.this.getGammaArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda24
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTSchemeColorImpl.this.setGammaArray(((Integer) obj).intValue(), (CTGammaTransform) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda25
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSchemeColorImpl.this.insertNewGamma(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda26
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTSchemeColorImpl.this.removeGamma(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda27
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTSchemeColorImpl.this.sizeOfGammaArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTGammaTransform[] getGammaArray() {
        return getXmlObjectArray(PROPERTY_QNAME[26], (XmlObject[]) new CTGammaTransform[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public int sizeOfGammaArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[26]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void setGammaArray(CTGammaTransform[] gammaArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) gammaArray, PROPERTY_QNAME[26]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void setGammaArray(int i, CTGammaTransform gamma) {
        generatedSetterHelperImpl(gamma, PROPERTY_QNAME[26], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTGammaTransform insertNewGamma(int i) {
        CTGammaTransform target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[26], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTGammaTransform addNewGamma() {
        CTGammaTransform target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[26]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void removeGamma(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[26], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public List<CTInverseGammaTransform> getInvGammaList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda46
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSchemeColorImpl.this.getInvGammaArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda47
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTSchemeColorImpl.this.setInvGammaArray(((Integer) obj).intValue(), (CTInverseGammaTransform) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda48
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSchemeColorImpl.this.insertNewInvGamma(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda49
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTSchemeColorImpl.this.removeInvGamma(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSchemeColorImpl$$ExternalSyntheticLambda50
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTSchemeColorImpl.this.sizeOfInvGammaArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTInverseGammaTransform[] getInvGammaArray() {
        return getXmlObjectArray(PROPERTY_QNAME[27], (XmlObject[]) new CTInverseGammaTransform[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public int sizeOfInvGammaArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[27]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void setInvGammaArray(CTInverseGammaTransform[] invGammaArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) invGammaArray, PROPERTY_QNAME[27]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void setInvGammaArray(int i, CTInverseGammaTransform invGamma) {
        generatedSetterHelperImpl(invGamma, PROPERTY_QNAME[27], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTInverseGammaTransform insertNewInvGamma(int i) {
        CTInverseGammaTransform target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[27], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public CTInverseGammaTransform addNewInvGamma() {
        CTInverseGammaTransform target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[27]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void removeInvGamma(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[27], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public STSchemeColorVal.Enum getVal() {
        STSchemeColorVal.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[28]);
            r1 = target == null ? null : (STSchemeColorVal.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public STSchemeColorVal xgetVal() {
        STSchemeColorVal target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STSchemeColorVal) get_store().find_attribute_user(PROPERTY_QNAME[28]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void setVal(STSchemeColorVal.Enum val) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[28]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[28]);
            }
            target.setEnumValue(val);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor
    public void xsetVal(STSchemeColorVal val) {
        synchronized (monitor()) {
            check_orphaned();
            STSchemeColorVal target = (STSchemeColorVal) get_store().find_attribute_user(PROPERTY_QNAME[28]);
            if (target == null) {
                target = (STSchemeColorVal) get_store().add_attribute_user(PROPERTY_QNAME[28]);
            }
            target.set(val);
        }
    }
}
