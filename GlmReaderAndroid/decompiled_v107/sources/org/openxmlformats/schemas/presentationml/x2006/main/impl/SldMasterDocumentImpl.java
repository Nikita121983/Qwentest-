package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMaster;
import org.openxmlformats.schemas.presentationml.x2006.main.SldMasterDocument;

/* loaded from: classes11.dex */
public class SldMasterDocumentImpl extends XmlComplexContentImpl implements SldMasterDocument {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_PRESENTATIONML, "sldMaster")};
    private static final long serialVersionUID = 1;

    public SldMasterDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.SldMasterDocument
    public CTSlideMaster getSldMaster() {
        CTSlideMaster cTSlideMaster;
        synchronized (monitor()) {
            check_orphaned();
            CTSlideMaster target = (CTSlideMaster) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTSlideMaster = target == null ? null : target;
        }
        return cTSlideMaster;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.SldMasterDocument
    public void setSldMaster(CTSlideMaster sldMaster) {
        generatedSetterHelperImpl(sldMaster, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.SldMasterDocument
    public CTSlideMaster addNewSldMaster() {
        CTSlideMaster target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSlideMaster) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }
}
