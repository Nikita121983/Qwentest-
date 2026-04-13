package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSst;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.SstDocument;

/* loaded from: classes12.dex */
public class SstDocumentImpl extends XmlComplexContentImpl implements SstDocument {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "sst")};
    private static final long serialVersionUID = 1;

    public SstDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.SstDocument
    public CTSst getSst() {
        CTSst cTSst;
        synchronized (monitor()) {
            check_orphaned();
            CTSst target = (CTSst) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTSst = target == null ? null : target;
        }
        return cTSst;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.SstDocument
    public void setSst(CTSst sst) {
        generatedSetterHelperImpl(sst, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.SstDocument
    public CTSst addNewSst() {
        CTSst target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSst) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }
}
