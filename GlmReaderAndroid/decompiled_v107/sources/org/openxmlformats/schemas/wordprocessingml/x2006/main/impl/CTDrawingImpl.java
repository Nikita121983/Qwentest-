package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTInline;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDrawing;

/* loaded from: classes12.dex */
public class CTDrawingImpl extends XmlComplexContentImpl implements CTDrawing {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing", "anchor"), new QName("http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing", "inline")};
    private static final long serialVersionUID = 1;

    public CTDrawingImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDrawing
    public List<CTAnchor> getAnchorList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTDrawingImpl$$ExternalSyntheticLambda5
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTDrawingImpl.this.getAnchorArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTDrawingImpl$$ExternalSyntheticLambda6
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTDrawingImpl.this.setAnchorArray(((Integer) obj).intValue(), (CTAnchor) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTDrawingImpl$$ExternalSyntheticLambda7
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTDrawingImpl.this.insertNewAnchor(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTDrawingImpl$$ExternalSyntheticLambda8
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTDrawingImpl.this.removeAnchor(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTDrawingImpl$$ExternalSyntheticLambda9
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTDrawingImpl.this.sizeOfAnchorArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDrawing
    public CTAnchor[] getAnchorArray() {
        return (CTAnchor[]) getXmlObjectArray(PROPERTY_QNAME[0], new CTAnchor[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDrawing
    public CTAnchor getAnchorArray(int i) {
        CTAnchor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTAnchor) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDrawing
    public int sizeOfAnchorArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDrawing
    public void setAnchorArray(CTAnchor[] anchorArray) {
        check_orphaned();
        arraySetterHelper(anchorArray, PROPERTY_QNAME[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDrawing
    public void setAnchorArray(int i, CTAnchor anchor) {
        generatedSetterHelperImpl(anchor, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDrawing
    public CTAnchor insertNewAnchor(int i) {
        CTAnchor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTAnchor) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDrawing
    public CTAnchor addNewAnchor() {
        CTAnchor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTAnchor) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDrawing
    public void removeAnchor(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDrawing
    public List<CTInline> getInlineList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTDrawingImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTDrawingImpl.this.getInlineArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTDrawingImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTDrawingImpl.this.setInlineArray(((Integer) obj).intValue(), (CTInline) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTDrawingImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTDrawingImpl.this.insertNewInline(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTDrawingImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTDrawingImpl.this.removeInline(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTDrawingImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTDrawingImpl.this.sizeOfInlineArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDrawing
    public CTInline[] getInlineArray() {
        return (CTInline[]) getXmlObjectArray(PROPERTY_QNAME[1], new CTInline[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDrawing
    public CTInline getInlineArray(int i) {
        CTInline target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTInline) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDrawing
    public int sizeOfInlineArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDrawing
    public void setInlineArray(CTInline[] inlineArray) {
        check_orphaned();
        arraySetterHelper(inlineArray, PROPERTY_QNAME[1]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDrawing
    public void setInlineArray(int i, CTInline inline) {
        generatedSetterHelperImpl(inline, PROPERTY_QNAME[1], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDrawing
    public CTInline insertNewInline(int i) {
        CTInline target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTInline) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDrawing
    public CTInline addNewInline() {
        CTInline target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTInline) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDrawing
    public void removeInline(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }
}
