package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPrChange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPrOriginal;

/* loaded from: classes12.dex */
public class CTRPrChangeImpl extends CTTrackChangeImpl implements CTRPrChange {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "rPr")};
    private static final long serialVersionUID = 1;

    public CTRPrChangeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPrChange
    public CTRPrOriginal getRPr() {
        CTRPrOriginal cTRPrOriginal;
        synchronized (monitor()) {
            check_orphaned();
            CTRPrOriginal target = (CTRPrOriginal) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTRPrOriginal = target == null ? null : target;
        }
        return cTRPrOriginal;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPrChange
    public void setRPr(CTRPrOriginal rPr) {
        generatedSetterHelperImpl(rPr, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPrChange
    public CTRPrOriginal addNewRPr() {
        CTRPrOriginal target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRPrOriginal) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }
}
