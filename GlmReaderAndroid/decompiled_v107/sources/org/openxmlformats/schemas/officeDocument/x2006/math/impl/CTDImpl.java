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
import org.openxmlformats.schemas.officeDocument.x2006.math.CTD;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTDPr;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTOMathArg;

/* loaded from: classes11.dex */
public class CTDImpl extends XmlComplexContentImpl implements CTD {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "dPr"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "e")};
    private static final long serialVersionUID = 1;

    public CTDImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTD
    public CTDPr getDPr() {
        CTDPr cTDPr;
        synchronized (monitor()) {
            check_orphaned();
            CTDPr target = (CTDPr) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTDPr = target == null ? null : target;
        }
        return cTDPr;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTD
    public boolean isSetDPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTD
    public void setDPr(CTDPr dPr) {
        generatedSetterHelperImpl(dPr, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTD
    public CTDPr addNewDPr() {
        CTDPr target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDPr) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTD
    public void unsetDPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTD
    public List<CTOMathArg> getEList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTDImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTDImpl.this.getEArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTDImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTDImpl.this.setEArray(((Integer) obj).intValue(), (CTOMathArg) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTDImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTDImpl.this.insertNewE(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTDImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTDImpl.this.removeE(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTDImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTDImpl.this.sizeOfEArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTD
    public CTOMathArg[] getEArray() {
        return (CTOMathArg[]) getXmlObjectArray(PROPERTY_QNAME[1], new CTOMathArg[0]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTD
    public CTOMathArg getEArray(int i) {
        CTOMathArg target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOMathArg) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTD
    public int sizeOfEArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTD
    public void setEArray(CTOMathArg[] eArray) {
        check_orphaned();
        arraySetterHelper(eArray, PROPERTY_QNAME[1]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTD
    public void setEArray(int i, CTOMathArg e) {
        generatedSetterHelperImpl(e, PROPERTY_QNAME[1], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTD
    public CTOMathArg insertNewE(int i) {
        CTOMathArg target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOMathArg) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTD
    public CTOMathArg addNewE() {
        CTOMathArg target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOMathArg) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTD
    public void removeE(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }
}
