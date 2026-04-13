package com.microsoft.schemas.vml.impl;

import com.microsoft.schemas.vml.CTF;
import com.microsoft.schemas.vml.CTFormulas;
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
public class CTFormulasImpl extends XmlComplexContentImpl implements CTFormulas {
    private static final QName[] PROPERTY_QNAME = {new QName("urn:schemas-microsoft-com:vml", "f")};
    private static final long serialVersionUID = 1;

    public CTFormulasImpl(SchemaType sType) {
        super(sType);
    }

    @Override // com.microsoft.schemas.vml.CTFormulas
    public List<CTF> getFList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTFormulasImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTFormulasImpl.this.getFArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTFormulasImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTFormulasImpl.this.setFArray(((Integer) obj).intValue(), (CTF) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTFormulasImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTFormulasImpl.this.insertNewF(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTFormulasImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTFormulasImpl.this.removeF(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTFormulasImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTFormulasImpl.this.sizeOfFArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTFormulas
    public CTF[] getFArray() {
        return (CTF[]) getXmlObjectArray(PROPERTY_QNAME[0], new CTF[0]);
    }

    @Override // com.microsoft.schemas.vml.CTFormulas
    public CTF getFArray(int i) {
        CTF target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTF) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTFormulas
    public int sizeOfFArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTFormulas
    public void setFArray(CTF[] fArray) {
        check_orphaned();
        arraySetterHelper(fArray, PROPERTY_QNAME[0]);
    }

    @Override // com.microsoft.schemas.vml.CTFormulas
    public void setFArray(int i, CTF f) {
        generatedSetterHelperImpl(f, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTFormulas
    public CTF insertNewF(int i) {
        CTF target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTF) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTFormulas
    public CTF addNewF() {
        CTF target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTF) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTFormulas
    public void removeF(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
