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
import org.openxmlformats.schemas.drawingml.x2006.main.CTEffectStyleItem;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEffectStyleList;

/* loaded from: classes11.dex */
public class CTEffectStyleListImpl extends XmlComplexContentImpl implements CTEffectStyleList {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "effectStyle")};
    private static final long serialVersionUID = 1;

    public CTEffectStyleListImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectStyleList
    public List<CTEffectStyleItem> getEffectStyleList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectStyleListImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectStyleListImpl.this.getEffectStyleArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectStyleListImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTEffectStyleListImpl.this.setEffectStyleArray(((Integer) obj).intValue(), (CTEffectStyleItem) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectStyleListImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEffectStyleListImpl.this.insertNewEffectStyle(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectStyleListImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTEffectStyleListImpl.this.removeEffectStyle(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectStyleListImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTEffectStyleListImpl.this.sizeOfEffectStyleArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectStyleList
    public CTEffectStyleItem[] getEffectStyleArray() {
        return (CTEffectStyleItem[]) getXmlObjectArray(PROPERTY_QNAME[0], new CTEffectStyleItem[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectStyleList
    public CTEffectStyleItem getEffectStyleArray(int i) {
        CTEffectStyleItem target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEffectStyleItem) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectStyleList
    public int sizeOfEffectStyleArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectStyleList
    public void setEffectStyleArray(CTEffectStyleItem[] effectStyleArray) {
        check_orphaned();
        arraySetterHelper(effectStyleArray, PROPERTY_QNAME[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectStyleList
    public void setEffectStyleArray(int i, CTEffectStyleItem effectStyle) {
        generatedSetterHelperImpl(effectStyle, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectStyleList
    public CTEffectStyleItem insertNewEffectStyle(int i) {
        CTEffectStyleItem target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEffectStyleItem) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectStyleList
    public CTEffectStyleItem addNewEffectStyle() {
        CTEffectStyleItem target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEffectStyleItem) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectStyleList
    public void removeEffectStyle(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
