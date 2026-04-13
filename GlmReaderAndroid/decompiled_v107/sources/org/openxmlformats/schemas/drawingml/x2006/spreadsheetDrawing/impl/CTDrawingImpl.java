package org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAbsoluteAnchor;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTDrawing;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTOneCellAnchor;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTTwoCellAnchor;

/* loaded from: classes11.dex */
public class CTDrawingImpl extends XmlComplexContentImpl implements CTDrawing {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "twoCellAnchor"), new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "oneCellAnchor"), new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "absoluteAnchor")};
    private static final long serialVersionUID = 1;

    public CTDrawingImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTDrawing
    public List<CTTwoCellAnchor> getTwoCellAnchorList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl.CTDrawingImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTDrawingImpl.this.getTwoCellAnchorArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl.CTDrawingImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTDrawingImpl.this.setTwoCellAnchorArray(((Integer) obj).intValue(), (CTTwoCellAnchor) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl.CTDrawingImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTDrawingImpl.this.insertNewTwoCellAnchor(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl.CTDrawingImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTDrawingImpl.this.removeTwoCellAnchor(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl.CTDrawingImpl$$ExternalSyntheticLambda5
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTDrawingImpl.this.sizeOfTwoCellAnchorArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTDrawing
    public CTTwoCellAnchor[] getTwoCellAnchorArray() {
        return (CTTwoCellAnchor[]) getXmlObjectArray(PROPERTY_QNAME[0], new CTTwoCellAnchor[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTDrawing
    public CTTwoCellAnchor getTwoCellAnchorArray(int i) {
        CTTwoCellAnchor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTwoCellAnchor) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTDrawing
    public int sizeOfTwoCellAnchorArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTDrawing
    public void setTwoCellAnchorArray(CTTwoCellAnchor[] twoCellAnchorArray) {
        check_orphaned();
        arraySetterHelper(twoCellAnchorArray, PROPERTY_QNAME[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTDrawing
    public void setTwoCellAnchorArray(int i, CTTwoCellAnchor twoCellAnchor) {
        generatedSetterHelperImpl(twoCellAnchor, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTDrawing
    public CTTwoCellAnchor insertNewTwoCellAnchor(int i) {
        CTTwoCellAnchor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTwoCellAnchor) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTDrawing
    public CTTwoCellAnchor addNewTwoCellAnchor() {
        CTTwoCellAnchor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTwoCellAnchor) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTDrawing
    public void removeTwoCellAnchor(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTDrawing
    public List<CTOneCellAnchor> getOneCellAnchorList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl.CTDrawingImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTDrawingImpl.this.getOneCellAnchorArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl.CTDrawingImpl$$ExternalSyntheticLambda6
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTDrawingImpl.this.setOneCellAnchorArray(((Integer) obj).intValue(), (CTOneCellAnchor) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl.CTDrawingImpl$$ExternalSyntheticLambda7
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTDrawingImpl.this.insertNewOneCellAnchor(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl.CTDrawingImpl$$ExternalSyntheticLambda8
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTDrawingImpl.this.removeOneCellAnchor(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl.CTDrawingImpl$$ExternalSyntheticLambda9
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTDrawingImpl.this.sizeOfOneCellAnchorArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTDrawing
    public CTOneCellAnchor[] getOneCellAnchorArray() {
        return (CTOneCellAnchor[]) getXmlObjectArray(PROPERTY_QNAME[1], new CTOneCellAnchor[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTDrawing
    public CTOneCellAnchor getOneCellAnchorArray(int i) {
        CTOneCellAnchor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOneCellAnchor) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTDrawing
    public int sizeOfOneCellAnchorArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTDrawing
    public void setOneCellAnchorArray(CTOneCellAnchor[] oneCellAnchorArray) {
        check_orphaned();
        arraySetterHelper(oneCellAnchorArray, PROPERTY_QNAME[1]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTDrawing
    public void setOneCellAnchorArray(int i, CTOneCellAnchor oneCellAnchor) {
        generatedSetterHelperImpl(oneCellAnchor, PROPERTY_QNAME[1], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTDrawing
    public CTOneCellAnchor insertNewOneCellAnchor(int i) {
        CTOneCellAnchor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOneCellAnchor) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTDrawing
    public CTOneCellAnchor addNewOneCellAnchor() {
        CTOneCellAnchor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOneCellAnchor) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTDrawing
    public void removeOneCellAnchor(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTDrawing
    public List<CTAbsoluteAnchor> getAbsoluteAnchorList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl.CTDrawingImpl$$ExternalSyntheticLambda10
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTDrawingImpl.this.getAbsoluteAnchorArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl.CTDrawingImpl$$ExternalSyntheticLambda11
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTDrawingImpl.this.setAbsoluteAnchorArray(((Integer) obj).intValue(), (CTAbsoluteAnchor) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl.CTDrawingImpl$$ExternalSyntheticLambda12
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTDrawingImpl.this.insertNewAbsoluteAnchor(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl.CTDrawingImpl$$ExternalSyntheticLambda13
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTDrawingImpl.this.removeAbsoluteAnchor(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl.CTDrawingImpl$$ExternalSyntheticLambda14
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTDrawingImpl.this.sizeOfAbsoluteAnchorArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTDrawing
    public CTAbsoluteAnchor[] getAbsoluteAnchorArray() {
        return (CTAbsoluteAnchor[]) getXmlObjectArray(PROPERTY_QNAME[2], new CTAbsoluteAnchor[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTDrawing
    public CTAbsoluteAnchor getAbsoluteAnchorArray(int i) {
        CTAbsoluteAnchor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTAbsoluteAnchor) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTDrawing
    public int sizeOfAbsoluteAnchorArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTDrawing
    public void setAbsoluteAnchorArray(CTAbsoluteAnchor[] absoluteAnchorArray) {
        check_orphaned();
        arraySetterHelper(absoluteAnchorArray, PROPERTY_QNAME[2]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTDrawing
    public void setAbsoluteAnchorArray(int i, CTAbsoluteAnchor absoluteAnchor) {
        generatedSetterHelperImpl(absoluteAnchor, PROPERTY_QNAME[2], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTDrawing
    public CTAbsoluteAnchor insertNewAbsoluteAnchor(int i) {
        CTAbsoluteAnchor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTAbsoluteAnchor) get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTDrawing
    public CTAbsoluteAnchor addNewAbsoluteAnchor() {
        CTAbsoluteAnchor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTAbsoluteAnchor) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTDrawing
    public void removeAbsoluteAnchor(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }
}
