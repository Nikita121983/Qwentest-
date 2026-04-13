package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalcChain;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CalcChainDocument;

/* loaded from: classes12.dex */
public class CalcChainDocumentImpl extends XmlComplexContentImpl implements CalcChainDocument {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "calcChain")};
    private static final long serialVersionUID = 1;

    public CalcChainDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CalcChainDocument
    public CTCalcChain getCalcChain() {
        CTCalcChain cTCalcChain;
        synchronized (monitor()) {
            check_orphaned();
            CTCalcChain target = (CTCalcChain) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTCalcChain = target == null ? null : target;
        }
        return cTCalcChain;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CalcChainDocument
    public void setCalcChain(CTCalcChain calcChain) {
        generatedSetterHelperImpl(calcChain, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CalcChainDocument
    public CTCalcChain addNewCalcChain() {
        CTCalcChain target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTCalcChain) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }
}
