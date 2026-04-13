package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.ChartsheetDocument;

/* loaded from: classes12.dex */
public class ChartsheetDocumentImpl extends XmlComplexContentImpl implements ChartsheetDocument {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "chartsheet")};
    private static final long serialVersionUID = 1;

    public ChartsheetDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.ChartsheetDocument
    public CTChartsheet getChartsheet() {
        CTChartsheet cTChartsheet;
        synchronized (monitor()) {
            check_orphaned();
            CTChartsheet target = (CTChartsheet) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTChartsheet = target == null ? null : target;
        }
        return cTChartsheet;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.ChartsheetDocument
    public void setChartsheet(CTChartsheet chartsheet) {
        generatedSetterHelperImpl(chartsheet, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.ChartsheetDocument
    public CTChartsheet addNewChartsheet() {
        CTChartsheet target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTChartsheet) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }
}
