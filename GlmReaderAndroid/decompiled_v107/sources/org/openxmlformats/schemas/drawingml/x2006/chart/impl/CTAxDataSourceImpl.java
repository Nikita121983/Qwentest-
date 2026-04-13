package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTMultiLvlStrRef;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumData;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumRef;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTStrData;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTStrRef;

/* loaded from: classes11.dex */
public class CTAxDataSourceImpl extends XmlComplexContentImpl implements CTAxDataSource {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_CHART, "multiLvlStrRef"), new QName(XSSFRelation.NS_CHART, "numRef"), new QName(XSSFRelation.NS_CHART, "numLit"), new QName(XSSFRelation.NS_CHART, "strRef"), new QName(XSSFRelation.NS_CHART, "strLit")};
    private static final long serialVersionUID = 1;

    public CTAxDataSourceImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource
    public CTMultiLvlStrRef getMultiLvlStrRef() {
        CTMultiLvlStrRef cTMultiLvlStrRef;
        synchronized (monitor()) {
            check_orphaned();
            CTMultiLvlStrRef target = get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTMultiLvlStrRef = target == null ? null : target;
        }
        return cTMultiLvlStrRef;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource
    public boolean isSetMultiLvlStrRef() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource
    public void setMultiLvlStrRef(CTMultiLvlStrRef multiLvlStrRef) {
        generatedSetterHelperImpl(multiLvlStrRef, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource
    public CTMultiLvlStrRef addNewMultiLvlStrRef() {
        CTMultiLvlStrRef target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource
    public void unsetMultiLvlStrRef() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource
    public CTNumRef getNumRef() {
        CTNumRef cTNumRef;
        synchronized (monitor()) {
            check_orphaned();
            CTNumRef target = (CTNumRef) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTNumRef = target == null ? null : target;
        }
        return cTNumRef;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource
    public boolean isSetNumRef() {
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource
    public void setNumRef(CTNumRef numRef) {
        generatedSetterHelperImpl(numRef, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource
    public CTNumRef addNewNumRef() {
        CTNumRef target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTNumRef) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource
    public void unsetNumRef() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource
    public CTNumData getNumLit() {
        CTNumData cTNumData;
        synchronized (monitor()) {
            check_orphaned();
            CTNumData target = (CTNumData) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTNumData = target == null ? null : target;
        }
        return cTNumData;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource
    public boolean isSetNumLit() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource
    public void setNumLit(CTNumData numLit) {
        generatedSetterHelperImpl(numLit, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource
    public CTNumData addNewNumLit() {
        CTNumData target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTNumData) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource
    public void unsetNumLit() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource
    public CTStrRef getStrRef() {
        CTStrRef cTStrRef;
        synchronized (monitor()) {
            check_orphaned();
            CTStrRef target = (CTStrRef) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTStrRef = target == null ? null : target;
        }
        return cTStrRef;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource
    public boolean isSetStrRef() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource
    public void setStrRef(CTStrRef strRef) {
        generatedSetterHelperImpl(strRef, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource
    public CTStrRef addNewStrRef() {
        CTStrRef target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTStrRef) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource
    public void unsetStrRef() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource
    public CTStrData getStrLit() {
        CTStrData cTStrData;
        synchronized (monitor()) {
            check_orphaned();
            CTStrData target = (CTStrData) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTStrData = target == null ? null : target;
        }
        return cTStrData;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource
    public boolean isSetStrLit() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource
    public void setStrLit(CTStrData strLit) {
        generatedSetterHelperImpl(strLit, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource
    public CTStrData addNewStrLit() {
        CTStrData target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTStrData) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource
    public void unsetStrLit() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }
}
