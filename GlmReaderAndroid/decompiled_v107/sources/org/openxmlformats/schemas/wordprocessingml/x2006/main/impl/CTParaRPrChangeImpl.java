package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrChange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal;

/* loaded from: classes12.dex */
public class CTParaRPrChangeImpl extends CTTrackChangeImpl implements CTParaRPrChange {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "rPr")};
    private static final long serialVersionUID = 1;

    public CTParaRPrChangeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrChange
    public CTParaRPrOriginal getRPr() {
        CTParaRPrOriginal cTParaRPrOriginal;
        synchronized (monitor()) {
            check_orphaned();
            CTParaRPrOriginal target = (CTParaRPrOriginal) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTParaRPrOriginal = target == null ? null : target;
        }
        return cTParaRPrOriginal;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrChange
    public void setRPr(CTParaRPrOriginal rPr) {
        generatedSetterHelperImpl(rPr, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrChange
    public CTParaRPrOriginal addNewRPr() {
        CTParaRPrOriginal target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTParaRPrOriginal) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }
}
