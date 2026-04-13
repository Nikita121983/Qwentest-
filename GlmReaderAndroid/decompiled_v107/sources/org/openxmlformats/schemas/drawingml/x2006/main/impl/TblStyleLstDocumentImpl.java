package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleList;
import org.openxmlformats.schemas.drawingml.x2006.main.TblStyleLstDocument;

/* loaded from: classes11.dex */
public class TblStyleLstDocumentImpl extends XmlComplexContentImpl implements TblStyleLstDocument {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "tblStyleLst")};
    private static final long serialVersionUID = 1;

    public TblStyleLstDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.TblStyleLstDocument
    public CTTableStyleList getTblStyleLst() {
        CTTableStyleList cTTableStyleList;
        synchronized (monitor()) {
            check_orphaned();
            CTTableStyleList target = (CTTableStyleList) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTTableStyleList = target == null ? null : target;
        }
        return cTTableStyleList;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.TblStyleLstDocument
    public void setTblStyleLst(CTTableStyleList tblStyleLst) {
        generatedSetterHelperImpl(tblStyleLst, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.TblStyleLstDocument
    public CTTableStyleList addNewTblStyleLst() {
        CTTableStyleList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTableStyleList) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }
}
