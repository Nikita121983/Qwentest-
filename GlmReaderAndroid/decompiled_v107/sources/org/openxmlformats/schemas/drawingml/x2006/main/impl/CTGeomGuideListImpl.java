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
import org.openxmlformats.schemas.drawingml.x2006.main.CTGeomGuide;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGeomGuideList;

/* loaded from: classes11.dex */
public class CTGeomGuideListImpl extends XmlComplexContentImpl implements CTGeomGuideList {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "gd")};
    private static final long serialVersionUID = 1;

    public CTGeomGuideListImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTGeomGuideList
    public List<CTGeomGuide> getGdList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTGeomGuideListImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGeomGuideListImpl.this.getGdArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTGeomGuideListImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTGeomGuideListImpl.this.setGdArray(((Integer) obj).intValue(), (CTGeomGuide) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTGeomGuideListImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGeomGuideListImpl.this.insertNewGd(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTGeomGuideListImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTGeomGuideListImpl.this.removeGd(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTGeomGuideListImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTGeomGuideListImpl.this.sizeOfGdArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTGeomGuideList
    public CTGeomGuide[] getGdArray() {
        return (CTGeomGuide[]) getXmlObjectArray(PROPERTY_QNAME[0], new CTGeomGuide[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTGeomGuideList
    public CTGeomGuide getGdArray(int i) {
        CTGeomGuide target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTGeomGuide) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTGeomGuideList
    public int sizeOfGdArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTGeomGuideList
    public void setGdArray(CTGeomGuide[] gdArray) {
        check_orphaned();
        arraySetterHelper(gdArray, PROPERTY_QNAME[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTGeomGuideList
    public void setGdArray(int i, CTGeomGuide gd) {
        generatedSetterHelperImpl(gd, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTGeomGuideList
    public CTGeomGuide insertNewGd(int i) {
        CTGeomGuide target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTGeomGuide) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTGeomGuideList
    public CTGeomGuide addNewGd() {
        CTGeomGuide target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTGeomGuide) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTGeomGuideList
    public void removeGd(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
