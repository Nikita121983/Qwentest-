package com.microsoft.schemas.vml.impl;

import com.microsoft.schemas.vml.CTH;
import com.microsoft.schemas.vml.CTHandles;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

/* loaded from: classes9.dex */
public class CTHandlesImpl extends XmlComplexContentImpl implements CTHandles {
    private static final QName[] PROPERTY_QNAME = {new QName("urn:schemas-microsoft-com:vml", "h")};
    private static final long serialVersionUID = 1;

    public CTHandlesImpl(SchemaType sType) {
        super(sType);
    }

    @Override // com.microsoft.schemas.vml.CTHandles
    public List<CTH> getHList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTHandlesImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTHandlesImpl.this.getHArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTHandlesImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTHandlesImpl.this.setHArray(((Integer) obj).intValue(), (CTH) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTHandlesImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTHandlesImpl.this.insertNewH(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTHandlesImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTHandlesImpl.this.removeH(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTHandlesImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTHandlesImpl.this.sizeOfHArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTHandles
    public CTH[] getHArray() {
        return (CTH[]) getXmlObjectArray(PROPERTY_QNAME[0], new CTH[0]);
    }

    @Override // com.microsoft.schemas.vml.CTHandles
    public CTH getHArray(int i) {
        CTH target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTH) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTHandles
    public int sizeOfHArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTHandles
    public void setHArray(CTH[] hArray) {
        check_orphaned();
        arraySetterHelper(hArray, PROPERTY_QNAME[0]);
    }

    @Override // com.microsoft.schemas.vml.CTHandles
    public void setHArray(int i, CTH h) {
        generatedSetterHelperImpl(h, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTHandles
    public CTH insertNewH(int i) {
        CTH target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTH) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTHandles
    public CTH addNewH() {
        CTH target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTH) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTHandles
    public void removeH(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
