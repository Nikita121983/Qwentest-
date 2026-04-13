package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.WorksheetDocument;

/* loaded from: classes12.dex */
public class WorksheetDocumentImpl extends XmlComplexContentImpl implements WorksheetDocument {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "worksheet")};
    private static final long serialVersionUID = 1;

    public WorksheetDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.WorksheetDocument
    public CTWorksheet getWorksheet() {
        CTWorksheet cTWorksheet;
        synchronized (monitor()) {
            check_orphaned();
            CTWorksheet target = (CTWorksheet) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTWorksheet = target == null ? null : target;
        }
        return cTWorksheet;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.WorksheetDocument
    public void setWorksheet(CTWorksheet worksheet) {
        generatedSetterHelperImpl(worksheet, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.WorksheetDocument
    public CTWorksheet addNewWorksheet() {
        CTWorksheet target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTWorksheet) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }
}
