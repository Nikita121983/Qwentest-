package org.openxmlformats.schemas.officeDocument.x2006.math.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTCtrlPr;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTSSubPr;

/* loaded from: classes11.dex */
public class CTSSubPrImpl extends XmlComplexContentImpl implements CTSSubPr {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "ctrlPr")};
    private static final long serialVersionUID = 1;

    public CTSSubPrImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTSSubPr
    public CTCtrlPr getCtrlPr() {
        CTCtrlPr cTCtrlPr;
        synchronized (monitor()) {
            check_orphaned();
            CTCtrlPr target = (CTCtrlPr) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTCtrlPr = target == null ? null : target;
        }
        return cTCtrlPr;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTSSubPr
    public boolean isSetCtrlPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTSSubPr
    public void setCtrlPr(CTCtrlPr ctrlPr) {
        generatedSetterHelperImpl(ctrlPr, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTSSubPr
    public CTCtrlPr addNewCtrlPr() {
        CTCtrlPr target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTCtrlPr) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTSSubPr
    public void unsetCtrlPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }
}
