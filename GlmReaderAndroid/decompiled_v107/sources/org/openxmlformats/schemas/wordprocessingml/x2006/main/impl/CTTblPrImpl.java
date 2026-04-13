package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrChange;

/* loaded from: classes12.dex */
public class CTTblPrImpl extends CTTblPrBaseImpl implements CTTblPr {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "tblPrChange")};
    private static final long serialVersionUID = 1;

    public CTTblPrImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr
    public CTTblPrChange getTblPrChange() {
        CTTblPrChange cTTblPrChange;
        synchronized (monitor()) {
            check_orphaned();
            CTTblPrChange target = get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTTblPrChange = target == null ? null : target;
        }
        return cTTblPrChange;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr
    public boolean isSetTblPrChange() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr
    public void setTblPrChange(CTTblPrChange tblPrChange) {
        generatedSetterHelperImpl(tblPrChange, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr
    public CTTblPrChange addNewTblPrChange() {
        CTTblPrChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr
    public void unsetTblPrChange() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }
}
