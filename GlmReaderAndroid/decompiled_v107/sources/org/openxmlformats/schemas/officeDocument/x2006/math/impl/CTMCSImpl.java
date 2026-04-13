package org.openxmlformats.schemas.officeDocument.x2006.math.impl;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTMC;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTMCS;

/* loaded from: classes11.dex */
public class CTMCSImpl extends XmlComplexContentImpl implements CTMCS {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "mc")};
    private static final long serialVersionUID = 1;

    public CTMCSImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMCS
    public List<CTMC> getMcList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTMCSImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTMCSImpl.this.getMcArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTMCSImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTMCSImpl.this.setMcArray(((Integer) obj).intValue(), (CTMC) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTMCSImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTMCSImpl.this.insertNewMc(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTMCSImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTMCSImpl.this.removeMc(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTMCSImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTMCSImpl.this.sizeOfMcArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMCS
    public CTMC[] getMcArray() {
        return (CTMC[]) getXmlObjectArray(PROPERTY_QNAME[0], new CTMC[0]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMCS
    public CTMC getMcArray(int i) {
        CTMC target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMC) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMCS
    public int sizeOfMcArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMCS
    public void setMcArray(CTMC[] mcArray) {
        check_orphaned();
        arraySetterHelper(mcArray, PROPERTY_QNAME[0]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMCS
    public void setMcArray(int i, CTMC mc) {
        generatedSetterHelperImpl(mc, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMCS
    public CTMC insertNewMc(int i) {
        CTMC target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMC) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMCS
    public CTMC addNewMc() {
        CTMC target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMC) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMCS
    public void removeMc(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
