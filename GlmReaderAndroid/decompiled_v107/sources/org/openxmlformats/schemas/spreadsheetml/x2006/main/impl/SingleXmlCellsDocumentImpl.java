package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSingleXmlCells;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.SingleXmlCellsDocument;

/* loaded from: classes12.dex */
public class SingleXmlCellsDocumentImpl extends XmlComplexContentImpl implements SingleXmlCellsDocument {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "singleXmlCells")};
    private static final long serialVersionUID = 1;

    public SingleXmlCellsDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.SingleXmlCellsDocument
    public CTSingleXmlCells getSingleXmlCells() {
        CTSingleXmlCells cTSingleXmlCells;
        synchronized (monitor()) {
            check_orphaned();
            CTSingleXmlCells target = (CTSingleXmlCells) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTSingleXmlCells = target == null ? null : target;
        }
        return cTSingleXmlCells;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.SingleXmlCellsDocument
    public void setSingleXmlCells(CTSingleXmlCells singleXmlCells) {
        generatedSetterHelperImpl(singleXmlCells, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.SingleXmlCellsDocument
    public CTSingleXmlCells addNewSingleXmlCells() {
        CTSingleXmlCells target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSingleXmlCells) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }
}
