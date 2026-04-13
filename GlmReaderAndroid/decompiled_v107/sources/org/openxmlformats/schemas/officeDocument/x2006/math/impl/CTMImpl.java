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
import org.openxmlformats.schemas.officeDocument.x2006.math.CTM;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTMPr;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTMR;

/* loaded from: classes11.dex */
public class CTMImpl extends XmlComplexContentImpl implements CTM {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "mPr"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "mr")};
    private static final long serialVersionUID = 1;

    public CTMImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTM
    public CTMPr getMPr() {
        CTMPr cTMPr;
        synchronized (monitor()) {
            check_orphaned();
            CTMPr target = (CTMPr) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTMPr = target == null ? null : target;
        }
        return cTMPr;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTM
    public boolean isSetMPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTM
    public void setMPr(CTMPr mPr) {
        generatedSetterHelperImpl(mPr, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTM
    public CTMPr addNewMPr() {
        CTMPr target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMPr) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTM
    public void unsetMPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTM
    public List<CTMR> getMrList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTMImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTMImpl.this.getMrArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTMImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTMImpl.this.setMrArray(((Integer) obj).intValue(), (CTMR) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTMImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTMImpl.this.insertNewMr(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTMImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTMImpl.this.removeMr(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTMImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTMImpl.this.sizeOfMrArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTM
    public CTMR[] getMrArray() {
        return (CTMR[]) getXmlObjectArray(PROPERTY_QNAME[1], new CTMR[0]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTM
    public CTMR getMrArray(int i) {
        CTMR target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMR) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTM
    public int sizeOfMrArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTM
    public void setMrArray(CTMR[] mrArray) {
        check_orphaned();
        arraySetterHelper(mrArray, PROPERTY_QNAME[1]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTM
    public void setMrArray(int i, CTMR mr) {
        generatedSetterHelperImpl(mr, PROPERTY_QNAME[1], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTM
    public CTMR insertNewMr(int i) {
        CTMR target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMR) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTM
    public CTMR addNewMr() {
        CTMR target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMR) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTM
    public void removeMr(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }
}
