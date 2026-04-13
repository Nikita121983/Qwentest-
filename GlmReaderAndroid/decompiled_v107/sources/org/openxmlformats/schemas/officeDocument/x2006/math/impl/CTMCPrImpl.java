package org.openxmlformats.schemas.officeDocument.x2006.math.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTInteger255;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTMCPr;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTXAlign;

/* loaded from: classes11.dex */
public class CTMCPrImpl extends XmlComplexContentImpl implements CTMCPr {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "count"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "mcJc")};
    private static final long serialVersionUID = 1;

    public CTMCPrImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMCPr
    public CTInteger255 getCount() {
        CTInteger255 cTInteger255;
        synchronized (monitor()) {
            check_orphaned();
            CTInteger255 target = (CTInteger255) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTInteger255 = target == null ? null : target;
        }
        return cTInteger255;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMCPr
    public boolean isSetCount() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMCPr
    public void setCount(CTInteger255 count) {
        generatedSetterHelperImpl(count, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMCPr
    public CTInteger255 addNewCount() {
        CTInteger255 target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTInteger255) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMCPr
    public void unsetCount() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMCPr
    public CTXAlign getMcJc() {
        CTXAlign cTXAlign;
        synchronized (monitor()) {
            check_orphaned();
            CTXAlign target = (CTXAlign) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTXAlign = target == null ? null : target;
        }
        return cTXAlign;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMCPr
    public boolean isSetMcJc() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = true;
            if (get_store().count_elements(PROPERTY_QNAME[1]) == 0) {
                z = false;
            }
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMCPr
    public void setMcJc(CTXAlign mcJc) {
        generatedSetterHelperImpl(mcJc, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMCPr
    public CTXAlign addNewMcJc() {
        CTXAlign target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTXAlign) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMCPr
    public void unsetMcJc() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }
}
