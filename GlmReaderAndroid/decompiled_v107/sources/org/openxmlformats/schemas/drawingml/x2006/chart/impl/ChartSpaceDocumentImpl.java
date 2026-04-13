package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace;
import org.openxmlformats.schemas.drawingml.x2006.chart.ChartSpaceDocument;

/* loaded from: classes11.dex */
public class ChartSpaceDocumentImpl extends XmlComplexContentImpl implements ChartSpaceDocument {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_CHART, "chartSpace")};
    private static final long serialVersionUID = 1;

    public ChartSpaceDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.ChartSpaceDocument
    public CTChartSpace getChartSpace() {
        CTChartSpace cTChartSpace;
        synchronized (monitor()) {
            check_orphaned();
            CTChartSpace target = (CTChartSpace) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTChartSpace = target == null ? null : target;
        }
        return cTChartSpace;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.ChartSpaceDocument
    public void setChartSpace(CTChartSpace chartSpace) {
        generatedSetterHelperImpl(chartSpace, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.ChartSpaceDocument
    public CTChartSpace addNewChartSpace() {
        CTChartSpace target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTChartSpace) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }
}
