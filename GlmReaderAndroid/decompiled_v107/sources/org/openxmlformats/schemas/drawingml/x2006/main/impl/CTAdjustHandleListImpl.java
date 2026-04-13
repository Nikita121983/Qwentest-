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
import org.openxmlformats.schemas.drawingml.x2006.main.CTAdjustHandleList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle;

/* loaded from: classes11.dex */
public class CTAdjustHandleListImpl extends XmlComplexContentImpl implements CTAdjustHandleList {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "ahXY"), new QName(XSSFRelation.NS_DRAWINGML, "ahPolar")};
    private static final long serialVersionUID = 1;

    public CTAdjustHandleListImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTAdjustHandleList
    public List<CTXYAdjustHandle> getAhXYList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTAdjustHandleListImpl$$ExternalSyntheticLambda5
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTAdjustHandleListImpl.this.getAhXYArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTAdjustHandleListImpl$$ExternalSyntheticLambda6
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTAdjustHandleListImpl.this.setAhXYArray(((Integer) obj).intValue(), (CTXYAdjustHandle) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTAdjustHandleListImpl$$ExternalSyntheticLambda7
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTAdjustHandleListImpl.this.insertNewAhXY(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTAdjustHandleListImpl$$ExternalSyntheticLambda8
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTAdjustHandleListImpl.this.removeAhXY(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTAdjustHandleListImpl$$ExternalSyntheticLambda9
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTAdjustHandleListImpl.this.sizeOfAhXYArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTAdjustHandleList
    public CTXYAdjustHandle[] getAhXYArray() {
        return (CTXYAdjustHandle[]) getXmlObjectArray(PROPERTY_QNAME[0], new CTXYAdjustHandle[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTAdjustHandleList
    public CTXYAdjustHandle getAhXYArray(int i) {
        CTXYAdjustHandle target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTXYAdjustHandle) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTAdjustHandleList
    public int sizeOfAhXYArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTAdjustHandleList
    public void setAhXYArray(CTXYAdjustHandle[] ahXYArray) {
        check_orphaned();
        arraySetterHelper(ahXYArray, PROPERTY_QNAME[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTAdjustHandleList
    public void setAhXYArray(int i, CTXYAdjustHandle ahXY) {
        generatedSetterHelperImpl(ahXY, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTAdjustHandleList
    public CTXYAdjustHandle insertNewAhXY(int i) {
        CTXYAdjustHandle target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTXYAdjustHandle) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTAdjustHandleList
    public CTXYAdjustHandle addNewAhXY() {
        CTXYAdjustHandle target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTXYAdjustHandle) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTAdjustHandleList
    public void removeAhXY(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTAdjustHandleList
    public List<CTPolarAdjustHandle> getAhPolarList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTAdjustHandleListImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTAdjustHandleListImpl.this.getAhPolarArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTAdjustHandleListImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTAdjustHandleListImpl.this.setAhPolarArray(((Integer) obj).intValue(), (CTPolarAdjustHandle) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTAdjustHandleListImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTAdjustHandleListImpl.this.insertNewAhPolar(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTAdjustHandleListImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTAdjustHandleListImpl.this.removeAhPolar(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTAdjustHandleListImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTAdjustHandleListImpl.this.sizeOfAhPolarArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTAdjustHandleList
    public CTPolarAdjustHandle[] getAhPolarArray() {
        return (CTPolarAdjustHandle[]) getXmlObjectArray(PROPERTY_QNAME[1], new CTPolarAdjustHandle[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTAdjustHandleList
    public CTPolarAdjustHandle getAhPolarArray(int i) {
        CTPolarAdjustHandle target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPolarAdjustHandle) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTAdjustHandleList
    public int sizeOfAhPolarArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTAdjustHandleList
    public void setAhPolarArray(CTPolarAdjustHandle[] ahPolarArray) {
        check_orphaned();
        arraySetterHelper(ahPolarArray, PROPERTY_QNAME[1]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTAdjustHandleList
    public void setAhPolarArray(int i, CTPolarAdjustHandle ahPolar) {
        generatedSetterHelperImpl(ahPolar, PROPERTY_QNAME[1], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTAdjustHandleList
    public CTPolarAdjustHandle insertNewAhPolar(int i) {
        CTPolarAdjustHandle target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPolarAdjustHandle) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTAdjustHandleList
    public CTPolarAdjustHandle addNewAhPolar() {
        CTPolarAdjustHandle target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPolarAdjustHandle) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTAdjustHandleList
    public void removeAhPolar(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }
}
