package org.openxmlformats.schemas.officeDocument.x2006.math.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTOMathArg;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTSSub;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTSSubPr;

/* loaded from: classes11.dex */
public class CTSSubImpl extends XmlComplexContentImpl implements CTSSub {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "sSubPr"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "e"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "sub")};
    private static final long serialVersionUID = 1;

    public CTSSubImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTSSub
    public CTSSubPr getSSubPr() {
        CTSSubPr cTSSubPr;
        synchronized (monitor()) {
            check_orphaned();
            CTSSubPr target = (CTSSubPr) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTSSubPr = target == null ? null : target;
        }
        return cTSSubPr;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTSSub
    public boolean isSetSSubPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTSSub
    public void setSSubPr(CTSSubPr sSubPr) {
        generatedSetterHelperImpl(sSubPr, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTSSub
    public CTSSubPr addNewSSubPr() {
        CTSSubPr target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSSubPr) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTSSub
    public void unsetSSubPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTSSub
    public CTOMathArg getE() {
        CTOMathArg cTOMathArg;
        synchronized (monitor()) {
            check_orphaned();
            CTOMathArg target = (CTOMathArg) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTOMathArg = target == null ? null : target;
        }
        return cTOMathArg;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTSSub
    public void setE(CTOMathArg e) {
        generatedSetterHelperImpl(e, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTSSub
    public CTOMathArg addNewE() {
        CTOMathArg target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOMathArg) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTSSub
    public CTOMathArg getSub() {
        CTOMathArg cTOMathArg;
        synchronized (monitor()) {
            check_orphaned();
            CTOMathArg target = (CTOMathArg) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTOMathArg = target == null ? null : target;
        }
        return cTOMathArg;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTSSub
    public void setSub(CTOMathArg sub) {
        generatedSetterHelperImpl(sub, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTSSub
    public CTOMathArg addNewSub() {
        CTOMathArg target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOMathArg) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }
}
