package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrDefault;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrGeneral;

/* loaded from: classes12.dex */
public class CTPPrDefaultImpl extends XmlComplexContentImpl implements CTPPrDefault {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "pPr")};
    private static final long serialVersionUID = 1;

    public CTPPrDefaultImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrDefault
    public CTPPrGeneral getPPr() {
        CTPPrGeneral cTPPrGeneral;
        synchronized (monitor()) {
            check_orphaned();
            CTPPrGeneral target = (CTPPrGeneral) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTPPrGeneral = target == null ? null : target;
        }
        return cTPPrGeneral;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrDefault
    public boolean isSetPPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrDefault
    public void setPPr(CTPPrGeneral pPr) {
        generatedSetterHelperImpl(pPr, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrDefault
    public CTPPrGeneral addNewPPr() {
        CTPPrGeneral target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPPrGeneral) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrDefault
    public void unsetPPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }
}
