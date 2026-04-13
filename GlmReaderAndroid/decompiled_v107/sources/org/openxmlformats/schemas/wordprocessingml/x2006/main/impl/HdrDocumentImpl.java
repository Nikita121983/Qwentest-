package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHdrFtr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.HdrDocument;

/* loaded from: classes12.dex */
public class HdrDocumentImpl extends XmlComplexContentImpl implements HdrDocument {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "hdr")};
    private static final long serialVersionUID = 1;

    public HdrDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.HdrDocument
    public CTHdrFtr getHdr() {
        CTHdrFtr cTHdrFtr;
        synchronized (monitor()) {
            check_orphaned();
            CTHdrFtr target = (CTHdrFtr) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTHdrFtr = target == null ? null : target;
        }
        return cTHdrFtr;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.HdrDocument
    public void setHdr(CTHdrFtr hdr) {
        generatedSetterHelperImpl(hdr, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.HdrDocument
    public CTHdrFtr addNewHdr() {
        CTHdrFtr target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTHdrFtr) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }
}
