package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DArcTo;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DClose;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DCubicBezierTo;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DLineTo;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DMoveTo;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DQuadBezierTo;
import org.openxmlformats.schemas.drawingml.x2006.main.STPathFillMode;
import org.openxmlformats.schemas.drawingml.x2006.main.STPositiveCoordinate;

/* loaded from: classes11.dex */
public class CTPath2DImpl extends XmlComplexContentImpl implements CTPath2D {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "close"), new QName(XSSFRelation.NS_DRAWINGML, "moveTo"), new QName(XSSFRelation.NS_DRAWINGML, "lnTo"), new QName(XSSFRelation.NS_DRAWINGML, "arcTo"), new QName(XSSFRelation.NS_DRAWINGML, "quadBezTo"), new QName(XSSFRelation.NS_DRAWINGML, "cubicBezTo"), new QName("", "w"), new QName("", "h"), new QName("", "fill"), new QName("", "stroke"), new QName("", "extrusionOk")};
    private static final long serialVersionUID = 1;

    public CTPath2DImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public List<CTPath2DClose> getCloseList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPath2DImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPath2DImpl.this.getCloseArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPath2DImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTPath2DImpl.this.setCloseArray(((Integer) obj).intValue(), (CTPath2DClose) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPath2DImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPath2DImpl.this.insertNewClose(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPath2DImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTPath2DImpl.this.removeClose(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPath2DImpl$$ExternalSyntheticLambda5
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTPath2DImpl.this.sizeOfCloseArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public CTPath2DClose[] getCloseArray() {
        return (CTPath2DClose[]) getXmlObjectArray(PROPERTY_QNAME[0], new CTPath2DClose[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public CTPath2DClose getCloseArray(int i) {
        CTPath2DClose target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPath2DClose) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public int sizeOfCloseArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public void setCloseArray(CTPath2DClose[] closeArray) {
        check_orphaned();
        arraySetterHelper(closeArray, PROPERTY_QNAME[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public void setCloseArray(int i, CTPath2DClose close) {
        generatedSetterHelperImpl(close, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public CTPath2DClose insertNewClose(int i) {
        CTPath2DClose target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPath2DClose) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public CTPath2DClose addNewClose() {
        CTPath2DClose target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPath2DClose) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public void removeClose(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public List<CTPath2DMoveTo> getMoveToList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPath2DImpl$$ExternalSyntheticLambda6
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPath2DImpl.this.getMoveToArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPath2DImpl$$ExternalSyntheticLambda7
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTPath2DImpl.this.setMoveToArray(((Integer) obj).intValue(), (CTPath2DMoveTo) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPath2DImpl$$ExternalSyntheticLambda8
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPath2DImpl.this.insertNewMoveTo(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPath2DImpl$$ExternalSyntheticLambda9
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTPath2DImpl.this.removeMoveTo(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPath2DImpl$$ExternalSyntheticLambda10
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTPath2DImpl.this.sizeOfMoveToArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public CTPath2DMoveTo[] getMoveToArray() {
        return (CTPath2DMoveTo[]) getXmlObjectArray(PROPERTY_QNAME[1], new CTPath2DMoveTo[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public CTPath2DMoveTo getMoveToArray(int i) {
        CTPath2DMoveTo target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPath2DMoveTo) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public int sizeOfMoveToArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public void setMoveToArray(CTPath2DMoveTo[] moveToArray) {
        check_orphaned();
        arraySetterHelper(moveToArray, PROPERTY_QNAME[1]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public void setMoveToArray(int i, CTPath2DMoveTo moveTo) {
        generatedSetterHelperImpl(moveTo, PROPERTY_QNAME[1], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public CTPath2DMoveTo insertNewMoveTo(int i) {
        CTPath2DMoveTo target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPath2DMoveTo) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public CTPath2DMoveTo addNewMoveTo() {
        CTPath2DMoveTo target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPath2DMoveTo) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public void removeMoveTo(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public List<CTPath2DLineTo> getLnToList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPath2DImpl$$ExternalSyntheticLambda17
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPath2DImpl.this.getLnToArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPath2DImpl$$ExternalSyntheticLambda18
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTPath2DImpl.this.setLnToArray(((Integer) obj).intValue(), (CTPath2DLineTo) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPath2DImpl$$ExternalSyntheticLambda19
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPath2DImpl.this.insertNewLnTo(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPath2DImpl$$ExternalSyntheticLambda20
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTPath2DImpl.this.removeLnTo(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPath2DImpl$$ExternalSyntheticLambda21
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTPath2DImpl.this.sizeOfLnToArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public CTPath2DLineTo[] getLnToArray() {
        return (CTPath2DLineTo[]) getXmlObjectArray(PROPERTY_QNAME[2], new CTPath2DLineTo[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public CTPath2DLineTo getLnToArray(int i) {
        CTPath2DLineTo target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPath2DLineTo) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public int sizeOfLnToArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public void setLnToArray(CTPath2DLineTo[] lnToArray) {
        check_orphaned();
        arraySetterHelper(lnToArray, PROPERTY_QNAME[2]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public void setLnToArray(int i, CTPath2DLineTo lnTo) {
        generatedSetterHelperImpl(lnTo, PROPERTY_QNAME[2], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public CTPath2DLineTo insertNewLnTo(int i) {
        CTPath2DLineTo target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPath2DLineTo) get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public CTPath2DLineTo addNewLnTo() {
        CTPath2DLineTo target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPath2DLineTo) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public void removeLnTo(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public List<CTPath2DArcTo> getArcToList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPath2DImpl$$ExternalSyntheticLambda12
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPath2DImpl.this.getArcToArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPath2DImpl$$ExternalSyntheticLambda13
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTPath2DImpl.this.setArcToArray(((Integer) obj).intValue(), (CTPath2DArcTo) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPath2DImpl$$ExternalSyntheticLambda14
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPath2DImpl.this.insertNewArcTo(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPath2DImpl$$ExternalSyntheticLambda15
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTPath2DImpl.this.removeArcTo(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPath2DImpl$$ExternalSyntheticLambda16
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTPath2DImpl.this.sizeOfArcToArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public CTPath2DArcTo[] getArcToArray() {
        return (CTPath2DArcTo[]) getXmlObjectArray(PROPERTY_QNAME[3], new CTPath2DArcTo[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public CTPath2DArcTo getArcToArray(int i) {
        CTPath2DArcTo target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPath2DArcTo) get_store().find_element_user(PROPERTY_QNAME[3], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public int sizeOfArcToArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public void setArcToArray(CTPath2DArcTo[] arcToArray) {
        check_orphaned();
        arraySetterHelper(arcToArray, PROPERTY_QNAME[3]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public void setArcToArray(int i, CTPath2DArcTo arcTo) {
        generatedSetterHelperImpl(arcTo, PROPERTY_QNAME[3], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public CTPath2DArcTo insertNewArcTo(int i) {
        CTPath2DArcTo target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPath2DArcTo) get_store().insert_element_user(PROPERTY_QNAME[3], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public CTPath2DArcTo addNewArcTo() {
        CTPath2DArcTo target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPath2DArcTo) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public void removeArcTo(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public List<CTPath2DQuadBezierTo> getQuadBezToList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPath2DImpl$$ExternalSyntheticLambda25
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPath2DImpl.this.getQuadBezToArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPath2DImpl$$ExternalSyntheticLambda26
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTPath2DImpl.this.setQuadBezToArray(((Integer) obj).intValue(), (CTPath2DQuadBezierTo) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPath2DImpl$$ExternalSyntheticLambda27
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPath2DImpl.this.insertNewQuadBezTo(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPath2DImpl$$ExternalSyntheticLambda28
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTPath2DImpl.this.removeQuadBezTo(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPath2DImpl$$ExternalSyntheticLambda29
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTPath2DImpl.this.sizeOfQuadBezToArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public CTPath2DQuadBezierTo[] getQuadBezToArray() {
        return (CTPath2DQuadBezierTo[]) getXmlObjectArray(PROPERTY_QNAME[4], new CTPath2DQuadBezierTo[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public CTPath2DQuadBezierTo getQuadBezToArray(int i) {
        CTPath2DQuadBezierTo target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPath2DQuadBezierTo) get_store().find_element_user(PROPERTY_QNAME[4], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public int sizeOfQuadBezToArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[4]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public void setQuadBezToArray(CTPath2DQuadBezierTo[] quadBezToArray) {
        check_orphaned();
        arraySetterHelper(quadBezToArray, PROPERTY_QNAME[4]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public void setQuadBezToArray(int i, CTPath2DQuadBezierTo quadBezTo) {
        generatedSetterHelperImpl(quadBezTo, PROPERTY_QNAME[4], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public CTPath2DQuadBezierTo insertNewQuadBezTo(int i) {
        CTPath2DQuadBezierTo target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPath2DQuadBezierTo) get_store().insert_element_user(PROPERTY_QNAME[4], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public CTPath2DQuadBezierTo addNewQuadBezTo() {
        CTPath2DQuadBezierTo target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPath2DQuadBezierTo) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public void removeQuadBezTo(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public List<CTPath2DCubicBezierTo> getCubicBezToList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPath2DImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPath2DImpl.this.getCubicBezToArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPath2DImpl$$ExternalSyntheticLambda11
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTPath2DImpl.this.setCubicBezToArray(((Integer) obj).intValue(), (CTPath2DCubicBezierTo) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPath2DImpl$$ExternalSyntheticLambda22
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPath2DImpl.this.insertNewCubicBezTo(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPath2DImpl$$ExternalSyntheticLambda23
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTPath2DImpl.this.removeCubicBezTo(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPath2DImpl$$ExternalSyntheticLambda24
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTPath2DImpl.this.sizeOfCubicBezToArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public CTPath2DCubicBezierTo[] getCubicBezToArray() {
        return (CTPath2DCubicBezierTo[]) getXmlObjectArray(PROPERTY_QNAME[5], new CTPath2DCubicBezierTo[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public CTPath2DCubicBezierTo getCubicBezToArray(int i) {
        CTPath2DCubicBezierTo target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPath2DCubicBezierTo) get_store().find_element_user(PROPERTY_QNAME[5], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public int sizeOfCubicBezToArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[5]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public void setCubicBezToArray(CTPath2DCubicBezierTo[] cubicBezToArray) {
        check_orphaned();
        arraySetterHelper(cubicBezToArray, PROPERTY_QNAME[5]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public void setCubicBezToArray(int i, CTPath2DCubicBezierTo cubicBezTo) {
        generatedSetterHelperImpl(cubicBezTo, PROPERTY_QNAME[5], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public CTPath2DCubicBezierTo insertNewCubicBezTo(int i) {
        CTPath2DCubicBezierTo target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPath2DCubicBezierTo) get_store().insert_element_user(PROPERTY_QNAME[5], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public CTPath2DCubicBezierTo addNewCubicBezTo() {
        CTPath2DCubicBezierTo target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPath2DCubicBezierTo) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public void removeCubicBezTo(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public long getW() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[6]);
            }
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public STPositiveCoordinate xgetW() {
        STPositiveCoordinate target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STPositiveCoordinate) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (STPositiveCoordinate) get_default_attribute_value(PROPERTY_QNAME[6]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public boolean isSetW() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[6]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public void setW(long w) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.setLongValue(w);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public void xsetW(STPositiveCoordinate w) {
        synchronized (monitor()) {
            check_orphaned();
            STPositiveCoordinate target = (STPositiveCoordinate) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (STPositiveCoordinate) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.set(w);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public void unsetW() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[6]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public long getH() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[7]);
            }
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public STPositiveCoordinate xgetH() {
        STPositiveCoordinate target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STPositiveCoordinate) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (STPositiveCoordinate) get_default_attribute_value(PROPERTY_QNAME[7]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public boolean isSetH() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[7]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public void setH(long h) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.setLongValue(h);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public void xsetH(STPositiveCoordinate h) {
        synchronized (monitor()) {
            check_orphaned();
            STPositiveCoordinate target = (STPositiveCoordinate) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (STPositiveCoordinate) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.set(h);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public void unsetH() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[7]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public STPathFillMode.Enum getFill() {
        STPathFillMode.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[8]);
            }
            r1 = target == null ? null : (STPathFillMode.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public STPathFillMode xgetFill() {
        STPathFillMode target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STPathFillMode) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (STPathFillMode) get_default_attribute_value(PROPERTY_QNAME[8]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public boolean isSetFill() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[8]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public void setFill(STPathFillMode.Enum fill) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[8]);
            }
            target.setEnumValue(fill);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public void xsetFill(STPathFillMode fill) {
        synchronized (monitor()) {
            check_orphaned();
            STPathFillMode target = (STPathFillMode) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (STPathFillMode) get_store().add_attribute_user(PROPERTY_QNAME[8]);
            }
            target.set(fill);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public void unsetFill() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[8]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public boolean getStroke() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[9]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public XmlBoolean xgetStroke() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[9]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public boolean isSetStroke() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[9]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public void setStroke(boolean stroke) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[9]);
            }
            target.setBooleanValue(stroke);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public void xsetStroke(XmlBoolean stroke) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[9]);
            }
            target.set(stroke);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public void unsetStroke() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[9]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public boolean getExtrusionOk() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[10]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public XmlBoolean xgetExtrusionOk() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[10]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public boolean isSetExtrusionOk() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[10]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public void setExtrusionOk(boolean extrusionOk) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[10]);
            }
            target.setBooleanValue(extrusionOk);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public void xsetExtrusionOk(XmlBoolean extrusionOk) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[10]);
            }
            target.set(extrusionOk);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D
    public void unsetExtrusionOk() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[10]);
        }
    }
}
