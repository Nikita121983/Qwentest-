package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblGrid;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblGridChange;

/* loaded from: classes12.dex */
public class CTTblGridImpl extends CTTblGridBaseImpl implements CTTblGrid {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "tblGridChange")};
    private static final long serialVersionUID = 1;

    public CTTblGridImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblGrid
    public CTTblGridChange getTblGridChange() {
        CTTblGridChange cTTblGridChange;
        synchronized (monitor()) {
            check_orphaned();
            CTTblGridChange target = get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTTblGridChange = target == null ? null : target;
        }
        return cTTblGridChange;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblGrid
    public boolean isSetTblGridChange() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblGrid
    public void setTblGridChange(CTTblGridChange tblGridChange) {
        generatedSetterHelperImpl(tblGridChange, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblGrid
    public CTTblGridChange addNewTblGridChange() {
        CTTblGridChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblGrid
    public void unsetTblGridChange() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }
}
