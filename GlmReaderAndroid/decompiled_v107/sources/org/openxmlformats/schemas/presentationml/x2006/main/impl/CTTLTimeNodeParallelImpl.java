package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeNodeParallel;

/* loaded from: classes11.dex */
public class CTTLTimeNodeParallelImpl extends XmlComplexContentImpl implements CTTLTimeNodeParallel {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_PRESENTATIONML, "cTn")};
    private static final long serialVersionUID = 1;

    public CTTLTimeNodeParallelImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeNodeParallel
    public CTTLCommonTimeNodeData getCTn() {
        CTTLCommonTimeNodeData cTTLCommonTimeNodeData;
        synchronized (monitor()) {
            check_orphaned();
            CTTLCommonTimeNodeData target = (CTTLCommonTimeNodeData) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTTLCommonTimeNodeData = target == null ? null : target;
        }
        return cTTLCommonTimeNodeData;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeNodeParallel
    public void setCTn(CTTLCommonTimeNodeData cTn) {
        generatedSetterHelperImpl(cTn, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeNodeParallel
    public CTTLCommonTimeNodeData addNewCTn() {
        CTTLCommonTimeNodeData target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTLCommonTimeNodeData) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }
}
