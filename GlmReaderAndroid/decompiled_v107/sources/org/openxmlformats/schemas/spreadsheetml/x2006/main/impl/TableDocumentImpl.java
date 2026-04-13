package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTable;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.TableDocument;

/* loaded from: classes12.dex */
public class TableDocumentImpl extends XmlComplexContentImpl implements TableDocument {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "table")};
    private static final long serialVersionUID = 1;

    public TableDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.TableDocument
    public CTTable getTable() {
        CTTable cTTable;
        synchronized (monitor()) {
            check_orphaned();
            CTTable target = (CTTable) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTTable = target == null ? null : target;
        }
        return cTTable;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.TableDocument
    public void setTable(CTTable table) {
        generatedSetterHelperImpl(table, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.TableDocument
    public CTTable addNewTable() {
        CTTable target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTable) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }
}
