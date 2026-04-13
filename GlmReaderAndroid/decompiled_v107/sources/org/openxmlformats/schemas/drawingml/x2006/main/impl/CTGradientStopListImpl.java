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
import org.openxmlformats.schemas.drawingml.x2006.main.CTGradientStop;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGradientStopList;

/* loaded from: classes11.dex */
public class CTGradientStopListImpl extends XmlComplexContentImpl implements CTGradientStopList {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "gs")};
    private static final long serialVersionUID = 1;

    public CTGradientStopListImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTGradientStopList
    public List<CTGradientStop> getGsList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTGradientStopListImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGradientStopListImpl.this.getGsArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTGradientStopListImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTGradientStopListImpl.this.setGsArray(((Integer) obj).intValue(), (CTGradientStop) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTGradientStopListImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGradientStopListImpl.this.insertNewGs(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTGradientStopListImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTGradientStopListImpl.this.removeGs(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTGradientStopListImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTGradientStopListImpl.this.sizeOfGsArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTGradientStopList
    public CTGradientStop[] getGsArray() {
        return (CTGradientStop[]) getXmlObjectArray(PROPERTY_QNAME[0], new CTGradientStop[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTGradientStopList
    public CTGradientStop getGsArray(int i) {
        CTGradientStop target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTGradientStop) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTGradientStopList
    public int sizeOfGsArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTGradientStopList
    public void setGsArray(CTGradientStop[] gsArray) {
        check_orphaned();
        arraySetterHelper(gsArray, PROPERTY_QNAME[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTGradientStopList
    public void setGsArray(int i, CTGradientStop gs) {
        generatedSetterHelperImpl(gs, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTGradientStopList
    public CTGradientStop insertNewGs(int i) {
        CTGradientStop target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTGradientStop) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTGradientStopList
    public CTGradientStop addNewGs() {
        CTGradientStop target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTGradientStop) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTGradientStopList
    public void removeGs(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
