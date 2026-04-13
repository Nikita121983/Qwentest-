package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSRgbColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTScRgbColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSystemColor;

/* loaded from: classes11.dex */
public class CTDuotoneEffectImpl extends XmlComplexContentImpl implements CTDuotoneEffect {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "scrgbClr"), new QName(XSSFRelation.NS_DRAWINGML, "srgbClr"), new QName(XSSFRelation.NS_DRAWINGML, "hslClr"), new QName(XSSFRelation.NS_DRAWINGML, "sysClr"), new QName(XSSFRelation.NS_DRAWINGML, "schemeClr"), new QName(XSSFRelation.NS_DRAWINGML, "prstClr")};
    private static final long serialVersionUID = 1;

    public CTDuotoneEffectImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect
    public List<CTScRgbColor> getScrgbClrList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTDuotoneEffectImpl$$ExternalSyntheticLambda6
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTDuotoneEffectImpl.this.getScrgbClrArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTDuotoneEffectImpl$$ExternalSyntheticLambda7
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTDuotoneEffectImpl.this.setScrgbClrArray(((Integer) obj).intValue(), (CTScRgbColor) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTDuotoneEffectImpl$$ExternalSyntheticLambda8
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTDuotoneEffectImpl.this.insertNewScrgbClr(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTDuotoneEffectImpl$$ExternalSyntheticLambda9
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTDuotoneEffectImpl.this.removeScrgbClr(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTDuotoneEffectImpl$$ExternalSyntheticLambda10
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTDuotoneEffectImpl.this.sizeOfScrgbClrArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect
    public CTScRgbColor[] getScrgbClrArray() {
        return (CTScRgbColor[]) getXmlObjectArray(PROPERTY_QNAME[0], new CTScRgbColor[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect
    public CTScRgbColor getScrgbClrArray(int i) {
        CTScRgbColor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTScRgbColor) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect
    public int sizeOfScrgbClrArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect
    public void setScrgbClrArray(CTScRgbColor[] scrgbClrArray) {
        check_orphaned();
        arraySetterHelper(scrgbClrArray, PROPERTY_QNAME[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect
    public void setScrgbClrArray(int i, CTScRgbColor scrgbClr) {
        generatedSetterHelperImpl(scrgbClr, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect
    public CTScRgbColor insertNewScrgbClr(int i) {
        CTScRgbColor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTScRgbColor) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect
    public CTScRgbColor addNewScrgbClr() {
        CTScRgbColor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTScRgbColor) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect
    public void removeScrgbClr(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect
    public List<CTSRgbColor> getSrgbClrList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTDuotoneEffectImpl$$ExternalSyntheticLambda17
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTDuotoneEffectImpl.this.getSrgbClrArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTDuotoneEffectImpl$$ExternalSyntheticLambda18
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTDuotoneEffectImpl.this.setSrgbClrArray(((Integer) obj).intValue(), (CTSRgbColor) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTDuotoneEffectImpl$$ExternalSyntheticLambda19
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTDuotoneEffectImpl.this.insertNewSrgbClr(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTDuotoneEffectImpl$$ExternalSyntheticLambda20
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTDuotoneEffectImpl.this.removeSrgbClr(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTDuotoneEffectImpl$$ExternalSyntheticLambda21
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTDuotoneEffectImpl.this.sizeOfSrgbClrArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect
    public CTSRgbColor[] getSrgbClrArray() {
        return (CTSRgbColor[]) getXmlObjectArray(PROPERTY_QNAME[1], new CTSRgbColor[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect
    public CTSRgbColor getSrgbClrArray(int i) {
        CTSRgbColor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSRgbColor) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect
    public int sizeOfSrgbClrArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect
    public void setSrgbClrArray(CTSRgbColor[] srgbClrArray) {
        check_orphaned();
        arraySetterHelper(srgbClrArray, PROPERTY_QNAME[1]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect
    public void setSrgbClrArray(int i, CTSRgbColor srgbClr) {
        generatedSetterHelperImpl(srgbClr, PROPERTY_QNAME[1], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect
    public CTSRgbColor insertNewSrgbClr(int i) {
        CTSRgbColor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSRgbColor) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect
    public CTSRgbColor addNewSrgbClr() {
        CTSRgbColor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSRgbColor) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect
    public void removeSrgbClr(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect
    public List<CTHslColor> getHslClrList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTDuotoneEffectImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTDuotoneEffectImpl.this.getHslClrArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTDuotoneEffectImpl$$ExternalSyntheticLambda11
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTDuotoneEffectImpl.this.setHslClrArray(((Integer) obj).intValue(), (CTHslColor) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTDuotoneEffectImpl$$ExternalSyntheticLambda22
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTDuotoneEffectImpl.this.insertNewHslClr(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTDuotoneEffectImpl$$ExternalSyntheticLambda23
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTDuotoneEffectImpl.this.removeHslClr(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTDuotoneEffectImpl$$ExternalSyntheticLambda24
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTDuotoneEffectImpl.this.sizeOfHslClrArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect
    public CTHslColor[] getHslClrArray() {
        return (CTHslColor[]) getXmlObjectArray(PROPERTY_QNAME[2], new CTHslColor[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect
    public CTHslColor getHslClrArray(int i) {
        CTHslColor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTHslColor) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect
    public int sizeOfHslClrArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect
    public void setHslClrArray(CTHslColor[] hslClrArray) {
        check_orphaned();
        arraySetterHelper(hslClrArray, PROPERTY_QNAME[2]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect
    public void setHslClrArray(int i, CTHslColor hslClr) {
        generatedSetterHelperImpl(hslClr, PROPERTY_QNAME[2], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect
    public CTHslColor insertNewHslClr(int i) {
        CTHslColor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTHslColor) get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect
    public CTHslColor addNewHslClr() {
        CTHslColor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTHslColor) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect
    public void removeHslClr(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect
    public List<CTSystemColor> getSysClrList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTDuotoneEffectImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTDuotoneEffectImpl.this.getSysClrArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTDuotoneEffectImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTDuotoneEffectImpl.this.setSysClrArray(((Integer) obj).intValue(), (CTSystemColor) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTDuotoneEffectImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTDuotoneEffectImpl.this.insertNewSysClr(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTDuotoneEffectImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTDuotoneEffectImpl.this.removeSysClr(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTDuotoneEffectImpl$$ExternalSyntheticLambda5
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTDuotoneEffectImpl.this.sizeOfSysClrArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect
    public CTSystemColor[] getSysClrArray() {
        return (CTSystemColor[]) getXmlObjectArray(PROPERTY_QNAME[3], new CTSystemColor[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect
    public CTSystemColor getSysClrArray(int i) {
        CTSystemColor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSystemColor) get_store().find_element_user(PROPERTY_QNAME[3], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect
    public int sizeOfSysClrArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect
    public void setSysClrArray(CTSystemColor[] sysClrArray) {
        check_orphaned();
        arraySetterHelper(sysClrArray, PROPERTY_QNAME[3]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect
    public void setSysClrArray(int i, CTSystemColor sysClr) {
        generatedSetterHelperImpl(sysClr, PROPERTY_QNAME[3], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect
    public CTSystemColor insertNewSysClr(int i) {
        CTSystemColor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSystemColor) get_store().insert_element_user(PROPERTY_QNAME[3], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect
    public CTSystemColor addNewSysClr() {
        CTSystemColor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSystemColor) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect
    public void removeSysClr(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect
    public List<CTSchemeColor> getSchemeClrList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTDuotoneEffectImpl$$ExternalSyntheticLambda25
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTDuotoneEffectImpl.this.getSchemeClrArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTDuotoneEffectImpl$$ExternalSyntheticLambda26
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTDuotoneEffectImpl.this.setSchemeClrArray(((Integer) obj).intValue(), (CTSchemeColor) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTDuotoneEffectImpl$$ExternalSyntheticLambda27
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTDuotoneEffectImpl.this.insertNewSchemeClr(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTDuotoneEffectImpl$$ExternalSyntheticLambda28
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTDuotoneEffectImpl.this.removeSchemeClr(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTDuotoneEffectImpl$$ExternalSyntheticLambda29
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTDuotoneEffectImpl.this.sizeOfSchemeClrArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect
    public CTSchemeColor[] getSchemeClrArray() {
        return (CTSchemeColor[]) getXmlObjectArray(PROPERTY_QNAME[4], new CTSchemeColor[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect
    public CTSchemeColor getSchemeClrArray(int i) {
        CTSchemeColor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSchemeColor) get_store().find_element_user(PROPERTY_QNAME[4], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect
    public int sizeOfSchemeClrArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[4]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect
    public void setSchemeClrArray(CTSchemeColor[] schemeClrArray) {
        check_orphaned();
        arraySetterHelper(schemeClrArray, PROPERTY_QNAME[4]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect
    public void setSchemeClrArray(int i, CTSchemeColor schemeClr) {
        generatedSetterHelperImpl(schemeClr, PROPERTY_QNAME[4], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect
    public CTSchemeColor insertNewSchemeClr(int i) {
        CTSchemeColor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSchemeColor) get_store().insert_element_user(PROPERTY_QNAME[4], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect
    public CTSchemeColor addNewSchemeClr() {
        CTSchemeColor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSchemeColor) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect
    public void removeSchemeClr(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect
    public List<CTPresetColor> getPrstClrList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTDuotoneEffectImpl$$ExternalSyntheticLambda12
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTDuotoneEffectImpl.this.getPrstClrArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTDuotoneEffectImpl$$ExternalSyntheticLambda13
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTDuotoneEffectImpl.this.setPrstClrArray(((Integer) obj).intValue(), (CTPresetColor) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTDuotoneEffectImpl$$ExternalSyntheticLambda14
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTDuotoneEffectImpl.this.insertNewPrstClr(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTDuotoneEffectImpl$$ExternalSyntheticLambda15
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTDuotoneEffectImpl.this.removePrstClr(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTDuotoneEffectImpl$$ExternalSyntheticLambda16
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTDuotoneEffectImpl.this.sizeOfPrstClrArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect
    public CTPresetColor[] getPrstClrArray() {
        return (CTPresetColor[]) getXmlObjectArray(PROPERTY_QNAME[5], new CTPresetColor[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect
    public CTPresetColor getPrstClrArray(int i) {
        CTPresetColor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPresetColor) get_store().find_element_user(PROPERTY_QNAME[5], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect
    public int sizeOfPrstClrArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[5]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect
    public void setPrstClrArray(CTPresetColor[] prstClrArray) {
        check_orphaned();
        arraySetterHelper(prstClrArray, PROPERTY_QNAME[5]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect
    public void setPrstClrArray(int i, CTPresetColor prstClr) {
        generatedSetterHelperImpl(prstClr, PROPERTY_QNAME[5], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect
    public CTPresetColor insertNewPrstClr(int i) {
        CTPresetColor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPresetColor) get_store().insert_element_user(PROPERTY_QNAME[5], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect
    public CTPresetColor addNewPrstClr() {
        CTPresetColor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPresetColor) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect
    public void removePrstClr(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], i);
        }
    }
}
