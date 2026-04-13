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
import org.openxmlformats.schemas.drawingml.x2006.main.CTAdjPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DCubicBezierTo;

/* loaded from: classes11.dex */
public class CTPath2DCubicBezierToImpl extends XmlComplexContentImpl implements CTPath2DCubicBezierTo {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "pt")};
    private static final long serialVersionUID = 1;

    public CTPath2DCubicBezierToImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DCubicBezierTo
    public List<CTAdjPoint2D> getPtList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPath2DCubicBezierToImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPath2DCubicBezierToImpl.this.getPtArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPath2DCubicBezierToImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTPath2DCubicBezierToImpl.this.setPtArray(((Integer) obj).intValue(), (CTAdjPoint2D) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPath2DCubicBezierToImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPath2DCubicBezierToImpl.this.insertNewPt(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPath2DCubicBezierToImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTPath2DCubicBezierToImpl.this.removePt(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPath2DCubicBezierToImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTPath2DCubicBezierToImpl.this.sizeOfPtArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DCubicBezierTo
    public CTAdjPoint2D[] getPtArray() {
        return (CTAdjPoint2D[]) getXmlObjectArray(PROPERTY_QNAME[0], new CTAdjPoint2D[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DCubicBezierTo
    public CTAdjPoint2D getPtArray(int i) {
        CTAdjPoint2D target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTAdjPoint2D) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DCubicBezierTo
    public int sizeOfPtArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DCubicBezierTo
    public void setPtArray(CTAdjPoint2D[] ptArray) {
        check_orphaned();
        arraySetterHelper(ptArray, PROPERTY_QNAME[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DCubicBezierTo
    public void setPtArray(int i, CTAdjPoint2D pt) {
        generatedSetterHelperImpl(pt, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DCubicBezierTo
    public CTAdjPoint2D insertNewPt(int i) {
        CTAdjPoint2D target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTAdjPoint2D) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DCubicBezierTo
    public CTAdjPoint2D addNewPt() {
        CTAdjPoint2D target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTAdjPoint2D) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DCubicBezierTo
    public void removePt(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
