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
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineStyleList;

/* loaded from: classes11.dex */
public class CTLineStyleListImpl extends XmlComplexContentImpl implements CTLineStyleList {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "ln")};
    private static final long serialVersionUID = 1;

    public CTLineStyleListImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineStyleList
    public List<CTLineProperties> getLnList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTLineStyleListImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTLineStyleListImpl.this.getLnArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTLineStyleListImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTLineStyleListImpl.this.setLnArray(((Integer) obj).intValue(), (CTLineProperties) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTLineStyleListImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTLineStyleListImpl.this.insertNewLn(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTLineStyleListImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTLineStyleListImpl.this.removeLn(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTLineStyleListImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTLineStyleListImpl.this.sizeOfLnArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineStyleList
    public CTLineProperties[] getLnArray() {
        return (CTLineProperties[]) getXmlObjectArray(PROPERTY_QNAME[0], new CTLineProperties[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineStyleList
    public CTLineProperties getLnArray(int i) {
        CTLineProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLineProperties) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineStyleList
    public int sizeOfLnArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineStyleList
    public void setLnArray(CTLineProperties[] lnArray) {
        check_orphaned();
        arraySetterHelper(lnArray, PROPERTY_QNAME[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineStyleList
    public void setLnArray(int i, CTLineProperties ln) {
        generatedSetterHelperImpl(ln, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineStyleList
    public CTLineProperties insertNewLn(int i) {
        CTLineProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLineProperties) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineStyleList
    public CTLineProperties addNewLn() {
        CTLineProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLineProperties) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineStyleList
    public void removeLn(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
