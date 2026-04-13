package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumData;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumRef;

/* loaded from: classes11.dex */
public class CTNumDataSourceImpl extends XmlComplexContentImpl implements CTNumDataSource {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_CHART, "numRef"), new QName(XSSFRelation.NS_CHART, "numLit")};
    private static final long serialVersionUID = 1;

    public CTNumDataSourceImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTNumDataSource
    public CTNumRef getNumRef() {
        CTNumRef cTNumRef;
        synchronized (monitor()) {
            check_orphaned();
            CTNumRef target = (CTNumRef) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTNumRef = target == null ? null : target;
        }
        return cTNumRef;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTNumDataSource
    public boolean isSetNumRef() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTNumDataSource
    public void setNumRef(CTNumRef numRef) {
        generatedSetterHelperImpl(numRef, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTNumDataSource
    public CTNumRef addNewNumRef() {
        CTNumRef target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTNumRef) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTNumDataSource
    public void unsetNumRef() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTNumDataSource
    public CTNumData getNumLit() {
        CTNumData cTNumData;
        synchronized (monitor()) {
            check_orphaned();
            CTNumData target = (CTNumData) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTNumData = target == null ? null : target;
        }
        return cTNumData;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTNumDataSource
    public boolean isSetNumLit() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = true;
            if (get_store().count_elements(PROPERTY_QNAME[1]) == 0) {
                z = false;
            }
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTNumDataSource
    public void setNumLit(CTNumData numLit) {
        generatedSetterHelperImpl(numLit, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTNumDataSource
    public CTNumData addNewNumLit() {
        CTNumData target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTNumData) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTNumDataSource
    public void unsetNumLit() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }
}
